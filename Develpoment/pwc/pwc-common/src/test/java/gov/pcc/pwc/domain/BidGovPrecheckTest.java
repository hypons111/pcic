package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidGovPrecheckTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidGovPrecheck.class);
        BidGovPrecheck bidGovPrecheck1 = new BidGovPrecheck();
        bidGovPrecheck1.setId(new BidGovPrecheckPrimaryKey("123","456", LocalDate.now()));
        BidGovPrecheck bidGovPrecheck2 = new BidGovPrecheck();
        bidGovPrecheck2.setId(bidGovPrecheck1.getId());
        assertThat(bidGovPrecheck1).isEqualTo(bidGovPrecheck2);
        bidGovPrecheck2.setId(new BidGovPrecheckPrimaryKey("223","456", LocalDate.now()));
        assertThat(bidGovPrecheck1).isNotEqualTo(bidGovPrecheck2);
        bidGovPrecheck1.setId(null);
        assertThat(bidGovPrecheck1).isNotEqualTo(bidGovPrecheck2);
    }
}
