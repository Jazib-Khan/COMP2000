package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;



public class Customers extends JFrame {
    private JPanel mainPanel;
    private JTextField customerID;
    private JTextField customerPassword;
    private JTextField customerName;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JTable customerTbl;
    private JLabel categoriesLbl;
    private JLabel productLbl;
    private JLabel logoutLbl;

    public static void main(String[] args) {
        Customers page = new Customers();
        page.setVisible(true);
    }

    public Customers(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,600));
        pack();

        String[] columnIdentifiers = new String[]{"ID", "Name", "Password"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        customerTbl.setModel(model);
        customerTbl.getTableHeader().setReorderingAllowed(false);
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (customerID.getText().isEmpty() || customerName.getText().isEmpty() || customerPassword.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else{

                    try {

                        //Insert the values into the text file
                        model.addRow(new Object[]{customerID.getText(), customerName.getText(), customerPassword.getText()});

                        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\User\\Documents\\GitHub\\COMP2000\\customerTbl.txt"));
                        bw.write(customerID.getText() + ", " + customerName.getText() + ", " + customerPassword.getText());
                        bw.close();

                        JOptionPane.showMessageDialog(null, "Customer added successfully");
                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                }
            }
        });

        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                customerID.setText("");
                customerName.setText("");
                customerPassword.setText("");
            }
        });

        customerTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //Displays the selected row onto the text fields
                DefaultTableModel model = (DefaultTableModel)customerTbl.getModel();
                int myIndex = customerTbl.getSelectedRow();
                customerID.setText(model.getValueAt(myIndex, 0).toString());
                customerName.setText(model.getValueAt(myIndex, 1).toString());
                customerPassword.setText(model.getValueAt(myIndex, 2).toString());

            }
        });
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(customerID.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Enter the Seller to be deleted");
                }
                else {
                    try{
                        //connect to text file
                        //get SellID.gettext();
                        //Delete from text file
                        //JOptionPane "Seller Deleted Successfully"

                    }catch (Exception event){
                        event.printStackTrace();
                    }

                }
            }
        });
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (customerID.getText().isEmpty() || customerName.getText().isEmpty() || customerPassword.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else{
                    try{
                        //connect to text file
                        //update query
                        //JOptionPane "Seller information updated"
                    }catch (Exception event){
                        event.printStackTrace();
                    }
                }
            }
        });

        productLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Stock().setVisible(true);
            }
        });

        categoriesLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Categories().setVisible(true);
            }
        });

        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login().setVisible(true);
            }
        });


    }

    public void SelectSeller(){
        try{
            //connect to text file
            customerTbl.setModel(null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
