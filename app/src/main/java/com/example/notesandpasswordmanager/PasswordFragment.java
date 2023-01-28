package com.example.notesandpasswordmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;


public class PasswordFragment extends Fragment {
    private ListView passwordListView;
    private ArrayList<String> passwords;
//    private ArrayList<String> passwordList = new ArrayList<>();

    private PasswordAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_password, container, false);

        // Initialize the passwords list and adapter
        passwords = new ArrayList<String>();
        adapter = new PasswordAdapter(getContext(), passwords);

        // Bind the ListView to the adapter
        passwordListView = view.findViewById(R.id.password_list_view);
        passwordListView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = generatePassword();
                adapter.add(newPassword);
            }
        });

        return view;
    }
    private String generatePassword() {
        // generate random password
        String password = "";
        Random rand = new Random();
        String[] arr = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i",
                "j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D",
                "E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "!","@","#","$","%","^","&","*","(",")","-","_","+","="};
        int length = rand.nextInt(10) + 8; // Generate a random length between 8 and 18 characters
        for (int i = 0; i < length; i++) {
            password += arr[rand.nextInt(arr.length)]; // Select a random character from the array and add it to the password string
        }

        return password;
    }
}