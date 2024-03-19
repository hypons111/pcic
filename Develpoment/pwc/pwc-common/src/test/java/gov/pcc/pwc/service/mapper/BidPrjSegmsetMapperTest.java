package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjSegmsetMapperTest {

    private BidPrjSegmsetMapper bidPrjSegmsetMapper;

    @BeforeEach
    public void setUp() {
        bidPrjSegmsetMapper = new BidPrjSegmsetMapperImpl();
    }
}
