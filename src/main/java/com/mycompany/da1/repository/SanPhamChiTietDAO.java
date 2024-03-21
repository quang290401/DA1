package com.mycompany.da1.repository;

import com.mycompany.da1.entity.ChatLieuEntity;
import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.KichCoEntity;
import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.entity.NhaSanXuatEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Transaction;

public class SanPhamChiTietDAO {

    public ArrayList<SanPhamChiTietEntity> GetList() {
        ArrayList<SanPhamChiTietEntity> sanPhamChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            sanPhamChiTietEntities = (ArrayList<SanPhamChiTietEntity>) session.createQuery("from SanPhamChiTietEntity order by id asc").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhamChiTietEntities;
    }

    public List<SanPhamChiTietEntity> getListByMaSp(String maSpCt) {
        List<SanPhamChiTietEntity> sanPhamChiTietEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query<SanPhamChiTietEntity> query = session.createQuery("from SanPhamChiTietEntity where maSanPhamCt = :maSpCt order by id asc", SanPhamChiTietEntity.class);
            query.setParameter("maSpCt", maSpCt);
            sanPhamChiTietEntities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhamChiTietEntities;
    }

    public List<SanPhamChiTietEntity> getSearch(String textSearch, DanhMucEntity dme, NhaSanXuatEntity nsxe, int status, SanPhamEntity sanPhamEntity) {
        List<SanPhamChiTietEntity> lstData = new ArrayList<>();
        System.out.println(sanPhamEntity.getId() + " ơ " + sanPhamEntity.getMaSanPham());
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query<SanPhamChiTietEntity> query = session.createQuery(
                    "SELECT spct FROM SanPhamChiTietEntity spct "
                    + "LEFT JOIN spct.sanPhamEntity sp "
                    + "LEFT JOIN spct.danhMucEntity dm "
                    + "LEFT JOIN spct.nhaSanXuatEntity nsx "
                    + "WHERE "
                    + "(spct.maSanPhamCt LIKE :textSearch) "
                    + "AND (sp.id = :spId)"
                    + "AND (:trangThai = 3 OR spct.trangThai = :trangThai) "
                    + "AND (:dmeId = 0 OR dm.id = :dmeId) "
                    + "AND (:nsxeId = 0 OR nsx.id = :nsxeId) "
                    + "ORDER BY spct.id ASC",
                    SanPhamChiTietEntity.class
            );
            query.setParameter("textSearch", "%" + textSearch + "%");
            query.setParameter("spId", sanPhamEntity.getId());
            query.setParameter("trangThai", status);
            query.setParameter("dmeId", dme != null ? dme.getId() : 0);
            query.setParameter("nsxeId", nsxe != null ? nsxe.getId() : 0);
            lstData = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstData;
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
            System.out.println(objInput.getMoTa());
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
                    + " moTa = :moTa,"
                    + " maSanPhamCt = :maSanPhamCt,"
                    + " anhSanPham = :anhSanPham"
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
            query.setParameter("maSanPhamCt", objInput.getMaSanPhamCt());
            query.setParameter("anhSanPham", objInput.getAnhSanPham());
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
