package com.mycompany.da1.view.form.product;

import com.mycompany.da1.entity.ChatLieuEntity;
import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.KichCoEntity;
import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.entity.NhaSanXuatEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.service.IMPL.SanPhamChiTietIMPL;
import com.mycompany.da1.service.IMPL.SanPhamIMPL;
import com.mycompany.da1.util.Contants;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.ValidateEx;
import com.mycompany.da1.view.events.EventDialogListener;
import java.awt.Dialog;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sonst
 */
public class FormSanPhamCt extends javax.swing.JFrame implements EventDialogListener {

    private SanPhamChiTietIMPL sanPhamChiTietIMPL = new SanPhamChiTietIMPL();
    private SanPhamIMPL sanPhamIMPL = new SanPhamIMPL();

    private List<SanPhamChiTietEntity> lstSanPhamCt;
    private List<SanPhamEntity> lstSanPham;
    private List<DanhMucEntity> lstDanhMuc;
    private List<NhaSanXuatEntity> lstNhaSx;
    private List<MauSacEntity> lstMauSac;
    private List<KichCoEntity> lstKichCo;
    private List<ChatLieuEntity> lstChatLieu;

    private JDialog jDialog;

    public FormSanPhamCt() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setUp();
    }

    private void setUp() {
        rdoDangBan.setSelected(true);
        lstSanPhamCt = sanPhamChiTietIMPL.GetAll();
        fillTableSanPham();
        fillCboChiTiet();
    }

    private void fillTableSanPham() {
        List<SanPhamChiTietEntity> lstData = sanPhamChiTietIMPL.GetAll();
        if (lstData == null) {
            MsgBox.alert(this, "Lỗi");
        } else if (lstData.isEmpty()) {
            MsgBox.alert(this, "Không có dữ liệu");
        } else {
            DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
            model.setRowCount(0);
            for (SanPhamChiTietEntity el : lstSanPhamCt) {
                model.addRow(new Object[]{
                    el.getId(),
                    el.getSanPhamEntity().getTenSanPham(),
                    el.getGiaSanPham(),
                    el.getSoLuong(),
                    el.getDanhMucEntity() != null ? el.getDanhMucEntity().getTenDanhMuc() : "",
                    el.getNhaSanXuatEntity() != null ? el.getNhaSanXuatEntity().getTenNhaSanXuat() : "",
                    el.getMauSacEntity() != null ? el.getMauSacEntity().getTenMauSac() : "",
                    el.getKichCoEntity() != null ? el.getKichCoEntity().getTenKichCo() : "",
                    el.getChatLieuEntity() != null ? el.getChatLieuEntity().getTenChatLieu() : "",
                    Contants.getTinhTrangText(el.getTrangThai())
                });
            }
        }
    }

    private void fillCboChiTiet() {
        lstDanhMuc = sanPhamChiTietIMPL.getListDanhMuc();
        lstNhaSx = sanPhamChiTietIMPL.getListNhaSx();
        lstKichCo = sanPhamChiTietIMPL.getListKichCo();
        lstMauSac = sanPhamChiTietIMPL.getListMauSac();
        lstChatLieu = sanPhamChiTietIMPL.getListChatLieu();
        lstSanPham = sanPhamIMPL.GetAll();
        if (lstDanhMuc.isEmpty()
                || lstNhaSx.isEmpty()
                || lstKichCo.isEmpty()
                || lstMauSac.isEmpty()
                || lstChatLieu.isEmpty()) {
            System.out.println("=================== ERROR ==============");
            System.out.println("Thuộc tính tồn tại 1 bảng trống");
        } else {
            DefaultComboBoxModel comboBoxModel;

            comboBoxModel = (DefaultComboBoxModel) cboSanPham.getModel();
            cboSanPham.removeAllItems();
            comboBoxModel.removeAllElements();
            for (SanPhamEntity item : lstSanPham) {
                comboBoxModel.addElement(item);
            }

            comboBoxModel = (DefaultComboBoxModel) cboDanhMuc.getModel();
            comboBoxModel.removeAllElements();
            cboDanhMuc.removeAllItems();
            for (DanhMucEntity item : lstDanhMuc) {
                comboBoxModel.addElement(item);
            }

            comboBoxModel = (DefaultComboBoxModel) cboNsx.getModel();
            comboBoxModel.removeAllElements();
            cboNsx.removeAllItems();
            for (NhaSanXuatEntity item : lstNhaSx) {
                comboBoxModel.addElement(item);
            }

            comboBoxModel = (DefaultComboBoxModel) cboKichCo.getModel();
            comboBoxModel.removeAllElements();
            cboKichCo.removeAllItems();
            for (KichCoEntity item : lstKichCo) {
                comboBoxModel.addElement(item);
            }

            comboBoxModel = (DefaultComboBoxModel) cboChatLieu.getModel();
            comboBoxModel.removeAllElements();
            cboChatLieu.removeAllItems();
            for (ChatLieuEntity item : lstChatLieu) {
                comboBoxModel.addElement(item);
            }

            comboBoxModel = (DefaultComboBoxModel) cboMauSac.getModel();
            comboBoxModel.removeAllElements();
            cboMauSac.removeAllItems();
            for (MauSacEntity item : lstMauSac) {
                comboBoxModel.addElement(item);
            }
        }
    }

    public void clearFrom() {
        lblid.setText("");
        txtGia.setText("");
        txtMoTa.setText("");
        txtSoLuong.setText("");
        cboDanhMuc.setSelectedIndex(0);
        rdoDangBan.setSelected(true);
        cboChatLieu.setSelectedIndex(0);
        cboSanPham.setSelectedIndex(0);
        cboNsx.setSelectedIndex(0);
        cboKichCo.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
    }

    @Override
    public void closeDialog() {
        fillCboChiTiet();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboNsx = new javax.swing.JComboBox<>();
        cboKichCo = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboSanPham = new javax.swing.JComboBox<>();
        cboDanhMuc = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        rdoDangBan = new javax.swing.JRadioButton();
        rdoTamNgung = new javax.swing.JRadioButton();
        lblid = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        lbDanhMuc = new javax.swing.JLabel();
        lbChatLieu = new javax.swing.JLabel();
        lbMauSac = new javax.swing.JLabel();
        lbSize = new javax.swing.JLabel();
        lbNSX = new javax.swing.JLabel();
        lbDe = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btNhoMax = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btLonMax = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDanhMuc = new javax.swing.JLabel();
        btnKichCo = new javax.swing.JLabel();
        btnMauSac = new javax.swing.JLabel();
        btnChatLieu = new javax.swing.JLabel();
        btnNhaSx = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chi tiết sản phẩm");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("Id Chi Tiết :");

        jLabel2.setText("Sản Phẩm");

        jLabel3.setText("Danh Mục");

        jLabel4.setText("Chất Liệu");

        jLabel5.setText("Màu Sắc");

        jLabel6.setText("Kích cỡ");

        jLabel7.setText("Nhà Sản Xuất");

        jLabel9.setText("Số Lượng");

        jLabel10.setText("Giá");

        jLabel11.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel12.setText("Trạng Thái");

        rdoDangBan.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoDangBan);
        rdoDangBan.setText("Đang bán");

        rdoTamNgung.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoTamNgung);
        rdoTamNgung.setText("Ngừng bán");

        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
        });

        lbDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDanhMucMouseClicked(evt);
            }
        });

        lbChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbChatLieuMouseClicked(evt);
            }
        });

        lbMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMauSacMouseClicked(evt);
            }
        });

        lbSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSizeMouseClicked(evt);
            }
        });

        lbNSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNSXMouseClicked(evt);
            }
        });

        lbDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDeMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Tên sản phẩm", "Giá", "Số lượng", "Danh mục", "Nhà sản xuất", "Màu sắc", "Kích cỡ", "Chất liệu", "Trạng Thái"
            }
        ));
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSach);

        btNhoMax.setText("<<");
        btNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhoMaxActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel4");

        btLonMax.setText(">>");
        btLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLonMaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btNhoMax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTrang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btLonMax)
                        .addGap(368, 368, 368)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNhoMax)
                    .addComponent(lbTrang)
                    .addComponent(btLonMax))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

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

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-clear-16.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset)
                .addGap(80, 80, 80))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat)
                    .addComponent(btnReset))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        btnDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDanhMucMouseClicked(evt);
            }
        });

        btnKichCo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnKichCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKichCoMouseClicked(evt);
            }
        });

        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMauSacMouseClicked(evt);
            }
        });

        btnChatLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChatLieuMouseClicked(evt);
            }
        });

        btnNhaSx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnNhaSx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhaSxMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbClose))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbMauSac))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnNhaSx, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbNSX))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbChatLieu))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbDanhMuc))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbSize)))
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel11))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rdoDangBan)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rdoTamNgung))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtGia)
                                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                                            .addComponent(txtSoLuong))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lbDe))))))
                                    .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboSanPham, lblid});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbClose)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbDe, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(63, 63, 63))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoTamNgung)
                                        .addComponent(rdoDangBan)
                                        .addComponent(jLabel12))
                                    .addGap(64, 64, 64))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addGap(136, 136, 136)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5)
                                                .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel7)
                                                .addComponent(cboNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnNhaSx, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboSanPham, lblid, txtGia, txtSoLuong});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_lbCloseMouseClicked

    private void lbDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDanhMucMouseClicked

    }//GEN-LAST:event_lbDanhMucMouseClicked

    private void lbChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbChatLieuMouseClicked

    }//GEN-LAST:event_lbChatLieuMouseClicked

    private void lbMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMauSacMouseClicked

    }//GEN-LAST:event_lbMauSacMouseClicked

    private void lbSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSizeMouseClicked

    }//GEN-LAST:event_lbSizeMouseClicked

    private void lbNSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNSXMouseClicked

    }//GEN-LAST:event_lbNSXMouseClicked

    private void lbDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDeMouseClicked

    }//GEN-LAST:event_lbDeMouseClicked

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int index = tblDanhSach.getSelectedRow();
        if (index < 0) {
            return;
        }
        txtMoTa.setText(lstSanPhamCt.get(index).getMoTa());

        lblid.setText(tblDanhSach.getValueAt(index, 0).toString());
        cboSanPham.setSelectedItem(lstSanPhamCt.get(index).getSanPhamEntity());
        txtGia.setText(tblDanhSach.getValueAt(index, 2).toString());
        txtSoLuong.setText(tblDanhSach.getValueAt(index, 3).toString());
        cboDanhMuc.setSelectedItem(tblDanhSach.getValueAt(index, 4).toString());
        cboNsx.setSelectedItem(tblDanhSach.getValueAt(index, 5).toString());
        cboMauSac.setSelectedItem(tblDanhSach.getValueAt(index, 6).toString());
        cboKichCo.setSelectedItem(tblDanhSach.getValueAt(index, 7).toString());
        cboChatLieu.setSelectedItem(tblDanhSach.getValueAt(index, 8).toString());
        if (tblDanhSach.getValueAt(index, 9).toString() == "Đang bán") {
            rdoDangBan.setSelected(true);
        } else {
            rdoTamNgung.setSelected(true);
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoMaxActionPerformed

    }//GEN-LAST:event_btNhoMaxActionPerformed

    private void btLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonMaxActionPerformed

    }//GEN-LAST:event_btLonMaxActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (ValidateEx.checkIsNull(txtMoTa, txtGia, txtSoLuong)) {
            MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (ValidateEx.checkIsNumber(txtSoLuong)) {
            MsgBox.alert(this, "Số lượng phải là kiểu số");
            return;
        } else if (ValidateEx.checkMoreThan(txtSoLuong, 0)) {
            MsgBox.alert(this, "Số lượng phải lớn hơn 0");
            return;
        }

        if (ValidateEx.checkIsFloat(txtGia)) {
            MsgBox.alert(this, "Giá bán phải là kiểu số");
            return;
        }
        BigDecimal gia = new BigDecimal(txtGia.getText());
        if (gia.compareTo(BigDecimal.ZERO) < 0) {
            MsgBox.alert(this, "Giá bán không thể là số âm");
            return;
        }

        SanPhamEntity sanPhamTmp = ((SanPhamEntity) cboSanPham.getSelectedItem());
        DanhMucEntity danhMucTmp = ((DanhMucEntity) cboDanhMuc.getSelectedItem());
        NhaSanXuatEntity nhaSanXuatTmp = ((NhaSanXuatEntity) cboNsx.getSelectedItem());
        MauSacEntity mauSacTmp = ((MauSacEntity) cboMauSac.getSelectedItem());
        ChatLieuEntity chatLieuTmp = ((ChatLieuEntity) cboChatLieu.getSelectedItem());
        KichCoEntity kichCoTmp = ((KichCoEntity) cboKichCo.getSelectedItem());

        SanPhamChiTietEntity sanPhamChiTiet = new SanPhamChiTietEntity();
        sanPhamChiTiet.setGiaSanPham(gia);
        sanPhamChiTiet.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        sanPhamChiTiet.setMoTa((txtMoTa.getText()));
        sanPhamChiTiet.setTrangThai(1);
        sanPhamChiTiet.setSanPhamEntity(sanPhamTmp);
        sanPhamChiTiet.setDanhMucEntity(danhMucTmp);
        sanPhamChiTiet.setNhaSanXuatEntity(nhaSanXuatTmp);
        sanPhamChiTiet.setMauSacEntity(mauSacTmp);
        sanPhamChiTiet.setChatLieuEntity(chatLieuTmp);
        sanPhamChiTiet.setKichCoEntity(kichCoTmp);
        if (rdoDangBan.isSelected()) {
            sanPhamChiTiet.setTrangThai(1);
        } else {
            sanPhamChiTiet.setTrangThai(0);
        }

        SanPhamChiTietEntity objTemp = sanPhamChiTietIMPL.save(sanPhamChiTiet);
        if (objTemp == null) {
            MsgBox.alert(this, "Thêm mới không thành công");
            return;
        } else {
            MsgBox.alert(this, "Thêm mới thành công");
        }

        lstSanPhamCt = sanPhamChiTietIMPL.GetAll();
        clearFrom();
        fillTableSanPham();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (ValidateEx.checkIsNull(txtMoTa, txtGia, txtSoLuong)) {
            MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (ValidateEx.checkIsNumber(txtSoLuong)) {
            MsgBox.alert(this, "Số lượng phải là kiểu số");
            return;
        } else if (ValidateEx.checkMoreThan(txtSoLuong, 0)) {
            MsgBox.alert(this, "Số lượng phải lớn hơn 0");
            return;
        }

        if (ValidateEx.checkIsFloat(txtGia)) {
            MsgBox.alert(this, "Giá bán phải là kiểu số");
            return;
        }
        BigDecimal gia = new BigDecimal(txtGia.getText());
        if (gia.compareTo(BigDecimal.ZERO) < 0) {
            MsgBox.alert(this, "Giá bán không thể là số âm");
            return;
        }

        SanPhamEntity sanPhamTmp = ((SanPhamEntity) cboSanPham.getSelectedItem());
        DanhMucEntity danhMucTmp = ((DanhMucEntity) cboDanhMuc.getSelectedItem());
        NhaSanXuatEntity nhaSanXuatTmp = ((NhaSanXuatEntity) cboNsx.getSelectedItem());
        MauSacEntity mauSacTmp = ((MauSacEntity) cboMauSac.getSelectedItem());
        ChatLieuEntity chatLieuTmp = ((ChatLieuEntity) cboChatLieu.getSelectedItem());
        KichCoEntity kichCoTmp = ((KichCoEntity) cboKichCo.getSelectedItem());

        SanPhamChiTietEntity sanPhamChiTiet = new SanPhamChiTietEntity();
        sanPhamChiTiet.setId(Integer.valueOf(lblid.getText()));
        sanPhamChiTiet.setGiaSanPham(gia);
        sanPhamChiTiet.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sanPhamChiTiet.setMoTa(txtMoTa.getText());
        sanPhamChiTiet.setTrangThai(1);
        sanPhamChiTiet.setSanPhamEntity(sanPhamTmp);
        sanPhamChiTiet.setDanhMucEntity(danhMucTmp);
        sanPhamChiTiet.setNhaSanXuatEntity(nhaSanXuatTmp);
        sanPhamChiTiet.setMauSacEntity(mauSacTmp);
        sanPhamChiTiet.setChatLieuEntity(chatLieuTmp);
        sanPhamChiTiet.setKichCoEntity(kichCoTmp);
        if (rdoDangBan.isSelected()) {
            sanPhamChiTiet.setTrangThai(1);
        } else {
            sanPhamChiTiet.setTrangThai(0);
        }

        int check = sanPhamChiTietIMPL.update(sanPhamChiTiet);
        if (check == 0) {
            MsgBox.alert(this, "Cập nhật không thành công");
            return;
        } else {
            MsgBox.alert(this, "Cập nhật thành công");
        }

        lstSanPhamCt = sanPhamChiTietIMPL.GetAll();
        clearFrom();
        fillTableSanPham();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDanhMucMouseClicked
        FormDanhMuc viewDanhMuc = new FormDanhMuc(this);
        jDialog = new JDialog();
        jDialog.setTitle("Bảng danh mục");
        jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jDialog.setSize(500, 450);
        jDialog.setResizable(false);
        jDialog.add(viewDanhMuc);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }//GEN-LAST:event_btnDanhMucMouseClicked

    private void btnKichCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKichCoMouseClicked
        FormKichCo view = new FormKichCo(this);
        jDialog = new JDialog();
        jDialog.setTitle("Bảng kích cỡ");
        jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jDialog.setSize(500, 450);
        jDialog.setResizable(false);
        jDialog.add(view);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }//GEN-LAST:event_btnKichCoMouseClicked

    private void btnMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMauSacMouseClicked
        FormMauSac view = new FormMauSac(this);
        jDialog = new JDialog();
        jDialog.setTitle("Bảng màu sắc");
        jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jDialog.setSize(500, 450);
        jDialog.setResizable(false);
        jDialog.add(view);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }//GEN-LAST:event_btnMauSacMouseClicked

    private void btnChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatLieuMouseClicked
        FormChatLieu view = new FormChatLieu(this);
        jDialog = new JDialog();
        jDialog.setTitle("Bảng chất liệu");
        jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jDialog.setSize(500, 450);
        jDialog.setResizable(false);
        jDialog.add(view);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }//GEN-LAST:event_btnChatLieuMouseClicked

    private void btnNhaSxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhaSxMouseClicked
        FormNhaSanXuat view = new FormNhaSanXuat(this);
        jDialog = new JDialog();
        jDialog.setTitle("Bảng nhà sản xuất");
        jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jDialog.setSize(500, 450);
        jDialog.setResizable(false);
        jDialog.add(view);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }//GEN-LAST:event_btnNhaSxMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamCt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamCt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamCt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamCt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSanPhamCt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLonMax;
    private javax.swing.JButton btNhoMax;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JLabel btnChatLieu;
    private javax.swing.JLabel btnDanhMuc;
    private javax.swing.JLabel btnKichCo;
    private javax.swing.JLabel btnMauSac;
    private javax.swing.JLabel btnNhaSx;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNsx;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbChatLieu;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbDanhMuc;
    private javax.swing.JLabel lbDe;
    private javax.swing.JLabel lbMauSac;
    private javax.swing.JLabel lbNSX;
    private javax.swing.JLabel lbSize;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JLabel lblid;
    private javax.swing.JRadioButton rdoDangBan;
    private javax.swing.JRadioButton rdoTamNgung;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
