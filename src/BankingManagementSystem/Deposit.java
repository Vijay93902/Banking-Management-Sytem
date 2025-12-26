package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;
    String pinnumber;

    public Deposit(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        
        ImageIcon i1 = new ImageIcon("C:\\JavaProject\\Banking Management System\\src\\BankingManagementSystem\\icons\\atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == deposit) {

            String number = amount.getText().trim();

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount");
                return;
            }

            try {
                Conn conn = new Conn();

                
                Timestamp ts = new Timestamp(System.currentTimeMillis());

                String query =
                    "INSERT INTO bank VALUES('" + pinnumber + "','" + ts + "','Deposit','" + number + "')";

                conn.stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null,
                        "Rs " + number + " Deposited Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
