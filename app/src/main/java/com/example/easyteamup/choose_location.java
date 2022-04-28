package com.example.easyteamup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.search.ResponseInfo;
import com.mapbox.search.result.SearchResult;
import com.mapbox.search.ui.view.SearchBottomSheetView;

public class choose_location extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        SearchBottomSheetView searchBottomSheetView = (SearchBottomSheetView) findViewById(R.id.search_view);
        searchBottomSheetView.initializeSearch(savedInstanceState, new SearchBottomSheetView.Configuration());
        searchBottomSheetView.setHideableByDrag(false);
        searchBottomSheetView.addOnSearchResultClickListener(new SearchBottomSheetView.OnSearchResultClickListener() {
            @Override
            public void onSearchResultClick(@NonNull SearchResult searchResult, @NonNull ResponseInfo responseInfo) {
                Point p = searchResult.getCoordinate();
                ((App)getApplication()).setLat(p.latitude());
                ((App)getApplication()).setLng(p.longitude());
                continueToEvent();
            }
        });
    }

    public void continueToEvent() {
        Intent intent = new Intent(this, post_event.class);
        startActivity(intent);
    }
}
