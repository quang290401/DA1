package com.mycompany.da1.combobox;

public class VoucherCBB {
    private Object id;
    private Object phanTramGiam;

    public VoucherCBB(Object id, Object phanTramGiam) {
        this.id = id;
        this.phanTramGiam = phanTramGiam;
    }

    public int IdInt() {
        return Integer.parseInt(id.toString());
    }



    @Override
    public String toString() {

        return String.valueOf(phanTramGiam);
    }
}
