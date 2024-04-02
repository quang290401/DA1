package com.mycompany.da1.repository;

import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
    public int getIdNhanVienByUsername(String username) {
        int idNhanVien = 0;
        Session session = HibernateUltil.getFACTORY().openSession();
        try {
            // Sử dụng HQL để truy vấn
            String hql = "SELECT e.id FROM TaiKhoanEntity e WHERE e.taiKhoan = :username";
            Query<Integer> query = session.createQuery(hql);
            query.setParameter("username", username);
            List<Integer> results = query.getResultList();
            if (!results.isEmpty()) {
                // Nếu có kết quả, lấy idNhanVien đầu tiên
                idNhanVien = results.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return idNhanVien;
    }
}
