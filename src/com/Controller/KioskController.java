package com.Controller;

import com.Model.KioskModel;
import com.View.Kiosk;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class KioskController {

    public void viewStock(JTable kioskTbl){

        String filepath = "resources\\stock.txt";
        File file = new File(filepath);

        KioskModel kioskModel = new KioskModel();
        kioskModel.table(kioskTbl);


        try {//File reader to open the text file
            BufferedReader br = new BufferedReader(new FileReader(file));
            DefaultTableModel model = (DefaultTableModel) kioskTbl.getModel();

            Object[] tableLines = br.lines().toArray();
            //For loop to take in the rows and columns separate the values with "," and to insert the data entries
            for (int i =0; i <tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void checkout(JTable kioskTbl) {

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

        //For loop to go through table and retrieve values
        for (int i = 0; i < rows; i++) {
            ArrayList<String> result = new ArrayList<String>();
            for (int j = 0; j < cols; j++) {
                result.add(kioskTbl.getModel().getValueAt(i, j).toString());
            }

            try { //writes into the text file
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

    public void displayContents(JTable kioskTbl){
        //Singleton design pattern: Retrieves the instance of itself
        Kiosk kiosk = Kiosk.getInstance();
        //Grabs the table model and sets the text fields to what's been selected to save the user time when adding to the bill
        DefaultTableModel model = (DefaultTableModel)kioskTbl.getModel();
        int myIndex = kioskTbl.getSelectedRow();
        kiosk.kioskName.setText(model.getValueAt(myIndex, 1).toString());
    }


    public void clear(){
        //Singleton design pattern: Retrieves the instance of itself
        Kiosk kiosk = Kiosk.getInstance();
        //Clears the text fields and text area by setting the text empty
        kiosk.total[0] = 0;
        kiosk.kioskName.setText("");
        kiosk.kioskQty.setText("");
        kiosk.receiptTxt.setText("");
        kiosk.totalLbl.setText("");
    }

    public void addData(JTable kioskTbl){
        //Singleton design pattern: Retrieves the instance of itself
        Kiosk kiosk = Kiosk.getInstance();

        //Gets the model of the J table
        DefaultTableModel model = (DefaultTableModel)kioskTbl.getModel();
        int myIndex = kioskTbl.getSelectedRow();

        double price;
        double prodTot;
        int availQty;

        //Variable available quantity is to get the value of the stock of the particular cell
        availQty = Integer.parseInt(model.getValueAt(myIndex, 2).toString());
        //The new quantity then becomes what the available quantity was with how much the user has taken to add to the bill
        int newQty = availQty - Integer.parseInt(kiosk.kioskQty.getText());

        price = Double.parseDouble(model.getValueAt(myIndex, 3).toString());
        //Calculates the total price of a particular item if the user has bought in quantity
        prodTot = price * Integer.parseInt(kiosk.kioskQty.getText());
        //Calculates the grand total price of all the items in the bill
        kiosk.total[0] = kiosk.total[0] + prodTot;

        //Validation if the user hasn't entered anything
        if (kiosk.kioskName.getText().isEmpty() || kiosk.kioskQty.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Missing information");
        }
        //Validation if the user has tried to order more then what is available
        else if( availQty < Integer.parseInt(kiosk.kioskQty.getText())){
            JOptionPane.showMessageDialog(null,"Not Enough Stock");
        }
        else{//Prints to the text area like a receipt
            if(!kiosk.receiptTxt.getText().contains("    =================KWIK-E-MART================\n"))
            {
                kiosk.receiptTxt.setText(kiosk.receiptTxt.getText()+"    =================KWIK-E-MART================\n"+"PRODUCT   QUANTITY   PRICE (£)   TOTAL\n" + kiosk.kioskName.getText() + "            " + kiosk.kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3)  + "            " + kiosk.df.format(prodTot) + "\n");
            }
            else{
                kiosk.receiptTxt.setText(kiosk.receiptTxt.getText() + kiosk.kioskName.getText() + "            " + kiosk.kioskQty.getText() + "                " + kioskTbl.getValueAt(myIndex,3) + "            " + kiosk.df.format(prodTot) + "\n");
            }
            //Prints the total price of the bill and wraps it 2 decimal places
            kiosk.totalLbl.setText("Total: £" + kiosk.df.format(kiosk.total[0]));
            kioskTbl.setValueAt(newQty, myIndex, 2);
        }
    }
}
