package com.mycompany.da1.service;

import com.mycompany.da1.entity.MauSacEntity;

import java.util.List;

public interface MauSacService {
    List<MauSacEntity> getAll();
   
    MauSacEntity getById();
}
