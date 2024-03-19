/*
 * Copyright (c) 2010-2020 IIS.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package gov.pcc.pwc.report;

/**
 * 報表資訊清單
 */
public interface ReportDefinition {

    String getReportCode();

    String getReportName();

    ReportFormat getReportFormat();

}