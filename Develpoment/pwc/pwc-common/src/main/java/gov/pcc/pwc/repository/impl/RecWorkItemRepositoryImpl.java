package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.RecWorkItemRepositoryCustom;
import gov.pcc.pwc.service.RecWorkItemService;
import gov.pcc.pwc.service.criteria.RecWorkItemCriteria;
import gov.pcc.pwc.service.dto.RecWorkItemDTO;
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

public class RecWorkItemRepositoryImpl implements RecWorkItemRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final Logger log = LoggerFactory.getLogger(RecWorkItemService.class);


    public RecWorkItemRepositoryImpl(SqlExecutor sqlExecutor, SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.serialNumGenerator = serialNumGenerator;
    }

    public static Date convertInstantToDate(Instant instant) {
        return instant == null ? null: Date.from(instant);
    }

    @Override
    public Page<RecWorkItemDTO> findAllByCriteria(RecWorkItemCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append("SELECT COUNT(*) FROM REC_WORK_ITEM WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getAreaNo()),"AND AREA_NO LIKE :areaNo","%"+criteria.getAreaNo()+"%"  )
                .appendWhen(StringUtils.isNotBlank(criteria.getWorkItemNo()),"AND WORK_ITEM_NO LIKE :workItemNo","%"+criteria.getWorkItemNo()+"%"  )
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER (ORDER BY "+ criteria.getOrderBy()+ ") AS row_num, * FROM REC_WORK_ITEM WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getAreaNo()),"AND AREA_NO LIKE :areaNo","%"+criteria.getAreaNo()+"%"  )
                .appendWhen(StringUtils.isNotBlank(criteria.getWorkItemNo()),"AND WORK_ITEM_NO LIKE :workItemNo","%"+criteria.getWorkItemNo()+"%"  )
                .append("ORDER BY "+criteria.getOrderBy())
                .append("OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                .build();
        List<RecWorkItemDTO> recWorkItemDTOS = sqlExecutor.queryForList(query, RecWorkItemDTO.class);
        Page<RecWorkItemDTO> resultPage = new PageImpl<>(recWorkItemDTOS,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public RecWorkItemDTO packPkAndLogColumn(RecWorkItemDTO recWorkItemDTO) throws TypeVariableNotMatchException {
        String worPk = serialNumGenerator
                .setModule(ModuleEnum.REC)
                .setVariable(new SerialNumVariableParam(RecGenNumberVariableEnum.WOR.getVariable()))
                .getGeneratedNumber();
        recWorkItemDTO.setWorkItemNo(worPk);
        return recWorkItemDTO;
    }
}
