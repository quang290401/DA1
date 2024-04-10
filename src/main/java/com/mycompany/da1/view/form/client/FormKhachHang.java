
package com.mycompany.da1.view.form.client;
import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.service.IMPL.KhachHangIMPL;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.ValidateEx;
import com.mycompany.da1.util.XDate;
import com.mycompany.da1.view.form.sales.InsertHoaDon;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FormKhachHang extends javax.swing.JPanel {

    private KhachHangIMPL khachHangService = new KhachHangIMPL();
    
    private List<KhachHangEntity> listKhachHang;
    private int idKhachHang = -1;
    private Integer pageSize = 10;
    private Integer currentPage = 0;
    
    private JDialog jDialog;
    
    public FormKhachHang() {
        initComponents();
        txtIdKhachHang.setEditable(false);
        setUp();
    }

    private void setUp(){
        listKhachHang = khachHangService.GetAll();
        fillTableKhachHang();
        
    }
    public void fillTableKhachHang(){
        listKhachHang = khachHangService.GetAll();
        DefaultTableModel def = (DefaultTableModel) tblKhachHang.getModel();
        def.setRowCount(0);
        
        int start = currentPage * pageSize;
        int end  = Math.min(start + pageSize, listKhachHang.size());
        

        for(int i = start; i < end; i++){
            KhachHangEntity kh = listKhachHang.get(i);
            Object[] rowData = {
                kh.getId() ,
                kh.getHoTen(),
                kh.getSoDienThoai(),
                kh.getNgayTao(),
                kh.getNgaySua()
            };
            
            def.addRow(rowData);

            
        }
        ButtonState();
        
    }
    
    public void fillTableWhenSearch(){
        String txtSearch = txtLocKhachHang.getText();
        
        listKhachHang = khachHangService.getSearch(txtSearch);
        DefaultTableModel def = (DefaultTableModel) tblKhachHang.getModel();
        def.setRowCount(0);
        
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, listKhachHang.size());
        
        int index = 1;
        for (int i = start; i < end; i++) {
            KhachHangEntity kh = listKhachHang.get(i);
            Object[] rowData = {
                kh.getId(),
                kh.getHoTen(),
                kh.getSoDienThoai(),
                XDate.toString(kh.getNgayTao()),
                
            };
            def.addRow(rowData);

        }
        ButtonState();
    }
    
    private KhachHangEntity getIdByMaKh(String maKh){
        List<KhachHangEntity> list = khachHangService.GetAll();
        for (KhachHangEntity item : list) {
//            if () {
//                return item;
//            }
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txthoVaTen = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnclean = new javax.swing.JButton();
        btnnhapExcel = new javax.swing.JButton();
        btnxuatExcel = new javax.swing.JButton();
        BtnTaoHoaDon = new javax.swing.JButton();
        txtIdKhachHang = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtLocKhachHang = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnNhoMax = new javax.swing.JButton();
        btnLonMax = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel2.setText("Họ và tên:");

        jLabel3.setText("SĐT:");

        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/Them.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/Sua.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnclean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-clear-16.png"))); // NOI18N
        btnclean.setText("Clean");
        btnclean.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncleanMouseClicked(evt);
            }
        });
        btnclean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncleanActionPerformed(evt);
            }
        });

        btnnhapExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/iconExcel.png"))); // NOI18N
        btnnhapExcel.setText("Nhập excel");

        btnxuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/iconExcel.png"))); // NOI18N
        btnxuatExcel.setText("Xuất excel");

        BtnTaoHoaDon.setText("Chọn Khách");
        BtnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTaoHoaDonActionPerformed(evt);
            }
        });

        txtIdKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(btnclean, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnnhapExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnxuatExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txthoVaTen, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(txtsdt))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(txtIdKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(BtnTaoHoaDon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txthoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnnhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(BtnTaoHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnthem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(btnclean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnxuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc khách hàng"));

        jLabel7.setText("Tên hoặc sđt khách hàng: ");

        txtLocKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocKhachHangKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addComponent(txtLocKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLocKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "TÊN", "SĐT", "NGÀY TẠO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        btnNhoMax.setText("<<");
        btnNhoMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhoMaxMouseClicked(evt);
            }
        });

        btnLonMax.setText(">>");
        btnLonMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLonMaxMouseClicked(evt);
            }
        });
        btnLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLonMaxActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(btnNhoMax)
                        .addGap(44, 44, 44)
                        .addComponent(lbTrang)
                        .addGap(36, 36, 36)
                        .addComponent(btnLonMax)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhoMax)
                    .addComponent(btnLonMax)
                    .addComponent(lbTrang))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        Date date2 = new Date();
        Date date = new java.sql.Date(date2.getTime());
        if (ValidateEx.checkIsNull(txthoVaTen) || ValidateEx.checkIsNull(txthoVaTen)) {
            MsgBox.alert(this, "Tên khách hàng và sđt bắt buộc phải nhập");
            return;
        }
        KhachHangEntity khachHang = new KhachHangEntity();
        khachHang.setHoTen(txthoVaTen.getText());
        khachHang.setSoDienThoai(txtsdt.getText());
        khachHang.setId(Integer.parseInt(txtIdKhachHang.getText()));
        khachHang.setNgaySua(date);

        KhachHangEntity objTmp = khachHangService.updateKhachHang(khachHang);
        if (objTmp != null) {
            MsgBox.alert(this, "Sửa khách hàng thành công !");
        }else{
            MsgBox.alert(this, "Sửa mới khách hàng thất bại !");
            return;
        }


        fillTableKhachHang();
        clearFrom();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        if (ValidateEx.checkIsNull(txthoVaTen) || ValidateEx.checkIsNull(txtsdt)) {
            MsgBox.alert(this, "Tên khách hàng và sđt bắt buộc phải nhập");
            return;
        }
        String regex = "^\\d{10}$";
        if (!txtsdt.getText().matches(regex)) {
            MsgBox.alert(this, "SĐt không hợp lệ");
            return;
        }
        KhachHangEntity khachHang = new KhachHangEntity();
        khachHang.setHoTen(txthoVaTen.getText());
        khachHang.setSoDienThoai(txtsdt.getText());
        KhachHangEntity objTmp = khachHangService.save(khachHang);
        if (objTmp != null) {
            MsgBox.alert(this, "Thêm khách hàng thất bại!");
           return;
        }else{
            MsgBox.alert(this, "Thêm mới khách thành công !");
        }
        
        
        fillTableKhachHang();
        clearFrom();
    }//GEN-LAST:event_btnthemActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        int index = tblKhachHang.getSelectedRow();
        if (index < 0) {
            return;
        }
        txtIdKhachHang.setText(tblKhachHang.getValueAt(index, 0).toString());
        txthoVaTen.setText(tblKhachHang.getValueAt(index, 1).toString());
        txtsdt.setText(tblKhachHang.getValueAt(index, 2).toString());
//        txtngayTao.setText((String) tblKhachHang.getValueAt(index, 3));
        
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btncleanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncleanMouseClicked
        // TODO add your handling code here:
        clearFrom();
        
    }//GEN-LAST:event_btncleanMouseClicked

    private void txtLocKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocKhachHangKeyReleased
        // TODO add your handling code here:
        fillTableWhenSearch();
    }//GEN-LAST:event_txtLocKhachHangKeyReleased

    private void btnLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLonMaxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnLonMaxActionPerformed

    private void btnLonMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLonMaxMouseClicked
        // TODO add your handling code here:
        if (currentPage < (int) Math.ceil((double) listKhachHang.size() / pageSize - 1)) {
            currentPage++;
            fillTableKhachHang();
        }
    }//GEN-LAST:event_btnLonMaxMouseClicked

    private void btnNhoMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhoMaxMouseClicked
        // TODO add your handling code here:
        if (currentPage > 0) {
            currentPage--;
            fillTableKhachHang();
        }
    }//GEN-LAST:event_btnNhoMaxMouseClicked

    private void BtnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTaoHoaDonActionPerformed
       if(txtIdKhachHang.getText().equals("")){
            MsgBox.alert(this, "Chưa Có Khách Hàng!");
            return;
       }
       InsertHoaDon in = new InsertHoaDon(txtIdKhachHang.getText(),txthoVaTen.getText());
       in.setVisible(true);
    }//GEN-LAST:event_BtnTaoHoaDonActionPerformed

    private void txtIdKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdKhachHangActionPerformed
       
    }//GEN-LAST:event_txtIdKhachHangActionPerformed

    private void btncleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncleanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncleanActionPerformed

    public void clearFrom(){
        idKhachHang = -1;
        txthoVaTen.setText("");
        txtsdt.setText("");
        txtIdKhachHang.setText("");
//        txtngayTao.setText("");
        
    }
    
    private void ButtonState(){
        int pageCout = (int) Math.ceil((double) listKhachHang.size() / pageSize);
        
        btnNhoMax.setEnabled(currentPage > 0);
        btnLonMax.setEnabled(currentPage < pageCout - 1);
        lbTrang.setText("Page: " + (currentPage + 1));
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnTaoHoaDon;
    private javax.swing.JButton btnLonMax;
    private javax.swing.JButton btnNhoMax;
    private javax.swing.JButton btnclean;
    private javax.swing.JButton btnnhapExcel;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxuatExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtIdKhachHang;
    private javax.swing.JTextField txtLocKhachHang;
    private javax.swing.JTextField txthoVaTen;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
