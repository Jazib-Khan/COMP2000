package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
    private JLabel totalLbl;
    private JFrame instance;
    final double[] total = {0.0};
    private static final DecimalFormat df = new DecimalFormat("0.00"); //Rounds the price to 2 decimal places

    public static void main(String[] args) {
        //Displays the kiosk gui form
        Kiosk page = new Kiosk();
        page.instance = page;
        page.setVisible(true);
    }

    public Kiosk() {
        //Adjusts the gui panel
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();


        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //function when clicked clears everything to allow user to reset

                //
                viewStock(kioskTbl);

                //Clears the text fields and text area by setting the text empty
                kioskName.setText("");
                kioskQty.setText("");
                receiptTxt.setText("");
                totalLbl.setText("");

            }
        });


        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //Gets the model of the J table
                DefaultTableModel model = (DefaultTableModel)kioskTbl.getModel();
                int myIndex = kioskTbl.getSelectedRow();

                double price;
                double prodTot;
                int availQty;

                //Variable available quantity is to get the value of the stock of the particular cell
                availQty = Integer.parseInt(model.getValueAt(myIndex, 2).toString());
                //The new quantity then becomes what the available quantity was with how much the user has taken to add to the bill
                int newQty = availQty - Integer.parseInt(kioskQty.getText());

                price = Double.parseDouble(model.getValueAt(myIndex, 3).toString());
                //Calculates the total price of a particular item if the user has bought in quantity
                prodTot = price * Integer.parseInt(kioskQty.getText());
                //Calculates the grand total price of all the items in the bill
                total[0] = total[0] + prodTot;

                //Validation if the user hasn't entered anything
                if (kioskName.getText().isEmpty() || kioskQty.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                //Validation if the user has tried to order more then what is available
                else if( availQty < Integer.parseInt(kioskQty.getText())){
                    JOptionPane.showMessageDialog(null,"Not Enough Stock");
                }
                else{//Prints to the text area like a receipt
                    if(!receiptTxt.getText().contains("    =================KWIK-E-MART================\n"))
                    {
                        receiptTxt.setText(receiptTxt.getText()+"    =================KWIK-E-MART================\n"+"PRODUCT   QUANTITY   PRICE (£)   TOTAL\n" + kioskName.getText() + "            " + kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3)  + "            " + df.format(prodTot) + "\n");
                    }
                    else{
                        receiptTxt.setText(receiptTxt.getText() + kioskName.getText() + "            " + kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3) + "            " + df.format(prodTot) + "\n");
                    }
                    //Prints the total price of the bill and wraps it 2 decimal places
                    totalLbl.setText("Total: £" + df.format(total[0]));
                    kioskTbl.setValueAt(newQty, myIndex, 2);
                }
            }
        });

        kioskTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Grabs the table model and sets the text fields to what's been selected to save the user time when adding to the bill
                DefaultTableModel model = (DefaultTableModel)kioskTbl.getModel();
                int myIndex = kioskTbl.getSelectedRow();
                kioskName.setText(model.getValueAt(myIndex, 1).toString());
            }
        });

        viewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                viewStock(kioskTbl);
            }
        });

        cardBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Validation if the user tries to make a purchase without adding anything to the bill
                if (receiptTxt.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else {
                    //Proceeds to the checkout function when the user has items in the bill
                    checkout(kioskTbl);
                    new Card(total[0]).setVisible(true);
                    instance.setVisible(false);
                }
            }
        });

        cashBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (receiptTxt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Missing information");
                }
                else {
                    checkout(kioskTbl);
                    new Cash(total[0]).setVisible(true);
                    instance.setVisible(false);
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

    private static void viewStock(JTable kioskTbl){
        String filepath = "resources\\stock.txt";
        File file = new File(filepath);


        //Creates table
        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price (£)"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        kioskTbl.setModel(model);
        kioskTbl.getTableHeader().setReorderingAllowed(false);


        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            model = (DefaultTableModel) kioskTbl.getModel();

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

    private static void checkout(JTable kioskTbl) {

        int rows = kioskTbl.getRowCount();
        int cols = kioskTbl.getColumnCount();

        try {
            //empty the file
            BufferedWriter bw = null;
            bw = new BufferedWriter(new FileWriter("resources\\stock.txt"));
            bw.write("");
            bw.close();
        } catch (Exception event) {
            event.printStackTrace();
        }


        for (int i = 0; i < rows; i++) {
            ArrayList<String> result = new ArrayList<String>();
            for (int j = 0; j < cols; j++) {
                result.add(kioskTbl.getModel().getValueAt(i, j).toString());
            }

            try {
                BufferedWriter bw = null;
                bw = new BufferedWriter(new FileWriter("resources\\stock.txt", true));
                bw.write(String.join(",", result));
                bw.newLine();
                bw.flush();
                bw.close();

            } catch (Exception event) {
                event.printStackTrace();
            }
        }
    }
}
