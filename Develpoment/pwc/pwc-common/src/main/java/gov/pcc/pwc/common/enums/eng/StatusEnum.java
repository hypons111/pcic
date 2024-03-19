package gov.pcc.pwc.common.enums.eng;

import gov.pcc.pwc.common.enums.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEnum implements CodeEnum<String> {
    TEMP("0", "暫存"),
    RETURN("1", "核退"),
    REVIEW1("2", "待審核(一關)"),
    REVIEW2_1("3", "待審核(二關)"),
    REVIEW2_2("4", "審核中(二關)"),
    CLOSE("5", "結案"),
    DISCARD("6", "作廢");

    private final String code;
    private final String desc;
}