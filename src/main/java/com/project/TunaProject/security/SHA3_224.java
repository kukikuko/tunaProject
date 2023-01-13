package com.project.TunaProject.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA3_224 {

    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA3-224");
        md.update(text.getBytes());

        StringBuilder builder = new StringBuilder();
        for (byte b : md.digest()) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
        
    }

}