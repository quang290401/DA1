package com.mycompany.da1.repository;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.util.HibernateUltil;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author sonst
 */
public class DanhMucDao {
    public ArrayList<DanhMucEntity> GetList() {
        ArrayList<DanhMucEntity> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            listData = (ArrayList<DanhMucEntity>) session.createQuery("from DanhMucEntity where trangThai = 1").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
}
