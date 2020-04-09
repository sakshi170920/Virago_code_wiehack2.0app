package com.example.pcos_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //widgets
    private TextView Pcos_fitness;
    private TextView  mSignOut;
    private TextView predictor;


    //vars
    private static final String TAG = "MainActivity";

    private String currentUserEmail;


    //firebase authetication
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private final String user_info = "user_info";


    //Firebase Database
    private DatabaseReference user_data = FirebaseDatabase.getInstance().getReference(user_info);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");



        //getting the intentExtra passed from login and register activity
        String currentUserEmail = getIntent().getStringExtra("email");



        Log.d(TAG, "newwww " + currentUserEmail +"user");

        Log.d(TAG, "onCreate: Current Email" + currentUserEmail +"user");

        //set title to the action bar
        getSupportActionBar().setTitle("HELLO !");


        //find the widgets

        Pcos_fitness = findViewById(R.id.pcos_fitness);
        mSignOut = findViewById(R.id.sign_out);
        predictor = findViewById(R.id.PCOS_Predictor);

        predictor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPcosQUE();


            }
        });








        //set onClick Listener to signout button
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });




    }

    //log out the current user
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Bye! Bye! ", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "signOut: User signed out");
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void goToPcosQUE(){
        Intent i = new Intent(this,PcosQuestionActivity.class);
        startActivity(i);
    }



    @Override
    public void onStart() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        super.onStart();

        if(currentUser == null)
        {
            //no user is logged in, revert to login Activity
            startActivity(new Intent(MainActivity.this , LoginActivity.class));
            finish();
        }
        else
        {
            currentUserEmail = currentUser.getEmail();
        }
        Log.d(TAG, "curent user email : " + currentUserEmail );

    }



}
