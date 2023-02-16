package com.company;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddCourse {

    private JFrame frame;
    private JTextField textFieldCourseCode;
    private JTextField textFieldCourseName;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCourse window = new AddCourse();
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
    public AddCourse() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("COURSE CODE");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(30, 47, 98, 31);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("COURSE NAME");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(192, 47, 187, 31);
        frame.getContentPane().add(lblNewLabel_1);

        textFieldCourseCode = new JTextField();
        textFieldCourseCode.setBounds(10, 89, 165, 37);
        frame.getContentPane().add(textFieldCourseCode);
        textFieldCourseCode.setColumns(10);

        textFieldCourseName = new JTextField();
        textFieldCourseName.setBounds(191, 89, 233, 37);
        frame.getContentPane().add(textFieldCourseName);
        textFieldCourseName.setColumns(10);

        JButton btnCourse = new JButton("ADD");
        btnCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 *
                 * hebu check on how to select an item from combobox ya courses
                 */
                try {
                    //String schoolName = (String) comboBoxSchool.getSelectedItem();
                    String CourseCode = textFieldCourseCode.getText();
                    String courseName = textFieldCourseName.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root","");
                    Statement stmt;
                    String sql = "INSERT INTO course VALUES('"+CourseCode+"','"+courseName+"')";
                    stmt = con.prepareStatement(sql);
                    stmt.execute(sql);


                    con.close();
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                setVisible(false);
            }
        });
        btnCourse.setBounds(78, 157, 156, 37);
        frame.getContentPane().add(btnCourse);

        JButton btnReset = new JButton("RESET");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldCourseCode.setText("");
                textFieldCourseName.setText("");
            }
        });
        btnReset.setBounds(257, 164, 89, 30);
        frame.getContentPane().add(btnReset);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        frame.setVisible(b);
    }
}
