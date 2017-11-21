package com.example.ankith.dailycalorie;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static android.content.ContentValues.TAG;

/**
 * Created by ankit on 01-05-2017.
 */

public class DatabaseEngine {

    /**
     *updates a user in the dataBase
     * @param user
     */
    public static void updateUserInDatabase(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child(user.getId()).push().setValue(user);
    }
    /**
     * Adds a new user to the database
     * @param user
     */
    public static void addNewUser(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Users");
        myRef.setValue(user.id,user);
    }




}
