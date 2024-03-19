package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidGovCheck;
import gov.pcc.pwc.repository.BidPlaceRepository;
import gov.pcc.pwc.service.criteria.BidPlaceCriteria;
import gov.pcc.pwc.service.dto.BidPlaceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link BidGovCheck}.
 */
@Service
@Transactional
public class BidPlaceService {

    private final Logger log = LoggerFactory.getLogger(BidPlaceService.class);

    private final BidPlaceRepository bidPlaceRepository;

    public BidPlaceService(BidPlaceRepository bidPlaceRepository) {
        this.bidPlaceRepository = bidPlaceRepository;
    }
    
    /**
     * Find the list of BidGovCheckDTO by placeCode.
     *
     * @param placeCode the placeCode.
     * @return the list of BidGovCheckDTO.
     */
    @Transactional(readOnly = true)
    public List<BidPlaceDTO> findMapInfoByPlaceCode(String placeCode) {
        log.debug("placeCode{}", placeCode);
        return bidPlaceRepository.findMapInfoByPlaceCode(placeCode);
    }

    /**
     * Find the list of BidPlaceDTO by placeCode.
     *
     * @param criteria the placeCode.
     * @return the list of BidGovCheckDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidPlaceDTO> findProjectCountByCriteria(BidPlaceCriteria criteria) {
        log.debug("criteria.toPageable{}", criteria.toString());
        return bidPlaceRepository.findProjectCountByCriteria(criteria);
    }

}
