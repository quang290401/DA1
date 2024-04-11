package com.mycompany.da1.service;

import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;

import java.util.ArrayList;

public interface KhachhangService {
    ArrayList<KhachHangEntity> GetAll();
    KhachHangEntity save(KhachHangEntity objInput);
    int TraVeKhachMoiThem();
    boolean KiemTraSDTKhach(String sdt);
}
