package com.mycompany.da1.repository;

import com.mycompany.da1.entity.ChatLieuEntity;
import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.KichCoEntity;
import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.entity.NhaSanXuatEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.util.HibernateUltil;
import jakarta.persistence.Query;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.ArrayList;
import org.hibernate.Transaction;

public class SanPhamChiTietDAO {

    public ArrayList<SanPhamChiTietEntity> GetList() {
        ArrayList<SanPhamChiTietEntity> sanPhamChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            sanPhamChiTietEntities = (ArrayList<SanPhamChiTietEntity>) session.createQuery("from SanPhamChiTietEntity where trangThai=1").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhamChiTietEntities;
    }

    public ArrayList<KichCoEntity> getListKichCo() {
        ArrayList<KichCoEntity> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            listData = (ArrayList<KichCoEntity>) session.createQuery("from KichCoEntity where trangThai = 1").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ArrayList<MauSacEntity> getListMauSac() {
        ArrayList<MauSacEntity> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            listData = (ArrayList<MauSacEntity>) session.createQuery("from MauSacEntity where trangThai = 1").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ArrayList<NhaSanXuatEntity> getListNhaSx() {
        ArrayList<NhaSanXuatEntity> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            listData = (ArrayList<NhaSanXuatEntity>) session.createQuery("from NhaSanXuatEntity where trangThai = 1").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ArrayList<ChatLieuEntity> getListChatLieu() {
        ArrayList<ChatLieuEntity> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            listData = (ArrayList<ChatLieuEntity>) session.createQuery("from ChatLieuEntity where trangThai = 1").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public SanPhamChiTietEntity save(SanPhamChiTietEntity objInput) {
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

    public int update(SanPhamChiTietEntity objInput) {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(objInput.toString());
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            session.beginTransaction();
            String sql = "UPDATE SanPhamChiTietEntity"
                    //                    + " SET soLuong = 13"
                    + " SET giaSanPham = :giaSanPham,"
                    + " soLuong = :soLuong,"
                    + " trangThai = :trangThai,"
                    + " chatlieu_id = :chatlieuid,"
                    + " danhmuc_id = :danhmucid,"
                    + " kichco_id = :kichcoid,"
                    + " mausac_id = :mausacid,"
                    + " nhaSanXuat_id = :nhaSanXuatid,"
                    + " moTa = :moTa"
                    + " WHERE id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("giaSanPham", objInput.getGiaSanPham());
            query.setParameter("soLuong", objInput.getSoLuong());
            query.setParameter("trangThai", objInput.getTrangThai());
            query.setParameter("chatlieuid", objInput.getChatLieuEntity().getId());
            query.setParameter("danhmucid", objInput.getDanhMucEntity().getId());
            query.setParameter("kichcoid", objInput.getKichCoEntity().getId());
            query.setParameter("mausacid", objInput.getMauSacEntity().getId());
            query.setParameter("nhaSanXuatid", objInput.getNhaSanXuatEntity().getId());
            query.setParameter("moTa", objInput.getMoTa());
            query.setParameter("id", objInput.getId());
            int updatedCount = query.executeUpdate();
            session.getTransaction().commit();

            return updatedCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public void updateSoLuongChiTietSanPham(Integer newSoLuong, Integer idChiTietSanPham) {
        Session session = HibernateUltil.getFACTORY().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE SanPhamChiTietEntity SET soLuong=soLuong-:newSoLuong WHERE id=:idChiTietSanPham";
            int updatedEntities = session.createQuery(hql)
                    .setParameter("newSoLuong", newSoLuong)
                    .setParameter("idChiTietSanPham", idChiTietSanPham)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
