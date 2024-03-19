package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.repository.custom.AdmMailSendRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AdmMailSendRepositoryImpl implements AdmMailSendRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmMailSendRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmMailSend> findAllByCriteria(AdmMailSend criteria) {
        Query query = Query
            .builder()
            .append(" SELECT *  FROM ADM_MAIL_SEND WHERE 1=1 ")
            .appendWhen(StringUtils.isNotBlank(criteria.getMailType()), " AND MAIL_TYPE = :mailType ", criteria.getMailType())
            .appendWhen(StringUtils.isNotBlank(criteria.getSourceId()), " AND SOURCE_ID = :sourceId ", criteria.getSourceId())
            .appendWhen(StringUtils.isNotBlank(criteria.getSender()), " AND SENDER = :sender ", criteria.getSender())
            .appendWhen(StringUtils.isNotBlank(criteria.getReceiver()), " AND RECEIVER = :receiver ", criteria.getReceiver())
            .append(" AND STATUS = 'W' ")
            .build();

        List<AdmMailSend> admMailSends = sqlExecutor.queryForList(query, AdmMailSend.class);

        return admMailSends;


    }
}
