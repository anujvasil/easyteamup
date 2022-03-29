package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class account_setup extends AppCompatActivity {

    Button submit;
    String email, password, fullname, username;

    EditText usernameText;
    EditText fullnameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);
        Intent intent1 = getIntent();
        email = intent1.getStringExtra("email");
        password = intent1.getStringExtra("password");
        submit = (Button) findViewById(R.id.button4);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameText = (EditText) findViewById(R.id.editTextTextPersonName2);
                fullnameText = (EditText) findViewById(R.id.editTextTextPersonName);
                fullname = fullnameText.getText().toString();
                username = usernameText.getText().toString();
                DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
                connectionHelper.createUser(username,password,fullname,email);
                openAccount();
            }
        });


    }

    public void openAccount(){
        Intent intent = new Intent(this, my_account.class);
        ((App)getApplication()).setUsername(username);
        ((App)getApplication()).setFullname(fullname);
        ((App)getApplication()).setEmail(email);
        startActivity(intent);
    }
}