package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Categories extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton editButton;
    private JButton addButton;
    private JButton clearButton;
    private JTable table1;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JLabel sellerLbl;
    private JLabel productsLbl;
    private JLabel logoutLbl;

    public static void main(String[] args) {
        Categories page = new Categories();
        page.setVisible(true);
    }
    public Categories () {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        sellerLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Seller().setVisible(true);
            }
        });

        productsLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Products().setVisible(true);
            }
        });

        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

    }




}
