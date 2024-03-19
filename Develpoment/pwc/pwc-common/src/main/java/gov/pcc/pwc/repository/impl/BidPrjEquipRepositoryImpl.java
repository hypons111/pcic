package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjEquipRepositoryCustom;
import gov.pcc.pwc.repository.custom.BidPrjFalRepositoryCustom;
import gov.pcc.pwc.service.dto.BidPrjEquipDTO;
import gov.pcc.pwc.service.dto.BidPrjFalDTO;

import java.util.List;

public class BidPrjEquipRepositoryImpl implements BidPrjEquipRepositoryCustom {

    private final SqlExecutor sqlExecutor;
    public BidPrjEquipRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidPrjEquipDTO> findPrjMonthAllEquip(String wkut, String prjno, String year, String month) {
        Query query = Query
                .builder()
                .append("select * from BID_PRJ_EQUIP bpe ")
                .append("WHERE bpe.WKUT = :wkut",wkut)
                .append("AND bpe.PRJNO = :prjno",prjno)
                .append("AND bpe.YEAR = :year",year)
                .append("AND bpe.MONTH = :month",month)
                .append("order by bpe.E_CODE")
                .build();

        return sqlExecutor.queryForList(query, BidPrjEquipDTO.class);
    }
}
