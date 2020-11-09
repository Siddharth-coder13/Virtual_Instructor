package com.release.virtualinstructor.channel_yoga_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.release.virtualinstructor.R;
import com.release.virtualinstructor.environment_list;

public class yoga_channel2_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_channel2_list);

        //close activity
        ImageView back = findViewById(R.id.yoga_channel2_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CardView yoga1 = findViewById(R.id.channel2_yoga1);
        TextView text1 = findViewById(R.id.yoga1_text);

        CardView yoga2 = findViewById(R.id.channel2_yoga2);
        TextView text2 = findViewById(R.id.yoga2_text);

        CardView yoga3 = findViewById(R.id.channel2_yoga3);
        TextView text3 = findViewById(R.id.yoga3_text);

        CardView yoga4 = findViewById(R.id.channel2_yoga4);
        TextView text4 = findViewById(R.id.yoga4_text);

        CardView yoga5 = findViewById(R.id.channel2_yoga5);
        TextView text5 = findViewById(R.id.yoga5_text);

        CardView yoga6 = findViewById(R.id.channel2_yoga6);
        TextView text6 = findViewById(R.id.yoga6_text);

        CardView yoga7 = findViewById(R.id.channel2_yoga7);
        TextView text7 = findViewById(R.id.yoga7_text);

        CardView yoga8 = findViewById(R.id.channel2_yoga8);
        TextView text8 = findViewById(R.id.yoga8_text);

        CardView yoga9 = findViewById(R.id.channel2_yoga9);
        TextView text9 = findViewById(R.id.yoga9_text);

        //To open environment_list when med card view is tapped.
        openEnvironmentList(yoga1,text1);
        openEnvironmentList(yoga2,text2);
        openEnvironmentList(yoga3,text3);
        openEnvironmentList(yoga4,text4);
        openEnvironmentList(yoga5,text5);
        openEnvironmentList(yoga6,text6);
        openEnvironmentList(yoga7,text7);
        openEnvironmentList(yoga8,text8);
        openEnvironmentList(yoga9,text9);
    }

    private void openEnvironmentList(CardView yoga, final TextView text){
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(yoga_channel2_list.this, environment_list.class);
                i.putExtra("heading",text.getText().toString());
                startActivity(i);
            }
        });
    }
}
