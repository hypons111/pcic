package gov.pcc.pwc.openDataBatch.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class TestPrintJob extends QuartzJobBean {

    public void doPrint() throws IOException {
        System.out.println("-----------TestPrintJob");
        System.out.println("-----------範例，每10秒執行一次");
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            doPrint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
