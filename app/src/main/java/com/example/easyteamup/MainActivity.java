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
    EditText emailText;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button2);
        login = (Button) findViewById(R.id.button);
        emailText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passwordText = (EditText) findViewById(R.id.editTextTextPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                if (!email.matches("") && !password.matches("")){
                    DBConnectionHelper connectionHelper = new DBConnectionHelper();
                    openSetup();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                if (!emailText.getText().toString().matches("") && !passwordText.getText().toString().matches("")){
                    DBConnectionHelper connectionHelper = new DBConnectionHelper();
                    if (connectionHelper.validateUser(email,password)) {
                        openProfilePage();
                    }
                }

            }
        });
    }
    public void openSetup(){
        Intent intent = new Intent(this, account_setup.class);
        intent.putExtra("email",emailText.getText().toString());
        intent.putExtra("password",passwordText.getText().toString());
        startActivity(intent);
    }

    public void openProfilePage(){
        Intent intent = new Intent(this, my_account.class);
        startActivity(intent);
    }



}