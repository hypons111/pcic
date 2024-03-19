package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidPrjEquipTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjEquip.class);
        BidPrjEquip bidPrjEquip1 = new BidPrjEquip();
        bidPrjEquip1.setId(new BidPrjEquipPrimaryKey("12345678","12345678","111","01","01"));
        BidPrjEquip bidPrjEquip2 = new BidPrjEquip();
        bidPrjEquip2.setId(bidPrjEquip1.getId());
        assertThat(bidPrjEquip1).isEqualTo(bidPrjEquip2);
        bidPrjEquip2.setId(new BidPrjEquipPrimaryKey("12345678","12345678","111","02","02"));
        assertThat(bidPrjEquip1).isNotEqualTo(bidPrjEquip2);
        bidPrjEquip1.setId(null);
        assertThat(bidPrjEquip1).isNotEqualTo(bidPrjEquip2);
    }
}
