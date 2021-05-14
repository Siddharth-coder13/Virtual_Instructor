package com.release.virtualinstructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.release.virtualinstructor.med_channel_list.channel1_med_list;
import com.release.virtualinstructor.med_channel_list.channel2_med_list;

public class meditation_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.meditation_fragment,container,false);


        RelativeLayout channel1 = v.findViewById(R.id.med_channel1_layout);
        RelativeLayout channel2 = v.findViewById(R.id.med_channel2_layout);
        RelativeLayout chakra_meditation = v.findViewById(R.id.chakra_meditation_layout);
        CardView focus_meditation = v.findViewById(R.id.focus_meditation); TextView focus = v.findViewById(R.id.focus1);
        CardView mindfullness_meditation = v.findViewById(R.id.mindfulness_meditation); TextView minfullness = v.findViewById(R.id.mindfulness1);
        CardView spiritual_meditation = v.findViewById(R.id.spiritual_meditation); TextView spiritual = v.findViewById(R.id.spiritual1);

        // Variables
        CardView muladhara_chakra = v.findViewById(R.id.chakra_card1);
        CardView swadhisthana_chakra = v.findViewById(R.id.chakra_card2);
        CardView manipura_chakra = v.findViewById(R.id.chakra_card3);
        CardView anhata_chakra = v.findViewById(R.id.chakra_card4);
        CardView vishuddha_chakra = v.findViewById(R.id.chakra_card5);
        CardView ajna_chakra = v.findViewById(R.id.chakra_card6);


        channel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), channel1_med_list.class);
                startActivity(i);
            }
        });

        channel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        chakra_meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Chakra_meditation.class);
                startActivity(i);
            }
        });

        open(focus_meditation,focus);
        open(mindfullness_meditation,minfullness);
        open(spiritual_meditation,spiritual);
        // Open youtube
        OpenYoutube(manipura_chakra, "");
        OpenYoutube(muladhara_chakra, "AuGIHznyuXk");
        OpenYoutube(swadhisthana_chakra, "");
        OpenYoutube(anhata_chakra, "");
        OpenYoutube(vishuddha_chakra, "");
        OpenYoutube(ajna_chakra, "");

        /*channel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), channel2_med_list.class);
                startActivity(i);
            }
        });*/

        /*youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), youtube.class));
            }
        });*/


        return v;
    }

    private void open(CardView cardView, final TextView heading){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),environment_list.class);
                i.putExtra("heading",heading.getText().toString());
                i.putExtra("coming", "false");
                startActivity(i);
            }
        });
    }

    private void OpenYoutube(CardView card, final String VideoId){
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VideoId.isEmpty()) {
                    Toast.makeText(getActivity(), "No video link found", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(getActivity(), Youtube.class);
                    i.putExtra("VideoId", VideoId);
                    startActivity(i);
                }
            }
        });
    }

}
