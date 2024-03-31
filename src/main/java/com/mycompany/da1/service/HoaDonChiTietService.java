package com.mycompany.da1.service;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.KhachHangEntity;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface HoaDonChiTietService {
    ArrayList<HoaDonChiTietEntity> GetAll(int idHoaDon);
    void Save (HoaDonChiTietEntity hoaDonChiTietEntity, int soLuong, BigDecimal newTongTien);
    void  DeleteHDCT(int idHoaDon,int idSP);
    BigDecimal SumtongTien(int idHoaDon);
    int traVeSoluongSP(int idHoaDon,int idSanPhamCT);
    void UpdateTongTien(int idHoaDon,int idSanPhamCT,BigDecimal tongTien);
    int DeSoLuongHoaDonChiTiet(int idHoaDon);


}
