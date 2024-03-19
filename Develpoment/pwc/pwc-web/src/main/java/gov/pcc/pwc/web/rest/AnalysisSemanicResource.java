package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.service.MrpSynonymService;
import gov.pcc.pwc.service.dto.AnalysisSemanicDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AnalysisSemanicResource {
    private final Logger log = LoggerFactory.getLogger(EctDownloadHisResource.class);
//	private static final String ref_path = "Z:\\program_web\\PCC_MRP\\application\\File\\";
//	private static final String sTime = "2017/1/1";
//	private static final String eTime = "2018/1/1";
//	private static final String Model = "0"; //0:處理近全部工項資料  1:處理工項編碼(Ex: M1612012111)  2:處理單一工項章節(Ex: 16210) 3:處理大類工項(Ex: 16)
//	private static final String ItemCode = ""; //模式 0 將不考慮工項編碼
//	private static final boolean isDelAnalysisSemanic = true;
//
//	private static List<LinkedHashMap<String, Object>> PCC_TABLE;
//	private static String error_msg = "";
//	private static String ref_file = "", app_path = "";
//	private static List<Map<String, Object>> dt_changeword = null;
//	private static String[] dir_objs;
//	private static List<Map<String, Object>> dsRefTable;

//    private final SqlExecutor sqlExecutor;
    private final MrpSynonymService mrpSynonymService;
    public AnalysisSemanicResource(MrpSynonymService mrpSynonymService) {
        this.mrpSynonymService = mrpSynonymService;
    }

    @GetMapping("/service/excludeVariation")
    public void excludeVariation(){//Date now) {
        mrpSynonymService.excludeVariation();
//        try{
//            System.out.println("===== START EXCLUDEVARIATION =====");
//            Date now = new Date();
//            String filename = "", process_time = "", sqlstr = "", tmpstr = "", ref_file_path = "";
//            String sql_time = "";
////        DataTable dt;
//
//            // var sTime = System.Configuration.ConfigurationSettings.AppSettings["START_TIME"];
//            // var eTime = System.Configuration.ConfigurationSettings.AppSettings["END_TIME"];
//
//            // var Model = System.Configuration.ConfigurationSettings.AppSettings["Model"];
//            //0:處理近全部工項資料  1:處理工項編碼(Ex: M1612012111)  2:處理單一工項章節(Ex: 16210) 3:處理大類工項(Ex: 16)
//            // var ItemCode = System.Configuration.ConfigurationSettings.AppSettings["ItemCode"];
//            //模式 0 將不考慮工項編碼
//
//           // ref_file_path = ref_path + "ref_FILE\\";
//
//            String sql_ItemCode = "";
//            if (Model == "1") { //處理工項編碼(Ex: M1612012111)
//                sql_ItemCode = " AND vre.CODE='" + ItemCode + "'";
//            } else if (Model == "2") { //處理單一工項章節(Ex: 16210)	case "3":  //處理大類工項(Ex: 16)
//                sql_ItemCode = " AND (vre.CODE LIKE '" + ItemCode + "%' OR vre.CODE LIKE 'M" + ItemCode + "%' )";
//            }
//
//            sql_time = "  AND CAST('__0__' AS datetime) <= cl.AWARD_BUDGET_DATE AND  cl.AWARD_BUDGET_DATE < CAST('__1__' AS datetime)";
//            if (sTime.length() > 0 && eTime.length() > 0)	{
//                sql_time = sql_time.replaceAll("__0__", sTime).replaceAll("__1__", eTime);
//            } else {
//                sqlstr = "";
//                sqlstr += " SELECT TOP 1 cl.AWARD_BUDGET_DATE ";
//                sqlstr += " FROM MRP_VERIFY_RESOURCE_CODE_ERROR vre, MRP_PROJECT_LIST cl ";
//                sqlstr += " WHERE vre.FILESOURCE_ID = cl.FILESOURCE_ID ";
//                sqlstr += " ORDER BY vre.FILESOURCE_ID DESC ";
//
//                Query qy = Query
//                    .builder()
//                    .append(sqlstr)
//                    .build();
//                List<AnalysisSemanicDTO> dt = sqlExecutor.queryForList(qy,AnalysisSemanicDTO.class);
//                System.out.println(dt.get(0));
//                // dt = ucf.getSQLtable(sqlstr, conn);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                if (dt.size() == 0) {
//                    String time = sdf.format(now);
//                    String tm[] = time.split("/");
//                    String std = String.valueOf((Integer.parseInt(tm[0]) - 1)) + "/" + tm[1] + "/01";
//                    String edd = tm[0] + "/" + tm[1] + "/01";
//                    sql_time = sql_time.replaceAll("__0__", std).replaceAll("__1__", edd);
//                    // sql_time = String.Format(sql_time, now.AddYears(-1).toString("yyyy/MM/01"), now.toString("yyyy/MM/01"));
//                } else {
//                    LocalDate t = dt.get(0).getAwardBudgetDate();
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                    String formattedString = t.format(formatter);
//                    Date abd = sdf.parse(formattedString);
//                    String time = sdf.format(abd);
//                    String tm[] = time.split("/");
//                    String std = String.valueOf((Integer.parseInt(tm[0]) - 1)) + "/" + tm[1] + "/01";
//                    String edd = tm[0] + "/" + tm[1] + "/01";
//                    sql_time = sql_time.replaceAll("__0__", std).replaceAll("__1__", edd);
//                }
//            }
//
//            sqlstr = "";
//            sqlstr += " UPDATE MRP_VERIFY_RESOURCE_CODE_ERROR ";
//            sqlstr += " SET STATUS=8 ";
//            sqlstr += " WHERE UNIT='式' AND STATUS !=8 ";
//            Query query1 = Query
//                .builder()
//                .append(sqlstr)
//                .build();
//            sqlExecutor.update(query1);
//            // ucf.setSQLprogram(sqlstr, conn);
//
//            String sqlstr0 = "";
//            sqlstr0 += " SELECT vre.ID AS anaId, CODE, NAME, UNIT, PRICE, STATUS, vre.KIND, CONVERT(nvarchar(8),cl.AWARD_BUDGET_DATE,111)+'01' AS awardBudgetDate ";
//            sqlstr0 += " FROM MRP_VERIFY_RESOURCE_CODE_ERROR vre, MRP_PROJECT_LIST cl  ";
//            sqlstr0 += " WHERE vre.FILESOURCE_ID = cl.FILESOURCE_ID  AND vre.UNIT != '式' ";
//            sqlstr0 += " AND vre.STATUS IN (4, 5, 6, 7) ";
//            sqlstr0 += sql_time;
//            sqlstr0 += sql_ItemCode;
//            // Query qy2 = Query
//            // .builder()
//            // .append(sqlstr)
//            // .build();
//            // List<ECT0201R1DTO> dt_Error_Ori = sqlExecutor.queryForList(qy2,ECT0201R1DTO.class);
//            // var dt_Error_Ori = ucf.getSQLtable(sqlstr, conn);
//            // var dt_Error = dt_Error_Ori.Clone();
//            // dt_Error.Columns["price"].DataType = typeof(System.Double);
//            // dt_Error.Columns["kind"].DataType = typeof(System.Int32);
//            // dt_Error.Columns["award_budget_date"].DataType = typeof(System.DateTime);
//            // dt_Error.Load(dt_Error_Ori.CreateDataReader());
//
//            sqlstr = "";
//            sqlstr += " SELECT DISTINCT CODE, vre.KIND ";
//            sqlstr += " FROM MRP_VERIFY_RESOURCE_CODE_ERROR vre, MRP_PROJECT_LIST cl ";
//            sqlstr += " WHERE vre.FILESOURCE_ID = cl.FILESOURCE_ID AND vre.UNIT != '式' ";
//            sqlstr += " AND vre.STATUS IN (4, 5, 6, 7) ";
//            sqlstr += sql_time;
//            sqlstr += sql_ItemCode;
//            sqlstr += " ORDER BY KIND, CODE ";
//            Query qy3 = Query
//                .builder()
//                .append(sqlstr)
//                .build();
//            List<AnalysisSemanicDTO> only_Error = sqlExecutor.queryForList(qy3,AnalysisSemanicDTO.class);
//            // var only_Error = ucf.getSQLtable(sqlstr, conn);
//
//            for (int x = 0 ; x < only_Error.size() ; x++) {
//                AnalysisSemanicDTO rowError = only_Error.get(x);
//
//                System.out.println("變異量分析 類型:" + rowError.getKind().toString() + "  工項:" + rowError.getCode().toString());
//                String sqlstr1 = sqlstr0 + " AND CODE='" + rowError.getCode().toString() + "' AND KIND=" + rowError.getKind().toString();
//                Query qy2 = Query
//                    .builder()
//                    .append(sqlstr1)
//                    .build();
//                List<AnalysisSemanicDTO> rowdata = sqlExecutor.queryForList(qy2,AnalysisSemanicDTO.class);
//
//                if (rowdata.size() < 2) {
//                    continue;
//                }
//                List<Double> price = new ArrayList<Double>();
//                for(int z = 0 ; z < rowdata.size() ; z++){
//                    if(rowdata.get(z).getPrice() != null && String.valueOf(rowdata.get(z).getPrice()) != ""){
//                        double pr = rowdata.get(z).getPrice();
//                        price.add(pr);
//                    }
//                }
//                double sum = 0;
//                for(int i = 0 ; i <price.size() ; i++){
//                    sum += price.get(i);
//                }
//                double mean = sum/price.size();
//                double total = 0;
//                for(int  i = 0 ; i < price.size() ; i++){
//                    total += (price.get(i)-mean)*(price.get(i)-mean);
//                }
//                double dev = Math.sqrt(total/price.size());
//
//                var maxValue = mean + 1 * dev;
//                var minValue = mean - 1 * dev;
//
//                String sqlstr2 = sqlstr0 + " AND CODE='" + rowError.getCode().toString() + "' AND KIND=" + rowError.getKind().toString() + " AND (" + minValue + " > PRICE OR PRICE > " + maxValue + ") ";
//                Query qy4 = Query
//                    .builder()
//                    .append(sqlstr2)
//                    .build();
//                List<AnalysisSemanicDTO> errors = sqlExecutor.queryForList(qy4,AnalysisSemanicDTO.class);
//                //seems no use
//                // var noerrors = dt_Error.Select(String.Format(" code='{0}' AND kind={1} AND ({2} < price and price < {3}) ", rowError["code"].toString(), rowError["kind"].toString(), minValue, maxValue));
//
//                String sql_err = "";
//                for (int y = 0 ; y < errors.size() ; y++) {
//                    AnalysisSemanicDTO error = errors.get(y);
//                    String status = "";
//                    String sts = error.getStatus().toString();
//                    if (sts.equals("4")) {
//                        status = "6";
//                    } else if (sts.equals("5")) {
//                        status = "7";
//                    } else{
//                        status = sts;
//                    }
//                    sql_err += " UPDATE MRP_VERIFY_RESOURCE_CODE_ERROR SET STATUS =" + status + " WHERE ID=" + error.getAnaId().toString() + "; \n";
//                }
//
//                if (sql_err !=  "") {
//                    Query updtErr = Query
//                        .builder()
//                        .append(sql_err)
//                        .build();
//                    sqlExecutor.update(updtErr);
//                    // ucf.setSQLprogram(sql_err, conn);
//                }
//
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("===== END EXCLUDEVARIATION =====");
    }

    @GetMapping("/service/analysisKeywords")
    public void analysisKeywords() {
        mrpSynonymService.analysisKeywords();
    }

    @GetMapping("/service/statisticsPrice")
    public void statisticsPrice() {
        mrpSynonymService.statisticsPrice();
    }

    @GetMapping("/service/analysisSemanic")
    public void analysisSemanic(){//Date now, String tabletype) {
        mrpSynonymService.analysisSemanic();
//        try{
//            Date now = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            String tabletype = "";
//            System.out.println("========== PCC RULES BATCH START ==========");
//            String filename = "", process_time = "", sqlstr = "", tmpstr = "", ref_file_path = "";
//            String sql_time = "";
//            List<Map<String, Object>> dt = new ArrayList<Map<String, Object>>();
//
//            ref_file_path = ref_path + "ref_FILE\\";
////		var noUnit = ProgramTool.UseGeneral.FileToTable_String(ref_file_path + "nounit.txt", ',');
//
//            var sql_ItemCode = "";
//            if(Model.equals("1")){
//                sql_ItemCode = " and rc.code='" + ItemCode + "'";
//            }else if(Model.equals("3")){
//                sql_ItemCode = " and (rc.code like '" + ItemCode + "%' or rc.code like 'M" + ItemCode + "%' or rc.code like 'W" + ItemCode + "%' )";
//            }else{
//                sql_ItemCode = "";
//            }
//
//            if (sTime.length() > 0 && eTime.length() > 0){
//                sql_time = "AND CAST('" + sTime + "' AS datetime) <= cl.award_budget_date AND  cl.award_budget_date < CAST('" + eTime + "' AS datetime) ";
//            }
//
//            if (isDelAnalysisSemanic){
//                //刪除過去以解析的資料 {X}_Code
//
//                Query query1 = Query
//                    .builder()
//                    .append("DELETE rc FROM MRP_PROJECT_LIST cl, " + tabletype + "List rl, " + tabletype + "Code rc ")
//                    .append("WHERE cl.FileSource_id = rl.FileSource_id ")
//                    .append("AND rl.id = rc." + tabletype + "_id ")
//                    .appendWhen((sTime.length() > 0 && eTime.length() > 0), "AND cast(:sTime AS datetime) <= cl.award_budget_date AND  cl.award_budget_date < cast(:eTime AS datetime) ", sTime, eTime)
//                    .append(sql_ItemCode)
//                    .append("WHERE rc.id IS NULL ORDER BY id")
//                    .build();
//                this.sqlExecutor.delete(query1);
//            }else{
//                sql_time = "AND rl.isAnalysis=0 ";
//            }
//
//
//            String[] dirs = getDirectories(ref_file_path);
//            if(Model.equals("1")){
////			dirs = dirs.Where(x => new FileInfo(x).Name.Split("_")[0].equals(getMainCode(ItemCode))).toList();
//            }else if(Model.equals("2")){
////			dirs = dirs.Where(x => new FileInfo(x).Name.Split("_")[0].equals(ItemCode)).toList();
//            }else if(Model.equals("3")){
////			dirs = dirs.Where(x => new FileInfo(x).Name.Split("_")[0].substring(0, 2) == ItemCode).toList();
//            }
//
//            for (int j = 0; j < dirs.length; j++){
//                String maincode = dirs[j].split("_")[0];
//                if (Model == "1"){
//                    maincode = ItemCode;
//                }
//
//                Query ascQry = Query
//                    .builder()
//                    .append("SELECT rl.*  FROM( ")
//                    .append("SELECT rl.id, rl.Description_tw, rl.Unit_tw, rl.ItemKey, cl.award_budget_date ")
//                    .append("FROM MRP_PROJECT_LIST cl, MRP_RESOURCE_LIST rl ")
//                    .append("WHERE cl.FileSource_id = rl.FileSource_id ")
//                    .append(sql_time)
//                    .append("AND(rl.itemkey like '" + maincode + "%' OR rl.itemkey LIKE 'M" + maincode + "%' OR rl.itemkey LIKE 'W" + maincode + "%') ")
//                    .append("AND cl.FileSource_id NOT IN ( SELECT filesource_id FROM TenderInfo_TaiwanCity titc, TaiwanCity tc  WHERE titc.filesource_id= tc.id AND tc.name='其他')) AS rl ")
//                    .append("LEFT JOIN MRP_RESOURCE_CODE rc ON rl.id = rc.Resource_id AND (rc.code LIKE '" + maincode + "%' OR rc.code LIKE 'M" + maincode + "%' OR rc.code like 'W" + maincode + "%') ")
//                    .append("WHERE rc.id IS NULL ORDER BY id")
//                    .build();
//                List<AnalysisSemanicDTO> ascData = this.sqlExecutor.queryForList(ascQry, AnalysisSemanicDTO.class);
//
//                log.debug(tabletype + " " + j + "/" + dirs.length + " : " + sdf.format(new Date()) + " => " + maincode + " (" + String.valueOf(dt.size()) + ")");
//
//                // var aSemantic = new AnalysisSemantic(ref_file_path, app_path);
//                ref_file = ref_file_path;
////            dt_changeword = ProgramTool.UseGeneral.FileToTable_String(app_path + "ChangeDefault.csv", true, '□');
//                List<String> ids = new ArrayList<String>();
//
//                for (int i = 0 ; i < dt.size() ; i++){
//                    try{
//                        // if (analysisPCCcode2(dt.get(i).get("ItemKey").toString(), dt.get(i).get("Description_tw").toString(), dt.get(i).get("Unit_tw").toString())){
//                        // 	if (PCC_TABLE.get(0).get("Edit_Unit").toString().trim().length() > 0 || noUnit.Select("code='" + PCC_TABLE.get(0).get("main_code").toString() + "'").length() > 0){
//
//                        // 		sqlstr = "INSERT INTO [" + tabletype + "Code] ([" + tabletype + "_id],[name],[code],[unit],[maincode]";
//                        // 		for (int k = 0; k < PCC_TABLE.Columns.Count - 8; k++){
//                        // 			sqlstr += ",[" + PCC_TABLE.Columns[k + 7].ColumnName + "]";
//                        // 		}
//                        // 		sqlstr += " )";
//                        // 		sqlstr += "  VALUES (" + dt.get(i).get("id").toString() + ",'" + PCC_TABLE.get(0).get("Edit_Content").toString() + "','" + PCC_TABLE.get(0).get("Edit_Code").toString() + "','" + PCC_TABLE.get(0).get("Edit_Unit").toString() + "','" + PCC_TABLE.get(0).get("main_code").toString() + "'";
//                        // 		// for (int k = 0; k < PCC_TABLE.Columns.Count - 8; k++){
//                        // 		// 	sqlstr += ",'" + PCC_TABLE.get(0).get((k + 7)).toString() + "'";
//                        // 		// }
//
//                        // 		LinkedHashMap<String, Object> selects = PCC_TABLE.get(0);
//                        // 		int aaa = 0;
//                        // 		for(Map.Entry<String, Object> entry : selects.entrySet()) {
//                        // 			if(aaa <= 7){
//                        // 				String value = String.valueOf(entry.getValue());
//                        // 				sqlstr += (",'" + value + "'");
//                        // 			}
//                        // 			aaa ++;
//                        // 		}
//                        // 		sqlstr += ") ";
//
//                        // 		Query qy = Query
//                        // 						.builder()
//                        // 						.append(tmpstr)
//                        // 						.build();
//                        // 		sqlExecutor.insert(qy);
//                        // 	}
//                        // }
//                    }catch (Exception se){
//                        se.printStackTrace();
////					log.debug(app_path + "errLog_" + DateTime.Now.ToString("yyyyMMdd") + ".log\nerr:" + se.Message);
//                    }
//
//                    ids.add(dt.get(i).get("id").toString());
//
//                    if (ids.size() > 5000){
//                        tmpstr = "UPDATE MRP_RESOURCE_LIST SET isAnalysis = 1 WHERE id IN (" + (String.join(",", ids)) + ")";
//                        Query qy = Query
//                            .builder()
//                            .append(tmpstr)
//                            .build();
//                        this.sqlExecutor.update(qy);
//                        ids.clear();
//
////					log.debug(DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") + " " + maincode + " 工項解析完成度 " + String.valueOf(i) + "/" + String.valueOf(dt.size()));
//                    }
//                }
//
//                if (!ids.isEmpty()){
//                    tmpstr = "UPDATE MRP_RESOURCE_LIST SET isAnalysis = 1 WHERE id IN (" + (String.join(",", ids)) + ")";
//                    Query qy = Query
//                        .builder()
//                        .append(tmpstr)
//                        .build();
//                    this.sqlExecutor.update(qy);
//                    ids.clear();
//				log.debug(sdf.format(new Date()) + " " + maincode + " 工項解析完成度 " + String.valueOf(dt.size()) + "/" + String.valueOf(dt.size()));
//                }
//            }
//            System.out.println("========== PCC RULES BATCH END ==========");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
	}

    private static String[] getDirectories(String ref_file_path){
        File directoryPath = new File(ref_file_path);
        FileFilter fileFilter = new FileFilter(){
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
        for(File fileName : list) {
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

