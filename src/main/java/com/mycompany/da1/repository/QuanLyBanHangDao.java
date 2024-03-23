/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.da1.repository;

import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.util.HibernateUltil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author sonst
 */
public class QuanLyBanHangDao {

    public List<SanPhamChiTietEntity> getByTxt(String textSearch) {
        List<SanPhamChiTietEntity> lstData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Query<SanPhamChiTietEntity> query = session.createQuery(
                    "SELECT spct FROM SanPhamChiTietEntity spct "
                    + "LEFT JOIN spct.sanPhamEntity sp "
                    + "WHERE "
                    + "LOWER(sp.tenSanPham) LIKE LOWER(:textSearch) "
                    + "AND spct.trangThai = 1 "
                    + "AND spct.soLuong > 0 "
                    + "ORDER BY spct.id ASC",
                    SanPhamChiTietEntity.class
            );
            query.setParameter("textSearch", "%" + textSearch.toLowerCase() + "%");
            lstData = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstData;
    }
}
