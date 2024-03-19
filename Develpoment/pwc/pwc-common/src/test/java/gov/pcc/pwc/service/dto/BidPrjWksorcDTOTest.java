package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjWksorcDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjWksorcDTO.class);
        BidPrjWksorcDTO bidPrjWksorcDTO1 = new BidPrjWksorcDTO();
//        bidPrjWksorcDTO1.setId(1L);
        BidPrjWksorcDTO bidPrjWksorcDTO2 = new BidPrjWksorcDTO();
        assertThat(bidPrjWksorcDTO1).isNotEqualTo(bidPrjWksorcDTO2);
        bidPrjWksorcDTO2.setId(bidPrjWksorcDTO1.getId());
        assertThat(bidPrjWksorcDTO1).isEqualTo(bidPrjWksorcDTO2);
//        bidPrjWksorcDTO2.setId(2L);
        assertThat(bidPrjWksorcDTO1).isNotEqualTo(bidPrjWksorcDTO2);
        bidPrjWksorcDTO1.setId(null);
        assertThat(bidPrjWksorcDTO1).isNotEqualTo(bidPrjWksorcDTO2);
    }
}
