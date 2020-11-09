package com.release.virtualinstructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.VirtualInstructor.mounteverest.UnityPlayerActivity;

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

        //open in vr
        CardView forest = findViewById(R.id.Forest);
        CardView mountain = findViewById(R.id.mountain);
        CardView space = findViewById(R.id.space);

        openVR(forest,"Forest");
        openVR(mountain,"MountEverest");
        openVR(space,"Space");

    }

    private void openVR(CardView cardView,final String text){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(environment_list.this, unityHolder.class);
                i.putExtra("code",text);
                startActivity(i);
            }
        });
    }
}
