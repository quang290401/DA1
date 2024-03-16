/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.da1.repository;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.util.HibernateUltil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author sonst
 */
public class MauSacDao {

    public MauSacEntity getById(int idMauSac) {
        Transaction transaction = null;
        MauSacEntity entity = null;
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            entity = session.get(MauSacEntity.class, idMauSac);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return entity;
    }
}
