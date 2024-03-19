package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmFunctionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmFunction.class);
        AdmFunction admFunction1 = new AdmFunction();
        admFunction1.setId(1L);
        AdmFunction admFunction2 = new AdmFunction();
        admFunction2.setId(admFunction1.getId());
        assertThat(admFunction1).isEqualTo(admFunction2);
        admFunction2.setId(2L);
        assertThat(admFunction1).isNotEqualTo(admFunction2);
        admFunction1.setId(null);
        assertThat(admFunction1).isNotEqualTo(admFunction2);
    }
}
