package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.BidProject;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.BidPrjDeleteRepository;
import gov.pcc.pwc.repository.BidProjectRepository;
import gov.pcc.pwc.service.criteria.BidProjectCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.mapper.BidProjectMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidProject}.
 */
@Service
@Transactional
public class BidProjectService {

    private final Logger log = LoggerFactory.getLogger(BidProjectService.class);

    private final BidProjectRepository bidProjectRepository;

    private final BidProjectMapper bidProjectMapper;

    private final BidPrjDeleteService bidPrjDeleteService;

    public BidProjectService(BidProjectRepository bidProjectRepository, BidProjectMapper bidProjectMapper, BidPrjDeleteRepository bidPrjDeleteRepository, BidPrjDeleteService bidPrjDeleteService) {
        this.bidProjectRepository = bidProjectRepository;
        this.bidProjectMapper = bidProjectMapper;
        this.bidPrjDeleteService = bidPrjDeleteService;
    }

    /**
     * Save a bidProject.
     *
     * @param bidProjectDTO the entity to save.
     * @return the persisted entity.
     */
    public BidProjectDTO save(BidProjectDTO bidProjectDTO) {
        log.debug("Request to save BidProject : {}", bidProjectDTO);
        BidProject bidProject = bidProjectMapper.toEntity(bidProjectDTO);
        bidProject = bidProjectRepository.save(bidProject);
        return bidProjectMapper.toDto(bidProject);
    }

    /**
     * Partially update a bidProject.
     *
     * @param bidProjectDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidProjectDTO> partialUpdate(BidProjectDTO bidProjectDTO) {
        log.debug("Request to partially update BidProject : {}", bidProjectDTO);

        return bidProjectRepository
            .findById(new BidProjectPrimaryKey(bidProjectDTO.getWkut(),bidProjectDTO.getPrjno()))
            .map(existingBidProject -> {
                bidProjectMapper.partialUpdate(existingBidProject, bidProjectDTO);

                return existingBidProject;
            })
            .map(bidProjectRepository::save)
            .map(bidProjectMapper::toDto);
    }

    /**
     * Get all the bidProjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidProjectDTO> findAll() {
        log.debug("Request to get all BidProjects");
        return bidProjectRepository.findAll().stream().map(bidProjectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<BidProjectInfoDTO> findBidProjectInfoByWkutAndPrjno(String wkut, String prjno) {
        log.debug("Request to get all BidProjects");
        return bidProjectRepository.findBidProjectInfoByWkutAndPrjno(wkut,prjno);
    }

    @Transactional(readOnly = true)
    public BidProjectBaseDTO findBidProjectBaseByWkutAndPrjno(String wkut, String prjno) {
        log.debug("Request to get all BidProjects");
        return bidProjectRepository.findBidProjectBaseByWkutAndPrjno(wkut,prjno);
    }

    /**
     * Get one bidProject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidProjectDTO> findOne(BidProjectPrimaryKey id) {
        log.debug("Request to get BidProject : {}", id);
        return bidProjectRepository.findById(id).map(bidProjectMapper::toDto);
    }

    /**
     * Delete the bidProject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidProjectPrimaryKey id) {
        log.debug("Request to delete BidProject : {}", id);
        bidProjectRepository.deleteById(id);
    }


    public void deleteAndInsert(BidPrjDeleteDTO bidPrjDeleteDTO) throws Exception {
        BidProjectPrimaryKey id = new BidProjectPrimaryKey(bidPrjDeleteDTO.getWkut(),bidPrjDeleteDTO.getPrjno());
        log.debug("Request to insert BidPrjDelete : {}", bidPrjDeleteDTO);
        bidPrjDeleteService.save(bidPrjDeleteDTO);
//        if(true){
//            throw new Exception();
//        }
        log.debug("Request to delete BidProject : {}", id);
        bidProjectRepository.deleteById(id);
    }

    public BidPrjDeleteDTO projectDTOtoPrjDeleteDTO(BidProjectDTO bidProjectDTO){
        BidPrjDeleteDTO deleteProject = new BidPrjDeleteDTO();
        deleteProject.setWkut(bidProjectDTO.getWkut());
        deleteProject.setPrjno(bidProjectDTO.getPrjno());
        deleteProject.setName(bidProjectDTO.getName());
        deleteProject.setPlnCode(bidProjectDTO.getPlnCode());
        deleteProject.setPlnNo(bidProjectDTO.getPlnNo());
        deleteProject.setTypeCode(bidProjectDTO.getTypeCode());
        deleteProject.setTotAmt(bidProjectDTO.getTotAmt());
        deleteProject.setBdgt1(bidProjectDTO.getBdgt1());
        deleteProject.setOpDate(bidProjectDTO.getOpDate());
        deleteProject.setAopDate(bidProjectDTO.getAopDate());
        deleteProject.setPctDate(bidProjectDTO.getPctDate());
        deleteProject.setActDate(bidProjectDTO.getActDate());
        deleteProject.setCtSum(bidProjectDTO.getCtSum());
        deleteProject.setCntid(bidProjectDTO.getCntid());
        deleteProject.setPays(bidProjectDTO.getPays());
        deleteProject.setCtmeth(bidProjectDTO.getCtmeth());
        deleteProject.setActmth(bidProjectDTO.getActmth());
        deleteProject.setwKind(bidProjectDTO.getwKind());
        deleteProject.setwDays(bidProjectDTO.getwDays());
        deleteProject.setPlaceCode(bidProjectDTO.getPlaceCode());
        deleteProject.setMan(bidProjectDTO.getMan());
        deleteProject.setTel(bidProjectDTO.getTel());
        deleteProject.setLnkNo2(bidProjectDTO.getLnkNo2());
        deleteProject.setFcost2(bidProjectDTO.getFcost2());
        deleteProject.setLnkCode(bidProjectDTO.getLnkCode());
        deleteProject.setLnkCode2(bidProjectDTO.getLnkCode2());
        deleteProject.setLnkCode3(bidProjectDTO.getLnkCode3());

        return deleteProject;
    }

    public List<BidUnit10DTO> findProjectDSUT(BidProjectPrimaryKey id){
        return bidProjectRepository.findProjectUnit(id.getWkut(),id.getPrjno(),"1");
    }

    public List<BidUnit10DTO> findProjectSCUT(BidProjectPrimaryKey id){
        return bidProjectRepository.findProjectUnit(id.getWkut(),id.getPrjno(),"2");
    }

    public List<BidUnit10DTO> findProjectCCUT(BidProjectPrimaryKey id){
        return bidProjectRepository.findProjectUnit(id.getWkut(),id.getPrjno(),"3");
    }

    public List<BidProjectDTO> findProjectListByCriteria(BidProjectCriteria criteria){
        return bidProjectRepository.findAll(criteria).stream().map(bidProjectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<BidProjectDTO> findProjectByCriteria(BidProjectCriteria criteria){
        return bidProjectRepository.findAll(criteria,criteria.toPageable()).map(bidProjectMapper::toDto);
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validCreateData(BidProjectDTO bidProjectDTO){
        List<String> retrunArray = new ArrayList<>();
        // (1) 若「標案名稱」＝空值，警示訊息：請輸入「標案名稱」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getName())) {
            retrunArray.add("請輸入「標案名稱」(ADM-0001-W)");
        }
        // (2) 若「工程類別」＝空值，警示訊息：請輸入「工程類別」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getTypeCode())) {
            retrunArray.add("請輸入「工程類別」(ADM-0001-W)");
        }
        // (3) 若「發包預算」＝空值，警示訊息：請輸入「發包預算」(ADM-0001-W)。
        if (bidProjectDTO.getBdgt1() == null) {
            retrunArray.add("請輸入「發包預算」(ADM-0001-W)");
        }
        // (4) 若「預定公告日期」＝空值，警示訊息：請輸入「預定公告日期」(ADM-0001-W)。
        if (bidProjectDTO.getOpDate() == null) {
            retrunArray.add("請輸入「預定公告日期」(ADM-0001-W)");
        }
        // (5) 若「預定決標日期」＝空值，警示訊息：請輸入「預定決標日期」(ADM-0001-W)。
        if (bidProjectDTO.getPctDate() == null) {
            retrunArray.add("請輸入「預定決標日期」(ADM-0001-W)");
        }
        // (6) 若「標案主辦資料-姓名」＝空值，警示訊息：請輸入「標案主辦資料-姓名」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getMan())) {
            retrunArray.add("請輸入「標案主辦資料-姓名」(ADM-0001-W)");
        }
        // (7) 若「標案主辦資料-聯絡電話」＝空值，警示訊息：請輸入「標案主辦資料-聯絡電話」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getTel())) {
            retrunArray.add("請輸入「標案主辦資料-聯絡電話」(ADM-0001-W)");
        }
        // (8) 若「標案主辦資料-E_Mail信箱」＝空值，警示訊息：請輸入「標案主辦資料-E_Mail信箱」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.geteMail())) {
            retrunArray.add("請輸入「標案主辦資料-E_Mail信箱」(ADM-0001-W)");
        }
        // (9) 若「工程概要」＝空值，警示訊息：請輸入「工程概要」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getContain())) {
            retrunArray.add("請輸入「工程概要」(ADM-0001-W)");
        }
        return retrunArray;
    }

    /**
     * 驗證前端DTO傳來的是否通過檢核
     * 若不通過的會把錯誤訊息放在回傳的陣列裡面
     */
    public List<String> validUpdateData(BidProjectDTO bidProjectDTO){
        List<String> retrunArray = new ArrayList<>();
        // 基本資料編輯-基本資料1
        // (1) 若「實際決標日期」＝空值且「標案名稱」內含"解約"，警示訊息：工程如尚未決標(「實際決標日期」未填報) 不可加註已解約(BID-0068-W)。
        if (bidProjectDTO.getActDate() == null && StringUtils.contains(bidProjectDTO.getName(), "解約")) {
            retrunArray.add("工程如尚未決標(「實際決標日期」未填報) 不可加註已解約(BID-0068-W)");
        }
        // (2) 若「解約原因」＜＞空值且「標案名稱」未含"解約"，警示訊息：已填報工程「解約原因」，標案名稱不可拿掉『已解約』註記(BID-0069-W)。
        if (StringUtils.isNotBlank(bidProjectDTO.getGyResn()) && !StringUtils.contains(bidProjectDTO.getName(), "解約")) {
            retrunArray.add("已填報工程「解約原因」，標案名稱不可拿掉『已解約』註記(BID-0069-W)");
        }
        // (3) 若「是否統包」＜＞"Y"且「標案名稱」內含"統包"，警示訊息：工程名稱含「統包」字眼，但是否統包欄位點選『非屬統包案件』(BID-0070-W)。
        if (!StringUtils.equalsIgnoreCase(bidProjectDTO.getIsUnion(), "Y") && StringUtils.contains(bidProjectDTO.getName(), "統包")) {
            retrunArray.add("工程名稱含「統包」字眼，但是否統包欄位點選『非屬統包案件』(BID-0070-W)");
        }
        // (4) 若「工程類別」＝空值或 "00"，警示訊息：請輸入「工程類別」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getTypeCode()) || StringUtils.equalsIgnoreCase(bidProjectDTO.getTypeCode(), "00")) {
            retrunArray.add("請輸入「工程類別」(ADM-0001-W)");
        }
        // (5) 若「工程類別」＝"89"且「其他類別說明」＝空值，警示訊息：「工程類別」選其他工程，請以文字說明屬於何類，俾供增加類別(BID-0071-W)。
        if (StringUtils.equalsIgnoreCase(bidProjectDTO.getTypeCode(), "89") && StringUtils.isBlank(bidProjectDTO.getTpcoth())) {
            retrunArray.add("「工程類別」選其他工程，請以文字說明屬於何類，俾供增加類別(BID-0071-W)");
        }
        // (6) 若「縣巿代碼」＝空值，警示訊息：請輸入「縣巿代碼」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getPlaceCode())) {
            retrunArray.add("請輸入「縣巿代碼」(ADM-0001-W)");
        }
        // (7) 若「施工地點」內含"原住民地區"，警示訊息：「施工地點」請填列完整資料，不要填非原住民地區(BID-0072-W)。
        if (StringUtils.contains(bidProjectDTO.getPlace(), "原住民地區")) {
            retrunArray.add("「施工地點」請填列完整資料，不要填非原住民地區(BID-0072-W)");
        }
        // (8) 若「聯絡電話」＝空值，警示訊息：請輸入「聯絡電話」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.geteMail())) {
            retrunArray.add("請輸入「標案主辦資料-E_Mail信箱」(ADM-0001-W)");
        }
        // (9) 若「E_Mail信箱」＝空值，警示訊息：請輸入「E_Mail信箱」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getContain())) {
            retrunArray.add("請輸入「工程概要」(ADM-0001-W)");
        }
        // (10) 若「工程概要」＝空值，警示訊息：請輸入「工程概要」(ADM-0001-W)。
        if (StringUtils.isBlank(bidProjectDTO.getContain())) {
            retrunArray.add("請輸入「工程概要」(ADM-0001-W)");
        }
        // 基本資料編輯-基本資料1(續)
        // TODO
        // 基本資料編輯-基本資料2


        return retrunArray;
    }
}
