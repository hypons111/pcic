package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjMonthTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjMonth.class);
        BidPrjMonth bidPrjMonth1 = new BidPrjMonth();
        bidPrjMonth1.setId(new BidPrjMonthPrimaryKey("12345678","12345678","111","01"));
        BidPrjMonth bidPrjMonth2 = new BidPrjMonth();
        bidPrjMonth2.setId(bidPrjMonth1.getId());
        assertThat(bidPrjMonth1).isEqualTo(bidPrjMonth2);
        bidPrjMonth2.setId(new BidPrjMonthPrimaryKey("22345678","22345678","112","02"));
        assertThat(bidPrjMonth1).isNotEqualTo(bidPrjMonth2);
        bidPrjMonth1.setId(null);
        assertThat(bidPrjMonth1).isNotEqualTo(bidPrjMonth2);
    }
}
