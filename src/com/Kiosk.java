package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Kiosk extends JFrame {
    private JPanel mainPanel;
    private JTable kioskTbl;
    private JTextField kioskName;
    private JTextField kioskQty;
    private JButton addBtn;
    private JButton clearBtn;
    private JTextArea receiptTxt;
    private JLabel logoutLbl;
    private JButton viewBtn;

    public static void main(String[] args) {
        Kiosk page = new Kiosk();
        page.setVisible(true);
    }

    public Kiosk() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        //Creates table
        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price (£)", "Category"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        kioskTbl.setModel(model);
        kioskTbl.getTableHeader().setReorderingAllowed(false);

        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                kioskName.setText("");
                kioskQty.setText("");
            }
        });


        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel)kioskTbl.getModel();
                int myIndex = kioskTbl.getSelectedRow();
                Double price;
                Double prodTot;
                price = Double.valueOf(model.getValueAt(myIndex, 3).toString());
                prodTot = price * Integer.valueOf(kioskQty.getText());

                if (kioskName.getText().isEmpty() || kioskQty.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else{
                    if(!receiptTxt.getText().contains("    =================KWIK-E-MART================\n"))
                    {
                        receiptTxt.setText(receiptTxt.getText()+"    =================KWIK-E-MART================\n"+"PRODUCT   QUANTITY   PRICE (£)   TOTAL\n" + kioskName.getText() + "            " + kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3) + "            " + prodTot.toString() + "\n");
                    }
                    else{
                        receiptTxt.setText(receiptTxt.getText() + kioskName.getText() + "            " + kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3) + "            " + prodTot.toString() + "\n");
                    }
                    try {

                        //Insert the values into the text file
                        //model.addRow(new Object[]{billName.getText(), billQuantity.getText(), categoryCB.getSelectedItem().toString()});
                        File file = new File("resources\\stockTbl.txt");
                        Scanner scan = new Scanner(file);

                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                }
            }
        });

        kioskTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel)kioskTbl.getModel();
                int myIndex = kioskTbl.getSelectedRow();
                kioskName.setText(model.getValueAt(myIndex, 1).toString());


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
                    DefaultTableModel model = (DefaultTableModel) kioskTbl.getModel();

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

        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login().setVisible(true);
            }
        });

    }

}
