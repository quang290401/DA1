package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.entity.VaiTroEntity;
import com.mycompany.da1.repository.TaiKhoanDAO;
import com.mycompany.da1.service.TaiKhoanService;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanIMPL implements TaiKhoanService {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    @Override
    public ArrayList<TaiKhoanEntity> GetAll() {
        ArrayList<TaiKhoanEntity> taiKhoanEntities = taiKhoanDAO.GetList();
        return taiKhoanEntities;
    }

    @Override
    public int traVeIdNhanVien(String userName) {
        return taiKhoanDAO.getIdNhanVienByUsername(userName);
    }
    
    public List<VaiTroEntity> getListVaiTro() {
        return taiKhoanDAO.getListVaiTro();
    }

    public ArrayList<TaiKhoanEntity> getAll() {
        ArrayList<TaiKhoanEntity> taiKhoanEntities = taiKhoanDAO.getAll();
        return taiKhoanEntities;
    }

    public List<TaiKhoanEntity> getSearch(String text, int status) {
        return taiKhoanDAO.searchTK(text, status);
    }

    public TaiKhoanEntity save(TaiKhoanEntity objInput) {
        return taiKhoanDAO.addTK(objInput);
    }

    public int updateTaiKhoan(TaiKhoanEntity objInput) {
        return taiKhoanDAO.updateTK(objInput);
    }
}
