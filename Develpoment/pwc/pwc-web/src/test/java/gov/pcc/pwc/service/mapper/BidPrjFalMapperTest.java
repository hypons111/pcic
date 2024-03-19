package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjFalMapperTest {

    private BidPrjFalMapper bidPrjFalMapper;

    @BeforeEach
    public void setUp() {
        bidPrjFalMapper = new BidPrjFalMapperImpl();
    }
}
