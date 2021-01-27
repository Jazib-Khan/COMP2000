package com.Controller;

import com.View.Kiosk;
import com.View.Stock;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginController {


    int ln;

    public void addData(String user, String pass, Object role){
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

    public void logic(String user, String pass, Object role){
        try { //Opens the text file of users and checks the count number to read and check specific lines
            RandomAccessFile raf = new RandomAccessFile("resources\\users.txt", "rw");
            for(int i=0;i<ln;i+=4){ System.out.println("count " +i);

                String forUser = raf.readLine().substring(9);
                String forPass = raf.readLine().substring(9);

                //Checks if the user input and password input is equal to what's written in the text field
                //If the role checked is customer then the program is taken into the kiosk page for customers to make purchases
                if(user.equals(forUser) & pass.equals(forPass) & role.equals("Customer")){
                    new Kiosk().setVisible(true);
                    break;
                    //If the role checked is customer then the program is taken into the kiosk page for admin to effect product/stock
                }else if(user.equals(forUser) & pass.equals(forPass) & role.equals("Admin")){
                    new Stock().setVisible(true);
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
    public void countLines(){
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

}
