package com.example.chatapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chatapp.databinding.ActivitySignUpAcitvityBinding;
import com.example.chatapp.module.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
//type Binding and you will get suggestion so  click on enter
    ActivitySignUpAcitvityBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //add this  line
        binding = ActivitySignUpAcitvityBinding.inflate(getLayoutInflater());
        //this  is  the original line for code which you will need to change
        //setContentView(R.layout.activity_sign_up_activity);
        //make  these changes in this line and use it
        setContentView(binding.getRoot());
        //this line is for removing top bar
     //  getSupportActionBar().hide();
        //creating intense for the auth and database
        auth =FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We are creating your account...");

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(this,gso);

        binding.SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
             auth.createUserWithEmailAndPassword(binding.EmailSignUp.getText().toString(),binding.PasswordSignUp.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                    // now check for if task is  complete or not
                     progressDialog.dismiss();
                    if(task.isSuccessful())
                    {

                        Users user = new Users(binding.usernameSignUp.getText().toString(),binding.EmailSignUp.getText().toString(),binding.PasswordSignUp.getText().toString());
                        //here we take result from fire base
                        String  id= task.getResult().getUser().getUid();
                        //this code writes result from firebase to the database

                        //here child("users") creates a child named as user
                        database.getReference().child("Users").child(id).setValue(user);
                       // Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                     //this line
                     // task.getException().getMessage()
                     //show default error msgs of firebase
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                 }
             });
            }
        });

        //code already have and account section
        binding.AlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LogIntent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(LogIntent);
            }
        });


        ///Code for signin user using google
        binding.googleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });

        ///this line keeps  user loged in
        if(auth.getCurrentUser()!=null)
        {
            Intent intt=new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intt);
        }
    }
    int RC_SIGN_IN=69;
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                          //  updateUI(null);
                        }
                    }
                });
    }


}
