package gov.pcc.pwc.service;

import gov.pcc.pwc.common.enums.BusinessTypeEnum;
import gov.pcc.pwc.common.enums.ModuleTypeEnum;
import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.*;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.mapper.PwcDownloadAppendixMapper;
import gov.pcc.pwc.domain.PwcDownload;
import gov.pcc.pwc.repository.PwcDownloadRepository;
import gov.pcc.pwc.repository.PwcInformationRepository;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;
import gov.pcc.pwc.service.dto.PwcInformationDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadMapper;
import gov.pcc.pwc.service.mapper.PwcDownloadModuleMapper;
import gov.pcc.pwc.service.mapper.PwcDownloadRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwcDownload}.
 */
@Service
@Transactional
public class PwcDownloadService {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadService.class);

    private final PwcDownloadRepository pwcDownloadRepository;

    private final PwcDownloadMapper pwcDownloadMapper;

    private final PwcDownloadAppendixRepository pwcDownloadAppendixRepository;

    private final PwcDownloadAppendixMapper pwcDownloadAppendixMapper;

    private final PwcDownloadModuleRepository pwcDownloadModuleRepository;

    private final PwcDownloadModuleMapper pwcDownloadModuleMapper;

    private final PwcDownloadRoleRepository pwcDownloadRoleRepository;

    private final PwcDownloadRoleMapper pwcDownloadRoleMapper;

    private final AdmFileRepository admFileRepository;

    private final AdmFileService admFileService;

    private final PwcInformationRepository pwcInformationRepository;

    public PwcDownloadService(PwcDownloadRepository pwcDownloadRepository, PwcDownloadMapper pwcDownloadMapper, PwcDownloadAppendixRepository pwcDownloadAppendixRepository, PwcDownloadAppendixMapper pwcDownloadAppendixMapper, PwcDownloadModuleRepository pwcDownloadModuleRepository, 
    PwcDownloadModuleMapper pwcDownloadModuleMapper, PwcDownloadRoleRepository pwcDownloadRoleRepository, PwcDownloadRoleMapper pwcDownloadRoleMapper, AdmFileRepository admFileRepository, AdmFileService admFileService, PwcInformationRepository pwcInformationRepository) {
        this.pwcDownloadRepository = pwcDownloadRepository;
        this.pwcDownloadMapper = pwcDownloadMapper;
        this.pwcDownloadAppendixRepository = pwcDownloadAppendixRepository;
        this.pwcDownloadAppendixMapper = pwcDownloadAppendixMapper;
        this.pwcDownloadModuleRepository = pwcDownloadModuleRepository;
        this.pwcDownloadModuleMapper = pwcDownloadModuleMapper;
        this.pwcDownloadRoleRepository = pwcDownloadRoleRepository;
        this.pwcDownloadRoleMapper = pwcDownloadRoleMapper;
        this.admFileRepository = admFileRepository;
        this.admFileService = admFileService;
        this.pwcInformationRepository = pwcInformationRepository;
    }

    /**
     * Save a pwcDownload.
     *
     * @param pwcDownloadDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcDownloadDTO save(PwcDownloadDTO pwcDownloadDTO) {
        log.debug("Request to save PwcDownload : {}", pwcDownloadDTO);


        PwcDownload pwcDownload = pwcDownloadMapper.toEntity(pwcDownloadDTO);
        pwcDownload = pwcDownloadRepository.save(pwcDownload);
        return pwcDownloadMapper.toDto(pwcDownload);
    }

    /**
     * Partially update a pwcDownload.
     *
     * @param pwcDownloadDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcDownloadDTO> partialUpdate(PwcDownloadDTO pwcDownloadDTO) {
        log.debug("Request to partially update PwcDownload : {}", pwcDownloadDTO);

        return pwcDownloadRepository
            .findById(pwcDownloadDTO.getId())
            .map(
                existingPwcDownload -> {
                    pwcDownloadMapper.partialUpdate(existingPwcDownload, pwcDownloadDTO);
                    return existingPwcDownload;
                }
            )
            .map(pwcDownloadRepository::save)
            .map(pwcDownloadMapper::toDto);
    }

    /**
     * Get all the pwcDownloads.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcDownloadDTO> findAll() {
        log.debug("Request to get all PwcDownloads");
        return pwcDownloadRepository.findAll().stream().map(pwcDownloadMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcDownload by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcDownloadDTO> findOne(Long id) {
        log.debug("Request to get PwcDownload : {}", id);
        return pwcDownloadRepository.findById(id).map(pwcDownloadMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<PwcDownloadDTO> findDownloadByCriteria(PwcDownloadDTO criteria) {
        criteria.setLocation("'A','C'");
        return pwcDownloadRepository.findByDownload(criteria);
    }

    @Transactional(readOnly = true)
    public int deleteByID(PwcDownloadDTO criteria) {
        int deleteCount = 0;
        if(criteria.getId() != null) {

            List<PwcDownloadAppendix> appendixAll = pwcDownloadAppendixRepository.findByCriteriaAll(criteria);
            appendixAll.forEach(appendix -> pwcDownloadAppendixRepository.deleteById(appendix.getProDownloadAppendixId()));
            //pwcDownloadModuleRepository
            //pwcDownloadRoleRepository
            deleteCount = pwcDownloadRepository.deleteByID(criteria);
        }
        return deleteCount;
    }

    /**
     * Save a pwcInformationAll.
     *
     * @param bulletinDownAndInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcDownloadDTO saveAll(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, List<MultipartFile> uploadFile) throws IOException {
        log.debug("Request to save PwcDownload : {}", bulletinDownAndInfoDTO);

        //for PwcDownload
        PwcDownloadDTO pwcDownloadDTO = new PwcDownloadDTO();
        BeanUtils.copyProperties(dataProcessForInfo(bulletinDownAndInfoDTO), pwcDownloadDTO);
        PwcDownload pwcDownload = pwcDownloadMapper.toEntity(pwcDownloadDTO);
        pwcDownload = pwcDownloadRepository.save(pwcDownload);

        //for Appendix
        dataProcessForAppUrl(bulletinDownAndInfoDTO, pwcDownload);
        dataProcessForAppFile(bulletinDownAndInfoDTO, pwcDownload, uploadFile);

        //for Module
        saveModule(bulletinDownAndInfoDTO, pwcDownload);

        //for Role (確認)
        saveRole(bulletinDownAndInfoDTO, pwcDownload);

        return pwcDownloadMapper.toDto(pwcDownload);
    }


    public BulletinDownAndInfoDTO dataProcessForInfo(BulletinDownAndInfoDTO bulletinDownAndInfoDTO) {
        log.debug("Request to save bulletinDownAndInfoDTO : {}", bulletinDownAndInfoDTO);

        //位置(LOCATION)
        if (bulletinDownAndInfoDTO.getLocationListDisplay().size() > 0) {
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

    public void dataProcessForAppUrl(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcDownload pwcDownload) {

        if (bulletinDownAndInfoDTO.getUpLoadUrl().size() != 0) {
            // 查詢Appendix是否有資料
            List<PwcDownloadAppendixDTO> appendixList = pwcDownloadAppendixRepository.findByInfoId(String.valueOf(pwcDownload.getId()));
            // 刪除舊有的Appendix資料
            if (appendixList.size() > 0) {
                appendixList.forEach(item -> {
                    pwcDownloadAppendixRepository.deleteById(item.getProDownloadAppendixId());
                });
            }
            //寫入Appendix資料
            bulletinDownAndInfoDTO.getUpLoadUrl().forEach(item -> {
                PwcDownloadAppendixDTO pwcDownloadAppendixDTO = new PwcDownloadAppendixDTO();
                BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcDownloadAppendixDTO);
                pwcDownloadAppendixDTO.setProDownloadId(pwcDownload.getId());
                pwcDownloadAppendixDTO.setText(item.getText());
                pwcDownloadAppendixDTO.setAppendix(item.getAppendix());
                pwcDownloadAppendixDTO.setAttributes("2");
                PwcDownloadAppendix pwcDownloadAppendix = pwcDownloadAppendixMapper.toEntity(pwcDownloadAppendixDTO);
                pwcDownloadAppendixRepository.save(pwcDownloadAppendix);
            });
        }
    }

    public void dataProcessForAppFile(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcDownload pwcDownload, List<MultipartFile> uploadFile) throws IOException {
        if (bulletinDownAndInfoDTO.getInputFileText().size() != 0) {
            // 查詢Appendix是否有資料
            List<PwcDownloadAppendixDTO> appendixList = pwcDownloadAppendixRepository.findByInfoId(String.valueOf(pwcDownload.getId()));
            // 刪除舊有的Appendix資料
            if (appendixList.size() > 0) {
                appendixList.forEach(item -> {
                    pwcDownloadAppendixRepository.deleteById(item.getProDownloadAppendixId());
                });
            }
            List<AdmFile> admFiles = new ArrayList<>();
            String index = "";
            //寫入Appendix資料
            for (int i = 0; i < bulletinDownAndInfoDTO.getInputFileText().size(); i++) {
                for (int j = 0; j < uploadFile.size(); j++) {
                    if (i == j) {
                        index = String.valueOf(j+1);
                        AdmFile admFile = fileProcessForAdmFile(uploadFile.get(j), bulletinDownAndInfoDTO, pwcDownload, index);
                        admFiles.add(admFile);
                        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = new PwcDownloadAppendixDTO();
                        BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcDownloadAppendixDTO);
                        pwcDownloadAppendixDTO.setProDownloadId(pwcDownload.getId());
                        pwcDownloadAppendixDTO.setText(bulletinDownAndInfoDTO.getInputFileText().get(i));
                        pwcDownloadAppendixDTO.setAppendix(uploadFile.get(j).getOriginalFilename()); //檔案名稱
                        pwcDownloadAppendixDTO.setAttributes("1");
                        PwcDownloadAppendix pwcDownloadAppendix = pwcDownloadAppendixMapper.toEntity(pwcDownloadAppendixDTO);
                        pwcDownloadAppendixRepository.save(pwcDownloadAppendix);
                    }
                }
            }
        }
    }

    public AdmFile fileProcessForAdmFile(MultipartFile file, BulletinDownAndInfoDTO dto, PwcDownload pwcDownload, String index) throws IOException {

        List<AdmFile> admFiles = new ArrayList<>();
        String path ="";
        if(file != null) {
            if("INFO".equals(dto.getType())){
                path = ModuleTypeEnum.PWC.getCode() + "\\" + BusinessTypeEnum.PWC_DOWNLOAD.getCode();
            }
            //加入新增加的資料
            AdmFile admFile = new AdmFile();
            admFile.setModuleType(ModuleTypeEnum.PWC.getCode());
            admFile.setBusinessType(BusinessTypeEnum.PWC_DOWNLOAD.getCode());
            admFile.setCreateTime(dto.getUpdateTime());
            admFile.setCreateUser(dto.getUpdateUser());
            admFile.setUpdateTime(dto.getUpdateTime());
            admFile.setUpdateUser(dto.getUpdateUser());
            admFile.setSourceId(String.valueOf(pwcDownload.getId()) + "_" + index);
            try {
                return admFileService.save(admFile, file, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void uploadFile(List<MultipartFile> uploadFile) {
        //取得 upload文件夾路徑
//        String realPath = request.getRealPath("/upload");
        String realPath = "C:\\data\\local";
        //將上傳文件寫入 upload文件夾中
        uploadFile.forEach(file ->
        {
            try {
                file.transferTo(new File(realPath,file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void saveModule(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcDownload pwcDownload) {
        List<PwcSharedDTO> ModuleRoles = bulletinDownAndInfoDTO.getModuleRoles();

        if (ModuleRoles.size() > 0) {
            // 查詢Module是否有資料
            List<PwcDownloadModule> moduleList = pwcDownloadModuleRepository.findByInfoId(String.valueOf(pwcDownload.getId()));
            // 刪除舊有的Module資料
            if (moduleList.size() > 0) {
                moduleList.forEach(item -> {
                    pwcDownloadModuleRepository.deleteById(item.getProDownloadModuleId());
                });
            }
            //寫入Module資料
            ModuleRoles.forEach(item -> {
                PwcDownloadModuleDTO pwcDownloadModuleDTO = new PwcDownloadModuleDTO();
                BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcDownloadModuleDTO);
                pwcDownloadModuleDTO.setProDownloadId(pwcDownload.getId());
                pwcDownloadModuleDTO.setModule(item.getCode());
                PwcDownloadModule pwcDownloadModule = pwcDownloadModuleMapper.toEntity(pwcDownloadModuleDTO);
                pwcDownloadModuleRepository.save(pwcDownloadModule);
            });
        }
    }

    public void saveRole(BulletinDownAndInfoDTO bulletinDownAndInfoDTO, PwcDownload pwcDownload) {
        List<PwcSharedDTO> roles = bulletinDownAndInfoDTO.getJurisdictionRoles();

        if (roles.size() > 0) {
            // 查詢Role是否有資料
            List<PwcDownloadRole> roleList = pwcDownloadRoleRepository.findByInfoId(String.valueOf(pwcDownload.getId()));
            // 刪除舊有的Role資料
            if (roleList.size() > 0) {
                roleList.forEach(item -> {
                    pwcDownloadModuleRepository.deleteById(item.getProDownloadId());
                });
            }
            //寫入Role資料
            roles.forEach(item -> {
                PwcDownloadRoleDTO pwcDownloadRoleDTO = new PwcDownloadRoleDTO();
                BeanUtils.copyProperties(bulletinDownAndInfoDTO, pwcDownloadRoleDTO);
                pwcDownloadRoleDTO.setProDownloadId(pwcDownload.getId());
                pwcDownloadRoleDTO.setRole(item.getCode());
                PwcDownloadRole pwcDownloadRole = pwcDownloadRoleMapper.toEntity(pwcDownloadRoleDTO);
                pwcDownloadRoleRepository.save(pwcDownloadRole);
            });
        }
    }

    @Transactional(readOnly = true)
    public List<PwcDownloadDTO> findDownByCriteria(PwcDownloadDTO criteria) {
        //查此登入帳號的PWB_JURISDICTION.TYPE
        try {
            PwcInformationDTO pwcInformationDTO = new PwcInformationDTO();
            BeanUtils.copyProperties(criteria, pwcInformationDTO);
            Long type = pwcInformationRepository.findTypeByUserId(pwcInformationDTO);
            //如果type是00則可以查看所有公告，非00則只能看他擁有權限的公告
            if(type > 0){
                criteria.setLocation("'A','B','C'");
                return pwcDownloadRepository.findByDownload(criteria);
            } else {
                return pwcDownloadRepository.findDownByCriteria(criteria);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
