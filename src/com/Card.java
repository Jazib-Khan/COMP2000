package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Card extends JFrame{
    private JPanel mainPanel;
    private JTextField gTotal;
    private JButton payBtn;
    private JButton decBtn;
    private double total;

    public static void main(String[] args) {

    }

    public Card(double total) {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        this.total = total;
        System.out.println(total);
        gTotal.setText("£" + total);
        gTotal.setEditable(false);


        payBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "CARD ACCEPTED BANK AUTHORISED CARD");
                new Login().setVisible(true);
                dispose();
            }
        });

        decBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Login().setVisible(true);
                dispose();
            }
        });
    }
}
