package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProInnovativeProductDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProInnovativeProductDTO.class);
        ProInnovativeProductDTO proInnovativeProductDTO1 = new ProInnovativeProductDTO();
        proInnovativeProductDTO1.setProInnovativeProductId("id1");
        ProInnovativeProductDTO proInnovativeProductDTO2 = new ProInnovativeProductDTO();
        assertThat(proInnovativeProductDTO1).isNotEqualTo(proInnovativeProductDTO2);
        proInnovativeProductDTO2.setProInnovativeProductId(proInnovativeProductDTO1.getProInnovativeProductId());
        assertThat(proInnovativeProductDTO1).isEqualTo(proInnovativeProductDTO2);
        proInnovativeProductDTO2.setProInnovativeProductId("id2");
        assertThat(proInnovativeProductDTO1).isNotEqualTo(proInnovativeProductDTO2);
        proInnovativeProductDTO1.setProInnovativeProductId(null);
        assertThat(proInnovativeProductDTO1).isNotEqualTo(proInnovativeProductDTO2);
    }
}
