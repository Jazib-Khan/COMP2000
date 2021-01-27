package com.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class KioskModel {

    public DefaultTableModel table(JTable kioskTbl){
        //Creates table
        String[] columnIdentifiers = new String[]{"ID", "Name", "Quantity", "Price (£)"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnIdentifiers);
        kioskTbl.setModel(model);
        kioskTbl.getTableHeader().setReorderingAllowed(false);


        return model;

    }

}
