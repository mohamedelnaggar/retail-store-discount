package com.retail.store.discount.util;

public class NumberUtils {

    public static double calculatePercentageValue(double value, double percentage){
        return value * (percentage / 100);
    }

}
