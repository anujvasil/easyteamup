package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class my_pending_events extends AppCompatActivity {

    Button confirmed;
    Button account;
    Button discover;
    Button invite;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pending_events);

        confirmed = (Button) findViewById(R.id.button12);
        discover = (Button) findViewById(R.id.button23);
        account = (Button) findViewById(R.id.button22);
        add = (Button) findViewById(R.id.button11);
        invite = (Button) findViewById(R.id.button13);

        confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmed();

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
                newEvent();

            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvite();

            }
        });
    }

    public void openConfirmed(){
        Intent intent = new Intent(this, my_confirmed_events.class);
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

    public void newEvent(){
        Intent intent = new Intent(this, post_event.class);
        startActivity(intent);
    }
    public void openInvite(){
        Intent intent = new Intent(this, pending_invites.class);
        startActivity(intent);
    }
}