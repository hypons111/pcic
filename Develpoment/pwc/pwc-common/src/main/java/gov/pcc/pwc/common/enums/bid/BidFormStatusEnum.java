package gov.pcc.pwc.common.enums.bid;

public enum BidFormStatusEnum {
    CREATE ("CREATE"),
    UPDATE("UPDATE"),
    ;
    private final String formStatus;

    BidFormStatusEnum(String formStatus) {
        this.formStatus = formStatus;
    }
    public String getFormStatus() {
        return formStatus;
    }
}
