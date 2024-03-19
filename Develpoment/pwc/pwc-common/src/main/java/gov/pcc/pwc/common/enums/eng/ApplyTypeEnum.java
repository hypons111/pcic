package gov.pcc.pwc.common.enums.eng;

import gov.pcc.pwc.common.enums.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplyTypeEnum implements CodeEnum<String> {
    PAPER_APPLY("0", "紙本申請"),
    ONLINE_APPLY("1", "線上申請");

    private final String code;
    private final String desc;
}
