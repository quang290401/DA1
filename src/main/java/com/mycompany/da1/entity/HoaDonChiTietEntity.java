package com.mycompany.da1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTietEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "hoaDon_id")
    private HoaDonEntity hoaDonEntity;
    @Id
    @ManyToOne
    @JoinColumn(name = "sanPhamChitiet_id")
    private SanPhamChiTietEntity sanPhamChiTietEntity;
    @Column(name = "tongTien")
    private BigDecimal tongTien;
    @Column(name = "soLuong")
    private int soLuong;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngaySua")
    private Date ngaySua;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngayTao")
    private Date ngayTao;
}
