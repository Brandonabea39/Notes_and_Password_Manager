package com.example.notesandpasswordmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PasswordAdapter extends ArrayAdapter<String> {
    public PasswordAdapter(Context context, ArrayList<String> passwords) {
        super(context, 0, passwords);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String password = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.password_item, parent, false);
        }
        // Lookup view for data population
        TextView passwordView = convertView.findViewById(R.id.password_view);
        Button deleteButton = convertView.findViewById(R.id.delete_password);
        // Populate the data into the template view using the data object
        passwordView.setText(password);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePassword(position);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

    private void deletePassword(int position) {
        // remove the password from the data source
        remove(getItem(position));
        // notify the adapter that the data set has changed
        notifyDataSetChanged();
    }
}
