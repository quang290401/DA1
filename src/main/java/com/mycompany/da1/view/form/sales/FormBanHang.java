package com.mycompany.da1.view.form.sales;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.service.IMPL.HoaDonChiTietIMPL;
import com.mycompany.da1.service.IMPL.HoaDonIMPL;
import com.mycompany.da1.service.IMPL.SanPhamChiTietIMPL;
import com.mycompany.da1.util.XFile;
import java.awt.Dialog;
import java.awt.Image;
import java.io.File;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import javax.swing.table.DefaultTableModel;

public class FormBanHang extends javax.swing.JPanel {

    private SanPhamChiTietIMPL sanPhamChiTietIMPL = new SanPhamChiTietIMPL();
    private HoaDonIMPL hoaDonIMPL = new HoaDonIMPL();
    private HoaDonChiTietIMPL hoaDonChiTietIMPL = new HoaDonChiTietIMPL();
    
    private JDialog jDialog;
    
    private String imageUrl, imageName;

    public FormBanHang() {
        initComponents();
    }

    private void LoadTableCTSanPham() {
        DefaultTableModel dtm = (DefaultTableModel) TableSanPhamCT.getModel();
        ArrayList<SanPhamChiTietEntity> sanPhams = sanPhamChiTietIMPL.GetAll();
        dtm.setRowCount(0);
        for (SanPhamChiTietEntity p : sanPhams) {
            Object[] row = {p.getId(), p.getSanPhamEntity().getTenSanPham(), p.getGiaSanPham(),
                p.getSoLuong(), p.getKichCoEntity().
                getTenKichCo(), p.getMauSacEntity().getTenMauSac(),
//                p.getSanPhamEntity().getAnhSanPham()}; - ảnh chuyển về sản phẩm chi tiết
                ""};
            dtm.addRow(row);
        }

    }

    private void LoadTableHoaDonCho() {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        ArrayList<HoaDonEntity> hoaDonEntitys = hoaDonIMPL.GetAll();
        dtm.setRowCount(0);
        for (HoaDonEntity p : hoaDonEntitys) {
            Object[] row = {p.getId(), p.getNgayTao(), p.getTaiKhoanEntity().getHoTen(), p.getTrangThai(),
                p.getKhachHangEntity().getHoTen(), p.getVoucherEntity().getPhanTramGiam()
            };
            dtm.addRow(row);
        }

    }

    private void LoadTableHoaDonCT() {
        String idhoaDon = txtHoaDon.getText();
        DefaultTableModel dtm = (DefaultTableModel) tbnHoaDonCT.getModel();
        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntitys = hoaDonChiTietIMPL.GetAll(Integer.parseInt(idhoaDon));
        dtm.setRowCount(0);
        for (HoaDonChiTietEntity p : hoaDonChiTietEntitys) {
            Object[] row = {p.getSanPhamChiTietEntity().getSanPhamEntity().getTenSanPham(),
                p.getSoLuong(), p.getSanPhamChiTietEntity().getGiaSanPham(),
                p.getTongTien()
            };
            dtm.addRow(row);
        }

    }

    private void LoadTableHoaDonThanhToan() {
        String idhoaDon = txtHoaDon.getText();
        DefaultTableModel dtm = (DefaultTableModel) tblDsMua.getModel();
        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntitys = hoaDonChiTietIMPL.GetAll(Integer.parseInt(idhoaDon));
        dtm.setRowCount(0);
        for (HoaDonChiTietEntity p : hoaDonChiTietEntitys) {
            Object[] row = {p.getSanPhamChiTietEntity().getSanPhamEntity().getTenSanPham(),
                p.getSanPhamChiTietEntity().getGiaSanPham(),
                p.getSoLuong()
            };
            dtm.addRow(row);
        }

    }

    private void showImage(String imageUrl) {
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

    private String getImageUrl() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        fileChooser.setDialogTitle("Chọn ảnh");
        File img = fileChooser.getSelectedFile();
        System.out.println(img.getAbsoluteFile());

        XFile.save(img);
        imageName = img.getName();
        return XFile.getPath(img.getName());
    }

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
        jPanel4 = new javax.swing.JPanel();
        btnTaoHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbnHoaDonCT = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
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

        setPreferredSize(new java.awt.Dimension(1200, 616));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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
        jLabel3.setText("Danh sách sản phẩm");

        jLabel18.setText("Giá bán");

        txtHoaDon.setText("#Here");

        TXTidSanPham.setText("#Here");

        txtGiaBan.setText("#Here");

        anhChiTiet.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TXTidSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(BtnThemHDCT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnThemHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chờ"));

        btnTaoHoaDon.setBackground(new java.awt.Color(255, 255, 51));
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
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
        jLabel2.setText("Hóa đơn chờ");

        btnReset.setBackground(new java.awt.Color(255, 255, 0));
        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnReset, btnTaoHoaDon});

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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
        jLabel7.setText("Giỏ Hàng");
        jLabel7.setFocusable(false);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tbnHoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Số Lượng", "Giá Sản Phẩm", "Thành Tiền"
            }
        ));
        jScrollPane3.setViewportView(tbnHoaDonCT);

        btnXoaSP.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaSP.setText("Xóa");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnThanhToan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThanhToan, btnXoaSP});

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Hóa Đơn Của Khách");

        jLabel11.setText("Tên Khách Hàng :");

        LableTenKhach.setText("#here");

        jLabel13.setText("Số Điện Thoại");

        lableSDTKhach.setText("#here");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lableTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lableTongTienSaugiam, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lableSDTKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(lableTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LableTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(251, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(lableSDTKhach)
                    .addComponent(LableTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(lableTenNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lableTongTien)
                    .addComponent(lableTongTienSaugiam)
                    .addComponent(jLabel17))
                .addGap(260, 260, 260))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnThemHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThemHDCTActionPerformed
        HoaDonChiTietEntity hoaDonChiTietEntity = new HoaDonChiTietEntity();
        HoaDonEntity hoaDonEntity = new HoaDonEntity();
        SanPhamChiTietEntity sanPhamChiTietEntity = new SanPhamChiTietEntity();
        hoaDonEntity.setId(Integer.parseInt(txtHoaDon.getText()));
        sanPhamChiTietEntity.setId(Integer.parseInt(TXTidSanPham.getText()));
        hoaDonChiTietEntity.setHoaDonEntity(hoaDonEntity);
        hoaDonChiTietEntity.setSanPhamChiTietEntity(sanPhamChiTietEntity);
        hoaDonChiTietEntity.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        BigDecimal Sl = new BigDecimal(txtSoLuong.getText());
        BigDecimal GiaBan = new BigDecimal(txtGiaBan.getText());
        BigDecimal tongTien = Sl.multiply(GiaBan);
        BigDecimal newTongTien = new BigDecimal(String.valueOf(tongTien));
        hoaDonChiTietEntity.setTongTien(tongTien);
        hoaDonChiTietIMPL.Save(hoaDonChiTietEntity, Integer.parseInt(txtSoLuong.getText()), newTongTien);
        LoadTableHoaDonCT();
    }//GEN-LAST:event_BtnThemHDCTActionPerformed

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
        FormInsertHoaDon insertHoaDon = new FormInsertHoaDon(); // có thểm add thêm event để bắt sự kiện từ dialog inesrt hóa đơn
        jDialog = new JDialog();
        jDialog.setTitle("Bảng danh mục");
        jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jDialog.setSize(500, 450);
        jDialog.setResizable(false);
        jDialog.add(insertHoaDon);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
        insertHoaDon.setVisible(true);
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:  int row = this.tblSon.getSelectedRow();
        int row = this.tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String idHoaDon = tblHoaDon.getValueAt(row, 0).toString();
        this.txtHoaDon.setText(idHoaDon);

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtHoaDon.setText("0");
        TXTidSanPham.setText("#here");
        txtSoLuong.setText("");
        txtGiaBan.setText("#here");
        LableTenKhach.setText("#here");
        lableSDTKhach.setText("#Hear");
        lableTongTien.setText("#here");
        lableTenNhanVien.setText("#here");
        lableTongTienSaugiam.setText("#here");
        LoadTableCTSanPham();
        LoadTableHoaDonCT();
        LoadTableHoaDonCho();
        LoadTableHoaDonThanhToan();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        BigDecimal bigDecimal = hoaDonChiTietIMPL.SumtongTien(Integer.parseInt(txtHoaDon.getText()));
        lableTongTien.setText(String.valueOf(bigDecimal));
        ArrayList<HoaDonEntity> hoaDonEntities = hoaDonIMPL.ThanhToan(Integer.parseInt(txtHoaDon.getText()));
        for (HoaDonEntity p : hoaDonEntities) {
            LableTenKhach.setText(p.getKhachHangEntity().getHoTen());
            lableSDTKhach.setText(p.getKhachHangEntity().getSoDienThoai());
            lableTenNhanVien.setText(p.getTaiKhoanEntity().getHoTen());
            BigDecimal discountPercentage = new BigDecimal(p.getVoucherEntity().getPhanTramGiam());
            lableTongTienSaugiam.setText(String.valueOf(bigDecimal.subtract(discountPercentage)));
        }
        BigDecimal tongTienHoaDon = new BigDecimal(lableTongTienSaugiam.getText());
        hoaDonIMPL.UpdateHoaDon(Integer.parseInt(txtHoaDon.getText()), tongTienHoaDon);
        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntities = hoaDonChiTietIMPL.GetAll(Integer.parseInt(txtHoaDon.getText()));
        for (HoaDonChiTietEntity p : hoaDonChiTietEntities) {
            sanPhamChiTietIMPL.UpdateSoLuongSP(p.getSoLuong(), p.getSanPhamChiTietEntity().getId());
        }
        LoadTableCTSanPham();
        LoadTableHoaDonThanhToan();
        LoadTableHoaDonCho();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        hoaDonChiTietIMPL.DeleteHDCT(Integer.parseInt(txtHoaDon.getText()));
        LoadTableHoaDonCT();
    }//GEN-LAST:event_btnXoaSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnThemHDCT;
    private javax.swing.JLabel LableTenKhach;
    private javax.swing.JLabel TXTidSanPham;
    private javax.swing.JTable TableSanPhamCT;
    private javax.swing.JLabel anhChiTiet;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
