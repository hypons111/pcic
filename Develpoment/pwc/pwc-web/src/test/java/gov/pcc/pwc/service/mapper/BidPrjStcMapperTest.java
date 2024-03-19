package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjStcMapperTest {

    private BidPrjStcMapper bidPrjStcMapper;

    @BeforeEach
    public void setUp() {
        bidPrjStcMapper = new BidPrjStcMapperImpl();
    }
}
