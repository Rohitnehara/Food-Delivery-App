package com.example.project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adapter.CartListAdapter;
import com.example.project.Domain.FoodDomain;
import com.example.project.Domain.userAddress;
import com.example.project.Helper.ManagementCart;
import com.example.project.Interface.ChangeNumberItemsListener;
import com.example.project.R;
import com.example.project.module.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt,checkout,userInfo;
    private double tax;
    private ScrollView scrollView;
    private FoodDomain domain;
   FirebaseFirestore dbroot;
    userAddress u;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        managementCart = new ManagementCart(this);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        initView();
        initList();
        calculateCard();
        bottomNavigation();
        database=FirebaseDatabase.getInstance();
        DatabaseReference myRef =database.getReference();
        u=new userAddress();

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CartListActivity.this,afterCheckout.class);
                startActivity(intent);
      //     insertData();
            }
        });

    }


    public  void  insertData()
    {
   dbroot.collection("orders").add(String.valueOf(managementCart.getListCard()));
    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();

               // FcmNotificationsSender notificationsSender=new FcmNotificationsSender("")
//                String title=domain.getTitle();
//                int price=domain.getFee().intValue();
//                int numer=domain.getNumberInCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("₹" + itemTotal);
        taxTxt.setText("₹" + tax);
        deliveryTxt.setText("₹" + delivery);
        totalTxt.setText("₹" + total);
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerview);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
        checkout=findViewById(R.id.checkoutView);
    }
}