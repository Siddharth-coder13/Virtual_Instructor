package com.release.virtualinstructor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.release.virtualinstructor.channel_yoga_list.yoga_channel1_list;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


public class register extends Fragment {


    private static final int RC_SIGN_IN = 123;
    private DatabaseReference databaseReference;
    private GoogleSignInClient mGoogleSignInClient;
    //private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private String idToken;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String name;
    private String email;

//    @Override
//    public void onStart(){
//        super.onStart();
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
//        if(account!=null){
//            Intent i = new Intent(getActivity(), meditation_fragment.class);
//            startActivity(i);
//        }
//    }

    @Override
    public void onStart() {
        super.onStart();
        if (authStateListener != null){
            FirebaseAuth.getInstance().signOut();
        }
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

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

        createRequest();
        v.findViewById(R.id.googleSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        signInButton = v.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//                startActivityForResult(intent,RC_SIGN_IN);
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

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

    private void createRequest() {
        // Configure Google Sign In
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

//        googleApiClient=new GoogleApiClient.Builder(getActivity())
//                .enableAutoManage(getActivity(), this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
//                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
//        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            //Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            // handleSignInResult(task);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result/*Task<GoogleSignInAccount> completedTask*/) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//           // updateUI(account);
//            Intent i = new Intent(getActivity(), meditation_fragment.class);
//            startActivity(i);
//
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//           // updateUI(null);
//            Toast.makeText(getActivity(), "Sorry auth failed.", Toast.LENGTH_SHORT).show();
//        }

        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            idToken = account.getIdToken();
            name = account.getDisplayName();
            email = account.getEmail();
            // you can store user data to SharedPreference  
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            firebaseAuthWithGoogle(credential);
        }else{
            // Google Sign In failed, update UI appropriately  
            Log.e(TAG, "Login Unsuccessful. "+result);
            Toast.makeText(getActivity(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(AuthCredential credential){

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                            gotoProfile();
                        }else{
                            Log.w(TAG, "signInWithCredential" + task.getException().getMessage());
                            task.getException().printStackTrace();
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void gotoProfile(){
        Intent intent = new Intent(getActivity(), meditation_fragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = (GoogleSignInAccount) task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account);
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                // ...
//                //Toast.makeText(getActivity(), "Google ", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//        }
//    }
//
//
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(getApplicationContext(),Profile.class);
//                            startActivity(intent);
//
//
//                        } else {
//                            Toast.makeText(MainActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
//
//
//                        }
//
//
//                        // ...
//                    }
//                });
//    }
}
