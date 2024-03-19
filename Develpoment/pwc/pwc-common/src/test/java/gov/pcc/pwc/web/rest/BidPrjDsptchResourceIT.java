package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjDsptch;
import gov.pcc.pwc.repository.BidPrjDsptchRepository;
import gov.pcc.pwc.service.dto.BidPrjDsptchDTO;
import gov.pcc.pwc.service.mapper.BidPrjDsptchMapper;
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
 * Integration tests for the {@link BidPrjDsptchResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjDsptchResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_DSP_NO = "AAAAAAAAAA";
    private static final String UPDATED_DSP_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DSP_SDAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DSP_SDAT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CCUT_1_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CCUT_1_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_CCUT_1_FAX = "AAAAAAAAAA";
    private static final String UPDATED_CCUT_1_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_CCUT_1_ER = "AAAAAAAAAA";
    private static final String UPDATED_CCUT_1_ER = "BBBBBBBBBB";

    private static final String DEFAULT_CCUT_1_ER_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CCUT_1_ER_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_SCUT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_SCUT_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_SCUT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_SCUT_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_SCUTER = "AAAAAAAAAA";
    private static final String UPDATED_SCUTER = "BBBBBBBBBB";

    private static final String DEFAULT_SCUTER_TEL = "AAAAAAAAAA";
    private static final String UPDATED_SCUTER_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_DSP_SPEED = "AA";
    private static final String UPDATED_DSP_SPEED = "BB";

    private static final String DEFAULT_DSP_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_DSP_ITEM = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DSP_PMON = new BigDecimal(1);
    private static final BigDecimal UPDATED_DSP_PMON = new BigDecimal(2);

    private static final LocalDate DEFAULT_DSP_PEDAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DSP_PEDAT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DSP_AEDAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DSP_AEDAT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DSP_F_1_DAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DSP_F_1_DAT = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_DSP_AMON = new BigDecimal(1);
    private static final BigDecimal UPDATED_DSP_AMON = new BigDecimal(2);

    private static final LocalDate DEFAULT_DSP_F_2_DAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DSP_F_2_DAT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_SENDTEL = "AAAAAAAAAA";
    private static final String UPDATED_SENDTEL = "BBBBBBBBBB";

    private static final String DEFAULT_SENDFAX = "AAAAAAAAAA";
    private static final String UPDATED_SENDFAX = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_DSP_RMK = "AAAAAAAAAA";
    private static final String UPDATED_DSP_RMK = "BBBBBBBBBB";

    private static final String DEFAULT_DSTS = "A";
    private static final String UPDATED_DSTS = "B";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_DSP_TEMP = "AAAAAAAAAA";
    private static final String UPDATED_DSP_TEMP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-dsptches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjDsptchRepository bidPrjDsptchRepository;

    @Autowired
    private BidPrjDsptchMapper bidPrjDsptchMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjDsptchMockMvc;

    private BidPrjDsptch bidPrjDsptch;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjDsptch createEntity(EntityManager em) {
        BidPrjDsptch bidPrjDsptch = new BidPrjDsptch()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .dspNo(DEFAULT_DSP_NO)
            .dspSdat(DEFAULT_DSP_SDAT)
            .ccut1Tel(DEFAULT_CCUT_1_TEL)
            .ccut1Fax(DEFAULT_CCUT_1_FAX)
            .ccut1er(DEFAULT_CCUT_1_ER)
            .ccut1erTel(DEFAULT_CCUT_1_ER_TEL)
            .scutTel(DEFAULT_SCUT_TEL)
            .scutFax(DEFAULT_SCUT_FAX)
            .scuter(DEFAULT_SCUTER)
            .scuterTel(DEFAULT_SCUTER_TEL)
            .dspSpeed(DEFAULT_DSP_SPEED)
            .dspItem(DEFAULT_DSP_ITEM)
            .dspPmon(DEFAULT_DSP_PMON)
            .dspPedat(DEFAULT_DSP_PEDAT)
            .dspAedat(DEFAULT_DSP_AEDAT)
            .dspF1dat(DEFAULT_DSP_F_1_DAT)
            .dspAmon(DEFAULT_DSP_AMON)
            .dspF2dat(DEFAULT_DSP_F_2_DAT)
            .sender(DEFAULT_SENDER)
            .sendtel(DEFAULT_SENDTEL)
            .sendfax(DEFAULT_SENDFAX)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .dspRmk(DEFAULT_DSP_RMK)
            .dsts(DEFAULT_DSTS)
            .uIp(DEFAULT_U_IP)
            .dspTemp(DEFAULT_DSP_TEMP);
        return bidPrjDsptch;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjDsptch createUpdatedEntity(EntityManager em) {
        BidPrjDsptch bidPrjDsptch = new BidPrjDsptch()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .dspNo(UPDATED_DSP_NO)
            .dspSdat(UPDATED_DSP_SDAT)
            .ccut1Tel(UPDATED_CCUT_1_TEL)
            .ccut1Fax(UPDATED_CCUT_1_FAX)
            .ccut1er(UPDATED_CCUT_1_ER)
            .ccut1erTel(UPDATED_CCUT_1_ER_TEL)
            .scutTel(UPDATED_SCUT_TEL)
            .scutFax(UPDATED_SCUT_FAX)
            .scuter(UPDATED_SCUTER)
            .scuterTel(UPDATED_SCUTER_TEL)
            .dspSpeed(UPDATED_DSP_SPEED)
            .dspItem(UPDATED_DSP_ITEM)
            .dspPmon(UPDATED_DSP_PMON)
            .dspPedat(UPDATED_DSP_PEDAT)
            .dspAedat(UPDATED_DSP_AEDAT)
            .dspF1dat(UPDATED_DSP_F_1_DAT)
            .dspAmon(UPDATED_DSP_AMON)
            .dspF2dat(UPDATED_DSP_F_2_DAT)
            .sender(UPDATED_SENDER)
            .sendtel(UPDATED_SENDTEL)
            .sendfax(UPDATED_SENDFAX)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dspRmk(UPDATED_DSP_RMK)
            .dsts(UPDATED_DSTS)
            .uIp(UPDATED_U_IP)
            .dspTemp(UPDATED_DSP_TEMP);
        return bidPrjDsptch;
    }

    @BeforeEach
    public void initTest() {
        bidPrjDsptch = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjDsptch() throws Exception {
        int databaseSizeBeforeCreate = bidPrjDsptchRepository.findAll().size();
        // Create the BidPrjDsptch
        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
        restBidPrjDsptchMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjDsptch in the database
        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjDsptch testBidPrjDsptch = bidPrjDsptchList.get(bidPrjDsptchList.size() - 1);
        assertThat(testBidPrjDsptch.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjDsptch.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjDsptch.getDspNo()).isEqualTo(DEFAULT_DSP_NO);
        assertThat(testBidPrjDsptch.getDspSdat()).isEqualTo(DEFAULT_DSP_SDAT);
        assertThat(testBidPrjDsptch.getCcut1Tel()).isEqualTo(DEFAULT_CCUT_1_TEL);
        assertThat(testBidPrjDsptch.getCcut1Fax()).isEqualTo(DEFAULT_CCUT_1_FAX);
        assertThat(testBidPrjDsptch.getCcut1er()).isEqualTo(DEFAULT_CCUT_1_ER);
        assertThat(testBidPrjDsptch.getCcut1erTel()).isEqualTo(DEFAULT_CCUT_1_ER_TEL);
        assertThat(testBidPrjDsptch.getScutTel()).isEqualTo(DEFAULT_SCUT_TEL);
        assertThat(testBidPrjDsptch.getScutFax()).isEqualTo(DEFAULT_SCUT_FAX);
        assertThat(testBidPrjDsptch.getScuter()).isEqualTo(DEFAULT_SCUTER);
        assertThat(testBidPrjDsptch.getScuterTel()).isEqualTo(DEFAULT_SCUTER_TEL);
        assertThat(testBidPrjDsptch.getDspSpeed()).isEqualTo(DEFAULT_DSP_SPEED);
        assertThat(testBidPrjDsptch.getDspItem()).isEqualTo(DEFAULT_DSP_ITEM);
        assertThat(testBidPrjDsptch.getDspPmon()).isEqualByComparingTo(DEFAULT_DSP_PMON);
        assertThat(testBidPrjDsptch.getDspPedat()).isEqualTo(DEFAULT_DSP_PEDAT);
        assertThat(testBidPrjDsptch.getDspAedat()).isEqualTo(DEFAULT_DSP_AEDAT);
        assertThat(testBidPrjDsptch.getDspF1dat()).isEqualTo(DEFAULT_DSP_F_1_DAT);
        assertThat(testBidPrjDsptch.getDspAmon()).isEqualByComparingTo(DEFAULT_DSP_AMON);
        assertThat(testBidPrjDsptch.getDspF2dat()).isEqualTo(DEFAULT_DSP_F_2_DAT);
        assertThat(testBidPrjDsptch.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testBidPrjDsptch.getSendtel()).isEqualTo(DEFAULT_SENDTEL);
        assertThat(testBidPrjDsptch.getSendfax()).isEqualTo(DEFAULT_SENDFAX);
        assertThat(testBidPrjDsptch.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjDsptch.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjDsptch.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjDsptch.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjDsptch.getDspRmk()).isEqualTo(DEFAULT_DSP_RMK);
        assertThat(testBidPrjDsptch.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjDsptch.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjDsptch.getDspTemp()).isEqualTo(DEFAULT_DSP_TEMP);
    }

    @Test
    @Transactional
    void createBidPrjDsptchWithExistingId() throws Exception {
//        // Create the BidPrjDsptch with an existing ID
//        bidPrjDsptch.setId(1L);
//        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
//
//        int databaseSizeBeforeCreate = bidPrjDsptchRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjDsptchMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDsptch in the database
//        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
//        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjDsptches() throws Exception {
        // Initialize the database
        bidPrjDsptchRepository.saveAndFlush(bidPrjDsptch);

        // Get all the bidPrjDsptchList
        restBidPrjDsptchMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjDsptch.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].dspNo").value(hasItem(DEFAULT_DSP_NO)))
            .andExpect(jsonPath("$.[*].dspSdat").value(hasItem(DEFAULT_DSP_SDAT.toString())))
            .andExpect(jsonPath("$.[*].ccut1Tel").value(hasItem(DEFAULT_CCUT_1_TEL)))
            .andExpect(jsonPath("$.[*].ccut1Fax").value(hasItem(DEFAULT_CCUT_1_FAX)))
            .andExpect(jsonPath("$.[*].ccut1er").value(hasItem(DEFAULT_CCUT_1_ER)))
            .andExpect(jsonPath("$.[*].ccut1erTel").value(hasItem(DEFAULT_CCUT_1_ER_TEL)))
            .andExpect(jsonPath("$.[*].scutTel").value(hasItem(DEFAULT_SCUT_TEL)))
            .andExpect(jsonPath("$.[*].scutFax").value(hasItem(DEFAULT_SCUT_FAX)))
            .andExpect(jsonPath("$.[*].scuter").value(hasItem(DEFAULT_SCUTER)))
            .andExpect(jsonPath("$.[*].scuterTel").value(hasItem(DEFAULT_SCUTER_TEL)))
            .andExpect(jsonPath("$.[*].dspSpeed").value(hasItem(DEFAULT_DSP_SPEED)))
            .andExpect(jsonPath("$.[*].dspItem").value(hasItem(DEFAULT_DSP_ITEM)))
            .andExpect(jsonPath("$.[*].dspPmon").value(hasItem(sameNumber(DEFAULT_DSP_PMON))))
            .andExpect(jsonPath("$.[*].dspPedat").value(hasItem(DEFAULT_DSP_PEDAT.toString())))
            .andExpect(jsonPath("$.[*].dspAedat").value(hasItem(DEFAULT_DSP_AEDAT.toString())))
            .andExpect(jsonPath("$.[*].dspF1dat").value(hasItem(DEFAULT_DSP_F_1_DAT.toString())))
            .andExpect(jsonPath("$.[*].dspAmon").value(hasItem(sameNumber(DEFAULT_DSP_AMON))))
            .andExpect(jsonPath("$.[*].dspF2dat").value(hasItem(DEFAULT_DSP_F_2_DAT.toString())))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].sendtel").value(hasItem(DEFAULT_SENDTEL)))
            .andExpect(jsonPath("$.[*].sendfax").value(hasItem(DEFAULT_SENDFAX)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].dspRmk").value(hasItem(DEFAULT_DSP_RMK)))
            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
            .andExpect(jsonPath("$.[*].dspTemp").value(hasItem(DEFAULT_DSP_TEMP)));
    }

    @Test
    @Transactional
    void getBidPrjDsptch() throws Exception {
        // Initialize the database
        bidPrjDsptchRepository.saveAndFlush(bidPrjDsptch);

        // Get the bidPrjDsptch
        restBidPrjDsptchMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjDsptch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjDsptch.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.dspNo").value(DEFAULT_DSP_NO))
            .andExpect(jsonPath("$.dspSdat").value(DEFAULT_DSP_SDAT.toString()))
            .andExpect(jsonPath("$.ccut1Tel").value(DEFAULT_CCUT_1_TEL))
            .andExpect(jsonPath("$.ccut1Fax").value(DEFAULT_CCUT_1_FAX))
            .andExpect(jsonPath("$.ccut1er").value(DEFAULT_CCUT_1_ER))
            .andExpect(jsonPath("$.ccut1erTel").value(DEFAULT_CCUT_1_ER_TEL))
            .andExpect(jsonPath("$.scutTel").value(DEFAULT_SCUT_TEL))
            .andExpect(jsonPath("$.scutFax").value(DEFAULT_SCUT_FAX))
            .andExpect(jsonPath("$.scuter").value(DEFAULT_SCUTER))
            .andExpect(jsonPath("$.scuterTel").value(DEFAULT_SCUTER_TEL))
            .andExpect(jsonPath("$.dspSpeed").value(DEFAULT_DSP_SPEED))
            .andExpect(jsonPath("$.dspItem").value(DEFAULT_DSP_ITEM))
            .andExpect(jsonPath("$.dspPmon").value(sameNumber(DEFAULT_DSP_PMON)))
            .andExpect(jsonPath("$.dspPedat").value(DEFAULT_DSP_PEDAT.toString()))
            .andExpect(jsonPath("$.dspAedat").value(DEFAULT_DSP_AEDAT.toString()))
            .andExpect(jsonPath("$.dspF1dat").value(DEFAULT_DSP_F_1_DAT.toString()))
            .andExpect(jsonPath("$.dspAmon").value(sameNumber(DEFAULT_DSP_AMON)))
            .andExpect(jsonPath("$.dspF2dat").value(DEFAULT_DSP_F_2_DAT.toString()))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.sendtel").value(DEFAULT_SENDTEL))
            .andExpect(jsonPath("$.sendfax").value(DEFAULT_SENDFAX))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.dspRmk").value(DEFAULT_DSP_RMK))
            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
            .andExpect(jsonPath("$.dspTemp").value(DEFAULT_DSP_TEMP));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjDsptch() throws Exception {
        // Get the bidPrjDsptch
        restBidPrjDsptchMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjDsptch() throws Exception {
        // Initialize the database
        bidPrjDsptchRepository.saveAndFlush(bidPrjDsptch);

        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();

        // Update the bidPrjDsptch
        BidPrjDsptch updatedBidPrjDsptch = bidPrjDsptchRepository.findById(bidPrjDsptch.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjDsptch are not directly saved in db
        em.detach(updatedBidPrjDsptch);
        updatedBidPrjDsptch
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .dspNo(UPDATED_DSP_NO)
            .dspSdat(UPDATED_DSP_SDAT)
            .ccut1Tel(UPDATED_CCUT_1_TEL)
            .ccut1Fax(UPDATED_CCUT_1_FAX)
            .ccut1er(UPDATED_CCUT_1_ER)
            .ccut1erTel(UPDATED_CCUT_1_ER_TEL)
            .scutTel(UPDATED_SCUT_TEL)
            .scutFax(UPDATED_SCUT_FAX)
            .scuter(UPDATED_SCUTER)
            .scuterTel(UPDATED_SCUTER_TEL)
            .dspSpeed(UPDATED_DSP_SPEED)
            .dspItem(UPDATED_DSP_ITEM)
            .dspPmon(UPDATED_DSP_PMON)
            .dspPedat(UPDATED_DSP_PEDAT)
            .dspAedat(UPDATED_DSP_AEDAT)
            .dspF1dat(UPDATED_DSP_F_1_DAT)
            .dspAmon(UPDATED_DSP_AMON)
            .dspF2dat(UPDATED_DSP_F_2_DAT)
            .sender(UPDATED_SENDER)
            .sendtel(UPDATED_SENDTEL)
            .sendfax(UPDATED_SENDFAX)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dspRmk(UPDATED_DSP_RMK)
            .dsts(UPDATED_DSTS)
            .uIp(UPDATED_U_IP)
            .dspTemp(UPDATED_DSP_TEMP);
        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(updatedBidPrjDsptch);

        restBidPrjDsptchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjDsptchDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjDsptch in the database
        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
        BidPrjDsptch testBidPrjDsptch = bidPrjDsptchList.get(bidPrjDsptchList.size() - 1);
        assertThat(testBidPrjDsptch.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjDsptch.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjDsptch.getDspNo()).isEqualTo(UPDATED_DSP_NO);
        assertThat(testBidPrjDsptch.getDspSdat()).isEqualTo(UPDATED_DSP_SDAT);
        assertThat(testBidPrjDsptch.getCcut1Tel()).isEqualTo(UPDATED_CCUT_1_TEL);
        assertThat(testBidPrjDsptch.getCcut1Fax()).isEqualTo(UPDATED_CCUT_1_FAX);
        assertThat(testBidPrjDsptch.getCcut1er()).isEqualTo(UPDATED_CCUT_1_ER);
        assertThat(testBidPrjDsptch.getCcut1erTel()).isEqualTo(UPDATED_CCUT_1_ER_TEL);
        assertThat(testBidPrjDsptch.getScutTel()).isEqualTo(UPDATED_SCUT_TEL);
        assertThat(testBidPrjDsptch.getScutFax()).isEqualTo(UPDATED_SCUT_FAX);
        assertThat(testBidPrjDsptch.getScuter()).isEqualTo(UPDATED_SCUTER);
        assertThat(testBidPrjDsptch.getScuterTel()).isEqualTo(UPDATED_SCUTER_TEL);
        assertThat(testBidPrjDsptch.getDspSpeed()).isEqualTo(UPDATED_DSP_SPEED);
        assertThat(testBidPrjDsptch.getDspItem()).isEqualTo(UPDATED_DSP_ITEM);
        assertThat(testBidPrjDsptch.getDspPmon()).isEqualByComparingTo(UPDATED_DSP_PMON);
        assertThat(testBidPrjDsptch.getDspPedat()).isEqualTo(UPDATED_DSP_PEDAT);
        assertThat(testBidPrjDsptch.getDspAedat()).isEqualTo(UPDATED_DSP_AEDAT);
        assertThat(testBidPrjDsptch.getDspF1dat()).isEqualTo(UPDATED_DSP_F_1_DAT);
        assertThat(testBidPrjDsptch.getDspAmon()).isEqualByComparingTo(UPDATED_DSP_AMON);
        assertThat(testBidPrjDsptch.getDspF2dat()).isEqualTo(UPDATED_DSP_F_2_DAT);
        assertThat(testBidPrjDsptch.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testBidPrjDsptch.getSendtel()).isEqualTo(UPDATED_SENDTEL);
        assertThat(testBidPrjDsptch.getSendfax()).isEqualTo(UPDATED_SENDFAX);
        assertThat(testBidPrjDsptch.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjDsptch.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjDsptch.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjDsptch.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjDsptch.getDspRmk()).isEqualTo(UPDATED_DSP_RMK);
        assertThat(testBidPrjDsptch.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjDsptch.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjDsptch.getDspTemp()).isEqualTo(UPDATED_DSP_TEMP);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjDsptch() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();
//        bidPrjDsptch.setId(count.incrementAndGet());
//
//        // Create the BidPrjDsptch
//        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjDsptchMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjDsptchDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDsptch in the database
//        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
//        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjDsptch() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();
//        bidPrjDsptch.setId(count.incrementAndGet());
//
//        // Create the BidPrjDsptch
//        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDsptchMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDsptch in the database
//        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
//        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjDsptch() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();
//        bidPrjDsptch.setId(count.incrementAndGet());
//
//        // Create the BidPrjDsptch
//        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDsptchMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjDsptch in the database
//        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
//        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjDsptchWithPatch() throws Exception {
        // Initialize the database
        bidPrjDsptchRepository.saveAndFlush(bidPrjDsptch);

        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();

        // Update the bidPrjDsptch using partial update
        BidPrjDsptch partialUpdatedBidPrjDsptch = new BidPrjDsptch();
        partialUpdatedBidPrjDsptch.setId(bidPrjDsptch.getId());

        partialUpdatedBidPrjDsptch
            .prjno(UPDATED_PRJNO)
            .dspNo(UPDATED_DSP_NO)
            .dspSdat(UPDATED_DSP_SDAT)
            .ccut1Tel(UPDATED_CCUT_1_TEL)
            .ccut1Fax(UPDATED_CCUT_1_FAX)
            .ccut1er(UPDATED_CCUT_1_ER)
            .scuterTel(UPDATED_SCUTER_TEL)
            .dspPmon(UPDATED_DSP_PMON)
            .dspPedat(UPDATED_DSP_PEDAT)
            .dspF1dat(UPDATED_DSP_F_1_DAT)
            .sender(UPDATED_SENDER)
            .sendtel(UPDATED_SENDTEL)
            .createUser(UPDATED_CREATE_USER)
            .updateUser(UPDATED_UPDATE_USER)
            .dspRmk(UPDATED_DSP_RMK)
            .dsts(UPDATED_DSTS);

        restBidPrjDsptchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjDsptch.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjDsptch))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjDsptch in the database
        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
        BidPrjDsptch testBidPrjDsptch = bidPrjDsptchList.get(bidPrjDsptchList.size() - 1);
        assertThat(testBidPrjDsptch.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjDsptch.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjDsptch.getDspNo()).isEqualTo(UPDATED_DSP_NO);
        assertThat(testBidPrjDsptch.getDspSdat()).isEqualTo(UPDATED_DSP_SDAT);
        assertThat(testBidPrjDsptch.getCcut1Tel()).isEqualTo(UPDATED_CCUT_1_TEL);
        assertThat(testBidPrjDsptch.getCcut1Fax()).isEqualTo(UPDATED_CCUT_1_FAX);
        assertThat(testBidPrjDsptch.getCcut1er()).isEqualTo(UPDATED_CCUT_1_ER);
        assertThat(testBidPrjDsptch.getCcut1erTel()).isEqualTo(DEFAULT_CCUT_1_ER_TEL);
        assertThat(testBidPrjDsptch.getScutTel()).isEqualTo(DEFAULT_SCUT_TEL);
        assertThat(testBidPrjDsptch.getScutFax()).isEqualTo(DEFAULT_SCUT_FAX);
        assertThat(testBidPrjDsptch.getScuter()).isEqualTo(DEFAULT_SCUTER);
        assertThat(testBidPrjDsptch.getScuterTel()).isEqualTo(UPDATED_SCUTER_TEL);
        assertThat(testBidPrjDsptch.getDspSpeed()).isEqualTo(DEFAULT_DSP_SPEED);
        assertThat(testBidPrjDsptch.getDspItem()).isEqualTo(DEFAULT_DSP_ITEM);
        assertThat(testBidPrjDsptch.getDspPmon()).isEqualByComparingTo(UPDATED_DSP_PMON);
        assertThat(testBidPrjDsptch.getDspPedat()).isEqualTo(UPDATED_DSP_PEDAT);
        assertThat(testBidPrjDsptch.getDspAedat()).isEqualTo(DEFAULT_DSP_AEDAT);
        assertThat(testBidPrjDsptch.getDspF1dat()).isEqualTo(UPDATED_DSP_F_1_DAT);
        assertThat(testBidPrjDsptch.getDspAmon()).isEqualByComparingTo(DEFAULT_DSP_AMON);
        assertThat(testBidPrjDsptch.getDspF2dat()).isEqualTo(DEFAULT_DSP_F_2_DAT);
        assertThat(testBidPrjDsptch.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testBidPrjDsptch.getSendtel()).isEqualTo(UPDATED_SENDTEL);
        assertThat(testBidPrjDsptch.getSendfax()).isEqualTo(DEFAULT_SENDFAX);
        assertThat(testBidPrjDsptch.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjDsptch.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjDsptch.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjDsptch.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjDsptch.getDspRmk()).isEqualTo(UPDATED_DSP_RMK);
        assertThat(testBidPrjDsptch.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjDsptch.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjDsptch.getDspTemp()).isEqualTo(DEFAULT_DSP_TEMP);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjDsptchWithPatch() throws Exception {
        // Initialize the database
        bidPrjDsptchRepository.saveAndFlush(bidPrjDsptch);

        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();

        // Update the bidPrjDsptch using partial update
        BidPrjDsptch partialUpdatedBidPrjDsptch = new BidPrjDsptch();
        partialUpdatedBidPrjDsptch.setId(bidPrjDsptch.getId());

        partialUpdatedBidPrjDsptch
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .dspNo(UPDATED_DSP_NO)
            .dspSdat(UPDATED_DSP_SDAT)
            .ccut1Tel(UPDATED_CCUT_1_TEL)
            .ccut1Fax(UPDATED_CCUT_1_FAX)
            .ccut1er(UPDATED_CCUT_1_ER)
            .ccut1erTel(UPDATED_CCUT_1_ER_TEL)
            .scutTel(UPDATED_SCUT_TEL)
            .scutFax(UPDATED_SCUT_FAX)
            .scuter(UPDATED_SCUTER)
            .scuterTel(UPDATED_SCUTER_TEL)
            .dspSpeed(UPDATED_DSP_SPEED)
            .dspItem(UPDATED_DSP_ITEM)
            .dspPmon(UPDATED_DSP_PMON)
            .dspPedat(UPDATED_DSP_PEDAT)
            .dspAedat(UPDATED_DSP_AEDAT)
            .dspF1dat(UPDATED_DSP_F_1_DAT)
            .dspAmon(UPDATED_DSP_AMON)
            .dspF2dat(UPDATED_DSP_F_2_DAT)
            .sender(UPDATED_SENDER)
            .sendtel(UPDATED_SENDTEL)
            .sendfax(UPDATED_SENDFAX)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dspRmk(UPDATED_DSP_RMK)
            .dsts(UPDATED_DSTS)
            .uIp(UPDATED_U_IP)
            .dspTemp(UPDATED_DSP_TEMP);

        restBidPrjDsptchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjDsptch.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjDsptch))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjDsptch in the database
        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
        BidPrjDsptch testBidPrjDsptch = bidPrjDsptchList.get(bidPrjDsptchList.size() - 1);
        assertThat(testBidPrjDsptch.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjDsptch.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjDsptch.getDspNo()).isEqualTo(UPDATED_DSP_NO);
        assertThat(testBidPrjDsptch.getDspSdat()).isEqualTo(UPDATED_DSP_SDAT);
        assertThat(testBidPrjDsptch.getCcut1Tel()).isEqualTo(UPDATED_CCUT_1_TEL);
        assertThat(testBidPrjDsptch.getCcut1Fax()).isEqualTo(UPDATED_CCUT_1_FAX);
        assertThat(testBidPrjDsptch.getCcut1er()).isEqualTo(UPDATED_CCUT_1_ER);
        assertThat(testBidPrjDsptch.getCcut1erTel()).isEqualTo(UPDATED_CCUT_1_ER_TEL);
        assertThat(testBidPrjDsptch.getScutTel()).isEqualTo(UPDATED_SCUT_TEL);
        assertThat(testBidPrjDsptch.getScutFax()).isEqualTo(UPDATED_SCUT_FAX);
        assertThat(testBidPrjDsptch.getScuter()).isEqualTo(UPDATED_SCUTER);
        assertThat(testBidPrjDsptch.getScuterTel()).isEqualTo(UPDATED_SCUTER_TEL);
        assertThat(testBidPrjDsptch.getDspSpeed()).isEqualTo(UPDATED_DSP_SPEED);
        assertThat(testBidPrjDsptch.getDspItem()).isEqualTo(UPDATED_DSP_ITEM);
        assertThat(testBidPrjDsptch.getDspPmon()).isEqualByComparingTo(UPDATED_DSP_PMON);
        assertThat(testBidPrjDsptch.getDspPedat()).isEqualTo(UPDATED_DSP_PEDAT);
        assertThat(testBidPrjDsptch.getDspAedat()).isEqualTo(UPDATED_DSP_AEDAT);
        assertThat(testBidPrjDsptch.getDspF1dat()).isEqualTo(UPDATED_DSP_F_1_DAT);
        assertThat(testBidPrjDsptch.getDspAmon()).isEqualByComparingTo(UPDATED_DSP_AMON);
        assertThat(testBidPrjDsptch.getDspF2dat()).isEqualTo(UPDATED_DSP_F_2_DAT);
        assertThat(testBidPrjDsptch.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testBidPrjDsptch.getSendtel()).isEqualTo(UPDATED_SENDTEL);
        assertThat(testBidPrjDsptch.getSendfax()).isEqualTo(UPDATED_SENDFAX);
        assertThat(testBidPrjDsptch.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjDsptch.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjDsptch.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjDsptch.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjDsptch.getDspRmk()).isEqualTo(UPDATED_DSP_RMK);
        assertThat(testBidPrjDsptch.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjDsptch.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjDsptch.getDspTemp()).isEqualTo(UPDATED_DSP_TEMP);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjDsptch() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();
//        bidPrjDsptch.setId(count.incrementAndGet());
//
//        // Create the BidPrjDsptch
//        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjDsptchMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjDsptchDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDsptch in the database
//        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
//        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjDsptch() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();
//        bidPrjDsptch.setId(count.incrementAndGet());
//
//        // Create the BidPrjDsptch
//        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDsptchMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDsptch in the database
//        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
//        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjDsptch() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDsptchRepository.findAll().size();
//        bidPrjDsptch.setId(count.incrementAndGet());
//
//        // Create the BidPrjDsptch
//        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchMapper.toDto(bidPrjDsptch);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDsptchMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDsptchDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjDsptch in the database
//        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
//        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjDsptch() throws Exception {
        // Initialize the database
        bidPrjDsptchRepository.saveAndFlush(bidPrjDsptch);

        int databaseSizeBeforeDelete = bidPrjDsptchRepository.findAll().size();

        // Delete the bidPrjDsptch
        restBidPrjDsptchMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjDsptch.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjDsptch> bidPrjDsptchList = bidPrjDsptchRepository.findAll();
        assertThat(bidPrjDsptchList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
