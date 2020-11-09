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

        return v;
    }
}
