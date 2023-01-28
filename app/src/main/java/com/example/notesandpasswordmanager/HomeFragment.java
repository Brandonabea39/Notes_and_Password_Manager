package com.example.notesandpasswordmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView notesTextView = view.findViewById(R.id.notes_textview);
        notesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesFragment notesFragment = new NotesFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, notesFragment)
                        .addToBackStack("notesFragment")
                        .commit();
                // your code here
            }
        });
//        return view;

        TextView passwordTextView = view.findViewById(R.id.password_textview);
        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordFragment passwordFragment = new PasswordFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, passwordFragment)
                        .addToBackStack("passwordFragment")
                        .commit();
                // your code here
            }
        });
        return view;
    }
}