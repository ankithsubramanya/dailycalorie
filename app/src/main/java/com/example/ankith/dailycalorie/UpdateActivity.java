package com.example.ankith.dailycalorie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Date;

/**
 * Created by ankit on 02-05-2017.
 * Updates user information in the App
 */

public class UpdateActivity extends AppCompatActivity {
    User user;
    EditText weightText;
    EditText heightText;
    EditText targetWeightText;
    RadioButton isMaleRadio;
    RadioButton isFemaleRadio;
    EditText ageText;
    Button finishProfile;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        weightText = (EditText) findViewById(R.id.weightText);
        weightText.setHint("Enter weight here");
        heightText = (EditText) findViewById(R.id.heightText);
        heightText.setHint("Enter height here");
        targetWeightText = (EditText) findViewById(R.id.targetWeightText);
        isMaleRadio = (RadioButton) findViewById(R.id.isMaleRadio);
        isFemaleRadio = (RadioButton) findViewById(R.id.isFemaleRadio);
        ageText = (EditText) findViewById(R.id.ageText);
        finishProfile = (Button) findViewById(R.id.finishProfile);
        finishProfile.setOnClickListener(view -> {
            finishProfile();
        });
    }
    public void finishProfile(){
        int age = Integer.parseInt(ageText.getText().toString());
        user.setAge(age);
        double weight = Double.parseDouble(weightText.getText().toString());
        user.setWeight(weight);
        double targetWeight = Double.parseDouble(targetWeightText.getText().toString());
        user.setTargetWeight(targetWeight);
        double height = Double.parseDouble(heightText.getText().toString());
        user.setHeight(height);
        boolean isMale = isMaleRadio.isSelected();
        user.setMale(isMale);
        Intent intent = new Intent(this.getApplicationContext(), HomeActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
    }

}
