package gov.pcc.pwc.config;

import gov.pcc.pwc.openDataBatch.jobs.MailListJob;
import gov.pcc.pwc.openDataBatch.jobs.OrgListJob;
import gov.pcc.pwc.openDataBatch.jobs.TestPrintJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    // 機關檔下載作業-Job設定
    @Bean
    public JobDetail orgListJob() {
        return JobBuilder
                .newJob(OrgListJob.class).withIdentity("OrgListJob")
                .storeDurably().build();
    }
    // 機關檔下載作業-Trigger設定
    @Bean
    public Trigger orgListJobTrigger(JobDetail orgListJob) {

        return TriggerBuilder.newTrigger().forJob(orgListJob)
                .withIdentity("OrgListJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 2 * * ?"))
                .build();
    }

    // 寄送MAIL作業-Job設定
    @Bean
    public JobDetail mailListJob() {

        return JobBuilder
                .newJob(MailListJob.class).withIdentity("MailListJob")
                .storeDurably().build();
    }
    // 寄送MAIL作業-Trigger設定
    @Bean
    public Trigger mailListJobTrigger(JobDetail mailListJob) {

        return TriggerBuilder.newTrigger().forJob(mailListJob)
                .withIdentity("MailListJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("* 0/5 * * * ?"))
                .build();
    }

    // 範例用-Job設定
    @Bean
    public JobDetail testPrintJob() {
        return JobBuilder
                .newJob(TestPrintJob.class).withIdentity("TestPrintJob")
                .storeDurably().build();
    }
    // 範例用-Trigger設定
    @Bean
    public Trigger testPrintJobTrigger(JobDetail testPrintJob) {

        return TriggerBuilder.newTrigger().forJob(testPrintJob)
                .withIdentity("TestPrintJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
    }

}
