package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.Scanner;

public class Products extends JFrame {
    private JTextField prodID;
    private JComboBox comboBox1;
    private JButton editBtn;
    private JButton addBtn;
    private JButton clearBtn;
    private JTable productTbl;
    private JButton delBtn;
    private JPanel mainPanel;
    private JTextField prodName;
    private JTextField prodQty;
    private JTextField prodPrice;
    private JLabel sellerLbl;
    private JLabel categoriesLbl;
    private JLabel logoutLbl;

    public static void main(String[] args) {
        Products page = new Products();
        page.setVisible(true);
    }

    public Products () {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,600));
        pack();

        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price (£)"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        productTbl.setModel(model);
        productTbl.getTableHeader().setReorderingAllowed(false);


        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (prodID.getText().isEmpty() || prodName.getText().isEmpty() || prodQty.getText().isEmpty() || prodPrice.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else{

                    try {

                        //Insert the values into the text file
                        model.addRow(new Object[]{prodID.getText(), prodName.getText(), prodQty.getText(), prodPrice.getText()});
                        File file = new File("C:\\Users\\User\\Documents\\GitHub\\COMP2000\\ProductTbl.txt");
                        Scanner scan = new Scanner(file);

                        JOptionPane.showMessageDialog(null, "Product added successfully");
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
                prodID.setText("");
                prodName.setText("");
                prodQty.setText("");
                prodPrice.setText("");
            }
        });

        productTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Displays the selected row onto the text fields
                DefaultTableModel model = (DefaultTableModel)productTbl.getModel();
                int myIndex = productTbl.getSelectedRow();
                prodID.setText(model.getValueAt(myIndex, 0).toString());
                prodName.setText(model.getValueAt(myIndex, 1).toString());
                prodQty.setText(model.getValueAt(myIndex, 2).toString());
                prodPrice.setText(model.getValueAt(myIndex, 3).toString());
            }
        });


        sellerLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Seller().setVisible(true);
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

}
