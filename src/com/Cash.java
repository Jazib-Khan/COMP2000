package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cash extends JFrame{
    private JTextField gTotal;
    private JTextField money;
    private JPanel mainPanel;
    private JButton payBtn;
    private double total;

    public static void main(String[] args) {

    }

    public Cash(double total) {
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

        payBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Takes the input of the amount of money entered and compares to see if it's greater then the amount required
                //if so the payment will be taken and the system will go back to the login screen
                //if not then the system will inform the user how much money is required
                double cash = Double.parseDouble(money.getText());
                if (cash < total){
                    JOptionPane.showMessageDialog(null, "Require " + (total - cash));
                }
                else {
                    JOptionPane.showMessageDialog(null, "Payment taken");
                    new Login().setVisible(true);
                    dispose();
                }
            }
        });
    }
}
