package gov.pcc.pwc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gov.pcc.pwc.repository.AdmAccountRoleRepository;
import gov.pcc.pwc.service.mapper.AdmRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.AdmRole;
import gov.pcc.pwc.domain.AdmRoleFunction;
import gov.pcc.pwc.repository.AdmRoleFunctionExtendRepository;
import gov.pcc.pwc.repository.AdmRoleFunctionRepository;
import gov.pcc.pwc.repository.AdmRoleRepository;
import gov.pcc.pwc.service.dto.AdmFunctionDTO;
import gov.pcc.pwc.service.dto.AdmRoleDTO;

/**
 * Service Implementation for managing {@link AdmRole}.
 */
@Service
public class AdmRoleService {

    private final Logger log = LoggerFactory.getLogger(AdmRoleService.class);

    private final AdmAccountRoleRepository admAccountRoleRepository;

    private final AdmRoleRepository admRoleRepository;

    private final AdmRoleFunctionRepository admRoleFunctionRepository;

    private final AdmRoleFunctionExtendRepository admRoleFunctionExtendRepository;

    private final AdmRoleMapper admRoleMapper;

    public AdmRoleService(
            AdmAccountRoleRepository admAccountRoleRepository, AdmRoleRepository admRoleRepository,
            AdmRoleFunctionRepository admRoleFunctionRepository,
            AdmRoleFunctionExtendRepository admRoleFunctionExtendRepository,
            AdmRoleMapper admRoleMapper) {
        this.admAccountRoleRepository = admAccountRoleRepository;
        this.admRoleRepository = admRoleRepository;
        this.admRoleFunctionRepository = admRoleFunctionRepository;
        this.admRoleFunctionExtendRepository = admRoleFunctionExtendRepository;
        this.admRoleMapper = admRoleMapper;
    }

    /**
     * Save a role.
     *
     * @param admRoleDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AdmRoleDTO save(AdmRoleDTO admRoleDTO) {
        log.debug("Request to save AdmRole & RoleFunction : {}", admRoleDTO);
        AdmRole admRole = new AdmRole();
        BeanUtils.copyProperties(admRoleDTO, admRole);

        // 刪除舊有的Adm_Role_Function、Adm_Role_Function_Extend
        admRoleFunctionRepository.deleteByRoleId(admRoleDTO.getRoleId());
//        admRoleFunctionExtendRepository.deleteByRoleId(admRoleDTO.getRoleId());

        // 存入角色至Adm_Role
        admRoleRepository.save(admRole);

        // 存入角色功能至Adm_Role_Function
        admRoleDTO
            .getAdmFunctionDTOs()
            .forEach(
                function -> {
                    AdmRoleFunction admRoleFunction = new AdmRoleFunction();
                    admRoleFunction.setRoleId(admRoleDTO.getRoleId());
                    admRoleFunction.setFunctionId(function.getFunctionId());
                    admRoleFunction.setUpdateUser(admRoleDTO.getUpdateUser());
                    admRoleFunction.setUpdateTime(admRoleDTO.getUpdateTime());
                    admRoleFunctionRepository.save(admRoleFunction);

                    // 存入所選功能之延伸功能至Adm_Role_Function_Extend
//                    Arrays
//                        .stream(function.getRoleFunctionExtendList().split(","))
//                        .forEach(
//                            roleFunctionExtend -> {
//                                AdmRoleFunctionExtend admRoleFunctionExtend = new AdmRoleFunctionExtend();
//                                admRoleFunctionExtend.setRoleId(admRoleDTO.getRoleId());
//                                admRoleFunctionExtend.setFunctionId(function.getFunctionId());
//                                admRoleFunctionExtend.setFunctionExtendId(roleFunctionExtend);
//                                admRoleFunctionExtend.setUpdateUser(admRoleDTO.getUpdateUser());
//                                admRoleFunctionExtend.setUpdateTime(admRoleDTO.getUpdateTime());
//                                admRoleFunctionExtendRepository.save(admRoleFunctionExtend);
//                            }
//                        );
                }
            );
        return admRoleDTO;
    }

    /**
     * Get one Role by id.
     *
     * @param roleId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdmRoleDTO> findOne(String roleId) {
        log.debug("Request to get AdmAccount & AdmContact : {}", roleId);
        final AdmRoleDTO optional = admRoleRepository.findByRoleId(roleId);
        AdmRoleDTO admRoleDTO = new AdmRoleDTO();
        admRoleDTO.setRoleId(optional.getRoleId());
        admRoleDTO.setRoleName(optional.getRoleName());
        admRoleDTO.setRoleDescript(optional.getRoleDescript());
        admRoleDTO.setBusinessCategory(optional.getBusinessCategory());
        admRoleDTO.setUpdateUser(optional.getUpdateUser());
        admRoleDTO.setUpdateTime(optional.getUpdateTime());
        admRoleDTO.setRoleFunctionList(optional.getRoleFunctionList());
        List<AdmFunctionDTO> result = admRoleFunctionRepository.findByRoleId(roleId);

        admRoleDTO.setAdmFunctionDTOs(result);
        return Optional.ofNullable(admRoleDTO);
    }

    /**
     * Find the list of RoleDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of RoleDTO.
     */

    @Transactional(readOnly = true)
    public List<AdmRoleDTO> findByCriteria(AdmRoleDTO criteria) {
        return admRoleRepository.findAllByCriteria(criteria);
    }

    /**
     * Find all role for front-end roleOpts
     *
     * @return the list of RoleDTO.
     */

    @Transactional(readOnly = true)
    public List<AdmRoleDTO> findAllRoleOpts() {
        return admRoleRepository.findAllRoleOpts();
    }

    /**
     * Delete the "id" role.
     *
     * @param roleId the id of the entity.
     */

    @Transactional
    public String delete(String roleId) {
        log.debug("Request to delete Role : {}", roleId);
        if (admAccountRoleRepository.findByRoleId(roleId).size() > 0) {
            return "N";
        } else {
            admRoleRepository.deleteById(roleId);
            admRoleFunctionRepository.deleteByRoleId(roleId);
//            admRoleFunctionExtendRepository.deleteByRoleId(roleId);
            return "Y";
        }
    }

     /**
     * Save a admRole.
     *
     * @param admRoleDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmRoleDTO saveAdmRole(AdmRoleDTO admRoleDTO) {
        log.debug("Request to save AdmRole : {}", admRoleDTO);
        AdmRole admRole = admRoleMapper.toEntity(admRoleDTO);
        admRole = admRoleRepository.save(admRole);
        return admRoleMapper.toDto(admRole);
    }

    /**
     * Partially update a admRole.
     *
     * @param admRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AdmRoleDTO> partialUpdate(AdmRoleDTO admRoleDTO) {
        log.debug("Request to partially update AdmRole : {}", admRoleDTO);

        return admRoleRepository
            .findById(admRoleDTO.getRoleId())
            .map(existingAdmRole -> {
                admRoleMapper.partialUpdate(existingAdmRole, admRoleDTO);

                return existingAdmRole;
            })
            .map(admRoleRepository::save)
            .map(admRoleMapper::toDto);
    }

    /**
     * Get all the admRoles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmRoleDTO> findAll() {
        log.debug("Request to get all AdmRoles");
        return admRoleRepository.findAll().stream().map(admRoleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one admRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdmRoleDTO> findOneAdmRole(String id) {
        log.debug("Request to get AdmRole : {}", id);
        return admRoleRepository.findById(id).map(admRoleMapper::toDto);
    }

    /**
     * Delete the admRole by id.
     *
     * @param id the id of the entity.
     */
    public void deleteAdmRole(String id) {
        log.debug("Request to delete AdmRole : {}", id);
        admRoleRepository.deleteById(id);
    }
}
