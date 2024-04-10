package com.mycompany.da1.repository;

import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class VoucherDAO {
    public ArrayList<VoucherEntity> GetList() {
        ArrayList<VoucherEntity> voucherEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from VoucherEntity where trangThai =1");
            voucherEntities = (ArrayList<VoucherEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucherEntities;
    }
    public ArrayList<VoucherEntity> GetList(int trangThai) {
        ArrayList<VoucherEntity> voucherEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from VoucherEntity where trangThai = :trangThai");
            query.setParameter("trangThai", trangThai);
            voucherEntities = (ArrayList<VoucherEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucherEntities;
    }
    public ArrayList<VoucherEntity> Serch(String  tenKhuyenMai) {
        ArrayList<VoucherEntity> voucherEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query query = session.createQuery("from VoucherEntity where tenKhuyenMai like :tenKhuyenMai");
            query.setParameter("tenKhuyenMai", "%" + tenKhuyenMai + "%");
            voucherEntities = (ArrayList<VoucherEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucherEntities;
    }


    public VoucherEntity Save(VoucherEntity objInput) {
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
    public VoucherEntity UpDate(VoucherEntity objInput) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(objInput);
            session.flush();
            session.refresh(objInput);
            transaction.commit();
            return objInput;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
