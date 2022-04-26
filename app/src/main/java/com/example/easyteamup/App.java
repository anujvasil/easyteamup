package com.example.easyteamup;

import android.app.Application;
import android.net.Uri;
import android.os.StrictMode;

public class App extends Application {
    private static App singleton;
    DBConnectionHelper connectionHelper;
    private String username;
    private String email;
    private String fullname;
    private Event event;
    private Uri profile_pic;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public Uri getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Uri pic) {
        this.profile_pic = pic;
    }


    public App getInstance(){
        return singleton;
    }


    public DBConnectionHelper getDatabase(){
        return connectionHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connectionHelper = new DBConnectionHelper();
        singleton = this;
    }
}

