package oakwood;

import java.sql.*;

public class ResidentDAO {
    public void addResident(Resident r) {
        if (r.getName().isEmpty() ||
                r.getAddress().isEmpty() ||
                r.getContact().isEmpty() ||
                r.getEmail().isEmpty() ||
                !r.getContact().matches("\\d{10,11}") ||
                !r.getEmail().matches("^[A-Za-z0-9+_.-]+@gmail\\.com$")) {

            System.out.println("Registration Failed!");
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO residents (name, address, contact, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setString(3, r.getContact());
            ps.setString(4, r.getEmail());

            ps.executeUpdate();

            System.out.println("Resident Successfully Registered!");

        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    public void viewResidents() {
        try {
            Connection conn = DBConnection.getConnection();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM residents");
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("address") + " | " +
                        rs.getString("contact") + " | " +
                        rs.getString("email")
                );
            }

            if (!hasData) {
                System.out.println("No residents found.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean checkResident(String name) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM residents WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                System.out.println("Resident not found.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}

