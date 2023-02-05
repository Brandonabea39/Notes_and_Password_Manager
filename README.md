# Notes_and_Password_Manager
This app helps users to add notes and manage passwords
Overview
The Notes and Password Manager is a mobile application that allows users to store and manage their notes and passwords in a secure and organized manner. The app features a user-friendly interface and includes functionalities such as creating, editing, and deleting notes, as well as creating and managing passwords.
 ![image](https://user-images.githubusercontent.com/71942720/216817135-57d63f10-a488-4c73-8cfa-beaba9ee7e00.png)


Code Structure
The project is built using the Java programming language and the Android SDK.
The app follows a standard Android app structure, with activities serving as the main screens and fragments handling specific functionalities such as creating, editing, and deleting notes.
The app makes use of a Firebase database to store and retrieve user data.
The app also uses a third-party library for password encryption and decryption.

Main Classes and Methods

MainActivity: This is the starting point of the app, it hosts the navigation drawer and handles the app's navigation flow.

NotesFragment: This fragment is responsible for displaying the list of notes and handling user interactions such as creating, editing, and deleting notes.

NotesAdapter: This is used to connect and display the various notes through a RecyclerView.

NoteEditFragment: This fragment is responsible for displaying the form for creating and editing notes and handling user input.

PasswordsFragment: This fragment is responsible for displaying the list of passwords and handling user interactions such as creating, editing, and deleting passwords.

PasswordAdapter: This class is used to display the password using a ListView

PasswordEditFragment: This fragment is responsible for displaying the form for creating and editing passwords and handling user input.
