package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjFrwkrTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjFrwkr.class);
        BidPrjFrwkr bidPrjFrwkr1 = new BidPrjFrwkr();
        bidPrjFrwkr1.setId(new BidPrjFrwkrPrimaryKey("12345678","12345678", LocalDate.now()));
        BidPrjFrwkr bidPrjFrwkr2 = new BidPrjFrwkr();
        bidPrjFrwkr2.setId(bidPrjFrwkr1.getId());
        assertThat(bidPrjFrwkr1).isEqualTo(bidPrjFrwkr2);
        bidPrjFrwkr2.setId(new BidPrjFrwkrPrimaryKey("12345678","12345678", LocalDate.now()));
        assertThat(bidPrjFrwkr1).isNotEqualTo(bidPrjFrwkr2);
        bidPrjFrwkr1.setId(null);
        assertThat(bidPrjFrwkr1).isNotEqualTo(bidPrjFrwkr2);
    }
}
