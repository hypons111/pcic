package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmFunctionClickRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmFunctionClickCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionClickDTO;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.util.List;

public class AdmFunctionClickRepositoryImpl implements AdmFunctionClickRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmFunctionClickRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmFunctionClickDTO> findAllByCriteria(AdmFunctionClickCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_FUNCTION_CLICK afc JOIN ADM_FUNCTION af ON afc.function_id = af.function_id WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afc.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getFunctionName()),
                        " AND af.FUNCTION_NAME LIKE :functionName ",
                        "%" + criteria.getFunctionName() + "%" )
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionExtendId()), " AND afc.FUNCTION_EXTEND_ID = :functionExtendId ",criteria.getFunctionExtendId())
                .appendWhen( Instant.MIN != criteria.getDateStart(), " AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, *")
                .append(" FROM ADM_FUNCTION_CLICK afc JOIN ADM_FUNCTION af ON afc.function_id = af.function_id JOIN ADM_FUNCTION_EXTEND afe ON afc.function_extend_id = afe.function_extend_id ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afc.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getFunctionName()),
                        " AND af.FUNCTION_NAME LIKE :functionName ",
                        "%" + criteria.getFunctionName() + "%" )
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionExtendId()), " AND afc.FUNCTION_EXTEND_ID = :functionExtendId ",criteria.getFunctionExtendId())
                .appendWhen( Instant.MIN != criteria.getDateStart(), " AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<AdmFunctionClickDTO> admFunctionClickDTOs = sqlExecutor.queryForList(query, AdmFunctionClickDTO.class);
        // TODO 時間轉換...這邊硬+8小時
        if (admFunctionClickDTOs.size() > 0) {
            admFunctionClickDTOs.forEach(admFunctionClickDTO -> {
                admFunctionClickDTO.setCreateTime(admFunctionClickDTO.getCreateTime().plusSeconds(28800));
            });
        }
        Page<AdmFunctionClickDTO> resultPage = new PageImpl<>(admFunctionClickDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public Page<AdmFunctionClickDTO> findAllStatisticsByCriteria(AdmFunctionClickCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" select MAX(row_num) from ( ")
                .append(" select row_number() over(order by afc.FUNCTION_ID ) as row_num, ")
                .append(" afc.FUNCTION_ID, af.FUNCTION_NAME, Use_Count")
                .append(" FROM ADM_FUNCTION_CLICK afc JOIN ADM_FUNCTION af ON afc.function_id = af.function_id JOIN ADM_FUNCTION_EXTEND afe ON afc.function_extend_id = afe.function_extend_id")
                .append(" JOIN (select afc.function_id, COUNT(afc.FUNCTION_ID ) Use_Count from ADM_FUNCTION_CLICK afc JOIN ADM_FUNCTION af ON afc.function_id = af.function_id")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afc.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afc.function_id) xx ON xx.FUNCTION_ID = afc.FUNCTION_ID")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afc.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afc.FUNCTION_ID, af.FUNCTION_NAME, Use_Count) n ")
                .build();
        int totalCount;
        List<Integer> totalCountList = sqlExecutor.queryForList(queryTotalCount, Integer.class);
        if (totalCountList.contains(null)) {
            totalCount = 0;
        } else {
            totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);
        }

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getStatisticsOrderBy() + ") as row_num, ")
                .append(" afc.FUNCTION_ID, af.FUNCTION_NAME, Use_Count")
                .append(" FROM ADM_FUNCTION_CLICK afc JOIN ADM_FUNCTION af ON afc.function_id = af.function_id JOIN ADM_FUNCTION_EXTEND afe ON afc.function_extend_id = afe.function_extend_id")
                .append(" JOIN (select afc.function_id, COUNT(afc.FUNCTION_ID ) Use_Count from ADM_FUNCTION_CLICK afc JOIN ADM_FUNCTION af ON afc.function_id = af.function_id")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afc.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afc.function_id) xx ON xx.FUNCTION_ID = afc.FUNCTION_ID")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afc.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afc.FUNCTION_ID, af.FUNCTION_NAME, Use_Count")
                .append("order by " + criteria.getStatisticsOrderBy() )
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<AdmFunctionClickDTO> admFunctionClickDTOs = sqlExecutor.queryForList(query, AdmFunctionClickDTO.class);
        Page<AdmFunctionClickDTO> resultPage = new PageImpl<>(admFunctionClickDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public List<AdmFunctionClickDTO> findAllStatisticsInfoByCriteria(AdmFunctionClickCriteria criteria) {
        Query query = Query
                .builder()
                .append(" select afc.FUNCTION_EXTEND_ID, afe.FUNCTION_EXTEND_NAME, Use_Count")
                .append(" FROM ADM_FUNCTION_CLICK afc JOIN ADM_FUNCTION_EXTEND afe ON afc.function_extend_id = afe.function_extend_id")
                .append(" JOIN (select afc.function_id, afc.function_extend_id, COUNT(afc.FUNCTION_EXTEND_ID ) Use_Count from ADM_FUNCTION_CLICK afc ")
                .append(" WHERE 1=1 ")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append(" group by afc.function_id, afc.function_extend_id) xx ON xx.FUNCTION_Extend_ID = afc.FUNCTION_Extend_ID")
                .append(" WHERE 1=1 ")
                .append(" AND afc.FUNCTION_ID = :functionId ",criteria.getFunctionId() )
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afc.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afc.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append(" group by afc.FUNCTION_EXTEND_ID, afe.FUNCTION_EXTEND_NAME, Use_Count")
                .append(" order by afc.FUNCTION_EXTEND_ID" )
                .build();

        List<AdmFunctionClickDTO> admFunctionClickDTOs = sqlExecutor.queryForList(query, AdmFunctionClickDTO.class);
        return admFunctionClickDTOs;
    }
}
