package com;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JPanel LoginPanel;
    private JComboBox comboBox1;
    private JTextField loginTextField;
    private JButton loginButton;
    private JButton clearButton;

    public Login(){
        setContentPane(LoginPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        pack();
    }

    public static void main(String[] args) {
        Login page = new Login();
        page.setVisible(true);
    }

}
