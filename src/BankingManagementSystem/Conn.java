package BankingManagementSystem;

import java.sql.*;

public class Conn {

    public Connection c;
    public Statement stmt;

    public Conn() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

        
            String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
            String user = "root";
            String password = "Vijay@123"; 

            c = DriverManager.getConnection(url, user, password);
            stmt = c.createStatement();
        } catch (Exception e) {
            System.err.println("Database connection failed:");
            e.printStackTrace(); 
        }
    }
}

