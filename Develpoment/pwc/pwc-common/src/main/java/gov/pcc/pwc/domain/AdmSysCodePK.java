package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdmSysCodePK implements Serializable {
    private String moduleType;
    private String dataType;
    private String dataKey;
}
