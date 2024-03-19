package gov.pcc.pwc.service;

import gov.pcc.pwc.common.enums.BusinessTypeEnum;
import gov.pcc.pwc.common.enums.ModuleTypeEnum;
import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.*;
import gov.pcc.pwc.service.criteria.InfoServiceCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.mapper.PwcInformationAppendixMapper;
import gov.pcc.pwc.service.mapper.PwcInformationMapper;
import gov.pcc.pwc.service.mapper.PwcInformationModuleMapper;
import gov.pcc.pwc.service.mapper.PwcInformationRoleMapper;
import gov.pcc.pwc.utils.ExcelFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Service Implementation for managing {@link PwcInformation}.
 */
@Service
@Transactional
public class PwcInformationService {

    private final Logger log = LoggerFactory.getLogger(PwcInformationService.class);

    private final PwcInformationRepository pwcInformationRepository;

    private final PwcInformationMapper pwcInformationMapper;

    private final PwcInformationAppendixRepository pwcInformationAppendixRepository;

    private final PwcInformationAppendixMapper pwcInformationAppendixMapper;

    private final PwcInformationModuleRepository pwcInformationModuleRepository;

    private final PwcInformationModuleMapper pwcInformationModuleMapper;

    private final PwcInformationRoleRepository pwcInformationRoleRepository;

    private final PwcInformationRoleMapper pwcInformationRoleMapper;

    private final AdmFileRepository admFileRepository;

    private final AdmFileService admFileService;

    public PwcInformationService(PwcInformationRepository pwcInformationRepository, PwcInformationMapper pwcInformationMapper, PwcInformationAppendixRepository pwcInformationAppendixRepository, PwcInformationAppendixMapper pwcInformationAppendixRepositoryMapper, PwcInformationAppendixMapper pwcInformationAppendixMapper, PwcInformationModuleRepository pwcInformationModuleRepository, PwcInformationModuleMapper pwcInformationModuleMapper, PwcInformationRoleRepository pwcInformationRoleRepository, AdmFileRepository dmFileRepository, PwcInformationRoleMapper pwcInformationRoleMapper, AdmFileRepository admFileRepository, AdmFileService admFileService) {
        this.pwcInformationRepository = pwcInformationRepository;
        this.pwcInformationMapper = pwcInformationMapper;
        this.pwcInformationAppendixRepository = pwcInformationAppendixRepository;
        this.pwcInformationModuleRepository = pwcInformationModuleRepository;
        this.pwcInformationModuleMapper = pwcInformationModuleMapper;
        this.pwcInformationRoleRepository = pwcInformationRoleRepository;
        this.pwcInformationAppendixMapper = pwcInformationAppendixMapper;
        this.pwcInformationRoleMapper = pwcInformationRoleMapper;
        this.admFileRepository = admFileRepository;
        this.admFileService = admFileService;
    }

    /**
     * Save a pwcInformation.
     *
     * @param pwcInformationDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcInformationDTO save(PwcInformationDTO pwcInformationDTO) {
        log.debug("Request to save PwcInformation : {}", pwcInformationDTO);
        PwcInformation pwcInformation = pwcInformationMapper.toEntity(pwcInformationDTO);
        pwcInformation = pwcInformationRepository.save(pwcInformation);
        return pwcInformationMapper.toDto(pwcInformation);
    }

    /**
     * Partially update a pwcInformation.
     *
     * @param pwcInformationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcInformationDTO> partialUpdate(PwcInformationDTO pwcInformationDTO) {
        log.debug("Request to partially update PwcInformation : {}", pwcInformationDTO);

        return pwcInformationRepository
                .findById(pwcInformationDTO.getId())
                .map(
                        existingPwcInformation -> {
                            pwcInformationMapper.partialUpdate(existingPwcInformation, pwcInformationDTO);
                            return existingPwcInformation;
                        }
                )
                .map(pwcInformationRepository::save)
                .map(pwcInformationMapper::toDto);
    }

    /**
     * Get all the pwcInformations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcInformationDTO> findAll() {
        log.debug("Request to get all PwcInformations");
        return pwcInformationRepository
                .findAll()
                .stream()
                .map(pwcInformationMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcInformation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcInformationDTO> findOne(Long id) {
        log.debug("Request to get PwcInformation : {}", id);
        return pwcInformationRepository.findById(id).map(pwcInformationMapper::toDto);
    }

    /**
     * Delete the pwcInformation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwcInformation : {}", id);
        pwcInformationRepository.deleteById(id);
    }

    /**
     * Find the list of PwcInformationDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of PwcInformationDTO.
     */
    @Transactional(readOnly = true)
    public Page<PwcInformationDTO> findByCriteria(InfoServiceCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"id"});
        }
        final Page<PwcInformation> pwcInformation = pwcInformationRepository.findAll(criteria, criteria.toPageable());
        return pwcInformation.map(pwcInformationMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<PwcInformationDTO> findInformationByCriteria(PwcInformationDTO criteria) {
        criteria.setLocation("'A','C'");
        return pwcInformationRepository.findByInformation(criteria);
    }

    @Transactional(readOnly = true)
    public int deleteByID(PwcInformationDTO criteria) {
        int deleteSum = 0;
        if (criteria.getId() != null) {
            List<PwcInformationAppendix> appendixAll = pwcInformationAppendixRepository.findByCriteriaAll(criteria);
            appendixAll.forEach(appendix -> pwcInformationAppendixRepository.deleteById(appendix.getProInformationAppendixId()));
            //pwcInformationModuleRepository
            //pwcInformationRoleRepository
            deleteSum = pwcInformationRepository.deleteByID(criteria);
        }
        return deleteSum;
    }

    /**
     * Save a pwcInformationAll.
     *
     * @param bulletinDownAndInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcInformationDTO saveAll(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, List<MultipartFile> uploadFile) throws IOException {
        log.debug("Request to save PwcInformation : {}", bulletinDownAndInfoDTO);

        //for PwcInformation
        PwcInformationDTO pwcInformationDTO = new PwcInformationDTO();
        BeanUtils.copyProperties(dataProcessForInfo(bulletinDownAndInfoDTO), pwcInformationDTO);
        PwcInformation pwcInformation = pwcInformationMapper.toEntity(pwcInformationDTO);
        pwcInformation = pwcInformationRepository.save(pwcInformation);

        //for Appendix
        dataProcessForAppendixUrl(bulletinDownAndInfoDTO, pwcInformation); //URL
        dataProcessForAppendixFile(bulletinDownAndInfoDTO, pwcInformation, uploadFile); //File

        //for Module
        saveModule(bulletinDownAndInfoDTO, pwcInformation);

        //for Role
        saveRole(bulletinDownAndInfoDTO, pwcInformation);

        return pwcInformationMapper.toDto(pwcInformation);
    }

    public BulletinDownAndInfoDTO dataProcessForInfo(BulletinDownAndInfoDTO bulletinDownAndInfoDTO) {
        log.debug("Request to save PwcInformation : {}", bulletinDownAndInfoDTO);

        //位置(LOCATION)
        if (bulletinDownAndInfoDTO.getLocationListDisplay() != null && bulletinDownAndInfoDTO.getLocationListDisplay().size() > 0) {
            if (bulletinDownAndInfoDTO.getLocationListDisplay().size() > 1) {
                bulletinDownAndInfoDTO.setLocation("A");
            } else {
                String Location = bulletinDownAndInfoDTO.getLocationListDisplay().get(0);
                bulletinDownAndInfoDTO.setLocation(Location);
            }
        }

        //日期時間
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        if ("create".equals(bulletinDownAndInfoDTO.getStatus())) {
            bulletinDownAndInfoDTO.setCreateTime(ts.toInstant());
        }
        bulletinDownAndInfoDTO.setUpdateTime(ts.toInstant());

        return bulletinDownAndInfoDTO;
    }

    public void dataProcessForAppendixUrl(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcInformation pwcInformation) {

        if (bulletinDownAndInfoDTO.getUpLoadUrl().size() != 0) {
            // 查詢Appendix是否有資料
            List<PwcInformationAppendixDTO> appendixList = pwcInformationAppendixRepository.findByInfoId(String.valueOf(pwcInformation.getId()));
            // 刪除舊有的Appendix資料
            if (appendixList.size() > 0) {
                appendixList.forEach(item -> {
                    pwcInformationAppendixRepository.deleteById(item.getProInformationAppendixId());
                    admFileRepository.deleteById(Long.valueOf(item.getAppendix()));
                });
            }
            //寫入Appendix資料
            bulletinDownAndInfoDTO.getUpLoadUrl().forEach(item -> {

                PwcInformationAppendixDTO pwcInformationAppendixDTO = new PwcInformationAppendixDTO();
                BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcInformationAppendixDTO);
                pwcInformationAppendixDTO.setProInformationId(pwcInformation.getId());
                pwcInformationAppendixDTO.setText(item.getText());
                pwcInformationAppendixDTO.setAppendix(item.getAppendix());
                pwcInformationAppendixDTO.setAttributes("2");
                PwcInformationAppendix pwcInformationAppendix = pwcInformationAppendixMapper.toEntity(pwcInformationAppendixDTO);
                pwcInformationAppendixRepository.save(pwcInformationAppendix);
            });
        }
    }

    public void dataProcessForAppendixFile(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcInformation pwcInformation, List<MultipartFile> uploadFile) throws IOException {
        if (bulletinDownAndInfoDTO.getInputFileText().size() != 0) {
            // 查詢Appendix是否有資料
            List<PwcInformationAppendixDTO> appendixList = pwcInformationAppendixRepository.findByInfoId(String.valueOf(pwcInformation.getId()));
            // 刪除舊有的Appendix資料
            if (appendixList.size() > 0) {
                appendixList.forEach(item -> {
                    pwcInformationAppendixRepository.deleteById(item.getProInformationAppendixId());
                });
            }

            List<AdmFile> admFiles = new ArrayList<>();
            String index = "";
            //寫入Appendix資料
            for (int i = 0; i < bulletinDownAndInfoDTO.getInputFileText().size(); i++) {
                for (int j = 0; j < uploadFile.size(); j++) {
                    if (i == j) {
                        index = String.valueOf(j+1);
                        AdmFile admFile = fileProcessForAdmFile(uploadFile.get(j), bulletinDownAndInfoDTO, pwcInformation, index);
                        admFiles.add(admFile);
                        PwcInformationAppendixDTO pwcInformationAppendixDTO = new PwcInformationAppendixDTO();
                        BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcInformationAppendixDTO);
                        pwcInformationAppendixDTO.setProInformationId(pwcInformation.getId());
                        pwcInformationAppendixDTO.setText(bulletinDownAndInfoDTO.getInputFileText().get(i));
                        pwcInformationAppendixDTO.setAppendix(admFile.getId().toString()); //檔案名稱 admFile Id
                        pwcInformationAppendixDTO.setAttributes("1");
                        PwcInformationAppendix pwcInformationAppendix = pwcInformationAppendixMapper.toEntity(pwcInformationAppendixDTO);
                        pwcInformationAppendixRepository.save(pwcInformationAppendix);
                    }
                }
            }
        }
    }

    public AdmFile fileProcessForAdmFile(MultipartFile file, BulletinDownAndInfoDTO dto, PwcInformation pwcInformation, String index) throws IOException {

        List<AdmFile> admFiles = new ArrayList<>();
        String path ="";
        if(file != null) {
            if("INFO".equals(dto.getType())){
                path = ModuleTypeEnum.PWC.getCode() + "\\" + BusinessTypeEnum.PWC_INFORMATION.getCode();
            }
            //加入新增加的資料
                AdmFile admFile = new AdmFile();
                admFile.setModuleType(ModuleTypeEnum.PWC.getCode());
                admFile.setBusinessType(BusinessTypeEnum.PWC_INFORMATION.getCode());
                admFile.setCreateTime(dto.getUpdateTime());
                admFile.setCreateUser(dto.getUpdateUser());
                admFile.setUpdateTime(dto.getUpdateTime());
                admFile.setUpdateUser(dto.getUpdateUser());
                admFile.setSourceId(String.valueOf(pwcInformation.getId()) + "_" + index);
                try {
                   return admFileService.save(admFile, file, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public void saveModule(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcInformation pwcInformation) {
        List<PwcSharedDTO> ModuleRoles = bulletinDownAndInfoDTO.getModuleRoles();

        if (ModuleRoles.size() > 0) {
            // 查詢Module是否有資料
            List<PwcInformationModule> moduleList = pwcInformationModuleRepository.findByInfoId(String.valueOf(pwcInformation.getId()));
            // 刪除舊有的Module資料
            if (moduleList.size() > 0) {
                moduleList.forEach(item -> {
                    pwcInformationModuleRepository.deleteById(item.getProInformationModuleId());
                });
            }
            //寫入Module資料
            ModuleRoles.forEach(item -> {
                PwcInformationModuleDTO pwcInformationModuleDTO = new PwcInformationModuleDTO();
                BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcInformationModuleDTO);
                pwcInformationModuleDTO.setProInformationId(pwcInformation.getId());
                pwcInformationModuleDTO.setModule(item.getCode());
                PwcInformationModule pwcInformationModule = pwcInformationModuleMapper.toEntity(pwcInformationModuleDTO);
                pwcInformationModuleRepository.save(pwcInformationModule);
            });
        }
    }

    public void saveRole(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcInformation pwcInformation) {
        List<PwcSharedDTO> roles = bulletinDownAndInfoDTO.getJurisdictionRoles();

        if (roles.size() > 0) {
            // 查詢Role是否有資料
            List<PwcInformationRole> roleList = pwcInformationRoleRepository.findByInfoId(String.valueOf(pwcInformation.getId()));
            // 刪除舊有的Role資料
            if (roleList.size() > 0) {
                roleList.forEach(item -> {
                    pwcInformationModuleRepository.deleteById(item.getProInformationRoleId());
                });
            }
            //寫入Role資料
            roles.forEach(item -> {
                PwcInformationRoleDTO pwcInformationRoleDTO = new PwcInformationRoleDTO();
                BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcInformationRoleDTO);
                pwcInformationRoleDTO.setProInformationId(pwcInformation.getId());
                pwcInformationRoleDTO.setRole(item.getCode());
                PwcInformationRole pwcInformationRole = pwcInformationRoleMapper.toEntity(pwcInformationRoleDTO);
                pwcInformationRoleRepository.save(pwcInformationRole);
            });
        }
    }

    @Transactional(readOnly = true)
    public List<PwcInformationDTO> findInfoByCriteria(PwcInformationDTO criteria) {
        //查此登入帳號的PWB_JURISDICTION.TYPE
        try {
            Long type = pwcInformationRepository.findTypeByUserId(criteria);
            //如果type是00則可以查看所有公告，非00則只能看他擁有權限的公告
            if(type > 0){
                criteria.setLocation("'A','B','C'");
                return pwcInformationRepository.findByInformation(criteria);
            } else {
                return pwcInformationRepository.findInfoByCriteria(criteria);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Transactional(readOnly = true)
    public void getFlieListData(List<MultipartFile> uploadFile) {
        //查此登入帳號的PWB_JURISDICTION.TYPE
        try {
            ExcelFileUtil.parseFlieListData(uploadFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
