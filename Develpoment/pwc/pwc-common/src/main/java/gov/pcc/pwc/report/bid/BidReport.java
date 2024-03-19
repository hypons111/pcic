/*
 * Copyright (c) 2010-2020 IIS.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package gov.pcc.pwc.report.bid;

import gov.pcc.pwc.report.ReportDefinition;
import gov.pcc.pwc.report.ReportFormat;

/**
 * 報表資訊清單
 */
public enum BidReport implements ReportDefinition {

    BidAae001("基本資料表", ReportFormat.PDF), //
    BidAae001s01("基本資料表(監督付款資料)", ReportFormat.PDF), //
    BidAae001s02("基本資料表(分包廠商資料)", ReportFormat.PDF), //
    BidAae001s03("基本資料表(未開工原因資料)", ReportFormat.PDF), //
    BidAae001s04("基本資料表(變更設計情形資料)", ReportFormat.PDF), //
    BidAae001s05("基本資料表(停工狀況資料)", ReportFormat.PDF), //
    BidAae002("工程開工報告表", ReportFormat.PDF), //
    BidAae009A("歷次請撥付款一覽表", ReportFormat.PDF), //
    BidAae009B("分批(期)付款表", ReportFormat.PDF), //
    BidAae009C("物價指數調整試算表", ReportFormat.PDF), //
    BidAaa003("經費變更或期程一覽表", ReportFormat.PDF),
    BidAaa004Stop("工程停工報告表", ReportFormat.PDF),
    BidAaa004Recovery("工程復工報告表", ReportFormat.PDF),
    BidAab001("個月分配表",ReportFormat.PDF),
    BidAab001Test("個月分配表",ReportFormat.PDF),
    BidAab001Test1("個月分配子表",ReportFormat.PDF),
    BidAab001Test2("個月分配子表2",ReportFormat.PDF),
    BidAae006("工安事件表",ReportFormat.PDF),
    BidAae023("復建工程發包執行表",ReportFormat.PDF),
    BidAae007("環保裁罰表",ReportFormat.PDF),
    BidAae023s01("復建工程發包執行表(行政院核定復建項目)",ReportFormat.PDF),
    BidAae023s02("復建工程發包執行表(歷次撥款狀況)",ReportFormat.PDF),
    BidAae023s03("復建工程發包執行表(各月份累積執行狀況)",ReportFormat.PDF),
    BidAae021("外勞核定表",ReportFormat.PDF),
    BidAae022("工地型預拌混凝土通報表",ReportFormat.PDF),
    BidAae013("重大公共工程開工要件機關應辦事項檢核表",ReportFormat.PDF),
    BidAae030A("人力需求表(技術工)",ReportFormat.PDF),
    BidAae030B("人力需求表(技術士)",ReportFormat.PDF),
    BidAae003("送機關備查之分包情形廠商表",ReportFormat.PDF),
    BidAae034D("工程施工查核改善對策及結果審查表",ReportFormat.PDF),
    BidAae034Ds01("工程施工查核改善對策及結果審查表(缺失項目)",ReportFormat.PDF),
    BidAae034Ds02("工程施工查核改善對策及結果審查表(規劃設計問題及建議)",ReportFormat.PDF),
    BidAae034Ds03("工程施工查核改善對策及結果審查表(其他建議)",ReportFormat.PDF),
    BidAae020s01a("開口合約派工表(基本資料)",ReportFormat.PDF),
    BidAae020s01b("開口合約派工表(變更設計)",ReportFormat.PDF),
    BidAae020s01c("開口合約派工表(開口合約派工)",ReportFormat.PDF),
    BidAae020s02("開口合約派工表(施工通知單)",ReportFormat.PDF),
    BidAad008("公共工程施工廠商履約情形計分表",ReportFormat.PDF),
    ;

    private String reportCode;

    private String reportName;

    private ReportFormat reportFormat;

    BidReport(String reportName, ReportFormat reportFormat) {
        this.reportCode = name();
        this.reportName = reportName;
        this.reportFormat = reportFormat;
    }

    public String getReportCode() {
        return this.reportCode;
    }

    public String getReportName() {
        return this.reportName;
    }

    public ReportFormat getReportFormat() {
        return this.reportFormat;
    }

}