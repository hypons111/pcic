package gov.pcc.pwc.report;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.collections4.CollectionUtils;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class AbstractReport extends ReportFormatter {

    protected ReportDefinition reportDefinition;

    protected List<?> defaultDataList = Collections.emptyList();

    public AbstractReport(final ReportDefinition reportDefinition) {
        this.reportDefinition = reportDefinition;
    }

    public ReportDefinition getReportDefinition() {
        return this.reportDefinition;
    }

    public void setDefaultDataList(List<?> defaultDataList) {
        this.defaultDataList = defaultDataList;
    }

    public Map<String, Object> toJasperParams() {
        return Collections.emptyMap();
    }

    protected List<Map<String, Object>> convertToDataList() {
        if (CollectionUtils.isNotEmpty(defaultDataList)) {
            return converBeanListToMapList(defaultDataList);
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    public JRDataSource toDataSource() {
        final List<Map<String, Object>> maps = this.convertToDataList();
        if (CollectionUtils.isNotEmpty(maps)) {
            return new JRBeanCollectionDataSource(maps);
        } else {
            return new JREmptyDataSource();
        }
    }

    public InputStream getJrxmlFile() {
        InputStream jrxmlFile = null;
        String reportCode = reportDefinition.getReportCode();
        String jrxmlFilePath = "/ireport/jrxml/" + reportCode + ".jrxml";
        jrxmlFile = this.getClass().getResourceAsStream(jrxmlFilePath);
        return jrxmlFile;
    }

    public InputStream getJsperFile() {
        InputStream jasperFile = null;
        String reportCode = reportDefinition.getReportCode();
        String jsperFilePath = "/ireport/jasper/" + reportCode + ".jasper";
        jasperFile = this.getClass().getResourceAsStream(jsperFilePath);
        return jasperFile;
    }

    public List<Map<String, Object>> converBeanListToMapList(List<?> beanList) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for(Object source: beanList){
            Map<String, Object> map = transBean2Map(source);
            mapList.add(map);
        }
        return mapList;
    }

    public Map<String, Object> transBean2Map(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

    public String getFileName() {
        ReportFormat reportFormat = reportDefinition.getReportFormat();
        String fileName = reportDefinition.getReportCode();
        switch (reportFormat) {
            case PDF:
            default:
                fileName = fileName + ".pdf";
                break;
        }
        return fileName;
    }

    public JasperReport getJasperReport() {
        try {
            JasperReport jasperReport = null;
            InputStream inputStream = this.getJsperFile();
            if (inputStream != null) {
                jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            } else {
                inputStream = this.getJrxmlFile();
                jasperReport = JasperCompileManager.compileReport(inputStream);
            }
            return jasperReport;
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
