package com.release.virtualinstructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class environment_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_list);

        //This java code displays the different types of virtual environment available to us for practicing yoga and meditation.

        //close activity
        ImageView back = findViewById(R.id.env_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //To display meditation name in heading
        Intent i = getIntent();
        String heading = i.getStringExtra("heading");


        TextView head = findViewById(R.id.heading);
        head.setText(heading);
    }
}
