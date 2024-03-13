package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.repository.KhachHangDAO;
import com.mycompany.da1.service.KhachhangService;

import java.util.ArrayList;

public class KhachHangIMPL implements KhachhangService {
    KhachHangDAO khachHangDAO = new KhachHangDAO();
    @Override
    public ArrayList<KhachHangEntity> GetAll() {
        ArrayList<KhachHangEntity>khachHangEntities = khachHangDAO.GetList();
        return khachHangEntities;
    }
}
