package gov.pcc.pwc.report;

import org.codehaus.plexus.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

public abstract class ReportFormatter {

    /**
     * 日期格式轉換
     */
    protected String parseLocalDateToYYYMMDD(LocalDate localDate) {
        String rocString = null;
        if (localDate != null) {
            rocString = String.format("%03d", localDate.getYear() - 1911) + "年"
                    + String.format("%02d", localDate.getMonthValue()) + "月"
                    + String.format("%02d", localDate.getDayOfMonth()) + "日";
        }
        return rocString;
    }

    protected String parseLocalDateToYYYMMDD(LocalDate localDate, String delimiter) {
        String rocString = null;
        if (localDate != null) {
            rocString = String.format("%03d", localDate.getYear() - 1911) + delimiter
                    + String.format("%02d", localDate.getMonthValue()) + delimiter
                    + String.format("%02d", localDate.getDayOfMonth());
        }
        return rocString;
    }

    protected String parseLocalDateToYYYMM(LocalDate localDate) {
        String rocString = null;
        if (localDate != null) {
            rocString = String.format("%03d", localDate.getYear() - 1911) + "年度"
                    + String.format("%02d", localDate.getMonthValue()) + "月份";
        }
        return rocString;
    }

    protected String parseLocalDateToYYYMM(LocalDate localDate, String delimiter) {
        String rocString = null;
        if (localDate != null) {
            rocString = String.format("%03d", localDate.getYear() - 1911) + delimiter
                    + String.format("%02d", localDate.getMonthValue()) + delimiter;
        }
        return rocString;
    }

    /**
     * 數字格式轉換
     */
    protected String numberFormat(BigDecimal number) {
        String numString = null;
        if (number != null) {
            DecimalFormat df = new DecimalFormat("#,###");
            numString = df.format(number);
        }
        return numString;
    }

    protected String numberFormat(BigDecimal number, String pattern) {
        String numString = null;
        if (number != null) {
            if (StringUtils.isBlank(pattern)) {
                numString = numberFormat(number);
            } else {
                DecimalFormat df = new DecimalFormat(pattern);
                numString = df.format(number);
            }
        }
        return numString;
    }

    /**
     * 數字：null轉成0
     */
    protected BigDecimal empty2Zero(BigDecimal amt) {
        return (amt == null ? BigDecimal.ZERO : amt);
    }

    /**
     * 字串：null轉成空字串
     */
    protected String empty2String(String str) {
        return (str == null ? "" : str);
    }

    protected String empty2String(String str, String defaultValue) {
        return (str == null ? defaultValue : str);
    }

    /**
     * Object格式轉型
     */
    protected static BigDecimal toDecimal(Object rawData) {
        if (rawData == null) {
            return null;
        }
        if (rawData instanceof BigDecimal) {
            return (BigDecimal) rawData;
        }
        if (rawData instanceof Double) {
            return BigDecimal.valueOf((Double) rawData);
        }
        if (rawData instanceof Number) {
            return BigDecimal.valueOf(((Number) rawData).doubleValue());
        }
        return new BigDecimal(Objects.toString(rawData));
    }

    protected LocalDate toLocalDate(Object object) {
        LocalDate localDate = null;
        if (object instanceof LocalDate) {
            localDate =  (LocalDate)object;
        }
        return localDate;
    }

}
