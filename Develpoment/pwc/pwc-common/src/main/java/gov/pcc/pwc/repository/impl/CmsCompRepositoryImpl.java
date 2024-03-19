package gov.pcc.pwc.repository.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.CmsCompRepositoryCustom;
import gov.pcc.pwc.service.dto.CmsCompDTO;

public class CmsCompRepositoryImpl implements CmsCompRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public CmsCompRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<CmsCompDTO> findAllByCriteria(CmsCompDTO criteria) {
        Query query = Query
            .builder()
            .append(" SELECT * FROM CMS_Comp WHERE 1=1 ")
            .appendWhen(StringUtils.isNotBlank(criteria.getType()), " AND Type = :type ", criteria.getType())
            .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND Name LIKE :name ", "%" + criteria.getName() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getChairman()), " AND chairman LIKE :chairman ", "%" + criteria.getChairman() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getUseStatus()), " AND Use_Status = :useStatus ", criteria.getUseStatus())
            .build();
        return sqlExecutor.queryForList(query, CmsCompDTO.class);
    }
}
