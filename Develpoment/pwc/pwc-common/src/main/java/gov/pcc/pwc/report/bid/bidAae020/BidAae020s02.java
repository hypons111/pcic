package gov.pcc.pwc.report.bid.bidAae020;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjDsptchDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae020s02 extends AbstractReport {
    public BidAae020s02() {
        super(BidReport.BidAae020s02);
    }

    private BidProjectDTO bidProjectDTO;

    private BidPrjDsptchDTO bidPrjDsptchDTO;

    private  BidUnit10DTO ccutNameDTO;

    private  BidUnit10DTO scutNameDTO;


    public BidProjectDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidPrjDsptchDTO getBidPrjDsptchDTO() {
        return bidPrjDsptchDTO;
    }

    public void setBidPrjDsptchDTO(BidPrjDsptchDTO bidPrjDsptchDTO) {
        this.bidPrjDsptchDTO = bidPrjDsptchDTO;
    }

    public BidUnit10DTO getCcutNameDTO() {
        return ccutNameDTO;
    }

    public void setCcutNameDTO(BidUnit10DTO ccutNameDTO) {
        this.ccutNameDTO = ccutNameDTO;
    }

    public BidUnit10DTO getScutNameDTO() {
        return scutNameDTO;
    }

    public void setScutNameDTO(BidUnit10DTO scutNameDTO) {
        this.scutNameDTO = scutNameDTO;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Map<String, Object> map = new HashMap<>();

        // 編號
        String dspNo = bidPrjDsptchDTO.getDspNo();
        map.put("dspNo", dspNo);
        // 通知日期
        String dspSdat = parseLocalDateToYYYMMDD(bidPrjDsptchDTO.getDspSdat(),"/");
        map.put("dspSdat", dspSdat);
        //送件人(需轉換)
        String sender = bidPrjDsptchDTO.getSender();
        map.put("sender", sender);
        //送件人電話
        String senderTel = bidPrjDsptchDTO.getSendtel();
        map.put("senderTel", senderTel);
        //送件人傳真
        String senderFax = bidPrjDsptchDTO.getSendfax();
        map.put("senderFax", senderFax);
        //收件者ccut
        String ccut1 = ccutNameDTO.getName();
        map.put("ccut1", ccut1);
        //收件者ccut電話
        String ccut1Tel = bidPrjDsptchDTO.getCcut1Tel();
        map.put("ccut1Tel", ccut1Tel);
        //收件者ccut傳真
        String ccut1Fax = bidPrjDsptchDTO.getCcut1Fax();
        map.put("ccut1Fax", ccut1Fax);
        //收件者ccut工地負責人
        String ccut1er = bidPrjDsptchDTO.getCcut1er();
        map.put("ccut1er", ccut1er);
        //收件者ccut工地負責人電話
        String ccut1erTel = bidPrjDsptchDTO.getCcut1erTel();
        map.put("ccut1erTel", ccut1erTel);
        //收件者scut
        String scut = scutNameDTO.getName();
        map.put("scut", scut);
        //收件者scut電話
        String scutTel = bidPrjDsptchDTO.getScutTel();
        map.put("scutTel", scutTel);
        //收件者scut傳真
        String scutFax = bidPrjDsptchDTO.getScutTel();
        map.put("scutFax", scutFax);
        //收件者scut監造負責人
        String scuter = bidPrjDsptchDTO.getScuter();
        map.put("scuter", scuter);
        //收件者scut監造負責人電話
        String scuterTel = bidPrjDsptchDTO.getScuterTel();
        map.put("scuterTel", scuterTel);
        //速別
        String dspSpeed = bidPrjDsptchDTO.getDspSpeed();
        map.put("dspSpeed", dspSpeed);
        // 標案名稱
        String name = bidProjectDTO.getName();
        map.put("name", name);
        // 通知內容
        String dspItem = bidPrjDsptchDTO.getDspItem();
        map.put("dspItem", dspItem);

        list.add(map);
        return list;
    }
}
