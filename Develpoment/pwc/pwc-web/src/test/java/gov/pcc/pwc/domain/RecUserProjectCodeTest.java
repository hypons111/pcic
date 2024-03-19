package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RecUserProjectCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecUserProjectCode.class);
        RecUserProjectCode recUserProjectCode1 = new RecUserProjectCode();
        recUserProjectCode1.setUserId("id1");
        RecUserProjectCode recUserProjectCode2 = new RecUserProjectCode();
        recUserProjectCode2.setUserId(recUserProjectCode1.getUserId());
        assertThat(recUserProjectCode1).isEqualTo(recUserProjectCode2);
        recUserProjectCode2.setUserId("id2");
        assertThat(recUserProjectCode1).isNotEqualTo(recUserProjectCode2);
        recUserProjectCode1.setUserId(null);
        assertThat(recUserProjectCode1).isNotEqualTo(recUserProjectCode2);
    }
}
