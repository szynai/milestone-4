package oakwood;

import java.sql.*;

public class EventDAO {

    public int addEvent(Event e) {
        int eventId = -1;

        if (e.getName().isEmpty() ||
                e.getContact().isEmpty() ||
                e.getEventName().isEmpty() ||
                e.getEventDate().isEmpty() ||
                !e.getContact().matches("\\d{10,11}")) {

            System.out.println("Event Registration Failed!");
            return eventId;
        }

        try {
            Connection conn = DBConnection.getConnection();

            // Check if there is already an active event on the same date
            String checkSql = "SELECT * FROM events WHERE event_date = ? AND status = 'Active'";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, e.getEventDate());

            ResultSet checkRs = checkPs.executeQuery();

            if (checkRs.next()) {
                System.out.println("Event Registration Failed!");
                System.out.println("There is already an active event registered on this date.");
                return eventId;
            }

            // Insert event if date is still available
            String sql = "INSERT INTO events (name, contact, event_name, event_date, status) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getName());
            ps.setString(2, e.getContact());
            ps.setString(3, e.getEventName());
            ps.setString(4, e.getEventDate());
            ps.setString(5, e.getStatus());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                eventId = generatedKeys.getInt(1);
            }

            System.out.println("Event Reservation Created Successfully.");

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return eventId;
    }

    public void viewEvents() {
        try {
            Connection conn = DBConnection.getConnection();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM events");
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("contact") + " | " +
                        rs.getString("event_name") + " | " +
                        rs.getString("event_date") + " | " +
                        rs.getString("status")
                );
            }

            if (!hasData) {
                System.out.println("No events available.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cancelEvent(int id) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE events SET status = 'Cancelled' WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Event cancelled!");
            } else {
                System.out.println("Event not found.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

