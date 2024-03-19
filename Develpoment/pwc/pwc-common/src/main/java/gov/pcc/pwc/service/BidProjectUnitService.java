package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidProjectUnit;
import gov.pcc.pwc.domain.BidProjectUnitPrimaryKey;
import gov.pcc.pwc.repository.BidProjectUnitRepository;
import gov.pcc.pwc.service.criteria.BidProjectUnitCriteria;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.dto.BidProjectUnitDTO;
import gov.pcc.pwc.service.mapper.BidProjectUnitMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidProjectUnit}.
 */
@Service
@Transactional
public class BidProjectUnitService {

  private final Logger log = LoggerFactory.getLogger(
    BidProjectUnitService.class
  );

  private final BidProjectUnitRepository bidProjectUnitRepository;

  private final BidProjectUnitMapper bidProjectUnitMapper;

  public BidProjectUnitService(
    BidProjectUnitRepository bidProjectUnitRepository,
    BidProjectUnitMapper bidProjectUnitMapper
  ) {
    this.bidProjectUnitRepository = bidProjectUnitRepository;
    this.bidProjectUnitMapper = bidProjectUnitMapper;
  }

  /**
   * Save a bidProjectUnit.
   *
   * @param bidProjectUnitDTO the entity to save.
   * @return the persisted entity.
   */
  public BidProjectUnitDTO save(BidProjectUnitDTO bidProjectUnitDTO) {
    log.debug("Request to save BidProjectUnit : {}", bidProjectUnitDTO);
    BidProjectUnit bidProjectUnit = bidProjectUnitMapper.toEntity(
      bidProjectUnitDTO
    );
    bidProjectUnit = bidProjectUnitRepository.save(bidProjectUnit);
    return bidProjectUnitMapper.toDto(bidProjectUnit);
  }

  /**
   * Partially update a bidProjectUnit.
   *
   * @param bidProjectUnitDTO the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<BidProjectUnitDTO> partialUpdate(
    BidProjectUnitDTO bidProjectUnitDTO
  ) {
    log.debug(
      "Request to partially update BidProjectUnit : {}",
      bidProjectUnitDTO
    );

    return bidProjectUnitRepository
      .findById(bidProjectUnitDTO.getId())
      .map(existingBidProjectUnit -> {
        bidProjectUnitMapper.partialUpdate(
          existingBidProjectUnit,
          bidProjectUnitDTO
        );

        return existingBidProjectUnit;
      })
      .map(bidProjectUnitRepository::save)
      .map(bidProjectUnitMapper::toDto);
  }

  /**
   * Get all the bidProjectUnits.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BidProjectUnitDTO> findAll() {
    log.debug("Request to get all BidProjectUnits");
    return bidProjectUnitRepository
      .findAll()
      .stream()
      .map(bidProjectUnitMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  public int findMaxSeq(String wkut, String prjno, String type) {
    return bidProjectUnitRepository.findMaxSeq(wkut, prjno, type);
  }

  public void saveDsutUnit(BidProjectDTO bidProjectDTO) {
    log.debug("Request to save BidProjectUnits : {}", bidProjectDTO);
    for (BidProjectUnitDTO oldDsut : this.findUnitsByWkutAndPrjnoAndType(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "1")) {
      boolean isDelete = true;
      for (BidProjectUnitDTO dsut : bidProjectDTO.getDsuts()) {
        if (oldDsut.getId().equals(dsut.getId())) {
          isDelete = false;
        }
      }
      if (isDelete) {
        this.delete(oldDsut.getId());
      }
    }
    int dsutMaxSeq = this.findMaxSeq(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "1");
    for (BidProjectUnitDTO dsut : bidProjectDTO.getDsuts()) {
      if (StringUtils.isBlank(dsut.getSeq())) {
        if (StringUtils.isNotBlank(dsut.getDsut())) { // 單位有輸入才新增
          dsutMaxSeq++;
          dsut.setSeq(String.valueOf(dsutMaxSeq));
          dsut.setCreateDate(bidProjectDTO.getUpdateDate());
          dsut.setCreateUser(bidProjectDTO.getUpdateUser());
          this.save(dsut);
        }
      } else {
        if (StringUtils.isBlank(dsut.getDsut())) { // 單位清空刪除
          this.delete(dsut.getId());
        } else {
          dsut.setUpdateDate(bidProjectDTO.getUpdateDate());
          dsut.setUpdateUser(bidProjectDTO.getUpdateUser());
          this.partialUpdate(dsut);
        }
      }
    }
  }

  public void saveScutUnit(BidProjectDTO bidProjectDTO) {
    log.debug("Request to save BidProjectUnits : {}", bidProjectDTO);
    for (BidProjectUnitDTO oldScut : this.findUnitsByWkutAndPrjnoAndType(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "2")) {
      boolean isDelete = true;
      for (BidProjectUnitDTO scut : bidProjectDTO.getScuts()) {
        if (oldScut.getId().equals(scut.getId())) {
          isDelete = false;
        }
      }
      if (isDelete) {
        this.delete(oldScut.getId());
      }
    }
    int scutMaxSeq = this.findMaxSeq(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "2");
    for (BidProjectUnitDTO scut : bidProjectDTO.getScuts()) {
      if (StringUtils.isBlank(scut.getSeq())) {
        if (StringUtils.isNotBlank(scut.getScut())) { // 單位有輸入才新增
          scutMaxSeq++;
          scut.setSeq(String.valueOf(scutMaxSeq));
          scut.setCreateDate(bidProjectDTO.getUpdateDate());
          scut.setCreateUser(bidProjectDTO.getUpdateUser());
          this.save(scut);
        }
      } else {
        if (StringUtils.isBlank(scut.getScut())) { // 單位清空刪除
          this.delete(scut.getId());
        } else {
          scut.setUpdateDate(bidProjectDTO.getUpdateDate());
          scut.setUpdateUser(bidProjectDTO.getUpdateUser());
          this.partialUpdate(scut);
        }
      }
    }
  }

  public void saveCcutUnit(BidProjectDTO bidProjectDTO) {
    log.debug("Request to save BidProjectUnits : {}", bidProjectDTO);
    for (BidProjectUnitDTO oldCcut : this.findUnitsByWkutAndPrjnoAndType(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "3")) {
      boolean isDelete = true;
      for (BidProjectUnitDTO ccut : bidProjectDTO.getCcuts()) {
        if (oldCcut.getId().equals(ccut.getId())) {
          isDelete = false;
        }
      }
      if (isDelete) {
        this.delete(oldCcut.getId());
      }
    }
    int ccutMaxSeq = this.findMaxSeq(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "3");
    for (BidProjectUnitDTO ccut : bidProjectDTO.getCcuts()) {
      if (StringUtils.isBlank(ccut.getSeq())) {
        if (StringUtils.isNotBlank(ccut.getCcut())) { // 單位有輸入才新增
          ccutMaxSeq++;
          ccut.setSeq(String.valueOf(ccutMaxSeq));
          ccut.setCreateDate(bidProjectDTO.getUpdateDate());
          ccut.setCreateUser(bidProjectDTO.getUpdateUser());
          this.save(ccut);
        }
      } else {
        if (StringUtils.isBlank(ccut.getCcut())) { // 單位清空刪除
          this.delete(ccut.getId());
        } else {
          ccut.setUpdateDate(bidProjectDTO.getUpdateDate());
          ccut.setUpdateUser(bidProjectDTO.getUpdateUser());
          this.partialUpdate(ccut);
        }
      }
    }
  }

  @Transactional(readOnly = true)
  public List<BidProjectUnitDTO> findUnitsByWkutAndPrjnoAndType(String wkut, String prjno, String type) {
    log.debug("Request to get BidProjectUnit by project : {}, {} ", wkut, prjno);
    return bidProjectUnitRepository.findByWkutAndPrjnoAndType(wkut, prjno, type)
            .stream().map(bidProjectUnitMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one bidProjectUnit by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<BidProjectUnitDTO> findOne(BidProjectUnitPrimaryKey id) {
    log.debug("Request to get BidProjectUnit : {}", id);
    return bidProjectUnitRepository
      .findById(id)
      .map(bidProjectUnitMapper::toDto);
  }

  /**
   * Delete the bidProjectUnit by id.
   *
   * @param id the id of the entity.
   */
  public void delete(BidProjectUnitPrimaryKey id) {
    log.debug("Request to delete BidProjectUnit : {}", id);
    bidProjectUnitRepository.deleteById(id);
  }

  /**
   *  find ccut names by wkut,prjno
   *
   * @param wkut
   * @param prjno
   * */
  public List<BidProjectUnitDTO> findBidCcutByKeyWorld(
    String wkut,
    String prjno
  ) {
    return bidProjectUnitRepository.findBidCcutNames(wkut, prjno);
  }

  /**
   *  find ccnt names by wkut,prjno
   *
   * @param wkut
   * @param prjno
   * */
  public List<BidProjectUnitDTO> findBidCcntByKeyWorld(
    String wkut,
    String prjno
  ) {
    return bidProjectUnitRepository.findBidCcntNames(wkut, prjno);
  }
  /**
   *
   * @param criteria query criteria(wkut and prjno)
   * @return Page< BidProjectUnit>
   */
  @Transactional(readOnly = true)
  public Page<BidProjectUnitDTO> getBidProjectUnitDTOPageByCriteria(BidProjectUnitCriteria criteria) {
    return bidProjectUnitRepository.findAll(criteria,criteria.toPageable()).map(bidProjectUnitMapper::toDto);
  }
}
