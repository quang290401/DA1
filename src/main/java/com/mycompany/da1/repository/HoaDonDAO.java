package com.mycompany.da1.repository;

import com.mycompany.da1.entity.HoaDonEntity;

import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
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
    public ArrayList<HoaDonEntity> ThanhToan(int idHoaDon) {
        ArrayList<HoaDonEntity> hoaDonEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from HoaDonEntity where id = :idHoaDon");
            query.setParameter("idHoaDon", idHoaDon); // Thiết lập giá trị cho tham số idHoaDon
            hoaDonEntities = (ArrayList<HoaDonEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonEntities;
    }

    public void UpdateHoaDon(int idHoaDon, BigDecimal tongTien) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            session.beginTransaction();
            String sql = "UPDATE HoaDonEntity SET trangThai = 1,tongTien=:tongTien WHERE id = :idHoaDon";
            Query query = session.createQuery(sql);
            query.setParameter("idHoaDon", idHoaDon);
            query.setParameter("tongTien", tongTien);// Thiết lập tham số idHoaDon
            int updatedCount = query.executeUpdate();

            // Kết thúc giao dịch
            session.getTransaction().commit();

            if (updatedCount > 0) {
                System.out.println("Hóa đơn đã được thanh toán thành công.");
            } else {
                System.out.println("Không tìm thấy hóa đơn với id = " + idHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
