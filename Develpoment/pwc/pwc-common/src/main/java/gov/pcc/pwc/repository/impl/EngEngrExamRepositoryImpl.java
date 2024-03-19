package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngEngrExamRepositoryCustom;
import gov.pcc.pwc.service.dto.EngEngrExamDTO;

import java.util.List;
import java.util.Optional;

public class EngEngrExamRepositoryImpl implements EngEngrExamRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public EngEngrExamRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public EngEngrExamDTO findByIdnoAndSubject(String idno, String subject) {
        Query query = Query
                .builder()
                .append("SELECT * FROM [ENG_ENGR_EXAM] ")
                .append("WHERE [IDNO] = :idno ", idno)
                .append("AND [SUBJECT] = :subject ", subject)
                .build();
        List<EngEngrExamDTO> list = sqlExecutor.queryForList(query, EngEngrExamDTO.class);
        EngEngrExamDTO engEngrExamDTO = null;
        if(list.size() > 0) {
            engEngrExamDTO = list.get(0);
        }
        return engEngrExamDTO;
    }
}
