package com.release.virtualinstructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class environment_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_list);

        TextView come = findViewById(R.id.coming_soon);

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
        String coming =  "c";
        coming = i.getStringExtra("coming");
        if(coming.equals("coming")){
            come.setVisibility(View.VISIBLE);
        }
        TextView head = findViewById(R.id.heading);
        head.setText(heading);

        //open in vr
        CardView forest = findViewById(R.id.Forest);
        CardView mountain = findViewById(R.id.mountain);
        CardView space = findViewById(R.id.space);


    }

    private void OpenYoutube(CardView cardView){
        Toast.makeText(this, "Environment is not added yet", Toast.LENGTH_SHORT).show();
    }
}
