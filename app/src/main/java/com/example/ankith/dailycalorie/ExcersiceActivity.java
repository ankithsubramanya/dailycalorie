package com.example.ankith.dailycalorie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ankit on 02-05-2017.
 */

public class ExcersiceActivity extends AppCompatActivity {
    User user;
    Button doneButton;
    EditText caloriesLostView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersice);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        doneButton = (Button)findViewById(R.id.addButton);
        caloriesLostView = (EditText) findViewById(R.id.caloriesLostView2);
        doneButton.setOnClickListener(view -> {
            finish();
        });
    }

    /**
     * Goes back to the home activity
     */
    public void finish(){
        double caloriesLost = Double.parseDouble(caloriesLostView.getText().toString());
        user.addCaloriesLost(caloriesLost);
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
    }
}
