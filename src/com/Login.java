package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;

public class Login extends JFrame {
    private JPanel LoginPanel;
    private JButton loginBtn;
    private JPanel mainPanel;
    private JComboBox roleCb;
    private JTextField userID;
    private JTextField passID;
    private JButton registerButton;


    private static Scanner x;

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

                userID.getText();
                passID.getText();
                File file = new File("resources\\customerTbl.txt");
                verifyLogin(userID.getText(), passID.getText(), file.getAbsolutePath());

            }
        });



        registerButton.addMouseListener(new MouseAdapter() {

        });
    }

    public static void verifyLogin(String user, String pass, String filepath) {

        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";

        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while(x.hasNext() && !found){
                tempUsername = x.next();
                tempPassword = x.next();

                if(tempUsername.trim().equals(user.trim()) && tempPassword.trim().equals(pass.trim()));{
                    found = true;
                }
            }
            x.close();
            System.out.println(found);

        }catch (Exception event){
            event.printStackTrace();
        }

    }


}
