package gov.pcc.pwc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


public class BidUnit10NameAndCodeDTO {

    private final String code;
    private final String name;

    public BidUnit10NameAndCodeDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
