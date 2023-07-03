package com.firedance.gps.utils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UUIDUtil {

    private static String alphaChars="abcdefghijklmnopqrstuvwxyz";
    public UUIDUtil() {
    }

    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String get4UUID(){
        Random random = new Random();
        int i1 = random.nextInt(10000)% alphaChars.length();
        int i2 = random.nextInt(10000)% alphaChars.length();
        int i3 = random.nextInt(10000)% alphaChars.length();
        int i4 = random.nextInt(10000)% alphaChars.length();
        char[] chars = new char[4];
        chars[0] = alphaChars.charAt(i1);
        chars[1] = alphaChars.charAt(i2);
        chars[2] = alphaChars.charAt(i3);
        chars[3] = alphaChars.charAt(i4);
        return String.valueOf(chars);
    }

    public static Integer get4Number(){
        Random random = new Random();
        return 1000+random.nextInt(9000);
    }

    public static Integer get8Number(){
        Random random = new Random();
        return 10000000+random.nextInt(90000000);
    }
}