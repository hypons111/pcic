package gov.pcc.pwc.report.bid.bidAab001;


import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BidAab001Test1 extends AbstractReport {

    public BidAab001Test1() {
        super(BidReport.BidAab001Test1);
    }

    private List<Map<String, Object>> list;

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        return null;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        return this.list;
    }



}
