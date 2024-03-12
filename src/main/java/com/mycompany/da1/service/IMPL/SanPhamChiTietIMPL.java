package com.mycompany.da1.service.IMPL;

import com.mycompany.da1.entity.SanPhamChiTietEntity;
import com.mycompany.da1.repository.SanPhamChiTietDAO;
import com.mycompany.da1.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;


public class SanPhamChiTietIMPL implements SanPhamChiTietService {
   SanPhamChiTietDAO sanPhamChiTietDAO = new SanPhamChiTietDAO();


    @Override
    public ArrayList<SanPhamChiTietEntity> GetAll() {
        ArrayList<SanPhamChiTietEntity>sanPhamChiTietEntities = sanPhamChiTietDAO.GetList();
        return sanPhamChiTietEntities;
    }
}
