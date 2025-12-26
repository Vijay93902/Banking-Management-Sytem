package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField pan, aadhar;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox<String> religion, category, occupation, education, income;
    String formno;

    public SignupTwo(String formno) {
        this.formno = formno;

        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

    
        JLabel lblReligion = new JLabel("Religion:");
        lblReligion.setFont(new Font("Raleway", Font.BOLD, 20));
        lblReligion.setBounds(100, 140, 200, 30);
        add(lblReligion);

        String[] valReligion = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox<>(valReligion);
        religion.setBounds(300, 140, 400, 30);
        add(religion);

    
        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(new Font("Raleway", Font.BOLD, 20));
        lblCategory.setBounds(100, 190, 200, 30);
        add(lblCategory);

        String[] valCategory = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox<>(valCategory);
        category.setBounds(300, 190, 400, 30);
        add(category);

        
        JLabel lblIncome = new JLabel("Income:");
        lblIncome.setFont(new Font("Raleway", Font.BOLD, 20));
        lblIncome.setBounds(100, 240, 200, 30);
        add(lblIncome);

        String[] valIncome = {"NULL", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Above 10,00,000"};
        income = new JComboBox<>(valIncome);
        income.setBounds(300, 240, 400, 30);
        add(income);

    
        JLabel lblEducation = new JLabel("Education:");
        lblEducation.setFont(new Font("Raleway", Font.BOLD, 20));
        lblEducation.setBounds(100, 290, 200, 30);
        add(lblEducation);

        String[] valEducation = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Other"};
        education = new JComboBox<>(valEducation);
        education.setBounds(300, 290, 400, 30);
        add(education);

    
        JLabel lblOccupation = new JLabel("Occupation:");
        lblOccupation.setFont(new Font("Raleway", Font.BOLD, 20));
        lblOccupation.setBounds(100, 340, 200, 30);
        add(lblOccupation);

        String[] valOccupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        occupation = new JComboBox<>(valOccupation);
        occupation.setBounds(300, 340, 400, 30);
        add(occupation);

        
        JLabel lblPan = new JLabel("PAN Number:");
        lblPan.setFont(new Font("Raleway", Font.BOLD, 20));
        lblPan.setBounds(100, 390, 200, 30);
        add(lblPan);

        pan = new JTextField();
        pan.setBounds(300, 390, 400, 30);
        add(pan);

        
        JLabel lblAadhar = new JLabel("Aadhar Number:");
        lblAadhar.setFont(new Font("Raleway", Font.BOLD, 20));
        lblAadhar.setBounds(100, 440, 200, 30);
        add(lblAadhar);

        aadhar = new JTextField();
        aadhar.setBounds(300, 440, 400, 30);
        add(aadhar);

        
        JLabel lblSenior = new JLabel("Senior Citizen:");
        lblSenior.setFont(new Font("Raleway", Font.BOLD, 20));
        lblSenior.setBounds(100, 490, 200, 30);
        add(lblSenior);

        syes = new JRadioButton("Yes");
        sno = new JRadioButton("No");
        syes.setBounds(300, 490, 100, 30);
        sno.setBounds(450, 490, 100, 30);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);

        add(syes);
        add(sno);

    
        JLabel lblExisting = new JLabel("Existing Account:");
        lblExisting.setFont(new Font("Raleway", Font.BOLD, 20));
        lblExisting.setBounds(100, 540, 200, 30);
        add(lblExisting);

        eyes = new JRadioButton("Yes");
        eno = new JRadioButton("No");
        eyes.setBounds(300, 540, 100, 30);
        eno.setBounds(450, 540, 100, 30);

        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(eyes);
        existingGroup.add(eno);

        add(eyes);
        add(eno);

        
        next = new JButton("Next");
        next.setBounds(620, 620, 80, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 750);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();

        String senior = syes.isSelected() ? "Yes" : "No";
        String existing = eyes.isSelected() ? "Yes" : "No";

        String span = pan.getText().trim();
        String saadhar = aadhar.getText().trim();

        if (span.equals("") || saadhar.equals("")) {
            JOptionPane.showMessageDialog(this, "PAN and Aadhar are required");
            return;
        }

        try {
            Conn c = new Conn();
            String query =
                "INSERT INTO signuptwo VALUES('" + formno + "','" + sreligion + "','" +
                scategory + "','" + sincome + "','" + seducation + "','" + soccupation +
                "','" + span + "','" + saadhar + "','" + senior + "','" + existing + "')";

            c.stmt.executeUpdate(query);

            setVisible(false);
            new SignupThree(formno).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }
}
