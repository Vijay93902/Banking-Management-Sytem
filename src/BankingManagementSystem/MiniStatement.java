package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    public MiniStatement(String pinnumber) {

        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 200, 20);
        bank.setFont(new Font("System", Font.BOLD, 16));
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 60, 350, 20);
        add(card);

        JLabel mini = new JLabel();
        mini.setBounds(20, 100, 350, 300);
        add(mini);

        JLabel balance = new JLabel();
        balance.setBounds(20, 420, 350, 20);
        add(balance);

        try {
            Conn conn = new Conn();

        
            ResultSet rs = conn.stmt.executeQuery(
                "SELECT * FROM login WHERE pin = '" + pinnumber + "'"
            );
            if (rs.next()) {
                String cardno = rs.getString("cardnumber");
                if (cardno.length() >= 16) {
                    card.setText(
                        "Card Number: " +
                        cardno.substring(0, 4) +
                        " XXXX XXXX " +
                        cardno.substring(12)
                    );
                } else {
                    card.setText("Card Number: " + cardno);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Conn conn = new Conn();
            int bal = 0;

            ResultSet rs = conn.stmt.executeQuery(
                "SELECT * FROM bank WHERE pin = '" + pinnumber + "'"
            );

            StringBuilder statement = new StringBuilder("<html>");

            while (rs.next()) {
                statement.append(rs.getString("date"))
                         .append("&nbsp;&nbsp;&nbsp;")
                         .append(rs.getString("type"))
                         .append("&nbsp;&nbsp;&nbsp;Rs ")
                         .append(rs.getString("amount"))
                         .append("<br><br>");

                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }

            statement.append("</html>");
            mini.setText(statement.toString());

            balance.setText("Your current account balance is Rs " + bal);

        } catch (Exception e) {
            System.out.println(e);
        }

        getContentPane().setBackground(Color.WHITE);
        setSize(400, 550);
        setLocation(20, 20);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}
