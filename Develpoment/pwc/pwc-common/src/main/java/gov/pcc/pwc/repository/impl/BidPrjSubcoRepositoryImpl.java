package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.BidPrjStopwkPrimaryKey;
import gov.pcc.pwc.domain.BidPrjSubcoPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjSubcoRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidProjectUnitCriteria;
import gov.pcc.pwc.service.dto.BidPrjQcDTO;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;

import java.util.ArrayList;
import java.util.List;

public class BidPrjSubcoRepositoryImpl implements BidPrjSubcoRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPrjSubcoRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }


    @Override
    public List<BidPrjSubcoDTO> findBidPrjSubco(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        Query query = Query
                .builder()
                .append("Select * FROM bid_prj_subco WHERE 1=1")
                .append("AND WKUT = :wkut", bidPrjSubcoPrimaryKey.getWkut())
                .append("AND PRJNO = :prjno", bidPrjSubcoPrimaryKey.getPrjno())
                .build();
        return sqlExecutor.queryForList(query, BidPrjSubcoDTO.class);
    }

    @Override
    public List<BidProjectDTO> gatAllProject(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        Query query = Query
                .builder()
                .append("Select * FROM bid_project WHERE 1=1")
                .append("AND WKUT = :wkut", bidPrjSubcoPrimaryKey.getWkut())
                .append("AND PRJNO = :prjno", bidPrjSubcoPrimaryKey.getPrjno())
                .build();
        return sqlExecutor.queryForList(query, BidProjectDTO.class);
    }

    @Override
    public List<BidPrjSubcoDTO> gatAllMet(BidProjectPrimaryKey bidProjectPrimaryKey) {
        Query query = Query
                .builder()
                .append("Select * FROM bid_prj_subco WHERE 1=1")
                .append("AND WKUT = :wkut", bidProjectPrimaryKey.getWkut())
                .append("AND PRJNO = :prjno", bidProjectPrimaryKey.getPrjno())
                .build();
        return sqlExecutor.queryForList(query, BidPrjSubcoDTO.class);
    }


    @Override
    public List<BidProjectDTO> findBidProject(BidProjectDTO bidProjectDTO) {
        Query query = Query
                .builder()
                .append("SELECT WKUT, PRJNO FROM BID_PROJECT WHERE 1=1")
                .append("AND SUBSTRING(:typeCode, 1, 1) <> '9' ", bidProjectDTO.getTypeCode())
                .append("AND ISNULL(:bdgt1, 0) >= 1000", bidProjectDTO.getBdgt1())
                .append("AND WKUT = :wkut", bidProjectDTO.getWkut())
                .append("AND PRJNO = :prjno", bidProjectDTO.getPrjno())
                .build();
        return sqlExecutor.queryForList(query, BidProjectDTO.class);
    }


    /**
     * 1. BID_PROJECT_UNIT.CCUT (監造、承攬)
     * 2. BID_PRJ_SUBCO.SUBCO_NO (分包協力)
     */
    @Override
    public List<BidProjectUnitCriteria> findBidCcntNames(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("SELECT bpu.SCUT,bpu.CCUT,bu10.NAME,bpu.TYPE from BID_PROJECT_UNIT bpu ")
                .append("JOIN BID_UNIT10 bu10 ON bpu.CCUT = bu10.CODE")
                .append("WHERE 1=1 AND bpu.wkut = :wkut", wkut)
                .append("AND bpu.prjno = :prjno", prjno)
                .build();
        List<BidProjectUnitCriteria> ccntList = sqlExecutor.queryForList(query, BidProjectUnitCriteria.class);


        Query psccQuery = Query.builder().append("Select bps.SUB_CCUT,bu10.NAME from BID_PRJ_SUBCO bps " +
                        "JOIN BID_UNIT10 bu10 on bps.SUB_CCUT = bu10.CODE WHERE 1=1 ")
                .append("AND bps.wkut = :wkut", wkut)
                .append("AND bps.prjno = :prjno", prjno)
                .append("AND bps.SUBCO_NO LIKE :SUBCO_NO ", "s%")
                .append("order by bps.SUBCO_NO")
                .build();
        List<BidProjectUnitCriteria> subcoList = sqlExecutor.queryForList(psccQuery, BidProjectUnitCriteria.class);

        List<BidProjectUnitCriteria> list = new ArrayList<BidProjectUnitCriteria>();
        list.addAll(ccntList);
        list.addAll(subcoList);
        return list;
    }

    /**
     * 找bid_project跟bid_project_unit裡面的廠商資訊
     */
    @Override
    public List<BidPrjSubcoDTO> findBidProjectAndProjectUnit(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        Query query = Query
                .builder()
                .append(" select bpu.WKUT,bpu.PRJNO,bpu.CCUT AS subCcut, bpu.crat,ROUND(a.ct_sum * bpu.crat/100,2) as sub_bdgt1 ")
                .append(" FROM bid_project a, bid_project_unit bpu ")
                .append(" WHERE 1=1 AND a.wkut = bpu.wkut ")
                .append(" AND a.prjno = bpu.prjno ")
                .append(" AND bpu.wkut = :wkut ", bidPrjSubcoPrimaryKey.getWkut())
                .append(" AND bpu.prjno = :prjno ", bidPrjSubcoPrimaryKey.getPrjno())
                .append(" AND  bpu.crat IS NOT NULL ")
                .build();
        return sqlExecutor.queryForList(query, BidPrjSubcoDTO.class);
    }

    /**
     * 找bid_project跟bid_project_unit裡面的廠商資訊
     */
    @Override
    public int findBidPrjSubcoCount(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        Query query = Query
                .builder()
                .append("select count(*) from bid_prj_subco prjsubco  WHERE 1=1 AND prjsubco.SUBCO_NO like 'M%'")
                .append("AND WKUT = :wkut", bidPrjSubcoPrimaryKey.getWkut())
                .append("AND PRJNO = :prjno", bidPrjSubcoPrimaryKey.getPrjno())
                .build();

        int totalCount = sqlExecutor.queryForList(query, Integer.class).get(0);

        return totalCount;
    }


    @Override
    public List<BidPrjSubcoDTO> insertBidPrjSubco(BidPrjSubcoDTO bidPrjSubcoDTO) {

        System.out.println("安安安安安安安安安安安安安安安安安安安安");

        Query query = Query
                .builder()
//                .append("Select * FROM bid_prj_subco WHERE 1=1")
//                .append("AND WKUT = :wkut",bidPrjSubcoDTO.getWkut())
//                .append("AND PRJNO = :prjno",bidPrjSubcoDTO.getPrjno())
                .build();
        return sqlExecutor.queryForList(query, BidPrjSubcoDTO.class);
    }
}

