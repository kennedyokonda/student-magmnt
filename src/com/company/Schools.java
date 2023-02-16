package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class Schools extends JFrame {
    private JPanel panel1;
    private JComboBox SchoolCombo;
    private JComboBox studentCombo;
    private JComboBox CourseCombo;
    private JTable SchoolTable;
    private JButton btnClasslist;
    private JButton btnTranscript;
    private JButton btnstudinfo;
    private JButton getSchoolButton;
    private JButton btnReset;

    public Schools(JFrame parent){
    super(String.valueOf(parent));
    setTitle("Schools");
    setContentPane(panel1);
    setMinimumSize(new Dimension(650,600));
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        getSchoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root","");


                    String fring=("SELECT SchoolCode FROM school");
                    PreparedStatement ps=Conn.prepareStatement(fring);
                    ResultSet result=ps.executeQuery(fring);
                    SchoolCombo.removeAllItems();
                    while(result.next()){
                        String name=result.getString("SchoolCode");
                        SchoolCombo.addItem(name);
                    }


                    String student = (String) CourseCombo.getSelectedItem();
                    String sql2 = "SELECT StudentNo FROM studata WHERE CourseID='"+student+"'";
                    PreparedStatement state = Conn.prepareStatement(sql2);
                    ResultSet rs1 = state.executeQuery(sql2);
                    studentCombo.removeAllItems();
                    while (rs1.next()) {
                        String namey=rs1.getString("StudentNo");
                        studentCombo.addItem(namey);
                    }

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchoolCombo.getSelectedItem();
                SchoolTable.setModel(new DefaultTableModel());
            }
        });


        btnstudinfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regno=(String) studentCombo.getSelectedItem();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String sql="SELECT StudentNo, FirstName, Surname, CourseID, Email FROM studata WHERE StudentNo='"+regno+"'";
                    PreparedStatement prepare=conn.prepareStatement(sql);
                    prepare.execute(sql);
                    ResultSet result=prepare.executeQuery(sql);

                    ResultSetMetaData tdata=result.getMetaData();
                    DefaultTableModel model=(DefaultTableModel) SchoolTable.getModel();

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
                            model.setRowCount(0);
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


        btnClasslist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course=(String) CourseCombo.getSelectedItem();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String query="SELECT StudentNo, FirstName, Surname FROM studata WHERE CourseID='"+course+"'";
                    PreparedStatement ps=conn.prepareStatement(query);
                    ResultSet result= ps.executeQuery(query);

                    ResultSetMetaData data=result.getMetaData();
                    DefaultTableModel modela=(DefaultTableModel) SchoolTable.getModel();
                    int col=data.getColumnCount();
                    String columns[]= new String [col];
                    for (int i=0;i<col;i++){
                        columns[i]=data.getColumnName(i+1);
                        modela.setColumnIdentifiers(columns);
                        String StudentNo, FirstName, Surname, CourseID, Email;
                        while (result.next()){
                            StudentNo=result.getString(1);
                            FirstName=result.getString(2);
                            Surname=result.getString(3);

                            String [] row={StudentNo, FirstName, Surname};
                            modela.setRowCount(0);
                            modela.addRow(row);

                        }
                    }



                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        SchoolCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String schoolName = (String) SchoolCombo.getSelectedItem();
                    String sql11 = "SELECT CourseID FROM course WHERE SchoolCode='"+schoolName+"'";
                    PreparedStatement stmt = Conn.prepareStatement(sql11);
                    ResultSet res = stmt.executeQuery(sql11);
                    CourseCombo.removeAllItems();

                    while (res.next()) {
                        String namel=res.getString("CourseID");
                        CourseCombo.addItem(namel);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        CourseCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "");

                    String student = (String) CourseCombo.getSelectedItem();
                    String sql2 = "SELECT StudentNo FROM studata WHERE CourseID='"+student+"'";
                    PreparedStatement state = Conn.prepareStatement(sql2);
                    ResultSet rs1 = state.executeQuery(sql2);
                    studentCombo.removeAllItems();
                    while (rs1.next()) {
                        String namey=rs1.getString("StudentNo");
                        studentCombo.addItem(namey);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnTranscript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regno= (String) studentCombo.getSelectedItem();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String query="SELECT * FROM marks WHERE StudentNo='"+regno+"'";
                    PreparedStatement pass= conn.prepareStatement(query);
                    ResultSet set=pass.executeQuery(query);

                    ResultSetMetaData meta=set.getMetaData();
                    DefaultTableModel transmodel=(DefaultTableModel) SchoolTable.getModel();

                    int col=meta.getColumnCount();
                    String Columns[] =new String[col];
                    for(int i=0;i<col;i++){
                        Columns[i]=meta.getColumnName(i+1);
                        transmodel.setColumnIdentifiers(Columns);
                        String A,B,C,D,E,F,G,H,I,J,K;
                        while (set.next()) {
                            A = set.getString(1);
                            B = set.getString(2);
                            C = set.getString(3);
                            D = set.getString(4);
                            E = set.getString(5);
                            F = set.getString(6);
                            G = set.getString(7);
                            H = set.getString(8);
                            I = set.getString(9);
                            J = set.getString(10);
                            K = set.getString(11);

                            String[] rory = {A,B,C,D,E,F,G,H,I,J,K};
                            transmodel.setRowCount(0);
                            transmodel.addRow(rory);
                        }
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
        Schools school=new Schools(null);
    }
}

