package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.repository.HoaDonChiTietDAO;
import com.mycompany.da1.repository.HoaDonDAO;
import com.mycompany.da1.service.HoaDonChiTietService;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;

public class HoaDonChiTietIMPL implements HoaDonChiTietService {
    HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO();
    @Override
    public ArrayList<HoaDonChiTietEntity> GetAll(int idHoaDon) {
       ArrayList<HoaDonChiTietEntity>hoaDonChiTietEntities = hoaDonChiTietDAO.GetList(idHoaDon);
       return hoaDonChiTietEntities;
    }

    @Override
    public void Save(HoaDonChiTietEntity hoaDonChiTietEntity, int soluong, BigDecimal newTongTien) {
     hoaDonChiTietDAO.Save(hoaDonChiTietEntity,soluong,newTongTien);

    }

    @Override
    public void DeleteHDCT(int idHoaDon,int idSP) {

        hoaDonChiTietDAO.deleteByHoaDonIdAndProductId(idHoaDon,idSP);
    }

    @Override
    public BigDecimal SumtongTien(int idHoaDon) {
      return  hoaDonChiTietDAO.getTotalTongTienByHoaDonId(idHoaDon);
    }

}
