package com.example.easyteamup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.mapbox.geojson.Point;


public class EventTest {
    @Test
    public void eventHashTest() {
        Event event = new Event("UnitTest1", "Test from jUnit", true, new Timestamp(122, 0, 1, 2, 3, 0, 0), null, null, null, new Timestamp(122, 1, 2, 3, 4, 0, 0), 0, 0.0, 0.0, "jUnit Tester");
        int hash = event.hashCode();
        assertNotNull(hash);
    }

    @Test
    public void pointTest() {
        Event event = new Event("UnitTest1", "Test from jUnit", true, new Timestamp(122, 0, 1, 2, 3, 0, 0), null, null, null, new Timestamp(122, 1, 2, 3, 4, 0, 0), 0, 0.0, 0.0, "jUnit Tester");
        event.getLocation();
        assertNotNull(event);
        Point p = Point.fromLngLat(9, 9);
        assertEquals(9, p.longitude(), 0);
        assertEquals(9, p.latitude(), 0);
    }
}
