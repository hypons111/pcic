package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;

import gov.pcc.pwc.domain.BidPrjSubco;
import gov.pcc.pwc.domain.BidPrjSubcoPrimaryKey;
import gov.pcc.pwc.repository.BidPrjSubcoRepository;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import gov.pcc.pwc.service.mapper.BidPrjSubcoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BidPrjSubcoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjSubcoResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCO_NO = "AAAA";
    private static final String UPDATED_SUBCO_NO = "BBBB";

    private static final String DEFAULT_SUB_CCUT = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CCUT = "BBBBBBBBBB";

    private static final String DEFAULT_CO_REG_NO = "AAAAAAAAAA";
    private static final String UPDATED_CO_REG_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_SUB_BDGT_1 = 1D;
    private static final Double UPDATED_SUB_BDGT_1 = 2D;

    private static final String DEFAULT_UP_CCUT = "AAAAAAAAAA";
    private static final String UPDATED_UP_CCUT = "BBBBBBBBBB";

    private static final String DEFAULT_STYPE = "AA";
    private static final String UPDATED_STYPE = "BB";

    private static final String DEFAULT_SPCW_01 = "A";
    private static final String UPDATED_SPCW_01 = "B";

    private static final String DEFAULT_SPCW_02 = "A";
    private static final String UPDATED_SPCW_02 = "B";

    private static final String DEFAULT_SPCW_03 = "A";
    private static final String UPDATED_SPCW_03 = "B";

    private static final String DEFAULT_SPCW_04 = "A";
    private static final String UPDATED_SPCW_04 = "B";

    private static final String DEFAULT_SPCW_05 = "A";
    private static final String UPDATED_SPCW_05 = "B";

    private static final String DEFAULT_SPCW_06 = "A";
    private static final String UPDATED_SPCW_06 = "B";

    private static final String DEFAULT_SPCW_07 = "A";
    private static final String UPDATED_SPCW_07 = "B";

    private static final String DEFAULT_SPCW_08 = "A";
    private static final String UPDATED_SPCW_08 = "B";

    private static final String DEFAULT_SPCW_09 = "A";
    private static final String UPDATED_SPCW_09 = "B";

    private static final String DEFAULT_SPCW_10 = "A";
    private static final String UPDATED_SPCW_10 = "B";

    private static final String DEFAULT_SPCW_11 = "A";
    private static final String UPDATED_SPCW_11 = "B";

    private static final String DEFAULT_SPCW_12 = "A";
    private static final String UPDATED_SPCW_12 = "B";

    private static final String DEFAULT_SUBCO_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_SUBCO_MEMO = "BBBBBBBBBB";

    private static final String DEFAULT_MET_01 = "A";
    private static final String UPDATED_MET_01 = "B";

    private static final String DEFAULT_MET_01_B = "A";
    private static final String UPDATED_MET_01_B = "B";

    private static final String DEFAULT_MET_01_C = "A";
    private static final String UPDATED_MET_01_C = "B";

    private static final String DEFAULT_MET_05 = "A";
    private static final String UPDATED_MET_05 = "B";

    private static final String DEFAULT_MET_02_F = "A";
    private static final String UPDATED_MET_02_F = "B";

    private static final String DEFAULT_MET_01_D = "A";
    private static final String UPDATED_MET_01_D = "B";

    private static final String DEFAULT_MET_02 = "A";
    private static final String UPDATED_MET_02 = "B";

    private static final String DEFAULT_MET_02_B = "A";
    private static final String UPDATED_MET_02_B = "B";

    private static final String DEFAULT_MET_04 = "A";
    private static final String UPDATED_MET_04 = "B";

    private static final String DEFAULT_MET_03 = "A";
    private static final String UPDATED_MET_03 = "B";

    private static final String DEFAULT_MET_03_B = "A";
    private static final String UPDATED_MET_03_B = "B";

    private static final String DEFAULT_MET_02_C = "A";
    private static final String UPDATED_MET_02_C = "B";

    private static final String DEFAULT_MET_02_D = "A";
    private static final String UPDATED_MET_02_D = "B";

    private static final String DEFAULT_MET_02_E = "A";
    private static final String UPDATED_MET_02_E = "B";

    private static final Double DEFAULT_MET_01_V = 1D;
    private static final Double UPDATED_MET_01_V = 2D;

    private static final Double DEFAULT_MET_01_BV = 1D;
    private static final Double UPDATED_MET_01_BV = 2D;

    private static final Double DEFAULT_MET_01_CV = 1D;
    private static final Double UPDATED_MET_01_CV = 2D;

    private static final Double DEFAULT_MET_05_V = 1D;
    private static final Double UPDATED_MET_05_V = 2D;

    private static final Double DEFAULT_MET_02_FV = 1D;
    private static final Double UPDATED_MET_02_FV = 2D;

    private static final Double DEFAULT_MET_01_DV = 1D;
    private static final Double UPDATED_MET_01_DV = 2D;

    private static final Double DEFAULT_MET_02_V = 1D;
    private static final Double UPDATED_MET_02_V = 2D;

    private static final Double DEFAULT_MET_02_BV = 1D;
    private static final Double UPDATED_MET_02_BV = 2D;

    private static final Double DEFAULT_MET_04_V = 1D;
    private static final Double UPDATED_MET_04_V = 2D;

    private static final Double DEFAULT_MET_03_V = 1D;
    private static final Double UPDATED_MET_03_V = 2D;

    private static final Double DEFAULT_MET_03_BV = 1D;
    private static final Double UPDATED_MET_03_BV = 2D;

    private static final Double DEFAULT_MET_02_CV = 1D;
    private static final Double UPDATED_MET_02_CV = 2D;

    private static final Double DEFAULT_MET_02_DV = 1D;
    private static final Double UPDATED_MET_02_DV = 2D;

    private static final Double DEFAULT_MET_02_EV = 1D;
    private static final Double UPDATED_MET_02_EV = 2D;

    private static final String DEFAULT_QCHK = "A";
    private static final String UPDATED_QCHK = "B";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_RMTFIL = "AAAAAAAAAA";
    private static final String UPDATED_RMTFIL = "BBBBBBBBBB";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_MET_06 = "A";
    private static final String UPDATED_MET_06 = "B";

    private static final String DEFAULT_MET_07 = "A";
    private static final String UPDATED_MET_07 = "B";

    private static final String DEFAULT_MET_08 = "A";
    private static final String UPDATED_MET_08 = "B";

    private static final String DEFAULT_MET_09 = "A";
    private static final String UPDATED_MET_09 = "B";

    private static final String DEFAULT_MET_10 = "A";
    private static final String UPDATED_MET_10 = "B";

    private static final String DEFAULT_MET_11 = "A";
    private static final String UPDATED_MET_11 = "B";

    private static final String DEFAULT_MET_12 = "A";
    private static final String UPDATED_MET_12 = "B";

    private static final String DEFAULT_MET_13 = "A";
    private static final String UPDATED_MET_13 = "B";

    private static final String DEFAULT_MET_14 = "A";
    private static final String UPDATED_MET_14 = "B";

    private static final String DEFAULT_MET_15 = "A";
    private static final String UPDATED_MET_15 = "B";

    private static final String DEFAULT_MET_16 = "A";
    private static final String UPDATED_MET_16 = "B";

    private static final String DEFAULT_MET_99 = "A";
    private static final String UPDATED_MET_99 = "B";

    private static final Double DEFAULT_MET_06_V = 1D;
    private static final Double UPDATED_MET_06_V = 2D;

    private static final Double DEFAULT_MET_07_V = 1D;
    private static final Double UPDATED_MET_07_V = 2D;

    private static final Double DEFAULT_MET_08_V = 1D;
    private static final Double UPDATED_MET_08_V = 2D;

    private static final Double DEFAULT_MET_09_V = 1D;
    private static final Double UPDATED_MET_09_V = 2D;

    private static final Double DEFAULT_MET_10_V = 1D;
    private static final Double UPDATED_MET_10_V = 2D;

    private static final Double DEFAULT_MET_11_V = 1D;
    private static final Double UPDATED_MET_11_V = 2D;

    private static final Double DEFAULT_MET_12_V = 1D;
    private static final Double UPDATED_MET_12_V = 2D;

    private static final Double DEFAULT_MET_13_V = 1D;
    private static final Double UPDATED_MET_13_V = 2D;

    private static final Double DEFAULT_MET_14_V = 1D;
    private static final Double UPDATED_MET_14_V = 2D;

    private static final Double DEFAULT_MET_15_V = 1D;
    private static final Double UPDATED_MET_15_V = 2D;

    private static final Double DEFAULT_MET_16_V = 1D;
    private static final Double UPDATED_MET_16_V = 2D;

    private static final Double DEFAULT_MET_99_V = 1D;
    private static final Double UPDATED_MET_99_V = 2D;

    private static final String DEFAULT_MET_99_STR = "AAAAAAAAAA";
    private static final String UPDATED_MET_99_STR = "BBBBBBBBBB";

    private static final String DEFAULT_CONFA = "A";
    private static final String UPDATED_CONFA = "B";

    private static final String DEFAULT_CONFB = "A";
    private static final String UPDATED_CONFB = "B";

    private static final String ENTITY_API_URL = "/api/bid-prj-subco";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjSubcoRepository bidPrjSubcoRepository;

    @Autowired
    private BidPrjSubcoMapper bidPrjSubcoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjSubcoMockMvc;

    private BidPrjSubco bidPrjSubco;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSubco createEntity(EntityManager em) {
        BidPrjSubco bidPrjSubco = new BidPrjSubco()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .subcoNo(DEFAULT_SUBCO_NO)
            .subCcut(DEFAULT_SUB_CCUT)
            .coRegNo(DEFAULT_CO_REG_NO)
            .subBdgt1(DEFAULT_SUB_BDGT_1)
            .upCcut(DEFAULT_UP_CCUT)
            .stype(DEFAULT_STYPE)
            .spcw01(DEFAULT_SPCW_01)
            .spcw02(DEFAULT_SPCW_02)
            .spcw03(DEFAULT_SPCW_03)
            .spcw04(DEFAULT_SPCW_04)
            .spcw05(DEFAULT_SPCW_05)
            .spcw06(DEFAULT_SPCW_06)
            .spcw07(DEFAULT_SPCW_07)
            .spcw08(DEFAULT_SPCW_08)
            .spcw09(DEFAULT_SPCW_09)
            .spcw10(DEFAULT_SPCW_10)
            .spcw11(DEFAULT_SPCW_11)
            .spcw12(DEFAULT_SPCW_12)
            .subcoMemo(DEFAULT_SUBCO_MEMO)
            .met01(DEFAULT_MET_01)
            .met01b(DEFAULT_MET_01_B)
            .met01c(DEFAULT_MET_01_C)
            .met05(DEFAULT_MET_05)
            .met02f(DEFAULT_MET_02_F)
            .met01d(DEFAULT_MET_01_D)
            .met02(DEFAULT_MET_02)
            .met02b(DEFAULT_MET_02_B)
            .met04(DEFAULT_MET_04)
            .met03(DEFAULT_MET_03)
            .met03b(DEFAULT_MET_03_B)
            .met02c(DEFAULT_MET_02_C)
            .met02d(DEFAULT_MET_02_D)
            .met02e(DEFAULT_MET_02_E)
            .met01v(DEFAULT_MET_01_V)
            .met01bv(DEFAULT_MET_01_BV)
            .met01cv(DEFAULT_MET_01_CV)
            .met05v(DEFAULT_MET_05_V)
            .met02fv(DEFAULT_MET_02_FV)
            .met01dv(DEFAULT_MET_01_DV)
            .met02v(DEFAULT_MET_02_V)
            .met02bv(DEFAULT_MET_02_BV)
            .met04v(DEFAULT_MET_04_V)
            .met03v(DEFAULT_MET_03_V)
            .met03bv(DEFAULT_MET_03_BV)
            .met02cv(DEFAULT_MET_02_CV)
            .met02dv(DEFAULT_MET_02_DV)
            .met02ev(DEFAULT_MET_02_EV)
            .qchk(DEFAULT_QCHK)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .rmtfil(DEFAULT_RMTFIL)
            .uIp(DEFAULT_U_IP)
            .met06(DEFAULT_MET_06)
            .met07(DEFAULT_MET_07)
            .met08(DEFAULT_MET_08)
            .met09(DEFAULT_MET_09)
            .met10(DEFAULT_MET_10)
            .met11(DEFAULT_MET_11)
            .met12(DEFAULT_MET_12)
            .met13(DEFAULT_MET_13)
            .met14(DEFAULT_MET_14)
            .met15(DEFAULT_MET_15)
            .met16(DEFAULT_MET_16)
            .met99(DEFAULT_MET_99)
            .met06v(DEFAULT_MET_06_V)
            .met07v(DEFAULT_MET_07_V)
            .met08v(DEFAULT_MET_08_V)
            .met09v(DEFAULT_MET_09_V)
            .met10v(DEFAULT_MET_10_V)
            .met11v(DEFAULT_MET_11_V)
            .met12v(DEFAULT_MET_12_V)
            .met13v(DEFAULT_MET_13_V)
            .met14v(DEFAULT_MET_14_V)
            .met15v(DEFAULT_MET_15_V)
            .met16v(DEFAULT_MET_16_V)
            .met99v(DEFAULT_MET_99_V)
            .met99str(DEFAULT_MET_99_STR)
            .confa(DEFAULT_CONFA)
            .confb(DEFAULT_CONFB);
        return bidPrjSubco;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSubco createUpdatedEntity(EntityManager em) {
        BidPrjSubco bidPrjSubco = new BidPrjSubco()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .subcoNo(UPDATED_SUBCO_NO)
            .subCcut(UPDATED_SUB_CCUT)
            .coRegNo(UPDATED_CO_REG_NO)
            .subBdgt1(UPDATED_SUB_BDGT_1)
            .upCcut(UPDATED_UP_CCUT)
            .stype(UPDATED_STYPE)
            .spcw01(UPDATED_SPCW_01)
            .spcw02(UPDATED_SPCW_02)
            .spcw03(UPDATED_SPCW_03)
            .spcw04(UPDATED_SPCW_04)
            .spcw05(UPDATED_SPCW_05)
            .spcw06(UPDATED_SPCW_06)
            .spcw07(UPDATED_SPCW_07)
            .spcw08(UPDATED_SPCW_08)
            .spcw09(UPDATED_SPCW_09)
            .spcw10(UPDATED_SPCW_10)
            .spcw11(UPDATED_SPCW_11)
            .spcw12(UPDATED_SPCW_12)
            .subcoMemo(UPDATED_SUBCO_MEMO)
            .met01(UPDATED_MET_01)
            .met01b(UPDATED_MET_01_B)
            .met01c(UPDATED_MET_01_C)
            .met05(UPDATED_MET_05)
            .met02f(UPDATED_MET_02_F)
            .met01d(UPDATED_MET_01_D)
            .met02(UPDATED_MET_02)
            .met02b(UPDATED_MET_02_B)
            .met04(UPDATED_MET_04)
            .met03(UPDATED_MET_03)
            .met03b(UPDATED_MET_03_B)
            .met02c(UPDATED_MET_02_C)
            .met02d(UPDATED_MET_02_D)
            .met02e(UPDATED_MET_02_E)
            .met01v(UPDATED_MET_01_V)
            .met01bv(UPDATED_MET_01_BV)
            .met01cv(UPDATED_MET_01_CV)
            .met05v(UPDATED_MET_05_V)
            .met02fv(UPDATED_MET_02_FV)
            .met01dv(UPDATED_MET_01_DV)
            .met02v(UPDATED_MET_02_V)
            .met02bv(UPDATED_MET_02_BV)
            .met04v(UPDATED_MET_04_V)
            .met03v(UPDATED_MET_03_V)
            .met03bv(UPDATED_MET_03_BV)
            .met02cv(UPDATED_MET_02_CV)
            .met02dv(UPDATED_MET_02_DV)
            .met02ev(UPDATED_MET_02_EV)
            .qchk(UPDATED_QCHK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .rmtfil(UPDATED_RMTFIL)
            .uIp(UPDATED_U_IP)
            .met06(UPDATED_MET_06)
            .met07(UPDATED_MET_07)
            .met08(UPDATED_MET_08)
            .met09(UPDATED_MET_09)
            .met10(UPDATED_MET_10)
            .met11(UPDATED_MET_11)
            .met12(UPDATED_MET_12)
            .met13(UPDATED_MET_13)
            .met14(UPDATED_MET_14)
            .met15(UPDATED_MET_15)
            .met16(UPDATED_MET_16)
            .met99(UPDATED_MET_99)
            .met06v(UPDATED_MET_06_V)
            .met07v(UPDATED_MET_07_V)
            .met08v(UPDATED_MET_08_V)
            .met09v(UPDATED_MET_09_V)
            .met10v(UPDATED_MET_10_V)
            .met11v(UPDATED_MET_11_V)
            .met12v(UPDATED_MET_12_V)
            .met13v(UPDATED_MET_13_V)
            .met14v(UPDATED_MET_14_V)
            .met15v(UPDATED_MET_15_V)
            .met16v(UPDATED_MET_16_V)
            .met99v(UPDATED_MET_99_V)
            .met99str(UPDATED_MET_99_STR)
            .confa(UPDATED_CONFA)
            .confb(UPDATED_CONFB);
        return bidPrjSubco;
    }

    @BeforeEach
    public void initTest() {
        bidPrjSubco = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjSubco() throws Exception {
        int databaseSizeBeforeCreate = bidPrjSubcoRepository.findAll().size();
        // Create the BidPrjSubco
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);
        restBidPrjSubcoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjSubco testBidPrjSubco = bidPrjSubcoList.get(bidPrjSubcoList.size() - 1);
        assertThat(testBidPrjSubco.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjSubco.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjSubco.getSubcoNo()).isEqualTo(DEFAULT_SUBCO_NO);
        assertThat(testBidPrjSubco.getSubCcut()).isEqualTo(DEFAULT_SUB_CCUT);
        assertThat(testBidPrjSubco.getCoRegNo()).isEqualTo(DEFAULT_CO_REG_NO);
        assertThat(testBidPrjSubco.getSubBdgt1()).isEqualTo(DEFAULT_SUB_BDGT_1);
        assertThat(testBidPrjSubco.getUpCcut()).isEqualTo(DEFAULT_UP_CCUT);
        assertThat(testBidPrjSubco.getStype()).isEqualTo(DEFAULT_STYPE);
        assertThat(testBidPrjSubco.getSpcw01()).isEqualTo(DEFAULT_SPCW_01);
        assertThat(testBidPrjSubco.getSpcw02()).isEqualTo(DEFAULT_SPCW_02);
        assertThat(testBidPrjSubco.getSpcw03()).isEqualTo(DEFAULT_SPCW_03);
        assertThat(testBidPrjSubco.getSpcw04()).isEqualTo(DEFAULT_SPCW_04);
        assertThat(testBidPrjSubco.getSpcw05()).isEqualTo(DEFAULT_SPCW_05);
        assertThat(testBidPrjSubco.getSpcw06()).isEqualTo(DEFAULT_SPCW_06);
        assertThat(testBidPrjSubco.getSpcw07()).isEqualTo(DEFAULT_SPCW_07);
        assertThat(testBidPrjSubco.getSpcw08()).isEqualTo(DEFAULT_SPCW_08);
        assertThat(testBidPrjSubco.getSpcw09()).isEqualTo(DEFAULT_SPCW_09);
        assertThat(testBidPrjSubco.getSpcw10()).isEqualTo(DEFAULT_SPCW_10);
        assertThat(testBidPrjSubco.getSpcw11()).isEqualTo(DEFAULT_SPCW_11);
        assertThat(testBidPrjSubco.getSpcw12()).isEqualTo(DEFAULT_SPCW_12);
        assertThat(testBidPrjSubco.getSubcoMemo()).isEqualTo(DEFAULT_SUBCO_MEMO);
        assertThat(testBidPrjSubco.getMet01()).isEqualTo(DEFAULT_MET_01);
        assertThat(testBidPrjSubco.getMet01b()).isEqualTo(DEFAULT_MET_01_B);
        assertThat(testBidPrjSubco.getMet01c()).isEqualTo(DEFAULT_MET_01_C);
        assertThat(testBidPrjSubco.getMet05()).isEqualTo(DEFAULT_MET_05);
        assertThat(testBidPrjSubco.getMet02f()).isEqualTo(DEFAULT_MET_02_F);
        assertThat(testBidPrjSubco.getMet01d()).isEqualTo(DEFAULT_MET_01_D);
        assertThat(testBidPrjSubco.getMet02()).isEqualTo(DEFAULT_MET_02);
        assertThat(testBidPrjSubco.getMet02b()).isEqualTo(DEFAULT_MET_02_B);
        assertThat(testBidPrjSubco.getMet04()).isEqualTo(DEFAULT_MET_04);
        assertThat(testBidPrjSubco.getMet03()).isEqualTo(DEFAULT_MET_03);
        assertThat(testBidPrjSubco.getMet03b()).isEqualTo(DEFAULT_MET_03_B);
        assertThat(testBidPrjSubco.getMet02c()).isEqualTo(DEFAULT_MET_02_C);
        assertThat(testBidPrjSubco.getMet02d()).isEqualTo(DEFAULT_MET_02_D);
        assertThat(testBidPrjSubco.getMet02e()).isEqualTo(DEFAULT_MET_02_E);
        assertThat(testBidPrjSubco.getMet01v()).isEqualTo(DEFAULT_MET_01_V);
        assertThat(testBidPrjSubco.getMet01bv()).isEqualTo(DEFAULT_MET_01_BV);
        assertThat(testBidPrjSubco.getMet01cv()).isEqualTo(DEFAULT_MET_01_CV);
        assertThat(testBidPrjSubco.getMet05v()).isEqualTo(DEFAULT_MET_05_V);
        assertThat(testBidPrjSubco.getMet02fv()).isEqualTo(DEFAULT_MET_02_FV);
        assertThat(testBidPrjSubco.getMet01dv()).isEqualTo(DEFAULT_MET_01_DV);
        assertThat(testBidPrjSubco.getMet02v()).isEqualTo(DEFAULT_MET_02_V);
        assertThat(testBidPrjSubco.getMet02bv()).isEqualTo(DEFAULT_MET_02_BV);
        assertThat(testBidPrjSubco.getMet04v()).isEqualTo(DEFAULT_MET_04_V);
        assertThat(testBidPrjSubco.getMet03v()).isEqualTo(DEFAULT_MET_03_V);
        assertThat(testBidPrjSubco.getMet03bv()).isEqualTo(DEFAULT_MET_03_BV);
        assertThat(testBidPrjSubco.getMet02cv()).isEqualTo(DEFAULT_MET_02_CV);
        assertThat(testBidPrjSubco.getMet02dv()).isEqualTo(DEFAULT_MET_02_DV);
        assertThat(testBidPrjSubco.getMet02ev()).isEqualTo(DEFAULT_MET_02_EV);
        assertThat(testBidPrjSubco.getQchk()).isEqualTo(DEFAULT_QCHK);
        assertThat(testBidPrjSubco.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjSubco.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjSubco.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjSubco.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjSubco.getRmtfil()).isEqualTo(DEFAULT_RMTFIL);
        assertThat(testBidPrjSubco.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjSubco.getMet06()).isEqualTo(DEFAULT_MET_06);
        assertThat(testBidPrjSubco.getMet07()).isEqualTo(DEFAULT_MET_07);
        assertThat(testBidPrjSubco.getMet08()).isEqualTo(DEFAULT_MET_08);
        assertThat(testBidPrjSubco.getMet09()).isEqualTo(DEFAULT_MET_09);
        assertThat(testBidPrjSubco.getMet10()).isEqualTo(DEFAULT_MET_10);
        assertThat(testBidPrjSubco.getMet11()).isEqualTo(DEFAULT_MET_11);
        assertThat(testBidPrjSubco.getMet12()).isEqualTo(DEFAULT_MET_12);
        assertThat(testBidPrjSubco.getMet13()).isEqualTo(DEFAULT_MET_13);
        assertThat(testBidPrjSubco.getMet14()).isEqualTo(DEFAULT_MET_14);
        assertThat(testBidPrjSubco.getMet15()).isEqualTo(DEFAULT_MET_15);
        assertThat(testBidPrjSubco.getMet16()).isEqualTo(DEFAULT_MET_16);
        assertThat(testBidPrjSubco.getMet99()).isEqualTo(DEFAULT_MET_99);
        assertThat(testBidPrjSubco.getMet06v()).isEqualTo(DEFAULT_MET_06_V);
        assertThat(testBidPrjSubco.getMet07v()).isEqualTo(DEFAULT_MET_07_V);
        assertThat(testBidPrjSubco.getMet08v()).isEqualTo(DEFAULT_MET_08_V);
        assertThat(testBidPrjSubco.getMet09v()).isEqualTo(DEFAULT_MET_09_V);
        assertThat(testBidPrjSubco.getMet10v()).isEqualTo(DEFAULT_MET_10_V);
        assertThat(testBidPrjSubco.getMet11v()).isEqualTo(DEFAULT_MET_11_V);
        assertThat(testBidPrjSubco.getMet12v()).isEqualTo(DEFAULT_MET_12_V);
        assertThat(testBidPrjSubco.getMet13v()).isEqualTo(DEFAULT_MET_13_V);
        assertThat(testBidPrjSubco.getMet14v()).isEqualTo(DEFAULT_MET_14_V);
        assertThat(testBidPrjSubco.getMet15v()).isEqualTo(DEFAULT_MET_15_V);
        assertThat(testBidPrjSubco.getMet16v()).isEqualTo(DEFAULT_MET_16_V);
        assertThat(testBidPrjSubco.getMet99v()).isEqualTo(DEFAULT_MET_99_V);
        assertThat(testBidPrjSubco.getMet99str()).isEqualTo(DEFAULT_MET_99_STR);
        assertThat(testBidPrjSubco.getConfa()).isEqualTo(DEFAULT_CONFA);
        assertThat(testBidPrjSubco.getConfb()).isEqualTo(DEFAULT_CONFB);
    }

    @Test
    @Transactional
    void createBidPrjSubcoWithExistingId() throws Exception {
        // Create the BidPrjSubco with an existing ID

        BidPrjSubcoDTO id = new BidPrjSubcoDTO();

        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(id.getWkut(),id.getPrjno(),id.getSubcoNo());


        bidPrjSubco.setId(bidPrjSubcoPrimaryKey);
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);

        int databaseSizeBeforeCreate = bidPrjSubcoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjSubcoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjSubcos() throws Exception {
        // Initialize the database
        bidPrjSubcoRepository.saveAndFlush(bidPrjSubco);

        // Get all the bidPrjSubcoList
        restBidPrjSubcoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjSubco.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].subcoNo").value(hasItem(DEFAULT_SUBCO_NO)))
            .andExpect(jsonPath("$.[*].subCcut").value(hasItem(DEFAULT_SUB_CCUT)))
            .andExpect(jsonPath("$.[*].coRegNo").value(hasItem(DEFAULT_CO_REG_NO)))
            .andExpect(jsonPath("$.[*].subBdgt1").value(hasItem(DEFAULT_SUB_BDGT_1.doubleValue())))
            .andExpect(jsonPath("$.[*].upCcut").value(hasItem(DEFAULT_UP_CCUT)))
            .andExpect(jsonPath("$.[*].stype").value(hasItem(DEFAULT_STYPE)))
            .andExpect(jsonPath("$.[*].spcw01").value(hasItem(DEFAULT_SPCW_01)))
            .andExpect(jsonPath("$.[*].spcw02").value(hasItem(DEFAULT_SPCW_02)))
            .andExpect(jsonPath("$.[*].spcw03").value(hasItem(DEFAULT_SPCW_03)))
            .andExpect(jsonPath("$.[*].spcw04").value(hasItem(DEFAULT_SPCW_04)))
            .andExpect(jsonPath("$.[*].spcw05").value(hasItem(DEFAULT_SPCW_05)))
            .andExpect(jsonPath("$.[*].spcw06").value(hasItem(DEFAULT_SPCW_06)))
            .andExpect(jsonPath("$.[*].spcw07").value(hasItem(DEFAULT_SPCW_07)))
            .andExpect(jsonPath("$.[*].spcw08").value(hasItem(DEFAULT_SPCW_08)))
            .andExpect(jsonPath("$.[*].spcw09").value(hasItem(DEFAULT_SPCW_09)))
            .andExpect(jsonPath("$.[*].spcw10").value(hasItem(DEFAULT_SPCW_10)))
            .andExpect(jsonPath("$.[*].spcw11").value(hasItem(DEFAULT_SPCW_11)))
            .andExpect(jsonPath("$.[*].spcw12").value(hasItem(DEFAULT_SPCW_12)))
            .andExpect(jsonPath("$.[*].subcoMemo").value(hasItem(DEFAULT_SUBCO_MEMO)))
            .andExpect(jsonPath("$.[*].met01").value(hasItem(DEFAULT_MET_01)))
            .andExpect(jsonPath("$.[*].met01b").value(hasItem(DEFAULT_MET_01_B)))
            .andExpect(jsonPath("$.[*].met01c").value(hasItem(DEFAULT_MET_01_C)))
            .andExpect(jsonPath("$.[*].met05").value(hasItem(DEFAULT_MET_05)))
            .andExpect(jsonPath("$.[*].met02f").value(hasItem(DEFAULT_MET_02_F)))
            .andExpect(jsonPath("$.[*].met01d").value(hasItem(DEFAULT_MET_01_D)))
            .andExpect(jsonPath("$.[*].met02").value(hasItem(DEFAULT_MET_02)))
            .andExpect(jsonPath("$.[*].met02b").value(hasItem(DEFAULT_MET_02_B)))
            .andExpect(jsonPath("$.[*].met04").value(hasItem(DEFAULT_MET_04)))
            .andExpect(jsonPath("$.[*].met03").value(hasItem(DEFAULT_MET_03)))
            .andExpect(jsonPath("$.[*].met03b").value(hasItem(DEFAULT_MET_03_B)))
            .andExpect(jsonPath("$.[*].met02c").value(hasItem(DEFAULT_MET_02_C)))
            .andExpect(jsonPath("$.[*].met02d").value(hasItem(DEFAULT_MET_02_D)))
            .andExpect(jsonPath("$.[*].met02e").value(hasItem(DEFAULT_MET_02_E)))
            .andExpect(jsonPath("$.[*].met01v").value(hasItem(DEFAULT_MET_01_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met01bv").value(hasItem(DEFAULT_MET_01_BV.doubleValue())))
            .andExpect(jsonPath("$.[*].met01cv").value(hasItem(DEFAULT_MET_01_CV.doubleValue())))
            .andExpect(jsonPath("$.[*].met05v").value(hasItem(DEFAULT_MET_05_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met02fv").value(hasItem(DEFAULT_MET_02_FV.doubleValue())))
            .andExpect(jsonPath("$.[*].met01dv").value(hasItem(DEFAULT_MET_01_DV.doubleValue())))
            .andExpect(jsonPath("$.[*].met02v").value(hasItem(DEFAULT_MET_02_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met02bv").value(hasItem(DEFAULT_MET_02_BV.doubleValue())))
            .andExpect(jsonPath("$.[*].met04v").value(hasItem(DEFAULT_MET_04_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met03v").value(hasItem(DEFAULT_MET_03_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met03bv").value(hasItem(DEFAULT_MET_03_BV.doubleValue())))
            .andExpect(jsonPath("$.[*].met02cv").value(hasItem(DEFAULT_MET_02_CV.doubleValue())))
            .andExpect(jsonPath("$.[*].met02dv").value(hasItem(DEFAULT_MET_02_DV.doubleValue())))
            .andExpect(jsonPath("$.[*].met02ev").value(hasItem(DEFAULT_MET_02_EV.doubleValue())))
            .andExpect(jsonPath("$.[*].qchk").value(hasItem(DEFAULT_QCHK)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].rmtfil").value(hasItem(DEFAULT_RMTFIL)))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
            .andExpect(jsonPath("$.[*].met06").value(hasItem(DEFAULT_MET_06)))
            .andExpect(jsonPath("$.[*].met07").value(hasItem(DEFAULT_MET_07)))
            .andExpect(jsonPath("$.[*].met08").value(hasItem(DEFAULT_MET_08)))
            .andExpect(jsonPath("$.[*].met09").value(hasItem(DEFAULT_MET_09)))
            .andExpect(jsonPath("$.[*].met10").value(hasItem(DEFAULT_MET_10)))
            .andExpect(jsonPath("$.[*].met11").value(hasItem(DEFAULT_MET_11)))
            .andExpect(jsonPath("$.[*].met12").value(hasItem(DEFAULT_MET_12)))
            .andExpect(jsonPath("$.[*].met13").value(hasItem(DEFAULT_MET_13)))
            .andExpect(jsonPath("$.[*].met14").value(hasItem(DEFAULT_MET_14)))
            .andExpect(jsonPath("$.[*].met15").value(hasItem(DEFAULT_MET_15)))
            .andExpect(jsonPath("$.[*].met16").value(hasItem(DEFAULT_MET_16)))
            .andExpect(jsonPath("$.[*].met99").value(hasItem(DEFAULT_MET_99)))
            .andExpect(jsonPath("$.[*].met06v").value(hasItem(DEFAULT_MET_06_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met07v").value(hasItem(DEFAULT_MET_07_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met08v").value(hasItem(DEFAULT_MET_08_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met09v").value(hasItem(DEFAULT_MET_09_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met10v").value(hasItem(DEFAULT_MET_10_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met11v").value(hasItem(DEFAULT_MET_11_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met12v").value(hasItem(DEFAULT_MET_12_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met13v").value(hasItem(DEFAULT_MET_13_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met14v").value(hasItem(DEFAULT_MET_14_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met15v").value(hasItem(DEFAULT_MET_15_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met16v").value(hasItem(DEFAULT_MET_16_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met99v").value(hasItem(DEFAULT_MET_99_V.doubleValue())))
            .andExpect(jsonPath("$.[*].met99str").value(hasItem(DEFAULT_MET_99_STR)))
            .andExpect(jsonPath("$.[*].confa").value(hasItem(DEFAULT_CONFA)))
            .andExpect(jsonPath("$.[*].confb").value(hasItem(DEFAULT_CONFB)));
    }

    @Test
    @Transactional
    void getBidPrjSubco() throws Exception {
        // Initialize the database
        bidPrjSubcoRepository.saveAndFlush(bidPrjSubco);

        // Get the bidPrjSubco
        restBidPrjSubcoMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjSubco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjSubco.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.subcoNo").value(DEFAULT_SUBCO_NO))
            .andExpect(jsonPath("$.subCcut").value(DEFAULT_SUB_CCUT))
            .andExpect(jsonPath("$.coRegNo").value(DEFAULT_CO_REG_NO))
            .andExpect(jsonPath("$.subBdgt1").value(DEFAULT_SUB_BDGT_1.doubleValue()))
            .andExpect(jsonPath("$.upCcut").value(DEFAULT_UP_CCUT))
            .andExpect(jsonPath("$.stype").value(DEFAULT_STYPE))
            .andExpect(jsonPath("$.spcw01").value(DEFAULT_SPCW_01))
            .andExpect(jsonPath("$.spcw02").value(DEFAULT_SPCW_02))
            .andExpect(jsonPath("$.spcw03").value(DEFAULT_SPCW_03))
            .andExpect(jsonPath("$.spcw04").value(DEFAULT_SPCW_04))
            .andExpect(jsonPath("$.spcw05").value(DEFAULT_SPCW_05))
            .andExpect(jsonPath("$.spcw06").value(DEFAULT_SPCW_06))
            .andExpect(jsonPath("$.spcw07").value(DEFAULT_SPCW_07))
            .andExpect(jsonPath("$.spcw08").value(DEFAULT_SPCW_08))
            .andExpect(jsonPath("$.spcw09").value(DEFAULT_SPCW_09))
            .andExpect(jsonPath("$.spcw10").value(DEFAULT_SPCW_10))
            .andExpect(jsonPath("$.spcw11").value(DEFAULT_SPCW_11))
            .andExpect(jsonPath("$.spcw12").value(DEFAULT_SPCW_12))
            .andExpect(jsonPath("$.subcoMemo").value(DEFAULT_SUBCO_MEMO))
            .andExpect(jsonPath("$.met01").value(DEFAULT_MET_01))
            .andExpect(jsonPath("$.met01b").value(DEFAULT_MET_01_B))
            .andExpect(jsonPath("$.met01c").value(DEFAULT_MET_01_C))
            .andExpect(jsonPath("$.met05").value(DEFAULT_MET_05))
            .andExpect(jsonPath("$.met02f").value(DEFAULT_MET_02_F))
            .andExpect(jsonPath("$.met01d").value(DEFAULT_MET_01_D))
            .andExpect(jsonPath("$.met02").value(DEFAULT_MET_02))
            .andExpect(jsonPath("$.met02b").value(DEFAULT_MET_02_B))
            .andExpect(jsonPath("$.met04").value(DEFAULT_MET_04))
            .andExpect(jsonPath("$.met03").value(DEFAULT_MET_03))
            .andExpect(jsonPath("$.met03b").value(DEFAULT_MET_03_B))
            .andExpect(jsonPath("$.met02c").value(DEFAULT_MET_02_C))
            .andExpect(jsonPath("$.met02d").value(DEFAULT_MET_02_D))
            .andExpect(jsonPath("$.met02e").value(DEFAULT_MET_02_E))
            .andExpect(jsonPath("$.met01v").value(DEFAULT_MET_01_V.doubleValue()))
            .andExpect(jsonPath("$.met01bv").value(DEFAULT_MET_01_BV.doubleValue()))
            .andExpect(jsonPath("$.met01cv").value(DEFAULT_MET_01_CV.doubleValue()))
            .andExpect(jsonPath("$.met05v").value(DEFAULT_MET_05_V.doubleValue()))
            .andExpect(jsonPath("$.met02fv").value(DEFAULT_MET_02_FV.doubleValue()))
            .andExpect(jsonPath("$.met01dv").value(DEFAULT_MET_01_DV.doubleValue()))
            .andExpect(jsonPath("$.met02v").value(DEFAULT_MET_02_V.doubleValue()))
            .andExpect(jsonPath("$.met02bv").value(DEFAULT_MET_02_BV.doubleValue()))
            .andExpect(jsonPath("$.met04v").value(DEFAULT_MET_04_V.doubleValue()))
            .andExpect(jsonPath("$.met03v").value(DEFAULT_MET_03_V.doubleValue()))
            .andExpect(jsonPath("$.met03bv").value(DEFAULT_MET_03_BV.doubleValue()))
            .andExpect(jsonPath("$.met02cv").value(DEFAULT_MET_02_CV.doubleValue()))
            .andExpect(jsonPath("$.met02dv").value(DEFAULT_MET_02_DV.doubleValue()))
            .andExpect(jsonPath("$.met02ev").value(DEFAULT_MET_02_EV.doubleValue()))
            .andExpect(jsonPath("$.qchk").value(DEFAULT_QCHK))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.rmtfil").value(DEFAULT_RMTFIL))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
            .andExpect(jsonPath("$.met06").value(DEFAULT_MET_06))
            .andExpect(jsonPath("$.met07").value(DEFAULT_MET_07))
            .andExpect(jsonPath("$.met08").value(DEFAULT_MET_08))
            .andExpect(jsonPath("$.met09").value(DEFAULT_MET_09))
            .andExpect(jsonPath("$.met10").value(DEFAULT_MET_10))
            .andExpect(jsonPath("$.met11").value(DEFAULT_MET_11))
            .andExpect(jsonPath("$.met12").value(DEFAULT_MET_12))
            .andExpect(jsonPath("$.met13").value(DEFAULT_MET_13))
            .andExpect(jsonPath("$.met14").value(DEFAULT_MET_14))
            .andExpect(jsonPath("$.met15").value(DEFAULT_MET_15))
            .andExpect(jsonPath("$.met16").value(DEFAULT_MET_16))
            .andExpect(jsonPath("$.met99").value(DEFAULT_MET_99))
            .andExpect(jsonPath("$.met06v").value(DEFAULT_MET_06_V.doubleValue()))
            .andExpect(jsonPath("$.met07v").value(DEFAULT_MET_07_V.doubleValue()))
            .andExpect(jsonPath("$.met08v").value(DEFAULT_MET_08_V.doubleValue()))
            .andExpect(jsonPath("$.met09v").value(DEFAULT_MET_09_V.doubleValue()))
            .andExpect(jsonPath("$.met10v").value(DEFAULT_MET_10_V.doubleValue()))
            .andExpect(jsonPath("$.met11v").value(DEFAULT_MET_11_V.doubleValue()))
            .andExpect(jsonPath("$.met12v").value(DEFAULT_MET_12_V.doubleValue()))
            .andExpect(jsonPath("$.met13v").value(DEFAULT_MET_13_V.doubleValue()))
            .andExpect(jsonPath("$.met14v").value(DEFAULT_MET_14_V.doubleValue()))
            .andExpect(jsonPath("$.met15v").value(DEFAULT_MET_15_V.doubleValue()))
            .andExpect(jsonPath("$.met16v").value(DEFAULT_MET_16_V.doubleValue()))
            .andExpect(jsonPath("$.met99v").value(DEFAULT_MET_99_V.doubleValue()))
            .andExpect(jsonPath("$.met99str").value(DEFAULT_MET_99_STR))
            .andExpect(jsonPath("$.confa").value(DEFAULT_CONFA))
            .andExpect(jsonPath("$.confb").value(DEFAULT_CONFB));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjSubco() throws Exception {
        // Get the bidPrjSubco
        restBidPrjSubcoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjSubco() throws Exception {
        // Initialize the database
        bidPrjSubcoRepository.saveAndFlush(bidPrjSubco);

        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();

        // Update the bidPrjSubco
        BidPrjSubco updatedBidPrjSubco = bidPrjSubcoRepository.findById(bidPrjSubco.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjSubco are not directly saved in db
        em.detach(updatedBidPrjSubco);
        updatedBidPrjSubco
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .subcoNo(UPDATED_SUBCO_NO)
            .subCcut(UPDATED_SUB_CCUT)
            .coRegNo(UPDATED_CO_REG_NO)
            .subBdgt1(UPDATED_SUB_BDGT_1)
            .upCcut(UPDATED_UP_CCUT)
            .stype(UPDATED_STYPE)
            .spcw01(UPDATED_SPCW_01)
            .spcw02(UPDATED_SPCW_02)
            .spcw03(UPDATED_SPCW_03)
            .spcw04(UPDATED_SPCW_04)
            .spcw05(UPDATED_SPCW_05)
            .spcw06(UPDATED_SPCW_06)
            .spcw07(UPDATED_SPCW_07)
            .spcw08(UPDATED_SPCW_08)
            .spcw09(UPDATED_SPCW_09)
            .spcw10(UPDATED_SPCW_10)
            .spcw11(UPDATED_SPCW_11)
            .spcw12(UPDATED_SPCW_12)
            .subcoMemo(UPDATED_SUBCO_MEMO)
            .met01(UPDATED_MET_01)
            .met01b(UPDATED_MET_01_B)
            .met01c(UPDATED_MET_01_C)
            .met05(UPDATED_MET_05)
            .met02f(UPDATED_MET_02_F)
            .met01d(UPDATED_MET_01_D)
            .met02(UPDATED_MET_02)
            .met02b(UPDATED_MET_02_B)
            .met04(UPDATED_MET_04)
            .met03(UPDATED_MET_03)
            .met03b(UPDATED_MET_03_B)
            .met02c(UPDATED_MET_02_C)
            .met02d(UPDATED_MET_02_D)
            .met02e(UPDATED_MET_02_E)
            .met01v(UPDATED_MET_01_V)
            .met01bv(UPDATED_MET_01_BV)
            .met01cv(UPDATED_MET_01_CV)
            .met05v(UPDATED_MET_05_V)
            .met02fv(UPDATED_MET_02_FV)
            .met01dv(UPDATED_MET_01_DV)
            .met02v(UPDATED_MET_02_V)
            .met02bv(UPDATED_MET_02_BV)
            .met04v(UPDATED_MET_04_V)
            .met03v(UPDATED_MET_03_V)
            .met03bv(UPDATED_MET_03_BV)
            .met02cv(UPDATED_MET_02_CV)
            .met02dv(UPDATED_MET_02_DV)
            .met02ev(UPDATED_MET_02_EV)
            .qchk(UPDATED_QCHK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .rmtfil(UPDATED_RMTFIL)
            .uIp(UPDATED_U_IP)
            .met06(UPDATED_MET_06)
            .met07(UPDATED_MET_07)
            .met08(UPDATED_MET_08)
            .met09(UPDATED_MET_09)
            .met10(UPDATED_MET_10)
            .met11(UPDATED_MET_11)
            .met12(UPDATED_MET_12)
            .met13(UPDATED_MET_13)
            .met14(UPDATED_MET_14)
            .met15(UPDATED_MET_15)
            .met16(UPDATED_MET_16)
            .met99(UPDATED_MET_99)
            .met06v(UPDATED_MET_06_V)
            .met07v(UPDATED_MET_07_V)
            .met08v(UPDATED_MET_08_V)
            .met09v(UPDATED_MET_09_V)
            .met10v(UPDATED_MET_10_V)
            .met11v(UPDATED_MET_11_V)
            .met12v(UPDATED_MET_12_V)
            .met13v(UPDATED_MET_13_V)
            .met14v(UPDATED_MET_14_V)
            .met15v(UPDATED_MET_15_V)
            .met16v(UPDATED_MET_16_V)
            .met99v(UPDATED_MET_99_V)
            .met99str(UPDATED_MET_99_STR)
            .confa(UPDATED_CONFA)
            .confb(UPDATED_CONFB);
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(updatedBidPrjSubco);

        restBidPrjSubcoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjSubcoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSubco testBidPrjSubco = bidPrjSubcoList.get(bidPrjSubcoList.size() - 1);
        assertThat(testBidPrjSubco.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSubco.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSubco.getSubcoNo()).isEqualTo(UPDATED_SUBCO_NO);
        assertThat(testBidPrjSubco.getSubCcut()).isEqualTo(UPDATED_SUB_CCUT);
        assertThat(testBidPrjSubco.getCoRegNo()).isEqualTo(UPDATED_CO_REG_NO);
        assertThat(testBidPrjSubco.getSubBdgt1()).isEqualTo(UPDATED_SUB_BDGT_1);
        assertThat(testBidPrjSubco.getUpCcut()).isEqualTo(UPDATED_UP_CCUT);
        assertThat(testBidPrjSubco.getStype()).isEqualTo(UPDATED_STYPE);
        assertThat(testBidPrjSubco.getSpcw01()).isEqualTo(UPDATED_SPCW_01);
        assertThat(testBidPrjSubco.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
        assertThat(testBidPrjSubco.getSpcw03()).isEqualTo(UPDATED_SPCW_03);
        assertThat(testBidPrjSubco.getSpcw04()).isEqualTo(UPDATED_SPCW_04);
        assertThat(testBidPrjSubco.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
        assertThat(testBidPrjSubco.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
        assertThat(testBidPrjSubco.getSpcw07()).isEqualTo(UPDATED_SPCW_07);
        assertThat(testBidPrjSubco.getSpcw08()).isEqualTo(UPDATED_SPCW_08);
        assertThat(testBidPrjSubco.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
        assertThat(testBidPrjSubco.getSpcw10()).isEqualTo(UPDATED_SPCW_10);
        assertThat(testBidPrjSubco.getSpcw11()).isEqualTo(UPDATED_SPCW_11);
        assertThat(testBidPrjSubco.getSpcw12()).isEqualTo(UPDATED_SPCW_12);
        assertThat(testBidPrjSubco.getSubcoMemo()).isEqualTo(UPDATED_SUBCO_MEMO);
        assertThat(testBidPrjSubco.getMet01()).isEqualTo(UPDATED_MET_01);
        assertThat(testBidPrjSubco.getMet01b()).isEqualTo(UPDATED_MET_01_B);
        assertThat(testBidPrjSubco.getMet01c()).isEqualTo(UPDATED_MET_01_C);
        assertThat(testBidPrjSubco.getMet05()).isEqualTo(UPDATED_MET_05);
        assertThat(testBidPrjSubco.getMet02f()).isEqualTo(UPDATED_MET_02_F);
        assertThat(testBidPrjSubco.getMet01d()).isEqualTo(UPDATED_MET_01_D);
        assertThat(testBidPrjSubco.getMet02()).isEqualTo(UPDATED_MET_02);
        assertThat(testBidPrjSubco.getMet02b()).isEqualTo(UPDATED_MET_02_B);
        assertThat(testBidPrjSubco.getMet04()).isEqualTo(UPDATED_MET_04);
        assertThat(testBidPrjSubco.getMet03()).isEqualTo(UPDATED_MET_03);
        assertThat(testBidPrjSubco.getMet03b()).isEqualTo(UPDATED_MET_03_B);
        assertThat(testBidPrjSubco.getMet02c()).isEqualTo(UPDATED_MET_02_C);
        assertThat(testBidPrjSubco.getMet02d()).isEqualTo(UPDATED_MET_02_D);
        assertThat(testBidPrjSubco.getMet02e()).isEqualTo(UPDATED_MET_02_E);
        assertThat(testBidPrjSubco.getMet01v()).isEqualTo(UPDATED_MET_01_V);
        assertThat(testBidPrjSubco.getMet01bv()).isEqualTo(UPDATED_MET_01_BV);
        assertThat(testBidPrjSubco.getMet01cv()).isEqualTo(UPDATED_MET_01_CV);
        assertThat(testBidPrjSubco.getMet05v()).isEqualTo(UPDATED_MET_05_V);
        assertThat(testBidPrjSubco.getMet02fv()).isEqualTo(UPDATED_MET_02_FV);
        assertThat(testBidPrjSubco.getMet01dv()).isEqualTo(UPDATED_MET_01_DV);
        assertThat(testBidPrjSubco.getMet02v()).isEqualTo(UPDATED_MET_02_V);
        assertThat(testBidPrjSubco.getMet02bv()).isEqualTo(UPDATED_MET_02_BV);
        assertThat(testBidPrjSubco.getMet04v()).isEqualTo(UPDATED_MET_04_V);
        assertThat(testBidPrjSubco.getMet03v()).isEqualTo(UPDATED_MET_03_V);
        assertThat(testBidPrjSubco.getMet03bv()).isEqualTo(UPDATED_MET_03_BV);
        assertThat(testBidPrjSubco.getMet02cv()).isEqualTo(UPDATED_MET_02_CV);
        assertThat(testBidPrjSubco.getMet02dv()).isEqualTo(UPDATED_MET_02_DV);
        assertThat(testBidPrjSubco.getMet02ev()).isEqualTo(UPDATED_MET_02_EV);
        assertThat(testBidPrjSubco.getQchk()).isEqualTo(UPDATED_QCHK);
        assertThat(testBidPrjSubco.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSubco.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSubco.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSubco.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSubco.getRmtfil()).isEqualTo(UPDATED_RMTFIL);
        assertThat(testBidPrjSubco.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjSubco.getMet06()).isEqualTo(UPDATED_MET_06);
        assertThat(testBidPrjSubco.getMet07()).isEqualTo(UPDATED_MET_07);
        assertThat(testBidPrjSubco.getMet08()).isEqualTo(UPDATED_MET_08);
        assertThat(testBidPrjSubco.getMet09()).isEqualTo(UPDATED_MET_09);
        assertThat(testBidPrjSubco.getMet10()).isEqualTo(UPDATED_MET_10);
        assertThat(testBidPrjSubco.getMet11()).isEqualTo(UPDATED_MET_11);
        assertThat(testBidPrjSubco.getMet12()).isEqualTo(UPDATED_MET_12);
        assertThat(testBidPrjSubco.getMet13()).isEqualTo(UPDATED_MET_13);
        assertThat(testBidPrjSubco.getMet14()).isEqualTo(UPDATED_MET_14);
        assertThat(testBidPrjSubco.getMet15()).isEqualTo(UPDATED_MET_15);
        assertThat(testBidPrjSubco.getMet16()).isEqualTo(UPDATED_MET_16);
        assertThat(testBidPrjSubco.getMet99()).isEqualTo(UPDATED_MET_99);
        assertThat(testBidPrjSubco.getMet06v()).isEqualTo(UPDATED_MET_06_V);
        assertThat(testBidPrjSubco.getMet07v()).isEqualTo(UPDATED_MET_07_V);
        assertThat(testBidPrjSubco.getMet08v()).isEqualTo(UPDATED_MET_08_V);
        assertThat(testBidPrjSubco.getMet09v()).isEqualTo(UPDATED_MET_09_V);
        assertThat(testBidPrjSubco.getMet10v()).isEqualTo(UPDATED_MET_10_V);
        assertThat(testBidPrjSubco.getMet11v()).isEqualTo(UPDATED_MET_11_V);
        assertThat(testBidPrjSubco.getMet12v()).isEqualTo(UPDATED_MET_12_V);
        assertThat(testBidPrjSubco.getMet13v()).isEqualTo(UPDATED_MET_13_V);
        assertThat(testBidPrjSubco.getMet14v()).isEqualTo(UPDATED_MET_14_V);
        assertThat(testBidPrjSubco.getMet15v()).isEqualTo(UPDATED_MET_15_V);
        assertThat(testBidPrjSubco.getMet16v()).isEqualTo(UPDATED_MET_16_V);
        assertThat(testBidPrjSubco.getMet99v()).isEqualTo(UPDATED_MET_99_V);
        assertThat(testBidPrjSubco.getMet99str()).isEqualTo(UPDATED_MET_99_STR);
        assertThat(testBidPrjSubco.getConfa()).isEqualTo(UPDATED_CONFA);
        assertThat(testBidPrjSubco.getConfb()).isEqualTo(UPDATED_CONFB);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjSubco() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();
        BidPrjSubcoDTO id = new BidPrjSubcoDTO();

        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(id.getWkut(),id.getPrjno(),id.getSubcoNo());

        bidPrjSubco.setId(bidPrjSubcoPrimaryKey);

        // Create the BidPrjSubco
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBidPrjSubcoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjSubcoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjSubco() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();

        BidPrjSubcoDTO id = new BidPrjSubcoDTO();

        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(id.getWkut(),id.getPrjno(),id.getSubcoNo());


        bidPrjSubco.setId(bidPrjSubcoPrimaryKey);

        // Create the BidPrjSubco
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjSubcoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjSubco() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();


        BidPrjSubcoDTO id = new BidPrjSubcoDTO();

        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(id.getWkut(),id.getPrjno(),id.getSubcoNo());

        bidPrjSubco.setId(bidPrjSubcoPrimaryKey);

        // Create the BidPrjSubco
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjSubcoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjSubcoWithPatch() throws Exception {
        // Initialize the database
        bidPrjSubcoRepository.saveAndFlush(bidPrjSubco);

        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();

        // Update the bidPrjSubco using partial update
        BidPrjSubco partialUpdatedBidPrjSubco = new BidPrjSubco();
        partialUpdatedBidPrjSubco.setId(bidPrjSubco.getId());

        partialUpdatedBidPrjSubco
            .prjno(UPDATED_PRJNO)
            .subcoNo(UPDATED_SUBCO_NO)
            .subCcut(UPDATED_SUB_CCUT)
            .coRegNo(UPDATED_CO_REG_NO)
            .upCcut(UPDATED_UP_CCUT)
            .spcw02(UPDATED_SPCW_02)
            .spcw05(UPDATED_SPCW_05)
            .spcw06(UPDATED_SPCW_06)
            .spcw08(UPDATED_SPCW_08)
            .subcoMemo(UPDATED_SUBCO_MEMO)
            .met01(UPDATED_MET_01)
            .met02f(UPDATED_MET_02_F)
            .met04(UPDATED_MET_04)
            .met02d(UPDATED_MET_02_D)
            .met02e(UPDATED_MET_02_E)
            .met01v(UPDATED_MET_01_V)
            .met02v(UPDATED_MET_02_V)
            .met03bv(UPDATED_MET_03_BV)
            .met02ev(UPDATED_MET_02_EV)
            .qchk(UPDATED_QCHK)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .rmtfil(UPDATED_RMTFIL)
            .met06(UPDATED_MET_06)
            .met07(UPDATED_MET_07)
            .met09(UPDATED_MET_09)
            .met14(UPDATED_MET_14)
            .met16(UPDATED_MET_16)
            .met99(UPDATED_MET_99)
            .met06v(UPDATED_MET_06_V)
            .met09v(UPDATED_MET_09_V)
            .met10v(UPDATED_MET_10_V)
            .met11v(UPDATED_MET_11_V)
            .met14v(UPDATED_MET_14_V)
            .met15v(UPDATED_MET_15_V)
            .met99str(UPDATED_MET_99_STR)
            .confa(UPDATED_CONFA)
            .confb(UPDATED_CONFB);

        restBidPrjSubcoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSubco.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSubco))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSubco testBidPrjSubco = bidPrjSubcoList.get(bidPrjSubcoList.size() - 1);
        assertThat(testBidPrjSubco.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjSubco.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSubco.getSubcoNo()).isEqualTo(UPDATED_SUBCO_NO);
        assertThat(testBidPrjSubco.getSubCcut()).isEqualTo(UPDATED_SUB_CCUT);
        assertThat(testBidPrjSubco.getCoRegNo()).isEqualTo(UPDATED_CO_REG_NO);
        assertThat(testBidPrjSubco.getSubBdgt1()).isEqualTo(DEFAULT_SUB_BDGT_1);
        assertThat(testBidPrjSubco.getUpCcut()).isEqualTo(UPDATED_UP_CCUT);
        assertThat(testBidPrjSubco.getStype()).isEqualTo(DEFAULT_STYPE);
        assertThat(testBidPrjSubco.getSpcw01()).isEqualTo(DEFAULT_SPCW_01);
        assertThat(testBidPrjSubco.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
        assertThat(testBidPrjSubco.getSpcw03()).isEqualTo(DEFAULT_SPCW_03);
        assertThat(testBidPrjSubco.getSpcw04()).isEqualTo(DEFAULT_SPCW_04);
        assertThat(testBidPrjSubco.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
        assertThat(testBidPrjSubco.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
        assertThat(testBidPrjSubco.getSpcw07()).isEqualTo(DEFAULT_SPCW_07);
        assertThat(testBidPrjSubco.getSpcw08()).isEqualTo(UPDATED_SPCW_08);
        assertThat(testBidPrjSubco.getSpcw09()).isEqualTo(DEFAULT_SPCW_09);
        assertThat(testBidPrjSubco.getSpcw10()).isEqualTo(DEFAULT_SPCW_10);
        assertThat(testBidPrjSubco.getSpcw11()).isEqualTo(DEFAULT_SPCW_11);
        assertThat(testBidPrjSubco.getSpcw12()).isEqualTo(DEFAULT_SPCW_12);
        assertThat(testBidPrjSubco.getSubcoMemo()).isEqualTo(UPDATED_SUBCO_MEMO);
        assertThat(testBidPrjSubco.getMet01()).isEqualTo(UPDATED_MET_01);
        assertThat(testBidPrjSubco.getMet01b()).isEqualTo(DEFAULT_MET_01_B);
        assertThat(testBidPrjSubco.getMet01c()).isEqualTo(DEFAULT_MET_01_C);
        assertThat(testBidPrjSubco.getMet05()).isEqualTo(DEFAULT_MET_05);
        assertThat(testBidPrjSubco.getMet02f()).isEqualTo(UPDATED_MET_02_F);
        assertThat(testBidPrjSubco.getMet01d()).isEqualTo(DEFAULT_MET_01_D);
        assertThat(testBidPrjSubco.getMet02()).isEqualTo(DEFAULT_MET_02);
        assertThat(testBidPrjSubco.getMet02b()).isEqualTo(DEFAULT_MET_02_B);
        assertThat(testBidPrjSubco.getMet04()).isEqualTo(UPDATED_MET_04);
        assertThat(testBidPrjSubco.getMet03()).isEqualTo(DEFAULT_MET_03);
        assertThat(testBidPrjSubco.getMet03b()).isEqualTo(DEFAULT_MET_03_B);
        assertThat(testBidPrjSubco.getMet02c()).isEqualTo(DEFAULT_MET_02_C);
        assertThat(testBidPrjSubco.getMet02d()).isEqualTo(UPDATED_MET_02_D);
        assertThat(testBidPrjSubco.getMet02e()).isEqualTo(UPDATED_MET_02_E);
        assertThat(testBidPrjSubco.getMet01v()).isEqualTo(UPDATED_MET_01_V);
        assertThat(testBidPrjSubco.getMet01bv()).isEqualTo(DEFAULT_MET_01_BV);
        assertThat(testBidPrjSubco.getMet01cv()).isEqualTo(DEFAULT_MET_01_CV);
        assertThat(testBidPrjSubco.getMet05v()).isEqualTo(DEFAULT_MET_05_V);
        assertThat(testBidPrjSubco.getMet02fv()).isEqualTo(DEFAULT_MET_02_FV);
        assertThat(testBidPrjSubco.getMet01dv()).isEqualTo(DEFAULT_MET_01_DV);
        assertThat(testBidPrjSubco.getMet02v()).isEqualTo(UPDATED_MET_02_V);
        assertThat(testBidPrjSubco.getMet02bv()).isEqualTo(DEFAULT_MET_02_BV);
        assertThat(testBidPrjSubco.getMet04v()).isEqualTo(DEFAULT_MET_04_V);
        assertThat(testBidPrjSubco.getMet03v()).isEqualTo(DEFAULT_MET_03_V);
        assertThat(testBidPrjSubco.getMet03bv()).isEqualTo(UPDATED_MET_03_BV);
        assertThat(testBidPrjSubco.getMet02cv()).isEqualTo(DEFAULT_MET_02_CV);
        assertThat(testBidPrjSubco.getMet02dv()).isEqualTo(DEFAULT_MET_02_DV);
        assertThat(testBidPrjSubco.getMet02ev()).isEqualTo(UPDATED_MET_02_EV);
        assertThat(testBidPrjSubco.getQchk()).isEqualTo(UPDATED_QCHK);
        assertThat(testBidPrjSubco.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjSubco.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSubco.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSubco.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSubco.getRmtfil()).isEqualTo(UPDATED_RMTFIL);
        assertThat(testBidPrjSubco.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjSubco.getMet06()).isEqualTo(UPDATED_MET_06);
        assertThat(testBidPrjSubco.getMet07()).isEqualTo(UPDATED_MET_07);
        assertThat(testBidPrjSubco.getMet08()).isEqualTo(DEFAULT_MET_08);
        assertThat(testBidPrjSubco.getMet09()).isEqualTo(UPDATED_MET_09);
        assertThat(testBidPrjSubco.getMet10()).isEqualTo(DEFAULT_MET_10);
        assertThat(testBidPrjSubco.getMet11()).isEqualTo(DEFAULT_MET_11);
        assertThat(testBidPrjSubco.getMet12()).isEqualTo(DEFAULT_MET_12);
        assertThat(testBidPrjSubco.getMet13()).isEqualTo(DEFAULT_MET_13);
        assertThat(testBidPrjSubco.getMet14()).isEqualTo(UPDATED_MET_14);
        assertThat(testBidPrjSubco.getMet15()).isEqualTo(DEFAULT_MET_15);
        assertThat(testBidPrjSubco.getMet16()).isEqualTo(UPDATED_MET_16);
        assertThat(testBidPrjSubco.getMet99()).isEqualTo(UPDATED_MET_99);
        assertThat(testBidPrjSubco.getMet06v()).isEqualTo(UPDATED_MET_06_V);
        assertThat(testBidPrjSubco.getMet07v()).isEqualTo(DEFAULT_MET_07_V);
        assertThat(testBidPrjSubco.getMet08v()).isEqualTo(DEFAULT_MET_08_V);
        assertThat(testBidPrjSubco.getMet09v()).isEqualTo(UPDATED_MET_09_V);
        assertThat(testBidPrjSubco.getMet10v()).isEqualTo(UPDATED_MET_10_V);
        assertThat(testBidPrjSubco.getMet11v()).isEqualTo(UPDATED_MET_11_V);
        assertThat(testBidPrjSubco.getMet12v()).isEqualTo(DEFAULT_MET_12_V);
        assertThat(testBidPrjSubco.getMet13v()).isEqualTo(DEFAULT_MET_13_V);
        assertThat(testBidPrjSubco.getMet14v()).isEqualTo(UPDATED_MET_14_V);
        assertThat(testBidPrjSubco.getMet15v()).isEqualTo(UPDATED_MET_15_V);
        assertThat(testBidPrjSubco.getMet16v()).isEqualTo(DEFAULT_MET_16_V);
        assertThat(testBidPrjSubco.getMet99v()).isEqualTo(DEFAULT_MET_99_V);
        assertThat(testBidPrjSubco.getMet99str()).isEqualTo(UPDATED_MET_99_STR);
        assertThat(testBidPrjSubco.getConfa()).isEqualTo(UPDATED_CONFA);
        assertThat(testBidPrjSubco.getConfb()).isEqualTo(UPDATED_CONFB);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjSubcoWithPatch() throws Exception {
        // Initialize the database
        bidPrjSubcoRepository.saveAndFlush(bidPrjSubco);

        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();

        // Update the bidPrjSubco using partial update
        BidPrjSubco partialUpdatedBidPrjSubco = new BidPrjSubco();
        partialUpdatedBidPrjSubco.setId(bidPrjSubco.getId());

        partialUpdatedBidPrjSubco
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .subcoNo(UPDATED_SUBCO_NO)
            .subCcut(UPDATED_SUB_CCUT)
            .coRegNo(UPDATED_CO_REG_NO)
            .subBdgt1(UPDATED_SUB_BDGT_1)
            .upCcut(UPDATED_UP_CCUT)
            .stype(UPDATED_STYPE)
            .spcw01(UPDATED_SPCW_01)
            .spcw02(UPDATED_SPCW_02)
            .spcw03(UPDATED_SPCW_03)
            .spcw04(UPDATED_SPCW_04)
            .spcw05(UPDATED_SPCW_05)
            .spcw06(UPDATED_SPCW_06)
            .spcw07(UPDATED_SPCW_07)
            .spcw08(UPDATED_SPCW_08)
            .spcw09(UPDATED_SPCW_09)
            .spcw10(UPDATED_SPCW_10)
            .spcw11(UPDATED_SPCW_11)
            .spcw12(UPDATED_SPCW_12)
            .subcoMemo(UPDATED_SUBCO_MEMO)
            .met01(UPDATED_MET_01)
            .met01b(UPDATED_MET_01_B)
            .met01c(UPDATED_MET_01_C)
            .met05(UPDATED_MET_05)
            .met02f(UPDATED_MET_02_F)
            .met01d(UPDATED_MET_01_D)
            .met02(UPDATED_MET_02)
            .met02b(UPDATED_MET_02_B)
            .met04(UPDATED_MET_04)
            .met03(UPDATED_MET_03)
            .met03b(UPDATED_MET_03_B)
            .met02c(UPDATED_MET_02_C)
            .met02d(UPDATED_MET_02_D)
            .met02e(UPDATED_MET_02_E)
            .met01v(UPDATED_MET_01_V)
            .met01bv(UPDATED_MET_01_BV)
            .met01cv(UPDATED_MET_01_CV)
            .met05v(UPDATED_MET_05_V)
            .met02fv(UPDATED_MET_02_FV)
            .met01dv(UPDATED_MET_01_DV)
            .met02v(UPDATED_MET_02_V)
            .met02bv(UPDATED_MET_02_BV)
            .met04v(UPDATED_MET_04_V)
            .met03v(UPDATED_MET_03_V)
            .met03bv(UPDATED_MET_03_BV)
            .met02cv(UPDATED_MET_02_CV)
            .met02dv(UPDATED_MET_02_DV)
            .met02ev(UPDATED_MET_02_EV)
            .qchk(UPDATED_QCHK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .rmtfil(UPDATED_RMTFIL)
            .uIp(UPDATED_U_IP)
            .met06(UPDATED_MET_06)
            .met07(UPDATED_MET_07)
            .met08(UPDATED_MET_08)
            .met09(UPDATED_MET_09)
            .met10(UPDATED_MET_10)
            .met11(UPDATED_MET_11)
            .met12(UPDATED_MET_12)
            .met13(UPDATED_MET_13)
            .met14(UPDATED_MET_14)
            .met15(UPDATED_MET_15)
            .met16(UPDATED_MET_16)
            .met99(UPDATED_MET_99)
            .met06v(UPDATED_MET_06_V)
            .met07v(UPDATED_MET_07_V)
            .met08v(UPDATED_MET_08_V)
            .met09v(UPDATED_MET_09_V)
            .met10v(UPDATED_MET_10_V)
            .met11v(UPDATED_MET_11_V)
            .met12v(UPDATED_MET_12_V)
            .met13v(UPDATED_MET_13_V)
            .met14v(UPDATED_MET_14_V)
            .met15v(UPDATED_MET_15_V)
            .met16v(UPDATED_MET_16_V)
            .met99v(UPDATED_MET_99_V)
            .met99str(UPDATED_MET_99_STR)
            .confa(UPDATED_CONFA)
            .confb(UPDATED_CONFB);

        restBidPrjSubcoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSubco.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSubco))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSubco testBidPrjSubco = bidPrjSubcoList.get(bidPrjSubcoList.size() - 1);
        assertThat(testBidPrjSubco.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSubco.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSubco.getSubcoNo()).isEqualTo(UPDATED_SUBCO_NO);
        assertThat(testBidPrjSubco.getSubCcut()).isEqualTo(UPDATED_SUB_CCUT);
        assertThat(testBidPrjSubco.getCoRegNo()).isEqualTo(UPDATED_CO_REG_NO);
        assertThat(testBidPrjSubco.getSubBdgt1()).isEqualTo(UPDATED_SUB_BDGT_1);
        assertThat(testBidPrjSubco.getUpCcut()).isEqualTo(UPDATED_UP_CCUT);
        assertThat(testBidPrjSubco.getStype()).isEqualTo(UPDATED_STYPE);
        assertThat(testBidPrjSubco.getSpcw01()).isEqualTo(UPDATED_SPCW_01);
        assertThat(testBidPrjSubco.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
        assertThat(testBidPrjSubco.getSpcw03()).isEqualTo(UPDATED_SPCW_03);
        assertThat(testBidPrjSubco.getSpcw04()).isEqualTo(UPDATED_SPCW_04);
        assertThat(testBidPrjSubco.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
        assertThat(testBidPrjSubco.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
        assertThat(testBidPrjSubco.getSpcw07()).isEqualTo(UPDATED_SPCW_07);
        assertThat(testBidPrjSubco.getSpcw08()).isEqualTo(UPDATED_SPCW_08);
        assertThat(testBidPrjSubco.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
        assertThat(testBidPrjSubco.getSpcw10()).isEqualTo(UPDATED_SPCW_10);
        assertThat(testBidPrjSubco.getSpcw11()).isEqualTo(UPDATED_SPCW_11);
        assertThat(testBidPrjSubco.getSpcw12()).isEqualTo(UPDATED_SPCW_12);
        assertThat(testBidPrjSubco.getSubcoMemo()).isEqualTo(UPDATED_SUBCO_MEMO);
        assertThat(testBidPrjSubco.getMet01()).isEqualTo(UPDATED_MET_01);
        assertThat(testBidPrjSubco.getMet01b()).isEqualTo(UPDATED_MET_01_B);
        assertThat(testBidPrjSubco.getMet01c()).isEqualTo(UPDATED_MET_01_C);
        assertThat(testBidPrjSubco.getMet05()).isEqualTo(UPDATED_MET_05);
        assertThat(testBidPrjSubco.getMet02f()).isEqualTo(UPDATED_MET_02_F);
        assertThat(testBidPrjSubco.getMet01d()).isEqualTo(UPDATED_MET_01_D);
        assertThat(testBidPrjSubco.getMet02()).isEqualTo(UPDATED_MET_02);
        assertThat(testBidPrjSubco.getMet02b()).isEqualTo(UPDATED_MET_02_B);
        assertThat(testBidPrjSubco.getMet04()).isEqualTo(UPDATED_MET_04);
        assertThat(testBidPrjSubco.getMet03()).isEqualTo(UPDATED_MET_03);
        assertThat(testBidPrjSubco.getMet03b()).isEqualTo(UPDATED_MET_03_B);
        assertThat(testBidPrjSubco.getMet02c()).isEqualTo(UPDATED_MET_02_C);
        assertThat(testBidPrjSubco.getMet02d()).isEqualTo(UPDATED_MET_02_D);
        assertThat(testBidPrjSubco.getMet02e()).isEqualTo(UPDATED_MET_02_E);
        assertThat(testBidPrjSubco.getMet01v()).isEqualTo(UPDATED_MET_01_V);
        assertThat(testBidPrjSubco.getMet01bv()).isEqualTo(UPDATED_MET_01_BV);
        assertThat(testBidPrjSubco.getMet01cv()).isEqualTo(UPDATED_MET_01_CV);
        assertThat(testBidPrjSubco.getMet05v()).isEqualTo(UPDATED_MET_05_V);
        assertThat(testBidPrjSubco.getMet02fv()).isEqualTo(UPDATED_MET_02_FV);
        assertThat(testBidPrjSubco.getMet01dv()).isEqualTo(UPDATED_MET_01_DV);
        assertThat(testBidPrjSubco.getMet02v()).isEqualTo(UPDATED_MET_02_V);
        assertThat(testBidPrjSubco.getMet02bv()).isEqualTo(UPDATED_MET_02_BV);
        assertThat(testBidPrjSubco.getMet04v()).isEqualTo(UPDATED_MET_04_V);
        assertThat(testBidPrjSubco.getMet03v()).isEqualTo(UPDATED_MET_03_V);
        assertThat(testBidPrjSubco.getMet03bv()).isEqualTo(UPDATED_MET_03_BV);
        assertThat(testBidPrjSubco.getMet02cv()).isEqualTo(UPDATED_MET_02_CV);
        assertThat(testBidPrjSubco.getMet02dv()).isEqualTo(UPDATED_MET_02_DV);
        assertThat(testBidPrjSubco.getMet02ev()).isEqualTo(UPDATED_MET_02_EV);
        assertThat(testBidPrjSubco.getQchk()).isEqualTo(UPDATED_QCHK);
        assertThat(testBidPrjSubco.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSubco.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSubco.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSubco.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSubco.getRmtfil()).isEqualTo(UPDATED_RMTFIL);
        assertThat(testBidPrjSubco.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjSubco.getMet06()).isEqualTo(UPDATED_MET_06);
        assertThat(testBidPrjSubco.getMet07()).isEqualTo(UPDATED_MET_07);
        assertThat(testBidPrjSubco.getMet08()).isEqualTo(UPDATED_MET_08);
        assertThat(testBidPrjSubco.getMet09()).isEqualTo(UPDATED_MET_09);
        assertThat(testBidPrjSubco.getMet10()).isEqualTo(UPDATED_MET_10);
        assertThat(testBidPrjSubco.getMet11()).isEqualTo(UPDATED_MET_11);
        assertThat(testBidPrjSubco.getMet12()).isEqualTo(UPDATED_MET_12);
        assertThat(testBidPrjSubco.getMet13()).isEqualTo(UPDATED_MET_13);
        assertThat(testBidPrjSubco.getMet14()).isEqualTo(UPDATED_MET_14);
        assertThat(testBidPrjSubco.getMet15()).isEqualTo(UPDATED_MET_15);
        assertThat(testBidPrjSubco.getMet16()).isEqualTo(UPDATED_MET_16);
        assertThat(testBidPrjSubco.getMet99()).isEqualTo(UPDATED_MET_99);
        assertThat(testBidPrjSubco.getMet06v()).isEqualTo(UPDATED_MET_06_V);
        assertThat(testBidPrjSubco.getMet07v()).isEqualTo(UPDATED_MET_07_V);
        assertThat(testBidPrjSubco.getMet08v()).isEqualTo(UPDATED_MET_08_V);
        assertThat(testBidPrjSubco.getMet09v()).isEqualTo(UPDATED_MET_09_V);
        assertThat(testBidPrjSubco.getMet10v()).isEqualTo(UPDATED_MET_10_V);
        assertThat(testBidPrjSubco.getMet11v()).isEqualTo(UPDATED_MET_11_V);
        assertThat(testBidPrjSubco.getMet12v()).isEqualTo(UPDATED_MET_12_V);
        assertThat(testBidPrjSubco.getMet13v()).isEqualTo(UPDATED_MET_13_V);
        assertThat(testBidPrjSubco.getMet14v()).isEqualTo(UPDATED_MET_14_V);
        assertThat(testBidPrjSubco.getMet15v()).isEqualTo(UPDATED_MET_15_V);
        assertThat(testBidPrjSubco.getMet16v()).isEqualTo(UPDATED_MET_16_V);
        assertThat(testBidPrjSubco.getMet99v()).isEqualTo(UPDATED_MET_99_V);
        assertThat(testBidPrjSubco.getMet99str()).isEqualTo(UPDATED_MET_99_STR);
        assertThat(testBidPrjSubco.getConfa()).isEqualTo(UPDATED_CONFA);
        assertThat(testBidPrjSubco.getConfb()).isEqualTo(UPDATED_CONFB);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjSubco() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();

        BidPrjSubcoDTO id = new BidPrjSubcoDTO();

        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(id.getWkut(),id.getPrjno(),id.getSubcoNo());

        bidPrjSubco.setId(bidPrjSubcoPrimaryKey);

        // Create the BidPrjSubco
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBidPrjSubcoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bidPrjSubcoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjSubco() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();

        BidPrjSubcoDTO id = new BidPrjSubcoDTO();

        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(id.getWkut(),id.getPrjno(),id.getSubcoNo());

        bidPrjSubco.setId(bidPrjSubcoPrimaryKey);

        // Create the BidPrjSubco
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjSubcoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjSubco() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjSubcoRepository.findAll().size();

        BidPrjSubcoDTO id = new BidPrjSubcoDTO();

        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(id.getWkut(),id.getPrjno(),id.getSubcoNo());

        bidPrjSubco.setId(bidPrjSubcoPrimaryKey);

        // Create the BidPrjSubco
        BidPrjSubcoDTO bidPrjSubcoDTO = bidPrjSubcoMapper.toDto(bidPrjSubco);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjSubcoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjSubcoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BidPrjSubco in the database
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjSubco() throws Exception {
        // Initialize the database
        bidPrjSubcoRepository.saveAndFlush(bidPrjSubco);

        int databaseSizeBeforeDelete = bidPrjSubcoRepository.findAll().size();

        // Delete the bidPrjSubco
        restBidPrjSubcoMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjSubco.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjSubco> bidPrjSubcoList = bidPrjSubcoRepository.findAll();
        assertThat(bidPrjSubcoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
