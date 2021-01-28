package com.View;

import com.Controller.KioskController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class Kiosk extends JFrame {
    private static Kiosk instance; //Singleton design pattern: Created an object of single object
    private JPanel mainPanel;
    private JTable kioskTbl;
    public JTextField kioskName;
    public JTextField kioskQty;
    private JButton addBtn;
    private JButton clearBtn;
    public JTextArea receiptTxt;
    private JLabel logoutLbl;
    private JButton viewBtn;
    private JButton cashBtn;
    private JButton cardBtn;
    public JLabel totalLbl;
    public final double[] total = {0.0};
    public static final DecimalFormat df = new DecimalFormat("0.00"); //Rounds the price to 2 decimal places

    public static void main(String[] args) {
        //Displays the kiosk gui form
        Kiosk page = new Kiosk();
        //Singleton design pattern: Sets up the instance
        instance = page;
        page.setVisible(true);
    }

    public static Kiosk getInstance() {
        //Singleton
        return Kiosk.instance;
    }

    public Kiosk() {
        //Adjusts the gui panel
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();

        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //function when clicked clears everything to allow user to reset

                //view stock function is called so that if changes were made to the stock before clearing the bill
                //it will reset to what the original amount of stock is available since a purchase hadn't yet been made
                KioskController kioskController = new KioskController();
                kioskController.viewStock(kioskTbl);

                //Clears the text fields and text area by setting the text empty
                kioskController.clear();
            }
        });


        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Adds inputted product into receipt bill
                KioskController kioskController = new KioskController();
                kioskController.addData(kioskTbl);

            }
        });

        kioskTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //displays contents of the table to text fields
                KioskController kioskController = new KioskController();
                kioskController.displayContents(kioskTbl);

            }
        });

        viewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Display the contents of the stock in a JTable
                KioskController kioskController = new KioskController();
                kioskController.viewStock(kioskTbl);
            }
        });

        cardBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Validation if the user tries to make a purchase without adding anything to the bill
                if (receiptTxt.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else {
                    //Proceeds to the checkout function when the user has items in the bill
                    KioskController kioskController = new KioskController();
                    kioskController.checkout(kioskTbl);
                    //Takes in the total price for the user to make the purchase
                    new Card(total[0]).setVisible(true);
                    instance.setVisible(false);
                }
            }
        });

        cashBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Validation if the user tries to make a purchase without adding anything to the bill
                if (receiptTxt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Missing information");
                }
                else {
                    //Proceeds to the checkout function when the user has items in the bill
                    KioskController kioskController = new KioskController();
                    kioskController.checkout(kioskTbl);
                    //Takes in the total price for the user to make the purchase
                    new Cash(total[0]).setVisible(true);
                    instance.setVisible(false);
                }
            }
        });

        logoutLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Goes to the login menu
                new Login().setVisible(true);
                dispose();
            }
        });
    }
}
