package com.example.easyteamup;

public class Event {

    private String type;
    private String description;
    private boolean isPrivate;
    private String time1;
    private String time2;
    private String time3;
    private String time4;
    private String dueDate;
    private int image;

    public Event(String t, String desc, boolean pri, String t1, String t2, String t3, String t4, String due, int img) {
        type = t;
        description = desc;
        isPrivate = pri;
        time1 = t1;
        time2 = t2;
        time3 = t3;
        time4 = t4;
        dueDate = due;
        image = img;
    }

    public String getType() {
        return this.type;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean isEventPrivate() {
        return this.isPrivate;
    }
    public String getTime1() {
        return this.time1;
    }
    public String getTime2() {
        return this.time2;
    }
    public String getTime3() {
        return this.time3;
    }
    public String getTime4() {
        return this.time4;
    }
    public String getDueDate() {
        return this.dueDate;
    }
    public int getImage() {
        return this.image;
    }


}
