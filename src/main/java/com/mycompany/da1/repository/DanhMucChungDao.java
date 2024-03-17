package com.mycompany.da1.repository;

import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.util.HibernateUltil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author sonst
 */
public class DanhMucChungDao<T> {

    public ArrayList<T> getAll(Class<T> entityType) {
        ArrayList<T> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            listData = (ArrayList<T>) session.createQuery("from " + entityType.getSimpleName()).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public T Save(T objInput) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(objInput);
            session.flush();
            session.refresh(objInput);
            transaction.commit();
            return objInput;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int updateSanPham(Class<T> entityType, String tenDanhMuc, int id, int trangThai, String value) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            String entityName = entityType.getSimpleName();
            session.beginTransaction();
            String sql = "UPDATE "
                    + entityName
                    + " SET trangThai = :trangThai,"
                    + tenDanhMuc + " = :" + tenDanhMuc
                    + " WHERE id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("trangThai", trangThai);
            query.setParameter(tenDanhMuc, value);
            query.setParameter("id", id);
            int check = query.executeUpdate();
            session.getTransaction().commit();
            return check;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
