package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjMilestoneMapperTest {

    private BidPrjMilestoneMapper bidPrjMilestoneMapper;

    @BeforeEach
    public void setUp() {
        bidPrjMilestoneMapper = new BidPrjMilestoneMapperImpl();
    }
}
