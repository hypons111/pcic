package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.PwbRoleModuleRepositoryCustom;
import gov.pcc.pwc.service.criteria.PwbRoleApproveCriteria;
import gov.pcc.pwc.service.criteria.PwbRoleModuleCriteria;
import gov.pcc.pwc.service.dto.PwbRoleApplyDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import gov.pcc.pwc.service.dto.PwbRoleModuleDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


public class PwbRoleModuleRepositoryImpl implements PwbRoleModuleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwbRoleModuleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<String> findAllBusinessType() {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT BUSINESS_TYPE FROM PWB_ROLE_MODULE ")
                .append(" WHERE 1=1 ")
                .append(" AND BUSINESS_TYPE IS NOT NULL AND BUSINESS_TYPE != '' ")
                .build();
        List<String> result = sqlExecutor.queryForList(query, String.class);
        return result;
    }

    @Override
    public List<PwbRoleModuleDTO> findRoleId() {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT ar.ROLE_ID, ar.Role_Name FROM PWB_ROLE_MODULE prm " )
                .append(" JOIN ADM_ROLE ar ON prm.ROLE_ID = ar.Role_Id " )
                .build();
        return sqlExecutor.queryForList(query, PwbRoleModuleDTO.class);
    }

    @Override
    public Page<PwbRoleModuleDTO> findSearchList(PwbRoleModuleCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) ")
                .append(" FROM (SELECT ROLE_TYPE, MODULE_TYPE, BUSINESS_TYPE, APPROVE_LEVEL FROM PWB_ROLE_MODULE " )
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getBusinessType()), " AND BUSINESS_TYPE = :businessType ", criteria.getBusinessType())
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleId()), " AND ROLE_ID = :roleId ", criteria.getRoleId())
                .append(" GROUP BY ROLE_TYPE, MODULE_TYPE, BUSINESS_TYPE, APPROVE_LEVEL) AS PRM ")
                .build();

        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" SELECT ROLE_TYPE, MODULE_TYPE, BUSINESS_TYPE, APPROVE_LEVEL " )
                .append(" FROM PWB_ROLE_MODULE " )
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getBusinessType()), " AND BUSINESS_TYPE = :businessType ", criteria.getBusinessType())
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleId()), " AND ROLE_ID = :roleId ", criteria.getRoleId())
                .append(" GROUP BY ROLE_TYPE, MODULE_TYPE, BUSINESS_TYPE, APPROVE_LEVEL ")
                .append(" ORDER BY ROLE_TYPE ")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        PwbRoleModuleCriteria pwbRoleModuleCriteria = new PwbRoleModuleCriteria();
        pwbRoleModuleCriteria.setSortBy(new String[] { "id" });
        List<PwbRoleModuleDTO> PwbRoleModuleDTOs = sqlExecutor.queryForList(query, PwbRoleModuleDTO.class);
        Page<PwbRoleModuleDTO> resultPage = new PageImpl<>(PwbRoleModuleDTOs, pwbRoleModuleCriteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public List<PwbRoleModuleDTO> findRoleIdByModule(PwbRoleModuleCriteria criteria) {
//        Query query = Query
//                .builder()
//                .append(" SELECT prm.ID, prm.ROLE_TYPE, prm.MODULE_TYPE, prm.ROLE_ID, prm.CREATE_USER, prm.CREATE_TIME ,ar.Role_Name, prm.BUSINESS_TYPE, prm.APPROVE_LEVEL" )
//                .append(" FROM PWB_ROLE_MODULE prm " )
//                .append(" JOIN ADM_ROLE ar ON prm.ROLE_ID = ar.Role_Id ")
//                .append(" WHERE 1=1 ")
//                .appendWhen(StringUtils.isNotBlank(criteria.getRoleType()), " AND prm.ROLE_TYPE = :roleType ", criteria.getRoleType())
//                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND prm.MODULE_TYPE = :moduleType ", criteria.getModuleType())
//                .appendWhen(StringUtils.isNotBlank(criteria.getBusinessType()), " AND prm.BUSINESS_TYPE = :businessType ", criteria.getBusinessType())
//                .append(" ORDER BY prm.ROLE_ID ")
//                .build();
        Query query = Query
                .builder()
                .append(" SELECT ar.ROLE_ID, ar.Role_Name " )
                .append(" FROM PWB_ROLE_MODULE prm " )
                .append(" JOIN ADM_ROLE ar ON prm.ROLE_ID = ar.Role_Id ")
                .append(" WHERE 1=1 ")
                .append(" AND prm.ROLE_TYPE = :roleType ", criteria.getRoleType())
                .append(" AND prm.MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getBusinessType()), " AND prm.BUSINESS_TYPE = :businessType ", criteria.getBusinessType())
                .appendWhen(StringUtils.isNotBlank(criteria.getApproveLevel()), " AND prm.APPROVE_LEVEL = :approveLevel ", criteria.getApproveLevel())
                .append(" ORDER BY prm.ROLE_ID ")
                .build();
        return sqlExecutor.queryForList(query, PwbRoleModuleDTO.class);
    }

    @Override
    public void deleteModule(PwbRoleModuleDTO pwbRoleModuleDTO) {
        Query query = Query
                .builder()
                .append(" DELETE FROM PWB_ROLE_MODULE " )
                .append(" WHERE 1=1 ")
                .append(" AND ROLE_TYPE = :roleType ", pwbRoleModuleDTO.getRoleType())
                .append(" AND MODULE_TYPE = :moduleType ", pwbRoleModuleDTO.getModuleType())
                .appendWhen(StringUtils.isNotBlank(pwbRoleModuleDTO.getBusinessType()), " AND BUSINESS_TYPE = :businessType ", pwbRoleModuleDTO.getBusinessType())
                .build();
        sqlExecutor.delete(query);
    }

    @Override
    public List<PwbRoleModuleDTO> findAllRoleId(PwbRoleModuleCriteria criteria) {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT ar.Role_Id, ar.Role_Name " )
                .append(" FROM PWB_ROLE_MODULE prm " )
                .append(" JOIN ADM_ROLE ar ON prm.MODULE_TYPE = ar.Business_Category ")
                .append(" WHERE 1=1 ")
                .append(" AND prm.MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .append(" ORDER BY ar.Role_Id ")
                .build();
        return sqlExecutor.queryForList(query, PwbRoleModuleDTO.class);
    }
}
