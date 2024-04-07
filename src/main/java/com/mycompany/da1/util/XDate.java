package com.mycompany.da1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Date toDate(String date) {
        if (date == null) {
            return new Date();
        }
        return toDate(date, "dd-MM-yyyy");
    }

    public static String toString(Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    public static String toString(Date date) {
        if (date == null) {
            return "";
        } else {
            formater.applyPattern("dd-MM-yyyy");
            return formater.format(date);
        }

    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static Date converDate(Date date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(toString(date));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
