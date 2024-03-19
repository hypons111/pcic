package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.MrpSynonymRepository;
import gov.pcc.pwc.service.MrpSynonymService;
import gov.pcc.pwc.service.criteria.MrpSynonymCriteria;
import gov.pcc.pwc.service.dto.MrpSynonymDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.utils.PropUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.MrpSynonym}.
 */
@RestController
@RequestMapping("/api")
public class MrpSynonymResource {

    private final Logger log = LoggerFactory.getLogger(MrpSynonymResource.class);

    private static final String ENTITY_NAME = "mrpSynonym";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MrpSynonymService mrpSynonymService;

    private final MrpSynonymRepository mrpSynonymRepository;

    public MrpSynonymResource(MrpSynonymService mrpSynonymService, MrpSynonymRepository mrpSynonymRepository) {
        this.mrpSynonymService = mrpSynonymService;
        this.mrpSynonymRepository = mrpSynonymRepository;
    }

    @GetMapping("/service/MRP0102R/queryAdmSysCodeA")
    public Map<String, Object> queryAdmSysCodeA() {
        log.debug("Rest queryAdmSysCodeA");
        return mrpSynonymService.queryAdmSysCodeA();
    }

    @GetMapping("/service/MRP0102R/queryMrpSynonym")
    public Map<String, Object> queryMrpSynonym(@RequestParam("sectionName") String section_name, @RequestParam("dataKeyType") String data_key_type, HttpServletRequest request) {
        log.debug("Rest queryMrpSynonym");
        return mrpSynonymService.queryMrpSynonym(section_name, data_key_type, request);
    }

    @PostMapping("/service/MRP0102R/addMrpSynonym")
    public Map<String, Object> addMrpSynonym(@Valid @RequestBody MrpSynonymCriteria criteria) {
        log.debug("Rest addMrpSynonym");
        return mrpSynonymService.addMrpSynonym(criteria);
    }

    @PostMapping("/service/MRP0102R/updateMrpSynonym")
    public Map<String, Object> updateMrpSynonym(@Valid @RequestBody MrpSynonymCriteria criteria) {
        log.debug("Rest updateMrpSynonym");
        return mrpSynonymService.updateMrpSynonym(criteria);
    }

    @PostMapping("/service/MRP0102R/deleteMrpSynonym")
    public Map<String, Object> deleteMrpSynonym(@Valid @RequestBody MrpSynonymCriteria criteria) {
        log.debug("Rest deleteMrpSynonym");
        return mrpSynonymService.deleteMrpSynonym(criteria);
    }

    @PostMapping("/service/MRP0102R/downloadFile")
    public void downloadFile(@Valid @RequestBody MrpSynonymCriteria criteria, HttpServletResponse response) {
        log.debug("Rest downloadFile");
//        mrpSynonymService.downloadFile(criteria);
        try {
            String section_name = criteria.getSectionName();
            String downloadFilePath = //"/stsdat/pwc/mrp/data/ref_FILE/"
                        PropUtil.getPropVal("mrp.data.path") + section_name + "/Original.xls";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
            File pdf = new File(downloadFilePath);
//            Path filePath = pdf.toPath();
//            Resource resource = new UrlResource(filePath.toUri());
//            return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("application/x-download"))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + section_name + "_Original.xls")
//                .body(resource);
            response.setHeader("Content-Disposition", "attachment; filename=" + section_name + "_Original.xls");
            System.out.println("HAS PDF FILE: " + pdf.exists());
            if (pdf.exists()) {
                InputStream fis = new FileInputStream(pdf);
                IOUtils.copy(fis, response.getOutputStream());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//        return null;
    }

    @GetMapping("/service/MRP0103R/queryMrpRecordingSize")
    public Map<String, Object> queryMrpRecordingSize(@RequestParam("type") String type, @RequestParam("create_sdate") String create_sdate, @RequestParam("create_edate") String create_edate, @RequestParam("search_conditions") String search_conditions){
        Map<String, Object> rs = new HashMap<String, Object>();
        int size = mrpSynonymService.queryMrpRecordingSize(type, create_sdate, create_edate, search_conditions);
        rs.put("size", size);
        return rs;
    }

    @GetMapping("/service/MRP0103R/queryMrpRecording")
    public Map<String, Object> queryMrpRecording(@RequestParam("type") String type, @RequestParam("create_sdate") String create_sdate, @RequestParam("create_edate") String create_edate, @RequestParam("search_conditions") String search_conditions, @RequestParam("perPage") int perPage, @RequestParam("queryArea") int queryArea, HttpServletRequest request) {
        log.debug("Rest queryMrpRecording");
        return mrpSynonymService.queryMrpRecording(type, create_sdate, create_edate, search_conditions, perPage, queryArea, request);
    }

    @GetMapping("/service/MRP0103R/downloadMrp0103R")
    public void downloadMrp0103R(@RequestParam("type") String type, @RequestParam("create_sdate") String create_sdate, @RequestParam("create_edate") String create_edate, @RequestParam("search_conditions") String search_conditions, HttpServletRequest request, HttpServletResponse response){
        log.debug("Rest downloadMrp0103R");
        mrpSynonymService.downloadMrp0103R(type, create_sdate, create_edate, search_conditions, request, response);
    }

    @GetMapping("/service/MRP0103R/queryMrpRecordingStatistics")
    public Map<String, Object> queryMrpRecordingStatistics(@RequestParam("type") String type, @RequestParam("create_sdate") String create_sdate, @RequestParam("create_edate") String create_edate, @RequestParam("search_conditions") String search_conditions, HttpServletRequest request) {
        log.debug("Rest queryMrpRecordingStatistics");
        return mrpSynonymService.queryMrpRecordingStatistics(type, create_sdate, create_edate, search_conditions, request);
    }

    @GetMapping("/service/MRP0501R/queryMrpReport")
    public Map<String, Object> queryMrpReport(HttpServletRequest request) {
        log.debug("Rest queryMrpReport");
        return mrpSynonymService.queryMrpReport(request);
    }

    @GetMapping("/service/MRP0501R/queryMrpReportDownload")
    public Map<String, Object> queryMrpReportDownload(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Rest queryMrpReportDownload");
        return mrpSynonymService.queryMrpReportDownload(id, request);
    }

    @GetMapping("/service/MRP0501R/downloadMrpReport")
    public void downloadMrpReport(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Rest downloadMrpReport");
        try {
            File pdf = new File(fileName);
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName.substring(fileName.lastIndexOf("\\")+1));
            if (pdf.exists()) {
                InputStream fis = new FileInputStream(pdf);
                IOUtils.copy(fis, response.getOutputStream());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/service/MRP0301R/queryMrpResult")
    public Map<String, Object> queryMrpResult(@RequestParam("org") String org, @RequestParam("engineering") String engineering, @RequestParam("start_date") String start_date, @RequestParam("end_date") String end_date, HttpServletRequest request) {
        log.debug("Rest queryMrpResult");
        return mrpSynonymService.queryMrpResult(org, engineering, start_date, end_date, request);
    }


    @GetMapping("/service/MRP0301R/queryMrpResultDt")
    public Map<String, Object> queryMrpResultDt(@RequestParam("filesource_id") String filesource_id, HttpServletRequest request) {
        log.debug("Rest queryMrpResultDt");
        return mrpSynonymService.queryMrpResultDt(filesource_id, request);
    }

    @GetMapping("/service/MRP0105R/queryLatestDate")
    public Map<String, Object> queryLatestDate(HttpServletRequest request) {
        log.debug("Rest queryLatestDate");
        return mrpSynonymService.queryLatestDate(request);
    }

    @GetMapping("/service/MRP0105R/queryMrpDelta")
    public Map<String, Object> queryMrpDelta(@RequestParam("start_date") String start_date, @RequestParam("end_date") String end_date, @RequestParam("keyword") String keyword, HttpServletRequest request) {
        log.debug("Rest queryMrpDelta");
        return mrpSynonymService.queryMrpDelta(start_date, end_date, keyword, request);
    }

    @GetMapping("/service/MRP0105R/queryMrpView")
    public Map<String, Object> queryMrpView(@RequestParam("create_date") String create_date, @RequestParam("code") String code, @RequestParam("award_budget_date") String award_budget_date, HttpServletRequest request) {
        log.debug("Rest queryMrpView");
        return mrpSynonymService.queryMrpView(create_date, code, award_budget_date, request);
    }
/*
    $$$ 106的city pull down menu 也要有 北中南東部地區, 所以改成用 mrpSynonymService.queryMrpCity();
    @GetMapping("/service/MRP0106R/queryArea")
    public Map<String, Object> queryArea(HttpServletRequest request) {
        log.debug("Rest queryArea");
        return mrpSynonymService.queryArea(request);
    }
*/
    @GetMapping("/service/MRP0106R/queryCode")
    public Map<String, Object> queryCode(@RequestParam("keyword") String keyword, HttpServletRequest request) {
        log.debug("Rest queryCode");
        return mrpSynonymService.queryCode(keyword, request);
    }

    @GetMapping("/service/MRP0106R/querySuggestBound")
    public Map<String, Object> querySuggestBound(@RequestParam("resourceCode") String resourceCode, @RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd,
                                                 @RequestParam("isBudget") String isBudget, @RequestParam("region") String region, @RequestParam("regionName") String regionName, HttpServletRequest request) {
        log.debug("Rest querySuggestBound");
        return mrpSynonymService.querySuggestBound(resourceCode, dateStart, dateEnd, isBudget, region, regionName, request);
    }

    @GetMapping("/service/MRP0106R/queryPrice")
    public Map<String, Object> queryPrice(@RequestParam("resourceCode") String resourceCode, @RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, @RequestParam("isBudget") String isBudget,
                                          @RequestParam("region") String region, @RequestParam("regionName") String regionName, @RequestParam("lowerBound") String lowerBound, @RequestParam("upperBound") String upperBound, HttpServletRequest request) {
        log.debug("Rest queryPrice");
        return mrpSynonymService.queryPrice(resourceCode, dateStart, dateEnd, isBudget, region, regionName, lowerBound, upperBound, request);
    }

    @GetMapping("/service/MRP0202R/queryConPrice")
    public Map<String, Object> queryConPrice(@RequestParam("code_type") String code_type, @RequestParam("code") String code, @RequestParam("name1") String name1, @RequestParam("name2") String name2, @RequestParam("name3") String name3,
                                             @RequestParam("cond1") String cond1, @RequestParam("cond2") String cond2, @RequestParam("period") String period, @RequestParam("area") String area, HttpServletRequest request) {
        log.debug("Rest queryConPrice");
        return mrpSynonymService.queryConPrice(code_type, code, name1, name2, name3, cond1, cond2, period, area, request);
    }

    @GetMapping("/service/MRP0202R/queryConPriceDetail")
    public Map<String, Object> queryConPriceDetail(@RequestParam("code") String code, @RequestParam("code_name") String code_name, @RequestParam("period") String period, @RequestParam("unit") String unit, HttpServletRequest request) {
        log.debug("Rest queryConPriceDetail");
        return mrpSynonymService.queryConPriceDetail(code, code_name, period, unit, request);
    }

    @GetMapping("/service/MRP0121R/downloadExcel")
    public ResponseEntity downloadExcel_0121R(@RequestParam("start_date") String start_date,@RequestParam("end_date") String end_date, HttpServletRequest request) {
        log.debug("Rest downloadExcel_0121R");
        try {
            String filename =  mrpSynonymService.downloadExcel_0121R(start_date, end_date);
            String downloadFilePath = PropUtil.getPropVal("mrp.file.savepath") + filename;
            File pdf = new File(downloadFilePath);
            Path filePath = pdf.toPath();
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-download"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .body(resource);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/service/MRP0107R/queryBasic")
    public Map<String, Object> queryBasic(@RequestParam("ddl_city") String ddl_city, @RequestParam("ddl_size") String ddl_size, @RequestParam("ddl_type") String ddl_type, @RequestParam("key_word") String key_word,
                                          @RequestParam("startime") String startime, @RequestParam("endtime") String endtime, @RequestParam("backtime") String backtime, @RequestParam("account_id") String account_id, @RequestParam("create_id") String create_id, @RequestParam("mrpNo") String mrpNo, HttpServletRequest request) {
        log.debug("Rest queryBasic");
        return mrpSynonymService.queryBasic(ddl_city, "", ddl_size, ddl_type, key_word, startime, endtime, backtime, account_id, create_id, mrpNo, request);
    }

    @GetMapping("/service/MRP0107R/queryBasicDetail")
    public Map<String, Object> queryBasicDetail(@RequestParam("ddl_city") String ddl_city, @RequestParam("ddl_size") String ddl_size, @RequestParam("ddl_type") String ddl_type, @RequestParam("startime") String startime,
                                                @RequestParam("endtime") String endtime, @RequestParam("backtime") String backtime, @RequestParam("time") String time, @RequestParam("code") String code,
                                                @RequestParam("area") String area, @RequestParam("unit") String unit, HttpServletRequest request) {
        log.debug("Rest queryBasicDetail");
        return mrpSynonymService.queryBasicDetail(ddl_city, ddl_size, ddl_type, startime, endtime, backtime, time, code, area, unit, request);
    }

    @GetMapping({"/service/MRP0101R/downloadExcel", "/service/MRP0105R/downloadExcel", "/service/MRP0107R/downloadExcel", "/service/MRP0108R/downloadExcel", "/service/MRP0109R/downloadExcel", "/service/MRP0201R/downloadExcel"})
    public ResponseEntity downloadExcelFile(@RequestParam("fileName") String fileName, @RequestParam(value="acc", required=false) String acc, @RequestParam(value="ip", required=false) String ip, HttpServletRequest request) {
        log.debug("Rest downloadExcel");
        try {
            String downloadFilePath = PropUtil.getPropVal("mrp.file.savepath") + fileName;//"/stsdat/pwc/mrp/" + fileName;  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
			//add record
            mrpSynonymService.addRecord(request, "", "member", "", "下載", ip);//id & type(member & passer) 取得方式? // $$$
            File pdf = new File(downloadFilePath);
            Path filePath = pdf.toPath();
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-download"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/service/MRP0108R/queryBasic")
    public Map<String, Object> queryBasic108(@RequestParam("ddl_city") String ddl_city, @RequestParam("ddl_size") String ddl_size, @RequestParam("ddl_type") String ddl_type, @RequestParam("key_word") String key_word, @RequestParam("startime") String startime,
                                             @RequestParam("endtime") String endtime, HttpServletRequest request) {
        log.debug("Rest queryBasic109");
        return mrpSynonymService.queryBasic108(ddl_city, ddl_size, ddl_type, key_word, startime, endtime, request);
    }

    @GetMapping("/service/MRP0108R/queryBasicDetail")
    public Map<String, Object> queryBasicDetail108(@RequestParam("ddl_city") String ddl_city, @RequestParam("ddl_size") String ddl_size, @RequestParam("ddl_type") String ddl_type, @RequestParam("startime") String startime,
                                                   @RequestParam("endtime") String endtime, @RequestParam("backtime") String backtime, @RequestParam("time") String time, @RequestParam("code") String code,
                                                   @RequestParam("area") String area, @RequestParam("unit") String unit, HttpServletRequest request) {
        log.debug("Rest queryBasicDetail108");
        return mrpSynonymService.queryBasicDetail108(ddl_city, ddl_size, ddl_type, startime, endtime, backtime, time, code, area, unit, request);
    }


    @GetMapping("/service/MRP0109R/queryBasic")
    public Map<String, Object> queryBasic109(@RequestParam("ddl_city") String ddl_city, @RequestParam("ddl_size") String ddl_size, @RequestParam("ddl_type") String ddl_type, @RequestParam("key_word") String key_word, @RequestParam("startime") String startime,
                                             @RequestParam("endtime") String endtime, HttpServletRequest request) {
        log.debug("Rest queryBasic109");
        return mrpSynonymService.queryBasic109(ddl_city, ddl_size, ddl_type, key_word, startime, endtime, request);
    }

    @GetMapping("/service/MRP0109R/queryBasicDetail")
    public Map<String, Object> queryBasicDetail109(@RequestParam("ddl_city") String ddl_city, @RequestParam("ddl_size") String ddl_size, @RequestParam("ddl_type") String ddl_type, @RequestParam("startime") String startime,
                                                   @RequestParam("endtime") String endtime, @RequestParam("backtime") String backtime, @RequestParam("time") String time, @RequestParam("code") String code,
                                                   @RequestParam("area") String area, @RequestParam("unit") String unit, @RequestParam("des_tw") String des_tw, HttpServletRequest request) {
        log.debug("Rest queryBasicDetail109");
        return mrpSynonymService.queryBasicDetail109(ddl_city, ddl_size, ddl_type, startime, endtime, backtime, time, code, area, unit, des_tw, request);
    }

    @GetMapping({"/service/MRP0201R/queryMainInfo", "/service/MRP0107R/queryMainInfo"})
    public Map<String, Object> queryMainInfo() {
        log.debug("Rest queryMainInfo");
        return mrpSynonymService.queryMainInfo();
    }

    @GetMapping("/service/MRP0201R/queryMrpTown")
    public Map<String, Object> queryMrpTown() {
        log.debug("Rest queryMrpTown");
        return mrpSynonymService.queryMrpTown();
    }

	// $$$ 加了"/service/MRP0106R/queryMrpCity"
    @GetMapping({"/service/MRP0201R/queryMrpCity","/service/MRP0101R/queryMrpCity","/service/MRP0106R/queryMrpCity","/service/MRP0107R/queryMrpCity","/service/MRP0108R/queryMrpCity","/service/MRP0109R/queryMrpCity"})
    public Map<String, Object> queryMrpCity() {
        log.debug("Rest queryMrpCity");
        return mrpSynonymService.queryMrpCity();
    }

    @GetMapping({"/service/MRP0201R/queryItemCode","/service/MRP0101R/queryItemCode","/service/MRP0107R/queryItemCode","/service/MRP0108R/queryItemCode","/service/MRP0109R/queryItemCode"})
    public Map<String, Object> queryItemCode() {
        log.debug("Rest queryItemCode");
        return mrpSynonymService.queryItemCode();
    }

    @GetMapping({"/service/MRP0201R/queryAdmSysCode","/service/MRP0101R/queryAdmSysCode","/service/MRP0107R/queryAdmSysCode","/service/MRP0108R/queryAdmSysCode","/service/MRP0109R/queryAdmSysCode"})
    public Map<String, Object> queryAdmSysCode() {
        log.debug("Rest queryAdmSysCode");
        return mrpSynonymService.queryAdmSysCode();
    }

    @GetMapping({"/service/MRP0201R/queryBasic","/service/MRP0101R/queryBasic"})
    public Map<String, Object> queryBasic201(@RequestParam("city_id") String city_id, @RequestParam("town_id") String town_id, @RequestParam("pro_scale") String pro_scale, @RequestParam("pro_type") String pro_type,
                                             @RequestParam("key_word") String key_word, @RequestParam("startmonth") String startmonth, @RequestParam("endmonth") String endmonth, @RequestParam("account_id") String account_id, @RequestParam("create_id") String create_id, @RequestParam("mrpNo") String mrpNo, HttpServletRequest request) {
        log.debug("Rest queryBasic201");
        return mrpSynonymService.queryBasic(city_id, town_id, pro_scale, pro_type, key_word, startmonth, endmonth, null, account_id, create_id, mrpNo, request);
    }

    @PostMapping("/service/MRP0118R")
    public Map<String, Object> mrp0118R(@RequestParam("XLS_file") MultipartFile XLS, HttpServletRequest request) {
        log.debug("Rest mrp0118R");
        return mrpSynonymService.mrp0118R(XLS, request);
    }

    @PostMapping("/service/MRP0119R")
    public Map<String, Object> mrp0119R(@RequestParam("XLS_file") MultipartFile XLS, HttpServletRequest request) {
        log.debug("Rest mrp0119R");
        return mrpSynonymService.mrp0119R(XLS, request);
    }

    // 120 縣巿
    @GetMapping("/service/MRP0120R/queryMrpCity")
    public Map<String, Object> queryMrpCity120() {
        log.debug("Rest queryMrpCity120");
        return mrpSynonymService.queryMrpCity120();
    }

    // 120 鄉鎮
    @GetMapping("/service/MRP0120R/queryMrpTown")
    public Map<String, Object> queryMrpTown120() {
        log.debug("Rest queryMrpTown120");
        return mrpSynonymService.queryMrpTown120();
    }

    // 120 MainInfo
    @GetMapping("/service/MRP0120R/queryMainInfo")
    public Map<String, Object> queryMainInfo120(@RequestParam("contractCode") String contractCode, @RequestParam("filesourceId") String filesourceId, HttpServletRequest request) {
        log.debug("Rest queryMainInfo120");
        return mrpSynonymService.queryMainInfo120(contractCode, filesourceId, request);
    }

    // 120 步驟一填寫的資料
    @PostMapping("/service/MRP0120R/updateContractInfo")
    public void updateContractInfo(@Valid @RequestBody MrpSynonymCriteria criteria) {
        log.debug("Rest updateContractInfo");
        mrpSynonymService.updateContractInfo(criteria);
    }

    // 120 步驟一上傳的 xml
    @PostMapping("/service/MRP0120R/uploadFiles")
    public Map<String, Object> uploadFiles120(@RequestParam("xml_files") MultipartFile[] xmls, @RequestParam("project_id") String project_id, HttpServletRequest request) {
        log.debug("Rest uploadFiles120");
        return mrpSynonymService.uploadFiles120(xmls, project_id, request);
    }

    // 中華電訊用
    @GetMapping("/service/MRP0120R/purchase")
    public Map<String, Object> purchase120(@RequestParam("code") String code, @RequestParam("content") String content, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Rest purchase120");
        return mrpSynonymService.purchase120(code, content, request, response);
    }

    @PostMapping("/service/MRP0101R/updateWrongReturn")
    public Map<String, Object> updateWrongReturn(@Valid @RequestBody MrpSynonymCriteria criteria) {
        log.debug("Rest updateWrongReturn");
        return mrpSynonymService.updateWrongReturn(criteria);
    }


    @GetMapping("/service/MRP0101R/queryCommonKeywordAdd")
    public Map<String, Object> queryCommonKeywordAdd(@RequestParam("account_id") String ACCOUNT_ID, HttpServletRequest request) {
        log.debug("Rest queryCommonKeywordAdd");
        return mrpSynonymService.queryCommonKeywordAdd(ACCOUNT_ID, request);
    }

    // 使用集合查詢
    @GetMapping("/service/MRP0101R/queryCommonKeywordbtn")
    public Map<String, Object> queryCommonKeywordbtn(@RequestParam("account_id") String ACCOUNT_ID, @RequestParam("btn_name") String BTN_NAME, HttpServletRequest request) {
        log.debug("Rest queryCommonKeywordbtn");
        return mrpSynonymService.queryCommonKeywordbtn(ACCOUNT_ID, BTN_NAME, request);
    }

    // 新增集合
    @PostMapping("/service/MRP0101R/updateCommonKeywordbtn")
    public Map<String, Object> updateCommonKeywordbtn(@Valid @RequestBody MrpSynonymCriteria criteria) {
        log.debug("Rest updateCommonKeywordbtn");
        return mrpSynonymService.updateCommonKeywordbtn(criteria);
    }

    // 刪除集合
    @GetMapping("/service/MRP0101R/queryCommonKeywordDelete")
    public Map<String, Object> queryCommonKeywordDelete(@RequestParam("account_id") String ACCOUNT_ID, @RequestParam("btn_name") String BTN_NAME, HttpServletRequest request) {
        log.debug("Rest queryCommonKeywordDelete");
        return mrpSynonymService.queryCommonKeywordDelete(ACCOUNT_ID, BTN_NAME, request);
    }

    // 修改集合
    @GetMapping("/service/MRP0101R/queryCommonKeywordUpdate")
    public Map<String, Object> queryCommonKeywordUpdate(@RequestParam("account_id") String ACCOUNT_ID, @RequestParam("btn_name") String BTN_NAME, @RequestParam("new_btn_name") String NEW_BTN_NAME, @RequestParam("new_keyword_name") String NEW_KEYWORD_NAME, HttpServletRequest request) {
        log.debug("Rest queryCommonKeywordUpdate");
        return mrpSynonymService.queryCommonKeywordUpdate(ACCOUNT_ID, BTN_NAME, NEW_BTN_NAME, NEW_KEYWORD_NAME, request);
    }

//    @GetMapping("/service/MRP0801R/historyPayableDetail")
//    public String historyPayableDetail(@RequestParam("TENDER_AWARD_TYPE") String TENDER_AWARD_TYPE, @RequestParam("TENDER_ORG_NAME") String TENDER_ORG_NAME, @RequestParam("TENDER_ORG_ID") String TENDER_ORG_ID,
//                                       @RequestParam("TENDER_CASE_NO") String TENDER_CASE_NO, @RequestParam("TENDER_NAME") String TENDER_NAME, @RequestParam("TENDER_CPC_CODE") String TENDER_CPC_CODE, @RequestParam("TENDER_BUDGET") String TENDER_BUDGET,
//                                       @RequestParam("TENDER_PROCUREMENT_RANGE") String TENDER_PROCUREMENT_RANGE, @RequestParam("TENDER_WAY") String TENDER_WAY, @RequestParam("AWARD_WAY") String AWARD_WAY, @RequestParam("TENDER_GOV_ESTIMATE") String TENDER_GOV_ESTIMATE,
//                                       @RequestParam("AWARD_PRICE") String AWARD_PRICE, @RequestParam("AWARD_PRICE_PUBLIC") String AWARD_PRICE_PUBLIC, @RequestParam("BIDDER_ID") String BIDDER_ID, @RequestParam("BIDDER_SUPP_NAME") String BIDDER_SUPP_NAME,
//                                       @RequestParam("TENDER_LIMITED_LAW") String TENDER_LIMITED_LAW, @RequestParam("IS_PACKAGE") String IS_PACKAGE, @RequestParam("IS_MULTIPLE_AWARD") String IS_MULTIPLE_AWARD, @RequestParam("IS_INTER_ENTITY_SUPPLY") String IS_INTER_ENTITY_SUPPLY,
//                                       @RequestParam("AWARD_NOTICE_DATE") String AWARD_NOTICE_DATE, @RequestParam("AWARD_DATE") String AWARD_DATE, @RequestParam("SUPPLIER_AMOUNT") String SUPPLIER_AMOUNT, @RequestParam("TENDER_STATUS") String TENDER_STATUS,
//                                       @RequestParam("TENDER_CONTACT_PERSON") String TENDER_CONTACT_PERSON, @RequestParam("TENDER_CONTACT_TEL") String TENDER_CONTACT_TEL, @RequestParam("TENDER_SEQ") String TENDER_SEQ, @RequestParam("BIDDER_AWARD_AMOUNT") String BIDDER_AWARD_AMOUNT,
//                                       @RequestParam("TENDER_IS_LOWEST_LAW") String TENDER_IS_LOWEST_LAW, @RequestParam("TENDER_IS_CHG_CONTRACT") String TENDER_IS_CHG_CONTRACT, @RequestParam("BUDGET_PUBLIC") String BUDGET_PUBLIC, @RequestParam("TENDER_GOV_EST_PUBLIC") String TENDER_GOV_EST_PUBLIC,
//                                       @RequestParam("OID") String OID, @RequestParam("ACCOUNT_ID") String AWARD_UPDATE_SEQ, @RequestParam("DATE_OF_OPENING_OF_TENDERS") String DATE_OF_OPENING_OF_TENDERS,
//                                       @RequestParam("DATE_OF_ORIGINAL_PUBLICATION") String DATE_OF_ORIGINAL_PUBLICATION, @RequestParam("AWARD_SEQ") String AWARD_SEQ, @RequestParam("PK_PMS_MAIN") String PK_PMS_MAIN) {
//        log.debug("Rest historyPayableDetail");
//        return mrpSynonymService.historyPayableDetail(TENDER_AWARD_TYPE, TENDER_ORG_NAME, TENDER_ORG_ID, TENDER_CASE_NO, TENDER_NAME, TENDER_CPC_CODE, TENDER_BUDGET, TENDER_PROCUREMENT_RANGE,
//            TENDER_WAY, AWARD_WAY, TENDER_GOV_ESTIMATE, AWARD_PRICE, AWARD_PRICE_PUBLIC, BIDDER_ID, BIDDER_SUPP_NAME, TENDER_LIMITED_LAW, IS_PACKAGE, IS_MULTIPLE_AWARD, IS_INTER_ENTITY_SUPPLY,
//            AWARD_NOTICE_DATE, AWARD_DATE, SUPPLIER_AMOUNT, TENDER_STATUS, TENDER_CONTACT_PERSON, TENDER_CONTACT_TEL, TENDER_SEQ, BIDDER_AWARD_AMOUNT, TENDER_IS_LOWEST_LAW,
//            TENDER_IS_CHG_CONTRACT, BUDGET_PUBLIC, TENDER_GOV_EST_PUBLIC, OID, AWARD_UPDATE_SEQ, DATE_OF_OPENING_OF_TENDERS, DATE_OF_ORIGINAL_PUBLICATION, AWARD_SEQ, PK_PMS_MAIN);
//    }
//
//    @GetMapping("/service/MRP0801R/getPurchaseByPmsMainion")
//    public String getPurchaseByPmsMainion(@RequestParam("PK_PMS_MAIN") String PK_PMS_MAIN) {
//        log.debug("Rest getPurchaseByPmsMainion");
//        return mrpSynonymService.getPurchaseByPmsMainion(PK_PMS_MAIN);
//    }




    /**
     * {@code POST  /mrp-synonyms} : Create a new mrpSynonym.
     *
     * @param mrpSynonymDTO the mrpSynonymDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mrpSynonymDTO, or with status {@code 400 (Bad Request)} if the mrpSynonym has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mrp-synonyms")
    public ResponseEntity<MrpSynonymDTO> createMrpSynonym(@Valid @RequestBody MrpSynonymDTO mrpSynonymDTO) throws URISyntaxException {
        log.debug("REST request to save MrpSynonym : {}", mrpSynonymDTO);
        MrpSynonymDTO result = mrpSynonymService.save(mrpSynonymDTO);
        return ResponseEntity
            .created(new URI("/api/mrp-synonyms/" + result.getMrpSynonymId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getMrpSynonymId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mrp-synonyms/:mrpSynonymId} : Updates an existing mrpSynonym.
     *
     * @param mrpSynonymId the id of the mrpSynonymDTO to save.
     * @param mrpSynonymDTO the mrpSynonymDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mrpSynonymDTO,
     * or with status {@code 400 (Bad Request)} if the mrpSynonymDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mrpSynonymDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mrp-synonyms/{mrpSynonymId}")
    public ResponseEntity<MrpSynonymDTO> updateMrpSynonym(
        @PathVariable(value = "mrpSynonymId", required = false) final Long mrpSynonymId,
        @Valid @RequestBody MrpSynonymDTO mrpSynonymDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MrpSynonym : {}, {}", mrpSynonymId, mrpSynonymDTO);

        MrpSynonymDTO result = mrpSynonymService.save(mrpSynonymDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mrpSynonymDTO.getMrpSynonymId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /mrp-synonyms/:mrpSynonymId} : Partial updates given fields of an existing mrpSynonym, field will ignore if it is null
     *
     * @param mrpSynonymId the id of the mrpSynonymDTO to save.
     * @param mrpSynonymDTO the mrpSynonymDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mrpSynonymDTO,
     * or with status {@code 400 (Bad Request)} if the mrpSynonymDTO is not valid,
     * or with status {@code 404 (Not Found)} if the mrpSynonymDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the mrpSynonymDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/mrp-synonyms/{mrpSynonymId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MrpSynonymDTO> partialUpdateMrpSynonym(
        @PathVariable(value = "mrpSynonymId", required = false) final Long mrpSynonymId,
        @NotNull @RequestBody MrpSynonymDTO mrpSynonymDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MrpSynonym partially : {}, {}", mrpSynonymId, mrpSynonymDTO);

        Optional<MrpSynonymDTO> result = mrpSynonymService.partialUpdate(mrpSynonymDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mrpSynonymDTO.getMrpSynonymId().toString())
        );
    }

    /**
     * {@code GET  /mrp-synonyms} : get all the mrpSynonyms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mrpSynonyms in body.
     */
    @GetMapping("/mrp-synonyms")
    public List<MrpSynonymDTO> getAllMrpSynonyms() {
        log.debug("REST request to get all MrpSynonyms");
        return mrpSynonymService.findAll();
    }

    /**
     * {@code GET  /mrp-synonyms/:id} : get the "id" mrpSynonym.
     *
     * @param id the id of the mrpSynonymDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mrpSynonymDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mrp-synonyms/{id}")
    public ResponseEntity<MrpSynonymDTO> getMrpSynonym(@PathVariable Long id) {
        log.debug("REST request to get MrpSynonym : {}", id);
        Optional<MrpSynonymDTO> mrpSynonymDTO = mrpSynonymService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mrpSynonymDTO);
    }

    /**
     * {@code DELETE  /mrp-synonyms/:id} : delete the "id" mrpSynonym.
     *
     * @param id the id of the mrpSynonymDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mrp-synonyms/{id}")
    public ResponseEntity<Void> deleteMrpSynonym(@PathVariable Long id) {
        log.debug("REST request to delete MrpSynonym : {}", id);
        mrpSynonymService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
