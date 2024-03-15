package com.mycompany.da1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "HoaDon")
public class HoaDonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @Column(name = "tongTien")
    private BigDecimal tongTien;
    @ManyToOne
    @JoinColumn(name = "TaiKhoan_id")
    private TaiKhoanEntity taiKhoanEntity;
    @ManyToOne
    @JoinColumn(name = "KhachHang_id")
    private KhachHangEntity khachHangEntity;
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private VoucherEntity voucherEntity;
}
