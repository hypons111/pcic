package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.RecProject;
import gov.pcc.pwc.repository.RecProjectRepository;
import gov.pcc.pwc.service.criteria.RecProjectCriteria;
import gov.pcc.pwc.service.criteria.RecProjectQueryCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.mapper.RecProjectMapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing {@link RecProject}.
 */
@Service
@Transactional
public class RecProjectService {

    private final Logger log = LoggerFactory.getLogger(RecProjectService.class);

    private final RecProjectRepository recProjectRepository;

    private final RecProjectMapper recProjectMapper;

    public RecProjectService(RecProjectRepository recProjectRepository, RecProjectMapper recProjectMapper) {
        this.recProjectRepository = recProjectRepository;
        this.recProjectMapper = recProjectMapper;
    }

    //查詢全部縣市下拉清單
    @Transactional(readOnly = true)
    public List<RecSelectOptionsDTO> findAllOrg() {
        log.debug("Request to findAllOrg");
        return recProjectRepository.findAllOrgByQuery();
    }

    //查詢全部颱風下拉清單
    @Transactional(readOnly = true)
    public List<RecSelectOptionsDTO> findAllTyphoon() {
        log.debug("Request to findAllOrg");
        return recProjectRepository.findAllTyphoonByQuery();
    }

    //查詢全部工項下拉清單
    @Transactional(readOnly = true)
    public List<RecSelectOptionsDTO> findAllPrjCode() {
        log.debug("Request to findAllPrjCode");
        return recProjectRepository.findAllProjectCode();
    }

    //查詢災後案件
    @Transactional(readOnly = true)
    public Page<RecProjectQueryDTO> findByCriteria(RecProjectQueryCriteria criteria){
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0){
            criteria.setSortBy(new String[]{"APPROVE_NO"});
        }
        log.debug("Request to findByCriteria");
        final Page<RecProjectQueryDTO> recProjectQueryDTOS = recProjectRepository.findAllByCriteria(criteria);
        return recProjectQueryDTOS;
    }

    //復建工程條件查詢
    @Transactional(readOnly = true)
    public Page<RecProject0303DTO> findByCriteria(RecProjectCriteria criteria){
        // criteria呼叫getSortBy時如果非空值且getSortBy()陣列長度不為0
        if ( criteria.getSortBy() != null && criteria.getSortBy().length == 0 ){
            criteria.setSortBy(new String[] { "PROJECT_NO" });
        }
        final Page<RecProject0303DTO> recProject0303DTOs = recProjectRepository.findAllByCriteria(criteria);
        return recProject0303DTOs;
    }

    //匯出ods檔案
    @Transactional(readOnly = true)
    public void exportOdsByCriteriaAndFile (RecProjectQueryCriteria criteria,String downloadFilePath,String fileName) throws IOException {
        recProjectRepository.exportOdsByCriteria(criteria,downloadFilePath,fileName);
    }

    //匯出kml檔案
    @Transactional(readOnly = true)
    public void exportKmlByCriteriaAndFile (RecProjectQueryCriteria criteria,String downloadFilePath,String fileName) throws IOException {
        recProjectRepository.exportKmlByCriteria(criteria,downloadFilePath,fileName);
    }

    //取出颱風圖表資料
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectChartDTO>> findTyphoonCountAndAmountByCriteria(RecProjectQueryCriteria criteria) {
        return recProjectRepository.findTyphoonCountAndAmount(criteria);
    }

    //取出工程代碼圖表資料
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectChartDTO>> findProjectCodeCountAndAmountByCriteria(RecProjectQueryCriteria criteria) {
        return recProjectRepository.findProjectCodeCountAndAmount(criteria);
    }

    //取出機關名稱圖表資料
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectChartDTO>> findOrgCountAndAmountByCriteria(RecProjectQueryCriteria criteria) {
        return recProjectRepository.findOrgCountAndAmount(criteria);
    }

    //取出個別颱風的機關圖表
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectChartDTO>> findOrgCountAndAmountByTyphoonCriteria(RecProjectQueryCriteria criteria) {
        return recProjectRepository.findOrgCountAndAmountByTyphoon(criteria);
    }

    //取出全部颱風圖表
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectChartDTO>> findAllTyphoonCountAndAmountNoParam() {
        return recProjectRepository.findAllTyphoonCountAndAmount();
    }

    //取出全部颱風圖表By年份
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectChartDTO>> findAllYearTyphoonCountAndAmountNoParam() {
        return recProjectRepository.findAllTyphoonCountAndAmountByYear();
    }

    //取出圖表By縣市
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectMultiChartDTO>> findOrgCountAndAmountByYearAndCriteria(RecProjectQueryCriteria criteria) {
        return recProjectRepository.findOrgCountAndAmountByYear(criteria);
    }

    //取出圖表By原住民鄉鎮市
    @Transactional(readOnly = true)
    public Map<String,List<RecProjectChartDTO>> findCountAndAmountByIndigenousAndCriteria(RecProjectQueryCriteria criteria) {
        return recProjectRepository.findTyphoonCountAndAmountByIndigenous(criteria);
    }

    //查詢逾期未完工災後案件
    @Transactional(readOnly = true)
    public Page<RecProjectQueryDTO> findExpiredByCriteria(RecProjectQueryCriteria criteria){
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0){
            criteria.setSortBy(new String[]{"APPROVE_NO"});
        }
        log.debug("Request to findExpiredByCriteria");
        final Page<RecProjectQueryDTO> recProjectQueryDTOS = recProjectRepository.findAllExpiredByCriteria(criteria,false);
        return recProjectQueryDTOS;
    }

    //匯出逾期已完工災後案件ods
    @Transactional(readOnly = true)
    public void exportOdsByCriteriaAndFileExpired (RecProjectQueryCriteria criteria,String downloadFilePath,String fileName) throws IOException {
        recProjectRepository.exportOdsByCriteriaAndExpired(criteria,downloadFilePath,fileName,false);
    }

    //查詢逾期已完工災後案件
    @Transactional(readOnly = true)
    public Page<RecProjectQueryDTO> findExpiredAndFinishedByCriteria(RecProjectQueryCriteria criteria){
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0){
            criteria.setSortBy(new String[]{"APPROVE_NO"});
        }
        log.debug("Request to findExpiredByCriteria");
        final Page<RecProjectQueryDTO> recProjectQueryDTOS = recProjectRepository.findAllExpiredByCriteria(criteria,true);
        return recProjectQueryDTOS;
    }

    //匯出逾期已完工災後案件ods
    @Transactional(readOnly = true)
    public void exportOdsByCriteriaAndFileExpiredAndFinished (RecProjectQueryCriteria criteria,String downloadFilePath,String fileName) throws IOException {
        recProjectRepository.exportOdsByCriteriaAndExpired(criteria,downloadFilePath,fileName,true);
    }

    //調查規劃案件查詢
    @Transactional(readOnly = true)
    public Page<RecProjectQueryDTO> findResearchAndFinishedByCriteria(RecProjectQueryCriteria criteria){
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0){
            criteria.setSortBy(new String[]{"APPROVE_NO"});
        }
        log.debug("Request to findExpiredByCriteria");
        final Page<RecProjectQueryDTO> recProjectQueryDTOS = recProjectRepository.findAllResearchByCriteria(criteria);
        return recProjectQueryDTOS;
    }

    //匯出逾期已完工災後案件ods
    @Transactional(readOnly = true)
    public void exportOdsByCriteriaAndFileResearch (RecProjectQueryCriteria criteria,String downloadFilePath,String fileName) throws IOException {
        recProjectRepository.exportOdsByCriteriaAndResearch(criteria,downloadFilePath,fileName);
    }

    //調查規劃案件查詢
    @Transactional(readOnly = true)
    public Page<RecProjectQueryDTO> findProjectByCriteria(RecProjectQueryCriteria criteria){
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0){
            criteria.setSortBy(new String[]{"APPROVE_NO"});
        }
        log.debug("Request to findExpiredByCriteria");
        final Page<RecProjectQueryDTO> recProjectQueryDTOS = recProjectRepository.findAllProjectByCriteria(criteria);
        return recProjectQueryDTOS;
    }

    //匯出kml檔案
    @Transactional(readOnly = true)
    public void exportProjectKmlByCriteriaAndFile (RecProjectQueryCriteria criteria,String downloadFilePath,String fileName) throws IOException {
        recProjectRepository.exportProjectKmlByCriteria(criteria,downloadFilePath,fileName);
    }

    //取已存在颱風的颱風
    @Transactional(readOnly = true)
    public List<RecProjectOptionsDTO> findProjectTyphoon(String orgId) {
        return recProjectRepository.findProjectTyphoon(orgId);
    }

    //取已存在復建工程的工程類別
    @Transactional(readOnly = true)
    public List<RecProjectOptionsDTO> findProjectCode(String typhoonNo, String orgId) {
        return recProjectRepository.findProjectCode(typhoonNo,orgId);
    }

    //取已存在復建工程的機關名稱
    @Transactional(readOnly = true)
    public List<RecProjectOptionsDTO> findProjectOrgRole(String typhoonNo, String orgId) {
        return recProjectRepository.findProjectOrgRole(typhoonNo,orgId);
    }

    //取已存在復建工程的鄉(鎮市)
    @Transactional(readOnly = true)
    public List<RecProjectOptionsDTO> findProjectCity(String typhoonNo, String orgId) {
        return recProjectRepository.findProjectCity(typhoonNo,orgId);
    }

    //取已存在復建工程的申請狀況
    @Transactional(readOnly = true)
    public List<RecProjectOptionsDTO> findProjectStatus(String typhoonNo, String orgId) {
        return recProjectRepository.findProjectStatus(typhoonNo,orgId);
    }

    //取構造物主檔的構造物名稱
    @Transactional(readOnly = true)
    public List<RecProjectOptionsDTO> findProjectConstItem() {
        return recProjectRepository.findProjectConstItem();
    }

    //取構造物形式主檔的構造物形式名稱
    @Transactional(readOnly = true)
    public List<RecProjectOptionsDTO> findProjectConstModel(String constItemName) {
        return recProjectRepository.findProjectConstModel(constItemName);
    }

    /**
     * Save a recProject.
     *
     * @param recProjectDTO the entity to save.
     * @return the persisted entity.
     */
    public RecProjectDTO save(RecProjectDTO recProjectDTO) {
        log.debug("Request to save RecProject : {}", recProjectDTO);
        RecProject recProject = recProjectMapper.toEntity(recProjectDTO);
        recProject = recProjectRepository.save(recProject);
        return recProjectMapper.toDto(recProject);
    }

    public RecProjectDTO beforeInsertPackage(RecProjectDTO recProjectDTO) throws TypeVariableNotMatchException {
        recProjectRepository.packPkAndLogColumn(recProjectDTO);
        return  recProjectDTO;
    }

    /**
     * Partially update a recProject.
     *
     * @param recProjectDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecProjectDTO> partialUpdate(RecProjectDTO recProjectDTO) {
        log.debug("Request to partially update RecProject : {}", recProjectDTO);

        return recProjectRepository
            .findById(recProjectDTO.getProjectNo())
            .map(existingRecProject -> {
                recProjectMapper.partialUpdate(existingRecProject, recProjectDTO);

                return existingRecProject;
            })
            .map(recProjectRepository::save)
            .map(recProjectMapper::toDto);
    }

    /**
     * Get all the recProjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecProjectDTO> findAll() {
        log.debug("Request to get all RecProjects");
        return recProjectRepository.findAll().stream().map(recProjectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one recProject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecProjectDTO> findOne(String id) {
        log.debug("Request to get RecProject : {}", id);
        return recProjectRepository.findById(id).map(recProjectMapper::toDto);
    }

    /**
     * Delete the recProject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RecProject : {}", id);
        recProjectRepository.deleteById(id);
    }

}
