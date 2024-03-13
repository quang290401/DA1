package com.mycompany.da1.combobox;

public class KhachHangCBB {
    private Object id;
    private Object hoten;

    public KhachHangCBB(Object id, Object hoten) {
        this.id = id;
        this.hoten = hoten;
    }
    public int IdInt() {
        return Integer.parseInt(id.toString());
    }


    @Override
    public String toString() {

        return String.valueOf(hoten);
    }
}
