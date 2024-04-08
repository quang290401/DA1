package com.mycompany.da1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "taiKhoan")
    private String taiKhoan;
    @Column(name = "matKhau")
    private String matKhau;
    @ManyToOne
    @JoinColumn(name = "vaiTro_id")
    private VaiTroEntity vaiTroEntity;
    @Column(name = "hoTen", columnDefinition = "NVARCHAR(255)")
    private String hoTen;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngayTao")
    private Date ngayTao;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngaySua")
    private Date ngaySua;
    @Column(name = "trangThai")
    private int trangThai;
    @Column(name = "diaChi", columnDefinition = "NVARCHAR(255)")
    private String diaChi;
    @Column(name = "ngaySinh")
    private Date ngaySinh;
    @Column(name = "gioiTinh", columnDefinition = "NVARCHAR(255)")
    private String gioiTinh;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "cccd")
    private String cccd;
    @Column(name = "maTaiKhoan")
    private String maTK;

    @Override
    public String toString() {
        return hoTen + trangThai;
    }
}
