package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjPriceMapperTest {

    private BidPrjPriceMapper bidPrjPriceMapper;

    @BeforeEach
    public void setUp() {
        bidPrjPriceMapper = new BidPrjPriceMapperImpl();
    }
}
