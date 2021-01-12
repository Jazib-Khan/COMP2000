package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Categories extends JFrame {
    private JPanel mainPanel;
    private JTextField SellID;
    private JTextField SellPass;
    private JTextField SellName;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JTable table1;
    private JLabel customerLbl;
    private JLabel productsLbl;
    private JLabel logoutLbl;

    public static void main(String[] args) {
        Categories page = new Categories();
        page.setVisible(true);
    }

    public Categories() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(750, 400));
        pack();

        customerLbl.addMouseListener(new MouseAdapter() {
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
                new Login().setVisible(true);
            }
        });
    }
}
