package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinnumber;

    public Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

    
        ImageIcon i1 = new ImageIcon(
            "C:\\JavaProject\\Banking Management System\\src\\BankingManagementSystem\\icons\\atm.jpg"
        );
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to Withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == withdraw) {

            String number = amount.getText().trim();

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to Withdraw");
                return;
            }

            try {
                Conn conn = new Conn();
                int amt = Integer.parseInt(number);

        
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

                if (balance < amt) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                Timestamp ts = new Timestamp(new Date().getTime());

                String query =
                    "INSERT INTO bank VALUES('" + pinnumber + "','" + ts + "','Withdrawal','" + amt + "')";
                conn.stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Rs " + amt + " Withdrawn Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}
