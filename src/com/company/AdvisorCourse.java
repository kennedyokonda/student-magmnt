package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class AdvisorCourse extends JFrame{
    private JPanel panelAdvisor;
    private JButton searchCoursesButton;
    private JComboBox courseBox1;
    private JComboBox admBox2;
    private JTable tbladvise;
    private JButton approveButton;
    private JButton rejectButton;
    private JComboBox schoolcombo;

    public AdvisorCourse(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Welcome to CourseAdvisor");
        setContentPane(panelAdvisor);
        setMinimumSize(new Dimension(650,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        searchCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root","");

                    String fring=("SELECT SchoolCode FROM school");
                    PreparedStatement ps=Conn.prepareStatement(fring);
                    ResultSet result=ps.executeQuery(fring);
                    schoolcombo.removeAllItems();
                    while(result.next()){
                        String name=result.getString("SchoolCode");
                        schoolcombo.addItem(name);
                    }

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        schoolcombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String schoolName = (String) schoolcombo.getSelectedItem();
                    String sql11 = "SELECT CourseID FROM course WHERE SchoolCode='"+schoolName+"'";
                    PreparedStatement stmt = Conn.prepareStatement(sql11);
                    ResultSet res = stmt.executeQuery(sql11);
                    admBox2.removeAllItems();

                    while (res.next()) {
                        String namel=res.getString("CourseID");
                        admBox2.addItem(namel);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        admBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String fali= (String) admBox2.getSelectedItem();
                    String ringer = "SELECT StudentNo FROM studata WHERE CourseID='"+fali+"'";
                    PreparedStatement preps = Conn.prepareStatement(ringer);
                    ResultSet dom = preps.executeQuery(ringer);
                    courseBox1.removeAllItems();
                    while (dom.next()) {
                        courseBox1.addItem(dom.getString("StudentNo"));
                    }


                    String fal= (String) admBox2.getSelectedItem();
                    String ring = "SELECT course.CourseDescription, studata.KCSEgrade FROM course INNER JOIN studata ON course.CourseID = studata.CourseID WHERE studata.CourseID='"+fal+"'";

                    PreparedStatement pepper = Conn.prepareStatement(ring);
                    ResultSet cars = pepper.executeQuery(ring);
                    ResultSetMetaData meta=cars.getMetaData();

                    DefaultTableModel model=(DefaultTableModel) tbladvise.getModel();

                    int col=meta.getColumnCount();
                    String Columns[]=new String[col];
                    for(int i=0;i<col;i++){
                        Columns[i]=meta.getColumnName(i+1);
                        model.setColumnIdentifiers(Columns);
                        String CourseDescription, KCSEgrade;

                        while (cars.next()){
                            CourseDescription=cars.getString(1);
                            KCSEgrade=cars.getString(2);
                            String [] row={CourseDescription,KCSEgrade};
                            model.setRowCount(0);
                            model.addRow(row);
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model=(DefaultTableModel) tbladvise.getModel();
                model.setRowCount(0);
            }
        });

        setVisible(true);

        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String reg=(String) admBox2.getSelectedItem();
                    String course=(String) courseBox1.getSelectedItem();

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String stu="INSERT INTO approved VALUES('"+reg+"','"+course+"')";
                    PreparedStatement perp= Conn.prepareStatement(stu);
                    perp.execute(stu);

                    JOptionPane.showMessageDialog(null,"Student approval is successful");

                    DefaultTableModel model=(DefaultTableModel) tbladvise.getModel();
                    model.setRowCount(0);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "You have already approved this student");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public static void main(String[] args) {
AdvisorCourse advisorCourse=new AdvisorCourse(null);
    }
}
