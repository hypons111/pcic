package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjStopwkTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjStopwk.class);
        BidPrjStopwk bidPrjStopwk1 = new BidPrjStopwk();
        bidPrjStopwk1.setId(new BidPrjStopwkPrimaryKey("12345678","12345678", LocalDate.now()));
        BidPrjStopwk bidPrjStopwk2 = new BidPrjStopwk();
        bidPrjStopwk2.setId(bidPrjStopwk1.getId());
        assertThat(bidPrjStopwk1).isEqualTo(bidPrjStopwk2);
        bidPrjStopwk2.setId(new BidPrjStopwkPrimaryKey("22345678","22345678", LocalDate.now()));
        assertThat(bidPrjStopwk1).isNotEqualTo(bidPrjStopwk2);
        bidPrjStopwk1.setId(null);
        assertThat(bidPrjStopwk1).isNotEqualTo(bidPrjStopwk2);
    }
}
