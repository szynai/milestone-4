package oakwood;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.3:3306/hoa_db";
    private static final String USER = "root";
    private static final String PASS = "Red123lyn45";

  public static Connection getConnection() throws Exception {
  return DriverManager.getConnection(URL, USER, PASS);
}
} 

