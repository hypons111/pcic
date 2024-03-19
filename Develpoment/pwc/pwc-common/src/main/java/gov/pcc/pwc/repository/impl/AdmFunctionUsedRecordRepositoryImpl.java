package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmFunctionUsedRecordRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmFunctionUsedRecordCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.util.List;

public class AdmFunctionUsedRecordRepositoryImpl implements AdmFunctionUsedRecordRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmFunctionUsedRecordRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmFunctionUsedRecordDTO> findAllByCriteria(AdmFunctionUsedRecordCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION af ON afur.function_id = af.function_id " +
                        " JOIN ADM_FUNCTION_EXTEND afe ON afur.function_extend_id = afe.function_extend_id WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afur.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getFunctionName()),
                        " AND af.FUNCTION_NAME LIKE :functionName ",
                        "%" + criteria.getFunctionName() + "%" )
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionExtendId()), " AND afur.FUNCTION_EXTEND_ID = :functionExtendId ",criteria.getFunctionExtendId())
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND afur.SYS_CATEGORY = :sysCategory ", criteria.getSysCategory())
                .appendWhen( Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, ")
                .append(" afur.ID, afur.SYS_CATEGORY, afur.JWT_TOKEN, afur.FUNCTION_ID, af.FUNCTION_NAME, afe.FUNCTION_EXTEND_NAME, afur.CREATE_TIME, afur.CREATE_USER_SYS ")
                .append(" FROM ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION af ON afur.function_id = af.function_id JOIN ADM_FUNCTION_EXTEND afe ON afur.function_extend_id = afe.function_extend_id")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afur.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getFunctionName()),
                        " AND af.FUNCTION_NAME LIKE :functionName ",
                        "%" + criteria.getFunctionName() + "%" )
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionExtendId()), " AND afur.FUNCTION_EXTEND_ID = :functionExtendId ",criteria.getFunctionExtendId())
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND afur.SYS_CATEGORY = :sysCategory ", criteria.getSysCategory())
                .appendWhen( Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<AdmFunctionUsedRecordDTO> admFunctionUsedRecordDTOs = sqlExecutor.queryForList(query, AdmFunctionUsedRecordDTO.class);

        // TODO 時間轉換...這邊硬+8小時
        if (admFunctionUsedRecordDTOs.size() > 0) {
            admFunctionUsedRecordDTOs.forEach(admFunctionUsedRecordDTO -> {
                admFunctionUsedRecordDTO.setCreateTime(admFunctionUsedRecordDTO.getCreateTime().plusSeconds(28800));
            });
        }
        Page<AdmFunctionUsedRecordDTO> resultPage = new PageImpl<>(admFunctionUsedRecordDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public Page<AdmFunctionUsedRecordDTO> findAllStatisticsByCriteria(AdmFunctionUsedRecordCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" select MAX(row_num) from ( ")
                .append(" select row_number() over(order by afur.FUNCTION_ID ) as row_num, ")
                .append(" afur.SYS_CATEGORY, afur.FUNCTION_ID, af.FUNCTION_NAME, Use_Count")
                .append(" FROM ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION af ON afur.function_id = af.function_id JOIN ADM_FUNCTION_EXTEND afe ON afur.function_extend_id = afe.function_extend_id")
                .append(" JOIN (select afur.function_id, COUNT(afur.FUNCTION_ID ) Use_Count from ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION af ON afur.function_id = af.function_id")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afur.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND afur.SYS_CATEGORY LIKE :sysCategory ", "%" + criteria.getSysCategory() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afur.function_id) xx ON xx.FUNCTION_ID = afur.FUNCTION_ID")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afur.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND afur.SYS_CATEGORY LIKE :sysCategory ", "%" + criteria.getSysCategory() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afur.SYS_CATEGORY, afur.FUNCTION_ID, af.FUNCTION_NAME, Use_Count) n ")
                .build();
        int totalCount;
        List<Integer> totalCountList = sqlExecutor.queryForList(queryTotalCount, Integer.class);
        if (totalCountList.contains(null) ) {
            totalCount = 0;
        } else {
            totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);
        }

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getStatisticsOrderBy() + ") as row_num, ")
                .append(" afur.SYS_CATEGORY, afur.FUNCTION_ID, af.FUNCTION_NAME, Use_Count")
                .append(" FROM ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION af ON afur.function_id = af.function_id JOIN ADM_FUNCTION_EXTEND afe ON afur.function_extend_id = afe.function_extend_id")
                .append(" JOIN (select afur.function_id, COUNT(afur.FUNCTION_ID ) Use_Count from ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION af ON afur.function_id = af.function_id")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afur.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND afur.SYS_CATEGORY LIKE :sysCategory ", "%" + criteria.getSysCategory() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afur.function_id) xx ON xx.FUNCTION_ID = afur.FUNCTION_ID")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND afur.FUNCTION_ID LIKE :functionId ","%" + criteria.getFunctionId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getFunctionName()), " AND af.FUNCTION_NAME LIKE :functionName ", "%" + criteria.getFunctionName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND afur.SYS_CATEGORY LIKE :sysCategory ", "%" + criteria.getSysCategory() + "%")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("group by afur.SYS_CATEGORY, afur.FUNCTION_ID, af.FUNCTION_NAME, Use_Count")
                .append("order by " + criteria.getStatisticsOrderBy() )
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<AdmFunctionUsedRecordDTO> admFunctionUsedRecordDTOs = sqlExecutor.queryForList(query, AdmFunctionUsedRecordDTO.class);
        Page<AdmFunctionUsedRecordDTO> resultPage = new PageImpl<>(admFunctionUsedRecordDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public List<AdmFunctionUsedRecordDTO> findAllStatisticsInfoByCriteria(AdmFunctionUsedRecordCriteria criteria) {
        Query query = Query
                .builder()
                .append(" select afur.FUNCTION_EXTEND_ID, afe.FUNCTION_EXTEND_NAME, Use_Count")
                .append(" FROM ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION_EXTEND afe ON afur.function_extend_id = afe.function_extend_id")
                .append(" JOIN (select afur.function_id, afur.function_extend_id, COUNT(afur.FUNCTION_EXTEND_ID ) Use_Count from ADM_FUNCTION_USED_RECORD afur ")
                .append(" WHERE 1=1 ")
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append(" group by afur.function_id, afur.function_extend_id) xx ON xx.FUNCTION_Extend_ID = afur.FUNCTION_Extend_ID")
                .append(" WHERE 1=1 ")
                .append(" AND afur.FUNCTION_ID = :functionId ",criteria.getFunctionId() )
                .appendWhen(Instant.MIN != criteria.getDateStart()," AND afur.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND afur.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append(" group by afur.FUNCTION_EXTEND_ID, afe.FUNCTION_EXTEND_NAME, Use_Count")
                .append(" order by afur.FUNCTION_EXTEND_ID" )
                .build();

        List<AdmFunctionUsedRecordDTO> admFunctionUsedRecordDTOs = sqlExecutor.queryForList(query, AdmFunctionUsedRecordDTO.class);
        return admFunctionUsedRecordDTOs;
    }

    @Override
    public AdmFunctionUsedRecordDTO findByPk(long id) {
        Query query = Query
                .builder()
                .append(" select afur.id, afur.sys_category, afur.jwt_token, afur.function_id, af.function_name, afur.function_extend_id, afur.table_name, afur.table_key, afur.update_value, afur.record_content, afur.create_time, afur.create_user_sys ")
                .append(" FROM ADM_FUNCTION_USED_RECORD afur JOIN ADM_FUNCTION af ON afur.function_id = af.function_id JOIN ADM_FUNCTION_EXTEND afe ON afur.function_extend_id = afe.function_extend_id ")
                .append(" WHERE 1=1 ")
                .append( " AND afur.ID = :id ", id)
                .build();
        List<AdmFunctionUsedRecordDTO> admFunctionUsedRecordDTO = sqlExecutor.queryForList(query, AdmFunctionUsedRecordDTO.class);
        if (admFunctionUsedRecordDTO.size() > 0) {
            // TODO 時間轉換...這邊硬+8小時
            admFunctionUsedRecordDTO.get(0).setCreateTime(admFunctionUsedRecordDTO.get(0).getCreateTime().plusSeconds(28800));
            return admFunctionUsedRecordDTO.get(0);
        }
        return null;
    }
}
