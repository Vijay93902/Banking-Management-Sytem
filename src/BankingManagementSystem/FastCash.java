package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton b100, b500, b1000, b2000, b5000, b10000, back;
    String pinnumber;

    public FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

    
        ImageIcon i1 = new ImageIcon( "C:\\JavaProject\\Banking Management System\\src\\BankingManagementSystem\\icons\\atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        b100 = new JButton("Rs 100");
        b100.setBounds(170, 415, 150, 30);
        b100.addActionListener(this);
        image.add(b100);

        b500 = new JButton("Rs 500");
        b500.setBounds(355, 415, 150, 30);
        b500.addActionListener(this);
        image.add(b500);

        b1000 = new JButton("Rs 1000");
        b1000.setBounds(170, 450, 150, 30);
        b1000.addActionListener(this);
        image.add(b1000);

        b2000 = new JButton("Rs 2000");
        b2000.setBounds(355, 450, 150, 30);
        b2000.addActionListener(this);
        image.add(b2000);

        b5000 = new JButton("Rs 5000");
        b5000.setBounds(170, 485, 150, 30);
        b5000.addActionListener(this);
        image.add(b5000);

        b10000 = new JButton("Rs 10000");
        b10000.setBounds(355, 485, 150, 30);
        b10000.addActionListener(this);
        image.add(b10000);

        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
            return;
        }

        String amount = ((JButton) ae.getSource()).getText().substring(3);

        try {
            Conn conn = new Conn();

            ResultSet rs = conn.stmt.executeQuery(
                "SELECT * FROM bank WHERE pin = '" + pinnumber + "'"
            );

            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            if (balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            Timestamp ts = new Timestamp(new Date().getTime());

            String query =
                "INSERT INTO bank VALUES('" + pinnumber + "','" + ts + "','Withdrawal','" + amount + "')";
            conn.stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
