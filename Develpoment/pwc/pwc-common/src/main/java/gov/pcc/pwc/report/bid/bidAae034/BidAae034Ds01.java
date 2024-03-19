package gov.pcc.pwc.report.bid.bidAae034;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae034Ds01 extends AbstractReport {

    public BidAae034Ds01() {
        super(BidReport.BidAae034Ds01);
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
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        return this.list;
    }

}
