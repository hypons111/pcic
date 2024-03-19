package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.RecProjectRepositoryCustom;
import gov.pcc.pwc.service.criteria.RecProjectCriteria;
import gov.pcc.pwc.service.criteria.RecProjectQueryCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.RecGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.odsExporter.OdsExportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class RecProjectRepositoryImpl implements RecProjectRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public RecProjectRepositoryImpl(SqlExecutor sqlExecutor,OdsExportService odsExportService,SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }

    public static Date convertInstantToDate(Instant instant) {
        return instant == null ? null: Date.from(instant);
    }

    public static String parseNull(String str){ return  str == null ? "" : str; }

    public static String parseNull(Integer i) {
        return i == null ? "" : String.valueOf(i);
    }

    public static String parseNull(Double d) { return d == null ? "" : String.valueOf(d);}

    public static String parseNull(Instant instant) {
        if (instant == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dateStr = sdf.format(Date.from(instant));
            return String.valueOf(Integer.parseInt(dateStr.substring(0,4))-1911)+dateStr.substring(4,10);
        }
    }


    @Override
    public Page<RecProjectQueryDTO> findAllByCriteria(RecProjectQueryCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append("SELECT COUNT(*) FROM ")
                .append("(SELECT DISTINCT t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE, " )
                .append("s1.VALUE AS PROJ_CODE_NAME,o.ORG_NAME,p.PROJECT_NAME, a.CITY, a.VILLAGE,p.REVIEW_AMOUNT,")
                .append(" p.APPLY_AMOUNT,b. BID_PROGRESS, b.SCHEDULED_FINISH_DATE,b.BID_DATE, b.ACTUAL_FINISH_DATE FROM REC_PROJECT p")
                .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                .append("join REC_BID b on b.BID_ID = r.BID_ID")
                .append("left join ADM_AGENT o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                .append("where p.PROJECT_STATUS = -1")
                .appendWhen(criteria.getTyphoonName().size()>0,"AND t.TYPHOON_NO IN (:typhoonName)",criteria.getTyphoonName())
                .appendWhen(criteria.getProjectCodes().size()>0,"AND p.PROJECT_CODE IN (:projectCodes)",criteria.getProjectCodes())
                .appendWhen(criteria.getApplyOrgId().size()>0,"AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                .appendWhen(criteria.getBidProgressMin() != null,"AND b.BID_PROGRESS >= :bidProgressMin", criteria.getBidProgressMin() )
                .appendWhen(criteria.getBidProgressMax() != null,"AND b.BID_PROGRESS <= :bidProgressMax", criteria.getBidProgressMax() )
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() +"%")
                .appendWhen(criteria.getReviewAmountMin() != null,"AND p.REVIEW_AMOUNT >= :reviewAmountMin" ,criteria.getReviewAmountMin())
                .appendWhen(criteria.getReviewAmountMax() != null,"AND p.REVIEW_AMOUNT <= :reviewAmountMax" ,criteria.getReviewAmountMax())
                .append(") AS rec_project_query")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount,Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER(ORDER BY "+criteria.getOrderBy()+") as row_num,")
                .append("t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE,")
                .append("s1.VALUE AS PROJ_CODE_NAME,o.ORG_NAME,p.PROJECT_NAME, a.CITY, a.VILLAGE,p.REVIEW_AMOUNT,")
                .append(" p.APPLY_AMOUNT,b. BID_PROGRESS, b.SCHEDULED_FINISH_DATE,b.BID_DATE, b.ACTUAL_FINISH_DATE FROM REC_PROJECT p")
                .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                .append("join REC_BID b on b.BID_ID = r.BID_ID")
                .append("left join ADM_AGENT o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                .append("where p.PROJECT_STATUS = -1")
                .appendWhen(criteria.getTyphoonName().size()>0,"AND t.TYPHOON_NO in (:typhoonName)",criteria.getTyphoonName())
                .appendWhen(criteria.getProjectCodes().size()>0,"AND p.PROJECT_CODE in (:projectCodes)",criteria.getProjectCodes())
                .appendWhen(criteria.getApplyOrgId().size()>0,"AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                .appendWhen(criteria.getBidProgressMin() != null,"AND b.BID_PROGRESS >= :bidProgressMin", criteria.getBidProgressMin() )
                .appendWhen(criteria.getBidProgressMax() != null,"AND b.BID_PROGRESS <= :bidProgressMax", criteria.getBidProgressMax() )
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() +"%")
                .appendWhen(criteria.getReviewAmountMin() != null,"AND p.REVIEW_AMOUNT >= :reviewAmountMin" ,criteria.getReviewAmountMin())
                .appendWhen(criteria.getReviewAmountMax() != null,"AND p.REVIEW_AMOUNT <= :reviewAmountMax" ,criteria.getReviewAmountMax())
                .append("ORDER BY " + criteria.getOrderBy())
                .append("OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                .build();
        List<RecProjectQueryDTO> recProjectQueryDTOs = sqlExecutor.queryForList(query,RecProjectQueryDTO.class);
        Page<RecProjectQueryDTO> resultPage = new PageImpl<>(recProjectQueryDTOs,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public Page<RecProject0303DTO> findAllByCriteria(RecProjectCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append("SELECT COUNT(*) FROM ")
                .append("(SELECT DISTINCT p.PROJECT_NO, p.APPLY_SUBMIT_NO, p.APPROVE_NO, p.PROJECT_CODE,")
                .append("s1.VALUE AS PROJECT_CODE_NAME, p.PROJECT_NAME, a.CITY, a.VILLAGE, p.APPLY_AMOUNT, p.REVIEW_AMOUNT,")
                .append("p.PROJECT_STATUS, s2.VALUE AS PROJECT_STATUS_NAME, p.STATUS_CHANGE_DATE,")
                .append("p.DAMAGE_DATE, p.DAMAGE_DESCRIPT, p.DANGEROUS_DEGREE, p.DANGEROUS_DESCRIPT,")
                .append("p.CREATE_USER, p.CREATE_TIME, p.UPDATE_USER, p.UPDATE_TIME FROM REC_PROJECT p")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                .append("where 1=1")
                .append("and (a.AREA_NO in (select min_area_no from (select project_no, min(area_no) min_area_no from rec_area group by project_no) v)")
                .append("     or a.AREA_NO is null)")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgId()),"AND p.APPLY_ORG_ID in (select ORG_ID from REC_APPLY_ORG where ORG_ID = :orgId or PARENT_ORG_ID = :orgId)", criteria.getOrgId(), criteria.getOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getTyphoonNo()),"AND p.TYPHOON_NO = :typhoonNo",  criteria.getTyphoonNo().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getApplyOrgId()),"AND p.APPLY_ORG_ID = :applyOrgId",  criteria.getApplyOrgId().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectCode()),"AND p.PROJECT_CODE = :projectCode",criteria.getProjectCode().trim())
                .appendWhen(criteria.getApplyAmount().intValue() == 999 , "AND p.APPLY_AMOUNT <= 999")
                .appendWhen(criteria.getApplyAmount().intValue() == 4999 , "AND p.APPLY_AMOUNT >= 1000 AND p.APPLY_AMOUNT <= 4999")
                .appendWhen(criteria.getApplyAmount().intValue() == 5000 , "AND p.APPLY_AMOUNT >= 5000")
                .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.city = :city",criteria.getCity().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectStatus()),"AND p.PROJECT_STATUS = :projectStatus", criteria.getProjectStatus().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() + "%" )
                .appendWhen(criteria.getIsReviewChangeAmount() != null,"AND p.IS_REVIEW_CHANGE_AMOUNT = :isReviewChangeAmount", criteria.getIsReviewChangeAmount())
                .appendWhen(criteria.getIsSurveyPlan() != null,"AND p.IS_SURVEY_PLAN = :isSurveyPlan", criteria.getIsSurveyPlan())
                .append(") AS x")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER (ORDER BY "+ criteria.getOrderBy()+ ") AS row_num,")
                .append("p.PROJECT_NO, p.APPLY_SUBMIT_NO, p.APPROVE_NO, p.PROJECT_CODE,")
                .append("s1.VALUE AS PROJECT_CODE_NAME, p.PROJECT_NAME, a.CITY, a.VILLAGE, p.APPLY_AMOUNT, p.REVIEW_AMOUNT,")
                .append("p.PROJECT_STATUS, s2.VALUE AS PROJECT_STATUS_NAME, p.STATUS_CHANGE_DATE,")
                .append("p.DAMAGE_DATE, p.DAMAGE_DESCRIPT, p.DANGEROUS_DEGREE, p.DANGEROUS_DESCRIPT,")
                .append("p.CREATE_USER, p.CREATE_TIME, p.UPDATE_USER, p.UPDATE_TIME FROM REC_PROJECT p")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                .append("where 1=1")
                .append("and (a.AREA_NO in (select min_area_no from (select project_no, min(area_no) min_area_no from rec_area group by project_no) v)")
                .append("     or a.AREA_NO is null)")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgId()),"AND p.APPLY_ORG_ID in (select ORG_ID from REC_APPLY_ORG where ORG_ID = :orgId or PARENT_ORG_ID = :orgId)", criteria.getOrgId(), criteria.getOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getTyphoonNo()),"AND p.TYPHOON_NO = :typhoonNo",  criteria.getTyphoonNo().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getApplyOrgId()),"AND p.APPLY_ORG_ID = :applyOrgId",  criteria.getApplyOrgId().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectCode()),"AND p.PROJECT_CODE = :projectCode",criteria.getProjectCode().trim())
                .appendWhen(criteria.getApplyAmount().intValue() == 999 , "AND p.APPLY_AMOUNT <= 999")
                .appendWhen(criteria.getApplyAmount().intValue() == 4999 , "AND p.APPLY_AMOUNT >= 1000 AND p.APPLY_AMOUNT <= 4999")
                .appendWhen(criteria.getApplyAmount().intValue() == 5000 , "AND p.APPLY_AMOUNT >= 5000")
                .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.city = :city",criteria.getCity().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectStatus()),"AND p.PROJECT_STATUS = :projectStatus", criteria.getProjectStatus().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() + "%" )
                .appendWhen(criteria.getIsReviewChangeAmount() != null,"AND p.IS_REVIEW_CHANGE_AMOUNT = :isReviewChangeAmount", criteria.getIsReviewChangeAmount())
                .appendWhen(criteria.getIsSurveyPlan() != null,"AND p.IS_SURVEY_PLAN = :isSurveyPlan", criteria.getIsSurveyPlan())
                .append("ORDER BY "+criteria.getOrderBy())
                .append("OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                .build();
        List<RecProject0303DTO> recProjectDTOS = sqlExecutor.queryForList(query, RecProject0303DTO.class);
        Page<RecProject0303DTO> resultPage = new PageImpl<>(recProjectDTOS,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public List<RecSelectOptionsDTO> findAllOrgByQuery() {
        Query query = Query
                .builder()
                .append("SELECT AREA_NAME, ORG_ID, LEFT(ORG_NAME,3) ORG_NAME FROM REC_ORG_ROLE")
                .append("WHERE AUTHORITY_LEVEL = 4")
                .append("ORDER BY 1,2")
                .build();
        List <RecOrgRoleAreaListDTO> originAreaList = sqlExecutor.queryForList(query,RecOrgRoleAreaListDTO.class);

        Map<String, List<RecOrgRoleAreaListDTO>> wrappedMap = new TreeMap<>();
        for (int i = 0; i < originAreaList.size(); i++) {
            if(!wrappedMap.containsKey(originAreaList.get(i).getAreaName())){
                List list = new ArrayList<RecOrgRoleAreaListDTO>();
                list.add(originAreaList.get(i));
                wrappedMap.put(originAreaList.get(i).getAreaName(), list);
            } else {
                wrappedMap.get(originAreaList.get(i).getAreaName()).add(originAreaList.get(i));
            }
        }

        List wrappedList = new ArrayList<RecSelectOptionsDTO>();
        wrappedMap.forEach((k, v) -> {
            RecSelectOptionsDTO dto = new RecSelectOptionsDTO();
            dto.setParents("-----"+k);
            List childList = new ArrayList<RecSelectOptionsChildDTO>();
            for(RecOrgRoleAreaListDTO element:v){
                RecSelectOptionsChildDTO dtoChild = new RecSelectOptionsChildDTO();
                dtoChild.setText(element.getOrgName());
                dtoChild.setValue(element.getOrgId());
                childList.add(dtoChild);
            }
            dto.setChild(childList);
            wrappedList.add(dto);
        });

        return wrappedList;
    }

    @Override
    public List<RecSelectOptionsDTO> findAllTyphoonByQuery() {
        //取出原始資料
        Query query = Query
                .builder()
                .append("SELECT TYPHOON_NO AS value, TYPHOON_NAME AS text ,")
                .append("YEAR (APPLY_START_TIME) AS PARENT  FROM REC_TYPHOON ORDER BY PARENT")
                .build();
        List <RecSelectOptionsChildDTO> originTyphoonList = sqlExecutor.queryForList(query,RecSelectOptionsChildDTO.class);
        //分離月份
        Set<String> parentSet = new TreeSet();
        for (RecSelectOptionsChildDTO element : originTyphoonList) {
            parentSet.add(element.getParent());
        }
        //依月分包裝
        List<RecSelectOptionsDTO> wrappedList = new ArrayList();
        for (String parentSeparate : parentSet) {
            List<RecSelectOptionsChildDTO> childList
                    = originTyphoonList.stream()
                    .filter(e -> e.getParent().equals(parentSeparate))
                    .collect(Collectors.toList());
            RecSelectOptionsDTO dto = new RecSelectOptionsDTO();
            dto.setParents("-----" + parentSeparate + "年度風災");
            dto.setChild(childList);
            wrappedList.add(dto);
        }
        return wrappedList;
    }

    @Override
    public List<RecSelectOptionsDTO> findAllProjectCode() {
        Query query = Query
                .builder()
                .append("SELECT DATA_KEY AS value, VALUE AS text  FROM ADM_SYS_CODE ")
                .append("WHERE MODULE_TYPE ='REC' AND DATA_TYPE ='REC_PROJECT_CODE'")
                .build();
        List <RecSelectOptionsChildDTO> originPrjCodeList = sqlExecutor.queryForList(query,RecSelectOptionsChildDTO.class);

        List<RecSelectOptionsDTO> wrappedList = new ArrayList();
        RecSelectOptionsDTO dto = new RecSelectOptionsDTO();
        dto.setParents("全選");
        dto.setChild(originPrjCodeList);
        wrappedList.add(dto);

        return wrappedList;
    }

    @Override
    public void exportOdsByCriteria(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException {

        Query query = Query
                .builder()
                .append(" SELECT t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE,")
                .append("s1.VALUE AS PROJ_CODE_NAME,o.ORG_NAME,p.PROJECT_NAME, a.CITY, a.VILLAGE,p.REVIEW_AMOUNT,")
                .append(" p.APPLY_AMOUNT,b. BID_PROGRESS, b.SCHEDULED_FINISH_DATE,b.BID_DATE, b.ACTUAL_FINISH_DATE FROM REC_PROJECT p")
                .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                .append("join REC_BID b on b.BID_ID = r.BID_ID")
                .append("left join ADM_AGENT o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                .append("where p.PROJECT_STATUS = -1")
                .appendWhen(criteria.getTyphoonName().size()>0,"AND t.TYPHOON_NO in (:typhoonName)",criteria.getTyphoonName())
                .appendWhen(criteria.getProjectCodes().size()>0,"AND p.PROJECT_CODE in (:projectCodes)",criteria.getProjectCodes())
                .appendWhen(criteria.getApplyOrgId().size()>0,"AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                .appendWhen(criteria.getBidProgressMin() != null,"AND b.BID_PROGRESS >= :bidProgressMin", criteria.getBidProgressMin() )
                .appendWhen(criteria.getBidProgressMax() != null,"AND b.BID_PROGRESS <= :bidProgressMax", criteria.getBidProgressMax() )
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() +"%")
                .appendWhen(criteria.getReviewAmountMin() != null,"AND p.REVIEW_AMOUNT >= :reviewAmountMin" ,criteria.getReviewAmountMin())
                .appendWhen(criteria.getReviewAmountMax() != null,"AND p.REVIEW_AMOUNT <= :reviewAmountMax" ,criteria.getReviewAmountMax())
                .append("ORDER BY t.TYPHOON_NO,p.APPROVE_NO,o.ORG_NAME,p.PROJECT_CODE,p.PROJECT_NO")
                .build();
        List<RecProjectQueryDTO> exportRawData = sqlExecutor.queryForList(query, RecProjectQueryDTO.class);
        Map<Integer, List<String>> paramMap = new TreeMap<>();
        //欄位名設置
        List<String> column = Arrays.asList(new String[]{"颱風", "核列序號", "工程代碼", "機關名稱", "復建工程名稱", "鄉(鎮市)",
                "村(里)", "提報經費(千元)", "審議經費(千元)", "實際進度%", "完工期限", "發包日期", "完工日期"
        });
        paramMap.put(0,column);
        //內容設置
        int rowNum = 1;
        for (RecProjectQueryDTO element : exportRawData) {
            List<String> columnList = new LinkedList<>();
            columnList.add(parseNull(element.getTyphoonName()));
            columnList.add(parseNull(element.getApproveNo()));
            columnList.add(parseNull(element.getProjectCode()));
            columnList.add(parseNull(element.getOrgName()));
            columnList.add(parseNull(element.getProjectName()));
            columnList.add(parseNull(element.getCity()));
            columnList.add(parseNull(element.getVillage()));
            columnList.add(parseNull(element.getReviewAmount().toString()));
            columnList.add(parseNull(element.getApplyAmount().toString()));
            columnList.add(parseNull(element.getBidProgress().toString()));
            columnList.add(parseNull(element.getScheduledFinishDate()));
            columnList.add(parseNull(element.getBidDate()));
            columnList.add(parseNull(element.getActualFinishDate()));
            paramMap.put(rowNum,columnList);
            rowNum++;
        }
        odsExportService.exportByParam(paramMap,downloadFilePath,fileName);

    }

    @Override
    public void exportKmlByCriteria(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException {
        //取得原始資料
        Query query = Query
                .builder()
                .append("SELECT prj_result.PROJECT_NAME ,ar.ORIGINAL_DESIGN,ar.GPS_X,ar.GPS_Y FROM")
                .append("(SELECT DISTINCT p.PROJECT_NO ,p.PROJECT_NAME FROM REC_PROJECT p")
                .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                .append("join REC_BID b on b.BID_ID = r.BID_ID")
                .append("left join ADM_AGENT o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                .append("where p.PROJECT_STATUS = -1")
                .appendWhen(criteria.getTyphoonName().size()>0,"AND t.TYPHOON_NO in (:typhoonName)",criteria.getTyphoonName())
                .appendWhen(criteria.getProjectCodes().size()>0,"AND p.PROJECT_CODE in (:projectCodes)",criteria.getProjectCodes())
                .appendWhen(criteria.getApplyOrgId().size()>0,"AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                .appendWhen(criteria.getBidProgressMin() != null,"AND b.BID_PROGRESS >= :bidProgressMin", criteria.getBidProgressMin() )
                .appendWhen(criteria.getBidProgressMax() != null,"AND b.BID_PROGRESS <= :bidProgressMax", criteria.getBidProgressMax() )
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() +"%")
                .appendWhen(criteria.getReviewAmountMin() != null,"AND p.REVIEW_AMOUNT >= :reviewAmountMin" ,criteria.getReviewAmountMin())
                .appendWhen(criteria.getReviewAmountMax() != null,"AND p.REVIEW_AMOUNT <= :reviewAmountMax" ,criteria.getReviewAmountMax())
                .append(") AS prj_result")
                .append("JOIN  REC_AREA ar ON ar.PROJECT_NO =prj_result.PROJECT_NO")
                .build();
        List<RecProjectKmlDTO> kmlRawData = sqlExecutor.queryForList(query,RecProjectKmlDTO.class);

        //包裝kml
        String kmlstart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
                +"\t<Document>\n";
        String kmlConfig =
                "\t\t<name>AA</name>\n"+
                        "\t    <description>Examples of paths.</description>\n"+
                        "\t    <Style id=\"default\">\r\n"
                        + "\t        <IconStyle>\r\n"
                        + "\t            <scale>1.1</scale>\r\n"
                        + "\t            <Icon>\r\n"
                        + "\t                <href>http://maps.google.com/mapfiles/kml/paddle/red-circle.png</href>\r\n"
                        + "\t            </Icon>\r\n"
                        + "\t            <width>4</width>\r\n"
                        + "\t        </IconStyle>\r\n"
                        + "\t\t</Style>\n";
        String kmlElments = "";
        for(RecProjectKmlDTO element:kmlRawData){
            kmlElments +=
                      "\t\t<Placemark>\n"
                    + "\t\t\t<name>"+element.getProjectName()+"</name>\n" + "\t\t\t<description>" + element.getOriginalDesign() + "</description>\n"
                    +"            <LookAt>\r\n"
                    + "                <longitude>"+element.getGpsX()+"</longitude>\r\n"
                    + "                <latitude>"+element.getGpsY()+"</latitude>\r\n"
                    + "                <styleUrl>#default</styleUrl>\r\n"
                    + "                <altitude>0</altitude>\r\n"
                    + "                <heading>-0.3279814145170951</heading>\r\n"
                    + "                <tilt>0</tilt>\r\n"
                    + "                <range>25827.95372167005</range>\r\n"
                    + "            </LookAt>\r\n"
                    + "            <Point>\r\n"
                    + "                <coordinates>"+element.getGpsX()+","+element.getGpsY()+"</coordinates>\r\n"
                    + "                <gx:drawOrder>1</gx:drawOrder>\r\n"
                    + "            </Point>\r\n"
                    + "\t\t</Placemark>\n";
        }
        String kmlend = "\t</Document>\n</kml>";
        String kmlConcat = kmlstart+kmlConfig+kmlElments+kmlend;

        Writer fw = new FileWriter(downloadFilePath + "/" + fileName + ".kml");;
        fw.write(kmlConcat);
        fw.flush();
        fw.close();
    }

    @Override
    public Map<String,List<RecProjectChartDTO>> findTyphoonCountAndAmount(RecProjectQueryCriteria criteria) {
        Map<String,List<RecProjectChartDTO>> chartDataMap = new TreeMap<>();
        String[] typeEnum = {"count","amount"};
        for(int i = 0 ;i<typeEnum.length;i++){
            Query query = Query
                    .builder()
                    .append("SELECT ")
                    .appendWhen(typeEnum[i].equals("count"),"x.TYPHOON_NAME AS ITEM, COUNT(1) AS CNT ")
                    .appendWhen(typeEnum[i].equals("amount"),"x.TYPHOON_NAME AS ITEM, SUM(x.REVIEW_AMOUNT) AS AMT ")
                    .append("FROM (SELECT t.TYPHOON_NAME,p.REVIEW_AMOUNT")
                    .append("FROM REC_PROJECT p")
                    .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                    .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                    .append("join REC_BID b on b.BID_ID = r.BID_ID")
                    .append("left join ADM_AGENT o on o.ORG_ID = p.APPLY_ORG_ID")
                    .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                    .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                    .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                    .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                    .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                    .append("where p.PROJECT_STATUS = -1")
                    .appendWhen(criteria.getTyphoonName().size()>0,"AND t.TYPHOON_NO in (:typhoonName)",criteria.getTyphoonName())
                    .appendWhen(criteria.getProjectCodes().size()>0,"AND p.PROJECT_CODE in (:projectCodes)",criteria.getProjectCodes())
                    .appendWhen(criteria.getApplyOrgId().size()>0,"AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                    .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                    .appendWhen(criteria.getBidProgressMin() != null,"AND b.BID_PROGRESS >= :bidProgressMin", criteria.getBidProgressMin() )
                    .appendWhen(criteria.getBidProgressMax() != null,"AND b.BID_PROGRESS <= :bidProgressMax", criteria.getBidProgressMax() )
                    .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() +"%")
                    .appendWhen(criteria.getReviewAmountMin() != null,"AND p.REVIEW_AMOUNT >= :reviewAmountMin" ,criteria.getReviewAmountMin())
                    .appendWhen(criteria.getReviewAmountMax() != null,"AND p.REVIEW_AMOUNT <= :reviewAmountMax" ,criteria.getReviewAmountMax())
                    .append(") AS x")
                    .append("group by x.TYPHOON_NAME order by x.TYPHOON_NAME")
                    .build();
            List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);
            chartDataMap.put(typeEnum[i],chartRawData);
        }

        return chartDataMap;
    }

    @Override
    public Map<String, List<RecProjectChartDTO>> findProjectCodeCountAndAmount(RecProjectQueryCriteria criteria) {
        Map<String,List<RecProjectChartDTO>> chartDataMap = new TreeMap<>();
        String[] typeEnum = {"count","amount"};
        for(int i = 0 ;i<typeEnum.length;i++){
            Query query = Query
                    .builder()
                    .append("SELECT ")
                    .appendWhen(typeEnum[i].equals("count"),"x.PROJ_CODE_NAME AS ITEM, COUNT(1) AS CNT ")
                    .appendWhen(typeEnum[i].equals("amount"),"x.PROJ_CODE_NAME AS ITEM, SUM(x.REVIEW_AMOUNT) AS AMT ")
                    .append("FROM (SELECT s1.VALUE as PROJ_CODE_NAME, p.REVIEW_AMOUNT")
                    .append("FROM REC_PROJECT p")
                    .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                    .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                    .append("join REC_BID b on b.BID_ID = r.BID_ID")
                    .append("left join ADM_AGENT o on o.ORG_ID = p.APPLY_ORG_ID")
                    .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                    .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                    .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                    .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                    .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                    .append("where p.PROJECT_STATUS = -1")
                    .appendWhen(criteria.getTyphoonName().size()>0,"AND t.TYPHOON_NO in (:typhoonName)",criteria.getTyphoonName())
                    .appendWhen(criteria.getProjectCodes().size()>0,"AND p.PROJECT_CODE in (:projectCodes)",criteria.getProjectCodes())
                    .appendWhen(criteria.getApplyOrgId().size()>0,"AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                    .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                    .appendWhen(criteria.getBidProgressMin() != null,"AND b.BID_PROGRESS >= :bidProgressMin", criteria.getBidProgressMin() )
                    .appendWhen(criteria.getBidProgressMax() != null,"AND b.BID_PROGRESS <= :bidProgressMax", criteria.getBidProgressMax() )
                    .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() +"%")
                    .appendWhen(criteria.getReviewAmountMin() != null,"AND p.REVIEW_AMOUNT >= :reviewAmountMin" ,criteria.getReviewAmountMin())
                    .appendWhen(criteria.getReviewAmountMax() != null,"AND p.REVIEW_AMOUNT <= :reviewAmountMax" ,criteria.getReviewAmountMax())
                    .append(") AS x")
                    .append("group by x.PROJ_CODE_NAME order by x.PROJ_CODE_NAME")
                    .build();
            List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);
            chartDataMap.put(typeEnum[i],chartRawData);
        }

        return chartDataMap;
    }

    @Override
    public Map<String, List<RecProjectChartDTO>> findOrgCountAndAmount(RecProjectQueryCriteria criteria) {
        Map<String,List<RecProjectChartDTO>> chartDataMap = new TreeMap<>();
        String[] typeEnum = {"count","amount"};
        for(int i = 0 ;i<typeEnum.length;i++){
            Query query = Query
                    .builder()
                    .append("SELECT ")
                    .appendWhen(typeEnum[i].equals("count"),"x.ORG_NAME AS ITEM, COUNT(1) AS CNT ")
                    .appendWhen(typeEnum[i].equals("amount"),"x.ORG_NAME AS ITEM, SUM(x.REVIEW_AMOUNT) AS AMT ")
                    .append("FROM (SELECT o.ORG_NAME, p.REVIEW_AMOUNT")
                    .append("FROM REC_PROJECT p")
                    .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                    .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                    .append("join REC_BID b on b.BID_ID = r.BID_ID")
                    .append("left join ADM_AGENT o on o.ORG_ID = p.APPLY_ORG_ID")
                    .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                    .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'")
                    .append("and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                    .append("left join ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'")
                    .append("and s2.DATA_TYPE = 'REC_PROJECT_STATUS' and s2.DATA_KEY = p.PROJECT_STATUS")
                    .append("where p.PROJECT_STATUS = -1")
                    .appendWhen(criteria.getTyphoonName().size()>0,"AND t.TYPHOON_NO in (:typhoonName)",criteria.getTyphoonName())
                    .appendWhen(criteria.getProjectCodes().size()>0,"AND p.PROJECT_CODE in (:projectCodes)",criteria.getProjectCodes())
                    .appendWhen(criteria.getApplyOrgId().size()>0,"AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                    .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                    .appendWhen(criteria.getBidProgressMin() != null,"AND b.BID_PROGRESS >= :bidProgressMin", criteria.getBidProgressMin() )
                    .appendWhen(criteria.getBidProgressMax() != null,"AND b.BID_PROGRESS <= :bidProgressMax", criteria.getBidProgressMax() )
                    .appendWhen(StringUtils.isNotBlank(criteria.getProjectName()),"AND p.PROJECT_NAME LIKE :projectName", "%" + criteria.getProjectName().trim() +"%")
                    .appendWhen(criteria.getReviewAmountMin() != null,"AND p.REVIEW_AMOUNT >= :reviewAmountMin" ,criteria.getReviewAmountMin())
                    .appendWhen(criteria.getReviewAmountMax() != null,"AND p.REVIEW_AMOUNT <= :reviewAmountMax" ,criteria.getReviewAmountMax())
                    .append(") AS x")
                    .append("group by x.ORG_NAME order by x.ORG_NAME")
                    .build();
            List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);
            chartDataMap.put(typeEnum[i],chartRawData);
        }

        return chartDataMap;
    }

    @Override
    public Map<String, List<RecProjectChartDTO>> findOrgCountAndAmountByTyphoon(RecProjectQueryCriteria criteria) {
        Map<String,List<RecProjectChartDTO>> chartDataMap = new TreeMap<>();
        String[] typeEnum = {"count","amount"};
        for(int i = 0 ;i<typeEnum.length;i++){
            Query query = Query
                    .builder()
                    .append("SELECT ")
                    .appendWhen(typeEnum[i].equals("count"),"distinct  x.ORG_NAME AS ITEM, COUNT(1) AS CNT ")
                    .appendWhen(typeEnum[i].equals("amount"),"distinct  x.ORG_NAME AS ITEM, SUM(x.REVIEW_AMOUNT) AS AMT ")
                    .append("FROM (select distinct t.TYPHOON_NAME, r1.ORG_NAME, p.PROJECT_NAME, p.REVIEW_AMOUNT")
                    .append("from REC_ORG_ROLE r ")
                    .append("join REC_ORG_ROLE r1 on (r1.ORG_ID = r.ORG_ID or r1.PARENT_ORG_ID = r.ORG_ID)")
                    .append("join REC_PROJECT p on p.APPLY_ORG_ID = r1.ORG_ID")
                    .append("join REC_TYPHOON t on t.TYPHOON_NO = p. TYPHOON_NO")
                    .append("where r.AUTHORITY_LEVEL = 4 and p.PROJECT_STATUS = -1")
                    .appendWhen(StringUtils.isNotBlank(criteria.getTyphoonNameSingle()),"AND t.TYPHOON_NO = :typhoonName",criteria.getTyphoonNameSingle())
                    .append(") AS x")
                    .append("group by x.ORG_NAME order by x.ORG_NAME")
                    .build();
            List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);
            chartDataMap.put(typeEnum[i],chartRawData);
        }
        return chartDataMap;
    }

    @Override
    public Map<String, List<RecProjectChartDTO>> findAllTyphoonCountAndAmount() {
        Map<String,List<RecProjectChartDTO>> chartDataMap = new TreeMap<>();
        String[] typeEnum = {"count","amount"};
        for(int i = 0 ;i<typeEnum.length;i++){
            Query query = Query
                    .builder()
                    .append("SELECT ")
                    .appendWhen(typeEnum[i].equals("count"),"distinct  x.TYPHOON_NAME AS ITEM, COUNT(1) AS CNT ")
                    .appendWhen(typeEnum[i].equals("amount"),"distinct  x.TYPHOON_NAME AS ITEM, SUM(x.REVIEW_AMOUNT) AS AMT ")
                    .append("FROM (select distinct t.TYPHOON_NAME, r1.ORG_NAME, p.PROJECT_NAME, p.REVIEW_AMOUNT")
                    .append("from REC_ORG_ROLE r ")
                    .append("join REC_ORG_ROLE r1 on (r1.ORG_ID = r.ORG_ID or r1.PARENT_ORG_ID = r.ORG_ID)")
                    .append("join REC_PROJECT p on p.APPLY_ORG_ID = r1.ORG_ID")
                    .append("join REC_TYPHOON t on t.TYPHOON_NO = p. TYPHOON_NO")
                    .append("where r.AUTHORITY_LEVEL = 4 and p.PROJECT_STATUS = -1")
                    .append(") AS x")
                    .append("group by x.TYPHOON_NAME order by x.TYPHOON_NAME")
                    .build();
            List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);
            chartDataMap.put(typeEnum[i],chartRawData);
        }
        return chartDataMap;
    }

    @Override
    public Map<String, List<RecProjectChartDTO>> findAllTyphoonCountAndAmountByYear() {
        Map<String,List<RecProjectChartDTO>> chartDataMap = new TreeMap<>();
        String[] typeEnum = {"count","amount"};
        for(int i = 0 ;i<typeEnum.length;i++){
            Query query = Query
                    .builder()
                    .append("SELECT ")
                    .appendWhen(typeEnum[i].equals("count"),"distinct  x.TYPHOON_YEAR AS ITEM, COUNT(1) AS CNT ")
                    .appendWhen(typeEnum[i].equals("amount"),"distinct  x.TYPHOON_YEAR AS ITEM, SUM(x.REVIEW_AMOUNT) AS AMT ")
                    .append("FROM (select distinct t.TYPHOON_NAME, YEAR(t.apply_start_time) AS TYPHOON_YEAR, r1.ORG_NAME, p.PROJECT_NAME, p.REVIEW_AMOUNT")
                    .append("from REC_ORG_ROLE r ")
                    .append("join REC_ORG_ROLE r1 on (r1.ORG_ID = r.ORG_ID or r1.PARENT_ORG_ID = r.ORG_ID)")
                    .append("join REC_PROJECT p on p.APPLY_ORG_ID = r1.ORG_ID")
                    .append("join REC_TYPHOON t on t.TYPHOON_NO = p. TYPHOON_NO")
                    .append("where r.AUTHORITY_LEVEL = 4 and p.PROJECT_STATUS = -1")
                    .append(") AS x")
                    .append("group by x.TYPHOON_YEAR order by x.TYPHOON_YEAR")
                    .build();
            List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);
            chartDataMap.put(typeEnum[i],chartRawData);
        }
        return chartDataMap;
    }

    @Override
    public RecProjectDTO packPkAndLogColumn(RecProjectDTO recProjectDTO) throws TypeVariableNotMatchException {
        String prjPk = serialNumGenerator
                .setModule(ModuleEnum.REC)
                .setVariable(new SerialNumVariableParam(RecGenNumberVariableEnum.PRJ.getVariable()))
                .getGeneratedNumber();
        recProjectDTO.setProjectNo(prjPk);
        return recProjectDTO;
    }

    @Override
    public Map<String, List<RecProjectMultiChartDTO>> findOrgCountAndAmountByYear(RecProjectQueryCriteria criteria) {
        Map<String,List<RecProjectMultiChartDTO>> chartDataMap = new TreeMap<>();
        Query query = Query
                .builder()
                .append("select z1.TYPHOON_YEAR, z2.ORG_NAME AS ITEM, ISNULL (y.cnt,0) AS CNT, ISNULL(y.amt,0) AS AMT")
                .append("from (select distinct year(t1.APPLY_START_TIME) TYPHOON_YEAR from REC_TYPHOON t1) as z1")
                .append("left join ( select distinct r1.ORG_NAME,r1.ORG_ID from REC_ORG_ROLE r join REC_ORG_ROLE r1 on (r1.ORG_ID = r.ORG_ID or r1.PARENT_ORG_ID = r.ORG_ID)")
                .append("join REC_PROJECT p on p.APPLY_ORG_ID = r1.ORG_ID  where r.AUTHORITY_LEVEL = 4 and p.PROJECT_STATUS = -1 ")
                .append(") as z2 on 1 = 1")
                .append("left join ( select distinct x.ORG_NAME,x.TYPHOON_YEAR,count(1) cnt, SUM(x.REVIEW_AMOUNT) amt")
                .append("from ( select distinct t.TYPHOON_NAME,r1.ORG_NAME,year(t.APPLY_START_TIME) typhoon_year,p.PROJECT_NAME,p.REVIEW_AMOUNT from REC_ORG_ROLE r")
                .append("join REC_ORG_ROLE r1 on (r1.ORG_ID = r.ORG_ID or r1.PARENT_ORG_ID = r.ORG_ID)")
                .append("join REC_PROJECT p on p.APPLY_ORG_ID = r1.ORG_ID")
                .append("join REC_TYPHOON t on t.TYPHOON_NO = p. TYPHOON_NO where r.AUTHORITY_LEVEL = 4 and p.PROJECT_STATUS = -1 ) as x")
                .append("group by TYPHOON_NAME, ORG_NAME, TYPHOON_YEAR ) as y ")
                .append("on y.typhoon_year = z1.TYPHOON_YEAR and y.ORG_NAME = z2.ORG_NAME ")
                .appendWhen(criteria.getApplyOrgId().size()>0,"where z2.org_id in (:applyOrgId) ",criteria.getApplyOrgId())
                .append("order by 2,1")
                .build();
        List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);

        //分離年份
        Set<String> yearSet = new TreeSet();
        chartRawData.forEach( (e) -> { yearSet.add(e.getTyphoonYear());} );
        List<RecProjectMultiChartDTO> yearList = new ArrayList<>();
        RecProjectMultiChartDTO dtoFirst = new RecProjectMultiChartDTO();
        dtoFirst.setYear("機關名稱");
        yearList.add(dtoFirst);
        for(String e:yearSet){
            RecProjectMultiChartDTO dto = new RecProjectMultiChartDTO();
            dto.setYear(e);
            yearList.add(dto);
        }

        //分離件數
        Map<String, List<RecProjectChartDTO>> countMap = new TreeMap<>();
        List<RecProjectMultiChartDTO> countList = new ArrayList();
        countMap = chartRawData.stream().collect(Collectors.groupingBy(RecProjectChartDTO::getItem));
        countMap.forEach((k,v)-> {
            RecProjectMultiChartDTO dto = new RecProjectMultiChartDTO();
            dto.setName(k);
            List<String> dtoCountList = new ArrayList();
            v.forEach(e->dtoCountList.add(e.getCnt().toString()));
            dto.setData(dtoCountList);
            countList.add(dto);
        });

        //分離金額
        Map<String, List<RecProjectChartDTO>> amountMap = new TreeMap<>();
        List<RecProjectMultiChartDTO> amountList = new ArrayList();
        countMap = chartRawData.stream().collect(Collectors.groupingBy(RecProjectChartDTO::getItem));
        countMap.forEach((k,v)-> {
            RecProjectMultiChartDTO dto = new RecProjectMultiChartDTO();
            dto.setName(k);
            List<String> dtoCountList = new ArrayList();
            v.forEach(e->dtoCountList.add(e.getAmt().toString()));
            dto.setData(dtoCountList);
            amountList.add(dto);
        });

        //分離map,一個包件數一個包金額，裡面依照縣市分離字串
        chartDataMap.put("yearList",yearList);
        chartDataMap.put("countList",countList);
        chartDataMap.put("amountList",amountList);

        return chartDataMap;
    }


    @Override
    public Map<String, List<RecProjectChartDTO>> findTyphoonCountAndAmountByIndigenous(RecProjectQueryCriteria criteria) {
        Map<String,List<RecProjectChartDTO>> chartDataMap = new TreeMap<>();
            Query query = Query
                    .builder()
                    .append("select distinct x.CITY AS item, ISNULL(count(1),0) AS cnt, ISNULL(SUM(x.REVIEW_AMOUNT),0) AS amt ")
                    .append("from ( select distinct t.TYPHOON_NAME, p.PROJECT_NAME, a.CITY, a.REVIEW_AMOUNT from REC_PROJECT p")
                    .append("join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO where p.PROJECT_STATUS = -1")
                    .appendWhen(criteria.getTyphoonName().size()>0,"AND p.TYPHOON_NO in (:typhoonName)",criteria.getTyphoonName())
                    .append("and a.CITY in ( select VALUE from ADM_SYS_CODE where MODULE_TYPE = 'REC' and DATA_TYPE = 'REC_INDIGEN_CITY') ) as x")
                    .append("group by x.CITY order by x.CITY")
                    .build();
            List<RecProjectChartDTO> chartRawData = sqlExecutor.queryForList(query,RecProjectChartDTO.class);
            chartDataMap.put("countAndAmount",chartRawData);
        return chartDataMap;
    }

    @Override
    public Page<RecProjectQueryDTO> findAllExpiredByCriteria(RecProjectQueryCriteria criteria, boolean isFinish) {
        Map<String,Query> queryMap = new HashMap<>();
        String[] typeEnum = {"countQuery","query"};
        for(String s:typeEnum){
            Query query = Query
                    .builder()
                    .appendWhen(s.equals("countQuery"),"select count(*) ")
                    .appendWhen(s.equals("query"),"SELECT ROW_NUMBER() OVER(ORDER BY "+criteria.getOrderBy()+") as row_num,")
                    .appendWhen(s.equals("query"),"t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE, s1.VALUE as PROJ_CODE_NAME, o.ORG_NAME, p.PROJECT_NAME, ")
                    .appendWhen(s.equals("query"),"a.CITY, a.VILLAGE, p.REVIEW_AMOUNT, b.BID_PROGRESS, b.SCHEDULED_FINISH_DATE, b.BID_DATE, b.ACTUAL_FINISH_DATE ")
                    .append("from REC_PROJECT p join REC_TYPHOON t on t.TYPHOON_NO = p. TYPHOON_NO")
                    .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                    .append("join REC_BID b on b.BID_ID = r.BID_ID")
                    .append("left join REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID and o.AUTHORITY_LEVEL = 6 ")
                    .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                    .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC' and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                    .append("where p.PROJECT_STATUS  = -1 ")
                    //逾期未完工案件
                    .appendWhen(criteria.getExpiredDate() != null && isFinish == false,"and b.SCHEDULED_FINISH_DATE is not null AND b.SCHEDULED_FINISH_DATE is not null and b.SCHEDULED_FINISH_DATE <= :expiredDate",convertInstantToDate(criteria.getExpiredDate()))
                    .appendWhen(criteria.getExpiredDate() != null && isFinish == false, "and ( b.ACTUAL_FINISH_DATE is null or b.ACTUAL_FINISH_DATE > :expiredDate )",convertInstantToDate(criteria.getExpiredDate()))
                    //逾期已完工案件
                    .appendWhen(criteria.getExpiredDate() != null && isFinish == true,"and b.SCHEDULED_FINISH_DATE is not null and b.ACTUAL_FINISH_DATE is not null")
                    .appendWhen(criteria.getExpiredDate() != null && isFinish == true, "and b.SCHEDULED_FINISH_DATE <= b.ACTUAL_FINISH_DATE and b.ACTUAL_FINISH_DATE < :expiredDate ",convertInstantToDate(criteria.getExpiredDate()))
                    .appendWhen(criteria.getTyphoonName().size()>0,"AND p.TYPHOON_NO IN (:typhoonName)",criteria.getTyphoonName())
                    .appendWhen(criteria.getProjectCodes().size()>0, "AND p.PROJECT_CODE in (:projectCode)",criteria.getProjectCodes())
                    .appendWhen(criteria.getApplyOrgId().size()>0, "AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                    .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                    .appendWhen(s.equals("query"),"ORDER BY " + criteria.getOrderBy())
                    .appendWhen(s.equals("query"),"OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                    .build();
            queryMap.put(s,query);
        }

        int totalCount = sqlExecutor.queryForList(queryMap.get("countQuery"),Integer.class).get(0);
        List<RecProjectQueryDTO> recProjectQueryDTOs = sqlExecutor.queryForList(queryMap.get("query"),RecProjectQueryDTO.class);
        Page<RecProjectQueryDTO> resultPage = new PageImpl<>(recProjectQueryDTOs,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public void exportOdsByCriteriaAndExpired(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName, boolean isFinish) throws IOException {
        Query query = Query
                .builder()
                .append("SELECT t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE, s1.VALUE as PROJ_CODE_NAME, o.ORG_NAME, p.PROJECT_NAME, ")
                .append("a.CITY, a.VILLAGE, p.REVIEW_AMOUNT, b.BID_PROGRESS, b.SCHEDULED_FINISH_DATE, b.BID_DATE, b.ACTUAL_FINISH_DATE ")
                .append("from REC_PROJECT p join REC_TYPHOON t on t.TYPHOON_NO = p. TYPHOON_NO")
                .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                .append("join REC_BID b on b.BID_ID = r.BID_ID")
                .append("left join REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID and o.AUTHORITY_LEVEL = 6 ")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC' and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append("where p.PROJECT_STATUS  = -1")
                //逾期未完工案件
                .appendWhen(criteria.getExpiredDate() != null && isFinish == false,"and b.SCHEDULED_FINISH_DATE is not null AND b.SCHEDULED_FINISH_DATE is not null and b.SCHEDULED_FINISH_DATE <= :expiredDate",convertInstantToDate(criteria.getExpiredDate()))
                .appendWhen(criteria.getExpiredDate() != null && isFinish == false, "and ( b.ACTUAL_FINISH_DATE is null or b.ACTUAL_FINISH_DATE > :expiredDate )",convertInstantToDate(criteria.getExpiredDate()))
                //逾期已完工案件
                .appendWhen(criteria.getExpiredDate() != null && isFinish == true,"and b.SCHEDULED_FINISH_DATE is not null and b.ACTUAL_FINISH_DATE is not null")
                .appendWhen(criteria.getExpiredDate() != null && isFinish == true, "and b.SCHEDULED_FINISH_DATE <= b.ACTUAL_FINISH_DATE and b.ACTUAL_FINISH_DATE < :expiredDate ",convertInstantToDate(criteria.getExpiredDate()))
                .appendWhen(criteria.getTyphoonName().size()>0,"AND p.TYPHOON_NO IN (:typhoonName)",criteria.getTyphoonName())
                .appendWhen(criteria.getProjectCodes().size()>0, "AND p.PROJECT_CODE in (:projectCode)",criteria.getProjectCodes())
                .appendWhen(criteria.getApplyOrgId().size()>0, "AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCity()),"AND a.CITY LIKE :city", "%"+criteria.getCity()+"%")
                .append("ORDER BY t.TYPHOON_NO,p.APPROVE_NO,o.ORG_NAME,p.PROJECT_CODE,p.PROJECT_NO")
                .build();
        List<RecProjectQueryDTO> exportRawData = sqlExecutor.queryForList(query, RecProjectQueryDTO.class);
        Map<Integer, List<String>> paramMap = new TreeMap<>();
        //欄位名設置
        List<String> column = Arrays.asList(new String[]{"颱風", "核列序號", "工程代碼", "機關名稱", "復建工程名稱", "鄉(鎮市)",
                "村(里)", "審議經費(千元)", "實際進度%", "完工期限", "發包日期", "完工日期"
        });
        //內容設置
        int rowNum = 1;
        for (RecProjectQueryDTO element : exportRawData) {
            List<String> columnList = new LinkedList<>();
            columnList.add(parseNull(element.getTyphoonName()));
            columnList.add(parseNull(element.getApproveNo()));
            columnList.add(parseNull(element.getProjectCode()));
            columnList.add(parseNull(element.getOrgName()));
            columnList.add(parseNull(element.getProjectName()));
            columnList.add(parseNull(element.getCity()));
            columnList.add(parseNull(element.getVillage()));
            columnList.add(parseNull(element.getReviewAmount().toString()));
            columnList.add(parseNull(element.getBidProgress().toString()));
            columnList.add(parseNull(element.getScheduledFinishDate()));
            columnList.add(parseNull(element.getBidDate()));
            columnList.add(parseNull(element.getActualFinishDate()));
            paramMap.put(rowNum,columnList);
            rowNum++;
        }
        paramMap.put(0,column);
        odsExportService.exportByParam(paramMap,downloadFilePath,fileName);
    }

    @Override
    public Page<RecProjectQueryDTO> findAllResearchByCriteria(RecProjectQueryCriteria criteria) {
        Map<String,Query> queryMap = new HashMap<>();
        String[] typeEnum = {"countQuery","query"};
        for(String s:typeEnum) {
            Query query = Query
                    .builder()
                    .appendWhen(s.equals("countQuery"),"SELECT count(*)")
                    .appendWhen(s.equals("query"),"SELECT ROW_NUMBER() OVER(ORDER BY "+criteria.getOrderBy()+") as row_num,")
                    .appendWhen(s.equals("query")," t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE, s1.VALUE as PROJ_CODE_NAME, o.ORG_NAME, p.PROJECT_NAME,")
                    .appendWhen(s.equals("query")," a.CITY, a.VILLAGE, p.REVIEW_AMOUNT, b.BID_PROGRESS, b.SCHEDULED_FINISH_DATE, b.BID_DATE, b.ACTUAL_FINISH_DATE,")
                    .appendWhen(s.equals("query"),"p.SURVEY_PLAN_DATE1, p.SURVEY_PLAN_DATE2, p.SURVEY_PLAN_DATE3")
                    .append("from REC_PROJECT p join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                    .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                    .append("join REC_BID b on b.BID_ID = r.BID_ID")
                    .append("left join REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                    .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                    .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC' and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                    .append(" where p.PROJECT_STATUS = -1 and  p.IS_SURVEY_PLAN = 1 ")
                    .appendWhen(criteria.getTyphoonName().size()>0,"AND p.TYPHOON_NO IN (:typhoonName)",criteria.getTyphoonName())
                    .appendWhen(criteria.getProjectCodes().size()>0, "AND p.PROJECT_CODE in (:projectCode)",criteria.getProjectCodes())
                    .appendWhen(criteria.getApplyOrgId().size()>0, "AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                    .appendWhen(s.equals("query"),"ORDER BY " + criteria.getOrderBy())
                    .appendWhen(s.equals("query"),"OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                    .build();
            queryMap.put(s,query);
        }
        int totalCount = sqlExecutor.queryForList(queryMap.get("countQuery"),Integer.class).get(0);
        List<RecProjectQueryDTO> recProjectQueryDTOs = sqlExecutor.queryForList(queryMap.get("query"),RecProjectQueryDTO.class);
        Page<RecProjectQueryDTO> resultPage = new PageImpl<>(recProjectQueryDTOs,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public void exportOdsByCriteriaAndResearch(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException {
        Query query = Query
                .builder()
                .append("SELECT t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE, s1.VALUE as PROJ_CODE_NAME, o.ORG_NAME, p.PROJECT_NAME,")
                .append(" a.CITY, a.VILLAGE, p.REVIEW_AMOUNT, b.BID_PROGRESS, b.SCHEDULED_FINISH_DATE, b.BID_DATE, b.ACTUAL_FINISH_DATE,")
                .append("p.SURVEY_PLAN_DATE1, p.SURVEY_PLAN_DATE2, p.SURVEY_PLAN_DATE3")
                .append("from REC_PROJECT p join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                .append("join REC_BID b on b.BID_ID = r.BID_ID")
                .append("left join REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC' and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append(" where p.PROJECT_STATUS = -1 and  p.IS_SURVEY_PLAN = 1 ")
                .appendWhen(criteria.getTyphoonName().size()>0,"AND p.TYPHOON_NO IN (:typhoonName)",criteria.getTyphoonName())
                .appendWhen(criteria.getProjectCodes().size()>0, "AND p.PROJECT_CODE in (:projectCode)",criteria.getProjectCodes())
                .appendWhen(criteria.getApplyOrgId().size()>0, "AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                .append("ORDER BY t.TYPHOON_NO,o.ORG_NAME,p.PROJECT_NO,p.PROJECT_CODE,p.APPROVE_NO")
                .build();
        List<RecProjectQueryDTO> exportRawData = sqlExecutor.queryForList(query, RecProjectQueryDTO.class);
        Map<Integer, List<String>> paramMap = new TreeMap<>();
        //欄位名設置
        List<String> column = Arrays.asList(new String[]{"颱風", "核列序號", "工程代碼", "機關名稱", "復建工程名稱", "鄉(鎮市)",
                "村(里)", "審議經費(千元)", "實際進度%", "完工期限", "發包日期", "完工日期","調查規劃作業預計決標日","調查規劃設計預計完成日","調查規劃作業預計送工程會"
        });
        //內容設置
        int rowNum = 1;
        for (RecProjectQueryDTO element : exportRawData) {
            List<String> columnList = new LinkedList<>();
            columnList.add(parseNull(element.getTyphoonName()));
            columnList.add(parseNull(element.getApproveNo()));
            columnList.add(parseNull(element.getProjectCode()));
            columnList.add(parseNull(element.getOrgName()));
            columnList.add(parseNull(element.getProjectName()));
            columnList.add(parseNull(element.getCity()));
            columnList.add(parseNull(element.getVillage()));
            columnList.add(parseNull(element.getReviewAmount().toString()));
            columnList.add(parseNull(element.getBidProgress().toString()));
            columnList.add(parseNull(element.getScheduledFinishDate()));
            columnList.add(parseNull(element.getBidDate()));
            columnList.add(parseNull(element.getActualFinishDate()));
            columnList.add(parseNull(element.getSurveyPlanDate1()));
            columnList.add(parseNull(element.getSurveyPlanDate2()));
            columnList.add(parseNull(element.getSurveyPlanDate3()));
            paramMap.put(rowNum,columnList);
            rowNum++;
        }
        paramMap.put(0,column);
        odsExportService.exportByParam(paramMap,downloadFilePath,fileName);
    }

    @Override
    public Page<RecProjectQueryDTO> findAllProjectByCriteria(RecProjectQueryCriteria criteria) {
        Map<String,Query> queryMap = new HashMap<>();
        String[] typeEnum = {"countQuery","query"};
        for(String s:typeEnum) {
            Query query = Query
                    .builder()
                    .appendWhen(s.equals("countQuery"),"SELECT count(*)")
                    .appendWhen(s.equals("query"),"SELECT ROW_NUMBER() OVER(ORDER BY "+criteria.getOrderBy()+") as row_num,")
                    .appendWhen(s.equals("query")," t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE, s1.VALUE as PROJ_CODE_NAME, o.ORG_NAME, p.PROJECT_NAME,")
                    .appendWhen(s.equals("query")," a.CITY, a.VILLAGE, p.REVIEW_AMOUNT, b.BID_PROGRESS, b.SCHEDULED_FINISH_DATE, b.BID_DATE, b.ACTUAL_FINISH_DATE")
                    .append("from REC_PROJECT p join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                    .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                    .append("join REC_BID b on b.BID_ID = r.BID_ID")
                    .append("left join REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                    .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                    .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC' and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                    .append(" where p.PROJECT_STATUS = -1 and  p.IS_SURVEY_PLAN = 1 ")
                    .appendWhen(criteria.getTyphoonName().size()>0,"AND p.TYPHOON_NO IN (:typhoonName)",criteria.getTyphoonName())
                    .appendWhen(criteria.getProjectCodes().size()>0, "AND p.PROJECT_CODE in (:projectCode)",criteria.getProjectCodes())
                    .appendWhen(criteria.getApplyOrgId().size()>0, "AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                    .appendWhen(s.equals("query"),"ORDER BY " + criteria.getOrderBy())
                    .appendWhen(s.equals("query"),"OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                    .build();
            queryMap.put(s,query);
        }
        int totalCount = sqlExecutor.queryForList(queryMap.get("countQuery"),Integer.class).get(0);
        List<RecProjectQueryDTO> recProjectQueryDTOs = sqlExecutor.queryForList(queryMap.get("query"),RecProjectQueryDTO.class);
        Page<RecProjectQueryDTO> resultPage = new PageImpl<>(recProjectQueryDTOs,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public void exportProjectKmlByCriteria(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException {
        //取得原始資料
        Query query = Query
                .builder()
                .append("SELECT prj_result.PROJECT_NAME ,ar.ORIGINAL_DESIGN,ar.GPS_X,ar.GPS_Y FROM(")
                .append(" SELECT t.TYPHOON_NAME, p.APPROVE_NO, p.PROJECT_CODE, s1.VALUE as PROJ_CODE_NAME, o.ORG_NAME, p.PROJECT_NO, p.PROJECT_NAME,")
                .append(" a.CITY, a.VILLAGE, p.REVIEW_AMOUNT, b.BID_PROGRESS, b.SCHEDULED_FINISH_DATE, b.BID_DATE, b.ACTUAL_FINISH_DATE")
                .append("from REC_PROJECT p join REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("join REC_BID_PROJECT_RELA r on r.PROJECT_NO = p.PROJECT_NO")
                .append("join REC_BID b on b.BID_ID = r.BID_ID")
                .append("left join REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("left join REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("left join ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC' and s1.DATA_TYPE = 'REC_PROJECT_CODE' and s1.DATA_KEY = p.PROJECT_CODE")
                .append(" where p.PROJECT_STATUS = -1 and  p.IS_SURVEY_PLAN = 1 ")
                .appendWhen(criteria.getTyphoonName().size()>0,"AND p.TYPHOON_NO IN (:typhoonName)",criteria.getTyphoonName())
                .appendWhen(criteria.getProjectCodes().size()>0, "AND p.PROJECT_CODE in (:projectCode)",criteria.getProjectCodes())
                .appendWhen(criteria.getApplyOrgId().size()>0, "AND p.APPLY_ORG_ID IN ( SELECT ORG_ID FROM REC_ORG_ROLE WHERE ORG_ID IN (:applyOrgId) OR PARENT_ORG_ID IN (:applyOrgId))",criteria.getApplyOrgId(),criteria.getApplyOrgId())
                .append(") AS prj_result")
                .append("JOIN  REC_AREA ar ON ar.PROJECT_NO =prj_result.PROJECT_NO")
                .build();
        List<RecProjectKmlDTO> kmlRawData = sqlExecutor.queryForList(query,RecProjectKmlDTO.class);

        //包裝kml
        String kmlstart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
                +"\t<Document>\n";
        String kmlConfig =
                "\t\t<name>AA</name>\n"+
                        "\t    <description>Examples of paths.</description>\n"+
                        "\t    <Style id=\"default\">\r\n"
                        + "\t        <IconStyle>\r\n"
                        + "\t            <scale>1.1</scale>\r\n"
                        + "\t            <Icon>\r\n"
                        + "\t                <href>http://maps.google.com/mapfiles/kml/paddle/red-circle.png</href>\r\n"
                        + "\t            </Icon>\r\n"
                        + "\t            <width>4</width>\r\n"
                        + "\t        </IconStyle>\r\n"
                        + "\t\t</Style>\n";
        String kmlElments = "";
        for(RecProjectKmlDTO element:kmlRawData){
            kmlElments +=
                    "\t\t<Placemark>\n"
                            + "\t\t\t<name>"+element.getProjectName()+"</name>\n" + "\t\t\t<description>" + element.getOriginalDesign() + "</description>\n"
                            +"            <LookAt>\r\n"
                            + "                <longitude>"+element.getGpsX()+"</longitude>\r\n"
                            + "                <latitude>"+element.getGpsY()+"</latitude>\r\n"
                            + "                <styleUrl>#default</styleUrl>\r\n"
                            + "                <altitude>0</altitude>\r\n"
                            + "                <heading>-0.3279814145170951</heading>\r\n"
                            + "                <tilt>0</tilt>\r\n"
                            + "                <range>25827.95372167005</range>\r\n"
                            + "            </LookAt>\r\n"
                            + "            <Point>\r\n"
                            + "                <coordinates>"+element.getGpsX()+","+element.getGpsY()+"</coordinates>\r\n"
                            + "                <gx:drawOrder>1</gx:drawOrder>\r\n"
                            + "            </Point>\r\n"
                            + "\t\t</Placemark>\n";
        }
        String kmlend = "\t</Document>\n</kml>";
        String kmlConcat = kmlstart+kmlConfig+kmlElments+kmlend;

        Writer fw = new FileWriter(downloadFilePath + "/" + fileName + ".kml");;
        fw.write(kmlConcat);
        fw.flush();
        fw.close();
    }

    @Override
    public List<RecProjectOptionsDTO> findProjectTyphoon(String orgId) {
        Query query = Query
                .builder()
                .append("SELECT t.TYPHOON_NO, t.TYPHOON_NAME,")
                .append("case when c.ORG_APPLY_END_TIME is not null then c.ORG_APPLY_END_TIME ")
                .append("else t.APPLY_END_TIME end APPLY_END_TIME FROM REC_TYPHOON t")
                .append("LEFT JOIN REC_ORG_DEADLINE c on c.TYPHOON_NO = t.TYPHOON_NO")
                .append("AND c.ORG_ID = (select PARENT_ORG_ID from REC_APPLY_ORG where ORG_ID = :orgId)", orgId)
                .append("ORDER BY t.TYPHOON_NO desc")
                .build();
        return sqlExecutor.queryForList(query, RecProjectOptionsDTO.class);
    }

    @Override
    public List<RecProjectOptionsDTO> findProjectCode(String typhoonNo,String orgId) {
        Query query = Query
                .builder()
                .append("SELECT DISTINCT p.PROJECT_CODE, s1.VALUE AS PROJECT_CODE_NAME FROM REC_PROJECT p")
                .append("JOIN REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("JOIN REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("LEFT JOIN REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("LEFT JOIN ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'  AND s1.DATA_TYPE = 'REC_PROJECT_CODE'")
                .append("AND s1.DATA_KEY = p.PROJECT_CODE")
                .append("LEFT JOIN ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'  AND s2.DATA_TYPE = 'REC_PROJECT_STATUS'")
                .append("AND s2.DATA_KEY = p.PROJECT_STATUS")
                .append("WHERE 1 = 1")
                .append("and (a.AREA_NO in (select min_area_no from (select project_no, min(area_no) min_area_no from rec_area group by project_no) v)")
                .append("     or a.AREA_NO is null)")
                .appendWhen(StringUtils.isNotBlank(typhoonNo),"AND p.TYPHOON_NO = :typhoonNo", typhoonNo)
                .appendWhen(StringUtils.isNotBlank(orgId),"AND p.APPLY_ORG_ID in (select ORG_ID from REC_APPLY_ORG where ORG_ID = :orgId or PARENT_ORG_ID = :orgId)", orgId, orgId)
                .build();
        return sqlExecutor.queryForList(query, RecProjectOptionsDTO.class);
    }

    @Override
    public List<RecProjectOptionsDTO> findProjectOrgRole(String typhoonNo,String orgId) {
        Query query = Query
                .builder()
                .append("SELECT DISTINCT p.APPLY_ORG_ID, o.ORG_NAME FROM REC_PROJECT p")
                .append("JOIN REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("JOIN REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("LEFT JOIN REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("LEFT JOIN ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'  AND s1.DATA_TYPE = 'REC_PROJECT_CODE'")
                .append("AND s1.DATA_KEY = p.PROJECT_CODE")
                .append("LEFT JOIN ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'  AND s2.DATA_TYPE = 'REC_PROJECT_STATUS'")
                .append("AND s2.DATA_KEY = p.PROJECT_STATUS")
                .append("WHERE 1 = 1")
                .append("and (a.AREA_NO in (select min_area_no from (select project_no, min(area_no) min_area_no from rec_area group by project_no) v)")
                .append("     or a.AREA_NO is null)")
                .appendWhen(StringUtils.isNotBlank(typhoonNo),"AND p.TYPHOON_NO = :typhoonNo", typhoonNo)
                .appendWhen(StringUtils.isNotBlank(orgId),"AND p.APPLY_ORG_ID in (select ORG_ID from REC_APPLY_ORG where ORG_ID = :orgId or PARENT_ORG_ID = :orgId)", orgId, orgId)
                .build();
        return sqlExecutor.queryForList(query, RecProjectOptionsDTO.class);
    }

    @Override
    public List<RecProjectOptionsDTO> findProjectCity(String typhoonNo,String orgId) {
        Query query = Query
                .builder()
                .append("SELECT DISTINCT a.CITY FROM REC_PROJECT p")
                .append("JOIN REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("JOIN REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("LEFT JOIN REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("LEFT JOIN ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'  AND s1.DATA_TYPE = 'REC_PROJECT_CODE'")
                .append("AND s1.DATA_KEY = p.PROJECT_CODE")
                .append("LEFT JOIN ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'  AND s2.DATA_TYPE = 'REC_PROJECT_STATUS'")
                .append("AND s2.DATA_KEY = p.PROJECT_STATUS")
                .append("WHERE 1 = 1")
                .append("and (a.AREA_NO in (select min_area_no from (select project_no, min(area_no) min_area_no from rec_area group by project_no) v)")
                .append("     or a.AREA_NO is null)")
                .appendWhen(StringUtils.isNotBlank(typhoonNo),"AND p.TYPHOON_NO = :typhoonNo", typhoonNo)
                .appendWhen(StringUtils.isNotBlank(orgId),"AND p.APPLY_ORG_ID in (select ORG_ID from REC_APPLY_ORG where ORG_ID = :orgId or PARENT_ORG_ID = :orgId)", orgId, orgId)
                .build();
        return sqlExecutor.queryForList(query, RecProjectOptionsDTO.class);
    }

    @Override
    public List<RecProjectOptionsDTO> findProjectStatus(String typhoonNo,String orgId) {
        Query query = Query
                .builder()
                .append("SELECT DISTINCT p.PROJECT_STATUS , s2.VALUE AS PROJECT_STATUS_NAME FROM REC_PROJECT p")
                .append("JOIN REC_TYPHOON t on t.TYPHOON_NO = p.TYPHOON_NO")
                .append("JOIN REC_AREA a on a.PROJECT_NO = p.PROJECT_NO")
                .append("LEFT JOIN REC_ORG_ROLE o on o.ORG_ID = p.APPLY_ORG_ID")
                .append("LEFT JOIN ADM_SYS_CODE s1 on s1.MODULE_TYPE = 'REC'  AND s1.DATA_TYPE = 'REC_PROJECT_CODE'")
                .append("AND s1.DATA_KEY = p.PROJECT_CODE")
                .append("LEFT JOIN ADM_SYS_CODE s2 on s2.MODULE_TYPE = 'REC'  AND s2.DATA_TYPE = 'REC_PROJECT_STATUS'")
                .append("AND s2.DATA_KEY = p.PROJECT_STATUS")
                .append("WHERE 1 = 1")
                .append("and (a.AREA_NO in (select min_area_no from (select project_no, min(area_no) min_area_no from rec_area group by project_no) v)")
                .append("     or a.AREA_NO is null)")
                .appendWhen(StringUtils.isNotBlank(typhoonNo),"AND p.TYPHOON_NO = :typhoonNo", typhoonNo)
                .appendWhen(StringUtils.isNotBlank(orgId),"AND p.APPLY_ORG_ID in (select ORG_ID from REC_APPLY_ORG where ORG_ID = :orgId or PARENT_ORG_ID = :orgId)", orgId, orgId)
                .build();
        return sqlExecutor.queryForList(query, RecProjectOptionsDTO.class);
    }

    @Override
    public List<RecProjectOptionsDTO> findProjectConstItem() {
        Query query = Query
                .builder()
                .append("SELECT CONST_ITEM_NAME FROM REC_CONST_ITEM")
                .append("WHERE 1 = 1")
                .append("ORDER BY SORT_NO")
                .build();
        return sqlExecutor.queryForList(query, RecProjectOptionsDTO.class);
    }

    @Override
    public List<RecProjectOptionsDTO> findProjectConstModel(String constItemName) {
        Query query = Query
                .builder()
                .append("SELECT CONST_MODEL_NAME, CONST_MODEL_SOURCE, CONST_MODEL_PRICE, CONST_MODEL_UNIT")
                .append("FROM REC_CONST_MODEL")
                .append("WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(constItemName),"AND CONST_ITEM_NAME = :constItemName", constItemName)
                .append("ORDER BY CONST_MODEL_NAME")
                .build();
        return sqlExecutor.queryForList(query, RecProjectOptionsDTO.class);
    }

}
