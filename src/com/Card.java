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
        //Displays and adjusts the panel
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        //Takes the parameter of the total price and sets it to text field for the customer to know the total price
        //the text field is setEditable false so that the user can't edit what the final price is
        this.total = total;
        System.out.println(total);
        gTotal.setText("£" + total);
        gTotal.setEditable(false);

        //When the user clicks confirm payment the systems accepts it and takes the user back to the login menu
        payBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "CARD ACCEPTED BANK AUTHORISED CARD");
                new Login().setVisible(true);
                dispose();
            }
        });

        //When the user clicks decline payment the systems will take the user back to the login menu
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
