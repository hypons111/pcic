package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmCodeTableRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmCodeTableCriteria;
import gov.pcc.pwc.service.dto.AdmCodeTableDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class AdmCodeTableRepositoryImpl implements AdmCodeTableRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmCodeTableRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }


    @Override
    public Page<AdmCodeTableDTO> findAllByCriteria(AdmCodeTableCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_CODE_TABLE WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getLang()), " AND LANG = :lang ",criteria.getLang())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getCategory()),
                        " AND CATEGORY = :category ",
                        criteria.getCategory()
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getCode()), " AND CODE LIKE :code ", "%" + criteria.getCode() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getText()), " AND TEXT LIKE :text ", "%" + criteria.getText() + "%")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, *")
                .append(" FROM ADM_CODE_TABLE ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getLang()), " AND LANG = :lang ",criteria.getLang())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getCategory()),
                        " AND CATEGORY = :category ",
                        criteria.getCategory()
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getCode()), " AND CODE LIKE :code ", "%" + criteria.getCode() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getText()), " AND TEXT LIKE :text ", "%" + criteria.getText() + "%")
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<AdmCodeTableDTO> admCodeTableDTOs = sqlExecutor.queryForList(query, AdmCodeTableDTO.class);
        Page<AdmCodeTableDTO> resultPage = new PageImpl<>(admCodeTableDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public AdmCodeTableDTO findByPk(Long id) {
        Query query = Query
                .builder()
                .append(" select * from ADM_CODE_TABLE ")
                .append( " WHERE ID = :id ", id)
                .build();
        List<AdmCodeTableDTO> admCodeTableDTOs = sqlExecutor.queryForList(query, AdmCodeTableDTO.class);
        AdmCodeTableDTO result = null;
        if (admCodeTableDTOs.size() > 0) {
            result = admCodeTableDTOs.get(0);
        }
        return result;
    }

    @Override
    public int update(AdmCodeTableDTO dto) {
        Query query = Query
                .builder()
                .append(" UPDATE ADM_CODE_TABLE SET LANG = :lang", dto.getLang())
                .append(" , CATEGORY = :category", dto.getCategory())
                .append(" , CODE = :code", dto.getCode())
                .append(" , TEXT = :text", dto.getText())
                .append(" WHERE ID = :id", dto.getId())
                .build();
        return sqlExecutor.update(query);
    }
}
