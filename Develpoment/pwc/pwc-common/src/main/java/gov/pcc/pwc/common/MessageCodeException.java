package gov.pcc.pwc.common;

public class MessageCodeException extends RuntimeException implements HasStatusCode {

    private final MessageCode code;

    public MessageCodeException(MessageCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public MessageCodeException(MessageCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
    }

    @Override
    public MessageCode getCode() {
        return code;
    }
}
