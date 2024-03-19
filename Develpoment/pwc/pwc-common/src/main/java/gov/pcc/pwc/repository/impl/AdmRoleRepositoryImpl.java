package gov.pcc.pwc.repository.impl;

import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmRoleRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmRoleDTO;

public class AdmRoleRepositoryImpl implements AdmRoleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmRoleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmRoleDTO> findAllByCriteria(AdmRoleDTO criteria) {
        Query query = Query
            .builder()
            .append(
                " SELECT r.Role_Id, Role_Name, Role_Descript, Business_Category, r.Update_User, r.Update_Time, Role_Function_List FROM ADM_ROLE r LEFT JOIN " +
                " (SELECT DISTINCT Role_Id, Role_Function_List= ( SELECT cast(Function_Id AS VARCHAR) + ',' from ADM_ROLE_FUNCTION where Role_Id = rf.Role_ID FOR XML PATH('')) " +
                " FROM ADM_ROLE_FUNCTION rf ) fl ON r.Role_Id = fl.Role_Id  WHERE 1=1 "
            )
            .appendWhen(StringUtils.isNotBlank(criteria.getRoleId()), " AND r.ROLE_ID LIKE :roleId ", "%" + criteria.getRoleId()+ "%")
            .appendWhen(
                StringUtils.isNotBlank(criteria.getRoleName()),
                " AND r.ROLE_NAME LIKE :roleName ",
                "%" + criteria.getRoleName() + "%"
            )
            .appendWhen(StringUtils.isNotBlank(criteria.getBusinessCategory()), " AND r.BUSINESS_CATEGORY = :businessCategory ", criteria.getBusinessCategory())
            .build();
        return sqlExecutor.queryForList(query, AdmRoleDTO.class);
    }

    @Override
    public List<AdmRoleDTO> findAllRoleOpts() {
        Query query = Query.builder().append(" SELECT Role_Id, Role_Name FROM ADM_ROLE ORDER BY Business_Category ").build();
        return sqlExecutor.queryForList(query, AdmRoleDTO.class);
    }

    @Override
    public AdmRoleDTO findByRoleId(String roleId) {
        Query query = Query
            .builder()
            .append(
                " SELECT r.Role_Id, Role_Name, Role_Descript, Business_Category, r.Update_User, r.Update_Time, Role_Function_List FROM ADM_ROLE r LEFT JOIN " +
                " (SELECT DISTINCT Role_Id, Role_Function_List= ( SELECT cast(Function_Id AS VARCHAR) + ',' from ADM_ROLE_FUNCTION where Role_Id = rf.Role_ID FOR XML PATH('')) " +
                " FROM ADM_ROLE_FUNCTION rf ) fl ON r.Role_Id = fl.Role_Id  WHERE 1=1 "
            )
            .append(" AND r.ROLE_ID = :roleId ", roleId)
            .build();
        AdmRoleDTO result = sqlExecutor.queryForList(query, AdmRoleDTO.class).get(0);

        // TODO 時間轉換...這邊硬+8小時
        result.setUpdateTime(result.getUpdateTime().plusSeconds(28800));
        return result;
    }
}
