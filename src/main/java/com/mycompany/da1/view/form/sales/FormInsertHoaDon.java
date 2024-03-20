package com.mycompany.da1.view.form.sales;

public class FormInsertHoaDon extends javax.swing.JPanel {

    public FormInsertHoaDon() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CBBtaiKhoan = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        CBBKhachhang = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        CBBVoucher = new javax.swing.JComboBox<>();
        btnThemHoaDon = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        jLabel1.setBackground(new java.awt.Color(255, 51, 51));
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Thêm Hóa Đơn Chờ");

        jLabel2.setText("Tên Nhân viên");

        CBBtaiKhoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Tên Khách hàng");

        CBBKhachhang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBBKhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBBKhachhangActionPerformed(evt);
            }
        });

        jLabel4.setText("VouCher");

        CBBVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0" }));
        CBBVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBBVoucherActionPerformed(evt);
            }
        });

        btnThemHoaDon.setText("Thêm");
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThemHoaDon)
                                .addGap(77, 77, 77)
                                .addComponent(btnClose))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CBBtaiKhoan, 0, 155, Short.MAX_VALUE)
                                    .addComponent(CBBKhachhang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CBBVoucher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CBBtaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CBBKhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBBVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemHoaDon)
                    .addComponent(btnClose))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CBBKhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBBKhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBBKhachhangActionPerformed

    private void CBBVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBBVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBBVoucherActionPerformed

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed

    }//GEN-LAST:event_btnCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBBKhachhang;
    private javax.swing.JComboBox<String> CBBVoucher;
    private javax.swing.JComboBox<String> CBBtaiKhoan;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnThemHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
