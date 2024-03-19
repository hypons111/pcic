package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.Geo0601RepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;


public class Geo0601RepositoryImpl implements Geo0601RepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public Geo0601RepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public int countMemberCount() {
        Query query = Query
                .builder()
                .append("select count(distinct GEO_MEMBER_ID) ")
                .append("from GEO_MEMBER ")
                .append("where IS_APPROVE='1' ")
                .build();
        return sqlExecutor.queryForList(query, Integer.class).get(0);
    }
    @Override
    public int countCompanyCount() {
        Query query = Query
                .builder()
                .append("select count(GEO_COMPANY_ID) ")
                .append("from GEO_COMPANY ")
                .append("where IS_APPROVE='1' ")
                .build();
        return sqlExecutor.queryForList(query, Integer.class).get(0);
    }
    @Override
    public int countJobCount() {
        Query query = Query
                .builder()
                .append("select count(GEO_JOB_ID) from GEO_JOB ")
                .append("where IS_APPROVE='1' ")
                .append("And getdate() < PUBLISH_DATE_END ")
                .build();
        return sqlExecutor.queryForList(query, Integer.class).get(0);
    }
    @Override
    public int countQaReview() {
        Query query = Query
                .builder()
                .append("select count(GEO_QUESTION_ID) from GEO_QUESTION ")
                .append("where IS_DEL ='N' ")
                .append("and REPLY is null ")
                .append("or REPLY = '' ")
                .build();
        return sqlExecutor.queryForList(query, Integer.class).get(0);
    }
    @Override
    public int countCompanyReview() {
        Query query = Query
                .builder()
                .append("select count(a.USER_ID) ")
                .append("from ADM_ACCOUNT a ")
                .append("left join ADM_CONTACT b on b.USER_ID =a.USER_ID ")
                .append("left join ADM_ACCOUNT_ROLE c on c.USER_ID = a.USER_ID ")
                .append("left join ADM_ROLE d on d.ROLE_ID = c.ROLE_ID  ")
                .append("where a.AUDIT_STATUS ='S' ")
                .append("and a.COMP_IDNO is not null ")
                .append("and a.USER_TYPE='C' ")
                .append("and d.BUSINESS_CATEGORY='GEO' ")
                .build();
        return sqlExecutor.queryForList(query, Integer.class).get(0);
    }
    @Override
    public int countjobReview() {
        Query query = Query
                .builder()
                .append("select count(GEO_JOB_ID) from GEO_JOB ")
                .append("where IS_APPROVE='0' ")
                .append("And getdate() < PUBLISH_DATE_END; ")
                .build();
        return sqlExecutor.queryForList(query, Integer.class).get(0);
    }

}
