package oakwood;

import java.sql.*;

public class PaymentDAO {
    public void addPayment(String reservationType, int reservationId, ReservationPayment payment) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO payments " +
                    "(reservation_type, reservation_id, amount, vat_amount, discount_amount, total_amount, payment_method, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, reservationType);
            ps.setInt(2, reservationId);
            ps.setDouble(3, payment.getAmount());
            ps.setDouble(4, payment.getVatAmount());
            ps.setDouble(5, payment.getDiscountAmount());
            ps.setDouble(6, payment.getTotalAmount());
            ps.setString(7, payment.getPaymentMethod());
            ps.setString(8, "Paid");

            ps.executeUpdate();

            System.out.println("Payment transaction saved successfully.");

        } catch (Exception e) {
            System.out.println("Payment saving failed: " + e.getMessage());
        }
    }

    public void showIncomeStatement() {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT " +
                    "COUNT(*) AS total_transactions, " +
                    "SUM(amount) AS gross_income, " +
                    "SUM(vat_amount) AS total_vat, " +
                    "SUM(discount_amount) AS total_discount, " +
                    "SUM(total_amount) AS net_income " +
                    "FROM payments WHERE status = 'Paid'";

            ResultSet rs = conn.createStatement().executeQuery(sql);

            System.out.println("\n=== HOA INCOME STATEMENT ===");

            if (rs.next()) {
                System.out.println("Total Transactions: " + rs.getInt("total_transactions"));
                System.out.printf("Gross Income: PHP %.2f%n", rs.getDouble("gross_income"));
                System.out.printf("Total VAT: PHP %.2f%n", rs.getDouble("total_vat"));
                System.out.printf("Total Discount: PHP %.2f%n", rs.getDouble("total_discount"));
                System.out.printf("Net Income: PHP %.2f%n", rs.getDouble("net_income"));
            }

        } catch (Exception e) {
            System.out.println("Failed to generate income statement: " + e.getMessage());
        }
    }
}

