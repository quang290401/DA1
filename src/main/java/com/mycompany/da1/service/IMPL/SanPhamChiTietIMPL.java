package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.ChatLieuEntity;
import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.KichCoEntity;
import com.mycompany.da1.entity.MauSacEntity;
import com.mycompany.da1.entity.NhaSanXuatEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.repository.DanhMucDao;
import com.mycompany.da1.repository.SanPhamChiTietDAO;
import com.mycompany.da1.service.SanPhamChiTietService;
import java.math.BigDecimal;

import java.util.ArrayList;

public class SanPhamChiTietIMPL implements SanPhamChiTietService {

    SanPhamChiTietDAO sanPhamChiTietDAO = new SanPhamChiTietDAO();
    DanhMucDao danhMucDao = new DanhMucDao();

    @Override
    public ArrayList<SanPhamChiTietEntity> GetAll() {
        ArrayList<SanPhamChiTietEntity> sanPhamChiTietEntities = sanPhamChiTietDAO.GetList();
        return sanPhamChiTietEntities;
    }

    public SanPhamChiTietEntity save(SanPhamChiTietEntity objInput) {
        return sanPhamChiTietDAO.save(objInput);
    }
    
    public int update(SanPhamChiTietEntity objInput) {
        return sanPhamChiTietDAO.update(objInput);
    }

    @Override
    public ArrayList<DanhMucEntity> getListDanhMuc() {
        ArrayList<DanhMucEntity> listData = danhMucDao.GetList();
        return listData;
    }

    public ArrayList<NhaSanXuatEntity> getListNhaSx() {
        return sanPhamChiTietDAO.getListNhaSx();
    }

    public ArrayList<KichCoEntity> getListKichCo() {
        return sanPhamChiTietDAO.getListKichCo();
    }

    public ArrayList<MauSacEntity> getListMauSac() {
        return sanPhamChiTietDAO.getListMauSac();
    }

    public ArrayList<ChatLieuEntity> getListChatLieu() {
        return sanPhamChiTietDAO.getListChatLieu();
    }
}
