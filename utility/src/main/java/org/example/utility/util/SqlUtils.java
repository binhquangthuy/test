package org.example.utility.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlUtils {
    public static int getOffsetQuery(int page, int pageSize) {
        if (page < 1 || pageSize < 1) {
            return 0;
        }
        return (page - 1) * pageSize;
    }


}
