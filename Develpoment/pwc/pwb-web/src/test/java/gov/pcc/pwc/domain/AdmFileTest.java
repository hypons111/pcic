package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmFileTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmFile.class);
        AdmFile admFile1 = new AdmFile();
        admFile1.setId(1L);
        AdmFile admFile2 = new AdmFile();
        admFile2.setId(admFile1.getId());
        assertThat(admFile1).isEqualTo(admFile2);
        admFile2.setId(2L);
        assertThat(admFile1).isNotEqualTo(admFile2);
        admFile1.setId(null);
        assertThat(admFile1).isNotEqualTo(admFile2);
    }
}
