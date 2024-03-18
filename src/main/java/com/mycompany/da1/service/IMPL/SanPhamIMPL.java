package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.SanPhamEntity;
import com.mycompany.da1.repository.SanPhamDao;
import com.mycompany.da1.service.SanPhamService;
import java.util.ArrayList;
import java.util.List;


public class SanPhamIMPL implements SanPhamService {

    SanPhamDao service = new SanPhamDao();

    @Override
    public ArrayList<SanPhamEntity> GetAll() {
        ArrayList<SanPhamEntity> listData = service.GetList();
        return listData;
    }
    
    public List<SanPhamEntity> getSerch(String text, int status) {
        List<SanPhamEntity> listData = service.getSearch(text, status);
        return listData;
    }
    
    @Override
    public SanPhamEntity save(SanPhamEntity objInput) {
        return service.Save(objInput);
    }
    
    public int updateSanPham(SanPhamEntity objInput) {
        return service.updateSanPham(objInput);
    }

}
