package com.mycompany.da1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "SanPhamChiTiet")
public class SanPhamChiTietEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "giaSanPham")
    private BigDecimal giaSanPham;

    @Column(name = "soLuong")
    private int soLuong;

    @Column(name = "moTa", columnDefinition = "NVARCHAR(255)")
    private String moTa;

    @Column(name = "anhSanPham")
    private String anhSanPham;
    
    @Column(name = "maSanPhamCt")
    private String maSanPhamCt;

    @ManyToOne
    @JoinColumn(name = "SanPham_id")
    private SanPhamEntity sanPhamEntity;
    @ManyToOne
    @JoinColumn(name = "MauSac_id")
    private MauSacEntity mauSacEntity;
    @ManyToOne
    @JoinColumn(name = "DanhMuc_id")
    private DanhMucEntity danhMucEntity;
    @ManyToOne
    @JoinColumn(name = "ChatLieu_id")
    private ChatLieuEntity chatLieuEntity;

    @ManyToOne
    @JoinColumn(name = "NhaSanXuat_id")
    private NhaSanXuatEntity nhaSanXuatEntity;
    @ManyToOne
    @JoinColumn(name = "KichCo_id")
    private KichCoEntity kichCoEntity;
    @Column(name = "trangThai")
    private int trangThai;
}
