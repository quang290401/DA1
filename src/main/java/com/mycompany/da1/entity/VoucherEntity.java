package com.mycompany.da1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "Voucher")
public class VoucherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "phanTramGiam")
    private int phanTramGiam;
    @Column(name = "ngayBatDau")
    private LocalDate ngayBatDau;
    @Column(name = "ngayKetThuc")
    private LocalDate ngayKetThuc;
    @Column(name = "trangThai")
    private int trangThai;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngaySua")
    private Date ngaySua;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngayTao")
    private Date ngayTao;


    @Override
    public String toString(){
        return  ""+id;
    }
}

