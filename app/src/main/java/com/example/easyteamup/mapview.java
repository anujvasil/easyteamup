package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mapview extends AppCompatActivity {

    Button listtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);

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
        startActivity(intent);
    }
}