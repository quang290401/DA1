package com.mycompany.da1.repository;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.util.HibernateUltil;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author sonst
 */
public class QuanLyHoaDonDao {

    public ArrayList<HoaDonEntity> getListHd() {
        ArrayList<HoaDonEntity> hoaDonEntitys = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from HoaDonEntity");
            hoaDonEntitys = (ArrayList<HoaDonEntity>) ((org.hibernate.query.Query<?>) query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonEntitys;
    }

    public ArrayList<HoaDonChiTietEntity> getList() {
        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from HoaDonChiTietEntity");
            hoaDonChiTietEntities = (ArrayList<HoaDonChiTietEntity>) ((org.hibernate.query.Query<?>) query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonChiTietEntities;
    }

    public ArrayList<HoaDonChiTietEntity> getListSeach(String txtSearch, Date ngayBd, Date ngayKt, Integer trangThai) {
        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from HoaDonChiTietEntity");
            hoaDonChiTietEntities = (ArrayList<HoaDonChiTietEntity>) ((org.hibernate.query.Query<?>) query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonChiTietEntities;
    }
    
    public ArrayList<HoaDonChiTietEntity> getListHdct(int id) {
        ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from HoaDonChiTietEntity where hoaDonEntity = " + id);
            hoaDonChiTietEntities = (ArrayList<HoaDonChiTietEntity>) ((org.hibernate.query.Query<?>) query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonChiTietEntities;
    }
}
