package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidUnit10DTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidUnit10DTO.class);
        BidUnit10DTO bidUnit10DTO1 = new BidUnit10DTO();
        bidUnit10DTO1.setCode("id1");
        BidUnit10DTO bidUnit10DTO2 = new BidUnit10DTO();
        assertThat(bidUnit10DTO1).isNotEqualTo(bidUnit10DTO2);
        bidUnit10DTO2.setCode(bidUnit10DTO1.getCode());
        assertThat(bidUnit10DTO1).isEqualTo(bidUnit10DTO2);
        bidUnit10DTO2.setCode("id2");
        assertThat(bidUnit10DTO1).isNotEqualTo(bidUnit10DTO2);
        bidUnit10DTO1.setCode(null);
        assertThat(bidUnit10DTO1).isNotEqualTo(bidUnit10DTO2);
    }
}
