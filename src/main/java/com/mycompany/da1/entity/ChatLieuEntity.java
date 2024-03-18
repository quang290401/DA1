package com.mycompany.da1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ChatLieu")
public class ChatLieuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tenChatLieu", columnDefinition = "NVARCHAR(255)")
    private String tenChatLieu;
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
        return tenChatLieu;
    }
}

