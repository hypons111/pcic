package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngMemberRepositoryCustom;
import gov.pcc.pwc.service.dto.EngMemberDTO;

import java.util.List;

public class EngMemberRepositoryImpl implements EngMemberRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public EngMemberRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public EngMemberDTO findByTypeAndIdno(String type, String idno) {
        Query query = Query
                .builder()
                .append("SELECT * FROM [ENG_MEMBER] ")
                .append("WHERE [TYPE] = :type ", type)
                .append("AND IDNO = :idno ", idno)
                .build();
        List<EngMemberDTO> list = sqlExecutor.queryForList(query, EngMemberDTO.class);
        EngMemberDTO engMemberDTO = null;
        if(list.size() > 0) {
            engMemberDTO = list.get(0);
        }
        return engMemberDTO;
    }
}
