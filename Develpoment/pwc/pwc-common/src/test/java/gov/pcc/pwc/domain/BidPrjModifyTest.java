package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidPrjModifyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidPrjModify.class);
        BidPrjModify bidPrjModify1 = new BidPrjModify();
        bidPrjModify1.setId(new BidPrjModifyPrimaryKey("12345678","12345678", LocalDate.now()));
        BidPrjModify bidPrjModify2 = new BidPrjModify();
        bidPrjModify2.setId(bidPrjModify1.getId());
        assertThat(bidPrjModify1).isEqualTo(bidPrjModify2);
        bidPrjModify2.setId(new BidPrjModifyPrimaryKey("22345678","22345678", LocalDate.now()));
        assertThat(bidPrjModify1).isNotEqualTo(bidPrjModify2);
        bidPrjModify1.setId(null);
        assertThat(bidPrjModify1).isNotEqualTo(bidPrjModify2);
    }
}
