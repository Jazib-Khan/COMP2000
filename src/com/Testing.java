package com;
import com.Controller.LoginController;
import com.View.Login;

import static org.junit.Assert.*;

public class Testing {


    public void testRegister(){
        LoginController loginController = new LoginController();
        loginController.countLines();
        boolean check = loginController.addData("Mohid", "Khan", "Admin");

        assertTrue(check);
    }

    public void testLogin(){
        LoginController loginController = new LoginController();
        loginController.countLines();
        boolean check = loginController.logic("Mohid","Khan", "Admin");

        assertTrue(check);
    }

    
}
