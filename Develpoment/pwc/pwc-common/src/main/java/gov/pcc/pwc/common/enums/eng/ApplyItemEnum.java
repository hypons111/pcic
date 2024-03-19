package gov.pcc.pwc.common.enums.eng;

import gov.pcc.pwc.common.enums.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplyItemEnum implements CodeEnum<String> {
    NEW_APPLY("0", "新申請"),
    APPLY_EN("1", "申請英文證書"),
    REISSUE("2", "補發"),
    ABOLISH("4", "廢止");

    private final String code;
    private final String desc;
}