package gov.pcc.pwc.service;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.PccPdfExporter;
import gov.pcc.pwc.report.ReportDefinition;
import gov.pcc.pwc.report.ReportFormat;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.ByteArrayOutputStream;

/**
 * Service for generate Report.
 */
public interface ReportService {

    default byte[] printReport(AbstractReport report) {
        try {
            ReportDefinition definition = report.getReportDefinition();
            ByteArrayOutputStream fos = new ByteArrayOutputStream();
            JasperPrint jasperPrint = generateReport(report);
            if (ReportFormat.PDF == definition.getReportFormat()) {
                final PccPdfExporter pdf = new PccPdfExporter();
                pdf.setExporterInput(new SimpleExporterInput(jasperPrint));
                pdf.setExporterOutput(new SimpleOutputStreamExporterOutput(fos));
                pdf.exportReport();
            }
            return fos.toByteArray();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }

    JasperPrint generateReport(AbstractReport report) throws JRException;

}