package com.mycompany.da1.util;

// Nơi chứa dữ liệu của use suốt quá trình đăng nhập
// Cách gọi Ex: UserSession.getInstance().getUsername()
public class UserSession {
    private static UserSession instance;
    private int id;
    private String username;
    private String name;
    private String password;
    private String email;
    private String phonenumber;
    private String maVaiTro;
    private String maNv;
    private int idhinhAnh;
    private String diaChi;
    
    private UserSession() {
    }
    
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    
    // có thể thêm setAllData nếu cần

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(String maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public int getIdhinhAnh() {
        return idhinhAnh;
    }

    public void setIdhinhAnh(int idhinhAnh) {
        this.idhinhAnh = idhinhAnh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
