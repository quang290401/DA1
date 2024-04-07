package com.mycompany.da1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "VaiTro")
public class VaiTroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tenVaiTro")
    private String tenVaiTro;
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
        return tenVaiTro;
    }
}
