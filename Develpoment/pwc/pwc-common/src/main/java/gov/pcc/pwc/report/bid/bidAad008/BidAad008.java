package gov.pcc.pwc.report.bid.bidAad008;


import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAad008 extends AbstractReport {

    public BidAad008() {
        super(BidReport.BidAad008);
    }

    private BidProjectInfoDTO bidProjectInfoDTO;

    private BidProjectDTO bidProjectDTO;

    private BidKpifinDTO bidKpifinDTO;

    private BidChkaptDTO bidChkaptDTO;

    private List<BidUnit10DTO> ccuts;

    private List<BidPrjMonthDTO> bidPrjMonthDTOs;

    private List<BidGovCheckDTO> bidGovCheckDTOs;

    private List<BidPrjPriceDTO> bidPrjPriceDTOs;

    private List<BidPrjSafeDTO> bidPrjSafeDTOS;

    private List<BidPrjEnviDTO> bidPrjEnviDTOS;

    private List<BidOverseeDTO> bidOversees;

    // getter and setter

    public BidProjectInfoDTO getBidProjectInfoDTO() {
        return bidProjectInfoDTO;
    }

    public void setBidProjectInfoDTO(BidProjectInfoDTO bidProjectInfoDTO) {
        this.bidProjectInfoDTO = bidProjectInfoDTO;
    }

    public BidProjectDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidKpifinDTO getBidKpifinDTO() {
        return bidKpifinDTO;
    }

    public void setBidKpifinDTO(BidKpifinDTO bidKpifinDTO) {
        this.bidKpifinDTO = bidKpifinDTO;
    }

    public List<BidUnit10DTO> getCcuts() {
        return ccuts;
    }

    public void setCcuts(List<BidUnit10DTO> ccuts) {
        this.ccuts = ccuts;
    }

    public BidChkaptDTO getBidChkaptDTO() {
        return bidChkaptDTO;
    }

    public List<BidPrjPriceDTO> getBidPrjPriceDTOs() {
        return bidPrjPriceDTOs;
    }

    public void setBidPrjPriceDTOs(List<BidPrjPriceDTO> bidPrjPriceDTOs) {
        this.bidPrjPriceDTOs = bidPrjPriceDTOs;
    }

    public void setBidChkaptDTO(BidChkaptDTO bidChkaptDTO) {
        this.bidChkaptDTO = bidChkaptDTO;
    }

    public List<BidPrjMonthDTO> getBidPrjMonthDTOs() {
        return bidPrjMonthDTOs;
    }

    public void setBidPrjMonthDTOs(List<BidPrjMonthDTO> bidPrjMonthDTOs) {
        this.bidPrjMonthDTOs = bidPrjMonthDTOs;
    }

    public List<BidGovCheckDTO> getBidGovCheckDTOs() {
        return bidGovCheckDTOs;
    }

    public void setBidGovCheckDTOs(List<BidGovCheckDTO> bidGovCheckDTOs) {
        this.bidGovCheckDTOs = bidGovCheckDTOs;
    }


    public List<BidPrjSafeDTO> getBidPrjSafeDTOS() {
        return bidPrjSafeDTOS;
    }

    public void setBidPrjSafeDTOS(List<BidPrjSafeDTO> bidPrjSafeDTOS) {
        this.bidPrjSafeDTOS = bidPrjSafeDTOS;
    }

    public List<BidPrjEnviDTO> getBidPrjEnviDTOS() {
        return bidPrjEnviDTOS;
    }

    public void setBidPrjEnviDTOS(List<BidPrjEnviDTO> bidPrjEnviDTOS) {
        this.bidPrjEnviDTOS = bidPrjEnviDTOS;
    }

    public List<BidOverseeDTO> getBidOversees() {
        return bidOversees;
    }

    public void setBidOversees(List<BidOverseeDTO> bidOversees) {
        this.bidOversees = bidOversees;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.transBean2Map(bidProjectInfoDTO));
        map.putAll(this.transBean2Map(bidKpifinDTO));
        if (bidChkaptDTO != null) {
            map.putAll(this.transBean2Map(bidChkaptDTO));
        }

        // 處理project 基本資料
        String planName = bidProjectInfoDTO.getPlnName();
        if (StringUtils.isNotBlank(planName)) {
            map.put("planName", "(未填)");
        }

        BigDecimal bdgt1 = bidProjectInfoDTO.getBdgt1();
        if (bdgt1 != null) {
            map.put("bdgt1", numberFormat(bdgt1, "#,##0.000#") + "千元");
        }

        BigDecimal ctSum = bidProjectInfoDTO.getCtSum();
        if (ctSum != null) {
            map.put("ctSum", numberFormat(ctSum, "#,##0.000#") + "千元");
        }
        BigDecimal ctSumF = bidProjectInfoDTO.getCtSumF();
        if (ctSumF != null) {
            map.put("ctSumF", numberFormat(ctSum, "#,##0.000#") + "千元");
        }
        map.put("wDays", bidProjectDTO.getwDays()!=null ? bidProjectDTO.getwDays() : 0 );

        map.put("fastDay", bidProjectDTO.getFastDay()!=null?bidProjectDTO.getFastDay().toString():"(未填)" );
        map.put("overDayY",bidProjectDTO.getOverDayY()!=null?bidProjectDTO.getOverDayY().toString():"(未填)");
        map.put("otherFee",bidProjectDTO.getOtherFee()!=null?numberFormat(bidProjectDTO.getOtherFee(), "#,##0.000#") + "千元":"(未填)");

        if (!ccuts.isEmpty()) {
            StringBuilder ccutsNameBuilder = new StringBuilder();
            StringBuilder ccutsCodeBuilder = new StringBuilder();
            for (int i = 0; i < ccuts.size(); i++) {
                ccutsNameBuilder.append(ccuts.get(i).getName());
                ccutsCodeBuilder.append(ccuts.get(i).getCode());
                if (i < ccuts.size() - 1){
                    ccutsNameBuilder.append("\n");
                    ccutsCodeBuilder.append("\n");
                }
            }
            map.put("ccutNames", ccutsNameBuilder.toString());
            map.put("ccutCodes", ccutsCodeBuilder.toString());
        }

        if (bdgt1 != null && ctSum != null) {
            map.put("bdgt1DctSum", numberFormat(bdgt1.divide(ctSum).multiply(new BigDecimal(100)), "#,##0.00#") + "%");
        } else {
            map.put("bdgt1DctSum", "(無法計算)");
        }

        map.put("documentNumber", parseLocalDateToYYYMMDD(bidKpifinDTO.getIdate()) + "     " + bidKpifinDTO.getConfNumber());

        if (bidKpifinDTO.getRecnt() != null) {
            if (bidKpifinDTO.getRecnt() == 1) {
                map.put("recntResult", "■初次計分　　　　　　　　□第 次修正計分");
            } else {
                map.put("recntResult", "□初次計分　　　　　　　　■第" + bidKpifinDTO.getRecnt() + "次修正計分");
            }
        }

        //承攬廠商欄位
        map.put("asDate", bidProjectDTO.getAsDate() != null ? parseLocalDateToYYYMMDD(bidProjectDTO.getAsDate()) : "(未填)");

        LocalDate ceDateF = bidProjectDTO.getCeDateF() != null ? bidProjectDTO.getCeDateF() : bidProjectDTO.getCeDate() != null ? bidProjectDTO.getCeDate() : null;
        map.put("ceDateF", ceDateF != null ? parseLocalDateToYYYMMDD(ceDateF) : "(未填)");
        map.put("aeDate", bidProjectDTO.getAeDate() != null ? parseLocalDateToYYYMMDD(bidProjectDTO.getAeDate()) : "(未填)");
        if (bidProjectDTO.getFinWays() == null || bidProjectDTO.getFinWays() == 0) {
            map.put("finWays", "(不合理)");
        }
        //提前竣工比例
        if (bidProjectDTO.getFastDay() != null && bidProjectDTO.getFinWays() != null && bidProjectDTO.getFastDay() > 0 && bidProjectDTO.getFinWays() > 0) {
            BigDecimal rate = new BigDecimal(bidProjectDTO.getFastDay()).divide(new BigDecimal(bidProjectDTO.getFinWays()).multiply(new BigDecimal(100)));
            map.put("fastRate", numberFormat(rate, "#,##0.00#") + "%");
        } else {
            map.put("fastRate", "---");
        }
        map.put("rb01", this.getRb01ByCtsum(bidProjectDTO.getCtSum()) + "%");
        //逾期竣工
        //逾期違約金天數比例
        if (bidProjectDTO.getOverDayY() != null && bidProjectDTO.getFinWays() != null && bidProjectDTO.getOverDayY() > 0 && bidProjectDTO.getFinWays() > 0) {
            BigDecimal rate = null;
            if (ceDateF != null && bidProjectDTO.getAsDate() != null) {
                long days = Duration.between(ceDateF, bidProjectDTO.getAsDate()).toDays();
                if (bidProjectDTO.getwDays() != null && bidProjectDTO.getwDays() > 0) {
                    rate = new BigDecimal(bidProjectDTO.getFastDay()).divide(new BigDecimal(bidProjectDTO.getFinWays()).multiply(new BigDecimal(100)));
                } else if (days > 0) {
                    rate = new BigDecimal(bidProjectDTO.getFastDay()).divide(new BigDecimal(bidProjectDTO.getFinWays()).multiply(new BigDecimal(100)));
                }
            }
            if (rate != null) {
                map.put("slowRate", numberFormat(rate, "#,##0.00#") + "%");
            } else {
                map.put("slowRate", "---");
            }
        }

        //是否有施工廠商於履約期間主動提出技術、工法、材料或設備之替代方案，經主辦機關依政府採購法第35條規定評估後採行之情形
        LocalDate compareDate = bidProjectDTO.getAeDate() != null ? bidProjectDTO.getAeDate() : LocalDate.now();
        boolean checkSubstitude = false;
        for (BidPrjMonthDTO monthDTO : bidPrjMonthDTOs) {
            LocalDate monthDate = LocalDate.of(Integer.parseInt(monthDTO.getSyr()) + 1911, Integer.parseInt(monthDTO.getMonth()), 1);
            if ("Y".equals(monthDTO.getReplMeth()) && monthDate.isBefore(compareDate)) {
                checkSubstitude = true;
            }
        }
        map.put("isSubstitude", checkSubstitude ? "是" : "否");
        if ("Y".equals(bidKpifinDTO.getQc01())) {
            map.put("qc01", "是");
        } else if ("N".equals(bidKpifinDTO.getQc01())) {
            map.put("qc01", "否");
        }

        //計分項目-違約金金額
        //違約金比率
        BigDecimal liquidatedDamagesRate = null;
        if (bidProjectDTO.getOtherFee() != null && bidProjectDTO.getCtSumF() != null) {
            liquidatedDamagesRate = bidProjectDTO.getOtherFee().divide(bidProjectDTO.getCtSumF()).multiply(new BigDecimal(100));
        }
        if (liquidatedDamagesRate != null)
            map.put("liquidatedDamagesRate", numberFormat(liquidatedDamagesRate, "#,##0.000#") + "%");
        else
            map.put("liquidatedDamagesRate", "---");

        //計分項目-初驗、複驗及驗收過程之缺失與改善天數
        if (bidChkaptDTO != null) {
            LocalDate fcksdat = bidChkaptDTO.getFcksdat();
            LocalDate fckedat = bidChkaptDTO.getFckedat();

            LocalDate acksdat = bidChkaptDTO.getAcksdat();
            LocalDate ackedat = bidChkaptDTO.getAckedat();
            map.put("fcksdat", fcksdat != null ? parseLocalDateToYYYMMDD(fcksdat) : "(未填)");
            map.put("fckedat", fckedat != null ? parseLocalDateToYYYMMDD(fckedat) : "(未填)");
            map.put("acksdat", acksdat != null ? parseLocalDateToYYYMMDD(acksdat) : "(未填)");
            map.put("ackedat", fckedat != null ? parseLocalDateToYYYMMDD(ackedat) : "(未填)");
            Integer fchkcnt = 0;
            if (fcksdat != null && fckedat != null) {
                long fchkDiff = Duration.between(fcksdat, fckedat).toDays();
                fchkcnt = (int) fchkDiff;
                if (bidChkaptDTO.getFdays() != null && bidChkaptDTO.getFdays() > (int) fchkDiff) {
                    fchkcnt = bidChkaptDTO.getFdays();
                }
            } else {
                fchkcnt = 0;
            }

            Integer achkcnt = 0;
            if (acksdat != null && ackedat != null) {
                long fchkDiff = Duration.between(acksdat, ackedat).toDays();
                achkcnt = (int) fchkDiff;
                if (bidChkaptDTO.getAdays() != null && fchkcnt > bidChkaptDTO.getFdays()) {
                    achkcnt = bidChkaptDTO.getAdays();
                }
            } else {
                achkcnt = 0;
            }
            map.put("fchkcnt",Integer.toString(fchkcnt));
            map.put("achkcnt",Integer.toString(achkcnt));
            //累計改善天數
            int faChkCnt = fchkcnt + achkcnt;
            map.put("faChkCnt", Integer.toString(faChkCnt));

            map.put("rd01", this.getRd01RatioByProjectCtsum(bidProjectDTO.getCtSum()) + "%");

            if (bidProjectDTO.getOverFee() != null) {
                map.put("overFee", numberFormat(bidProjectDTO.getOverFee(), "#,##0.000#") + "千元");
            } else {
                map.put("overFee", "--");
            }
//            rd03rat
            String rd03rat = getRd03RateByProjectFinWay(bidProjectDTO.getFinWays() != null ? bidProjectDTO.getFinWays() : 0);
            map.put("rd03rat", rd03rat);

            //施工查核
            String[] messages = getGovCheckScoreMessage(bidGovCheckDTOs);//[0] 查核分數,[1]平均分數
            map.put("govCheckScore", messages[0]);
            map.put("govCheckAvgScore", messages[1]);

            //專任工程人員參與情形
            //constructionIndustry 營造業法
            if ("Y".equals(bidProjectDTO.getUntnd())) {
                map.put("constructionIndustry", "◎本案屬適用營造業法之工程");
                map.put("qd06Message", "◎專任工程人員於施工期間親自赴工地現場督察且留存督察紀錄，平均每月：" + bidKpifinDTO.getQd06() + "次(且需每月至少1次)");
            } else {
                map.put("constructionIndustry", "◎本案非屬適用營造業法之工程");
            }
            //最終核定工期
            map.put("lastApproveWorkDay", bidProjectDTO.getFinWays() != null ? bidProjectDTO.getFinWays().toString() : bidProjectDTO.getwDays());
            //適用營造業法
            if ("Y".equals(bidProjectDTO.getUntnd())) {
                boolean matchFlag = false;
                for (BidUnit10DTO ccut : ccuts) {
                    if (ccut.getName().matches(".*\u571f\u6728\u5305\u5de5.*")) {//%土木包工%
                        matchFlag = true;
                    }
                }
                if (matchFlag) {
                    map.put("constructionIndustrySecond", "◎本案屬土木包工業承攬之工程");
                } else {
                    map.put("constructionIndustrySecond", "◎本案屬適用營造業法之工程\n◎平均每月" + bidKpifinDTO.getQd07() + "次");
                }

            } else {
                map.put("constructionIndustrySecond", "◎本案非屬適用營造業法之工程");
            }

            //技術士參與
            //品質優良
            map.put("qe01a", empty2String(bidKpifinDTO.getQe01a(),"(未填"));
            String[] priceMessage = getBidProjectPrice(ccuts, bidPrjPriceDTOs);
            map.put("priceA", priceMessage[0]);
            map.put("priceB", priceMessage[1]);
            map.put("priceC", priceMessage[2]);

            //職業安全衛生與職業災害
            map.put("aWorkDay", bidProjectDTO.getAworkDay() != null && bidProjectDTO.getAworkDay() > 0 ? bidProjectDTO.getAworkDay() : "(不合理)");

        }

        String safeMessage = prjSafeMessage(bidPrjSafeDTOS, bidProjectDTO, bidChkaptDTO);
        map.put("safeMessage", safeMessage);
        String enviMessage = prjEnviMessage(bidPrjEnviDTOS, bidProjectDTO, bidChkaptDTO);
        map.put("enviMessage", enviMessage);

        String overSeeMessage = overSeeMessage(bidOversees);
        map.put("overSeeMessage", overSeeMessage);

        //處理下拉式選單
//        String qf2a = bidKpifinDTO.getQf02a();
        switch (bidKpifinDTO.getQf02a()) {
            case "1":
                map.put("qf02a", "1款");
                break;
            case "2":
                map.put("qf02a", "2款");
                break;
            case "3":
                map.put("qf02a", "3款");
                break;
        }
        switch (bidKpifinDTO.getQf02b()) {
            case "Y":
                map.put("qf02b", "是");
                break;
            case "N":
                map.put("qf02b", "否");
                break;
        }
        switch (bidKpifinDTO.getQf02c()) {
            case "Y":
                map.put("qf02c", "是");
                break;
            case "N":
                map.put("qf02c", "否");
                break;
        }
        map.put("qf02d", numberFormat(bidKpifinDTO.getQf02d(), "#,##0.00#") + "%");


        BigDecimal addPoint =
                empty2Zero(bidKpifinDTO.getKb01())
                        .add(empty2Zero(bidKpifinDTO.getKc01()))
                        .add(empty2Zero(bidKpifinDTO.getKd01()))
                        .add(empty2Zero(bidKpifinDTO.getKd04()))
                        .add(empty2Zero(bidKpifinDTO.getKd06()))
                        .add(empty2Zero(bidKpifinDTO.getKd09()))
                        .add(empty2Zero(bidKpifinDTO.getKe01()))
                        .add(empty2Zero(bidKpifinDTO.getKe02()))
                        .add(empty2Zero(bidKpifinDTO.getKe04()))
                        .add(empty2Zero(bidKpifinDTO.getKf01()));


        BigDecimal minusPoint =
                empty2Zero(bidKpifinDTO.getKb02())
                        .add(empty2Zero(bidKpifinDTO.getKc02()))
                        .add(empty2Zero(bidKpifinDTO.getKd02()))
                        .add(empty2Zero(bidKpifinDTO.getKd03()))
                        .add(empty2Zero(bidKpifinDTO.getKd05()))
                        .add(empty2Zero(bidKpifinDTO.getKd07()))
                        .add(empty2Zero(bidKpifinDTO.getKd08()))
                        .add(empty2Zero(bidKpifinDTO.getKe03()))
                        .add(empty2Zero(bidKpifinDTO.getKf02()));


        map.put("addPoint", numberFormat(addPoint, "#,##0.00#"));
        map.put("minusPoint", numberFormat(minusPoint, "#,##0.00#"));

        map.put("item", empty2String(bidKpifinDTO.getItem(),"(未填"));
        System.out.println("map");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        return list;
    }

    private String getRb01ByCtsum(BigDecimal decimalCtsum) {
        int ctsum = decimalCtsum.intValue();
        String rb01;
        if (ctsum > 5000000) {
            rb01 = "2.0";
        } else if (ctsum > 3000000) {
            rb01 = "1.8";
        } else if (ctsum > 1000000) {
            rb01 = "1.5";
        } else if (ctsum > 500000) {
            rb01 = "1.2";
        } else if (ctsum > 200000) {
            rb01 = "1.0";
        } else if (ctsum > 50000) {
            rb01 = "0.8";
        } else if (ctsum > 10000) {
            rb01 = "0.7";
        } else if (ctsum > 1000) {
            rb01 = "0.6";
        } else {
            rb01 = "0.0";
        }
        return rb01;
    }

    private String getRd01RatioByProjectCtsum(BigDecimal decimalCtsum) {
        int ctsum = decimalCtsum.intValue();
        String rd01;
        if (ctsum > 5000000) {
            rd01 = "0.6";
        } else if (ctsum > 3000000) {
            rd01 = "0.7";
        } else if (ctsum > 1000000) {
            rd01 = "0.8";
        } else if (ctsum > 500000) {
            rd01 = "1.0";
        } else if (ctsum > 200000) {
            rd01 = "1.2";
        } else if (ctsum > 50000) {
            rd01 = "1.5";
        } else if (ctsum > 10000) {
            rd01 = "1.8";
        } else {
            rd01 = "2.0";
        }
        return rd01;
    }

    private String getRd03RateByProjectFinWay(Integer finWay) {
        String rd03rat;
        if (finWay > 1825) {
            rd03rat = "0.5";
        } else if (finWay > 1460) {
            rd03rat = "0.6";
        } else if (finWay > 1095) {
            rd03rat = "0.7";
        } else if (finWay > 730) {
            rd03rat = "0.8";
        } else if (finWay > 365) {
            rd03rat = "0.9";
        } else {
            rd03rat = "1.0";
        }
        return rd03rat;
    }

    private String[] getGovCheckScoreMessage(List<BidGovCheckDTO> govChkDTOlist) {

        String[] returnMessages = new String[2]; //第一個放訊息，第二個放平均分數
        int checkCount = 0;
        BigDecimal scoreS = new BigDecimal(0);
//        Integer gradeCount = 0; //規格書有寫但不知道要幹嘛
        StringBuilder returnMessage = new StringBuilder();
        if (govChkDTOlist != null && govChkDTOlist.size() > 0) {

            BidGovCheckDTO govChkDTO;
            for (int i = 0; i < govChkDTOlist.size(); i++) {
                govChkDTO = govChkDTOlist.get(i);
                //govChkDTO.getScore() >40
                if (govChkDTO.getScore().compareTo(new BigDecimal(40)) >0 ) {
                    returnMessage.append(govChkDTO.getScore()).append("分(").append(parseLocalDateToYYYMMDD(govChkDTO.getCheckDate())).append(")");
                    checkCount++;
                    scoreS =scoreS.add(govChkDTO.getScore());
//                    if (govChkDTO.getScore() < 70) {
//                        gradeCount++;
//                    }
                } else {
                    returnMessage.append("不計分(").append(parseLocalDateToYYYMMDD(govChkDTO.getCheckDate())).append(")");
                }

                if (i < govChkDTOlist.size() - 1) {
                    returnMessage.append("\n");
                }
            }

        } else {
            returnMessage.append("(無)分( 年 月 日)");
        }
        returnMessages[0] = returnMessage.toString();

        if (checkCount > 0) {
            BigDecimal avgScore = scoreS.divide(new BigDecimal(checkCount));
            String avgScoreString = numberFormat(avgScore, "#,##0.000#") + "分";
            returnMessages[1] = avgScoreString;
        } else {
            returnMessages[1] = "(無)分";
        }
        return returnMessages;
    }

    private String[] getBidProjectPrice(List<BidUnit10DTO> ccutList, List<BidPrjPriceDTO> bidPrjPriceDTOs) {
        String[] priceMessages = new String[3];
        StringBuilder priceA = new StringBuilder();
        StringBuilder priceB = new StringBuilder();
        StringBuilder priceC = new StringBuilder();
        if (bidPrjPriceDTOs != null && !bidPrjPriceDTOs.isEmpty()) {
            for (BidPrjPriceDTO bidPrjPriceDTO : bidPrjPriceDTOs) {
                if (!ccutList.isEmpty() && bidPrjPriceDTO != null) {
                    for (BidUnit10DTO ccut : ccutList) {
                        if (bidPrjPriceDTO.getpUnit().equals(ccut.getCode())) {
                            switch (bidPrjPriceDTO.getGrade()) {
                                case "A":
                                    priceA.append(bidPrjPriceDTO.getYear()).append(" ");
                                    break;
                                case "B":
                                    priceB.append(bidPrjPriceDTO.getYear()).append(" ");
                                    break;
                                case "C":
                                    priceC.append(bidPrjPriceDTO.getYear()).append(" ");
                                    break;
                                default:
                                    break;
                            }
                        }

                    }
                }
            }
        }

        priceMessages[0] = priceA.length() > 0 ? priceA.toString() + "年" : "無";
        priceMessages[1] = priceB.length() > 0 ? priceA.toString() + "年" : "無";
        priceMessages[2] = priceC.length() > 0 ? priceA.toString() + "年" : "無";

        return priceMessages;
    }

    private String prjSafeMessage(List<BidPrjSafeDTO> bidPrjSafeDTOs, BidProjectDTO bidProjectDTO, BidChkaptDTO bidChkaptDTO) {
        int safeMaxDay = 0;
        int cntSafe = 0;
        int cWayed = 0;

        StringBuilder safeMessage = new StringBuilder();
        if (bidPrjSafeDTOs != null && !bidPrjSafeDTOs.isEmpty()) {
            for (BidPrjSafeDTO bidPrjSafeDTO : bidPrjSafeDTOs) {
                cntSafe++;
                safeMessage.append(parseLocalDateToYYYMMDD(bidPrjSafeDTO.getSdate())).append("(累積工期");
                String wayed = bidPrjSafeDTO.getWdayed() == null ? "--" : bidPrjSafeDTO.getWdayed().toString();
                BigDecimal dieNum = bidPrjSafeDTO.getDiedNum() != null ? bidPrjSafeDTO.getDiedNum() : new BigDecimal(0);
                BigDecimal hurtNum = bidPrjSafeDTO.getHurtNum() != null ? bidPrjSafeDTO.getHurtNum() : new BigDecimal(0);
                safeMessage.append(wayed).append("天)發生工安事件，死").append(dieNum.toString()).append("人，傷").append(hurtNum.toString()).append("人。\n");

                if (bidPrjSafeDTO.getWdayed() != null) {
                    if (cntSafe == 1) {
                        safeMessage.append("連續未發生天數").append(bidPrjSafeDTO.getWdayed().toString()).append("天\n");
                        safeMaxDay = bidPrjSafeDTO.getWdayed().intValue();
                    } else {
                        //(連續未發生天數 ${prjSafeDTO.wdayed} -(${cWayed} +1)= (${prjSafeDTO.wdayed - cWayed + 1}天)
                        safeMessage.append("(連續未發生天數")
                                .append(bidPrjSafeDTO.getWdayed().toString())//"${prjSafeDTO.wdayed}"
                                .append("-(")
                                .append(cWayed)
                                .append("+1)= (")
                                .append(bidPrjSafeDTO.getWdayed().subtract(new BigDecimal(cWayed).add(new BigDecimal(1))).toString())
                                .append("天)\n");
                    }
                } else {
                    safeMessage.append("(已累計工期未填，請先於D3補填)\n");
                }
                cWayed = bidPrjSafeDTO.getWdayed().intValue();
            }
        }

        Integer aWorkDay = bidProjectDTO.getAworkDay() !=null? bidProjectDTO.getAworkDay():0;


        //`完工實際履約日數：${prjAworkDay}天(連續未發生天數 ${prjAworkDay}-${lastWdayed + 1}=${prjAworkDay - lastWdayed - 1}天)
        safeMessage.append("完工實際履約日數：").append(aWorkDay).append("天(連續未發生天數")
                .append(aWorkDay).append("-").append(cWayed + 1).append("=").append(aWorkDay - cWayed - 1).append(")\n");


        if (bidChkaptDTO != null) {
            if (bidChkaptDTO.getAokDate() != null && bidChkaptDTO.getAokDate().isAfter(LocalDate.of(2018, 07, 01))) {
                if (bidProjectDTO.getAworkDay() != null && (bidProjectDTO.getAworkDay() - cWayed - 1 > safeMaxDay)) {
                    safeMaxDay = bidProjectDTO.getAworkDay() - cWayed - 1;
                }
                safeMessage.append("'(連續未發生最多天").append(safeMaxDay).append("天)\n");
            }
        }


        if (cntSafe == 0) {
            safeMessage.append("(本案系統中無工安記錄，如有漏填請先於D3補填)。");
        } else {
            safeMessage.append("(工安記錄如有漏填請先於D3補填)。");
        }

        return safeMessage.toString();
    }

    private String prjEnviMessage(List<BidPrjEnviDTO> BidPrjEnviDTOs, BidProjectDTO bidProjectDTO, BidChkaptDTO bidChkaptDTO) {
        int safeMaxDay = 0;
        int cntSafe = 0;
        int cWayed = 0;

        StringBuilder enviMessage = new StringBuilder();
        if (BidPrjEnviDTOs != null && !BidPrjEnviDTOs.isEmpty()) {
            for (BidPrjEnviDTO bidPrjEnviDTO : BidPrjEnviDTOs) {
                cntSafe++;
                enviMessage.append(parseLocalDateToYYYMMDD(bidPrjEnviDTO.getSdate())).append("(累積工期");
                String wayed = bidPrjEnviDTO.getWdayed() == null ? "--" : bidPrjEnviDTO.getWdayed().toString();
                String prmNo = bidPrjEnviDTO.getPrmNo() == null ? "" : bidPrjEnviDTO.getPrmNo();
                enviMessage.append(wayed).append("天)").append(prmNo).append("。\n");

                if (bidPrjEnviDTO.getWdayed() != null) {
                    if (cntSafe == 1) {
                        enviMessage.append("連續未發生天數").append(bidPrjEnviDTO.getWdayed().toString()).append("天\n");
                        safeMaxDay = bidPrjEnviDTO.getWdayed().intValue();
                    } else {
                        //(連續未發生天數 ${prjSafeDTO.wdayed} -(${cWayed} +1)= (${prjSafeDTO.wdayed - cWayed + 1}天)
                        enviMessage.append("(連續未發生天數")
                                .append(bidPrjEnviDTO.getWdayed().toString())//"${prjSafeDTO.wdayed}"
                                .append("-(")
                                .append(cWayed)
                                .append("+1)= (")
                                .append((bidPrjEnviDTO.getWdayed() - cWayed +1))
                                .append("天)\n");
                    }
                } else {
                    enviMessage.append("(已累計工期未填，請先於D3補填)\n");
                }
                cWayed = bidPrjEnviDTO.getWdayed().intValue();
            }
        }

        Integer aWorkDay = bidProjectDTO.getAworkDay() !=null? bidProjectDTO.getAworkDay():0;
        //`完工實際履約日數：${prjAworkDay}天(連續未發生天數 ${prjAworkDay}-${lastWdayed + 1}=${prjAworkDay - lastWdayed - 1}天)
        enviMessage.append("完工實際履約日數：").append(aWorkDay).append("天(連續未發生天數")
                .append(aWorkDay).append("-").append(cWayed + 1).append("=").append(aWorkDay - cWayed - 1).append(")\n");

        if (bidChkaptDTO != null) {
            if (bidChkaptDTO.getAokDate() != null && bidChkaptDTO.getAokDate().isAfter(LocalDate.of(2018, 07, 01))) {
                if (bidProjectDTO.getAworkDay() != null && (bidProjectDTO.getAworkDay() - cWayed - 1 > safeMaxDay)) {
                    safeMaxDay = bidProjectDTO.getAworkDay() - cWayed - 1;
                }
                enviMessage.append("'(連續未發生最多天").append(safeMaxDay).append("天\n");
            }
        }


        if (cntSafe == 0) {
            enviMessage.append("(本案系統中無環保裁處記錄，如有漏填請先於D3補填)。");
        } else {
            enviMessage.append("(環保裁處記錄如有漏填請先於D3補填)。");
        }

        return enviMessage.toString();
    }

    private String overSeeMessage(List<BidOverseeDTO> bidOverseeDTOS) {
        StringBuilder overSeeMessage = new StringBuilder();
        if (bidOverseeDTOS != null && !bidOverseeDTOS.isEmpty()) {
            for (BidOverseeDTO bidOverseeDTO : bidOverseeDTOS) {
                overSeeMessage.append(parseLocalDateToYYYMMDD(bidOverseeDTO.getSeqDate())).append(" ");
            }
        }

        if (overSeeMessage.length() > 0) {
            return "全民督工被通報日期：" + overSeeMessage.toString();
        } else {
            return "(本案無全民督工被通報記錄)";
        }
    }
}
