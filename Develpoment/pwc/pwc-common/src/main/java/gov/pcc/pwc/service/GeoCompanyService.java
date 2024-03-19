package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.GeoCompany;
import gov.pcc.pwc.repository.GeoCompanyRepository;
import gov.pcc.pwc.service.dto.GeoCompanyDTO;
import gov.pcc.pwc.service.mapper.GeoCompanyMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GeoCompany}.
 */
@Service
@Transactional
public class GeoCompanyService {

    private final Logger log = LoggerFactory.getLogger(GeoCompanyService.class);

    private final GeoCompanyRepository geoCompanyRepository;

    private final GeoCompanyMapper geoCompanyMapper;

    public GeoCompanyService(GeoCompanyRepository geoCompanyRepository, GeoCompanyMapper geoCompanyMapper) {
        this.geoCompanyRepository = geoCompanyRepository;
        this.geoCompanyMapper = geoCompanyMapper;
    }

    /**
     * Save a geoCompany.
     *
     * @param geoCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoCompanyDTO save(GeoCompanyDTO geoCompanyDTO) {
        log.debug("Request to save GeoCompany : {}", geoCompanyDTO);
        GeoCompany geoCompany = geoCompanyMapper.toEntity(geoCompanyDTO);
        geoCompany = geoCompanyRepository.save(geoCompany);
        return geoCompanyMapper.toDto(geoCompany);
    }

    /**
     * Partially update a geoCompany.
     *
     * @param geoCompanyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GeoCompanyDTO> partialUpdate(GeoCompanyDTO geoCompanyDTO) {
        log.debug("Request to partially update GeoCompany : {}", geoCompanyDTO);

        return geoCompanyRepository
            .findById(geoCompanyDTO.getGeoCompanyId())
            .map(existingGeoCompany -> {
                geoCompanyMapper.partialUpdate(existingGeoCompany, geoCompanyDTO);

                return existingGeoCompany;
            })
            .map(geoCompanyRepository::save)
            .map(geoCompanyMapper::toDto);
    }

    /**
     * Get all the geoCompanies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GeoCompanyDTO> findAll() {
        log.debug("Request to get all GeoCompanies");
        return geoCompanyRepository.findAll().stream().map(geoCompanyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one geoCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoCompanyDTO> findOne(String id) {
        log.debug("Request to get GeoCompany : {}", id);
        return geoCompanyRepository.findById(id).map(geoCompanyMapper::toDto);
    }

    /**
     * Delete the geoCompany by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete GeoCompany : {}", id);
        geoCompanyRepository.deleteById(id);
    }
}
