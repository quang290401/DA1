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
    @Column(name = "hoTen")
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

    @Override
    public String toString() {
        return "TaiKhoanEntity{" +
                "id=" + id +
                ", taiKhoan='" + taiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", vaiTroEntity=" + vaiTroEntity.getTenVaiTro() +
                '}';
    }
}
