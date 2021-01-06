package com;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame {
    private JProgressBar progressBar1;
    private JLabel percentLbl;
    private JPanel mainPanel;


    public Loading(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        pack();
    }

    public static void main(String[] args){
        Loading page = new Loading();
        page.setVisible(true);
        try{
            for(int i = 0; i <=100; i++){
                Thread.sleep(40);
                page.progressBar1.setValue(i);
                page.percentLbl.setText(Integer.toString(i)+"%");
            }
        }catch (Exception e) {

        }
        new Login().setVisible(true);
        page.dispose();
    }
}
