package com.mycompany.da1.service;

import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.entity.VoucherEntity;

import java.util.ArrayList;

public interface VoucherService {
    ArrayList<VoucherEntity> GetAll();
    ArrayList<VoucherEntity> GetAllByTrangthai( int trangThai);
    ArrayList<VoucherEntity> SerchVoucher( String tenKhuyenMai);
    VoucherEntity save(VoucherEntity voucherEntity);
    VoucherEntity Update(VoucherEntity voucherEntity);
}
