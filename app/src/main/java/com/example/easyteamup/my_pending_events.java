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

public class my_pending_events extends AppCompatActivity {

    Button confirmed;
    Button account;
    Button discover;
    Button invite;
    Button add;

    String email, username, fullname;

    private RecyclerView recycle;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public class EventAdapter extends RecyclerView.Adapter<my_pending_events.EventAdapter.EventViewHolder> {

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
        public my_pending_events.EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_event, parent, false);
            my_pending_events.EventAdapter.EventViewHolder evh = new my_pending_events.EventAdapter.EventViewHolder(v);
            return evh;
        }

        @Override
        public void onBindViewHolder(@NonNull my_pending_events.EventAdapter.EventViewHolder holder, int position) {
            Event curr = eventList.get(position);
            holder.img.setImageResource(curr.getImage());
            holder.text1.setText(curr.getDescription());
            holder.click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = holder.getAdapterPosition();
                    ((App)getApplication()).setEvent(eventList.get(i));
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
        setContentView(R.layout.activity_my_pending_events);

        Intent intent = getIntent();
        email = ((App) getApplication()).getEmail();
        username = ((App)getApplication()).getUsername();
        fullname = ((App) getApplication()).getFullname();

        confirmed = (Button) findViewById(R.id.button12);
        discover = (Button) findViewById(R.id.button23);
        account = (Button) findViewById(R.id.button22);
        add = (Button) findViewById(R.id.button11);
        invite = (Button) findViewById(R.id.button13);

        DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
        ArrayList<Event> eventList = connectionHelper.getOwnerEventsPending(username);
        eventList.addAll(connectionHelper.getPendingInvites(username));
        eventList.sort((e1,e2) -> e1.getDueDate().compareTo(e2.getDueDate()));
        recycle = findViewById(R.id.recycleViewer);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new my_pending_events.EventAdapter(eventList);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);

        confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmed();

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
                newEvent();

            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInvite();

            }
        });
    }

    public void openConfirmed(){
        Intent intent = new Intent(this, my_confirmed_events.class);
        startActivity(intent);
    }

    public void openDiscover(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }

    public void openAccount(){
        Intent intent = new Intent(this, my_account.class);
        startActivity(intent);
    }

    public void newEvent(){
        Intent intent = new Intent(this, choose_location.class);
        startActivity(intent);
    }
    public void openInvite(){
        Intent intent = new Intent(this, pending_invites.class);
        startActivity(intent);
    }

    public void openEventInfo(){
        Intent intent = new Intent(this, event_info.class);
        startActivity(intent);
    }

}