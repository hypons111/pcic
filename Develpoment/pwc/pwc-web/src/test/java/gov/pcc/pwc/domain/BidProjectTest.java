package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidProjectTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidProject.class);
        BidProject bidProject1 = new BidProject();
        bidProject1.setId(new BidProjectPrimaryKey("1234","2234"));
        BidProject bidProject2 = new BidProject();
        bidProject2.setId(bidProject1.getId());
        assertThat(bidProject1).isEqualTo(bidProject2);
        bidProject2.setId(new BidProjectPrimaryKey("2234","2234"));
        assertThat(bidProject1).isNotEqualTo(bidProject2);
        bidProject1.setId(null);
        assertThat(bidProject1).isNotEqualTo(bidProject2);
    }
}
