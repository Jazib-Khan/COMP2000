package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateAdmin extends JFrame {
    private JTextField adminName;
    private JTextField adminPass;
    private JButton updateButton;
    private JButton clearButton;
    private JPanel mainPanel;

    public static void main(String[] args) {
        UpdateAdmin page = new UpdateAdmin();
        page.setVisible(true);
    }

    public UpdateAdmin () {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));
        pack();

        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminName.setText("");
                adminPass.setText("");
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (adminName.getText().isEmpty() || adminPass.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else {
                    try {

                    }catch (Exception event) {
                        event.printStackTrace();
                    }
                }
            }
        });
    }
}
