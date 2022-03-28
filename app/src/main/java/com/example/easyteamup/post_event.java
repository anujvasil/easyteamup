package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class post_event extends AppCompatActivity {

    Button back;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

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
        startActivity(intent);
    }

    public void postEvent(){
        Intent intent = new Intent(this, my_pending_events.class);
        startActivity(intent);
    }
}