package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class confirmed_invites extends AppCompatActivity {

    Button pending;
    Button events;
    Button discover;
    Button account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_invites);

        pending = (Button) findViewById(R.id.button27);
        events = (Button) findViewById(R.id.button21);
        discover = (Button) findViewById(R.id.button23);
        account = (Button) findViewById(R.id.button22);

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPending();

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();

            }
        });

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiscover();

            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccounts();

            }
        });
    }
    public void openPending(){
        Intent intent = new Intent(this, pending_invites.class);
        startActivity(intent);
    }
    public void openEvents(){
        Intent intent = new Intent(this, my_pending_events.class);
        startActivity(intent);
    }
    public void openDiscover(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }
    public void openAccounts(){
        Intent intent = new Intent(this, my_account.class);
        startActivity(intent);
    }
}