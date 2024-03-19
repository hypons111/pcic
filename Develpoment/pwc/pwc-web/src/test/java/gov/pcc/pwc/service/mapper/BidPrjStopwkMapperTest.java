package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjStopwkMapperTest {

    private BidPrjStopwkMapper bidPrjStopwkMapper;

    @BeforeEach
    public void setUp() {
        bidPrjStopwkMapper = new BidPrjStopwkMapperImpl();
    }
}
