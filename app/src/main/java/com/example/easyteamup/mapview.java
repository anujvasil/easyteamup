package com.example.easyteamup;

import static android.provider.MediaStore.Images.Media.getBitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.delegates.MapPluginProviderDelegate;
import com.mapbox.maps.viewannotation.ViewAnnotationManager;

import java.util.ArrayList;


public class mapview extends AppCompatActivity {

    Button listtoggle;
    String username, fullname, email;
    ArrayList<Event> eventList;
    private MapView mapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        DBConnectionHelper connectionHelper = ((App)getApplication()).getDatabase();
        Intent intent = getIntent();
        username = ((App) getApplication()).getUsername();
        email = ((App) getApplication()).getEmail();
        fullname = ((App) getApplication()).getFullname();
        eventList = connectionHelper.discoverEvents();

        mapView = findViewById(R.id.mapView);
        AnnotationPlugin annotationAPI = AnnotationPluginImplKt.getAnnotations((MapPluginProviderDelegate)mapView);
        PointAnnotationManager pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationAPI,mapView);
        for (Event e : eventList) {
            PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                    .withPoint(e.getLocation())
                    .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.mappin));
            pointAnnotationManager.create(pointAnnotationOptions);
        }

        listtoggle = (Button) findViewById(R.id.button19);

        listtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openList();

            }
        });

    }

    public void openList(){
        Intent intent = new Intent(this, discover_list.class);
        startActivity(intent);
    }
}