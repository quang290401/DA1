package com.mycompany.da1.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class XFastFuntion {
    public static int convertInt(Object object){
        return Integer.valueOf(((String) object));
    };
    
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private static final int CODE_LENGTH = 8;

    private static Set<String> usedCodes = new HashSet<>();

    public static String createCode() {
        String invoiceCode;
        do {
            invoiceCode = generateRandomLetters(CODE_LENGTH);
        } while (usedCodes.contains(invoiceCode));

        usedCodes.add(invoiceCode);
        return invoiceCode;
    }

    private static String generateRandomLetters(int length) {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = LETTERS.charAt(random.nextInt(LETTERS.length()));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

}
