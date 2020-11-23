package com.release.virtualinstructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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



        channel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), channel1_med_list.class);
                startActivity(i);
            }
        });

        /*channel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), channel2_med_list.class);
                startActivity(i);
            }
        });*/


        return v;
    }


}
