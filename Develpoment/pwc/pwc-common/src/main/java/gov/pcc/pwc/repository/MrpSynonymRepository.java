package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.MrpSynonym;
import gov.pcc.pwc.repository.custom.MrpSynonymRepositoryCustom;
import gov.pcc.pwc.service.criteria.MrpSynonymCriteria;
import gov.pcc.pwc.service.dto.MrpSynonymDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Spring Data SQL repository for the MrpSynonym entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MrpSynonymRepository extends JpaRepository<MrpSynonym, Long>, MrpSynonymRepositoryCustom, JpaSpecificationExecutor<MrpSynonym> {
    public Map<String, Object> queryAdmSysCodeA();

    public Map<String, Object> queryMrpSynonym(String section_name, String data_key_type, HttpServletRequest request);

    public Map<String, Object> addMrpSynonym(MrpSynonymCriteria criteria);

    public Map<String, Object> updateMrpSynonym(MrpSynonymCriteria criteria);

    public Map<String, Object> deleteMrpSynonym(MrpSynonymCriteria criteria);

    public int queryMrpRecordingSize(String type, String create_sdate, String create_edate, String search_conditions);

    public Map<String, Object> queryMrpRecording(String type, String create_sdate, String create_edate, String search_conditions, int perPage, int queryArea, HttpServletRequest request);

    public void downloadMrp0103R(String type, String create_sdate, String create_edate, String search_conditions, HttpServletRequest request, HttpServletResponse response);

    public Map<String, Object> queryMrpRecordingStatistics(String type, String create_sdate, String create_edate, String search_conditions, HttpServletRequest request);

    public Map<String, Object> queryMrpReport(HttpServletRequest request);

    public Map<String, Object> queryMrpReportDownload(String id, HttpServletRequest request);

    public Map<String, Object> queryMrpResult(String org, String engineering, String start_date, String end_date, HttpServletRequest request);

    public Map<String, Object> queryMrpResultDt(String filesource_id, HttpServletRequest request);

    public Map<String, Object> queryLatestDate(HttpServletRequest request);

    public Map<String, Object> queryMrpDelta(String start_date, String end_date, String keyword, HttpServletRequest request);

    public Map<String, Object> queryMrpView(String create_date, String code, String award_budget_date, HttpServletRequest request);

    public Map<String, Object> queryArea(HttpServletRequest request);

    public Map<String, Object> queryCode(String keyword, HttpServletRequest request);

    public Map<String, Object> querySuggestBound(String resourceCode, String dateStart, String dateEnd, String isBudget, String region, String regionName, HttpServletRequest request);

    public Map<String, Object> queryPrice(String resourceCode, String dateStart, String dateEnd, String isBudget, String region, String regionName, String lowerBound, String upperBound, HttpServletRequest request);

    public Map<String, Object> queryConPrice(String code_type, String code, String name1, String name2, String name3, String cond1, String cond2, String period, String area, HttpServletRequest request);

    public Map<String, Object> queryConPriceDetail(String code, String code_name, String period, String unit, HttpServletRequest request);

    public Map<String, Object> queryBasic(String ddl_city, String ddl_town, String ddl_size, String ddl_type, String key_word, String startime, String endtime, String backtime, String account_id, String create_id, String mrpNo, HttpServletRequest request);

    public Map<String, Object> queryBasicDetail(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, HttpServletRequest request);

    public Map<String, Object> queryBasic108(String ddl_city, String ddl_size, String ddl_type, String key_word, String startime, String endtime, HttpServletRequest request);

    public Map<String, Object> queryBasicDetail108(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, HttpServletRequest request);

    public Map<String, Object> queryBasic109(String ddl_city, String ddl_size, String ddl_type, String key_word, String startime, String endtime, HttpServletRequest request);

    public Map<String, Object> queryBasicDetail109(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, String des_tw, HttpServletRequest request);

    public Map<String, Object> queryCommonKeywordbtn(String ACCOUNT_ID, String BTN_NAME, HttpServletRequest request);

    public Map<String, Object> queryCommonKeywordDelete(String ACCOUNT_ID, String BTN_NAME, HttpServletRequest request);

    public Map<String, Object> queryCommonKeywordUpdate(String ACCOUNT_ID, String BTN_NAME, String NEW_BTN_NAME, String NEW_KEYWORD_NAME, HttpServletRequest request);

    public String historyPayableDetail(String TENDER_AWARD_TYPE, String TENDER_ORG_NAME, String TENDER_ORG_ID, String TENDER_CASE_NO, String TENDER_NAME, String TENDER_CPC_CODE, String TENDER_BUDGET, String TENDER_PROCUREMENT_RANGE, String TENDER_WAY, String AWARD_WAY, String TENDER_GOV_ESTIMATE, String AWARD_PRICE, String AWARD_PRICE_PUBLIC, String BIDDER_ID, String BIDDER_SUPP_NAME, String TENDER_LIMITED_LAW, String IS_PACKAGE, String IS_MULTIPLE_AWARD, String IS_INTER_ENTITY_SUPPLY, String AWARD_NOTICE_DATE, String AWARD_DATE, String SUPPLIER_AMOUNT, String TENDER_STATUS, String TENDER_CONTACT_PERSON, String TENDER_CONTACT_TEL, String TENDER_SEQ, String BIDDER_AWARD_AMOUNT, String TENDER_IS_LOWEST_LAW, String TENDER_IS_CHG_CONTRACT, String BUDGET_PUBLIC, String TENDER_GOV_EST_PUBLIC, String OID, String AWARD_UPDATE_SEQ, String DATE_OF_OPENING_OF_TENDERS, String DATE_OF_ORIGINAL_PUBLICATION, String AWARD_SEQ, String PK_PMS_MAIN);

    public String getPurchaseByPmsMainion(String PK_PMS_MAIN);

    public Map<String, Object> purchase120(String code, String content, HttpServletRequest request, HttpServletResponse response);

    public Map<String, Object> queryMainInfo();

    public Map<String, Object> queryMrpTown();

    public Map<String, Object> queryMrpCity();

    public Map<String, Object> queryItemCode();

    public Map<String, Object> queryAdmSysCode();

    public String downloadExcel_0121R(String start_date, String end_date);

    public Map<String, Object> mrp0118R(MultipartFile XLS, HttpServletRequest request);

    public Map<String, Object> mrp0119R(MultipartFile XLS, HttpServletRequest request);

    public Map<String, Object> queryMrpCity120();

    public Map<String, Object> queryMrpTown120();

    public Map<String, Object> queryMainInfo120(String contractCode, String filesourceId, HttpServletRequest request);
    
	public void updateContractInfo(MrpSynonymCriteria criteria);

    public void addRecord(HttpServletRequest request, String condition, String type, String acc, String action, String ip);

    public Map<String, Object> uploadFiles120(MultipartFile[] xmls, String project_id, HttpServletRequest request);

    public Map<String, Object> updateWrongReturn(MrpSynonymCriteria criteria);

    public Map<String, Object> updateCommonKeywordbtn(MrpSynonymCriteria criteria);

    public Map<String, Object> queryCommonKeywordAdd(String ACCOUNT_ID, HttpServletRequest request);

    public void downloadFile(MrpSynonymCriteria criteria);

    public void excludeVariation();

    public void analysisSemanic();

    public void analysisKeywords();

    public void statisticsPrice();
}
