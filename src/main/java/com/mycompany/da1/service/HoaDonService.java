package com.mycompany.da1.service;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.KhachHangEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public interface HoaDonService {
   void Save(HoaDonEntity hoaDonEntity);
   ArrayList<HoaDonEntity> GetAll();
   ArrayList<HoaDonEntity>ThanhToan(int idHoaDon);
   void UpdateHoaDon(int idHoaDon, BigDecimal tongTien);
   void  UpDateHuyHD(int idHoaDon);
   BigDecimal tongTienTuNgayDenNgay(Timestamp ngayBatDau, Timestamp ngayKetThuc);
}
