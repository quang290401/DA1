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

    public ArrayList<HoaDonEntity> getListHd(int trangThai, String tenKhach) {
        ArrayList<HoaDonEntity> hoaDonEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            String queryString;
            if (trangThai == 3 && (tenKhach == null || tenKhach.isEmpty())) {
                queryString = "from HoaDonEntity";
            } else if (tenKhach == null || tenKhach.isEmpty()) {
                queryString = "from HoaDonEntity where trangThai = :trangThai";
            } else if (trangThai == 3) {
                queryString = "select h from HoaDonEntity h inner join h.khachHangEntity k "
                        + "where h.trangThai = :trangThai and k.hoTen like :tenKhach";
            } else {
                queryString = "select h from HoaDonEntity h inner join h.khachHangEntity k "
                        + "where h.trangThai = :trangThai and k.hoTen like :tenKhach";
            }

            Query query = session.createQuery(queryString);

            if (trangThai != 3) {
                query.setParameter("trangThai", trangThai);
            }
            if (tenKhach != null && !tenKhach.isEmpty()) {
                query.setParameter("tenKhach", "%" + tenKhach + "%");
            }

            hoaDonEntities = (ArrayList<HoaDonEntity>) ((org.hibernate.query.Query<?>) query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonEntities;
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
