package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.AdmNumber;
import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmNumberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmNumber.class);
        AdmNumber admNumber1 = new AdmNumber();
        admNumber1.setId(1L);
        AdmNumber admNumber2 = new AdmNumber();
        admNumber2.setId(admNumber1.getId());
        assertThat(admNumber1).isEqualTo(admNumber2);
        admNumber2.setId(2L);
        assertThat(admNumber1).isNotEqualTo(admNumber2);
        admNumber1.setId(null);
        assertThat(admNumber1).isNotEqualTo(admNumber2);
    }
}
