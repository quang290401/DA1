package com.mycompany.da1.repository;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class HoaDonDAO {
    public void Save(HoaDonEntity hoaDonEntity) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(hoaDonEntity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public ArrayList<HoaDonEntity> GetList() {
        ArrayList<HoaDonEntity> hoaDonEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from HoaDonEntity where trangThai = 0");
            hoaDonEntities = (ArrayList<HoaDonEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonEntities;
    }
}
