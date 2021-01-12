package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class Stock extends JFrame {
    private JPanel mainPanel;
    private JTextField barcode;
    private JTextField stockQuantity;
    private JTextField stockName;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JTable stockTbl;
    private JLabel categoriesLbl;
    private JLabel logoutLbl;
    private JTextField stockPrice;
    private JButton viewBtn;

    public static void main(String[] args) {
        Stock page = new Stock();
        page.setVisible(true);
    }

    public Stock() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        String[] columnIdentifiers = new String[]{"BarCode", "Name", "Quantity", "Price (£)"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        stockTbl.setModel(model);
        stockTbl.getTableHeader().setReorderingAllowed(false);

        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (barcode.getText().isEmpty() || stockName.getText().isEmpty() || stockQuantity.getText().isEmpty() || stockPrice.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing information");
                } else {

                    try {


                        model.addRow(new Object[]{barcode.getText(), stockName.getText(), stockQuantity.getText(), stockPrice.getText()});


                        BufferedWriter bw = null;
                        bw = new BufferedWriter(new FileWriter("resources\\stockTbl.txt", true));
                        bw.write(barcode.getText() + "," + stockName.getText() + "," + stockQuantity.getText() + "," + stockPrice.getText());
                        bw.newLine();
                        bw.flush();
                        bw.close();


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
                barcode.setText("");
                stockName.setText("");
                stockQuantity.setText("");
                stockPrice.setText("");
            }
        });

        stockTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Displays the selected row onto the text fields
                DefaultTableModel model = (DefaultTableModel) stockTbl.getModel();
                int myIndex = stockTbl.getSelectedRow();
                barcode.setText(model.getValueAt(myIndex, 0).toString());
                stockName.setText(model.getValueAt(myIndex, 1).toString());
                stockQuantity.setText(model.getValueAt(myIndex, 2).toString());
                stockPrice.setText(model.getValueAt(myIndex, 3).toString());
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

        viewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String filepath = "resources\\stockTbl.txt";
                File file = new File(filepath);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String firstLine = br.readLine().trim();
                    /*String[] columnsName = firstLine.split(",");*/
                    DefaultTableModel model = (DefaultTableModel) stockTbl.getModel();

                    Object[] tableLines = br.lines().toArray();

                    for (int i =0; i <tableLines.length; i++){
                        String line = tableLines[i].toString().trim();
                        String[] dataRow = line.split(",");
                        model.addRow(dataRow);
                    }
                } catch (Exception event) {
                    event.printStackTrace();
                }


            }
        });
    }

}
