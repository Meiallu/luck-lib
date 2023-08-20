package org.luck.util;

public class Util {
    
    public static boolean isBetween(double num, double min, double max) {
        if (num >= min && num <= max) {
            return true; 
        } else { return false; }
    }
}
