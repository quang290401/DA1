package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.repository.QuanLyBanHangDao;
import java.util.List;

/**
 *
 * @author sonst
 */
public class QuanLyBanHangImpl {
    
    private QuanLyBanHangDao qlbhd = new QuanLyBanHangDao();
    
     public List<SanPhamChiTietEntity> getByTxt(String textSearch) {
         return qlbhd.getByTxt(textSearch);
     }
}
