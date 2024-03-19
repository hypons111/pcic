package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.BidCcindRepository;
import gov.pcc.pwc.service.dto.BidCcindDTO;
import gov.pcc.pwc.service.mapper.BidCcindMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BidCcind}.
 */
@Service
@Transactional
public class BidCcindService {

    private final Logger log = LoggerFactory.getLogger(BidCcindService.class);

    private final BidCcindRepository bidCcindRepository;

    private final BidCcindMapper bidCcindMapper;

    public BidCcindService(BidCcindRepository bidCcindRepository, BidCcindMapper bidCcindMapper) {
        this.bidCcindRepository = bidCcindRepository;
        this.bidCcindMapper = bidCcindMapper;
    }

    /**
     * Get one bidCcind by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidCcindDTO> findOne(BidCcindPrimaryKey id) {
        log.debug("Request to get BidCcind : {}", id);
        return bidCcindRepository.findById(id).map(bidCcindMapper::toDto);
    }

}
