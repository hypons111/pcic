package gov.pcc.pwc.common.enums.eng;

import gov.pcc.pwc.common.enums.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberTypeEnum implements CodeEnum<String> {
    ENGR("0", "技師"),
    CONTRACTOR("1", "承辦人(工程會)"),
    MASTER("2", "公司負責人"),
    REPRESENTATIVE("3", "其它團體代表人");

    private final String code;
    private final String desc;
}
