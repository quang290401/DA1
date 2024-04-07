package com.mycompany.da1.repository;

import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.entity.VaiTroEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Transaction;

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

    public List<VaiTroEntity> getListVaiTro() {
        List<VaiTroEntity> listTK = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from VaiTroEntity where trangThai = 1");
            listTK = (ArrayList<VaiTroEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTK;
    }

    public ArrayList<TaiKhoanEntity> getAll() {
        ArrayList<TaiKhoanEntity> listTK = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from TaiKhoanEntity");
            listTK = (ArrayList<TaiKhoanEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTK;
    }

    public List<TaiKhoanEntity> searchTK(String textSearch, int status) {
        List<TaiKhoanEntity> lstData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query<TaiKhoanEntity> query = session
                    .createQuery(
                            "from TaiKhoanEntity where"
                            + " (hoTen LIKE :textSearch OR maTaiKhoan LIKE :textSearch)  And"
                            + " (:trangThai = 3 OR trangThai = :trangThai) "
                            + " order by id asc",
                            TaiKhoanEntity.class);
            query.setParameter("textSearch", "%" + textSearch + "%");
            query.setParameter("trangThai", status);
            lstData = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstData;
    }

    public TaiKhoanEntity addTK(TaiKhoanEntity objInput) {
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

    public int updateTK(TaiKhoanEntity obiInput) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            session.beginTransaction();
            String sql = "UPDATE TaiKhoanEntity"
                    + " SET trangThai = :trangThai,"
                    + " hoTen = :hoTen,"
                    + " matKhau = :matKhau,"
                    + " taiKhoan = :taiKhoan,"
                    + " ngaySinh = :ngaySinh,"
                    + " diaChi = :diaChi,"
                    + " gioiTinh = :gioiTinh,"
                    + " sdt = :sdt,"
                    + " cccd = :cccd,"
                    + " vaiTro_id = :vaiTroId"
                    + " WHERE id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("trangThai", obiInput.getTrangThai());
            query.setParameter("hoTen", obiInput.getHoTen());
            query.setParameter("matKhau", obiInput.getMatKhau());
            query.setParameter("taiKhoan", obiInput.getTaiKhoan());
            query.setParameter("ngaySinh", obiInput.getNgaySinh());
            query.setParameter("diaChi", obiInput.getDiaChi());
            query.setParameter("gioiTinh", obiInput.getGioiTinh());
            query.setParameter("sdt", obiInput.getSdt());
            query.setParameter("cccd", obiInput.getCccd());
            query.setParameter("vaiTroId", obiInput.getVaiTroEntity().getId());
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
