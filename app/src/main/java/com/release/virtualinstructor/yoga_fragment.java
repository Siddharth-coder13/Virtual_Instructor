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

import com.release.virtualinstructor.channel_yoga_list.yoga_channel1_list;
import com.release.virtualinstructor.channel_yoga_list.yoga_channel2_list;

// import java.util.ArrayList;

public class yoga_fragment extends Fragment {
//
//    SearchView searchView;
//    ListView yogaList;
//
//    ArrayList<String> list;
//    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.yoga_fragment,container,false);

        CardView asana1 = v.findViewById(R.id.asana1_yoga); TextView tada = v.findViewById(R.id.tadasana);
        CardView asana2 = v.findViewById(R.id.asana2_yoga); TextView vriksha = v.findViewById(R.id.vriksha);
        CardView asana3 = v.findViewById(R.id.asana3_yoga); TextView adho = v.findViewById(R.id.adho);
        CardView asana4 = v.findViewById(R.id.asana4_yoga); TextView bala = v.findViewById(R.id.bala);
        CardView asana5 = v.findViewById(R.id.asana5_yoga); TextView bhujanga = v.findViewById(R.id.bhujanga);
        CardView asana6 = v.findViewById(R.id.asana6_yoga); TextView kursi = v.findViewById(R.id.kursi);
        CardView asana7 = v.findViewById(R.id.asana7_yoga); TextView nauka = v.findViewById(R.id.nauka);
        CardView asana8 = v.findViewById(R.id.asana8_yoga); TextView paschim = v.findViewById(R.id.paschim);
        CardView asana9 = v.findViewById(R.id.asana9_yoga); TextView trikon = v.findViewById(R.id.trikon);

//        searchView = (SearchView) v.findViewById(R.id.yogaSearch);
//        yogaList = (ListView) v.findViewById(R.id.yogaList);
//
//        list = new ArrayList<String>();
//        list.add("Basic Poses for Beginners");
//        list.add("Instructor");
//
//        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list);
//        yogaList.setAdapter(adapter);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });

        RelativeLayout yogaChannel1 = v.findViewById(R.id.yoga_channel1_layout);
        RelativeLayout yogaChannel2 = v.findViewById(R.id.yoga_channel2_layout);

        yogaChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), yoga_channel1_list.class);
                startActivity(i);
            }
        });

        yogaChannel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), yoga_channel2_list.class);
                startActivity(i);
            }
        });



        //open environment from parent fragment
        open(asana1,tada);
        open(asana2,vriksha);
        open(asana3,adho);
        open(asana4,bala);
        open(asana5,bhujanga);
        open(asana6,kursi);
        open(asana7,nauka);
        open(asana8,paschim);
        open(asana9,trikon);

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
