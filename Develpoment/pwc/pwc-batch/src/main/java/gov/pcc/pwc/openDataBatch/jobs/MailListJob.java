package gov.pcc.pwc.openDataBatch.jobs;

import gov.pcc.pwc.common.SpringContextJobUtil;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.repository.AdmMailSendRepository;
import gov.pcc.pwc.service.SendMailDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Component
public class MailListJob extends QuartzJobBean {

    SendMailDataService sendMailDataService;

    AdmMailSendRepository admMailSendRepository;

    public void doSchedule() throws Exception {
        System.out.println("-----------MailListJob Start");
        admMailSendRepository = (AdmMailSendRepository) SpringContextJobUtil.getBean("admMailSendRepository");
        sendMailDataService = (SendMailDataService) SpringContextJobUtil.getBean("sendMailDataService");
        AdmMailSend admMailSend = new AdmMailSend();
//        admMailSend.setMailType("PWC0303");
//import gov.pcc.pwc.domain.AdmMailSend;
//import gov.pcc.pwc.repository.AdmMailSendRepository;
//import gov.pcc.pwc.service.SendMailDataService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class MailListJob {
//
//    @Autowired
//    SendMailDataService sendMailDataService;
//
//    @Autowired
//    AdmMailSendRepository admMailSendRepository;


//    public void doSchedule() throws Exception {
        System.out.println("-----------MailListJob Start");
//        AdmMailSend admMailSend = new AdmMailSend();
        admMailSend.setMailType("PWC0303");
        List<AdmMailSend> AdmMailSendList = admMailSendRepository.findAllByCriteria(admMailSend);
        System.out.println("-AdmMailSendList = "+ AdmMailSendList.size());
        //處理寄信
        AdmMailSendList.forEach(item -> {
            System.out.println("AdmMailitem = "+item);
            try {
                sendMailDataService.sendMailListData(item);
                item.setStatus("S");//修改狀態為 S 已發送
                admMailSendRepository.save(item);
            } catch (IOException e) {
                item.setStatus("E");//修改狀態為 E 發送失敗
                item.setUpdateTime(Instant.now());
                admMailSendRepository.save(item);
            } catch (Exception e) {
                item.setStatus("E");//修改狀態為 E 發送失敗
                item.setUpdateTime(Instant.now());
                admMailSendRepository.save(item);
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            doSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
