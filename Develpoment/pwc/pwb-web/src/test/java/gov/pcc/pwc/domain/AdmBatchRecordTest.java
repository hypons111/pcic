package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmBatchRecordTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmBatchRecord.class);
        AdmBatchRecord admBatchRecord1 = new AdmBatchRecord();
        admBatchRecord1.setId(1L);
        AdmBatchRecord admBatchRecord2 = new AdmBatchRecord();
        admBatchRecord2.setId(admBatchRecord1.getId());
        assertThat(admBatchRecord1).isEqualTo(admBatchRecord2);
        admBatchRecord2.setId(2L);
        assertThat(admBatchRecord1).isNotEqualTo(admBatchRecord2);
        admBatchRecord1.setId(null);
        assertThat(admBatchRecord1).isNotEqualTo(admBatchRecord2);
    }
}
