package gov.pcc.pwc.service.genSerialNumber.enums;

public enum RevGenNumberVariableEnum {
    CODE("CODE");

    private final String variableName;

    RevGenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
