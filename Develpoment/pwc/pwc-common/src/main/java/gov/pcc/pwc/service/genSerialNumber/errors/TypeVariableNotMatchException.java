package gov.pcc.pwc.service.genSerialNumber.errors;

public class TypeVariableNotMatchException extends Exception{
    private final String message = "Type and Variable Not Match";

    @Override
    public String getMessage() {
        return message;
    }
}
