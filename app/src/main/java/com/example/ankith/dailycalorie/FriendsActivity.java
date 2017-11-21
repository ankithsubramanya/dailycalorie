package com.example.ankith.dailycalorie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ankit on 03-05-2017.
 */

public class FriendsActivity extends AppCompatActivity {
    User user;
    final ArrayList<User> userFriends = new ArrayList<>();
    final ArrayList<String> friendIds = new ArrayList<String>();
    private RecyclerView mRecyclerView;
    private FriendsAdapter friendsAdapter;
    SearchView searchFriendsView;
    Button addFriendsButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        for (long id : user.getFriends()){
            friendIds.add(Long.toString(id));
        }
        buildUserFriends();
        searchFriendsView = (SearchView) findViewById(R.id.searchFriendsView);
        searchFriendsView.setQueryHint("add friend by email id");
        addFriendsButton = (Button) findViewById(R.id.addFriendButton);
        addFriendsButton.setOnClickListener(view -> {
            addFriend();
        });

        if (userFriends !=null) {
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            friendsAdapter = new FriendsAdapter(userFriends);
            mRecyclerView.setAdapter(friendsAdapter);
        }
        else{

        }
    }
    public void addFriend(){
        final String friendEmail = searchFriendsView.getQuery().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean added = false;
                /*for (DataSnapshot myData : dataSnapshot.getChildren()){
                    User tempUser = (myData.getValue(User.class) == null? new User() : myData.getValue(User.class));
                    if(tempUser.getEmail().equals(friendEmail)){
                        user.addFriend(tempUser);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setText("Add successful!");
                        toast.show();
                        added = true;
                        break;
                    }
                }*/
                if (!added){
                    /*Toast toast = new Toast(getApplicationContext());
                    toast.setText("Could not find user");
                    toast.show();*/
                    System.out.println("Not added");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("User", user);
        getApplicationContext().startActivity(intent);
        System.out.println("Back pressed");
    }
    public void buildUserFriends(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot myData : dataSnapshot.getChildren()){
                    User tempUser = myData.getValue(User.class);
                    if(friendIds.contains(tempUser.getId())){
                        userFriends.add(tempUser);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
