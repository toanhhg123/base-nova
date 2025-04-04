package com.example.demo.core.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeGenerationUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generates a secure random number between min and max (inclusive)
     *
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Random number within the specified range
     */
    public static int secureRandom(int min, int max) {
        int range = max - min + 1;
        return min + secureRandom.nextInt(range);
    }

    /**
     * Generates a code with current timestamp and a random number
     * Format: prefix + yyMMddHHmmss + 4-digit random number
     *
     * @param prefix Optional prefix for the code (can be null or empty)
     * @return Generated code
     */
    public static String genCodeWithTime(String prefix) {
        // Format current date as yyMMddHHmmss
        var now = LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String currentDate = now.format(formatter);

        // Generate random 4-digit number with leading zeros if needed
        int randomNum = secureRandom(1, 9999);
        String randomNumber = String.format("%04d", randomNum);

        // If prefix is null, use empty string
        String actualPrefix = prefix != null ? prefix : "";

        return actualPrefix + currentDate + randomNumber;
    }
}
