package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admision  extends JFrame{
    private JPanel label;
    private JLabel Stureg;
    private JLabel StuNo;
    private JLabel Fname;
    private JLabel Sname;
    private JLabel phone;
    private JLabel gen;
    private JLabel stuEm;
    private JLabel DOB;
    private JLabel course;
    private JLabel Kcse;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton btnDelete;
    private JButton btnAdd;
    private JTextField textField8;
    private JTextField textField9;
    private JLabel yearOfStudyLabel;
    private JTextField textField10;


    public Admision(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Admission");
        setContentPane(label);
        setMinimumSize(new Dimension(500,524));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String StudentNo=textField1.getText();
                String  Firstname=textField2.getText();
                String  Surname=textField3.getText();
                String phoneNo = textField4.getText();
                int phone=phoneNo.length();
                String Gender=textField5.getText();
                String Email=textField6.getText();
                String DateOfBirth=textField7.getText();
                String  CourseId=textField8.getText();
                String KCSEgrade=textField9.getText();
                String year=textField10.getText();

                if(phone!= 10){
                    JOptionPane.showMessageDialog(btnAdd,"Enter correct Number");
                }
                else{
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                        String query= "INSERT INTO studata values('"+StudentNo+"', '"+Firstname+"', '"+Surname+"', '"+phoneNo+"', '"+Gender+"', " +
                                "'"+Email+"', '"+DateOfBirth+"', '"+CourseId+"', '"+KCSEgrade+"','"+year+"')";

                        PreparedStatement preparedStatement=conn.prepareStatement(query);
                        preparedStatement.execute(query);

                        JOptionPane.showMessageDialog(null,"Data successfully inserted");
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                        textField5.setText("");
                        textField6.setText("");
                        textField7.setText("");
                        textField8.setText("");
                        textField9.setText("");
                        textField10.setText("");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        //JOptionPane.showMessageDialog(null,"Data not inserted");
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null,"Database not found");
                    }
                }

                }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");
                textField9.setText("");
                textField10.setText("");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        Admision admision = new Admision(null);
    }


}

