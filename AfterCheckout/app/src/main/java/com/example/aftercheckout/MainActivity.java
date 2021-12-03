package com.example.aftercheckout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aftercheckout.model.user_info;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText phone,address,number,locality;
Button submit;
user_info u=new user_info();
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.Final_Address);
        number=findViewById(R.id.Pincode);
        locality=findViewById(R.id.Locality);
        submit=findViewById(R.id.button);

        database = FirebaseDatabase.getInstance();
        myRef =  database.getReference();
        String phones=phone.getText().toString();
        String addresse=address.getText().toString();
        String numbers=number.getText().toString();
        String localities=locality.getText().toString();

        u.setAddress(addresse);
        u.setLocality(localities);
        u.setPhone(phones);
        u.setPincode(numbers);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             myRef.child("User Info").setValue(u);
            }
        });
    }
}