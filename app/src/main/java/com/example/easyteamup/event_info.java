package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class event_info extends AppCompatActivity {
    String username, email, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        fullname = intent.getStringExtra("fullname");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
    }
}