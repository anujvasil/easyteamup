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

import java.util.ArrayList;

public class my_confirmed_events extends AppCompatActivity {

    Button pending;
    Button discover;
    Button account;
    Button add;
    Button invite;

    private RecyclerView recycle;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    String email, username, fullname;

    public class EventAdapter extends RecyclerView.Adapter<my_confirmed_events.EventAdapter.EventViewHolder> {

        private ArrayList<Event> eventList;


        public class EventViewHolder extends RecyclerView.ViewHolder {

            public ImageView img;
            public TextView text1;
            public Button click;

            public EventViewHolder(View itemview) {
                super(itemview);
                img = itemview.findViewById(R.id.imageView8);
                text1 = itemview.findViewById(R.id.textView4);
                click = itemview.findViewById(R.id.button16);
            }
        }

        public EventAdapter(ArrayList<Event> l) {
            eventList = l;
        }

        @NonNull
        @Override
        public my_confirmed_events.EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_event, parent, false);
            my_confirmed_events.EventAdapter.EventViewHolder evh = new my_confirmed_events.EventAdapter.EventViewHolder(v);
            return evh;
        }

        @Override
        public void onBindViewHolder(@NonNull my_confirmed_events.EventAdapter.EventViewHolder holder, int position) {
            Event curr = eventList.get(position);
            holder.img.setImageResource(curr.getImage());
            holder.text1.setText(curr.getDescription());
            holder.click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openEventInfo();

                }
            });
        }

        @Override
        public int getItemCount() {
            return eventList.size();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_confirmed_events);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        fullname = intent.getStringExtra("fullname");

        DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
        ArrayList<Event> eventList = connectionHelper.discoverEvents();
        recycle = findViewById(R.id.recycleViewer);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new my_confirmed_events.EventAdapter(eventList);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);

        pending = (Button) findViewById(R.id.button25);
        discover = (Button) findViewById(R.id.button23);
        account = (Button) findViewById(R.id.button22);
        add = (Button) findViewById(R.id.button24);
        invite = (Button) findViewById(R.id.button13);


        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPending();

            }
        });

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiscover();

            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();

            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvite();

            }
        });

    }

    public void openPending(){
        Intent intent = new Intent(this, my_pending_events.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("fullname",fullname);
        startActivity(intent);
    }

    public void openDiscover(){
        Intent intent = new Intent(this, discover_list.class);
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

    public void addEvent(){
        Intent intent = new Intent(this, post_event.class);
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

    public void openEventInfo(){
        Intent intent = new Intent(this, event_info.class);
        startActivity(intent);
    }

}