package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjFrwkr;
import gov.pcc.pwc.domain.BidPrjFrwkrPrimaryKey;
import gov.pcc.pwc.repository.BidPrjFrwkrRepository;
import gov.pcc.pwc.service.dto.BidPrjFrwkrDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;

import gov.pcc.pwc.service.mapper.BidPrjFrwkrMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BidPrjFrwkrResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjFrwkrResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_FR_WKR = 1;
    private static final Integer UPDATED_FR_WKR = 2;

    private static final Integer DEFAULT_FR_WKRED = 1;
    private static final Integer UPDATED_FR_WKRED = 2;

    private static final String DEFAULT_FR_TYPE = "A";
    private static final String UPDATED_FR_TYPE = "B";

    private static final String DEFAULT_FR_PNO = "AAAAAAAAAA";
    private static final String UPDATED_FR_PNO = "BBBBBBBBBB";

    private static final String DEFAULT_FR_RMK = "AAAAAAAAAA";
    private static final String UPDATED_FR_RMK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FR_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FR_RATE = new BigDecimal(2);

    private static final String DEFAULT_FR_KND1 = "AA";
    private static final String UPDATED_FR_KND1 = "BB";

    private static final String DEFAULT_FR_KND2 = "AA";
    private static final String UPDATED_FR_KND2 = "BB";

    private static final String DEFAULT_FR_KND3 = "AA";
    private static final String UPDATED_FR_KND3 = "BB";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN1 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN1 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-frwkrs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjFrwkrRepository bidPrjFrwkrRepository;

    @Autowired
    private BidPrjFrwkrMapper bidPrjFrwkrMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjFrwkrMockMvc;

    private BidPrjFrwkr bidPrjFrwkr;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjFrwkr createEntity(EntityManager em) {
        BidPrjFrwkr bidPrjFrwkr = new BidPrjFrwkr()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .sdate(DEFAULT_SDATE)
            .frWkr(DEFAULT_FR_WKR)
            .frWkred(DEFAULT_FR_WKRED)
            .frType(DEFAULT_FR_TYPE)
            .frPno(DEFAULT_FR_PNO)
            .frRmk(DEFAULT_FR_RMK)
            .frRate(DEFAULT_FR_RATE)
            .frKnd1(DEFAULT_FR_KND1)
            .frKnd2(DEFAULT_FR_KND2)
            .frKnd3(DEFAULT_FR_KND3)
            .confOut(DEFAULT_CONF_OUT)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .uIp(DEFAULT_U_IP)
            .column1(DEFAULT_COLUMN1);
        return bidPrjFrwkr;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjFrwkr createUpdatedEntity(EntityManager em) {
        BidPrjFrwkr bidPrjFrwkr = new BidPrjFrwkr()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .sdate(UPDATED_SDATE)
            .frWkr(UPDATED_FR_WKR)
            .frWkred(UPDATED_FR_WKRED)
            .frType(UPDATED_FR_TYPE)
            .frPno(UPDATED_FR_PNO)
            .frRmk(UPDATED_FR_RMK)
            .frRate(UPDATED_FR_RATE)
            .frKnd1(UPDATED_FR_KND1)
            .frKnd2(UPDATED_FR_KND2)
            .frKnd3(UPDATED_FR_KND3)
            .confOut(UPDATED_CONF_OUT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .column1(UPDATED_COLUMN1);
        return bidPrjFrwkr;
    }

    @BeforeEach
    public void initTest() {
        bidPrjFrwkr = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjFrwkr() throws Exception {
        int databaseSizeBeforeCreate = bidPrjFrwkrRepository.findAll().size();
        // Create the BidPrjFrwkr
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
        restBidPrjFrwkrMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjFrwkr in the database
        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjFrwkr testBidPrjFrwkr = bidPrjFrwkrList.get(bidPrjFrwkrList.size() - 1);
        assertThat(testBidPrjFrwkr.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjFrwkr.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjFrwkr.getSdate()).isEqualTo(DEFAULT_SDATE);
        assertThat(testBidPrjFrwkr.getFrWkr()).isEqualTo(DEFAULT_FR_WKR);
        assertThat(testBidPrjFrwkr.getFrWkred()).isEqualTo(DEFAULT_FR_WKRED);
        assertThat(testBidPrjFrwkr.getFrType()).isEqualTo(DEFAULT_FR_TYPE);
        assertThat(testBidPrjFrwkr.getFrPno()).isEqualTo(DEFAULT_FR_PNO);
        assertThat(testBidPrjFrwkr.getFrRmk()).isEqualTo(DEFAULT_FR_RMK);
        assertThat(testBidPrjFrwkr.getFrRate()).isEqualByComparingTo(DEFAULT_FR_RATE);
        assertThat(testBidPrjFrwkr.getFrKnd1()).isEqualTo(DEFAULT_FR_KND1);
        assertThat(testBidPrjFrwkr.getFrKnd2()).isEqualTo(DEFAULT_FR_KND2);
        assertThat(testBidPrjFrwkr.getFrKnd3()).isEqualTo(DEFAULT_FR_KND3);
        assertThat(testBidPrjFrwkr.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjFrwkr.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjFrwkr.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjFrwkr.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjFrwkr.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjFrwkr.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjFrwkr.getColumn1()).isEqualTo(DEFAULT_COLUMN1);
    }

    @Test
    @Transactional
    void createBidPrjFrwkrWithExistingId() throws Exception {
        // Create the BidPrjFrwkr with an existing ID
        bidPrjFrwkr.setId(new BidPrjFrwkrPrimaryKey("12345678","12345678", LocalDate.now()));

        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);

        int databaseSizeBeforeCreate = bidPrjFrwkrRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjFrwkrMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjFrwkr in the database
        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFrwkrRepository.findAll().size();
        // set the field null
        bidPrjFrwkr.setWkut(null);

        // Create the BidPrjFrwkr, which fails.
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);

        restBidPrjFrwkrMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFrwkrRepository.findAll().size();
        // set the field null
        bidPrjFrwkr.setPrjno(null);

        // Create the BidPrjFrwkr, which fails.
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);

        restBidPrjFrwkrMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSdateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFrwkrRepository.findAll().size();
        // set the field null
        bidPrjFrwkr.setSdate(null);

        // Create the BidPrjFrwkr, which fails.
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);

        restBidPrjFrwkrMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFrwkrRepository.findAll().size();
        // set the field null
        bidPrjFrwkr.setCreateDate(null);

        // Create the BidPrjFrwkr, which fails.
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);

        restBidPrjFrwkrMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFrwkrRepository.findAll().size();
        // set the field null
        bidPrjFrwkr.setCreateUser(null);

        // Create the BidPrjFrwkr, which fails.
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);

        restBidPrjFrwkrMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidPrjFrwkrs() throws Exception {
//        // Initialize the database
//        bidPrjFrwkrRepository.saveAndFlush(bidPrjFrwkr);
//
//        // Get all the bidPrjFrwkrList
//        restBidPrjFrwkrMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjFrwkr.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].sdate").value(hasItem(DEFAULT_SDATE.toString())))
//            .andExpect(jsonPath("$.[*].frWkr").value(hasItem(DEFAULT_FR_WKR)))
//            .andExpect(jsonPath("$.[*].frWkred").value(hasItem(DEFAULT_FR_WKRED)))
//            .andExpect(jsonPath("$.[*].frType").value(hasItem(DEFAULT_FR_TYPE)))
//            .andExpect(jsonPath("$.[*].frPno").value(hasItem(DEFAULT_FR_PNO)))
//            .andExpect(jsonPath("$.[*].frRmk").value(hasItem(DEFAULT_FR_RMK)))
//            .andExpect(jsonPath("$.[*].frRate").value(hasItem(sameNumber(DEFAULT_FR_RATE))))
//            .andExpect(jsonPath("$.[*].frKnd1").value(hasItem(DEFAULT_FR_KND1)))
//            .andExpect(jsonPath("$.[*].frKnd2").value(hasItem(DEFAULT_FR_KND2)))
//            .andExpect(jsonPath("$.[*].frKnd3").value(hasItem(DEFAULT_FR_KND3)))
//            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
//            .andExpect(jsonPath("$.[*].column1").value(hasItem(DEFAULT_COLUMN1)));
//    }

//    @Test
//    @Transactional
//    void getBidPrjFrwkr() throws Exception {
//        // Initialize the database
//        bidPrjFrwkrRepository.saveAndFlush(bidPrjFrwkr);
//
//        // Get the bidPrjFrwkr
//        restBidPrjFrwkrMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjFrwkr.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjFrwkr.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.sdate").value(DEFAULT_SDATE.toString()))
//            .andExpect(jsonPath("$.frWkr").value(DEFAULT_FR_WKR))
//            .andExpect(jsonPath("$.frWkred").value(DEFAULT_FR_WKRED))
//            .andExpect(jsonPath("$.frType").value(DEFAULT_FR_TYPE))
//            .andExpect(jsonPath("$.frPno").value(DEFAULT_FR_PNO))
//            .andExpect(jsonPath("$.frRmk").value(DEFAULT_FR_RMK))
//            .andExpect(jsonPath("$.frRate").value(sameNumber(DEFAULT_FR_RATE)))
//            .andExpect(jsonPath("$.frKnd1").value(DEFAULT_FR_KND1))
//            .andExpect(jsonPath("$.frKnd2").value(DEFAULT_FR_KND2))
//            .andExpect(jsonPath("$.frKnd3").value(DEFAULT_FR_KND3))
//            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
//            .andExpect(jsonPath("$.column1").value(DEFAULT_COLUMN1));
//    }

    @Test
    @Transactional
    void getNonExistingBidPrjFrwkr() throws Exception {
        // Get the bidPrjFrwkr
        restBidPrjFrwkrMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjFrwkr() throws Exception {
        // Initialize the database
        bidPrjFrwkrRepository.saveAndFlush(bidPrjFrwkr);

        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();

        // Update the bidPrjFrwkr
        BidPrjFrwkr updatedBidPrjFrwkr = bidPrjFrwkrRepository.findById(bidPrjFrwkr.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjFrwkr are not directly saved in db
        em.detach(updatedBidPrjFrwkr);
        updatedBidPrjFrwkr
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .sdate(UPDATED_SDATE)
            .frWkr(UPDATED_FR_WKR)
            .frWkred(UPDATED_FR_WKRED)
            .frType(UPDATED_FR_TYPE)
            .frPno(UPDATED_FR_PNO)
            .frRmk(UPDATED_FR_RMK)
            .frRate(UPDATED_FR_RATE)
            .frKnd1(UPDATED_FR_KND1)
            .frKnd2(UPDATED_FR_KND2)
            .frKnd3(UPDATED_FR_KND3)
            .confOut(UPDATED_CONF_OUT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .column1(UPDATED_COLUMN1);
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(updatedBidPrjFrwkr);

        restBidPrjFrwkrMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjFrwkrDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjFrwkr in the database
        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
        BidPrjFrwkr testBidPrjFrwkr = bidPrjFrwkrList.get(bidPrjFrwkrList.size() - 1);
        assertThat(testBidPrjFrwkr.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjFrwkr.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjFrwkr.getSdate()).isEqualTo(UPDATED_SDATE);
        assertThat(testBidPrjFrwkr.getFrWkr()).isEqualTo(UPDATED_FR_WKR);
        assertThat(testBidPrjFrwkr.getFrWkred()).isEqualTo(UPDATED_FR_WKRED);
        assertThat(testBidPrjFrwkr.getFrType()).isEqualTo(UPDATED_FR_TYPE);
        assertThat(testBidPrjFrwkr.getFrPno()).isEqualTo(UPDATED_FR_PNO);
        assertThat(testBidPrjFrwkr.getFrRmk()).isEqualTo(UPDATED_FR_RMK);
        assertThat(testBidPrjFrwkr.getFrRate()).isEqualByComparingTo(UPDATED_FR_RATE);
        assertThat(testBidPrjFrwkr.getFrKnd1()).isEqualTo(UPDATED_FR_KND1);
        assertThat(testBidPrjFrwkr.getFrKnd2()).isEqualTo(UPDATED_FR_KND2);
        assertThat(testBidPrjFrwkr.getFrKnd3()).isEqualTo(UPDATED_FR_KND3);
        assertThat(testBidPrjFrwkr.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjFrwkr.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjFrwkr.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjFrwkr.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjFrwkr.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjFrwkr.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjFrwkr.getColumn1()).isEqualTo(UPDATED_COLUMN1);
    }

//    @Test
//    @Transactional
//    void putNonExistingBidPrjFrwkr() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();
//        bidPrjFrwkr.setId(count.incrementAndGet());
//
//        // Create the BidPrjFrwkr
//        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjFrwkrMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjFrwkrDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFrwkr in the database
//        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
//        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchBidPrjFrwkr() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();
//        bidPrjFrwkr.setId(count.incrementAndGet());
//
//        // Create the BidPrjFrwkr
//        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFrwkrMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFrwkr in the database
//        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
//        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidPrjFrwkr() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();
//        bidPrjFrwkr.setId(count.incrementAndGet());
//
//        // Create the BidPrjFrwkr
//        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFrwkrMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjFrwkr in the database
//        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
//        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void partialUpdateBidPrjFrwkrWithPatch() throws Exception {
        // Initialize the database
        bidPrjFrwkrRepository.saveAndFlush(bidPrjFrwkr);

        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();

        // Update the bidPrjFrwkr using partial update
        BidPrjFrwkr partialUpdatedBidPrjFrwkr = new BidPrjFrwkr();
        partialUpdatedBidPrjFrwkr.setId(bidPrjFrwkr.getId());

        partialUpdatedBidPrjFrwkr
            .wkut(UPDATED_WKUT)
            .frWkred(UPDATED_FR_WKRED)
            .frPno(UPDATED_FR_PNO)
            .frKnd1(UPDATED_FR_KND1)
            .frKnd3(UPDATED_FR_KND3)
            .confOut(UPDATED_CONF_OUT)
            .createUser(UPDATED_CREATE_USER)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP);

        restBidPrjFrwkrMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjFrwkr.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjFrwkr))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjFrwkr in the database
        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
        BidPrjFrwkr testBidPrjFrwkr = bidPrjFrwkrList.get(bidPrjFrwkrList.size() - 1);
        assertThat(testBidPrjFrwkr.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjFrwkr.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjFrwkr.getSdate()).isEqualTo(DEFAULT_SDATE);
        assertThat(testBidPrjFrwkr.getFrWkr()).isEqualTo(DEFAULT_FR_WKR);
        assertThat(testBidPrjFrwkr.getFrWkred()).isEqualTo(UPDATED_FR_WKRED);
        assertThat(testBidPrjFrwkr.getFrType()).isEqualTo(DEFAULT_FR_TYPE);
        assertThat(testBidPrjFrwkr.getFrPno()).isEqualTo(UPDATED_FR_PNO);
        assertThat(testBidPrjFrwkr.getFrRmk()).isEqualTo(DEFAULT_FR_RMK);
        assertThat(testBidPrjFrwkr.getFrRate()).isEqualByComparingTo(DEFAULT_FR_RATE);
        assertThat(testBidPrjFrwkr.getFrKnd1()).isEqualTo(UPDATED_FR_KND1);
        assertThat(testBidPrjFrwkr.getFrKnd2()).isEqualTo(DEFAULT_FR_KND2);
        assertThat(testBidPrjFrwkr.getFrKnd3()).isEqualTo(UPDATED_FR_KND3);
        assertThat(testBidPrjFrwkr.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjFrwkr.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjFrwkr.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjFrwkr.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjFrwkr.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjFrwkr.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjFrwkr.getColumn1()).isEqualTo(DEFAULT_COLUMN1);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjFrwkrWithPatch() throws Exception {
        // Initialize the database
        bidPrjFrwkrRepository.saveAndFlush(bidPrjFrwkr);

        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();

        // Update the bidPrjFrwkr using partial update
        BidPrjFrwkr partialUpdatedBidPrjFrwkr = new BidPrjFrwkr();
        partialUpdatedBidPrjFrwkr.setId(bidPrjFrwkr.getId());

        partialUpdatedBidPrjFrwkr
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .sdate(UPDATED_SDATE)
            .frWkr(UPDATED_FR_WKR)
            .frWkred(UPDATED_FR_WKRED)
            .frType(UPDATED_FR_TYPE)
            .frPno(UPDATED_FR_PNO)
            .frRmk(UPDATED_FR_RMK)
            .frRate(UPDATED_FR_RATE)
            .frKnd1(UPDATED_FR_KND1)
            .frKnd2(UPDATED_FR_KND2)
            .frKnd3(UPDATED_FR_KND3)
            .confOut(UPDATED_CONF_OUT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .column1(UPDATED_COLUMN1);

        restBidPrjFrwkrMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjFrwkr.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjFrwkr))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjFrwkr in the database
        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
        BidPrjFrwkr testBidPrjFrwkr = bidPrjFrwkrList.get(bidPrjFrwkrList.size() - 1);
        assertThat(testBidPrjFrwkr.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjFrwkr.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjFrwkr.getSdate()).isEqualTo(UPDATED_SDATE);
        assertThat(testBidPrjFrwkr.getFrWkr()).isEqualTo(UPDATED_FR_WKR);
        assertThat(testBidPrjFrwkr.getFrWkred()).isEqualTo(UPDATED_FR_WKRED);
        assertThat(testBidPrjFrwkr.getFrType()).isEqualTo(UPDATED_FR_TYPE);
        assertThat(testBidPrjFrwkr.getFrPno()).isEqualTo(UPDATED_FR_PNO);
        assertThat(testBidPrjFrwkr.getFrRmk()).isEqualTo(UPDATED_FR_RMK);
        assertThat(testBidPrjFrwkr.getFrRate()).isEqualByComparingTo(UPDATED_FR_RATE);
        assertThat(testBidPrjFrwkr.getFrKnd1()).isEqualTo(UPDATED_FR_KND1);
        assertThat(testBidPrjFrwkr.getFrKnd2()).isEqualTo(UPDATED_FR_KND2);
        assertThat(testBidPrjFrwkr.getFrKnd3()).isEqualTo(UPDATED_FR_KND3);
        assertThat(testBidPrjFrwkr.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjFrwkr.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjFrwkr.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjFrwkr.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjFrwkr.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjFrwkr.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjFrwkr.getColumn1()).isEqualTo(UPDATED_COLUMN1);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidPrjFrwkr() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();
//        bidPrjFrwkr.setId(count.incrementAndGet());
//
//        // Create the BidPrjFrwkr
//        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjFrwkrMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjFrwkrDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFrwkr in the database
//        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
//        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchBidPrjFrwkr() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();
//        bidPrjFrwkr.setId(count.incrementAndGet());
//
//        // Create the BidPrjFrwkr
//        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFrwkrMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFrwkr in the database
//        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
//        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidPrjFrwkr() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFrwkrRepository.findAll().size();
//        bidPrjFrwkr.setId(count.incrementAndGet());
//
//        // Create the BidPrjFrwkr
//        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrMapper.toDto(bidPrjFrwkr);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFrwkrMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjFrwkrDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjFrwkr in the database
//        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
//        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidPrjFrwkr() throws Exception {
        // Initialize the database
        bidPrjFrwkrRepository.saveAndFlush(bidPrjFrwkr);

        int databaseSizeBeforeDelete = bidPrjFrwkrRepository.findAll().size();

        // Delete the bidPrjFrwkr
        restBidPrjFrwkrMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjFrwkr.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjFrwkr> bidPrjFrwkrList = bidPrjFrwkrRepository.findAll();
        assertThat(bidPrjFrwkrList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
