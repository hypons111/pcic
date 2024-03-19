package gov.pcc.pwc.service;

import gov.pcc.pwc.repository.AdmUnitRepository;
import gov.pcc.pwc.service.dto.AdmUnitDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdmUnitService {
    public static final String FIND_ALL_UNIT_OPTS= "findAllUnitOpts";

    private final Logger log = LoggerFactory.getLogger(AdmUnitService.class);

    private final AdmUnitRepository admUnitRepository;

    public AdmUnitService(AdmUnitRepository admUnitRepository) {
        this.admUnitRepository = admUnitRepository;
    }

    /**
     * Find all unit for front-end unitOpts
     *
     * @return the list of AdmUnitDTO.
     */

    @Cacheable(FIND_ALL_UNIT_OPTS)
    public List<AdmUnitDTO> findAllUnitOpts() {
        return admUnitRepository.findAllUnitOpts();
    }
}
