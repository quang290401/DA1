package com.mycompany.da1.combobox;

public class TaiKhoanCBB {
    private Object id;
    private Object hoTen;

    public TaiKhoanCBB(Object id, Object hoTen) {
        this.id = id;
        this.hoTen = hoTen;
    }
    public int IdInt() {
        return Integer.parseInt(id.toString());
    }




    @Override
    public String toString() {

        return String.valueOf(hoTen);
    }
}
