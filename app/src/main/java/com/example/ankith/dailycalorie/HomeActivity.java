package com.example.ankith.dailycalorie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
/**
 * Created by ankit on 01-05-2017.
 */

public class HomeActivity extends AppCompatActivity{
    User user = new User();
    String userId;
    ImageButton addFoodButton;
    ImageButton removeFoodButton;
    ImageButton getFriendsButton;
    ImageButton updateProfileButton;
    ImageButton addExcersiceButton;
    TextView userNameView;
    TextView caloriesLostView;
    TextView caloriesGainedView;
    TextView caloriesLeftView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            // If the login activity is the preceding activity, user data is drawn from firebase
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            final String id = firebaseUser.getUid();
            System.out.println(id);
            userId = id;
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot){
                    System.out.println("Searching for user");
                    /*for (DataSnapshot myData : dataSnapshot.getChildren()){
                        System.out.println(myData);
                        User tempUser = (myData.getValue(User.class) == null? new User() : myData.getValue(User.class));
                        if(tempUser.getId().equals(id)){
                            user = tempUser;
                            System.out.println("Found user" + user.getEmail());
                        }
                    }*/

                }
                @Override
                public void onCancelled(DatabaseError error){

                }
            });
        } else {
            user = intent.getParcelableExtra("User");
            //DatabaseEngine.updateUserInDatabase(user);
        }
        addFoodButton = (ImageButton) findViewById(R.id.addFoodButton);
        removeFoodButton = (ImageButton) findViewById(R.id.removeFoodButton);
        getFriendsButton = (ImageButton) findViewById(R.id.getFriendsButton);
        addExcersiceButton = (ImageButton) findViewById(R.id.addExcerciseButton);
        updateProfileButton = (ImageButton) findViewById(R.id.userProfileButton);
        userNameView = (TextView) findViewById(R.id.userIdView);
        caloriesLostView = (TextView) findViewById(R.id.caloriesLostView);
        caloriesGainedView = (TextView) findViewById(R.id.caloriesGainedView);
        caloriesLeftView = (TextView) findViewById(R.id.caloriesLeftView);

        addFoodButton.setOnClickListener(view -> {
            addFood();
        });
        updateProfileButton.setOnClickListener(view -> {
            updateProfile();
        });
        addExcersiceButton.setOnClickListener(view -> {
            addExcersice();
        });
        removeFoodButton.setOnClickListener(view -> {
            removeFood();
        });
        getFriendsButton.setOnClickListener(view -> {
            getFriendsActivity();
        });
        initializeScreens();
    }
    public void addFood(){
        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
    }
    public void updateProfile(){
        Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
    }
    public void removeFood(){
        Intent intent = new Intent(HomeActivity.this, RemoveActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
    }
    public void addExcersice(){
        Intent intent = new Intent(HomeActivity.this, ExcersiceActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
    }
    public void getFriendsActivity(){
        Intent intent = new Intent(HomeActivity.this, FriendsActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
    }
    /**
     * Initilized the UI for the home activity
     */
    public void initializeScreens(){
        userNameView.setText(user.getEmail() == null? "  Hello!" : " " + user.getEmail());
        caloriesLostView.setText(Double.toString(user.getTotalCaloriesLost()));
        caloriesGainedView.setText(Double.toString(user.getTotalCaloriesGained()));
        if (user.targetWeight == 0){
            caloriesLeftView.setText("Update profile");
        }
        else{
            caloriesLeftView.setText(Double.toString(user.getCaloriesLeft()));
        }
    }


}