package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidGovPrecheckPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BidGovPrecheckDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidGovPrecheckDTO.class);
        BidGovPrecheckDTO bidGovPrecheckDTO1 = new BidGovPrecheckDTO();
        bidGovPrecheckDTO1.setId(new BidGovPrecheckPrimaryKey("123","456", LocalDate.now()));
        BidGovPrecheckDTO bidGovPrecheckDTO2 = new BidGovPrecheckDTO();
        assertThat(bidGovPrecheckDTO1).isNotEqualTo(bidGovPrecheckDTO2);
        bidGovPrecheckDTO2.setId(bidGovPrecheckDTO1.getId());
        assertThat(bidGovPrecheckDTO1).isEqualTo(bidGovPrecheckDTO2);
        bidGovPrecheckDTO2.setId(new BidGovPrecheckPrimaryKey("223","456", LocalDate.now()));
        assertThat(bidGovPrecheckDTO1).isNotEqualTo(bidGovPrecheckDTO2);
        bidGovPrecheckDTO1.setId(null);
        assertThat(bidGovPrecheckDTO1).isNotEqualTo(bidGovPrecheckDTO2);
    }
}
