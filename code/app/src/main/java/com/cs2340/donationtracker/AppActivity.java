package com.cs2340.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AppActivity extends AppCompatActivity implements View.OnClickListener{
    Button bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bLogout) {

            Intent goback = new Intent(this, LoginPage.class);
            startActivity(goback);
            return;
        }
    }
}