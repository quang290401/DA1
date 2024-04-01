package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.KhachHangEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.repository.KhachHangDAO;
import com.mycompany.da1.service.KhachhangService;

import java.util.ArrayList;
import java.util.List;

public class KhachHangIMPL implements KhachhangService {
    KhachHangDAO service = new KhachHangDAO();

    @Override
    public ArrayList<KhachHangEntity> GetAll() {
        ArrayList<KhachHangEntity> khachHangEntities = service.GetList();
        return khachHangEntities;
    }

    public List<KhachHangEntity> getSearch(String text) {
        List<KhachHangEntity> listData = service.getSearch(text);
        return listData;
    }

    @Override
    public KhachHangEntity save(KhachHangEntity objInput) {
        return service.Save(objInput);

    }

    public KhachHangEntity updateKhachHang(KhachHangEntity objInput) {

        return service.Update(objInput);
    }
}

