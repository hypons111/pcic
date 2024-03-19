package gov.pcc.pwc.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessTypeEnum implements CodeEnum<String> {
    ENG_ENGR_CERTIFICATE("ENG_ENGR_CERTIFICATE", "技師證書"),
    ENG_ENGR_LICENSE("ENG_ENGR_LICENSE", "技師執照"),
    ENG_ENGR_DISCIPLINE("ENG_ENGR_DISCIPLINE", "技師懲戒"),
    ENG_CST_LICENSE("ENG_CST_LICENSE", "顧問公司許可登記"),
    ENG_COMP_CHECK("ENG_COMP_CHECK", "公司查核"),
    PRO_INNOVATIVE_PRODUCT("PRO_INNOVATIVE_PRODUCT","創新產品"),
    PWC_INFORMATION("PWC_INFORMATION", "公告事項"),
    PWC_DOWNLOAD("PWC_DOWNLOAD", "下載專區") ;

    private final String code;
    private final String desc;
}
