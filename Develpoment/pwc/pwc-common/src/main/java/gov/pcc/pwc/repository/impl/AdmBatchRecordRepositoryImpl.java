package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmBatchRecordRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmBatchRecordCriteria;
import gov.pcc.pwc.service.dto.AdmBatchRecordDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;

public class AdmBatchRecordRepositoryImpl implements AdmBatchRecordRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmBatchRecordRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmBatchRecordDTO> findAllByCriteria(AdmBatchRecordCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_BATCH_RECORD WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getId()), " AND ID LIKE :id ",criteria.getId())
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceNo()), " AND BATCH_SERVICE_NO LIKE :batchServiceNo ",criteria.getBatchServiceNo())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getBatchServiceName()),
                        " AND BATCH_SERVICE_NAME LIKE :batchServiceName ",
                        "%" + criteria.getBatchServiceName() + "%"
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceCategory()), " AND BATCH_SERVICE_CATEGORY LIKE :batchServiceCategory ", "%" + criteria.getBatchServiceCategory() + "%")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, * ")
                .append(" FROM ADM_BATCH_RECORD ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getId()), " AND ID LIKE :id ",criteria.getId())
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceNo()), " AND BATCH_SERVICE_NO LIKE :batchServiceNo ",criteria.getBatchServiceNo())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getBatchServiceName()),
                        " AND BATCH_SERVICE_NAME LIKE :batchServiceName ",
                        "%" + criteria.getBatchServiceName() + "%"
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceCategory()), " AND BATCH_SERVICE_CATEGORY LIKE :batchServiceCategory ", "%" + criteria.getBatchServiceCategory() + "%")
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<AdmBatchRecordDTO> admBatchRecordDTOs = sqlExecutor.queryForList(query, AdmBatchRecordDTO.class);
        // TODO 時間轉換...這邊硬+8小時
        if (admBatchRecordDTOs.size() > 0) {
            admBatchRecordDTOs.forEach(admBatchRecordDTO -> {
                admBatchRecordDTO.setTriggerTime(admBatchRecordDTO.getTriggerTime().plusSeconds(28800));
                admBatchRecordDTO.setFinishTime(admBatchRecordDTO.getFinishTime().plusSeconds(28800));
            });
        }
        Page<AdmBatchRecordDTO> resultPage = new PageImpl<>(admBatchRecordDTOs, criteria.toPageable(), totalCount);

        return resultPage;
    }
}
