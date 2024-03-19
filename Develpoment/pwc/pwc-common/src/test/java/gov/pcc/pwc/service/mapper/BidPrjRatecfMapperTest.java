package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjRatecfMapperTest {

    private BidPrjRatecfMapper bidPrjRatecfMapper;

    @BeforeEach
    public void setUp() {
        bidPrjRatecfMapper = new BidPrjRatecfMapperImpl();
    }
}
