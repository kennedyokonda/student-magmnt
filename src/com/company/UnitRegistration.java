package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class UnitRegistration extends JFrame{
    private JTextField regtext;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JCheckBox sem1CheckBox;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JButton registerButton;
    private JButton deferSemesterButton;
    private JButton button1;
    private JPanel unitreg;
    private JRadioButton sem2RadioButton;
    private JRadioButton sem1RadioButton;

    public UnitRegistration(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Unit Registration");
        setContentPane(unitreg);
        setMinimumSize(new Dimension(650,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Stud=regtext.getText();
                if(regtext.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill the field");
                }
                else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                        String sql = "SELECT CourseID from approved WHERE StudentNo='" + Stud + "'";
                        PreparedStatement prep = conn.prepareStatement(sql);
                        ResultSet res= prep.executeQuery(sql);
                        if(res.next()){
                            textField2.setText(res.getString("CourseID"));
                        }else {
                            JOptionPane.showMessageDialog(null,"Data Not Found");
                            textField2.setText("");
                        }

                        String stude=regtext.getText();
                        String sql2 = "SELECT YOS FROM studata WHERE StudentNo='"+stude+"'";
                        PreparedStatement state = conn.prepareStatement(sql2);
                        ResultSet rs1 = state.executeQuery(sql2);
                        comboBox1.removeAllItems();
                        while (rs1.next()) {
                            String namey=rs1.getString("YOS");
                            comboBox1.addItem(namey);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            });

        sem1RadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String student = textField2.getText();
                    String sql2 = "SELECT UnitCode1,UnitCode2,UnitCode3,UnitCode4,UnitCode5 FROM units WHERE CourseID='"+student+"'";
                    PreparedStatement state = Conn.prepareStatement(sql2);
                    ResultSet rs1 = state.executeQuery(sql2);
                    while (rs1.next()) {
                        checkBox1.setText(rs1.getString("UnitCode1"));
                        checkBox2.setText(rs1.getString("UnitCode2"));
                        checkBox3.setText(rs1.getString("UnitCode3"));
                        checkBox4.setText(rs1.getString("UnitCode4"));
                        checkBox5.setText(rs1.getString("UnitCode5"));
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

       /* comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String stude =regtext.getText() ;
                    String sql2 = "SELECT YOS FROM studata WHERE StudentNo='"+stude+"'";
                    PreparedStatement state = Conn.prepareStatement(sql2);
                    ResultSet rs1 = state.executeQuery(sql2);
                    comboBox1.removeAllItems();
                    while (rs1.next()) {
                        String namey=rs1.getString("YOS");
                        comboBox1.addItem(namey);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        */
        sem2RadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String student = textField2.getText();
                    String sql2 = "SELECT UnitCode1,UnitCode2,UnitCode3,UnitCode4,UnitCode5 FROM units WHERE CourseID='"+student+"'";
                    PreparedStatement state = Conn.prepareStatement(sql2);
                    ResultSet rs1 = state.executeQuery(sql2);
                    while (rs1.next()) {
                        checkBox1.setText("");
                        checkBox2.setText("");
                        checkBox3.setText("");
                        checkBox4.setText("");
                        checkBox5.setText("");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String no=regtext.getText();
                    String cos=textField2.getText();
                    String comb=(String) comboBox1.getSelectedItem();
                    String sem=sem1RadioButton.getText();
                    String unit1=checkBox1.getText();
                    String unit2=checkBox2.getText();
                    String unit3=checkBox3.getText();
                    String unit4=checkBox4.getText();
                    String unit5=checkBox5.getText();

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String query = "INSERT INTO gradlist VALUES('"+no+"','"+cos+"','"+comb+"','"+sem+"','"+unit1+"','"+unit2+"','"+unit3+"','"+unit4+"','"+unit5+"')";
                    PreparedStatement pre=Conn.prepareStatement(query);
                    pre.execute(query);
                    JOptionPane.showMessageDialog(null,"Units Successfully registered");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"You have already registered");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            });

        setVisible(true);
        deferSemesterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String no=regtext.getText();
                    String cos=textField2.getText();

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String query = "INSERT INTO defer VALUES('"+no+"','"+cos+"')";
                    PreparedStatement pre=Conn.prepareStatement(query);
                    pre.execute(query);
                    JOptionPane.showMessageDialog(null,"You have deffered successfully");
                    regtext.getText();
                    textField2.getText();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"You have already deffered");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        UnitRegistration unitRegistration=new UnitRegistration(null);
    }
}
