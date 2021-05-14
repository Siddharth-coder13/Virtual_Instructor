package com.release.virtualinstructor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends Fragment {


    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_register, container, false);

        // Variables
        final EditText name = v.findViewById(R.id.name);
        final EditText age = v.findViewById(R.id.age);
        final EditText email = v.findViewById(R.id.email_id);
        final EditText password = v.findViewById(R.id.password);
        final RadioGroup radioGroup = v.findViewById(R.id.gender);
        Button register = v.findViewById(R.id.register);

        final Button register_fg = getActivity().findViewById(R.id.register_fg);
        final Button login_fg = getActivity().findViewById(R.id.login_fg);

        // Firebase authentication
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //final FirebaseDatabase database = FirebaseDatabase.getInstance();





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Age = age.getText().toString().trim();
                String Name = name.getText().toString().trim();
                int gender_id = radioGroup.getCheckedRadioButtonId();
                RadioButton gender = v.findViewById(gender_id);

                if(Name.isEmpty()){
                    name.setError("Name is required");
                    name.requestFocus();
                    return;
                }

                if(Age.isEmpty()){
                    age.setError("Age is required");
                    age.requestFocus();
                    return;
                }

                if(email_id.isEmpty()){
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email_id).matches()){
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    return;
                }

                if(Password.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }

                if(Password.length()<8){
                    password.setError("Minimum length of password should be 8");
                    password.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email_id, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser user = mAuth.getCurrentUser();
                            //String id = user.getUid();
                            //databaseReference = database.getReference().child("Users").child(id);

                            Toast.makeText(getActivity(), "User registered successfully", Toast.LENGTH_SHORT).show();

                            // Add user to database


                            // Switch to login fragment
                            getFragmentManager().beginTransaction().replace(R.id.frame_container, new login_fragment()).commit();
                            login_fg.setVisibility(View.VISIBLE);
                            register_fg.setVisibility(View.GONE);

                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){

                                Toast.makeText(getActivity(), "User already registered", Toast.LENGTH_SHORT).show();
                            }else{

                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });

        return v;
    }


}
