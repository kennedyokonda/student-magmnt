package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Enrollment extends JFrame {
    public JPanel label3;
    public JTextField tf1;
    public JButton viewbtn;
    public JTextField stutext;
    public JButton btnRetrieve;
    public JTextField textFieldU;
    public JTextField textFieldU1;
    public JTextField textFieldU2;
    public JTextField textFieldU3;
    public JTextField textFieldU4;
    public JButton btnReset;
    public JButton btnApply;
    public JTextField tfTranscript;
    public JButton btnTranscript;
    public JTable Transcriptble;
    public JTextField courseTF;
    public JButton btnClassL;
    public JTable ViewTable;
    public JTable ClassTable;
    public JTextField tfgrade;
    public JButton btnGrade;
    public JLabel tfGdUnit5;
    public JLabel tfGdUnit4;
    public JLabel tfGdUnit3;
    public JLabel tfGdUnit2;
    public JLabel tfGdUnit1;
    public JTextField tfGdUnit6;
    public JTextField tfGdUnit7;
    public JTextField tfGdUnit8;
    public JTextField tfGdUnit9;
    public JTextField tfGdUnit10;
    public JButton btnresultsave;
    public JButton btnclrview;
    public JButton btnclrTranscript;
    public JTextField textField1;
    public JButton printButton;
    public JTable dropoutbl;
    private JPanel Graduation;
    private JTextField coursetext;
    private JButton searchButton;
    private JTable gradtbl;
    private JButton btnclear;
    private JButton clearButton;


    public Enrollment(JFrame parent) {
            super(String.valueOf(parent));
            setTitle("Enrollment");
            setContentPane(label3);
            setMinimumSize(new Dimension(650,600));
            //setModal(true);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        viewbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regno=tf1.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String sql="SELECT StudentNo, FirstName, Surname, CourseID, Email FROM studata WHERE StudentNo='"+regno+"'";
                    PreparedStatement prepare=conn.prepareStatement(sql);
                    prepare.execute(sql);
                    ResultSet result=prepare.executeQuery(sql);

                    ResultSetMetaData tdata=result.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) ViewTable.getModel();

                    int col=tdata.getColumnCount();
                    String columns[]= new String [col];
                    for (int i=0;i<col;i++){
                        columns[i]=tdata.getColumnName(i+1);
                        model.setColumnIdentifiers(columns);
                        String StudentNo, FirstName, Surname, CourseID, Email;

                        while (result.next()){
                            StudentNo=result.getString(1);
                            FirstName=result.getString(2);
                            Surname=result.getString(3);
                            CourseID=result.getString(4);
                            Email=result.getString(5);

                            String [] row={StudentNo, FirstName, Surname, CourseID, Email};
                            model.addRow(row);
                        }

                    }


                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });

        btnclrview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTable.setModel(new DefaultTableModel());
            }
        });


        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regno=stutext.getText();
                if(stutext.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill the field");
                }
                else{
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                        String sql="SELECT StudentNo, Email, PhoneNo, FirstName, Surname FROM studata WHERE StudentNo='"+regno+"' ";
                        PreparedStatement prepare=conn.prepareStatement(sql);
                        ResultSet rs=prepare.executeQuery(sql);

                        if(rs.next()){
                            textFieldU.setText(rs.getString("StudentNo"));
                            textFieldU1.setText(rs.getString("Email"));
                            textFieldU2.setText(rs.getString("PhoneNo"));
                            textFieldU3.setText(rs.getString("FirstName"));
                            textFieldU4.setText(rs.getString("Surname"));
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Data Not Found");
                            textFieldU.setText("");
                        }


                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

                }
        });

        btnApply.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regno=textFieldU.getText();
                String mail=textFieldU1.getText();
                String phone=textFieldU2.getText();
                String Fname=textFieldU3.getText();
                String Sname=textFieldU4.getText();

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");
                    String query="UPDATE studata SET StudentNo='"+regno+"', Email='"+mail+"', PhoneNo='"+phone+"', FirstName='"+Fname+"'," +
                            "Surname='"+Sname+"' WHERE StudentNo='"+regno+"'";
                    PreparedStatement prepared= conn.prepareStatement(query);
                    prepared.execute(query);


                    JOptionPane.showMessageDialog(null,"Details Successfully updated");
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Could not update details");
                }catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldU.setText("");
                textFieldU1.setText("");
                textFieldU2.setText("");
                textFieldU3.setText("");
                textFieldU4.setText("");
            }
        });


        btnGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regno=tfgrade.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String query1="SELECT UnitCode1,UnitCode2,UnitCode3,UnitCode4,UnitCode5  FROM units WHERE CourseID IN(SELECT CourseID FROM studata WHERE StudentNo='"+regno+"')";
                    PreparedStatement ps=conn.prepareStatement(query1);
                    ResultSet rs=ps.executeQuery(query1);
                    if(rs.next()){

                        tfGdUnit1.setText(rs.getString("UnitCode1"));
                        tfGdUnit2.setText(rs.getString("UnitCode2"));
                        tfGdUnit3.setText(rs.getString("UnitCode3"));
                        tfGdUnit4.setText(rs.getString("UnitCode4"));
                        tfGdUnit5.setText(rs.getString("UnitCode5"));
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Could not find data related to the student");
                        tfGdUnit1.setText("");
                        tfGdUnit2.setText("");
                        tfGdUnit3.setText("");
                        tfGdUnit4.setText("");
                        tfGdUnit5.setText("");
                    }



                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }            }
        });
        btnresultsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regno=tfgrade.getText();
                int text1= Integer.parseInt(tfGdUnit6.getText());
                int text2= Integer.parseInt(tfGdUnit7.getText());
                int text3= Integer.parseInt(tfGdUnit8.getText());
                int text4= Integer.parseInt(tfGdUnit9.getText());
                int text5= Integer.parseInt(tfGdUnit10.getText());

                String unit1=tfGdUnit1.getText();
                String unit2=tfGdUnit2.getText();
                String unit3=tfGdUnit3.getText();
                String unit4=tfGdUnit4.getText();
                String unit5=tfGdUnit5.getText();

                int result, sum;
                result=text1+text2+text3+text4+text5;
                sum=result/5;

                if(tfGdUnit6.getText().equals("") && tfGdUnit7.getText().equals("") && tfGdUnit8.getText().equals("") && tfGdUnit9.getText().equals("") && tfGdUnit10.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"input some marks");
                }
                else if (text1 > 100 && text2 > 100 && text3 > 100 && text4 > 100 && text5 > 100){
                   JOptionPane.showMessageDialog(null,"Invalid marks");
               }
                else{
                    if(sum>=70){
                        JOptionPane.showMessageDialog(null, "Congrats, you have an A");
                    }else if(sum>=60){
                        JOptionPane.showMessageDialog(null, "Congrats, you have an B");
                    }else if(sum>=50){
                        JOptionPane.showMessageDialog(null, "Congrats, you have an C");
                    }else if(sum>=40)
                        JOptionPane.showMessageDialog(null, "Congrats, you have an D");
                    else if(sum<40){
                        JOptionPane.showMessageDialog(null, "Congrats, you have an F");
                    }
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                            String sql="INSERT INTO marks VALUES('"+regno+"','"+unit1+"','"+text1+"','"+unit2+"','"+text2+"','"+unit3+"','"+text3+"','"+unit4+"','"+text4+"','"+unit5+"','"+text5+"')";
                            PreparedStatement ps=conn.prepareStatement(sql);
                            ps.execute(sql);

                            JOptionPane.showMessageDialog(null, "Data successfully saved");
                            tfGdUnit6.setText("");
                            tfGdUnit7.setText("");
                            tfGdUnit8.setText("");
                            tfGdUnit9.setText("");
                            tfGdUnit10.setText("");

                        } catch (ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Database not found");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Data could not be saved");
                        }
                }
            }
        });




        setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=coursetext.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String query="SELECT StudentNo FROM approved WHERE CourseID='"+name+"'";
                    PreparedStatement pre=conn.prepareStatement(query);
                    ResultSet sety= pre.executeQuery(query);

                    ResultSetMetaData tdata=sety.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) gradtbl.getModel();

                    int col=tdata.getColumnCount();
                    String columns[]= new String [col];
                    for (int i=0;i<col;i++){
                        columns[i]=tdata.getColumnName(i+1);
                        model.setColumnIdentifiers(columns);
                        String StudentNo;

                        while (sety.next()){
                            StudentNo=sety.getString(1);
                            String [] row={StudentNo,};
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
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=textField1.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String query="SELECT * FROM defer WHERE CourseCode='"+name+"'";
                    PreparedStatement pre=conn.prepareStatement(query);
                    ResultSet sety= pre.executeQuery(query);

                    ResultSetMetaData tdata=sety.getMetaData();
                    DefaultTableModel model=(DefaultTableModel)dropoutbl.getModel();

                    int col=tdata.getColumnCount();
                    String columns[]= new String [col];
                    for (int i=0;i<col;i++){
                        columns[i]=tdata.getColumnName(i+1);
                        model.setColumnIdentifiers(columns);
                        String StudentNo, CourseID;

                        while (sety.next()){
                            StudentNo=sety.getString(1);
                            CourseID=sety.getString(2);
                            String [] row={StudentNo,CourseID};
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
        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {{
                    dropoutbl.setModel(new DefaultTableModel());
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   gradtbl.setModel(new DefaultTableModel());
            }
        });
    }


    public static void main(String[] args) {
        Enrollment enrol=new Enrollment(null);
    }
}
