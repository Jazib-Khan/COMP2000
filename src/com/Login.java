package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private JPanel LoginPanel;
    private JButton loginBtn;
    private JPanel mainPanel;
    private JComboBox roleCb;
    private JTextField userID;
    private JTextField passID;


    public static void main(String[] args) {
        Login page = new Login();
        page.setVisible(true);
    }

    public Login(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300,300));
        pack();

        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //connect to text file
                //see if matching userID and userPass
                try{

                }catch (Exception event){
                    event.printStackTrace();
                }
            }
        });
    }

}
