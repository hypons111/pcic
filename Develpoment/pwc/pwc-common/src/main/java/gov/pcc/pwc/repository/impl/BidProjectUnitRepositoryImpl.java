package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidProjectUnitRepositoryCustom;
import gov.pcc.pwc.service.dto.BidProjectUnitDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BidProjectUnitRepositoryImpl implements BidProjectUnitRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidProjectUnitRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }
    /**
     * 找尋可歸責之廠商
     * */
    @Override
    public List<BidProjectUnitDTO> findBidCcutNames(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("SELECT bpu.CCUT,bu10.NAME,bpu.TYPE,bpu.SCUT from BID_PROJECT_UNIT bpu ")
                .append("JOIN BID_UNIT10 bu10 ON (bpu.CCUT = bu10.CODE OR bpu.SCUT = bu10.CODE)")
                .append("WHERE 1=1 AND bpu.wkut = :wkut",wkut)
                .append("AND bpu.prjno = :prjno",prjno).build();


        return sqlExecutor.queryForList(query, BidProjectUnitDTO.class);
    }

    /**
     * 找尋罹災人員隸屬單位的方法
     * 分三段:
     * 1. BID_PROJECT_UNIT.CCUT (監造、承攬)
     * 2. BID_PROJECT.PCMUT (PCM單位)
     * 3. BID_PRJ_SUBCO.SUBCO_NO (分包協力)
     * */
    @Override
    public List<BidProjectUnitDTO> findBidCcntNames(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("SELECT bpu.SCUT,bpu.CCUT,bu10.NAME,bpu.TYPE from BID_PROJECT_UNIT bpu ")
                .append("JOIN BID_UNIT10 bu10 ON (bpu.CCUT = bu10.CODE OR bpu.SCUT = bu10.CODE)")
                .append("WHERE 1=1 AND bpu.wkut = :wkut",wkut)
                .append("AND bpu.prjno = :prjno",prjno)
                .build();
        List<BidProjectUnitDTO> ccntList = sqlExecutor.queryForList(query, BidProjectUnitDTO.class);

        Query pcmQuery = Query
                .builder()
                .append("Select bu10.NAME,bp.PCMUT from  BID_PROJECT bp " +
                        "JOIN BID_UNIT10 bu10 on bp.PCMUT = bu10.CODE")
                .append("WHERE bp.wkut = :wkut",wkut)
                .append("AND bp.prjno = :prjno",prjno)
                .build();
        List<BidProjectUnitDTO> pcmutList = sqlExecutor.queryForList(pcmQuery, BidProjectUnitDTO.class);

        Query psccQuery = Query.builder()
                .append("Select bps.SUB_CCUT,bu10.NAME from BID_PRJ_SUBCO bps " +
                        "JOIN BID_UNIT10 bu10 on bps.SUB_CCUT = bu10.CODE WHERE 1=1 ")
                .append("AND bps.wkut = :wkut",wkut)
                .append("AND bps.prjno = :prjno",prjno)
                .append("AND bps.SUBCO_NO LIKE :SUBCO_NO ","s%")
                .append("order by bps.SUBCO_NO")
                .build();
        List<BidProjectUnitDTO> subcoList = sqlExecutor.queryForList(psccQuery, BidProjectUnitDTO.class);

        List<BidProjectUnitDTO> list = new ArrayList<BidProjectUnitDTO>();
        list.addAll(ccntList);
        list.addAll(pcmutList);
        list.addAll(subcoList);
        return list;
    }

    @Override
    public int findMaxSeq(String wkut, String prjno, String type) {
        Query query = Query
                .builder()
                .append(" select MAX(SEQ) from BID_PROJECT_UNIT ")
                .append( " WHERE 1=1")
                .append( " AND WKUT = :wkut ", wkut)
                .append( " AND PRJNO = :prjno ", prjno)
                .append( " AND TYPE = :type ", type)
                .append( " AND ISNUMERIC(SEQ) = '1' ")
                .build();
        String sMaxSeq = sqlExecutor.queryForList(query, String.class).get(0);
        int iMaxSeq = 0;
        if (StringUtils.isNotBlank(sMaxSeq)) {
            iMaxSeq = Integer.parseInt(sMaxSeq);
        }
        return iMaxSeq;
    }
}
