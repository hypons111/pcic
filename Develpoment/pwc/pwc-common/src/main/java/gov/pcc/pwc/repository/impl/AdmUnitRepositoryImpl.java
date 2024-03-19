package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmUnitRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmUnitDTO;
import java.util.List;

public class AdmUnitRepositoryImpl implements AdmUnitRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmUnitRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmUnitDTO> findAllUnitOpts() {
        Query query = Query.builder().append(" SELECT Unit_ID, Unit_Name FROM ADM_UNIT order by Unit_ID asc").build();
        return sqlExecutor.queryForList(query, AdmUnitDTO.class);
    }
}
