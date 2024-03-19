package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.PwbUnitApplyRepositoryCustom;
import gov.pcc.pwc.service.criteria.PwbRoleApproveForUnitCriteria;
import gov.pcc.pwc.service.criteria.PwbUnitApplyCriteria;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class PwbUnitApplyRepositoryImpl implements PwbUnitApplyRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwbUnitApplyRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<PwbUnitApplyDTO> findUnitApply(PwbUnitApplyDTO criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT DISTINCT COUNT(*) " )
                .append(" FROM ADM_ACCOUNT aa " )
                .append(" JOIN PWB_ROLE_MODULE prm ON prm.ROLE_TYPE = aa.USER_TYPE " )
                .append(" LEFT JOIN PWB_UNIT_APPLY pua ON pua.UNIT_ID = aa.USER_ID AND pua.STATUS <> '6' AND prm.BUSINESS_TYPE = pua.BUSINESS_TYPE ")
                .append(" AND pua.BUSINESS_TYPE = prm.BUSINESS_TYPE AND pua.APPLY_TIME = (Select Max(b.APPLY_TIME) From PWB_UNIT_APPLY b Where pua.MODULE_TYPE = b.MODULE_TYPE AND pua.BUSINESS_TYPE = b.BUSINESS_TYPE) ")
                .append(" WHERE 1=1 ")
                .append(" AND aa.USER_ID = :unitId ", criteria.getUnitId())
                .append(" AND aa.USER_TYPE IN ('C','A') ")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        PwbUnitApplyCriteria pwbUnitApplyCriteria = new PwbUnitApplyCriteria();
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT prm.MODULE_TYPE, pua.ID, prm.BUSINESS_TYPE, pua.STATUS, pua.APPLY_TIME, pua.APPROVE_TIME, pua.UPDATE_USER, pua.UPDATE_TIME, pua.CREATE_USER, pua.CREATE_TIME, pua.UNIT_ID " )
                .append(" FROM ADM_ACCOUNT aa " )
                .append(" JOIN PWB_ROLE_MODULE prm ON prm.ROLE_TYPE = aa.USER_TYPE " )
                .append(" LEFT JOIN PWB_UNIT_APPLY pua ON pua.UNIT_ID = aa.USER_ID AND pua.STATUS <> '6' AND prm.BUSINESS_TYPE = pua.BUSINESS_TYPE ")
                .append(" AND pua.BUSINESS_TYPE = prm.BUSINESS_TYPE AND pua.APPLY_TIME = (Select Max(b.APPLY_TIME) From PWB_UNIT_APPLY b Where pua.MODULE_TYPE = b.MODULE_TYPE AND pua.BUSINESS_TYPE = b.BUSINESS_TYPE) ")
                .append(" WHERE 1=1 ")
                .append(" AND aa.USER_ID = :unitId ", criteria.getUnitId())
                .append(" AND aa.USER_TYPE IN ('C','A') ")
                .append(" ORDER BY prm.MODULE_TYPE, prm.BUSINESS_TYPE ")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        pwbUnitApplyCriteria.getCurrentPage(),
                        pwbUnitApplyCriteria.getPerPage(),
                        pwbUnitApplyCriteria.getPerPage()
                )
                .build();
        pwbUnitApplyCriteria.setSortBy(new String[] { "id" });
        List<PwbUnitApplyDTO> pwbUnitApplyDTOs = sqlExecutor.queryForList(query, PwbUnitApplyDTO.class);
        Page<PwbUnitApplyDTO> resultPage = new PageImpl<>(pwbUnitApplyDTOs, pwbUnitApplyCriteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public Page<PwbUnitApplyDTO> findByApproveCriteria(PwbRoleApproveForUnitCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(DISTINCT app.ID) ")
                .append(" FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY UNIT_ID, MODULE_TYPE, BUSINESS_TYPE ORDER BY APPLY_TIME DESC) AS SN FROM PWB_UNIT_APPLY) app " )
                .append(" JOIN PWB_ROLE_APPROVE pra ON app.MODULE_TYPE = pra.MODULE_TYPE ")
                .append(" JOIN ADM_ACCOUNT acc ON app.UNIT_ID = acc.USER_ID ")
                .append(" JOIN ADM_CONTACT con ON app.UNIT_ID = con.USER_ID ")
                .append(" WHERE 1=1 ")
                .append(" AND app.SN = '1' ")
                .append(" AND app.STATUS != '6' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getBusinessType()), " AND app.BUSINESS_TYPE = :businessType ", criteria.getBusinessType())
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND app.MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND app.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getUnitId()), " AND app.UNIT_ID LIKE :unitId ","%" + criteria.getUnitId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND con.CH_NAME LIKE :chName ","%" + criteria.getChName() + "%")
                .build();

        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" SELECT DISTINCT app.ID, app.UPDATE_USER, app.UPDATE_TIME, app.CREATE_USER, app.CREATE_TIME, app.UNIT_ID,")
                .append(" app.MODULE_TYPE, app.BUSINESS_TYPE, acc.USER_TYPE, app.STATUS, app.APPLY_TIME, app.APPROVE_TIME, " )
                .append(" (SELECT aa.ORG_NAME FROM ADM_AGENT aa WHERE acc.PCC_ORG_ID = aa.PCC_ORG_ID")
                .append(" UNION ")
                .append(" SELECT ac.NAME FROM ADM_COMPANY ac WHERE acc.COMP_IDNO  = ac.COMP_IDNO)AS CH_NAME ")
                .append(" FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY UNIT_ID, MODULE_TYPE, BUSINESS_TYPE  ORDER BY APPLY_TIME DESC) AS SN FROM PWB_UNIT_APPLY) app " )
                .append(" JOIN PWB_ROLE_APPROVE pra ON app.MODULE_TYPE = pra.MODULE_TYPE ")
                .append(" JOIN ADM_ACCOUNT acc ON app.UNIT_ID = acc.USER_ID ")
                .append(" JOIN ADM_CONTACT con ON app.UNIT_ID = con.USER_ID ")
                .append(" WHERE 1=1 ")
                .append(" AND app.SN = '1' ")
                .append(" AND app.STATUS != '6' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getBusinessType()), " AND app.BUSINESS_TYPE = :businessType ", criteria.getBusinessType())
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND app.MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND app.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getUnitId()), " AND app.UNIT_ID LIKE :unitid ","%" + criteria.getUnitId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND con.CH_NAME LIKE :chName ","%" + criteria.getChName() + "%")
                .append(" ORDER BY app.APPLY_TIME DESC ")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<PwbUnitApplyDTO> pwbUnitDTOs = sqlExecutor.queryForList(query, PwbUnitApplyDTO.class);
        Page<PwbUnitApplyDTO> resultPage = new PageImpl<>(pwbUnitDTOs, criteria.toPageable(), totalCount);
        return resultPage;

    }

    @Override
    public Page<PwbUnitApplyDTO> findApplyList(PwbUnitApplyCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT DISTINCT COUNT(*) " )
                .append(" FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY UNIT_ID, MODULE_TYPE, BUSINESS_TYPE ORDER BY APPLY_TIME DESC) AS SN FROM PWB_UNIT_APPLY) pua ")
                .append(" JOIN ADM_AGENT aa ON pua.UNIT_ID = aa.PCC_ORG_ID " )
                .append(" JOIN ADM_ACCOUNT ac ON pua.UNIT_ID = ac.PCC_ORG_ID ")
                .append(" WHERE 1=1 ")
                .append(" AND pua.SN = '1' ")
                .append(" AND pua.STATUS != '6' ")
                .append(" AND pua.APPROVE_UNIT = :pccOrgId " , criteria.getPccOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND pua.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), " AND aa.ORG_NAME LIKE :orgName ","%" + criteria.getOrgName() + "%")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        PwbUnitApplyCriteria pwbUnitApplyCriteria = new PwbUnitApplyCriteria();
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT pua.ID, pua.UNIT_ID, pua.MODULE_TYPE, pua.BUSINESS_TYPE, pua.APPROVE_UNIT, pua.CREATE_USER, pua.CREATE_TIME, pua.STATUS, pua.APPLY_TIME, pua.APPROVE_TIME, aa.ORG_NAME, ac.USER_TYPE, " )
                .append(" (SELECT VALUE FROM ADM_SYS_CODE sys WHERE MODULE_TYPE = 'ADM' AND DATA_KEY = 'REC') AS MODULE_NAME ")
                .append(" FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY UNIT_ID, MODULE_TYPE, BUSINESS_TYPE ORDER BY APPLY_TIME DESC) AS SN FROM PWB_UNIT_APPLY) pua ")
                .append(" JOIN ADM_AGENT aa ON pua.UNIT_ID = aa.PCC_ORG_ID " )
                .append(" JOIN ADM_ACCOUNT ac ON pua.UNIT_ID = ac.PCC_ORG_ID ")
                .append(" WHERE 1=1 ")
                .append(" AND pua.SN = '1' ")
                .append(" AND pua.STATUS != '6' ")
                .append(" AND pua.APPROVE_UNIT = :pccOrgId " , criteria.getPccOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND pua.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), " AND aa.ORG_NAME LIKE :orgName ","%" + criteria.getOrgName() + "%")
                .append(" ORDER BY pua.APPLY_TIME DESC ")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        pwbUnitApplyCriteria.getCurrentPage(),
                        pwbUnitApplyCriteria.getPerPage(),
                        pwbUnitApplyCriteria.getPerPage()
                )
                .build();
        pwbUnitApplyCriteria.setSortBy(new String[] { "STATUS" });
        List<PwbUnitApplyDTO> pwbUnitApplyDTOs = sqlExecutor.queryForList(query, PwbUnitApplyDTO.class);
        Page<PwbUnitApplyDTO> resultPage = new PageImpl<>(pwbUnitApplyDTOs, pwbUnitApplyCriteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public List<String> getUnitApplyRoleId(String moduleType, String businessType) {
        Query query = Query
                .builder()
                .append(" select ROLE_ID from PWB_ROLE_MODULE ")
                .append(" where ROLE_TYPE = 'A'  ")
                .append(" and MODULE_TYPE = :moduleType ", moduleType )
                .append(" and BUSINESS_TYPE = :businessType ", businessType )
                .build();
        List<String> roleIds = sqlExecutor.queryForList(query, String.class);
        return roleIds;
    }

    @Override
    public void cancelUnitRole(String unitId) {
        Query query = Query
                .builder()
                .append(" DELETE FROM PWB_UNIT_ROLE WHERE UNIT_ID = :unitId ", unitId )
                .build();
        sqlExecutor.delete(query);
    }
}
