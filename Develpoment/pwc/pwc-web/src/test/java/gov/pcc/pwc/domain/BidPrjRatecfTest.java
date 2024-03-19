package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjRatecfTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjRatecf.class);
        BidPrjRatecf bidPrjRatecf1 = new BidPrjRatecf();
//        bidPrjRatecf1.setId(1L);
        BidPrjRatecf bidPrjRatecf2 = new BidPrjRatecf();
        bidPrjRatecf2.setId(bidPrjRatecf1.getId());
        assertThat(bidPrjRatecf1).isEqualTo(bidPrjRatecf2);
//        bidPrjRatecf2.setId(2L);
        assertThat(bidPrjRatecf1).isNotEqualTo(bidPrjRatecf2);
        bidPrjRatecf1.setId(null);
        assertThat(bidPrjRatecf1).isNotEqualTo(bidPrjRatecf2);
    }
}
