package gov.pcc.pwc.report.bid.bidAae020;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.ReportDefinition;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjDsptchDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae020s01c extends AbstractReport {
    public BidAae020s01c() {
        super(BidReport.BidAae020s01c);
    }

    List<BidPrjDsptchDTO> bidPrjDsptchDTOList;

    public List<BidPrjDsptchDTO> getBidPrjDsptchDTOList() {
        return bidPrjDsptchDTOList;
    }

    public void setBidPrjDsptchDTOList(List<BidPrjDsptchDTO> bidPrjDsptchDTOList) {
        this.bidPrjDsptchDTOList = bidPrjDsptchDTOList;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();

        // 編號
        String dspNo = "";
        // 通知日期
        String dspSdat = "";
        // 施工通知事項
        String dspItem = "";
        // 概估金額千元
        String dspPmon = "";
        // 預定完成日期
        String dspPedat = "";
        // 實際完成日期
        String dspAedat = "";
        // 估驗日期
        String dspF1dat = "";
        // 計價金額千元
        String dspAmon = "";
        // 付款日期
        String dspF2dat = "";

        if(bidPrjDsptchDTOList != null && bidPrjDsptchDTOList.size() > 0){
            for(BidPrjDsptchDTO item : bidPrjDsptchDTOList){
                Map<String, Object> map = new HashMap<>();
                // 編號
                dspNo = item.getDspNo();
                map.put("dspNo", dspNo);
                // 通知日期
                dspSdat = parseLocalDateToYYYMMDD(item.getDspSdat(),"/");
                map.put("dspSdat", dspSdat);
                // 施工通知事項
                dspItem = item.getDspItem();
                map.put("dspItem", dspItem);
                // 概估金額千元
                dspPmon = numberFormat(item.getDspPmon(), "#,##0.000#");
                map.put("dspPmon", dspPmon);
                // 預定完成日期
                dspPedat = parseLocalDateToYYYMMDD(item.getDspPedat(),"/");
                map.put("dspPedat", dspPedat);
                // 實際完成日期
                dspAedat = parseLocalDateToYYYMMDD(item.getDspAedat(),"/");
                map.put("dspAedat", dspAedat);
                // 估驗日期
                dspF1dat = parseLocalDateToYYYMMDD(item.getDspF1dat(),"/");
                map.put("dspF1dat", dspF1dat);
                // 計價金額千元
                dspAmon = numberFormat(item.getDspAmon(),"#,##0.000#");
                map.put("dspAmon", dspAmon);
                // 付款日期
                dspF2dat = parseLocalDateToYYYMMDD(item.getDspF2dat(),"/");
                map.put("dspF2dat", dspF2dat);

                list.add(map);
            }
        }

        return list;
    }
}
