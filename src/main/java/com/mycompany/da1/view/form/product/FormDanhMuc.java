package com.mycompany.da1.view.form.product;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.service.IMPL.DanhMucChungImpl;
import com.mycompany.da1.util.Contants;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.ValidateEx;
import com.mycompany.da1.view.events.EventDialogListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sonst
 */
public class FormDanhMuc extends javax.swing.JPanel implements EventDialogListener {

    private DanhMucChungImpl<DanhMucEntity> danhMucChungImpl = new DanhMucChungImpl<>();

    private EventDialogListener dialogListener;

    private List<DanhMucEntity> lstData;

    public FormDanhMuc() {
        initComponents();
        setUp();
    }

    public FormDanhMuc(EventDialogListener dialogListener) {
        initComponents();
        setUp();
        this.dialogListener = dialogListener;
    }

    private void setUp() {
        fillTable();
    }

    private void fillTable() {
        lstData = danhMucChungImpl.getAll(DanhMucEntity.class);
        if (lstData == null) {
            MsgBox.alert(this, "Có lỗi xảy ra");
        } else if (lstData.isEmpty()) {
            MsgBox.alert(this, "Không có dữ liệu");
        } else {
            DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
            model.setRowCount(0);
            for (DanhMucEntity el : lstData) {
                model.addRow(new Object[]{
                    el.getId(),
                    el.getTenDanhMuc(),
                    Contants.getStatusAttribute(el.getTrangThai())
                });
            }
        }
    }

    private void clearForm() {
        lblId.setText("");
        txtTen.setText("");
        rdoCon.setSelected(true);
    }

    private Boolean checkEquasName(String name) {
        for (DanhMucEntity item : lstData) {
            if (item.getTenDanhMuc().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    private Boolean checkEquasName(String name, int id) {
        for (DanhMucEntity item : lstData) {
            if (item.getTenDanhMuc().equalsIgnoreCase(name.trim()) && item.getId() == id) {
                continue;
            }
            if (item.getTenDanhMuc().equalsIgnoreCase(name.trim()) && item.getId() != id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void closeDialog() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        lbClose = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoHet = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        jLabel17.setText("ID");

        jLabel18.setText("Tên danh mục");

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên Danh mục", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSach);

        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
        });

        jLabel20.setText("Trạng Thái");

        rdoCon.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoCon);
        rdoCon.setSelected(true);
        rdoCon.setText("Còn ");

        rdoHet.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoHet);
        rdoHet.setText("Hết");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnCapNhat)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bảng danh mục");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lbClose)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel17))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblId)
                                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoCon)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoHet))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblId, txtTen});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel17, jLabel18, jLabel20});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblId)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(rdoCon)
                    .addComponent(rdoHet))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblId, txtTen});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel17, jLabel18});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (ValidateEx.checkIsNull(txtTen)) {
            MsgBox.alert(this, "Tên danh mục bắt buộc nhập");
            return;
        }

        if (checkEquasName(txtTen.getText().trim())) {
            MsgBox.alert(this, "Tên đã tồn tại");
            return;
        }

        DanhMucEntity danhMucEntity = new DanhMucEntity();
        danhMucEntity.setTenDanhMuc(txtTen.getText().trim());
        danhMucEntity.setTrangThai(rdoCon.isSelected() ? 1 : 0);

        System.out.println(danhMucEntity.getTrangThai() + " =========================================");

        DanhMucEntity objTmp = danhMucChungImpl.save(danhMucEntity);
        if (objTmp == null) {
            MsgBox.alert(this, "Thêm mới không thành công");
            return;
        }

        MsgBox.alert(this, "Thêm mới thành công");
        dialogListener.closeDialog();
        clearForm();
        fillTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (ValidateEx.checkIsNull(txtTen)) {
            MsgBox.alert(this, "Tên danh mục bắt buộc nhập");
            return;
        }

        if (lblId.getText().equals("")) {
            MsgBox.alert(this, "Không lấy được id danh mục");
            return;
        }

        if (checkEquasName(txtTen.getText().trim(), Integer.valueOf(lblId.getText()))) {
            MsgBox.alert(this, "Tên sản phẩm đã tồn tại");
            return;
        }

        int id = Integer.valueOf(lblId.getText());
        DanhMucEntity danhMucEntity = new DanhMucEntity();
        danhMucEntity.setId(id);
        danhMucEntity.setTenDanhMuc(txtTen.getText().trim());
        danhMucEntity.setTrangThai(rdoCon.isSelected() ? 1 : 0);

        int check = danhMucChungImpl.update(DanhMucEntity.class,
                "tenDanhMuc",
                danhMucEntity.getId(),
                danhMucEntity.getTrangThai(),
                danhMucEntity.getTenDanhMuc());
        if (check == 0) {
            MsgBox.alert(this, "Cập nhật không thành công");
            return;
        }

        MsgBox.alert(this, "Cập nhật thành công");
        dialogListener.closeDialog();
        clearForm();
        fillTable();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int index = tblDanhSach.getSelectedRow();
        if (index < 0) {
            return;
        }
        lblId.setText(tblDanhSach.getValueAt(index, 0).toString());
        txtTen.setText(tblDanhSach.getValueAt(index, 1).toString());
        if (tblDanhSach.getValueAt(index, 2).toString().equals("Hết")) {
            rdoHet.setSelected(true);
        } else {
            rdoCon.setSelected(true);
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked

    }//GEN-LAST:event_lbCloseMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lblId;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
