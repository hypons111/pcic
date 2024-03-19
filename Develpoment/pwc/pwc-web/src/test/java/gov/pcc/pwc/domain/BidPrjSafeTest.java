package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjSafeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjSafe.class);
        BidPrjSafe bidPrjSafe1 = new BidPrjSafe();
        bidPrjSafe1.setId(new BidPrjSafePrimaryKey("13123123","56564", LocalDate.parse("2022-02-21")));
        BidPrjSafe bidPrjSafe2 = new BidPrjSafe();
        bidPrjSafe2.setId(bidPrjSafe1.getId());
        assertThat(bidPrjSafe1).isEqualTo(bidPrjSafe2);
        bidPrjSafe2.setId(new BidPrjSafePrimaryKey("341212","3322", LocalDate.parse("2022-05-11")));
        assertThat(bidPrjSafe1).isNotEqualTo(bidPrjSafe2);
        bidPrjSafe1.setId(null);
        assertThat(bidPrjSafe1).isNotEqualTo(bidPrjSafe2);
    }
}
