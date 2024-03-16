package com.mycompany.da1.repository;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.util.HibernateUltil;
import java.math.BigDecimal;
import java.util.ArrayList;
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
            listData = (ArrayList<SanPhamEntity>) session.createQuery("from SanPhamEntity where trangThai = 1").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
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
                    + " soLuong= :soLuong,"
                    + " tenSanPham = :tenSanPham,"
                    + " maSanPham = :maSanPham,"
                    + " anhSanPham = :anhSanPham"
                    + " WHERE id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("trangThai", obiInput.getTrangThai());
            query.setParameter("soLuong", obiInput.getSoLuong());
            query.setParameter("tenSanPham", obiInput.getTenSanPham());
            query.setParameter("maSanPham", obiInput.getMaSanPham());
            query.setParameter("anhSanPham", obiInput.getAnhSanPham());
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
