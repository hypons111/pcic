package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPlnbasRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPlnbasCriteria;
import gov.pcc.pwc.service.dto.BidPlnbasDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BidPlnbasRepositoryImpl implements BidPlnbasRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidPlnbasRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<BidPlnbasDTO> findAllByCriteria(BidPlnbasCriteria criteria) {
        String isExtend = criteria.getIsExtend();

        String punit0 = "XXX";
        String wkut = criteria.getWkut();
        if (StringUtils.isNotBlank(wkut)) {
            if ("Y".equals(isExtend) && wkut.length() >= 3) {
                punit0 = wkut.substring(0, 3);
            } else if (wkut.length() >= 5) {
                punit0 = wkut.substring(0, 5);
            }
        }
        String punit1 = "XXX";
        String plnCode = criteria.getPlnCode();
        if (StringUtils.isNotBlank(plnCode)) {
            if ("Y".equals(isExtend) && plnCode.length() >= 3) {
                punit1 = plnCode.substring(0, 3);
            } else if (plnCode.length() >= 5) {
                punit1 = plnCode.substring(0, 5);
            }
        }
        String punit2 = "XXX";
        String bos2Code = criteria.getBos2Code();
        if (StringUtils.isNotBlank(bos2Code)) {
            if ("Y".equals(isExtend) && bos2Code.length() >= 3) {
                punit2 = bos2Code.substring(0, 3);
            } else if (bos2Code.length() >= 5) {
                punit2 = bos2Code.substring(0, 5);
            }
        }
        String punit3 = "XXX";
        String bos2bCode = criteria.getBos2bCode();
        if (StringUtils.isNotBlank(bos2bCode)) {
            if ("Y".equals(isExtend) && bos2bCode.length() >= 3) {
                punit3 = bos2bCode.substring(0, 3);
            } else if (bos2bCode.length() >= 5) {
                punit3 = bos2bCode.substring(0, 5);
            }
        }
        String punit4 = "XXX";
        String bos2cCode = criteria.getBos2cCode();
        if (StringUtils.isNotBlank(bos2cCode)) {
            if ("Y".equals(isExtend) && bos2cCode.length() >= 3) {
                punit4 = bos2cCode.substring(0, 3);
            } else if (bos2cCode.length() >= 5) {
                punit4 = bos2cCode.substring(0, 5);
            }
        }
        String punit5 = "XXX";
        String bos2dCode = criteria.getBos2dCode();
        if (StringUtils.isNotBlank(bos2dCode)) {
            if ("Y".equals(isExtend) && bos2dCode.length() >= 3) {
                punit5 = bos2dCode.substring(0, 3);
            } else if (bos2dCode.length() >= 5) {
                punit5 = bos2dCode.substring(0, 5);
            }
        }

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM BID_PLNBAS bp ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getPlnno()), " AND bp.PLNNO LIKE :plnno ", criteria.getPlnno() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND bp.NAME LIKE :name ", "%" + criteria.getName() + "%")
                .append(" AND (bp.PLNCODE LIKE :punit0 ", punit0 + "%")
                .append(" OR bp.PLNCODE LIKE :punit1 ", punit1 + "%")
                .append(" OR bp.PLNCODE LIKE :punit2 ", punit2 + "%")
                .append(" OR bp.PLNCODE LIKE :punit3 ", punit3 + "%")
                .append(" OR bp.PLNCODE LIKE :punit4 ", punit4 + "%")
                .append(" OR bp.PLNCODE LIKE :punit5) ", punit5 + "%")
                // .append(" AND (bp.HM_110 = '5' ")
                // .append(" OR bp.HM_109 = '5' ")
                // .append(" OR bp.HM_108 = '5' ")
                // .append(" OR bp.HM_107 = '5' ")
                // .append(" OR bp.HM_106 = '5' ")
                // .append(" OR bp.HM_105 = '5' ")
                // .append(" OR bp.PLNKINDA='Y') ")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, * ")
                .append(" FROM BID_PLNBAS bp ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getPlnno()), " AND bp.PLNNO LIKE :plnno ", criteria.getPlnno() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND bp.NAME LIKE :name ", "%" + criteria.getName() + "%")
                .append(" AND (bp.PLNCODE LIKE :punit0 ", punit0 + "%")
                .append(" OR bp.PLNCODE LIKE :punit1 ", punit1 + "%")
                .append(" OR bp.PLNCODE LIKE :punit2 ", punit2 + "%")
                .append(" OR bp.PLNCODE LIKE :punit3 ", punit3 + "%")
                .append(" OR bp.PLNCODE LIKE :punit4 ", punit4 + "%")
                .append(" OR bp.PLNCODE LIKE :punit5) ", punit5 + "%")
                // .append(" AND (bp.HM_110 = '5' ")
                // .append(" OR bp.HM_109 = '5' ")
                // .append(" OR bp.HM_108 = '5' ")
                // .append(" OR bp.HM_107 = '5' ")
                // .append(" OR bp.HM_106 = '5' ")
                // .append(" OR bp.HM_105 = '5' ")
                // .append(" OR bp.PLNKINDA='Y') ")
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<BidPlnbasDTO> bidPlnbasDTOs = sqlExecutor.queryForList(query, BidPlnbasDTO.class);
        Page<BidPlnbasDTO> resultPage = new PageImpl<>(bidPlnbasDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }
}
