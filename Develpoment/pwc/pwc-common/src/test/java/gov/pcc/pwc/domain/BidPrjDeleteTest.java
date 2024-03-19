package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjDeleteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjDelete.class);
        BidPrjDelete bidPrjDelete1 = new BidPrjDelete();
        bidPrjDelete1.setId(new BidProjectPrimaryKey("1234","56789"));
        BidPrjDelete bidPrjDelete2 = new BidPrjDelete();
        bidPrjDelete2.setId(bidPrjDelete1.getId());
        assertThat(bidPrjDelete1).isEqualTo(bidPrjDelete2);
        bidPrjDelete2.setId(new BidProjectPrimaryKey("2234","56789"));
        assertThat(bidPrjDelete1).isNotEqualTo(bidPrjDelete2);
        bidPrjDelete1.setId(null);
        assertThat(bidPrjDelete1).isNotEqualTo(bidPrjDelete2);
    }
}
