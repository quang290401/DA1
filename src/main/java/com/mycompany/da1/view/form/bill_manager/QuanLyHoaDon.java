package com.mycompany.da1.view.form.bill_manager;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.repository.QuanLyHoaDonDao;
import com.mycompany.da1.service.IMPL.HoaDonIMPL;
import com.mycompany.da1.util.Contants;
import com.mycompany.da1.util.PhanTrang;
import com.mycompany.da1.util.XDate;
import com.mycompany.da1.view.ToanCucStatic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author sonst
 */
public class QuanLyHoaDon extends javax.swing.JPanel {

    private QuanLyHoaDonDao quanLyHoaDonDao = new QuanLyHoaDonDao();
    private HoaDonIMPL hoaDonIMPL = new HoaDonIMPL();

    private PhanTrang<HoaDonEntity> phanTranglocal;
    private List<HoaDonChiTietEntity> lstHdct = new ArrayList<>();
    private List<HoaDonEntity> lstHd = new ArrayList<>();

    public QuanLyHoaDon() {
        initComponents();
        lstHdct = new ArrayList<>();
        phanTranglocal = new PhanTrang<>(lstHd);
        setUp();

    }

    private void setUp() {
        fillCboTrangThai();
//        lstHdct = quanLyHoaDonDao.GetList();
        lstHd = quanLyHoaDonDao.getListHd(cboLocTrangThai.getSelectedIndex());
        phanTranglocal.refreshList(lstHd);

        Date currentDate = new Date();
        // Tạo một đối tượng Calendar và thiết lập ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        // Trừ đi một tháng từ ngày hiện tại
        calendar.add(Calendar.MONTH, -1);
        Date oneMonthAgo = calendar.getTime();
        txtNgayBatDau.setDate(currentDate);
        txtNgayKetThuc.setDate(oneMonthAgo);

        //
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }

    public void fillCboTrangThai() {
        DefaultComboBoxModel comboBoxModel;
        comboBoxModel = (DefaultComboBoxModel) cboLocTrangThai.getModel();
        cboLocTrangThai.removeAllItems();
        comboBoxModel.removeAllElements();
        // -----------
        DanhMucEntity danhMuc = new DanhMucEntity();

        danhMuc.setTenDanhMuc("--Tất cả--");
        danhMuc.setTrangThai(3);
        comboBoxModel.addElement(danhMuc);
        // -----------
        DanhMucEntity danhMuc1 = new DanhMucEntity();
        danhMuc1.setTenDanhMuc("Thành công");
        danhMuc1.setTrangThai(1);
        comboBoxModel.addElement(danhMuc1);
        // -----------
        DanhMucEntity danhMuc2 = new DanhMucEntity();
        danhMuc2.setTenDanhMuc("Chờ");
        danhMuc2.setTrangThai(0);
        comboBoxModel.addElement(danhMuc2);
        // -----------
        DanhMucEntity danhMuc3 = new DanhMucEntity();
        danhMuc3.setTenDanhMuc("Hủy");
        danhMuc3.setTrangThai(2);
        comboBoxModel.addElement(danhMuc3);
    }

    private void fillTableWhenSearch(int page) {
        String textSearch = txtSearch.getText();
        Date ngayBd = txtNgayBatDau.getDate();
        Date ngayKt = txtNgayKetThuc.getDate();
        DanhMucEntity danhMucTmp = ((DanhMucEntity) cboLocTrangThai.getSelectedItem());
        int trangThai = danhMucTmp.getTrangThai();

//        this.lstHdct = quanLyHoaDonDao.getListSeach(textSearch, ngayBd, ngayKt, trangThai);
        this.lstHd = quanLyHoaDonDao.getListHd(trangThai);
        phanTranglocal.refreshList(this.lstHd);
        DefaultTableModel def = (DefaultTableModel) tblHoaDon.getModel();
        def.setRowCount(0);

        for (HoaDonEntity item : phanTranglocal.getListData(page)) {
            String SDT = item.getKhachHangEntity() != null ? item.getKhachHangEntity().getSoDienThoai() : "Vãng lai";
            String tenKhach = item.getKhachHangEntity() != null ? item.getKhachHangEntity().getHoTen() : "Vãng lai";
             Serializable tongTien = item.getTongTien() != null ? item.getTongTien() : "Hủy";
            Object[] rowData = {
                    item.getId(),
                    item.getTaiKhoanEntity().getTaiKhoan(), SDT,
                    tenKhach,
                    tongTien,
                    XDate.toString(item.getNgayTao()),

            };
            def.addRow(rowData);
        }
        phanTranglocal.setButtonStatus();
        ButtonState();
        System.out.println("====================Table=====================");
        System.out.println(phanTranglocal.toString());
        System.out.println("====================TongTrang=====================");
        System.out.println(phanTranglocal.getTongTrang());
    }

    private void ButtonState() {
        btNhoMax.setEnabled(phanTranglocal.getIsPrev());
        btLonMax1.setEnabled(phanTranglocal.getIsNext());
        lbTrang.setText("Page: " + phanTranglocal.getPage());
    }

    private void fillTableHdct(int index) {
        DefaultTableModel def = (DefaultTableModel) tblHoaDonCT.getModel();
        def.setRowCount(0);
        int stt = 1;
//        HoaDonChiTietEntity item = lstHdct.get(index);
        System.out.println(quanLyHoaDonDao.getListHdct(index));
        for (HoaDonChiTietEntity item : quanLyHoaDonDao.getListHdct(index)) {
            System.out.println("====================================================");
            System.out.println(item.toString());
            Object[] rowData = {
                    stt,
                    item.getHoaDonEntity().getId(),
                    item.getHoaDonEntity().getTaiKhoanEntity() == null ? "" : item.getHoaDonEntity().getTaiKhoanEntity().getTaiKhoan(),
                    item.getHoaDonEntity().getKhachHangEntity() == null ? "" : item.getHoaDonEntity().getKhachHangEntity().getHoTen(),
                    item.getHoaDonEntity().getKhachHangEntity() == null ? "" : item.getHoaDonEntity().getKhachHangEntity().getSoDienThoai(),
                    item.getNgayTao(),
                    item.getSanPhamChiTietEntity() == null ? "" : item.getSanPhamChiTietEntity().getMaSanPhamCt(),
                    item.getSanPhamChiTietEntity() == null ? "" : item.getSanPhamChiTietEntity().getSanPhamEntity().getTenSanPham(),
                    item.getSoLuong(),
                    item.getTongTien()
            };
            def.addRow(rowData);
            stt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonCT = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThongKe = new javax.swing.JButton();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        cboLocTrangThai = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btLonMax1 = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btNhoMax = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1157, 600));

        tblHoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null}
                },
                new String[]{
                        "STT", "ID hóa đơn", "Mã NV", "Tên KH", "Sdt KH", "Ngày tạo", "Mã SP", "Tên SP", "Số lượng", "Đơn giá"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblHoaDonCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonCT);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String[]{
                        "ID", "Mã Nhân Viên", "SDT Khách", "Tên Khách", "Tổng Tiền ", "Ngày Tạo"
                }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Bảng hóa đơn:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Bảng hóa đơn chi tiết:");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel2.setText("Tên nhân viên hoặc khách hàng ");

        jLabel5.setText("Đến Ngày");

        jLabel6.setText("Từ Ngày");

        btnThongKe.setText("Thống Kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        txtNgayBatDau.setToolTipText("");

        cboLocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocTrangThaiActionPerformed(evt);
            }
        });

        jLabel15.setText("Trạng thái");

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        btLonMax1.setText(">>");
        btLonMax1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLonMax1ActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel4");

        btNhoMax.setText("<<");
        btNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhoMaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(440, 440, 440)
                                .addComponent(btNhoMax)
                                .addGap(18, 18, 18)
                                .addComponent(lbTrang)
                                .addGap(18, 18, 18)
                                .addComponent(btLonMax1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btNhoMax)
                                        .addComponent(lbTrang)
                                        .addComponent(btLonMax1))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtSearch)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                                .addGap(62, 62, 62)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel15)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(88, 88, 88)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(29, 29, 29)
                                                                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(32, 32, 32))
                                        .addComponent(jScrollPane1)
                                        .addComponent(jScrollPane3)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{cboLocTrangThai, txtNgayBatDau, txtNgayKetThuc});

        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel15))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnThongKe)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[]{cboLocTrangThai, txtNgayBatDau, txtNgayKetThuc, txtSearch});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1147, Short.MAX_VALUE)
                                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonCTMouseClicked

    }//GEN-LAST:event_tblHoaDonCTMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        if (index < 0) {
            return;
        }
        int id = Integer.valueOf(tblHoaDon.getValueAt(index, 0).toString());
        fillTableHdct(id);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        Date GetDate = txtNgayBatDau.getDate();
        Date EndDate = txtNgayKetThuc.getDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp dateGet = Timestamp.valueOf(dateFormat.format(GetDate));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp dateEnd = Timestamp.valueOf(dateFormat2.format(EndDate));

        BigDecimal thongKe = hoaDonIMPL.tongTienTuNgayDenNgay(dateGet,dateEnd);
        ToanCucStatic tt = new ToanCucStatic();
        tt.setThongKe(thongKe);
        ThongKe thongKe1 = new ThongKe();
        thongKe1.setVisible(true);
        System.out.println(thongKe);
        System.out.println(dateGet);
        System.out.println(dateEnd);


    }//GEN-LAST:event_btnThongKeActionPerformed

    private void cboLocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocTrangThaiActionPerformed
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_cboLocTrangThaiActionPerformed


    private void btLonMax1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonMax1ActionPerformed
        if (phanTranglocal.getIsNext()) {
            fillTableWhenSearch(phanTranglocal.getPage() + 1);
        }
    }//GEN-LAST:event_btLonMax1ActionPerformed

    private void btNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoMaxActionPerformed
        if (phanTranglocal.getIsPrev()) {
            fillTableWhenSearch(phanTranglocal.getPage() - 1);
        }
    }//GEN-LAST:event_btNhoMaxActionPerformed

    private void SerchTableHD(int page) {
        String tenKhach = txtSearch.getText();
        this.lstHd = quanLyHoaDonDao.findHoaDonByTenKhachHang(tenKhach);
        phanTranglocal.refreshList(this.lstHd);
        DefaultTableModel def = (DefaultTableModel) tblHoaDon.getModel();
        def.setRowCount(0);

        for (HoaDonEntity item : phanTranglocal.getListData(page)) {
            String SDT = item.getKhachHangEntity() != null ? item.getKhachHangEntity().getSoDienThoai() : "Vãng lai";
            String tenKhach2 = item.getKhachHangEntity() != null ? item.getKhachHangEntity().getHoTen() : "Vãng lai";
            Object[] rowData = {
                    item.getId(),
                    item.getTaiKhoanEntity().getTaiKhoan(), SDT,
                    tenKhach2,
                    item.getTongTien(),
                    XDate.toString(item.getNgayTao()),

            };
            def.addRow(rowData);
        }
        phanTranglocal.setButtonStatus();
        ButtonState();
    }

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        SerchTableHD(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLonMax1;
    private javax.swing.JButton btNhoMax;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cboLocTrangThai;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCT;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
