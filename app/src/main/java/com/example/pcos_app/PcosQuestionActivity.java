package com.example.pcos_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PcosQuestionActivity extends AppCompatActivity {
    private static final String TAG = "PcosQueActivity";
    private  String key;

    private static final String que = "que-data";

    private TextView diagnosis;
    private TextView irregular_perios;

    private TextView conceive;
    private TextView mood;

    private TextView exercise;
private RelativeLayout submit ;


    //firebase database
    private DatabaseReference quedata;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcos_question);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //get the chatkey and create a reference to it
        key = getIntent().getStringExtra("key");
        Log.d(TAG, " passing key " + key );
        quedata = FirebaseDatabase.getInstance().getReference().child(que).child("user_info").child(key);
        Log.d(TAG, " passing key 123 " + key );

        mood = findViewById(R.id.mood);
        diagnosis = findViewById(R.id.diagnosis);
        exercise = findViewById(R.id.exercise);

        irregular_perios = findViewById(R.id.periods);
        conceive = findViewById(R.id.conceive);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = quedata.push().getKey();
                PCOSQuestions p = new PCOSQuestions(mood.getText().toString() ,diagnosis.getText().toString(),irregular_perios.getText().toString(),exercise.getText().toString(),conceive.getText().toString());


            }
        });






    }
}
