package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.VwBidProjectRepositoryCustom;
import gov.pcc.pwc.service.criteria.VwBidProjectCriteria;
import gov.pcc.pwc.service.dto.VwBidProjectDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class VwBidProjectRepositoryImpl implements VwBidProjectRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public VwBidProjectRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<VwBidProjectDTO> findProjectByDesingOrSupervisionUnitName(VwBidProjectCriteria criteria) {
        // LocalDate specificLocaldate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 16);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDD");
        // String specificDateString = specificLocaldate.format(formatter);
        if(criteria.getSortBy() == null ||criteria.getSortBy().length==0){
            criteria.setSortBy(new String[]{"prjno","wkut"});
        }
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM VW_BID_PROJECT vbp")
                .append(" WHERE vbp.PRJNO in ")
                .append( "(SELECT distinct PRJNO FROM VW_BID_PROJECT_UNIT vbpu")
                .append( "WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getSpecificUnitName()), "AND DSUT_NAME LIKE :keyWord OR SCUT_NAME LIKE :keyWord )","%"+criteria.getSpecificUnitName()+"%","%"+criteria.getSpecificUnitName()+"%")
                .append("AND ( SUBSTRING(vbp.type_code, 1, 1) != '9')")
                .append("AND ( isnull(vbp.bdgt1, 0) >= 1000 )")
                //.append("AND (AOK_DATE is null OR CONVERT(varchar, AOK_DATE, 112) >= :specificDateString )",specificDateString)
                .build();

        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" SELECT * FROM VW_BID_PROJECT vbp")
                .append(" WHERE vbp.PRJNO in ")
                .append( "(SELECT distinct PRJNO FROM VW_BID_PROJECT_UNIT vbpu")
                .append( "WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getSpecificUnitName()), "AND DSUT_NAME LIKE :keyWord OR SCUT_NAME LIKE :keyWord2 )","%"+criteria.getSpecificUnitName()+"%","%"+criteria.getSpecificUnitName()+"%")
                .append("AND ( SUBSTRING(vbp.type_code, 1, 1) != '9')")
                .append("AND ( isnull(vbp.bdgt1, 0) >= 1000 )")
                //.append("AND (AOK_DATE is null OR CONVERT(varchar, AOK_DATE, 112) >= :specificDateString )",specificDateString)
                .append("order by " + StringUtils.join(criteria.getSortBy(), ","))
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<VwBidProjectDTO> VwBidProjectDTOs = sqlExecutor.queryForList(query, VwBidProjectDTO.class);
        return  new PageImpl<>(VwBidProjectDTOs, criteria.toPageable(), totalCount);
    }
}
