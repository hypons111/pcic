package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmFunctionUsedRecord;
import gov.pcc.pwc.repository.AdmFunctionUsedRecordRepository;
import gov.pcc.pwc.service.criteria.AdmFunctionUsedRecordCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import gov.pcc.pwc.service.mapper.AdmFunctionUsedRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AdmFunctionUsedRecordService {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionUsedRecordService.class);

    private final AdmFunctionUsedRecordRepository admFunctionUsedRecordRepository;

    private final AdmFunctionUsedRecordMapper admFunctionUsedRecordMapper;

    public AdmFunctionUsedRecordService(AdmFunctionUsedRecordRepository admFunctionUsedRecordRepository, AdmFunctionUsedRecordMapper admFunctionUsedRecordMapper) {
        this.admFunctionUsedRecordRepository = admFunctionUsedRecordRepository;
        this.admFunctionUsedRecordMapper = admFunctionUsedRecordMapper;
    }


    /**
     * Find the list of AdmFunctionUsedRecordDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmFunctionUsedRecordDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmFunctionUsedRecordDTO> findByCriteria(AdmFunctionUsedRecordCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "createTime" });
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final Page<AdmFunctionUsedRecordDTO> admFunctionUsedRecordDTOs = admFunctionUsedRecordRepository.findAllByCriteria(criteria);
        return admFunctionUsedRecordDTOs;
    }

    /**
     * Find the list of AdmFunctionUsedRecordDTO statistics by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmFunctionUsedRecordDTO statistics.
     */
    @Transactional(readOnly = true)
    public Page<AdmFunctionUsedRecordDTO> findStatisticsByCriteria(AdmFunctionUsedRecordCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "functionId" });
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final Page<AdmFunctionUsedRecordDTO> admFunctionUsedRecordDTOs = admFunctionUsedRecordRepository.findAllStatisticsByCriteria(criteria);
        return admFunctionUsedRecordDTOs;
    }

    /**
     * Find the list of AdmFunctionUsedRecordDTO statistics info by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmFunctionUsedRecordDTO statistics info.
     */
    @Transactional(readOnly = true)
    public List<AdmFunctionUsedRecordDTO> findStatisticsInfoByCriteria(AdmFunctionUsedRecordCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "functionExtendId" });
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final List<AdmFunctionUsedRecordDTO> admFunctionUsedRecordDTOs = admFunctionUsedRecordRepository.findAllStatisticsInfoByCriteria(criteria);
        return admFunctionUsedRecordDTOs;
    }

    @Transactional(readOnly = true)
    public AdmFunctionUsedRecordDTO findByPk(Long id) {
        return admFunctionUsedRecordRepository.findByPk(id);
    }

    @Transactional(readOnly = true)
    public AdmFunctionUsedRecordDTO save(AdmFunctionUsedRecordDTO admFunctionUsedRecordDTO) {
        Optional<AdmFunctionUsedRecord> pre_admFunctionUsedRecord =
                admFunctionUsedRecordRepository.findTopByTableNameAndTableKeyOrderByCreateTimeDesc(admFunctionUsedRecordDTO.getTableName(), admFunctionUsedRecordDTO.getTableKey());
        if(pre_admFunctionUsedRecord.isPresent() && !Objects.equals(pre_admFunctionUsedRecord.get().getUpdateValue(), admFunctionUsedRecordDTO.getUpdateValue())) {
            admFunctionUsedRecordDTO.setFunctionExtendId("");
            admFunctionUsedRecordDTO.setJwtToken("Fake Token");
            admFunctionUsedRecordDTO.setCreateTime(Instant.now());
            admFunctionUsedRecordDTO.setCreateUserSys("Fake User");

            AdmFunctionUsedRecord admFunctionUsedRecord = admFunctionUsedRecordMapper.toEntity(admFunctionUsedRecordDTO);
            admFunctionUsedRecord = admFunctionUsedRecordRepository.save(admFunctionUsedRecord);
            return admFunctionUsedRecordMapper.toDto(admFunctionUsedRecord);
        }
        return null;
    }

}
