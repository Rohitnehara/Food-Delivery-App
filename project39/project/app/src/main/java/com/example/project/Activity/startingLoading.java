package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.R;

public class startingLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_loading);
        Thread td=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch ( Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(startingLoading.this,splash_activity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };td.start();
    }
}