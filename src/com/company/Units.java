package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Units {

    private JFrame frame;
    private JTextField textFieldUnitCode1;
    private JTextField textFieldUnitCode2;
    private JTextField textFieldCode3;
    private JTextField textFieldUnitCode4;
    private JTextField textFieldUnitCode5;
    private JTextField textFieldUnit1;
    private JTextField textFieldUnit2;
    private JTextField textFieldUnit3;
    private JTextField textFieldUnit4;
    private JTextField textFieldUnit5;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Units window = new Units();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Units() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 419, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Unit Code");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 47, 91, 34);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Unit Name");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(192, 57, 138, 24);
        frame.getContentPane().add(lblNewLabel_1);

        textFieldUnitCode1 = new JTextField();
        textFieldUnitCode1.setBounds(10, 82, 132, 34);
        frame.getContentPane().add(textFieldUnitCode1);
        textFieldUnitCode1.setColumns(10);

        textFieldUnitCode2 = new JTextField();
        textFieldUnitCode2.setColumns(10);
        textFieldUnitCode2.setBounds(10, 142, 132, 34);
        frame.getContentPane().add(textFieldUnitCode2);

        textFieldCode3 = new JTextField();
        textFieldCode3.setColumns(10);
        textFieldCode3.setBounds(10, 208, 132, 34);
        frame.getContentPane().add(textFieldCode3);

        textFieldUnitCode4 = new JTextField();
        textFieldUnitCode4.setColumns(10);
        textFieldUnitCode4.setBounds(10, 275, 132, 34);
        frame.getContentPane().add(textFieldUnitCode4);

        textFieldUnitCode5 = new JTextField();
        textFieldUnitCode5.setColumns(10);
        textFieldUnitCode5.setBounds(10, 340, 132, 34);
        frame.getContentPane().add(textFieldUnitCode5);

        textFieldUnit1 = new JTextField();
        textFieldUnit1.setColumns(10);
        textFieldUnit1.setBounds(192, 82, 185, 34);
        frame.getContentPane().add(textFieldUnit1);

        textFieldUnit2 = new JTextField();
        textFieldUnit2.setColumns(10);
        textFieldUnit2.setBounds(192, 142, 185, 34);
        frame.getContentPane().add(textFieldUnit2);

        textFieldUnit3 = new JTextField();
        textFieldUnit3.setColumns(10);
        textFieldUnit3.setBounds(192, 215, 185, 34);
        frame.getContentPane().add(textFieldUnit3);

        textFieldUnit4 = new JTextField();
        textFieldUnit4.setColumns(10);
        textFieldUnit4.setBounds(192, 275, 185, 34);
        frame.getContentPane().add(textFieldUnit4);

        textFieldUnit5 = new JTextField();
        textFieldUnit5.setColumns(10);
        textFieldUnit5.setBounds(192, 340, 185, 34);
        frame.getContentPane().add(textFieldUnit5);

        JButton btnUnit = new JButton("ADD");
        btnUnit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String unit1 = textFieldUnit1.getText();
                String unitCode1 = textFieldUnitCode1.getText();

                String unit2 = textFieldUnit2.getText();
                String unitCode2 = textFieldUnitCode2.getText();

                String unit3 = textFieldUnit3.getText();
                String unitCode3 = textFieldCode3.getText();

                String unit4 = textFieldUnit4.getText();
                String unitCode4 = textFieldUnitCode4.getText();

                String unit5 = textFieldUnit5.getText();
                String unitCode5 = textFieldUnitCode5.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root","");

                    String sql1 = "INSERT INTO course VALUES('"+unitCode1+"','"+unit1+"')";
                    String sql2 = "INSERT INTO course VALUES('"+unitCode2+"','"+unit2+"')";
                    String sql3 = "INSERT INTO course VALUES('"+unitCode3+"','"+unit3+"')";
                    String sql4 = "INSERT INTO course VALUES('"+unitCode4+"','"+unit4+"')";
                    String sql5 = "INSERT INTO course VALUES('"+unitCode5+"','"+unit5+"')";

                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql1);
                    stmt.executeUpdate(sql2);
                    stmt.executeUpdate(sql3);
                    stmt.executeUpdate(sql4);
                    stmt.executeUpdate(sql5);
                }
                catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                setVisible(false);
            }
        });
        btnUnit.setBounds(71, 395, 109, 43);
        frame.getContentPane().add(btnUnit);

        JButton btnReset = new JButton("RESET");
        btnReset.setBounds(209, 404, 98, 34);
        frame.getContentPane().add(btnReset);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        frame.setVisible(b);
    }

}
