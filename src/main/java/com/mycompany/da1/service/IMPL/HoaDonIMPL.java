package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.repository.HoaDonDAO;
import com.mycompany.da1.service.HoaDonService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonIMPL implements HoaDonService {
    HoaDonDAO hoaDonDAO = new HoaDonDAO();
    @Override
    public void Save(HoaDonEntity hoaDonEntity) {
        hoaDonDAO.Save(hoaDonEntity);
    }

    @Override
    public ArrayList<HoaDonEntity> GetAll() {
        ArrayList<HoaDonEntity>hoaDonEntities = hoaDonDAO.GetList();
        return hoaDonEntities;
    }

    @Override
    public ArrayList<HoaDonEntity> ThanhToan(int idHoaDon) {
        ArrayList<HoaDonEntity>hoaDonEntities = hoaDonDAO.ThanhToan(idHoaDon);
        return hoaDonEntities;
    }

    @Override
    public void UpdateHoaDon(int idHoaDon, BigDecimal tongTien) {
         hoaDonDAO.UpdateHoaDon(idHoaDon,tongTien);

    }

    @Override
    public void UpDateHuyHD(int idHoaDon) {
      hoaDonDAO.UpdateHuyHD(idHoaDon);
    }

    @Override
    public BigDecimal tongTienTuNgayDenNgay(Timestamp ngayBatDau, Timestamp ngayKetThuc) {
        return hoaDonDAO.tongTienTuNgayDenNgay(ngayBatDau,ngayKetThuc);
    }

}
