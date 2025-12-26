package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameTextField, fnameTextField, emailTextField,
               addressTextField, cityTextField, stateTextField, pinTextField;
    JButton next;
    JRadioButton male, female, other, married, unmarried;
    JDateChooser dateChooser;

    public SignupOne() {

        setLayout(null);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 32));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personDetails = new JLabel("Page 1: Personal Details");
        personDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personDetails.setBounds(290, 80, 400, 30);
        add(personDetails);

        JLabel name = new JLabel("Name:");
        name.setBounds(100, 140, 200, 30);
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        add(name);

        nameTextField = new JTextField();
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        JLabel fname = new JLabel("Father's Name:");
        fname.setBounds(100, 190, 200, 30);
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setBounds(100, 240, 200, 30);
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 400, 30);
        add(dateChooser);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(100, 290, 200, 30);
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        add(gender);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBounds(300, 290, 80, 30);
        female.setBounds(400, 290, 100, 30);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        add(male);
        add(female);

        JLabel email = new JLabel("Email:");
        email.setBounds(100, 340, 200, 30);
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        add(email);

        emailTextField = new JTextField();
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        JLabel marital = new JLabel("Marital Status:");
        marital.setBounds(100, 390, 200, 30);
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        add(marital);

        married = new JRadioButton("Married");
        unmarried = new JRadioButton("Unmarried");
        other = new JRadioButton("Other");

        married.setBounds(300, 390, 100, 30);
        unmarried.setBounds(420, 390, 120, 30);
        other.setBounds(560, 390, 80, 30);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);

        add(married);
        add(unmarried);
        add(other);

        JLabel address = new JLabel("Address:");
        address.setBounds(100, 440, 200, 30);
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        add(address);

        addressTextField = new JTextField();
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);

        JLabel city = new JLabel("City:");
        city.setBounds(100, 490, 200, 30);
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        add(city);

        cityTextField = new JTextField();
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);

        JLabel state = new JLabel("State:");
        state.setBounds(100, 540, 200, 30);
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        add(state);

        stateTextField = new JTextField();
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);

        JLabel pincode = new JLabel("Pin Code:");
        pincode.setBounds(100, 590, 200, 30);
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        add(pincode);

        pinTextField = new JTextField();
        pinTextField.setBounds(300, 590, 400, 30);
        add(pinTextField);

        next = new JButton("Next");
        next.setBounds(620, 660, 80, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();

        String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : null;

        String marital =
                married.isSelected() ? "Married" :
                unmarried.isSelected() ? "Unmarried" :
                other.isSelected() ? "Other" : null;

        String email = emailTextField.getText();
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pin = pinTextField.getText();

        if (name.equals("") || dob.equals("") || gender == null || marital == null) {
            JOptionPane.showMessageDialog(null, "Please fill all required fields");
            return;
        }

        try {
            Conn c = new Conn();
            String query =
                "INSERT INTO signup VALUES('" + formno + "','" + name + "','" + fname +
                "','" + dob + "','" + gender + "','" + email + "','" + marital +
                "','" + address + "','" + city + "','" + pin + "','" + state + "')";

            c.stmt.executeUpdate(query);

            setVisible(false);
            new SignupTwo(formno).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}
