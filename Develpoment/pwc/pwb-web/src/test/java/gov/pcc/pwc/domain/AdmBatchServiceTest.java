package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmBatchServiceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmBatchService.class);
        AdmBatchService admBatchService1 = new AdmBatchService();
        admBatchService1.setBatchServiceNo("id1");
        AdmBatchService admBatchService2 = new AdmBatchService();
        admBatchService2.setBatchServiceNo(admBatchService1.getBatchServiceNo());
        assertThat(admBatchService1).isEqualTo(admBatchService2);
        admBatchService2.setBatchServiceNo("id2");
        assertThat(admBatchService1).isNotEqualTo(admBatchService2);
        admBatchService1.setBatchServiceNo(null);
        assertThat(admBatchService1).isNotEqualTo(admBatchService2);
    }
}
