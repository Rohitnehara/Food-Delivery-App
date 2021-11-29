package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.R;

public class loadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Thread td=new Thread(){
            public void run(){
            try{
        sleep(4000);
        }catch ( Exception e){
          e.printStackTrace();
        }
            finally {
                Intent intent=new Intent(loadingActivity.this,EntireMenu.class);
                startActivity(intent);
                finish();
            }

            }
        };td.start();
    }
}