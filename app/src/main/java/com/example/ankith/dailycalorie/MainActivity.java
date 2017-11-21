package com.example.ankith.dailycalorie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * login activity for the application
 */
    public class MainActivity extends AppCompatActivity {
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListener;
        Button logInButton;
        Button registerButton;
        EditText emailText;
        EditText passwordText;
        public static String USER_ID;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            logInButton = (Button) findViewById(R.id.loginButton);
            registerButton = (Button) findViewById(R.id.signUpButton);
            emailText = (EditText) findViewById(R.id.emailText);
            passwordText = (EditText) findViewById(R.id.passwordText);
            emailText.setHint("Email Address");
            passwordText.setHint("Password");
            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        Log.d("Yes", "onAuthStateChanged:signed_in:" + user.getUid());
                    } else {
                        // User is signed out
                        Log.d("Yes", "onAuthStateChanged:signed_out");
                    }
                    // ...
                }
            };

            logInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signIn(emailText.getText().toString(), passwordText.getText().toString());
                }
            });
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createAccount(emailText.getText().toString(), passwordText.getText().toString());
                }
            });
        }
        @Override
        public void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }
        @Override
        public void onStop() {
            super.onStop();
            if (mAuthListener != null) {
                mAuth.removeAuthStateListener(mAuthListener);
            }
        }
        public void createAccount (String email, String password){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Yes", "createUserWithEmail:onComplete:" + task.isSuccessful());
                            if (task.isSuccessful()){
                                User user = new User();
                                user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                user.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                DatabaseEngine.updateUserInDatabase(user);
                                Context context = getApplicationContext();
                                Intent intent = new Intent(context,HomeActivity.class);
                                context.startActivity(intent);
                            }
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Could not create account",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        public void signIn (String email, String password){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Yes", "signInWithEmail:onComplete:" + task.isSuccessful());
                            if (task.isSuccessful()){
                                Context context = getApplicationContext();
                                Intent intent = new Intent(context,HomeActivity.class);
                                context.startActivity(intent);
                            }
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("Yes", "signInWithEmail", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        public void getCurrentUser(){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                // Name, email address, and profile photo Url
                String name = user.getDisplayName();
                String email = user.getEmail();
                Uri photoUrl = user.getPhotoUrl();
                String uid = user.getUid();
            }
        }

    }
