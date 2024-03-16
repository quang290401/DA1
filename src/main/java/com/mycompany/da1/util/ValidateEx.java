package com.mycompany.da1.util;

import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author sonst
 */
public class ValidateEx {

    public static String checkIsNullWithLengt(JTextComponent jTextField, JLabel jLabel, int maxLengt) throws Exception {
        if (jTextField.getText().isBlank()) {
            throw new Exception(String.format(Contants.Validates.NO_EMTY.getTen(), jLabel.getText()));
        } else if (maxLengt > 0) {
            if (jTextField.getText().length() > maxLengt) {
                throw new Exception(String.format(Contants.Validates.NO_EMTY.getTen(), jLabel.getText(), maxLengt));
            }
        }
        return jTextField.getText();
    }

    public static String checkIsNull(JTextComponent jTextField, JLabel jLabel) throws Exception {
        return checkIsNullWithLengt(jTextField, jLabel, 0);
    }

    public static String checkIsNumF(JTextField jTextField, JLabel jLabel) throws Exception {
        String result = checkIsNull(jTextField, jLabel);
        try {
            Float.valueOf(result);
        } catch (NumberFormatException e) {
            throw new Exception(String.format(Contants.Validates.IS_NUMS.getTen(), jLabel.getText()));
        }
        return result;
    }

    public static String checkMoreThan(JTextField jTextField, JLabel jLabel, int valueCheck) throws Exception {
        String result = checkIsNumF(jTextField, jLabel);
        float a = Float.valueOf(result);
        if (a < valueCheck) {
            throw new Exception(String.format(Contants.Validates.PHAI_LON_HON.getTen(), jLabel.getText(), valueCheck));
        }
        return result;
    }

    public static String checkMoreThan(JTextField jTextField, JLabel jLabel) throws Exception {
        return checkMoreThan(jTextField, jLabel, 0);
    }

    public static <T> T convert(String input, Class<T> clazzType) throws Exception {
        try {
            Constructor<T> constructor = clazzType.getConstructor(String.class);
            return constructor.newInstance(input);
        } catch (NoSuchMethodException e) {
            throw new Exception(String.format(Contants.Validates.CONVERT_FAILED.getTen(), clazzType.getName()));
        }
    }

    public static Date checkIsDate(JTextComponent jTextField, JLabel jLabel) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (jTextField.getText().isBlank()) {
            throw new Exception(String.format(Contants.Validates.NO_EMTY.getTen(), jLabel.getText()));
        } else {

            try {
                sdf.parse(jTextField.getText());
            } catch (ParseException e) {
                throw new Exception(String.format(Contants.Validates.CHECK_DATE_DD_MM_YYYY.getTen(), jLabel.getText()));
            }
        }
        return sdf.parse(jTextField.getText());
    }
    
}
