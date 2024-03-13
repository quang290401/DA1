package com.mycompany.da1.repository;

import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class VoucherDAO {
    public ArrayList<VoucherEntity> GetList() {
        ArrayList<VoucherEntity> voucherEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from VoucherEntity where trangThai =1");
            voucherEntities = (ArrayList<VoucherEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucherEntities;
    }

}
