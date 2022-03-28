package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class post_event extends AppCompatActivity {

    Button back;
    Button post;

    String username, email, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        fullname = intent.getStringExtra("fullname");

        back = (Button) findViewById(R.id.button20);
        post = (Button) findViewById(R.id.button14);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();

            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postEvent();

            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, my_pending_events.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }

    public void postEvent(){
        Intent intent = new Intent(this, my_pending_events.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }
}