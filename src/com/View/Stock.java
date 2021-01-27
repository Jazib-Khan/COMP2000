package com.View;

import com.Controller.StockController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Stock extends JFrame {
    private static Stock instance; //Singleton
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
        //Singleton
        instance = page;
        page.setVisible(true);
    }

    public static Stock getInstance(){
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

                stockController.displayContents(stockTbl);

            }
        });

        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                stockController.addData(stockTbl);

            }
        });

        //When clicked will clear everything in the text fields
        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                stockController.clear();

            }
        });

        viewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

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
