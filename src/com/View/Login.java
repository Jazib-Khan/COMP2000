package com.View;

import com.Controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private JButton loginBtn;
    private JPanel mainPanel;
    private JComboBox roleCb;
    private JTextField userID;
    private JTextField passID;
    private JButton registerBtn;


    //Displays the login gui form
    public static void main(String[] args) {
        Login page = new Login();
        page.setVisible(true);
    }


    public Login(){
        //Adjusts the gui panel
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300,300));
        pack();

        //When the login button is clicked the count lines function is called
        // as well as the logic function to check user inputs
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                LoginController loginController = new LoginController();
                loginController.countLines();
                loginController.logic(userID.getText(), passID.getText(), roleCb.getSelectedItem());

            }
        });

        //When the login button is clicked the count lines function is called
        // as well as the addData function to add new entries to the text file
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                LoginController loginController = new LoginController();
                loginController.countLines();
                loginController.addData(userID.getText(), passID.getText(), roleCb.getSelectedItem());
            }
        });
    }
}
