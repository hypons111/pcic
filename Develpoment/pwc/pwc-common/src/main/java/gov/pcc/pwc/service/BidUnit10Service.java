package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidUnit10;
import gov.pcc.pwc.repository.BidUnit10Repository;
import gov.pcc.pwc.service.criteria.BidUnit10Criteria;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO;
import gov.pcc.pwc.service.mapper.BidUnit10Mapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidUnit10}.
 */
@Service
@Transactional
public class BidUnit10Service {

  private final Logger log = LoggerFactory.getLogger(BidUnit10Service.class);

  private final BidUnit10Repository bidUnit10Repository;

  private final BidUnit10Mapper bidUnit10Mapper;

  public BidUnit10Service(
    BidUnit10Repository bidUnit10Repository,
    BidUnit10Mapper bidUnit10Mapper
  ) {
    this.bidUnit10Repository = bidUnit10Repository;
    this.bidUnit10Mapper = bidUnit10Mapper;
  }

  /**
   * Save a bidUnit10.
   *
   * @param bidUnit10DTO the entity to save.
   * @return the persisted entity.
   */
  public BidUnit10DTO save(BidUnit10DTO bidUnit10DTO) {
    log.debug("Request to save BidUnit10 : {}", bidUnit10DTO);
    BidUnit10 bidUnit10 = bidUnit10Mapper.toEntity(bidUnit10DTO);
    bidUnit10 = bidUnit10Repository.save(bidUnit10);
    return bidUnit10Mapper.toDto(bidUnit10);
  }

  /**
   * Partially update a bidUnit10.
   *
   * @param bidUnit10DTO the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<BidUnit10DTO> partialUpdate(BidUnit10DTO bidUnit10DTO) {
    log.debug("Request to partially update BidUnit10 : {}", bidUnit10DTO);

    return bidUnit10Repository
      .findById(bidUnit10DTO.getCode())
      .map(existingBidUnit10 -> {
        bidUnit10Mapper.partialUpdate(existingBidUnit10, bidUnit10DTO);

        return existingBidUnit10;
      })
      .map(bidUnit10Repository::save)
      .map(bidUnit10Mapper::toDto);
  }

  /**
   * Get all the bidUnit10s.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BidUnit10DTO> findAll() {
    log.debug("Request to get all BidUnit10s");
    return bidUnit10Repository
      .findAll()
      .stream()
      .map(bidUnit10Mapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one bidUnit10 by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<BidUnit10DTO> findOne(String id) {
    log.debug("Request to get BidUnit10 : {}", id);
    return bidUnit10Repository.findById(id).map(bidUnit10Mapper::toDto);
  }

  /**
   * Delete the bidUnit10 by id.
   *
   * @param id the id of the entity.
   */
  public void delete(String id) {
    log.debug("Request to delete BidUnit10 : {}", id);
    bidUnit10Repository.deleteById(id);
  }

  /**
   * Get all the bidUnit10s.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BidUnit10NameAndCodeDTO> findNameAndCodeByKeyWord(
    String keyWord
  ) {
    log.debug("Request to get all BidUnit10s");
    Page<BidUnit10NameAndCodeDTO> resWithPage = bidUnit10Repository.findNameAndCodeByKeyWord(
      keyWord,
      PageRequest.of(0, 25)
    );
    return resWithPage.getContent();
  }

  @Transactional(readOnly = true)
  public List<BidUnit10NameAndCodeDTO> findAllWithNameAndCode() {
    log.debug("Request to get all BidUnit10s");
    Page<BidUnit10NameAndCodeDTO> resWithPage = bidUnit10Repository.findAllWithNameAndCode(
      PageRequest.of(0, 100)
    );
    return resWithPage.getContent();
  }

  public String getBidUnit10NameByCode(String code) {
    if (bidUnit10Repository.findById(code).isPresent()) {
      return bidUnit10Repository.findById(code).get().getName();
    } else {
      return "";
    }
  }

  /**
   * Get name of the BID_PRJ_SAFE.respons
   *
   * @return the list of entities.
   */

  public List<BidUnit10DTO> findResponsName(String code) {
    log.debug("Request to get ResponsName");
    return bidUnit10Repository.findBidPrjResponsName(code);
  }

  public List<BidUnit10DTO> findBidManageUnit(String wkutCode) {
    log.debug("Request to get ManageUnit");
    return bidUnit10Repository.findBidManageUnit(wkutCode);
  }

  @Transactional(readOnly = true)
  public Optional<BidUnit10DTO> findByCode(String code) {
    log.debug("Request to get BidUnit10DTO, docType : {}, docId : {}", code);
    return bidUnit10Repository.findByCode(code).map(bidUnit10Mapper::toDto);
  }

  @Transactional(readOnly = true)
  public Page<BidUnit10DTO> findByCriteria(BidUnit10Criteria criteria) {
    if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
      criteria.setSortBy(new String[] { "code" });
    }
    final Page<BidUnit10> bidUnit10s = bidUnit10Repository.findAll(criteria, criteria.toPageable());
    return bidUnit10s.map(bidUnit10Mapper::toDto);
  }

  @Transactional(readOnly = true)
  public Page<BidUnit10DTO> findAllByCriteria(BidUnit10Criteria criteria) {
    log.debug("Request to get BidUnit10 by criteria : {} ", criteria.toString());
    if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
      criteria.setSortBy(new String[] { "code" });
    }
    return bidUnit10Repository.findAllByCriteria(criteria);
  }
}
