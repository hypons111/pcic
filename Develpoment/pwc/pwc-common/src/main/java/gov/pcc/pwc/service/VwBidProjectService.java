package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.domain.VwBidProject;
import gov.pcc.pwc.repository.VwBidProjectRepository;
import gov.pcc.pwc.service.criteria.CmsCompCriteria;
import gov.pcc.pwc.service.criteria.VwBidProjectCriteria;
import gov.pcc.pwc.service.dto.CmsCompDTO;
import gov.pcc.pwc.service.dto.VwBidProjectDTO;
import gov.pcc.pwc.service.dto.VwBidProjectWkutDTO;
import gov.pcc.pwc.service.mapper.VwBidProjectMapper;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
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
 * Service Implementation for managing {@link VwBidProject}.
 */
@Service
@Transactional
public class VwBidProjectService {

    private final Logger log = LoggerFactory.getLogger(VwBidProjectService.class);

    private final VwBidProjectRepository vwBidProjectRepository;

    private final VwBidProjectMapper vwBidProjectMapper;

    public VwBidProjectService(VwBidProjectRepository vwBidProjectRepository, VwBidProjectMapper vwBidProjectMapper) {
        this.vwBidProjectRepository = vwBidProjectRepository;
        this.vwBidProjectMapper = vwBidProjectMapper;
    }


    /**
     * Get all the vwBidProjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<VwBidProjectDTO> findAll() {
        log.debug("Request to get all VwBidProjects");
        return vwBidProjectRepository.findAll().stream().map(vwBidProjectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one vwBidProject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VwBidProjectDTO> findOne(BidProjectPrimaryKey id) {
        log.debug("Request to get VwBidProject : {}", id);
        return vwBidProjectRepository.findById(id).map(vwBidProjectMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<VwBidProjectDTO> findByCriteria(VwBidProjectCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "prjno" });
        }
        log.debug("criteria.toPageable()", criteria.toString());
        final Page<VwBidProject> VwBidProjects = vwBidProjectRepository.findAll(criteria, criteria.toPageable());
        return VwBidProjects.map(vwBidProjectMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<VwBidProjectWkutDTO> findWkutNameAndCodeByAdd92(String add92) {
        LocalDate specificLocaldate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 16);
        final Page<VwBidProjectWkutDTO> VwBidProjects = vwBidProjectRepository.findWkutNameAndCodeByAdd92(add92,specificLocaldate, PageRequest.of(0, 100));
        return VwBidProjects.getContent();
    }

    public Page<VwBidProjectDTO> findProjectByDesingOrSupervisionUnitName(VwBidProjectCriteria criteria){
        return vwBidProjectRepository.findProjectByDesingOrSupervisionUnitName(criteria);
    }

}
