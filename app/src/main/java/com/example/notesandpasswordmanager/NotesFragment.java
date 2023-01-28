package com.example.notesandpasswordmanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {
    private EditText mNoteTitleEditText;
    private EditText mNoteContentEditText;
    private FloatingActionButton mAddNoteFab;
    private RecyclerView mNotesRecyclerView;
    private NotesAdapter mNotesAdapter;

    private List<Note> mNotes = new ArrayList<>();

    private FirebaseFirestore mFirestore;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_notes, container, false);

        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        mNoteTitleEditText = view.findViewById(R.id.note_title);
        mNoteContentEditText = view.findViewById(R.id.note_content);
        mAddNoteFab = view.findViewById(R.id.add_note_fab);
        mNotesRecyclerView = view.findViewById(R.id.notes_recycler_view);

        mFirestore = FirebaseFirestore.getInstance();

        mAddNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mNoteTitleEditText.getText().toString();
                String content = mNoteContentEditText.getText().toString();
                if (!title.isEmpty() && !content.isEmpty()) {
                    Note note = new Note(title, content);
                    mNotes.add(note);
                    mNotesAdapter.notifyDataSetChanged();
                    mNoteTitleEditText.setText("");
                    mNoteContentEditText.setText("");

                    mFirestore.collection("notes").add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("NotesFragment", "Note added with ID: " + documentReference.getId());
                        }
                    });
                }else {
                    Toast.makeText(getContext(), "Note title and content cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mNotesAdapter = new NotesAdapter(mNotes);
        mNotesRecyclerView.setAdapter(mNotesAdapter);
        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }

}