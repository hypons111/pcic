package gov.pcc.pwc.report.bid.bidAab001;


import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BidAab001Test extends AbstractReport {

    public BidAab001Test() {
        super(BidReport.BidAab001Test);
    }

    private BidProjectInfoDTO bidProjectDTO;

    private List<BidPrjMonthDTO> bidPrjMonthDTOs;

    //subReport用
    private BidAab001Test1 bidAab001Test1 = new BidAab001Test1();

    private BidAab001Test2 bidAab001Test2 = new BidAab001Test2();

    private List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();

    private List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

    public BidAab001Test1 getBidAab001Test1() {
        return bidAab001Test1;
    }

    public void setBidAab001Test1(BidAab001Test1 bidAab001Test1) {
        this.bidAab001Test1 = bidAab001Test1;
    }

    public List<Map<String, Object>> getList1() {
        return list1;
    }

    public void setList1(List<Map<String, Object>> list1) {
        this.list1 = list1;
    }

    public BidProjectInfoDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectInfoDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public List<BidPrjMonthDTO> getBidPrjMonthDTOs() {
        return bidPrjMonthDTOs;
    }

    public void setBidPrjMonthDTOs(List<BidPrjMonthDTO> bidPrjMonthDTOs) {
        this.bidPrjMonthDTOs = bidPrjMonthDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.transBean2Map(bidProjectDTO));

        String planName = bidProjectDTO.getPlnName();
        if (StringUtils.isNotBlank(planName)) {
            map.put("planName", "(未填)");
        }
        String planNo = bidProjectDTO.getPlnNo();
        if (StringUtils.isNotBlank(planNo)) {
            map.put("planName", "(未填)");
        }
        BigDecimal bdgt1 = bidProjectDTO.getBdgt1();
        if (bdgt1 != null) {
            map.put("bdgt1", numberFormat(bdgt1, "#,##0.00#") + "千元");
        }
        BigDecimal ctSum = bidProjectDTO.getCtSum();
        if (ctSum != null) {
            map.put("ctSum", numberFormat(ctSum, "#,##0.00#") + "千元");
        }
        //put subReport

        map.put("subReport", this.bidAab001Test1.getJasperReport());
        map.put("subReport2", this.bidAab001Test2.getJasperReport());

        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {

//        List<Map<String, Object>> list1 = super.convertToDataList();
        for (BidPrjMonthDTO dto : bidPrjMonthDTOs) {
            Map<String, Object> map = new HashMap<>();
            map.put("yearAndMonth", dto.getYr() + "/" + dto.getMonth());
            map.put("ptrate", dto.getPtrate() != null ? (numberFormat(dto.getPtrate(), "#,##0.00#") + "%") : "(未填)");
            map.put("pexp", dto.getPexp() != null ? (numberFormat(dto.getPexp(), "#,##0.00#")) : "(未填)");
            map.put("item", dto.getItem());
            this.list1.add(map);
        }

//        List<Map<String, Object>> list2 = super.convertToDataList();

        List<BidPrjMonthDTO> newList = convertMonthDTOtoYearDTOList(bidPrjMonthDTOs);
        for (BidPrjMonthDTO monthDto : newList) {
            Map<String, Object> newMap = new HashMap<>();
            if (monthDto.getRate() != null) {
                newMap.put("rate", monthDto.getRate() != null ? (numberFormat(monthDto.getRate(), "#,##0.00#") + "%") : "(未填)");
            }
            if (monthDto.getYpexp() != null) {
                newMap.put("ypexp", monthDto.getYpexp() != null ? (numberFormat(monthDto.getYpexp(), "#,##0.00#")) : "(未填)");
            }
            newMap.put("year", monthDto.getSyr());
            this.list2.add(newMap);
        }

        List<Map<String, Object>> returnList = super.convertToDataList();
        Map<String, Object> returnMap = new HashMap<>();

        this.bidAab001Test1.setList(this.list1);
        returnMap.put("list1", this.bidAab001Test1.toDataSource());

        this.bidAab001Test2.setList(this.list2);
        returnMap.put("list2", this.bidAab001Test2.toDataSource());

        returnList.add(returnMap);

        return returnList;
    }

    private List<BidPrjMonthDTO> convertMonthDTOtoYearDTOList(List<BidPrjMonthDTO> bidPrjMonthDTOList) {
        Map<String, BidPrjMonthDTO> map = new HashMap<>();
        for (BidPrjMonthDTO dto : bidPrjMonthDTOList) {
            map.put(dto.getSyr(), dto);
        }
        return map.values().stream().collect(Collectors.toList());

    }


}
