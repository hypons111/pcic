package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjFrwkrMapperTest {

    private BidPrjFrwkrMapper bidPrjFrwkrMapper;

    @BeforeEach
    public void setUp() {
        bidPrjFrwkrMapper = new BidPrjFrwkrMapperImpl();
    }
}
