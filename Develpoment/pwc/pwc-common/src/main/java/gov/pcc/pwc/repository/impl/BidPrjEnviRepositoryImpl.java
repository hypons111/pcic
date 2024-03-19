package gov.pcc.pwc.repository.impl;


import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjEnviRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.dto.BidCcutDTO;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import java.util.ArrayList;
import java.util.List;




public class BidPrjEnviRepositoryImpl implements BidPrjEnviRepositoryCustom {

    private final SqlExecutor sqlExecutor;
    public BidPrjEnviRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidPrjEnviDTO> findByPk(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("select * from BID_PRJ_ENVI bpe ")
                .append("WHERE bpe.WKUT = :wkut",wkut)
                .append("AND bpe.PRJNO = :prjno",prjno)
                .build();

        return sqlExecutor.queryForList(query, BidPrjEnviDTO.class);
    }


    @Override
    public List<BidCcutDTO> findctUnitByPk(BidPrjEnviCriteria criteria) {
////1
        Query query = Query
                .builder()
                .append("SELECT bp.WKUT, bu.NAME FROM BID_PROJECT bp  JOIN BID_UNIT10 bu  ON  bp.WKUT = bu.CODE    ")
                .append("WHERE 1=1 AND bp.WKUT = :wkut",criteria.getWkut())
                .append("AND bp.PRJNO = :prjno",criteria.getPrjno())
                .build();
        List<BidCcutDTO> cCnt1List = sqlExecutor.queryForList(query, BidCcutDTO.class);
//2-1監造承造一起
        Query scutQuery = Query
                .builder()
                .append("Select bpu.CCUT,bpu.SCUT, bu.NAME, bpu.TYPE FROM BID_PROJECT_UNIT bpu JOIN BID_UNIT10 bu ON bpu.CCUT = bu.CODE")
                .append("OR bpu.SCUT = bu.CODE")
                .append("WHERE 1=1 AND bpu.WKUT = :wkut",criteria.getWkut())
                .append("AND bpu.PRJNO = :prjno",criteria.getPrjno())
                .build();
        List<BidCcutDTO> scutList = sqlExecutor.queryForList(scutQuery, BidCcutDTO.class);
//3
        Query query2 = Query
                .builder()
                .append("SELECT bp.PCMUT, bu.NAME FROM BID_PROJECT bp JOIN BID_UNIT10 bu ON bp.PCMUT = bu.CODE ")
                .append("WHERE 1=1 AND bp.WKUT = :wkut",criteria.getWkut())
                .append("AND bp.prjno = :prjno",criteria.getPrjno())
                .build();
        List<BidCcutDTO> cCnt2List = sqlExecutor.queryForList(query2, BidCcutDTO.class);
//4
        Query query3 = Query.builder()
                .append("Select bps.SUB_CCUT,bu.NAME from BID_PRJ_SUBCO bps ")
                .append("JOIN BID_UNIT10 bu on bps.SUB_CCUT = bu.CODE WHERE 1=1 ")
                .append("AND bps.wkut = :wkut",criteria.getWkut())
                .append("AND bps.prjno = :prjno",criteria.getPrjno())
                .append("AND bps.SUBCO_NO LIKE :SUBCO_NO ","s%")
                .append("order by bps.SUBCO_NO")
                .build();
        List<BidCcutDTO> cCnt3List = sqlExecutor.queryForList(query3, BidCcutDTO.class);

        List<BidCcutDTO> list = new ArrayList<>();
        list.addAll(cCnt1List);
        list.addAll (scutList);
        list.addAll(cCnt2List);
        list.addAll(cCnt3List);
        return list;
    }

}
