package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.RecTyphoonRepositoryCustom;
import gov.pcc.pwc.service.RecTyphoonService;
import gov.pcc.pwc.service.criteria.RecTyphoonCriteria;
import gov.pcc.pwc.service.dto.RecTyphoonDTO;
import gov.pcc.pwc.service.dto.RecTyphoonQueryDTO;
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

public class RecTyphoonRepositoryImpl implements RecTyphoonRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final Logger log = LoggerFactory.getLogger(RecTyphoonService.class);


    public RecTyphoonRepositoryImpl(SqlExecutor sqlExecutor, SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.serialNumGenerator = serialNumGenerator;
    }

    public static Date convertInstantToDate(Instant instant) {
        return instant == null ? null: Date.from(instant);
    }

    @Override
    public Page<RecTyphoonQueryDTO> findAllByCriteria(RecTyphoonCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append("SELECT COUNT(*) FROM REC_TYPHOON WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getTyphoonName()),"AND TYPHOON_NAME LIKE :typhoonName", "%" + criteria.getTyphoonName().trim() + "%" )
                .appendWhen(criteria.getApplyStartTimeFrom() != null, "AND APPLY_START_TIME >= :applyStartTimeFrom", convertInstantToDate(criteria.getApplyStartTimeFrom()))
                .appendWhen(criteria.getApplyStartTimeTo() != null , "AND APPLY_START_TIME <= :applyStartTimeTo", convertInstantToDate(criteria.getApplyStartTimeTo()))
                .appendWhen(criteria.getApplyEndTimeFrom() != null, "AND APPLY_END_TIME >= :applyEndTimeFrom",  convertInstantToDate(criteria.getApplyEndTimeFrom()))
                .appendWhen(criteria.getApplyEndTimeTo() != null, "AND APPLY_END_TIME <= :applyEndTimeTo", convertInstantToDate(criteria.getApplyEndTimeTo()))
                .appendWhen(criteria.getIsLocalOnly() != null,"AND IS_LOCAL_ONLY = :isLocalOnly", criteria.getIsLocalOnly())
                .appendWhen( criteria.getIsEditSubmitData() != null,"AND IS_EDIT_SUBMIT_DATA = :isEditSubmitData" , criteria.getIsEditSubmitData())
                .appendWhen(criteria.getIsDenyBudgetChange() != null,"AND IS_DENY_BUDGET_CHANGE = :DenyBudgetChange", criteria.getIsDenyBudgetChange())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER (ORDER BY "+ criteria.getOrderBy()+ ") AS row_num, * FROM REC_TYPHOON WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getTyphoonName()),"AND TYPHOON_NAME LIKE :typhoonName", "%" + criteria.getTyphoonName().trim() + "%" )
                .appendWhen(criteria.getApplyStartTimeFrom() != null, "AND APPLY_START_TIME >= :applyStartTimeFrom", convertInstantToDate(criteria.getApplyStartTimeFrom()))
                .appendWhen(criteria.getApplyStartTimeTo() != null , "AND APPLY_START_TIME <= :applyStartTimeTo", convertInstantToDate(criteria.getApplyStartTimeTo()))
                .appendWhen(criteria.getApplyEndTimeFrom() != null, "AND APPLY_END_TIME >= :applyEndTimeFrom",  convertInstantToDate(criteria.getApplyEndTimeFrom()))
                .appendWhen(criteria.getApplyEndTimeTo() != null, "AND APPLY_END_TIME <= :applyEndTimeTo", convertInstantToDate(criteria.getApplyEndTimeTo()))
                .appendWhen(criteria.getIsLocalOnly() != null,"AND IS_LOCAL_ONLY = :isLocalOnly", criteria.getIsLocalOnly())
                .appendWhen( criteria.getIsEditSubmitData() != null,"AND IS_EDIT_SUBMIT_DATA = :isEditSubmitData" , criteria.getIsEditSubmitData())
                .appendWhen(criteria.getIsDenyBudgetChange() != null,"AND IS_DENY_BUDGET_CHANGE = :DenyBudgetChange", criteria.getIsDenyBudgetChange())
                .append("ORDER BY "+criteria.getOrderBy())
                .append("OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                .build();
        List<RecTyphoonQueryDTO> recTyphoonQueryDTOS = sqlExecutor.queryForList(query, RecTyphoonQueryDTO.class);
        Page<RecTyphoonQueryDTO> resultPage = new PageImpl<>(recTyphoonQueryDTOS,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public RecTyphoonDTO packPkAndLogColumn(RecTyphoonDTO recTyphoonDTO) throws TypeVariableNotMatchException {
            String typPk = serialNumGenerator
                    .setModule(ModuleEnum.REC)
                    .setVariable(new SerialNumVariableParam(RecGenNumberVariableEnum.TYP.getVariable()))
                    .getGeneratedNumber();
            recTyphoonDTO.setTyphoonNo(typPk);
        return recTyphoonDTO;
    }
}
