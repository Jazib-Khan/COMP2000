package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
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
    private JButton cashBtn;
    private JButton cardBtn;
    private JButton saveBtn;

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
        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price (£)"};
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
                receiptTxt.setText("");

                try{
                    BufferedWriter bw = null;
                    bw = new BufferedWriter(new FileWriter("resources\\receipt.txt"));
                    bw.write("");
                }catch (Exception event){
                    event.printStackTrace();
                }
            }
        });


        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel)kioskTbl.getModel();
                int myIndex = kioskTbl.getSelectedRow();

                double price;
                double prodTot;
                double total;
                String pName;
                String pQuantity;
                pName = model.getValueAt(myIndex, 1).toString();
                pQuantity = model.getValueAt(myIndex, 2).toString();
                price = Double.parseDouble(model.getValueAt(myIndex, 3).toString());
                prodTot = price * Integer.parseInt(kioskQty.getText());

                if (kioskName.getText().isEmpty() || kioskQty.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else{
                    if(!receiptTxt.getText().contains("    =================KWIK-E-MART================\n"))
                    {
                        receiptTxt.setText(receiptTxt.getText()+"    =================KWIK-E-MART================\n"+"PRODUCT   QUANTITY   PRICE (£)   TOTAL\n" + kioskName.getText() + "            " + kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3)  + "            " + Double.toString(prodTot) + "\n");
                    }
                    else{
                        receiptTxt.setText(receiptTxt.getText() + kioskName.getText() + "            " + kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3) + "            " + Double.toString(prodTot) + "\n");
                    }
                    try {

                        BufferedWriter bw = null;
                        bw = new BufferedWriter(new FileWriter("resources\\receipt.txt", true));
                        bw.write(pName + "," + pQuantity + "," + price + "," + prodTot);
                        bw.newLine();
                        bw.flush();
                        bw.close();

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

        cardBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (receiptTxt.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else {
                    JOptionPane.showMessageDialog(null,"########CARD ACCEPTED TRANSACTION COMPLETED##########");
                    try{
                        BufferedWriter bw = null;
                        bw = new BufferedWriter(new FileWriter("resources\\receipt.txt"));
                        bw.write("");
                    }catch (Exception event){
                        event.printStackTrace();
                    }
                    new Login().setVisible(true);
                    dispose();
                }
            }
        });

        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

    }

}
