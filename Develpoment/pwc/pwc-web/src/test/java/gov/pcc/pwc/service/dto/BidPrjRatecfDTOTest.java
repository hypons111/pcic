package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjRatecfDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjRatecfDTO.class);
        BidPrjRatecfDTO bidPrjRatecfDTO1 = new BidPrjRatecfDTO();
//        bidPrjRatecfDTO1.setId(1L);
        BidPrjRatecfDTO bidPrjRatecfDTO2 = new BidPrjRatecfDTO();
        assertThat(bidPrjRatecfDTO1).isNotEqualTo(bidPrjRatecfDTO2);
        bidPrjRatecfDTO2.setId(bidPrjRatecfDTO1.getId());
        assertThat(bidPrjRatecfDTO1).isEqualTo(bidPrjRatecfDTO2);
//        bidPrjRatecfDTO2.setId(2L);
        assertThat(bidPrjRatecfDTO1).isNotEqualTo(bidPrjRatecfDTO2);
        bidPrjRatecfDTO1.setId(null);
        assertThat(bidPrjRatecfDTO1).isNotEqualTo(bidPrjRatecfDTO2);
    }
}
