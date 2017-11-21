package com.example.ankith.dailycalorie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by ankit on 02-05-2017.
 */

public class RemoveActivity extends AppCompatActivity {
    User user;
    private RecyclerView mRecyclerView;
    private ArrayList<FoodItem> foodItems;
    private FoodRemoveAdapter foodRemoveAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        foodItems = user.getListOfFoodItems();
        if (foodItems !=null) {
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            foodRemoveAdapter = new FoodRemoveAdapter(foodItems, user);
            mRecyclerView.setAdapter(foodRemoveAdapter);
        }
        else{

        }
    }


}
