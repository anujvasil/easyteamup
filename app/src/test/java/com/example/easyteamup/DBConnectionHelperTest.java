package com.example.easyteamup;

import org.junit.After;
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

/**
 *
 */
public class DBConnectionHelperTest {

    DBConnectionHelper connectionHelper;

    Integer priv_id = null;
    Integer pub_id = null;

    @Before
    public void dbConnectionTest() {
        connectionHelper = new DBConnectionHelper();
    }

    @Test
    public void queryTest() throws SQLException {
        String query = "SELECT * FROM events WHERE private_event = FALSE;";
        ResultSet resultSet = connectionHelper.runProcedure(query);
        ArrayList<Event> events = new ArrayList<>();
        while (resultSet.next()) {
            Event event = new Event(resultSet.getString("title"), resultSet.getString("description"), false, resultSet.getTimestamp("time_1"), resultSet.getTimestamp("time_2"),
                    resultSet.getTimestamp("time_3"), resultSet.getTimestamp("time_4"), resultSet.getTimestamp("due_time"), 0, resultSet.getDouble("latitude"), resultSet.getDouble("longitude"), resultSet.getString("owner"));
            event.setId(resultSet.getInt("id"));
            events.add(event);
        }
        ArrayList<Event> events2 = connectionHelper.discoverEvents();
        for (int i = 0; i < events.size(); ++i) {
            assertEquals(events.get(i).toString(), events2.get(i).toString());
        }

    }

    @Test
    public void eventTest() {
        Event priv_event = new Event("UnitTest1", "Test from jUnit", true, new Timestamp(122, 0, 1, 2, 3, 0, 0), null, null, null, new Timestamp(122, 1, 2, 3, 4, 0, 0), 0, 0.0, 0.0, "jUnit Tester");
        Event pub_event = new Event("UnitTest1", "Test from jUnit", false,new Timestamp(122, 0, 1, 2, 3, 0, 0), null, null, null, new Timestamp(122, 1, 2, 3, 4, 0, 0), 0, 0.0, 0.0, "jUnit Tester");
        priv_id = connectionHelper.createEvent(priv_event, "jUnit Tester");
        pub_id = connectionHelper.createEvent(pub_event,"jUnit Tester");
        priv_event.setId(priv_id);
        pub_event.setId(pub_id);
        Event event2 = connectionHelper.getEvent(priv_id);
        Event event3 = connectionHelper.getEvent(pub_id);
        assertEquals(priv_event, event2);
        assertEquals(pub_event,event3);
    }

    @Test
    public void validUserTest() {
        Map<String, String> userInfo = connectionHelper.validateUser("jsmith@gmail.com", "password123");
        assertNotNull(userInfo);
        assertEquals("John Smith", userInfo.get("fullname"));
        assertEquals("johnsmith", userInfo.get("username"));
        assertNull(connectionHelper.validateUser("jsmith@gmail.com", "password124"));
    }

    @Test
    public void closeTest() {
        connectionHelper.close();
        assertNotNull(connectionHelper);
    }
    @Test
    public void eventTimeTest() {
        Event event = new Event("UnitTest1","Test from jUnit",true,new Timestamp(122,0,1,2,3,0,0),null,null,null,new Timestamp(122,1,2,3,4,0,0),0,0.0,0.0,"jUnit Tester");
        int id = connectionHelper.createEvent(event,"jUnit Tester");
        event.setId(id);
        Integer temp = connectionHelper.getEventTime(id);
        assertNull(temp);
    }



    @Test
    public void inviteEventTest() throws Exception {
        eventTest();
        connectionHelper.inviteAttendee("johnsmith", priv_id);
        ResultSet rs = connectionHelper.runProcedure("SELECT * FROM attendees WHERE attendee = 'johnsmith' AND event_id = " + priv_id.toString());
        rs.next();
        assertEquals(1,rs.getInt("invited"));
    }
    @Test
    public void joinAttendeeTest() throws Exception{
        eventTest();
        connectionHelper.publicJoinAttendee("johnsmith",pub_id);
        ResultSet rs = connectionHelper.runProcedure("SELECT * FROM attendees WHERE attendee = 'johnsmith' AND event_id = " + pub_id.toString());
        rs.next();
        assertEquals("johnsmith",rs.getString("attendee"));
    }
    @Test
    public void pendingInvitesTest() throws Exception {
        eventTest();
        inviteEventTest();
        ArrayList<Event> al = connectionHelper.getPendingInvites("johnsmith");
        ArrayList<Integer> al1 = new ArrayList<>();
        for (Event e : al) {
            al1.add(e.getId());
        }
        assertTrue(al1.contains(priv_id));
    }
    @Test
    public void updateEventTest() throws Exception {
        eventTest();
        Event event = connectionHelper.getEvent(priv_id);
        event.setDescription("New description");
        connectionHelper.updateEvent(event,event.getOwner());
        Event event2 = connectionHelper.getEvent(priv_id);
        assertEquals("New description", event2.getDescription());
    }
    @Test
    public void createUserTest() {
        Integer uid = connectionHelper.createUser("test1","test1","test1","test1");
        assertNotNull(uid);
        connectionHelper.runProcedure("DELETE FROM users WHERE id = " + uid.toString(),true);
    }
    @Test
    public void voteEventTest() throws Exception {
        eventTest();
        inviteEventTest();
        connectionHelper.voteEvent("johnsmith",priv_id,1);
        ResultSet rs = connectionHelper.runProcedure("SELECT * FROM attendees WHERE event_id = " + priv_id.toString());
        rs.next();
        assertEquals(1,rs.getInt("vote"));
    }
    @Test
    public void confirmedEventTest() throws Exception {
        eventTest();
        joinAttendeeTest();
        ArrayList<Event> al = connectionHelper.getConfirmedEvents("johnsmith");
        ArrayList<Integer> al1 = new ArrayList<>();
        for (Event e : al) {
            al1.add(e.getId());
        }

        assertTrue(al1.contains(pub_id));

    }
    @After
    public void cleanup() {
        connectionHelper.runProcedure("DELETE FROM events WHERE title = 'UnitTest1'",true);
    }
}