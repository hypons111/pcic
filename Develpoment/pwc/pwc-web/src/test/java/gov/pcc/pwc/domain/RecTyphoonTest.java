package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RecTyphoonTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecTyphoon.class);
        RecTyphoon recTyphoon1 = new RecTyphoon();
        recTyphoon1.setTyphoonNo("id1");
        RecTyphoon recTyphoon2 = new RecTyphoon();
        recTyphoon2.setTyphoonNo(recTyphoon1.getTyphoonNo());
        assertThat(recTyphoon1).isEqualTo(recTyphoon2);
        recTyphoon2.setTyphoonNo("id2");
        assertThat(recTyphoon1).isNotEqualTo(recTyphoon2);
        recTyphoon1.setTyphoonNo(null);
        assertThat(recTyphoon1).isNotEqualTo(recTyphoon2);
    }
}
