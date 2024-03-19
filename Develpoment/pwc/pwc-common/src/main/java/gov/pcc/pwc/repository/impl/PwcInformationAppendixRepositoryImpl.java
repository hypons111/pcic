package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.PwcInformationAppendix;
import gov.pcc.pwc.repository.custom.PwcInformationAppendixRepositoryCustom;
import gov.pcc.pwc.service.dto.PwcInformationAppendixDTO;
import gov.pcc.pwc.service.dto.PwcInformationDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PwcInformationAppendixRepositoryImpl implements PwcInformationAppendixRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcInformationAppendixRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcInformationAppendixDTO> findByLink(PwcInformationDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWC_INFORMATION_APPENDIX WHERE 1=1 AND Attributes = '2' ")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getId())), " AND Pro_Information_Id = :Id ", criteria.getId())
        .build();
        return sqlExecutor.queryForList(query, PwcInformationAppendixDTO.class);
    }

    @Override
    public List<PwcInformationAppendixDTO> findByAttachment(PwcInformationDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWC_INFORMATION_APPENDIX WHERE 1=1 AND Attributes = '1' ")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getId())), " AND Pro_Information_Id = :Id ", criteria.getId())
                .build();
        return sqlExecutor.queryForList(query, PwcInformationAppendixDTO.class);
    }

    @Override
    public List<PwcInformationAppendix> findByCriteriaAll(PwcInformationDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWC_INFORMATION_APPENDIX WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getId())), " AND Pro_Information_Id = :Id ", criteria.getId())
                .build();
        return sqlExecutor.queryForList(query, PwcInformationAppendix.class);
    }

    @Override
    public List<PwcInformationAppendixDTO> findByInfoId(String id) {
        Query query = Query
                .builder()
                .append(
                        "SELECT * FROM PWC_INFORMATION_APPENDIX WHERE 1=1  " +
                                "AND PRO_INFORMATION_ID = :Id" , id )
                .build();
        return sqlExecutor.queryForList(query, PwcInformationAppendixDTO.class);
    }
}
