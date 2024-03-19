package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TecContentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TecContentDTO.class);
        TecContentDTO tecContentDTO1 = new TecContentDTO();
        tecContentDTO1.setContentId(1L);
        TecContentDTO tecContentDTO2 = new TecContentDTO();
        assertThat(tecContentDTO1).isNotEqualTo(tecContentDTO2);
        tecContentDTO2.setContentId(tecContentDTO1.getContentId());
        assertThat(tecContentDTO1).isEqualTo(tecContentDTO2);
        tecContentDTO2.setContentId(2L);
        assertThat(tecContentDTO1).isNotEqualTo(tecContentDTO2);
        tecContentDTO1.setContentId(null);
        assertThat(tecContentDTO1).isNotEqualTo(tecContentDTO2);
    }
}
