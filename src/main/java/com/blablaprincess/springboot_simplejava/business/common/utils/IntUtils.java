package com.blablaprincess.springboot_simplejava.business.common.utils;

public class IntUtils {
    public static int getLength(int number) {
        return number == 0 ? 1 : (int)(Math.log10(Math.abs(number)) + 1);
    }
}
