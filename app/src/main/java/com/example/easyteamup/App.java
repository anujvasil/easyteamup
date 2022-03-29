package com.example.easyteamup;

import android.app.Application;

public class App extends Application {
    private static App singleton;
    DBConnectionHelper connectionHelper;

    public App getInstance(){
        return singleton;
    }

    public DBConnectionHelper getDatabase(){
        return connectionHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        connectionHelper = new DBConnectionHelper();
        singleton = this;
    }
}

