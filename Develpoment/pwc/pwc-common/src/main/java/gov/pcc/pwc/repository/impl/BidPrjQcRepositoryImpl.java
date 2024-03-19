package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.BidPrjQc;
import gov.pcc.pwc.repository.custom.BidPrjQcRepositoryCustom;
import gov.pcc.pwc.service.dto.BidPrjQcDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import liquibase.pro.packaged.Q;

import java.util.List;

public class BidPrjQcRepositoryImpl implements BidPrjQcRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidPrjQcRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidPrjQcDTO> findBidPrjQc(BidPrjQcDTO bidPrjQcDTO) {
        Query query = Query
                .builder()
                .append("Select * FROM BID_PRJ_QC WHERE 1=1")
                .append("AND WKUT = :wkut",bidPrjQcDTO.getWkut())
                .append("AND PRJNO = :prjno",bidPrjQcDTO.getPrjno())
                .build();
        return sqlExecutor.queryForList(query, BidPrjQcDTO.class);
    }
}
