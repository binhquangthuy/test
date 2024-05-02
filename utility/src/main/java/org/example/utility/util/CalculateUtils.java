package org.example.utility.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CalculateUtils {
    public static Long sumLongNumbers(Long... numbers) {
       long result = 0;
       for (Long num : numbers) {
           if (num == null) {
               num = 0L;
           }
           result += num;
       }
       return result;
    }
    public static Long sumLongNumbers(List<Long> numbers) {
        long result = 0;
        for (Long num : numbers) {
            if (num == null) {
                num = 0L;
            }
            result += num;
        }
        return result;
    }

    public static Long subLongNumbers(Long numStart, Long... numbers) {
        for (Long num : numbers) {
            if (num == null) {
                num = 0L;
            }
            numStart -= num;
        }
        return numStart;
    }
}
