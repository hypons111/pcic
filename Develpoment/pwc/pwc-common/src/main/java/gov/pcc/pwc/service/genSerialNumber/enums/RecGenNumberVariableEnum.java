package gov.pcc.pwc.service.genSerialNumber.enums;

public enum RecGenNumberVariableEnum {
    TYP("Typ"),
    PRJ("Prj"),
    ACT("Act"),
    Are("Are"),
    Pic("Pic"),
    New("New"),

    WOR("Wor"),
    ANA("Ana"),
    FIL("Fil"),
    ;

    private final String variableName;

    RecGenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
