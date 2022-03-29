package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class my_account extends AppCompatActivity {

    Button logout;
    Button events;
    Button discover;
    Button invite;

    TextView fullnameView, emailView;

    String email, fullname, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Intent intent = getIntent();
        email = ((App)getApplication()).getEmail();
        fullname = ((App)getApplication()).getFullname();
        username = ((App)getApplication()).getUsername();

        logout = (Button) findViewById(R.id.button5);
        events = (Button) findViewById(R.id.button8);
        discover = (Button) findViewById(R.id.button7);
        invite = (Button) findViewById(R.id.button6);
        fullnameView = findViewById(R.id.textView);
        emailView = findViewById(R.id.textView2);
        emailView.setText(email);
        fullnameView.setText(fullname);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();

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

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvite();

            }
        });
    }

    public void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
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

    public void openInvite(){
        Intent intent = new Intent(this, pending_invites.class);
        startActivity(intent);
    }
}