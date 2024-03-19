package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidMdlbas;
import gov.pcc.pwc.repository.BidMdlbasRepository;
import gov.pcc.pwc.service.dto.BidMdlbasDTO;
import gov.pcc.pwc.service.mapper.BidMdlbasMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BidMdlbasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidMdlbasResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_DIS_NO = "AAAAAAAAAA";
    private static final String UPDATED_DIS_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DIS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIS_PLCE = "AAAAAAAAAA";
    private static final String UPDATED_DIS_PLCE = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_TYPE = "AA";
    private static final String UPDATED_WORK_TYPE = "BB";

    private static final BigDecimal DEFAULT_EST_BUDG = new BigDecimal(1);
    private static final BigDecimal UPDATED_EST_BUDG = new BigDecimal(2);

    private static final String DEFAULT_WORK_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_WORK_ITEM = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VERI_BUDG = new BigDecimal(1);
    private static final BigDecimal UPDATED_VERI_BUDG = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VERI_2_BUDG = new BigDecimal(1);
    private static final BigDecimal UPDATED_VERI_2_BUDG = new BigDecimal(2);

    private static final String DEFAULT_VERI_2_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_VERI_2_ITEM = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VERI_3_BUDG = new BigDecimal(1);
    private static final BigDecimal UPDATED_VERI_3_BUDG = new BigDecimal(2);

    private static final String DEFAULT_VERI_3_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_VERI_3_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_TM_2_X = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_X = "BBBBBBBBBB";

    private static final String DEFAULT_TM_2_Y = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_Y = "BBBBBBBBBB";

    private static final String DEFAULT_NOTY_BUDG = "AAAAAAAAAA";
    private static final String UPDATED_NOTY_BUDG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_NOTY_BUDG_0 = new BigDecimal(1);
    private static final BigDecimal UPDATED_NOTY_BUDG_0 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VERI_3_BUDG_0 = new BigDecimal(1);
    private static final BigDecimal UPDATED_VERI_3_BUDG_0 = new BigDecimal(2);

    private static final String DEFAULT_DIS_RMK = "AAAAAAAAAA";
    private static final String UPDATED_DIS_RMK = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_S_ACTDT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_S_ACTDT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_S_ACTDT_0 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_S_ACTDT_0 = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_S_AEDAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_S_AEDAT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_S_AEDAT_0 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_S_AEDAT_0 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CHG_RMK = "AAAAAAAAAA";
    private static final String UPDATED_CHG_RMK = "BBBBBBBBBB";

    private static final String DEFAULT_DEL_RESN = "A";
    private static final String UPDATED_DEL_RESN = "B";

    private static final String DEFAULT_DEL_RESN_2 = "A";
    private static final String UPDATED_DEL_RESN_2 = "B";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-mdlbas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidMdlbasRepository bidMdlbasRepository;

    @Autowired
    private BidMdlbasMapper bidMdlbasMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidMdlbasMockMvc;

    private BidMdlbas bidMdlbas;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidMdlbas createEntity(EntityManager em) {
        BidMdlbas bidMdlbas = new BidMdlbas()
            .wkut(DEFAULT_WKUT)
            .disNo(DEFAULT_DIS_NO)
            .disName(DEFAULT_DIS_NAME)
            .disPlce(DEFAULT_DIS_PLCE)
            .workType(DEFAULT_WORK_TYPE)
            .estBudg(DEFAULT_EST_BUDG)
            .workItem(DEFAULT_WORK_ITEM)
            .veriBudg(DEFAULT_VERI_BUDG)
            .veri2Budg(DEFAULT_VERI_2_BUDG)
            .veri2Item(DEFAULT_VERI_2_ITEM)
            .veri3Budg(DEFAULT_VERI_3_BUDG)
            .veri3Item(DEFAULT_VERI_3_ITEM)
            .tm2x(DEFAULT_TM_2_X)
            .tm2y(DEFAULT_TM_2_Y)
            .notyBudg(DEFAULT_NOTY_BUDG)
            .notyBudg0(DEFAULT_NOTY_BUDG_0)
            .veri3Budg0(DEFAULT_VERI_3_BUDG_0)
            .disRmk(DEFAULT_DIS_RMK)
            .sActdt(DEFAULT_S_ACTDT)
            .sActdt0(DEFAULT_S_ACTDT_0)
            .sAedat(DEFAULT_S_AEDAT)
            .sAedat0(DEFAULT_S_AEDAT_0)
            .chgRmk(DEFAULT_CHG_RMK)
            .delResn(DEFAULT_DEL_RESN)
            .delResn2(DEFAULT_DEL_RESN_2)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER);
        return bidMdlbas;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidMdlbas createUpdatedEntity(EntityManager em) {
        BidMdlbas bidMdlbas = new BidMdlbas()
            .wkut(UPDATED_WKUT)
            .disNo(UPDATED_DIS_NO)
            .disName(UPDATED_DIS_NAME)
            .disPlce(UPDATED_DIS_PLCE)
            .workType(UPDATED_WORK_TYPE)
            .estBudg(UPDATED_EST_BUDG)
            .workItem(UPDATED_WORK_ITEM)
            .veriBudg(UPDATED_VERI_BUDG)
            .veri2Budg(UPDATED_VERI_2_BUDG)
            .veri2Item(UPDATED_VERI_2_ITEM)
            .veri3Budg(UPDATED_VERI_3_BUDG)
            .veri3Item(UPDATED_VERI_3_ITEM)
            .tm2x(UPDATED_TM_2_X)
            .tm2y(UPDATED_TM_2_Y)
            .notyBudg(UPDATED_NOTY_BUDG)
            .notyBudg0(UPDATED_NOTY_BUDG_0)
            .veri3Budg0(UPDATED_VERI_3_BUDG_0)
            .disRmk(UPDATED_DIS_RMK)
            .sActdt(UPDATED_S_ACTDT)
            .sActdt0(UPDATED_S_ACTDT_0)
            .sAedat(UPDATED_S_AEDAT)
            .sAedat0(UPDATED_S_AEDAT_0)
            .chgRmk(UPDATED_CHG_RMK)
            .delResn(UPDATED_DEL_RESN)
            .delResn2(UPDATED_DEL_RESN_2)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        return bidMdlbas;
    }

    @BeforeEach
    public void initTest() {
        bidMdlbas = createEntity(em);
    }

    @Test
    @Transactional
    void createBidMdlbas() throws Exception {
        int databaseSizeBeforeCreate = bidMdlbasRepository.findAll().size();
        // Create the BidMdlbas
        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
        restBidMdlbasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO)))
            .andExpect(status().isCreated());

        // Validate the BidMdlbas in the database
        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeCreate + 1);
        BidMdlbas testBidMdlbas = bidMdlbasList.get(bidMdlbasList.size() - 1);
        assertThat(testBidMdlbas.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidMdlbas.getDisNo()).isEqualTo(DEFAULT_DIS_NO);
        assertThat(testBidMdlbas.getDisName()).isEqualTo(DEFAULT_DIS_NAME);
        assertThat(testBidMdlbas.getDisPlce()).isEqualTo(DEFAULT_DIS_PLCE);
        assertThat(testBidMdlbas.getWorkType()).isEqualTo(DEFAULT_WORK_TYPE);
        assertThat(testBidMdlbas.getEstBudg()).isEqualByComparingTo(DEFAULT_EST_BUDG);
        assertThat(testBidMdlbas.getWorkItem()).isEqualTo(DEFAULT_WORK_ITEM);
        assertThat(testBidMdlbas.getVeriBudg()).isEqualByComparingTo(DEFAULT_VERI_BUDG);
        assertThat(testBidMdlbas.getVeri2Budg()).isEqualByComparingTo(DEFAULT_VERI_2_BUDG);
        assertThat(testBidMdlbas.getVeri2Item()).isEqualTo(DEFAULT_VERI_2_ITEM);
        assertThat(testBidMdlbas.getVeri3Budg()).isEqualByComparingTo(DEFAULT_VERI_3_BUDG);
        assertThat(testBidMdlbas.getVeri3Item()).isEqualTo(DEFAULT_VERI_3_ITEM);
        assertThat(testBidMdlbas.getTm2x()).isEqualTo(DEFAULT_TM_2_X);
        assertThat(testBidMdlbas.getTm2y()).isEqualTo(DEFAULT_TM_2_Y);
        assertThat(testBidMdlbas.getNotyBudg()).isEqualTo(DEFAULT_NOTY_BUDG);
        assertThat(testBidMdlbas.getNotyBudg0()).isEqualByComparingTo(DEFAULT_NOTY_BUDG_0);
        assertThat(testBidMdlbas.getVeri3Budg0()).isEqualByComparingTo(DEFAULT_VERI_3_BUDG_0);
        assertThat(testBidMdlbas.getDisRmk()).isEqualTo(DEFAULT_DIS_RMK);
        assertThat(testBidMdlbas.getsActdt()).isEqualTo(DEFAULT_S_ACTDT);
        assertThat(testBidMdlbas.getsActdt0()).isEqualTo(DEFAULT_S_ACTDT_0);
        assertThat(testBidMdlbas.getsAedat()).isEqualTo(DEFAULT_S_AEDAT);
        assertThat(testBidMdlbas.getsAedat0()).isEqualTo(DEFAULT_S_AEDAT_0);
        assertThat(testBidMdlbas.getChgRmk()).isEqualTo(DEFAULT_CHG_RMK);
        assertThat(testBidMdlbas.getDelResn()).isEqualTo(DEFAULT_DEL_RESN);
        assertThat(testBidMdlbas.getDelResn2()).isEqualTo(DEFAULT_DEL_RESN_2);
        assertThat(testBidMdlbas.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidMdlbas.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidMdlbas.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidMdlbas.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    void createBidMdlbasWithExistingId() throws Exception {
//        // Create the BidMdlbas with an existing ID
//        bidMdlbas.setId(1L);
//        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
//
//        int databaseSizeBeforeCreate = bidMdlbasRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidMdlbasMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidMdlbas in the database
//        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
//        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidMdlbas() throws Exception {
        // Initialize the database
        bidMdlbasRepository.saveAndFlush(bidMdlbas);

        // Get all the bidMdlbasList
        restBidMdlbasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidMdlbas.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].disNo").value(hasItem(DEFAULT_DIS_NO)))
            .andExpect(jsonPath("$.[*].disName").value(hasItem(DEFAULT_DIS_NAME)))
            .andExpect(jsonPath("$.[*].disPlce").value(hasItem(DEFAULT_DIS_PLCE)))
            .andExpect(jsonPath("$.[*].workType").value(hasItem(DEFAULT_WORK_TYPE)))
            .andExpect(jsonPath("$.[*].estBudg").value(hasItem(sameNumber(DEFAULT_EST_BUDG))))
            .andExpect(jsonPath("$.[*].workItem").value(hasItem(DEFAULT_WORK_ITEM)))
            .andExpect(jsonPath("$.[*].veriBudg").value(hasItem(sameNumber(DEFAULT_VERI_BUDG))))
            .andExpect(jsonPath("$.[*].veri2Budg").value(hasItem(sameNumber(DEFAULT_VERI_2_BUDG))))
            .andExpect(jsonPath("$.[*].veri2Item").value(hasItem(DEFAULT_VERI_2_ITEM)))
            .andExpect(jsonPath("$.[*].veri3Budg").value(hasItem(sameNumber(DEFAULT_VERI_3_BUDG))))
            .andExpect(jsonPath("$.[*].veri3Item").value(hasItem(DEFAULT_VERI_3_ITEM)))
            .andExpect(jsonPath("$.[*].tm2x").value(hasItem(DEFAULT_TM_2_X)))
            .andExpect(jsonPath("$.[*].tm2y").value(hasItem(DEFAULT_TM_2_Y)))
            .andExpect(jsonPath("$.[*].notyBudg").value(hasItem(DEFAULT_NOTY_BUDG)))
            .andExpect(jsonPath("$.[*].notyBudg0").value(hasItem(sameNumber(DEFAULT_NOTY_BUDG_0))))
            .andExpect(jsonPath("$.[*].veri3Budg0").value(hasItem(sameNumber(DEFAULT_VERI_3_BUDG_0))))
            .andExpect(jsonPath("$.[*].disRmk").value(hasItem(DEFAULT_DIS_RMK)))
            .andExpect(jsonPath("$.[*].sActdt").value(hasItem(DEFAULT_S_ACTDT.toString())))
            .andExpect(jsonPath("$.[*].sActdt0").value(hasItem(DEFAULT_S_ACTDT_0.toString())))
            .andExpect(jsonPath("$.[*].sAedat").value(hasItem(DEFAULT_S_AEDAT.toString())))
            .andExpect(jsonPath("$.[*].sAedat0").value(hasItem(DEFAULT_S_AEDAT_0.toString())))
            .andExpect(jsonPath("$.[*].chgRmk").value(hasItem(DEFAULT_CHG_RMK)))
            .andExpect(jsonPath("$.[*].delResn").value(hasItem(DEFAULT_DEL_RESN)))
            .andExpect(jsonPath("$.[*].delResn2").value(hasItem(DEFAULT_DEL_RESN_2)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)));
    }

    @Test
    @Transactional
    void getBidMdlbas() throws Exception {
        // Initialize the database
        bidMdlbasRepository.saveAndFlush(bidMdlbas);

        // Get the bidMdlbas
        restBidMdlbasMockMvc
            .perform(get(ENTITY_API_URL_ID, bidMdlbas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidMdlbas.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.disNo").value(DEFAULT_DIS_NO))
            .andExpect(jsonPath("$.disName").value(DEFAULT_DIS_NAME))
            .andExpect(jsonPath("$.disPlce").value(DEFAULT_DIS_PLCE))
            .andExpect(jsonPath("$.workType").value(DEFAULT_WORK_TYPE))
            .andExpect(jsonPath("$.estBudg").value(sameNumber(DEFAULT_EST_BUDG)))
            .andExpect(jsonPath("$.workItem").value(DEFAULT_WORK_ITEM))
            .andExpect(jsonPath("$.veriBudg").value(sameNumber(DEFAULT_VERI_BUDG)))
            .andExpect(jsonPath("$.veri2Budg").value(sameNumber(DEFAULT_VERI_2_BUDG)))
            .andExpect(jsonPath("$.veri2Item").value(DEFAULT_VERI_2_ITEM))
            .andExpect(jsonPath("$.veri3Budg").value(sameNumber(DEFAULT_VERI_3_BUDG)))
            .andExpect(jsonPath("$.veri3Item").value(DEFAULT_VERI_3_ITEM))
            .andExpect(jsonPath("$.tm2x").value(DEFAULT_TM_2_X))
            .andExpect(jsonPath("$.tm2y").value(DEFAULT_TM_2_Y))
            .andExpect(jsonPath("$.notyBudg").value(DEFAULT_NOTY_BUDG))
            .andExpect(jsonPath("$.notyBudg0").value(sameNumber(DEFAULT_NOTY_BUDG_0)))
            .andExpect(jsonPath("$.veri3Budg0").value(sameNumber(DEFAULT_VERI_3_BUDG_0)))
            .andExpect(jsonPath("$.disRmk").value(DEFAULT_DIS_RMK))
            .andExpect(jsonPath("$.sActdt").value(DEFAULT_S_ACTDT.toString()))
            .andExpect(jsonPath("$.sActdt0").value(DEFAULT_S_ACTDT_0.toString()))
            .andExpect(jsonPath("$.sAedat").value(DEFAULT_S_AEDAT.toString()))
            .andExpect(jsonPath("$.sAedat0").value(DEFAULT_S_AEDAT_0.toString()))
            .andExpect(jsonPath("$.chgRmk").value(DEFAULT_CHG_RMK))
            .andExpect(jsonPath("$.delResn").value(DEFAULT_DEL_RESN))
            .andExpect(jsonPath("$.delResn2").value(DEFAULT_DEL_RESN_2))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingBidMdlbas() throws Exception {
        // Get the bidMdlbas
        restBidMdlbasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidMdlbas() throws Exception {
        // Initialize the database
        bidMdlbasRepository.saveAndFlush(bidMdlbas);

        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();

        // Update the bidMdlbas
        BidMdlbas updatedBidMdlbas = bidMdlbasRepository.findById(bidMdlbas.getId()).get();
        // Disconnect from session so that the updates on updatedBidMdlbas are not directly saved in db
        em.detach(updatedBidMdlbas);
        updatedBidMdlbas
            .wkut(UPDATED_WKUT)
            .disNo(UPDATED_DIS_NO)
            .disName(UPDATED_DIS_NAME)
            .disPlce(UPDATED_DIS_PLCE)
            .workType(UPDATED_WORK_TYPE)
            .estBudg(UPDATED_EST_BUDG)
            .workItem(UPDATED_WORK_ITEM)
            .veriBudg(UPDATED_VERI_BUDG)
            .veri2Budg(UPDATED_VERI_2_BUDG)
            .veri2Item(UPDATED_VERI_2_ITEM)
            .veri3Budg(UPDATED_VERI_3_BUDG)
            .veri3Item(UPDATED_VERI_3_ITEM)
            .tm2x(UPDATED_TM_2_X)
            .tm2y(UPDATED_TM_2_Y)
            .notyBudg(UPDATED_NOTY_BUDG)
            .notyBudg0(UPDATED_NOTY_BUDG_0)
            .veri3Budg0(UPDATED_VERI_3_BUDG_0)
            .disRmk(UPDATED_DIS_RMK)
            .sActdt(UPDATED_S_ACTDT)
            .sActdt0(UPDATED_S_ACTDT_0)
            .sAedat(UPDATED_S_AEDAT)
            .sAedat0(UPDATED_S_AEDAT_0)
            .chgRmk(UPDATED_CHG_RMK)
            .delResn(UPDATED_DEL_RESN)
            .delResn2(UPDATED_DEL_RESN_2)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(updatedBidMdlbas);

        restBidMdlbasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidMdlbasDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidMdlbas in the database
        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
        BidMdlbas testBidMdlbas = bidMdlbasList.get(bidMdlbasList.size() - 1);
        assertThat(testBidMdlbas.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidMdlbas.getDisNo()).isEqualTo(UPDATED_DIS_NO);
        assertThat(testBidMdlbas.getDisName()).isEqualTo(UPDATED_DIS_NAME);
        assertThat(testBidMdlbas.getDisPlce()).isEqualTo(UPDATED_DIS_PLCE);
        assertThat(testBidMdlbas.getWorkType()).isEqualTo(UPDATED_WORK_TYPE);
        assertThat(testBidMdlbas.getEstBudg()).isEqualByComparingTo(UPDATED_EST_BUDG);
        assertThat(testBidMdlbas.getWorkItem()).isEqualTo(UPDATED_WORK_ITEM);
        assertThat(testBidMdlbas.getVeriBudg()).isEqualByComparingTo(UPDATED_VERI_BUDG);
        assertThat(testBidMdlbas.getVeri2Budg()).isEqualByComparingTo(UPDATED_VERI_2_BUDG);
        assertThat(testBidMdlbas.getVeri2Item()).isEqualTo(UPDATED_VERI_2_ITEM);
        assertThat(testBidMdlbas.getVeri3Budg()).isEqualByComparingTo(UPDATED_VERI_3_BUDG);
        assertThat(testBidMdlbas.getVeri3Item()).isEqualTo(UPDATED_VERI_3_ITEM);
        assertThat(testBidMdlbas.getTm2x()).isEqualTo(UPDATED_TM_2_X);
        assertThat(testBidMdlbas.getTm2y()).isEqualTo(UPDATED_TM_2_Y);
        assertThat(testBidMdlbas.getNotyBudg()).isEqualTo(UPDATED_NOTY_BUDG);
        assertThat(testBidMdlbas.getNotyBudg0()).isEqualByComparingTo(UPDATED_NOTY_BUDG_0);
        assertThat(testBidMdlbas.getVeri3Budg0()).isEqualByComparingTo(UPDATED_VERI_3_BUDG_0);
        assertThat(testBidMdlbas.getDisRmk()).isEqualTo(UPDATED_DIS_RMK);
        assertThat(testBidMdlbas.getsActdt()).isEqualTo(UPDATED_S_ACTDT);
        assertThat(testBidMdlbas.getsActdt0()).isEqualTo(UPDATED_S_ACTDT_0);
        assertThat(testBidMdlbas.getsAedat()).isEqualTo(UPDATED_S_AEDAT);
        assertThat(testBidMdlbas.getsAedat0()).isEqualTo(UPDATED_S_AEDAT_0);
        assertThat(testBidMdlbas.getChgRmk()).isEqualTo(UPDATED_CHG_RMK);
        assertThat(testBidMdlbas.getDelResn()).isEqualTo(UPDATED_DEL_RESN);
        assertThat(testBidMdlbas.getDelResn2()).isEqualTo(UPDATED_DEL_RESN_2);
        assertThat(testBidMdlbas.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidMdlbas.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidMdlbas.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidMdlbas.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingBidMdlbas() throws Exception {
//        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();
//        bidMdlbas.setId(count.incrementAndGet());
//
//        // Create the BidMdlbas
//        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidMdlbasMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidMdlbasDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidMdlbas in the database
//        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
//        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidMdlbas() throws Exception {
//        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();
//        bidMdlbas.setId(count.incrementAndGet());
//
//        // Create the BidMdlbas
//        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidMdlbasMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidMdlbas in the database
//        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
//        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidMdlbas() throws Exception {
//        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();
//        bidMdlbas.setId(count.incrementAndGet());
//
//        // Create the BidMdlbas
//        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidMdlbasMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidMdlbas in the database
//        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
//        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidMdlbasWithPatch() throws Exception {
        // Initialize the database
        bidMdlbasRepository.saveAndFlush(bidMdlbas);

        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();

        // Update the bidMdlbas using partial update
        BidMdlbas partialUpdatedBidMdlbas = new BidMdlbas();
        partialUpdatedBidMdlbas.setId(bidMdlbas.getId());

        partialUpdatedBidMdlbas
            .wkut(UPDATED_WKUT)
            .disName(UPDATED_DIS_NAME)
            .disPlce(UPDATED_DIS_PLCE)
            .estBudg(UPDATED_EST_BUDG)
            .workItem(UPDATED_WORK_ITEM)
            .veri3Budg(UPDATED_VERI_3_BUDG)
            .veri3Item(UPDATED_VERI_3_ITEM)
            .tm2y(UPDATED_TM_2_Y)
            .notyBudg0(UPDATED_NOTY_BUDG_0)
            .veri3Budg0(UPDATED_VERI_3_BUDG_0)
            .sActdt0(UPDATED_S_ACTDT_0)
            .delResn(UPDATED_DEL_RESN)
            .createDate(UPDATED_CREATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);

        restBidMdlbasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidMdlbas.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidMdlbas))
            )
            .andExpect(status().isOk());

        // Validate the BidMdlbas in the database
        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
        BidMdlbas testBidMdlbas = bidMdlbasList.get(bidMdlbasList.size() - 1);
        assertThat(testBidMdlbas.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidMdlbas.getDisNo()).isEqualTo(DEFAULT_DIS_NO);
        assertThat(testBidMdlbas.getDisName()).isEqualTo(UPDATED_DIS_NAME);
        assertThat(testBidMdlbas.getDisPlce()).isEqualTo(UPDATED_DIS_PLCE);
        assertThat(testBidMdlbas.getWorkType()).isEqualTo(DEFAULT_WORK_TYPE);
        assertThat(testBidMdlbas.getEstBudg()).isEqualByComparingTo(UPDATED_EST_BUDG);
        assertThat(testBidMdlbas.getWorkItem()).isEqualTo(UPDATED_WORK_ITEM);
        assertThat(testBidMdlbas.getVeriBudg()).isEqualByComparingTo(DEFAULT_VERI_BUDG);
        assertThat(testBidMdlbas.getVeri2Budg()).isEqualByComparingTo(DEFAULT_VERI_2_BUDG);
        assertThat(testBidMdlbas.getVeri2Item()).isEqualTo(DEFAULT_VERI_2_ITEM);
        assertThat(testBidMdlbas.getVeri3Budg()).isEqualByComparingTo(UPDATED_VERI_3_BUDG);
        assertThat(testBidMdlbas.getVeri3Item()).isEqualTo(UPDATED_VERI_3_ITEM);
        assertThat(testBidMdlbas.getTm2x()).isEqualTo(DEFAULT_TM_2_X);
        assertThat(testBidMdlbas.getTm2y()).isEqualTo(UPDATED_TM_2_Y);
        assertThat(testBidMdlbas.getNotyBudg()).isEqualTo(DEFAULT_NOTY_BUDG);
        assertThat(testBidMdlbas.getNotyBudg0()).isEqualByComparingTo(UPDATED_NOTY_BUDG_0);
        assertThat(testBidMdlbas.getVeri3Budg0()).isEqualByComparingTo(UPDATED_VERI_3_BUDG_0);
        assertThat(testBidMdlbas.getDisRmk()).isEqualTo(DEFAULT_DIS_RMK);
        assertThat(testBidMdlbas.getsActdt()).isEqualTo(DEFAULT_S_ACTDT);
        assertThat(testBidMdlbas.getsActdt0()).isEqualTo(UPDATED_S_ACTDT_0);
        assertThat(testBidMdlbas.getsAedat()).isEqualTo(DEFAULT_S_AEDAT);
        assertThat(testBidMdlbas.getsAedat0()).isEqualTo(DEFAULT_S_AEDAT_0);
        assertThat(testBidMdlbas.getChgRmk()).isEqualTo(DEFAULT_CHG_RMK);
        assertThat(testBidMdlbas.getDelResn()).isEqualTo(UPDATED_DEL_RESN);
        assertThat(testBidMdlbas.getDelResn2()).isEqualTo(DEFAULT_DEL_RESN_2);
        assertThat(testBidMdlbas.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidMdlbas.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidMdlbas.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidMdlbas.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void fullUpdateBidMdlbasWithPatch() throws Exception {
        // Initialize the database
        bidMdlbasRepository.saveAndFlush(bidMdlbas);

        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();

        // Update the bidMdlbas using partial update
        BidMdlbas partialUpdatedBidMdlbas = new BidMdlbas();
        partialUpdatedBidMdlbas.setId(bidMdlbas.getId());

        partialUpdatedBidMdlbas
            .wkut(UPDATED_WKUT)
            .disNo(UPDATED_DIS_NO)
            .disName(UPDATED_DIS_NAME)
            .disPlce(UPDATED_DIS_PLCE)
            .workType(UPDATED_WORK_TYPE)
            .estBudg(UPDATED_EST_BUDG)
            .workItem(UPDATED_WORK_ITEM)
            .veriBudg(UPDATED_VERI_BUDG)
            .veri2Budg(UPDATED_VERI_2_BUDG)
            .veri2Item(UPDATED_VERI_2_ITEM)
            .veri3Budg(UPDATED_VERI_3_BUDG)
            .veri3Item(UPDATED_VERI_3_ITEM)
            .tm2x(UPDATED_TM_2_X)
            .tm2y(UPDATED_TM_2_Y)
            .notyBudg(UPDATED_NOTY_BUDG)
            .notyBudg0(UPDATED_NOTY_BUDG_0)
            .veri3Budg0(UPDATED_VERI_3_BUDG_0)
            .disRmk(UPDATED_DIS_RMK)
            .sActdt(UPDATED_S_ACTDT)
            .sActdt0(UPDATED_S_ACTDT_0)
            .sAedat(UPDATED_S_AEDAT)
            .sAedat0(UPDATED_S_AEDAT_0)
            .chgRmk(UPDATED_CHG_RMK)
            .delResn(UPDATED_DEL_RESN)
            .delResn2(UPDATED_DEL_RESN_2)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);

        restBidMdlbasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidMdlbas.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidMdlbas))
            )
            .andExpect(status().isOk());

        // Validate the BidMdlbas in the database
        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
        BidMdlbas testBidMdlbas = bidMdlbasList.get(bidMdlbasList.size() - 1);
        assertThat(testBidMdlbas.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidMdlbas.getDisNo()).isEqualTo(UPDATED_DIS_NO);
        assertThat(testBidMdlbas.getDisName()).isEqualTo(UPDATED_DIS_NAME);
        assertThat(testBidMdlbas.getDisPlce()).isEqualTo(UPDATED_DIS_PLCE);
        assertThat(testBidMdlbas.getWorkType()).isEqualTo(UPDATED_WORK_TYPE);
        assertThat(testBidMdlbas.getEstBudg()).isEqualByComparingTo(UPDATED_EST_BUDG);
        assertThat(testBidMdlbas.getWorkItem()).isEqualTo(UPDATED_WORK_ITEM);
        assertThat(testBidMdlbas.getVeriBudg()).isEqualByComparingTo(UPDATED_VERI_BUDG);
        assertThat(testBidMdlbas.getVeri2Budg()).isEqualByComparingTo(UPDATED_VERI_2_BUDG);
        assertThat(testBidMdlbas.getVeri2Item()).isEqualTo(UPDATED_VERI_2_ITEM);
        assertThat(testBidMdlbas.getVeri3Budg()).isEqualByComparingTo(UPDATED_VERI_3_BUDG);
        assertThat(testBidMdlbas.getVeri3Item()).isEqualTo(UPDATED_VERI_3_ITEM);
        assertThat(testBidMdlbas.getTm2x()).isEqualTo(UPDATED_TM_2_X);
        assertThat(testBidMdlbas.getTm2y()).isEqualTo(UPDATED_TM_2_Y);
        assertThat(testBidMdlbas.getNotyBudg()).isEqualTo(UPDATED_NOTY_BUDG);
        assertThat(testBidMdlbas.getNotyBudg0()).isEqualByComparingTo(UPDATED_NOTY_BUDG_0);
        assertThat(testBidMdlbas.getVeri3Budg0()).isEqualByComparingTo(UPDATED_VERI_3_BUDG_0);
        assertThat(testBidMdlbas.getDisRmk()).isEqualTo(UPDATED_DIS_RMK);
        assertThat(testBidMdlbas.getsActdt()).isEqualTo(UPDATED_S_ACTDT);
        assertThat(testBidMdlbas.getsActdt0()).isEqualTo(UPDATED_S_ACTDT_0);
        assertThat(testBidMdlbas.getsAedat()).isEqualTo(UPDATED_S_AEDAT);
        assertThat(testBidMdlbas.getsAedat0()).isEqualTo(UPDATED_S_AEDAT_0);
        assertThat(testBidMdlbas.getChgRmk()).isEqualTo(UPDATED_CHG_RMK);
        assertThat(testBidMdlbas.getDelResn()).isEqualTo(UPDATED_DEL_RESN);
        assertThat(testBidMdlbas.getDelResn2()).isEqualTo(UPDATED_DEL_RESN_2);
        assertThat(testBidMdlbas.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidMdlbas.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidMdlbas.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidMdlbas.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingBidMdlbas() throws Exception {
//        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();
//        bidMdlbas.setId(count.incrementAndGet());
//
//        // Create the BidMdlbas
//        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidMdlbasMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidMdlbasDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidMdlbas in the database
//        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
//        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidMdlbas() throws Exception {
//        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();
//        bidMdlbas.setId(count.incrementAndGet());
//
//        // Create the BidMdlbas
//        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidMdlbasMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidMdlbas in the database
//        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
//        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidMdlbas() throws Exception {
//        int databaseSizeBeforeUpdate = bidMdlbasRepository.findAll().size();
//        bidMdlbas.setId(count.incrementAndGet());
//
//        // Create the BidMdlbas
//        BidMdlbasDTO bidMdlbasDTO = bidMdlbasMapper.toDto(bidMdlbas);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidMdlbasMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidMdlbasDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidMdlbas in the database
//        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
//        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidMdlbas() throws Exception {
        // Initialize the database
        bidMdlbasRepository.saveAndFlush(bidMdlbas);

        int databaseSizeBeforeDelete = bidMdlbasRepository.findAll().size();

        // Delete the bidMdlbas
        restBidMdlbasMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidMdlbas.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidMdlbas> bidMdlbasList = bidMdlbasRepository.findAll();
        assertThat(bidMdlbasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
