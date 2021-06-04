package com.release.virtualinstructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Chakra_meditation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chakra_meditation);

        //close activity
        ImageView back = findViewById(R.id.chakra_meditation_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Variables
        CardView muladhara_chakra = findViewById(R.id.chakra_card1);
        CardView swadhisthana_chakra = findViewById(R.id.chakra_card2);
        CardView manipura_chakra = findViewById(R.id.chakra_card3);
        CardView anhata_chakra = findViewById(R.id.chakra_card4);
        CardView vishuddha_chakra = findViewById(R.id.chakra_card5);
        CardView ajna_chakra = findViewById(R.id.chakra_card6);

        OpenYoutube(muladhara_chakra, "BgauOifrvtc");
        OpenYoutube(swadhisthana_chakra, "B_I3xg0gyx4");
        OpenYoutube(manipura_chakra, "QnzgRDrkRHU");
        OpenYoutube(anhata_chakra, "pJ86HzNuTh4");
        OpenYoutube(vishuddha_chakra, "wdlfsoO0Bhc");
        OpenYoutube(ajna_chakra, "ABf7dgGl9ZM");
    }

    private void OpenYoutube(CardView card, final String VideoId){
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VideoId.isEmpty()) {
                    Toast.makeText(Chakra_meditation.this, "No video link found", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(Chakra_meditation.this, Youtube.class);
                    i.putExtra("VideoId", VideoId);
                    startActivity(i);
                }
            }
        });
    }
}
