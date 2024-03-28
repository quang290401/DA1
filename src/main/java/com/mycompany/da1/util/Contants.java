package com.mycompany.da1.util;

public class Contants {
    
    public static enum MODEL_VIEW {
        THEM_MOI, SUA, XOA, CHI_TIET, THEM_MOI_CT
    }

    public static enum ChucVu {
        NHAN_VIEN("NV", "Nhân viên"),
        QUAN_LY("QL", "Quản lý");
        private String ma;
        private String ten;

        ChucVu(String ma, String ten) {
            this.ma = ma;
            this.ten = ten;
        }

        public String getMa() {
            return ma;
        }

        public String getTen() {
            return ten;
        }
    }

    public static enum Validates {
        NO_EMTY("NO_EMTY", "Trường %s không được để trống"),
        QUAN_KY_TU_CHO_PHEP("QUAN_KY_TU_CHO_PHEP", "Trường %s vượt qua khí tự cho phép: max = %s"),
        IS_NUMS("IS_NUMS", "Trường %s là kiểu số"),
        PHAI_LON_HON("PHAI_LON_HON", "Trường %s phải lớn hơn %s"),
        CONVERT_FAILED("CONVERT_FAILED", "Không thể chuyển đổi sang %s"),
        CHECK_DATE_DD_MM_YYYY("CHECK_DATE_DD_MM_YYYY", "Trường %s không đúng định dạng (dd-MM-yyyy)");
        ;
        private String ma;
        private String ten;

        Validates(String ma, String ten) {
            this.ma = ma;
            this.ten = ten;
        }

        public String getMa() {
            return ma;
        }

        public String getTen() {
            return ten;
        }
    }
    
    static public String getStatusBusiness (int status) {
        if (status == 0) {
            return "Ngừng kinh doanh";
        }
        return "Đang kinh doanh";
    }
    
    static public String getStatusAttribute(int tinhTrang) {
        return tinhTrang == 0 ? "Hết" : "Còn";
    }
    
    public static enum PhanTrang {
        DEFAULT_PAGE(1);
        private int value;

        PhanTrang(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    
    public static enum VaiTro {
        USER(1),
        ADMIN(2);
        private int value;

        VaiTro(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
}
