package gov.pcc.pwc.service;


import gov.pcc.pwc.repository.Geo0601Repository;

import gov.pcc.pwc.service.dto.Geo0601DTO;
import gov.pcc.pwc.service.mapper.GeoJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing {@link }.
 */
@Service
@Transactional
public class Geo0601Service {

    private final Logger log = LoggerFactory.getLogger(Geo0601Service.class);

    private final Geo0601Repository geo0601Repository;

    public Geo0601Service(Geo0601Repository geo0601Repository) {
        this.geo0601Repository = geo0601Repository;
    }

    @Transactional(readOnly = true)
    public Geo0601DTO getAllCount() {
        log.debug("Request to get a Geo0601DTO");
        Geo0601DTO geo0601DTO = new Geo0601DTO();
        geo0601DTO.setMemberCount(geo0601Repository.countMemberCount());
        geo0601DTO.setCompanyCount(geo0601Repository.countCompanyCount());
        geo0601DTO.setJobCount(geo0601Repository.countJobCount());
        geo0601DTO.setQaReview(geo0601Repository.countQaReview());
        geo0601DTO.setCompanyReview(geo0601Repository.countCompanyReview());
        geo0601DTO.setJobReview(geo0601Repository.countjobReview());
        return geo0601DTO;
    }

}
