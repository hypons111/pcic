package gov.pcc.pwc.service;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjPrice;
import gov.pcc.pwc.domain.BidPrjPricePrimaryKey;
import gov.pcc.pwc.repository.BidPrjPriceRepository;
import gov.pcc.pwc.service.criteria.BidPrjPriceCriteria;
import gov.pcc.pwc.service.dto.BidPrjPriceDTO;
import gov.pcc.pwc.service.mapper.BidPrjPriceMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Service Implementation for managing {@link BidPrjPrice}.
 */
@Service
@Transactional
public class BidPrjPriceService {

    private final Logger log = LoggerFactory.getLogger(BidPrjPriceService.class);

    private final BidPrjPriceRepository bidPrjPriceRepository;

    private final BidPrjPriceMapper bidPrjPriceMapper;

    public BidPrjPriceService(BidPrjPriceRepository bidPrjPriceRepository, BidPrjPriceMapper bidPrjPriceMapper) {
        this.bidPrjPriceRepository = bidPrjPriceRepository;
        this.bidPrjPriceMapper = bidPrjPriceMapper;
    }

    /**
     * Save a bidPrjPrice.
     *
     * @param bidPrjPriceDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjPriceDTO save(BidPrjPriceDTO bidPrjPriceDTO) {
        log.debug("Request to save BidPrjPrice : {}", bidPrjPriceDTO);
        BidPrjPrice bidPrjPrice = bidPrjPriceMapper.toEntity(bidPrjPriceDTO);
        bidPrjPrice = bidPrjPriceRepository.save(bidPrjPrice);
        return bidPrjPriceMapper.toDto(bidPrjPrice);
    }

    /**
     * Partially update a bidPrjPrice.
     *
     * @param bidPrjPriceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjPriceDTO> partialUpdate(BidPrjPriceDTO bidPrjPriceDTO) {
        log.debug("Request to partially update BidPrjPrice : {}", bidPrjPriceDTO);

        return bidPrjPriceRepository
            .findById(bidPrjPriceDTO.getId())
            .map(existingBidPrjPrice -> {
                bidPrjPriceMapper.partialUpdate(existingBidPrjPrice, bidPrjPriceDTO);

                return existingBidPrjPrice;
            })
            .map(bidPrjPriceRepository::save)
            .map(bidPrjPriceMapper::toDto);
    }

    /**
     * Get all the bidPrjPrices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjPriceDTO> findAll() {
        log.debug("Request to get all BidPrjPrices");
        return bidPrjPriceRepository.findAll().stream().map(bidPrjPriceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjPrice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjPriceDTO> findOne(BidPrjPricePrimaryKey id) {
        log.debug("Request to get BidPrjPrice : {}", id);
        return bidPrjPriceRepository.findById(id).map(bidPrjPriceMapper::toDto);
    }

    /**
     * Delete the bidPrjPrice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjPricePrimaryKey id) {
        log.debug("Request to delete BidPrjPrice : {}", id);
        bidPrjPriceRepository.deleteById(id);
    }

    /**
     * 查金質獎，或
     * @param criteria query criteria(wkut and prjno)
     * @return Page<BidPrjPriceDTO>
     */
    @Transactional(readOnly = true)
    public List<BidPrjPriceDTO> getBidPrjPriceDTOListByCriteria(BidPrjPriceCriteria criteria) {
        criteria.setSortBy(new String[]{"year"});
        criteria.setSortDirection(Sort.Direction.DESC);
        return bidPrjPriceRepository.findAll(criteria, criteria.toPageable().getSort()).stream().map(bidPrjPriceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<BidPrjPriceDTO> getCertainBidPrjPriceDTOListByCriteria(BidPrjPriceCriteria criteria,String priceTypeName) {
        criteria.setSortBy(new String[]{"year"});
        criteria.setSortDirection(Sort.Direction.DESC);
        Criteria<BidPrjPrice> additionalCriteria = new Criteria<BidPrjPrice>() {

            @Override
            public Predicate toPredicate(Root<BidPrjPrice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("wkut"), criteria.getWkut()),
                        criteriaBuilder.equal(root.get("prjno"), criteria.getPrjno()),
                        criteriaBuilder.equal(root.get("pType"), priceTypeName));
            }
            @Override
            public String getOrderBy() {
                return null;
            }
        };

        return bidPrjPriceRepository.findAll(criteria, criteria.toPageable().getSort()).stream().map(bidPrjPriceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
