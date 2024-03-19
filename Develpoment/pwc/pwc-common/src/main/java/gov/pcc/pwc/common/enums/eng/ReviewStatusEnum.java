package gov.pcc.pwc.common.enums.eng;

import gov.pcc.pwc.common.enums.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewStatusEnum implements CodeEnum<String> {
    APPLY("1", "申請"),
    REAPPLY("2", "重新申請"),
    APPROVE("3", "核准"),
    RETURN("4", "核退"),
    CANCEL("5", "取消核准"),
    DISCARD("6", "作廢");

    private final String code;
    private final String desc;
}
