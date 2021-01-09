package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;

public class Products extends JFrame {
    private JTextField prodID;
    private JComboBox comboBox1;
    private JButton editBtn;
    private JButton addBtn;
    private JButton clearBtn;
    private JTable productTbl;
    private JButton delBtn;
    private JPanel mainPanel;
    private JTextField prodName;
    private JTextField prodQty;
    private JTextField prodPrice;

    public static void main(String[] args) {
        Products page = new Products();
        page.setVisible(true);
    }

    public Products () {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,600));
        pack();

        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price (£)"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        productTbl.setModel(model);
        productTbl.getTableHeader().setReorderingAllowed(false);


        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (prodID.getText().isEmpty() || prodName.getText().isEmpty() || prodQty.getText().isEmpty() || prodPrice.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing information");
                }
                else{

                    try {

                        //Insert the values into the text file
                        model.addRow(new Object[]{prodID.getText(), prodName.getText(), prodQty.getText(), prodPrice.getText()});
                        File file = new File("C:\\Users\\User\\Documents\\GitHub\\COMP2000\\ProductTbl.txt");
                        Scanner scan = new Scanner(file);

                        JOptionPane.showMessageDialog(null, "Added successfully");
                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                }
            }
        });
        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                prodID.setText("");
                prodName.setText("");
                prodQty.setText("");
                prodPrice.setText("");
            }
        });
    }

}
