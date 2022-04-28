package com.example.easyteamup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.LocationProvider;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.geojson.Point;
import com.mapbox.search.MapboxSearchSdk;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.search.ResponseInfo;
import com.mapbox.search.SearchEngine;
import com.mapbox.search.SearchOptions;
import com.mapbox.search.SearchRequestTask;
import com.mapbox.search.SearchSuggestionsCallback;
import com.mapbox.search.result.SearchResult;
import com.mapbox.search.result.SearchSuggestion;
import com.mapbox.search.ui.view.SearchBottomSheetView;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;

public class post_event extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button back;
    Button post;
    Button time1B, time2B, time3B, time4B, timeDue;

    Integer month, day, year, hour, minute;
    Integer time1day, time1month, time1year, time1hour, time1minute;
    Integer time2day, time2month, time2year, time2hour, time2minute;
    Integer time3day, time3month, time3year, time3hour, time3minute;
    Integer time4day, time4month, time4year, time4hour, time4minute;
    Integer dueday, duemonth, dueyear, duehour, dueminute;
    String username, email, fullname;
    int lastbutton;

    Event event;

    Timestamp time1, time2, time3, time4, due;
    String desc, type;
    Boolean priv, editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

        EditText descriptionText = (EditText) findViewById(R.id.editTextTextPersonName4);
        CheckBox privateCheckbox = (CheckBox) findViewById(R.id.checkBox);
        event = ((App)getApplication()).getEvent();

        username = ((App)getApplication()).getUsername();
        email = ((App)getApplication()).getEmail();
        fullname = ((App)getApplication()).getFullname();
        time1day = null; time1month = null; time1year = null; time1hour = null; time1minute = null;

        back = (Button) findViewById(R.id.button20);
        post = (Button) findViewById(R.id.button14);
        time1B = (Button) findViewById(R.id.editTextTime);
        time1B.setText("Time Slot 1");
        time2B = (Button) findViewById(R.id.editTextTime2);
        time2B.setText("Time Slot 2");
        time3B = (Button) findViewById(R.id.editTextTime3);
        time3B.setText("Time Slot 3");
        time4B = (Button) findViewById(R.id.editTextTime4);
        time4B.setText("Time Slot 4");
        timeDue = (Button) findViewById(R.id.editTextDate);
        timeDue.setText("Due Date");
        editing = false;
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getBooleanExtra("Editing",false) && event != null &&username.equals(event.getOwner())) {
            editing = true;
            descriptionText.setText(event.getDescription());
            privateCheckbox.setChecked(event.isEventPrivate());
            time1B.setText("Time Slot 1: " + event.getTime1().getHours() + ":" + event.getTime1().getMinutes() + " " + (event.getTime1().getMonth() + 1) + "/" + event.getTime1().getDay() + "/" + event.getTime1().getYear());
            time2B.setText("Time Slot 2: " + event.getTime2().getHours() + ":" + event.getTime2().getMinutes() + " " + (event.getTime2().getMonth() + 1) + "/" + event.getTime2().getDay() + "/" + event.getTime2().getYear());
            time3B.setText("Time Slot 3: " + event.getTime3().getHours() + ":" + event.getTime3().getMinutes() + " " + (event.getTime3().getMonth() + 1) + "/" + event.getTime3().getDay() + "/" + event.getTime3().getYear());
            time4B.setText("Time Slot 4: " + event.getTime4().getHours() + ":" + event.getTime4().getMinutes() + " " + (event.getTime4().getMonth() + 1) + "/" + event.getTime4().getDay() + "/" + event.getTime4().getYear());
            timeDue.setText("Due Date: " + event.getDueDate().getHours() + ":" + event.getDueDate().getMinutes() + " " + (event.getDueDate().getMonth() + 1) + "/" + event.getDueDate().getDay() + "/" + event.getDueDate().getYear());

        }


////        LocationEngine locationEngine = LocationEngineProvider.getBestLocationEngine(this);
////        MapboxSearchSdk.initialize(getApplication(), getResources().getString(R.string.mapbox_access_token), locationEngine);
////
////        SearchEngine searchEngine = MapboxSearchSdk.getSearchEngine();
////        SearchOptions options = new SearchOptions();
////        SearchSuggestionsCallback onSearchResults = new SearchSuggestionsCallback() {
////            @Override
////            public void onSuggestions(@NonNull List<? extends SearchSuggestion> list, @NonNull ResponseInfo responseInfo) {
////                for (SearchSuggestion s : list) {
////                    Log.e("suggestion: ", s.getName());
////                }
////            }
////
////            @Override
////            public void onError(@NonNull Exception e) {
////                return;
////            }
////        };
////        SearchRequestTask searchRequestTask = searchEngine.search("Paris", options, onSearchResults);
//
//
//        SearchBottomSheetView searchBottomSheetView = (SearchBottomSheetView) findViewById(R.id.search_view);
//        searchBottomSheetView.initializeSearch(savedInstanceState, new SearchBottomSheetView.Configuration());
//        searchBottomSheetView.setHideableByDrag(true);
//        searchBottomSheetView.addOnSearchResultClickListener(new SearchBottomSheetView.OnSearchResultClickListener() {
//            @Override
//            public void onSearchResultClick(@NonNull SearchResult searchResult, @NonNull ResponseInfo responseInfo) {
//                Point p = searchResult.getCoordinate();
//                p_lat = p.latitude();
//                p_long = p.longitude();
//                searchBottomSheetView.hide();
//                Log.e("test ", p.toString());
//            }
//        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();

            }
        });

        time1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastbutton = 0;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(post_event.this, post_event.this,year, month,day);
                datePickerDialog.show();
            }
        });
        time2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastbutton = 1;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(post_event.this, post_event.this,year, month,day);
                datePickerDialog.show();
            }
        });
        time3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastbutton = 2;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(post_event.this, post_event.this,year, month,day);
                datePickerDialog.show();
            }
        });
        time4B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastbutton = 3;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(post_event.this, post_event.this,year, month,day);
                datePickerDialog.show();
            }
        });
        timeDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastbutton = 4;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(post_event.this, post_event.this,year, month,day);
                datePickerDialog.show();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                type = findViewById(R.id.spinner).get;
                desc = ((EditText) findViewById(R.id.editTextTextPersonName4)).getText().toString();
                if(desc.isEmpty()) {
                    return;
                }
                time1 = null; time2 = null; time3 = null; time4 = null; due = null;
                if (time1day != null && time1minute != null && time1hour != null && time1month != null && time1year != null) {
                    time1 = new Timestamp(time1year-1900, time1month, time1day, time1hour, time1minute,0,0);
                }
                if (time2day != null && time2minute != null && time2hour != null && time2month != null && time2year != null) {
                    time2 = new Timestamp(time2year-1900, time2month, time2day, time2hour, time2minute,0,0);
                }
                if (time3day != null && time3minute != null && time3hour != null && time3month != null && time3year != null) {
                    time3 = new Timestamp(time3year-1900, time3month, time3day, time3hour, time3minute,0,0);
                }
                if (time4day != null && time4minute != null && time4hour != null && time4month != null && time4year != null) {
                    time4 = new Timestamp(time4year-1900, time4month, time4day, time4hour, time4minute,0,0);
                }
                if (dueday != null && dueminute != null && duehour != null && duemonth != null && dueyear != null) {
                    due = new Timestamp(dueyear-1900, duemonth, dueday, duehour, dueminute,0,0);
                }
                priv = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
                if (editing) {
                    event.setDescription(desc);
                    event.setPrivate(priv);
                    event.setTime1(time1);
                    event.setTime2(time2);
                    event.setTime3(time3);
                    event.setTime4(time4);

                    event.setDueDate(due);
                    DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
                    connectionHelper.updateEvent(event,username);
                }
                else {
                    double lat = ((App)getApplication()).getLat();
                    double lng = ((App)getApplication()).getLng();
                    Event newEvent = new Event(null, desc, priv, time1, time2, time3, time4, due, 0, lat, lng, username);
                    DBConnectionHelper connectionHelper = ((App) getApplication()).getDatabase();
                    connectionHelper.createEvent(newEvent, username);
                }
                postEvent();

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        switch (lastbutton) {
            case 0: time1year = year;
                time1day = dayOfMonth;
                time1month = month;
                break;
            case 1: time2year = year;
                time2day = dayOfMonth;
                time2month = month;
                break;
            case 2: time3year = year;
                time3day = dayOfMonth;
                time3month = month;
                break;
            case 3: time4year = year;
                time4day = dayOfMonth;
                time4month = month;
                break;
            case 4: dueyear = year;
                dueday = dayOfMonth;
                duemonth = month;
                break;
        }
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(post_event.this, post_event.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        switch (lastbutton) {
            case 0:
                time1hour = hourOfDay;
                time1minute = minute;
                time1B.setText("Time Slot 1: " + time1hour + ":" + time1minute + " " + (time1month + 1) + "/" + time1day + "/" + time1year);
                break;
            case 1:
                time2hour = hourOfDay;
                time2minute = minute;
                time2B.setText("Time Slot 2: " + time2hour + ":" + time2minute + " " + (time2month + 1) + "/" + time2day + "/" + time2year);
                break;
            case 2:
                time3hour = hourOfDay;
                time3minute = minute;
                time3B.setText("Time Slot 3: " + time3hour + ":" + time3minute + " " + (time3month + 1) + "/" + time3day + "/" + time3year);
                break;
            case 3:
                time4hour = hourOfDay;
                time4minute = minute;
                time1B.setText("Time Slot 4: " + time4hour + ":" + time4minute + " " + (time4month + 1) + "/" + time4day + "/" + time4year);
                break;
            case 4:
                duehour = hourOfDay;
                dueminute = minute;
                timeDue.setText("Due Date: " + duehour + ":" + dueminute + " " + (duemonth + 1) + "/" + dueday + "/" + dueyear);
                break;
        }
    }



    public void goBack(){
        Intent intent = new Intent(this, my_pending_events.class);
        startActivity(intent);
    }

    public void postEvent(){
        Intent intent = new Intent(this, my_pending_events.class);
        startActivity(intent);
    }
}