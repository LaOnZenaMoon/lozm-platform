package lozm.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * <p>KtisDateUtils</p>
 * <p>
 * 날짜 변한 유틸리티
 * </p>
 * 수정시 @version 에 수정일자를 변경하여 commit 해 주세요
 * @version 2020.04.10
 */
public class DateUtils {

    public final static DateTimeFormatter HHmm = DateTimeFormatter.ofPattern("HHmm");
    public final static DateTimeFormatter HHmmss = DateTimeFormatter.ofPattern("HHmmss");

    public final static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    public final static DateTimeFormatter yyyyMMddHH = DateTimeFormatter.ofPattern("yyyyMMddHH");
    public final static DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public final static DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public final static DateTimeFormatter yyyyMMddHHmmssSSS = new DateTimeFormatterBuilder()
            .appendPattern("yyyyMMddHHmmss").appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();

    public final static DateTimeFormatter IOS_HHmm = DateTimeFormatter.ofPattern("HH:mm");
    public final static DateTimeFormatter IOS_HHmmss = DateTimeFormatter.ofPattern("HH:mm:ss");
    public final static DateTimeFormatter IOS_yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final static DateTimeFormatter IOS_yyyyMMddHH = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH");
    public final static DateTimeFormatter IOS_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    public final static DateTimeFormatter IOS_yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public final static DateTimeFormatter IOS_yyyyMMddHHmmssSSS = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public final static DateTimeFormatter IOS_yyyyMMdd_HH = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    public final static DateTimeFormatter IOS_yyyyMMdd_HHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public final static DateTimeFormatter IOS_yyyyMMdd_HHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final static DateTimeFormatter IOS_yyyyMMdd_HHmmssSSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static boolean checkDate(String date) {
        try {
            parseDateTime(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String format(String date, String toFormat) {
        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern(toFormat);
        if ("yyyyMMddHHmmssSSS".equals(toFormat)) {
            toFormatter = yyyyMMddHHmmssSSS;
        }
        return format(date, toFormatter);
    }

    public static String format(String date, DateTimeFormatter toFormat) {
        return parseDateTime(date).format(toFormat);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, getFormatter(date));
    }

    public static LocalDateTime parseDateTime(String date) {
        DateTimeFormatter formatter = getFormatter(date);
        if (formatter == yyyyMMdd) {
            return LocalDateTime.parse(date + "00", yyyyMMddHH);
        }
        if (formatter == IOS_yyyyMMdd) {
            return LocalDateTime.parse(date + "T00", IOS_yyyyMMddHH);
        }
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalTime parseTime(String date) {
        DateTimeFormatter formatter = getFormatter(date);
        if (formatter == yyyyMMdd) {
            return LocalTime.parse(date + "00", yyyyMMddHH);
        }
        if (formatter == IOS_yyyyMMdd) {
            return LocalTime.parse(date + "T00", IOS_yyyyMMddHH);
        }
        return LocalTime.parse(date, getFormatter(date));
    }

    private static DateTimeFormatter getFormatter(String date) {
        if (StringUtils.isBlank(date)) {
            throw new IllegalArgumentException("유효하지 않은 date 타입 입니다.");
        }
        int length = date.length();
        if (StringUtils.containsAny(date, "-", ":")) {
            if (length == 5)
                return IOS_HHmm;
            if (length == 8)
                return IOS_HHmmss;
            if (length == 10)
                return IOS_yyyyMMdd;

            if (StringUtils.contains(date, "T")) {
                if (length == 13)
                    return IOS_yyyyMMddHH;
                if (length == 16)
                    return IOS_yyyyMMddHHmm;
                if (length == 19)
                    return IOS_yyyyMMddHHmmss;
                if (length == 23)
                    return IOS_yyyyMMddHHmmssSSS;
            } else {
                if (length == 13)
                    return IOS_yyyyMMdd_HH;
                if (length == 16)
                    return IOS_yyyyMMdd_HHmm;
                if (length == 19)
                    return IOS_yyyyMMdd_HHmmss;
                if (length == 23)
                    return IOS_yyyyMMdd_HHmmssSSS;
            }

        } else {
            if (length == 4)
                return HHmm;
            if (length == 6)
                return HHmmss;
            if (length == 8)
                return yyyyMMdd;
            if (length == 10)
                return yyyyMMddHH;
            if (length == 12)
                return yyyyMMddHHmm;
            if (length == 14)
                return yyyyMMddHHmmss;
            if (length == 17)
                return yyyyMMddHHmmssSSS;
        }
        throw new IllegalArgumentException("유효하지 않은 date 타입 입니다.");
    }
}
