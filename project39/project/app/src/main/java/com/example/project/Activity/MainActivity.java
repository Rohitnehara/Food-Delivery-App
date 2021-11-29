package com.example.project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.Adapter.CategoryAdapter;
import com.example.project.Adapter.MenuAdaptor;
import com.example.project.Adapter.PopularAdapter;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.FoodDomain;
import com.example.project.R;
import com.example.project.module.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private ImageView settingggg,profilePic;
    private TextView settingTxt,usernameboy;
    private LinearLayout setingLyout,profileLyout,support;
private Bitmap bitmap;
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference myRef;
//    PopularAdapter adaptor;
    FirebaseFirestore DBRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
support=findViewById(R.id.support);
profilePic=findViewById(R.id.imageView3);
        DBRoot=FirebaseFirestore.getInstance();
usernameboy=findViewById(R.id.textView4);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
        settingggg=findViewById(R.id.settings);

        profileLyout=findViewById(R.id.profileLayout);

        setingLyout=findViewById(R.id.settinfLyaout);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();

support.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,splash_activity.class);
        startActivity(intent);
    }
});



//   myRef.addValueEventListener(new ValueEventListener() {
//       @Override
//       public void onDataChange(@NonNull DataSnapshot snapshot) {
//           String value= snapshot.getValue(String.class);
//           usernameboy.setText(value);
//       }
//
//       @Override
//       public void onCancelled(@NonNull DatabaseError error) {
//
//       }
//   });

//    Users users = new Users();
//    String fsdd = users.getUserId();
//    users.getUsername();
    usernameboy.setText("Hi " + auth.getCurrentUser().getDisplayName());
        //usernameboy.setText("Hi " + myRef.g);


        auth.getCurrentUser().getPhotoUrl();
//      profilePic.setImageURI(auth.getCurrentUser().getPhotoUrl());
    Glide.with(this).load(auth.getCurrentUser().getPhotoUrl()).into(profilePic);
////        try {
//            URL url = new URL("  auth.getCurrentUser().getPhotoUrl()");
//            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch(IOException e) {
//            System.out.println(e);
//        }




        setingLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,settings.class);
            startActivity(intent);
            }
        });

        profileLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,loadingActivity.class);
                startActivity(intent);
            }
        });


//        settingTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,settings.class);
//                startActivity(intent);
//            }
//        });
  }


    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
//        foodlist.add(new FoodDomain("Pepperoni pizza", "pizza1", "slices pepperoni ,mozzarella cheese, fresh oregano,  ground black pepper, pizza sauce", 9.76));
//        foodlist.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato ", 8.79));
//        foodlist.add(new FoodDomain("Vegetable pizza", "pizza2", " olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 8.5));


        db=FirebaseFirestore.getInstance();
        db.collection("products").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            FoodDomain obj=d.toObject(FoodDomain.class);
                            foodlist.add(obj);
                        }

                        adapter2 = new PopularAdapter(foodlist);
                        recyclerViewPopularList.setAdapter(adapter2);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed To Load Data", Toast.LENGTH_SHORT).show();
            }
        });



        db.collection("pizza").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            FoodDomain obj=d.toObject(FoodDomain.class);
                            foodlist.add(obj);
                        }

                        adapter2 = new PopularAdapter(foodlist);
                        recyclerViewPopularList.setAdapter(adapter2);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed To Load Data", Toast.LENGTH_SHORT).show();
            }
        });




//        db.collection("sandwich").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                        for(DocumentSnapshot d:list)
//                        {
//                            FoodDomain obj=d.toObject(FoodDomain.class);
//                            foodlist.add(obj);
//                        }
//
//                        adaptor.notifyDataSetChanged();
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), "Failed To Load Data", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "cat_1"));
        categoryList.add(new CategoryDomain("Burger", "cat_2"));
        categoryList.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryList.add(new CategoryDomain("Drink", "cat_4"));
        categoryList.add(new CategoryDomain("Dount", "cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
//    public void fetchData()
//    {
//        DocumentReference documentReference=DBRoot.collection("products").document("K595o3HnkGDgymlypQNb");
//        documentReference.get().addOnSuccessListener()
//    }









}