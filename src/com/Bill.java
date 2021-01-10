package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bill extends JFrame{
    private JTable billTbl;
    private JTextField billID;
    private JTextField billName;
    private JTextField billQuantity;
    private JTextField billPrice;
    private JButton editButton;
    private JButton refreshButton;
    private JButton addButton;
    private JComboBox comboBox1;
    private JButton clearButton;
    private JPanel mainPanel;

    public static void main(String[] args) {
        Bill page = new Bill();
        page.setVisible(true);
    }

    public Bill() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        //Creates table
        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price", "Category"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        billTbl.setModel(model);
        billTbl.getTableHeader().setReorderingAllowed(false);

        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                billID.setText("");
                billName.setText("");
                billQuantity.setText("");
                billPrice.setText("");
            }
        });
    }
}
