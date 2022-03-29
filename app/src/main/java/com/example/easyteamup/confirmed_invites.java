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

public class confirmed_invites extends AppCompatActivity {


    private RecyclerView recycle;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Button pending;
    Button events;
    Button discover;
    Button account;

    public class EventAdapter extends RecyclerView.Adapter<confirmed_invites.EventAdapter.EventViewHolder> {

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
        public confirmed_invites.EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_event, parent, false);
            confirmed_invites.EventAdapter.EventViewHolder evh = new confirmed_invites.EventAdapter.EventViewHolder(v);
            return evh;
        }

        @Override
        public void onBindViewHolder(@NonNull confirmed_invites.EventAdapter.EventViewHolder holder, int position) {
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
        setContentView(R.layout.activity_confirmed_invites);

        String username = ((App)getApplication()).getUsername();

        pending = (Button) findViewById(R.id.button27);
        events = (Button) findViewById(R.id.button21);
        discover = (Button) findViewById(R.id.button23);
        account = (Button) findViewById(R.id.button22);
        DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
        ArrayList<Event> eventList = connectionHelper.getConfirmedInvites(username);

        recycle = findViewById(R.id.recycleViewer);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new confirmed_invites.EventAdapter(eventList);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPending();

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();

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
                openAccounts();

            }
        });
    }
    public void openPending(){
        Intent intent = new Intent(this, pending_invites.class);
        startActivity(intent);
    }
    public void openEvents(){
        Intent intent = new Intent(this, my_pending_events.class);
        startActivity(intent);
    }
    public void openDiscover(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }
    public void openAccounts(){
        Intent intent = new Intent(this, my_account.class);
        startActivity(intent);
    }
    public void openEventInfo(){
        Intent intent = new Intent(this, event_info.class);
        startActivity(intent);
    }
}