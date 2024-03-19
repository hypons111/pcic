package gov.pcc.pwc.common.errors;

public class BidErrorResponse {

    private String message;

    private String errorKey;

    private String[] detailMessages;

    public BidErrorResponse(String message,String errorKey, String[] detailMessages) {
        this.errorKey = errorKey;
        this.message = message;
        this.detailMessages = detailMessages;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getDetailMessages() {
        return detailMessages;
    }

    public void setDetailMessages(String[] detailMessages) {
        this.detailMessages = detailMessages;
    }
}
