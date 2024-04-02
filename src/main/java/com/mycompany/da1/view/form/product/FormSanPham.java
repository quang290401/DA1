package com.mycompany.da1.view.form.product;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.service.IMPL.SanPhamIMPL;
import com.mycompany.da1.util.Contants;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.PhanTrang;
import com.mycompany.da1.util.UserSession;
import com.mycompany.da1.util.ValidateEx;
import com.mycompany.da1.util.XDate;
import com.mycompany.da1.util.XFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Sonng
 */
public class FormSanPham extends javax.swing.JPanel {

    private PhanTrang<SanPhamEntity> phanTranglocal;

    private final SanPhamIMPL sanPhamService = new SanPhamIMPL();

    private List<SanPhamEntity> lstSanPham;

    public FormSanPham() {
        initComponents();
        setUp();
    }

    private void setUp() {
        rdoConHang.setSelected(true);
        lstSanPham = sanPhamService.GetAll();
        // phân trang
        phanTranglocal = new PhanTrang<>(lstSanPham);
        fillCboTimKiem();
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }

    public void fillTableWhenSearch(int page) {
        String txtSearch = txtLocSanPham.getText();
        DanhMucEntity danhMucTmp = ((DanhMucEntity) cboLocTrangThai.getSelectedItem());
        int trangThai = danhMucTmp.getTrangThai();

        this.lstSanPham = sanPhamService.getSerch(txtSearch, trangThai);
        phanTranglocal.refreshList(this.lstSanPham);
        DefaultTableModel def = (DefaultTableModel) tbDanhSach.getModel();
        def.setRowCount(0);

        int index = 1;
        for (SanPhamEntity item : phanTranglocal.getListData(page)) {
            Object[] rowData = {
                index,
                item.getMaSanPham(),
                item.getTenSanPham(),
                XDate.toString(item.getNgayTao()),
                Contants.getStatusBusiness(item.getTrangThai())
            };
            def.addRow(rowData);
            index++;
        }
        phanTranglocal.setButtonStatus();
        ButtonState();
        System.out.println("====================Table=====================");
        System.out.println(phanTranglocal.toString());
    }

    public void fillCboTimKiem() {
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
        danhMuc1.setTenDanhMuc("Ngừng kinh doanh");
        danhMuc1.setTrangThai(0);
        comboBoxModel.addElement(danhMuc1);
        // -----------
        DanhMucEntity danhMuc2 = new DanhMucEntity();
        danhMuc2.setTenDanhMuc("Đang kinh doanh");
        danhMuc2.setTrangThai(1);
        comboBoxModel.addElement(danhMuc2);
    }

    private SanPhamEntity getIdByMaSp(String maSp) {
        List<SanPhamEntity> lst = sanPhamService.GetAll();
        for (SanPhamEntity item : lst) {
            if (item.getMaSanPham().equalsIgnoreCase(maSp)) {
                return item;
            }
        }
        return null;
    }

    private Boolean checkEquasName(String name) {
        for (SanPhamEntity item : lstSanPham) {
            if (item.getTenSanPham().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private Boolean checkEquasName(String name, String maSp) {
        for (SanPhamEntity item : lstSanPham) {
            if (item.getTenSanPham().equalsIgnoreCase(name) && item.getMaSanPham().equalsIgnoreCase(maSp)) {
                continue;
            }
            if (item.getTenSanPham().equalsIgnoreCase(name) && !item.getMaSanPham().equalsIgnoreCase(maSp)) {
                return true;
            }
        }
        return false;
    }

    private void exportExcel(File file) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Sản phẩm");

            // Tạo CellStyle border
            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);
            // Tạo CellStyle để thiết lập chữ in đậm và căn giữa
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            //

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH SẢN PHẨM");
            cell.setCellStyle(style);
            
            //
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã sản phẩm");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên sản phẩm");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ngày nhập");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Trạng thái");
            cell.setCellStyle(borderStyle);
            
            for (int i = 0; i < lstSanPham.size(); i++) {
                SanPhamEntity item = lstSanPham.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(i + 1);
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(1);
                cell0.setCellValue(item.getMaSanPham());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(2);
                cell0.setCellValue(item.getTenSanPham());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(3);
                cell0.setCellValue(XDate.toString(item.getNgayTao()));
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(4);
                cell0.setCellValue(Contants.getStatusBusiness(item.getTrangThai()));
                cell0.setCellStyle(borderStyle); //
            }

            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnNhapExcel = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        txtMaSanPham = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboLocTrangThai = new javax.swing.JComboBox<>();
        txtLocSanPham = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSach = new javax.swing.JTable();
        btNhoMax = new javax.swing.JButton();
        btLonMax = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(980, 580));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(980, 660));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel2.setText("Tên Sản Phẩm");

        jLabel13.setText("Trạng Thái");

        rdoConHang.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoConHang);
        rdoConHang.setText("Đang kinh doanh");

        rdoHetHang.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoHetHang);
        rdoHetHang.setText("Ngừng kinh doanh");

        jLabel4.setText("Mã Sản Phẩm");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

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
        btnNhapExcel.setText("Xuất Excel");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhapExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtMaSanPham.setEditable(false);
        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoConHang)
                                .addGap(18, 18, 18)
                                .addComponent(rdoHetHang))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel2});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMaSanPham, txtTenSanPham});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(rdoConHang)
                    .addComponent(rdoHetHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, jLabel2});

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Lọc Sản Phẩm"));

        jLabel14.setText("Tên hoặc mã sản phẩm");

        jLabel15.setText("Trạng thái");

        cboLocTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocTrangThaiActionPerformed(evt);
            }
        });

        txtLocSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtLocSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboLocTrangThai, txtLocSanPham});

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        tbDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Ngày nhập", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        btNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhoMaxActionPerformed(evt);
            }
        });

        btLonMax.setText(">>");
        btLonMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLonMaxMouseClicked(evt);
            }
        });
        btLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLonMaxActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel4");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(btNhoMax)
                .addGap(18, 18, 18)
                .addComponent(lbTrang)
                .addGap(18, 18, 18)
                .addComponent(btLonMax)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNhoMax)
                    .addComponent(btLonMax)
                    .addComponent(lbTrang))
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (ValidateEx.checkIsNull(txtTenSanPham)) {
            MsgBox.alert(this, "Tên sản phẩm bắt buộc nhập");
            return;
        }

        if (checkEquasName(txtTenSanPham.getText().trim())) {
            MsgBox.alert(this, "Tên sản phẩm đã tồn tại");
            return;
        }

        List<SanPhamEntity> lstTmp = sanPhamService.GetAll();
        int idTmp = 0;
        if (lstTmp.size() > 0) {
            idTmp = lstTmp.get(lstTmp.size() - 1).getId() + 1;
        } else {
            idTmp = 1;
        }

        SanPhamEntity sanPham = new SanPhamEntity();
        sanPham.setMaSanPham("SP" + idTmp);
        sanPham.setTenSanPham(txtTenSanPham.getText().trim());
        if (rdoConHang.isSelected()) {
            sanPham.setTrangThai(1);
        } else {
            sanPham.setTrangThai(0);
        }

        SanPhamEntity objTmp = sanPhamService.save(sanPham);
        if (objTmp == null) {
            MsgBox.alert(this, "Thêm mới sản phẩm thất bại!");
            return;
        }

        MsgBox.alert(this, "Thêm mới sản phẩm thành công!");
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
        clearFrom();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (ValidateEx.checkIsNull(txtTenSanPham)) {
            MsgBox.alert(this, "Tên sản phẩm bắt buộc nhập");
            return;
        }
        if (ValidateEx.checkIsNull(txtMaSanPham)) {
            MsgBox.alert(this, "Mã sản phẩm bắt buộc nhập");
            return;
        }

        if (checkEquasName(txtTenSanPham.getText().trim(), txtMaSanPham.getText())) {
            MsgBox.alert(this, "Tên sản phẩm đã tồn tại");
            return;
        }

        if (getIdByMaSp(txtMaSanPham.getText()) == null) {
            MsgBox.alert(this, "Mã sản phẩm không tồn tại");
            return;
        }

        SanPhamEntity sanPham = new SanPhamEntity();
        sanPham.setId(getIdByMaSp(txtMaSanPham.getText()).getId());
        sanPham.setMaSanPham(txtMaSanPham.getText());
        sanPham.setTenSanPham(txtTenSanPham.getText().trim());
        if (rdoConHang.isSelected()) {
            sanPham.setTrangThai(1);
        } else {
            sanPham.setTrangThai(0);
        }

        int tmp = sanPhamService.updateSanPham(sanPham);
        if (tmp < 1) {
            MsgBox.alert(this, "Cập nhật sản phẩm thất bại");
            return;
        }

        MsgBox.alert(this, "Cập nhật sản phẩm thành công!");
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
        clearFrom();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
//        MsgBox.alert(this, "Chức năng sắp ra  mắt");
//        System.out.println(UserSession.getInstance().getAccountSession().toString());
        JFileChooser fs = new JFileChooser(new File("C:\\"));
        fs.setDialogTitle("Save file");
        fs.setFileFilter(new XFile(".xlsx", "Title file"));
        int rs = fs.showSaveDialog(null);
        if (rs == JFileChooser.APPROVE_OPTION) {
            File fi = fs.getSelectedFile();
            fi.renameTo(new File(fi.getAbsoluteFile() + ".xlsx"));
            System.out.println("=========================File Path============================");
            System.out.println(fi.getAbsoluteFile());
            System.out.println(fi.getName());
            exportExcel(new File(fi.getAbsoluteFile() + ".xlsx"));
        }
    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        if (ValidateEx.checkIsNull(txtMaSanPham)) {
            MsgBox.alert(this, "Hãy chọn hoặc nhập mã sản phẩm");
            return;
        }

        if (getIdByMaSp(txtMaSanPham.getText()) == null) {
            MsgBox.alert(this, "Mã sản phẩm không tồn tại!");
            return;
        }

        FormSanPhamCt viewSanPhamCt = new FormSanPhamCt(getIdByMaSp(txtMaSanPham.getText()));
        viewSanPhamCt.setVisible(true);
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void tbDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachMouseClicked
        int index = tbDanhSach.getSelectedRow();
        if (index < 0) {
            return;
        }
        txtMaSanPham.setText(tbDanhSach.getValueAt(index, 1).toString());
        txtTenSanPham.setText(tbDanhSach.getValueAt(index, 2).toString());
        if (tbDanhSach.getValueAt(index, 4).toString() == "Đang kinh doanh") {
            rdoConHang.setSelected(true);
        } else {
            rdoHetHang.setSelected(true);
        }
    }//GEN-LAST:event_tbDanhSachMouseClicked

    private void btLonMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLonMaxMouseClicked
        if (phanTranglocal.getIsNext()) {
            fillTableWhenSearch(phanTranglocal.getPage() + 1);
        }
    }//GEN-LAST:event_btLonMaxMouseClicked

    private void btNhoMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNhoMaxMouseClicked
        if (phanTranglocal.getIsPrev()) {
            fillTableWhenSearch(phanTranglocal.getPage() - 1);
        }
    }//GEN-LAST:event_btNhoMaxMouseClicked

    private void cboLocTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocTrangThaiActionPerformed
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_cboLocTrangThaiActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtLocSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocSanPhamKeyReleased
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_txtLocSanPhamKeyReleased

    private void btNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btNhoMaxActionPerformed

    private void btLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btLonMaxActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    public void clearFrom() {
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        rdoConHang.setSelected(true);
    }

    private void ButtonState() {
        btNhoMax.setEnabled(phanTranglocal.getIsPrev());
        btLonMax.setEnabled(phanTranglocal.getIsNext());
        lbTrang.setText("Page: " + phanTranglocal.getPage());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLonMax;
    private javax.swing.JButton btNhoMax;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLocTrangThai;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JTable tbDanhSach;
    private javax.swing.JTextField txtLocSanPham;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
