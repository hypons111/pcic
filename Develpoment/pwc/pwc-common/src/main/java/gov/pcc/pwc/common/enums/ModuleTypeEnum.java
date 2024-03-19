package gov.pcc.pwc.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ModuleTypeEnum implements CodeEnum<String> {
    ENG("ENG", "技師顧問"),
    PRO("PRO", "創新平台"),
    PWC("PWC", "公告訊息");

    private final String code;
    private final String desc;

}
