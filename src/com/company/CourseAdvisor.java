package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CourseAdvisor {

    private JFrame frame;
    private JTable table;
    private JTextField textRegNo;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CourseAdvisor window = new CourseAdvisor();
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
    public CourseAdvisor() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 488, 529);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setToolTipText("table");
        table.setSurrendersFocusOnKeystroke(true);
        table.setFillsViewportHeight(true);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "RegNo", "CourseID", "Year", "Fee Statement"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    Object.class, Object.class, Integer.class, Double.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        table.getColumnModel().getColumn(3).setPreferredWidth(82);
        table.getColumnModel().getColumn(3).setMinWidth(20);
        table.setBounds(25, 218, 296, 235);
        frame.getContentPane().add(table);

        textRegNo = new JTextField();
        textRegNo.setBounds(25, 172, 149, 35);
        frame.getContentPane().add(textRegNo);
        textRegNo.setColumns(10);

        JButton btnSearch = new JButton("SEARCH");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNo = textRegNo.getText();
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project?" + "user=root&password=2402");
                    Statement stmt1;
                    String sql1 = "SELECT course_name FROM course";
                    stmt1 = con.createStatement();
                    ResultSet rs = stmt1.executeQuery(sql1);

                    /*
                     *
                     * check on how to put the value on the table
                     */
                    con.close();
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }
            }
        });
        btnSearch.setBounds(184, 184, 89, 23);
        frame.getContentPane().add(btnSearch);

        JLabel lblNewLabel = new JLabel("Enter the admission number");
        lblNewLabel.setBounds(25, 138, 168, 35);
        frame.getContentPane().add(lblNewLabel);

        btnNewButton = new JButton("APPROVE");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 *
                 * check on ways to approve the courses in the table
                 *
                 */
            }
        });
        btnNewButton.setBounds(332, 218, 102, 35);
        frame.getContentPane().add(btnNewButton);

        btnNewButton_1 = new JButton("REJECT");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_project?" + "user=root&password=2402");
                    Statement stmt2;
                    int RegNo = table.getSelectedColumn();
                    String sql = "DELETE  FROM ExamList WHERE regno='"+RegNo+"'";
                    stmt2 = con.createStatement();
                    stmt2.execute(sql);

                    /*
                     *
                     * check on ways to retrieve and select data from the table
                     *
                     */
                    con.close();
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_1.setBounds(331, 264, 89, 35);
        frame.getContentPane().add(btnNewButton_1);

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
                    }
                    con.close();
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }
            }
        });
        comboCourse.setBounds(25, 80, 190, 35);
        frame.getContentPane().add(comboCourse);

        JLabel lblNewLabel_1 = new JLabel("Select Course");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(51, 47, 117, 35);
        frame.getContentPane().add(lblNewLabel_1);

        btnNewButton_2 = new JButton("CLOSE");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.setVisible(false);
            }

            private void setVisible(boolean b) {
                // TODO Auto-generated method stub
                frame.setVisible(b);
            }
        });
        btnNewButton_2.setBounds(331, 439, 117, 29);
        frame.getContentPane().add(btnNewButton_2);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        frame.setVisible(b);
    }
}
