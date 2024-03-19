package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.TecPageRepositoryCustom;
import gov.pcc.pwc.service.TecPageService;
import gov.pcc.pwc.service.dto.TecPageContentDTO;
import gov.pcc.pwc.service.dto.TecInfoContentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TecPageRepositoryImpl implements TecPageRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public TecPageRepositoryImpl(SqlExecutor sqlExecutor){ this.sqlExecutor = sqlExecutor; }

    private final Logger log = LoggerFactory.getLogger(TecPageService.class);

    @Override
    public List<TecPageContentDTO> findTopQuery() {
        Query query = Query
                .builder()
                //join TEC_CONTENT
                .append("SELECT TOP 3 tp.PAGE_ID,tp.PAGE_START,tp.PAGE_END,tp.PAGE_TITLE,tp.CONTENT_TYPE,tc.CONTENT ")
                .append("FROM TEC_PAGE tp JOIN TEC_CONTENT tc ON tp.PAGE_ID = tc.PAGE_ID ")
                .append("WHERE 1=1")
                .append("AND tp.MENU = '技術資料庫-最新消息'")
                .append("AND tc.ISDELETE  = 0")
                .append("AND tp.PAGE_START <=GETDATE() AND tp.PAGE_END >=GETDATE()")
                .append("ORDER BY tp.PAGE_START DESC")
                .build();
        return sqlExecutor.queryForList(query, TecPageContentDTO.class);
    }

    @Override
    public TecInfoContentDTO findTecInfoQuery() {
        Query query = Query
                .builder()
                .append("SELECT TOP 1 tp.PAGE_TITLE,tc.CONTENT")
                .append("FROM TEC_PAGE tp JOIN TEC_CONTENT tc ON tp.PAGE_ID = tc.PAGE_ID")
                .append("WHERE 1=1")
                .append("AND tp.MENU = '技術資料庫-簡介'")
                .append("AND tp.SUB_MENU = '技術資料庫-簡介-公共工程技術資料庫簡介'")
                .append("AND tc.ISDELETE  = 0")
                .build();
        TecInfoContentDTO tecInfoContentDTO = sqlExecutor.queryForList(query,TecInfoContentDTO.class).get(0);
        return tecInfoContentDTO;
    }

    @Override
    public TecInfoContentDTO findLawRefQuery() {
        Query query = Query
                .builder()
                .append("SELECT TOP 1 tp.PAGE_TITLE ,tc.CONTENT")
                .append("FROM TEC_PAGE tp JOIN TEC_CONTENT tc ON tp.PAGE_ID = tc.PAGE_ID")
                .append("WHERE 1=1")
                .append("AND tp.MENU = '技術資料庫-簡介'")
                .append("AND tp.SUB_MENU = '技術資料庫-簡介-法規參考'")
                .append("AND tc.ISDELETE  = 0")
                .build();
        TecInfoContentDTO tecInfoContentDTO = sqlExecutor.queryForList(query,TecInfoContentDTO.class).get(0);
        return tecInfoContentDTO;
    }

}
