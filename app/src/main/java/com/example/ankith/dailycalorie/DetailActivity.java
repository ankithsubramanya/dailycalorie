package com.example.ankith.dailycalorie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by ankit on 02-05-2017.
 */
public class DetailActivity extends AppCompatActivity{
    private TextView mTitleTextView;
    private TextView mAuthorTextView;
    private NumberPicker numberPicker;
    private Button doneButton;
    private User user;
    private FoodItem foodItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // to find and initialize the required views
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mAuthorTextView = (TextView) findViewById(R.id.authorTextView);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(15);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);
        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(view -> {
            onDone();
        });
        Intent intent = getIntent();
        // To recieve the parcelable Food item and user
        foodItem = intent.getParcelableExtra("FOOD_ITEM");
        user = intent.getParcelableExtra("USER");
        mTitleTextView.setText(foodItem.getName());
        mAuthorTextView.setText(foodItem.getDescription());
    }
    /**
     * Goes back to the home activity
     */
    private void onDone(){
        int servings = numberPicker.getValue();
        user.addFood(foodItem, servings);
        Intent intent = new Intent(this.getApplicationContext(), HomeActivity.class);
        intent.putExtra("User", user);
        this.getApplicationContext().startActivity(intent);
    }
}
