package com.mycompany.da1.service;

import com.mycompany.da1.entity.HoaDonEntity;
import com.mycompany.da1.entity.KhachHangEntity;

import java.util.ArrayList;

public interface HoaDonService {
   void Save(HoaDonEntity hoaDonEntity);
   ArrayList<HoaDonEntity> GetAll();
}
