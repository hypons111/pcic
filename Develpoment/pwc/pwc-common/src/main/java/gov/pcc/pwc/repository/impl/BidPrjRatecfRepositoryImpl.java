package gov.pcc.pwc.repository.impl;


import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjRatecfRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjRatecfCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidPrjRatecfDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


public class BidPrjRatecfRepositoryImpl implements BidPrjRatecfRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPrjRatecfRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public BidPrjRatecfDTO findByPk(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("SELECT PTRATE , ATRATE FROM (SELECT*, ROW_NUMBER() OVER (PARTITION BY prjno, wkut ORDER BY (syr+month) DESC) AS SN FROM BID_PRJ_MONTH) bpm ")
                .append("WHERE bpm.WKUT = :wkut", wkut)
                .append("AND bpm.PRJNO = :prjno", prjno)
                .append("AND bpm.SN = '1'")
                .build();

        return sqlExecutor.queryForList(query, BidPrjRatecfDTO.class).get(0);

    }

    @Override
    public Page<BidPrjRatecfDTO> findName(BidPrjRatecfCriteria criteria) {
        Query query = Query
                .builder()
                .append("SELECT  bpr.*, aa.ORG_NAME  from  BID_PRJ_RATECF bpr join  ADM_AGENT aa  on bpr.CHKUNIT  = aa.ORG_ID   " )
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getWkut()), "AND bpr.wkut = :wkut", criteria.getWkut())
                .appendWhen(StringUtils.isNotBlank(criteria.getPrjno()), "AND bpr.prjno = :prjno ", criteria.getPrjno())
                .append("ORDER BY " + criteria.getOrderBy())
                .append(
                        "OFFSET :currentPage * :perPage ROWS FETCH NEXT :perPage ROWS ONLY",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        Query queryTotalRows = Query
                .builder()
                .append("SELECT  count(*)  from  BID_PRJ_RATECF bpr join  ADM_AGENT aa  on bpr.CHKUNIT  = aa.ORG_ID   ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getWkut()), "AND bpr.wkut = :wkut", criteria.getWkut())
                .appendWhen(StringUtils.isNotBlank(criteria.getPrjno()), "AND bpr.prjno = :prjno ", criteria.getPrjno())
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, BidPrjRatecfDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }

    @Override
    public List<AdmAgentDTO> findOrgId(String userId) {
        Query query = Query
                .builder()
                .append("SELECT ADM_AGENT.ORG_NAME,ADM_AGENT.ORG_ID FROM ADM_AGENT WHERE PCC_ORG_ID = (SELECT PCC_ORG_ID FROM dbo.ADM_ACCOUNT ")
                .append("WHERE 1 = 1 ")
                .append("AND ADM_ACCOUNT.PCC_ORG_ID = :userId)", userId)


                .build();

        List<AdmAgentDTO> result = sqlExecutor.queryForList(query, AdmAgentDTO.class);

            return result;



    }


}
