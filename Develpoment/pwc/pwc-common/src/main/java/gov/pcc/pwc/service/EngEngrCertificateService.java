package gov.pcc.pwc.service;

import gov.pcc.pwc.common.enums.BusinessTypeEnum;
import gov.pcc.pwc.common.enums.ModuleTypeEnum;
import gov.pcc.pwc.common.enums.eng.*;
import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.domain.EngEngrCertificate;
import gov.pcc.pwc.domain.EngEngrCertificatePK;
import gov.pcc.pwc.repository.AdmFileRepository;
import gov.pcc.pwc.repository.EngEngrCertificateRepository;
import gov.pcc.pwc.repository.EngMemberRepository;
import gov.pcc.pwc.service.criteria.EngEngrCertificateCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.EngGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.mapper.EngEngrCertificateMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import gov.pcc.pwc.service.mapper.EngMemberMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ArrayUtils;

/**
 * Service Implementation for managing {@link EngEngrCertificate}.
 */
@Service
@Transactional
public class EngEngrCertificateService {

    private final Logger log = LoggerFactory.getLogger(EngEngrCertificateService.class);

    private final EngEngrCertificateRepository engEngrCertificateRepository;
    private final EngEngrCertificateMapper engEngrCertificateMapper;

    private final EngMemberRepository engMemberRepository;
    private final EngMemberMapper engMemberMapper;

    private final EngEbillingCheckService engEbillingCheckService;

    private final AdmFileService admFileService;
    private final AdmFileRepository admFileRepository;

    private final EngReviewLogService engReviewLogService;

    private final EngDocNoService engDocNoService;

    private final SerialNumGenerator serialNumGenerator;

    public EngEngrCertificateService(
            EngEngrCertificateRepository engEngrCertificateRepository,
            EngEngrCertificateMapper engEngrCertificateMapper,
            EngMemberRepository engMemberRepository,
            EngMemberMapper engMemberMapper,
            EngEbillingCheckService engEbillingCheckService,
            AdmFileService admFileService,
            AdmFileRepository admFileRepository,
            EngReviewLogService engReviewLogService,
            EngDocNoService engDocNoService, SerialNumGenerator serialNumGenerator) {
        this.engEngrCertificateRepository = engEngrCertificateRepository;
        this.engEngrCertificateMapper = engEngrCertificateMapper;
        this.engMemberRepository = engMemberRepository;
        this.engMemberMapper = engMemberMapper;
        this.engEbillingCheckService = engEbillingCheckService;
        this.admFileService = admFileService;
        this.admFileRepository = admFileRepository;
        this.engReviewLogService = engReviewLogService;
        this.engDocNoService = engDocNoService;
        this.serialNumGenerator = serialNumGenerator;
    }

    /**
     * Save a engEngrCertificate.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    public Eng0101DTO save(EngEngrCertificateDTO dto, MultipartFile picFile, List<MultipartFile> appendixFiles, EngReviewLogDTO engReviewLogDTO)
            throws TypeVariableNotMatchException, IOException {
        log.debug("Request to save EngEngrCertificate : {}", dto);

        // [ 新增或更新 ENG_MEMBER, 須先處理以取得 engId ]
        // 狀態如為已結案
        if (Objects.equals(dto.getStatus(), StatusEnum.CLOSE.getCode())) {
            EngMemberDTO engMemberDTO;
            boolean isNew = false;
            boolean isSave = false;
            // engId == null, 表示ENG_MEMBER無資料
            if (dto.getEngrId() == null) {
                engMemberDTO = new EngMemberDTO();
                isNew = true;
                isSave = true;
            } else {
                Optional<EngMemberDTO> optEngMemberDTO = engMemberRepository.findById(dto.getEngrId()).map(engMemberMapper::toDto);
                if(optEngMemberDTO.isPresent()) {
                    engMemberDTO = optEngMemberDTO.get();
                    if (engMemberDTO.getUpdateTime().getEpochSecond() < dto.getApplyDate().atStartOfDay(ZoneId.systemDefault()).toEpochSecond()) {
                        isSave = true;
                    }
                }else {
                    engMemberDTO = new EngMemberDTO();
                    isNew = true;
                    isSave = true;
                }
            }

            if(isNew) {
                String engMemberId = serialNumGenerator
                        .setModule(ModuleEnum.ENG)
                        .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.MEMBER.getVariable()))
                        .getGeneratedNumber();
                engMemberDTO.setEngMemberId(engMemberId);
                // 取得 engId
                dto.setEngrId(engMemberId);
                engMemberDTO.setType(MemberTypeEnum.ENGR.getCode());
                engMemberDTO.setCreateUser(dto.getUpdateUser());
                engMemberDTO.setCreateTime(dto.getUpdateTime());
            }

            if (isSave) {
                engMemberDTO.setChName(dto.getChName());
                engMemberDTO.setEnName(dto.getEnName());
                engMemberDTO.setIdno(dto.getIdno());
                engMemberDTO.setBirthDate(dto.getBirthDate());
                engMemberDTO.setSex(dto.getSex());
                engMemberDTO.setTel1(dto.getContactTelOffice());
                engMemberDTO.setTel2(dto.getContactTelHome());
                engMemberDTO.setTel3(dto.getMobileTel());
                engMemberDTO.setFax(dto.getFax());
                engMemberDTO.setPermanentAddrZip(dto.getPermanentAddrZip());
                engMemberDTO.setPermanentAddrCity(dto.getPermanentAddrCity());
                engMemberDTO.setPermanentAddrTown(dto.getPermanentAddrTown());
                engMemberDTO.setPermanentAddrOther(dto.getPermanentAddrOther());
                engMemberDTO.setContactAddrZip(dto.getContactAddrZip());
                engMemberDTO.setContactAddrCity(dto.getContactAddrCity());
                engMemberDTO.setContactAddrTown(dto.getContactAddrTown());
                engMemberDTO.setContactAddrOther(dto.getContactAddrOther());
                engMemberDTO.setEmail1(dto.getEmail());
                engMemberDTO.setIdentityType(dto.getIdentityType());
                engMemberDTO.setAddrForeign(dto.getAddrForeign());
                engMemberDTO.setUpdateUser(dto.getUpdateUser());
                engMemberDTO.setUpdateTime(dto.getUpdateTime());
                engMemberRepository.save(engMemberMapper.toEntity(engMemberDTO));
            }
        }


        // [ 新增或更新 ENG_ENGR_CERTIFICATE ]
        Eng0101DTO eng0101DTO = new Eng0101DTO();
        EngEngrCertificatePK pk = new EngEngrCertificatePK(dto.getEngEngrCertificateId(), dto.getEngEngrCertificateVersion());

        // 表示所有會新增一筆資料的情況，包含新增、變更申請、新增另案、補登
        if (!engEngrCertificateRepository.existsById(pk)) {

            // engEngrCertificateId == null，表示為單純新增時，非變更申請、新增另案、補登
            if (dto.getEngEngrCertificateId() == null) {
//            if (Objects.equals(dto.getNewFrom(), "1")) {
                dto.setEngEngrCertificateId(serialNumGenerator
                        .setModule(ModuleEnum.ENG)
                        .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.ENGR_CERTIFATE_ID.getVariable()))
                        .getGeneratedNumber());
                dto.setEngrCertificateDocNo(serialNumGenerator
                        .setModule(ModuleEnum.ENG)
                        .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.ENGR_CERTIFICATE_DOC_NO.getVariable()))
                        .getGeneratedNumber());
//                dto.setEngEngrCertificateVersion(BigDecimal.valueOf(1.000));
            }
//            else if(Objects.equals(dto.getNewFrom(), "2") || Objects.equals(dto.getNewFrom(), "3") || Objects.equals(dto.getNewFrom(), "4")) {
//                EngEngrCertificateDTO next = findTopByEngEngrCertificateIdAndEngEngrCertificateVersionGreaterThanOrderByEngEngrCertificateVersion(dto.getEngEngrCertificateId(), dto.getEngEngrCertificateVersion());
//                BigDecimal status = null;
//                if (next != null) {
//                    BigDecimal versionDiff = next.getEngEngrCertificateVersion().subtract(dto.getEngEngrCertificateVersion(), MathContext.DECIMAL32);
//                    if (1 >= versionDiff.doubleValue() && versionDiff.doubleValue() > 0.1) {
//                        status = dto.getEngEngrCertificateVersion().add(BigDecimal.valueOf(0.1), MathContext.DECIMAL32);
//                    } else if (0.1 >= versionDiff.doubleValue() && versionDiff.doubleValue() > 0.01) {
//                        status = 	dto.getEngEngrCertificateVersion().add(BigDecimal.valueOf(0.01), MathContext.DECIMAL32);
//                    } else if (0.01 >= versionDiff.doubleValue() && versionDiff.doubleValue() > 0.001) {
//                        status = dto.getEngEngrCertificateVersion().add(BigDecimal.valueOf(0.001), MathContext.DECIMAL32);
//                    } else if (versionDiff.doubleValue() == 0.001) {
//                        throw new RuntimeException("無法新增");
//                    }
//                } else {
//                    status = dto.getEngEngrCertificateVersion().add(BigDecimal.valueOf(1), MathContext.DECIMAL32);
//                }
//                dto.setEngEngrCertificateVersion(status);
//            }

            dto.setEngEngrCertificateNo(serialNumGenerator
                    .setModule(ModuleEnum.ENG)
                    .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.ENGR_CERTIFATE_NO.getVariable()))
                    .getGeneratedNumber());

            // [新增 ENG_EBILLING_CHECK]
            // 申請方式為線上申請時，新增付款資訊
            if(dto.getApplyType().equals(ApplyTypeEnum.ONLINE_APPLY.getCode())) {
                EngEbillingCheckDTO engEbillingCheckDTO = new EngEbillingCheckDTO();
                engEbillingCheckDTO.setDocType("1"); // 與EngDocNoType相同，差一個0，待統一
                engEbillingCheckDTO.setDocId(dto.getEngEngrCertificateNo());
                engEbillingCheckDTO.setUpdateTime(dto.getUpdateTime());
                engEbillingCheckDTO.setUpdateUser(dto.getUpdateUser());
                engEbillingCheckDTO.setCreateTime(dto.getCreateTime());
                engEbillingCheckDTO.setCreateUser(dto.getCreateUser());
                BigDecimal price;
                String[] applyItemsList = dto.getApplyItemsList().split(",");
                if (Arrays.asList(applyItemsList).contains(ApplyItemEnum.NEW_APPLY.getCode())) {
                    price = BigDecimal.valueOf(800);
                } else if (ArrayUtils.contains(applyItemsList, ApplyItemEnum.REISSUE.getCode())) {
                    price = BigDecimal.valueOf(700);
                } else {
                    price = BigDecimal.valueOf(0);
                }
                engEbillingCheckDTO.setPrice(price);
                EngEbillingCheckDTO newEngEbillingCheckDTO = engEbillingCheckService.save(engEbillingCheckDTO);
                eng0101DTO.setEngEbillingCheckDTO(newEngEbillingCheckDTO);
            }
        }
        EngEngrCertificate engEngrCertificate = engEngrCertificateMapper.toEntity(dto);
        engEngrCertificate = engEngrCertificateRepository.save(engEngrCertificate);
        EngEngrCertificateDTO newDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        // [ 新增或更新 ADM_FILE ]
        DecimalFormat bf = new DecimalFormat("0.000");
        dto.setEngEngrCertificateVersion(new BigDecimal(bf.format(dto.getEngEngrCertificateVersion())));
        Optional<AdmFile> picFileInfo = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId(
                ModuleTypeEnum.ENG.getCode(),
                BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode(),
                dto.getEngEngrCertificateId() + "_" + dto.getEngEngrCertificateVersion() + "@0");
        if(picFile != null) {
            String path = ModuleTypeEnum.ENG.getCode() + "\\" + BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode() + "\\"
                    + dto.getEngEngrCertificateId() + "_" + dto.getEngEngrCertificateVersion() + "\\照片";
            AdmFile admFile;
            if(picFileInfo.isPresent()) {
                admFile = picFileInfo.get();
                admFile.setFilePathName(dto.getChName() + "_大頭貼." + Objects.requireNonNull(picFile.getOriginalFilename()).split("\\.")[1]);
                admFile.setUpdateTime(dto.getUpdateTime());
                admFile.setUpdateUser(dto.getUpdateUser());
            }else {
                admFile = new AdmFile();
                admFile.setModuleType(ModuleTypeEnum.ENG.getCode());
                admFile.setBusinessType(BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode());
                admFile.setSourceId(dto.getEngEngrCertificateId() + "_" + dto.getEngEngrCertificateVersion() + "@0");
                admFile.setFilePathName(dto.getChName() + "_大頭貼." + Objects.requireNonNull(picFile.getOriginalFilename()).split("\\.")[1]);
                admFile.setCreateTime(dto.getUpdateTime());
                admFile.setCreateUser(dto.getUpdateUser());
            }
            AdmFile picFileOut = admFileService.save(admFile, picFile, path);
            newDTO.setPicFileInfo(picFileOut);
        }else {
            picFileInfo.ifPresent(newDTO::setPicFileInfo);
        }

        List<AdmFile> appendixFilesInfoFromDB = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdLikeOrderBySourceId(
                ModuleTypeEnum.ENG.getCode(),
                BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode(),
                dto.getEngEngrCertificateId() + "_" + dto.getEngEngrCertificateVersion() + "@%");
        appendixFilesInfoFromDB.removeIf(admFile -> Objects.equals(admFile.getSourceId().split("@")[1], "0"));
        List<AdmFile> admFiles = new ArrayList<>();

        if(appendixFiles != null) {
            String path = ModuleTypeEnum.ENG.getCode() + "\\" + BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode() + "\\"
                    + dto.getEngEngrCertificateId() + "_" + dto.getEngEngrCertificateVersion() + "\\附件";
            int index = 1;
            for(AdmFile admFile: appendixFilesInfoFromDB) {
                admFile.setSourceId(admFile.getSourceId().split("@")[0] + "@" + index);
                admFiles.add(admFileService.save(admFile));
                index++;
            }
            for (MultipartFile file : appendixFiles) {
                AdmFile admFile = new AdmFile();
                admFile.setModuleType(ModuleTypeEnum.ENG.getCode());
                admFile.setBusinessType(BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode());
                admFile.setCreateTime(dto.getUpdateTime());
                admFile.setCreateUser(dto.getUpdateUser());
                admFile.setUpdateTime(dto.getUpdateTime());
                admFile.setUpdateUser(dto.getUpdateUser());
                admFile.setSourceId(dto.getEngEngrCertificateId() + "_"
                        + dto.getEngEngrCertificateVersion() + "@" + index);
                admFiles.add(admFileService.save(admFile, file, path));
                index++;
            }
        }else {
            admFiles.addAll(appendixFilesInfoFromDB);
        }

        // [ 新增 ENG_REVIEW_LOG，及字號保留機制 ]
        if(engReviewLogDTO != null) {
            engReviewLogService.insert(engReviewLogDTO);
            // 作廢時
            if(Objects.equals(engReviewLogDTO.getReviewStatus(), ReviewStatusEnum.DISCARD.getCode())) {
                // 查出相同id的資料，並以version排序
                List<EngEngrCertificateDTO> list = findByEngEngrCertificateId(dto.getEngEngrCertificateId())
                        .stream()
                        .sorted(Comparator.comparing(EngEngrCertificateDTO::getEngEngrCertificateVersion))
                        .collect(Collectors.toList());
                // 如果是初始版本(version最小)，則清除所有相同字號資料，並新增至ENG_DOC_NO
                if(!list.isEmpty() && Objects.equals(list.get(0).getEngEngrCertificateVersion().doubleValue(), dto.getEngEngrCertificateVersion().doubleValue())) {
                    String docNo = dto.getEngrCertificateDocNo().replaceAll("[^\\d]", ""); // 去除數字以外字元
                    EngDocNoDTO engDocNoDTO = new EngDocNoDTO();
                    engDocNoDTO.setEngDocNoType(EngDocNoTypeEnum.ENGR_CERTIFICATE.getCode());
                    engDocNoDTO.setEngDocNoNo(docNo);
                    engDocNoDTO.setCreateTime(engReviewLogDTO.getCreateTime());
                    engDocNoDTO.setCreateUser(engReviewLogDTO.getCreateUser());
                    engDocNoDTO.setUpdateTime(engReviewLogDTO.getUpdateTime());
                    engDocNoDTO.setUpdateUser(engReviewLogDTO.getUpdateUser());
                    engDocNoService.save(engDocNoDTO);
                    engEngrCertificateRepository.clearDocNoByEngrCertificateDocNo(dto.getEngrCertificateDocNo());

                    // 讓回傳的EngEngrCertificateDTO為已清除字號
                    newDTO.setEngrCertificateDocNo(null);
                }
            }
        }

        newDTO.setAppendixFilesInfo(admFiles);
        eng0101DTO.setEngEngrCertificateDTO(newDTO);
        return eng0101DTO;
    }

    /**
     * Partially update a engEngrCertificate.
     *
     * @param engEngrCertificateDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngEngrCertificateDTO> partialUpdate(EngEngrCertificateDTO engEngrCertificateDTO) {
        log.debug("Request to partially update EngEngrCertificate : {}", engEngrCertificateDTO);

        return engEngrCertificateRepository
                .findById(new EngEngrCertificatePK(engEngrCertificateDTO.getEngEngrCertificateId(),
                        engEngrCertificateDTO.getEngEngrCertificateVersion()))
                .map(existingEngEngrCertificate -> {
                    engEngrCertificateMapper.partialUpdate(existingEngEngrCertificate, engEngrCertificateDTO);

                    return existingEngEngrCertificate;
                })
                .map(engEngrCertificateRepository::save)
                .map(engEngrCertificateMapper::toDto);
    }

    /**
     * Get all the engEngrCertificates.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngEngrCertificateDTO> findAll() {
        log.debug("Request to get all EngEngrCertificates");
        return engEngrCertificateRepository
                .findAll()
                .stream()
                .map(engEngrCertificateMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engEngrCertificate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngEngrCertificateDTO> findOne(String id, BigDecimal version) {
        log.debug("Request to get EngEngrCertificate : {}", id);
        return engEngrCertificateRepository.findById(new EngEngrCertificatePK(id, version))
                .map(engEngrCertificateMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<EngEngrCertificateDTO> findOne(@NotNull EngEngrCertificateCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "applyDate" });
            criteria.setSortDirection(Sort.Direction.DESC);
        }
        Page<EngEngrCertificate> engEngrCertificates = engEngrCertificateRepository.findAll(criteria, criteria.toPageable());
        return engEngrCertificates.get().findAny().map(engEngrCertificateMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Eng0101DTO findEng0101(@NotNull EngEngrCertificateDTO dto) throws IOException {
        log.debug("Request to get Eng0101 : {}, {}", dto.getEngEngrCertificateId(), dto.getEngEngrCertificateVersion());

        DecimalFormat bf = new DecimalFormat("0.000");
        dto.setEngEngrCertificateVersion(new BigDecimal(bf.format(dto.getEngEngrCertificateVersion())));

        Optional<EngEngrCertificateDTO> engEngrCertificateDTO = engEngrCertificateRepository.findById(new EngEngrCertificatePK(
                dto.getEngEngrCertificateId(), dto.getEngEngrCertificateVersion())).map(engEngrCertificateMapper::toDto);
        Optional<EngEbillingCheckDTO> engEbillingCheckDTO = engEbillingCheckService.findByDocTypeAndDocId(
            "1", dto.getEngEngrCertificateNo());
        Optional<AdmFile> picFileInfo = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId(
                ModuleTypeEnum.ENG.getCode(),
                BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode(),
                dto.getEngEngrCertificateId() + "_" + dto.getEngEngrCertificateVersion() + "@0");
        List<AdmFile> admFiles = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdLikeOrderBySourceId(
                ModuleTypeEnum.ENG.getCode(),
                BusinessTypeEnum.ENG_ENGR_CERTIFICATE.getCode(),
                dto.getEngEngrCertificateId() + "_" + dto.getEngEngrCertificateVersion() + "@%");
        List<AdmFile> appendixFiles = admFiles.stream()
                .filter(file -> Integer.parseInt(StringUtils.substringAfter(file.getSourceId(), "@")) > 0)
                .collect(Collectors.toList());

        Eng0101DTO eng0101DTO = new Eng0101DTO();
        if(engEngrCertificateDTO.isPresent()) {
            engEngrCertificateDTO.get().setPicFileInfo(picFileInfo.orElse(null));
            engEngrCertificateDTO.get().setAppendixFilesInfo(appendixFiles);
            eng0101DTO.setEngEngrCertificateDTO(engEngrCertificateDTO.get());
        }
        eng0101DTO.setEngEbillingCheckDTO(engEbillingCheckDTO.orElse(null));

        return eng0101DTO;
    }
    @Transactional(readOnly = true)
    public List<EngEngrCertificateDTO> findByIdnoAndStatusAndApplyItemsList(String idno, String status, String applyItemsList) {
        log.debug("Request to get EngEngrCertificates, idno : {}, status : {}, applyItemsList : {}", idno, status, applyItemsList);
        return engEngrCertificateRepository.findByIdnoAndStatusAndApplyItemsList(idno, status, applyItemsList).stream()
                .map(engEngrCertificateMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Delete the engEngrCertificate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id, BigDecimal version) {
        log.debug("Request to delete EngEngrCertificate : {}", id);
        engEngrCertificateRepository.deleteById(new EngEngrCertificatePK(id, version));
    }

    /**
     * Find the page of EngEngrCertificateDTO by criteria.
     *
     * @param criteria the EngEngrCertificateCriteria.
     * @return the page of EngEngrCertificateDTO.
     */
    @Transactional(readOnly = true)
    public Page<EngEngrCertificateDTO> findAll(@NotNull EngEngrCertificateCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "applyDate" });
            criteria.setSortDirection(Sort.Direction.DESC);
        }
        Page<EngEngrCertificate> engEngrCertificates = engEngrCertificateRepository.findAll(criteria,
                criteria.toPageable());
        return engEngrCertificates.map(engEngrCertificateMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<EngEngrCertificateDTO> findByEngEngrCertificateId(String engEngrCertificateId) {
        log.debug("Request to get EngEngrCertificates by engEngrCertificateId");
        return engEngrCertificateRepository
                .findByEngEngrCertificateId(engEngrCertificateId)
                .stream()
                .map(engEngrCertificateMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public EngEngrCertificateDTO findTopVersionByIdAndVersionGreaterThan(String id, BigDecimal version) {
        log.debug("Request to get EngEngrCertificates by engEngrCertificateId");
        return engEngrCertificateRepository
                .findTopByEngEngrCertificateIdAndEngEngrCertificateVersionGreaterThanOrderByEngEngrCertificateVersion(id, version)
                .map(engEngrCertificateMapper::toDto)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<EngEngrCertificateDTO> findByIdnoAndStatus(String idno, String status) {
        log.debug("Request to get EngEngrCertificates by engEngrCertificateId");
        return engEngrCertificateRepository
                .findByIdnoAndStatus(idno, status)
                .stream()
                .map(engEngrCertificateMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
