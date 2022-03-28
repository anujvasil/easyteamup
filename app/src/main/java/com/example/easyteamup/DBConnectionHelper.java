package com.example.easyteamup;

import android.os.StrictMode;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.net.ssl.HandshakeCompletedEvent;


public class DBConnectionHelper {
    Connection connection;
    String user, pass, url, schema;

    public DBConnectionHelper() {
        createConnection();
    }



    private void createConnection() {
        user = "cs310admin";
        pass = "v4zIgbKpUx6z";
        url = "jdbc:mysql://cs310.cj9r3muy3ryl.us-east-1.rds.amazonaws.com:3306/team_up";
        schema = "team_up";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;

        try {

//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        connection = conn;
    }

    public ResultSet runProcedure(String query) {
        createConnection();
        ResultSet resultSet = null;
        try {
            Statement statement;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public CallableStatement prepCall(String query) {
        createConnection();
        try {
            return connection.prepareCall(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer createEvent(Event event, String owner) {

        String query = "{CALL sp_createEvent(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setString("p_title", event.getTitle());
            stmt.setString("p_description", event.getDescription());
            stmt.setInt("p_privateEvent", event.isEventPrivate() ? 1 : 0);
            stmt.setString("p_owner", owner);

            stmt.setTimestamp("p_dueTime", event.getDueDate());
//            stmt.setInt("p_lengthMinutes", );
//            stmt.setString("p_category", );
//            stmt.setString("p_categoryField", categoryField);
//            stmt.setString("p_lat", lat);
//            stmt.setString("p_long", lon);
            stmt.setTimestamp("p_time1", event.getTime1());
            stmt.setTimestamp("p_time2", event.getTime2());
            stmt.setTimestamp("p_time3", event.getTime3());
            stmt.setTimestamp("p_time4", event.getTime4());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Integer createUser(String username, String password, String fullname, String email) {

        String query = "{CALL sp_createUser(?,?,?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setString("p_username", username);
            stmt.setString("p_password", password);
            stmt.setString("p_fullName",fullname);
            stmt.setString("p_email", email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public boolean deleteAttendee(String attendee, int id) {
        String query = "{CALL sp_deleteAttendee(?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setString("p_attendee",attendee);
            stmt.setInt("p_eventId", id);
            ResultSet rs = stmt.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Event> discoverEvents() {
        String query = "{CALL sp_discoverEvents()}";
        CallableStatement stmt = prepCall(query);
        ArrayList<Event> out = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String t = rs.getString("title");
                String desc = rs.getString("description");
                boolean priv = rs.getInt("private_event") == 1;
                Timestamp time1 = rs.getTimestamp("time_1");
                Timestamp time2 = rs.getTimestamp("time_2");
                Timestamp time3 = rs.getTimestamp("time_3");
                Timestamp time4 = rs.getTimestamp("time_4");
                Timestamp due = rs.getTimestamp("due_time");
                int id = rs.getInt("id");
                Event event = new Event(t, desc, priv, time1, time2, time3, time4, due,  0);
                event.setId(id);
                out.add(event);
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

    public ArrayList<Event> getConfirmedEvents(String username) {
        String query = "{CALL sp_getConfirmedEvents(?)}";
        CallableStatement stmt = prepCall(query);
        ArrayList<Event> out = new ArrayList<>();
        try {
            stmt.setString("p_username",username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String t = rs.getString("title");
                String desc = rs.getString("description");
                boolean priv = rs.getInt("private_event") == 1;
                Timestamp time1 = rs.getTimestamp("time_1");
                Timestamp time2 = rs.getTimestamp("time_2");
                Timestamp time3 = rs.getTimestamp("time_3");
                Timestamp time4 = rs.getTimestamp("time_4");
                Timestamp due = rs.getTimestamp("due_time");
                int id = rs.getInt("id");
                Event event = new Event(t, desc, priv, time1, time2, time3, time4, due,  0);
                event.setId(id);
                out.add(event);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public ArrayList<Event> getConfirmedInvites(String username) {
        String query = "{CALL sp_getConfirmedInvites(?)}";
        CallableStatement stmt = prepCall(query);
        ArrayList<Event> out = new ArrayList<>();
        try {
            stmt.setString("p_username",username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String t = rs.getString("title");
                String desc = rs.getString("description");
                boolean priv = rs.getInt("private_event") == 1;
                Timestamp time1 = rs.getTimestamp("time_1");
                Timestamp time2 = rs.getTimestamp("time_2");
                Timestamp time3 = rs.getTimestamp("time_3");
                Timestamp time4 = rs.getTimestamp("time_4");
                Timestamp due = rs.getTimestamp("due_time");
                int id = rs.getInt("id");
                Event event = new Event(t, desc, priv, time1, time2, time3, time4, due,  0);
                event.setId(id);
                out.add(event);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public Map<String,Integer> getConfirmedEventAttendee(int id) {
        String query = "{CALL sp_getConfirmedEventAttendee(?)}";
        CallableStatement stmt = prepCall(query);
        Map<String,Integer> out = new HashMap<>();
        try {
            stmt.setInt("p_eventId",id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String attendee = rs.getString("attendee");
                Integer vote = rs.getInt("vote");
                out.put(attendee,vote);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public Event getEvent(int id) {
        String query = "{CALL sp_getEvent(?)}";
        CallableStatement stmt = prepCall(query);
        Event out = null;
        try {
            stmt.setInt("p_id",id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String t = rs.getString("title");
            String desc = rs.getString("description");
            boolean priv = rs.getInt("private_event") == 1;
            Timestamp time1 = rs.getTimestamp("time_1");
            Timestamp time2 = rs.getTimestamp("time_2");
            Timestamp time3 = rs.getTimestamp("time_3");
            Timestamp time4 = rs.getTimestamp("time_4");
            Timestamp due = rs.getTimestamp("due_time");
            out = new Event(t, desc, priv, time1, time2, time3, time4, due,  0);
            out.setId(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public Integer getEventTime(int id) {
        String query = "{CALL sp_getEventTime(?)}";
        CallableStatement stmt = prepCall(query);
        Integer out = null;
        try {
            stmt.setInt("p_eventId",id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            out = rs.getInt("time_slot");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public ArrayList<Event> getOwnerEventsConfirmed(String owner) {
        String query = "{CALL sp_getOwnerEventsConfirmed(?)}";
        CallableStatement stmt = prepCall(query);
        ArrayList<Event> out = new ArrayList<>();
        try {
            stmt.setString("p_owner",owner);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String t = rs.getString("title");
                String desc = rs.getString("description");
                boolean priv = rs.getInt("private_event") == 1;
                Timestamp time1 = rs.getTimestamp("time_1");
                Timestamp time2 = rs.getTimestamp("time_2");
                Timestamp time3 = rs.getTimestamp("time_3");
                Timestamp time4 = rs.getTimestamp("time_4");
                Timestamp due = rs.getTimestamp("due_time");
                int id = rs.getInt("id");
                Event event = new Event(t, desc, priv, time1, time2, time3, time4, due,  0);
                event.setId(id);
                out.add(event);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public ArrayList<Event> getOwnerEventsPending(String owner) {
        String query = "{CALL sp_getOwnerEventsPending(?)}";
        CallableStatement stmt = prepCall(query);
        ArrayList<Event> out = new ArrayList<>();
        try {
            stmt.setString("p_owner",owner);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String t = rs.getString("title");
                String desc = rs.getString("description");
                boolean priv = rs.getInt("private_event") == 1;
                Timestamp time1 = rs.getTimestamp("time_1");
                Timestamp time2 = rs.getTimestamp("time_2");
                Timestamp time3 = rs.getTimestamp("time_3");
                Timestamp time4 = rs.getTimestamp("time_4");
                Timestamp due = rs.getTimestamp("due_time");
                int id = rs.getInt("id");
                Event event = new Event(t, desc, priv, time1, time2, time3, time4, due,  0);
                event.setId(id);
                out.add(event);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public ArrayList<Event> getPendingInvites(String username) {
        String query = "{CALL sp_getPendingInvites(?)}";
        CallableStatement stmt = prepCall(query);
        ArrayList<Event> out = new ArrayList<>();
        try {
            stmt.setString("p_username",username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String t = rs.getString("title");
                String desc = rs.getString("description");
                boolean priv = rs.getInt("private_event") == 1;
                Timestamp time1 = rs.getTimestamp("time_1");
                Timestamp time2 = rs.getTimestamp("time_2");
                Timestamp time3 = rs.getTimestamp("time_3");
                Timestamp time4 = rs.getTimestamp("time_4");
                Timestamp due = rs.getTimestamp("due_time");
                int id = rs.getInt("id");
                Event event = new Event(t, desc, priv, time1, time2, time3, time4, due,  0);
                event.setId(id);
                out.add(event);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public boolean inviteAttendee(String attendee, int id) {
        String query = "{CALL sp_inviteAttendee(?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setString("p_attendee",attendee);
            stmt.setInt("p_eventId", id);
            ResultSet rs = stmt.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean publicJoinAttendee(String attendee, int id) {
        String query = "{CALL sp_publicJoinAttendee(?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setString("p_attendee",attendee);
            stmt.setInt("p_eventId", id);
            ResultSet rs = stmt.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean respondInvite(String attendee, int id, boolean going) {
        String query = "{CALL sp_respondInvite(?,?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setString("p_attendee",attendee);
            stmt.setInt("p_eventId", id);
            stmt.setInt("p_going", going ? 1 : 0);
            ResultSet rs = stmt.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateEvent(Event event, String owner) {
        String query = "{CALL sp_updateEvent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setInt("p_id",event.getId());
            stmt.setString("p_title", event.getTitle());
            stmt.setString("p_description", event.getDescription());
            stmt.setInt("p_privateEvent", event.isEventPrivate() ? 1 : 0);
            stmt.setString("p_owner", owner);

            stmt.setTimestamp("p_dueTime", event.getDueDate());
//            stmt.setInt("p_lengthMinutes", );
//            stmt.setString("p_category", );
//            stmt.setString("p_categoryField", categoryField);
//            stmt.setString("p_lat", lat);
//            stmt.setString("p_long", lon);
            stmt.setTimestamp("p_time1", event.getTime1());
            stmt.setTimestamp("p_time2", event.getTime2());
            stmt.setTimestamp("p_time3", event.getTime3());
            stmt.setTimestamp("p_time4", event.getTime4());
            ResultSet rs = stmt.executeQuery();
            rs.next();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Map<String,String> validateUser(String email, String password) {
        String query = "{CALL sp_validateUser(?,?)}";
        CallableStatement stmt = prepCall(query);
        Map<String,String> out = null;
        try {
            stmt.setString("p_email", email);
            stmt.setString("p_password", password);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String username = rs.getString("username");
            String fullname = rs.getString("full_name");
            out = new HashMap<>();
            out.put("username",username);
            out.put("fullname",fullname);
            return out;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean voteEvent(String username, int id, int vote) {
        String query = "{CALL sp_voteEvent(?,?,?)}";
        CallableStatement stmt = prepCall(query);
        try {
            stmt.setString("p_username",username);
            stmt.setInt("p_eventId", id);
            stmt.setInt("p_vote",vote);
            ResultSet rs = stmt.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void close() {

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() {
        close();
    }

}
