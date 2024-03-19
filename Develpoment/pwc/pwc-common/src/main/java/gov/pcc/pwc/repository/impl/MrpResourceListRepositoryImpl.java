package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.MrpResourceListRepositoryCustom;
import gov.pcc.pwc.service.dto.MRP0104RDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.odsExporter.OdsExportService;
import gov.pcc.pwc.utils.PropUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class MrpResourceListRepositoryImpl implements MrpResourceListRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public MrpResourceListRepositoryImpl(SqlExecutor sqlExecutor, OdsExportService odsExportService, SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }

    @Override
    public Map<String, Object> queryKPI(){
        Map<String, Object> rs = new HashMap<String, Object>();
        ArrayList<Map<String, String>> response = new ArrayList<>();
        Map<String, String> rsMap = new HashMap<String, String>();
        Map<String, String> dates = getStartEndDate();
        String startDate = dates.get("start");
        String endDate = dates.get("end");
        String mostSearch = mostSearchTop100();
        String failSearch = mostNoresTop100();
        String A1 = monthAnaAmt(startDate, endDate);
        if(A1 == null){
            A1 = "0";
        }
        String A2 = monthAnaScsAmt(startDate, endDate);
        if(A2 == null){
            A2 = "0";
        }
        String A3 = "";
        if(A1 != null && A2 != null && !A1.equals("") && !A2.equals("") && !A1.equals("0")){
            A3 = String.valueOf(Integer.parseInt(A2)/Integer.parseInt(A1));
        }else{
            A3 = "0";
        }
        String base1 = //"806";//"";
         getBase1();
        String A4 = "";
        if(A2 != null && base1 != null && !A2.equals("") && !base1.equals("") && !base1.equals("0")){
            A4 = String.valueOf(Integer.parseInt(A2)/Integer.parseInt(base1));
        }else{
            A4 = "0";
        }
        String A5 = getMonthDeviation(startDate, endDate);
        if(A5 == null){
            A5 = "0";
        }
        String A6 = getMonthChangeAmt(startDate, endDate);
        if(A6 == null){
            A6 = "0";
        }
        Map<String, String> mostSearchTop3 = mostSearchTop3();
        Map<String, String> mostNoresTop3 = mostNoresTop3();
        String B1 = mostSearchTop3.get("B1");
        String B2 = mostSearchTop3.get("B2");
        String B3 = mostSearchTop3.get("B3");
        String B4 = mostNoresTop3.get("B4");
        String B5 = mostNoresTop3.get("B5");
        String B6 = mostNoresTop3.get("B6");
        if(B1 != "" && B1.indexOf("^") > -1){
            B1 = B1.substring(0, B1.indexOf("^"));
        }
        if(B2 != "" && B2.indexOf("^") > -1){
            B2 = B2.substring(0, B2.indexOf("^"));
        }
        if(B3 != "" && B3.indexOf("^") > -1){
            B3 = B3.substring(0, B3.indexOf("^"));
        }
        if(B4 != "" && B4.indexOf("^") > -1){
            B4 = B4.substring(0, B4.indexOf("^"));
        }
        if(B5 != "" && B5.indexOf("^") > -1){
            B5 = B5.substring(0, B5.indexOf("^"));
        }
        if(B6 != "" && B6.indexOf("^") > -1){
            B6 = B6.substring(0, B6.indexOf("^"));
        }
        rsMap.put("A1", A1);
        rsMap.put("A2", A2);
        rsMap.put("A3", A3);
        rsMap.put("A4", A4);
        rsMap.put("A5", A5);
        rsMap.put("A6", A6);
        rsMap.put("B1", B1);
        rsMap.put("B2", B2);
        rsMap.put("B3", B3);
        rsMap.put("B4", B4);
        rsMap.put("B5", B5);
        rsMap.put("B6", B6);
        rsMap.put("mostSearch", mostSearch);
        rsMap.put("failSearch", failSearch);
        response.add(rsMap);
        rs.put("response",response);
        rs.put("status","0");
        return rs;
    }

    private Map<String, String> getStartEndDate(){
        Map<String, String> dates = new HashMap<String, String>();
        Query ascQry = Query
                .builder()
                .append("SELECT max(CREATE_DATE) as max_date FROM MRP_VERIFY_TABLE")
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        Date maxD = Date.from(ascData.get(0).getMaxDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(maxD);
        calendar.add(Calendar.MONTH, -2);
        Date dt1 = calendar.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(maxD);
        calendar2.add(Calendar.MONTH, 1);
        Date dt2 = calendar2.getTime();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String startD = sdf2.format(dt1);
        String endD = sdf2.format(dt2);
        dates.put("start", startD);
        dates.put("end", endD);
        return dates;
    }

    private String monthAnaAmt(String startDate, String endDate){
        Query ascQry = Query
                .builder()
                .append("SELECT FORMAT(COUNT(*), '#,0') AS A1 FROM (SELECT Extent1.* FROM MRP_RESOURCE_LIST AS Extent1")
                .append("JOIN MRP_PROJECT_LIST AS Join2 ON Extent1.FILESOURCE_ID = Join2.FILESOURCE_ID")
                .append("WHERE :startDdate <= Join2.award_budget_date AND Join2.AWARD_BUDGET_DATE < :endDate) AS vResourceList", startDate, endDate)
//                .append("SELECT count(*) AS A1 FROM MRP_RESOURCE_LIST a JOIN MRP_PROJECT_LIST b")
//                .append("ON b.FILESOURCE_ID=a.FILESOURCE_ID WHERE b.AWARD_BUDGET_DATE  BETWEEN :startDate AND :endDate", startDate, endDate)
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        return ascData.get(0).getA1();
    }

    private String monthAnaScsAmt(String startDate, String endDate){
        Query ascQry = Query
                .builder()
                .append("SELECT FORMAT(count(*), '#,0') AS A2 FROM (SELECT Extent5.* FROM MRP_RESOURCE_Code Extent5 ")
                .append("JOIN (SELECT Extent1.* FROM MRP_RESOURCE_LIST AS Extent1 JOIN MRP_PROJECT_LIST AS Join2 ON Extent1.FILESOURCE_ID = Join2.FILESOURCE_ID")
                .append("WHERE :startDdate <= Join2.AWARD_BUDGET_DATE AND Join2.AWARD_BUDGET_DATE < :endDate) Extent1", startDate, endDate)
                .append("ON Extent1.id = Extent5.resource_id) AS vResourceCode")
//                .append("SELECT count(*) AS A2 FROM MRP_RESOURCE_CODE a JOIN MRP_PROJECT_LIST b")
//                .append("ON b.FILESOURCE_ID=a.RESOURCE_ID WHERE a.CODE != null AND b.AWARD_BUDGET_DATE BETWEEN :startDate AND :endDate", startDate, endDate)
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        return ascData.get(0).getA2();
    }

    private String getBase1(){
        Query ascQry = Query
                .builder()
                .append("SELECT count(*) AS base1 FROM MRP_MRSBASEA")
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        return ascData.get(0).getBase1();
    }

    private String getMonthDeviation(String startDate, String endDate){
        Query ascQry = Query
                .builder()
                .append("SELECT FORMAT(COUNT(*), '#,0') AS A5 FROM (SELECT Extent1.* FROM MRP_VERIFY_RESOURCE_CODE Extent1")
                .append("JOIN (SELECT Extent1.* FROM MRP_RESOURCE_LIST AS Extent1 JOIN MRP_PROJECT_LIST AS Join2 ON Extent1.FILESOURCE_ID = Join2.FILESOURCE_ID")
                .append("WHERE :startDdate <= Join2.AWARD_BUDGET_DATE AND Join2.AWARD_BUDGET_DATE < :endDate) Extent4", startDate, endDate)
                .append("ON Extent1.RESOURCE_ID = Extent4.ID) AS vVerifyResourceCode")
//                .append("SELECT count(*) AS A5 FROM MRP_VERIFY_RESOURCE_CODE a JOIN MRP_PROJECT_LIST b")
//                .append("ON b.FILESOURCE_ID=a.FILESOURCE_ID WHERE b.AWARD_BUDGET_DATE BETWEEN :startDdate AND :endDate", startDate, endDate)
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        return ascData.get(0).getA5();
    }

    private String getMonthChangeAmt(String startDate, String endDate){
        Query ascQry = Query
                .builder()
                .append("SELECT 6 AS SORT_NO, N'本月變動列管工項量' AS KPI_DESC, FORMAT(COUNT(*), '#,0') AS A6")
                .append("FROM (SELECT Extent1.* FROM MRP_VERIFY_RESOURCE_CODE AS Extent1")
                .append("JOIN (SELECT Extent1.* FROM MRP_RESOURCE_LIST AS Extent1")
                .append("JOIN MRP_PROJECT_LIST AS Join2 ON Extent1.FILESOURCE_ID = Join2.FILESOURCE_ID")
                .append("WHERE :startDdate <= Join2.AWARD_BUDGET_DATE AND Join2.AWARD_BUDGET_DATE < :endDate) AS Extent4", startDate, endDate)
                .append("ON Extent1.RESOURCE_ID = Extent4.ID) AS a")
//                .append("SELECT count(*) AS A6 FROM MRP_VERIFY_RESOURCE_CODE a JOIN MRP_PROJECT_LIST b")
//                .append("ON b.FILESOURCE_ID=a.FILESOURCE_ID WHERE a.STATUS=2 AND b.AWARD_BUDGET_DATE BETWEEN :startDdate AND :endDate", startDate, endDate)
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        return ascData.get(0).getA6();
    }

    private Map<String, String> mostSearchTop3(){
        Map<String, String> topThree = new HashMap<String, String>();
        Query ascQry = Query
                .builder()
                .append("SELECT REPLACE(KEYWORD_NAME, N'。', N'，') AS KEYWORD_NAME, NUM AS CNT FROM (")
                .append("SELECT KEYWORD_NAME, SEARCH_SUCCESS, SUM(KEYWORD_AMOUNT) AS NUM")
                .append(", ROW_NUMBER() OVER (PARTITION BY SEARCH_SUCCESS ORDER BY SUM(KEYWORD_AMOUNT) DESC) RN")
                .append("FROM (SELECT KEYWORD_AMOUNT, ISNULL(SEARCH_SUCCESS, 0) SEARCH_SUCCESS")
                .append(", SUBSTRING(KEYWORD_NAME, 1, CHARINDEX('^', KEYWORD_NAME)-1) KEYWORD_NAME")
                .append("FROM MRP_COMMON_KEYWORD WHERE KEYWORD_NAME NOT LIKE N'%?%'")
                .append("AND KEYWORD_NAME NOT LIKE N'%請輸入關鍵字%' AND KEYWORD_NAME LIKE N'%^%' AND KEYWORD_NAME NOT LIKE N'^%') ck")
                .append("GROUP BY KEYWORD_NAME, SEARCH_SUCCESS)KPI WHERE RN <=3 AND SEARCH_SUCCESS=1 ORDER BY 2,1 DESC")
//                .append("SELECT TOP(3) count(KEYWORD_NAME) AS CNT, KEYWORD_NAME FROM MRP_COMMON_KEYWORD")
//                .append("WHERE SEARCH_SUCCESS=1 AND KEYWORD_NAME NOT LIKE '%?%' AND KEYWORD_NAME NOT LIKE '%請輸入關鍵字%' GROUP BY KEYWORD_NAME ORDER BY 2,1 DESC")
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        if(ascData.size() > 0 && ascData.get(0) != null){
            topThree.put("B1", String.valueOf(ascData.get(0).getKeywordName()));
            if(ascData.size() > 1 && ascData.get(1) != null){
                topThree.put("B2", String.valueOf(ascData.get(1).getKeywordName()));
                if(ascData.size() > 2 && ascData.get(2) != null){
                    topThree.put("B3", String.valueOf(ascData.get(2).getKeywordName()));
                }else{
                    topThree.put("B3", "");
                }
            }else{
                topThree.put("B2", "");
                topThree.put("B3", "");
            }
        }else{
            topThree.put("B1", "");
            topThree.put("B2", "");
            topThree.put("B3", "");
        }
        return topThree;
    }

    private Map<String, String> mostNoresTop3(){
        Map<String, String> topThree = new HashMap<String, String>();
        Query ascQry = Query
                .builder()
                .append("SELECT REPLACE(KEYWORD_NAME, N'。', N'，') AS KEYWORD_NAME, NUM AS CNT FROM (")
                .append("SELECT KEYWORD_NAME, SEARCH_SUCCESS, SUM(KEYWORD_AMOUNT) AS NUM")
                .append(", ROW_NUMBER() OVER (PARTITION BY SEARCH_SUCCESS ORDER BY SUM(KEYWORD_AMOUNT) DESC) RN")
                .append("FROM (SELECT KEYWORD_AMOUNT, ISNULL(SEARCH_SUCCESS, 0) SEARCH_SUCCESS")
                .append(", SUBSTRING(KEYWORD_NAME, 1, CHARINDEX('^', KEYWORD_NAME)-1) KEYWORD_NAME")
                .append("FROM MRP_COMMON_KEYWORD WHERE KEYWORD_NAME NOT LIKE N'%?%'")
                .append("AND KEYWORD_NAME NOT LIKE N'%請輸入關鍵字%' AND KEYWORD_NAME LIKE N'%^%' AND KEYWORD_NAME NOT LIKE N'^%') ck")
                .append("GROUP BY KEYWORD_NAME, SEARCH_SUCCESS)KPI WHERE RN <=3 AND SEARCH_SUCCESS=0 ORDER BY 2,1 DESC")
//                .append("SELECT TOP(3) count(KEYWORD_NAME) AS CNT, KEYWORD_NAME FROM MRP_COMMON_KEYWORD")
//                .append("WHERE SEARCH_SUCCESS=0 AND KEYWORD_NAME NOT LIKE '%?%' AND KEYWORD_NAME NOT LIKE '%請輸入關鍵字%' GROUP BY KEYWORD_NAME ORDER BY 2,1 DESC")
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        if(ascData.size() > 0 && ascData.get(0) != null){
            topThree.put("B4", String.valueOf(ascData.get(0).getKeywordName()));
            if(ascData.size() > 1 && ascData.get(1) != null){
                topThree.put("B5", String.valueOf(ascData.get(1).getKeywordName()));
                if(ascData.size() > 2 && ascData.get(2) != null){
                    topThree.put("B6", String.valueOf(ascData.get(2).getKeywordName()));
                }else{
                    topThree.put("B6", "");
                }
            }else{
                topThree.put("B5", "");
                topThree.put("B6", "");
            }
        }else{
            topThree.put("B4", "");
            topThree.put("B5", "");
            topThree.put("B6", "");
        }
        return topThree;
    }

    private String mostSearchTop100(){
        Map<String, String> dates = new HashMap<String, String>();
        Query ascQry = Query
                .builder()
                .append("SELECT REPLACE(KEYWORD_NAME, N'。', N'，') AS KEYWORD_NAME, NUM AS CNT FROM (")
                .append("SELECT KEYWORD_NAME, SEARCH_SUCCESS, SUM(KEYWORD_AMOUNT) AS NUM")
                .append(", ROW_NUMBER() OVER (PARTITION BY SEARCH_SUCCESS ORDER BY SUM(KEYWORD_AMOUNT) DESC) RN")
                .append("FROM (SELECT KEYWORD_AMOUNT, ISNULL(SEARCH_SUCCESS, 0) SEARCH_SUCCESS")
                .append(", SUBSTRING(KEYWORD_NAME, 1, CHARINDEX('^', KEYWORD_NAME)-1) KEYWORD_NAME")
                .append("FROM MRP_COMMON_KEYWORD WHERE KEYWORD_NAME NOT LIKE N'%?%'")
                .append("AND KEYWORD_NAME NOT LIKE N'%請輸入關鍵字%' AND KEYWORD_NAME LIKE N'%^%' AND KEYWORD_NAME NOT LIKE N'^%') ck")
                .append("GROUP BY KEYWORD_NAME, SEARCH_SUCCESS)KPI WHERE RN <=100 AND SEARCH_SUCCESS=1 ORDER BY 2,1 DESC")
//                .append("SELECT TOP(100) count(KEYWORD_NAME) AS CNT, KEYWORD_NAME FROM MRP_COMMON_KEYWORD")
//                .append("WHERE SEARCH_SUCCESS=1 AND KEYWORD_NAME NOT LIKE '%?%' AND KEYWORD_NAME NOT LIKE '%請輸入關鍵字%' GROUP BY KEYWORD_NAME ORDER BY 2,1 DESC")
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        String fileName = createXls(ascData, "0");
        return fileName;
    }

    private String mostNoresTop100(){
        Map<String, String> dates = new HashMap<String, String>();
        Query ascQry = Query
                .builder()
                .append("SELECT REPLACE(KEYWORD_NAME, N'。', N'，') AS KEYWORD_NAME, NUM AS CNT FROM (")
                .append("SELECT KEYWORD_NAME, SEARCH_SUCCESS, SUM(KEYWORD_AMOUNT) AS NUM")
                .append(", ROW_NUMBER() OVER (PARTITION BY SEARCH_SUCCESS ORDER BY SUM(KEYWORD_AMOUNT) DESC) RN")
                .append("FROM (SELECT KEYWORD_AMOUNT, ISNULL(SEARCH_SUCCESS, 0) SEARCH_SUCCESS")
                .append(", SUBSTRING(KEYWORD_NAME, 1, CHARINDEX('^', KEYWORD_NAME)-1) KEYWORD_NAME")
                .append("FROM MRP_COMMON_KEYWORD WHERE KEYWORD_NAME NOT LIKE N'%?%'")
                .append("AND KEYWORD_NAME NOT LIKE N'%請輸入關鍵字%' AND KEYWORD_NAME LIKE N'%^%' AND KEYWORD_NAME NOT LIKE N'^%') ck")
                .append("GROUP BY KEYWORD_NAME, SEARCH_SUCCESS)KPI WHERE RN <=100 AND SEARCH_SUCCESS=0 ORDER BY 2,1 DESC")
//                .append("SELECT TOP(100) count(KEYWORD_NAME) AS CNT, KEYWORD_NAME FROM MRP_COMMON_KEYWORD")
//                .append("WHERE SEARCH_SUCCESS=0 AND KEYWORD_NAME NOT LIKE '%?%' AND KEYWORD_NAME NOT LIKE '%請輸入關鍵字%' GROUP BY KEYWORD_NAME ORDER BY 2,1 DESC")
                .build();
        List<MRP0104RDTO> ascData = sqlExecutor.queryForList(ascQry, MRP0104RDTO.class);
        String fileName = createXls(ascData, "1");
        return fileName;
    }

    private String createXls(List<MRP0104RDTO> items, String type){
        try {
            System.out.println("CREATE XLS START");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Date now = new Date();
            String fn = sdf.format(now);
            String tp = "";
            if(type.equals("0")){
                tp = "most_search_";
            }else{
                tp = "faild_search_";
            }
            // 開新檔案
            String path = //"/stsdat/pwc/mrp/"
                    PropUtil.getPropVal("mrp.file.savepath") + tp + fn + ".xls";//"要匯出的檔案 e.g. C:\\測試.xlsx";
            Workbook wb = null;
            wb = new HSSFWorkbook();
            // 設定儲存格資料
            Sheet sheet = wb.createSheet();
            Row row = null;
            Cell cell = null;

            for(int r = 0 ; r < (items.size() + 2) ; r++){
                row = sheet.createRow(r);
                if(r < 2){
                    if(r < 1){
                        cell = row.createCell(0);
                        if(type.equals("0")){
                            cell.setCellValue("公共工程價格資料庫(解析KPI管理 最常查詢工項前100項)");
                        }else{
                            cell.setCellValue("公共工程價格資料庫(解析KPI管理 最常查詢不到工項前100項)");
                        }
                        sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
                    }else{
                        for(int c = 0 ; c < 3 ; c++){
                            cell = row.createCell(c);
                            if(c == 0){
                                cell.setCellValue("編號");
                            }else if(c == 1){
                                cell.setCellValue("關鍵字");
                            }else if(c == 2){
                                cell.setCellValue("查詢次數");
                            }
                        }
                    }
                }else{
                    for(int c = 0 ; c < 3 ; c++){
                        cell = row.createCell(c);
                        MRP0104RDTO item = items.get(r-2);
                        if(c == 0){
                            cell.setCellValue(r-1);
                        }else if(c == 1){
                            cell.setCellValue(item.getKeywordName());
                        }else if(c == 2){
                            cell.setCellValue(item.getCnt());
                        }
                    }
                }
            }
            // 寫出 Excel
            FileOutputStream fos = new FileOutputStream(new File(path));
            wb.write(fos);
            fos.flush();
            fos.close();
            System.out.println("CREATE XLS END");
            return (tp + fn + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
