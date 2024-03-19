package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjSafeMapperTest {

    private BidPrjSafeMapper bidPrjSafeMapper;

    @BeforeEach
    public void setUp() {
        bidPrjSafeMapper = new BidPrjSafeMapperImpl();
    }
}
