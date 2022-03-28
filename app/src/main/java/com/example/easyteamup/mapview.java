package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mapview extends AppCompatActivity {

    Button listtoggle;
    String username, fullname, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        fullname = intent.getStringExtra("fullname");
        email = intent.getStringExtra("email");

        listtoggle = (Button) findViewById(R.id.button19);

        listtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openList();

            }
        });
    }

    public void openList(){
        Intent intent = new Intent(this, discover_list.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }
}