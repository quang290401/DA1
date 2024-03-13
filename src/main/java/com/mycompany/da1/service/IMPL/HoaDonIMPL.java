package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.repository.HoaDonDAO;
import com.mycompany.da1.service.HoaDonService;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

}
