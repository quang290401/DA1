package com.mycompany.da1.util;

// Nơi chứa dữ liệu của use suốt quá trình đăng nhập
import com.mycompany.da1.entity.TaiKhoanEntity;

// Cách gọi Ex: UserSession.getInstance().getUsername()
public class UserSession {
    private static UserSession instance;
    private TaiKhoanEntity accountSession;
    
    private UserSession() {
    }
    
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    
    // có thể thêm setAllData nếu cần

    public TaiKhoanEntity getAccountSession() {
        return accountSession;
    }

    public void setAccountSession(TaiKhoanEntity accountSession) {
        this.accountSession = accountSession;
    } 
    
    public boolean checkAdmin() {
        return this.accountSession.getVaiTroEntity().getTenVaiTro().equalsIgnoreCase("admin");
    }
}
