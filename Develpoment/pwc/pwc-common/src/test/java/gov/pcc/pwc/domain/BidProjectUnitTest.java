package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidProjectUnitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidProjectUnit.class);
        BidProjectUnit bidProjectUnit1 = new BidProjectUnit();
        bidProjectUnit1.setId(new BidProjectUnitPrimaryKey("123456789", "123456789", "1", "1"));
        BidProjectUnit bidProjectUnit2 = new BidProjectUnit();
        bidProjectUnit2.setId(bidProjectUnit1.getId());
        assertThat(bidProjectUnit1).isEqualTo(bidProjectUnit2);
        bidProjectUnit2.setId(new BidProjectUnitPrimaryKey("223456789", "223456789", "2", "2"));
        assertThat(bidProjectUnit1).isNotEqualTo(bidProjectUnit2);
        bidProjectUnit1.setId(null);
        assertThat(bidProjectUnit1).isNotEqualTo(bidProjectUnit2);
    }
}
