package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjDsptchMapperTest {

    private BidPrjDsptchMapper bidPrjDsptchMapper;

    @BeforeEach
    public void setUp() {
        bidPrjDsptchMapper = new BidPrjDsptchMapperImpl();
    }
}
