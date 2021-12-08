
package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.project.Domain.userAddress;
import com.example.project.Helper.ManagementCart;
import com.example.project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class afterCheckout extends AppCompatActivity {

    EditText phone,address,number,locality;
    Button submit;
    userAddress u;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_checkout);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.Final_Address);
        number=findViewById(R.id.Pincode);
        locality=findViewById(R.id.Locality);
        submit=findViewById(R.id.button);
        u=new userAddress();
        database = FirebaseDatabase.getInstance();
        myRef =  database.getReference();
        managementCart = new ManagementCart(this);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phones=phone.getText().toString();
                String addresse=address.getText().toString();
                String numbers=number.getText().toString();
                String localities=locality.getText().toString();
                u.setAddress(addresse);
                u.setLocality(localities);
                u.setPhone(phones);
                u.setPincode(numbers);
                FirebaseFirestore dbroot;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                myRef.child("Users information").push().setValue(u);



                FirebaseMessaging.getInstance().subscribeToTopic("all");
                dbroot= FirebaseFirestore.getInstance();


                FcmNotificationsSender notificationsSender=new FcmNotificationsSender
                        ("/topics/all","ODER!","you got new order",getApplicationContext(),afterCheckout.this);
                notificationsSender.SendNotifications();
                database.getReference().child("Orders").setValue(managementCart.getListCard());
                Intent intent=new Intent(afterCheckout.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}