package gov.pcc.pwc.repository.impl;

import java.util.List;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmRoleFunctionRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmFunctionDTO;

public class AdmRoleFunctionRepositoryImpl implements AdmRoleFunctionRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmRoleFunctionRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmFunctionDTO> findByRoleId(String roleId) {
        Query query = Query
            .builder()
            .append(
                "SELECT * FROM ADM_ROLE_FUNCTION rf LEFT JOIN ADM_FUNCTION f ON rf.FUNCTION_ID = f.FUNCTION_ID " +
                        "WHERE rf.ROLE_ID = :roleId" , roleId )
            .build();
        return sqlExecutor.queryForList(query, AdmFunctionDTO.class);
    }

    public static void main(String args[]) {
        String roleFunctionList = "admin01,admin02,";
        String roleFunctionListSplit[] = roleFunctionList.split(",");
        for (String roleFunction : roleFunctionListSplit) {
            System.out.println(roleFunction);
        }
    }
}
