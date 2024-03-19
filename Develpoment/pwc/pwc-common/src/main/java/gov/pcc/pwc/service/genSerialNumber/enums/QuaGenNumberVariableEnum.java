package gov.pcc.pwc.service.genSerialNumber.enums;

public enum QuaGenNumberVariableEnum {
    QTRAIN_QTYCLASS("QTRAIN_QTYCLASS");

    private final String variableName;

    QuaGenNumberVariableEnum(String variableName) {
        this.variableName = variableName;
    }

    public String getVariable() {
        return variableName;
    }
}
