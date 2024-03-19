package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjDeleteMapperTest {

    private BidPrjDeleteMapper bidPrjDeleteMapper;

    @BeforeEach
    public void setUp() {
        bidPrjDeleteMapper = new BidPrjDeleteMapperImpl();
    }
}
