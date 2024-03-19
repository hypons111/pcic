package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmBatchServiceRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmBatchServiceCriteria;
import gov.pcc.pwc.service.dto.AdmBatchServiceDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class AdmBatchServiceRepositoryImpl implements AdmBatchServiceRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmBatchServiceRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmBatchServiceDTO> findAllByCriteria(AdmBatchServiceCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_BATCH_SERVICE WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceNo()), " AND BATCH_SERVICE_NO LIKE :batchServiceNo ",criteria.getBatchServiceNo())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getBatchServiceName()),
                        " AND BATCH_SERVICE_NAME LIKE :batchServiceName ",
                        "%" + criteria.getBatchServiceName() + "%"
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceCategory()), " AND BATCH_SERVICE_CATEGORY LIKE :batchServiceCategory ", "%" + criteria.getBatchServiceCategory() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getIsRegular()),
                        " AND IS_REGULAR = :isRegular ", criteria.getIsRegular())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, * ")
                .append(" FROM ADM_BATCH_SERVICE ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceNo()), " AND BATCH_SERVICE_NO LIKE :batchServiceNo ",criteria.getBatchServiceNo())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getBatchServiceName()),
                        " AND BATCH_SERVICE_NAME LIKE :batchServiceName ",
                        "%" + criteria.getBatchServiceName() + "%"
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getBatchServiceCategory()), " AND BATCH_SERVICE_CATEGORY LIKE :batchServiceCategory ", "%" + criteria.getBatchServiceCategory() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getIsRegular()),
                        " AND IS_REGULAR = :isRegular ", criteria.getIsRegular())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<AdmBatchServiceDTO> admBatchServiceDTOs = sqlExecutor.queryForList(query, AdmBatchServiceDTO.class);
        Page<AdmBatchServiceDTO> resultPage = new PageImpl<>(admBatchServiceDTOs, criteria.toPageable(), totalCount);

        return resultPage;
    }
}
