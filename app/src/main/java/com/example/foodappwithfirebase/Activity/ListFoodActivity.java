package com.example.foodappwithfirebase.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappwithfirebase.Adapter.ListFoodAdapter;
import com.example.foodappwithfirebase.Domain.Foods;
import com.example.foodappwithfirebase.R;
import com.example.foodappwithfirebase.databinding.ActivityListFoodBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodActivity extends BaseActivity {
  private ActivityListFoodBinding binding;
  private RecyclerView.Adapter adapterListFood;
  private int categoryId;
  private String categoryName;
  private String searchText;
  private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_list_food);

        getIntentExtra();
        initList();

    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId",0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("Text");
        isSearch = getIntent().getBooleanExtra("isSearch",false);

        binding.titleListTxt.setText(categoryName);
        binding.backListBtn.setOnClickListener(view -> finish());
    }

    private void initList(){

        DatabaseReference myRef=database.getReference("Food");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query ;
        if(isSearch){
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else {
            query=myRef.orderByChild("CategoryId").equalTo(categoryId);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()){

                        list.add(issue.getValue(Foods.class));
                    }

                    if (list.size()>0){
                        binding.foodListView.setLayoutManager(new GridLayoutManager(ListFoodActivity.this,2));
                        adapterListFood=new ListFoodAdapter(list);
                        binding.foodListView.setAdapter(adapterListFood);
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}