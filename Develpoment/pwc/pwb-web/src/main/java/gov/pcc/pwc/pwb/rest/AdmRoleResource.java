package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmRole;
import gov.pcc.pwc.repository.AdmRoleRepository;
import gov.pcc.pwc.service.AdmRoleService;
import gov.pcc.pwc.service.dto.AdmRoleDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link AdmRole}.
 */
@RestController
@RequestMapping("/api")
public class AdmRoleResource {

    private final Logger log = LoggerFactory.getLogger(AdmRoleResource.class);

    private static final String ENTITY_NAME = "AdmRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmRoleService admRoleService;

    private final AdmRoleRepository admRoleRepository;

    public AdmRoleResource(AdmRoleService admRoleService, AdmRoleRepository admRoleRepository) {
        this.admRoleService = admRoleService;
        this.admRoleRepository = admRoleRepository;
    }

    /**
     * {@code POST  /adm-role} : Create a new AdmRole and RoleFunction.
     *
     * @param admRoleDTO the AdmRole and RoleFunction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleDTO, or with status {@code 400 (Bad Request)} if the admRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-role")
    public ResponseEntity<AdmRoleDTO> createRole(@Valid @RequestBody AdmRoleDTO admRoleDTO) throws URISyntaxException {
        log.debug("REST request to save AdmRole and RoleFunction : {}", admRoleDTO);
        if (admRoleRepository.existsById(admRoleDTO.getRoleId())) {
            throw new BadRequestAlertException("the roleId had already used", ENTITY_NAME, "idexists");
        }
        AdmRoleDTO result = admRoleService.save(admRoleDTO);
        return ResponseEntity
            .created(new URI("/api/adm-role/" + result.getRoleId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD05_0001_S(result.getRoleId())))
            .body(result);
    }

    /**
     * {@code PUT  /adm-role/:roleId} : Updates an existing role.
     *
     * @param roleId the id of the roleDTO to save.
     * @param admRoleDTO the admAccountContractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleDTO,
     * or with status {@code 400 (Bad Request)} if the roleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-role/{roleId}")
    public ResponseEntity<AdmRoleDTO> updateUserAccount(
        @PathVariable(value = "roleId", required = false) final String roleId,
        @Valid @RequestBody AdmRoleDTO admRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update admRole : {}, {}", roleId, admRoleDTO);
        if (admRoleDTO.getRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleId, admRoleDTO.getRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admRoleRepository.existsById(roleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        admRoleDTO.setUpdateUser("SYS");
        admRoleDTO.setUpdateTime(Instant.now());

        AdmRoleDTO result = admRoleService.save(admRoleDTO);
        return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD05_0002_S(result.getRoleId()))).body(result);
    }

    /**
     * {@code GET  /adm-role/:roleId} : get the "id" admRole.
     *
     * @param roleId the id of the roleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-role/{roleId}")
    public ResponseEntity<AdmRoleDTO> getAdmRole(@PathVariable String roleId) {
        log.debug("REST request to get RoleDTO : {}", roleId);
        final Optional<AdmRoleDTO> amdRole = admRoleService.findOne(roleId);
        return ResponseUtil.wrapOrNotFound(amdRole);
    }

    /**
     * {@code GET  /adm-role/get-role-opts} : get all admRole for front-end roleOpts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleDTOs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-role/get-role-opts")
    public List<AdmRoleDTO> getRoleOpts() {
        log.debug("REST request to get all admRole for front-end roleOpts");
        return admRoleService.findAllRoleOpts();
    }

    @GetMapping("/service/adm-role/get-role-opts")
    public List<AdmRoleDTO> getServiceRoleOpts() {
        log.debug("REST request to get all admRole for front-end roleOpts");
        return admRoleService.findAllRoleOpts();
    }

    /**
     * {@code POST  /adm-role/criteria} : get roles by criteria.
     *
     * @param criteria the criteria of the RoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the RoleDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-role/criteria")
    public List<AdmRoleDTO> getRolesByCriteria(@RequestBody AdmRoleDTO criteria) {
        log.debug("REST request to get RoleDTOs : {}", criteria);
        return admRoleService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /adm-role/:roleId} : delete data of the "id" admRole.
     *
     * @param roleId the id of the roleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-role/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable String roleId) {
        log.debug("REST request to delete admRole : {}", roleId);
        String result = admRoleService.delete(roleId);
        if ("Y".equals(result)) {
            return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD05_0003_S(roleId))).build();
        } else {
            return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD05_0004_S(roleId))).build();
        }
    }
}
