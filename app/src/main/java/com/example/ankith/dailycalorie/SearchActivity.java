package com.example.ankith.dailycalorie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.SearchView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.Response;
import com.fatsecret.platform.services.android.Request;
import com.fatsecret.platform.services.android.ResponseListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 01-05-2017.
 * Implements response listener for FatSecret API
 * responsible for all the communication with the api
 */

public class SearchActivity extends AppCompatActivity implements ResponseListener{
    SearchView searchView;
    Button searchButton;
    public List<CompactFood> foods;
    public ArrayList<FoodItem> foodItems;
    private RecyclerView mRecyclerView;
    private FoodItemAdapter foodItemAdapter;
    private User user;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Enter food item");
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(view -> {
                onSearch();
        });
    }

    /**
     * Gets results from the search query
     */
    public void onSearch(){
        String query = searchView.getQuery().toString();
        String key = Constants.KEY;
        String secret = Constants.SECRET;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ArrayList<FoodItem> items = new ArrayList<FoodItem>();
        Request req = new Request(key, secret, this);
        req.getFoods(requestQueue, query, 0);
        if (foodItems != null) {
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            foodItemAdapter = new FoodItemAdapter(foodItems, user);
            mRecyclerView.setAdapter(foodItemAdapter);
        }
    }

    /**
     * Listens to the response from the URL for the particular query
     * @param response
     */
    @Override
    public void onFoodListRespone(Response<CompactFood> response) {
        foodItems = new ArrayList<FoodItem>();
        foods = response.getResults();
        //This list contains summary information about the food item
        for (CompactFood food: foods) {
            FoodItem item = DataScraper.descriptionToFoodItem(food.getName(),food.getId(),food.getDescription());
            foodItems.add(item);
        }
    }

}



