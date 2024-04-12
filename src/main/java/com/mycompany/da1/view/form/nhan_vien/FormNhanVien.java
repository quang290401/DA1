package com.mycompany.da1.view.form.nhan_vien;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.entity.VaiTroEntity;
import com.mycompany.da1.service.IMPL.TaiKhoanIMPL;
import com.mycompany.da1.util.AgeCalculator;
import com.mycompany.da1.util.Contants;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.PhanTrang;
import com.mycompany.da1.util.UserSession;
import com.mycompany.da1.util.ValidateEx;
import com.mycompany.da1.util.XDate;
import com.mycompany.da1.util.XFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * @author truong
 */
public class FormNhanVien extends javax.swing.JPanel {

    private PhanTrang<TaiKhoanEntity> phanTranglocal;
    private final TaiKhoanIMPL taiKhoanIMPL = new TaiKhoanIMPL();

    private List<TaiKhoanEntity> listTK;
    private List<VaiTroEntity> listVaiTro;

    public FormNhanVien() {
        initComponents();
        setUp();
    }

    private void setUp() {
        listTK = new ArrayList<>();
        phanTranglocal = new PhanTrang<>(listTK);
        txtNgaySinh.setDate(new Date());
        txtNgaySinh.getDateEditor().setEnabled(false);
        fillCboTimKiem();
        fillCboVaiTro();
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }

    public void fillTableWhenSearch(int page) {
        String txtSearch = txtTim.getText().trim();
        DanhMucEntity taiKhoanTmp = ((DanhMucEntity) cboTrangThai.getSelectedItem());
        int trangThai = taiKhoanTmp.getTrangThai();

        listTK = taiKhoanIMPL.getSearch(txtSearch, trangThai);
        phanTranglocal.refreshList(this.listTK);

        DefaultTableModel def = (DefaultTableModel) tblNhanVien.getModel();
        def.setRowCount(0);

        int index = 1;
        for (TaiKhoanEntity tk : phanTranglocal.getListData(page)) {
            Object[] rowData = {
                index,
                tk.getMaTK(),
                tk.getHoTen(),
                tk.getGioiTinh(),
                XDate.toString(tk.getNgaySinh()),
                tk.getDiaChi(),
                tk.getSdt(),
                Contants.getStatusEmployee(tk.getTrangThai()),
                tk.getCccd(),
                tk.getTaiKhoan(),
                tk.getMatKhau(),
                tk.getVaiTroEntity() == null ? "" : tk.getVaiTroEntity().getTenVaiTro(),
                XDate.toString(tk.getNgayTao()),
                XDate.toString(tk.getNgaySua())
            };
            def.addRow(rowData);
            index++;
        }
        phanTranglocal.setButtonStatus();
        ButtonState();
        System.out.println("====================Table=====================");
        System.out.println(phanTranglocal.toString());
    }

    public void fillCboVaiTro() {
        DefaultComboBoxModel comboBoxModel;
        comboBoxModel = (DefaultComboBoxModel) cboVaiTro.getModel();
        cboVaiTro.removeAllItems();
        comboBoxModel.removeAllElements();
        if (!UserSession.getInstance().checkAdmin()) {
            cboVaiTro.setEnabled(false);
        } else {
            cboVaiTro.setEnabled(true);
        }
        for (VaiTroEntity el : taiKhoanIMPL.getListVaiTro()) {
            comboBoxModel.addElement(el);
        }
    }

    public void fillCboTimKiem() {
        DefaultComboBoxModel comboBoxModel;
        comboBoxModel = (DefaultComboBoxModel) cboTrangThai.getModel();
        cboTrangThai.removeAllItems();
        comboBoxModel.removeAllElements();
        // -----------
        DanhMucEntity taiKhoan = new DanhMucEntity();
        taiKhoan.setTrangThai(3);
        taiKhoan.setTenDanhMuc("---Tất cả---");
        comboBoxModel.addElement(taiKhoan);
        // -----------
        DanhMucEntity taiKhoan1 = new DanhMucEntity();
        taiKhoan1.setTrangThai(1);
        taiKhoan1.setTenDanhMuc("Đang hoạt động");
        comboBoxModel.addElement(taiKhoan1);
        // -----------
        DanhMucEntity taiKhoan2 = new DanhMucEntity();
        taiKhoan2.setTrangThai(0);
        taiKhoan2.setTenDanhMuc("Ngừng hoạt động");
        comboBoxModel.addElement(taiKhoan2);
    }

    private TaiKhoanEntity getIdByMaSp(String maTK) {
        List<TaiKhoanEntity> lst = taiKhoanIMPL.getAll();
        for (TaiKhoanEntity item : lst) {
            if (item.getMaTK().equalsIgnoreCase(maTK)) {
                return item;
            }
        }
        return null;
    }

    private void ButtonState() {
        btNhoMax.setEnabled(phanTranglocal.getIsPrev());
        btLonMax1.setEnabled(phanTranglocal.getIsNext());
        lbTrang.setText("Page: " + phanTranglocal.getPage());
    }

    public void clearForm() {
        txtCCCD.setText("");
        txtDiaChi.setText("");
        txtHoTen.setText("");
        txtMatKhau.setText("");
        txtMa.setText("");
        txtNgaySinh.setDate(new Date());
        txtSdt.setText("");
        txtTaiKhoan.setText("");
        rdoHoatDong.setSelected(true);
        rdoNam.setSelected(true);
        cboVaiTro.setSelectedIndex(0);
    }

    private boolean checkObjInsert(TaiKhoanEntity objInput) {
        for (TaiKhoanEntity el : taiKhoanIMPL.getAll()) {
            if (objInput.getCccd().equals(el.getCccd())) {
                MsgBox.alert(this, "Căn cước công dân đã tồn tại");
                return true;
            }
            if (objInput.getTaiKhoan().equals(el.getTaiKhoan())) {
                MsgBox.alert(this, "Tài khoản đã tồn tại");
                return true;
            }
            if (objInput.getSdt().equalsIgnoreCase(el.getSdt())) {
                MsgBox.alert(this, "Số điện thoại đã tồn tại");
                return true;
            }
        }
        return false;
    }

    private boolean checkObjUpdate(TaiKhoanEntity objInput) {
        for (TaiKhoanEntity el : taiKhoanIMPL.getAll()) {
            if (objInput.getCccd().equals(el.getCccd()) && objInput.getId() != el.getId()) {
                MsgBox.alert(this, "Căn cước công dân đã tồn tại");
                return true;
            }
            if (objInput.getTaiKhoan().equals(el.getTaiKhoan()) && objInput.getId() != el.getId()) {
                MsgBox.alert(this, "Tài khoản đã tồn tại");
                return true;
            }
            if (objInput.getSdt().equalsIgnoreCase(el.getSdt()) && objInput.getId() != el.getId()) {
                MsgBox.alert(this, "Số điện thoại đã tồn tại");
                return true;
            }
        }
        return false;
    }

    private boolean checkTuoiNv(Calendar calendar) {
        if (AgeCalculator.checkAge(calendar, 18)) {
            MsgBox.alert(this, "Tuôi nhân viên phải lớn hơn hoặc bằng 18");
            return true;
        }
        return false;
    }

    private void exportExcel(File file) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Nhân viên");

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
            cell.setCellValue("Mã nhân viên");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ và tên");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Địa chỉ");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("CCCD");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Trạng thái");
            cell.setCellStyle(borderStyle);

            for (int i = 0; i < listTK.size(); i++) {
                TaiKhoanEntity item = listTK.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(i + 1);
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(1);
                cell0.setCellValue(item.getMaTK());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(2);
                cell0.setCellValue(item.getHoTen());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(3);
                cell0.setCellValue(item.getGioiTinh());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(4);
                cell0.setCellValue(XDate.toString(item.getNgaySinh()));
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(5);
                cell0.setCellValue(item.getDiaChi());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(6);
                cell0.setCellValue(item.getSdt());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(7);
                cell0.setCellValue(item.getCccd());
                cell0.setCellStyle(borderStyle); //
                cell0 = row.createCell(8);
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

        buttonGroupStatus = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rdoHoatDong = new javax.swing.JRadioButton();
        rdoNgungHoatDong = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cboVaiTro = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btNhoMax = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btLonMax1 = new javax.swing.JButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();

        setPreferredSize(new java.awt.Dimension(1200, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 500));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Trạng thái", "CCCD", "Tài khoản", "Mật khẩu", "Vai trò", "Ngày tạo", "Ngày sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("THÔNG TIN NHÂN VIÊN");

        jLabel2.setText("Mã");

        jLabel3.setText("Ngày sinh");

        txtMa.setEditable(false);
        txtMa.setPreferredSize(new java.awt.Dimension(64, 20));

        jLabel4.setText("Họ tên");

        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("Giới tính");

        rdoNam.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        rdoNu.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        jLabel7.setText("Số điện thoại");

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        jLabel10.setText("CCCD");

        jLabel11.setText("Trạng thái");

        rdoHoatDong.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroupStatus.add(rdoHoatDong);
        rdoHoatDong.setSelected(true);
        rdoHoatDong.setText("Đang hoạt động");
        rdoHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHoatDongActionPerformed(evt);
            }
        });

        rdoNgungHoatDong.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroupStatus.add(rdoNgungHoatDong);
        rdoNgungHoatDong.setText("Ngừng hoạt động");

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/Them.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/Sua.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-clear-16.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        jLabel9.setText("Nhập tên hoặc mã nhân viên");

        jLabel12.setText("Lọc trạng thái");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        jLabel8.setText("Tài khoản");

        jLabel13.setText("Mật khẩu");

        jLabel14.setText("Vai trò");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/iconExcel.png"))); // NOI18N
        jButton1.setText("Export");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btNhoMax.setText("<<");
        btNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhoMaxActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel4");

        btLonMax1.setText(">>");
        btLonMax1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLonMax1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboVaiTro, 0, 155, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhau))))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(145, 145, 145))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(btNhoMax)
                        .addGap(18, 18, 18)
                        .addComponent(lbTrang)
                        .addGap(18, 18, 18)
                        .addComponent(btLonMax1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNgungHoatDong))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboVaiTro, txtCCCD, txtDiaChi, txtHoTen, txtMa, txtMatKhau, txtNgaySinh, txtSdt, txtTaiKhoan});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel13, jLabel14, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(btnLamMoi))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnSua)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(rdoHoatDong)
                    .addComponent(rdoNgungHoatDong))
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNhoMax)
                    .addComponent(lbTrang)
                    .addComponent(btLonMax1))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboVaiTro, txtCCCD, txtDiaChi, txtHoTen, txtMa, txtMatKhau, txtNgaySinh, txtSdt, txtTaiKhoan});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel13, jLabel14, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1269, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int index = tblNhanVien.getSelectedRow();
        if (index < 0) {
            return;
        }
        txtMa.setText(tblNhanVien.getValueAt(index, 1).toString());
        txtHoTen.setText(tblNhanVien.getValueAt(index, 2).toString());
        if (tblNhanVien.getValueAt(index, 3).toString().equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtNgaySinh.setDate(XDate.toDate(tblNhanVien.getValueAt(index, 4).toString()));
        txtDiaChi.setText(tblNhanVien.getValueAt(index, 5).toString());
        txtSdt.setText(tblNhanVien.getValueAt(index, 6).toString());

        if (tblNhanVien.getValueAt(index, 7).toString().equalsIgnoreCase("Đang hoạt động")) {
            rdoHoatDong.setSelected(true);
        } else {
            rdoNgungHoatDong.setSelected(true);
        }

        txtCCCD.setText(tblNhanVien.getValueAt(index, 8).toString());
        txtTaiKhoan.setText(tblNhanVien.getValueAt(index, 9).toString());
        txtMatKhau.setText(tblNhanVien.getValueAt(index, 10).toString());

        if (tblNhanVien.getValueAt(index, 11).toString().equalsIgnoreCase("admin")) {
            cboVaiTro.setSelectedIndex(0);
        } else {
            cboVaiTro.setSelectedIndex(1);
        }

    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void rdoHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHoatDongActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (ValidateEx.checkIsNull(txtHoTen)) {
            MsgBox.alert(this, "Vui lòng nhập tên");
            return;
        } else if (ValidateEx.checkIsNull(txtSdt)) {
            MsgBox.alert(this, "Vui lòng nhập số điện thoại");
            return;
        } else if (txtSdt.getText().length() > 10) {
            MsgBox.alert(this, "Số điện thoại tối đa là 10 số");
            return;
        } else if (ValidateEx.checkIsNumber(txtSdt)) {
            MsgBox.alert(this, "Vui lòng nhập số");
            return;
        } else if (ValidateEx.checkIsNull(txtDiaChi)) {
            MsgBox.alert(this, "Vui lòng nhập địa chỉ");
            return;
        } else if (ValidateEx.checkIsNull(txtCCCD)) {
            MsgBox.alert(this, "Vui lòng nhập căn cước công dân");
            return;
        } else if (ValidateEx.checkIsNull(txtTaiKhoan)) {
            MsgBox.alert(this, "Vui lòng nhập tên tài khoản");
            return;
        } else if (ValidateEx.checkIsNull(txtMatKhau)) {
            MsgBox.alert(this, "Vui lòng nhập mật khẩu");
            return;
        }
        if (checkTuoiNv(txtNgaySinh.getCalendar())) {
            return;
        }

        List<TaiKhoanEntity> lstTmp = taiKhoanIMPL.getAll();
        int idTmp = 0;
        if (lstTmp.size() > 0) {
            idTmp = lstTmp.get(lstTmp.size() - 1).getId() + 1;
        } else {
            idTmp = 1;
        }

        TaiKhoanEntity taiKhoan = new TaiKhoanEntity();
        taiKhoan.setMaTK("TK" + idTmp);
        taiKhoan.setHoTen(txtHoTen.getText().trim());
        taiKhoan.setSdt(txtSdt.getText().trim());
        taiKhoan.setDiaChi(txtDiaChi.getText().trim());
        taiKhoan.setCccd(txtCCCD.getText().trim());
        taiKhoan.setTaiKhoan(txtTaiKhoan.getText().trim());
        taiKhoan.setMatKhau(txtMatKhau.getText().trim());
        taiKhoan.setNgaySinh(txtNgaySinh.getDate());
        if (rdoNam.isSelected()) {
            taiKhoan.setGioiTinh("Nam");
        } else {
            taiKhoan.setGioiTinh("Nữ");
        }
        if (rdoHoatDong.isSelected()) {
            taiKhoan.setTrangThai(1);
        } else {
            taiKhoan.setTrangThai(0);
        }

        VaiTroEntity vaiTroEntity = (VaiTroEntity) cboVaiTro.getSelectedItem();
        taiKhoan.setVaiTroEntity(vaiTroEntity);

        if (checkObjInsert(taiKhoan)) {
            return;
        }

        TaiKhoanEntity objTmp = taiKhoanIMPL.save(taiKhoan);
        if (objTmp == null) {
            MsgBox.alert(this, "Thêm mới nhân viên thất bại!");
            return;
        }
        MsgBox.alert(this, "Thêm mới nhân viên thành công!");
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
        clearForm();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (ValidateEx.checkIsNull(txtHoTen)) {
            MsgBox.alert(this, "Vui lòng nhập tên");
            return;
        } else if (ValidateEx.checkIsNull(txtSdt)) {
            MsgBox.alert(this, "Vui lòng nhập số điện thoại");
            return;
        } else if (txtSdt.getText().length() > 10) {
            MsgBox.alert(this, "Số điện thoại tối đa là 10 số");
            return;
        } else if (ValidateEx.checkIsNumber(txtSdt)) {
            MsgBox.alert(this, "Vui lòng nhập số");
            return;
        } else if (ValidateEx.checkIsNull(txtDiaChi)) {
            MsgBox.alert(this, "Vui lòng nhập địa chỉ");
            return;
        } else if (ValidateEx.checkIsNull(txtCCCD)) {
            MsgBox.alert(this, "Vui lòng nhập căn cước công dân");
            return;
        } else if (ValidateEx.checkIsNull(txtTaiKhoan)) {
            MsgBox.alert(this, "Vui lòng nhập tên tài khoản");
            return;
        } else if (ValidateEx.checkIsNull(txtMatKhau)) {
            MsgBox.alert(this, "Vui lòng nhập mật khẩu");
            return;
        }
        if (getIdByMaSp(txtMa.getText()) == null) {
            MsgBox.alert(this, "Mã nhân viên không tồn tại");
            return;
        }
        if (checkTuoiNv(txtNgaySinh.getCalendar())) {
            return;
        }
        TaiKhoanEntity taiKhoan = new TaiKhoanEntity();
        taiKhoan.setId(getIdByMaSp(txtMa.getText()).getId());
        taiKhoan.setHoTen(txtHoTen.getText().trim());
        taiKhoan.setSdt(txtSdt.getText().trim());
        taiKhoan.setDiaChi(txtDiaChi.getText().trim());
        taiKhoan.setCccd(txtCCCD.getText().trim());
        taiKhoan.setTaiKhoan(txtTaiKhoan.getText().trim());
        taiKhoan.setMatKhau(txtMatKhau.getText().trim());
        taiKhoan.setNgaySinh(txtNgaySinh.getDate());
        if (rdoNam.isSelected()) {
            taiKhoan.setGioiTinh("Nam");
        } else {
            taiKhoan.setGioiTinh("Nữ");
        }
        if (rdoHoatDong.isSelected()) {
            taiKhoan.setTrangThai(1);
        } else {
            taiKhoan.setTrangThai(0);
        }
        VaiTroEntity vaiTroEntity = (VaiTroEntity) cboVaiTro.getSelectedItem();
        taiKhoan.setVaiTroEntity(vaiTroEntity);

        if (checkObjUpdate(taiKhoan)) {
            return;
        }
        int tmp = taiKhoanIMPL.updateTaiKhoan(taiKhoan);
        if (tmp < 1) {
            MsgBox.alert(this, "Cập nhật nhân viên thất bại");
            return;
        }
        MsgBox.alert(this, "Cập nhật nhân viên thành công!");
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
        clearForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_txtTimKeyReleased

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void btNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoMaxActionPerformed
        if (phanTranglocal.getIsPrev()) {
            fillTableWhenSearch(phanTranglocal.getPage() - 1);
        }
    }//GEN-LAST:event_btNhoMaxActionPerformed

    private void btLonMax1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonMax1ActionPerformed
        if (phanTranglocal.getIsNext()) {
            fillTableWhenSearch(phanTranglocal.getPage() + 1);
        }
    }//GEN-LAST:event_btLonMax1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLonMax1;
    private javax.swing.JButton btNhoMax;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupStatus;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNgungHoatDong;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
