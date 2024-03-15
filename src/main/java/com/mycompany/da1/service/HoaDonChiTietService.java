package com.mycompany.da1.service;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.KhachHangEntity;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface HoaDonChiTietService {
    ArrayList<HoaDonChiTietEntity> GetAll(int idHoaDon);
    void Save (HoaDonChiTietEntity hoaDonChiTietEntity, int soLuong, BigDecimal newTongTien);
    void  DeleteHDCT(int idHoaDon);
    BigDecimal SumtongTien(int idHoaDon);


}
