package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RecTyphoonDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecTyphoonDTO.class);
        RecTyphoonDTO recTyphoonDTO1 = new RecTyphoonDTO();
        recTyphoonDTO1.setTyphoonNo("id1");
        RecTyphoonDTO recTyphoonDTO2 = new RecTyphoonDTO();
        assertThat(recTyphoonDTO1).isNotEqualTo(recTyphoonDTO2);
        recTyphoonDTO2.setTyphoonNo(recTyphoonDTO1.getTyphoonNo());
        assertThat(recTyphoonDTO1).isEqualTo(recTyphoonDTO2);
        recTyphoonDTO2.setTyphoonNo("id2");
        assertThat(recTyphoonDTO1).isNotEqualTo(recTyphoonDTO2);
        recTyphoonDTO1.setTyphoonNo(null);
        assertThat(recTyphoonDTO1).isNotEqualTo(recTyphoonDTO2);
    }
}
