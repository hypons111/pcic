package gov.pcc.pwc.service;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidKpifin;
import gov.pcc.pwc.domain.BidKpifinPrimaryKey;
import gov.pcc.pwc.repository.BidKpifinRepository;
import gov.pcc.pwc.service.criteria.BidKpifinCriteria;
import gov.pcc.pwc.service.dto.BidKpifinDTO;
import gov.pcc.pwc.service.mapper.BidKpifinMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Service Implementation for managing {@link BidKpifin}.
 */
@Service
@Transactional
public class BidKpifinService {

    private final Logger log = LoggerFactory.getLogger(BidKpifinService.class);

    private final BidKpifinRepository bidKpifinRepository;

    private final BidKpifinMapper bidKpifinMapper;

    public BidKpifinService(BidKpifinRepository bidKpifinRepository, BidKpifinMapper bidKpifinMapper) {
        this.bidKpifinRepository = bidKpifinRepository;
        this.bidKpifinMapper = bidKpifinMapper;
    }

    /**
     * Save a bidKpifin.
     *
     * @param bidKpifinDTO the entity to save.
     * @return the persisted entity.
     */
    public BidKpifinDTO save(BidKpifinDTO bidKpifinDTO) {
        log.debug("Request to save BidKpifin : {}", bidKpifinDTO);
        BidKpifin bidKpifin = bidKpifinMapper.toEntity(bidKpifinDTO);
        bidKpifin = bidKpifinRepository.save(bidKpifin);
        return bidKpifinMapper.toDto(bidKpifin);
    }

    /**
     * Partially update a bidKpifin.
     *
     * @param bidKpifinDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidKpifinDTO> partialUpdate(BidKpifinDTO bidKpifinDTO) {
        log.debug("Request to partially update BidKpifin : {}", bidKpifinDTO);

        return bidKpifinRepository
            .findById(bidKpifinDTO.getId())
            .map(existingBidKpifin -> {
                bidKpifinMapper.partialUpdate(existingBidKpifin, bidKpifinDTO);

                return existingBidKpifin;
            })
            .map(bidKpifinRepository::save)
            .map(bidKpifinMapper::toDto);
    }

    /**
     * Get all the bidKpifins.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidKpifinDTO> findAll() {
        log.debug("Request to get all BidKpifins");
        return bidKpifinRepository.findAll().stream().map(bidKpifinMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidKpifin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidKpifinDTO> findOne(BidKpifinPrimaryKey id) {
        log.debug("Request to get BidKpifin : {}", id);
        return bidKpifinRepository.findById(id).map(bidKpifinMapper::toDto);
    }

    /**
     * Delete the bidKpifin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidKpifinPrimaryKey id) {
        log.debug("Request to delete BidKpifin : {}", id);
        bidKpifinRepository.deleteById(id);
    }

    public Page<BidKpifinDTO> findPageByCriteria(BidKpifinCriteria criteria){
        return bidKpifinRepository.findAll(criteria,criteria.toPageable()).map(bidKpifinMapper::toDto);
    }

    public List<BidKpifinDTO> findListByCriteria(BidKpifinCriteria criteria){
        criteria.setSortBy(new String[]{"idate","seq"});
        criteria.setSortDirection(Sort.Direction.DESC);
        return bidKpifinRepository.findAll(criteria,criteria.toPageable().getSort()).stream().map(bidKpifinMapper::toDto).collect(Collectors.toCollection(LinkedList::new)) ;
    }

    public List<BidKpifinDTO> getListByCriteriaExceptSeq(BidKpifinDTO bidKpifinDTO){
        Criteria<BidKpifin> customCriteria = new Criteria<BidKpifin>() {
            @Override
            public Predicate toPredicate(Root<BidKpifin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return  criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("wkut"), bidKpifinDTO.getWkut()),
                        criteriaBuilder.equal(root.get("prjno"), bidKpifinDTO.getPrjno()),
                        criteriaBuilder.equal(root.get("idate"),bidKpifinDTO.getIdate()),
                        criteriaBuilder.equal(root.get("flag"),"N"));
            }

            @Override
            public String getOrderBy() {return null;}
        };
        customCriteria.setSortBy(new String[]{"idate","seq"});
        customCriteria.setSortDirection(Sort.Direction.DESC);
        return bidKpifinRepository.findAll(customCriteria,customCriteria.toPageable().getSort()).stream().map(bidKpifinMapper::toDto).collect(Collectors.toCollection(LinkedList::new)) ;
    }
}
