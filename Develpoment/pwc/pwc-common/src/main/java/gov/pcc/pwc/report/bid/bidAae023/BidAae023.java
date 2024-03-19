package gov.pcc.pwc.report.bid.bidAae023;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae023  extends AbstractReport {
    public BidAae023() {
        super(BidReport.BidAae023);
    }

    //subReport用
    private BidAae023s01 bidAae023s01 =new BidAae023s01();
    private BidAae023s02 bidAae023s02 =new BidAae023s02();
    private BidAae023s03 bidAae023s03 =new BidAae023s03();

    private BidProjectDTO bidProjectDTO;
    private BidUnit10DTO wkutDTO;
    private BidUnit10DTO bosCodeDTO;
    private BidUnit10DTO depCodeDTO;
    private List<BidProjectUnitDTO> dsctLsit;
    private List<BidProjectUnitDTO> scctList;
    private List<BidPrjSur2DTO> sur2DTOList;
    private List<BidPrjPay2DTO> pay2DTOList;
    private  List<BidPrjMonthDTO> monthDTOList;

    public BidAae023s01 getBidAae023s01() {
        return bidAae023s01;
    }

    public void setBidAae023s01(BidAae023s01 bidAae023s01) {
        this.bidAae023s01 = bidAae023s01;
    }

    public BidAae023s02 getBidAae023s02() {
        return bidAae023s02;
    }

    public void setBidAae023s02(BidAae023s02 bidAae023s02) {
        this.bidAae023s02 = bidAae023s02;
    }

    public BidAae023s03 getBidAae023s03() {
        return bidAae023s03;
    }

    public void setBidAae023s03(BidAae023s03 bidAae023s03) {
        this.bidAae023s03 = bidAae023s03;
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

    public List<BidProjectUnitDTO> getDsctLsit() {
        return dsctLsit;
    }

    public void setDsctLsit(List<BidProjectUnitDTO> dsctLsit) {
        this.dsctLsit = dsctLsit;
    }

    public List<BidProjectUnitDTO> getScctList() {
        return scctList;
    }

    public void setScctList(List<BidProjectUnitDTO> scctList) {
        this.scctList = scctList;
    }

    public List<BidPrjSur2DTO> getSur2DTOList() {
        return sur2DTOList;
    }

    public void setSur2DTOList(List<BidPrjSur2DTO> sur2DTOList) {
        this.sur2DTOList = sur2DTOList;
    }

    public List<BidPrjPay2DTO> getPay2DTOList() {
        return pay2DTOList;
    }

    public void setPay2DTOList(List<BidPrjPay2DTO> pay2DTOList) {
        this.pay2DTOList = pay2DTOList;
    }

    public List<BidPrjMonthDTO> getMonthDTOList() {
        return monthDTOList;
    }

    public void setMonthDTOList(List<BidPrjMonthDTO> monthDTOList) {
        this.monthDTOList = monthDTOList;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("subreport1", bidAae023s01.getJasperReport());
        map.put("subreport2", bidAae023s02.getJasperReport());
        map.put("subreport3", bidAae023s03.getJasperReport());
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

        String bosCode = bosCodeDTO.getName();
        if(bosCode == null ){
            map.put("bosCode","(未填)");
        }else {
            map.put("bosCode",bosCode);
        }

        String depCode = depCodeDTO.getName();
        if(depCode == null ){
            map.put("depCode","(未填)");
        }else {
            map.put("depCode",depCode);
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
            map.put("ctSum",numberFormat(ctSumCheck, "#,##0.000#")+ " 千元");
        }

        LocalDate aopDate = bidProjectDTO.getAopDate();
        if(aopDate == null ){
            map.put("aopDate","(尚未公告)");
        }else {
            map.put("aopDate",parseLocalDateToYYYMMDD(aopDate));
        }

        // 規劃設計費用
        String dsct ="";
        int dsctCnt = 1;
        for (BidProjectUnitDTO item : dsctLsit) {
            if(dsctLsit.size() >1){
                if(item.getDsct() != null) {
                    dsct = dsct + "規劃設計費用" + dsctCnt + "： " + numberFormat(item.getDsct(), "#,##0.000#") + " 千元\n";
                }else {
                    dsct = dsct + "規劃設計費用" + dsctCnt + "： (未填)\n";
                }
                dsctCnt++;
            }else{
                if(item.getDsct() != null) {
                    dsct = "規劃設計費用： " + numberFormat(item.getDsct(), "#,##0.000#") + " 千元";
                }else{
                    dsct = "規劃設計費用： (未填)" ;
                }
            }

        }
        map.put("dsct", dsct);

        LocalDate actDate = bidProjectDTO.getActDate();
        if(actDate == null ){
            map.put("actDate","尚未決標");
        }else {
            map.put("actDate",parseLocalDateToYYYMMDD(actDate));
        }

        // 監造費用
        String scct ="";
        int scctCnt = 1;
        for (BidProjectUnitDTO item : scctList) {
            if(scctList.size() >1){
                if(item.getScct() != null) {
                    scct = scct + "監造費用" + scctCnt + "： " + numberFormat(item.getScct(), "#,##0.000#") + " 千元\n";

                }else {
                    scct = scct + "監造費用" + scctCnt + "： (未填)\n";
                }
                scctCnt++;
            }else{
                if(item.getScct() != null){
                    scct =  "監造費用： " + numberFormat(item.getScct(), "#,##0.000#") + " 千元";
                }else {
                    scct = "監造費用： (未填)";
                }

            }

        }
        map.put("scct", scct);

        // 行政院核定復建項目 子表1(BidAae023s01)
        bidAae023s01.setSur2DTOList(sur2DTOList);
        map.put("list1", bidAae023s01.toDataSource());
        // 歷次撥款狀況 子表2(BidAae023s02)
        bidAae023s02.setPay2DTOList(pay2DTOList);
        map.put("list2", bidAae023s02.toDataSource());
        // 各月份累積執行狀況 子表3(BidAae023s03)
        bidAae023s03.setMonthDTOList(monthDTOList);
        map.put("list3", bidAae023s03.toDataSource());


        list.add(map);




        return list;
    }
}
