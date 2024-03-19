package gov.pcc.pwc.report.bid.bidAae013;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.ReportDefinition;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BidAae013 extends AbstractReport {
    public BidAae013() {
        super(BidReport.BidAae013);
    }

    private BidPrjStcDTO bidPrjStcDTO;

    public BidPrjStcDTO getBidPrjStcDTO() {
        return bidPrjStcDTO;
    }

    public void setBidPrjStcDTO(BidPrjStcDTO bidPrjStcDTO) {
        this.bidPrjStcDTO = bidPrjStcDTO;
    }
    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("name",bidPrjStcDTO.getName() != null ? bidPrjStcDTO.getName() : "(未填)");
        map.put("prjno",bidPrjStcDTO.getName() != null ? bidPrjStcDTO.getPrjno() : "(未填)");

        BigDecimal bdgt1Chk = bidPrjStcDTO.getBdgt1();
        String bdgt1 = "";
        if(bdgt1Chk != null){
            bdgt1 =  numberFormat(bdgt1Chk,"#,##0.000#")+" 千元";
        }else {
            bdgt1 = "(未填)";
        }
        map.put("bdgt1",bdgt1);

        LocalDate checkDateChk = bidPrjStcDTO.getCheckDate();
        if(checkDateChk != null){
            map.put("checkDate",parseLocalDateToYYYMMDD(checkDateChk));
        }else {
            map.put("checkDate", "(未填)");
        }

        return map;
    }


    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Map<String, Object> map1 = new HashMap<>();

        // 分項目填入值
        map1.put("number", "1");
        map1.put("checkedListName", "環境影響評估(含環境影響差異分析)");
        map1.put("yndA", bidPrjStcDTO.getYndA01() != null ? bidPrjStcDTO.getYndA01() : "");
        map1.put("resonA", bidPrjStcDTO.getResonA01() != null ? bidPrjStcDTO.getResonA01() : "");
        map1.put("dateA", bidPrjStcDTO.getDateA01() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA01()) : "");
        map1.put("law", "環境影響評估法");
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("number", "2");
        map2.put("checkedListName", "水土保持規劃書及水土保持計畫");
        map2.put("yndA", bidPrjStcDTO.getYndA02() != null ? bidPrjStcDTO.getYndA02() : "");
        map2.put("resonA", bidPrjStcDTO.getResonA02() != null ? bidPrjStcDTO.getResonA02() : "");
        map2.put("dateA", bidPrjStcDTO.getDateA02() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA02()) : "");
        map2.put("law", "水土保持法");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("number", "3");
        map3.put("checkedListName", "非都市土地使用變更編定");
        map3.put("yndA", bidPrjStcDTO.getYndA03() != null ? bidPrjStcDTO.getYndA03() : "");
        map3.put("resonA", bidPrjStcDTO.getResonA03() != null ? bidPrjStcDTO.getResonA03() : "");
        map3.put("dateA", bidPrjStcDTO.getDateA03() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA03()) : "");
        map3.put("law", "區域計畫法、非都市土地使用管制規則");
        list.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("number", "4");
        map4.put("checkedListName", "都市計畫擬定或變更");
        map4.put("yndA", bidPrjStcDTO.getYndA04() != null ? bidPrjStcDTO.getYndA04() : "");
        map4.put("resonA", bidPrjStcDTO.getResonA04() != null ? bidPrjStcDTO.getResonA04() : "");
        map4.put("dateA", bidPrjStcDTO.getDateA04() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA04()) : "");
        map4.put("law", "都市計畫法");
        list.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("number", "5");
        map5.put("checkedListName", "用地取得");
        map5.put("yndA", bidPrjStcDTO.getYndA05() != null ? bidPrjStcDTO.getYndA05() : "");
        map5.put("resonA", bidPrjStcDTO.getResonA05() != null ? bidPrjStcDTO.getResonA05() : "");
        map5.put("dateA", bidPrjStcDTO.getDateA05() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA05()) : "");
        map5.put("law", "土地徵收條例、土地法、國有財產法");
        list.add(map5);

        Map<String, Object> map6 = new HashMap<>();
        map6.put("number", "6");
        map6.put("checkedListName", "都市計畫審議");
        map6.put("yndA", bidPrjStcDTO.getYndA06() != null ? bidPrjStcDTO.getYndA06() : "");
        map6.put("resonA", bidPrjStcDTO.getResonA06() != null ? bidPrjStcDTO.getResonA06() : "");
        map6.put("dateA", bidPrjStcDTO.getDateA06() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA06()) : "");
        map6.put("law", "都市計畫法");
        list.add(map6);

        Map<String, Object> map7 = new HashMap<>();
        map7.put("number", "7");
        map7.put("checkedListName", "古蹟、歷史建築、聚落、文化景觀及遺址保存與維護");
        map7.put("yndA", bidPrjStcDTO.getYndA07() != null ? bidPrjStcDTO.getYndA07() : "");
        map7.put("resonA", bidPrjStcDTO.getResonA07() != null ? bidPrjStcDTO.getResonA07() : "");
        map7.put("dateA", bidPrjStcDTO.getDateA07() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA07()) : "");
        map7.put("law", "文化資產保存法");
        list.add(map7);

        Map<String, Object> map8 = new HashMap<>();
        map8.put("number", "8");
        map8.put("checkedListName", "建築許可(建築執照或特種建築物許可等)");
        map8.put("yndA", bidPrjStcDTO.getYndA08() != null ? bidPrjStcDTO.getYndA08() : "");
        map8.put("resonA", bidPrjStcDTO.getResonA08() != null ? bidPrjStcDTO.getResonA08() : "");
        map8.put("dateA", bidPrjStcDTO.getDateA08() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA08()) : "");
        map8.put("law", "建築法");
        list.add(map8);

        Map<String, Object> map9 = new HashMap<>();
        map9.put("number", "9");
        map9.put("checkedListName", "河川區域使用許可");
        map9.put("yndA", bidPrjStcDTO.getYndA09() != null ? bidPrjStcDTO.getYndA09() : "");
        map9.put("resonA", bidPrjStcDTO.getResonA09() != null ? bidPrjStcDTO.getResonA09() : "");
        map9.put("dateA", bidPrjStcDTO.getDateA09() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA09()) : "");
        map9.put("law", "水利法");
        list.add(map9);

        Map<String, Object> map10 = new HashMap<>();
        map10.put("number", "10");
        map10.put("checkedListName", "鄰近、跨(穿)越鐵路、公路施工許可；捷運禁限建範圍施工許可；道路挖掘許可");
        map10.put("yndA", bidPrjStcDTO.getYndA10() != null ? bidPrjStcDTO.getYndA10() : "");
        map10.put("resonA", bidPrjStcDTO.getResonA10() != null ? bidPrjStcDTO.getResonA10() : "");
        map10.put("dateA", bidPrjStcDTO.getDateA10() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA10()) : "");
        map10.put("law", "公路法、鐵路法、大眾捷運法、地方自治條例");
        list.add(map10);

        Map<String, Object> map11 = new HashMap<>();
        map11.put("number", "11");
        map11.put("checkedListName", "管線箱涵處理及公有管線佈設許可");
        map11.put("yndA", bidPrjStcDTO.getYndA11() != null ? bidPrjStcDTO.getYndA11() : "");
        map11.put("resonA", bidPrjStcDTO.getResonA11() != null ? bidPrjStcDTO.getResonA11() : "");
        map11.put("dateA", bidPrjStcDTO.getDateA11() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA11()) : "");
        map11.put("law", "共同管道法及地方自治條例");
        list.add(map11);

        Map<String, Object> map12 = new HashMap<>();
        map12.put("number", "12");
        map12.put("checkedListName", "樹木保護計畫");
        map12.put("yndA", bidPrjStcDTO.getYndA12() != null ? bidPrjStcDTO.getYndA12() : "");
        map12.put("resonA", bidPrjStcDTO.getResonA12() != null ? bidPrjStcDTO.getResonA12() : "");
        map12.put("dateA", bidPrjStcDTO.getDateA12() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA12()) : "");
        map12.put("law", "各直轄市、縣(市)自治條例");
        list.add(map12);

        Map<String, Object> map13 = new HashMap<>();
        map13.put("number", "13");
        map13.put("checkedListName", "評估其他堤案影響");
        map13.put("yndA", bidPrjStcDTO.getYndA13() != null ? bidPrjStcDTO.getYndA13() : "");
        map13.put("resonA", bidPrjStcDTO.getResonA13() != null ? bidPrjStcDTO.getResonA13() : "");
        map13.put("dateA", bidPrjStcDTO.getDateA13() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA13()) : "");
        map13.put("law", "");
        list.add(map13);

        Map<String, Object> map14 = new HashMap<>();
        map14.put("number", "14");
        map14.put("checkedListName", "地上物處理");
        map14.put("yndA", bidPrjStcDTO.getYndA14() != null ? bidPrjStcDTO.getYndA14() : "");
        map14.put("resonA", bidPrjStcDTO.getResonA14() != null ? bidPrjStcDTO.getResonA14() : "");
        map14.put("dateA", bidPrjStcDTO.getDateA14() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA14()) : "");
        map14.put("law", "土地徵收條例");
        list.add(map14);

        Map<String, Object> map15 = new HashMap<>();
        map15.put("number", "15");
        map15.put("checkedListName", "工程預算可支用情形");
        map15.put("yndA", bidPrjStcDTO.getYndA15() != null ? bidPrjStcDTO.getYndA15() : "");
        map15.put("resonA", bidPrjStcDTO.getResonA15() != null ? bidPrjStcDTO.getResonA15() : "");
        map15.put("dateA", bidPrjStcDTO.getDateA15() != null ? parseLocalDateToYYYMMDD(bidPrjStcDTO.getDateA15()) : "");
        map15.put("law", "");
        list.add(map15);

        return list;
    }
}
