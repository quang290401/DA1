package com.mycompany.da1.service;

import com.mycompany.da1.entity.SanPhamEntity;

import java.util.ArrayList;

public interface SanPhamService {
    ArrayList<SanPhamEntity> GetAll();
    
    SanPhamEntity save(SanPhamEntity objInput);
}
