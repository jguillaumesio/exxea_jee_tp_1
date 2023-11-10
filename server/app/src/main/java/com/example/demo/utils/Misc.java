package com.example.demo.utils;

import java.text.DecimalFormat;

public class Misc {
    public static String floatToString(float number) {
        if (number == (int) number) {
            return String.valueOf((int) number);
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(number).replace('.', ',');
        }
    }
}
