package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class event_info extends AppCompatActivity {
    String username, email, fullname;
    Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        fullname = intent.getStringExtra("fullname");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        join = (Button) findViewById(R.id.button29);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openList();

            }
        });
    }

    public void openList(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }

}