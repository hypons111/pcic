package gov.pcc.pwc.openDataBatch.jobs;

import gov.pcc.pwc.common.SpringContextJobUtil;
import gov.pcc.pwc.repository.AdmMailSendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SendMailDataJob {

//    @Autowired
    MailListJob mailListJob;

//    admMailSendRepository = (AdmMailSendRepository) SpringContextJobUtil.getBean("admMailSendRepository");
    //every 20 min run every day
    @Scheduled(cron = "0 0/30 * * * ?")
    public void doProcess() throws Exception {
        mailListJob = (MailListJob) SpringContextJobUtil.getBean("mailListJob");
        System.out.println("-----------doProcess ");
        mailListJob.doSchedule();
        System.out.println("-----------Down ");
    }
}
