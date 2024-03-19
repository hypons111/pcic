package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TecPageDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TecPageDTO.class);
        TecPageDTO tecPageDTO1 = new TecPageDTO();
        tecPageDTO1.setPageId(1L);
        TecPageDTO tecPageDTO2 = new TecPageDTO();
        assertThat(tecPageDTO1).isNotEqualTo(tecPageDTO2);
        tecPageDTO2.setPageId(tecPageDTO1.getPageId());
        assertThat(tecPageDTO1).isEqualTo(tecPageDTO2);
        tecPageDTO2.setPageId(2L);
        assertThat(tecPageDTO1).isNotEqualTo(tecPageDTO2);
        tecPageDTO1.setPageId(null);
        assertThat(tecPageDTO1).isNotEqualTo(tecPageDTO2);
    }
}
