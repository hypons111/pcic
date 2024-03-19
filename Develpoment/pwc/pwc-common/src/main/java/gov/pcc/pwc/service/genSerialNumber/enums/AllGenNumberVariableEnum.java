package gov.pcc.pwc.service.genSerialNumber.enums;

public enum AllGenNumberVariableEnum {
    API_CALL_NO("API_CALL_NO");

    private final String variableName;

    AllGenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
