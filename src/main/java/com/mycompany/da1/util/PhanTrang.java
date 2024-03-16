package com.mycompany.da1.util;

import java.util.List;

public class PhanTrang {
    public static <T> List<T> phanTrang(List<T> data, int pageSize, int currentPage) {
        int totalRecords = data.size();
//        int totalPages = tinhTongSoTrang(totalRecords, pageSize);

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalRecords);

        return data.subList(startIndex, endIndex);
    }
}
