package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmImportRecordTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmImportRecord.class);
        AdmImportRecord admImportRecord1 = new AdmImportRecord();
        admImportRecord1.setId(1L);
        AdmImportRecord admImportRecord2 = new AdmImportRecord();
        admImportRecord2.setId(admImportRecord1.getId());
        assertThat(admImportRecord1).isEqualTo(admImportRecord2);
        admImportRecord2.setId(2L);
        assertThat(admImportRecord1).isNotEqualTo(admImportRecord2);
        admImportRecord1.setId(null);
        assertThat(admImportRecord1).isNotEqualTo(admImportRecord2);
    }
}
