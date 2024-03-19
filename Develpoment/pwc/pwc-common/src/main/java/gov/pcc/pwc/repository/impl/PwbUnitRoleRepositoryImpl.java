package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.PwbUnitRoleRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmRoleDTO;
import gov.pcc.pwc.service.dto.PwbUnitRoleDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class PwbUnitRoleRepositoryImpl implements PwbUnitRoleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwbUnitRoleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmRoleDTO> findAllRoleOpts(String unitId) {
        Query query = Query
                .builder()
                .append(" SELECT ar.ROLE_ID, ar.Role_Name FROM PWB_UNIT_ROLE pur ")
                .append(" JOIN ADM_ROLE ar ON pur.ROLE_ID = ar.Role_Id  ")
                .append(" WHERE 1=1 ")
                .append(" AND pur.UNIT_ID = :unitId ", unitId)
                .build();
        List<AdmRoleDTO> result = sqlExecutor.queryForList(query, AdmRoleDTO.class);

        return result;
    }

    @Override
    public void deleteUnitRole(String unitId) {
        Query query = Query
                .builder()
                .append(" DELETE FROM PWB_UNIT_ROLE WHERE UNIT_ID = :unitId ", unitId )
                .build();
        sqlExecutor.delete(query);
    }
}
