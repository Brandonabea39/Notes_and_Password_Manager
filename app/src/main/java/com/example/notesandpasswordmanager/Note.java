package com.example.notesandpasswordmanager;

public class Note {
    private String mTitle;
    private String mContent;

    public Note() {
        // Default constructor required for calls to DataSnapshot.getValue(Note.class)
    }

    public Note(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
