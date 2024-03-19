package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPlnunitbRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPlnunitbCriteria;
import gov.pcc.pwc.service.dto.BidPlnunitbDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class BidPlnunitbRepositoryImpl implements BidPlnunitbRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidPlnunitbRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidPlnunitbDTO> findAllByCriteria(BidPlnunitbCriteria criteria) {
        String punit0 = "XXX";
        String wkut = criteria.getWkut();
        if (StringUtils.isNotBlank(wkut) && wkut.length() >= 3) {
            punit0 = wkut.substring(0, 3);
        }
        String punit1 = "XXX";
        String plnCode = criteria.getPlnCode();
        if (StringUtils.isNotBlank(plnCode) && plnCode.length() >= 3) {
            punit1 = plnCode.substring(0, 3);
        }
        String punit2 = "XXX";
        String bos2Code = criteria.getBos2Code();
        if (StringUtils.isNotBlank(bos2Code) && bos2Code.length() >= 3) {
            punit2 = bos2Code.substring(0, 3);
        }
        String punit3 = "XXX";
        String bos2bCode = criteria.getBos2bCode();
        if (StringUtils.isNotBlank(bos2bCode) && bos2bCode.length() >= 3) {
            punit3 = bos2bCode.substring(0, 3);
        }
        String punit4 = "XXX";
        String bos2cCode = criteria.getBos2cCode();
        if (StringUtils.isNotBlank(bos2cCode) && bos2cCode.length() >= 3) {
            punit4 = bos2cCode.substring(0, 3);
        }
        String punit5 = "XXX";
        String bos2dCode = criteria.getBos2dCode();
        if (StringUtils.isNotBlank(bos2dCode) && bos2dCode.length() >= 3) {
            punit5 = bos2dCode.substring(0, 3);
        }

        Query query = Query
                .builder()
                .append(" select * ")
                .append(" FROM BID_PLNUNITB bp ")
                .append(" WHERE 1=1 ")
                .append(" AND (bp.UNICODE LIKE :punit0 ", punit0 + "%")
                .append(" OR bp.UNICODE LIKE :punit1 ", punit1 + "%")
                .append(" OR bp.UNICODE LIKE :punit2 ", punit2 + "%")
                .append(" OR bp.UNICODE LIKE :punit3 ", punit3 + "%")
                .append(" OR bp.UNICODE LIKE :punit4 ", punit4 + "%")
                .append(" OR bp.UNICODE LIKE :punit5) ", punit5 + "%")
                .append("order by " + criteria.getOrderBy())
                .build();

        List<BidPlnunitbDTO> bidPlnunitbs = sqlExecutor.queryForList(query, BidPlnunitbDTO.class);
        return bidPlnunitbs;
    }
}
