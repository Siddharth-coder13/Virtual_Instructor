package com.release.virtualinstructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        CardView focus_meditation = v.findViewById(R.id.focus_meditation); TextView focus = v.findViewById(R.id.focus);
        CardView mindfullness_meditation = v.findViewById(R.id.mindfulness_meditation); TextView minfullness = v.findViewById(R.id.mindfulness);
        CardView spiritual_meditation = v.findViewById(R.id.spiritual_meditation); TextView spiritual = v.findViewById(R.id.spiritual);
        final CardView youtube = v.findViewById(R.id.youtube);

        channel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), channel1_med_list.class);
                startActivity(i);
            }
        });

        open(focus_meditation,focus);
        open(mindfullness_meditation,minfullness);
        open(spiritual_meditation,spiritual);

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
                startActivity(i);
            }
        });
    }


}
