package com.mycompany.da1.view.form.sales;

import javax.swing.table.DefaultTableModel;

public class TableSwing extends DefaultTableModel{
        public TableSwing(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Đánh dấu tất cả các ô không thể chỉnh sửa
        }
    }


