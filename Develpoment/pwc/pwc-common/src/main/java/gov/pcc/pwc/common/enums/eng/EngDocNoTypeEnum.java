package gov.pcc.pwc.common.enums.eng;

import gov.pcc.pwc.common.enums.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EngDocNoTypeEnum implements CodeEnum<String> {
    ENGR_CERTIFICATE("01", "技師證書"),
    ENGR_LICENSE("02", "技師執照"),
    CST_LICENSE("03", "顧問公司許可登記證");

    private final String code;
    private final String desc;
}
