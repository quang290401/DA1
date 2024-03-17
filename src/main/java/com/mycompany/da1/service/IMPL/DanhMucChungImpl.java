/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.repository.DanhMucChungDao;
import java.util.ArrayList;

/**
 *
 * @author sonst
 */
public class DanhMucChungImpl<T> {
    
    private DanhMucChungDao<T> danhMucChungDao;
    
    public DanhMucChungImpl() {
        this.danhMucChungDao = new DanhMucChungDao<>();
    }
    
    public T save(T objInput) {
       return danhMucChungDao.Save(objInput);
    }
    
    public int update(Class<T> entityType, String tenDanhMuc, int id, int status, String name) {
       return danhMucChungDao.updateSanPham(entityType, tenDanhMuc, id, status, name);
    }
    
    public ArrayList<T> getAll(Class<T> entityType) {
       return danhMucChungDao.getAll(entityType);
    }
}
