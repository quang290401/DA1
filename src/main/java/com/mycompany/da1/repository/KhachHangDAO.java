package com.mycompany.da1.repository;

import antlr.debug.TraceAdapter;
import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.util.HibernateUltil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class KhachHangDAO {
    public ArrayList<KhachHangEntity> GetList() {
        ArrayList<KhachHangEntity> khachHangEntities = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            khachHangEntities = (ArrayList<KhachHangEntity>) session.createQuery("from KhachHangEntity ").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHangEntities ;
    }
    
    
    public List<KhachHangEntity> getSearch(String textSearch) {
        List<KhachHangEntity> listData = new ArrayList<>();
        try (Session session = HibernateUltil.getFACTORY().openSession()){
            Query<KhachHangEntity> query = session
                    .createQuery(
                        "from KhachHangEntity where"
                        + " hoTen LIKE :textSearch OR soDienThoai LIKE :textSearch"
                        + " order by id asc",
                        KhachHangEntity.class
                    );
            query.setParameter("textSearch", "%" + textSearch + "%");
            listData = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    public KhachHangEntity Update(KhachHangEntity objInput) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();

            Integer id = objInput.getId();
            // Kiểm tra xem đối tượng đã tồn tại trong cơ sở dữ liệu chưa

                // Lấy ngày tạo hiện tại của đối tượng từ cơ sở dữ liệu
                KhachHangEntity existingObj = session.get(KhachHangEntity.class, id);
                // Gán ngày tạo hiện tại cho đối tượng đang được cập nhật
                objInput.setNgayTao(existingObj.getNgayTao());

                // Nếu đối tượng đã tồn tại, sử dụng merge để cập nhật
                session.merge(objInput);


            session.flush();
            session.refresh(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objInput;
    }





    public KhachHangEntity Save(KhachHangEntity objInput) {
        try (Session session = HibernateUltil.getFACTORY().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(objInput);
            session.flush();
            session.refresh(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public int getLastCustomerId() {
        int lastCustomerId = 0;
        try (Session session = HibernateUltil.getFACTORY().openSession()) {
            // Viết câu truy vấn để lấy ID của khách hàng cuối cùng
            Query query = session.createQuery("select max(id) from KhachHangEntity ");

            // Thực hiện truy vấn và lấy kết quả
            Object result = query.uniqueResult();
            if (result != null) {
                lastCustomerId = (int) result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastCustomerId;
    }


}
