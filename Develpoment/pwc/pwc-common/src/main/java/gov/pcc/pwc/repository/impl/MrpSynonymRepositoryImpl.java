package gov.pcc.pwc.repository.impl;

import com.google.gson.Gson;
import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.MrpSynonymRepositoryCustom;
import gov.pcc.pwc.service.criteria.MrpSynonymCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.odsExporter.OdsExportService;
import gov.pcc.pwc.utils.PropUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MrpSynonymRepositoryImpl implements MrpSynonymRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public MrpSynonymRepositoryImpl(SqlExecutor sqlExecutor, OdsExportService odsExportService, SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }

    @Override
    public Map<String, Object> queryAdmSysCodeA() {
        System.out.println(PropUtil.getPropVal("ect.sign.cerpath"));

        Map<String, Object> rs = new HashMap<String, Object>();
        Query ascQry = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER (ORDER BY CHAPTER_CODE, MAIN_CODE, DATA_KEY_TYPE) SORT_NO")
                .append(", MAIN_CODE AS DATA_KEY, RM.NAME+DK_TP.NAME AS DATA_VALUE, DATA_KEY_TYPE")
                .append("FROM MRP_REF_MAINCODE AS RM, (SELECT N'' AS NAME, 'CHAPTER' AS DATA_KEY_TYPE UNION ALL")
                .append("SELECT N'(單位)' AS NAME, 'UNIT' AS DATA_KEY_TYPE) AS DK_TP")
//                .append("SELECT SORT_NO, DATA_KEY, DATA_VALUE, DATA_KEY_TYPE FROM MRP_SYS_CODE")
//                .append("WHERE DATA_TYPE = 'Modelsysnon'")
                .build();
        List<MrpSysCodeDTO> ascData = sqlExecutor.queryForList(ascQry, MrpSysCodeDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < ascData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MrpSysCodeDTO dt = ascData.get(x);
            tmpMap.put("sortNo", dt.getSortNo());
            tmpMap.put("dataKey", dt.getDataKey());
            tmpMap.put("dataValue", dt.getDataValue());
            tmpMap.put("dataKeyType", dt.getDataKeyType());
            tmpMap.put("sectionName", String.valueOf(dt.getDataKey()) + "_" + String.valueOf(dt.getDataValue()));
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpSynonym(String section_name, String data_key_type, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT ID AS MRP_SYNONYM_ID, SECTION_NAME, KEYWORD, PHRASE, DATA_KEY_TYPE FROM MRP_SYNONYM")
                .append("WHERE SECTION_NAME = :section_name AND DATA_KEY_TYPE = :DATA_KEY_TYPE ORDER BY ID", section_name, data_key_type)
                .build();
        List<MrpSynonymDTO> msyData = sqlExecutor.queryForList(msyQry, MrpSynonymDTO.class);
        //查詢時後端需寫入log
//        addRecord(request, section_name, "passer", "", "查詢", ip);//id & type(member & passer) 取得方式?
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MrpSynonymDTO dt = msyData.get(x);
            tmpMap.put("id", dt.getMrpSynonymId());
            tmpMap.put("sectionName", dt.getSectionName());
            tmpMap.put("keyword", dt.getKeyword());
            tmpMap.put("phrase", dt.getPhrase());
            tmpMap.put("dataKeyType", dt.getDataKeyType());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> addMrpSynonym(MrpSynonymCriteria mrpSynonymDTO) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Long newPk = Long.valueOf(0);
        Query msyQry = Query
                .builder()
                .append("SELECT TOP(1) ID AS MRP_SYNONYM_ID FROM MRP_SYNONYM ORDER BY ID DESC")
                .build();
        List<MrpSynonymDTO> getpkData = sqlExecutor.queryForList(msyQry, MrpSynonymDTO.class);
        if (getpkData.size() > 0 && getpkData.get(0) != null) {
            newPk = (getpkData.get(0).getMrpSynonymId() + 1);
        }

        Instant createTime = Instant.now();
        Query qy = Query
                .builder()
                .append("Insert into MRP_SYNONYM (ID, SECTION_NAME, KEYWORD, PHRASE, CREATE_ID, CREATE_DATE, DATA_KEY_TYPE)")
                .append("VALUES (:ID, :SECTION_NAME, :KEYWORD, :PHRASE, :CREATE_ID, :CREATE_DATE, :DATA_KEY_TYPE)",
                        newPk, mrpSynonymDTO.getSectionName(), mrpSynonymDTO.getKeyword(), mrpSynonymDTO.getPhrase(),
                        mrpSynonymDTO.getCreateId(), Timestamp.from(createTime), mrpSynonymDTO.getDataKeyType())
                .build();
        sqlExecutor.insert(qy);
        rs.put("status", "0");
        return rs;
    }

    @Override
    public Map<String, Object> updateMrpSynonym(MrpSynonymCriteria mrpSynonymDTO) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Instant updateTime = Instant.now();
        Query query = Query
                .builder()
                .append("UPDATE MRP_SYNONYM SET")
                .append("KEYWORD = :KEYWORD, PHRASE = :PHRASE,", mrpSynonymDTO.getKeyword(), mrpSynonymDTO.getPhrase())
                .append("UPDATE_ID = :UPDATE_ID, UPDATE_DATE = :UPDATE_DATE", mrpSynonymDTO.getUpdateId(), Timestamp.from(updateTime))
                .append("WHERE ID = :SYNONYM_ID", mrpSynonymDTO.getMrpSynonymId())
                .build();
        sqlExecutor.update(query);
        rs.put("status", "0");
        return rs;
    }

    @Override
    public Map<String, Object> deleteMrpSynonym(MrpSynonymCriteria mrpSynonymDTO) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query query = Query
                .builder()
                .append("DELETE MRP_SYNONYM WHERE ID = :synonym_id", mrpSynonymDTO.getMrpSynonymId())
                .build();
        sqlExecutor.delete(query);
        rs.put("status", "0");
        return rs;
    }

    @Override
    public void downloadFile(MrpSynonymCriteria mrpSynonymDTO) {
        Query msyQry = Query
                .builder()
                .append("SELECT SYNONYM_ID, KEYWORD, PHRASE FROM MRP_SYNONYM")
                .append("WHERE SECTION_NAME = :section_name ORDER BY SYNONYM_ID", mrpSynonymDTO.getSectionName())
                .build();
        List<MrpSynonymDTO> msyData = sqlExecutor.queryForList(msyQry, MrpSynonymDTO.class);
    }

    @Override
    public int queryMrpRecordingSize(String type, String create_sdate, String create_edate, String search_conditions) {
        Query msyQry = Query
                .builder()
				.append("SELECT ROW_NUMBER() OVER (order by CREATE_DATE desc) SORT_NO, format(CREATE_DATE, 'yyyy/MM/dd') CREATE_DATE, USER_NAME, TYPE, UNIT_TYPE, ACTION, IP, SEARCH_CONDITIONS")
				.append("FROM (SELECT R.*, CASE WHEN R.ACCOUNT_ID='' THEN N'無登入使用者' ELSE COALESCE(C.CH_NAME, R.ACCOUNT_ID, N'') END USER_NAME , ISNULL(AR.Role_Name, R.TYPE) UNIT_TYPE ")
				.append("FROM MRP_RECORDING R")
				.append("LEFT JOIN ADM_CONTACT C ON C.USER_ID=R.ACCOUNT_ID COLLATE Chinese_Taiwan_Stroke_CS_AS")
				.append("LEFT JOIN (SELECT AAR.USER_ID, STRING_AGG(AR.Role_Id, '^') WITHIN GROUP (ORDER BY AR.Role_Id) AS ROLE_ID, ")
				.append("STRING_AGG(AR.Role_Name, '^') WITHIN GROUP (ORDER BY AR.Role_Id) AS ROLE_NAME")
				.append("FROM ADM_ACCOUNT_ROLE AAR JOIN ADM_ROLE AR ON AR.Role_Id = AAR.Role_Id")
				.append("WHERE AAR.Role_Id LIKE 'MRP%' GROUP BY AAR.USER_ID) AR")
				.append("ON AR.USER_ID = R.ACCOUNT_ID COLLATE Chinese_Taiwan_Stroke_CS_AS")
				.append("WHERE R.CREATE_DATE >= :CREATE_SDATE AND R.CREATE_DATE < DATEADD(DAY, 1, :CREATE_EDATE) AND SEARCH_CONDITIONS LIKE N'%' + :SEARCH_CONDITIONS + N'%') SRC_DATA", create_sdate, create_edate, search_conditions)
				.append("WHERE (:TYPE = N'會員' AND USER_NAME <> N'無登入使用者' COLLATE Chinese_Taiwan_Stroke_CS_AS)", type)
				.append("OR (:TYPE = N'非會員' AND USER_NAME = N'無登入使用者' COLLATE Chinese_Taiwan_Stroke_CS_AS)", type)
				.append("OR (:TYPE NOT IN (N'會員', N'非會員')", type)
				.append("AND UNIT_TYPE LIKE N'%' + :TYPE + N'%') ORDER BY CREATE_DATE DESC", type)

                // .append("SELECT CREATE_DATE, ACCOUNT_ID, TYPE, ACTION, IP, SEARCH_CONDITIONS FROM MRP_RECORDING")
                // .append("WHERE TYPE= :TYPE AND CREATE_DATE BETWEEN :CREATE_SDATE AND :CREATE_EDATE AND SEARCH_CONDITIONS  LIKE CONCAT('%', :SEARCH_CONDITIONS, '%') ", type, create_sdate, create_edate, search_conditions)
                // .append("ORDER BY CREATE_DATE DESC")
                .build();
        List<MRP0103RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0103RDTO.class);
        return msyData.size();
    }

    @Override
    public Map<String, Object> queryMrpRecording(String type, String create_sdate, String create_edate, String search_conditions, int perPage, int queryArea, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            Query msyQry = Query
                    .builder()
					.append("SELECT ROW_NUMBER() OVER (order by CREATE_DATE desc) SORT_NO, format(CREATE_DATE, 'yyyy/MM/dd') CREATE_DATE, USER_NAME, TYPE, UNIT_TYPE, ACTION, IP, SEARCH_CONDITIONS")
					.append("FROM (SELECT R.*, CASE WHEN R.ACCOUNT_ID='' THEN N'無登入使用者' ELSE COALESCE(C.CH_NAME, R.ACCOUNT_ID, N'') END USER_NAME , ISNULL(AR.Role_Name, R.TYPE) UNIT_TYPE ")
					.append("FROM MRP_RECORDING R")
					.append("LEFT JOIN ADM_CONTACT C ON C.USER_ID=R.ACCOUNT_ID COLLATE Chinese_Taiwan_Stroke_CS_AS")
					.append("LEFT JOIN (SELECT AAR.USER_ID, STRING_AGG(AR.Role_Id, '^') WITHIN GROUP (ORDER BY AR.Role_Id) AS ROLE_ID, ")
					.append("STRING_AGG(AR.Role_Name, '^') WITHIN GROUP (ORDER BY AR.Role_Id) AS ROLE_NAME")
					.append("FROM ADM_ACCOUNT_ROLE AAR JOIN ADM_ROLE AR ON AR.Role_Id = AAR.Role_Id")
					.append("WHERE AAR.Role_Id LIKE 'MRP%' GROUP BY AAR.USER_ID) AR")
					.append("ON AR.USER_ID = R.ACCOUNT_ID COLLATE Chinese_Taiwan_Stroke_CS_AS")
					.append("WHERE R.CREATE_DATE >= :CREATE_SDATE AND R.CREATE_DATE < DATEADD(DAY, 1, :CREATE_EDATE) AND SEARCH_CONDITIONS LIKE N'%' + :SEARCH_CONDITIONS + N'%') SRC_DATA", create_sdate, create_edate, search_conditions)
					.append("WHERE (:TYPE = N'會員' AND USER_NAME <> N'無登入使用者' COLLATE Chinese_Taiwan_Stroke_CS_AS)", type)
					.append("OR (:TYPE = N'非會員' AND USER_NAME = N'無登入使用者' COLLATE Chinese_Taiwan_Stroke_CS_AS)", type)
					.append("OR (:TYPE NOT IN (N'會員', N'非會員')", type)
					.append("AND UNIT_TYPE LIKE N'%' + :TYPE + N'%') ORDER BY CREATE_DATE DESC OFFSET :queryArea ROWS FETCH FIRST :perPage ROWS ONLY", type, queryArea, perPage)
					
                    // .append("SELECT CREATE_DATE, ACCOUNT_ID, TYPE, ACTION, IP, SEARCH_CONDITIONS FROM MRP_RECORDING")
                    // .append("WHERE TYPE= :TYPE AND CREATE_DATE BETWEEN :CREATE_SDATE AND :CREATE_EDATE AND SEARCH_CONDITIONS  LIKE CONCAT('%', :SEARCH_CONDITIONS, '%') ", type, create_sdate, create_edate, search_conditions)
                    // .append("ORDER BY CREATE_DATE DESC OFFSET :queryArea ROWS FETCH FIRST :perPage ROWS ONLY", queryArea, perPage)
                    .build();
            List<MRP0103RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0103RDTO.class);
            //查詢時後端需寫入log
//            addRecord(request, search_conditions, "passer", "", "查詢", ip);//id & type(member & passer) 取得方式?
            List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
            for (int x = 0; x < msyData.size(); x++) {
                Map<String, Object> tmpMap = new HashMap<String, Object>();
                MRP0103RDTO dt = msyData.get(x);
                tmpMap.put("idx", (x + 1));
                Instant cd = dt.getCreateDate();
                Date myDate = Date.from(cd);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                String formattedDate = formatter.format(myDate);
                String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];
                tmpMap.put("create_date", ymd);
                tmpMap.put("accountId", dt.getAccountId());
                String tt = "member";
                if (dt.getType() != "8") {
                    tt = "passer";
                }
                tmpMap.put("type", tt);
                tmpMap.put("action", dt.getAction());
                tmpMap.put("ip", dt.getIp());
                tmpMap.put("search_conditions", dt.getSearchConditions());
                rsList.add(tmpMap);
            }
            rs.put("status", "0");
            rs.put("response", rsList);
//            System.out.println("CREATE XLS START");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//            Date now = new Date();
//            String fn = sdf.format(now);
//            String tp = "mrp0103r_";
//            // 開新檔案
//            String path = PropUtil.getPropVal("mrp.file.savepath") + tp + fn + ".xls";
//            Workbook wb  = new HSSFWorkbook();
//            // 設定儲存格資料
//            Sheet sheet = wb.createSheet();
//            Row row = null;
//            Cell cell = null;
//
//            for(int r = 0 ; r < (rsList.size() + 2) ; r++){
//                row = sheet.createRow(r);
//                if(r < 2){
//                    if(r < 1){
//                        cell = row.createCell(0);
//                        cell.setCellValue("公共工程價格資料庫(使用者紀錄管理)");
//                        sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
//                    }else{
//                        for(int c = 0 ; c < 7 ; c++){
//                            cell = row.createCell(c);
//                            if(c == 0){
//                                cell.setCellValue("編號");
//                            }else if(c == 1){
//                                cell.setCellValue("時間");
//                            }else if(c == 2){
//                                cell.setCellValue("使用者");
//                            }else if(c == 3){
//                                cell.setCellValue("單位類型");
//                            }else if(c == 4){
//                                cell.setCellValue("動作/頁籤");
//                            }else if(c == 5){
//                                cell.setCellValue("位址");
//                            }else if(c == 6){
//                                cell.setCellValue("查詢條件");
//                            }
//                        }
//                    }
//                }else{
//                    for(int c = 0 ; c < 7 ; c++){
//                        cell = row.createCell(c);
//                        Map<String, Object> item = rsList.get(r-2);
//                        if(c == 0){
//                            cell.setCellValue(r-1);
//                        }else if(c == 1){
//                            cell.setCellValue(item.get("create_date").toString());
//                        }else if(c == 2){
//                            if(item.get("accountId") != null && item.get("accountId").toString() != ""){
//                                cell.setCellValue(item.get("accountId").toString());
//                            }else{
//                                cell.setCellValue("無登入使用者");
//                            }
//                        }else if(c == 3){
//                            cell.setCellValue(item.get("type").toString());
//                        }else if(c == 4){
//                            cell.setCellValue(item.get("action").toString());
//                        }else if(c == 5){
//                            cell.setCellValue(item.get("ip").toString());
//                        }else if(c == 6){
//                            cell.setCellValue(item.get("search_conditions").toString());
//                        }
//                    }
//                }
//            }
//            // 寫出 Excel
//            FileOutputStream fos = new FileOutputStream(new File(path));
//            wb.write(fos);
//            fos.flush();
//            fos.close();
//            rs.put("fileName", (tp + fn + ".xls"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void downloadMrp0103R(String type, String create_sdate, String create_edate, String search_conditions, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            Query msyQry = Query
                    .builder()
                    .append("SELECT CREATE_DATE, ACCOUNT_ID, TYPE, ACTION, IP, SEARCH_CONDITIONS FROM MRP_RECORDING")
                    .append("WHERE TYPE= :TYPE AND CREATE_DATE BETWEEN :CREATE_SDATE AND :CREATE_EDATE AND SEARCH_CONDITIONS  LIKE CONCAT('%', :SEARCH_CONDITIONS, '%') ", type, create_sdate, create_edate, search_conditions)
                    .append("ORDER BY CREATE_DATE")
                    .build();
            List<MRP0103RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0103RDTO.class);
            //查詢時後端需寫入log
//            addRecord(request, search_conditions, "passer", "", "下載", ip);//id & type(member & passer) 取得方式?
            List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
            for (int x = 0; x < msyData.size(); x++) {
                Map<String, Object> tmpMap = new HashMap<String, Object>();
                MRP0103RDTO dt = msyData.get(x);
                tmpMap.put("idx", (x + 1));
                Instant cd = dt.getCreateDate();
                Date myDate = Date.from(cd);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                String formattedDate = formatter.format(myDate);
                String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];
                tmpMap.put("create_date", ymd);
                tmpMap.put("accountId", dt.getAccountId());
                String tt = "member";
                if (dt.getType() != "8") {
                    tt = "passer";
                }
                tmpMap.put("type", tt);
                tmpMap.put("action", dt.getAction());
                tmpMap.put("ip", dt.getIp());
                tmpMap.put("search_conditions", dt.getSearchConditions());
                rsList.add(tmpMap);
            }
            rs.put("status", "0");
            rs.put("response", rsList);
            System.out.println("CREATE XLS START");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Date now = new Date();
            String fn = sdf.format(now);
            String tp = "mrp0103r_";
            Workbook wb = new HSSFWorkbook();
            // 設定儲存格資料
            Sheet sheet = wb.createSheet();
            Row row = null;
            Cell cell = null;

            for (int r = 0; r < (rsList.size() + 2); r++) {
                row = sheet.createRow(r);
                if (r < 2) {
                    if (r < 1) {
                        cell = row.createCell(0);
                        cell.setCellValue("公共工程價格資料庫(使用者紀錄管理)");
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
                    } else {
                        for (int c = 0; c < 7; c++) {
                            cell = row.createCell(c);
                            if (c == 0) {
                                cell.setCellValue("編號");
                            } else if (c == 1) {
                                cell.setCellValue("時間");
                            } else if (c == 2) {
                                cell.setCellValue("使用者");
                            } else if (c == 3) {
                                cell.setCellValue("單位類型");
                            } else if (c == 4) {
                                cell.setCellValue("動作/頁籤");
                            } else if (c == 5) {
                                cell.setCellValue("位址");
                            } else if (c == 6) {
                                cell.setCellValue("查詢條件");
                            }
                        }
                    }
                } else {
                    for (int c = 0; c < 7; c++) {
                        cell = row.createCell(c);
                        Map<String, Object> item = rsList.get(r - 2);
                        if (c == 0) {
                            cell.setCellValue(r - 1);
                        } else if (c == 1) {
                            cell.setCellValue(item.get("create_date").toString());
                        } else if (c == 2) {
                            if (item.get("accountId") != null && item.get("accountId").toString() != "") {
                                cell.setCellValue(item.get("accountId").toString());
                            } else {
                                cell.setCellValue("無登入使用者");
                            }
                        } else if (c == 3) {
                            if (item.get("type") != null) {
                                if (item.get("type").toString().equals("passer")) {
                                    cell.setCellValue("非會員");
                                } else if (item.get("type").toString().equals("member")) {
                                    cell.setCellValue("會員");
                                } else {
                                    cell.setCellValue(item.get("type").toString());
                                }
                            } else {
                                cell.setCellValue(item.get("type").toString());
                            }
                        } else if (c == 4) {
                            cell.setCellValue(item.get("action").toString());
                        } else if (c == 5) {
                            cell.setCellValue(item.get("ip").toString());
                        } else if (c == 6) {
                            cell.setCellValue(item.get("search_conditions").toString());
                        }
                    }
                }
            }
            // 寫出 Excel
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; " + tp + fn + ".xls");
            wb.write(response.getOutputStream()); // Write workbook to response.
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> queryMrpRecordingStatistics(String type, String create_sdate, String create_edate, String search_conditions, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT COUNT(DISTINCT ACCOUNT_ID) AS USER_CNT, COUNT(SEARCH_CONDITIONS) AS KEYWORD_CNT, ")
                .append("SUM(NUM) AS NUM_CNT,SUM(SAMPLE_NUM) AS SAM_NUM_CNT, SUM(DOWNLOAD_NUM) AS DOW_NUM_CNT")
                .append("FROM MRP_RECORDING")
                .append("WHERE TYPE= :TYPE AND CREATE_DATE BETWEEN :CREATE_SDATE AND :CREATE_EDATE AND SEARCH_CONDITIONS  LIKE CONCAT('%', :SEARCH_CONDITIONS, '%') ", type, create_sdate, create_edate, search_conditions)
                .build();
        List<MRP0103RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0103RDTO.class);
        //查詢時後端需寫入log
//        addRecord(request, search_conditions, "passer", "", "查詢", ip);//id & type(member & passer) 取得方式?
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0103RDTO dt = msyData.get(x);
            tmpMap.put("user_cnt", dt.getUserCnt());
            tmpMap.put("search_conditions", dt.getKeywordCnt());
            tmpMap.put("num", dt.getNumCnt());
            tmpMap.put("sample_num", dt.getSamNumCnt());
            tmpMap.put("download_num", dt.getDowNumCnt());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpReport(HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT ID, RELEASE_DATE, SUBJECT FROM PWC_INFORMATION ORDER BY RELEASE_DATE DESC")   // $$$
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("id", dt.getId());
            tmpMap.put("date", dt.getReleaseDate());
            tmpMap.put("subject", dt.getSubject());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpReportDownload(String id, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT A.ID , B.PRO_INFORMATION_APPENDIX_ID , B.PRO_INFORMATION_ID, CONCAT(C.FILE_PATH_NAME, '\', FILE_NAME_ORI) AS fileName")
                .append("FROM PWC_INFORMATION A , PWC_INFORMATION_APPENDIX B, ADM_FILE C")
                .append("WHERE B.PRO_INFORMATION_ID = A.ID AND A.ID = :ID", id)
                .append("AND C.SOURCE_ID = CAST(B.PRO_INFORMATION_APPENDIX_ID AS VARCHAR) + '_' + CAST(B.ATTRIBUTES AS VARCHAR)")
                .append("AND C.MODULE_TYPE = 'PWC' AND C.BUSINESS_TYPE = 'PWC_INFORMATION'")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        //查詢時後端需寫入log
//        addRecord(request, "", "passers", "", "稽催報表", ip);//id & type(member & passer) 取得方式?
//        MRP0501RDTO dt = msyData.get(0);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("fileName", dt.getFileName());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpResult(String org, String engineering, String start_date, String end_date, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT TMP.MAIN_ORG_NAME AS MAIN_ORG_NAME, TMP.MAIN_ORG_CODE AS MAIN_ORG_CODE, TMP.ENGINEERING_CODE AS ENGINEERING_CODE, TMP.ENGINEERING_NAME AS ENGINEERING_NAME,")
                .append("TMP.AWARD_BUDGET_DATE AS AWARD_BUDGET_DATE, CLL.MEMO, MAX(CLL.CREATE_DATE) AS CREATE_DATE, TMP.AWARD_NOTICE_DATE AS ANNOUNCEMENT")
                .append("FROM (SELECT MAX(CL.FILESOURCE_ID)AS FILESOURCE_ID, CL.CONTRACT_CODE, CL.MAIN_ORG_NAME, CL.MAIN_ORG_CODE")
                .append(", CL.ENGINEERING_CODE , CL.ENGINEERING_NAME , CL.AWARD_BUDGET_DATE, TMP.AWARD_NOTICE_DATE")
                .append("FROM MRP_PROJECT_LIST CL LEFT JOIN (SELECT CP.CONTRACT_CODE, CP.CREATE_DATE, CP.AWARD_NOTICE_DATE FROM MRP_PROJECT_PURCHASE CP,")
                .append("(SELECT CONTRACT_CODE, MAX(CREATE_DATE) AS CREATE_DATE FROM MRP_PROJECT_PURCHASE WHERE TENDER_AWARD_TYPE != '無法決標公告' GROUP BY CONTRACT_CODE ) AS CP2 ) AS TMP")
                .append("ON TMP.CONTRACT_CODE = CL.CONTRACT_CODE WHERE TMP.CONTRACT_CODE IS NOT NULL AND (MAIN_ORG_NAME LIKE '%" + org + "%' OR MAIN_ORG_CODE LIKE '" + org + "')")
                .append("AND (CAST('" + start_date + "' AS DATETIME) <= AWARD_BUDGET_DATE AND CAST(" + end_date + " AS DATETIME) >= AWARD_BUDGET_DATE ")
                .append("OR CAST('" + start_date + "' AS DATETIME) <= AWARD_NOTICE_DATE AND CAST('" + end_date + "' AS DATETIME) >= AWARD_NOTICE_DATE)")
                .append("GROUP BY CL.CONTRACT_CODE, MAIN_ORG_NAME, MAIN_ORG_CODE, ENGINEERING_CODE, ENGINEERING_NAME, AWARD_BUDGET_DATE, TMP.AWARD_NOTICE_DATE) AS TMP")
                .append("LEFT JOIN MRP_PROJECT_LOG CLL ON TMP.FILESOURCE_ID = CLL.FILESOURCE_ID WHERE CLL.MEMO IS NOT NULL")
                .append("GROUP BY TMP.MAIN_ORG_NAME, TMP.MAIN_ORG_CODE, TMP.ENGINEERING_CODE, TMP.ENGINEERING_NAME, TMP.AWARD_BUDGET_DATE, CLL.MEMO, TMP.AWARD_NOTICE_DATE")
                .append("UNION SELECT TMP.MAIN_ORG_NAME AS MAIN_ORG_NAME, TMP.MAIN_ORG_CODE AS MAIN_ORG_CODE, TMP.ENGINEERING_CODE AS ENGINEERING_CODE, TMP.ENGINEERING_NAME AS ENGINEERING_NAME")
                .append(", TMP.AWARD_BUDGET_DATE AS AWARD_BUDGET_DATE, CLL.MEMO, CLL.CREATE_DATE, TMP.AWARD_NOTICE_DATE AS ANNOUNCEMENT")
                .append("FROM (SELECT MAX(CL.FILESOURCE_ID)AS FILESOURCE_ID, CL.CONTRACT_CODE, CL.MAIN_ORG_NAME, CL.MAIN_ORG_CODE,")
                .append("CL.ENGINEERING_CODE, CL.ENGINEERING_NAME, CL.AWARD_BUDGET_DATE, TMP.AWARD_NOTICE_DATE")
                .append("FROM MRP_PROJECT_LIST CL LEFT JOIN (SELECT CP.CONTRACT_CODE, CP.CREATE_DATE, CP.AWARD_NOTICE_DATE")
                .append("FROM MRP_PROJECT_PURCHASE CP, (SELECT CONTRACT_CODE, MAX(CREATE_DATE) AS CREATE_DATE ")
                .append("FROM MRP_PROJECT_PURCHASE WHERE TENDER_AWARD_TYPE != '無法決標公告' AND KIND = 0 GROUP BY CONTRACT_CODE) AS CP2")
                .append("WHERE CP.CONTRACT_CODE = CP2.CONTRACT_CODE AND CP.CREATE_DATE = CP2.CREATE_DATE AND TENDER_AWARD_TYPE != '無法決標公告' AND KIND = 0) AS TMP")
                .append("ON TMP.CONTRACT_CODE = CL.CONTRACT_CODE WHERE TMP.CONTRACT_CODE IS NOT NULL AND (MAIN_ORG_NAME LIKE '%" + org + "%' OR MAIN_ORG_CODE LIKE '" + org + "')")
                .append("AND (CAST('" + start_date + "' AS DATETIME) <= AWARD_BUDGET_DATE AND CAST('" + end_date + "' AS DATETIME) >= AWARD_BUDGET_DATE")
                .append("OR CAST('" + start_date + "' AS DATETIME) <= AWARD_NOTICE_DATE AND CAST('" + end_date + "' AS DATETIME) >= AWARD_NOTICE_DATE)")
                .append("GROUP BY CL.CONTRACT_CODE, MAIN_ORG_NAME, MAIN_ORG_CODE, ENGINEERING_CODE, ENGINEERING_NAME, AWARD_BUDGET_DATE, TMP.AWARD_NOTICE_DATE) AS TMP")
                .append("LEFT JOIN MRP_PROJECT_LOG CLL ON TMP.FILESOURCE_ID = CLL.FILESOURCE_ID WHERE CLL.MEMO IS NULL AND")
                .append("(CAST('" + start_date + "' AS DATETIME) <= AWARD_BUDGET_DATE AND CAST('" + end_date + "' AS DATETIME) >= AWARD_BUDGET_DATE")
                .append("OR CAST('" + start_date + "' AS DATETIME) <= AWARD_NOTICE_DATE AND CAST('" + end_date + "' AS DATETIME) >= AWARD_NOTICE_DATE)")
                .append("AND (MAIN_ORG_NAME LIKE '%" + org + "%' OR MAIN_ORG_CODE LIKE '" + org + "') AND (ENGINEERING_NAME LIKE '%災害復建%' OR ENGINEERING_CODE LIKE '災害復建')")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            String sts = "未上傳";
            if (dt.getCreateDate() != null && String.valueOf(dt.getMemo()) != "") {
                sts = "已上傳";
            }
            Instant cd = dt.getCreateDate();
            Date myDate = Date.from(cd);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = formatter.format(myDate);
            String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];

            Instant nd = dt.getAnnouncement();
            Date myDate2 = Date.from(nd);
            String formattedDate2 = formatter.format(myDate2);
            String ymd2 = String.valueOf(Integer.parseInt(formattedDate2.split("/")[0]) - 1911) + "/" + formattedDate2.split("/")[1] + "/" + formattedDate2.split("/")[2];

            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = dt.getAwardBudgetDate();
            Date myDate3 = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            String formattedDate3 = formatter.format(myDate3);
            String ymd3 = String.valueOf(Integer.parseInt(formattedDate3.split("/")[0]) - 1911) + "/" + formattedDate3.split("/")[1] + "/" + formattedDate3.split("/")[2];

            tmpMap.put("create_date", ymd);
            tmpMap.put("status", sts);
            tmpMap.put("org_name", dt.getMainOrgName());
            tmpMap.put("org_code", dt.getMainOrgCode());
            tmpMap.put("engineering_name", dt.getEngineeringName());
            tmpMap.put("engineering_code", dt.getEngineeringCode());
            tmpMap.put("budget_date", ymd3);
            tmpMap.put("notice_date", ymd2);
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "passers", "", "查詢", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpResultDt(String filesource_id, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT MPL.ENGINEERING_CODE, MLO.MEMO, MLO.CREATE_DATE ")
                .append("FROM MRP_PROJECT_LIST MPL INNER JOIN MRP_PROJECT_LOG MLO")
                .append("ON MPL.FILESOURCE_ID = MLO.FILESOURCE_ID WHERE MLO.FILESOURCE_ID = :filesourceId", filesource_id)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            String sts = "未上傳";
            if (dt.getCreateDate() != null && String.valueOf(dt.getMemo()) != "") {
                sts = "已上傳";
            }

            Instant cd = dt.getCreateDate();
            Date myDate = Date.from(cd);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = formatter.format(myDate);
            String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];

            tmpMap.put("engineering_code", dt.getEngineeringCode());
            tmpMap.put("status", sts);
            tmpMap.put("create_date", ymd);
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "passers", "", "查看", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryLatestDate(HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT MAX(CREATE_DATE) AS CREATE_DATE FROM MRP_VERIFY_TABLE ")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        MRP0501RDTO dt = msyData.get(0);
        Instant maxDate = dt.getCreateDate();
        String[] parts = maxDate.toString().split("-");

// $$$
        if(parts[1].equals("2")) {
            tmpMap.put("start_date", (Integer.parseInt(parts[0]) - 1912) + "/12/" + parts[2].substring(0, 2));
        } else if(parts[1].equals("1")) {
            tmpMap.put("start_date", (Integer.parseInt(parts[0]) - 1912) + "/11/" + parts[2].substring(0, 2));
        } else {
            tmpMap.put("start_date", (Integer.parseInt(parts[0]) - 1911) + "/" + (Integer.parseInt(parts[1]) - 2) + "/" + parts[2].substring(0, 2));
        }
        tmpMap.put("end_date", (Integer.parseInt(parts[0]) - 1911) + "/" + parts[1] + "/" + parts[2].substring(0, 2));
// $$$
        rsList.add(tmpMap);
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpDelta(String start_date, String end_date, String keyword, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT CODE, NAME, KIND AS PRICETYPE , USED, NUM, CREATE_DATE, CONVERT(DATETIME, AWARD_BUDGET_DATE) AS AWARD_BUDGET_DATE, DELTA, ROW , MEAN_VALUE ")
                .append("FROM (SELECT VRC.CODE, VRC.NAME, VRC.KIND, MIN(VRC.USED) AS USED, COUNT(VRC.CODE) AS NUM, VRC.CREATE_DATE, CONVERT(NVARCHAR(8),")
                .append("CL.AWARD_BUDGET_DATE,111)+'01' AS AWARD_BUDGET_DATE, VS.DELTA, ROW_NUMBER() OVER(ORDER BY VRC.CREATE_DATE) AS ROW  ,VS.MEAN_VALUE ")
                .append("FROM MRP_VERIFY_RESOURCE_CODE VRC, MRP_PROJECT_LIST CL, MRP_VERIFY_STATISTICS VS")
                .append("WHERE VRC.STATUS =2   AND (USED =0 OR USED = 1 )  AND VRC.FILESOURCE_ID = CL.FILESOURCE_ID AND VRC.CODE = VS.RESOURCE_CODE   AND VRC.KIND = VS.KIND")
				.append("AND FORMAT(CL.AWARD_BUDGET_DATE, 'yyyy/MM/01') = CONVERT(NVARCHAR, VS.ANALYSIS_TIME, 111)")
				.append("AND CONVERT(NVARCHAR, VRC.CREATE_DATE, 111) = CONVERT(NVARCHAR, VS.CREATE_DATE, 111)")
                .appendWhen(keyword.length() > 0, "AND VRC.NAME LIKE '%" + keyword + "%'") // $$$ '%'" + keyword + "'%' -> '%" + keyword + "%'
                .appendWhen(start_date.length() > 0 && end_date.length() > 0, "AND CAST('" + start_date + "' AS DATETIME)  <= VRC.CREATE_DATE AND VRC.CREATE_DATE <= CAST('" + end_date + "' AS DATETIME)") // $$$ CREATETIME -> CREATE_DATE
                .append("GROUP BY VRC.CODE, VRC.NAME, VRC.CREATE_DATE, VRC.KIND, CONVERT(NVARCHAR(8), CL.AWARD_BUDGET_DATE,111)+'01',VS.DELTA ,VS.MEAN_VALUE) AS TMP")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            Instant cd = dt.getCreateDate();
            Date myDate = Date.from(cd);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = formatter.format(myDate);
            String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];

            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = dt.getAwardBudgetDate();
            Date myDate3 = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            String formattedDate3 = formatter.format(myDate3);
            String ymd3 = String.valueOf(Integer.parseInt(formattedDate3.split("/")[0]) - 1911) + "/" + formattedDate3.split("/")[1] + "/" + formattedDate3.split("/")[2];

            tmpMap.put("id", x + 1);
            tmpMap.put("date", ymd);
            tmpMap.put("code", dt.getCode());
            tmpMap.put("award_budget_date", ymd3);
            tmpMap.put("name", dt.getName());
            tmpMap.put("pricetype", dt.getPricetype());
            tmpMap.put("delta", dt.getDelta());
            tmpMap.put("status", dt.getUsed());
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "查詢", ip);//id & type(member & passer) 取得方式? // $$$ 關掉才能跑
        rs.put("status", "0");
        rs.put("response", rsList);
        try {
            System.out.println("CREATE XLS START");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Date now = new Date();
            String fn = sdf.format(now);
            String tp = "mrp0105r_";
            // 開新檔案
            String path = PropUtil.getPropVal("mrp.file.savepath") + tp + fn + ".xls";
            Workbook wb = new HSSFWorkbook();
            // 設定儲存格資料
            Sheet sheet = wb.createSheet();
            Row row = null;
            Cell cell = null;

            for (int r = 0; r < (rsList.size() + 2); r++) {
                row = sheet.createRow(r);
                if (r < 2) {
                    if (r < 1) {
                        cell = row.createCell(0);
                        cell.setCellValue("公共工程價格資料庫(變動篩選管理)");
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
                    } else {
                        for (int c = 0; c < 5; c++) {
                            cell = row.createCell(c);
                            if (c == 0) {
                                cell.setCellValue("編號");
                            } else if (c == 1) {
                                cell.setCellValue("列管時間");
                            } else if (c == 2) {
                                cell.setCellValue("工作名稱");
                            } else if (c == 3) {
                                cell.setCellValue("變動幅度");
                            } else if (c == 4) {
                                cell.setCellValue("列管狀態");
                            }
                        }
                    }
                } else {
                    for (int c = 0; c < 5; c++) {
                        cell = row.createCell(c);
                        Map<String, Object> item = rsList.get(r - 2);
                        if (c == 0) {
                            cell.setCellValue(r - 1);
                        } else if (c == 1) {
                            cell.setCellValue(item.get("date").toString());
                        } else if (c == 2) {
                            cell.setCellValue(item.get("name").toString());
                        } else if (c == 3) {
                            cell.setCellValue(item.get("delta").toString());
                        } else if (c == 4) {
                            cell.setCellValue(item.get("status").toString());
                        }
                    }
                }
            }
            // 寫出 Excel
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            fos.flush();
            fos.close();
            rs.put("fileName", (tp + fn + ".xls"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpView(String create_date, String code, String award_budget_date, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT ID, CODE, NAME, KIND AS priceType, UNIT, PRICE, USED, DESCRIPTION_TW, UNIT_TW, ")
                .append("FILESOURCE_ID, CREATE_DATE, CONVERT(DATETIME,AWARD_BUDGET_DATE)AS awardBudgetDate ")
                .append("FROM( SELECT VRC.ID, VRC.CODE, VRC.NAME, VRC.KIND, VRC.UNIT, VRC.PRICE, VRC.USED, RL.DESCRIPTION_TW,")
                .append("RL.UNIT_TW, VRC.FILESOURCE_ID, VRC.CREATE_DATE, CONVERT(NVARCHAR(8), CL.AWARD_BUDGET_DATE,111)+'01' AS AWARD_BUDGET_DATE")
                .append("FROM MRP_VERIFY_RESOURCE_CODE VRC, MRP_PROJECT_LIST CL, MRP_RESOURCE_LIST RL ")
                .append("WHERE VRC.STATUS =2 AND VRC.FILESOURCE_ID = CL.FILESOURCE_ID AND VRC.RESOURCE_ID = RL.ID")
                .append("AND VRC.CODE = :CODE AND CONVERT(NVARCHAR, VRC.CREATE_DATE,111) = :CREATE_DATE", code, create_date)
                .append("AND CONVERT(NVARCHAR(8), CL.AWARD_BUDGET_DATE,111)+'01' = :AWARD_BUDGET_DATE)AS TMP", award_budget_date)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        float allPrice = 0; // $$$
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            Instant cd = dt.getCreateDate();
            Date myDate = Date.from(cd);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = formatter.format(myDate);
            String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];

            tmpMap.put("id", dt.getId());
            tmpMap.put("code", dt.getCode());
            tmpMap.put("name", dt.getName());
            tmpMap.put("pricetype", dt.getPricetype());
            tmpMap.put("unit", dt.getUnit());
            tmpMap.put("price", dt.getPrice());
            allPrice += Float.valueOf(dt.getPrice()); // $$$
            tmpMap.put("used", dt.getUsed());
            tmpMap.put("description_tw", dt.getDescriptionTw());
            tmpMap.put("unit_tw", dt.getUnitTw());
            tmpMap.put("fileSource_id", dt.getFileSourceId());
            tmpMap.put("create_date", ymd);
            tmpMap.put("award_budget_date", dt.getAwardBudgetDate());
            rsList.add(tmpMap);
        }
        float avg =  allPrice / msyData.size(); // $$$

        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "詳情:查詢明細", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        rs.put("avg", avg); // $$$
        return rs;
    }

    @Override
    public Map<String, Object> queryArea(HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT ID, NAME FROM MRP_TW_CITY WHERE ID != -1 ORDER BY RANK")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = -1; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            if (x != -1) {
                MRP0501RDTO dt = msyData.get(x);
                tmpMap.put("id", dt.getId());
                tmpMap.put("name", dt.getName());
            } else {
                tmpMap.put("id", "all");
                tmpMap.put("name", "不限");
            }
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
        // $$$ addRecord(request, "", "passers", "", "查看", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryCode(String keyword, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT DISTINCT NAME, CODE, UNIT FROM MRP_RESOURCE_CODE ")
                .append("WHERE NAME LIKE '%" + keyword + "%' OR CODE LIKE '" + keyword + "%'")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("name", dt.getName());
            tmpMap.put("code", dt.getCode());
            tmpMap.put("unit", dt.getUnit());
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "編碼查詢", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> querySuggestBound(String resourceCode, String dateStart, String dateEnd, String isBudget, String region, String regionName, HttpServletRequest request) {
        String documentType = "budget";
        if (isBudget.equals("false")) {
            documentType = "contract";
        }
        String regionType = "all";
        if (!region.equals("all")) {
            if (region.matches("-?\\d+")) {
                regionType = "city";
            } else {
                regionType = "area";
            }
        }
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER (ORDER BY QUANTITY) ROW_NUM, COUNT(1) OVER (PARTITION BY 1) CNT, QB.QUANTITY")
                .append("FROM (SELECT RL.QUANTITY FROM MRP_RESOURCE_LIST RL, MRP_RESOURCE_CODE RC, MRP_TENDERINFO_FILE TIF, MRP_PROJECT_LIST CL, MRP_TENDERINFO TI")
                .append("WHERE RC.RESOURCE_ID = RL.ID AND TIF.FILESOURCE_ID = RL.FILESOURCE_ID AND CL.FILESOURCE_ID = TIF.FILESOURCE_ID AND TI.FILESOURCE_ID = CL.FILESOURCE_ID")
                .appendWhen(regionType.equals("area") && regionName != null && !regionName.equals(""), "AND TI.AREA = :REGION_NAME", regionName)
                .appendWhen(regionType.equals("city") && region != null && !region.equals(""), "AND TI.TAIWANCITY_ID = :REGION", region)
                .append("AND TIF.DOCUMENT_TYPE = :DOCUMENT_TYPE", documentType)
                .appendWhen(resourceCode != null && !resourceCode.equals(""), "AND RC.CODE = :resourceCode", resourceCode)
                .appendWhen(resourceCode != null && !resourceCode.equals("") && !dateStart.equals("") && dateStart != null && !dateEnd.equals("") && dateEnd != null, "AND CL.AWARD_BUDGET_DATE >= :dateStart AND CL.AWARD_BUDGET_DATE < :dateEnd", dateStart, dateEnd)
                .append(") as QB")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            int cnt = dt.getCnt();
            float q2 = cnt / 4;
            float q3 = cnt * 3 / 4;
            tmpMap.put("q2", q2);
            tmpMap.put("q3", q3);
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryPrice(String resourceCode, String dateStart, String dateEnd, String isBudget, String region, String regionName, String lowerBound, String upperBound, HttpServletRequest request) {
        String documentType = "budget";
        if (isBudget.equals("false")) {
            documentType = "contract";
        }
        String regionType = "all";
        if (!region.equals("all")) {
            if (region.matches("-?\\d+")) {
                regionType = "city";
            } else {
                regionType = "area";
            }
        }
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT TOP 1 NAME, UNIT FROM MRP_RESOURCE_CODE WHERE CODE = :RESOURCE_CODE", resourceCode)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        if (msyData.size() > 0) {
            Query outQry = Query
                    .builder()
                    .append("SELECT 'IN_BOUND' AS BOUND_TYPE, PRJ_BASE.ID, PRJ_BASE.NAME, PRJ_BASE.UNIT, PRJ_BASE.TOT_AMT, PRJ_BASE.TOT_QUANTITY, PRJ_BASE.AVG_PRICE FROM (")
                    .append("SELECT CL.ID, RC.NAME, RC.UNIT, SUM(RL.PRICE * RL.QUANTITY) AS TOT_AMT, SUM(RL.QUANTITY) AS TOT_QUANTITY, SUM(RL.PRICE * RL.QUANTITY) / SUM(RL.QUANTITY) AS AVG_PRICE")
                    .append("FROM MRP_RESOURCE_LIST RL, MRP_RESOURCE_CODE RC, MRP_TENDERINFO_FILE TIF, MRP_PROJECT_LIST CL, MRP_TENDERINFO TI ")
                    .append("WHERE RC.RESOURCE_ID = RL.ID AND TIF.FILESOURCE_ID = RL.FILESOURCE_ID AND CL.FILESOURCE_ID = TIF.FILESOURCE_ID ")
                    .append("AND TI.FILESOURCE_ID = CL.FILESOURCE_ID AND ISNULL(RL.PRICE, 0) > 0 AND ISNULL(RL.QUANTITY, 0) > 0")
                    .appendWhen(regionType.equals("area"), "AND TI.AREA = :REGION_NAME", regionName)
                    .appendWhen(regionType.equals("city"), "AND TI.TAIWANCITY_ID = :REGION", region)
                    .append("AND TIF.DOCUMENT_TYPE = :DOCUMENT_TYPE", documentType)
                    .appendWhen(resourceCode != null && !resourceCode.equals(""), "AND RC.CODE = :resourceCode", resourceCode)
                    .appendWhen(resourceCode != null && !resourceCode.equals(""), "AND CL.AWARD_BUDGET_DATE >= :dateStart AND CL.AWARD_BUDGET_DATE < :dateEnd", dateStart, dateEnd)
                    .append("GROUP BY CL.ID, RC.NAME, RC.UNIT")
                    .appendWhen(!regionType.equals("all"), "HAVING ISNULL(SUM(RL.QUANTITY), 0) > 0")
                    .append(") AS PRJ_BASE WHERE PRJ_BASE.TOT_AMT >= 10 AND PRJ_BASE.TOT_AMT < 100")
                    .build();
            List<MRP0501RDTO> inData = sqlExecutor.queryForList(outQry, MRP0501RDTO.class);

            Query inQry = Query
                    .builder()
                    .append("SELECT 'OUT_BOUND' AS BOUND_TYPE, PRJ_BASE.ID, PRJ_BASE.NAME, PRJ_BASE.UNIT, PRJ_BASE.TOT_AMT, PRJ_BASE.TOT_QUANTITY, PRJ_BASE.AVG_PRICE FROM (")
                    .append("SELECT CL.ID, RC.NAME, RC.UNIT, SUM(RL.PRICE * RL.QUANTITY) AS TOT_AMT, SUM(RL.QUANTITY) AS TOT_QUANTITY, SUM(RL.PRICE * RL.QUANTITY) / SUM(RL.QUANTITY) AS AVG_PRICE")
                    .append("FROM MRP_RESOURCE_LIST RL, MRP_RESOURCE_CODE RC, MRP_TENDERINFO_FILE TIF, MRP_PROJECT_LIST CL, MRP_TENDERINFO TI ")
                    .append("WHERE RC.RESOURCE_ID = RL.ID AND TIF.FILESOURCE_ID = RL.FILESOURCE_ID AND CL.FILESOURCE_ID = TIF.FILESOURCE_ID ")
                    .append("AND TI.FILESOURCE_ID = CL.FILESOURCE_ID AND ISNULL(RL.PRICE, 0) > 0 AND ISNULL(RL.QUANTITY, 0) > 0")
                    .appendWhen(regionType.equals("area"), "AND TI.AREA = :REGION_NAME", regionName)
                    .appendWhen(regionType.equals("city"), "AND TI.TAIWANCITY_ID = :REGION", region)
                    .append("AND TIF.DOCUMENT_TYPE = :DOCUMENT_TYPE", documentType)
                    .appendWhen(resourceCode != null && !resourceCode.equals(""), "AND RC.CODE = :resourceCode", resourceCode)
                    .appendWhen(resourceCode != null && !resourceCode.equals(""), "AND CL.AWARD_BUDGET_DATE >= :dateStart AND CL.AWARD_BUDGET_DATE < :dateEnd", dateStart, dateEnd)
                    .append("GROUP BY CL.ID, RC.NAME, RC.UNIT")
                    .appendWhen(!regionType.equals("all"), "HAVING ISNULL(SUM(RL.QUANTITY), 0) > 0")
                    .append(") AS PRJ_BASE WHERE PRJ_BASE.TOT_AMT >= 10 OR PRJ_BASE.TOT_AMT < 100")
                    .build();
            List<MRP0501RDTO> outData = sqlExecutor.queryForList(inQry, MRP0501RDTO.class);
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            int inBoundDataCount = inData.size();
            int totalDataCount = (inData.size() + outData.size());
            tmpMap.put("name", msyData.get(0).getName());
            tmpMap.put("unit", msyData.get(0).getUnit());
            tmpMap.put("lowerBound", lowerBound);
            tmpMap.put("upperBound", upperBound);
            double pri = 0;
            for (int x = 0; x < inData.size(); x++) {
                pri = pri + inData.get(x).getAVG_PRICE();
            }
            double avg = pri / inData.size();
            tmpMap.put("avg", avg);

            tmpMap.put("intvl", "");
            tmpMap.put("pValue", "");
            tmpMap.put("isFit", "");

            tmpMap.put("inBoundData", inData);
            tmpMap.put("outBoundData", outData);
            tmpMap.put("inBoundDataCount", inBoundDataCount);
            tmpMap.put("totalDataCount", totalDataCount);
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "價格查詢", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryConPrice(String code_type, String code, String name1, String name2, String name3, String cond1, String cond2, String period, String area, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT period, code, code_name, unit, area, avg_price, sample FROM MRP_CONPRICE WHERE ")

                .appendWhen(!code_type.equals("M") && !code_type.equals("LEM_"), "CODE LIKE '" + code_type + "%'")
                .appendWhen(code_type.equals("LEM_"), "(CODE LIKE 'L%' OR CODE LIKE 'E%' OR CODE LIKE 'M%')")
                .appendWhen(code_type.equals("M"), "CODE LIKE 'M%' OR CODE LIKE 'W%' OR CODE LIKE '^[0-9]+'")

                .appendWhen(!code.equals(""), "AND CODE = :code", code)
                .append("AND [PERIOD] = :period", period)
                .append("AND AREA = :area", area)
                .appendWhen(name1 != null && !name1.equals(""), "AND CODE_NAME LIKE CONCAT('%', :name1, '%') ", name1)
                .appendWhen(cond1 != null && name2 != null && !name2.equals(""),  cond1)
                .appendWhen(name2 != null && !name2.equals(""), "CODE_NAME LIKE CONCAT('%', :name2, '%')", name2)
                .appendWhen(cond2 != null && name3 != null && !name3.equals(""), cond2)
                .appendWhen(name3 != null && !name3.equals(""), "CODE_NAME LIKE CONCAT('%', :name3, '%')", name3)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("period", dt.getPeriod());
            tmpMap.put("code", dt.getCode());
            tmpMap.put("code_name", dt.getCode_name());
            tmpMap.put("unit", dt.getUnit());
            tmpMap.put("area", dt.getArea());
            tmpMap.put("avg_price", dt.getAVG_PRICE());
            tmpMap.put("sample", dt.getSample());
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
        // $$$ addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryConPriceDetail(String code, String code_name, String period, String unit, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT Period, Code, Code_name, Unit, Area, AVG_PRICE, UPPER_PRICE, LOWER_PRICE, Sample FROM MRP_CONPRICE WHERE ")
                .append("CODE = :code", code)
                .append("AND CODE_NAME = :code_name ", code_name)
                .append("AND PERIOD = :period", period)
                .append("AND UNIT = :unit", unit)
                .append("AND AREA IN ('北部','中部','南部','東部')")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        Map<String, Object> rsList = new HashMap<String, Object>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("period", dt.getPeriod());
            tmpMap.put("code", dt.getCode());
            tmpMap.put("code_name", dt.getCode_name());
            tmpMap.put("unit", dt.getUnit());
            tmpMap.put("area", dt.getArea());
            tmpMap.put("avg_price", dt.getAVG_PRICE());
            tmpMap.put("upper_price", dt.getUPPER_PRICE());
            tmpMap.put("lower_price", dt.getLOWER_PRICE());
            tmpMap.put("sample", dt.getSample());
            rsList.put(dt.getArea(),tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryBasic(String ddl_city, String ddl_town, String ddl_size, String ddl_type, String key_word, String startime, String endtime, String backtime, String ACCOUNT_ID, String create_id, String mrpNo, HttpServletRequest request) {
        String COL_NAME = getColName_sqlstr(ddl_city);
        String sqlarea = getArea_sqlstr(ddl_city, ddl_town);
        String sqlcity = getCity_sqlstr(ddl_city);
        String sqlTown = getTownSqlstr(ddl_town);
        String sqltime = getTime_sqlstr(startime, endtime);
        String sqlsize = getSize_sqlstr(ddl_size);
        String sqlkeyword = getKeyword_sqlstr(key_word);
        String sqltype = getType_sqlstr(ddl_type);
        String backtime_str = "";
        if (backtime != null) {
            getAdvanceBacktime_sqlstr(backtime);
        }
        Map<String, Object> rs = new HashMap<String, Object>();
        try{
            Query msyQry = Query
                    .builder()
                    .append("SELECT * FROM ( SELECT tmp.DATESTRING, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, tmp.CITY, tmp.TOWN, ROUND(AVG(tmp.PRICE),0)AS PRICE, ") // $$$
                    .append("ROUND((AVG(tmp.PRICE)+STDEVP(tmp.PRICE)),0)AS PXMAX, ROUND((AVG(tmp.PRICE)-STDEVP(tmp.PRICE)),0)AS PXMIN, ")
                    .append("ROUND( AVG(tmp.PRICE),0) AS PRICE_INDEX, ROUND( COUNT(tmp.PRICE),0) AS NUM, ROUND( STDEVP(tmp.PRICE),0) AS ERR_PRICE, ")
                    .append("ROW_NUMBER() OVER(ORDER BY tmp.CODE DESC) AS ROW, tmp.PRICETYPE FROM ( ")
                    .append("SELECT DISTINCT CONVERT(NVARCHAR(8), cl.AWARD_BUDGET_DATE, 111)+'01' AS DATESTRING, rc.RESOURCE_ID, ") // $$$
                    .append("rc.NAME, rc.UNIT, rc.CODE, " + COL_NAME + " rc.PRICE AS PRICE, rc.STATUS, rc.USED, rc.KIND AS PRICETYPE ")
                    .append("FROM MRP_VERIFY_RESOURCE_CODE rc, MRP_TENDERINFO ti, MRP_PROJECT_LIST cl, ")
                    .append("MRP_TENDERINFO_PROJECTCODE tip, MRP_TENDERINFO_TW_CITY tic, MRP_TW_AREA ta")
                    .append("WHERE rc.FILESOURCE_ID = ti.FILESOURCE_ID AND ti.FILESOURCE_ID = tic.FILESOURCE_ID")
                    .append("AND ti.FILESOURCE_ID = tic.FILESOURCE_ID AND tic.TAIWAN_CITY_ID = ta.CITY_ID")
                    .append("AND ti.FILESOURCE_ID = tip.FILESOURCE_ID AND rc.UNIT != '' AND rc.PRICE > 0 AND rc.USED =1")
                    .append(sqlarea + sqltime + sqlsize + sqlkeyword + sqltype)
                    .appendWhen(backtime != null, backtime_str)
                    .append(") AS tmp GROUP BY tmp.DATESTRING, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, tmp.PRICETYPE, tmp.CITY, tmp.TOWN) AS tmp WHERE tmp.PRICE > 0") // $$$
                    .build();
            List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
            List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
            for (int x = 0; x < msyData.size(); x++) {
                Map<String, Object> tmpMap = new HashMap<String, Object>();
                MRP0501RDTO dt = msyData.get(x);
                tmpMap.put("datestring", dt.getDatestring()); // $$$
                tmpMap.put("name", dt.getName());
                tmpMap.put("code", dt.getCode());
                tmpMap.put("unit", dt.getUnit());
                tmpMap.put("area", dt.getArea());
                tmpMap.put("price", dt.getPrice());
                tmpMap.put("num", dt.getNum());
                tmpMap.put("pricetype", dt.getPricetype());
                tmpMap.put("err_price", dt.getErrPrice());
                tmpMap.put("city", dt.getCity());
                rsList.add(tmpMap);
            }

            String search_success = "0";
            if(msyData.size() > 0){
                search_success = "1";
            }
            String kind = sqlsize.substring(sqlsize.indexOf("rc.KIND = ")).replace("rc.KIND = ", "").trim();
            String KEYWORD_NAME = key_word + "^" + ddl_city + "^" + kind;
            Query qmck = Query
                    .builder()
                    .append("SELECT KEYWORD_AMOUNT AS cnt FROM MRP_COMMON_KEYWORD WHERE KEYWORD_NAME = :KEYWORD_NAME", KEYWORD_NAME)
                    .build();
            List<MRP0501RDTO> mck = sqlExecutor.queryForList(qmck, MRP0501RDTO.class);
            if(mck.size() > 0){
                int cnt = mck.get(0).getCnt();
                cnt++;
                Query qy = Query
                        .builder()
                        .append("UPDATE MRP_COMMON_KEYWORD SET")
                        .append("KEYWORD_AMOUNT = :KEYWORD_AMOUNT", cnt)
                        .build();
                sqlExecutor.update(qy);
            }else{
                Query qy = Query
                        .builder()
                        .append("INSERT INTO MRP_COMMON_KEYWORD (ACCOUNT_ID, BTN_NAME, KEYWORD_NAME, KEYWORD_AMOUNT, SEARCH_SUCCESS, CREATE_ID) ")
                        .append("VALUES (:ACCOUNT_ID, :BTN_NAME, :KEYWORD_NAME, :KEYWORD_AMOUNT, :SEARCH_SUCCESS, :CREATE_ID)",
                                ACCOUNT_ID, null, KEYWORD_NAME, 1, search_success, create_id)
                        .build();
                sqlExecutor.insert(qy);
            }

            //查詢時後端需寫入log // ?????
//          addRecord(request, "", "member", ACCOUNT_ID, "取用建議值", ip);//id & type(member & passer) 取得方式?

            System.out.println("CREATE XLS START");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Date now = new Date();
            String fn = sdf.format(now);
            String tp = "mrp" + mrpNo + "r_";
            // 開新檔案
            String path = PropUtil.getPropVal("mrp.file.savepath") + tp + fn + ".xls";
            Workbook wb = new HSSFWorkbook();
            // 設定儲存格資料
            Sheet sheet = wb.createSheet();
            Row row = null;
            Cell cell = null;

            for (int r = 0; r < (msyData.size() + 2); r++) {
                row = sheet.createRow(r);
                if (r < 2) {
                    if (r < 1) {
                        cell = row.createCell(0);
                        cell.setCellValue("公共工程價格資料庫(常用查詢)");
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
                    } else {
                        for (int c = 0; c < 8; c++) {
                            cell = row.createCell(c);
                            if (c == 0) {
                                cell.setCellValue("工項名稱");
                            } else if (c == 1) {
                                cell.setCellValue("工項編碼");
                            } else if (c == 2) {
                                cell.setCellValue("單位");
                            } else if (c == 3) {
                                cell.setCellValue("所屬區域");
                            } else if (c == 4) {
                                cell.setCellValue("價格類型");
                            } else if (c == 5) {
                                cell.setCellValue("平均價格");
                            } else if (c == 6) {
                                cell.setCellValue("標準差");
                            } else if (c == 7) {
                                cell.setCellValue("樣本數量");
                            }
                        }
                    }
                } else {
                    for (int c = 0; c < 8; c++) {
                        cell = row.createCell(c);
                        MRP0501RDTO item = msyData.get(r - 2);
                        if (c == 0) {
                            cell.setCellValue(item.getName());
                        } else if (c == 1) {
                            cell.setCellValue(item.getCode());
                        } else if (c == 2) {
                            cell.setCellValue(item.getUnit());
                        } else if (c == 3) {
                            cell.setCellValue(item.getArea());
                        } else if (c == 4) {
                            cell.setCellValue(item.getPricetype());
                        } else if (c == 5) {
                            cell.setCellValue(item.getPrice());
                        } else if (c == 6) {
                            cell.setCellValue(item.getErrPrice());
                        } else if (c == 7) {
                            cell.setCellValue(item.getNum());
                        }
                    }
                }
            }

            row = sheet.createRow(msyData.size() + 3);
            cell = row.createCell(0);
            cell.setCellValue("搜尋關鍵字");
            cell = row.createCell(1);
            cell.setCellValue("搜尋縣市");
            cell = row.createCell(2);
            cell.setCellValue("搜尋工程規模");
            cell = row.createCell(3);
            cell.setCellValue("搜尋工程類型");
            cell = row.createCell(4);
            cell.setCellValue("搜尋時間區間");

            row = sheet.createRow(msyData.size() + 4);
            cell = row.createCell(0);
            cell.setCellValue(key_word);
            cell = row.createCell(1);
            cell.setCellValue(ddl_city);
            cell = row.createCell(2);
            cell.setCellValue(ddl_size);
            cell = row.createCell(3);
            cell.setCellValue(ddl_type);
            cell = row.createCell(4);
            cell.setCellValue(startime + "~" + endtime);

            row = sheet.createRow(msyData.size() + 6);
            cell = row.createCell(0);
            cell.setCellValue("提醒：1.本資料庫不提供依物價指數調整後的價格資訊，使用者可依需求自行計算調整。");
            sheet.addMergedRegion(new CellRangeAddress(msyData.size() + 6, msyData.size() + 6, 0, 7));
            row = sheet.createRow(msyData.size() + 7);
            cell = row.createCell(0);
            cell.setCellValue("2.樣本數較少時，使用者應注意其平均價格與標準差兩者數據之差異，不宜逕行參考引用。");
            sheet.addMergedRegion(new CellRangeAddress(msyData.size() + 7, msyData.size() + 7, 0, 7));
            row = sheet.createRow(msyData.size() + 8);
            cell = row.createCell(0);
            cell.setCellValue("3.使用者請依使用目的，並考量工程規模、性質、規範要求、施工地點及工期等因素，配合工程專業判斷，予以調整，以符實際個案需求。");
            sheet.addMergedRegion(new CellRangeAddress(msyData.size() + 8, msyData.size() + 8, 0, 7));


            // 寫出 Excel
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            fos.flush();
            fos.close();
            String fname = tp + fn + ".xls";
            rs.put("filename", fname);

            rs.put("status", "0");
            rs.put("response", rsList);
        }catch(Exception e){
            e.printStackTrace();
            rs.put("status", "0");
            rs.put("message", "產EXCEL檔案發生錯誤");
        }
        return rs;
    }

    @Override
    public Map<String, Object> queryBasicDetail(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, HttpServletRequest request) {
        String sqlcity = getCity_sqlstr(ddl_city);
        String sqltime = getTime_sqlstr(startime, endtime);
        String sqlsize = getSize_sqlstr(ddl_size);
        String sqltype = getType_sqlstr(ddl_type);
        String backtime_str = getAdvanceBacktime_sqlstr(backtime);
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT tmp.ID, tmp.GETCREATETIME, tmp.DESCRIPTION_TW, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, ROUND(tmp.PRICE,0)AS PRICE, tmp.STATUS, ")
                .append("tmp.CONTRACT_TITLE_TW, tmp.MAIN_ORG_NAME, tmp.CREATE_DATE, tmp.FILE_NAME, tmp.LOCAL_ZIP_FILE, tmp.KIND AS PRICETYPE ")
                .append("FROM ( SELECT cl.ID, CONVERT(NVARCHAR(8), cl.AWARD_BUDGET_DATE, 111) + '01' AS GETCREATETIME, rl.DESCRIPTION_TW, ")
                .append("rc.NAME, rc.RESOURCE_ID, rc.CODE, rc.UNIT , ti.CITY AS AREA, rc.PRICE, rc.STATUS, rc.USED, ")
                .append("ti.CONTRACT_TITLE_TW, cl.MAIN_ORG_NAME, ti.CREATE_DATE, ti.FILE_NAME, ti.LOCAL_ZIP_FILE ,rc.KIND ")
                .append("FROM MRP_PROJECT_LIST cl, MRP_TENDERINFO ti, MRP_VERIFY_RESOURCE_CODE rc, MRP_TW_CITY tc, MRP_RESOURCE_LIST rl , ")
                .append("MRP_TENDERINFO_PROJECTCODE tip,MRP_TENDERINFO_TW_CITY tic")
                .append("WHERE rc.FILESOURCE_ID = ti.FILESOURCE_ID AND cl.FILESOURCE_ID = ti.FILESOURCE_ID AND rc.RESOURCE_ID = rl.ID")
                .append("AND ti.FILESOURCE_ID = tip.FILESOURCE_ID AND ti.FILESOURCE_ID = tic.FILESOURCE_ID AND tic.TAIWAN_CITY_ID = tc.ID AND rc.UNIT != '' AND rc.PRICE > 0 ")
                .append(sqltime + sqlsize + sqlcity + sqltype + backtime_str)
                .append(") AS tmp WHERE tmp.USED = 1  AND CODE=:code AND GETCREATETIME=CONCAT(:time ,'/01') AND AREA = :area AND UNIT = :unit", code, time, area, unit)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            Instant cd = dt.getCreateDate();
            Date myDate = Date.from(cd);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = formatter.format(myDate);
            String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];

            tmpMap.put("id", dt.getId());
            tmpMap.put("name", dt.getName());
            tmpMap.put("code", dt.getCode());
            tmpMap.put("unit", dt.getUnit());
            tmpMap.put("area", dt.getArea());
            tmpMap.put("priceType", dt.getPricetype());
            tmpMap.put("price", dt.getPrice());
            tmpMap.put("getcreatetime", dt.getGetcreatetime());
            tmpMap.put("description_tw", dt.getDescriptionTw());
            tmpMap.put("contract_Title_tw", dt.getContractTitleTw());
            tmpMap.put("main_org_name", dt.getMainOrgName());
            tmpMap.put("create_date", ymd);
            tmpMap.put("file_Name", dt.getFileName());
            tmpMap.put("local_Zip_File", dt.getLocalZipFile());
            tmpMap.put("status", dt.getStatus());
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryBasic108(String ddl_city, String ddl_size, String ddl_type, String key_word, String startime, String endtime, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        String sqltime = getTime_sqlstr(startime, endtime);
        String sqlsize = getSize_sqlstr(ddl_size);
        String sqlcity = getCity_sqlstr(ddl_city);
        String sqlkeyword = getKeyword_sqlstr(key_word); // getKeyword_sqlstr_109(key_word); // $$$
        String sqltype = getType_sqlstr(ddl_type);

        Query msyQry = Query
                .builder()
                .append("SELECT GETCREATETIME, NAME, CODE, UNIT, AREA, PRICE, NUM, ERR_PRICE, KIND AS PRICETYPE ,STATUS FROM (")
                .append("SELECT tmp.GETCREATETIME, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, tmp.STATUS, ")
                .append("ROUND(AVG(tmp.PRICE),0) AS PRICE, COUNT(tmp.PRICE) AS NUM, ROUND(STDEVP(tmp.PRICE), 0) AS ERR_PRICE, ")
                .append("ROW_NUMBER() OVER(ORDER BY tmp.GETCREATETIME) AS ROW , tmp.KIND FROM (")
                .append("SELECT CONVERT(NVARCHAR(8),cl.AWARD_BUDGET_DATE,111)+'01' AS GETCREATETIME, ")
                .append("rc.NAME, rc.RESOURCE_ID, rc.CODE, rc.UNIT, ti.CITY AS AREA, rc.PRICE AS PRICE, rc.STATUS, rc.USED, rc.KIND ")
                .append("FROM MRP_PROJECT_LIST cl, MRP_TENDERINFO ti, MRP_VERIFY_RESOURCE_CODE rc, MRP_TW_CITY tc, MRP_TENDERINFO_PROJECTCODE tip, MRP_TENDERINFO_TW_CITY tic ")
                .append("WHERE rc.FILESOURCE_ID = ti.FILESOURCE_ID AND cl.FILESOURCE_ID = ti.FILESOURCE_ID AND ti.FILESOURCE_ID = tic.FILESOURCE_ID ")
                .append("AND tic.TAIWAN_CITY_ID = tc.ID AND ti.FILESOURCE_ID = tip.FILESOURCE_ID ")
                .append(sqltime + sqlsize + sqlcity + sqlkeyword + sqltype)
                .append(") AS tmp WHERE 1 = 1")
                .append("GROUP BY tmp.GETCREATETIME, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, tmp.KIND, tmp.STATUS) AS tmp WHERE 1=1")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("status", dt.getStatus());
            tmpMap.put("getcreatetime", dt.getGetcreatetime());
            tmpMap.put("name", dt.getName());
            tmpMap.put("unit", dt.getUnit());
            tmpMap.put("area", dt.getArea());
            tmpMap.put("price", dt.getPrice());
            tmpMap.put("num", dt.getNum());
            tmpMap.put("pricetype", dt.getPricetype());

            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryBasicDetail108(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        String sqltime = getTime_sqlstr(startime, endtime);
        String sqlsize = getSize_sqlstr(ddl_size);
        String sqlcity = getCity_sqlstr(ddl_city);
        String sqltype = getType_sqlstr(ddl_type);

        Query msyQry = Query
                .builder()
                .append("SELECT tmp.GETCREATETIME ,tmp.DESCRIPTION_TW, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, ROUND(tmp.PRICE,0) AS PRICE, tmp.STATUS, ")
                .append("tmp.CONTRACT_TITLE_TW, tmp.MAIN_ORG_NAME, tmp.CREATE_DATE, tmp.FILE_NAME, tmp.LOCAL_ZIP_FILE, tmp.KIND AS PRICETYPE ")
                .append("FROM (SELECT CONVERT(NVARCHAR(8),cl.AWARD_BUDGET_DATE,111)+'01' AS GETCREATETIME,rl.DESCRIPTION_TW, ")
                .append("rc.NAME, rc.RESOURCE_ID, rc.CODE, rc.UNIT , ti.CITY AS AREA, rc.PRICE ,rc.STATUS ,rc.USED, ")
                .append("ti.CONTRACT_TITLE_TW, cl.MAIN_ORG_NAME, ti.CREATE_DATE, ti.FILE_NAME, ti.LOCAL_ZIP_FILE, rc.KIND ")
                .append("FROM MRP_PROJECT_LIST cl, MRP_TENDERINFO ti, MRP_VERIFY_RESOURCE_CODE_ERROR rc, MRP_TW_CITY tc, MRP_RESOURCE_LIST rl, ")
                .append("MRP_TENDERINFO_PROJECTCODE tip ,MRP_TENDERINFO_TW_CITY tic ")
                .append("WHERE rc.FILESOURCE_ID = ti.FILESOURCE_ID AND cl.FILESOURCE_ID = ti.FILESOURCE_ID AND rc.RESOURCE_ID = rl.ID")
                .append("AND ti.FILESOURCE_ID = tip.FILESOURCE_ID AND ti.FILESOURCE_ID = tic.FILESOURCE_ID AND tic.TAIWAN_CITY_ID = tc.ID")
                .append(sqltime + sqlsize + sqlcity + sqltype)
                .append(") AS tmp WHERE 1 = 1 AND CODE = :CODE AND GETCREATETIME=CONCAT(:TIME, '/01') AND AREA = :AREA AND UNIT = :UNIT", code, time, area, unit)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            Instant cd = dt.getCreateDate();
            Date myDate = Date.from(cd);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = formatter.format(myDate);
            String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];

            tmpMap.put("id", dt.getId());
            tmpMap.put("name", dt.getName());
            tmpMap.put("code", dt.getCode());
            tmpMap.put("unit", dt.getUnit());
            tmpMap.put("area", dt.getArea());
            tmpMap.put("priceType", dt.getPricetype());
            tmpMap.put("price", dt.getPrice());
            tmpMap.put("getcreatetime", dt.getGetcreatetime());
            tmpMap.put("description_tw", dt.getDescriptionTw());
            tmpMap.put("contract_Title_tw", dt.getContractTitleTw());
            tmpMap.put("main_org_name", dt.getMainOrgName());
            tmpMap.put("create_date", ymd);
            tmpMap.put("file_Name", dt.getFileName());
            tmpMap.put("local_Zip_File", dt.getLocalZipFile());
            tmpMap.put("status", dt.getStatus());
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryBasic109(String ddl_city, String ddl_size, String ddl_type, String key_word, String startime, String endtime, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try{
            String sqltime = "";
            if (startime != null && !startime.equals("") && endtime != null && !endtime.equals("")) {
                sqltime = getTime_sqlstr(startime, endtime);
            }
            String sqlsize = getSize_sqlstr(ddl_size).replaceAll("KIND", "ITEM_KIND");
            String sqlcity = getCity_sqlstr(ddl_city);
            String sqlkeyword = getKeyword_sqlstr_109(key_word).replaceAll("ITEMKEY", "ITEM_KEY");
            String sqltype = getType_sqlstr(ddl_type);
//        String sqlTown = getTownSqlstr(ddl_town);

            Query msyQry = Query
                    .builder()
                    .append("SELECT  CONVERT(NVARCHAR(8),cl.AWARD_BUDGET_DATE,111)+'01' AS GETCREATETIME, rc.DESCRIPTION_TW,rc.ITEM_KEY, rc.UNIT_TW , ti.CITY AS AREA,")
                    .append("ROUND( AVG(PRICE),0) AS PRICE ,tif.DOCUMENT_TYPE AS PRICETYPE , COUNT(PRICE) AS NUM")
                    .append("FROM MRP_PROJECT_LIST cl, MRP_TENDERINFO ti,MRP_TENDERINFO_TW_CITY tic, MRP_TW_CITY tc,MRP_TENDERINFO_PROJECTCODE tip ,MRP_TENDERINFO_FILE tif , MRP_RESOURCE_LIST rc")
//                .appendWhen(town_id != null, ", MRP_PROJECT_LIST_TOWN ctt, MRP_TW_TOWN tt")
                    .append("WHERE  cl.FILESOURCE_ID = ti.FILESOURCE_ID AND ti.FILESOURCE_ID = tic.FILESOURCE_ID AND tic.TAIWAN_CITY_ID = tc.ID")
                    .append("AND ti.FILESOURCE_ID = tip.FILESOURCE_ID AND ti.FILESOURCE_ID = tif.FILESOURCE_ID")
                    .append("AND rc.FILESOURCE_ID = tif.FILESOURCE_ID  AND rc.TENDER_INFO_FILE_ID = tif.ID")
//                .appendWhen(town_id != null," AND ctt.FILESOURCE_ID = cl.FILESOURCE_ID AND ctt.TAIWAN_TOWN_ID  = tt.TOWN_ID")
                    .append(sqltime + sqlsize + sqlcity + sqlkeyword + sqltype)// + sqlTown)
                    .append("GROUP BY CONVERT(NVARCHAR(8),cl.AWARD_BUDGET_DATE,111)+'01' , rc.DESCRIPTION_TW,rc.ITEM_KEY, rc.UNIT_TW , ti.CITY  ,tif.DOCUMENT_TYPE")
                    .build();
            List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
            List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
            for (int x = 0; x < msyData.size(); x++) {
                Map<String, Object> tmpMap = new HashMap<String, Object>();
                MRP0501RDTO dt = msyData.get(x);
                tmpMap.put("status", dt.getStatus());
                tmpMap.put("getcreatetime", dt.getGetcreatetime());
                tmpMap.put("name", dt.getName());
                tmpMap.put("unit", dt.getUnit());
                tmpMap.put("area", dt.getArea());
                tmpMap.put("price", dt.getPrice());
                tmpMap.put("num", dt.getNum());
                tmpMap.put("pricetype", dt.getPricetype());

                rsList.add(tmpMap);
            }
            //查詢時後端需寫入log
//            addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?

            System.out.println("CREATE XLS START");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Date now = new Date();
            String fn = sdf.format(now);
            String tp = "mrp0109r_";
            // 開新檔案
            String path = PropUtil.getPropVal("mrp.file.savepath") + tp + fn + ".xls";
            Workbook wb = new HSSFWorkbook();
            // 設定儲存格資料
            Sheet sheet = wb.createSheet();
            Row row = null;
            Cell cell = null;

            for (int r = 0; r < (msyData.size() + 2); r++) {
                row = sheet.createRow(r);
                if (r < 2) {
                    if (r < 1) {
                        cell = row.createCell(0);
                        cell.setCellValue("解析前原始工項");
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
                    } else {
                        for (int c = 0; c < 8; c++) {
                            cell = row.createCell(c);
                            if (c == 0) {
                                cell.setCellValue("決標時間");
                            } else if (c == 1) {
                                cell.setCellValue("原描述");
                            } else if (c == 2) {
                                cell.setCellValue("原編碼");
                            } else if (c == 3) {
                                cell.setCellValue("單位");
                            } else if (c == 4) {
                                cell.setCellValue("所屬區域");
                            } else if (c == 5) {
                                cell.setCellValue("平均價格");
                            } else if (c == 6) {
                                cell.setCellValue("價格類型");
                            } else if (c == 7) {
                                cell.setCellValue("樣本數量");
                            }
                        }
                    }
                } else {
                    for (int c = 0; c < 8; c++) {
                        cell = row.createCell(c);
                        MRP0501RDTO item = msyData.get(r - 2);
                        if (c == 0) {
                            cell.setCellValue(item.getGetcreatetime().toString().substring(0, 10));
                        } else if (c == 1) {
                            cell.setCellValue(item.getName());
                        } else if (c == 2) {
                            cell.setCellValue(item.getUnit());
                        } else if (c == 3) {
                            cell.setCellValue(item.getArea());
                        } else if (c == 4) {
                            cell.setCellValue(item.getPrice());
                        } else if (c == 5) {
                            cell.setCellValue(item.getPrice());
                        } else if (c == 6) {
                            cell.setCellValue(item.getNum());
                        } else if (c == 7) {
                            cell.setCellValue(item.getPricetype());
                        }
                    }
                }
            }
            // 寫出 Excel
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            fos.flush();
            fos.close();
            String fname = tp + fn + ".xls";
            rs.put("filename", fname);

            rs.put("status", "0");
            rs.put("response", rsList);
        }catch(Exception e){
            e.printStackTrace();
            rs.put("status", "1");
            rs.put("message", "產Excel檔案發生錯誤");
        }
        return rs;
    }

    @Override
    public Map<String, Object> queryBasicDetail109(String ddl_city, String ddl_size, String ddl_type, String startime, String endtime, String backtime, String time, String code, String area, String unit, String des_tw, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        String sqlsize = getSize_sqlstr(ddl_size);
        String sqlcity = getCity_sqlstr(ddl_city);
        String sqltype = getType_sqlstr(ddl_type);

        Query msyQry = Query
                .builder()
                .append("SELECT CONVERT(NVARCHAR(8), cl.AWARD_BUDGET_DATE, 111)+'01' AS GETCREATETIME, rc.DESCRIPTION_TW, rc.ITEM_KEY, rc.UNIT_TW")
                .append(", ti.CITY as AREA, rc.PRICE, tif.DOCUMENT_TYPE as PRICETYPE, cl.MAIN_ORG_NAME, ti.CONTRAC_TTITLE_TW")
                .append("FROM MRP_PROJECT_LIST cl, MRP_TENDERINFO ti, MRP_TENDERINFO_TW_CITY tic, MRP_TW_CITY tc, MRP_TENDERINFO_PROJECTCODE tip")
                .append(", MRP_TENDERINFO_FILE tif, MRP_RESOURCE_LIST rc")
                .append("WHERE cl.FILESOURCE_ID = ti.FILESOURCE_ID AND ti.FILESOURCE_ID = tic.FILESOURCE_ID AND tic.TAIWAN_CITY_ID = tc.ID")
                .append("AND ti.FILESOURCE_ID = tip.FILESOURCE_ID AND ti.FILESOURCE_ID = tif.FILESOURCE_ID")
                .append("AND rc.FILESOURCE_ID = ti.FILESOURCE_ID AND rc.TENDER_INFO_FILE_ID = tif.ID")
                .append(sqlsize + sqlcity + sqltype)
                .append("AND rc.DESCRIPTION_TW LIKE'%" + des_tw + "%' AND CONVERT(NVARCHAR(8),cl.AWARD_BUDGET_DATE,111)+'01' =CONCAT(" + time + ",'/01') AND tc.NAME LIKE '%" + area + "%' AND rc.UNIT_TW = '" + unit + "' AND rc.ITEM_KEY ='" + code + "'")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            Instant cd = dt.getCreateDate();
            Date myDate = Date.from(cd);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = formatter.format(myDate);
            String ymd = String.valueOf(Integer.parseInt(formattedDate.split("/")[0]) - 1911) + "/" + formattedDate.split("/")[1] + "/" + formattedDate.split("/")[2];

            tmpMap.put("id", dt.getId());
            tmpMap.put("name", dt.getName());
            tmpMap.put("code", dt.getCode());
            tmpMap.put("unit", dt.getUnit());
            tmpMap.put("area", dt.getArea());
            tmpMap.put("priceType", dt.getPricetype());
            tmpMap.put("price", dt.getPrice());
            tmpMap.put("getcreatetime", dt.getGetcreatetime());
            tmpMap.put("description_tw", dt.getDescriptionTw());
            tmpMap.put("contract_Title_tw", dt.getContractTitleTw());
            tmpMap.put("main_org_name", dt.getMainOrgName());
            tmpMap.put("create_date", ymd);
            tmpMap.put("file_Name", dt.getFileName());
            tmpMap.put("local_Zip_File", dt.getLocalZipFile());
            tmpMap.put("status", dt.getStatus());
            rsList.add(tmpMap);
        }
        //查詢時後端需寫入log
//        addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMainInfo() {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Query sql0 = Query
                    .builder()
                    .append("SELECT DISTINCT CREATE_DATE AS createDate FROM MRP_VERIFY_RESOURCE_CODE ORDER BY CREATE_DATE DESC")
                    .build();
            List<MRP0501RDTO> r0 = sqlExecutor.queryForList(sql0, MRP0501RDTO.class);
            List<String> createDateList = new ArrayList<String>();
            for (int x = 0; x < r0.size(); x++) {
                Instant cDate = r0.get(x).getCreateDate();
                Date d0 = Date.from(cDate);
                String d0Str = formatter.format(d0);
                String createDate = String.valueOf(Integer.parseInt(d0Str.substring(0, 4)) - 1911) + d0Str.substring(4);
                createDateList.add(createDate);
            }
            Query sql1 = Query
                    .builder()
                    .append("SELECT MAX(CREATE_DATE) AS createDate FROM MRP_VERIFY_RESOURCE_CODE")
                    .build();
            List<MRP0501RDTO> r1 = sqlExecutor.queryForList(sql1, MRP0501RDTO.class);
            Instant dbUpdatedDate = r1.get(0).getCreateDate();
            Date d1 = Date.from(dbUpdatedDate);
            String d1Str = formatter.format(d1);
            String db_updated_date = String.valueOf(Integer.parseInt(d1Str.substring(0, 4)) - 1911) + d1Str.substring(4);
            String budget_smonth = String.valueOf(Integer.parseInt(d1Str.substring(0, 4)) - 1912) + "/"+ d1Str.split("-")[1]; // $$$
            String budget_emonth = String.valueOf(Integer.parseInt(d1Str.substring(0, 4)) - 1911) + "/"+ d1Str.split("-")[1]; // $$$

            Query sql2 = Query
                    .builder()
                    .append("SELECT MAX(AWARD_BUDGET_DATE) AS awardBudgetDate FROM MRP_PROJECT_LIST")
                    .build();
            List<MRP0501RDTO> r2 = sqlExecutor.queryForList(sql2, MRP0501RDTO.class);
            LocalDate lastBudgetDate = r2.get(0).getAwardBudgetDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedString = lastBudgetDate.format(fm);
            Date d2 = sdf.parse(formattedString);
            String d2Str = formatter.format(d2);
            String data_range = "104-05-01 - " + String.valueOf(Integer.parseInt(d2Str.substring(0, 4)) - 1911) + d2Str.substring(4);
            List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("db_updated_date", db_updated_date);
            tmpMap.put("data_range", data_range);
            tmpMap.put("budget_smonth", budget_smonth);
            tmpMap.put("budget_emonth", budget_emonth);
            tmpMap.put("create_date_list", createDateList);
            rsList.add(tmpMap);
            rs.put("status", "0");
            rs.put("response", rsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpTown() {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT CITY_ID, TOWN_ID, NAME FROM MRP_TW_TOWN ORDER BY POSTAL")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("CITY_ID", dt.getCityId());
            tmpMap.put("TOWN_ID", dt.getTownId());
            tmpMap.put("NAME", dt.getName());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpCity() {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT ID, NAME FROM MRP_TW_CITY WHERE id != -1 AND id != 18 ORDER BY RANK")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int y = 0; y < 5; y++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            if (y == 0) {
                tmpMap.put("id", ""); // $$$
                tmpMap.put("text", "全選");
                tmpMap.put("value", "");
            } else if (y == 1) {
                tmpMap.put("id", "1,2,3,13,19,20,22"); // $$$
                tmpMap.put("text", "北部地區");
                tmpMap.put("value", "北部地區");
            } else if (y == 2) {
                tmpMap.put("id", "4,5,6,7,8"); // $$$
                tmpMap.put("text", "中部地區");
                tmpMap.put("value", "中部地區");
            } else if (y == 3) {
                tmpMap.put("id", "9,10,11,12,23"); // $$$
                tmpMap.put("text", "南部地區");
                tmpMap.put("value", "南部地區");
            } else if (y == 4) {
                tmpMap.put("id", "14,15"); // $$$
                tmpMap.put("text", "東部地區");
                tmpMap.put("value", "東部地區");
            }
//            tmpMap.put("id", "");  // $$$
            rsList.add(tmpMap);
        }
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("id", dt.getId());
            tmpMap.put("text", dt.getName());
            tmpMap.put("value", dt.getName());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryItemCode() {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
//                .append("SELECT code, name FROM MRP_ITEM_CODE x WHERE x.code != -1 AND x.code != 30")
                .append("SELECT CODE, NAME FROM MRP_ITEM_CODE WHERE NAME NOT IN (N'無', N'其他工程') UNION ALL SELECT 0, N'全選' UNION ALL SELECT 999999, N'其他工程' ORDER BY 1;")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
//        Map<String, Object> tmpMap0 = new HashMap<String, Object>();
//        tmpMap0.put("name", "全選");
//        tmpMap0.put("code", "");
//        rsList.add(tmpMap0);
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("name", dt.getName());
            tmpMap.put("code", dt.getCode());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryAdmSysCode() {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT DATA_KEY AS CODE, VALUE AS NAME FROM ADM_SYS_CODE WHERE MODULE_TYPE = 'MRP' AND DATA_TYPE = 'ddl_size' ORDER BY DATA_KEY, SORT_NO")
                .build();

        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("DATA_KEY", dt.getName());
            tmpMap.put("VALUE", dt.getCode());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public String downloadExcel_0121R(String start_date, String end_date) {
        String rs = "";
        try {
            Query msyQry = Query
                    .builder()
                    .append("SELECT CODE , NAME, UNIT, budget, contract")
                    .append("from ( select CODE, DOCUMENT_TYPE, min(ADATA.NAME)	AS NAME, min(ADATA.UNIT)	AS UNIT, avg(ADATA.PRICE)	AVG_PRICE")
                    .append("from ( select * from ( select VRC.CODE, TIF.DOCUMENT_TYPE, VRC.NAME, VRC.UNIT, VRC.PRICE")
                    .append(", AVG(VRC.PRICE) over (partition by  VRC.CODE, TIF.DOCUMENT_TYPE) AS AVG_PRICE, STDEVP(VRC.PRICE) over (partition by  VRC.CODE, TIF.DOCUMENT_TYPE) AS ST_DEVP")
                    .append("from MRP_VERIFY_RESOURCE_CODE VRC  join MRP_RESOURCE_LIST RL on RL.ID = VRC.RESOURCE_ID")
                    .append("join MRP_TENDERINFO_FILE TIF on TIF.FILESOURCE_ID = VRC.FILESOURCE_ID join MRP_PROJECT_LIST CL on CL.FILESOURCE_ID = VRC.FILESOURCE_ID")
                    .append("and cl.AWARD_BUDGET_DATE   >= :start_date", start_date)
                    .append("and cl.AWARD_BUDGET_DATE   >= DateAdd(year, -1, :end_date)", end_date)
                    .append("and cl.AWARD_BUDGET_DATE < :end_date", end_date)
                    .append("where VRC.unit != '' and VRC.Price > 0 and exists ( select 1 from ( select DISTINCT VRC.CODE")
                    .append("from MRP_VERIFY_RESOURCE_CODE VRC join MRP_PROJECT_LIST CL on CL.FILESOURCE_ID = VRC.FILESOURCE_ID")
                    .append("and cl.AWARD_BUDGET_DATE   >= :start_date", start_date)
                    .append("and cl.AWARD_BUDGET_DATE   >= DateAdd(year, -1, :end_date)", end_date)
                    .append("and cl.AWARD_BUDGET_DATE <  :end_date", end_date)
                    .append("where VRC.unit != '' and VRC.Price > 0 and VRC.used =1 ) vLast1Code where   vLast1Code.CODE = VRC.CODE ) ) AS vLast1Data union all select * from (")
                    .append("select VRC.CODE, TIF.DOCUMENT_TYPE, VRC.NAME, VRC.UNIT, VRC.PRICE")
                    .append(", AVG(VRC.PRICE) over (partition by  VRC.CODE, TIF.DOCUMENT_TYPE) AS AVG_PRICE, STDEVP(VRC.PRICE) over (partition by  VRC.CODE, TIF.DOCUMENT_TYPE) AS ST_DEVP")
                    .append("from MRP_VERIFY_RESOURCE_CODE VRC join MRP_RESOURCE_LIST RL on RL.ID = VRC.RESOURCE_ID")
                    .append("join MRP_TENDERINFO_FILE TIF on TIF.FILESOURCE_ID = VRC.FILESOURCE_ID join MRP_PROJECT_LIST CL on CL.FILESOURCE_ID = VRC.FILESOURCE_ID")
                    .append("and cl.AWARD_BUDGET_DATE   >= :start_date", start_date)
                    .append("and cl.AWARD_BUDGET_DATE   >= DateAdd(year, -1, :end_date)", end_date)
                    .append("and cl.AWARD_BUDGET_DATE <  :end_date", end_date)
                    .append("where VRC.unit != ''	and VRC.Price > 0 and exists ( select 1 from ( select DISTINCT VRC.CODE")
                    .append("from MRP_VERIFY_RESOURCE_CODE VRC join MRP_PROJECT_LIST CL on CL.FILESOURCE_ID = VRC.FILESOURCE_ID")
                    .append("and cl.AWARD_BUDGET_DATE   >= :start_date", start_date)
                    .append("and cl.AWARD_BUDGET_DATE   >= DateAdd(year, -3, :end_date)", end_date)
                    .append("and cl.AWARD_BUDGET_DATE < DateAdd(year, -1, :end_date)", end_date)
                    .append("where VRC.unit != '' and VRC.Price > 0 and VRC.used =1")
                    .append("and not exists ( select 1 from ( select DISTINCT VRC.CODE	")
                    .append("from MRP_VERIFY_RESOURCE_CODE VRC join MRP_PROJECT_LIST CL on CL.FILESOURCE_ID = VRC.FILESOURCE_ID")
                    .append("and cl.AWARD_BUDGET_DATE   >= :start_date", start_date)
                    .append("and cl.AWARD_BUDGET_DATE   >= DateAdd(year, -1,:end_date)", end_date)
                    .append("and cl.AWARD_BUDGET_DATE < :end_date", end_date)
                    .append("where VRC.unit != '' and VRC.Price > 0 and VRC.used =1")
                    .append(") AS vLast1Code  where vLast1Code.CODE = VRC.CODE ) ) AS vLast23Code where vLast23Code.CODE = VRC.CODE ) ) AS vLast23Data ) ADATA")
                    .append("where PRICE between AVG_PRICE - ST_DEVP and  AVG_PRICE + ST_DEVP group by CODE, DOCUMENT_TYPE ) GDATA")
                    .append("pivot (sum(AVG_PRICE) for DOCUMENT_TYPE in (budget, contract) ) as PVT")
                    .build();
            List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
            //查詢時後端需寫入log
	        // $$$ addRecord(request, "", "member", "", "取用建議值", ip);//id & type(member & passer) 取得方式?
            System.out.println("CREATE XLS START");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Date now = new Date();
            String fn = sdf.format(now);
            String tp = "mrp0121r_";
            // 開新檔案
            String path = PropUtil.getPropVal("mrp.file.savepath") + tp + fn + ".xls";
            Workbook wb = new HSSFWorkbook();
            // 設定儲存格資料
            Sheet sheet = wb.createSheet();
            Row row = null;
            Cell cell = null;

            for (int r = 0; r < (msyData.size() + 2); r++) {
                row = sheet.createRow(r);
                if (r < 2) {
                    if (r < 1) {
                        cell = row.createCell(0);
                        cell.setCellValue("公共工程價格資料庫(參考單價設定)");
                        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
                    } else {
                        for (int c = 0; c < 6; c++) {
                            cell = row.createCell(c);
                            if (c == 0) {
                                cell.setCellValue("序號");
                            } else if (c == 1) {
                                cell.setCellValue("工項代碼");
                            } else if (c == 2) {
                                cell.setCellValue("工項名稱");
                            } else if (c == 3) {
                                cell.setCellValue("單位");
                            } else if (c == 4) {
                                cell.setCellValue("預算平均單價");
                            } else if (c == 5) {
                                cell.setCellValue("決標平均單價");
                            }
                        }
                    }
                } else {
                    for (int c = 0; c < 7; c++) {
                        cell = row.createCell(c);
                        MRP0501RDTO item = msyData.get(r - 2);
                        if (c == 0) {
                            cell.setCellValue(r - 1);
                        } else if (c == 1) {
                            cell.setCellValue(item.getCode());
                        } else if (c == 2) {
                            cell.setCellValue(item.getName());
                        } else if (c == 3) {
                            cell.setCellValue(item.getUnit());
                        } else if (c == 4) {
                            cell.setCellValue(item.getBudget());
                        } else if (c == 5) {
                            cell.setCellValue(item.getContract());
                        }
                    }
                }
            }
            // 寫出 Excel
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            fos.flush();
            fos.close();
            rs = tp + fn + ".xls";
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Map<String, Object> mrp0118R(MultipartFile XLS, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(XLS.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumbers = sheet.getLastRowNum() + 1;
            int breakCol = 3;
            for (int c = 3; c < 18; c++) {
                String t = sheet.getRow(1).getCell(c).toString();
                if (t.indexOf("備註") > -1) {
                    breakCol = c;
                    break;
                }
            }
            Query query = Query
                    .builder()
                    .append("DELETE FROM MRP_MRSBASEA_TEMP")
                    .build();
            sqlExecutor.delete(query);
            String MAIN_CODE = "", SIGN = "", NAME ="";
            if(null != sheet.getRow(2).getCell(0)) {
                MAIN_CODE = sheet.getRow(2).getCell(0).toString();
            }
            if(null != sheet.getRow(2).getCell(1)) {
                SIGN = sheet.getRow(2).getCell(1).toString();
            }
            if(null != sheet.getRow(2).getCell(2)) {
                NAME = sheet.getRow(2).getCell(2).toString();
            }
            for (int r = 3; r < rowNumbers; r++) {
                Row row = sheet.getRow(r);
                String CODE6 = "", NAME6 = "", CODE7 = "", NAME7 = "", CODE8 = "", NAME8 = "",
                        CODE9 = "", NAME9 = "", CODE10 = "", NAME10 = "", CODE11 = "", NAME11 = "", CODE12 = "", NAME12 = "";
                for (int cc = 3; cc < breakCol; cc++) {
                    if(null != row.getCell(cc)) {
                        switch (cc) {
                            case 3:
                                CODE6 = row.getCell(cc).toString();
                                break;
                            case 4:
                                NAME6 = row.getCell(cc).toString();
                                break;
                            case 5:
                                CODE7 = row.getCell(cc).toString();
                                break;
                            case 6:
                                NAME7 = row.getCell(cc).toString();
                                break;
                            case 7:
                                CODE8 = row.getCell(cc).toString();
                                break;
                            case 8:
                                NAME8 = row.getCell(cc).toString();
                                break;
                            case 9:
                                CODE9 = row.getCell(cc).toString();
                                break;
                            case 10:
                                NAME9 = row.getCell(cc).toString();
                                break;
                            case 11:
                                CODE10 = row.getCell(cc).toString();
                                break;
                            case 12:
                                NAME10 = row.getCell(cc).toString();
                                break;
                            case 13:
                                CODE11 = row.getCell(cc).toString();
                                break;
                            case 14:
                                NAME11 = row.getCell(cc).toString();
                                break;
                            case 15:
                                CODE12 = row.getCell(cc).toString();
                                break;
                            case 16:
                                NAME12 = row.getCell(cc).toString();
                                break;
                            default:
                                break;
                        }
                    }
                    Query qy = Query
                            .builder()
                            .append("INSERT INTO MRP_MRSBASEA_TEMP (ROW_NUM, MAIN_TITLE_CODE, MAIN_CODE, CODE6, NAME6, CODE7, NAME7, CODE8, NAME8, CODE9, NAME9, CODE10, NAME10, CODE11, NAME11, CODE12, NAME12)")
                            .append("VALUES ((select count(*)+1 from MRP_MRSBASEA_TEMP), :MAIN_TITLE_CODE, :MAIN_CODE, :CODE6, :NAME6, :CODE7, :NAME7, :CODE8, :NAME8, :CODE9, :NAME9, :CODE10, :NAME10, :CODE11, :NAME11, :CODE12, :NAME12)",
                                    SIGN, MAIN_CODE, CODE6, NAME6, CODE7, NAME7, CODE8, NAME8, CODE9, NAME9, CODE10, NAME10, CODE11, NAME11, CODE12, NAME12)
                            .build();
                    sqlExecutor.insert(qy);
                    Query qy2 = Query
                            .builder()
                            .append("SELECT :SIGN + :MAIN_CODE + ISNULL(CODE6, '') + ISNULL(CODE7, '') + ISNULL(CODE8, '') + ISNULL(CODE9, '') + ISNULL(CODE10, '') + ISNULL(CODE11, '') + ISNULL(CODE12, '')AS CODE ", SIGN, MAIN_CODE)
                            .append(", '污水管路系統' + CASE WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE7, NAME7 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE7 <> '') AS C7) > 0 AND ISNULL(NAME6, '') <>'' THEN '。'+NAME6 ELSE '' END ")
                            .append("+ CASE WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE8, NAME8 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE8 <> '') AS C8) > 0 AND ISNULL(NAME7, '') <>'' THEN '。'+NAME7 ELSE '' END ")
                            .append("+ CASE WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE9, NAME9 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE9 <> '') AS C9) > 0 AND ISNULL(NAME8, '') <>'' THEN '。'+NAME8 ELSE '' END ")
                            .append("+ CASE WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE10, NAME10 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE10 <> '') AS C10) > 0 AND ISNULL(NAME9, '') <>'' THEN '。'+NAME9 ELSE '' END ")
                            .append("+ CASE WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE11, NAME11 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE11 <> '') AS C11) > 0 AND ISNULL(NAME10, '') <>'' THEN '。'+NAME10 ELSE '' END ")
                            .append("+ CASE WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE12, NAME12 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE12 <> '') AS C12) > 0 AND ISNULL(NAME11, '') <>'' THEN '。'+NAME11 ELSE '' END AS NAME ")
                            .append(", CASE WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE12, NAME12 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE12 <> '') AS C12) > 0 THEN NAME12 ")
                            .append("WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE11, NAME11 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE11 <> '') AS C11) > 0 THEN NAME11 ", MAIN_CODE)
                            .append("WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE10, NAME10 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE10 <> '') AS C10) > 0 THEN NAME10 ", MAIN_CODE)
                            .append("WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE9, NAME9 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE9 <> '') AS C9) > 0 THEN NAME9 ", MAIN_CODE)
                            .append("WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE8, NAME8 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE8 <> '') AS C8) > 0 THEN NAME8 ", MAIN_CODE)
                            .append("WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE7, NAME7 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE7 <> '') AS C7) > 0 THEN NAME7 ", MAIN_CODE)
                            .append("WHEN (SELECT COUNT(*) FROM (SELECT ROW_NUM, MAIN_CODE, CODE6, NAME6 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE6 <> '') AS C6) > 0 THEN NAME6 ", MAIN_CODE)
                            .append("END AS UNIT ")
                            .append("FROM ( ")
                            .append("SELECT C6.*, C6N.START_NUM, C6N.END_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE6, NAME6 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE6 <> '' ")
                            .append(") AS C6 ")
                            .append("JOIN ( ")
                            .append("SELECT ISNULL(( ")
                            .append("SELECT MIN(ROW_NUM) ")
                            .append("FROM ( ")
                            .append("SELECT C6.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE6, NAME6 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE6 <> '' ")
                            .append(") AS C6 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE6, NAME6 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE6 <> '' ")
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C6.ROW_NUM-1 ")
                            .append("AND PP.CODE6 < C6.CODE6 ")
                            .append(") ")
                            .append(") AS AA ")
                            .append("WHERE AA.ROW_NUM>C6F.ROW_NUM ")
                            .append(")-1, 999999999) END_NUM, C6F.ROW_NUM START_NUM ")
                            .append("FROM ( ")
                            .append("SELECT C6.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE6, NAME6 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE6 <> '' ")
                            .append(") AS C6 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE6, NAME6 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE6 <> '' ")
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C6.ROW_NUM-1 ")
                            .append("AND PP.CODE6 < C6.CODE6 ")
                            .append(") ")
                            .append(") AS C6F ")
                            .append(") AS C6N ")
                            .append("ON C6.ROW_NUM BETWEEN C6N.START_NUM AND C6N.END_NUM ")
                            .append(") AS C6A ")
                            .append("LEFT JOIN ( ")
                            .append("SELECT C7.*, C7N.START_NUM, C7N.END_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE7, NAME7 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE7 <> '' ")
                            .append(") AS C7 ")
                            .append("JOIN ( ")
                            .append("SELECT ISNULL(( ")
                            .append("SELECT MIN(ROW_NUM) ")
                            .append("FROM ( ")
                            .append("SELECT C7.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE7, NAME7 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE7 <> '' ", MAIN_CODE)
                            .append(") AS C7 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE7, NAME7 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE7 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C7.ROW_NUM-1 ")
                            .append("AND PP.CODE7 < C7.CODE7 ")
                            .append(") ")
                            .append(") AS AA ")
                            .append("WHERE AA.ROW_NUM>C7F.ROW_NUM ")
                            .append(")-1, 999999999) END_NUM, C7F.ROW_NUM START_NUM ")
                            .append("FROM ( ")
                            .append("SELECT C7.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE7, NAME7 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE7 <> '' ", MAIN_CODE)
                            .append(") AS C7 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE7, NAME7 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE7 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C7.ROW_NUM-1 ")
                            .append("AND PP.CODE7 < C7.CODE7 ")
                            .append(") ")
                            .append(") AS C7F ")
                            .append(") AS C7N ")
                            .append("ON C7.ROW_NUM BETWEEN C7N.START_NUM AND C7N.END_NUM ")
                            .append(") AS C7A ")
                            .append("ON C7A.START_NUM<= C6A.END_NUM ")
                            .append("AND C7A.END_NUM>= C6A.START_NUM ")
                            .append("LEFT JOIN ( ")
                            .append("SELECT C8.*, C8N.START_NUM, C8N.END_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE8, NAME8 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE8 <> '' ")
                            .append(") AS C8 ")
                            .append("JOIN ( ")
                            .append("SELECT ISNULL(( ")
                            .append("SELECT MIN(ROW_NUM) ")
                            .append("FROM ( ")
                            .append("SELECT C8.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE8, NAME8 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE8 <> '' ", MAIN_CODE)
                            .append(") AS C8 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE8, NAME8 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE8 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C8.ROW_NUM-1 ")
                            .append("AND PP.CODE8 < C8.CODE8 ")
                            .append(") ")
                            .append(") AS AA ")
                            .append("WHERE AA.ROW_NUM>C8F.ROW_NUM ")
                            .append(")-1, 999999999) END_NUM, C8F.ROW_NUM START_NUM ")
                            .append("FROM ( ")
                            .append("SELECT C8.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE8, NAME8 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE8 <> '' ", MAIN_CODE)
                            .append(") AS C8 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE8, NAME8 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE8 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C8.ROW_NUM-1 ")
                            .append("AND PP.CODE8 < C8.CODE8 ")
                            .append(") ")
                            .append(") AS C8F ")
                            .append(") AS C8N ")
                            .append("ON C8.ROW_NUM BETWEEN C8N.START_NUM AND C8N.END_NUM ")
                            .append(") AS C8A ")
                            .append("ON C8A.START_NUM<= C6A.END_NUM ")
                            .append("AND C8A.END_NUM>= C6A.START_NUM ")
                            .append("AND C8A.START_NUM<= C7A.END_NUM ")
                            .append("AND C8A.END_NUM>= C7A.START_NUM ")
                            .append("LEFT JOIN ( ")
                            .append("SELECT C9.*, C9N.START_NUM, C9N.END_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE9, NAME9 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE9 <> '' ")
                            .append(") AS C9 ")
                            .append("JOIN ( ")
                            .append("SELECT ISNULL(( ")
                            .append("SELECT MIN(ROW_NUM) ")
                            .append("FROM ( ")
                            .append("SELECT C9.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE9, NAME9 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE9 <> '' ", MAIN_CODE)
                            .append(") AS C9 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE9, NAME9 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE9 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C9.ROW_NUM-1 ")
                            .append("AND PP.CODE9 < C9.CODE9 ")
                            .append(") ")
                            .append(") AS AA ")
                            .append("WHERE AA.ROW_NUM>C9F.ROW_NUM ")
                            .append(")-1, 999999999) END_NUM, C9F.ROW_NUM START_NUM ")
                            .append("FROM ( ")
                            .append("SELECT C9.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE9, NAME9 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE9 <> '' ", MAIN_CODE)
                            .append(") AS C9 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE9, NAME9 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE9 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C9.ROW_NUM-1 ")
                            .append("AND PP.CODE9 < C9.CODE9 ")
                            .append(") ")
                            .append(") AS C9F ")
                            .append(") AS C9N ")
                            .append("ON C9.ROW_NUM BETWEEN C9N.START_NUM AND C9N.END_NUM ")
                            .append(") AS C9A ")
                            .append("ON C9A.START_NUM<= C6A.END_NUM ")
                            .append("AND C9A.END_NUM>= C6A.START_NUM ")
                            .append("AND C9A.START_NUM<= C7A.END_NUM ")
                            .append("AND C9A.END_NUM>= C7A.START_NUM ")
                            .append("AND C9A.START_NUM<= C8A.END_NUM ")
                            .append("AND C9A.END_NUM>= C8A.START_NUM ")
                            .append("LEFT JOIN ( ")
                            .append("SELECT C10.*, C10N.START_NUM, C10N.END_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE10, NAME10 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE10 <> '' ")
                            .append(") AS C10 ")
                            .append("JOIN ( ")
                            .append("SELECT ISNULL(( ")
                            .append("SELECT MIN(ROW_NUM) ")
                            .append("FROM ( ")
                            .append("SELECT C10.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE10, NAME10 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE10 <> '' ", MAIN_CODE)
                            .append(") AS C10 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE10, NAME10 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE10 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C10.ROW_NUM-1 ")
                            .append("AND PP.CODE10 < C10.CODE10 ")
                            .append(") ")
                            .append(") AS AA ")
                            .append("WHERE AA.ROW_NUM>C10F.ROW_NUM ")
                            .append(")-1, 999999999) END_NUM, C10F.ROW_NUM START_NUM ")
                            .append("FROM ( ")
                            .append("SELECT C10.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE10, NAME10 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE10 <> '' ", MAIN_CODE)
                            .append(") AS C10 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE10, NAME10 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE10 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C10.ROW_NUM-1 ")
                            .append("AND PP.CODE10 < C10.CODE10 ")
                            .append(") ")
                            .append(") AS C10F ")
                            .append(") AS C10N ")
                            .append("ON C10.ROW_NUM BETWEEN C10N.START_NUM AND C10N.END_NUM ")
                            .append(") AS C10A ")
                            .append("ON C10A.START_NUM<= C6A.END_NUM ")
                            .append("AND C10A.END_NUM>= C6A.START_NUM ")
                            .append("AND C10A.START_NUM<= C7A.END_NUM ")
                            .append("AND C10A.END_NUM>= C7A.START_NUM ")
                            .append("AND C10A.START_NUM<= C8A.END_NUM ")
                            .append("AND C10A.END_NUM>= C8A.START_NUM ")
                            .append("AND C10A.START_NUM<= C9A.END_NUM ")
                            .append("AND C10A.END_NUM>= C9A.START_NUM ")
                            .append("LEFT JOIN ( ")
                            .append("SELECT C11.*, C11N.START_NUM, C11N.END_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE11, NAME11 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE11 <> '' ")
                            .append(") AS C11 ")
                            .append("JOIN ( ")
                            .append("SELECT ISNULL(( ")
                            .append("SELECT MIN(ROW_NUM) ")
                            .append("FROM ( ")
                            .append("SELECT C11.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE11, NAME11 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE11 <> '' ", MAIN_CODE)
                            .append(") AS C11 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE11, NAME11 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE11 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C11.ROW_NUM-1 ")
                            .append("AND PP.CODE11 < C11.CODE11 ")
                            .append(") ")
                            .append(") AS AA ")
                            .append("WHERE AA.ROW_NUM>C11F.ROW_NUM ")
                            .append(")-1, 999999999) END_NUM, C11F.ROW_NUM START_NUM ")
                            .append("FROM ( ")
                            .append("SELECT C11.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE11, NAME11 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE11 <> '' ", MAIN_CODE)
                            .append(") AS C11 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE11, NAME11 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE11 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C11.ROW_NUM-1 ")
                            .append("AND PP.CODE11 < C11.CODE11 ")
                            .append(") ")
                            .append(") AS C11F ")
                            .append(") AS C11N ")
                            .append("ON C11.ROW_NUM BETWEEN C11N.START_NUM AND C11N.END_NUM ")
                            .append(") AS C11A ")
                            .append("ON C11A.START_NUM<= C6A.END_NUM ")
                            .append("AND C11A.END_NUM>= C6A.START_NUM ")
                            .append("AND C11A.START_NUM<= C7A.END_NUM ")
                            .append("AND C11A.END_NUM>= C7A.START_NUM ")
                            .append("AND C11A.START_NUM<= C8A.END_NUM ")
                            .append("AND C11A.END_NUM>= C8A.START_NUM ")
                            .append("AND C11A.START_NUM<= C9A.END_NUM ")
                            .append("AND C11A.END_NUM>= C9A.START_NUM ")
                            .append("AND C11A.START_NUM<= C10A.END_NUM ")
                            .append("AND C11A.END_NUM>= C10A.START_NUM ")
                            .append("LEFT JOIN ( ")
                            .append("SELECT C12.*, C12N.START_NUM, C12N.END_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE12, NAME12 ")
                            .append("FROM MRP_MRSBASEA_TEMP ")
                            .append("WHERE MAIN_CODE=:MAIN_CODE ", MAIN_CODE)
                            .append("AND CODE12 <> '' ")
                            .append(") AS C12 ")
                            .append("JOIN ( ")
                            .append("SELECT ISNULL(( ")
                            .append("SELECT MIN(ROW_NUM) ")
                            .append("FROM ( ")
                            .append("SELECT C12.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE12, NAME12 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE12 <> '' ", MAIN_CODE)
                            .append(") AS C12 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE12, NAME12 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE12 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C12.ROW_NUM-1 ")
                            .append("AND PP.CODE12 < C12.CODE12 ")
                            .append(") ")
                            .append(") AS AA ")
                            .append("WHERE AA.ROW_NUM > C12F.ROW_NUM ")
                            .append(")-1, 999999999) END_NUM, C12F.ROW_NUM START_NUM ")
                            .append("FROM ( ")
                            .append("SELECT C12.ROW_NUM ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE12, NAME12 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE12 <> '' ", MAIN_CODE)
                            .append(") AS C12 ")
                            .append("WHERE NOT EXISTS ( ")
                            .append("SELECT 1 ")
                            .append("FROM ( ")
                            .append("SELECT ROW_NUM, MAIN_CODE, CODE12, NAME12 FROM MRP_MRSBASEA_TEMP WHERE MAIN_CODE=:MAIN_CODE AND CODE12 <> '' ", MAIN_CODE)
                            .append(") AS PP ")
                            .append("WHERE PP.ROW_NUM=C12.ROW_NUM-1 ")
                            .append("AND PP.CODE12 < C12.CODE12 ")
                            .append(") ")
                            .append(") AS C12F ")
                            .append(") AS C12N ")
                            .append("ON C12.ROW_NUM BETWEEN C12N.START_NUM AND C12N.END_NUM ")
                            .append(") AS C12A ")
                            .append("ON C12A.START_NUM<= C6A.END_NUM ")
                            .append("AND C12A.END_NUM>= C6A.START_NUM ")
                            .append("AND C12A.START_NUM<= C7A.END_NUM ")
                            .append("AND C12A.END_NUM>= C7A.START_NUM ")
                            .append("AND C12A.START_NUM<= C8A.END_NUM ")
                            .append("AND C12A.END_NUM>= C8A.START_NUM ")
                            .append("AND C12A.START_NUM<= C9A.END_NUM ")
                            .append("AND C12A.END_NUM>= C9A.START_NUM ")
                            .append("AND C12A.START_NUM<= C10A.END_NUM ")
                            .append("AND C12A.END_NUM>= C10A.START_NUM ")
                            .append("AND C12A.START_NUM<= C11A.END_NUM ")
                            .append("AND C12A.END_NUM>= C11A.START_NUM ")
                            .build();
                    List<MRP0501RDTO> msyData = sqlExecutor.queryForList(qy2, MRP0501RDTO.class);
                    List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
                    for (int x = 0; x < msyData.size(); x++) {
                        Map<String, Object> tmpMap = new HashMap<String, Object>();
                        MRP0501RDTO dt = msyData.get(x);
                        tmpMap.put("PCCESCODE", dt.getCode());
                        tmpMap.put("CNAME", dt.getName());
                        tmpMap.put("UNIT", dt.getUnit());
                        rsList.add(tmpMap);
                    }
                    rs.put("status", "0");
                    rs.put("response", rsList);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            rs.put("status", "1");
        }
        return rs;
    }

    @Override
    public Map<String, Object> mrp0119R(MultipartFile XLS, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(XLS.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumbers = sheet.getLastRowNum() + 1;
//            Query query = Query
//                    .builder()
//                    .append("DELETE FROM MRP_REF_PRICE_LOG")
//                    .build();
//            sqlExecutor.delete(query);
            for (int r = 1; r < rowNumbers; r++) {
                Cell cell = sheet.getRow(r).getCell(0);
                if(cell.toString().equals("")) {
                    break;
                }

                Row row = sheet.getRow(r);
                String CODE = "", NAME = "", UNIT = "", LOCATION = "";
                int RESOURCE_ID = 0, VERSION = 0, KIND = 0;
                double PRICE = 0;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date STARTTIME = null, ENDTIME = null, CREATETIME = null;
                for (int cc = 0; cc < 11; cc++) {
                    if (null != row.getCell(cc)) {
                        switch (cc) {
                            case 0:
                                CODE = row.getCell(cc).toString().substring(0, row.getCell(cc).toString().indexOf("."));
                                break;
                            case 1:
                                NAME = row.getCell(cc).toString();
                                break;
                            case 2:
                                UNIT = row.getCell(cc).toString();
                                break;
                            case 3:
                                PRICE = Double.parseDouble(row.getCell(cc).toString());
                                break;
                            case 4:
                                LOCATION = row.getCell(cc).toString();
                                break;
                            case 5:
                                Date temp = row.getCell(cc).getDateCellValue();
                                if (null != temp) {
                                    String t1 = sdf.format(temp);
                                    STARTTIME = sdf.parse(t1);
                                }
                                break;
                            case 6:
                                Date temp2 = row.getCell(cc).getDateCellValue();
                                if (null != temp2) {
                                    String t2 = sdf.format(temp2);
                                    ENDTIME = sdf.parse(t2);
                                }
                                break;
                            case 7:
                                VERSION = Integer.parseInt(row.getCell(cc).toString().substring(0, row.getCell(cc).toString().indexOf(".")));
                                break;
                            case 8:
                                RESOURCE_ID = Integer.parseInt(row.getCell(cc).toString().substring(0, row.getCell(cc).toString().indexOf(".")));
                                break;
                            case 9:
                                KIND = Integer.parseInt(row.getCell(cc).toString().substring(0, row.getCell(cc).toString().indexOf(".")));
                                break;
                            case 10:
                                Date temp3 = row.getCell(cc).getDateCellValue();
                                if (null != temp3) {
                                    String t3 = sdf.format(temp3);
                                    CREATETIME = sdf.parse(t3);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                Query qy = Query
                        .builder()
                        .append("INSERT INTO MRP_REF_PRICE (CODE, NAME, UNIT, PRICE, LOCATION, START_TIME, END_TIME, VERSION, RESOURCE_ID, KIND, UPDATE_ID, UPDATE_DATE, CREATE_ID, CREATE_DATE)")
                        .append("VALUES (:CODE, :NAME, :UNIT, :PRICE, :LOCATION, :STARTTIME, :ENDTIME, :VERSION, :RESOURCE_ID, :KIND, :UPDATE_ID, :UPDATE_DATE, :CREATE_ID, :CREATETIME)",
                                CODE, NAME, UNIT, PRICE, LOCATION, STARTTIME, ENDTIME, VERSION, RESOURCE_ID, KIND, "", CREATETIME, "", CREATETIME)
                        .build();
                sqlExecutor.insert(qy);

//                    Query msyQry = Query
//                            .builder()
//                            .append("SELECT DISTINCT CASE WHEN COUNT(*) OVER (PARTITION BY CODE) > 1")
//                            .append("THEN '重覆' + FORMAT(COUNT(*) OVER (PARTITION BY CODE), '#,0') + '筆'")
//                            .append("else 'OK' end SOURCE_STATUS, ID, CODE, NAME, UNIT, PRICE, LOCATION, ")
//                            .append("START_TIME, END_TIME, VERSION, RESOURCE_ID, KIND FROM MRP_REF_PRICE_LOG A")
//                            .build();
//                    List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
//                    List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
//                    for (int x = 0; x < msyData.size(); x++) {
//                        Map<String, Object> tmpMap = new HashMap<String, Object>();
//                        MRP0501RDTO dt = msyData.get(x);
//                        tmpMap.put("SOURCE_STATUS", dt.getSourceStatus());
//                        tmpMap.put("ID", dt.getId());
//                        tmpMap.put("CODE", dt.getCode());
//                        tmpMap.put("NAME", dt.getName());
//                        tmpMap.put("UNIT", dt.getUnit());
//                        tmpMap.put("PRICE", dt.getPrice());
//                        tmpMap.put("LOCATION", dt.getLocation());
//                        tmpMap.put("START_TIME", dt.getStartTime());
//                        tmpMap.put("END_TIME", dt.getEndTime());
//                        tmpMap.put("VERSION", dt.getVersion());
//                        tmpMap.put("RESOURCE_ID", dt.getResourceId());
//                        tmpMap.put("KIND", dt.getKind());
//                        rsList.add(tmpMap);
//                    }
                rs.put("status", "0");
                rs.put("insertDataNumber", r); // $$$
//                    rs.put("response", rsList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            rs.put("status", "1");
        }
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpCity120() {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT ID, NAME FROM MRP_TW_CITY WHERE ID NOT IN (-1, 18, 24, 25, 26) ORDER BY RANK")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("ID", dt.getId());
            tmpMap.put("NAME", dt.getName());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMrpTown120() {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT CITY_ID AS cityId, TOWN_ID AS townId, NAME FROM MRP_TW_TOWN ORDER BY POSTAL")
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            MRP0501RDTO dt = msyData.get(x);
            tmpMap.put("CITY_ID", dt.getCityId());
            tmpMap.put("TOWN_ID", dt.getTownId());
            tmpMap.put("NAME", dt.getName());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryMainInfo120(String contractCode, String filesourceId, HttpServletRequest request) {
        Query query1 = Query
                .builder()
                .append("SELECT contract_code, filesource_id FROM MRP_PROJECT_LIST WHERE CONTRACT_CODE = :contractCode", contractCode)
                .build();
        List<MRP0501RDTO> mrp501dto1 = sqlExecutor.queryForList(query1, MRP0501RDTO.class);
        Map<String, Object> rs = new HashMap<String, Object>();
        if(!filesourceId.equals(mrp501dto1.get(0).getFilesourceId())) {
            rs.put("status", "1");
            rs.put("result", "上傳失敗，請重新上傳");
            return rs;
        } else {
            List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
            Map<String, Object> dataMap = new HashMap<String, Object>();
            Query query2 = Query
                    .builder()
                    .append("SELECT PL.ENGINEERING_CODE, PL.MAIN_ORG_NAME, PL.HOST, PL.ENGINEERING_NAME, PL.MAIN_ORG_CODE, PL.PHONE, PL.AWARD_BUDGET_DATE, PL.AWARD_BUDGET, PL.MAIN_BUDGET, PC.NAME, PC.EMAIL, PC.PHONE AS used , PC.CHECK_BUDGET_DATE AS createdate, PC.START_TIME, PC.END_TIME")
                    .append("FROM MRP_PROJECT_LIST AS PL")
                    .append("INNER JOIN MRP_PROJECT_CONTRACT AS PC ON PL.FILESOURCE_ID = PC.FILESOURCE_ID")
                    .append("WHERE PL.FILESOURCE_ID = :filesourceId", filesourceId)
                    .build();
            List<MRP0501RDTO> mrp501dto2 = sqlExecutor.queryForList(query2, MRP0501RDTO.class);
            Map<String, Object> tmpMap1 = new HashMap<String, Object>();
            MRP0501RDTO dt1 = mrp501dto2.get(0);
            tmpMap1.put("ENGINEERING_CODE", dt1.getEngineeringCode());
            tmpMap1.put("MAIN_ORG_NAME", dt1.getMainOrgName());
            tmpMap1.put("TENDER_HOST", dt1.getHost());
            tmpMap1.put("ENGINEERING_NAME", dt1.getEngineeringName());
            tmpMap1.put("MAIN_ORG_CODE", dt1.getMainOrgCode());
            tmpMap1.put("TENDER_PHONE", dt1.getPhone());
            tmpMap1.put("AWARD_BUDGET_DATE", dt1.getAwardBudgetDate());
            tmpMap1.put("AWARD_BUDGET", dt1.getAward_budget());
            tmpMap1.put("MAIN_BUDGET", dt1.getMain_budget());
            tmpMap1.put("name", dt1.getName());
            tmpMap1.put("email", dt1.getEmail());
            tmpMap1.put("phone", dt1.getUsed());
            tmpMap1.put("check_budget_date", dt1.getCreateDate());
            tmpMap1.put("start_time", dt1.getStartTime());
            tmpMap1.put("end_time", dt1.getEndTime());
            dataMap.put("data", tmpMap1);
            rsList.add(dataMap);

            Map<String, Object> areaMap = new HashMap<String, Object>();
            Query query3 = Query
                    .builder()
                    .append("SELECT C.NAME AS city, T.NAME AS town")
                    .append("FROM MRP_TW_TOWN AS T")
                    .append("LEFT JOIN MRP_TW_CITY AS C ON T.CITY_ID = C.ID")
                    .append("LEFT JOIN MRP_PROJECT_LIST_TOWN AS PLT ON T.TOWN_ID = PLT.TAIWAN_TOWN_ID")
                    .append("WHERE PLT.FILESOURCE_ID = :filesourceId", filesourceId)
                    .build();
            List<MRP0501RDTO> mrp501dto3 = sqlExecutor.queryForList(query3, MRP0501RDTO.class);
            List<String> areaList = new ArrayList<>();
            for (int i = 0; i < mrp501dto3.size(); i++) {
                areaList.add(mrp501dto3.get(i).getCity() + "^" + mrp501dto3.get(i).getTown());
            }
            areaMap.put("area", areaList);
            rsList.add(areaMap);
            rs.put("status", "0");
            rs.put("response", rsList);
            return rs;
        }
    }

    @Override
    public void updateContractInfo(MrpSynonymCriteria criteria) {
        Query qy = Query
                .builder()
                .append("INSERT OR UPDATE MRP_PROJECT_CONTRACT (FILESOURCE_ID, NAME, EMAIL, PHONE, START_TIME, END_TIME, CHECK_BUDGET_DATE) ") // $$$
                .append("VALUES (:PROJECT_ID, :NAME, :EMAIL, :PHONE, :START_TIME, :END_TIME, :CHECK_BUDGET_DATE)",
                        criteria.getProject_id(), criteria.getName(), criteria.getEmail(), criteria.getPhone(), criteria.getStart_time(), criteria.getEnd_time(), criteria.getCheck_budget_date())
                .build();
        sqlExecutor.insert(qy);
    }

    @Override
    public Map<String, Object> uploadFiles120(MultipartFile[] xmls, String project_id, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            int errorCode = 0;
            String message_error = "";

            //一、上傳檔案
            int XML_num = 0;
            for (int x = 0; x < xmls.length; x++) {
//                File file = new File(xmls[x].getOriginalFilename());
//                String file_name = xmls[x].getOriginalFilename();
                String fileType = FilenameUtils.getExtension(xmls[x].getOriginalFilename());
                System.out.println("fileType: " + fileType);
                if (!fileType.equals("xml")) {
                    errorCode = 2;
                    message_error = "檔案格式不正確，勿上傳EXCEL或AP等其他檔案格式，請使用XML檔案格式，PCCES轉檔問題，請洽(02)2708-8090";
                    rs.put("errorCode", errorCode);
                    rs.put("message_error", message_error);
                    return rs;
                } else {
                    //把XML存檔，路徑待確認。
                    XML_num++;
                }
            }
            //二、解析XML, XML需要將TenderInformation及GrandTotalInformation的資料讀取出來做檢核用, 同時紀錄file_name
            List<String> fileNames = new ArrayList<String>();

            int contract_price = 0;
            int budget_price = 0;
            int contract_num = 0;
            int budget_num = 0;
            int request_num = 0;
            int submit_num = 0;
            int other_num = 0;

            String request_mes = "";
            String submin_mes = "";
            String other_mes = "";

            Query sQry1 = Query
                    .builder()
                    .append("SELECT * FROM MRP_PROJECT_LIST WHERE ID = :PROJECT_ID", project_id)
                    .build();
            List<MrpProjectListDTO> SQL1 = sqlExecutor.queryForList(sQry1, MrpProjectListDTO.class);

            Query sQry2 = Query
                    .builder()
                    .append("SELECT MPP.* FROM MRP_PROJECT_PURCHASE MPP INNER JOIN (SELECT * FROM MRP_PROJECT_LIST WHERE ID = :PROJECT_ID) SQL1 ON MPP.CONTRACT_CODE = SQL1.CONTRACT_CODE", project_id)
                    .build();
            List<MrpProjectPurchaseDTO> SQL2 = sqlExecutor.queryForList(sQry2, MrpProjectPurchaseDTO.class);

            if (!(SQL1.size() > 0 && SQL2.size() > 0)) {
                errorCode = 998;
                message_error = "查無project_id";
                rs.put("errorCode", errorCode);
                rs.put("message_error", message_error);
                return rs;
            }

            String CON_PE_TW = SQL1.get(0).getMainOrgName();
            String CON_TW = SQL1.get(0).getEngineeringName();
            String CON_NO = SQL1.get(0).getEngineeringCode();
            Double CON_AWARD_BUDGET = SQL2.get(0).getAwardPrice();
            Double CON_MAIN_BUDGET = SQL1.get(0).getMainBudget();

            boolean IS_PACKAGE = SQL2.get(0).getIsPackage();
            boolean IS_MULTIPLE_AWARD = SQL2.get(0).getIsMultipleAward();
            int AWARD_ITEM_AMOUNT = SQL2.get(0).getAwardItemAmount();
            Double AWARD_BUDGET = SQL1.get(0).getAwardBudget();

            String error_remind = "";
            String error_remind_cor = "";
            Integer NOCorrect = null;
            boolean errname_yesno = false;//XML跟標案內容是否符合

            for (int x = 0; x < xmls.length; x++) {

                File file = new File(xmls[x].getOriginalFilename());
                String file_name = xmls[x].getOriginalFilename();
                fileNames.add(file_name);

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                //an instance of builder to parse the specified xml file
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                NodeList nodeList = doc.getElementsByTagName("ETenderSheet");
                Node node = nodeList.item(0);
                String documentType = node.getAttributes().getNamedItem("documentType").getNodeValue();

                if (documentType == null) {
                    errorCode = 2;
                    message_error = "傳輸檔案非公共工程經費電腦估價系統格式電子檔，PCCES 單價電子檔製作問題，請洽(02)2708-8090";
                    rs.put("errorCode", errorCode);
                    rs.put("message_error", message_error);
                    return rs;
                }

                String CorrectRatio = doc.getElementsByTagName("CorrectRatio").item(0).getTextContent();
                String TotalAmount = doc.getElementsByTagName("TotalAmount").item(0).getTextContent();

                if (doc.getElementsByTagName("CorrectRatio").item(0).getTextContent() == null) {
                    NOCorrect = 1;
                }

                // Code1

                switch (documentType) {
                    case "contract":
                        contract_num++;
                        contract_price = contract_price + Integer.parseInt(TotalAmount);
                        break;
                    case "budget":
                        budget_num++;
                        budget_price = budget_price + Integer.parseInt(TotalAmount);
                        break;
                    case "request":
                        request_num++;
                        request_mes += file_name + "為空白標單\n";
                        break;
                    case "submit":
                        submit_num++;
                        submin_mes += file_name + "為投標單\n";
                        break;
                    default:
                        other_num++;
                        other_mes += file_name + "非契約單價電子檔或預算單價電子檔\n";
                        break;
                }

                String File_name = file_name;//二.
                boolean total_yesno = true;
                switch (documentType) {//xml裡面的documentType
                    case "budget":
                        if (CON_MAIN_BUDGET != null && CON_MAIN_BUDGET != budget_price) {//Code1裡面的budget_price
                            total_yesno = true;
                        }
                        break;
                    case "contract":
                        if (AWARD_BUDGET != null && CON_AWARD_BUDGET != contract_price) {//Code1裡面的contract_price
                            total_yesno = true;
                        }
                        break;
                }

                String ContractTitle_tw = "";
                int l1 = doc.getElementsByTagName("ContractTitle").getLength();
                for (int n1 = 0; n1 < l1; n1++) {
                    if (doc.getElementsByTagName("ContractTitle").item(n1).getAttributes().getNamedItem("language").getNodeValue().equals("zh-TW")) {
                        ContractTitle_tw = doc.getElementsByTagName("ContractTitle").item(n1).getTextContent();
                        break;
                    }
                }
                String ProcuringEntity_tw = "";
                int l2 = doc.getElementsByTagName("ProcuringEntity").getLength();
                for (int n2 = 0; n2 < l2; n2++) {
                    if (doc.getElementsByTagName("ProcuringEntity").item(n2).getAttributes().getNamedItem("language").getNodeValue().equals("zh-TW")) {
                        ProcuringEntity_tw = doc.getElementsByTagName("ProcuringEntity").item(n2).getTextContent();
                        break;
                    }
                }

                String ContractNo = doc.getElementsByTagName("ContractNo").item(0).getTextContent();

                if (!CON_TW.contains(ContractTitle_tw.trim()) || !CON_PE_TW.contains(ProcuringEntity_tw.trim()) || !CON_NO.contains(ContractNo.trim()) || total_yesno) {
                    if (!CON_TW.contains(ContractTitle_tw.trim()) || !CON_PE_TW.contains(ProcuringEntity_tw.trim()) || !CON_NO.contains(ContractNo.trim())) {
                        errname_yesno = true;
                    }
                    String PE_tw = "";
                    String CT_tw = "";
                    String C_NO = "";
                    String total_name = "";

                    PE_tw = CON_PE_TW.contains(ProcuringEntity_tw.trim()) ? ProcuringEntity_tw : ProcuringEntity_tw + "  (決標公告：" + CON_PE_TW + ")";
                    CT_tw = CON_TW.contains(ContractTitle_tw.trim()) ? ContractTitle_tw : ContractTitle_tw + "  (決標公告：" + CON_TW + ")";
                    C_NO = CON_NO.contains(ContractNo.trim()) ? ContractNo : ContractNo + "  (決標公告：" + CON_NO + ")";

                    if (total_yesno) {
                        switch (documentType) {
                            case "budget":
                                if (CON_MAIN_BUDGET != null) {
                                    total_name = "預算金額:" + TotalAmount;
                                } else {
                                    total_name = "預算金額: --";
                                }
                                break;
                            case "contract":
                                if (CON_AWARD_BUDGET != null) {
                                    total_name = "決標金額:" + TotalAmount;
                                } else {
                                    total_name = "決標金額: --";
                                }
                                break;
                        }
                    }

                    error_remind += "單價電子檔：" + File_name + "<br />";
                    error_remind += "招標機關：" + PE_tw + "<br />";
                    error_remind += "標案案號:" + C_NO + "<br />";
                    error_remind += "標案名稱:" + CT_tw + " <br />";
                    error_remind += total_name + " <br />";
                    error_remind += " <br />";
                }
                String cor = "";
                cor = CorrectRatio == null ? "0" : CorrectRatio.replace("%", "");

                if (Double.parseDouble(cor) < 40 && documentType == "budget") {
                    error_remind_cor += "<br />";
                    error_remind_cor += File_name + "<br />";
                    error_remind_cor += "細目編碼正確率" + cor + "% <br />";
                }


            }

            if (other_num > 0 || submit_num > 0 || request_num > 0) {
                message_error = request_mes + submin_mes + other_mes + "，請選擇契約單價電子檔及預算單價電子檔， PCCES單價電子檔製作問題，請洽(02)2708-8090」，下方有「利用預算書製作契約單價xml電子檔，供決標上傳之應用」之連結";
                errorCode = 9;
                // errorMsg = message_error;
                rs.put("errorCode", errorCode);
                rs.put("message_error", message_error);
                return rs;
            }
            // Code2
            if (IS_PACKAGE == true) {//統包
                if (contract_num == 0) {
                    errorCode = 3;
                    message_error = "傳輸檔案非契約單價電子檔";
                    rs.put("errorCode", errorCode);
                    rs.put("message_error", message_error);
                    return rs;
                }
            } else {
                if (IS_MULTIPLE_AWARD == true) { //複數
                    int sum_num = (int) (AWARD_ITEM_AMOUNT) * 2;
                    if (budget_num != contract_num || budget_num < AWARD_ITEM_AMOUNT || contract_num < AWARD_ITEM_AMOUNT || XML_num % 2 != 0) {
                        errorCode = 4;
                        message_error = "屬複數決標案件者，契約單價電子檔及預算單價電子檔之檔案數量須分別大於等於決標品項數";
                        rs.put("errorCode", errorCode);
                        rs.put("message_error", message_error);
                        return rs;
                    }
                } else { //非複數
                    if (budget_num != contract_num || XML_num % 2 != 0) {
                        errorCode = 5;
                        message_error = "缺漏部分契約單價電子檔或預算單價電子檔";
                        rs.put("errorCode", errorCode);
                        rs.put("message_error", message_error);
                        return rs;
                    }
                }
            }

            // Code4
            if (error_remind_cor.length() > 0) {
                String remind_cor = "";
                remind_cor += "標案案號：" + CON_NO + " <br />";
                remind_cor += "標案名稱：" + CON_TW + " <br />";
                remind_cor += "<br />";
                remind_cor += "<b>配合公共工程委員會提升編碼正確率目標，細目編碼正確率須達40%以上，請更正後重新上傳，或於下方填報說明無法達成之原因，按下[確定]完成上傳作業</b>";
                remind_cor += "<br />";

                error_remind_cor = remind_cor + error_remind_cor;

                if (error_remind.length() > 0) {
                    String remind = "";
                    remind += "招標機關：" + CON_PE_TW + " <br />";
                    remind += "標案案號：" + CON_NO + " <br />";
                    remind += "標案名稱：" + CON_TW + " <br />";
                    if (CON_MAIN_BUDGET == null) {
                        remind += "預算金額：-- <br />";
                    } else {
                        remind += "預算金額：" + CON_MAIN_BUDGET + " <br />";
                    }

                    if (CON_AWARD_BUDGET == null) {
                        remind += "決標金額：-- <br />";
                    } else {
                        remind += "決標金額：" + CON_AWARD_BUDGET + " <br />";
                    }

                    remind += " <br />";
                    if (errname_yesno) {
                        remind += "<b>提醒您，選擇之單價檔有部分不符合決標公告內容，請再次確認有選擇正確之單價檔，如確認無誤請按下「皆已確認無誤」繼續上傳作業。</b>  <br />";
                    } else {
                        remind += "<b>您所選擇之單價檔皆符合決標公告內容，請按「下一步」進行細目編碼正確率比對。</b>  <br />";
                    }
                    remind += " <br />";
                    error_remind = remind + error_remind;
                }

                rs.put("constrcution_id", project_id);
                rs.put("status", "success");
                rs.put("memo", "已上傳");
                rs.put("err_id", SQL1.get(0).getFilesourceId().toString());
                rs.put("error_remind", error_remind);
                rs.put("error_remind_cor", error_remind_cor);
                rs.put("NoCorrect", NOCorrect);
                rs.put("listOfXmlFiles", fileNames);
                rs.put("errname_yesno", errname_yesno);
            } else {
                if (error_remind.length() > 0) {
                    String remind = "";
                    remind += "招標機關：" + CON_PE_TW + "<br />";
                    remind += "標案案號：" + CON_NO + "<br />";
                    remind += "標案名稱：" + CON_TW + "<br />";
                    if (CON_MAIN_BUDGET == null) {
                        remind += "預算金額：-- <br />";
                    } else {
                        remind += "預算金額：" + CON_MAIN_BUDGET + " <br />";
                    }

                    if (CON_AWARD_BUDGET == null) {
                        remind += "決標金額：-- <br />";
                    } else {
                        remind += "決標金額：" + CON_AWARD_BUDGET + " <br />";
                    }

                    remind += "<br />";
                    if (errname_yesno) {
                        remind += "<b>提醒您，選擇之單價檔有部分不符合決標公告內容，請再次確認有選擇正確之單價檔，如確認無誤請按下「皆已確認無誤」繼續上傳作業。</b>  <br />";
                    } else {
                        remind += "<b>您所選擇之單價檔皆符合決標公告內容，請按「下一步」進行細目編碼正確率比對。</b>  <br />";
                    }
                    remind += "<br />";
                    error_remind = remind + error_remind;
                }

                rs.put("constrcution_id", project_id);
                rs.put("status", "success");
                rs.put("memo", "已上傳");
                rs.put("err_id", SQL1.get(0).getFilesourceId().toString());
                rs.put("error_remind", error_remind);
                rs.put("error_remind_cor", error_remind_cor);
                rs.put("NoCorrect", NOCorrect);
                rs.put("listOfXmlFiles", fileNames);
                rs.put("errname_yesno", errname_yesno);
            }
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            rs.put("errorCode", 999);
            rs.put("message_error", "解析XML時發生錯誤");
            return rs;
        }
    }

    @Override
    public Map<String, Object> updateWrongReturn(MrpSynonymCriteria criteria){
        Map<String, Object> rs = new HashMap<String, Object>();
        Query qy = Query
                .builder()
                .append("INSERT INTO MRP_WRONG_RETURN (NAME, CODE, UNIT, PRICE, PURCHASE_QUANTITY, PERSON_NAME, PERSONTEL, PERSONE_MAIL, WRONG_RETURN_DESCRIPTION) ")
                .append("VALUES (:name, :code, :unit, :price, :purchase_quantity, :person_name, :persontel, :person_mail, :wrong_return_description)",
                        criteria.getName(), criteria.getCode(), criteria.getUnit(), criteria.getPrice(), criteria.getPurchase_quantity(), criteria.getPerson_name(), criteria.getPersontel(), criteria.getPerson_mail(), criteria.getWrong_return_description())
                .build();
        sqlExecutor.insert(qy);
        rs.put("status", "0");
        return rs;
    }

    @Override
    public Map<String, Object> updateCommonKeywordbtn(MrpSynonymCriteria criteria){
        Map<String, Object> rs = new HashMap<String, Object>();
        Query qmck = Query
                .builder()
                .append("SELECT KEYWORD_AMOUNT AS cnt FROM MRP_COMMON_KEYWORD WHERE KEYWORD_NAME = :KEYWORD_NAME", criteria.getKeyword_name())
                .build();
        List<MRP0501RDTO> mck = sqlExecutor.queryForList(qmck, MRP0501RDTO.class);
        if(mck.size() > 0) {
            int cnt = mck.get(0).getCnt();
            cnt++;
            Query qy = Query
                    .builder()
                    .append("UPDATE MRP_COMMON_KEYWORD SET")
                    .append("KEYWORD_AMOUNT = :KEYWORD_AMOUNT", cnt)
                    .build();
            sqlExecutor.update(qy);
        }else{
            Query qy = Query
                    .builder()
                    .append("INSERT INTO MRP_COMMON_KEYWORD (ACCOUNT_ID, BTN_NAME, KEYWORD_NAME, KEYWORD_AMOUNT, SEARCH_SUCCESS, CREATE_ID) ")
                    .append("VALUES (:ACCOUNT_ID, :BTN_NAME, :KEYWORD_NAME, :KEYWORD_AMOUNT, :SEARCH_SUCCESS, :CREATE_ID)",
                            criteria.getAccount_id(), criteria.getBtn_name(), criteria.getKeyword_name(), 1, null, criteria.getCreate_id())
                    .build();
            sqlExecutor.insert(qy);
        }
        rs.put("status", "0");
        return rs;
    }

    @Override
    public Map<String, Object> queryCommonKeywordAdd(String ACCOUNT_ID, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT BTN_NAME as NAME FROM MRP_COMMON_KEYWORD WHERE ACCOUNT_ID =:ACCOUNT_ID AND BTN_NAME IS NOT NULL", ACCOUNT_ID)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("BTN_NAME", msyData.get(x).getName());
            rsList.add(tmpMap);
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryCommonKeywordbtn(String ACCOUNT_ID, String BTN_NAME, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query msyQry = Query
                .builder()
                .append("SELECT KEYWORD_NAME as NAME FROM MRP_COMMON_KEYWORD WHERE ACCOUNT_ID =:ACCOUNT_ID AND BTN_NAME = :BTN_NAME", ACCOUNT_ID, BTN_NAME)
                .build();
        List<MRP0501RDTO> msyData = sqlExecutor.queryForList(msyQry, MRP0501RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        for (int x = 0; x < msyData.size(); x++) {
            String [] keywords = msyData.get(x).getName().split(":::");
            for(int y = 0 ; y < keywords.length ; y++){
                String NAME = keywords[y].split("\\^")[0];
                String AREA = keywords[y].split("\\^")[1];
                String KIND = keywords[y].split("\\^")[2];
                String CODE = keywords[y].split("\\^")[3];

                String COL_NAME = getColName_sqlstr(AREA);
                String sqlarea = getArea_sqlstr(AREA, null);
                Map<String, Object> rst = new HashMap<String, Object>();
                Query msyQry1 = Query
                        .builder()
                        .append("SELECT TOP 1 * FROM ( SELECT tmp.DATESTRING, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, tmp.CITY, tmp.TOWN, ROUND(AVG(tmp.PRICE),0)AS PRICE, ")
                        .append("ROUND((AVG(tmp.PRICE)+STDEVP(tmp.PRICE)),0)AS PXMAX, ROUND((AVG(tmp.PRICE)-STDEVP(tmp.PRICE)),0)AS PXMIN, ")
                        .append("ROUND( AVG(tmp.PRICE),0) AS PRICE_INDEX, ROUND( COUNT(tmp.PRICE),0) AS NUM, ROUND( STDEVP(tmp.PRICE),0) AS ERR_PRICE, ")
                        .append("ROW_NUMBER() OVER(ORDER BY tmp.CODE DESC) AS ROW, tmp.PRICETYPE FROM ( ")
                        .append("SELECT DISTINCT CONVERT(NVARCHAR(8), cl.AWARD_BUDGET_DATE, 111)+'01' AS DATESTRING, rc.RESOURCE_ID, ") // $$$
                        .append("rc.NAME, rc.UNIT, rc.CODE, " + COL_NAME + " rc.PRICE AS PRICE, rc.STATUS, rc.USED, rc.KIND AS PRICETYPE ")
                        .append("FROM MRP_VERIFY_RESOURCE_CODE rc, MRP_TENDERINFO ti, MRP_PROJECT_LIST cl, ")
                        .append("MRP_TENDERINFO_PROJECTCODE tip, MRP_TENDERINFO_TW_CITY tic, MRP_TW_AREA ta")
                        .append("WHERE rc.FILESOURCE_ID = ti.FILESOURCE_ID AND ti.FILESOURCE_ID = tic.FILESOURCE_ID")
                        .append("AND ti.FILESOURCE_ID = tic.FILESOURCE_ID AND tic.TAIWAN_CITY_ID = ta.CITY_ID")
                        .append("AND ti.FILESOURCE_ID = tip.FILESOURCE_ID AND rc.UNIT != '' AND rc.PRICE > 0 AND rc.USED =1")
                        .append(sqlarea)
                        .append("AND rc.KIND = :KIND AND rc.NAME = :NAME  AND rc.CODE = :CODE", KIND, NAME, CODE)
                        .append(") AS tmp GROUP BY tmp.DATESTRING, tmp.NAME, tmp.CODE, tmp.UNIT, tmp.AREA, tmp.PRICETYPE, tmp.CITY, tmp.TOWN) AS tmp WHERE tmp.PRICE > 0")
                        .build();
                List<MRP0501RDTO> msyData1 = sqlExecutor.queryForList(msyQry1, MRP0501RDTO.class);
                for (int z = 0; z < msyData1.size(); z++) {
                    Map<String, Object> tmpMap = new HashMap<String, Object>();
                    MRP0501RDTO dt = msyData1.get(z);
                    tmpMap.put("datestring", dt.getDatestring());
                    tmpMap.put("name", dt.getName());
                    tmpMap.put("code", dt.getCode());
                    tmpMap.put("unit", dt.getUnit());
                    tmpMap.put("area", dt.getArea());
                    tmpMap.put("price", dt.getPrice());
                    tmpMap.put("num", dt.getNum());
                    tmpMap.put("pricetype", dt.getPricetype());
                    tmpMap.put("err_price", dt.getErrPrice());
                    tmpMap.put("city", dt.getCity());
                    rsList.add(tmpMap);
                }
            }
        }
        rs.put("status", "0");
        rs.put("response", rsList);
        return rs;
    }

    @Override
    public Map<String, Object> queryCommonKeywordDelete(String ACCOUNT_ID, String BTN_NAME, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Query query1 = Query
                .builder()
                .append("DELETE FROM MRP_COMMON_KEYWORD WHERE ACCOUNT_ID = :ACCOUNT_ID AND BTN_NAME = :BTN_NAME", ACCOUNT_ID, BTN_NAME)
                .build();
        sqlExecutor.delete(query1);
        rs.put("status", "0");
        return rs;
    }

    @Override
    public Map<String, Object> queryCommonKeywordUpdate(String ACCOUNT_ID, String BTN_NAME, String NEW_BTN_NAME, String NEW_KEYWORD_NAME, HttpServletRequest request) {
        Map<String, Object> rs = new HashMap<String, Object>();
        if((NEW_BTN_NAME != null && !NEW_BTN_NAME.equals("")) || (NEW_KEYWORD_NAME != null && !NEW_KEYWORD_NAME.equals(""))){
            Query qy = Query
                    .builder()
                    .append("UPDATE MRP_COMMON_KEYWORD SET")
                    .appendWhen(NEW_BTN_NAME != null && !NEW_BTN_NAME.equals(""), "BTN_NAME = :NEW_BTN_NAME,", NEW_BTN_NAME)
                    .appendWhen(NEW_KEYWORD_NAME != null && !NEW_KEYWORD_NAME.equals(""), "KEYWORD_NAME = :NEW_KEYWORD_NAME", NEW_KEYWORD_NAME)
                    .append("WHERE ACCOUNT_ID = :ACCOUNT_ID AND BTN_NAME = :BTN_NAME", ACCOUNT_ID, BTN_NAME)
                    .build();
            sqlExecutor.update(qy);
        }
        rs.put("status", "0");
        return rs;
    }

    @Override
    public String historyPayableDetail(String TENDER_AWARD_TYPE, String TENDER_ORG_NAME, String TENDER_ORG_ID, String TENDER_CASE_NO, String TENDER_NAME, String TENDER_CPC_CODE, String TENDER_BUDGET, String TENDER_PROCUREMENT_RANGE, String TENDER_WAY, String AWARD_WAY, String TENDER_GOV_ESTIMATE, String AWARD_PRICE, String AWARD_PRICE_PUBLIC, String BIDDER_ID, String BIDDER_SUPP_NAME, String TENDER_LIMITED_LAW, String IS_PACKAGE, String IS_MULTIPLE_AWARD, String IS_INTER_ENTITY_SUPPLY, String AWARD_NOTICE_DATE, String AWARD_DATE, String SUPPLIER_AMOUNT, String TENDER_STATUS, String TENDER_CONTACT_PERSON, String TENDER_CONTACT_TEL, String TENDER_SEQ, String BIDDER_AWARD_AMOUNT, String TENDER_IS_LOWEST_LAW, String TENDER_IS_CHG_CONTRACT, String BUDGET_PUBLIC, String TENDER_GOV_EST_PUBLIC, String OID, String AWARD_UPDATE_SEQ, String DATE_OF_OPENING_OF_TENDERS, String DATE_OF_ORIGINAL_PUBLICATION, String AWARD_SEQ, String PK_PMS_MAIN) {
        try{
            String CONTRACT_CODE = TENDER_ORG_ID + "_" + String.valueOf(Integer.parseInt(AWARD_SEQ)) + "_" + String.valueOf(Integer.parseInt(TENDER_SEQ)) + TENDER_CASE_NO;
            if(Integer.parseInt(AWARD_UPDATE_SEQ) > 1) {
                Query ascQry = Query
                        .builder()
                        .append("SELECT TOP 1 CONTRACT_CODE AS NUM FROM MRP_PROJECT_PURCHASE WHERE PK_PMS_MAIN = :PK_PMS_MAIN ORDER BY CREATE_DATE DESC", PK_PMS_MAIN)
                        .build();
                List<MRP0501RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0501RDTO.class);
                if(ascData.size() > 0){
                    CONTRACT_CODE = ascData.get(0).getNum();
                }
            }
            Query qy = Query
                    .builder()
                    .append("INSERT INTO MRP_PROJECT_PURCHASE (CONTRACT_CODE, TENDER_AWARD_TYPE, TENDER_ORG_NAME, TENDER_ORG_ID, TENDER_CASE_NO, TENDER_NAME, TENDER_CPC_CODE, TENDER_BUDGET, TENDER_PROCUREMENT_RANGE, TENDER_WAY, AWARD_WAY, TENDER_GOV_ESTIMATE, AWARD_PRICE, AWARD_PRICE_PUBLIC, BIDDER_ID, BIDDER_SUPP_NAME, TENDER_LIMITED_LAW, IS_PACKAGE, IS_MULTIPLE_AWARD, IS_INTER_ENTITY_SUPPLY, AWARD_NOTICE_DATE, AWARD_DATE, SUPPLIER_AMOUNT, TENDER_STATUS, TENDER_CONTACT_PERSON, TENDER_CONTACT_TEL, TENDER_SEQ, BIDDER_AWARD_AMOUNT, TENDER_IS_LOWEST_LAW, TENDER_IS_CHG_CONTRACT, BUDGET_PUBLIC, OID, AWARD_UPDATE_SEQ, DATE_OF_OPENING_OF_TENDERS, DATE_OF_ORIGINAL_PUBLICATION, AWARD_SEQ, KIND, PK_PMS_MAIN, CREATE_ID, CREATE_DATE)")
                    .append("VALUES (:CONTRACT_CODE, :TENDER_AWARD_TYPE, :TENDER_ORG_NAME, :TENDER_ORG_ID, :TENDER_CASE_NO, :TENDER_NAME, :TENDER_CPC_CODE, :TENDER_BUDGET, :TENDER_PROCUREMENT_RANGE, :TENDER_WAY, :AWARD_WAY, :TENDER_GOV_ESTIMATE, :AWARD_PRICE, :AWARD_PRICE_PUBLIC, :BIDDER_ID, :BIDDER_SUPP_NAME, :TENDER_LIMITED_LAW, :IS_PACKAGE, :IS_MULTIPLE_AWARD, :IS_INTER_ENTITY_SUPPLY, :AWARD_NOTICE_DATE, :AWARD_DATE, :SUPPLIER_AMOUNT, :TENDER_STATUS, :TENDER_CONTACT_PERSON, :TENDER_CONTACT_TEL, :TENDER_SEQ, :BIDDER_AWARD_AMOUNT, :TENDER_IS_LOWEST_LAW, :TENDER_IS_CHG_CONTRACT, :BUDGET_PUBLIC, :OID, :AWARD_UPDATE_SEQ, :DATE_OF_OPENING_OF_TENDERS, :DATE_OF_ORIGINAL_PUBLICATION, :AWARD_SEQ, 0, :PK_PMS_MAIN,  'SYS', GetDate())",
                            CONTRACT_CODE, TENDER_AWARD_TYPE, TENDER_ORG_NAME, TENDER_ORG_ID, TENDER_CASE_NO, TENDER_NAME, TENDER_CPC_CODE, Double.parseDouble(TENDER_BUDGET), TENDER_PROCUREMENT_RANGE, TENDER_WAY, AWARD_WAY, Double.parseDouble(TENDER_GOV_ESTIMATE), Double.parseDouble(AWARD_PRICE), AWARD_PRICE_PUBLIC, BIDDER_ID, BIDDER_SUPP_NAME, TENDER_LIMITED_LAW, Integer.parseInt(IS_PACKAGE), Integer.parseInt(IS_MULTIPLE_AWARD), Integer.parseInt(IS_INTER_ENTITY_SUPPLY), AWARD_NOTICE_DATE, AWARD_DATE, Integer.parseInt(SUPPLIER_AMOUNT), TENDER_STATUS, TENDER_CONTACT_PERSON, TENDER_CONTACT_TEL, TENDER_SEQ, Double.parseDouble(BIDDER_AWARD_AMOUNT), TENDER_IS_LOWEST_LAW, TENDER_IS_CHG_CONTRACT, BUDGET_PUBLIC, OID, AWARD_UPDATE_SEQ, DATE_OF_OPENING_OF_TENDERS, DATE_OF_ORIGINAL_PUBLICATION, AWARD_SEQ, PK_PMS_MAIN)
				.build();
            sqlExecutor.insert(qy);
            Query qy2 = Query
                    .builder()
                    .append("merge into MRP_PROJECT_LIST CL")
                    .append("using (select TOP 1 CONTRACT_CODE as CONTRACT_CODE")
                    .append(", TENDER_ORG_NAME as MAIN_ORG_NAME")
                    .append(", TENDER_ORG_ID as MAIN_ORG_CODE")
                    .append(", TENDER_CASE_NO as ENGINEERING_CODE")
                    .append(", TENDER_NAME as ENGINEERING_NAME")
                    .append(", AWARD_DATE as AWARD_BUDGET_DATE")
                    .append(", TENDER_BUDGET as MAIN_BUDGET")
                    .append(", TENDER_GOV_ESTIMATE as BASE_BUDGET")
                    .append(", AWARD_PRICE as AWARD_BUDGET")
                    .append(", TENDER_CONTACT_PERSON as HOST")
                    .append(", TENDER_CONTACT_TEL as PHONE")
                    .append(", ISNULL(BIDDER_ID, '') as COMPANY_NO")
                    .append(", BIDDER_SUPP_NAME as COMPANY_NAME")
                    .append(", AWARD_UPDATE_SEQ as AWARD_UPDATE_SEQ")
                    .append(", case when isnull(trim(OID), '') <> '' then OID end")
                    .append("as OID")
                    .append(", CONTRACT_CODE as LocalZipFile")
                    .append(", case when IS_PACKAGE = 1")
                    .append("then 1")
                    .append("when IS_MULTIPLE_AWARD = 1")
                    .append("then 3")
                    .append("when IS_INTER_ENTITY_SUPPLY = 1")
                    .append("then 2")
                    .append("else 0")
                    .append("end as PURCHASE_TYPE")
                    .append(", 1 as IS_AUDIT")
                    .append(", DATEDIFF_BIG( microsecond, '00010101', SYSDATETIME() ) * 10 + (DATEPART( NANOSECOND, SYSDATETIME()) % 1000 ) / 100")
                    .append("as FILESOURCE_ID")
                    .append("from [PWC_DB].[dbo].[MRP_PROJECT_PURCHASE] ")
                    .append("where CONTRACT_CODE = :CONTRACT_CODE", CONTRACT_CODE)
                    .append("order by CREATE_DATE DESC) CP")
                    .append("on CL.CONTRACT_CODE = CP.CONTRACT_CODE")
                    .append("when not matched then")
                    .append("insert ( CONTRACT_CODE, MAIN_ORG_NAME, MAIN_ORG_CODE, ENGINEERING_CODE, ENGINEERING_NAME")
                    .append(", AWARD_BUDGET_DATE, MAIN_BUDGET, BASE_BUDGET, AWARD_BUDGET, HOST")
                    .append(", PHONE, COMPANY_NO, COMPANY_NAME, AWARD_UPDATE_SEQ, OID")
                    .append(", LOCAL_ZIP_FILE, PURCHASE_TYPE, IS_AUDIT, FILESOURCE_ID, CREATE_DATE)")
                    .append("values ( CP.CONTRACT_CODE, CP.MAIN_ORG_NAME, CP.MAIN_ORG_CODE, CP.ENGINEERING_CODE, CP.ENGINEERING_NAME")
                    .append(", CP.AWARD_BUDGET_DATE, CP.MAIN_BUDGET, CP.BASE_BUDGET, CP.AWARD_BUDGET, CP.HOST")
                    .append(", CP.PHONE, CP.COMPANY_NO, CP.COMPANY_NAME, CP.AWARD_UPDATE_SEQ, CP.OID")
                    .append(", CP.LocalZipFile, CP.PURCHASE_TYPE, 1, CP.FILESOURCE_ID, GetDate())")
                    .append("when matched then")
                    .append("update ")
                    .append("set company_no = case when isnull(CL.COMPANY_NO, '') not like '%'+ CP.COMPANY_NO +'%'")
                    .append("then isnull(CL.COMPANY_NO + '/' + CP.COMPANY_NO, CP.COMPANY_NO)")
                    .append("end")
                    .append(", company_name = case when isnull(CL.COMPANY_NO, '') not like '%'+ CP.COMPANY_NO +'%'")
                    .append("then isnull(CL.COMPANY_NAME + '/' + CP.COMPANY_NAME, CP.COMPANY_NAME)")
                    .append("end")
                    .append(", company_memo = isnull(company_memo, '');")
                    .build();
            sqlExecutor.insert(qy2);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "faild";
        }
    }

    @Override
    public String getPurchaseByPmsMainion(String PK_PMS_MAIN) {
        Query ascQry = Query
                .builder()
                .append("SELECT TOP 1 * FROM MRP_PROJECT_PURCHASE WHERE PK_PMS_MAIN = :PK_PMS_MAIN AND KIND = 0 ORDER BY CREATE_DATE DESC", PK_PMS_MAIN)
                .build();
        List<MrpProjectPurchaseDTO> ascData = sqlExecutor.queryForList(ascQry, MrpProjectPurchaseDTO.class);
        if(ascData.size() > 0){
            Gson gson = new Gson();
            String result = gson.toJson(ascData);
            return result;
        }
        return "";
    }

    @Override
    public Map<String, Object> purchase120(String code, String content, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> rs = new HashMap<String, Object>();
        try{
            Map<String, String> params = splitParams(content);
            String contract_code = params.get("TENDER_ORG_ID") + params.get("TENDER_CASE_NO") + params.get("TENDER_NAME") + "BjyS9qo2MIgC5qczn+ghSn/eH1Rtl9hI" + code;
            String checksum = "";
            if(params.get("checksum") != null) {
                checksum = params.get("checksum");
            }
            boolean checkP = checkParam(contract_code, checksum.toLowerCase());
            if(!checkP){
                rs.put("status", "1");
                rs.put("result", "錯誤");
                return rs;
            }
            if(params.containsKey("AWARD_UPDATE_SEQ") && params.get("AWARD_UPDATE_SEQ") != null){
                String CONTRACT_CODE = params.get("TENDER_ORG_ID") + "_" + String.valueOf(Integer.parseInt(params.get("AWARD_SEQ"))) + "_" + String.valueOf(Integer.parseInt(params.get("TENDER_SEQ"))) + params.get("TENDER_CASE_NO");
                int AWARD_UPDATE_SEQ = Integer.parseInt(params.get("AWARD_UPDATE_SEQ"));
                if(AWARD_UPDATE_SEQ > 1){
                    String PK_PMS_MAIN = params.get("PK_PMS_MAIN");
                    Query ascQry = Query
                            .builder()
                            .append("SELECT TOP 1 CONTRACT_CODE FROM MRP_PROJECT_PURCHASE WHERE PK_PMS_MAIN = :PK_PMS_MAIN ORDER BY CREATE_DATE DESC", PK_PMS_MAIN)
                            .build();
                    List<MrpProjectPurchaseDTO> ascData = sqlExecutor.queryForList(ascQry, MrpProjectPurchaseDTO.class);
                    if(ascData.size() > 0){
                        CONTRACT_CODE = ascData.get(0).getContractCode();
                    }
                    int isPackage = params.get("IS_PACKAGE").equals("true") ? 1 : 0;
                    int isMultipleAward = params.get("IS_MULTIPLE_AWARD").equals("true") ? 1 : 0;
                    int isInterEntitySupply = params.get("IS_INTER_ENTITY_SUPPLY").equals("true") ? 1 : 0;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date awardDate = null;
                    Date awardNoticeDate = null;
                    if(!params.get("AWARD_DATE").equals("")) {
                        String year = String.valueOf(Integer.parseInt(params.get("AWARD_DATE").substring(1).split("/")[0]) + 1911);
                        year += "-" + params.get("AWARD_DATE").split("/")[1] + "-" +  params.get("AWARD_DATE").split("/")[2];
                        System.out.println("yesr");
                        System.out.println(year);
                        awardDate = sdf.parse(year);
                    }
                    if(!params.get("AWARD_NOTICE_DATE").equals("")) {
                        String year2 = String.valueOf(Integer.parseInt(params.get("AWARD_NOTICE_DATE").substring(1).split("/")[0]) + 1911) + "-" + params.get("AWARD_NOTICE_DATE").split("/")[1] + "-" +  params.get("AWARD_NOTICE_DATE").split("/")[2];
                        awardNoticeDate = sdf.parse(year2);
                    }
                    Query qy = Query
                            .builder()
                            .append("INSERT INTO MRP_PROJECT_PURCHASE (CONTRACT_CODE, TENDER_AWARD_TYPE, TENDER_ORG_NAME, TENDER_ORG_ID, TENDER_CASE_NO, TENDER_NAME, TENDER_CPC_CODE, TENDER_BUDGET, BIDDER_AMOUNT, IS_MULTIPLE_AWARD, IS_INTER_ENTITY_SUPPLY, AWARD_DATE, TENDER_STATUS, TENDER_CONTACT_PERSON, TENDER_CONTACT_TEL, TENDER_SEQ, AWARD_SEQ, PK_PMS_MAIN, TENDER_EMAIL, TENDER_LOCATION_AREA, AWARD_ITEM_AMOUNT, AWARD_NOTICE_DATE, IS_PACKAGE, AWARD_UPDATE_SEQ, AWARD_PRICE, KIND, CREATE_ID, CREATE_DATE)")
                            .append("VALUES (:CONTRACT_CODE, :TENDER_AWARD_TYPE, :TENDER_ORG_NAME, :TENDER_ORG_ID, :TENDER_CASE_NO, :TENDER_NAME, :TENDER_CPC_CODE, :TENDER_BUDGET, :BIDDER_AMOUNT, :IS_MULTIPLE_AWARD, :IS_INTER_ENTITY_SUPPLY, :AWARD_DATE, :TENDER_STATUS, :TENDER_CONTACT_PERSON, :TENDER_CONTACT_TEL, :TENDER_SEQ, :AWARD_SEQ, :PK_PMS_MAIN, :TENDER_EMAIL, :TENDER_LOCATION_AREA, :AWARD_ITEM_AMOUNT, :AWARD_NOTICE_DATE, :IS_PACKAGE, :AWARD_UPDATE_SEQ, :AWARD_PRICE, 1, 'SYS', GetDate())",
                                    CONTRACT_CODE, params.get("TENDER_AWARD_TYPE"), params.get("TENDER_ORG_NAME"), params.get("TENDER_ORG_ID"), params.get("TENDER_CASE_NO"), params.get("TENDER_NAME"), params.get("TENDER_CPC_CODE"), Double.parseDouble(params.get("TENDER_BUDGET")), params.get("BIDDER_AMOUNT"), isMultipleAward, isInterEntitySupply, awardDate, params.get("TENDER_STATUS"), params.get("TENDER_CONTACT_PERSON"), params.get("TENDER_CONTACT_TEL"), params.get("TENDER_SEQ"), params.get("AWARD_SEQ"), params.get("PK_PMS_MAIN"), params.get("TENDER_EMAIL"), params.get("TENDER_LOCATION_AREA"), params.get("AWARD_ITEM_AMOUNT"), awardNoticeDate, isPackage, params.get("AWARD_UPDATE_SEQ"), Double.parseDouble(params.get("AWARD_PRICE")))
                            .build();
                    sqlExecutor.insert(qy);
                }
                if(params.containsKey("TENDER_STATUS") && params.get("TENDER_STATUS").equals("決標公告")){
                    Query qy2 = Query
                            .builder()
                            .append("merge into [PWC_DB].[dbo].[MRP_PROJECT_LIST] CL")
                            .append("using (select TOP 1 CONTRACT_CODE as CONTRACT_CODE")
                            .append(", TENDER_ORG_NAME as MAIN_ORG_NAME")
                            .append(", TENDER_ORG_ID as MAIN_ORG_CODE")
                            .append(", TENDER_CASE_NO as ENGINEERING_CODE")
                            .append(", TENDER_NAME as ENGINEERING_NAME")
                            .append(", TENDER_BUDGET as MAIN_BUDGET")
                            .append(", AWARD_PRICE as AWARD_BUDGET")
                            .append(", AWARD_DATE as AWARD_BUDGET_DATE")
                            .append(", TENDER_CONTACT_PERSON as HOST")
                            .append(", TENDER_CONTACT_TEL as PHONE")
                            .append(", BIDDER_SUPP_NAME as COMPANY_NAME")
                            .append(", AWARD_UPDATE_SEQ as AWARD_UPDATE_SEQ")
                            .append(", case when isnull(trim(OID), '') <> '' then OID end")
                            .append("as OID")
                            .append(", DATEDIFF_BIG( microsecond, '00010101', SYSDATETIME() ) * 10 + (DATEPART( NANOSECOND, SYSDATETIME()) % 1000 ) / 100")
                            .append("as FILESOURCE_ID")
                            .append(", BIDDER_AMOUNT as BIDDER_AMOUNT")
                            .append(", CONTRACT_CODE as LocalZipFile")
                            .append(", case when IS_PACKAGE = 1")
                            .append("then 1")
                            .append("when IS_MULTIPLE_AWARD = 1")
                            .append("then 3")
                            .append("when IS_INTER_ENTITY_SUPPLY = 1")
                            .append("then 2")
                            .append("else 0")
                            .append("end as PURCHASE_TYPE")
                            .append(", 1 as IS_AUDIT")
                            .append("from [PWC_DB].[dbo].[MRP_PROJECT_PURCHASE] ")
                            .append("where CONTRACT_CODE = :contract_code", CONTRACT_CODE)
                            .append("order by CREATE_DATE DESC) CP")
                            .append("on CL.CONTRACT_CODE = CP.CONTRACT_CODE")
                            .append("when not matched then")
                            .append("insert ( CONTRACT_CODE, MAIN_ORG_NAME, MAIN_ORG_CODE, ENGINEERING_CODE, ENGINEERING_NAME")
                            .append(", AWARD_BUDGET_DATE, MAIN_BUDGET, AWARD_BUDGET, HOST")
                            .append(", PHONE, COMPANY_NAME, AWARD_UPDATE_SEQ, OID")
                            .append(", LOCAL_ZIP_FILE, PURCHASE_TYPE, FILESOURCE_ID, CREATE_DATE)")
                            .append("values ( CP.CONTRACT_CODE, CP.MAIN_ORG_NAME, CP.MAIN_ORG_CODE, CP.ENGINEERING_CODE, CP.ENGINEERING_NAME")
                            .append(", CP.AWARD_BUDGET_DATE, CP.MAIN_BUDGET, CP.AWARD_BUDGET, CP.HOST")
                            .append(", CP.PHONE, CP.COMPANY_NAME, CP.AWARD_UPDATE_SEQ, CP.OID")
                            .append(", CP.LocalZipFile, CP.PURCHASE_TYPE, CP.FILESOURCE_ID, GetDate())")
                            .append("when matched then")
                            .append("update ")
                            .append("set BIDDER_AMOUNT = CP.BIDDER_AMOUNT;")
                            .build();
                    sqlExecutor.insert(qy2);
                }
                Query fQry = Query
                        .builder()
                        .append("SELECT TOP 1 FILESOURCE_ID FROM MRP_PROJECT_LIST WHERE CONTRACT_CODE = :CONTRACT_CODE ORDER BY CREATE_DATE DESC, ID DESC", CONTRACT_CODE)
                        .build();
                List<MrpProjectListDTO> fData = sqlExecutor.queryForList(fQry, MrpProjectListDTO.class);
                if(fData.size() > 0 && fData.get(0).getFilesourceId() != null){
                    response.addHeader("contract_code", CONTRACT_CODE);
//                    response.addHeader("contract_code", DigestUtils.sha256Hex(CONTRACT_CODE));
                    response.addHeader("filesource_id",String.valueOf(fData.get(0).getFilesourceId()));
//                    response.addHeader("filesource_id", DigestUtils.sha256Hex(String.valueOf(fData.get(0).getFilesourceId())));
                    rs.put("status", "0");
                    rs.put("result", "成功");
                }else{
                    rs.put("status", "1");
                    rs.put("result", "錯誤");
                }
            }else{
                rs.put("status", "2");
                rs.put("result", "錯誤");
            }
        }catch(Exception e){
            e.printStackTrace();
            rs.put("status", "3");
            rs.put("result", "錯誤");
        }
        return rs;
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, String> splitParams(String contents) {
        Map<String, String> result = new HashMap<String, String>();

        String code = "";
        if(contents.split("&content=")[0].split("code=").length > 1) {
            code = contents.split("&content=")[0].split("code=")[1];
        }
        result.put("code", code);
        String content[] = contents.split("%26");
        for(int x = 0 ; x < content.length ; x++) {
            String key = content[x].split("%3D")[0];
            String value = "";
            if(content[x].split("%3D").length > 1) {
                value = content[x].split("%3D")[1];
                value = URLDecoder.decode(value, StandardCharsets.UTF_8);
            }
            if(key.equals("TENDER_CONTACT_TEL")){
                if(value.indexOf("%23") > -1){
                    value = value.replace("%23", "#");
                }
            }
            result.put(key, value);
        }
        return result;
    }

    private boolean checkParam(String contract_code, String checksum){
//        String password = "SHA-256";
        String sha256 = DigestUtils.sha256Hex(contract_code);
//        System.out.println(sha256);
        if(sha256.equals(checksum)){
            return true;
        }else{
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void addRecord(HttpServletRequest request, String condition, String type, String acc, String action, String ip) {
//        String ip = getIpAddr(request);
        Long newPk = Long.valueOf(0);
        Query msyQry = Query
                .builder()
                .append("SELECT TOP(1) ID AS MR_ID FROM MRP_RECORDING ORDER BY ID DESC")
                .build();
        List<MrpRecordingDTO> getpkData = sqlExecutor.queryForList(msyQry, MrpRecordingDTO.class);
        if (getpkData.size() > 0 && getpkData.get(0) != null) {
            newPk = (getpkData.get(0).getMrId() + 1);
        }
        Instant createTime = Instant.now();

        Query qy = Query
                .builder()
                .append("Insert into MRP_RECORDING (ID, ACCOUNT_ID, TYPE, ACTION, SEARCH_CONDITIONS, IP, NUM, SAMPLE_NUM, DOWNLOAD_NUM, CREATE_ID, CREATE_DATE)")
                .append("VALUES (:ID, :ACCOUNT_ID, :TYPE, :ACTION, :SEARCH_CONDITIONS, :IP, :NUM, :SAMPLE_NUM, :DOWNLOAD_NUM, :CREATE_ID, :CREATE_DATE)",
                        newPk, acc, type, action, condition, ip, 0, 0, 0, "SYS", Timestamp.from(createTime))
                .build();
        sqlExecutor.insert(qy);
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /////////////////////

    private String getArea_sqlstr(String ddl_city, String ddl_town) {
        String sql_area = "";
        String sql_city = "";
        String sql_town = "";
        if (ddl_city == "") {
            //全台灣
            sql_city = "AND (tic.TAIWAN_CITY_ID >-1 AND tic.TAIWAN_CITY_ID < 24)  ";
        } else if (ddl_city == "離島偏鄉地區") {
            sql_city = "AND tic.TAIWAN_CITY_ID = 24   ";
        } else {
            if (ddl_city.indexOf("地區") >= 0 || ddl_city.indexOf("區域") >= 0) {
                sql_city = " AND ti.AREA like '%" + ddl_city + "%'  AND (tic.TAIWAN_CITY_ID >-1 AND tic.TAIWAN_CITY_ID < 24) ";
            } else if (ddl_city.indexOf("鄉") >= 0) {
                sql_city = " AND ta.CITY_NAME LIKE '%" + ddl_city + "%' ";
            } else {
                sql_city = " AND ta.CITY_NAME LIKE '%" + ddl_city + "%' ";
            }
        }

        if(null != ddl_town && !ddl_town.equals("")) {
            if (ddl_town.indexOf("臺") > -1) {
                String town2 = ddl_town.replace("臺", "台");
                sql_town = "AND (ta.TOWN_NAME LIKE '%" + ddl_town + "%' or ta.TOWN_NAME LIKE '%" + town2 + "%') ";
                } else {
                sql_town = "AND ta.TOWN_NAME LIKE '%" + ddl_town + "%'";
            }
        }

        sql_area = sql_city.concat(sql_town);
        return sql_area;
    }

    private String getTownSqlstr(String ddl_town) {
        String sql_town = "";
        if (ddl_town != null && !ddl_town.equals("")) {
            if (ddl_town.indexOf("臺") > -1) {
                String town2 = ddl_town.replace("臺", "台");
                sql_town = "AND (tt.NAME LIKE '%" + ddl_town + "%' or tt.NAME LIKE '%" + town2 + "%') ";
            } else {
                sql_town = "AND tt.NAME LIKE '%" + ddl_town + "%'";
            }
        }
        return sql_town;
    }

    private String getCity_sqlstr(String ddl_city) {
        String sql_city = "";
        if (ddl_city == "") {
            //全台灣
            sql_city = "AND (tic.TAIWAN_CITY_ID >-1 AND tic.TAIWAN_CITY_ID < 24)  ";
        } else if (ddl_city == "離島偏鄉地區") {
            sql_city = "AND tic.TAIWAN_CITY_ID = 24   ";
        } else {
            if (ddl_city.indexOf("地區") >= 0 || ddl_city.indexOf("區域") >= 0) {
                sql_city = " AND ti.AREA like '%" + ddl_city + "%'  AND (tic.TAIWAN_CITY_ID >-1 AND tic.TAIWAN_CITY_ID < 24) ";
            } else if (ddl_city.indexOf("鄉") >= 0) {
                sql_city = " AND tc.NAME LIKE '%" + ddl_city + "%' ";
            } else {
                sql_city = " AND tc.NAME LIKE '%" + ddl_city + "%' ";
            }
        }
        return sql_city;
    }

    private String getColName_sqlstr(String ddl_city) {
        String col_name = "";
        if (ddl_city == "") {
            //全台灣
            col_name = " '不分區' AS AREA, ta.CITY_NAME AS CITY, ta.TOWN_NAME AS TOWN,";
        } else if (ddl_city == "離島偏鄉地區") {
            col_name = " '離島偏鄉地區' AS AREA, ta.CITY_NAME AS CITY, ta.TOWN_NAME AS TOWN,";
        } else {
            if (ddl_city.indexOf("地區") >= 0 || ddl_city.indexOf("區域") >= 0) {
                col_name = " '" + ddl_city + "' AS AREA, ta.CITY_NAME AS CITY, ta.TOWN_NAME AS TOWN,";
            } else if (ddl_city.indexOf("鄉") >= 0) {
                col_name = " '" + ddl_city + "' AS AREA, ta.CITY_NAME AS CITY, ta.TOWN_NAME AS TOWN,";
            } else {
                col_name = "  ta.AREA AS AREA, ta.CITY_NAME AS CITY, ta.TOWN_NAME AS TOWN,";
            }
        }
        return col_name;
    }



    private String getTime_sqlstr(String startime, String endtime) {
        var sql_time = "";
        try {
            String sqlStartime = startime;// + "-01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dqetd = sdf.parse(endtime);// + "-01");
            Calendar c = Calendar.getInstance();
            c.setTime(dqetd);
            c.add(Calendar.MONTH, 1);
            dqetd = c.getTime();
            String sqlEndtime = sdf.format(dqetd);

            if (startime.length() > 0 && endtime.length() > 0) {
                sql_time = "AND (CAST('" + sqlStartime + "' AS DATETIME) <= cl.AWARD_BUDGET_DATE AND cl.AWARD_BUDGET_DATE < CAST('" + sqlEndtime + "' AS DATETIME)) ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql_time;
    }

    private String getSize_sqlstr(String ddl_size) {//新的代碼變成win & budget
        String sql_size = "";
        if (ddl_size.length() > 0) {
            switch (ddl_size) {       //決標金額
                case "win1":  //金額全選
                    sql_size = " AND rc.KIND = 5 ";
                    break;
                case "win2":  //2億以上
                    sql_size = " AND 200000000 <= cl.AWARD_BUDGET AND rc.KIND = 5 ";
                    break;
                case "win3":    //2億到5000萬
                    sql_size = "AND 50000000 <= cl.AWARD_BUDGET AND cl.AWARD_BUDGET < 200000000 AND rc.KIND = 5 ";
                    break;
                case "win4":  //5000萬到2000萬
                    sql_size = "AND 20000000 <= cl.AWARD_BUDGET AND cl.AWARD_BUDGET < 50000000 AND rc.KIND = 5 ";
                    break;
                case "win5":  //2000萬以下
                    sql_size = "AND cl.AWARD_BUDGET < 20000000 AND rc.KIND = 5 ";
                    break;
                //預算金額
                case "budget1":  //金額全選
                    sql_size = "AND rc.KIND = 2 ";
                    break;
                case "budget2":  //2億以上
                    sql_size = "AND 200000000 <= cl.MAIN_BUDGET AND rc.KIND = 2 ";
                    break;
                case "budget3":    //2億到5000萬
                    sql_size = "AND 50000000 <= cl.MAIN_BUDGET AND cl.MAIN_BUDGET < 200000000 AND rc.KIND = 2 ";
                    break;
                case "budget4":  //5000萬到2000萬
                    sql_size = "AND 20000000 <= cl.MAIN_BUDGET AND cl.MAIN_BUDGET < 50000000 AND rc.KIND = 2 ";
                    break;
                case "budget5":  //2000萬以下
                    sql_size = "AND cl.MAIN_BUDGET < 20000000 AND rc.KIND = 2 ";
                    break;
            }
        }
        return sql_size;
    }

    private List<MrpSynonymDTO> getSynonym_sqlstr(String keyword) {
        Query msyQry = Query
                .builder()
                .append("SELECT * FROM MRP_SYNONYM WHERE PHRASE= :keyword", keyword)
                .build();
        List<MrpSynonymDTO> items = sqlExecutor.queryForList(msyQry, MrpSynonymDTO.class);
        return items;
    }

    private String getKeyword_sqlstr(String keyword) {
        String sql_keyword = "";
        if (keyword.length() > 0) {
            String[] items_space = keyword.split(" ");
            for (int i = 0; i < items_space.length; i++) {
                if (sql_keyword.length() > 0) sql_keyword += " and ";
                List<MrpSynonymDTO> synonym_keyword = getSynonym_sqlstr(items_space[i]);
                if (synonym_keyword.size() > 0) {
                    sql_keyword += "(rc.name like '%" + synonym_keyword.get(0).getKeyword() + "%' or rc.name like '%" + synonym_keyword.get(0).getPhrase() + "%')";
                } else {
                    sql_keyword += " (rc.name like '%" + items_space[i] + "%' or rc.code like '" + items_space[i] + "%' or rc.code like 'M" + items_space[i] + "%' or rc.code like 'E" + items_space[i] + "%' or rc.code like 'L" + items_space[i] + "%'  or rc.code like 'W" + items_space[i] + "%' ) ";
                }
            }

            if (sql_keyword.length() > 0) sql_keyword = " and " + sql_keyword;
        }
        return sql_keyword;
    }

    private String getKeyword_sqlstr_109(String keyword) {
        String sql_keyword = " AND ( rc.DESCRIPTION_TW LIKE '%" + keyword + "%' OR rc.ITEMKEY LIKE '" + keyword + "%' or rc.ITEMKEY LIKE 'M" + keyword + "%' OR rc.ITEMKEY LIKE 'E" + keyword + "%' OR rc.ITEMKEY LIKE 'L" + keyword + "%' OR rc.ITEMKEY LIKE 'W" + keyword + "%' )  ";
        return sql_keyword;
    }

    private String getType_sqlstr(String ddl_type) {
        String sql_type = "";
        if (ddl_type.length() > 0) {
            sql_type = " AND tip.PROJECT_CODE = '" + ddl_type + "' ";
            if(ddl_type.equals("0")){
                sql_type += "OR " + ddl_type + " = '0'";
            }else if(ddl_type.equals("999999")){
                sql_type += " OR( " + ddl_type + " = '999999' AND NOT EXISTS ( SELECT 1 FROM MRP_ITEM_CODE IC WHERE IC.NAME<>N'其他工程' AND IC.CODE = ISNULL(TIP.PROJECT_CODE, -1)))";
            }
        }
        return sql_type;
    }

    private String getAdvanceBacktime_sqlstr(String backtime) {
        String sqlstr = "AND rc.CREATE_DATE <= CAST('" + backtime + "' as DATETIME) ";
        sqlstr += "UNION SELECT DISTINCT CONVERT(NVARCHAR(8),cl.AWARD_BUDGET_DATE,111)+'01' AS GETCREATETIME,";
        sqlstr += "rc.NAME, rc.RESOURCE_ID,rc.CODE,rc.UNIT,ti.CITY,rc.PRICE,rc.STATUS,rc.USED ,CAST(rc.KIND AS TINYINT) ";
        sqlstr += "FROM (SELECT TOP 1 TABLE_NAME FROM MRP_VERIFY_TABLE WHERE CREATE_DATE <= " + backtime + " ORDER BY CREATE_DATE DESC) rc ";
        sqlstr += ", MRP_PROJECT_LIST cl, MRP_TENDERINFO ti, MRP_TW_CITY tc ,MRP_TENDERINFO_PROJECTCODE tip ,MRP_TENDERINFO_TW_CITY tic ";
        sqlstr += "WHERE rc.FILESOURCE_ID = ti.FILESOURCE_ID and cl.FILESOURCE_ID = ti.FILESOURCE_ID and ti.FILESOURCE_ID = tic.FILESOURCE_ID ";
        sqlstr += "AND tic.TAIWAN_CITY_ID = tc.ID AND ti.FILESOURCE_ID = tip.FILESOURCE_ID AND rc.UNIT != '' AND rc.PRICE > 0";
        return sqlstr;
    }





    ///////BATCH TEST////////
    private static final String ref_path = "Z:\\program_web\\PCC_MRP\\application\\File\\";
    private static final String sTime = "2017/1/1";
    private static final String eTime = "2018/1/1";
    private static final String Model = "0"; //0:處理近全部工項資料  1:處理工項編碼(Ex: M1612012111)  2:處理單一工項章節(Ex: 16210) 3:處理大類工項(Ex: 16)
    private static final String ItemCode = ""; //模式 0 將不考慮工項編碼
    private static final boolean isDelAnalysisSemanic = true;

    private static List<LinkedHashMap<String, Object>> PCC_TABLE;
    private static String error_msg = "";
    private static String ref_file = "", app_path = "";
    private static List<Map<String, Object>> dt_changeword = null;
    private static String[] dir_objs;
    private static List<Map<String, Object>> dsRefTable;

    private static int KEYWORD_FREQUENCY = 2;

    @Override
    public void excludeVariation() {//Date now) {
        try {
            System.out.println("===== START EXCLUDEVARIATION =====");
            Date now = new Date();
            String filename = "", process_time = "", sqlstr = "", tmpstr = "", ref_file_path = "";
            String sql_time = "";
            // ref_file_path = ref_path + "ref_FILE\\";

            String sql_ItemCode = "";
            if (Model == "1") { //處理工項編碼(Ex: M1612012111)
                sql_ItemCode = " AND vre.CODE='" + ItemCode + "'";
            } else if (Model == "2") { //處理單一工項章節(Ex: 16210)	case "3":  //處理大類工項(Ex: 16)
                sql_ItemCode = " AND (vre.CODE LIKE '" + ItemCode + "%' OR vre.CODE LIKE 'M" + ItemCode + "%' )";
            }

            sql_time = "  AND CAST('__0__' AS datetime) <= cl.AWARD_BUDGET_DATE AND  cl.AWARD_BUDGET_DATE < CAST('__1__' AS datetime)";
            if (sTime.length() > 0 && eTime.length() > 0) {
                sql_time = sql_time.replaceAll("__0__", sTime).replaceAll("__1__", eTime);
            } else {
                sqlstr = "";
                sqlstr += " SELECT TOP 1 cl.AWARD_BUDGET_DATE ";
                sqlstr += " FROM MRP_VERIFY_RESOURCE_CODE_ERROR vre, MRP_PROJECT_LIST cl ";
                sqlstr += " WHERE vre.FILESOURCE_ID = cl.FILESOURCE_ID ";
                sqlstr += " ORDER BY vre.FILESOURCE_ID DESC ";

                Query qy = Query
                        .builder()
                        .append(sqlstr)
                        .build();
                List<AnalysisSemanicDTO> dt = sqlExecutor.queryForList(qy, AnalysisSemanicDTO.class);
                System.out.println(dt.get(0));
                // dt = ucf.getSQLtable(sqlstr, conn);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                if (dt.size() == 0) {
                    String time = sdf.format(now);
                    String tm[] = time.split("/");
                    String std = String.valueOf((Integer.parseInt(tm[0]) - 1)) + "/" + tm[1] + "/01";
                    String edd = tm[0] + "/" + tm[1] + "/01";
                    sql_time = sql_time.replaceAll("__0__", std).replaceAll("__1__", edd);
                    // sql_time = String.Format(sql_time, now.AddYears(-1).toString("yyyy/MM/01"), now.toString("yyyy/MM/01"));
                } else {
                    LocalDate t = dt.get(0).getAwardBudgetDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String formattedString = t.format(formatter);
                    Date abd = sdf.parse(formattedString);
                    String time = sdf.format(abd);
                    String tm[] = time.split("/");
                    String std = String.valueOf((Integer.parseInt(tm[0]) - 1)) + "/" + tm[1] + "/01";
                    String edd = tm[0] + "/" + tm[1] + "/01";
                    sql_time = sql_time.replaceAll("__0__", std).replaceAll("__1__", edd);
                }
            }

            sqlstr = "";
            sqlstr += " UPDATE MRP_VERIFY_RESOURCE_CODE_ERROR ";
            sqlstr += " SET STATUS=8 ";
            sqlstr += " WHERE UNIT='式' AND STATUS !=8 ";
            Query query1 = Query
                    .builder()
                    .append(sqlstr)
                    .build();
            sqlExecutor.update(query1);
            // ucf.setSQLprogram(sqlstr, conn);

            String sqlstr0 = "";
            sqlstr0 += " SELECT vre.ID AS anaId, CODE, NAME, UNIT, PRICE, STATUS, vre.KIND, CONVERT(nvarchar(8),cl.AWARD_BUDGET_DATE,111)+'01' AS awardBudgetDate ";
            sqlstr0 += " FROM MRP_VERIFY_RESOURCE_CODE_ERROR vre, MRP_PROJECT_LIST cl  ";
            sqlstr0 += " WHERE vre.FILESOURCE_ID = cl.FILESOURCE_ID  AND vre.UNIT != '式' ";
            sqlstr0 += " AND vre.STATUS IN (4, 5, 6, 7) ";
            sqlstr0 += sql_time;
            sqlstr0 += sql_ItemCode;

            sqlstr = "";
            sqlstr += " SELECT DISTINCT CODE, vre.KIND ";
            sqlstr += " FROM MRP_VERIFY_RESOURCE_CODE_ERROR vre, MRP_PROJECT_LIST cl ";
            sqlstr += " WHERE vre.FILESOURCE_ID = cl.FILESOURCE_ID AND vre.UNIT != '式' ";
            sqlstr += " AND vre.STATUS IN (4, 5, 6, 7) ";
            sqlstr += sql_time;
            sqlstr += sql_ItemCode;
            sqlstr += " ORDER BY KIND, CODE ";
            Query qy3 = Query
                    .builder()
                    .append(sqlstr)
                    .build();
            List<AnalysisSemanicDTO> only_Error = sqlExecutor.queryForList(qy3, AnalysisSemanicDTO.class);

            for (int x = 0; x < only_Error.size(); x++) {
                AnalysisSemanicDTO rowError = only_Error.get(x);

                System.out.println("變異量分析 類型:" + rowError.getKind().toString() + "  工項:" + rowError.getCode().toString());
                String sqlstr1 = sqlstr0 + " AND CODE='" + rowError.getCode().toString() + "' AND KIND=" + rowError.getKind().toString();
                Query qy2 = Query
                        .builder()
                        .append(sqlstr1)
                        .build();
                List<AnalysisSemanicDTO> rowdata = sqlExecutor.queryForList(qy2, AnalysisSemanicDTO.class);

                if (rowdata.size() < 2) {
                    continue;
                }
                List<Double> price = new ArrayList<Double>();
                for (int z = 0; z < rowdata.size(); z++) {
                    if (rowdata.get(z).getPrice() != null && String.valueOf(rowdata.get(z).getPrice()) != "") {
                        double pr = rowdata.get(z).getPrice();
                        price.add(pr);
                    }
                }
                double sum = 0;
                for (int i = 0; i < price.size(); i++) {
                    sum += price.get(i);
                }
                double mean = sum / price.size();
                double total = 0;
                for (int i = 0; i < price.size(); i++) {
                    total += (price.get(i) - mean) * (price.get(i) - mean);
                }
                double dev = Math.sqrt(total / price.size());

                var maxValue = mean + 1 * dev;
                var minValue = mean - 1 * dev;

                String sqlstr2 = sqlstr0 + " AND CODE='" + rowError.getCode().toString() + "' AND KIND=" + rowError.getKind().toString() + " AND (" + minValue + " > PRICE OR PRICE > " + maxValue + ") ";
                Query qy4 = Query
                        .builder()
                        .append(sqlstr2)
                        .build();
                List<AnalysisSemanicDTO> errors = sqlExecutor.queryForList(qy4, AnalysisSemanicDTO.class);
                //seems no use
                // var noerrors = dt_Error.Select(String.Format(" code='{0}' AND kind={1} AND ({2} < price and price < {3}) ", rowError["code"].toString(), rowError["kind"].toString(), minValue, maxValue));

                String sql_err = "";
                for (int y = 0; y < errors.size(); y++) {
                    AnalysisSemanicDTO error = errors.get(y);
                    String status = "";
                    String sts = error.getStatus().toString();
                    if (sts.equals("4")) {
                        status = "6";
                    } else if (sts.equals("5")) {
                        status = "7";
                    } else {
                        status = sts;
                    }
                    sql_err += " UPDATE MRP_VERIFY_RESOURCE_CODE_ERROR SET STATUS =" + status + " WHERE ID=" + error.getAnaId().toString() + "; \n";
                }

                if (sql_err != "") {
                    Query updtErr = Query
                            .builder()
                            .append(sql_err)
                            .build();
                    sqlExecutor.update(updtErr);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===== END EXCLUDEVARIATION =====");
    }

    @Override
    public void analysisKeywords() {//List<Map<String, Object>> table, Date now) {
        int frequency = KEYWORD_FREQUENCY;
        String sqlstr = "";
        Date end;

        //???
//        var lsal = new LatentSemanticAnalysisTool();
//        lsal.VoidTermHeadTail = getAvoidTerm(ref_path + "LatentSemantic\\head_tail_word.txt");
//        lsal.VoidTerm = getAvoidTerm(ref_path + "LatentSemantic\\2_gram.txt");

        var FileSourceIds = "";
//        for (int i = 0; i < table.size(); i++) {
//            if (FileSourceIds.length() > 0) {
//                FileSourceIds += ",";
//            }
//            FileSourceIds += table.get(i).get("FileSource_id").toString();
//        }

        sqlstr += " SELECT rl.DESCRIPTION_TW ";
        sqlstr += " FROM MRP_RESOURCE_LIST rl, MRP_PROJECT_LIST cl ";
        sqlstr += " WHERE rl.FILESOURCE_ID=cl.FILESOURCE_ID ";

        if (sTime != "" && eTime != "") {
            sqlstr += " AND CAST('" + sTime + "' as datetime) < cl.AWARD_BUDGET_DATE AND cl.AWARD_BUDGET_DATE < CAST('" + eTime + "' as datetime)";
        } else {
            sqlstr += " AND rl.FILESOURCE_ID IN (" + FileSourceIds + ")";
        }

//        String[] dir_objs = Directory.GetDirectories(ref_path + "ref_FILE");
        for (String dirObj : dir_objs) {
//            String[] items = dirObj.Split('\\');
//            items = items[items.Length - 1].Split('_');
//            String localPath = dirObj.SubString(0, dirObj.LastIndexOf("\\", StringComparison.Ordinal)) + "\\";
//            String maincode = items[0];
//            String sql = "";
//            if (maincode.SubString(0, 1).equals("L")) {
//            } else if (maincode.SubString(0, 1).equals("E")) {
//                sql = "AND (ITEMKEY LIKE '" + maincode + "%')";
//            } else {
//                sql = "AND (ITEMKEY LIKE 'M" + maincode + "%' OR ITEMKEY LIKE '" + maincode + "%')";
//            }
//
//            List<String> lis_dictory = null, lis_Synonymous = null, lis_err = null, lis_agreecode = null;
//            lis_dictory = getFileList(dirObj + "\\Dictionary.csv", '◆', 1);
//            lis_err = getFileList(localPath + items[0] + "_排除項目.txt", '◆', 0);
//            lis_agreecode = getFileList(localPath + items[0] + "_擴充編碼.txt", '◆', 0);

            //#region 取代文字
//            var ReplaceDictionaryString = new Dictionary<String, String>();
//            DataTable dt_tmp = null;
//            dt_tmp = ProgramTool.UseGeneral.FileToTable_String(ref_path + "LatentSemantic\\ChangeDefault.txt", false, ',');
//            ReplaceDictionaryString = AddDictionary(ReplaceDictionaryString, dt_tmp);

//            if (File.Exists(localPath + items[0] + "_同義詞.txt")) {
//                dt_tmp = UseGeneral.FileToTable_String(localPath + items[0] + "_同義詞.txt", '◆');
//                ReplaceDictionaryString = AddDictionary(ReplaceDictionaryString, dt_tmp);
//            }
//            lsal.DefaultReplaceString = ReplaceDictionaryString;
            //#endregion

//            var data = TableToDictionary(ucf.getSQLtable(sqlstr + sql, conn), "Description_tw");
//            var keywords = lsal.AnalysisKeywordToClusterResult(data, frequency).ToList();

            String sqlstr_insert = "";
//            for (Map<String, String> keyword : keywords) {
//                for (String k :keyword.Value.KeywordAndFrequencys) {
//                    Boolean isExists = false;
//                    if (!isExists && lis_dictory != null) isExists = lis_dictory.Contains(k.Key);
//                    if (!isExists && lis_err != null) isExists = lis_err.Contains(k.Key);
//                    if (!isExists && lis_agreecode != null) isExists = lis_agreecode.Contains(k.Key);
//
//                    if (!isExists) {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                        String ndt = sdf.format(now);
//                        sqlstr_insert += " INSERT INTO MRP_EXCEPT_SEMANTIC (MAINCODE,NAME],FREQUENCY,CREATETIME)";
//                        sqlstr_insert += " VALUES ('" + maincode + "','" + k.Key + "'," + k.Value + ",CAST('" + ndt + "' AS datetime) );\n";
//                    }
//                }
//            }

            if (sqlstr_insert.length() > 0) {
                Query updtErr = Query
                        .builder()
                        .append(sqlstr_insert)
                        .build();
                sqlExecutor.update(updtErr);
                // ucf.setSQLprogram(sqlstr_insert, conn);
            }

        }
    }

    @Override
    public void statisticsPrice() {
    }

    @Override
    public void analysisSemanic() {//Date now, String tabletype) {
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String tabletype = "";
            System.out.println("========== PCC RULES BATCH START ==========");
            String filename = "", process_time = "", sqlstr = "", tmpstr = "", ref_file_path = "";
            String sql_time = "";
            List<Map<String, Object>> dt = new ArrayList<Map<String, Object>>();

            ref_file_path = ref_path + "ref_FILE\\";
//		var noUnit = ProgramTool.UseGeneral.FileToTable_String(ref_file_path + "nounit.txt", ',');

            var sql_ItemCode = "";
            if (Model.equals("1")) {
                sql_ItemCode = " and rc.code='" + ItemCode + "'";
            } else if (Model.equals("3")) {
                sql_ItemCode = " and (rc.code like '" + ItemCode + "%' or rc.code like 'M" + ItemCode + "%' or rc.code like 'W" + ItemCode + "%' )";
            } else {
                sql_ItemCode = "";
            }

            if (sTime.length() > 0 && eTime.length() > 0) {
                sql_time = "AND CAST('" + sTime + "' AS datetime) <= cl.award_budget_date AND  cl.award_budget_date < CAST('" + eTime + "' AS datetime) ";
            }

            if (isDelAnalysisSemanic) {
                //刪除過去以解析的資料 {X}_Code

                Query query1 = Query
                        .builder()
                        .append("DELETE rc FROM MRP_PROJECT_LIST cl, " + tabletype + "List rl, " + tabletype + "Code rc ")
                        .append("WHERE cl.FileSource_id = rl.FileSource_id ")
                        .append("AND rl.id = rc." + tabletype + "_id ")
                        .appendWhen((sTime.length() > 0 && eTime.length() > 0), "AND cast(:sTime AS datetime) <= cl.award_budget_date AND  cl.award_budget_date < cast(:eTime AS datetime) ", sTime, eTime)
                        .append(sql_ItemCode)
                        .append("WHERE rc.id IS NULL ORDER BY id")
                        .build();
                sqlExecutor.delete(query1);
            } else {
                sql_time = "AND rl.isAnalysis=0 ";
            }


            String[] dirs = getDirectories(ref_file_path);
            if (Model.equals("1")) {
//			dirs = dirs.Where(x => new FileInfo(x).Name.Split("_")[0].equals(getMainCode(ItemCode))).toList();
            } else if (Model.equals("2")) {
//			dirs = dirs.Where(x => new FileInfo(x).Name.Split("_")[0].equals(ItemCode)).toList();
            } else if (Model.equals("3")) {
//			dirs = dirs.Where(x => new FileInfo(x).Name.Split("_")[0].substring(0, 2) == ItemCode).toList();
            }

            for (int j = 0; j < dirs.length; j++) {
                String maincode = dirs[j].split("_")[0];
                if (Model == "1") {
                    maincode = ItemCode;
                }

                Query ascQry = Query
                        .builder()
                        .append("SELECT rl.*  FROM( ")
                        .append("SELECT rl.id, rl.Description_tw, rl.Unit_tw, rl.ItemKey, cl.award_budget_date ")
                        .append("FROM MRP_PROJECT_LIST cl, MRP_RESOURCE_LIST rl ")
                        .append("WHERE cl.FileSource_id = rl.FileSource_id ")
                        .append(sql_time)
                        .append("AND(rl.itemkey like '" + maincode + "%' OR rl.itemkey LIKE 'M" + maincode + "%' OR rl.itemkey LIKE 'W" + maincode + "%') ")
                        .append("AND cl.FileSource_id NOT IN ( SELECT filesource_id FROM TenderInfo_TaiwanCity titc, TaiwanCity tc  WHERE titc.filesource_id= tc.id AND tc.name='其他')) AS rl ")
                        .append("LEFT JOIN MRP_RESOURCE_CODE rc ON rl.id = rc.Resource_id AND (rc.code LIKE '" + maincode + "%' OR rc.code LIKE 'M" + maincode + "%' OR rc.code like 'W" + maincode + "%') ")
                        .append("WHERE rc.id IS NULL ORDER BY id")
                        .build();
                List<AnalysisSemanicDTO> ascData = sqlExecutor.queryForList(ascQry, AnalysisSemanicDTO.class);

                System.out.println(tabletype + " " + j + "/" + dirs.length + " : " + sdf.format(new Date()) + " => " + maincode + " (" + String.valueOf(dt.size()) + ")");

                // var aSemantic = new AnalysisSemantic(ref_file_path, app_path);
                ref_file = ref_file_path;
//            dt_changeword = ProgramTool.UseGeneral.FileToTable_String(app_path + "ChangeDefault.csv", true, '□');
                List<String> ids = new ArrayList<String>();

                for (int i = 0; i < dt.size(); i++) {
                    try {
                        // if (analysisPCCcode2(dt.get(i).get("ItemKey").toString(), dt.get(i).get("Description_tw").toString(), dt.get(i).get("Unit_tw").toString())){
                        // 	if (PCC_TABLE.get(0).get("Edit_Unit").toString().trim().length() > 0 || noUnit.Select("code='" + PCC_TABLE.get(0).get("main_code").toString() + "'").length() > 0){

                        // 		sqlstr = "INSERT INTO [" + tabletype + "Code] ([" + tabletype + "_id],[name],[code],[unit],[maincode]";
                        // 		for (int k = 0; k < PCC_TABLE.Columns.Count - 8; k++){
                        // 			sqlstr += ",[" + PCC_TABLE.Columns[k + 7].ColumnName + "]";
                        // 		}
                        // 		sqlstr += " )";
                        // 		sqlstr += "  VALUES (" + dt.get(i).get("id").toString() + ",'" + PCC_TABLE.get(0).get("Edit_Content").toString() + "','" + PCC_TABLE.get(0).get("Edit_Code").toString() + "','" + PCC_TABLE.get(0).get("Edit_Unit").toString() + "','" + PCC_TABLE.get(0).get("main_code").toString() + "'";
                        // 		// for (int k = 0; k < PCC_TABLE.Columns.Count - 8; k++){
                        // 		// 	sqlstr += ",'" + PCC_TABLE.get(0).get((k + 7)).toString() + "'";
                        // 		// }

                        // 		LinkedHashMap<String, Object> selects = PCC_TABLE.get(0);
                        // 		int aaa = 0;
                        // 		for(Map.Entry<String, Object> entry : selects.entrySet()) {
                        // 			if(aaa <= 7){
                        // 				String value = String.valueOf(entry.getValue());
                        // 				sqlstr += (",'" + value + "'");
                        // 			}
                        // 			aaa ++;
                        // 		}
                        // 		sqlstr += ") ";

                        // 		Query qy = Query
                        // 						.builder()
                        // 						.append(tmpstr)
                        // 						.build();
                        // 		sqlExecutor.insert(qy);
                        // 	}
                        // }
                    } catch (Exception se) {
                        se.printStackTrace();
//					log.debug(app_path + "errLog_" + DateTime.Now.ToString("yyyyMMdd") + ".log\nerr:" + se.Message);
                    }

                    ids.add(dt.get(i).get("id").toString());

                    if (ids.size() > 5000) {
                        tmpstr = "UPDATE MRP_RESOURCE_LIST SET isAnalysis = 1 WHERE id IN (" + (String.join(",", ids)) + ")";
                        Query qy = Query
                                .builder()
                                .append(tmpstr)
                                .build();
                        sqlExecutor.update(qy);
                        ids.clear();

//					log.debug(DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") + " " + maincode + " 工項解析完成度 " + String.valueOf(i) + "/" + String.valueOf(dt.size()));
                    }
                }

                if (!ids.isEmpty()) {
                    tmpstr = "UPDATE MRP_RESOURCE_LIST SET isAnalysis = 1 WHERE id IN (" + (String.join(",", ids)) + ")";
                    Query qy = Query
                            .builder()
                            .append(tmpstr)
                            .build();
                    sqlExecutor.update(qy);
                    ids.clear();
                    System.out.println(sdf.format(new Date()) + " " + maincode + " 工項解析完成度 " + String.valueOf(dt.size()) + "/" + String.valueOf(dt.size()));
                }
            }
            System.out.println("========== PCC RULES BATCH END ==========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] getDirectories(String ref_file_path) {
        File directoryPath = new File(ref_file_path);
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File dir) {
                if (dir.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        File[] list = directoryPath.listFiles(fileFilter);
        System.out.println("List of the jpeg files in the specified directory:");
        String[] res = new String[list.length];
        int x = 0;
        for (File fileName : list) {
            res[x] = fileName.toString();
            x++;
            System.out.println(fileName);
        }
        return res;
    }

//	public static boolean analysisPCCcode2(String subcode, String content, String unit){
//		boolean isSuccess = false;
//		err_message = "";
//		PCC_TABLE = new List<LinkedHashMap<String, Object>>();
//		content = content.trim();
//		unit = unit.trim();
//
//		String ori_content = content;
//		String ori_code = subcode;
//		String ori_unit = unit;
//
//		String path_dictionary = "";
//		String search_code = "", main_name = "", main_name_tmp = "", file_code = "";
//
//		String main_title = "";       //冠詞(Ex: M:產品)
//		String main_title_code = "";  //類別碼 M:材料; E:機具; L:人力
//
//		//編碼長度過小，無法做解析
//		if (subcode.length() > 9){
//			//搜尋前 5 碼 若是機具為 7 碼
//			if (IsNatural(subcode.substring(0, 1))){
//				main_title_code = subcode.substring(0, 1).toUpperCase();
//				if (main_title_code == "E"){ //機具
//
//					search_code = getMainCode(subcode, 1, 7);
//				}else{//材料 人力
//					search_code = getMainCode(subcode, 1, 5);
//					if (main_title_code == "M"){ //材料
//						main_title = "產品，";
//					}
//				}
//				if (!main_title_code.contains("EML")){  //類別碼不屬於 E M L 全部清掉
//					main_title_code = "";
//				}
//			}else{
//				//沒有說明是哪一種類別
//				search_code = getMainCode(subcode, 0, 5);
//			}
//		}else{
//			err_message = "code:" + subcode + " content:" + ori_content + " 編碼長度 < 9";
//			return isSuccess;
//		}
//
//		//region 比對現在所有的參考資料夾，是否有吻合的部分
//		if (dir_objs == null){
//			dir_objs = Directory.GetDirectories(ref_file);
//		}
//		for (String dir_obj : dir_objs){
//			if (dir_obj.Contains(main_title_code.Replace("M", "") + search_code)){
//				path_dictionary = getBasename(dir_obj);
//				String[] items = getBasename(dir_obj).Split('_');
//				main_name = items[1];
//				file_code = items[0];
//				break;
//			}
//		}
//
//		if (file_code.length() == 0){
//			err_message = "code:" + subcode + " content:" + ori_content + " 沒有參考字典檔";
//			return isSuccess;
//		}
//		//endregion
//
//		//比對首要字串是否與章節名稱一致，一致需要過濾掉
//		main_name_tmp = main_title + main_name;
//		if (ori_content.IndexOf(main_name_tmp) == 0){
//			content = content.Remove(0, main_name_tmp.length());
//			if (content.length() > 0 && content.Substring(0, 1) != "，"){
//				content = main_name_tmp + content;
//			}
//		}
//
//		content = changeWord(content);
//
//		//region 過濾字串有小數.0的文字
//		var pattern = @"\d+\.?\d*";
//		var regex = new Regex(pattern);
//		if (regex.IsMatch(content)){
//			MatchCollection ms = regex.Matches(content);
//
//			var index = 0;
//			for (var item : ms){
//				var word = item as Match;
//				var sContent = content.Substring(0, word.Index - index);
//				var wContent = Convert.ToDouble(Strings.StrConv(content.Substring(word.Index - index, word.length()), VbStrConv.Narrow)).ToString("0.##");
//				var eContent = content.Substring(word.Index + word.length() - index, content.length() - (word.Index + word.length() - index));
//
//				if (!wContent.Equals(word.Value)){
//					index = index + (word.length() - wContent.length());
//				}
//				content = sContent + wContent + eContent;
//			}
//		}
//		//endregion
//
//
//		if (dsRefTable == null || dsRefTable.DataSetName != file_code){
//			dsRefTable = new DataSet();
//			dsRefTable.DataSetName = file_code;
//
//			//region 不列入此項目
//			var file_exclude = file_code + "_排除項目.txt";
//			if (File.Exists(ref_file + file_exclude)){
//				var dt_exclude = ProgramTool.UseGeneral.FileToTable_String(ref_file + file_exclude, true, '◆');
//				dt_exclude.TableName = "排除項目" + file_code;
//				dsRefTable.Tables.Add(dt_exclude);
//			}
//			//endregion
//
//			//region 同義詞
//			var content_synonym = file_code + "_同義詞.txt";
//			if (File.Exists(ref_file + content_synonym)){
//				var dtContentSynonym = ProgramTool.UseGeneral.FileToTable_String(ref_file + content_synonym, true, '◆');
//				dtContentSynonym.TableName = "同義詞" + file_code;
//				dsRefTable.Tables.Add(dtContentSynonym);
//			}
//
//			var unit_synonym = file_code + "_單位同義詞.txt";
//			if (File.Exists(ref_file + unit_synonym)){
//				var dtUnitSynonym = ProgramTool.UseGeneral.FileToTable_String(ref_file + unit_synonym, true, '◆');
//				dtUnitSynonym.TableName = "單位同義詞" + file_code;
//				dsRefTable.Tables.Add(dtUnitSynonym);
//			}
//			//endregion
//
//			//region 字典
//			if (!File.Exists(ref_file + path_dictionary + "\\Dictionary.csv")){
//				err_message = subcode + " 該項目沒有編列字典";
//				return isSuccess;
//			}
//
//			if (File.Exists(ref_file + path_dictionary + "\\Dictionary同義詞.txt")){
//				var dtDictionarySynonym = ProgramTool.UseGeneral.FileToTable_String(ref_file + path_dictionary + "\\Dictionary同義詞.txt", true, '◆');
//				dtDictionarySynonym.TableName = "Dictionary同義詞" + file_code;
//				dsRefTable.Tables.Add(dtDictionarySynonym);
//			}
//
//			var tmpDictionary = ProgramTool.UseGeneral.FileToTable_String(ref_file + path_dictionary + "\\Dictionary.csv", true, '◆');
//			tmpDictionary.TableName = "字典" + file_code;
//			tmpDictionary.Columns.Add("index", typeof(System.Int32));
//			tmpDictionary.Columns.Add("ori_name", typeof(System.String));
//			tmpDictionary.Columns.Add("num", typeof(System.Double));
//
//			tmpDictionary.Rows.RemoveAt(0);
//
//			for (int i = 0; i < tmpDictionary.Rows.Count; i++){
//				tmpDictionary.Rows[i]["index"] = i + 1;
//				tmpDictionary.Rows[i]["ori_name"] = changeWord(tmpDictionary.Rows[i]["name"].toString());
//				tmpDictionary.Rows[i]["num"] = 0.0;
//			}
//
//			dsRefTable.Tables.Add(tmpDictionary);
//			//endregion
//
//			//region 單位
//			if (File.Exists(ref_file + path_dictionary + "\\Unit.csv")){
//				var tmpUnit = ProgramTool.UseGeneral.FileToTable_String(ref_file + path_dictionary + "\\Unit.csv", true, '◆');
//				tmpUnit.TableName = "單位" + file_code;
//				dsRefTable.Tables.Add(tmpUnit);
//			}
//			//endregion
//
//			//region NotNeedCode
//			var dtNotNeedCode = ProgramTool.UseGeneral.FileToTable_String(app_path + "NotNeedCode.txt", true, ',');
//			dtNotNeedCode.TableName = "NotNeedCode";
//			dsRefTable.Tables.Add(dtNotNeedCode);
//			//endregion
//
//			//region NotNeedWord
//			var dtNotNeedWord = ProgramTool.UseGeneral.FileToTable_String(app_path + "NotNeedWord.txt", true, ',');
//			dtNotNeedWord.TableName = "NotNeedWord";
//			dsRefTable.Tables.Add(dtNotNeedWord);
//			//endregion
//		}
//
//		var dt_dictionary = dsRefTable.Tables["字典" + file_code];
//
//		DataTable dt_unit = null;
//
//		//region 不列入此項目
//		if (dsRefTable.Tables["排除項目" + file_code] != null){
//			for (int i = 0; i < dsRefTable.Tables["排除項目" + file_code].Rows.Count; i++){
//				if (content.Contains(dsRefTable.Tables["排除項目" + file_code].Rows[i]["name"].toString())){
//					err_message = "code:" + subcode + " content:" + ori_content + " 不列入此項目";
//					return isSuccess;
//				}
//			}
//		}
//		//endregion
//
//		//region 同義詞
//		if (dsRefTable.Tables["同義詞" + file_code] != null){
//			for (int i = 0; i < dsRefTable.Tables["同義詞" + file_code].Rows.Count; i++){
//				content = content.Replace(dsRefTable.Tables["同義詞" + file_code].Rows[i]["beforeword"].toString(), dsRefTable.Tables["同義詞" + file_code].Rows[i]["afterword"].toString());
//			}
//		}
//
//		if (dsRefTable.Tables["單位同義詞" + file_code] != null){
//			for (int i = 0; i < dsRefTable.Tables["單位同義詞" + file_code].Rows.Count; i++){
//				unit = unit.Replace(dsRefTable.Tables["單位同義詞" + file_code].Rows[i]["beforeword"].toString().Replace(" ", ""), dsRefTable.Tables["單位同義詞" + file_code].Rows[i]["afterword"].toString());
//			}
//		}
//		//endregion
//
//		//region 字典
//		if (dsRefTable.Tables["Dictionary同義詞" + file_code] != null){
//			for (int i = 0; i < dsRefTable.Tables["Dictionary同義詞" + file_code].Rows.Count; i++){
//				var col = dsRefTable.Tables["Dictionary同義詞" + file_code].Rows[i]["col"].toString();
//				var beforeword = dsRefTable.Tables["Dictionary同義詞" + file_code].Rows[i]["beforeword"].toString();
//				var afterword = dsRefTable.Tables["Dictionary同義詞" + file_code].Rows[i]["afterword"].toString();
//
//				var rows = dt_dictionary.Select(" id like '%" + col + "%' and name = '" + beforeword + "'");
//				foreach (var row : rows){
//					row["name"] = afterword;
//				}
//			}
//		}
//
//		//endregion
//
//		//region 單位
//		String code_unit = "", name_unit = "";
//		if (dsRefTable.Tables["單位" + file_code] != null){
//			dt_unit = dsRefTable.Tables["單位" + file_code];
//
//			var rows_unit = dt_unit.Select("name='" + unit + "'",);
//			if (rows_unit.length() > 0){
//				code_unit = rows_unit[0]["id"].toString();
//				name_unit = rows_unit[0]["name"].toString();
//			}
//		}
//		//endregion
//
//		int min = 99, max = -1, tmp_num = 0;
//		for (int i = 0; i < dt_dictionary.Rows.Count; i++){
//			dt_dictionary.Rows[i]["num"] = 0;
//			String[] items = dt_dictionary.Rows[i][0].toString().Split('.');
//			tmp_num = Convert.ToInt32(items[1].Replace("碼", ""));
//			if (min > tmp_num){
//				min = tmp_num;
//			}
//			if (max < tmp_num){
//				max = tmp_num;
//			}
//		}
//		if (max < 10){
//			max = 9;
//		}
//		if (min > 6){
//			min = 6;
//		}
//		max++;
//
//		DataTable dt = null;
//		if (main_title_code == "E" || main_title_code == "L"){
//			min = min + 1;
//		}
//
//		if (content.length() > 0){
//			dt = getCodeMatch(dt_dictionary, content, min, max, ori_content);
//		}else{
//			dt = getCodeMatch(search_code, ori_content, min, max);
//		}
//
//		if (dt.Rows.Count > 0){
//			if (String.IsNullOrEmpty(dt.Rows[0]["table_id"].toString())){
//				return false;
//			}
//
//			// PCC_TABLE = new DataTable();
//			// PCC_TABLE.Columns.Add("Ori_Content");
//			// PCC_TABLE.Columns.Add("Ori_Code");
//			// PCC_TABLE.Columns.Add("Ori_Unit");
//			// PCC_TABLE.Columns.Add("Edit_Content");
//			// PCC_TABLE.Columns.Add("Edit_Code");
//			// PCC_TABLE.Columns.Add("Edit_Unit");
//			// PCC_TABLE.Columns.Add("main_code");
//
//			// for (int i = min; i < max; i++){
//			// 	PCC_TABLE.Columns.Add("code" + i);
//			// 	PCC_TABLE.Columns.Add("name" + i);
//			// }
//			// PCC_TABLE.Columns.Add("code" + max);
//			// PCC_TABLE.Columns.Add("name" + max);
//			// PCC_TABLE.Columns.Add("hasSave", typeof(System.Boolean));
//
//			for (int irow = 0; irow < dt.Rows.Count; irow++){
//				String tmp_content = content;
//				for (int i = min; i < max; i++){
//					String tmp_name = changeWord(dt.Rows[irow]["name" + i].toString());
//
//					if (tmp_name.length() > 0 && tmp_content.Contains(tmp_name)){
//						int index = tmp_content.IndexOf(tmp_name);
//						tmp_content = tmp_content.Remove(index, tmp_name.length());
//
//						dt.Rows[irow]["has_value" + i] = true;
//					}
//				}
//
//				for (int i = max - 1; i >= min; i--){
//					if (!Convert.ToBoolean(dt.Rows[irow]["has_value" + i])){
//						String tmp_name = dt.Rows[irow]["name" + i].toString();
//						if (tmp_name.length() == 0){
//							continue;
//						}
//
//						for (int j = i - 1; j >= min; j--){
//							String stay_name = dt.Rows[irow]["name" + j].toString();
//							if (stay_name.length() == 0){
//								continue;
//							}
//							if (similar_value(tmp_name, stay_name) > 0.4){
//								dt.Rows[irow]["name" + i] = "";
//								dt.Rows[irow]["code" + i] = "0";
//								dt.Rows[irow]["rate" + i] = 0.0;
//								break;
//							}
//						}
//					}
//				}
//
//				for (int i = min; i < max; i++){
//					if (!Convert.ToBoolean(dt.Rows[irow]["has_value" + i])){
//						String tmp_name = dt.Rows[irow]["name" + i].toString();
//						if (tmp_name.length() == 0){
//							continue;
//						}
//
//						for (int j = i + 1; j < max; j++){
//							String stay_name = dt.Rows[irow]["name" + j].toString();
//							if (stay_name.length() == 0){
//								continue;
//							}
//							if (similar_value(tmp_name, stay_name) > 0.4){
//								dt.Rows[irow]["name" + i] = "";
//								dt.Rows[irow]["code" + i] = "0";
//								dt.Rows[irow]["rate" + i] = 0.0;
//								break;
//							}
//						}
//					}
//				}
//			}
//
//			for (int i = 0; i < dt.Rows.Count; i++){
//				Double rate = 0.0;
//				for (int j = min; j < max; j++){
//					rate += Convert.ToDouble(dt.Rows[i]["rate" + j]);
//				}
//				dt.Rows[i]["maping_rate"] = rate;
//			}
//
//			DataView dv = dt.DefaultView;
//			dv.Sort = " maping_rate desc";
//			dt = dv.ToTable();
//
//			for (int i = dt.Rows.Count - 1; i > 0; i--){
//				dt.Rows.RemoveAt(i);
//			}
//			dt = correctCode(dt, dt_dictionary, min, max);
//			if (dt.Rows.Count == 0){
//				return isSuccess;
//			}
//
//			Boolean noZero = true;
//			for (int i = min; i < max; i++){
//				if (noZero && dt.Rows[0]["code" + i].toString() == "0" && !Convert.ToBoolean(dt.Rows[0]["has_value" + i])){
//					noZero = false;
//				}
//
//				if (Convert.ToBoolean(dt.Rows[0]["has_value" + i])){
//					noZero = true;
//				}
//
//				if (!noZero){
//					dt.Rows[0]["code" + i] = "0";
//					dt.Rows[0]["name" + i] = "";
//					dt.Rows[0]["name" + i] = "";
//					dt.Rows[0]["has_value" + i] = false;
//				}
//			}
//
//			if (main_title_code == "E"){}
//			search_code = search_code.Substring(0, 5);
//
//			String edit_content = main_name, edit_code = main_title_code + search_code, edit_unit = name_unit;
//			var SaveNum = 0;
//			var hasSave = false;
//
//			for (int i = min; i < max; i++){
//				if (dt.Rows[0]["name" + i].toString().length() > 0) edit_content += "。" + dt.Rows[0]["name" + i].toString();
//				if (dt.Rows[0]["code" + i].toString() != "0") SaveNum++;
//
//				if (dt.Rows[0]["code" + i].toString().length() == 1 && i == min){
//					edit_code = edit_code + dt.Rows[0]["code" + i].toString();
//				}else if (dt.Rows[0]["code" + i].toString().length() == 1 && dt.Rows[0]["code" + (i - 1)].toString().length() == 1){
//					edit_code = edit_code + dt.Rows[0]["code" + i].toString();
//				}else{
//					if (i != min && dt.Rows[0]["code" + (i - 1)].toString().length() == 2 && dt.Rows[0]["code" + i].toString() == "0"){
//						continue;
//					}
//					edit_code = edit_code + dt.Rows[0]["code" + i].toString();
//				}
//			}
//
//			if (code_unit != "0"){
//				SaveNum++;
//			}
//
//			if (search_code.Substring(0, 2) == "16"){
//				hasSave = SaveNum >= 3 ? true : false;
//			}else{
//				hasSave = SaveNum >= 2 ? true : false;
//			}
//
//			edit_code = edit_code + code_unit;
//
//			if (edit_content[0].toString() == "。"){
//				edit_content = edit_content.Remove(0, 1);
//			}
//
//			LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>();
//			row.put("Ori_Content", ori_content);
//			row.put("Ori_Code", ori_code);
//			row.put("Ori_Unit", ori_unit);
//
//			edit_code = filterNotNeedCode(edit_content, edit_code);
//			edit_content = filterNotNeedContent(edit_content, edit_code, dt.Rows[0]["code" + min].toString());
//
//			row.put("Edit_Content", edit_content);
//			row.put("Edit_Code", edit_code);
//			row.put("Edit_Unit", edit_unit);
//			row.put("main_code", file_code);// main_code;
//
//
//			for (int i = min; i < max; i++){
//				row.put("code" + String.valueOf(i), dt.Rows[0]["code" + i].toString());
//				row.put("name" + String.valueOf(i), dt.Rows[0]["name" + i].toString());
//			}
//
//			row.put("code" + max, code_unit);
//			row.put("name" + max, name_unit);
//			row.put("hasSave", hasSave);
//			PCC_TABLE.add(row);
//
//			isSuccess = hasSave;
//		}else{
//			err_message = "code:" + ori_code + " content:" + ori_content + " => 沒有比對任何資訊";
//		}
//		return isSuccess;
//	}
//
//	private static String getMainCode(String maincode, int index, int number) {
//		String main_code = maincode;
//		if (maincode.length() > (number + 1)) {
//			if (IsNatural(maincode[0].toString())) {
//				main_code = maincode.substring(index, (number + 1) - index);
//			} else {
//				main_code = maincode.substring(0, number);
//			}
//		}
//		return main_code;
//	}
//
//	private static bool IsNatural(String str){
//		System.Text.RegularExpressions.Regex reg1 = new System.Text.RegularExpressions.Regex(@"^[A-Za-z]+$");
//		return reg1.IsMatch(str);
//	}
}
