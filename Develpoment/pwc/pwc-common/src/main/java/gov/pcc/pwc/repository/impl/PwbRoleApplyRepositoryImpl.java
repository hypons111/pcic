package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.PwbRoleApplyRepositoryCustom;
import gov.pcc.pwc.service.dto.PwbRoleApplyDTO;

import java.util.List;

public class PwbRoleApplyRepositoryImpl implements PwbRoleApplyRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwbRoleApplyRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwbRoleApplyDTO> findRoleApply(PwbRoleApplyDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT  prm.MODULE_TYPE, pra.ID, pra.USER_ID, pra.APPLY_TIME, pra.APPROVE_TIME, pra.STATUS, pra.UPDATE_USER, pra.UPDATE_TIME, pra.CREATE_USER, pra.CREATE_TIME " )
                .append(" FROM PWB_ROLE_MODULE prm " )
                .append(" LEFT JOIN PWB_ROLE_APPLY pra " )
                .append(" ON pra.APPLY_TIME = (Select Max(b.APPLY_TIME) From PWB_ROLE_APPLY b Where pra.MODULE_TYPE = b.MODULE_TYPE) ")
                .append(" AND pra.STATUS <> '6' AND prm.MODULE_TYPE = pra.MODULE_TYPE ")
                .append(" AND pra.USER_ID = :userId ", criteria.getUserId())
                .append(" WHERE 1=1 AND prm.ROLE_TYPE = 'I' ")
                .append(" ORDER BY prm.MODULE_TYPE ")
                .build();
        return sqlExecutor.queryForList(query, PwbRoleApplyDTO.class);
    }
}
