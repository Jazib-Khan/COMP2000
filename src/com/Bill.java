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

public class Bill extends JFrame{
    private JTable billTbl;
    private JTextField billName;
    private JTextField billQuantity;
    private JButton editButton;
    private JButton refBtn;
    private JButton addButton;
    private JComboBox categoryCB;
    private JButton clearButton;
    private JPanel mainPanel;
    private JTextArea billTxt;
    private JLabel logoutLbl;

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
        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price (£)", "Category"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        billTbl.setModel(model);
        billTbl.getTableHeader().setReorderingAllowed(false);

        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                billName.setText("");
                billQuantity.setText("");
            }
        });


        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = 0;
                if (billName.getText().isEmpty() || billQuantity.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else{
                    i++;
                    if(i == 1){
                        billTxt.setText(billTxt.getText()+"\n========KWIK-E-MART=========\n"+"NUM   PRODUCT   PRICE   QUANTITY   TOTAL\n"+i+"     "+ billName.getText()+"    "+ billQuantity.getText());
                    }
                    try {

                        //Insert the values into the text file
                        //model.addRow(new Object[]{billName.getText(), billQuantity.getText(), categoryCB.getSelectedItem().toString()});
                        File file = new File("C:\\Users\\User\\Documents\\GitHub\\COMP2000\\stockTbl.txt");
                        Scanner scan = new Scanner(file);

                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                }
            }
        });

        billTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel)billTbl.getModel();
                int myIndex = billTbl.getSelectedRow();
                billName.setText(model.getValueAt(myIndex, 1).toString());
            }
        });

        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login().setVisible(true);


            }
        });

        refBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String filepath = "resources\\stockTbl.txt";
                File file = new File(filepath);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    DefaultTableModel model = (DefaultTableModel) billTbl.getModel();

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
