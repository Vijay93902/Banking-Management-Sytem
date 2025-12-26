package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;

        setLayout(null);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Recurring Deposit Account");

        JRadioButton[] radios = {r1, r2, r3, r4};
        int y = 180;
        for (JRadioButton r : radios) {
            r.setFont(new Font("Raleway", Font.BOLD, 16));
            r.setBackground(Color.WHITE);
            r.setBounds(100, y, 300, 25);
            add(r);
            y += 40;
        }

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 18));
        services.setBounds(100, 360, 200, 30);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("Email & SMS Alerts");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");
        c7 = new JCheckBox("I hereby declare that the above details are correct");

        JCheckBox[] checks = {c1, c2, c3, c4, c5, c6};
        y = 400;
        for (JCheckBox c : checks) {
            c.setFont(new Font("Raleway", Font.BOLD, 14));
            c.setBackground(Color.WHITE);
            c.setBounds(100, y, 300, 25);
            add(c);
            y += 30;
        }

        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBackground(Color.WHITE);
        c7.setBounds(100, y + 20, 600, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBounds(250, y + 70, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, y + 70, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 750);
        setLocation(350, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submit) {

            // ✅ Account type
            String accountType = null;
            if (r1.isSelected()) accountType = "Saving Account";
            else if (r2.isSelected()) accountType = "Fixed Deposit Account";
            else if (r3.isSelected()) accountType = "Current Account";
            else if (r4.isSelected()) accountType = "Recurring Deposit Account";

            if (accountType == null) {
                JOptionPane.showMessageDialog(this, "Please select Account Type");
                return;
            }

            if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please accept the declaration");
                return;
            }

            // ✅ Generate card & pin
            Random rand = new Random();
            long cardnumber = 5040936000000000L + Math.abs(rand.nextLong() % 1000000000000000L);
            String pinnumber = String.valueOf(1000 + rand.nextInt(9000));

            // ✅ Services (MULTIPLE allowed)
            String facility = "";
            if (c1.isSelected()) facility += " ATM Card";
            if (c2.isSelected()) facility += " Internet Banking";
            if (c3.isSelected()) facility += " Mobile Banking";
            if (c4.isSelected()) facility += " Email & SMS Alerts";
            if (c5.isSelected()) facility += " Cheque Book";
            if (c6.isSelected()) facility += " E-Statement";

            try {
                Conn conn = new Conn();

                String q1 = "INSERT INTO signupthree VALUES('" + formno + "','" +
                        accountType + "','" + cardnumber + "','" + pinnumber + "','" + facility + "')";

                String q2 = "INSERT INTO login VALUES('" + formno + "','" +
                        cardnumber + "','" + pinnumber + "')";

                conn.stmt.executeUpdate(q1);
                conn.stmt.executeUpdate(q2);

                JOptionPane.showMessageDialog(this,
                        "Account Created Successfully\n\nCard Number: " +
                        cardnumber + "\nPIN: " + pinnumber);

                setVisible(false);
                new Login().setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SignupThree("");
    }
}
