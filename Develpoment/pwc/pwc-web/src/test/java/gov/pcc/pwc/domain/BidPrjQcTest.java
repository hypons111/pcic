package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjQcTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjQc.class);
        BidPrjQc bidPrjQc1 = new BidPrjQc();
        bidPrjQc1.setPrjQcKey(new BidPrjQcPrimaryKey("123123","1232565431","1232541", LocalDate.parse("2022-02-21")));
        BidPrjQc bidPrjQc2 = new BidPrjQc();
        bidPrjQc2.setPrjQcKey(bidPrjQc1.getPrjQcKey());
        assertThat(bidPrjQc1).isEqualTo(bidPrjQc2);
        bidPrjQc2.setPrjQcKey(new BidPrjQcPrimaryKey("126436","546458","2343432", LocalDate.parse("2022-03-21")));
        assertThat(bidPrjQc1).isNotEqualTo(bidPrjQc2);
        bidPrjQc1.setPrjQcKey(null);
        assertThat(bidPrjQc1).isNotEqualTo(bidPrjQc2);
    }
}
