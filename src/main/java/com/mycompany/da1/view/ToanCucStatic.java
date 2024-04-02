package com.mycompany.da1.view;

public class ToanCucStatic {
    public  static  int idNhanVien;
    public  static  int idHoaDon;

    public ToanCucStatic() {
    }

    public static int getIdNhanVien() {
        return idNhanVien;
    }

    public static void setIdNhanVien(int idNhanVien) {
        ToanCucStatic.idNhanVien = idNhanVien;
    }

    public static int getIdHoaDon() {
        return idHoaDon;
    }

    public static void setIdHoaDon(int idHoaDon) {
        ToanCucStatic.idHoaDon = idHoaDon;
    }
}
