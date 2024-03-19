package gov.pcc.pwc.service.genSerialNumber.enums;

public enum Fa2GenNumberVariableEnum {
    IDLE_PLNBAS("IDLE_PLNBAS");

    private final String variableName;

    Fa2GenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
