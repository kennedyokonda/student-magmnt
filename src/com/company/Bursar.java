package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bursar extends JFrame {
    private JLabel bursary;
    private JTextField textField1;
    private JButton submitButton;
    private JPanel label1;
    private JTable tableu;
    private JButton btnclear;

    public Bursar(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Bursar");
        setContentPane(label1);
        setMinimumSize(new Dimension(650,600));
        //setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String StudentNO=textField1.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String query="SELECT studata.StudentNo, studata.FirstName, studata.Surname, studata.CourseID, course.CoursePrice FROM course " +
                            "INNER JOIN studata ON course.CourseID=studata.CourseID WHERE StudentNo= '"+StudentNO+"'";
                    PreparedStatement ps= conn.prepareStatement(query);
                    ResultSet rs=ps.executeQuery(query);

                    // rsmd has info about rs e.g. how many columns are there
                    ResultSetMetaData rsmd=rs.getMetaData();
                    // allows adding columns and rows at coding time
                    DefaultTableModel modely=(DefaultTableModel) tableu.getModel();

                    //fetch column name and find out how many columns are there
                    int colu= rsmd.getColumnCount();
                    String [] colName=new String[colu];
                    for(int i=0;i<colu;i++){
                        colName[i]=rsmd.getColumnName(i+1);
                        modely.setColumnIdentifiers(colName);
                        String StudentNo, FirstName, Surname, CourseID, Price;

                        // reads all rows one by one
                        while(rs.next()){
                            StudentNo=rs.getString(1);
                            FirstName=rs.getString(2);
                            Surname=rs.getString(3);
                            CourseID=rs.getString(4);
                            Price=rs.getString(5);

                            //stores row in array and adds them to table
                            String [] row={StudentNo, FirstName, Surname, CourseID, Price};
                            modely.setRowCount(0);
                            modely.addRow(row);
                    }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Data not found");
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Could not connect to Database");
                }
            }
        });

        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableu.setModel(new DefaultTableModel());
            }
        });

        setVisible(true);
    }



    public static void main(String[] args) {
        Bursar bursar = new Bursar(null);
    }
}


