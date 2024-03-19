package gov.pcc.pwc.repository.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmFunctionRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmFunctionDTO;

public class AdmFunctionRepositoryImpl implements AdmFunctionRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmFunctionRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmFunctionDTO> findAllByCriteria(AdmFunctionDTO criteria) {
        Query query = Query
            .builder()
            .append(
                " SELECT * FROM ADM_FUNCTION WHERE 1=1 "
            )
            .appendWhen(StringUtils.isNotBlank(criteria.getFunctionId()), " AND Function_ID LIKE :functionId ", "%" + criteria.getFunctionId() + "%")
            .appendWhen(
                StringUtils.isNotBlank(criteria.getFunctionName()),
                " AND FUNCTION_NAME LIKE :functionName ",
                "%" + criteria.getFunctionName() + "%"
            )
            .appendWhen(StringUtils.isNotBlank(criteria.getFunctionCategory()), " AND FUNCTION_CATEGORY = :functionCategory ", criteria.getFunctionCategory())
            .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND SYS_CATEGORY = :sysCategory ", criteria.getSysCategory())
            .appendWhen(
                StringUtils.isNotBlank(criteria.getStatus()),
                " AND STATUS = :status ",
                criteria.getStatus()
            )
            .append(" ORDER BY SYS_CATEGORY ASC, SORT_NO DESC ")
            .build();
        List<AdmFunctionDTO> result = sqlExecutor.queryForList(query, AdmFunctionDTO.class);

        // TODO 時間轉換...這邊硬+8小時
        if (result.size() > 0) {
            result.forEach(admFunctionDTO -> {
                admFunctionDTO.setUpdateTime(admFunctionDTO.getUpdateTime().plusSeconds(28800));
            });
        }
        return result;
    }

    @Override
    public List<AdmFunctionDTO> findAllFunctionOpts() {
        Query query = Query
            .builder()
            .append(
                " SELECT Function_ID, Function_Name FROM ADM_FUNCTION WHERE status = 'Y' and function_category = '1' Order by sys_category asc, sort_no desc"
            )
            .build();
        return sqlExecutor.queryForList(query, AdmFunctionDTO.class);
    }

    @Override
    public void deleteByFunctionId(String functionId) {
        Query query = Query.builder().append(" DELETE FROM ADM_FUNCTION WHERE Function_ID = :functionId", functionId).build();
        sqlExecutor.delete(query);
    }

    @Override
    public int update(AdmFunctionDTO admFunctionDTO) {
        Query query = Query
            .builder()
            .append(" UPDATE ADM_FUNCTION SET Function_Name = :functionName", admFunctionDTO.getFunctionName())
            .append(" , Function_Descript = :functionDescript", admFunctionDTO.getFunctionDescript())
            .append(" , Function_Path = :functionPath", admFunctionDTO.getFunctionPath())
            .append(" , Function_Category = :functionCategory", admFunctionDTO.getFunctionCategory())
            .append(" , Sys_Category = :sysCategory", admFunctionDTO.getSysCategory())
            .append(" , Sort_No = :sortNo", admFunctionDTO.getSortNo())
            .append(" , Master_Function_Id = :masterFunctionId", admFunctionDTO.getMasterFunctionId())
            .append(" , Status = :status", admFunctionDTO.getStatus())
            .append(" , Update_User = :updateUser", admFunctionDTO.getUpdateUser())
            .append(" , Update_Time = GetDate()")
            .append(" WHERE Function_ID = :functionId", admFunctionDTO.getFunctionId())
            .build();
        return sqlExecutor.update(query);
    }
}
