package gov.pcc.pwc.repository.impl;

import java.sql.Timestamp;
import java.util.List;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmAccountRoleRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmAccountRoleDTO;
import org.apache.commons.lang3.StringUtils;

public class AdmAccountRoleRepositoryImpl implements AdmAccountRoleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmAccountRoleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public void saveAdmAccountRoleList(AdmAccountContactDTO admAccountContractDTO) {
        // delete all roles of the UserId
        Query query1 = Query
            .builder()
            .append("DELETE FROM ADM_ACCOUNT_ROLE WHERE USER_ID = :userId", admAccountContractDTO.getUserId())
            .build();
        sqlExecutor.delete(query1);

        // insert all role function
        String admAccountRoleList = admAccountContractDTO.getRoleIdList();
        String admAccountRoleListSplit[] = admAccountRoleList.split(",");
        for (String admAccountRole : admAccountRoleListSplit) {
            Query query2 = Query
                .builder()
                .append(
                    " INSERT INTO ADM_ACCOUNT_ROLE ( USER_ID, ROLE_ID, UPDATE_USER, UPDATE_TIME ) VALUES (:userId, :roleId, :updateUser, :udpateTime)",
                    admAccountContractDTO.getUserId(),
                    admAccountRole,
                    admAccountContractDTO.getUpdateUser(),
                    new Timestamp(System.currentTimeMillis())
                )
                .build();
            sqlExecutor.insert(query2);
        }
    }

    @Override
    public List<String> getRoleId(String moduleType) {
        Query query = Query
                .builder()
                .append(" select ROLE_ID from PWB_ROLE_MODULE ")
                .append(" where ROLE_TYPE = 'I'  ")
                .append(" and MODULE_TYPE = :moduleType ", moduleType )
                .build();
        List<String> roleIds = sqlExecutor.queryForList(query, String.class);
        return roleIds;
    }

    @Override
    public void deleteAccountRole(String userId) {
        Query query = Query
                .builder()
                .append(" DELETE FROM ADM_ACCOUNT_ROLE WHERE USER_ID = :userId ", userId )
                .build();
        sqlExecutor.delete(query);
    }

    @Override
    public List<String> findAccountRoleId(AdmAccountContactDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT ROLE_ID FROM ADM_ACCOUNT_ROLE WHERE 1=1 ")
                .append(" AND User_Id = :loginUserId ", criteria.getLoginUserId())
                .build();
        List<String> result = sqlExecutor.queryForList(query, String.class);
        return result;
    }

    @Override
    public List<String> getUnitRoleId(String moduleType, String businessType, String roleType) {
        Query query = Query
                .builder()
                .append(" select ROLE_ID from PWB_ROLE_MODULE ")
                .append(" where ROLE_TYPE = :roleType ", roleType)
                .append(" and MODULE_TYPE = :moduleType ", moduleType )
                .append(" and BUSINESS_TYPE = :businessType ", businessType )
                .build();
        List<String> roleIds = sqlExecutor.queryForList(query, String.class);
        return roleIds;
    }
}
