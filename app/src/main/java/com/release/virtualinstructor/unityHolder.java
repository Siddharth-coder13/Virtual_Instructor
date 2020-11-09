package com.release.virtualinstructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.VirtualInstructor.mounteverest.UnityPlayerActivity;

public class unityHolder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_holder);

        Intent i = getIntent();
        String env = i.getStringExtra("code");

        Intent j = new Intent(unityHolder.this, UnityPlayerActivity.class);
        j.putExtra("Key",env);
        startActivity(j);
    }
}
