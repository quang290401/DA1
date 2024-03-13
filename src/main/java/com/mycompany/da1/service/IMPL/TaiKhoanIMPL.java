package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.TaiKhoanEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.repository.TaiKhoanDAO;
import com.mycompany.da1.service.TaiKhoanService;

import java.util.ArrayList;

public class TaiKhoanIMPL implements TaiKhoanService {
    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    @Override
    public ArrayList<TaiKhoanEntity> GetAll() {
        ArrayList<TaiKhoanEntity>taiKhoanEntities = taiKhoanDAO.GetList();
        return taiKhoanEntities;
    }
}
