package com.release.virtualinstructor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class yoga_fragment extends Fragment {

    SearchView searchView;
    ListView yogaList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.yoga_fragment,container,false);


        searchView = (SearchView) v.findViewById(R.id.yogaSearch);
        yogaList = (ListView) v.findViewById(R.id.yogaList);

        list = new ArrayList<String>();
        list.add("Basic Poses for Beginners");
        list.add("Instructor");

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list);
        yogaList.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return v;
    }
}
