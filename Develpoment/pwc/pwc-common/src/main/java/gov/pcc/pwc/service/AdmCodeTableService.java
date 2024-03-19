package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmCodeTable;
import gov.pcc.pwc.repository.AdmCodeTableRepository;
import gov.pcc.pwc.service.criteria.AdmCodeTableCriteria;
import gov.pcc.pwc.service.dto.AdmCodeTableDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdmCodeTableService {
    public static final String FIND_ALL_DATA_TYPE_OPTS= "findAllDataTypeOpts";


    private final Logger log = LoggerFactory.getLogger(AdmCodeTableService.class);

    private final AdmCodeTableRepository admCodeTableRepository;

    public AdmCodeTableService(AdmCodeTableRepository admCodeTableRepository) {
        this.admCodeTableRepository = admCodeTableRepository;
    }


    /**
     * Find the page of AdmCodeTableDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the page of AdmCodeTableDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmCodeTableDTO> findByCriteria(AdmCodeTableCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "lang" });
        }
        return admCodeTableRepository.findAllByCriteria(criteria);
    }

    /**
     * Find the AdmCodeTableDTO by PK.
     *
     * @param id the PK.
     * @return the list of AdmCodeTableDTO.
     */
    @Transactional(readOnly = true)
    public AdmCodeTableDTO findByPk(Long id) {
        return admCodeTableRepository.findByPk(id);
    }

    /**
     * Update the AdmCodeTableDTO.
     *
     * @param admCodeTableDTO the AdmCodeTableDTO.
     * @return updated AdmCodeTableDTO.
     */
    @Transactional(readOnly = true)
    public AdmCodeTableDTO update(AdmCodeTableDTO admCodeTableDTO) {
        admCodeTableRepository.update(admCodeTableDTO);
        return admCodeTableDTO;
    }

    @Transactional(readOnly = true)
    public Map<String, Map<String, Map<String, String>>> getI18n() {
        log.debug("Request to get i18n");
        return admCodeTableRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        AdmCodeTable::getLang,
                        Collectors.groupingBy(
                                AdmCodeTable::getCategory,
                                Collectors.toMap(AdmCodeTable::getCode, AdmCodeTable::getText))));
    }
}
