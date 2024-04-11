package com.mycompany.da1.view;

import com.mycompany.da1.util.MsgBox;
import com.mycompany.da1.util.UserSession;
import com.mycompany.da1.view.events.EventMenuSelected;
import com.mycompany.da1.view.form.bill_manager.QuanLyHoaDon;
import com.mycompany.da1.view.form.client.FormKhachHang;
import com.mycompany.da1.view.form.nhan_vien.FormNhanVien;
import com.mycompany.da1.view.form.product.FormSanPham;
import com.mycompany.da1.view.form.sales.FormBanHang;
import com.mycompany.da1.view.form.sales.FormVoucher2;
import com.mycompany.da1.view.form.temp.ViewDemo;
import com.mycompany.da1.view.swing.ScrollBar;

import java.awt.Color;
import javax.swing.JComponent;

/**
 *
 * @author sonst
 */
public class JFrameMain extends javax.swing.JFrame {

    public JFrameMain() {
        initComponents();

        setBackground(new Color(0, 0, 0));
        menu.initMoving(JFrameMain.this);
        sb.setVerticalScrollBar(new ScrollBar());
        // Đặt chế độ đóng cửa sổ
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForm(new FormSanPham());

        menu.addEventMenuSelected(new EventMenuSelected() {
            // Muốn setForm thì form degsin = JPanel mới add được
            // thêm thì dùng hàm setForm với class panel là tham số tryền vào
            // tạo class jpanel ở trong com.mycompany.da1.view.form.xxx; với xxx là tên chức năng
            // ví dụ chức năng sản phẩm tạo package là procudt => com.mycompany.da1.view.form.product
            @Override
            public void selected(int index) {
                System.out.println("Select index = " + index);
                if (UserSession.getInstance().checkAdmin()) {
                    if (index == 0) {  // Trang chủ    
                        setForm(new ViewDemo());
                    } else if (index == 2) { // Bán hàng
                        setForm(new FormBanHang());
                    } else if (index == 4) { // Sản phẩm
                        setForm(new FormSanPham());
                    } else if (index == 6) {    // Nhân viên
                        setForm(new FormNhanVien());
                    } else if (index == 8) {    //Khách hàng
                        setForm(new FormKhachHang());
                    } else if (index == 10) {   // Hóa đơn
                        setForm(new QuanLyHoaDon());
                    } else if (index == 12) {   // Khuyến mãi
                        setForm(new FormVoucher2());
                    } else if (index == 14) { // Đăng xuất
                        if (MsgBox.confirm(sb, "Bạn có chắc chắn muốn đăng xuất!")) {
                            UserSession.getInstance().setAccountSession(null);
                            new DangNhap().setVisible(true);
                            setVisible(false);
                        }
                    }
                } else {
                    if (index == 0) {  // Trang chủ    
                        setForm(new ViewDemo());
                    } else if (index == 2) { // Bán hàng
                        setForm(new FormBanHang());
                    } else if (index == 4) {    //Khách hàng
                        setForm(new FormKhachHang());
                    } else if (index == 6) {   // Hóa đơn
                        setForm(new QuanLyHoaDon());
                    } else if (index == 8) { // Đăng xuất
                        if (MsgBox.confirm(sb, "Bạn có chắc chắn muốn đăng xuất!")) {
                            UserSession.getInstance().setAccountSession(null);
                            new DangNhap().setVisible(true);
                            setVisible(false);
                        }
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        menu = new com.mycompany.da1.view.components.JPanelMenu();
        sb = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" WELLCOME TO SHOP");

        sb.setBorder(null);
        sb.setOpaque(false);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());
        sb.setViewportView(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sb, javax.swing.GroupLayout.DEFAULT_SIZE, 1242, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sb)
                .addContainerGap())
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMain().setVisible(true);
            }
        });
    }

    public void setForm(JComponent component) {
        mainPanel.removeAll();
        mainPanel.add(component);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private com.mycompany.da1.view.components.JPanelMenu menu;
    private javax.swing.JScrollPane sb;
    // End of variables declaration//GEN-END:variables
}
