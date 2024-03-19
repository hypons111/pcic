package gov.pcc.pwc.report.bid.bidAab001;


import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;

import java.util.List;
import java.util.Map;

public class BidAab001Test2 extends AbstractReport {

    public BidAab001Test2() {
        super(BidReport.BidAab001Test2);
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
