package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidMdlbasTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidMdlbas.class);
        BidMdlbas bidMdlbas1 = new BidMdlbas();
        bidMdlbas1.setId(new BidMdlbasPrimaryKey("wqe1231","213213"));
        BidMdlbas bidMdlbas2 = new BidMdlbas();
        bidMdlbas2.setId(bidMdlbas1.getId());
        assertThat(bidMdlbas1).isEqualTo(bidMdlbas2);
        bidMdlbas2.setId(new BidMdlbasPrimaryKey("213214","231321312"));
        assertThat(bidMdlbas1).isNotEqualTo(bidMdlbas2);
        bidMdlbas1.setId(null);
        assertThat(bidMdlbas1).isNotEqualTo(bidMdlbas2);
    }
}
