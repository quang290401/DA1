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
}
