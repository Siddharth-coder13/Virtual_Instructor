package com.release.virtualinstructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> list;
    ArrayList<String> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView meditation = findViewById(R.id.meditation_tab);
        final ImageView yoga = findViewById(R.id.yoga_tab);

        //to start with yoga fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new meditation_fragment()).commit();

        //bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.yoga_nav :
                        selectedFragment = new yoga_fragment();
                        meditation.setVisibility(View.INVISIBLE);
                        yoga.setVisibility(View.VISIBLE);
                        break;
                    case R.id.meditation_nav :
                        selectedFragment = new meditation_fragment();
                        meditation.setVisibility(View.VISIBLE);
                        yoga.setVisibility(View.INVISIBLE);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
        });

        /*meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //search yoga or meditation
        /*SearchView searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.list_view);
        list = new ArrayList<>();
        temp = new ArrayList<>();
        temp.add("Yoga");
        temp.add("Meditation");
        temp.add("Channel1");
        temp.add("Channel2");
        temp.add("Meditation1");
        temp.add("Meditation2");
        temp.add("Meditation3");
        temp.add("Meditation4");
        temp.add("Meditation5");
        temp.add("Meditation6");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                list.clear();
                if(temp.contains(query)){
                    list.add(query);
                }
                else{
                    list.add("No match found");
                }
                listView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.addAll(temp);
                adapter.getFilter().filter(newText);
                listView.setAdapter(adapter);
                return false;
            }
         });*/
    }
}
