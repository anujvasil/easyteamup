package com.example.easyteamup;

import android.app.Application;
import android.net.Uri;
import android.os.StrictMode;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.search.MapboxSearchSdk;

public class App extends Application {
    private static App singleton;
    DBConnectionHelper connectionHelper;
    private String username;
    private String email;
    private String fullname;
    private double lat = 0, lng = 0;
    private Event event;
    private Uri profile_pic;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

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
        LocationEngine locationEngine = LocationEngineProvider.getBestLocationEngine(this);
        MapboxSearchSdk.initialize(this, getResources().getString(R.string.mapbox_access_token), locationEngine);

    }
}

