package com.example.notesandpasswordmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Note> mNotes;

    public NotesAdapter(List<Note> notes) {
        mNotes = notes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.noteTitleTextView.setText(note.getTitle());
        holder.noteContentTextView.setText(note.getContent());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                mNotes.remove(position);
                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTitleTextView;
        public TextView noteContentTextView;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            noteTitleTextView = itemView.findViewById(R.id.note_title_view);
            noteContentTextView = itemView.findViewById(R.id.note_content_view);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

}

