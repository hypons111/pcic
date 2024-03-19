package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.GeoMember;
import gov.pcc.pwc.repository.GeoMemberRepository;
import gov.pcc.pwc.service.criteria.GeoMemberCriteria;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import gov.pcc.pwc.service.mapper.GeoMemberMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GeoMember}.
 */
@Service
@Transactional
public class GeoMemberService {

    private final Logger log = LoggerFactory.getLogger(GeoMemberService.class);

    private final GeoMemberRepository geoMemberRepository;

    private final GeoMemberMapper geoMemberMapper;

    public GeoMemberService(GeoMemberRepository geoMemberRepository, GeoMemberMapper geoMemberMapper) {
        this.geoMemberRepository = geoMemberRepository;
        this.geoMemberMapper = geoMemberMapper;
    }

    /**
     * Save a geoMember.
     *
     * @param geoMemberDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoMemberDTO save(GeoMemberDTO geoMemberDTO) {
        log.debug("Request to save GeoMember : {}", geoMemberDTO);
        GeoMember geoMember = geoMemberMapper.toEntity(geoMemberDTO);
        geoMember = geoMemberRepository.save(geoMember);
        return geoMemberMapper.toDto(geoMember);
    }

    /**
     * Partially update a geoMember.
     *
     * @param geoMemberDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GeoMemberDTO> partialUpdate(GeoMemberDTO geoMemberDTO) {
        log.debug("Request to partially update GeoMember : {}", geoMemberDTO);

        return geoMemberRepository
            .findById(geoMemberDTO.getGeoMemberId())
            .map(
                existingGeoMember -> {
                    geoMemberMapper.partialUpdate(existingGeoMember, geoMemberDTO);
                    return existingGeoMember;
                }
            )
            .map(geoMemberRepository::save)
            .map(geoMemberMapper::toDto);
    }

    /**
     * Get all the geoMembers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GeoMemberDTO> findAll() {
        log.debug("Request to get all GeoMembers");
        return geoMemberRepository.findAll().stream().map(geoMemberMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
    //jpa
//    @Transactional(readOnly = true)
//    public Page<GeoMemberDTO> findByCriteria(GeoMemberCriteria criteria) {
//        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
//            criteria.setSortBy(new String[] { "geoMemberId" });
//        }
//        final Page<GeoMember> geoMembers = geoMemberRepository.findAll(criteria, criteria.toPageable());
//        return geoMembers.map(geoMemberMapper::toDto);
//    }
    @Transactional(readOnly = true)
    public Page<GeoMemberDTO> findByCriteriaImpl(GeoMemberCriteria criteria) {
        log.debug("Request to get all GeoMembers by criteria");
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "geoMemberId" });
        }
        return geoMemberRepository.findAllByCriteriaImpl(criteria);
    }

    /**
     * Get one geoMember by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoMemberDTO> findOne(String id) {
        log.debug("Request to get GeoMember : {}", id);
        return geoMemberRepository.findById(id).map(geoMemberMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<GeoMemberDTO> findByUserId(String userId) {
        log.debug("Request to get GeoMember : {}", userId);
        return geoMemberRepository.findByUserId(userId).map(geoMemberMapper::toDto);
    }

    /**
     * Delete the geoMember by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete GeoMember : {}", id);
        geoMemberRepository.deleteById(id);
    }
}
