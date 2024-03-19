package gov.pcc.pwc.common.web;

import org.springframework.http.MediaType;

/**
 * 報表用的 MediaType。
 */
public class ReportMediaType extends MediaType {

    public ReportMediaType(String type, String subtype) {
        super(type, subtype);
    }

    /**
     * Public constant media type for {@code application/pdf}.
     */
    public static final MediaType APPLICATION_PDF;

    /**
     * A String equivalent of {@link ReportMediaType#APPLICATION_PDF}.
     */
    public static final String APPLICATION_PDF_VALUE = "application/pdf";

    /**
     * Public constant media type for {@code application/vnd.ms-excel}.
     */
    public static final MediaType APPLICATION_VND_MS_EXCEL;

    /**
     * A String equivalent of {@link ReportMediaType#APPLICATION_VND_MS_EXCEL}.
     */
    public static final String APPLICATION_VND_MS_EXCEL_VALUE = "application/vnd.ms-excel";

    /**
     * Public constant media type for {@code application/vnd.ms-excel}, an alias to
     * {@link #APPLICATION_VND_MS_EXCEL}.
     */
    public static final MediaType APPLICATION_XLS;

    /**
     * A String equivalent of {@link ReportMediaType#APPLICATION_XLS}, an alias to
     * {@link #APPLICATION_VND_MS_EXCEL_VALUE}.
     */
    public static final String APPLICATION_XLS_VALUE = APPLICATION_VND_MS_EXCEL_VALUE;

    /**
     * Public constant media type for {@code text/csv}.
     */
    public static final MediaType TEXT_CSV;

    /**
     * A String equivalent of {@link ReportMediaType#TEXT_CSV}.
     */
    public static final String TEXT_CSV_VALUE = "text/csv";

    /**
     * Public constant media type for {@value #APPLICATION_XLSX_VALUE}.
     */
    public static final MediaType APPLICATION_XLSX;

    /**
     * A String equivalent of {@link ReportMediaType#APPLICATION_XLSX}.
     */
    public static final String APPLICATION_XLSX_VALUE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    /**
     * Public constant media type for {@value #APPLICATION_ODS_VALUE}.
     */
    public static final MediaType APPLICATION_ODS;

    /**
     * A String equivalent of {@link ReportMediaType#APPLICATION_ODS}.
     */
    public static final String APPLICATION_ODS_VALUE = "application/vnd.oasis.opendocument.spreadsheet";

    /**
     * Public constant media type for {@value #APPLICATION_DOCX_VALUE}.
     */
    public static final MediaType APPLICATION_DOCX;

    /**
     * A String equivalent of {@link ReportMediaType#APPLICATION_DOCX}.
     */
    public static final String APPLICATION_DOCX_VALUE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    /**
     * Public constant media type for {@value #APPLICATION_ODT_VALUE}.
     */
    public static final MediaType APPLICATION_ODT;

    /**
     * A String equivalent of {@link ReportMediaType#APPLICATION_ODT}.
     */
    public static final String APPLICATION_ODT_VALUE = "application/vnd.oasis.opendocument.text";

    static {
        APPLICATION_PDF = MediaType.valueOf(APPLICATION_PDF_VALUE);
        APPLICATION_XLS = MediaType.valueOf(APPLICATION_XLS_VALUE);
        APPLICATION_VND_MS_EXCEL = MediaType.valueOf(APPLICATION_VND_MS_EXCEL_VALUE);
        TEXT_CSV = MediaType.valueOf(TEXT_CSV_VALUE);
        APPLICATION_XLSX = MediaType.valueOf(APPLICATION_XLSX_VALUE);
        APPLICATION_ODS = MediaType.valueOf(APPLICATION_ODS_VALUE);
        APPLICATION_DOCX = MediaType.valueOf(APPLICATION_DOCX_VALUE);
        APPLICATION_ODT = MediaType.valueOf(APPLICATION_ODT_VALUE);
    }
}
