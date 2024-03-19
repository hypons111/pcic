package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmSysParameterCriteria;
import gov.pcc.pwc.service.dto.AdmSysParameterDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdmSysParameterRepositoryCustom {
    Page<AdmSysParameterDTO> findAllByCriteria(AdmSysParameterCriteria criteria);
    AdmSysParameterDTO findByPk(String sysName, String sysType);
    List<AdmSysParameterDTO> findAllSysTypeOpts();
    int update(AdmSysParameterDTO dto);
}
