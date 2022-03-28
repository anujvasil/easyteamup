package com.example.easyteamup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;

public class discover_list extends AppCompatActivity {

    private RecyclerView recycle;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Button map;
    Button account;
    Button event;
    Button invite;
    String username, email, fullname;


    public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

        private ArrayList<Event> eventList;


        public class EventViewHolder extends RecyclerView.ViewHolder {

            public ImageView img;
            public TextView text1;

            public EventViewHolder(View itemview) {
                super(itemview);
                img = itemview.findViewById(R.id.imageView8);
                text1 = itemview.findViewById(R.id.textView4);
            }
        }

        public EventAdapter(ArrayList<Event> l) {
            eventList = l;
        }

        @NonNull
        @Override
        public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_event, parent, false);
            EventViewHolder evh = new EventViewHolder(v);
            return evh;
        }

        @Override
        public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
            Event curr = eventList.get(position);
            holder.img.setImageResource(curr.getImage());
            holder.text1.setText(curr.getDescription());
        }

        @Override
        public int getItemCount() {
            return eventList.size();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_list);
        DBConnectionHelper connectionHelper = new DBConnectionHelper();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        fullname = intent.getStringExtra("fullname");
        ArrayList<Event> eventList = connectionHelper.discoverEvents();


        recycle = findViewById(R.id.recycleViewer);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new EventAdapter(eventList);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);

        map = (Button) findViewById(R.id.button10);
        event = (Button) findViewById(R.id.button21);
        account = (Button) findViewById(R.id.button22);
        invite = (Button) findViewById(R.id.button13);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();

            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();

            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvent();

            }
        });

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvite();

            }
        });
    }

    public void openMap(){
        Intent intent = new Intent(this, mapview.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }

    public void openAccount(){
        Intent intent = new Intent(this, my_account.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }

    public void openEvent(){
        Intent intent = new Intent(this, my_pending_events.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }

    public void openInvite(){
        Intent intent = new Intent(this, pending_invites.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }


}