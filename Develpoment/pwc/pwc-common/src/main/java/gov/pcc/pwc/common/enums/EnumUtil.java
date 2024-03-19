package gov.pcc.pwc.common.enums;

import java.util.Arrays;

public class EnumUtil {
    public static <T extends CodeEnum<Integer>> T getByCode(Integer code, Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> code.equals(e.getCode()))
                .findAny()
                .orElse(null);
    }

    public static <T extends CodeEnum<String>> T getByCode(String code, Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> code.equals(e.getCode()))
                .findAny()
                .orElse(null);
    }
}
