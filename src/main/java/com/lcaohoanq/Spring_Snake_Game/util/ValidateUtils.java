package com.lcaohoanq.Spring_Snake_Game.util;

public class ValidateUtils {
    public static boolean checkTypeAccount(String email_phone) {
        return email_phone.contains("@");
    }

    public static boolean authenticate(String password, String hashedPassword) {
        return new PBKDF2().authenticate(password.toCharArray(), hashedPassword);
    }
}
