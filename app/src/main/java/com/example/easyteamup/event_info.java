package com.example.easyteamup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

public class event_info extends AppCompatActivity {
    String username, email, fullname;

    TextView name, owner, description, location, duetime;
    CheckBox time1, time2, time3, time4;
    Button join, back, modify;
    DBConnectionHelper connectionHelper;
    boolean isGoing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        back = (Button) findViewById(R.id.button17);
        join = (Button) findViewById(R.id.button29);
        modify = (Button) findViewById(R.id.buttonModify);


        connectionHelper = ((App)getApplication()).getDatabase();

        Event event = ((App)getApplication()).getEvent();
        username = ((App)getApplication()).getUsername();
        email = ((App)getApplication()).getEmail();
        fullname = ((App)getApplication()).getEmail();
        isGoing = connectionHelper.isAttending(username, event.getId());
        if (username.equals(event.getOwner())) {
            join.setText("Invite");
            modify.setVisibility(View.VISIBLE);
        }
        else if (isGoing){
            join.setText("Leave");
            modify.setVisibility(View.GONE);
        }
        else {
            join.setText("Join");
            modify.setVisibility(View.GONE);
        }



        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                modifyEvent();

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openList();

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.equals(event.getOwner())) {
                    invite();
                }
                else if (isGoing) {
                    connectionHelper.respondInvite(username, event.getId(), false);
                    join.setText("Join");
                }
                else {
                    connectionHelper.respondInvite(username, event.getId(), true);
                    join.setText("Leave");
                }
                isGoing= !isGoing;

            }
        });



        name = findViewById(R.id.textView5);
        owner = findViewById(R.id.textView7);
        description = findViewById(R.id.textView8);
        location = findViewById(R.id.search_view);
        duetime = findViewById(R.id.textView11);
        time1 = findViewById(R.id.checkBox2);
        time2 = findViewById(R.id.checkBox5);
        time3 = findViewById(R.id.checkBox6);
        time4 = findViewById(R.id.checkBox7);

        name.setText(event.getTitle());
        owner.setText(event.getOwner());
        description.setText(event.getDescription());
        duetime.setText(event.getDueDate() == null ? "No Due Date" : event.getDueDate().toString());

        time1.setText(event.getTime1() == null ? "No time set" : event.getTime1().toString());
        time2.setText(event.getTime2() == null ? "No time set" : event.getTime2().toString());
        time3.setText(event.getTime3() == null ? "No time set" : event.getTime3().toString());
        time4.setText(event.getTime4() == null ? "No time set" : event.getTime4().toString());



    }

    public void openList(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }

    public void modifyEvent() {
        Intent intent = new Intent(this, post_event.class);
        intent.putExtra("Editing",true);
        startActivity(intent);
    }

    public void invite() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Invite a user by username");
        EditText invitedUserET = new EditText(this);
        builder.setView(invitedUserET);
        builder.setPositiveButton("Invite", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String invitedUser = invitedUserET.getText().toString();
                DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
                connectionHelper.inviteAttendee(invitedUser,((App)getApplication()).getEvent().getId());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Blank on purpose
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Invite User");
        alert.show();



    }




}