package com.release.virtualinstructor.med_channel_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.release.virtualinstructor.MainActivity;
import com.release.virtualinstructor.R;
import com.release.virtualinstructor.environment_list;

public class channel2_med_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel2_med_list);

        //close activity
        ImageView back = findViewById(R.id.med_channel2_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CardView med1 = findViewById(R.id.channel2_med1); TextView text1 = findViewById(R.id.med1_text);
        CardView med2 = findViewById(R.id.channel2_med2); TextView text2 = findViewById(R.id.med2_text);
        CardView med3 = findViewById(R.id.channel2_med3); TextView text3 = findViewById(R.id.med3_text);
        CardView med4 = findViewById(R.id.channel2_med4); TextView text4 = findViewById(R.id.med4_text);
        CardView med5 = findViewById(R.id.channel2_med5); TextView text5 = findViewById(R.id.med5_text);
        CardView med6 = findViewById(R.id.channel2_med6); TextView text6 = findViewById(R.id.med6_text);

        //To open environment_list when med card view is tapped.
        openEnvironmentList(med1,text1);
        openEnvironmentList(med2,text2);
        openEnvironmentList(med3,text3);
        openEnvironmentList(med4,text4);
        openEnvironmentList(med5,text5);
        openEnvironmentList(med6,text6);

    }

    private void openEnvironmentList(CardView med, final TextView text){
        med.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(channel2_med_list.this, environment_list.class);
                i.putExtra("heading",text.getText().toString());
                startActivity(i);
            }
        });
    }

}
