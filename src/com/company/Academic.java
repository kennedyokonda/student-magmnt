package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Academic {

    private JFrame frame;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Academic window = new Academic();
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
    public Academic() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 579, 441);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(25, 55, 170, 33);
        frame.getContentPane().add(comboBox);

        JLabel lblNewLabel = new JLabel("Select Course");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(25, 21, 170, 33);
        frame.getContentPane().add(lblNewLabel);

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setBounds(239, 55, 192, 33);
        frame.getContentPane().add(comboBox_1);

        JLabel lblNewLabel_1 = new JLabel("Course Units");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(239, 26, 170, 22);
        frame.getContentPane().add(lblNewLabel_1);

        table = new JTable();
        table.setBounds(25, 116, 384, 225);
        frame.getContentPane().add(table);

        JButton btnNewButton = new JButton("APPROVE MARKS");
        btnNewButton.setBounds(412, 308, 141, 33);
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
        btnNewButton_1.setBounds(422, 352, 117, 39);
        frame.getContentPane().add(btnNewButton_1);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        frame.setVisible(b);
    }

}

