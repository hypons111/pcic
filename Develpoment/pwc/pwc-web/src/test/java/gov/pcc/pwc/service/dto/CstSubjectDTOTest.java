package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CstSubjectDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CstSubjectDTO.class);
        CstSubjectDTO cstSubjectDTO1 = new CstSubjectDTO();
        cstSubjectDTO1.setId(1L);
        CstSubjectDTO cstSubjectDTO2 = new CstSubjectDTO();
        assertThat(cstSubjectDTO1).isNotEqualTo(cstSubjectDTO2);
        cstSubjectDTO2.setId(cstSubjectDTO1.getId());
        assertThat(cstSubjectDTO1).isEqualTo(cstSubjectDTO2);
        cstSubjectDTO2.setId(2L);
        assertThat(cstSubjectDTO1).isNotEqualTo(cstSubjectDTO2);
        cstSubjectDTO1.setId(null);
        assertThat(cstSubjectDTO1).isNotEqualTo(cstSubjectDTO2);
    }
}
