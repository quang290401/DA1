package com.mycompany.da1.view.form.product;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.service.IMPL.SanPhamChiTietIMPL;
import com.mycompany.da1.service.IMPL.SanPhamIMPL;
import com.mycompany.da1.util.Contants;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.XFile;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sonng
 */
public class FormSanPham extends javax.swing.JPanel {

    private SanPhamIMPL sanPhamIMPL = new SanPhamIMPL();
    private SanPhamChiTietIMPL sanPhamChiTietIMPL = new SanPhamChiTietIMPL();

    private List<SanPhamChiTietEntity> lstSanPhamCt;
    private List<DanhMucEntity> listDanhMuc;

    private String imageUrl;
    private String imageName;

    private int idSp = -1;
    private Integer pageSize = 9;
    private Integer currentPage = 0;

    public FormSanPham() {
        initComponents();
        setUp();
    }

    private void setUp() {
        rdoConHang.setSelected(true);
        lstSanPhamCt = sanPhamChiTietIMPL.GetAll();
//        System.out.println("--------------------------------------------");
//        System.out.println(lstSanPhamCt.toString());
//        System.out.println("--------------------------------------------");
        fillTableSanPham(lstSanPhamCt);
        listDanhMuc = sanPhamChiTietIMPL.getListDanhMuc();
        fillCboSanPham();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        anhChiTiet = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnUpLoad = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnNhapExcel = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboLocDanhMuc = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        txtLocGia = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSach = new javax.swing.JTable();
        btNhoMax = new javax.swing.JButton();
        btLonMax = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(980, 740));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(980, 660));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel2.setText("Tên Sản Phẩm");

        jLabel13.setText("Trạng Thái");

        buttonGroup1.add(rdoConHang);
        rdoConHang.setText("Đang Bán");

        buttonGroup1.add(rdoHetHang);
        rdoHetHang.setText("Ngừng Bán");

        jLabel3.setText("Giá");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã Sản Phẩm");

        jLabel6.setText("Id");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        btnUpLoad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/UpLoad_Image.png"))); // NOI18N
        btnUpLoad.setText("UpLoad");
        btnUpLoad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpLoadMouseClicked(evt);
            }
        });
        btnUpLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpLoadActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/Them.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/Sua.png"))); // NOI18N
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnNhapExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhapExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/iconExcel.png"))); // NOI18N
        btnNhapExcel.setText("Nhập Excel");
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-clear-16.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-eye-16.png"))); // NOI18N
        btnChiTiet.setText("Xem Chi Tiết");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnUpLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(btnNhapExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChiTiet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpLoad)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhapExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Số lượng");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(74, 74, 74)
                                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(68, 68, 68)
                                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(rdoConHang)
                        .addGap(18, 18, 18)
                        .addComponent(rdoHetHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel2))
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(rdoConHang)
                                .addComponent(rdoHetHang)
                                .addComponent(jLabel5))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Lọc Sản Phẩm"));

        jLabel14.setText("Danh Mục");

        jLabel15.setText("Giá Tiền");

        cboLocDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocDanhMucActionPerformed(evt);
            }
        });

        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/Untitled-1.png"))); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtLocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(154, 154, 154))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc))
                .addGap(29, 29, 29))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        tbDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã Sản Phẩm", "Tên Sản Phẩm", "Danh Mục", "Giá", "Số lượng", "Nhà sản xuất", "Trạng Thái", "Image"
            }
        ));
        tbDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDanhSach);

        btNhoMax.setText("<<");
        btNhoMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btNhoMaxMouseClicked(evt);
            }
        });

        btLonMax.setText(">>");
        btLonMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLonMaxMouseClicked(evt);
            }
        });

        lbTrang.setText("jLabel4");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(btNhoMax)
                        .addGap(18, 18, 18)
                        .addComponent(lbTrang)
                        .addGap(18, 18, 18)
                        .addComponent(btLonMax)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNhoMax)
                    .addComponent(btLonMax)
                    .addComponent(lbTrang))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpLoadMouseClicked

    }//GEN-LAST:event_btnUpLoadMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (checkRongTxt(txtGia) == false || checkRongTxt(txtTenSanPham) == false || checkRongTxt(txtMaSanPham) == false) {
            MsgBox.alert(this, "Vui long nhap day du thong tin.");
            return;
        }

        if (!checkPhaiLaSoF(txtGia)) {
            MsgBox.alert(this, "Gia phai la so.");
            return;
        }

        if (!checkPhaiLaSo(txtSoLuong)) {
            MsgBox.alert(this, "Gia phai la so.");
            return;
        }

        BigDecimal gia = new BigDecimal(txtGia.getText());
        if (gia.compareTo(BigDecimal.ZERO) < 0) {
            MsgBox.alert(this, "Gia khong duoc am.");
            return;
        }

        SanPhamEntity sanPham = new SanPhamEntity();
        sanPham.setMaSanPham(String.valueOf(txtMaSanPham.getText()));
        sanPham.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sanPham.setTenSanPham(String.valueOf(txtTenSanPham.getText()));
        sanPham.setAnhSanPham(imageName);
        if (rdoConHang.isSelected()) {
            sanPham.setTrangThai(1);
        } else {
            sanPham.setTrangThai(0);
        }

        SanPhamEntity objTmp = sanPhamIMPL.save(sanPham);
        if (objTmp == null) {
            MsgBox.alert(this, "Them that bai");
            return;
        }

        SanPhamChiTietEntity sanPhamChiTiet = new SanPhamChiTietEntity();
        sanPhamChiTiet.setGiaSanPham(gia);
        sanPhamChiTiet.setTrangThai(1);
        sanPhamChiTiet.setSanPhamEntity(objTmp);

        SanPhamChiTietEntity objCtTmp = sanPhamChiTietIMPL.save(sanPhamChiTiet);
        System.out.println("-----------------INSERT SP DETAIL---------------------------");
        System.out.println(objCtTmp.toString());
        System.out.println("--------------------------------------------");

        if (objCtTmp == null) {
            MsgBox.alert(this, "Thêm mới sản phẩm chi tiết thất bại");
            return;
        }

        MsgBox.alert(this, "Thêm mới thành công");

        lstSanPhamCt = sanPhamChiTietIMPL.GetAll();
        fillTableSanPham(lstSanPhamCt);
        clearFrom();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
       
        if (checkRongTxt(txtGia) == false || checkRongTxt(txtTenSanPham) == false || checkRongTxt(txtMaSanPham) == false) {
            MsgBox.alert(this, "Vui long nhap day du thong tin.");
            return;
        }

        if (!checkPhaiLaSoF(txtGia)) {
            MsgBox.alert(this, "Gia phai la so.");
            return;
        }

        if (!checkPhaiLaSo(txtSoLuong)) {
            MsgBox.alert(this, "Gia phai la so.");
            return;
        }

        BigDecimal gia = new BigDecimal(txtGia.getText());
        if (gia.compareTo(BigDecimal.ZERO) < 0) {
            MsgBox.alert(this, "Gia khong duoc am.");
            return;
        }
        
        SanPhamEntity sanPham = new SanPhamEntity();
        sanPham.setId(Integer.valueOf(lblId.getText()));
        sanPham.setMaSanPham(String.valueOf(txtMaSanPham.getText()));
        sanPham.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sanPham.setTenSanPham(String.valueOf(txtTenSanPham.getText()));
        sanPham.setAnhSanPham(imageName);
        if (rdoConHang.isSelected()) {
            sanPham.setTrangThai(1);
        } else {
            sanPham.setTrangThai(0);
        }
        
        System.out.println("-----------------FORM SP UPDATE---------------------------");
        System.out.println(sanPham.toString());
        System.out.println("----------------------------------------------------------");

        int tmp = sanPhamIMPL.updateSanPham(sanPham);
        if (tmp < 1) {
            MsgBox.alert(this, "Cập nhật thất bại");
            return;
        }
        
        MsgBox.alert(this, "Cập nhật thành công");
//
//        SanPhamChiTietEntity sanPhamChiTiet = new SanPhamChiTietEntity();
//        sanPhamChiTiet.setGiaSanPham(gia);
//        sanPhamChiTiet.setTrangThai(1);
//        sanPhamChiTiet.setSanPhamEntity(objTmp);
//
//        SanPhamChiTietEntity objCtTmp = sanPhamChiTietIMPL.save(sanPhamChiTiet);
//        System.out.println("-----------------INSERT SP DETAIL---------------------------");
//        System.out.println(objCtTmp.toString());
//        System.out.println("--------------------------------------------");
//
//        if (objCtTmp == null) {
//            MsgBox.alert(this, "Thêm mới sản phẩm chi tiết thất bại");
//            return;
//        }
//
//        MsgBox.alert(this, "Thêm mới thành công");
//
        lstSanPhamCt = sanPhamChiTietIMPL.GetAll();
        fillTableSanPham(lstSanPhamCt);
        clearFrom();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        MsgBox.alert(this, "Chức năng sắp ra  mắt");
    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
//        FormSanPhamCt1 viewChiTiet = new FormSanPhamCt1();
//        viewChiTiet.setVisible(true);
        FormSanPhamCt d = new FormSanPhamCt();
        d.setVisible(true);
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        MsgBox.alert(this, "Chức năng sắp ra  mắt");
    }//GEN-LAST:event_btnLocActionPerformed

    private void tbDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachMouseClicked
        int index = tbDanhSach.getSelectedRow();
        if (index < 0) {
            return;
        }
        lblId.setText(tbDanhSach.getValueAt(index, 0).toString());
        txtMaSanPham.setText(tbDanhSach.getValueAt(index, 1).toString());
        txtTenSanPham.setText(tbDanhSach.getValueAt(index, 2).toString());
        txtGia.setText(tbDanhSach.getValueAt(index, 4).toString());
        txtSoLuong.setText(tbDanhSach.getValueAt(index, 5).toString());
        if (tbDanhSach.getValueAt(index, 7).toString() == "Đang bán") {
            rdoConHang.setSelected(true);
        } else {
            rdoHetHang.setSelected(true);
        }
        imageName = tbDanhSach.getValueAt(index, 8) == null ? "" : tbDanhSach.getValueAt(index, 8).toString();
        if (imageName != null) {
            showImage(XFile.getPath(imageName));
        } else {
            anhChiTiet.setIcon(null);
        }
    }//GEN-LAST:event_tbDanhSachMouseClicked

    private void btLonMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLonMaxMouseClicked
        if (currentPage < (int) Math.ceil((double) lstSanPhamCt.size() / pageSize) - 1) {
            currentPage++;
            fillTableSanPham(lstSanPhamCt);
        }
    }//GEN-LAST:event_btLonMaxMouseClicked

    private void btNhoMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNhoMaxMouseClicked
        if (currentPage > 0) {
            currentPage--;
            fillTableSanPham(lstSanPhamCt);
        }
    }//GEN-LAST:event_btNhoMaxMouseClicked

    private void cboLocDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocDanhMucActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnUpLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpLoadActionPerformed
        imageUrl = getImageUrl();
        showImage(imageUrl);

    }//GEN-LAST:event_btnUpLoadActionPerformed

    public void clearFrom() {
        lblId.setText("");
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtLocGia.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        rdoConHang.setSelected(true);
//        cboLocDanhMuc.setSelectedIndex(0);
        anhChiTiet.setIcon(null);
    }

    private void ButtonState() {
        int pageCount = (int) Math.ceil((double) lstSanPhamCt.size() / pageSize);

        btNhoMax.setEnabled(currentPage > 0);
        btLonMax.setEnabled(currentPage < pageCount - 1);
        lbTrang.setText("Page: " + (currentPage + 1));
    }

    public void fillTableSanPham(List<SanPhamChiTietEntity> listSanPhamCt) {
        DefaultTableModel def = (DefaultTableModel) tbDanhSach.getModel();
        def.setRowCount(0);

        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, lstSanPhamCt.size());

        for (int i = start; i < end; i++) {
            SanPhamChiTietEntity sp = listSanPhamCt.get(i);
            System.out.println("--------------------");
            System.out.println("sp.getId(): " + sp.getId());
            Object[] rowData = {
                sp.getSanPhamEntity().getId(),
                sp.getSanPhamEntity().getMaSanPham(),
                sp.getSanPhamEntity() == null ? "" : sp.getSanPhamEntity().getTenSanPham(),
                sp.getDanhMucEntity() == null ? "" : sp.getDanhMucEntity().getTenDanhMuc(),
                sp.getGiaSanPham(),
                sp.getSanPhamEntity().getSoLuong(),
                sp.getNhaSanXuatEntity() == null ? "" : sp.getNhaSanXuatEntity().getTenNhaSanXuat(),
                Contants.getTinhTrangText(sp.getSanPhamEntity().getTrangThai()),
                sp.getSanPhamEntity().getAnhSanPham()
            };
            def.addRow(rowData);
        }
        ButtonState();
    }

    public void fillCboSanPham() {
//        listDanhMuc = sanPhamChiTietIMPL.getListDanhMuc();
//        if (listDanhMuc.isEmpty()) {
//            System.out.println("List Danh Muc Rong");
//        } else {
//            cboDanhMuc.removeAllItems();
//            for (DanhMucEntity el : listDanhMuc) {
//                cboDanhMuc.addItem(el.getTenDanhMuc());
//            }
//            cboLocDanhMuc.removeAllItems();
//            cboLocDanhMuc.addItem("All");
//            for (DanhMucEntity string : listDanhMuc) {
//                cboLocDanhMuc.addItem(string.getTenDanhMuc());
//            }
//        }
    }

    public boolean checkRongTxt(JTextField txt) {
        if (txt.getText().trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public boolean checkRongTxtArea(JTextArea txtA) {
        if (txtA.getText().trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public boolean checkPhaiLaSo(JTextField txt) {
        try {
            Integer.parseInt(txt.getText().trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkPhaiLaSoF(JTextField txt) {
        try {
            Float.parseFloat(txt.getText().trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
        // save file
        XFile.save(img);
        imageName = img.getName();
        return XFile.getPath(img.getName());
//        return img.getAbsolutePath();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anhChiTiet;
    private javax.swing.JButton btLonMax;
    private javax.swing.JButton btNhoMax;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpLoad;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLocDanhMuc;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JLabel lblId;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JTable tbDanhSach;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtLocGia;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables

}
