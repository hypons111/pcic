package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RecUserProjectCodeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecUserProjectCodeDTO.class);
        RecUserProjectCodeDTO recUserProjectCodeDTO1 = new RecUserProjectCodeDTO();
        recUserProjectCodeDTO1.setUserId("id1");
        RecUserProjectCodeDTO recUserProjectCodeDTO2 = new RecUserProjectCodeDTO();
        assertThat(recUserProjectCodeDTO1).isNotEqualTo(recUserProjectCodeDTO2);
        recUserProjectCodeDTO2.setUserId(recUserProjectCodeDTO1.getUserId());
        assertThat(recUserProjectCodeDTO1).isEqualTo(recUserProjectCodeDTO2);
        recUserProjectCodeDTO2.setUserId("id2");
        assertThat(recUserProjectCodeDTO1).isNotEqualTo(recUserProjectCodeDTO2);
        recUserProjectCodeDTO1.setUserId(null);
        assertThat(recUserProjectCodeDTO1).isNotEqualTo(recUserProjectCodeDTO2);
    }
}
