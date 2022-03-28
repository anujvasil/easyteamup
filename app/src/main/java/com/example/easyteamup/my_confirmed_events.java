package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class my_confirmed_events extends AppCompatActivity {

    Button pending;
    Button discover;
    Button account;
    Button add;
    Button invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_confirmed_events);

        pending = (Button) findViewById(R.id.button25);
        discover = (Button) findViewById(R.id.button23);
        account = (Button) findViewById(R.id.button22);
        add = (Button) findViewById(R.id.button24);
        invite = (Button) findViewById(R.id.button13);


        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPending();

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
                openAccount();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();

            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvite();

            }
        });

    }

    public void openPending(){
        Intent intent = new Intent(this, my_pending_events.class);
        startActivity(intent);
    }

    public void openDiscover(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }

    public void openAccount(){
        Intent intent = new Intent(this, my_account.class);
        startActivity(intent);
    }

    public void addEvent(){
        Intent intent = new Intent(this, post_event.class);
        startActivity(intent);
    }

    public void openInvite(){
        Intent intent = new Intent(this, pending_invites.class);
        startActivity(intent);
    }
}