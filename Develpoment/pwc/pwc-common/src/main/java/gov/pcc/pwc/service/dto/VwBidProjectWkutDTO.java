package gov.pcc.pwc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


public class VwBidProjectWkutDTO {
    private final String wkut;
    private final String wkutName;

    public VwBidProjectWkutDTO(String wkut, String wkutName) {
        this.wkut = wkut;
        this.wkutName = wkutName;
    }

    public String getWkut() {
        return wkut;
    }

    public String getWkutName() {
        return wkutName;
    }
}