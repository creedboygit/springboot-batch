package com.valletta.pass.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class LocalDateTimeUtils {

    public static final DateTimeFormatter YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter YYYYM_MM_DD = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter MM_DD = DateTimeFormatter.ofPattern("MM-dd");

    public static String format(final LocalDateTime localDateTime) {
        return localDateTime.format(YYYY_MM_DD_HH_MM);
    }

    public static String format(final LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    public static LocalDateTime parse(final String localDateTimeString) {
        if (StringUtils.isBlank(localDateTimeString)) {
            return null;
        }
        return LocalDateTime.parse(localDateTimeString, YYYY_MM_DD_HH_MM);
    }

    public static int getWeekOfYear(final LocalDateTime localDateTime) {
        return localDateTime.get(WeekFields.of(Locale.KOREA).weekOfYear());
    }

    public static LocalDateTime parseDate(final String localDateTimeString) {
        if (StringUtils.isBlank(localDateTimeString)) {
            return null;
        }
        return LocalDate.parse(localDateTimeString, YYYYM_MM_DD).atStartOfDay();
    }
}
