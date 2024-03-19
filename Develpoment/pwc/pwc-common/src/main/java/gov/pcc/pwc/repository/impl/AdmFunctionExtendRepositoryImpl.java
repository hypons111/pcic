package gov.pcc.pwc.repository.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmFunctionExtendRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmFunctionExtendDTO;

public class AdmFunctionExtendRepositoryImpl implements AdmFunctionExtendRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmFunctionExtendRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmFunctionExtendDTO> findAllByCriteria(AdmFunctionExtendDTO criteria) {
        Query query = Query
            .builder()
            .append(" SELECT * FROM ADM_FUNCTION_EXTEND WHERE 1=1 ")
            .appendWhen(
                StringUtils.isNotBlank(criteria.getFunctionExtendId()),
                " AND Function_Extend_Id LIKE :functionExtendId ",
                "%" + criteria.getFunctionExtendId() + "%"
            )
            .appendWhen(
                StringUtils.isNotBlank(criteria.getFunctionExtendName()),
                " AND FUNCTION_EXTEND_NAME LIKE :functionExtendName ",
                "%" + criteria.getFunctionExtendName() + "%"
            )
            .build();
        List<AdmFunctionExtendDTO> result = sqlExecutor.queryForList(query, AdmFunctionExtendDTO.class);
        // TODO 時間轉換...這邊硬+8小時
        if (result.size() > 0) {
            result.forEach(admFunctionExtendDTO -> {
                admFunctionExtendDTO.setUpdateTime(admFunctionExtendDTO.getUpdateTime().plusSeconds(28800));
            });
        }
        return result;
    }

    @Override
    public List<AdmFunctionExtendDTO> findAllFunctionExtendOpts() {
        Query query = Query.builder().append(" SELECT Function_Extend_ID, Function_Extend_Name FROM ADM_FUNCTION_EXTEND ").build();
        return sqlExecutor.queryForList(query, AdmFunctionExtendDTO.class);
    }

    @Override
    public void deleteByFunctionExtendId(String functionExtendId) {
        Query query = Query
            .builder()
            .append(" DELETE FROM ADM_FUNCTION_EXTEND WHERE Function_Extend_Id = :functionExtendId", functionExtendId)
            .build();
        sqlExecutor.delete(query);
    }

    @Override
    public int update(AdmFunctionExtendDTO admFunctionExtendDTO) {
        Query query = Query
            .builder()
            .append(
                " UPDATE ADM_FUNCTION_EXTEND SET Function_Extend_Name = :functionExtendName",
                admFunctionExtendDTO.getFunctionExtendName()
            )
            .append(" , Function_Extend_Descript = :functionExtendDescript", admFunctionExtendDTO.getFunctionExtendDescript())
            .append(" , Update_User = :updateUser", admFunctionExtendDTO.getUpdateUser())
            .append(" , Update_Time = GetDate()")
            .append(" WHERE Function_Extend_ID = :functionExtendId", admFunctionExtendDTO.getFunctionExtendId())
            .build();
        return sqlExecutor.update(query);
    }
}
