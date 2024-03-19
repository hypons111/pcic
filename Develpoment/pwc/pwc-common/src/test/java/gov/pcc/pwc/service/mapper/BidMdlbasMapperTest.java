package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidMdlbasMapperTest {

    private BidMdlbasMapper bidMdlbasMapper;

    @BeforeEach
    public void setUp() {
        bidMdlbasMapper = new BidMdlbasMapperImpl();
    }
}
