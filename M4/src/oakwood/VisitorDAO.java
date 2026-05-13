package oakwood;

import java.sql.*;

public class VisitorDAO {
    public void addVisitor(Visitor v) {
        if (v.getName().isEmpty() ||
                v.getResidentToVisit().isEmpty() ||
                v.getPurpose().isEmpty() ||
                v.getDate().isEmpty() ||
                v.getTimeIn().isEmpty()) {

            System.out.println("Visitor Registration Failed!");
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO visitors (name, resident_to_visit, purpose, date, time_in, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, v.getName());
            ps.setString(2, v.getResidentToVisit());
            ps.setString(3, v.getPurpose());
            ps.setString(4, v.getDate());
            ps.setString(5, v.getTimeIn());
            ps.setString(6, v.getStatus());

            ps.executeUpdate();

            System.out.println("Visitor successfully registered!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewVisitors() {
        try {
            Connection conn = DBConnection.getConnection();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM visitors");
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("resident_to_visit") + " | " +
                        rs.getString("purpose") + " | " +
                        rs.getString("date") + " | " +
                        rs.getString("time_in") + " | " +
                        rs.getString("status")
                );
            }

            if (!hasData) {
                System.out.println("No visitor records available.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void visitorExit(int id) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE visitors SET status = 'Exited' WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Visitor marked as exited!");
            } else {
                System.out.println("Visitor not found.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
