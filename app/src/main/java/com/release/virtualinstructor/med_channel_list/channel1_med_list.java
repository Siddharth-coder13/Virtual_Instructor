package com.release.virtualinstructor.med_channel_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.release.virtualinstructor.MainActivity;
import com.release.virtualinstructor.R;
import com.release.virtualinstructor.Youtube;
import com.release.virtualinstructor.environment_list;


public class channel1_med_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel1_med_list);

        //close activity
        ImageView back = findViewById(R.id.med_channel1_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //This java file is used to navigate to environment_list activity

        CardView med1 = findViewById(R.id.main_building);
        CardView med2 = findViewById(R.id.vigyan_kunj);

        //To oepn youtube video
        OpenYoutube(med1, "kDW97yaxCss");
        OpenYoutube(med2, "eonqVdpqEDU");


    }

    /*private void openEnvironmentList(CardView med, final TextView text){
        med.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(channel1_med_list.this, environment_list.class);
                i.putExtra("heading",text.getText().toString());
                i.putExtra("coming", "false");
                startActivity(i);
            }
        });
    }*/

    private void OpenYoutube(CardView card, final String VideoId){
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VideoId.isEmpty()) {
                    Toast.makeText(channel1_med_list.this, "No video link found", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(channel1_med_list.this, Youtube.class);
                    i.putExtra("VideoId", VideoId);
                    startActivity(i);
                }
            }
        });
    }

}
