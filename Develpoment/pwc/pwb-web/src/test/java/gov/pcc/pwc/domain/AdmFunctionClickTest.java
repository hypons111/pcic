package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmFunctionClickTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmFunctionClick.class);
        AdmFunctionClick admFunctionClick1 = new AdmFunctionClick();
        admFunctionClick1.setId(1L);
        AdmFunctionClick admFunctionClick2 = new AdmFunctionClick();
        admFunctionClick2.setId(admFunctionClick1.getId());
        assertThat(admFunctionClick1).isEqualTo(admFunctionClick2);
        admFunctionClick2.setId(2L);
        assertThat(admFunctionClick1).isNotEqualTo(admFunctionClick2);
        admFunctionClick1.setId(null);
        assertThat(admFunctionClick1).isNotEqualTo(admFunctionClick2);
    }
}
