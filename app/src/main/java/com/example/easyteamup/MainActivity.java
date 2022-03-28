package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    Button button;
    Button login;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button2);
        login = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().matches("") && !password.getText().toString().matches("")){
                    openSetup();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().matches("") && !password.getText().toString().matches("")){
                    openProfilePage();
                }

            }
        });
    }
    public void openSetup(){
        Intent intent = new Intent(this, account_setup.class);
        startActivity(intent);
    }

    public void openProfilePage(){
        Intent intent = new Intent(this, my_account.class);
        startActivity(intent);
    }



}