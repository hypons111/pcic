package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidGovPrecheckMapperTest {

    private BidGovPrecheckMapper bidGovPrecheckMapper;

    @BeforeEach
    public void setUp() {
        bidGovPrecheckMapper = new BidGovPrecheckMapperImpl();
    }
}
