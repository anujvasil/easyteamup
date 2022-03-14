package com.example.easyteamup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class discover_list extends AppCompatActivity {

    private RecyclerView recycle;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event("type", "desc", false, "t1","t2","t3","t4", "due", com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal));
        eventList.add(new Event("type", "desc", false, "t1","t2","t3","t4", "due", com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal));
        eventList.add(new Event("type", "desc", false, "t1","t2","t3","t4", "due", com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal));
        eventList.add(new Event("type", "desc", false, "t1","t2","t3","t4", "due", com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal));

        recycle = findViewById(R.id.recycleViewer);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new EventAdapter(eventList);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);
    }


}