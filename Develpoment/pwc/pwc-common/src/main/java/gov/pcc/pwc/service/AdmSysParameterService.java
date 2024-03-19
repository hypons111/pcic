package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmSysParameter;
import gov.pcc.pwc.repository.AdmSysParameterRepository;
import gov.pcc.pwc.service.criteria.AdmSysParameterCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionExtendDTO;
import gov.pcc.pwc.service.dto.AdmSysParameterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.web.util.reactive.ResponseUtil;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdmSysParameterService {

    public static final String FIND_ALL_SYS_TYPE_OPTS= "findAllSysTypeOpts";

    private final Logger log = LoggerFactory.getLogger(AdmSysParameterService.class);

    private final AdmSysParameterRepository admSysParameterRepository;

    public AdmSysParameterService(AdmSysParameterRepository admSysParameterRepository) {
        this.admSysParameterRepository = admSysParameterRepository;
    }

    /**
     * Find the list of AdmSysParameterDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmSysParameterDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmSysParameterDTO> findByCriteria(AdmSysParameterCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "sysName" });
        }
        final Page<AdmSysParameterDTO> AdmSysParameterDTOs = admSysParameterRepository.findAllByCriteria(criteria);
        return AdmSysParameterDTOs;
    }

    /**
     * Find the AdmSysParameterDTO by PK.
     *
     * @param sysName,sysType the PK.
     * @return the list of AdmSysParameterDTO.
     */
    @Transactional(readOnly = true)
    public AdmSysParameterDTO findByPk(String sysName, String sysType) {
        Optional<AdmSysParameter> result = admSysParameterRepository.findBySysNameAndSysType(sysName, sysType);
        AdmSysParameter admSysParameter = result.get();
        AdmSysParameterDTO admSysParameterDTO = new AdmSysParameterDTO();
        admSysParameterDTO.setSysName(admSysParameter.getSysName());
        admSysParameterDTO.setSysType(admSysParameter.getSysType());
        admSysParameterDTO.setSysValue(admSysParameter.getSysValue());
        admSysParameterDTO.setDescript(admSysParameter.getDescript());
        admSysParameterDTO.setStatus(admSysParameter.getStatus());
        admSysParameterDTO.setUpdateUser(admSysParameter.getUpdateUser());
        admSysParameterDTO.setUpdateTime(admSysParameter.getUpdateTime());
        return admSysParameterDTO;
    }

    /**
     * Find all AdmSysParameterDTO for front-end sysTypeOpts
     *
     * @return the list of AdmSysParameterDTO.
     */

    @Cacheable(FIND_ALL_SYS_TYPE_OPTS)
    public List<AdmSysParameterDTO> findAllSysTypeOpts() {
        return admSysParameterRepository.findAllSysTypeOpts();
    }

    /**
     * Update the AdmSysParameterDTO.
     *
     * @param admSysParameterDTO the AdmSysParameterDTO.
     * @return updated AdmSysParameterDTO.
     */
    @Transactional(readOnly = true)
    public AdmSysParameterDTO update(AdmSysParameterDTO admSysParameterDTO) {
        admSysParameterRepository.update(admSysParameterDTO);
        return admSysParameterDTO;
    }

}
