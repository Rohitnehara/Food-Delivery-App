package com.example.project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class splash_activity extends AppCompatActivity {
private EditText email,passwpprd;
private TextView alreadt;
    FirebaseAuth auth;
    private Button signup,google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        email=findViewById(R.id.EmaillogIn);
        passwpprd=findViewById(R.id.passwordLogin);
        auth = FirebaseAuth.getInstance();
        signup=findViewById(R.id.SignUP);
        google=findViewById(R.id.googleSignUp);
        alreadt=findViewById(R.id.textView2);
//        progressDialog = new ProgressDialog(LoginActivity.this);
//        progressDialog.setTitle("Logging In");
//        progressDialog.setMessage("We are creating your account...");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressDialog.show();
                if(TextUtils.isEmpty(passwpprd.getText().toString())&&TextUtils.isEmpty(email.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(), "bro  kuch tho likh", Toast.LENGTH_SHORT).show();
                    }
                auth.signInWithEmailAndPassword(email.getText().toString(),
                        passwpprd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            Intent intent =new Intent(splash_activity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

       alreadt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(splash_activity.this,IntroActivity.class);
                startActivity(in);
            }
        });
    }
}

