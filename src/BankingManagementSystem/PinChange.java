package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    public PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

    
        ImageIcon i1 = new ImageIcon("C:\\JavaProject\\Banking Management System\\src\\BankingManagementSystem\\icons\\atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 18));
        text.setBounds(260, 260, 500, 35);
        image.add(text);

        JLabel pintext = new JLabel("New PIN:");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 320, 180, 25);
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 20));
        pin.setBounds(330, 320, 150, 25);
        image.add(pin);

        JLabel repintext = new JLabel("Re-enter New PIN:");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 360, 180, 25);
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 20));
        repin.setBounds(330, 360, 150, 25);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(355, 410, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(355, 450, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == change) {

            try {
                String npin = new String(pin.getPassword());
                String rpin = new String(repin.getPassword());

            
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }
                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PINs do not match");
                    return;
                }

                Conn conn = new Conn();

                // UPDATE PIN EVERYWHERE
                conn.stmt.executeUpdate(
                    "UPDATE bank SET pin = '" + npin + "' WHERE pin = '" + pinnumber + "'"
                );
                conn.stmt.executeUpdate(
                    "UPDATE login SET pin = '" + npin + "' WHERE pin = '" + pinnumber + "'"
                );
                conn.stmt.executeUpdate(
                    "UPDATE signupthree SET pin = '" + npin + "' WHERE pin = '" + pinnumber + "'"
                );

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transactions(npin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("");
    }
}
