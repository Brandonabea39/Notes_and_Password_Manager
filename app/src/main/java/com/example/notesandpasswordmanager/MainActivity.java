package com.example.notesandpasswordmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    NotesFragment notesFragment = new NotesFragment();
    PasswordFragment passwordFragment = new PasswordFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bottomNavigationView = findViewById(R.id.bottomnav);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack("homeFragment").commit();
                        return true;
                    case R.id.notes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notesFragment).addToBackStack("notesFragment").commit();
                        return true;
                    case R.id.password:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, passwordFragment).addToBackStack("passwordFragment").commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).addToBackStack("settingsFragment").commit();
                        return true;
                }
                return true;
            }
        });

    }
}