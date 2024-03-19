package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidProjectUnitPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidProjectUnitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidProjectUnitDTO.class);
        BidProjectUnitDTO bidProjectUnitDTO1 = new BidProjectUnitDTO();
        bidProjectUnitDTO1.setId(new BidProjectUnitPrimaryKey("123456789", "123456789", "1", "1"));
        BidProjectUnitDTO bidProjectUnitDTO2 = new BidProjectUnitDTO();
        assertThat(bidProjectUnitDTO1).isNotEqualTo(bidProjectUnitDTO2);
        bidProjectUnitDTO2.setId(bidProjectUnitDTO1.getId());
        assertThat(bidProjectUnitDTO1).isEqualTo(bidProjectUnitDTO2);
        bidProjectUnitDTO2.setId(new BidProjectUnitPrimaryKey("223456789", "223456789", "2", "2"));
        assertThat(bidProjectUnitDTO1).isNotEqualTo(bidProjectUnitDTO2);
        bidProjectUnitDTO1.setId(null);
        assertThat(bidProjectUnitDTO1).isNotEqualTo(bidProjectUnitDTO2);
    }
}
