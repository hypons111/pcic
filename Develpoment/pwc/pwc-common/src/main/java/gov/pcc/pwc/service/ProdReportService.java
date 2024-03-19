package gov.pcc.pwc.service;

import gov.pcc.pwc.report.AbstractReport;
import net.sf.jasperreports.engine.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

/**
 * Service for generate Report.
 */
@Service
@Profile("prod")
public class ProdReportService implements ReportService {

    public JasperPrint generateReport(AbstractReport report) throws JRException {
        InputStream jasperFile = report.getJsperFile();
        Map<String, Object> jasperParams = report.toJasperParams();
        JRDataSource jrDataSource = report.toDataSource();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, jasperParams, jrDataSource);
        return jasperPrint;
    }

}