package com.release.virtualinstructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //to start with yoga fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new meditation_fragment()).commit();

        //bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.yoga_nav :
                        selectedFragment = new yoga_fragment();
                        Toast.makeText(MainActivity.this, "Yoga", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.meditation_nav :
                        selectedFragment = new meditation_fragment();
                        Toast.makeText(MainActivity.this, "Meditation", Toast.LENGTH_SHORT).show();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
        });

    }
}
