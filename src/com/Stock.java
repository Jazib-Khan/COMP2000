package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;


public class Stock extends JFrame {
    private JPanel mainPanel;
    private JTextField barcode;
    private JTextField stockQuantity;
    private JTextField stockName;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JButton addBtn;
    private JTable stockTbl;
    private JLabel categoriesLbl;
    private JLabel logoutLbl;
    private JTextField stockPrice;
    private JButton viewBtn;
    private JButton saveBtn;

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
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
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


        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(barcode.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter The Stock To Be Deleted");
                }
                else{
                    try{
                        String filepath = "resources\\stockTbl.txt";
                        String BID = barcode.getText();

                    }catch (Exception event){
                        event.printStackTrace();
                    }
                }
            }
        });

        //if(barcode.getText().isEmpty() || stockName.getText().isEmpty() || stockQuantity.getText().isEmpty() || stockPrice.getText().isEmpty())

        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TableCellEditor editor = stockTbl.getCellEditor();
                if(editor != null) {
                    editor.stopCellEditing();
                }

                int rows = stockTbl.getRowCount();
                int cols = stockTbl.getColumnCount();

                try {
                    //empty the file
                    BufferedWriter bw = null;
                    bw = new BufferedWriter(new FileWriter("resources\\stockTbl.txt"));
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

                    try {
                        BufferedWriter bw = null;
                        bw = new BufferedWriter(new FileWriter("resources\\stockTbl.txt", true));
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
        });
    }

}
