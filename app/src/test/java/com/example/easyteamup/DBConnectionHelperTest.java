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

/**
 *
 */
public class DBConnectionHelperTest {

    DBConnectionHelper connectionHelper;
    @Before
    public void dbConnectionTest() {
        connectionHelper = new DBConnectionHelper();
    }
    @Test
    public void queryTest() throws SQLException {
        String query = "SELECT * FROM events WHERE private_event = FALSE;";
        ResultSet resultSet = connectionHelper.runProcedure(query);
        ArrayList<Event> events = new ArrayList<>();
        while(resultSet.next()) {
            Event event = new Event(resultSet.getString("title"),resultSet.getString("description"),false,resultSet.getTimestamp("time_1"),resultSet.getTimestamp("time_2"),
                    resultSet.getTimestamp("time_3"),resultSet.getTimestamp("time_4"),resultSet.getTimestamp("due_time"),0,resultSet.getDouble("latitude"),resultSet.getDouble("longitude"),resultSet.getString("owner"));
            event.setId(resultSet.getInt("id"));
            events.add(event);
        }
        ArrayList<Event> events2 = connectionHelper.discoverEvents();
        for (int i = 0; i < events.size(); ++i) {
            assertEquals(events.get(i).toString(), events2.get(i).toString());
        }

    }
    @Test
    public void eventTest(){
        Event event = new Event("UnitTest1","Test from jUnit",true,new Timestamp(122,0,1,2,3,0,0),null,null,null,new Timestamp(122,1,2,3,4,0,0),0,0.0,0.0,"jUnit Tester");
        int id = connectionHelper.createEvent(event,"jUnit Tester");
        event.setId(id);
        Event event2 = connectionHelper.getEvent(id);
        assertEquals(event,event2);
    }
    @Test
    public void validUserTest() {
        Map<String, String> userInfo = connectionHelper.validateUser("jsmith@gmail.com", "password123");
        assertNotNull(userInfo);
        assertEquals("John Smith", userInfo.get("fullname"));
        assertEquals("johnsmith", userInfo.get("username"));
        assertNull(connectionHelper.validateUser("jsmith@gmail.com", "password124"));
    }
}