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
