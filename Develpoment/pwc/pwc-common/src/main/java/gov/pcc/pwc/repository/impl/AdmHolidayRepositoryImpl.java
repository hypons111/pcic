package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmHolidayRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmHolidayCriteria;
import gov.pcc.pwc.service.dto.AdmHolidayDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class AdmHolidayRepositoryImpl implements AdmHolidayRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmHolidayRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmHolidayDTO> findAllByCriteria(AdmHolidayCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_HOLIDAY WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getHolidayName()), " AND HOLIDAY_NAME = :holidayName ",criteria.getHolidayName())
                .appendWhen(
                        Instant.ofEpochSecond(0) != criteria.getHolidayDate(),
                        " AND HOLIDAY_DATE = :holidayDate ",
                        LocalDate.from(criteria.getHolidayDate().atZone(ZoneId.systemDefault()))
                )
                .appendWhen( Instant.MIN != criteria.getDateStart()," AND HOLIDAY_DATE >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND HOLIDAY_DATE <= :dateEnd ", criteria.getDateEnd().toString())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, ")
                .append(" id, holiday_name, CONVERT(datetime, holiday_date) as holiday_date, update_user, update_time ")
                        .append(" from ADM_HOLIDAY WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getHolidayName()), " AND HOLIDAY_NAME = :holidayName ",criteria.getHolidayName())
                .appendWhen(
                        Instant.ofEpochSecond(0) != criteria.getHolidayDate(),
                        " AND HOLIDAY_DATE = :holidayDate ",
                        LocalDate.from(criteria.getHolidayDate().atZone(ZoneId.systemDefault()))
                )
                .appendWhen( Instant.MIN != criteria.getDateStart()," AND HOLIDAY_DATE >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND HOLIDAY_DATE <= :dateEnd ", criteria.getDateEnd().toString())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<AdmHolidayDTO> admHolidayDTOs = sqlExecutor.queryForList(query, AdmHolidayDTO.class);

        // TODO 時間轉換...這邊硬+8小時
        if (admHolidayDTOs.size() > 0) {
            admHolidayDTOs.forEach(admHolidayDTO -> {
                admHolidayDTO.setUpdateTime(admHolidayDTO.getUpdateTime().plusSeconds(28800));
            });
        }

        Page<AdmHolidayDTO> resultPage = new PageImpl<>(admHolidayDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }
}
