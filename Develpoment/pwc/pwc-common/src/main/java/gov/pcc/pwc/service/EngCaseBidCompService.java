package gov.pcc.pwc.service;

import gov.pcc.pwc.repository.EngCaseBidCompRepository;
import gov.pcc.pwc.service.criteria.EngCaseTotalDTOCriteria;
import gov.pcc.pwc.service.dto.EngCaseTotalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class EngCaseBidCompService {

    private final Logger log = LoggerFactory.getLogger(EngCaseBidCompService.class);

    private final EngCaseBidCompRepository engCaseBidCompRepository;

    public EngCaseBidCompService(EngCaseBidCompRepository engCaseBidCompRepository) {
        this.engCaseBidCompRepository = engCaseBidCompRepository;
    }

    // 查詢簽證分頁
    public Page<EngCaseTotalDTO> findEng0301R1(@NotNull EngCaseTotalDTOCriteria criteria) {
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] {"EXEC_DATE_START"});
            criteria.setSortDirection(Sort.Direction.DESC);
        }
        final Page<EngCaseTotalDTO> page = engCaseBidCompRepository.EngCaseTotalDTOR1(criteria);
        log.debug("service-0301R1" + page);
        return page;
    }

}
