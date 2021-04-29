package com.release.virtualinstructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        // To start with login_fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new login_fragment()).commit();

        // Active buttons
        final Button register_fg = findViewById(R.id.register_fg);
        final Button login_fg = findViewById(R.id.login_fg);


        // Switch to register fragment
        register_fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new register()).commit();
                login_fg.setVisibility(View.VISIBLE);
                register_fg.setVisibility(View.GONE);
            }
        });

        // Switch to login fragment
        login_fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new login_fragment()).commit();
                login_fg.setVisibility(View.GONE);
                register_fg.setVisibility(View.VISIBLE);
            }
        });

    }
}
