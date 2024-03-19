package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidProjectDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidProjectDTO.class);
        BidProjectDTO bidProjectDTO1 = new BidProjectDTO();
        bidProjectDTO1.setId(new BidProjectPrimaryKey("1234","3333"));
        BidProjectDTO bidProjectDTO2 = new BidProjectDTO();
        assertThat(bidProjectDTO1).isNotEqualTo(bidProjectDTO2);
        bidProjectDTO2.setId(bidProjectDTO1.getId());
        assertThat(bidProjectDTO1).isEqualTo(bidProjectDTO2);
        bidProjectDTO2.setId(new BidProjectPrimaryKey("2234","3333"));
        assertThat(bidProjectDTO1).isNotEqualTo(bidProjectDTO2);
        bidProjectDTO1.setId(null);
        assertThat(bidProjectDTO1).isNotEqualTo(bidProjectDTO2);
    }
}
