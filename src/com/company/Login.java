package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import com.company.UnitRegistration;

public class Login extends JFrame {
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JPanel label;
    private JButton loginButton;


    public Login(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Welcome to Shule University");
        setContentPane(label);
        setMinimumSize(new Dimension(450,400));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=tfUsername.getText();
                String pass= String.valueOf(tfPassword.getPassword());
                String ind=Character.toString(name.charAt(0));
                String ind1=Character.toString(name.charAt(1));
                String index=ind+ind1;


                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false","root","");

                    String Query="SELECT * FROM login1 WHERE Username LIKE '"+index+"%' AND Username='"+name+"' AND Password='"+pass+"'";
                    PreparedStatement ps=conn.prepareStatement(Query);
                    ResultSet set= ps.executeQuery(Query);
                    if (set.next()){
                        //Student
                          if(index.startsWith("IN")
                        ){
                            UnitRegistration UnitRegistration=new UnitRegistration(parent);
                           UnitRegistration.setVisible(true);
                           //Enrollment
                        } else if (index.startsWith("EN")){
                            Enrollment enroll=new Enrollment(parent);
                            enroll.setVisible(true);
                        }
                          //Bursar
                        else if (index.startsWith("BU")){
                              Bursar bus=new Bursar(parent);
                              bus.setVisible(true);
                        }
                        //School
                        else if (index.startsWith("SC")){
                              Schools sch=new Schools(parent);
                              sch.setVisible(true);
                        }
                        //registar
                        else if (index.startsWith("RE")){
                            Registar regi=new Registar(parent);
                            regi.setVisible(true);
                        }
                        //Admission
                        else if (index.startsWith("AD")){
                              Admision admn=new Admision(parent);
                              admn.setVisible(true);
                        }
                        else{
                            //JOptionPane.showMessageDialog(null,"Ooops, we could not find you because you don't exist");
                        }
                        label.setVisible(true);
                        //dispose();
                    } else{
                        JOptionPane.showMessageDialog(null,"Ooops, we could not find you because you don't exist");
                    }


;                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Login login=new Login(null);
    }
}
