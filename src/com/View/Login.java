package com.View;

import com.Controller.LoginController;
import com.Testing;

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

        //Testing testing = new Testing();
        //testing.testLogin();

        //Testing testing = new Testing();
        //testing.testRegister();

        Login page = new Login();
        page.setVisible(true);
    }


    public Login(){
        //Adjusts the gui panel
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300,300));
        pack();


        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //When the login button is clicked the count lines function is called
                // as well as the logic function to check user inputs
                LoginController loginController = new LoginController();
                loginController.countLines();
                loginController.logic(userID.getText(), passID.getText(), roleCb.getSelectedItem());

            }
        });


        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //When the login button is clicked the count lines function is called
                // as well as the addData function to add new entries to the text file
                LoginController loginController = new LoginController();
                loginController.countLines();
                loginController.addData(userID.getText(), passID.getText(), roleCb.getSelectedItem());
            }
        });
    }
}
