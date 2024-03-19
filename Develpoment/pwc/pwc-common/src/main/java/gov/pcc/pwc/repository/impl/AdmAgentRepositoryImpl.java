package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmAgentRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmAgentCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO;
import liquibase.pro.packaged.L;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AdmAgentRepositoryImpl implements AdmAgentRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmAgentRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmAgentDTO> findAllByCriteria(AdmAgentCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_AGENT WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgId()), " AND ORG_ID LIKE :orgId ","%" + criteria.getOrgId() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getPccOrgId()),
                        " AND PCC_ORG_ID LIKE :pccOrgId ",
                        "%" + criteria.getPccOrgId() + "%" )
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), " AND ORG_NAME LIKE :orgName ", "%" + criteria.getOrgName() + "%")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, *")
                .append(" FROM ADM_AGENT ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgId()), " AND ORG_ID LIKE :orgId ", "%" + criteria.getOrgId() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getPccOrgId()),
                        " AND PCC_ORG_ID LIKE :pccOrgId ",
                        "%" + criteria.getPccOrgId() + "%"
                )
                    .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), " AND ORG_NAME LIKE :orgName ", "%" + criteria.getOrgName() + "%" )
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<AdmAgentDTO> admAgentDTOs = sqlExecutor.queryForList(query, AdmAgentDTO.class);
        Page<AdmAgentDTO> resultPage = new PageImpl<>(admAgentDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public Page<AdmAgentDTO> findNameAndCodeByKeyWord(String keywordName, String keywordId, Pageable pageable) {
        Query query = Query
                .builder()
                .append(" SELECT ORG_ID, PCC_ORG_ID, ORG_NAME " )
                .append("FROM ADM_AGENT WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(keywordName), " AND ORG_NAME LIKE :orgName ", "%" + keywordName + "%" )
                .appendWhen(StringUtils.isNotBlank(keywordId), " AND ORG_ID LIKE :orgId ", "%" + keywordId + "%" )
                .build();
        List<AdmAgentDTO> admAgentDTOs = sqlExecutor.queryForList(query, AdmAgentDTO.class);
        Page<AdmAgentDTO> resultPage = new PageImpl<>(admAgentDTOs, pageable, admAgentDTOs.size());
        return resultPage;
    }

    public List<AdmAgentDTO> findBidExamineUnit(String wkutCode){
        Query query = Query
                .builder()
                .append(" SELECT ORG_ID , ORG_NAME " )
                .append("FROM ADM_AGENT aa WHERE 1=1 ")
                .append("AND aa.ORG_ID like :wkut1",wkutCode.substring(0,1)+"__000000_")
                .append("OR aa.ORG_ID like :wkut3 ",wkutCode.substring(0,3)+"000000_")
                .append("OR aa.ORG_ID like :wkut5 ",wkutCode.substring(0,5)+"0000_")
                .append("OR aa.ORG_ID like :wkut7 ",wkutCode.substring(0,7)+"00_")
                .append("OR aa.ORG_ID like :wkut9 ",wkutCode.substring(0,9)+"_")
                .append("OR aa.ORG_ID like '36000%'")
                .append("OR aa.ORG_ID LIKE '3710_0000%'")
                .append("OR (aa.ORG_ID LIKE '376__0000%' AND SUBSTRING(aa.ORG_ID, 4, 1)  < '7')")
                .append("ORDER BY ORG_ID;")
                .build();
        return sqlExecutor.queryForList(query, AdmAgentDTO.class);
    }
}
