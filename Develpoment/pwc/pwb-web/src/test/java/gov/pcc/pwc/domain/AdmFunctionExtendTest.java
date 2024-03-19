package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmFunctionExtendTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmFunctionExtend.class);
        AdmFunctionExtend admFunctionExtend1 = new AdmFunctionExtend();
        admFunctionExtend1.setId(1L);
        AdmFunctionExtend admFunctionExtend2 = new AdmFunctionExtend();
        admFunctionExtend2.setId(admFunctionExtend1.getId());
        assertThat(admFunctionExtend1).isEqualTo(admFunctionExtend2);
        admFunctionExtend2.setId(2L);
        assertThat(admFunctionExtend1).isNotEqualTo(admFunctionExtend2);
        admFunctionExtend1.setId(null);
        assertThat(admFunctionExtend1).isNotEqualTo(admFunctionExtend2);
    }
}
