package gov.pcc.pwc.service.genSerialNumber.enums;

public enum InsGenNumberVariableEnum {
    DUCON_OVERSEE("DUCON_OVERSEE"),
    DUCON_1999("DUCON_1999");

    private final String variableName;

    InsGenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
