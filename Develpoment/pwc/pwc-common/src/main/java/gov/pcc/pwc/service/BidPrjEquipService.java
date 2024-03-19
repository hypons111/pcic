package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjEquip;
import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import gov.pcc.pwc.repository.BidPrjEquipRepository;
import gov.pcc.pwc.service.dto.BidPrjEquipDTO;
import gov.pcc.pwc.service.mapper.BidPrjEquipMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjEquip}.
 */
@Service
@Transactional
public class BidPrjEquipService {

    private final Logger log = LoggerFactory.getLogger(BidPrjEquipService.class);

    private final BidPrjEquipRepository bidPrjEquipRepository;

    private final BidPrjEquipMapper bidPrjEquipMapper;

    public BidPrjEquipService(BidPrjEquipRepository bidPrjEquipRepository, BidPrjEquipMapper bidPrjEquipMapper) {
        this.bidPrjEquipRepository = bidPrjEquipRepository;
        this.bidPrjEquipMapper = bidPrjEquipMapper;
    }

    /**
     * Save a bidPrjEquip.
     *
     * @param bidPrjEquipDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjEquipDTO save(BidPrjEquipDTO bidPrjEquipDTO) {
        log.debug("Request to save BidPrjEquip : {}", bidPrjEquipDTO);
        BidPrjEquip bidPrjEquip = bidPrjEquipMapper.toEntity(bidPrjEquipDTO);
        bidPrjEquip = bidPrjEquipRepository.save(bidPrjEquip);
        return bidPrjEquipMapper.toDto(bidPrjEquip);
    }

    /**
     * Partially update a bidPrjEquip.
     *
     * @param bidPrjEquipDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjEquipDTO> partialUpdate(BidPrjEquipDTO bidPrjEquipDTO) {
        log.debug("Request to partially update BidPrjEquip : {}", bidPrjEquipDTO);

        return bidPrjEquipRepository
            .findById(bidPrjEquipDTO.getId())
            .map(existingBidPrjEquip -> {
                bidPrjEquipMapper.partialUpdate(existingBidPrjEquip, bidPrjEquipDTO);

                return existingBidPrjEquip;
            })
            .map(bidPrjEquipRepository::save)
            .map(bidPrjEquipMapper::toDto);
    }

    /**
     * Get all the bidPrjEquips.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjEquipDTO> findAll() {
        log.debug("Request to get all BidPrjEquips");
        return bidPrjEquipRepository.findAll().stream().map(bidPrjEquipMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjEquip by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjEquipDTO> findOne(BidPrjEquipPrimaryKey id) {
        log.debug("Request to get BidPrjEquip : {}", id);
        return bidPrjEquipRepository.findById(id).map(bidPrjEquipMapper::toDto);
    }

    /**
     * Delete the bidPrjEquip by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjEquipPrimaryKey id) {
        log.debug("Request to delete BidPrjEquip : {}", id);
        bidPrjEquipRepository.deleteById(id);
    }


    public List<BidPrjEquipDTO> findPrjMonthAllEquip(String wkut, String prjno, String year, String month){
        return bidPrjEquipRepository.findPrjMonthAllEquip(wkut,prjno,year,month);
    }
}
