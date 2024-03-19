package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.repository.AdmSysCodeRepository;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.mapper.AdmSysCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@Transactional
public class AdmSysCodeService {
    public static final String FIND_ALL_DATA_TYPE_OPTS= "findAllDataTypeOpts";

    public static final String FIND_ALL_ADM_SYS_CODE= "findAllAdmSysCode";

    private final Logger log = LoggerFactory.getLogger(AdmSysCodeService.class);

    private final AdmSysCodeRepository admSysCodeRepository;

    private final AdmSysCodeMapper admSysCodeMapper;

    public AdmSysCodeService(AdmSysCodeRepository admSysCodeRepository, AdmSysCodeMapper admSysCodeMapper) {
        this.admSysCodeRepository = admSysCodeRepository;
        this.admSysCodeMapper = admSysCodeMapper;
    }


    /**
     * Find the list of AdmSysCodeDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmSysCodeDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmSysCodeDTO> findByCriteria(AdmSysCodeCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "moduleType" });
        }
        final Page<AdmSysCodeDTO> AdmSysCodeDTOs = admSysCodeRepository.findAllByCriteria(criteria);
        return AdmSysCodeDTOs;
    }

    /**
     * Get all the admSysCodes.
     *
     * @return the list of entities.
     */
//    @Cacheable(FIND_ALL_ADM_SYS_CODE)
    @Transactional(readOnly = true)
    public List<AdmSysCodeDTO> findAll() {
        log.debug("Request to get all AdmSysCodes");
        return admSysCodeRepository
            .findAll(Sort.by(Sort.Direction.ASC, "sortNo"))
            .stream()
            .map(admSysCodeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Find the AdmSysCodeDTO by PK.
     *
     * @param moduleType,dataType,dataKey the PK.
     * @return the list of AdmSysCodeDTO.
     */
    @Transactional(readOnly = true)
    public AdmSysCodeDTO findByPk(String moduleType, String dataType, String dataKey) {
        Optional<AdmSysCode> result = admSysCodeRepository.findByModuleTypeAndDataTypeAndDataKey(moduleType ,dataType, dataKey);
        AdmSysCode admSysCode = result.get();
        AdmSysCodeDTO admSysCodeDTO = new AdmSysCodeDTO();
        admSysCodeDTO.setModuleType(admSysCode.getModuleType());
        admSysCodeDTO.setDataType(admSysCode.getDataType());
        admSysCodeDTO.setDataKey(admSysCode.getDataKey());
        admSysCodeDTO.setValue(admSysCode.getValue());
        admSysCodeDTO.setSortNo(admSysCode.getSortNo());
        admSysCodeDTO.setCodeDescript(admSysCode.getCodeDescript());
        admSysCodeDTO.setEnCodeDescript(admSysCode.getEnCodeDescript());
        admSysCodeDTO.setRemark(admSysCode.getRemark());
        admSysCodeDTO.setIsValid(admSysCode.getIsValid());
        admSysCodeDTO.setValidFromDate(admSysCode.getValidFromDate());
        admSysCodeDTO.setIsModify(admSysCode.getIsModify());
        admSysCodeDTO.setAttr1(admSysCode.getAttr1());
        admSysCodeDTO.setAttr2(admSysCode.getAttr2());
        admSysCodeDTO.setCreateUser(admSysCode.getCreateUser());
        admSysCodeDTO.setCreateTime(admSysCode.getCreateTime());
        admSysCodeDTO.setUpdateUser(admSysCode.getUpdateUser());
        admSysCodeDTO.setUpdateTime(admSysCode.getUpdateTime());
        return admSysCodeDTO;
    }

    /**
     * Find all AdmSysCodeDTO for front-end sysTypeOpts
     *
     * @return the list of AdmSysParameterDTO.
     */

    @Cacheable(FIND_ALL_DATA_TYPE_OPTS)
    public List<AdmSysCodeDTO> findAllDataTypeOpts() {
        return admSysCodeRepository.findAllDataTypeOpts();
    }

    /**
     * Update the AdmSysCodeDTO.
     *
     * @param admSysCodeDTO the AdmSysCodeDTO.
     * @return updated AdmSysCodeDTO.
     */
    @Transactional(readOnly = true)
    public AdmSysCodeDTO update(AdmSysCodeDTO admSysCodeDTO) {
        admSysCodeRepository.update(admSysCodeDTO);
        return admSysCodeDTO;
    }

}
