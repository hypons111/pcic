package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjSubcoMapperTest {

    private BidPrjSubcoMapper bidPrjSubcoMapper;

    @BeforeEach
    public void setUp() {
        bidPrjSubcoMapper = new BidPrjSubcoMapperImpl();
    }
}
