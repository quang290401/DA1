package com.mycompany.da1.service;

import com.mycompany.da1.entity.DanhMucEntity;
import com.mycompany.da1.entity.SanPhamChiTietEntity;
import java.util.ArrayList;

public interface SanPhamChiTietService {
    ArrayList<SanPhamChiTietEntity>GetAll();
    
    ArrayList<DanhMucEntity> getListDanhMuc();
    void UpdateSoLuongSP(Integer soLuong,Integer idChiTietSP);
}
