package com.mycompany.da1.repository;

import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.util.HibernateUltil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author sonst
 */
public class SanPhamDao {

    public ArrayList<SanPhamEntity> GetList() {
        ArrayList<SanPhamEntity> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            listData = (ArrayList<SanPhamEntity>) session.createQuery("from SanPhamEntity order by id asc").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public List<SanPhamEntity> getSearch(String textSearch, int status) {
        List<SanPhamEntity> lstData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query<SanPhamEntity> query = session
                    .createQuery(
                            "from SanPhamEntity where"
                            + " (tenSanPham LIKE :textSearch OR maSanPham LIKE :textSearch)  And"
                            + " (:trangThai = 3 OR trangThai = :trangThai) "
                            + " order by id asc",
                            SanPhamEntity.class);
            query.setParameter("textSearch", "%" + textSearch + "%");
            query.setParameter("trangThai", status);
            lstData = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstData;
    }

    public SanPhamEntity Save(SanPhamEntity objInput) {
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

    public int updateSanPham(SanPhamEntity obiInput) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            session.beginTransaction();
            String sql = "UPDATE SanPhamEntity"
                    + " SET trangThai = :trangThai,"
                    + " tenSanPham = :tenSanPham"
                    + " WHERE id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("trangThai", obiInput.getTrangThai());
            query.setParameter("tenSanPham", obiInput.getTenSanPham());
            query.setParameter("id", obiInput.getId());
            int check = query.executeUpdate();
            session.getTransaction().commit();
            return check;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
