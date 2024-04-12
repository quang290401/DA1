package com.mycompany.da1.view.form.product;

import com.mycompany.da1.entity.ChatLieuEntity;
import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.KichCoEntity;
import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.entity.NhaSanXuatEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.service.IMPL.SanPhamChiTietIMPL;
import com.mycompany.da1.util.Contants;
import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.PhanTrang;
import com.mycompany.da1.util.ValidateEx;
import com.mycompany.da1.util.XDate;
import com.mycompany.da1.util.XFile;
import com.mycompany.da1.view.events.EventDialogListener;
import java.awt.Dialog;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
 * @author sonst
 */
public class FormSanPhamCt extends javax.swing.JFrame implements EventDialogListener {

    private PhanTrang<SanPhamChiTietEntity> phanTranglocal;

    private SanPhamChiTietIMPL sanPhamChiTietIMPL = new SanPhamChiTietIMPL();
    private XFile xFile = new XFile();

    private List<SanPhamChiTietEntity> lstSanPhamCt;
    private List<DanhMucEntity> lstDanhMuc;
    private List<NhaSanXuatEntity> lstNhaSx;
    private List<MauSacEntity> lstMauSac;
    private List<KichCoEntity> lstKichCo;
    private List<ChatLieuEntity> lstChatLieu;
    private List<String> listFileName;

    private JDialog jDialog;

    private String imageUrl;
    private String imageName;

    private Integer listSize = 0;
    private Integer pageSize = 10;
    private Integer currentPage = 0;

    private SanPhamEntity sanPhamEntity;

    public FormSanPhamCt(SanPhamEntity objInput) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setUp(objInput);
    }

    public FormSanPhamCt() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void setUp(SanPhamEntity objInput) {
        lstSanPhamCt = sanPhamChiTietIMPL.GetAll();
        phanTranglocal = new PhanTrang<>(lstSanPhamCt);
        // set object cha sản phẩm
        sanPhamEntity = objInput;
        //
        rdoDangBan.setSelected(true);
        //
        fillCboChiTiet();
        fillCboStaus();
        fillCboTimKiem();
        fillCboFileLst();
        txtSanPham.setText(objInput.getTenSanPham());
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }

    private void ButtonState() {
        btNhoMax.setEnabled(phanTranglocal.getIsPrev());
        btLonMax.setEnabled(phanTranglocal.getIsNext());
        lbTrang.setText("Page: " + phanTranglocal.getPage());
    }

    private void fillTableWhenSearch(int page) {
        String txtSearch = txtLocText.getText();
        DanhMucEntity trangThaiTmp = ((DanhMucEntity) cboLocStatus.getSelectedItem());
        int trangThai = trangThaiTmp.getTrangThai();
        NhaSanXuatEntity nhaSxTmp = ((NhaSanXuatEntity) cboLocNhaSx.getSelectedItem());
        DanhMucEntity danhMucTmp = ((DanhMucEntity) cboLocDanhMuc.getSelectedItem());
        //
        lstSanPhamCt = sanPhamChiTietIMPL.getSearch(txtSearch, danhMucTmp, nhaSxTmp, trangThai, sanPhamEntity);
        phanTranglocal.refreshList(lstSanPhamCt);
        //
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        int index = 1;
        for (SanPhamChiTietEntity el : phanTranglocal.getListData(page)) {
            model.addRow(new Object[]{
                index,
                el.getMaSanPhamCt(),
                el.getSanPhamEntity().getTenSanPham(),
                el.getGiaSanPham(),
                el.getSoLuong(),
                el.getDanhMucEntity() != null ? el.getDanhMucEntity().getTenDanhMuc() : "",
                el.getNhaSanXuatEntity() != null ? el.getNhaSanXuatEntity().getTenNhaSanXuat() : "",
                el.getMauSacEntity() != null ? el.getMauSacEntity().getTenMauSac() : "",
                el.getKichCoEntity() != null ? el.getKichCoEntity().getTenKichCo() : "",
                el.getChatLieuEntity() != null ? el.getChatLieuEntity().getTenChatLieu() : "",
                Contants.getStatusBusiness(el.getTrangThai()),
                el.getAnhSanPham()
            });
            index++;
        }
        phanTranglocal.setButtonStatus();
        ButtonState();
        System.out.println("====================Table=====================");
        System.out.println(phanTranglocal.toString());
    }

    private void fillCboChiTiet() {
        lstDanhMuc = sanPhamChiTietIMPL.getListDanhMuc();
        lstNhaSx = sanPhamChiTietIMPL.getListNhaSx();
        lstKichCo = sanPhamChiTietIMPL.getListKichCo();
        lstMauSac = sanPhamChiTietIMPL.getListMauSac();
        lstChatLieu = sanPhamChiTietIMPL.getListChatLieu();
        if (lstDanhMuc.isEmpty()
                || lstNhaSx.isEmpty()
                || lstKichCo.isEmpty()
                || lstMauSac.isEmpty()
                || lstChatLieu.isEmpty()) {
            System.out.println("=================== ERROR ==============");
            System.out.println("Thuộc tính tồn tại 1 bảng trống");
        }
        DefaultComboBoxModel comboBoxModel;
        // ------
        comboBoxModel = (DefaultComboBoxModel) cboDanhMuc.getModel();
        comboBoxModel.removeAllElements();
        cboDanhMuc.removeAllItems();
        for (DanhMucEntity item : lstDanhMuc) {
            comboBoxModel.addElement(item);
        }
        // ------    
        comboBoxModel = (DefaultComboBoxModel) cboNsx.getModel();
        comboBoxModel.removeAllElements();
        cboNsx.removeAllItems();
        for (NhaSanXuatEntity item : lstNhaSx) {
            comboBoxModel.addElement(item);
        }
        // ------
        comboBoxModel = (DefaultComboBoxModel) cboKichCo.getModel();
        comboBoxModel.removeAllElements();
        cboKichCo.removeAllItems();
        for (KichCoEntity item : lstKichCo) {
            comboBoxModel.addElement(item);
        }
        // ------
        comboBoxModel = (DefaultComboBoxModel) cboChatLieu.getModel();
        comboBoxModel.removeAllElements();
        cboChatLieu.removeAllItems();
        for (ChatLieuEntity item : lstChatLieu) {
            comboBoxModel.addElement(item);
        }
        // ------
        comboBoxModel = (DefaultComboBoxModel) cboMauSac.getModel();
        comboBoxModel.removeAllElements();
        cboMauSac.removeAllItems();
        for (MauSacEntity item : lstMauSac) {
            comboBoxModel.addElement(item);
        }
    }

    public void fillCboTimKiem() {
        DefaultComboBoxModel comboBoxModel;
        // ------
        comboBoxModel = (DefaultComboBoxModel) cboLocDanhMuc.getModel();
        comboBoxModel.removeAllElements();
        cboLocDanhMuc.removeAllItems();
        DanhMucEntity danhMuc3 = new DanhMucEntity();
        danhMuc3.setTenDanhMuc("--Tất cả--");
        danhMuc3.setId(0);
        comboBoxModel.addElement(danhMuc3);
        for (DanhMucEntity item : lstDanhMuc) {
            comboBoxModel.addElement(item);
        }

        // ------
        comboBoxModel = (DefaultComboBoxModel) cboLocNhaSx.getModel();
        comboBoxModel.removeAllElements();
        cboLocNhaSx.removeAllItems();
        NhaSanXuatEntity nsxe = new NhaSanXuatEntity();
        nsxe.setId(0);
        nsxe.setTenNhaSanXuat("--Tất cả--");
        comboBoxModel.addElement(nsxe);
        for (NhaSanXuatEntity item : lstNhaSx) {
            comboBoxModel.addElement(item);
        }
    }

    public void fillCboStaus() {
        DefaultComboBoxModel comboBoxModel;
        comboBoxModel = (DefaultComboBoxModel) cboLocStatus.getModel();
        cboLocStatus.removeAllItems();
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

        cboLocStatus.setSelectedIndex(2);
    }

    public void fillCboFileLst() {
        listFileName = xFile.getListFile();
        DefaultComboBoxModel comboBoxModel;
        comboBoxModel = (DefaultComboBoxModel) cboListFileName.getModel();
        cboListFileName.removeAllItems();
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement(null);
        for (String item : listFileName) {
            comboBoxModel.addElement(item);
        }
    }

    public void clearFrom() {
        txtMaSpCt.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtMoTa.setText("");
        rdoDangBan.setSelected(true);
        cboDanhMuc.setSelectedIndex(0);
        cboKichCo.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboNsx.setSelectedIndex(0);
        anhChiTiet.setIcon(null);

        txtLocText.setText("");
        cboLocDanhMuc.setSelectedIndex(0);
        cboLocNhaSx.setSelectedIndex(0);
        cboLocStatus.setSelectedIndex(0);

        cboLocStatus.setSelectedIndex(2);

        fillCboFileLst();
    }

    private SanPhamChiTietEntity getSanPhamCtByMaSp(String maSp) {
        List<SanPhamChiTietEntity> lst = sanPhamChiTietIMPL.getAllByMaSpCt(maSp);
        for (SanPhamChiTietEntity item : lst) {
            if (item.getMaSanPhamCt().equalsIgnoreCase(maSp)) {
                return item;
            }
        }
        return null;
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

        XFile.save(img);
        imageName = img.getName();
        return XFile.getPath(img.getName());
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
            cell.setCellValue("Mã sản phẩm chi tiết");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên sản phẩm");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giá");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số lượng");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Danh mục");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Nhà sản xuất");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Màu sắc");
            cell.setCellStyle(borderStyle);
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Kích cỡ");
            cell.setCellStyle(borderStyle);
             cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Trạng thái");
            cell.setCellStyle(borderStyle);

//            for (int i = 0; i < lstSanPhamCt.size(); i++) {
//                SanPhamChiTietEntity item = lstSanPhamCt.get(i);
//                row = spreadsheet.createRow((short) 4 + i);
//                row.setHeight((short) 400);
//                Cell cell0 = row.createCell(0);
//                cell0.setCellValue(i + 1);
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(1);
//                cell0.setCellValue(item.getMaSanPhamCt());
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(2);
//                cell0.setCellValue(item.getSanPhamEntity().getTenSanPham());
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(3);
////                cell0.setCellValue(item.getGiaSanPham());
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(4);
//                cell0.setCellValue(Contants.getStatusBusiness(item.getTrangThai()));
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(4);
//                cell0.setCellValue(Contants.getStatusBusiness(item.getTrangThai()));
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(4);
//                cell0.setCellValue(Contants.getStatusBusiness(item.getTrangThai()));
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(4);
//                cell0.setCellValue(Contants.getStatusBusiness(item.getTrangThai()));
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(4);
//                cell0.setCellValue(Contants.getStatusBusiness(item.getTrangThai()));
//                cell0.setCellStyle(borderStyle); //
//                cell0 = row.createCell(4);
//                cell0.setCellValue(Contants.getStatusBusiness(item.getTrangThai()));
//                cell0.setCellStyle(borderStyle); //
//            }

            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkEquasProduct(SanPhamChiTietEntity objInput) {
        for (SanPhamChiTietEntity item : lstSanPhamCt) {
            if (item.getDanhMucEntity().getId() == objInput.getDanhMucEntity().getId()
                    && item.getKichCoEntity().getId() == objInput.getKichCoEntity().getId()
                    && item.getMauSacEntity().getId() == objInput.getMauSacEntity().getId()
                    && item.getChatLieuEntity().getId() == objInput.getChatLieuEntity().getId()
                    && item.getNhaSanXuatEntity().getId() == objInput.getNhaSanXuatEntity().getId()) {
                MsgBox.alert(this, "Đã tồn tại sản phẩm tương tự với MASPCT[" + item.getMaSanPhamCt() + "]");
                return true;
            }
        }
        return false;
    }

    private boolean checkEquasProduct(SanPhamChiTietEntity objInput, int id) {
        for (SanPhamChiTietEntity item : lstSanPhamCt) {
            if (item.getDanhMucEntity().getId() == objInput.getDanhMucEntity().getId()
                    && item.getKichCoEntity().getId() == objInput.getKichCoEntity().getId()
                    && item.getMauSacEntity().getId() == objInput.getMauSacEntity().getId()
                    && item.getChatLieuEntity().getId() == objInput.getChatLieuEntity().getId()
                    && item.getNhaSanXuatEntity().getId() == objInput.getNhaSanXuatEntity().getId()
                    && item.getId() != id) {
                MsgBox.alert(this, "Đã tồn tại sản phẩm tương tự với MASPCT[" + item.getMaSanPhamCt() + "]");
                return true;
            }
        }
        return false;
    }

    @Override
    public void closeDialog() {
        fillCboTimKiem();
        fillCboChiTiet();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cboDanhMuc = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMaSpCt = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnDanhMuc = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnKichCo = new javax.swing.JLabel();
        rdoDangBan = new javax.swing.JRadioButton();
        btnMauSac = new javax.swing.JLabel();
        rdoTamNgung = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        btnNhaSx = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboNsx = new javax.swing.JComboBox<>();
        cboKichCo = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        anhChiTiet = new javax.swing.JLabel();
        btnUpLoad1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtSanPham = new javax.swing.JTextField();
        btnChatLieu = new javax.swing.JLabel();
        cboListFileName = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btNhoMax = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btLonMax = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboLocDanhMuc = new javax.swing.JComboBox<>();
        txtLocText = new javax.swing.JTextField();
        cboLocNhaSx = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cboLocStatus = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chi tiết sản phẩm");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm chi tiết"));

        jLabel9.setText("Số Lượng");

        jLabel10.setText("Giá");

        jLabel11.setText("Mô Tả");

        jLabel8.setText("Mã sản phẩm chi tiết");

        txtMaSpCt.setEditable(false);

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        btnDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDanhMucMouseClicked(evt);
            }
        });

        jLabel12.setText("Trạng Thái");

        btnKichCo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnKichCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKichCoMouseClicked(evt);
            }
        });

        rdoDangBan.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoDangBan);
        rdoDangBan.setText("Đang kinh doanh");
        rdoDangBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangBanActionPerformed(evt);
            }
        });

        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMauSacMouseClicked(evt);
            }
        });

        rdoTamNgung.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(rdoTamNgung);
        rdoTamNgung.setText("Ngừng kinh doanh");

        jLabel2.setText("Sản Phẩm");

        btnNhaSx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnNhaSx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhaSxMouseClicked(evt);
            }
        });

        jLabel3.setText("Danh Mục");

        jLabel4.setText("Chất Liệu");

        jLabel5.setText("Màu Sắc");

        jLabel6.setText("Kích cỡ");

        jLabel7.setText("Nhà Sản Xuất");

        anhChiTiet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anhChiTiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnUpLoad1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpLoad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/UpLoad_Image.png"))); // NOI18N
        btnUpLoad1.setText("UpLoad");
        btnUpLoad1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpLoad1MouseClicked(evt);
            }
        });
        btnUpLoad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpLoad1ActionPerformed(evt);
            }
        });

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
                .addContainerGap(579, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat)
                    .addComponent(btnReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtSanPham.setEditable(false);

        btnChatLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/da1/Icon/icons8-add-new-32.png"))); // NOI18N
        btnChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChatLieuMouseClicked(evt);
            }
        });

        cboListFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboListFileNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnUpLoad1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboListFileName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cboNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNhaSx, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChatLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboDanhMuc, 0, 208, Short.MAX_VALUE)
                            .addComponent(txtMaSpCt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSanPham, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGia))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoDangBan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoTamNgung))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong)))
                .addGap(6, 6, 6))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel9});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboChatLieu, cboNsx});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtMaSpCt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addComponent(btnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addComponent(btnKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoTamNgung)
                                    .addComponent(rdoDangBan)
                                    .addComponent(jLabel12))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(cboNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnNhaSx, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(11, Short.MAX_VALUE)
                        .addComponent(cboListFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpLoad1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel9});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnChatLieu, btnDanhMuc, btnKichCo, btnMauSac, btnNhaSx, cboChatLieu, cboDanhMuc, cboKichCo, cboMauSac, cboNsx, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, txtGia, txtMaSpCt, txtSoLuong});

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Danh mục", "Nhà sản xuất", "Màu sắc", "Kích cỡ", "Chất liệu", "Trạng Thái", "Ảnh sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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

        lbTrang.setText("jLabel4");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(btNhoMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btLonMax)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Lọc Sản Phẩm"));

        jLabel16.setText("Mã sản phẩm chi tiết");

        jLabel17.setText("Danh mục");

        cboLocDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocDanhMucActionPerformed(evt);
            }
        });

        txtLocText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocTextKeyReleased(evt);
            }
        });

        cboLocNhaSx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocNhaSxActionPerformed(evt);
            }
        });

        jLabel18.setText("Nhà sản xuất");

        cboLocStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocStatusActionPerformed(evt);
            }
        });

        jLabel19.setText("Trạng thái");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtLocText, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(cboLocNhaSx, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(cboLocStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboLocDanhMuc, cboLocNhaSx, cboLocStatus, txtLocText});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocNhaSx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLocText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboLocDanhMuc, cboLocNhaSx, cboLocStatus, txtLocText});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int index = tblDanhSach.getSelectedRow();
        if (index < 0) {
            return;
        }
        txtMaSpCt.setText(tblDanhSach.getValueAt(index, 1).toString());
        txtGia.setText(tblDanhSach.getValueAt(index, 3).toString());
        txtSoLuong.setText(tblDanhSach.getValueAt(index, 4).toString());
        txtMoTa.setText(lstSanPhamCt.get(index).getMoTa());
        //--
        DanhMucEntity danhMucEntity = lstDanhMuc
                .stream()
                .filter(item -> item.getTenDanhMuc().equals(tblDanhSach.getValueAt(index, 5).toString()))
                .findFirst()
                .orElse(null);
        cboDanhMuc.setSelectedItem(danhMucEntity);
        //--
        NhaSanXuatEntity nhaSanXuatEntity = lstNhaSx
                .stream()
                .filter(item -> item.getTenNhaSanXuat().equals(tblDanhSach.getValueAt(index, 6).toString()))
                .findFirst()
                .orElse(null);
        cboNsx.setSelectedItem(nhaSanXuatEntity);
        //--
        MauSacEntity mauSacEntity = lstMauSac
                .stream()
                .filter(item -> item.getTenMauSac().equals(tblDanhSach.getValueAt(index, 7).toString()))
                .findFirst()
                .orElse(null);
        cboMauSac.setSelectedItem(mauSacEntity);
        //--
        KichCoEntity kichCoEntity = lstKichCo
                .stream()
                .filter(item -> item.getTenKichCo().equals(tblDanhSach.getValueAt(index, 8).toString()))
                .findFirst()
                .orElse(null);
        cboKichCo.setSelectedItem(kichCoEntity);
        //--
        ChatLieuEntity chatLieuEntity = lstChatLieu
                .stream()
                .filter(item -> item.getTenChatLieu().equals(tblDanhSach.getValueAt(index, 9).toString()))
                .findFirst()
                .orElse(null);
        cboChatLieu.setSelectedItem(chatLieuEntity);
        //--
        if (tblDanhSach.getValueAt(index, 10).toString().equals("Đang kinh doanh")) {
            rdoDangBan.setSelected(true);
        } else {
            rdoTamNgung.setSelected(true);
        }
        //--
        imageName = tblDanhSach.getValueAt(index, 11) == null ? "" : tblDanhSach.getValueAt(index, 11).toString();
        if (imageName != null) {
            showImage(XFile.getPath(imageName));
            cboListFileName.setSelectedItem(imageName);
        } else {
            anhChiTiet.setIcon(null);
            cboListFileName.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoMaxActionPerformed

    }//GEN-LAST:event_btNhoMaxActionPerformed

    private void btLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonMaxActionPerformed

    }//GEN-LAST:event_btLonMaxActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (ValidateEx.checkIsNull(txtSoLuong)) {
            MsgBox.alert(this, "Số lượng bắt buộc nhập");
            return;
        } else if (ValidateEx.checkIsNumber(txtSoLuong)) {
            MsgBox.alert(this, "Số lượng phải là kiểu số");
            return;
        } else if (ValidateEx.checkMoreThan(txtSoLuong, 0)) {
            MsgBox.alert(this, "Số lượng phải lớn hơn 0");
            return;
        }

        try {
            Integer.valueOf(txtSoLuong.getText().trim());
        } catch (Exception e) {
            MsgBox.alert(this, "Số lượng không đúng định dạng, xem lại");
            return;
        }

        if (ValidateEx.checkIsNull(txtGia)) {
            MsgBox.alert(this, "Giá bán bắt buộc nhập");
            return;
        } else if (ValidateEx.checkIsFloat(txtGia)) {
            MsgBox.alert(this, "Giá bán phải là kiểu số");
            return;
        }

        try {
            new BigDecimal(txtGia.getText().trim());
        } catch (Exception e) {
            MsgBox.alert(this, "Giá bán không đúng định dạng, xem lại");
            return;
        }

        BigDecimal gia = new BigDecimal(txtGia.getText().trim());
        if (gia.compareTo(BigDecimal.ZERO) < 0) {
            MsgBox.alert(this, "Giá bán không thể là số âm");
            return;
        }

        DanhMucEntity danhMucTmp = ((DanhMucEntity) cboDanhMuc.getSelectedItem());
        NhaSanXuatEntity nhaSanXuatTmp = ((NhaSanXuatEntity) cboNsx.getSelectedItem());
        MauSacEntity mauSacTmp = ((MauSacEntity) cboMauSac.getSelectedItem());
        ChatLieuEntity chatLieuTmp = ((ChatLieuEntity) cboChatLieu.getSelectedItem());
        KichCoEntity kichCoTmp = ((KichCoEntity) cboKichCo.getSelectedItem());

        if (danhMucTmp == null) {
            MsgBox.alert(this, "Danh mục bắt buộc chọn");
            return;
        } else if (nhaSanXuatTmp == null) {
            MsgBox.alert(this, "Nhà sản xuất bắt buộc chọn");
            return;
        } else if (mauSacTmp == null) {
            MsgBox.alert(this, "Màu sắc bắt buộc chọn");
            return;
        } else if (kichCoTmp == null) {
            MsgBox.alert(this, "Kích cỡ bắt buộc chọn");
            return;
        } else if (chatLieuTmp == null) {
            MsgBox.alert(this, "Chất liệu bắt buộc chọn");
            return;
        }

        List<SanPhamChiTietEntity> lstTmp = sanPhamChiTietIMPL.GetAll();
        int idTmp = 0;
        if (lstTmp.size() > 0) {
            idTmp = lstTmp.get(lstTmp.size() - 1).getId() + 1;
        } else {
            idTmp = 1;
        }

        SanPhamChiTietEntity sanPhamChiTiet = new SanPhamChiTietEntity();
        sanPhamChiTiet.setMaSanPhamCt("SPCT" + idTmp);
        sanPhamChiTiet.setGiaSanPham(gia);
        sanPhamChiTiet.setAnhSanPham(imageName);
        sanPhamChiTiet.setSoLuong(Integer.valueOf(txtSoLuong.getText().trim()));
        sanPhamChiTiet.setMoTa(txtMoTa.getText());
        sanPhamChiTiet.setSanPhamEntity(sanPhamEntity);
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

        if (checkEquasProduct(sanPhamChiTiet)) {
            return;
        }

        SanPhamChiTietEntity objTemp = sanPhamChiTietIMPL.save(sanPhamChiTiet);
        if (objTemp == null) {
            MsgBox.alert(this, "Thêm mới không thành công");
            return;
        } else {
            MsgBox.alert(this, "Thêm mới thành công");
        }

        clearFrom();
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed

        if (ValidateEx.checkIsNull(txtMaSpCt)) {
            MsgBox.alert(this, "Mã sản phẩm chi tiết bắt buộc nhập");
            return;
        }

        if (ValidateEx.checkIsNull(txtSoLuong)) {
            MsgBox.alert(this, "Số lượng bắt buộc nhập");
            return;
        } else if (ValidateEx.checkIsNumber(txtSoLuong)) {
            MsgBox.alert(this, "Số lượng phải là kiểu số");
            return;
        } else if (ValidateEx.checkMoreThan(txtSoLuong, 0)) {
            MsgBox.alert(this, "Số lượng phải lớn hơn 0");
            return;
        }

        try {
            Integer.valueOf(txtSoLuong.getText().trim());
        } catch (Exception e) {
            MsgBox.alert(this, "Số lượng không đúng định dạng, xem lại");
            return;
        }

        if (ValidateEx.checkIsNull(txtGia)) {
            MsgBox.alert(this, "Giá bán bắt buộc nhập");
            return;
        } else if (ValidateEx.checkIsFloat(txtGia)) {
            MsgBox.alert(this, "Giá bán phải là kiểu số");
            return;
        }

        try {
            new BigDecimal(txtGia.getText().trim());
        } catch (Exception e) {
            MsgBox.alert(this, "Giá bán không đúng định dạng, xem lại");
            return;
        }

        BigDecimal gia = new BigDecimal(txtGia.getText());
        if (gia.compareTo(BigDecimal.ZERO) < 0) {
            MsgBox.alert(this, "Giá bán không thể là số âm");
            return;
        }

        DanhMucEntity danhMucTmp = ((DanhMucEntity) cboDanhMuc.getSelectedItem());
        NhaSanXuatEntity nhaSanXuatTmp = ((NhaSanXuatEntity) cboNsx.getSelectedItem());
        MauSacEntity mauSacTmp = ((MauSacEntity) cboMauSac.getSelectedItem());
        ChatLieuEntity chatLieuTmp = ((ChatLieuEntity) cboChatLieu.getSelectedItem());
        KichCoEntity kichCoTmp = ((KichCoEntity) cboKichCo.getSelectedItem());

        if (danhMucTmp == null) {
            MsgBox.alert(this, "Danh mục bắt buộc chọn");
            return;
        } else if (nhaSanXuatTmp == null) {
            MsgBox.alert(this, "Nhà sản xuất bắt buộc chọn");
            return;
        } else if (mauSacTmp == null) {
            MsgBox.alert(this, "Màu sắc bắt buộc chọn");
            return;
        } else if (kichCoTmp == null) {
            MsgBox.alert(this, "Kích cỡ bắt buộc chọn");
            return;
        } else if (chatLieuTmp == null) {
            MsgBox.alert(this, "Chất liệu bắt buộc chọn");
            return;
        }

        if (getSanPhamCtByMaSp(txtMaSpCt.getText()) == null) {
            MsgBox.alert(this, "Mã sản phẩm chi tiết không tồn tại");
            return;
        }

        SanPhamChiTietEntity sanPhamChiTiet = new SanPhamChiTietEntity();
        sanPhamChiTiet.setId(getSanPhamCtByMaSp(txtMaSpCt.getText()).getId());
        sanPhamChiTiet.setMaSanPhamCt(txtMaSpCt.getText());
        sanPhamChiTiet.setGiaSanPham(gia);
        sanPhamChiTiet.setMoTa(txtMoTa.getText());
        sanPhamChiTiet.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sanPhamChiTiet.setSanPhamEntity(sanPhamEntity);
        sanPhamChiTiet.setDanhMucEntity(danhMucTmp);
        sanPhamChiTiet.setNhaSanXuatEntity(nhaSanXuatTmp);
        sanPhamChiTiet.setMauSacEntity(mauSacTmp);
        sanPhamChiTiet.setChatLieuEntity(chatLieuTmp);
        sanPhamChiTiet.setKichCoEntity(kichCoTmp);
        sanPhamChiTiet.setAnhSanPham(imageName);
        if (rdoDangBan.isSelected()) {
            sanPhamChiTiet.setTrangThai(1);
        } else {
            sanPhamChiTiet.setTrangThai(0);
        }

        if (checkEquasProduct(sanPhamChiTiet, sanPhamChiTiet.getId())) {
            return;
        }

        int check = sanPhamChiTietIMPL.update(sanPhamChiTiet);
        if (check == 0) {
            MsgBox.alert(this, "Cập nhật không thành công");
            return;
        } else {
            MsgBox.alert(this, "Cập nhật thành công");
        }

        clearFrom();
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
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

    private void btnUpLoad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpLoad1MouseClicked

    }//GEN-LAST:event_btnUpLoad1MouseClicked

    private void btnUpLoad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpLoad1ActionPerformed
        imageUrl = getImageUrl();
        showImage(imageUrl);
    }//GEN-LAST:event_btnUpLoad1ActionPerformed

    private void cboLocDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocDanhMucActionPerformed
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_cboLocDanhMucActionPerformed

    private void txtLocTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocTextKeyReleased
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_txtLocTextKeyReleased

    private void cboLocNhaSxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocNhaSxActionPerformed
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_cboLocNhaSxActionPerformed

    private void cboLocStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocStatusActionPerformed
        // TODO add your handling code here:
        fillTableWhenSearch(Contants.PhanTrang.DEFAULT_PAGE.getValue());
    }//GEN-LAST:event_cboLocStatusActionPerformed

    private void cboListFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboListFileNameActionPerformed
        // TODO add your handling code here:
        imageName = (String) cboListFileName.getSelectedItem();
        if (imageName != null) {
            showImage(XFile.getPath(imageName));
        } else {
            anhChiTiet.setIcon(null);
        }
    }//GEN-LAST:event_cboListFileNameActionPerformed

    private void btNhoMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNhoMaxMouseClicked
        if (phanTranglocal.getIsPrev()) {
            fillTableWhenSearch(phanTranglocal.getPage() - 1);
        }
    }//GEN-LAST:event_btNhoMaxMouseClicked

    private void btLonMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLonMaxMouseClicked
        if (phanTranglocal.getIsNext()) {
            fillTableWhenSearch(phanTranglocal.getPage() + 1);
        }
    }//GEN-LAST:event_btLonMaxMouseClicked

    private void rdoDangBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDangBanActionPerformed

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
            java.util.logging.Logger.getLogger(FormSanPhamCt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamCt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamCt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSanPhamCt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel anhChiTiet;
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
    private javax.swing.JButton btnUpLoad1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboListFileName;
    private javax.swing.JComboBox<String> cboLocDanhMuc;
    private javax.swing.JComboBox<String> cboLocNhaSx;
    private javax.swing.JComboBox<String> cboLocStatus;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNsx;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JRadioButton rdoDangBan;
    private javax.swing.JRadioButton rdoTamNgung;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtLocText;
    private javax.swing.JTextField txtMaSpCt;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSanPham;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
