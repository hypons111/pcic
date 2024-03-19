package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjScequipMapperTest {

    private BidPrjScequipMapper bidPrjScequipMapper;

    @BeforeEach
    public void setUp() {
        bidPrjScequipMapper = new BidPrjScequipMapperImpl();
    }
}
