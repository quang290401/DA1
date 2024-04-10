package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.VoucherEntity;
import com.mycompany.da1.repository.VoucherDAO;
import com.mycompany.da1.service.VoucherService;

import java.util.ArrayList;

public class VoucherServiceIMPL implements VoucherService {
    VoucherDAO voucherDAO = new VoucherDAO();

    @Override
    public ArrayList<VoucherEntity> GetAll() {
        ArrayList<VoucherEntity>voucherEntities = voucherDAO.GetList();
        return voucherEntities;
    }

    @Override
    public ArrayList<VoucherEntity> GetAllByTrangthai(int trangThai) {
        ArrayList<VoucherEntity>voucherEntities = voucherDAO.GetList(trangThai);
        return voucherEntities;
    }

    @Override
    public ArrayList<VoucherEntity> SerchVoucher(String tenKhuyenMai) {
        return voucherDAO.Serch(tenKhuyenMai);
    }

    @Override
    public VoucherEntity save(VoucherEntity voucherEntity) {
        return voucherDAO.Save(voucherEntity);
    }

    @Override
    public VoucherEntity Update(VoucherEntity voucherEntity) {
        return  voucherDAO.UpDate(voucherEntity);
    }
}
