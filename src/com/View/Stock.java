package com.View;

import com.Controller.StockController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stock extends JFrame {
    private static Stock instance; //Singleton design pattern: Created an object of single object
    private JPanel mainPanel;
    public JTextField barcode;
    public JTextField stockQuantity;
    public JTextField stockName;
    private JButton clearBtn;
    private JButton addBtn;
    private JTable stockTbl;
    private JLabel logoutLbl;
    public JTextField stockPrice;
    private JButton viewBtn;
    private JButton saveBtn;

    public static void main(String[] args) {
        //Displays the panel
        Stock page = new Stock();
        //Singleton design pattern: Sets up the instance
        instance = page;
        page.setVisible(true);
    }

    public static Stock getInstance(){
        //Singleton
        return Stock.instance;
    }

    public Stock() {
        //Adjusts the panel
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        //Creates table
        StockController stockController = new StockController();
        stockController.table(stockTbl);

        stockTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //displays contents of the table to text fields
                stockController.displayContents(stockTbl);

            }
        });

        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Adds stock into JTable
                stockController.addData(stockTbl);

            }
        });


        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //When clicked will clear everything in the text fields
                stockController.clear();

            }
        });

        viewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Display the contents of the stock in a JTable
                stockController.viewStock(stockTbl);

            }
        });


        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                stockController.save(stockTbl);

            }
        });

        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Takes the user back to eh login menu
                new Login().setVisible(true);
                dispose();
            }
        });
    }
}
