package gov.pcc.pwc.service.genSerialNumber.enums;

public enum ProGenNumberVariableEnum {
    INNOVATIVE_PRODUCT("INNOVATIVE_PRODUCT");

    private final String variableName;

    ProGenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
