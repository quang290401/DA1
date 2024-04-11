/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.da1.view.form.sales;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.service.IMPL.VoucherServiceIMPL;
import com.mycompany.da1.util.XFile;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Quang
 */
public class FormVoucher2 extends javax.swing.JPanel {

    VoucherServiceIMPL voucherServiceIMPL = new VoucherServiceIMPL();

    /**
     * Creates new form FormVoucher2
     */
    public FormVoucher2() {
        initComponents();
        SetUp();
        loadtableVoucher();
    }

    private void SetUp() {
        txtNgayBatDau.getDateEditor().setEnabled(false);
        txtNgayKetThuc.getDateEditor().setEnabled(false);
        rdoHoatDong.setSelected(true);
        txtIDvoucher.setEditable(false);
         whenSerch();
    }

    public void loadtableVoucher() {
        TableSwing dtm = new TableSwing(new Object[]{"ID", "Tên Khuyến Mại", "% Giảm", "Trạng thái", "Ngày Bắt Đầu", "Ngày kết thúc"}, 0);
        tblKhuyenMai.setModel(dtm);
        String trangThai2;
        int tt;
        if(cbbLoc.getSelectedItem().equals("Đang Hoạt Động")){
            tt=1;
        }else {
            tt=0;
        }
        ArrayList<VoucherEntity> voucherEntitys = voucherServiceIMPL.GetAllByTrangthai(tt);
        for (VoucherEntity p : voucherEntitys) {
            if (p.getTrangThai() == 1) {
                trangThai2 = "Đang hoạt Động";
            } else {
                trangThai2 = "Dừng hoạt động";
            }
            Object[] row = {p.getId(), p.getTenKhuyenMai(), p.getPhanTramGiam(),
                trangThai2, p.getNgayBatDau(), p.getNgayKetThuc()
            };
            dtm.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtSerch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        txtTenKhuyenMai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtPhanTramGiam = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        rdoHoatDong = new javax.swing.JRadioButton();
        rdoNgung = new javax.swing.JRadioButton();
        txtIDvoucher = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        cbbLoc = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtSerch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerchKeyReleased(evt);
            }
        });

        jLabel1.setText("Tìm kiếm theo tên Khuyến mại");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("VouCher");

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên Khuyến Mại", "% Giảm", "Trạng Thái", "Ngày Bắt Đầu", "Ngày kết thúc"
            }
        ));
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        jLabel3.setText("Tên Khuyến mại");

        txtNgayBatDau.setToolTipText("");

        jLabel4.setText("Ngày bắt đầu");

        jLabel5.setText("Ngày kết Thúc");

        txtNgayKetThuc.setToolTipText("");

        jLabel6.setText("Phần Trăm Giảm");

        txtPhanTramGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhanTramGiamActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 255, 51));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(51, 255, 102));
        btnSua.setIcon(new javax.swing.ImageIcon("D:\\JavaCode\\DA1\\src\\main\\java\\com\\mycompany\\da1\\Icon\\icons8-clear-16.png")); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHoatDong);
        rdoHoatDong.setText("Đang hoạt động");

        buttonGroup1.add(rdoNgung);
        rdoNgung.setText("Ngừng hoạt động");

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        cbbLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Hoạt Động", "Dừng" }));
        cbbLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocActionPerformed(evt);
            }
        });
        cbbLoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbbLocKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(266, 266, 266))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSerch, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIDvoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoHoatDong)
                            .addComponent(btnThem))
                        .addGap(7, 7, 7)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSua)
                        .addGap(36, 36, 36)
                        .addComponent(btnReset))
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNgung))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSerch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtIDvoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoHoatDong)
                            .addComponent(rdoNgung))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnReset))
                        .addGap(27, 27, 27))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPhanTramGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhanTramGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhanTramGiamActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        int row = this.tblKhuyenMai.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idVoucher = tblKhuyenMai.getValueAt(row, 0).toString();
        String tenKhuyenMai = tblKhuyenMai.getValueAt(row, 1).toString();
        String phanTramGiam = tblKhuyenMai.getValueAt(row, 2).toString();
        String trangThai = tblKhuyenMai.getValueAt(row, 3).toString();
        String ngayBatDau = tblKhuyenMai.getValueAt(row, 4).toString();
        String ngayKetThuc = tblKhuyenMai.getValueAt(row, 5).toString();
        if(trangThai.equalsIgnoreCase("Đang hoạt động")){
            rdoHoatDong.setSelected(true);
        }else{
            rdoNgung.setSelected(true);
        }
        // Đảm bảo rằng dữ liệu được chuyển đổi sang đúng định dạng trước khi gán cho JDateChooser
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(ngayBatDau);
        } catch (ParseException ex) {
            Logger.getLogger(FormVoucher2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date endDate = null;
        try {
            endDate = dateFormat.parse(ngayKetThuc);
        } catch (ParseException ex) {
            Logger.getLogger(FormVoucher2.class.getName()).log(Level.SEVERE, null, ex);
        }

// Gán ngày bắt đầu và ngày kết thúc cho JDateChooser
        txtNgayBatDau.setDate(startDate);
        txtNgayKetThuc.setDate(endDate);
        txtIDvoucher.setText(idVoucher);
        txtTenKhuyenMai.setText(tenKhuyenMai);
        txtPhanTramGiam.setText(phanTramGiam);

    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtTenKhuyenMai.setText("");
        txtPhanTramGiam.setText("");
        txtIDvoucher.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayKetThuc.setDate(null);


    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (txtTenKhuyenMai.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        } else if (txtPhanTramGiam.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        } else if (txtNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        } else if (txtNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        }
        VoucherEntity voucherEntity = new VoucherEntity();
        voucherEntity.setId(Integer.parseInt(txtIDvoucher.getText()));
        voucherEntity.setTenKhuyenMai(txtTenKhuyenMai.getText());
        voucherEntity.setPhanTramGiam(Integer.parseInt(txtPhanTramGiam.getText()));
        int trangThai;
        if (rdoHoatDong.isSelected()) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        voucherEntity.setTrangThai(trangThai);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayBatDau = dateFormat.format(txtNgayBatDau.getDate());
        voucherEntity.setNgayBatDau(LocalDate.parse(ngayBatDau));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String ngayKetthuc = dateFormat.format(txtNgayKetThuc.getDate());
        voucherEntity.setNgayKetThuc(LocalDate.parse(ngayKetthuc));
        voucherServiceIMPL.Update(voucherEntity);
        loadtableVoucher();
        JOptionPane.showMessageDialog(null, "Sửa thành công");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cbbLocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbLocKeyReleased
     
    }//GEN-LAST:event_cbbLocKeyReleased

    private void cbbLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocActionPerformed
        loadtableVoucher();
    }//GEN-LAST:event_cbbLocActionPerformed

    private void txtSerchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerchKeyReleased
      whenSerch();
    }//GEN-LAST:event_txtSerchKeyReleased
   public void whenSerch(){
            TableSwing dtm = new TableSwing(new Object[]{"ID", "Tên Khuyến Mại", "% Giảm", "Trạng thái", "Ngày Bắt Đầu", "Ngày kết thúc"}, 0);
        tblKhuyenMai.setModel(dtm);
        String trangThai2;
          String textSearch = txtSerch.getText().trim();
        ArrayList<VoucherEntity> voucherEntitys = voucherServiceIMPL.SerchVoucher(textSearch);
       System.out.println(txtSerch.getText());
        for (VoucherEntity p : voucherEntitys) {
            if (p.getTrangThai() == 1) {
                trangThai2 = "Đang hoạt Động";
            } else {
                trangThai2 = "Dừng hoạt động";
            }
            Object[] row = {p.getId(), p.getTenKhuyenMai(), p.getPhanTramGiam(),
                trangThai2, p.getNgayBatDau(), p.getNgayKetThuc()
            };
            dtm.addRow(row);
        }
   }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtTenKhuyenMai.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        } else if (txtPhanTramGiam.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        } else if (txtNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        } else if (txtNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(null, "không được để trống");
            return;
        }
        VoucherEntity voucherEntity = new VoucherEntity();
        voucherEntity.setTenKhuyenMai(txtTenKhuyenMai.getText());
        voucherEntity.setPhanTramGiam(Integer.parseInt(txtPhanTramGiam.getText()));
        int trangThai;
        if (rdoHoatDong.isSelected()) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        voucherEntity.setTrangThai(trangThai);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayBatDau = dateFormat.format(txtNgayBatDau.getDate());
        voucherEntity.setNgayBatDau(LocalDate.parse(ngayBatDau));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String ngayKetthuc = dateFormat.format(txtNgayKetThuc.getDate());
        voucherEntity.setNgayKetThuc(LocalDate.parse(ngayKetthuc));
        voucherServiceIMPL.save(voucherEntity);
        loadtableVoucher();
        JOptionPane.showMessageDialog(null, "Thêm Thành Công");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNgung;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtIDvoucher;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtSerch;
    private javax.swing.JTextField txtTenKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
