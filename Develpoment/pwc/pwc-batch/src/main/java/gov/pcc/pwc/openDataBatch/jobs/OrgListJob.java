package gov.pcc.pwc.openDataBatch.jobs;

import gov.pcc.pwc.common.SpringContextJobUtil;
import gov.pcc.pwc.domain.AdmAgent;
import gov.pcc.pwc.domain.AdmBatchRecord;
import gov.pcc.pwc.domain.AdmImportRecord;
import gov.pcc.pwc.repository.AdmAgentRepository;
import gov.pcc.pwc.repository.AdmBatchRecordRepository;
import gov.pcc.pwc.repository.AdmImportRecordRepository;
import gov.pcc.pwc.service.OpenDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class OrgListJob extends QuartzJobBean {

    OpenDataService openDataService;

    AdmAgentRepository admAgentRepository;

    AdmBatchRecordRepository admBatchRecordRepository;

    AdmImportRecordRepository admImportRecordRepository;

    public void doSchedule() throws IOException {
        System.out.println("-----------OrgListJob");
        AdmBatchRecord admBatchRecord = new AdmBatchRecord();
        admBatchRecord.setBatchServiceNo("pwbBatch001");
        admBatchRecord.setBatchServiceName("機關檔批次作業");
        admBatchRecord.setBatchServiceCategory("pwb");
        admBatchRecord.setTriggerTime(Instant.now());
        admBatchRecord.setFinishTime(Instant.now());
        admBatchRecord.setResult("N");
        admBatchRecordRepository = (AdmBatchRecordRepository) SpringContextJobUtil.getBean("admBatchRecordRepository");
        admBatchRecordRepository.save(admBatchRecord);

        openDataService = (OpenDataService) SpringContextJobUtil.getBean("openDataService");
        List<AdmAgent> admAgents = openDataService.parseOrgListData();
        BigDecimal originalDataCount = BigDecimal.valueOf(admAgents.size());
        AtomicInteger insertSuccessDataCount = new AtomicInteger();

        admAgentRepository = (AdmAgentRepository) SpringContextJobUtil.getBean("admAgentRepository");
        System.out.println("開始orglist批次轉入");
        admAgents.forEach(item -> {
//            System.out.println(item);
            admAgentRepository.save(item);
            insertSuccessDataCount.getAndIncrement();
        });
        admBatchRecord.setFinishTime(Instant.now());
        admBatchRecord.setResult("Y");
        AdmBatchRecord record = admBatchRecordRepository.save(admBatchRecord);

        AdmImportRecord admImportRecord = new AdmImportRecord();
        admImportRecord.setId(record.getId());
        admImportRecord.setProcessDate(LocalDate.now());
        admImportRecord.setProcessStartTime(record.getTriggerTime());
        admImportRecord.setProcessEndTime(record.getFinishTime());
        admImportRecord.setProcessFileName("orglist");
        admImportRecord.setOriginalDataCount(originalDataCount);
        admImportRecord.setProcessedDataCount(BigDecimal.ZERO);
        admImportRecord.setUnprocessedDataCount(BigDecimal.ZERO);
        admImportRecord.setInsertSuccessDataCount(BigDecimal.valueOf(insertSuccessDataCount.get()));
        admImportRecord.setInsertFailDataCount(originalDataCount.subtract(admImportRecord.getInsertSuccessDataCount()));
        admImportRecord.setUpdateSuccessDataCount(BigDecimal.ZERO);
        admImportRecord.setUpdateFailDataCount(BigDecimal.ZERO);
        admImportRecord.setDeleteSuccessDataCount(BigDecimal.ZERO);
        admImportRecord.setDeleteFailDataCount(BigDecimal.ZERO);
        admImportRecord.setCaseSuccessDataCount(BigDecimal.ZERO);
        admImportRecord.setCaseFailDataCount(BigDecimal.ZERO);
        admImportRecord.setInterfacingSuccessDataCount(BigDecimal.ZERO);
        admImportRecord.setInterfacingFailDataCount(BigDecimal.ZERO);
        admImportRecordRepository = (AdmImportRecordRepository) SpringContextJobUtil.getBean("admImportRecordRepository");
        admImportRecordRepository.save(admImportRecord);
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            doSchedule();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
