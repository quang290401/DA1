package com.mycompany.da1.repository;

import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;

import java.util.ArrayList;

public class KhachHangDAO {
    public ArrayList<KhachHangEntity> GetList() {
        ArrayList<KhachHangEntity> khachHangEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            khachHangEntities = (ArrayList<KhachHangEntity>) session.createQuery("from KhachHangEntity ").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHangEntities ;
    }
}
