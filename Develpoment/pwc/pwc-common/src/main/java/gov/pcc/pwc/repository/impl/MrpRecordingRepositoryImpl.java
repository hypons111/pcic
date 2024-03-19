package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.MrpRecordingRepositoryCustom;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.odsExporter.OdsExportService;

public class MrpRecordingRepositoryImpl implements MrpRecordingRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public MrpRecordingRepositoryImpl(SqlExecutor sqlExecutor, OdsExportService odsExportService, SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }
}
