package org.example.utility.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class CheckUtils {
    public static boolean isNotNegativeNumber(Long num) {
       if (num == null) {
           return false;
       }
       return num >= 0;
    }

    public static boolean isPositiveNumber(Long num) {
        if (num == null) {
            return false;
        }
        return num > 0;
    }

    public static boolean checkLongIsSmaller(Long num1, Long num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        return num1 < num2;
    }
}
