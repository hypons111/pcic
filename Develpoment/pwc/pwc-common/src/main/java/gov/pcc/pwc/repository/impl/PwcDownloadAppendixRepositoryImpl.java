package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.PwcDownloadAppendix;
import gov.pcc.pwc.repository.custom.PwcDownloadAppendixRepositoryCustom;
import gov.pcc.pwc.service.dto.PwcDownloadAppendixDTO;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PwcDownloadAppendixRepositoryImpl implements PwcDownloadAppendixRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcDownloadAppendixRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcDownloadAppendixDTO> findByLink(PwcDownloadDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWC_DOWNLOAD_APPENDIX WHERE 1=1 AND Attributes = '2' ")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getId())), " AND Pro_Download_Id = :Id ", criteria.getId())
        .build();
        return sqlExecutor.queryForList(query, PwcDownloadAppendixDTO.class);
    }

    @Override
    public List<PwcDownloadAppendixDTO> findByAttachment(PwcDownloadDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWC_DOWNLOAD_APPENDIX WHERE 1=1 AND Attributes = '1' ")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getId())), " AND Pro_Download_Id = :Id ", criteria.getId())
                .build();
        return sqlExecutor.queryForList(query, PwcDownloadAppendixDTO.class);
    }

    @Override
    public List<PwcDownloadAppendix> findByCriteriaAll(PwcDownloadDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM PWC_DOWNLOAD_APPENDIX WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getId())), " AND Pro_Download_Id = :Id ", criteria.getId())
                .build();
        return sqlExecutor.queryForList(query, PwcDownloadAppendix.class);
    }

    @Override
    public List<PwcDownloadAppendixDTO> findByInfoId(String id) {
        Query query = Query
                .builder()
                .append(
                        "SELECT * FROM PWC_DOWNLOAD_APPENDIX WHERE 1=1  " +
                                "AND PRO_DOWNLOAD_ID = :Id" , id )
                .build();
        return sqlExecutor.queryForList(query, PwcDownloadAppendixDTO.class);
    }
}
