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

public class Login extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    TextView logIn, createAnAccount, forgotPassword;
    ImageView googleLogin;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        inputEmail = findViewById(R.id.username_input);
        inputPassword = findViewById(R.id.pass);
        logIn = findViewById(R.id.user_login);
        createAnAccount = findViewById(R.id.create_account);
        forgotPassword = findViewById(R.id.forgot_password);
        googleLogin = findViewById(R.id.google_login);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        createAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Sign_up.class));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformLogin();

            }
        });
    }

    private void PerformLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (!email.matches(emailPattern))
        {
            inputEmail.setError("Enter Correct Email");
            inputEmail.requestFocus();
        }else if(password.isEmpty() || password.length()<6)
        {
            inputPassword.setError("Enter Correct Password");
        }else {
            progressDialog.setMessage("Please wait for Login....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNewActivity();
                        Toast.makeText(Login.this, "Login Completed", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
    private void sendUserToNewActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}