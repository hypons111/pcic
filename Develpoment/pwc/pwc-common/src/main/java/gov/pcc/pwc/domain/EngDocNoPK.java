package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class EngDocNoPK implements Serializable {
    private String engDocNoType;
    private String engDocNoNo;
}
