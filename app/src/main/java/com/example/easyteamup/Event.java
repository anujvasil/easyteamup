package com.example.easyteamup;

import android.util.Log;

import com.mapbox.geojson.Point;

import java.sql.Time;
import java.sql.Timestamp;

public class Event {

    private String title;
    private String description;
    private boolean isPrivate;
    private Timestamp time1;
    private Timestamp time2;
    private Timestamp time3;
    private Timestamp time4;
    private Timestamp dueDate;
    private int image;
    private double lat;
    private double lng;
    private Integer id;

    public Event(String t, String desc, boolean pri, Timestamp t1, Timestamp t2, Timestamp t3, Timestamp t4, Timestamp due, int img, double latitude, double longitude) {
        title = t;
        description = desc;
        isPrivate = pri;
        time1 = t1;
        time2 = t2;
        time3 = t3;
        time4 = t4;
        dueDate = due;
        image = img;
        lat = latitude;
        lng = longitude;
        id = null;
    }

    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean isEventPrivate() {
        return this.isPrivate;
    }
    public Timestamp getTime1() {
        return this.time1;
    }
    public Timestamp getTime2() {
        return this.time2;
    }
    public Timestamp getTime3() {
        return this.time3;
    }
    public Timestamp getTime4() {
        return this.time4;
    }
    public Timestamp getDueDate() {
        return this.dueDate;
    }
    public int getImage() {
        return this.image;
    }
    public Point getLocation() {
        Point p = Point.fromLngLat(lng, lat);
        return p;
    }

    public Integer getId() {
        return this.id;
    }

    void setId(int id) {
        this.id = id;
    }

}
