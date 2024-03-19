package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidPrjScequip;
import gov.pcc.pwc.domain.BidPrjScequipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjSubco;
import gov.pcc.pwc.repository.BidPrjScequipRepository;
import gov.pcc.pwc.service.dto.BidPrjScequipDTO;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import gov.pcc.pwc.service.mapper.BidPrjScequipMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjScequip}.
 */
@Service
@Transactional
public class BidPrjScequipService {

    private final Logger log = LoggerFactory.getLogger(BidPrjScequipService.class);

    private final BidPrjScequipRepository bidPrjScequipRepository;

    private final BidPrjScequipMapper bidPrjScequipMapper;

    public BidPrjScequipService(BidPrjScequipRepository bidPrjScequipRepository, BidPrjScequipMapper bidPrjScequipMapper) {
        this.bidPrjScequipRepository = bidPrjScequipRepository;
        this.bidPrjScequipMapper = bidPrjScequipMapper;
    }

    /**
     * Save a bidPrjScequip.
     *
     * @param bidPrjScequipDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjScequipDTO save(BidPrjScequipDTO bidPrjScequipDTO) {
        log.debug("Request to save BidPrjScequip : {}", bidPrjScequipDTO);
        BidPrjScequip bidPrjScequip = bidPrjScequipMapper.toEntity(bidPrjScequipDTO);
        bidPrjScequip = bidPrjScequipRepository.save(bidPrjScequip);
        return bidPrjScequipMapper.toDto(bidPrjScequip);
    }

    /**
     * Partially update a bidPrjScequip.
     *
     * @param bidPrjScequipDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjScequipDTO> partialUpdate(BidPrjScequipDTO bidPrjScequipDTO) {
        log.debug("Request to partially update BidPrjScequip : {}", bidPrjScequipDTO);

        return bidPrjScequipRepository
            .findById(bidPrjScequipDTO.getId())
            .map(existingBidPrjScequip -> {
                bidPrjScequipMapper.partialUpdate(existingBidPrjScequip, bidPrjScequipDTO);

                return existingBidPrjScequip;
            })
            .map(bidPrjScequipRepository::save)
            .map(bidPrjScequipMapper::toDto);
    }

    /**
     * Get all the bidPrjScequips.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjScequipDTO> findAll() {
        log.debug("Request to get all BidPrjScequips");
        return bidPrjScequipRepository.findAll().stream().map(bidPrjScequipMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjScequip by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjScequipDTO> findOne(BidPrjScequipPrimaryKey id) {
        log.debug("Request to get BidPrjScequip : {}", id);
        return bidPrjScequipRepository.findById(id).map(bidPrjScequipMapper::toDto);
    }

    /**
     * Delete the bidPrjScequip by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjScequipPrimaryKey id) {
        log.debug("Request to delete BidPrjScequip : {}", id);
        bidPrjScequipRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    public List<BidPrjScequip> findByWkutAndPrjnoAndSubCcut(String wkut, String prjno, String subCcut) {
        log.debug("Request to get wkut : {}", wkut);
        log.debug("Request to get prjno : {}", prjno);
        log.debug("Request to get subCcut : {}", subCcut);
        return bidPrjScequipRepository.findByWkutAndPrjnoAndSubCcut(wkut,prjno,subCcut);
    }


    public BidPrjScequipDTO saveScequip(BidPrjScequipDTO bidPrjScequipDTO) {
        log.debug("Request to save BidPrjScequip : {}", bidPrjScequipDTO);
        BidPrjScequip bidPrjScequip = bidPrjScequipMapper.toEntity(bidPrjScequipDTO);
        bidPrjScequip = bidPrjScequipRepository.save(bidPrjScequip);
        return bidPrjScequipMapper.toDto(bidPrjScequip);
    }

    public void deleteScequips(String wkut, String prjno, String subCcut) {
        log.debug("Request to delete wkut : {}", wkut);
        log.debug("Request to delete prjno : {}", prjno);
        log.debug("Request to delete subCcut : {}", subCcut);

        bidPrjScequipRepository.deleteByWkutAndPrjnoAndSubCcut(wkut,prjno,subCcut);
    }


}
