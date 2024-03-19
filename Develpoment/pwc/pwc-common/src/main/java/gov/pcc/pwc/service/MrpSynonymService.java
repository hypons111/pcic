package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.MrpSynonym;
import gov.pcc.pwc.repository.MrpSynonymRepository;
import gov.pcc.pwc.service.criteria.MrpSynonymCriteria;
import gov.pcc.pwc.service.dto.MrpSynonymDTO;
import gov.pcc.pwc.service.mapper.MrpSynonymMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Service Implementation for managing {@link MrpSynonym}.
 */
@Service
@Transactional
public class MrpSynonymService {

    private final Logger log = LoggerFactory.getLogger(MrpSynonymService.class);

    private final MrpSynonymRepository mrpSynonymRepository;

    private final MrpSynonymMapper mrpSynonymMapper;

    public MrpSynonymService(MrpSynonymRepository mrpSynonymRepository, MrpSynonymMapper mrpSynonymMapper) {
        this.mrpSynonymRepository = mrpSynonymRepository;
        this.mrpSynonymMapper = mrpSynonymMapper;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryAdmSysCodeA() {
        return mrpSynonymRepository.queryAdmSysCodeA();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpSynonym(String section_name, String data_key_type, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpSynonym(section_name, data_key_type, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> addMrpSynonym(MrpSynonymCriteria mrpSynonymDTO) {
        return mrpSynonymRepository.addMrpSynonym(mrpSynonymDTO);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> updateMrpSynonym(MrpSynonymCriteria mrpSynonymDTO) {
        return mrpSynonymRepository.updateMrpSynonym(mrpSynonymDTO);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> deleteMrpSynonym(MrpSynonymCriteria mrpSynonymDTO) {
        return mrpSynonymRepository.deleteMrpSynonym(mrpSynonymDTO);
    }

    @Transactional(readOnly = true)
    public int queryMrpRecordingSize(String type, String create_sdate, String create_edate, String search_conditions){
        return mrpSynonymRepository.queryMrpRecordingSize(type, create_sdate, create_edate, search_conditions);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpRecording(String type, String create_sdate, String create_edate, String search_conditions, int perPage, int queryArea, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpRecording(type, create_sdate, create_edate, search_conditions, perPage, queryArea, request);
    }

    @Transactional(readOnly = true)
    public void downloadMrp0103R(String type, String create_sdate, String create_edate, String search_conditions, HttpServletRequest request, HttpServletResponse response){
        mrpSynonymRepository.downloadMrp0103R(type, create_sdate, create_edate, search_conditions, request, response);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpRecordingStatistics(String type, String create_sdate, String create_edate, String search_conditions, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpRecordingStatistics(type, create_sdate, create_edate, search_conditions, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpReport(HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpReport(request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpReportDownload(String id, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpReportDownload(id, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpResult(String org, String engineering, String start_date, String end_date, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpResult(org, engineering, start_date, end_date, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpResultDt(String filesource_id, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpResultDt(filesource_id, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryLatestDate(HttpServletRequest request) {
        return mrpSynonymRepository.queryLatestDate(request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpDelta(String start_date, String end_date, String keyword, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpDelta(start_date, end_date, keyword, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpView(String create_date, String code, String award_budget_date, HttpServletRequest request) {
        return mrpSynonymRepository.queryMrpView(create_date, code, award_budget_date, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryArea(HttpServletRequest request) {
        return mrpSynonymRepository.queryArea(request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryCode(String keyword, HttpServletRequest request) {
        return mrpSynonymRepository.queryCode(keyword, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> querySuggestBound(String resourceCode, String dateStart, String dateEnd, String isBudget, String region, String regionName, HttpServletRequest request) {
        return mrpSynonymRepository.querySuggestBound(resourceCode, dateStart, dateEnd, isBudget, region, regionName, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryPrice(String resourceCode, String dateStart, String dateEnd, String isBudget, String region, String regionName, String lowerBound, String upperBound, HttpServletRequest request) {
        return mrpSynonymRepository.queryPrice(resourceCode, dateStart, dateEnd, isBudget, region, regionName, lowerBound, upperBound, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryConPrice(String code_type, String code, String name1, String name2, String name3, String cond1, String cond2, String period, String area, HttpServletRequest request){
        return mrpSynonymRepository.queryConPrice(code_type, code, name1, name2, name3, cond1, cond2, period, area, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryConPriceDetail(String code, String code_name, String period, String unit, HttpServletRequest request){
        return mrpSynonymRepository.queryConPriceDetail(code, code_name, period, unit, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryBasic(String ddl_city, String ddl_town, String ddl_size, String ddl_type, String key_word, String startime, String endtime, String backtime, String account_id, String create_id, String mrpNo, HttpServletRequest request){
        return mrpSynonymRepository.queryBasic(ddl_city, ddl_town, ddl_size, ddl_type, key_word, startime, endtime, backtime, account_id, create_id, mrpNo, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryBasicDetail(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, HttpServletRequest request){
        return mrpSynonymRepository.queryBasicDetail(ddl_city, ddl_size, ddl_type, startime, endtime, backtime, time, code, area, unit, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryBasic108(String ddl_city, String ddl_size, String ddl_type, String key_word, String startime, String endtime, HttpServletRequest request){
        return mrpSynonymRepository.queryBasic108(ddl_city, ddl_size, ddl_type, key_word, startime, endtime, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryBasicDetail108(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, HttpServletRequest request){
        return mrpSynonymRepository.queryBasicDetail108(ddl_city, ddl_size, ddl_type, startime, endtime, backtime, time, code, area, unit, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryBasic109(String ddl_city, String ddl_size, String ddl_type, String key_word, String startime, String endtime, HttpServletRequest request){
        return mrpSynonymRepository.queryBasic109(ddl_city, ddl_size, ddl_type, key_word, startime, endtime, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryBasicDetail109(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, String des_tw, HttpServletRequest request){
        return mrpSynonymRepository.queryBasicDetail109(ddl_city, ddl_size, ddl_type, startime, endtime, backtime, time, code, area, unit, des_tw, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryCommonKeywordbtn(String ACCOUNT_ID, String BTN_NAME, HttpServletRequest request){
        return mrpSynonymRepository.queryCommonKeywordbtn(ACCOUNT_ID, BTN_NAME, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryCommonKeywordDelete(String ACCOUNT_ID, String BTN_NAME, HttpServletRequest request) {
        return mrpSynonymRepository.queryCommonKeywordDelete(ACCOUNT_ID, BTN_NAME, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryCommonKeywordUpdate(String ACCOUNT_ID, String BTN_NAME, String NEW_BTN_NAME, String NEW_KEYWORD_NAME, HttpServletRequest request) {
        return mrpSynonymRepository.queryCommonKeywordUpdate(ACCOUNT_ID, BTN_NAME, NEW_BTN_NAME, NEW_KEYWORD_NAME, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMainInfo(){
        return mrpSynonymRepository.queryMainInfo();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpTown(){
        return mrpSynonymRepository.queryMrpTown();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpCity(){
        return mrpSynonymRepository.queryMrpCity();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryItemCode(){
        return mrpSynonymRepository.queryItemCode();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryAdmSysCode(){
        return mrpSynonymRepository.queryAdmSysCode();
    }

    @Transactional(readOnly = true)
    public String downloadExcel_0121R(String start_date, String end_date){
        return mrpSynonymRepository.downloadExcel_0121R(start_date, end_date);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> mrp0118R(MultipartFile XLS, HttpServletRequest request){
        return mrpSynonymRepository.mrp0118R(XLS, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> mrp0119R(MultipartFile XLS, HttpServletRequest request){
        return mrpSynonymRepository.mrp0119R(XLS, request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpCity120(){
        return mrpSynonymRepository.queryMrpCity120();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMrpTown120(){
        return mrpSynonymRepository.queryMrpTown120();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryMainInfo120(String contractCode, String filesourceId, HttpServletRequest request){
        return mrpSynonymRepository.queryMainInfo120(contractCode, filesourceId, request);
    }

    @Transactional(readOnly = true)
    public void updateContractInfo(MrpSynonymCriteria criteria) {
        mrpSynonymRepository.updateContractInfo(criteria);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> purchase120(String code, String content, HttpServletRequest request, HttpServletResponse response){
        return mrpSynonymRepository.purchase120( code,  content,  request,  response);
    }

    @Transactional(readOnly = true)
    public void addRecord(HttpServletRequest request, String condition, String type, String acc, String action, String ip){
        mrpSynonymRepository.addRecord(request, condition, type, acc, action, ip);
    }

    @Transactional(readOnly = true)
    public String historyPayableDetail(String TENDER_AWARD_TYPE, String TENDER_ORG_NAME, String TENDER_ORG_ID, String TENDER_CASE_NO, String TENDER_NAME, String TENDER_CPC_CODE, String TENDER_BUDGET, String TENDER_PROCUREMENT_RANGE, String TENDER_WAY, String AWARD_WAY, String TENDER_GOV_ESTIMATE, String AWARD_PRICE, String AWARD_PRICE_PUBLIC, String BIDDER_ID, String BIDDER_SUPP_NAME, String TENDER_LIMITED_LAW, String IS_PACKAGE, String IS_MULTIPLE_AWARD, String IS_INTER_ENTITY_SUPPLY, String AWARD_NOTICE_DATE, String AWARD_DATE, String SUPPLIER_AMOUNT, String TENDER_STATUS, String TENDER_CONTACT_PERSON, String TENDER_CONTACT_TEL, String TENDER_SEQ, String BIDDER_AWARD_AMOUNT, String TENDER_IS_LOWEST_LAW, String TENDER_IS_CHG_CONTRACT, String BUDGET_PUBLIC, String TENDER_GOV_EST_PUBLIC, String OID, String AWARD_UPDATE_SEQ, String DATE_OF_OPENING_OF_TENDERS, String DATE_OF_ORIGINAL_PUBLICATION, String AWARD_SEQ, String PK_PMS_MAIN) {
        return mrpSynonymRepository.historyPayableDetail(TENDER_AWARD_TYPE, TENDER_ORG_NAME, TENDER_ORG_ID, TENDER_CASE_NO, TENDER_NAME, TENDER_CPC_CODE, TENDER_BUDGET, TENDER_PROCUREMENT_RANGE,
                TENDER_WAY, AWARD_WAY, TENDER_GOV_ESTIMATE, AWARD_PRICE, AWARD_PRICE_PUBLIC, BIDDER_ID, BIDDER_SUPP_NAME, TENDER_LIMITED_LAW, IS_PACKAGE, IS_MULTIPLE_AWARD, IS_INTER_ENTITY_SUPPLY,
                AWARD_NOTICE_DATE, AWARD_DATE, SUPPLIER_AMOUNT, TENDER_STATUS, TENDER_CONTACT_PERSON, TENDER_CONTACT_TEL, TENDER_SEQ, BIDDER_AWARD_AMOUNT, TENDER_IS_LOWEST_LAW,
                TENDER_IS_CHG_CONTRACT, BUDGET_PUBLIC, TENDER_GOV_EST_PUBLIC, OID, AWARD_UPDATE_SEQ, DATE_OF_OPENING_OF_TENDERS, DATE_OF_ORIGINAL_PUBLICATION, AWARD_SEQ, PK_PMS_MAIN);
    }

    @Transactional(readOnly = true)
    public String getPurchaseByPmsMainion(String PK_PMS_MAIN){
        return mrpSynonymRepository.getPurchaseByPmsMainion(PK_PMS_MAIN);
    }

    public Map<String, Object> uploadFiles120(MultipartFile[] xmls, String project_id, HttpServletRequest request) {
        return mrpSynonymRepository.uploadFiles120(xmls, project_id, request);
    }

    public Map<String, Object> updateWrongReturn(MrpSynonymCriteria criteria) {
        return mrpSynonymRepository.updateWrongReturn(criteria);
    }

    public Map<String, Object> updateCommonKeywordbtn(MrpSynonymCriteria criteria){
        return mrpSynonymRepository.updateCommonKeywordbtn(criteria);
    }

    public Map<String, Object> queryCommonKeywordAdd(String ACCOUNT_ID, HttpServletRequest request){
        return mrpSynonymRepository.queryCommonKeywordAdd(ACCOUNT_ID, request);
    }

    public void downloadFile(MrpSynonymCriteria mrpSynonymDTO) {
        mrpSynonymRepository.downloadFile(mrpSynonymDTO);
    }

    public void excludeVariation(){
        mrpSynonymRepository.excludeVariation();
    }

    public void analysisSemanic(){
        mrpSynonymRepository.analysisSemanic();
    }

    public void analysisKeywords(){
        mrpSynonymRepository.analysisKeywords();
    }

    public void statisticsPrice(){ mrpSynonymRepository.statisticsPrice(); }

    /**
     * Save a mrpSynonym.
     *
     * @param mrpSynonymDTO the entity to save.
     * @return the persisted entity.
     */
    public MrpSynonymDTO save(MrpSynonymDTO mrpSynonymDTO) {
        log.debug("Request to save MrpSynonym : {}", mrpSynonymDTO);
        MrpSynonym mrpSynonym = mrpSynonymMapper.toEntity(mrpSynonymDTO);
        mrpSynonym = mrpSynonymRepository.save(mrpSynonym);
        return mrpSynonymMapper.toDto(mrpSynonym);
    }

    /**
     * Partially update a mrpSynonym.
     *
     * @param mrpSynonymDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MrpSynonymDTO> partialUpdate(MrpSynonymDTO mrpSynonymDTO) {
        log.debug("Request to partially update MrpSynonym : {}", mrpSynonymDTO);

        return mrpSynonymRepository
            .findById(mrpSynonymDTO.getMrpSynonymId())
            .map(existingMrpSynonym -> {
                mrpSynonymMapper.partialUpdate(existingMrpSynonym, mrpSynonymDTO);

                return existingMrpSynonym;
            })
            .map(mrpSynonymRepository::save)
            .map(mrpSynonymMapper::toDto);
    }

    /**
     * Get all the mrpSynonyms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MrpSynonymDTO> findAll() {
        log.debug("Request to get all MrpSynonyms");
        return mrpSynonymRepository.findAll().stream().map(mrpSynonymMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mrpSynonym by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MrpSynonymDTO> findOne(Long id) {
        log.debug("Request to get MrpSynonym : {}", id);
        return mrpSynonymRepository.findById(id).map(mrpSynonymMapper::toDto);
    }

    /**
     * Delete the mrpSynonym by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MrpSynonym : {}", id);
        mrpSynonymRepository.deleteById(id);
    }
}
