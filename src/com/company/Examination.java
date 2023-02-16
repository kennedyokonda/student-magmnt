package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Examination {

    private JFrame frame;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Examination window = new Examination();
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
    public Examination() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 479);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JComboBox comboCourse = new JComboBox();
        comboCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project?" + "user=root&password=2402");
                    Statement stmt;
                    String sql = "SELECT course_name FROM course";
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    while(rs.next()) {
                        String answer = rs.getString("course_name");
                        comboCourse.addItem(answer);
                        /*
                         * the course selected registered students to appear
                         *
                         *
                         */
                    }
                    con.close();
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }
            }
        });
        comboCourse.setBounds(32, 75, 179, 38);
        frame.getContentPane().add(comboCourse);

        JLabel lblNewLabel = new JLabel("Select Course");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(32, 33, 164, 31);
        frame.getContentPane().add(lblNewLabel);

        table = new JTable();
        table.setBounds(32, 141, 263, 222);
        frame.getContentPane().add(table);

        JButton btnNewButton = new JButton("PRINT");
        btnNewButton.setBounds(305, 294, 113, 38);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("CLOSE");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.setVisible(false);
            }

            private void setVisible(boolean b) {
                // TODO Auto-generated method stub
                frame.setVisible(b);
            }
        });
        btnNewButton_1.setBounds(293, 374, 113, 38);
        frame.getContentPane().add(btnNewButton_1);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        frame.setVisible(b);
    }
}
