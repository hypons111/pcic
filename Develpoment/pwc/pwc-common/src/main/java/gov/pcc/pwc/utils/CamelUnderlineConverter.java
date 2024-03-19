package gov.pcc.pwc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CamelUnderlineConverter {

    private CamelUnderlineConverter() {}

    /**
     * 下底線轉駝峰
     * @param str 要轉換的文字
     * @return 轉換結果
     */
    public static StringBuffer camel(String str) {
        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return camel(sb.toString());
    }

    /**
     * 駝峰轉下底線
     * @param str 要轉換的文字
     * @return 轉換結果
     */
    public static StringBuffer underline(String str) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return underline(sb.toString());
    }
}
