package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.repository.HoaDonDAO;
import com.mycompany.da1.service.HoaDonService;

import java.math.BigDecimal;
import java.util.ArrayList;

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

}
