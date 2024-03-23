package com.mycompany.da1.repository;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.util.HibernateUltil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.math.BigDecimal;
import java.util.ArrayList;

public class HoaDonChiTietDAO {
    public ArrayList<HoaDonChiTietEntity> GetList(int idHoaDon) {
        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from HoaDonChiTietEntity where hoaDonEntity.id = :idHoaDon");
            query.setParameter("idHoaDon", idHoaDon);
            hoaDonChiTietEntities =  (ArrayList<HoaDonChiTietEntity>) ((org.hibernate.query.Query<?>) query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonChiTietEntities;
    }
    public void Save(HoaDonChiTietEntity hoaDonChiTietEntity, int soLuong, BigDecimal newTongTien) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(hoaDonChiTietEntity);
            transaction.commit();
        } catch (Exception e) {
            updateSoLuong(hoaDonChiTietEntity.getHoaDonEntity().getId(),
                    hoaDonChiTietEntity.getSanPhamChiTietEntity().getId(),soLuong,newTongTien);
        }

    }
    public void updateSoLuong(int idHoaDon, int idChiTietSP, int newSoLuong, BigDecimal newTongTien) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            // Bắt đầu giao dịch
            session.beginTransaction();
            String sql = "UPDATE HoaDonChiTietEntity SET soLuong = :newSoLuong, tongTien = :newTongTien WHERE hoaDonEntity.id = :idHoaDon AND sanPhamChiTietEntity.id = :idChiTietSP";
            Query query = session.createQuery(sql);
            query.setParameter("newSoLuong", newSoLuong);
            query.setParameter("newTongTien", newTongTien); // Thiết lập tham số newTongTien
            query.setParameter("idHoaDon", idHoaDon);
            query.setParameter("idChiTietSP", idChiTietSP);
            int updatedCount = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByHoaDonIdAndProductId(int idHoaDon, int idSP) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            session.beginTransaction();
            String sql = "DELETE FROM HoaDonChiTietEntity WHERE hoaDonEntity.id = :idHoaDon AND sanPhamChiTietEntity.id = :idSP";
            Query query = session.createQuery(sql);
            query.setParameter("idHoaDon", idHoaDon);
            query.setParameter("idSP", idSP);
            int deletedCount = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getTotalTongTienByHoaDonId(int idHoaDon) {
        BigDecimal totalTongTien = BigDecimal.ZERO;
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            // Sử dụng câu lệnh SQL để tính tổng tiền của hóa đơn chi tiết dựa vào idHoaDon
            String sql = "SELECT SUM(tongTien) FROM HoaDonChiTietEntity WHERE hoaDonEntity.id = :idHoaDon";
            Query query = session.createQuery(sql);
            query.setParameter("idHoaDon", idHoaDon);
            totalTongTien = (BigDecimal) ((org.hibernate.query.Query<?>) query).uniqueResult();
            if (totalTongTien == null) {
                totalTongTien = BigDecimal.ZERO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalTongTien;
    }




}
