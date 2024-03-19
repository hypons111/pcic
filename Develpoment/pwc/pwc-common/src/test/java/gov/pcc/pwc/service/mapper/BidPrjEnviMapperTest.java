package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjEnviMapperTest {

    private BidPrjEnviMapper bidPrjEnviMapper;

    @BeforeEach
    public void setUp() {
        bidPrjEnviMapper = new BidPrjEnviMapperImpl();
    }
}
