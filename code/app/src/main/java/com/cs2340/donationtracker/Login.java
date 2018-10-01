package com.cs2340.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.app.Activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ChildEventListener;



public class Login extends AppCompatActivity implements View.OnClickListener{

    Button loginButton, bCancel;
    EditText editPW, editUser;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //this will look through the activity xml and find the View that matches editUser
        //and editPW, and will set it to the variables on the left.
        editUser = (EditText) findViewById(R.id.editUser);
        editPW = (EditText) findViewById(R.id.editPW);
        loginButton = (Button) findViewById(R.id.loginButton);
        bCancel = (Button) findViewById(R.id.bCancel);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //now we set an on click listener, which watches for when the user hits the login button
        //notifies the onClick method below
        loginButton.setOnClickListener(this);
        bCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //issue with this method is that it goes off if we have many on click listeners, and
        //we don't know why it went off. the if statement takes care of that
        //handles for all buttons, have to use if to see which is used
        if (v.getId() == R.id.loginButton) {
            //this is what happens when the login button is clicked
            //new intent for entering the app
            Intent openApp = new Intent(this, AppActivity.class);

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("users");
            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    RegistrationPage.User user = dataSnapshot.getValue(RegistrationPage.User.class);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
            if (editPW.getText().toString().equals("pass")
                    && editUser.getText().toString().equals("user")) {
                startActivity(openApp);
                //return;
            }
            //error in field if wrong username
            else if(!(editUser.getText().toString().equals("user"))){
                editUser.setError("wrong username");
            }
            //error in field if wrong password
            else if(!(editPW.getText().toString().equals("pass"))){
                editPW.setError("wrong password");
            }

        }
        //if cancel button is hit, go back to the welcome page
        if (v.getId() == R.id.bCancel) {
            Intent goback = new Intent(this, LoginPage.class);
            startActivity(goback);

        }
        return;
    }
}