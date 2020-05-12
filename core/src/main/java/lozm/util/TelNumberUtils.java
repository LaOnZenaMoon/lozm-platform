package lozm.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lozm.util.TelNumberUtils.Range.R4;
import static lozm.util.TelNumberUtils.Range.R8;


public class TelNumberUtils {

    public static String getDdd(String telNumber) {
        return getMature(telNumber).getDDD();
    }

    public static String getRowTelNumber(String telNumber) {
        String fixTelNumber = getMature(telNumber).getTelNumber(telNumber);
        return StringUtils.replace(fixTelNumber, "-", "");
    }

    public static String getTel(String telNumber) {
        return getMature(telNumber).getTel(telNumber);
    }

    public static String getTelNumber(String telNumber) {
        return getMature(telNumber).getTelNumber(telNumber);
    }

    public static List<String> getTelNumbers(String telNumber) {
        return getMature(telNumber).getTelNumbers(telNumber);
    }

    public static boolean isMobile(String telNumber) {
        String ddd = getDdd(telNumber);
        return Arrays.asList(DDD_MOBILE_ARRAY).contains(ddd);
    }

    public static void validate(String telNumber) {
        getMature(telNumber);
    }

    private static String clearTelNumber(String telNumber) {
        preValidate(telNumber);
        telNumber = StringUtils.trim(telNumber);
        telNumber = StringUtils.replace(telNumber, "-", "");
        if (StringUtils.contains(telNumber, ",")) {
            telNumber = StringUtils.split(telNumber, ",")[0];
        }
        if (StringUtils.contains(telNumber, "/")) {
            telNumber = StringUtils.split(telNumber, "/")[0];
        }
        return telNumber;
    }

    private static Mature getMature(String telNumber) {
        preValidate(telNumber);
        String clearTelNumber = clearTelNumber(telNumber);
        int length = clearTelNumber.length();
        if (length == 3)
            return DDD_ARRAY[0];
        if (length == 4 && isTel4(clearTelNumber)) {
            return DDD_ARRAY[0];
        }

//        return Arrays.stream(DDD_ARRAY)
//                .filter(dddType -> dddType.match(clearTelNumber))
//                .findFirst()
//                .orElseThrow(() -> makeException(clearTelNumber));

        //5% 정도 성능 향상(1억건 기준 stream : 30.229~30.897, for: 26.948~27.400)
        for (Mature mature : DDD_ARRAY) {
            if (mature.match(clearTelNumber))
                return mature;
        }
        throw makeException(clearTelNumber);

    }

    private static boolean isTel4(String telNumber) {
        String tel = StringUtils.substring(telNumber, 0, 3);
        return Arrays.asList(TEL_4).contains(tel);
    }

    private static IllegalArgumentException makeException(String telNumber) {
        return new IllegalArgumentException(String.format("%s [%s]", ExceptionMessage, telNumber));
    }

    private static void preValidate(String telNumber) {
        if (StringUtils.isBlank(telNumber)) {
            throw makeException(telNumber);
        }
    }

    private static final String ExceptionMessage = "유효하지 않은 전화번호 입니다";

    /**
     * <pre>
     * SELECT 'Mature.of("' || DDD || '", ' || TEL || '),' RES
     * FROM (SELECT DISTINCT DDD, listagg(TEL, ', ') WITHIN GROUP (ORDER BY TEL) OVER (PARTITION BY DDD) AS TEL
     *       FROM (
     *                SELECT DDD, CASE WHEN length(replace(TEL, '-', '')) <= 4 then 'R4' else 'R8' end AS TEL
     *                FROM TSM_BUSINESS
     *                GROUP BY DDD, CASE WHEN length(replace(TEL, '-', '')) <= 4 then 'R4' else 'R8' end
     *            )
     *      )
     * ORDER BY RES
     * </pre>
     */
    private static final Mature[] DDD_ARRAY = {
            Mature.of("000", R4),
            Mature.of("010", R8),
            Mature.of("011", R8),
            Mature.of("016", R8),
            Mature.of("017", R8),
            Mature.of("018", R8),
            Mature.of("019", R8),
            Mature.of("02", R4, R8),
            Mature.of("031", R4, R8),
            Mature.of("032", R4, R8),
            Mature.of("033", R4, R8),
            Mature.of("041", R4, R8),
            Mature.of("042", R4, R8),
            Mature.of("043", R4, R8),
            Mature.of("044", R8),
            Mature.of("0502", R8),
            Mature.of("0504", R8),
            Mature.of("0505", R8),
            Mature.of("0507", R8),
            Mature.of("0508", R8),
            Mature.of("051", R4, R8),
            Mature.of("052", R4, R8),
            Mature.of("053", R4, R8),
            Mature.of("054", R4, R8),
            Mature.of("055", R4, R8),
            Mature.of("060", R8),
            Mature.of("061", R4, R8),
            Mature.of("062", R4, R8),
            Mature.of("063", R4, R8),
            Mature.of("064", R4, R8),
            Mature.of("070", R8),
            Mature.of("080", R8),
            Mature.of("1422", R4),
            Mature.of("1433", R4),
            Mature.of("1466", R4),
            Mature.of("1522", R4),
            Mature.of("1544", R4),
            Mature.of("1566", R4),
            Mature.of("1577", R4),
            Mature.of("1588", R4),
            Mature.of("1599", R4),
            Mature.of("1600", R4),
            Mature.of("1607", R4),
            Mature.of("1608", R4),
            Mature.of("1644", R4),
            Mature.of("1661", R4),
            Mature.of("1666", R4),
            Mature.of("1668", R4),
            Mature.of("1670", R4),
            Mature.of("1688", R4),
            Mature.of("1800", R4),
            Mature.of("1811", R4),
            Mature.of("1833", R4),
            Mature.of("1855", R4),
            Mature.of("1877", R4),
            Mature.of("1899", R4),
    };

    private static final String[] DDD_MOBILE_ARRAY = {
            "010", "011", "016", "017", "018", "019"
    };

    private static final String[] TEL_4 = {
            "130", "133", "134", "135", "136", "137", "138", "139", "154", "157", "158"
    };

    protected enum Range {
        R4,
        R8;

        Range() {
        }

        private static Range getRange(int length) {
            if (length >= 1 && length <= 4) {
                return R4;
            }
            if (length >= 6 && length <= 8) {
                return R8;
            }
            throw new IllegalArgumentException(ExceptionMessage);
        }
    }

    private static class Mature {

        private String ddd;
        private Range[] telRanges;

        private Mature(String ddd, Range[] telRanges) {
            this.ddd = ddd;
            this.telRanges = telRanges;
        }

        private static Mature of(String ddd, Range... telRanges) {
            return new Mature(ddd, telRanges);
        }

        private String getDDD() {
            if ("000".equals(ddd))
                return "";
            return ddd;
        }

        private String getTel(String telNumber) {
            telNumber = clearTelNumber(telNumber);
            String tel = StringUtils.replace(telNumber, ddd, "");
            Range range = Range.getRange(tel.length());
            if (range == R4)
                return tel;

            int length = tel.length();
            String tel1 = StringUtils.substring(tel, 0, length - 4);
            String tel2 = StringUtils.substring(tel, length - 4);
            return tel1 + "-" + tel2;
        }

        private String getTelNumber(String telNumber) {
            String ddd = getDDD();
            if (ddd.length() == 0) {
                return getTel(telNumber);
            }
            return ddd + "-" + getTel(telNumber);
        }

        private List<String> getTelNumbers(String telNumber) {
            List<String> telNumbers = new ArrayList<>();
            String baseTelNumber = getTelNumber(telNumber);
            telNumbers.add(baseTelNumber);
            if (telNumber.contains(",")) {
                String[] split = telNumber.split(",");
                for (int i = 1; i < split.length; i++) {
                    String suffix = StringUtils.trim(split[i]);
                    String addTelNumber = baseTelNumber.substring(0, baseTelNumber.length() - suffix.length()) + suffix;
                    telNumbers.add(addTelNumber);
                }
            }
            if (telNumber.contains("/")) {
                String[] split = telNumber.split("/");
                String suffix = StringUtils.trim(split[1]);
                int size = suffix.length();
                String base = baseTelNumber.substring(0, baseTelNumber.length() - size);
                int from = NumberUtils.toInt(StringUtils.substring(baseTelNumber, baseTelNumber.length() - size), 0);
                int to = NumberUtils.toInt(suffix, 0);
                for (int i = from + 1; i <= to; i++) {
                    telNumbers.add(base + String.format("%0" + size + "d", i));
                }
            }
            return telNumbers;
        }

        private boolean match(String telNumber) {
            if (!StringUtils.startsWith(telNumber, ddd))
                return false;
            String tel = telNumber.replaceFirst(ddd, "");

            return ArrayUtils.indexOf(telRanges, Range.getRange(tel.length())) >= 0;
        }

    }

}