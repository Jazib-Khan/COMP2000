package com;
import com.Controller.LoginController;
import org.junit.*;
import static org.junit.Assert.*;

public class Testing {

    public void testLogin(){
        LoginController loginController = new LoginController();
        loginController.countLines();
        boolean check = loginController.logic("Mohid","Khan", "Admin");

        assertTrue(check);
    }
    
}
