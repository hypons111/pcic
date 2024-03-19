package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmHoliday;
import gov.pcc.pwc.repository.AdmHolidayRepository;
import gov.pcc.pwc.service.criteria.AdmHolidayCriteria;
import gov.pcc.pwc.service.dto.AdmHolidayDTO;
import gov.pcc.pwc.service.mapper.AdmHolidayMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class AdmHolidayService {

    private final Logger log = LoggerFactory.getLogger(AdmHolidayService.class);

    private final AdmHolidayRepository admHolidayRepository;

    private final AdmHolidayMapper admHolidayMapper;

    public AdmHolidayService(AdmHolidayRepository admHolidayRepository, AdmHolidayMapper admHolidayMapper) {
        this.admHolidayRepository = admHolidayRepository;
        this.admHolidayMapper = admHolidayMapper;
    }

    /**
     * Create the AdmHolidayDTO.
     *
     * @param admHolidayDTO the AdmHolidayDTO.
     * @return updated AdmHolidayDTO.
     */
    @Transactional(readOnly = true)
    public AdmHolidayDTO save(AdmHolidayDTO admHolidayDTO) {
        AdmHoliday admHoliday = admHolidayMapper.toEntity(admHolidayDTO);
        AdmHoliday result = admHolidayRepository.save(admHoliday);
        return admHolidayMapper.toDto(result);
    }


    /**
     * Find the list of AdmHolidayDTOs by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmHolidayDTOs.
     */
    @Transactional(readOnly = true)
    public Page<AdmHolidayDTO> findByCriteria(AdmHolidayCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "holidayDate" });
        }
        if (criteria.getHolidayDate() == null || criteria.getHolidayDate().equals("")) {
            criteria.setHolidayDate(Instant.ofEpochSecond(0));
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final Page<AdmHolidayDTO> admHolidayDTOs = admHolidayRepository.findAllByCriteria(criteria);
        return admHolidayDTOs;
    }

    /**
     * Update the AdmHolidayDTO.
     *
     * @param admHolidayDTO the AdmHolidayDTO.
     * @return updated AdmHolidayDTO.
     */
    @Transactional(readOnly = true)
    public AdmHolidayDTO update(AdmHolidayDTO admHolidayDTO) {
        AdmHoliday admHoliday = admHolidayMapper.toEntity(admHolidayDTO);
        AdmHoliday result = admHolidayRepository.save(admHoliday);
        return admHolidayMapper.toDto(result);
    }

}
