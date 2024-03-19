package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmCertRecordTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmCertRecord.class);
        AdmCertRecord admCertRecord1 = new AdmCertRecord();
        admCertRecord1.setId(1L);
        AdmCertRecord admCertRecord2 = new AdmCertRecord();
        admCertRecord2.setId(admCertRecord1.getId());
        assertThat(admCertRecord1).isEqualTo(admCertRecord2);
        admCertRecord2.setId(2L);
        assertThat(admCertRecord1).isNotEqualTo(admCertRecord2);
        admCertRecord1.setId(null);
        assertThat(admCertRecord1).isNotEqualTo(admCertRecord2);
    }
}
