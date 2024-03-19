package gov.pcc.pwc.service.genSerialNumber.enums;

public enum Fa1GenNumberVariableEnum {
    IDLE_UTILITY("IDLE_UTILITY"),
    IDLE_OVERSEE_TEMP("IDLE_OVERSEE_TEMP"),
    IDLE_OVERSEE("IDLE_OVERSEE");

    private final String variableName;

    Fa1GenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
