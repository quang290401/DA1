package com.mycompany.da1.repository;

import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.util.HibernateUltil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.ArrayList;

public class SanPhamChiTietDAO {
    public ArrayList<SanPhamChiTietEntity> GetList() {
        ArrayList<SanPhamChiTietEntity> sanPhamChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            sanPhamChiTietEntities = (ArrayList<SanPhamChiTietEntity>) session.createQuery("from SanPhamChiTietEntity ").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhamChiTietEntities ;
    }
}
