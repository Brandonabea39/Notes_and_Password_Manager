package com.example.notesandpasswordmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_up extends AppCompatActivity {

    EditText inputEmail, inputPassword, inputConfirmPassword;
    TextView signUp, alreadyHaveAnAccount;
    ImageView googleRegister;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        inputEmail = findViewById(R.id.username_input);
        inputPassword = findViewById(R.id.pass);
        inputConfirmPassword = findViewById(R.id.pass2);
        signUp = findViewById(R.id.sign_up);
        alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAccount);
        googleRegister = findViewById(R.id.google_register);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_up.this, Login.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });

    }

    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (!email.matches(emailPattern))
        {
            inputEmail.setError("Enter Correct Email");
            inputEmail.requestFocus();
        }else if(password.isEmpty() || password.length()<6)
        {
            inputPassword.setError("Enter Correct Password");
        }else if (!password.equals(confirmPassword))
        {
            inputConfirmPassword.setError("Passwords do not match");
        }else
        {
            progressDialog.setMessage("Please wait for Registration....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNewActivity();
                        Toast.makeText(Sign_up.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Sign_up.this, ""+task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }

    private void sendUserToNewActivity() {
        Intent intent = new Intent(Sign_up.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}