package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Login extends JFrame {
    private JButton loginBtn;
    private JPanel mainPanel;
    private JComboBox roleCb;
    private JTextField userID;
    private JTextField passID;
    private JButton registerBtn;

    int ln;

    //Displays the login gui form
    public static void main(String[] args) {
        Login page = new Login();
        page.setVisible(true);
    }

    void addData(String user, String pass, Object role){
        try { //Opens the text file of users and reads through it
            RandomAccessFile raf = new RandomAccessFile("resources\\users.txt", "rw");
            for(int i=0; i<ln; i++) {
                raf.readLine();
            }
            if(ln>0){
                raf.writeBytes("\r\n");
                raf.writeBytes("\r\n");
            } //inserts the data of inputs the user has entered for a username, password and role
            raf.writeBytes("Username:"+user+ "\r\n");
            raf.writeBytes("Password:"+pass+ "\r\n");
            raf.writeBytes("Role:"+role);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void logic(String user, String pass, Object role){
        try { //Opens the text file of users and checks the count number to read and check specific lines
            RandomAccessFile raf = new RandomAccessFile("resources\\users.txt", "rw");
            for(int i=0;i<ln;i+=4){ System.out.println("count " +i);

                String forUser = raf.readLine().substring(9);
                String forPass = raf.readLine().substring(9);

                //Checks if the user input and password input is equal to what's written in the text field
                //If the role checked is customer then the program is taken into the kiosk page for customers to make purchases
                if(user.equals(forUser) & pass.equals(forPass) & role.equals("Customer")){
                    new Kiosk().setVisible(true);
                    dispose();
                    break;
                //If the role checked is customer then the program is taken into the kiosk page for admin to effect product/stock
                }else if(user.equals(forUser) & pass.equals(forPass) & role.equals("Admin")){
                    new Stock().setVisible(true);
                    dispose();
                    break;
                //If the username or password input does not equal to what's been entered in the textf ile then an error message is prompted
                }else if(i==(ln-3)){
                    JOptionPane.showMessageDialog(null,"Incorrect Username/Password");
                    break;
                }
                for (int k=1; k<=2; k++){
                    raf.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function to check and read the lines of the text file
    void countLines(){
        try {
            ln=0;
            RandomAccessFile raf = new RandomAccessFile("resources\\users.txt", "rw");
            for(int i=0; raf.readLine() !=null; i++){
                ln++;
            }
            System.out.println("number of lines:" + ln);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

                countLines();
                logic(userID.getText(), passID.getText(), roleCb.getSelectedItem());

            }
        });

        //When the login button is clicked the count lines function is called
        // as well as the addData function to add new entries to the text file
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                countLines();
                addData(userID.getText(), passID.getText(), roleCb.getSelectedItem());
            }
        });
    }
}
