package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                if (!email.matches("") && !password.matches("")){
                    openSetup();
                    ((App)getApplication()).setEmail(email);
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                if (!email.matches("") && !password.matches("")){
                    DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
                    Map<String,String> m = connectionHelper.validateUser(email,password);
                    if (m != null) {
                        username = m.get("username");
                        fullname = m.get("fullname");
                        openProfilePage();
                    }
                    else {
                        TextView invlogin = (TextView) findViewById(R.id.textView9);
                        invlogin.setText("Invalid Login");
                    }

                }
                else {
                    TextView invlogin = (TextView) findViewById(R.id.textView9);
                    invlogin.setText("Invalid Login");
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

        ((App)getApplication()).setEmail(email);
        ((App)getApplication()).setFullname(fullname);
        ((App)getApplication()).setUsername(username);

        startActivity(intent);
    }

}