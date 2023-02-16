package com.company;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard window = new Dashboard();
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
    public Dashboard() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 515, 324);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnUnits = new JButton("STUDENTS UNITS ENROLLMENT");
        btnUnits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.setVisible(true);
                CourseAdvisor window = new CourseAdvisor();
                window.setVisible(true);

            }

            private void setVisible(boolean b) {
                // TODO Auto-generated method stub
                frame.setVisible(b);
            }
        });
        btnUnits.setBounds(10, 58, 214, 44);
        frame.getContentPane().add(btnUnits);

        JButton btnExam = new JButton("EXAMINATION LIST");
        btnExam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.setVisible(true);
                Examination window = new Examination();
                window.setVisible(true);

            }
            private void setVisible(boolean b) {
                // TODO Auto-generated method stub
                frame.setVisible(b);
            }
        });
        btnExam.setBounds(10, 155, 193, 39);
        frame.getContentPane().add(btnExam);

       /* JButton btnStudent = new JButton("STUDENT DETAILS");
        btnStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.setVisible(true);
                Students window = new Students();
                window.setVisible(true);
            }

            private void setVisible(boolean b) {
                // TODO Auto-generated method stub
                frame.setVisible(b);
            }
        });


        btnStudent.setBounds(291, 53, 175, 51);
        frame.getContentPane().add(btnStudent);

        */

        JButton btnAcademic = new JButton("ACADEMIC STANDINGS");
        btnAcademic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.setVisible(true);
                Academic window = new Academic();
                window.setVisible(true);
            }
            private void setVisible(boolean b) {
                // TODO Auto-generated method stub
                frame.setVisible(b);
            }
        });
        btnAcademic.setBounds(291, 152, 175, 44);
        frame.getContentPane().add(btnAcademic);

        JButton btnNewButton = new JButton("CLOSE");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.setVisible(false);
            }

            private void setVisible(boolean b) {
                // TODO Auto-generated method stub
                frame.setVisible(b);
            }
        });
        btnNewButton.setBounds(201, 235, 91, 39);
        frame.getContentPane().add(btnNewButton);
    }
}
