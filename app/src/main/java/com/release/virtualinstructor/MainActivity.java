package com.release.virtualinstructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> list;
    ArrayList<String> temp;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView meditation = findViewById(R.id.meditation_tab);
        final ImageView yoga = findViewById(R.id.yoga_tab);
        final LinearLayout share = findViewById(R.id.share);
        //ImageView three_dots = findViewById(R.id.three_dots);

        // Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = mAuth.getCurrentUser();
                if(user!=null){
                }
                else{
                    startActivity(new Intent(MainActivity.this, login_page.class));
                    finish();
                }

            }
        });

        //to start with meditation fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new meditation_fragment()).commit();

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

        //show share button
        /*three_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(share.getVisibility()==View.GONE){
                    share.setVisibility(View.VISIBLE);
                }else if(share.getVisibility()==View.VISIBLE){
                    share.setVisibility(View.GONE);
                }
            }
        });*/


        //share the app link using share button
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Virtual Instructor");
                    String shareMessage= "\nDownload the app and meditate in virtual reality.\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
    }
}
