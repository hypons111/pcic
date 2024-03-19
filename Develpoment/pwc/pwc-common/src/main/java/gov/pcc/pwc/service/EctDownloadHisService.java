package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EctDownloadHis;
import gov.pcc.pwc.repository.EctDownloadHisRepository;
import gov.pcc.pwc.service.criteria.ECT0101R2Criteria;
import gov.pcc.pwc.service.dto.EctDownloadHisDTO;
import gov.pcc.pwc.service.mapper.EctDownloadHisMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * Service Implementation for managing {@link EctDownloadHis}.
 */
@Service
@Transactional
public class EctDownloadHisService {

    private final Logger log = LoggerFactory.getLogger(EctDownloadHisService.class);

    private final EctDownloadHisRepository ectDownloadHisRepository;

    private final EctDownloadHisMapper ectDownloadHisMapper;

    public EctDownloadHisService(EctDownloadHisRepository ectDownloadHisRepository, EctDownloadHisMapper ectDownloadHisMapper) {
        this.ectDownloadHisRepository = ectDownloadHisRepository;
        this.ectDownloadHisMapper = ectDownloadHisMapper;
    }

    public void doDownloadRecord(@NotNull ECT0101R2Criteria criteria, HttpServletRequest request) {
        ectDownloadHisRepository.doDownloadRecord(criteria, request);
        log.debug("service-doDownloadRecord");
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getECT0301(@NotNull ECT0101R2Criteria criteria) {
        final Map<String, Object> rs = ectDownloadHisRepository.getECT0301(criteria);
        log.debug("service-ect0301" + rs.toString());
        return rs;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getDownloadFile(MultipartHttpServletRequest request) {
        final Map<String, Object> rs = ectDownloadHisRepository.getDownloadFile(request);
        log.debug("service-getDownloadFile" + rs.toString());
        return rs;
    }

    /**
     * Save a ectDownloadHis.
     *
     * @param ectDownloadHisDTO the entity to save.
     * @return the persisted entity.
     */
    public EctDownloadHisDTO save(EctDownloadHisDTO ectDownloadHisDTO) {
        log.debug("Request to save EctDownloadHis : {}", ectDownloadHisDTO);
        EctDownloadHis ectDownloadHis = ectDownloadHisMapper.toEntity(ectDownloadHisDTO);
        ectDownloadHis = ectDownloadHisRepository.save(ectDownloadHis);
        return ectDownloadHisMapper.toDto(ectDownloadHis);
    }

    /**
     * Partially update a ectDownloadHis.
     *
     * @param ectDownloadHisDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EctDownloadHisDTO> partialUpdate(EctDownloadHisDTO ectDownloadHisDTO) {
        log.debug("Request to partially update EctDownloadHis : {}", ectDownloadHisDTO);

        return ectDownloadHisRepository
            .findById(ectDownloadHisDTO.getEctDownloadHisId())
            .map(existingEctDownloadHis -> {
                ectDownloadHisMapper.partialUpdate(existingEctDownloadHis, ectDownloadHisDTO);

                return existingEctDownloadHis;
            })
            .map(ectDownloadHisRepository::save)
            .map(ectDownloadHisMapper::toDto);
    }

    /**
     * Get all the ectDownloadHis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EctDownloadHisDTO> findAll() {
        log.debug("Request to get all EctDownloadHis");
        return ectDownloadHisRepository
            .findAll()
            .stream()
            .map(ectDownloadHisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ectDownloadHis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EctDownloadHisDTO> findOne(Long id) {
        log.debug("Request to get EctDownloadHis : {}", id);
        return ectDownloadHisRepository.findById(id).map(ectDownloadHisMapper::toDto);
    }

    /**
     * Delete the ectDownloadHis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EctDownloadHis : {}", id);
        ectDownloadHisRepository.deleteById(id);
    }
}
