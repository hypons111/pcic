package gov.pcc.pwc.report.bid.bidAae020;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjDsptchDTO;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae020s01a  extends AbstractReport {
    public BidAae020s01a() {
        super(BidReport.BidAae020s01a);
    }

    //subReport用
    private BidAae020s01b bidAae020s01b = new BidAae020s01b();
    private BidAae020s01c bidAae020s01c = new BidAae020s01c();

    private BidProjectDTO bidProjectDTO;
    private BidUnit10DTO wkutDTO;
    private BidUnit10DTO bosCodeDTO;
    private BidUnit10DTO depCodeDTO;
    private BidUnit10DTO plnCodeDTO;
    private List<BidPrjModifyDTO> bidPrjModifyDTOList;
    private List<BidPrjDsptchDTO> bidPrjDsptchDTOList;

    public BidAae020s01b getBidAae020s01b() {
        return bidAae020s01b;
    }

    public void setBidAae020s01b(BidAae020s01b bidAae020s01b) {
        this.bidAae020s01b = bidAae020s01b;
    }

    public BidAae020s01c getBidAae020s01c() {
        return bidAae020s01c;
    }

    public void setBidAae020s01c(BidAae020s01c bidAae020s01c) {
        this.bidAae020s01c = bidAae020s01c;
    }

    public BidProjectDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidUnit10DTO getWkutDTO() {
        return wkutDTO;
    }

    public void setWkutDTO(BidUnit10DTO wkutDTO) {
        this.wkutDTO = wkutDTO;
    }

    public BidUnit10DTO getBosCodeDTO() {
        return bosCodeDTO;
    }

    public void setBosCodeDTO(BidUnit10DTO bosCodeDTO) {
        this.bosCodeDTO = bosCodeDTO;
    }

    public BidUnit10DTO getDepCodeDTO() {
        return depCodeDTO;
    }

    public void setDepCodeDTO(BidUnit10DTO depCodeDTO) {
        this.depCodeDTO = depCodeDTO;
    }

    public BidUnit10DTO getPlnCodeDTO() {
        return plnCodeDTO;
    }

    public void setPlnCodeDTO(BidUnit10DTO plnCodeDTO) {
        this.plnCodeDTO = plnCodeDTO;
    }

    public List<BidPrjModifyDTO> getBidPrjModifyDTOList() {
        return bidPrjModifyDTOList;
    }

    public void setBidPrjModifyDTOList(List<BidPrjModifyDTO> bidPrjModifyDTOList) {
        this.bidPrjModifyDTOList = bidPrjModifyDTOList;
    }

    public List<BidPrjDsptchDTO> getBidPrjDsptchDTOList() {
        return bidPrjDsptchDTOList;
    }

    public void setBidPrjDsptchDTOList(List<BidPrjDsptchDTO> bidPrjDsptchDTOList) {
        this.bidPrjDsptchDTOList = bidPrjDsptchDTOList;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("subreport1", bidAae020s01b.getJasperReport());
        map.put("subreport2", bidAae020s01c.getJasperReport());
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Map<String, Object> map = new HashMap<>();
        // 基本資料
        String name = bidProjectDTO.getName();
        if(name == null ){
            map.put("name","(未填)");
        }else {
            map.put("name",name);
        }

        String wkut = wkutDTO.getName();
        if(wkut == null ){
            map.put("wkut","(未填)");
        }else {
            map.put("wkut",wkut);
        }

        String prjno = bidProjectDTO.getPrjno();
        if(prjno == null ){
            map.put("prjno","(未填)");
        }else {
            map.put("prjno",prjno);
        }

        String man = bidProjectDTO.getMan();
        if(man == null ){
            map.put("man","(未填)");
        }else {
            map.put("man",man);
        }

        String tel = bidProjectDTO.getTel();
        if(tel == null ){
            map.put("tel","(未填)");
        }else {
            map.put("tel",tel);
        }

        String plnCode = plnCodeDTO.getName();
        if(plnCode == null ){
            map.put("plnCode","(未填)");
        }else {
            map.put("plnCode",plnCode);
        }

        String plnNo = bidProjectDTO.getPlnNo();
        if(plnNo == null ){
            map.put("plnNo","(未填)");
        }else {
            map.put("plnNo",plnNo);
        }

        String bosCode = bosCodeDTO.getName();
        if(bosCode == null ){
            map.put("bosCode","(未填)");
        }else {
            map.put("bosCode",bosCode);
        }

        String deptCode = depCodeDTO.getName();
        System.out.println("depCode : "+deptCode);
        if(deptCode == null ){
            map.put("depCode","(未填)");
        }else {
            map.put("deptCode",deptCode);
        }

        BigDecimal bdgt1Check = bidProjectDTO.getBdgt1();
        if(bdgt1Check == null ){
            map.put("bdgt1","(未填)");
        }else {
            map.put("bdgt1",numberFormat(bdgt1Check, "#,##0.000")+ " 千元");
        }

        BigDecimal ctSumCheck = bidProjectDTO.getCtSum();
        if(ctSumCheck == null ){
            map.put("ctSum","(未填)");
        }else {
            map.put("ctSum",numberFormat(ctSumCheck, "#,##0.000")+ " 千元");
        }

        //子報表
        //變更設計
        bidAae020s01b.setModifyDTOList(bidPrjModifyDTOList);
        map.put("list1", bidAae020s01b.toDataSource());
        //開口合約派工
        bidAae020s01c.setBidPrjDsptchDTOList(bidPrjDsptchDTOList);
        map.put("list2", bidAae020s01c.toDataSource());

        list.add(map);
        return list;
    }
}
