package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.MrpProjectPurchaseRepositoryCustom;
import gov.pcc.pwc.repository.custom.MrpSynonymRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.MrpSynonymDTO;
import gov.pcc.pwc.service.dto.MrpSysCodeDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.odsExporter.OdsExportService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MrpProjectPurchaseRepositoryImpl implements MrpProjectPurchaseRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public MrpProjectPurchaseRepositoryImpl(SqlExecutor sqlExecutor, OdsExportService odsExportService, SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }

}
