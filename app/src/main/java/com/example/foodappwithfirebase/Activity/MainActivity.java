package com.example.foodappwithfirebase.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.foodappwithfirebase.Adapter.BestFoodAdapter;
import com.example.foodappwithfirebase.Adapter.CategoryAdapter;
import com.example.foodappwithfirebase.Domain.Category;
import com.example.foodappwithfirebase.Domain.Foods;
import com.example.foodappwithfirebase.Domain.Location;
import com.example.foodappwithfirebase.Domain.Price;
import com.example.foodappwithfirebase.Domain.Time;
import com.example.foodappwithfirebase.R;
import com.example.foodappwithfirebase.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();
        setVariable();

    }

    private void setVariable() {
        binding.cartBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,CartActivity.class)));
    binding.searchBtn.setOnClickListener(view -> {
        String text = binding.searchTex.getText().toString();
        if (text.isEmpty()){
            Intent intent = new Intent(MainActivity.this,ListFoodActivity.class);
            intent.putExtra("text",text);
            intent.putExtra("isSearch",true);
            startActivity(intent);
        }
    });
    }

    private void initCategory() {
    DatabaseReference myRef=database.getReference("Category");
    binding.progressBarBestFood.setVisibility(View.VISIBLE);
    ArrayList<Category> list=new ArrayList<>();
        Query query=myRef.orderByChild("Category").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issu: snapshot.getChildren()){
                        list.add(issu.getValue(Category.class));

                    }
                    if (list.size()>0){
                        binding.categoryView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapterCategory=new CategoryAdapter(list);
                        binding.categoryView.setAdapter(adapterCategory);
                    }
                    binding.progressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initBestFood() {
    DatabaseReference myRef=database.getReference("Foods");
    binding.progressBarBestFood.setVisibility(View.VISIBLE);
    ArrayList<Foods> list=new ArrayList<>();
        Query query=myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issu: snapshot.getChildren()){
                        list.add(issu.getValue(Foods.class));

                    }
                    if (list.size()>0){
                        binding.bestFoodView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapterBestFood=new BestFoodAdapter(list);
                        binding.bestFoodView.setAdapter(adapterBestFood);
                    }
                    binding.progressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void initLocation(){
         DatabaseReference myRef= database.getReference("Location");
         ArrayList<Location> list = new ArrayList<>();
         myRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if(snapshot.exists()){
                     for( DataSnapshot issue: snapshot.getChildren()){
                         list.add(issue.getValue(Location.class));
                     }
                     ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item);
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     binding.locationSp.setAdapter(adapter);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
     }
     private void initTime(){
         DatabaseReference myRef= database.getReference("Time");
         ArrayList<Time> list = new ArrayList<>();
         myRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if(snapshot.exists()){
                     for( DataSnapshot issue: snapshot.getChildren()){
                         list.add(issue.getValue(Time.class));
                     }
                     ArrayAdapter<Time> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item);
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     binding.TimeSp.setAdapter(adapter);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
     }
     private void initPrice(){
         DatabaseReference myRef= database.getReference("Price");
         ArrayList<Price> list = new ArrayList<>();
         myRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if(snapshot.exists()){
                     for( DataSnapshot issue: snapshot.getChildren()){
                         list.add(issue.getValue(Price.class));
                     }
                     ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item);
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     binding.priceSp.setAdapter(adapter);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
     }

}