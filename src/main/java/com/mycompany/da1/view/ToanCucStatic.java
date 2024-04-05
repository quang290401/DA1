package com.mycompany.da1.view;

import java.math.BigDecimal;

public class ToanCucStatic {
    public  static  int idNhanVien;
    public  static  int idHoaDon;
    public  static BigDecimal thongKe;

    public static BigDecimal getThongKe() {
        return thongKe;
    }

    public static void setThongKe(BigDecimal thongKe) {
        ToanCucStatic.thongKe = thongKe;
    }

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
