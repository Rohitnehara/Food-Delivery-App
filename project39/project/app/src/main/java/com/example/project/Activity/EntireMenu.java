package com.example.project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.project.Adapter.MenuAdaptor;
import com.example.project.Domain.FoodDomain;
import com.example.project.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import com.example.project.Activity.ShowDetailActivity;
import com.example.project.Domain.FoodDomain;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;




public class EntireMenu extends AppCompatActivity {
RecyclerView recview;
MenuAdaptor adaptor;
ArrayList<FoodDomain>datalist;
FirebaseFirestore db;
FirebaseDatabase database;
DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entire_menu);


        recview=findViewById(R.id.recview);

        recview.setLayoutManager(new LinearLayoutManager(this));

        datalist  = new ArrayList<>();

        adaptor=new MenuAdaptor(datalist);
        recview.setAdapter(adaptor);

        db=FirebaseFirestore.getInstance();
        db.collection("products").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                       for(DocumentSnapshot d:list)
                       {
                           FoodDomain obj=d.toObject(FoodDomain.class);
                           datalist.add(obj);
                       }

                       adaptor.notifyDataSetChanged();

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
                            datalist.add(obj);
                        }

                        adaptor.notifyDataSetChanged();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed To Load Data", Toast.LENGTH_SHORT).show();
            }
        });




        db.collection("sandwich").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            FoodDomain obj=d.toObject(FoodDomain.class);
                            datalist.add(obj);
                        }

                        adaptor.notifyDataSetChanged();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed To Load Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}