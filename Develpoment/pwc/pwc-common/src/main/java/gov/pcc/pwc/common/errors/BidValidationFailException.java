package gov.pcc.pwc.common.errors;

import org.springframework.http.HttpStatus;

public class BidValidationFailException extends RuntimeException {

    private String errorKey;

    private String[] detailMessages;

    public BidValidationFailException() {
        super();
    }

    public BidValidationFailException(
            String message
    ) {
        super(message);
    }

    public BidValidationFailException(String errorKey,String message, String[] detailMessages) {
        super(message);
        this.errorKey = errorKey;
        this.detailMessages = detailMessages;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String[] getDetailMessages() {
        return detailMessages;
    }

    public void setDetailMessages(String[] detailMessages) {
        this.detailMessages = detailMessages;
    }
}
