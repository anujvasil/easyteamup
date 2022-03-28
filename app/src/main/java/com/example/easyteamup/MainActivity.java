package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button button;
    Button login;
    EditText emailText;
    EditText passwordText;
    String email, password, username, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button2);
        login = (Button) findViewById(R.id.button);
        emailText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passwordText = (EditText) findViewById(R.id.editTextTextPassword);
        email = emailText.getText().toString();
        password = passwordText.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.matches("") && !password.matches("")){
                    DBConnectionHelper connectionHelper = new DBConnectionHelper();
                    openSetup();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailText.getText().toString().matches("") && !passwordText.getText().toString().matches("")){
                    DBConnectionHelper connectionHelper = new DBConnectionHelper();
                    Map<String,String> m = connectionHelper.validateUser(email,password);
                    if (m != null) {
                        username = m.get("username");
                        fullname = m.get("fullname");
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
        intent.putExtra("email",email);
        intent.putExtra("username",username);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }



}