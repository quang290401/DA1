package com.mycompany.da1.service;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import java.util.ArrayList;

public interface SanPhamChiTietService {
    ArrayList<SanPhamChiTietEntity>GetAll();
    ArrayList<SanPhamChiTietEntity>GetAllSPFormBanHang();
    
    ArrayList<DanhMucEntity> getListDanhMuc();
    void UpdateTrangThaiToZero(Integer id);
    void UpdateTrangThaiToOne(Integer id);
    int getSoLuongById(int id);
    void UpdateSoLuongSPCT(int idSPCT,int soLuong);

}
