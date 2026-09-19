package com.VerifyX.VX.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;

@Service
public class HashingService {

    public String generateHash(MultipartFile file) throws Exception {


        MessageDigest digest = MessageDigest.getInstance("SHA-256");


        byte[] hashBytes = digest.digest(file.getBytes());


        StringBuilder hexString = new StringBuilder();

        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}