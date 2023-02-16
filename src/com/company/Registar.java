package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Registar extends JFrame{
    private JButton getSchoolsButton;
    private JPanel regpanel;
    private JComboBox SchoolCombo1;
    private JButton deleteCourseButton;
    private JComboBox comboCourse2;
    private JButton addUnitsButton;
    private JButton addCourseButton1;
    private JTextField coursecode;
    private JTextField coursename;
    private JTextField price;
    private JComboBox schoolcomboBox1;
    private JTextField textFieldUnit1;
    private JTextField textFieldUnitCode1;
    private JTextField textFieldUnit2;
    private JTextField textFieldUnit3;
    private JTextField textFieldUnit4;
    private JTextField textFieldUnit5;
    private JTextField textFieldUnitCode2;
    private JTextField textFieldUnitCode3;
    private JTextField textFieldUnitCode4;
    private JTextField textFieldUnitCode5;
    private JButton resetButton;
    private JRadioButton sem1RadioButton;
    private JRadioButton sem2RadioButton;
    private JTextField yeartx;

    public Registar(JFrame parent ){
        super(String.valueOf(parent));
        setTitle("Welcome to Registrar");
        setContentPane(regpanel);
        setMinimumSize(new Dimension(650,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        getSchoolsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root","");

                    String fring=("SELECT SchoolCode FROM school");
                    PreparedStatement ps=Conn.prepareStatement(fring);
                    ResultSet result=ps.executeQuery(fring);
                    SchoolCombo1.removeAllItems();
                    while(result.next()){
                        String name=result.getString("SchoolCode");
                        SchoolCombo1.addItem(name);
                    }

                    String gus=("SELECT SchoolCode FROM school");
                    PreparedStatement ps1=Conn.prepareStatement(gus);
                    ResultSet result1=ps1.executeQuery(gus);
                    schoolcomboBox1.removeAllItems();
                    while(result1.next()){
                        String name1=result1.getString("SchoolCode");
                        schoolcomboBox1.addItem(name1);
                    }

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addCourseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course= (String) schoolcomboBox1.getSelectedItem();
                String value=coursecode.getText();
                String value1=coursename.getText();
                String value3= String.valueOf(Integer.parseInt(price.getText()));

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");
                    String simo="INSERT INTO course VALUES('"+value+"','"+value1+"','"+course+"','"+value3+"')";
                    PreparedStatement prepsimo=conn.prepareStatement(simo);
                    prepsimo.execute(simo);

                    JOptionPane.showMessageDialog(null,"Data successfully inserted");
                    coursecode.setText("");
                    coursename.setText("");
                    price.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });


        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value=coursecode.getText();

                if(value.equals("")){
                    JOptionPane.showMessageDialog(null, "Enter courseID");
                }else {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");
                        String simo="DELETE FROM course WHERE CourseID='"+value+"'";
                        PreparedStatement prepsimo=conn.prepareStatement(simo);
                        prepsimo.execute(simo);

                        JOptionPane.showMessageDialog(null,"Data successfully deleted");
                        coursecode.setText("");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                }


        });

        addUnitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course=(String) comboCourse2.getSelectedItem();

                String unit1 = textFieldUnit1.getText();
                String unitCode1 = textFieldUnitCode1.getText();

                String unit2 = textFieldUnit2.getText();
                String unitCode2 = textFieldUnitCode2.getText();

                String unit3 = textFieldUnit3.getText();
                String unitCode3 = textFieldUnitCode3.getText();

                String unit4 = textFieldUnit4.getText();
                String unitCode4 = textFieldUnitCode4.getText();

                String unit5 = textFieldUnit5.getText();
                String unitCode5 = textFieldUnitCode5.getText();

                String year=yeartx.getText();

                String sem=sem1RadioButton.getText();
               // String sem1=sem2RadioButton;



                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root","");

                    String sql1 = "INSERT INTO units VALUES('"+course+"','"+unit1+"','"+unitCode1+"','"+unit2+"','"+unitCode2+"','"+unit3+"','"+unitCode3+"'," +
                            "'"+unit4+"','"+unitCode4+"','"+unit5+"','"+unitCode5+"','"+sem+"','"+year+"')";
                    PreparedStatement prepa=con.prepareStatement(sql1);
                    prepa.execute(sql1);

                    JOptionPane.showMessageDialog(null,"Units Successfully added");

                }
                catch (ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(null,"Units for this course are already added");
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldUnitCode1.setText("");
                textFieldUnitCode2.setText("");
                textFieldUnitCode3.setText("");
                textFieldUnitCode4.setText("");
                textFieldUnitCode5.setText("");
                textFieldUnit1.setText("");
                textFieldUnit2.setText("");
                textFieldUnit3.setText("");
                textFieldUnit4.setText("");
                textFieldUnit5.setText("");

            }
        });

        SchoolCombo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root","");

                    String schoolName = (String) SchoolCombo1.getSelectedItem();
                    String data = "SELECT CourseID FROM course WHERE SchoolCode='"+schoolName+"'";
                    PreparedStatement ps=Conn.prepareStatement(data);
                    ResultSet result=ps.executeQuery(data);
                    comboCourse2.removeAllItems();

                    while(result.next()){
                        String name=result.getString("CourseID");
                        comboCourse2.addItem(name);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
           Registar registar=new Registar(null);
    }
}


