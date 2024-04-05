package com.mycompany.da1.repository;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.util.HibernateUltil;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

/**
 *
 * @author sonst
 */
public class QuanLyHoaDonDao {

    public ArrayList<HoaDonEntity> getListHd(int trangThai) {
        ArrayList<HoaDonEntity> hoaDonEntitys = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            String queryString;
            if (trangThai == 3) {
                queryString = "from HoaDonEntity";
            } else {
                queryString = "from HoaDonEntity where trangThai = :trangThai";
            }
            Query query = session.createQuery(queryString);
            if (trangThai != 3) {
                query.setParameter("trangThai", trangThai);
            }
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

    public List<HoaDonEntity> findHoaDonByTenKhachHang(String tenKhachHang) {
        List<HoaDonEntity> hoaDonEntities = null;
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query;
            if (tenKhachHang != null && !tenKhachHang.isEmpty()) {
                query = session.createQuery("FROM HoaDonEntity hd WHERE hd.khachHangEntity.hoTen LIKE :tenKhachHang");
                query.setParameter("tenKhachHang", "%" + tenKhachHang + "%");
            } else {
                query = session.createQuery("FROM HoaDonEntity");
            }
            hoaDonEntities = (List<HoaDonEntity>) ((org.hibernate.query.Query<?>) query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonEntities;
    }
}
