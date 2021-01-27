package com.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StockModel {

    public DefaultTableModel table(JTable stockTbl){
        //Creates table
        String[] columnIdentifiers = new String[]{"BarCode", "Name", "Quantity", "Price (£)"};
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        model.setColumnIdentifiers(columnIdentifiers);
        stockTbl.setModel(model);
        stockTbl.getTableHeader().setReorderingAllowed(false);
        return model;

    }

}
