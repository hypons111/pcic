package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjFalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjFal.class);
        BidPrjFal bidPrjFal1 = new BidPrjFal();
        bidPrjFal1.setId(new BidPrjFalPrimaryKey("12345678","12345678","111","01","01"));
        BidPrjFal bidPrjFal2 = new BidPrjFal();
        bidPrjFal2.setId(bidPrjFal1.getId());
        assertThat(bidPrjFal1).isEqualTo(bidPrjFal2);
        bidPrjFal2.setId(new BidPrjFalPrimaryKey("12345678","12345678","111","02","02"));
        assertThat(bidPrjFal1).isNotEqualTo(bidPrjFal2);
        bidPrjFal1.setId(null);
        assertThat(bidPrjFal1).isNotEqualTo(bidPrjFal2);
    }
}
