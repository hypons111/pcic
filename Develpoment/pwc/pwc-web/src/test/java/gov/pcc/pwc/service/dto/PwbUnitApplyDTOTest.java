package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbUnitApplyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbUnitApplyDTO.class);
        PwbUnitApplyDTO pwbUnitApplyDTO1 = new PwbUnitApplyDTO();
        pwbUnitApplyDTO1.setId(1L);
        PwbUnitApplyDTO pwbUnitApplyDTO2 = new PwbUnitApplyDTO();
        assertThat(pwbUnitApplyDTO1).isNotEqualTo(pwbUnitApplyDTO2);
        pwbUnitApplyDTO2.setId(pwbUnitApplyDTO1.getId());
        assertThat(pwbUnitApplyDTO1).isEqualTo(pwbUnitApplyDTO2);
        pwbUnitApplyDTO2.setId(2L);
        assertThat(pwbUnitApplyDTO1).isNotEqualTo(pwbUnitApplyDTO2);
        pwbUnitApplyDTO1.setId(null);
        assertThat(pwbUnitApplyDTO1).isNotEqualTo(pwbUnitApplyDTO2);
    }
}
