package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.RecAnalysisRepositoryCustom;
import gov.pcc.pwc.service.RecAnalysisService;
import gov.pcc.pwc.service.criteria.RecAnalysisCriteria;
import gov.pcc.pwc.service.dto.RecAnalysisDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.RecGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class RecAnalysisRepositoryImpl implements RecAnalysisRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final Logger log = LoggerFactory.getLogger(RecAnalysisService.class);


    public RecAnalysisRepositoryImpl(SqlExecutor sqlExecutor, SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.serialNumGenerator = serialNumGenerator;
    }

    public static Date convertInstantToDate(Instant instant) {
        return instant == null ? null: Date.from(instant);
    }

    @Override
    public Page<RecAnalysisDTO> findAllByCriteria(RecAnalysisCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append("SELECT COUNT(*) FROM REC_ANALYSIS WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectNo()),"AND PROJECT_NO LIKE :projectNo","%"+criteria.getProjectNo()+"%"  )
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER (ORDER BY "+ criteria.getOrderBy()+ ") AS row_num, * FROM REC_ANALYSIS WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectNo()),"AND PROJECT_NO LIKE :projectNo","%"+criteria.getProjectNo()+"%" )
                .append("ORDER BY "+criteria.getOrderBy())
                .append("OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                .build();
        List<RecAnalysisDTO> recAnalysisDTOS = sqlExecutor.queryForList(query, RecAnalysisDTO.class);
        Page<RecAnalysisDTO> resultPage = new PageImpl<>(recAnalysisDTOS,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public RecAnalysisDTO packPkAndLogColumn(RecAnalysisDTO recAnalysisDTO) throws TypeVariableNotMatchException {
        String anaPk = serialNumGenerator
                .setModule(ModuleEnum.REC)
                .setVariable(new SerialNumVariableParam(RecGenNumberVariableEnum.ANA.getVariable()))
                .getGeneratedNumber();
        recAnalysisDTO.setAnalysisNo(anaPk);
        return recAnalysisDTO;
    }
}
