package com.mycompany.da1.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author sonst
 */
public class AgeCalculator {
    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
    
    public static boolean checkAge(Calendar ngaySinh, int tuoiMuonKt) {
        // Định nghĩa ngày sinh
        LocalDate birthDate = LocalDate.of(ngaySinh.get(Calendar.YEAR), ngaySinh.get(Calendar.MONTH), ngaySinh.get(Calendar.DATE));

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Chuyển đổi ngày thành số
        int birthDateAsNumber = birthDate.getYear() * 10000 + birthDate.getMonthValue() * 100 + birthDate.getDayOfMonth();
        int currentDateAsNumber = currentDate.getYear() * 10000 + currentDate.getMonthValue() * 100 + currentDate.getDayOfMonth();

        // Tính tuổi từ số
        int age = (currentDateAsNumber - birthDateAsNumber) / 10000;

        if (age >= tuoiMuonKt) {
            return false;
        } else {
            return true;
        }
    }
}