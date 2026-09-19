package com.auditorlab.vulnport;

import java.security.MessageDigest;

public final class WeakCrypto {

    private WeakCrypto() {}

    // LAB-CRYPTO-001: MD5 no es adecuado para almacenar contraseñas.
    public static String hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(value.getBytes("UTF-8"));
            StringBuilder out = new StringBuilder();
            for (byte b : digest) {
                out.append(String.format("%02x", b));
            }
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
