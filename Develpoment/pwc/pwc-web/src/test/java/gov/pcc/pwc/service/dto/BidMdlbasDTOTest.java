package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.domain.BidMdlbasPrimaryKey;
import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BidMdlbasDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidMdlbasDTO.class);
        BidMdlbasDTO bidMdlbasDTO1 = new BidMdlbasDTO();
        bidMdlbasDTO1.setId(new BidMdlbasPrimaryKey("12312466","34634"));
        BidMdlbasDTO bidMdlbasDTO2 = new BidMdlbasDTO();
        assertThat(bidMdlbasDTO1).isNotEqualTo(bidMdlbasDTO2);
        bidMdlbasDTO2.setId(bidMdlbasDTO1.getId());
        assertThat(bidMdlbasDTO1).isEqualTo(bidMdlbasDTO2);
        bidMdlbasDTO2.setId(new BidMdlbasPrimaryKey("4562456","4867746"));
        assertThat(bidMdlbasDTO1).isNotEqualTo(bidMdlbasDTO2);
        bidMdlbasDTO1.setId(null);
        assertThat(bidMdlbasDTO1).isNotEqualTo(bidMdlbasDTO2);
    }
}
