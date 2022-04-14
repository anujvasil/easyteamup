package com.example.easyteamup;


import android.util.Log;

import com.mapbox.geojson.Point;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

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


    private String owner;

    public Event(String t, String desc, boolean pri, Timestamp t1, Timestamp t2, Timestamp t3, Timestamp t4, Timestamp due, int img, double latitude, double longitude, String user) {
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
        owner = user;
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
    public String getOwner() {
        return owner;
    }

    public Integer getId() {
        return this.id;
    }

    void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isPrivate=" + isPrivate +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", time3=" + time3 +
                ", time4=" + time4 +
                ", dueDate=" + dueDate +
                ", lat=" + lat +
                ", lng=" + lng +
                ", id=" + id +
                ", owner='" + owner + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return isPrivate == event.isPrivate && image == event.image && Double.compare(event.lat, lat) == 0 && Double.compare(event.lng, lng) == 0 && Objects.equals(title, event.title) && Objects.equals(description, event.description) && Objects.equals(time1, event.time1) && Objects.equals(time2, event.time2) && Objects.equals(time3, event.time3) && Objects.equals(time4, event.time4) && Objects.equals(dueDate, event.dueDate) && Objects.equals(id, event.id) && Objects.equals(owner, event.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, isPrivate, time1, time2, time3, time4, dueDate, image, lat, lng, id, owner);
    }
}
