package com.mycompany.da1.view.excle;

import com.mycompany.da1.entity.HoaDonChiTietEntity;
import com.mycompany.da1.entity.KhachHangEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelExporter {
    public static void exportHoaDonChiTietToExcel(ArrayList<HoaDonChiTietEntity> hoaDonChiTietEntitys,
                                                  String tenKhach, String SDT,String tongTien,String tienSauGiam, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("HoaDonChiTiet");

            // Tạo headertién
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Tên sản phẩm");
            headerRow.createCell(1).setCellValue("Giá");
            headerRow.createCell(2).setCellValue("Số lượng");

            // Đổ dữ liệu từ ArrayList vào Excel
            int rowNum = 1;
            for (HoaDonChiTietEntity hoaDonChiTiet : hoaDonChiTietEntitys) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(hoaDonChiTiet.getSanPhamChiTietEntity().getSanPhamEntity().getTenSanPham());
                row.createCell(1).setCellValue(hoaDonChiTiet.getSanPhamChiTietEntity().getGiaSanPham().doubleValue());
                row.createCell(2).setCellValue(hoaDonChiTiet.getSoLuong());
            }

            // Tạo hàng mới cho thông tin khách hàng
            // Tạo hàng mới cho thông tin khách hàng
            Row customerRow = sheet.createRow(rowNum++);
            customerRow.createCell(0).setCellValue("Tên khách hàng:");
            customerRow.createCell(1).setCellValue(tenKhach);

            customerRow = sheet.createRow(rowNum++);
            customerRow.createCell(0).setCellValue("Số điện thoại:");
            customerRow.createCell(1).setCellValue(SDT);

            customerRow = sheet.createRow(rowNum++);
            customerRow.createCell(0).setCellValue("Tổng Tiền:");
            customerRow.createCell(1).setCellValue(tongTien);

            customerRow = sheet.createRow(rowNum++);
            customerRow.createCell(0).setCellValue("Tiền Sau Giảm:");
            customerRow.createCell(1).setCellValue(tienSauGiam);


            // Ghi dữ liệu ra file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Xuất Excel thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void exportKhacHHnag(ArrayList<KhachHangEntity> khachHangEntities, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Khách hàng");

            // Tạo headertién
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Họ tên");
            headerRow.createCell(1).setCellValue("số điện thoại");


            // Đổ dữ liệu từ ArrayList vào Excel
            int rowNum = 1;
            for (KhachHangEntity khachHangEntity : khachHangEntities) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(khachHangEntity.getHoTen());
                row.createCell(1).setCellValue(khachHangEntity.getSoDienThoai());

            }
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Xuất Excel thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}