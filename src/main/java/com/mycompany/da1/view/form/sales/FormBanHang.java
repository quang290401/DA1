package com.mycompany.da1.view.form.sales;

import com.mycompany.da1.combobox.VoucherCBB;
import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.service.IMPL.HoaDonChiTietIMPL;
import com.mycompany.da1.service.IMPL.HoaDonIMPL;
import com.mycompany.da1.service.IMPL.KhachHangIMPL;
import com.mycompany.da1.service.IMPL.QuanLyBanHangImpl;
import com.mycompany.da1.service.IMPL.SanPhamChiTietIMPL;
import com.mycompany.da1.service.IMPL.VoucherServiceIMPL;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.ValidateEx;
import com.mycompany.da1.util.XFile;
import com.mycompany.da1.view.ToanCucStatic;
import com.mycompany.da1.view.events.EventDialogListener;
import com.mycompany.da1.view.excle.ExcelExporter;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

public class FormBanHang extends javax.swing.JPanel {

    private QuanLyBanHangImpl qlbhi = new QuanLyBanHangImpl();

    SanPhamChiTietIMPL sanPhamChiTietIMPL = new SanPhamChiTietIMPL();
    HoaDonIMPL hoaDonIMPL = new HoaDonIMPL();
    HoaDonChiTietIMPL hoaDonChiTietIMPL = new HoaDonChiTietIMPL();
    VoucherServiceIMPL voucherServiceIMPL = new VoucherServiceIMPL();
    KhachHangIMPL khachHangIMPL = new KhachHangIMPL();
    String imageUrl, imageName;

    public FormBanHang() {
        initComponents();
        LoadTableCTSanPham();
        LoadTableHoaDonCho();
        loadDanhMucVouCher();

    }

    private void loadDanhMucVouCher() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbVouCher.getModel();
        // xoa du lieu cu
        model.removeAllElements();
        // đỗ dưc liệu mới vào
        List<VoucherEntity> voucherEntitys = voucherServiceIMPL.GetAll();
        try {
            for (VoucherEntity p : voucherEntitys) {
                int id = p.getId();
                String tenPhanTramGiam = p.getTenKhuyenMai();
                VoucherCBB voucherCBB = new VoucherCBB(id, tenPhanTramGiam);
                model.addElement(voucherCBB);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillTableWhenSearch() {
        String textSearch = txtLocSanPham.getText().trim();
        DefaultTableModel dtm = (DefaultTableModel) TableSanPhamCT.getModel();
        List<SanPhamChiTietEntity> sanPhams = qlbhi.getByTxt(textSearch);
        dtm.setRowCount(0);
        for (SanPhamChiTietEntity p : sanPhams) {
            if (p.getSoLuong() == 0) {
                sanPhamChiTietIMPL.UpdateTrangThaiToZero(p.getId());
            }
            Object[] row = {p.getId(), p.getSanPhamEntity().getTenSanPham(), p.getGiaSanPham(),
                p.getSoLuong(), p.getKichCoEntity().
                getTenKichCo(), p.getMauSacEntity().getTenMauSac(),
                p.getAnhSanPham()};
            dtm.addRow(row);
        }

    }

    public void LoadTableCTSanPham() {
        TableSwing dtm = new TableSwing(new Object[]{"ID", "Tên sản phẩm", "Giá", "Số lượng", "Kích cỡ", "Màu sắc", "Ảnh"}, 0);
        TableSanPhamCT.setModel(dtm);
        ArrayList<SanPhamChiTietEntity> sanPhams = sanPhamChiTietIMPL.GetAllSPFormBanHang();
        dtm.setRowCount(0);
        for (SanPhamChiTietEntity p : sanPhams) {
            if (p.getSoLuong() <= 0) {
                sanPhamChiTietIMPL.UpdateTrangThaiToZero(p.getId());
            }
            Object[] row = {p.getId(), p.getSanPhamEntity().getTenSanPham(), p.getGiaSanPham(),
                p.getSoLuong(), p.getKichCoEntity().getTenKichCo(), p.getMauSacEntity().getTenMauSac(),
                p.getAnhSanPham()};
            dtm.addRow(row);
        }

    }

    public void LoadTableHoaDonCho() {
        TableSwing dtm = new TableSwing(new Object[]{"ID", "Ngày tạo", "Người tạo", "Trạng thái", "Khách hàng", "Giảm giá"}, 0);
        tblHoaDon.setModel(dtm);

        ArrayList<HoaDonEntity> hoaDonEntitys = hoaDonIMPL.GetAll();
        for (HoaDonEntity p : hoaDonEntitys) {
            String trangThai = p.getTrangThai() == 0 ? "Chưa thanh toán" : "Đã thanh toán";
            String hoTen = p.getKhachHangEntity() == null ? "Vãng lai" : p.getKhachHangEntity().getHoTen();
            Object[] row = {p.getId(), p.getNgayTao(), p.getTaiKhoanEntity().getHoTen(), trangThai,
                hoTen, p.getVoucherEntity().getPhanTramGiam()
            };
            dtm.addRow(row);
        }
    }

    public void LoadTableHoaDonCT(int idHoadon) {
        idHoadon = Integer.parseInt(txtHoaDon.getText());
        TableSwing dtm = new TableSwing(new Object[]{"Tên sản phẩm", "Số lượng", "Giá", "Tổng tiền", "ID"}, 0);
        tbnHoaDonCT.setModel(dtm);

        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntitys = hoaDonChiTietIMPL.GetAll(idHoadon);
        for (HoaDonChiTietEntity p : hoaDonChiTietEntitys) {
            Object[] row = {p.getSanPhamChiTietEntity().getSanPhamEntity().getTenSanPham(),
                p.getSoLuong(), p.getSanPhamChiTietEntity().getGiaSanPham(),
                p.getTongTien(), p.getSanPhamChiTietEntity().getId()
            };
            dtm.addRow(row);
        }
    }

    private void refreshTongTien() {

        BigDecimal bigDecimal = hoaDonChiTietIMPL.SumtongTien(Integer.parseInt(txtHoaDon.getText()));
        ArrayList<HoaDonEntity> hoaDonEntities = hoaDonIMPL.ThanhToan(Integer.parseInt(txtHoaDon.getText()));
        for (HoaDonEntity p : hoaDonEntities) {
            if (p.getKhachHangEntity() == null) {
                LableTenKhach.setText("Vãng lai");
                lableSDTKhach.setText("Vãng lai");
            } else {
                LableTenKhach.setText(p.getKhachHangEntity().getHoTen() != null ? p.getKhachHangEntity().getHoTen() : "Vãng lai");
                lableSDTKhach.setText(p.getKhachHangEntity().getSoDienThoai() != null ? p.getKhachHangEntity().getSoDienThoai() : "Vãng lai");
            }

            lableTenNhanVien.setText(p.getTaiKhoanEntity().getHoTen());
            BigDecimal discountPercentage = new BigDecimal(p.getVoucherEntity().getPhanTramGiam());
            BigDecimal discountAmount = bigDecimal.multiply(discountPercentage.divide(BigDecimal.valueOf(100)));
            BigDecimal discountedTotal = bigDecimal.subtract(discountAmount);
            txtTongTien.setText(discountedTotal.toString());
        }

    }

    /**
     * Creates new form AA
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        BtnThemHDCT = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableSanPhamCT = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtHoaDon = new javax.swing.JLabel();
        TXTidSanPham = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JLabel();
        anhChiTiet = new javax.swing.JLabel();
        txtLocSanPham = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnTaoHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtTenKhach = new javax.swing.JTextField();
        cbbVouCher = new javax.swing.JComboBox<>();
        txtSDTKhach = new javax.swing.JTextField();
        btnThemHoaDon = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbnHoaDonCT = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtTienKhach = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        LableTenKhach = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lableSDTKhach = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDsMua = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        lableTongTien = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lableTenNhanVien = new javax.swing.JLabel();
        lableTongTienSaugiam = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        BtnexportEcle = new javax.swing.JToggleButton();

        setPreferredSize(new java.awt.Dimension(1200, 630));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BtnThemHDCT.setBackground(new java.awt.Color(255, 255, 51));
        BtnThemHDCT.setText("Thêm Sản Phẩm");
        BtnThemHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnThemHDCTActionPerformed(evt);
            }
        });

        TableSanPhamCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id SanPham", "Tên Sản phẩm", "Giá Sản phẩm", "Số Lương", "Size", "Màu Sắc", "Hình Ảnh"
            }
        ));
        TableSanPhamCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableSanPhamCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableSanPhamCT);

        jLabel4.setText("Id Hóa Đơn");

        jLabel6.setText("Số Lượng");

        jLabel5.setText("Id Sản Phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\JavaCode\\DA1\\src\\main\\java\\com\\mycompany\\da1\\Icon\\product.png")); // NOI18N
        jLabel3.setText("Danh sách sản phẩm");

        jLabel18.setText("Giá bán");

        txtHoaDon.setText("#Here");

        TXTidSanPham.setText("#Here");

        txtGiaBan.setText("#Here");

        anhChiTiet.setBackground(new java.awt.Color(255, 0, 0));
        anhChiTiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtLocSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocSanPhamActionPerformed(evt);
            }
        });
        txtLocSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocSanPhamKeyReleased(evt);
            }
        });

        jLabel8.setText("Nhập tên sản phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnThemHDCT)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel8)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TXTidSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txtLocSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(13, 13, 13))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtHoaDon)
                            .addComponent(jLabel18)
                            .addComponent(txtGiaBan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(TXTidSanPham))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLocSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnThemHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTaoHoaDon.setBackground(new java.awt.Color(255, 255, 51));
        btnTaoHoaDon.setText("Vãng Lai");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Hóa Đơn", "Ngày tạo", "Tên Nhân Viên", "Trạng thái", "Tên khách hàng", "Phần trăm giảm"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\JavaCode\\DA1\\src\\main\\java\\com\\mycompany\\da1\\Icon\\bill.png")); // NOI18N
        jLabel2.setText("Hóa đơn chờ");

        btnReset.setBackground(new java.awt.Color(255, 255, 0));
        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyHoaDon.setText("Hủy Hóa Đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        jLabel1.setText("Họ Tên Khách");

        jLabel10.setText("Số Điện Thoại");

        jLabel23.setText("VouCher");

        cbbVouCher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThemHoaDon.setBackground(new java.awt.Color(255, 255, 0));
        btnThemHoaDon.setText("Tạo Hóa Đơn");
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnTaoHoaDon)
                                .addGap(44, 44, 44)
                                .addComponent(btnReset)
                                .addGap(46, 46, 46)
                                .addComponent(btnHuyHoaDon)
                                .addGap(35, 35, 35)
                                .addComponent(btnThemHoaDon)
                                .addGap(0, 66, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSDTKhach)
                                .addGap(18, 18, 18)
                                .addComponent(cbbVouCher, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSDTKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbVouCher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThanhToan.setBackground(new java.awt.Color(255, 255, 51));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon("D:\\JavaCode\\DA1\\src\\main\\java\\com\\mycompany\\da1\\Icon\\cart.png")); // NOI18N
        jLabel7.setText("Giỏ Hàng");
        jLabel7.setFocusable(false);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tbnHoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Số Lượng", "Giá Sản Phẩm", "Thành Tiền", "IDSan Phẩm"
            }
        ));
        tbnHoaDonCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnHoaDonCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbnHoaDonCT);

        btnXoaSP.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaSP.setText("Xóa Sản Phẩm");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        jLabel14.setText("Tiền Khách Đưa");

        txtTienKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachActionPerformed(evt);
            }
        });
        txtTienKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("-");

        txtTongTien.setEditable(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });
        txtTongTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongTienKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("=");

        txtTienThua.setEditable(false);
        txtTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThuaActionPerformed(evt);
            }
        });
        txtTienThua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienThuaKeyReleased(evt);
            }
        });

        jLabel19.setText("Tổng tiền");

        jLabel20.setText("Tiền thừa");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnThanhToan)
                        .addGap(51, 51, 51)
                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel14)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel19)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(txtTienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSP)
                    .addComponent(btnThanhToan))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThanhToan, btnXoaSP});

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\JavaCode\\DA1\\src\\main\\java\\com\\mycompany\\da1\\Icon\\employee.png")); // NOI18N
        jLabel9.setText("Hóa Đơn Của Khách");

        jLabel11.setText("Tên Khách Hàng :");

        LableTenKhach.setText("#Here");

        jLabel13.setText("Số Điện Thoại");

        lableSDTKhach.setText("#Here");

        jLabel15.setText("Danh Sách Sản Phẩm :");

        tblDsMua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Giá Sản Phẩm", "Số Lượng"
            }
        ));
        jScrollPane5.setViewportView(tblDsMua);

        jLabel16.setText("Tổng Tiền : ");

        lableTongTien.setText("#here");

        jLabel12.setText("Tên Nhân Viên:");

        lableTenNhanVien.setText("#here");

        lableTongTienSaugiam.setText("#Here");

        jLabel17.setText("Tổng Tiền Sau Giảm:");

        BtnexportEcle.setBackground(new java.awt.Color(255, 255, 51));
        BtnexportEcle.setIcon(new javax.swing.ImageIcon("D:\\JavaCode\\DA1\\src\\main\\java\\com\\mycompany\\da1\\Icon\\iconExcel.png")); // NOI18N
        BtnexportEcle.setText("In Hóa Đơn");
        BtnexportEcle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnexportEcleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lableTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(lableTongTienSaugiam, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(LableTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel15))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lableTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lableSDTKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(19, 19, 19)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnexportEcle, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LableTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(lableSDTKhach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableTenNhanVien)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(BtnexportEcle, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lableTongTien)
                    .addComponent(jLabel17)
                    .addComponent(lableTongTienSaugiam))
                .addGap(577, 577, 577))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnThemHDCTActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtSoLuong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "bạn không được để trống Số Lượng");
            return;
        } else if (ValidateEx.checkIsNumber(txtSoLuong)) {
            JOptionPane.showMessageDialog(null, "Số Lượng Phải Là Kiểu Số ");
            return;
        } else if (txtHoaDon.getText().equalsIgnoreCase("#Here")) {
            JOptionPane.showMessageDialog(null, "Chưa Có Hơn Chờ");
            return;
        } else if (TXTidSanPham.getText().equalsIgnoreCase("#Here")) {
            JOptionPane.showMessageDialog(null, "Hãy Chọn Sản Phẩm để Mua");
            return;
        } else if (txtHoaDon.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Chưa có Hóa Đơn Mua Hàng");
        } else if (txtHoaDon.getText().equals("#Here")) {
            JOptionPane.showMessageDialog(null, "Chưa có Hóa Đơn Mua Hàng");
        }

        if (Integer.parseInt(txtSoLuong.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(null, "Hãy Nhập số Lượng Mua");
            return;
        }

        int soLuongSP = sanPhamChiTietIMPL.getSoLuongById(Integer.parseInt(TXTidSanPham.getText()));
        if (Integer.parseInt(txtSoLuong.getText().trim()) > soLuongSP) {
            JOptionPane.showMessageDialog(null, "Mua Vượt Số Lượng Sản Phẩm");
            return;

        }
        HoaDonChiTietEntity hoaDonChiTietEntity = new HoaDonChiTietEntity();
        HoaDonEntity hoaDonEntity = new HoaDonEntity();
        SanPhamChiTietEntity sanPhamChiTietEntity = new SanPhamChiTietEntity();
        hoaDonEntity.setId(Integer.parseInt(txtHoaDon.getText()));
        sanPhamChiTietEntity.setId(Integer.parseInt(TXTidSanPham.getText()));
        hoaDonChiTietEntity.setHoaDonEntity(hoaDonEntity);
        hoaDonChiTietEntity.setSanPhamChiTietEntity(sanPhamChiTietEntity);
        hoaDonChiTietEntity.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
        BigDecimal Sl = new BigDecimal(txtSoLuong.getText().trim());
        BigDecimal GiaBan = new BigDecimal(txtGiaBan.getText());
        BigDecimal tongTien = Sl.multiply(GiaBan);
        BigDecimal newTongTien = GiaBan.multiply(Sl);
        hoaDonChiTietEntity.setTongTien(tongTien);
        hoaDonChiTietIMPL.Save(hoaDonChiTietEntity, Integer.parseInt(txtSoLuong.getText().trim()), newTongTien);
        sanPhamChiTietIMPL.UpdateSoLuongSP(Integer.valueOf(txtSoLuong.getText().trim()), Integer.valueOf(TXTidSanPham.getText()));
        int bb = hoaDonChiTietIMPL.traVeSoluongSP(Integer.parseInt(txtHoaDon.getText()), Integer.parseInt(TXTidSanPham.getText()));
        System.out.println(bb);
        BigDecimal soLuong = new BigDecimal(bb);
        BigDecimal updateTongTien = GiaBan.multiply(soLuong);
        hoaDonChiTietIMPL.UpdateTongTien(Integer.parseInt(txtHoaDon.getText()), Integer.parseInt(TXTidSanPham.getText()),
                updateTongTien);

        LoadTableHoaDonCT(Integer.parseInt(txtHoaDon.getText()));
        LoadTableCTSanPham();
        refreshTongTien();

    }

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtHoaDon.getText().equals("0") || txtHoaDon.getText().equals("#Here")) {
            JOptionPane.showMessageDialog(null, "Chưa có Hóa Đơn Thanh Toán");
            return;
        }
        if (txtTienKhach.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Phải Đưa Tiền Trước");
            return;
        }
        double tienKhach = Double.parseDouble(txtTienKhach.getText());
        double tongTien = Double.parseDouble(txtTongTien.getText());
        if (tienKhach < tongTien) {
            JOptionPane.showMessageDialog(null, "Số tiền Thanh toán phải lớn hơn");
            return;
        }
        int soLuongHdCt = hoaDonChiTietIMPL.DeSoLuongHoaDonChiTiet(Integer.parseInt(txtHoaDon.getText()));
        if (soLuongHdCt == 0) {
            JOptionPane.showMessageDialog(null, "Hãy Mua Hàng");
            return;
        }
        if (ValidateEx.checkIsFloat(txtTienKhach)) {
            MsgBox.alert(this, "Số tiền phải là Kiểu Số");
            txtTienKhach.setText("");
            return;
        }

        if (ValidateEx.checkMoreThan(txtTienKhach, 0)) {
            MsgBox.alert(this, "Số tiền phải lớn hơn 0");
            txtTienKhach.setText("");
            return;
        }
        ToanCucStatic tt = new ToanCucStatic();
        tt.setIdHoaDon(Integer.parseInt(txtHoaDon.getText()));

        BigDecimal bigDecimal = hoaDonChiTietIMPL.SumtongTien(Integer.parseInt(txtHoaDon.getText()));
        lableTongTien.setText(String.valueOf(bigDecimal));
        ArrayList<HoaDonEntity> hoaDonEntities = hoaDonIMPL.ThanhToan(Integer.parseInt(txtHoaDon.getText()));
        for (HoaDonEntity p : hoaDonEntities) {
            if (p.getKhachHangEntity() == null || p.getKhachHangEntity().getHoTen() == null) {
                LableTenKhach.setText("Vãng Lai");
                lableSDTKhach.setText("Vãng Lai");
            } else {
                LableTenKhach.setText(p.getKhachHangEntity().getHoTen());
                lableSDTKhach.setText(p.getKhachHangEntity().getSoDienThoai());
            }

            lableTenNhanVien.setText(p.getTaiKhoanEntity().getHoTen());
            BigDecimal discountPercentage = new BigDecimal(p.getVoucherEntity().getPhanTramGiam());
            BigDecimal discountAmount = bigDecimal.multiply(discountPercentage.divide(BigDecimal.valueOf(100)));
            BigDecimal discountedTotal = bigDecimal.subtract(discountAmount);
            lableTongTienSaugiam.setText(discountedTotal.toString());
        }
        BigDecimal tongTienHoaDon = new BigDecimal(lableTongTienSaugiam.getText());
        hoaDonIMPL.UpdateHoaDon(Integer.parseInt(txtHoaDon.getText()), tongTienHoaDon);
        JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công ");
        LoadTableCTSanPham();
        LoadTableHoaDonThanhToan();
        LoadTableHoaDonCho();
        LoadTableCTSanPham();
        LoadTableHoaDonCT(Integer.parseInt(txtHoaDon.getText()));
        txtHoaDon.setText("0");
        TXTidSanPham.setText("#Here");
        txtSoLuong.setText("");
        txtGiaBan.setText("#Here");
        txtTongTien.setText(lableTongTienSaugiam.getText());
        txtTongTien.setText("0");
        txtTienKhach.setText("0");
    }

    public void LoadTableHoaDonThanhToan() {
        String idHoaDon = txtHoaDon.getText();
        TableSwing dtm = new TableSwing(new Object[]{"Tên sản phẩm", "Giá", "Số lượng"}, 0);
        tblDsMua.setModel(dtm);

        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntitys = hoaDonChiTietIMPL.GetAll(Integer.parseInt(idHoaDon));
        for (HoaDonChiTietEntity p : hoaDonChiTietEntitys) {
            Object[] row = {p.getSanPhamChiTietEntity().getSanPhamEntity().getTenSanPham(),
                p.getSanPhamChiTietEntity().getGiaSanPham(), p.getSoLuong()
            };
            dtm.addRow(row);
        }
    }


    private void TableSanPhamCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSanPhamCTMouseClicked
        // TODO add your handling code here:  int row = this.tblSon.getSelectedRow();
        int row = this.TableSanPhamCT.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idSP = TableSanPhamCT.getValueAt(row, 0).toString();
        String giaBan = TableSanPhamCT.getValueAt(row, 2).toString();
        imageUrl = TableSanPhamCT.getValueAt(row, 6) == null ? "" : TableSanPhamCT.getValueAt(row, 6).toString();
        if (imageUrl != null) {
            showImage(XFile.getPath(imageUrl));
        } else {
            anhChiTiet.setIcon(null);
        }
        this.TXTidSanPham.setText(idSP);
        this.txtGiaBan.setText(giaBan);
    }//GEN-LAST:event_TableSanPhamCTMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        HoaDonEntity hoaDonEntity = new HoaDonEntity();
        KhachHangEntity khachHangEntity = new KhachHangEntity();
        VoucherEntity voucherEntity = new VoucherEntity();
        TaiKhoanEntity taiKhoanEntity = new TaiKhoanEntity();
        ToanCucStatic tt = new ToanCucStatic();
        int maVouCher;
        voucherEntity.setId(1);
        taiKhoanEntity.setId(tt.getIdNhanVien());
        hoaDonEntity.setVoucherEntity(voucherEntity);
        hoaDonEntity.setTaiKhoanEntity(taiKhoanEntity);
        hoaDonEntity.setTrangThai(0);
        hoaDonIMPL.Save(hoaDonEntity);
        JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công.");
        LoadTableHoaDonCho();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();
    }//GEN-LAST:event_btnResetActionPerformed
    private void reset() {
        txtHoaDon.setText("0");
        TXTidSanPham.setText("#Here");
        txtSoLuong.setText("");
        txtGiaBan.setText("#Here");
        LableTenKhach.setText("#Here");
        lableSDTKhach.setText("#Hear");
        lableTongTien.setText("#Here");
        lableTenNhanVien.setText("#Here");
        lableTongTienSaugiam.setText("#Here");
        txtTienKhach.setText("");
        txtTienThua.setText("");
        txtTongTien.setText("0");
        txtTenKhach.setText("");
        txtSDTKhach.setText("");
        LoadTableCTSanPham();
        LoadTableHoaDonCT(Integer.parseInt(txtHoaDon.getText()));
        LoadTableHoaDonCho();
        LoadTableHoaDonThanhToan();

    }
    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:  int row = this.tblSon.getSelectedRow();
        int row = this.tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idHoaDon = tblHoaDon.getValueAt(row, 0).toString();
        this.txtHoaDon.setText(idHoaDon);
        LoadTableHoaDonCT(Integer.parseInt(txtHoaDon.getText()));
        refreshTongTien();
        LoadTableHoaDonThanhToan();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    public void showImage(String imageUrl) {
        if (imageUrl != null) {
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            Image image = imageIcon.getImage();
            Image newImg = image.getScaledInstance(anhChiTiet.getWidth(), anhChiTiet.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imgAfter = new ImageIcon(newImg);
            System.out.println(imageUrl);
            anhChiTiet.setIcon(imgAfter);
        } else {
            System.out.println("Không tìm thấy ảnh");
            anhChiTiet.setIcon(null);
        }
    }

    public String getImageUrl() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        fileChooser.setDialogTitle("Chọn ảnh");
        File img = fileChooser.getSelectedFile();
        System.out.println(img.getAbsoluteFile());

        XFile.save(img);
        imageName = img.getName();
        return XFile.getPath(img.getName());
    }

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        if (TXTidSanPham.getText().equals("#Here")) {
            JOptionPane.showMessageDialog(null, "Chọn Sản Phẩm để Xóa");
            return;
        } else if (txtHoaDon.getText().equals("#Here")) {
            JOptionPane.showMessageDialog(null, "Chọn Sản Phẩm để Xóa");
            return;
        } else if (txtHoaDon.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Chọn Sản Phẩm để Xóa");
            return;
        }
        int SoLuongCTSP = hoaDonChiTietIMPL.traVeSoluongSP(Integer.parseInt(txtHoaDon.getText()), Integer.parseInt(TXTidSanPham.getText()));
        System.out.println(SoLuongCTSP);
        sanPhamChiTietIMPL.UpdateSoLuongSPCT(SoLuongCTSP, Integer.parseInt(TXTidSanPham.getText()));
        sanPhamChiTietIMPL.UpdateTrangThaiToOne(Integer.parseInt(TXTidSanPham.getText()));
        hoaDonChiTietIMPL.DeleteHDCT(Integer.parseInt(txtHoaDon.getText()), Integer.parseInt(TXTidSanPham.getText()));
        LoadTableHoaDonCT(Integer.parseInt(txtHoaDon.getText()));
        LoadTableCTSanPham();
        refreshTongTien();

    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void txtLocSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocSanPhamActionPerformed

    private void txtLocSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocSanPhamKeyReleased
        fillTableWhenSearch();
    }//GEN-LAST:event_txtLocSanPhamKeyReleased

    private void txtTienKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachActionPerformed

    private void txtTienKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachKeyReleased

        if (ValidateEx.checkIsFloat(txtTienKhach)) {
            MsgBox.alert(this, "Số tiền phải là Kiểu Số");
            txtTienKhach.setText("");
        }

// Kiểm tra nếu giá trị không phải số âm
        if (txtTienKhach.getText().matches("^-?[0-9]*\\.?[0-9]+$")) {
            if (Float.parseFloat(txtTienKhach.getText()) <= 0) {
                MsgBox.alert(this, "Số tiền phải lớn hơn 0");
                txtTienKhach.setText("");
            }
        }

        BigDecimal tongTien = new BigDecimal(txtTongTien.getText());
        BigDecimal tienKhach = new BigDecimal(txtTienKhach.getText());
        BigDecimal tienThua = tienKhach.subtract(tongTien);

        txtTienThua.setText(tienThua + "");
    }//GEN-LAST:event_txtTienKhachKeyReleased

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void txtTongTienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongTienKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienKeyReleased

    private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThuaActionPerformed

    private void txtTienThuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienThuaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThuaKeyReleased

    private void tbnHoaDonCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnHoaDonCTMouseClicked
        int row = this.tbnHoaDonCT.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idSP = tbnHoaDonCT.getValueAt(row, 4).toString();
        String giaBan = tbnHoaDonCT.getValueAt(row, 2).toString();
        txtGiaBan.setText(giaBan);
        TXTidSanPham.setText(idSP);
    }//GEN-LAST:event_tbnHoaDonCTMouseClicked

    private void BtnexportEcleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnexportEcleActionPerformed
        String idHoaDon = txtHoaDon.getText();
        TableSwing dtm = new TableSwing(new Object[]{"Tên sản phẩm", "Giá", "Số lượng"}, 0);
        tblDsMua.setModel(dtm);
        ToanCucStatic tt = new ToanCucStatic();

        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntitys = hoaDonChiTietIMPL.GetAll(tt.getIdHoaDon());

        for (HoaDonChiTietEntity p : hoaDonChiTietEntitys) {

            Object[] row = {p.getSanPhamChiTietEntity().getSanPhamEntity().getTenSanPham(),
                p.getSanPhamChiTietEntity().getGiaSanPham(), p.getSoLuong()
            };
            dtm.addRow(row);

        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn thư mục lưu trữ");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          
            File selectedDirectory = fileChooser.getSelectedFile();
            String directoryPath = selectedDirectory.getAbsolutePath();
            String filePath = directoryPath + "\\HoaDon.xlsx"; // Đường dẫn của tệp Excel
            // Tiếp tục xử lý lưu trữ tệp Excel với đường dẫn của thư mục được chọn
            // Đảm bảo rằng bạn có dữ liệu để xuất ra tệp Excel trước khi thực hiện các bước dưới
            ExcelExporter.exportHoaDonChiTietToExcel(hoaDonChiTietEntitys, LableTenKhach.getText(), lableSDTKhach.getText(),
                    lableTongTien.getText(), lableTongTienSaugiam.getText(), filePath); // Xuất dữ liệu ra tệp Excel
            MsgBox.alert(this, "In Thành Công");
        }

    }//GEN-LAST:event_BtnexportEcleActionPerformed

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed
        // TODO add your handling code here:
        if (ValidateEx.checkIsNull(txtTenKhach) || ValidateEx.checkIsNull(txtSDTKhach)) {
            MsgBox.alert(this, "Tên khách hàng và sđt bắt buộc phải nhập");
            return;
        }
        String regex = "^\\d{10}$";
        if (!txtSDTKhach.getText().matches(regex)) {
            MsgBox.alert(this, "SĐt không hợp lệ");
            return;
        }
        Boolean kiemTraKhach = khachHangIMPL.KiemTraSDTKhach(txtSDTKhach.getText());
        if (kiemTraKhach == true) {
            MsgBox.alert(this, "Khách Hàng đã tồn tại");
            return;
        }

        KhachHangEntity khachHang = new KhachHangEntity();
        khachHang.setHoTen(txtTenKhach.getText());
        khachHang.setSoDienThoai(txtSDTKhach.getText());
        KhachHangEntity objTmp = khachHangIMPL.save(khachHang);
        int KhachMoiThem = khachHangIMPL.TraVeKhachMoiThem();
        KhachHangEntity khachHangEntity = new KhachHangEntity();
        TaiKhoanEntity taiKhoanEntity = new TaiKhoanEntity();
        khachHangEntity.setId(KhachMoiThem);
        ToanCucStatic tt = new ToanCucStatic();
        taiKhoanEntity.setId(tt.getIdNhanVien());
        HoaDonEntity hoaDonEntity = new HoaDonEntity();
        hoaDonEntity.setKhachHangEntity(khachHangEntity);
        hoaDonEntity.setTaiKhoanEntity(taiKhoanEntity);
        VoucherEntity voucherEntity = new VoucherEntity();
        int maVouCher;
        VoucherCBB voucherCBB = (VoucherCBB) cbbVouCher.getSelectedItem();
        maVouCher = voucherCBB.IdInt();
        voucherEntity.setId(maVouCher);
        hoaDonEntity.setVoucherEntity(voucherEntity);
        hoaDonIMPL.Save(hoaDonEntity);
        JOptionPane.showMessageDialog(null, "Thêm Mới Thành Công");
        LoadTableHoaDonCho();
        reset();
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtHoaDon.getText().equals("#Here")) {
            JOptionPane.showMessageDialog(null, "Chọn Hóa Đơn Để hủy");
            return;
        } else if (txtHoaDon.getText().equals("#0")) {
            JOptionPane.showMessageDialog(null, "Chọn Hóa Đơn Để hủy");
            return;
        }
        int soLuongHdCt = hoaDonChiTietIMPL.DeSoLuongHoaDonChiTiet(Integer.parseInt(txtHoaDon.getText()));
        if (soLuongHdCt > 0) {
            JOptionPane.showMessageDialog(null, "Hãy xóa sản phẩm khỏi giỏ hàng");
            return;
        }
        hoaDonIMPL.UpDateHuyHD(Integer.parseInt(txtHoaDon.getText()));
        JOptionPane.showMessageDialog(null, "Đã Hủy Hóa Đơn");
        LoadTableHoaDonCho();
        txtHoaDon.setText("#Here");
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnThemHDCT;
    private javax.swing.JToggleButton BtnexportEcle;
    private javax.swing.JLabel LableTenKhach;
    private javax.swing.JLabel TXTidSanPham;
    private javax.swing.JTable TableSanPhamCT;
    private javax.swing.JLabel anhChiTiet;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemHoaDon;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cbbVouCher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lableSDTKhach;
    private javax.swing.JLabel lableTenNhanVien;
    private javax.swing.JLabel lableTongTien;
    private javax.swing.JLabel lableTongTienSaugiam;
    private javax.swing.JTable tblDsMua;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tbnHoaDonCT;
    private javax.swing.JLabel txtGiaBan;
    private javax.swing.JLabel txtHoaDon;
    private javax.swing.JTextField txtLocSanPham;
    private javax.swing.JTextField txtSDTKhach;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtTienKhach;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
