package gov.pcc.pwc.domain;

import lombok.Data;
import java.io.Serializable;

@Data
public class AdmSysParameterPK implements Serializable {
    private String sysName;
    private String sysType;
}
