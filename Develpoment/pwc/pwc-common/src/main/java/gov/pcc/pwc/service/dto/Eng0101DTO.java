package gov.pcc.pwc.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Eng0101DTO implements Serializable {
    private EngEngrCertificateDTO engEngrCertificateDTO;
    private EngEbillingCheckDTO engEbillingCheckDTO;
    private List<EngEbillingCheckDtlDTO> engEbillingCheckDtlDTOList;
}
