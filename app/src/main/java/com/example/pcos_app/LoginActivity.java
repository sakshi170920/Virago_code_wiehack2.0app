package com.example.pcos_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
Login Activity
*/
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    //widgets
    private EditText mEmail, mPassword;
    private LinearLayout mRegisterTextView;
    private ProgressBar mProgressBar;
    private RelativeLayout mLoginButton;
    //Firebase Authentication
    private FirebaseAuth mAuth;
    //firebase
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hide the actionBar
        getSupportActionBar().hide();
        mEmail = findViewById(R.id.username);
        mPassword =findViewById(R.id.password);
        mRegisterTextView = findViewById(R.id.register_parent);
        mProgressBar = findViewById(R.id.loading);
        mLoginButton = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        //add onclickListener to login Button
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
        //add onClickListener to register TextView
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //revert the user to register activity
                startActivity(new Intent(LoginActivity.this ,
                        RegisterActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //if the user is already is logged in, revert him directly to MainActivity
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            //user is logged in
            Intent intent = new Intent(LoginActivity.this,
                    MainActivity.class);
            intent.putExtra("email", currentUser.getEmail());
            startActivity(intent);
            finish();//insta kholke back krne pe login pg nhi ata direct app se bahar nikl jate uske liye finish
        }
        else{
            //user is not logged in
            Toast.makeText(this, "User Not Login", Toast.LENGTH_SHORT).show();
        }
    }
    //method to logIn existing users
    private void logIn()
    {

        final String email, password;
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        //make sure the password and email are not empty
        if(!email.equals("") && !password.equals(""))
        {
            mProgressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email , password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            if(task.isSuccessful())
                            {
                                Intent intent =
                                        new Intent(LoginActivity.this
                                                , MainActivity.class);
                                intent.putExtra("email" , email);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                //login unsuccessful
                                Toast.makeText(LoginActivity.this, "Login Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
