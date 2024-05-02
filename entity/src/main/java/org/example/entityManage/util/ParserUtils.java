package org.example.entityManage.util;

import lombok.extern.slf4j.Slf4j;
import org.example.entityManage.constant.AppConstant;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
public class ParserUtils {

    public static String parseObjectToString(Object object) {
        if (object == null) {
            return null;
        }
        return object.toString();
    }

    public static Boolean parseObjectToBoolean(Object object) {
        if (object == null) {
            return false;
        }
        return Boolean.valueOf((Boolean) object);
    }

    public static Integer parseObjectToInteger(Object object) {
        try {
            if (object == null) {
                return null;
            }
            return Integer.parseInt(object.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static Long parseObjectToLong(Object object) {
        try {
            if (object == null) {
                return null;
            }
            return Long.parseLong(object.toString());
        } catch (Exception e) {
            return 0l;
        }
    }

    public static Date parseStringToDate(Object dateString) {
        try {
            if (dateString == null) {
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD);
            return simpleDateFormat.parse(dateString.toString());
        } catch (ParseException e) {
            return null;
        }
    }

}
