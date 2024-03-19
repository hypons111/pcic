package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjMonthMapperTest {

    private BidPrjMonthMapper bidPrjMonthMapper;

    @BeforeEach
    public void setUp() {
        bidPrjMonthMapper = new BidPrjMonthMapperImpl();
    }
}
