package com.Controller;

import com.Model.StockModel;
import com.View.Stock;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.io.*;
import java.util.ArrayList;

public class StockController {

    public void table(JTable stockTbl){
        //Creates table
        StockModel stockModel = new StockModel();
        stockModel.table(stockTbl);
    }

    public void addData(JTable stockTbl){
        Stock stock = Stock.getInstance();
        //Validation if some of the text fields are left blank then the stock won't be added in
        if (stock.barcode.getText().isEmpty() || stock.stockName.getText().isEmpty() || stock.stockQuantity.getText().isEmpty() || stock.stockPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Missing information");
        } else {

            try {
                //adds the values into the j table
                DefaultTableModel model = (DefaultTableModel)stockTbl.getModel();
                model.addRow(new Object[]{stock.barcode.getText(), stock.stockName.getText(), stock.stockQuantity.getText(), stock.stockPrice.getText()});
                //adds the values into the text file
                BufferedWriter bw = null;
                bw = new BufferedWriter(new FileWriter("resources\\stock.txt", true));
                bw.write(stock.barcode.getText() + "," + stock.stockName.getText() + "," + stock.stockQuantity.getText() + "," + stock.stockPrice.getText());
                bw.newLine();
                bw.flush();
                bw.close();
                //Prompts the user that the product has been added
                JOptionPane.showMessageDialog(null, "Product added successfully");

            } catch (Exception event) {
                event.printStackTrace();
            }
        }
    }

    public void displayContents(JTable stockTbl){
        Stock stock = Stock.getInstance();
        //Displays the selected row onto the text fields
        DefaultTableModel model = (DefaultTableModel) stockTbl.getModel();
        int myIndex = stockTbl.getSelectedRow();
        stock.barcode.setText(model.getValueAt(myIndex, 0).toString());
        stock.stockName.setText(model.getValueAt(myIndex, 1).toString());
        stock.stockQuantity.setText(model.getValueAt(myIndex, 2).toString());
        stock.stockPrice.setText(model.getValueAt(myIndex, 3).toString());
    }

    public void viewStock(JTable stockTbl){
        String filepath = "resources\\stock.txt";
        File file = new File(filepath);

        try { //reads from the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            DefaultTableModel model = (DefaultTableModel) stockTbl.getModel();

            Object[] tableLines = br.lines().toArray();

            //reads all the lines in the file and displays it accordingly
            for (int i =0; i <tableLines.length; i++){
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
        } catch (Exception event) {
            event.printStackTrace();
        }
    }

    public void save(JTable stockTbl){
        TableCellEditor editor = stockTbl.getCellEditor();
        if(editor != null) {
            editor.stopCellEditing();
        }

        int rows = stockTbl.getRowCount();
        int cols = stockTbl.getColumnCount();

        try {
            //empty the file
            BufferedWriter bw = null;
            bw = new BufferedWriter(new FileWriter("resources\\stock.txt"));
            bw.write("");
            bw.close();
        }catch(Exception event){
            event.printStackTrace();
        }


        for (int i = 0; i < rows; i++) {
            ArrayList<String> result = new ArrayList<String>();
            for (int j = 0; j < cols; j++) {
                result.add(stockTbl.getModel().getValueAt(i, j).toString());
            }

            try { //Writes in the file of what the current J table is thus saving the most up to date products
                BufferedWriter bw = null;
                bw = new BufferedWriter(new FileWriter("resources\\stock.txt", true));
                bw.write(String.join(",", result));
                bw.newLine();
                bw.flush();
                bw.close();

            }catch (Exception event) {
                event.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null, "Table saved");
    }

    public void clear(){
        Stock stock = Stock.getInstance();
        stock.barcode.setText("");
        stock.stockName.setText("");
        stock.stockQuantity.setText("");
        stock.stockPrice.setText("");
    }

}
