package gov.pcc.pwc.service;

import gov.pcc.pwc.common.enums.BusinessTypeEnum;
import gov.pcc.pwc.common.enums.ModuleTypeEnum;
import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.AdmFileRepository;
import gov.pcc.pwc.repository.ProEnginListRepository;
import gov.pcc.pwc.repository.ProInnovativeProductRepository;
import gov.pcc.pwc.repository.ProRecommendListRepository;
import gov.pcc.pwc.service.criteria.ProInnovativeProductProUserCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.dto.CommunicateProductQueryDTO;
import gov.pcc.pwc.service.dto.ProInnovativeProductDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.ProGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.mapper.ProEnginListMapper;
import gov.pcc.pwc.service.mapper.ProInnovativeProductMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import gov.pcc.pwc.service.mapper.ProRecommendListMapper;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link ProInnovativeProduct}.
 */
@Service
@Transactional
public class ProInnovativeProductService {

    private final Logger log = LoggerFactory.getLogger(ProInnovativeProductService.class);

    private final ProInnovativeProductRepository proInnovativeProductRepository;

    private final ProInnovativeProductMapper proInnovativeProductMapper;

    private final ProEnginListRepository proEnginListRepository;

    private final ProEnginListMapper proEnginListMapper;

    private final ProRecommendListRepository proRecommendListRepository;

    private final ProRecommendListMapper proRecommendListMapper;

    private final SerialNumGenerator serialNumGenerator;

    private final AdmFileService admFileService;

    private final AdmFileRepository admFileRepository;

    public ProInnovativeProductService(ProInnovativeProductRepository proInnovativeProductRepository,
                                       ProInnovativeProductMapper proInnovativeProductMapper,
                                       ProEnginListRepository proEnginListRepository,
                                       ProEnginListMapper proEnginListMapper,
                                       ProRecommendListRepository proRecommendListRepository,
                                       ProRecommendListMapper proRecommendListMapper,
                                       SerialNumGenerator serialNumGenerator,
                                       AdmFileService admFileService, AdmFileRepository admFileRepository) {
        this.proInnovativeProductRepository = proInnovativeProductRepository;
        this.proInnovativeProductMapper = proInnovativeProductMapper;
        this.proEnginListRepository = proEnginListRepository;
        this.proEnginListMapper = proEnginListMapper;
        this.proRecommendListRepository = proRecommendListRepository;
        this.proRecommendListMapper = proRecommendListMapper;
        this.serialNumGenerator = serialNumGenerator;
        this.admFileService =admFileService;
        this.admFileRepository = admFileRepository;
    }


    //新增方法 取得想要存儲的表的FK做關聯做三張表的儲存並且將資料統一統整到新增的DTO
    public ProInnovativeProductSumSearchDetailDTO save(ProInnovativeProductSumSearchDetailDTO proInnovativeProductSumSearchDetailDTO) throws TypeVariableNotMatchException {
        log.debug("Request to save proInnovativeProductSumSearchDetailDTO : {}", proInnovativeProductSumSearchDetailDTO);

        ProInnovativeProductDTO proInnovativeProductDTO = new ProInnovativeProductDTO();
        proInnovativeProductDTO.setCreateTime(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getCreateTime());
        proInnovativeProductDTO.setInnovateName(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateName());
        proInnovativeProductDTO.setAcceptedOrganization(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getAcceptedOrganization());
        proInnovativeProductDTO.setCommunicateDate(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getCommunicateDate());
        proInnovativeProductDTO.setCommunicateDocNumber(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getCommunicateDocNumber());
        proInnovativeProductDTO.setInnovateType(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateType());
        proInnovativeProductDTO.setInnovateFunction(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateFunction());
        proInnovativeProductDTO.setInnovateQuality(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateQuality());
        proInnovativeProductDTO.setInnovateProve(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateProve());
        proInnovativeProductDTO.setPatent(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getPatent());
        proInnovativeProductDTO.setPatentStartDate(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getPatentStartDate());
        proInnovativeProductDTO.setPatentEndDate(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getPatentEndDate());
        proInnovativeProductDTO.setConstructCost(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getConstructCost());
        proInnovativeProductDTO.setMaintainCost(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getMaintainCost());
        proInnovativeProductDTO.setInnovateCaseName(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateCaseName());
        proInnovativeProductDTO.setInnovateCaseVendor(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateCaseVendor());
        proInnovativeProductDTO.setInnovateCaseScale(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getInnovateCaseScale());
        proInnovativeProductDTO.setStatus(proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().getStatusIP());
        proInnovativeProductDTO.setUserId(proInnovativeProductSumSearchDetailDTO.getProApplyCompanyDTO().getUserId());

        String proInnovativeProductId = serialNumGenerator
                .setModule(ModuleEnum.PRO)
                .setVariable(new SerialNumVariableParam(ProGenNumberVariableEnum.INNOVATIVE_PRODUCT.getVariable()))
                .getGeneratedNumber();

        ProInnovativeProduct proInnovativeProduct = proInnovativeProductMapper.toEntity(proInnovativeProductDTO);
        proInnovativeProduct.setProInnovativeProductId(proInnovativeProductId);
        proInnovativeProduct = proInnovativeProductRepository.save(proInnovativeProduct);

        log.info("save proInnovativeProduct Id:{}", proInnovativeProduct.getProInnovativeProductId());

        //工程類別的checkbox
        if (!CollectionUtils.isEmpty(proInnovativeProductSumSearchDetailDTO.getEnginTypes())) {
            List<ProEnginList> proEnginLists = new ArrayList<>();

            for (String enginType: proInnovativeProductSumSearchDetailDTO.getEnginTypes()) {

                ProEnginList proEnginList = new ProEnginList();
                proEnginList.setProInnovativeProductId(proInnovativeProduct.getProInnovativeProductId());
                proEnginList.setEnginType(enginType);

                proEnginLists.add(proEnginList);
            }
            proEnginLists = proEnginListRepository.saveAll(proEnginLists);
            log.info("save proEnginLists:{}",proEnginLists);

        }

        //推薦單位的資料
        if (!CollectionUtils.isEmpty(proInnovativeProductSumSearchDetailDTO.getProRecommendListDTOs())) {
            List<ProRecommendList> proRecommendLists = new ArrayList<>();

            for (ProRecommendListDTO proRecommendListDTO: proInnovativeProductSumSearchDetailDTO.getProRecommendListDTOs()) {
                //getChecked為顯示打勾的地方
                if(proRecommendListDTO.getChecked()){
                    proRecommendListDTO.setProInnovativeProductId(proInnovativeProduct.getProInnovativeProductId());
                    ProRecommendList proRecommendList = proRecommendListMapper.toEntity(proRecommendListDTO);
                    proRecommendLists.add(proRecommendList);
                }
            }
            proRecommendLists = proRecommendListRepository.saveAll(proRecommendLists);
            log.info("save proRecommendLists:{} ", proRecommendLists);
        }

        //回傳編號資料
        proInnovativeProductSumSearchDetailDTO.getProInnovativeProductMainDTO().setProInnovativeProductId(proInnovativeProductId);

        return proInnovativeProductSumSearchDetailDTO;
    }



    //存檔案
    public void saveFile(ProInnovativeProductMainDTO proInnovativeProductMainDTO, MultipartFile picFile,Map<String, MultipartFile> appendixFilesMap)
            throws IOException, TypeVariableNotMatchException {
        log.debug("Request to save MultipartFile");
        Optional<AdmFile> picFileInfo = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId(
                ModuleTypeEnum.PRO.getCode(),
                BusinessTypeEnum.PRO_INNOVATIVE_PRODUCT.getCode(),
                proInnovativeProductMainDTO.getProInnovativeProductId() + "_dmFile");

        //存放DM路徑
        if(picFile != null) {
            String path = ModuleTypeEnum.PRO.getCode() + "\\" + BusinessTypeEnum.PRO_INNOVATIVE_PRODUCT.getCode() + "\\"
                    + proInnovativeProductMainDTO.getProInnovativeProductId() + "_dmFile_"  + "\\DM";
            AdmFile admFile = new AdmFile();
            if(picFileInfo.isPresent()) {
                admFile = picFileInfo.get();
//                admFile.setFileName(proInnovativeProductMainDTO.getInnovateName() + "_dmFile." + Objects.requireNonNull(picFile.getOriginalFilename()).split("\\.")[1]);
                admFile.setUpdateTime(proInnovativeProductMainDTO.getUpdateTime());
                admFile.setUpdateUser(proInnovativeProductMainDTO.getUpdateUser());
            }else {
                admFile = new AdmFile();
                admFile.setModuleType(ModuleTypeEnum.PRO.getCode());
                admFile.setBusinessType(BusinessTypeEnum.PRO_INNOVATIVE_PRODUCT.getCode());
                admFile.setSourceId(proInnovativeProductMainDTO.getProInnovativeProductId() + "_dmFile_");
//                admFile.setFileName(proInnovativeProductMainDTO.getInnovateName() + "_dmFile." + Objects.requireNonNull(picFile.getOriginalFilename()).split("\\.")[1]);
                admFile.setCreateTime(proInnovativeProductMainDTO.getCreateTime());
                admFile.setCreateUser(proInnovativeProductMainDTO.getCreateUser());
            }

              admFileService.save(admFile, picFile, path);
        }

        //存放推薦檔案路徑
        if(!appendixFilesMap.isEmpty()) {
            String path = ModuleTypeEnum.PRO.getCode() + "\\" + BusinessTypeEnum.PRO_INNOVATIVE_PRODUCT.getCode() + "\\"
                    + proInnovativeProductMainDTO.getProInnovativeProductId()  + "\\推薦檔案";
            for (String recommendType : appendixFilesMap.keySet()) {
                AdmFile admFile = new AdmFile();
                admFile.setModuleType(ModuleTypeEnum.PRO.getCode());
                admFile.setBusinessType(BusinessTypeEnum.PRO_INNOVATIVE_PRODUCT.getCode());
                admFile.setCreateTime(proInnovativeProductMainDTO.getCreateTime());
                admFile.setCreateUser(proInnovativeProductMainDTO.getCreateUser());
                admFile.setUpdateTime(proInnovativeProductMainDTO.getUpdateTime());
                admFile.setUpdateUser(proInnovativeProductMainDTO.getUpdateUser());
                admFile.setSourceId(proInnovativeProductMainDTO.getProInnovativeProductId()+ "_r_"
                        + recommendType);

                MultipartFile multipartFile = appendixFilesMap.get(recommendType);
                if(multipartFile!=null){
                    admFileService.save(admFile, multipartFile, path);
                }

            }
        }
    }


    /**
     * Partially update a proInnovativeProduct.
     *
     * @param proInnovativeProductDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProInnovativeProductDTO> partialUpdate(ProInnovativeProductDTO proInnovativeProductDTO) {
        log.debug("Request to partially update ProInnovativeProduct : {}", proInnovativeProductDTO);

        return proInnovativeProductRepository
                .findById(proInnovativeProductDTO.getProInnovativeProductId())
                .map(existingProInnovativeProduct -> {
                    proInnovativeProductMapper.partialUpdate(existingProInnovativeProduct, proInnovativeProductDTO);

                    return existingProInnovativeProduct;
                })
                .map(proInnovativeProductRepository::save)
                .map(proInnovativeProductMapper::toDto);
    }

    /**
     * Get all the proInnovativeProducts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProInnovativeProductDTO> findAll() {
        log.debug("Request to get all ProInnovativeProducts");
        return proInnovativeProductRepository
                .findAll()
                .stream()
                .map(proInnovativeProductMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one proInnovativeProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProInnovativeProductDTO> findOne(String id) {
        log.debug("Request to get ProInnovativeProduct : {}", id);
        return proInnovativeProductRepository.findById(id).map(proInnovativeProductMapper::toDto);
    }

    //前台查詢Page
    @Transactional(readOnly = true)
    public Page<ProInnovativeProductProUserDTO> findPageByProCriteria(ProInnovativeProductProUserCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"proInnovativeProductId"});
        }
        final Page<ProInnovativeProductProUserDTO> ProInnovativeProductProUserDTOs = proInnovativeProductRepository.findPageAllProInnovativeProducts(criteria);
        return proInnovativeProductRepository.findPageAllProInnovativeProducts(criteria);
    }

    //前台查詢List
    @Transactional(readOnly = true)
    public List<ProInnovativeProductProUserDTO> findByProCriteria(ProInnovativeProductProUserCriteria criteria) {

        return proInnovativeProductRepository.findAllProInnovativeProducts(criteria);
    }

    //後台查詢List
    @Transactional(readOnly = true)
    public List<ProInnovativeProductProUserDTO> findBackendProCriteria(ProInnovativeProductProUserCriteria criteria) {
        return proInnovativeProductRepository.findAllBackendProInnovativeProducts(criteria);
    }

    //後台查詢Page
    @Transactional(readOnly = true)
    public Page<ProInnovativeProductProUserDTO> findPageBackendProCriteria(ProInnovativeProductProUserCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"proInnovativeProductId"});
        }
        final Page<ProInnovativeProductProUserDTO> ProInnovativeProductProUserDTOs = proInnovativeProductRepository.findPageBackendAllProInnovativeProducts(criteria);
        return proInnovativeProductRepository.findPageBackendAllProInnovativeProducts(criteria);
    }


    //刪除資料
    public void deleteProIP(String proInnovativeProductId) {
        log.debug("Request to delete proInnovativeProductId : {}", proInnovativeProductId);
        proInnovativeProductRepository.deleteById(proInnovativeProductId);
        proEnginListRepository.deleteByProInnovativeProductId(proInnovativeProductId);
        proRecommendListRepository.deleteByProInnovativeProductId(proInnovativeProductId);

    }

    //前台查詢+明細查詢List
    @Transactional(readOnly = true)
    public ProInnovativeProductSumSearchDetailDTO findproInnovativeProductAndDetail(ProInnovativeProductProUserCriteria criteria) {
        log.debug("Request to get ProInnovativeProductSumSearchDetailDTO : {}", criteria);
        return proInnovativeProductRepository.findproInnovativeProductAndDetail(criteria);
    }


    //查詢

    /**
     * Find the list of CommunicateProductQueryDTO by criteria
     *
     * @param criteria the criteria .
     * @return the list of CommunicateProductQueryDTO .
     */
    @Transactional(readOnly = true)
    public List<CommunicateProductQueryDTO> findByCriteria(CommunicateProductQueryDTO criteria) {
        return proInnovativeProductRepository.findByCriteria(criteria);
    }

    //使用者登入後 按新增查詢帶出申請廠商的資料
    @Transactional(readOnly = true)
    public ProApplyCompanyDTO findApplyCompany(String userId) {
        log.debug("Request to get AdmAccount & AdmContact : {}",userId);
        return proInnovativeProductRepository.findApplyCompany(userId);
    }



}
