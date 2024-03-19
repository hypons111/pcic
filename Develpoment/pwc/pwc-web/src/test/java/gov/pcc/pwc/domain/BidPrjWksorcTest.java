package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjWksorcTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjWksorc.class);
        BidPrjWksorc bidPrjWksorc1 = new BidPrjWksorc();
//        bidPrjWksorc1.setId(1L);
        BidPrjWksorc bidPrjWksorc2 = new BidPrjWksorc();
        bidPrjWksorc2.setId(bidPrjWksorc1.getId());
        assertThat(bidPrjWksorc1).isEqualTo(bidPrjWksorc2);
//        bidPrjWksorc2.setId(2L);
        assertThat(bidPrjWksorc1).isNotEqualTo(bidPrjWksorc2);
        bidPrjWksorc1.setId(null);
        assertThat(bidPrjWksorc1).isNotEqualTo(bidPrjWksorc2);
    }
}
