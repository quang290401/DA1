package com.mycompany.da1.repository;

import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class TaiKhoanDAO {
    public ArrayList<TaiKhoanEntity> GetList() {
        ArrayList<TaiKhoanEntity> taiKhoanEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from TaiKhoanEntity where trangThai =1");
            taiKhoanEntities = (ArrayList<TaiKhoanEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taiKhoanEntities;
    }
}
