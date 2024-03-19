package gov.pcc.pwc.service.genSerialNumber.enums;

public enum EngGenNumberVariableEnum {
    //技師證書序號ID(申請日期西元年+月份)
    ENGR_CERTIFATE_ID("ENGR_CERTIFATE_ID"),
    //書面積分審查ID
    ENGR_SUBJECTPOINT_ID("ENGR_SUBJECTPOINT_ID"),

    //公司檔序號
    MEMBER("MEMBER_ID"),
    //人檔序號
    COMP("COMP_ID"),
    //公司許可登記
    CST_LICENSE("CST_LICENSE"),
    //技師證書編號NO(申請日期西元年)
    ENGR_CERTIFATE_NO("ENGR_CERTIFATE_NO"),
    //技師執業執照,
    ENGR_LICENSE("ENGR_LICENSE"),
    //課程序號
    COURSE("COURSE_ID"),
    //課程講師序號
    TEACHER("TEACHER_ID"),
    //審核紀錄
    REVIEW_LOG("REVIEW_LOG"),

    //證書字號
    ENGR_CERTIFICATE_DOC_NO("ENGR_CERTIFICATE_DOC_NO"),
    //執業執照字號
    ENGR_LICENCE_DOC_NO("ENGR_LICENCE_DOC_NO"),
    //公司許可登記字號
    CST_LICENCE_DOC_NO("CST_LICENCE_DOC_NO"),

    //電子對帳
    EBILLING_CHECK("EBILLING_CHECK"),
    ;

    private final String variableName;

    EngGenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
