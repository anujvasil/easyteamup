package com.example.easyteamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class event_info extends AppCompatActivity {
    String username, email, fullname;

    TextView name, owner, description, location, duetime;
    CheckBox time1, time2, time3, time4;

    Button join, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        back = (Button) findViewById(R.id.button28);
        join = (Button) findViewById(R.id.button29);

        Event event = ((App)getApplication()).getEvent();
        username = ((App)getApplication()).getUsername();
        email = ((App)getApplication()).getEmail();
        fullname = ((App)getApplication()).getEmail();

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
                connectionHelper.publicJoinAttendee(username, event.getId());

                openList();

            }
        });



        name = findViewById(R.id.textView5);
        owner = findViewById(R.id.textView7);
        description = findViewById(R.id.textView8);
        location = findViewById(R.id.editTextTextPostalAddress);
        duetime = findViewById(R.id.textView11);
        time1 = findViewById(R.id.checkBox2);
        time2 = findViewById(R.id.checkBox5);
        time3 = findViewById(R.id.checkBox6);
        time4 = findViewById(R.id.checkBox7);

        name.setText(event.getTitle());
        owner.setText(event.getOwner());
        description.setText(event.getDescription());
        duetime.setText(event.getDueDate().toString());

        time1.setText(event.getTime1().toString());
        time2.setText(event.getTime2().toString());
        time3.setText(event.getTime3().toString());
        time4.setText(event.getTime4().toString());



    }

    public void openList(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }





}