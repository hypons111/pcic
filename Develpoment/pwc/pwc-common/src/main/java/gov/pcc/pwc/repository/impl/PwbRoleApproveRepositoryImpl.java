package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.PwbRoleApproveRepositoryCustom;
import gov.pcc.pwc.service.criteria.PwbRoleApproveCriteria;
import gov.pcc.pwc.service.criteria.PwbUnitApplyCriteria;
import gov.pcc.pwc.service.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class PwbRoleApproveRepositoryImpl implements PwbRoleApproveRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwbRoleApproveRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<String> findByUserId(String userId) {
        Query query = Query
                .builder()
                .append(" SELECT MODULE_TYPE FROM PWB_ROLE_APPROVE WHERE 1=1 ")
                .append(" AND ROLE_TYPE = '01' ")
                .append(" AND USER_Id = :userId ", userId)
                .build();
        List<String> result = sqlExecutor.queryForList(query, String.class);
        return result;
    }


    @Override
    public Page<PwbRoleDTO> findByCriteria(PwbRoleApproveCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT DISTINCT COUNT(*) ")
                .append(" FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY USER_ID, MODULE_TYPE ORDER BY APPLY_TIME DESC) AS SN FROM PWB_ROLE_APPLY) app " )
                .append(" JOIN PWB_ROLE_APPROVE pra ON app.MODULE_TYPE = pra.MODULE_TYPE AND pra.ROLE_TYPE = '01' ")
                .append(" JOIN ADM_ACCOUNT acc ON app.USER_ID = acc.USER_ID ")
                .append(" JOIN ADM_CONTACT con ON app.USER_ID = con.USER_ID ")
                .append(" WHERE 1=1 ")
                .append(" AND app.SN = '1' ")
                .append(" AND app.STATUS != '6' ")
                .append(" AND acc.USER_TYPE = 'I' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND app.MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND app.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND app.USER_ID LIKE :userId ","%" + criteria.getUserId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND con.CH_NAME LIKE :chName ","%" + criteria.getChName() + "%")
                .build();

        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" SELECT DISTINCT app.ID, app.UPDATE_USER, app.UPDATE_TIME, app.CREATE_USER, app.CREATE_TIME, acc.USER_TYPE, con.CH_NAME, app.USER_ID, app.MODULE_TYPE, app.STATUS, app.APPLY_TIME, app.APPROVE_TIME " )
                .append(" FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY USER_ID, MODULE_TYPE ORDER BY APPLY_TIME DESC) AS SN FROM PWB_ROLE_APPLY) app " )
                .append(" JOIN PWB_ROLE_APPROVE pra ON app.MODULE_TYPE = pra.MODULE_TYPE AND pra.ROLE_TYPE = '01' ")
                .append(" JOIN ADM_ACCOUNT acc ON app.USER_ID = acc.USER_ID ")
                .append(" JOIN ADM_CONTACT con ON app.USER_ID = con.USER_ID ")
                .append(" WHERE 1=1 ")
                .append(" AND app.SN = '1' ")
                .append(" AND app.STATUS != '6' ")
                .append(" AND acc.USER_TYPE = 'I' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND app.MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND app.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND app.USER_ID LIKE :userId ","%" + criteria.getUserId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND con.CH_NAME LIKE :chName ","%" + criteria.getChName() + "%")
                .append(" ORDER BY app.APPLY_TIME DESC ")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<PwbRoleDTO> pwbRoleDTOs = sqlExecutor.queryForList(query, PwbRoleDTO.class);
        Page<PwbRoleDTO> resultPage = new PageImpl<>(pwbRoleDTOs, criteria.toPageable(), totalCount);
        return resultPage;

    }

    public List<PwbRoleApproveDTO> findUserId(String moduleType) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWB_ROLE_APPROVE WHERE 1=1 ")
                .append(" AND MODULE_TYPE = :moduleType ", moduleType)
                .build();
        return sqlExecutor.queryForList(query, PwbRoleApproveDTO.class);
    }

    public List<PwbRoleApproveDTO> findBusinessUserId(String moduleType, String businessType) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWB_ROLE_APPROVE WHERE 1=1 ")
                .append(" AND MODULE_TYPE = :moduleType ", moduleType)
                .append(" AND BUSINESS_TYPE = :businessType ", businessType)
                .append(" AND ROLE_TYPE = '00' ")
                .build();
        return sqlExecutor.queryForList(query, PwbRoleApproveDTO.class);
    }

    @Override
    public List<String> findByUserIdForAgency(String userId) {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT MODULE_TYPE FROM PWB_ROLE_APPROVE WHERE 1=1 ")
                .append(" AND ROLE_TYPE = '00' ")
                .append(" AND USER_Id = :userId ", userId)
                .build();
        List<String> result = sqlExecutor.queryForList(query, String.class);
        return result;
    }

    @Override
    public Page<PwbRoleDTO> findApproveList(PwbRoleApproveCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) " )
                .append(" FROM PWB_ROLE_APPROVE ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleType()), " AND ROLE_TYPE = :roleType ", criteria.getRoleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND USER_ID = :userId ", criteria.getUserId())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        PwbRoleApproveCriteria pwbRoleApproveCriteria = new PwbRoleApproveCriteria();
        Query query = Query
                .builder()
                .append(" SELECT ID, ROLE_TYPE, MODULE_TYPE, BUSINESS_TYPE, USER_ID, CREATE_USER, CREATE_TIME " )
                .append(" FROM PWB_ROLE_APPROVE ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleType()), " AND ROLE_TYPE = :roleType ", criteria.getRoleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND USER_ID = :userId ", criteria.getUserId())
                .append(" ORDER BY MODULE_TYPE ")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        pwbRoleApproveCriteria.getCurrentPage(),
                        pwbRoleApproveCriteria.getPerPage(),
                        pwbRoleApproveCriteria.getPerPage()
                )
                .build();

        pwbRoleApproveCriteria.setSortBy(new String[] { "id" });
        List<PwbRoleDTO> PwbRoleDTOs = sqlExecutor.queryForList(query, PwbRoleDTO.class);
        Page<PwbRoleDTO> resultPage = new PageImpl<>(PwbRoleDTOs, pwbRoleApproveCriteria.toPageable(), totalCount);

        return resultPage;
    }

    @Override
    public List<String> findApproveModuleType() {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT MODULE_TYPE FROM PWB_ROLE_APPROVE  ")
                .build();
        List<String> result = sqlExecutor.queryForList(query, String.class);
        return result;
    }

    @Override
    public List<PwbRoleDTO> findApproveUserId() {
        Query query = Query
                .builder()
                .append(" SELECT aa.USER_ID, ac.CH_NAME FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_CONTACT ac ON aa.USER_ID = ac.USER_ID ")
                .append(" WHERE 1=1 ")
                .append(" AND aa.PCC_ORG_ID = '3.60' ")
                .build();
        List<PwbRoleDTO> result = sqlExecutor.queryForList(query, PwbRoleDTO.class);
        return result;
    }
}
