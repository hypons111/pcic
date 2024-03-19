package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjSafe;
import gov.pcc.pwc.domain.BidPrjSafePrimaryKey;
import gov.pcc.pwc.repository.BidPrjSafeRepository;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import gov.pcc.pwc.service.mapper.BidPrjSafeMapper;
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
 * Integration tests for the {@link BidPrjSafeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjSafeResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STYPE = "AA";
    private static final String UPDATED_STYPE = "BB";

    private static final String DEFAULT_MEDIA = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DIED_NUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIED_NUM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HURT_NUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_HURT_NUM = new BigDecimal(2);

    private static final String DEFAULT_DISASTER_SCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_DISASTER_SCRIPT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRM_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRM_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ME_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ME_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MRM_NO = "AAAAAAAAAA";
    private static final String UPDATED_MRM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final String DEFAULT_CT_UNIT = "AA";
    private static final String UPDATED_CT_UNIT = "BB";

    private static final String DEFAULT_CTR_NO = "AAAAAAAAAA";
    private static final String UPDATED_CTR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CCNT = "AAAAAAAAAA";
    private static final String UPDATED_CCNT = "BBBBBBBBBB";

    private static final String DEFAULT_CCUT = "AAAAAAAAAA";
    private static final String UPDATED_CCUT = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONS = "AAAAAAAAAA";
    private static final String UPDATED_RESPONS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_WDAYED = new BigDecimal(1);
    private static final BigDecimal UPDATED_WDAYED = new BigDecimal(2);

    private static final String DEFAULT_RESPONS_2 = "AAAAAAAAAA";
    private static final String UPDATED_RESPONS_2 = "BBBBBBBBBB";

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

    private static final String DEFAULT_PEDATM = "AAAAAAAAAA";
    private static final String UPDATED_PEDATM = "BBBBBBBBBB";

    private static final String DEFAULT_MEDATM = "AAAAAAAAAA";
    private static final String UPDATED_MEDATM = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-safes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjSafeRepository bidPrjSafeRepository;

    @Autowired
    private BidPrjSafeMapper bidPrjSafeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjSafeMockMvc;

    private BidPrjSafe bidPrjSafe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSafe createEntity(EntityManager em) {
        BidPrjSafe bidPrjSafe = new BidPrjSafe()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .sdate(DEFAULT_SDATE)
            .stype(DEFAULT_STYPE)
            .media(DEFAULT_MEDIA)
            .diedNum(DEFAULT_DIED_NUM)
            .hurtNum(DEFAULT_HURT_NUM)
            .disasterScript(DEFAULT_DISASTER_SCRIPT)
            .peDate(DEFAULT_PE_DATE)
            .prmNo(DEFAULT_PRM_NO)
            .meDate(DEFAULT_ME_DATE)
            .mrmNo(DEFAULT_MRM_NO)
            .remark(DEFAULT_REMARK)
            .confOut(DEFAULT_CONF_OUT)
            .ctUnit(DEFAULT_CT_UNIT)
            .ctrNo(DEFAULT_CTR_NO)
            .ccnt(DEFAULT_CCNT)
            .ccut(DEFAULT_CCUT)
            .respons(DEFAULT_RESPONS)
            .wdayed(DEFAULT_WDAYED)
            .respons2(DEFAULT_RESPONS_2)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .uIp(DEFAULT_U_IP)
            .pedatm(DEFAULT_PEDATM)
            .medatm(DEFAULT_MEDATM);
        return bidPrjSafe;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSafe createUpdatedEntity(EntityManager em) {
        BidPrjSafe bidPrjSafe = new BidPrjSafe()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .sdate(UPDATED_SDATE)
            .stype(UPDATED_STYPE)
            .media(UPDATED_MEDIA)
            .diedNum(UPDATED_DIED_NUM)
            .hurtNum(UPDATED_HURT_NUM)
            .disasterScript(UPDATED_DISASTER_SCRIPT)
            .peDate(UPDATED_PE_DATE)
            .prmNo(UPDATED_PRM_NO)
            .meDate(UPDATED_ME_DATE)
            .mrmNo(UPDATED_MRM_NO)
            .remark(UPDATED_REMARK)
            .confOut(UPDATED_CONF_OUT)
            .ctUnit(UPDATED_CT_UNIT)
            .ctrNo(UPDATED_CTR_NO)
            .ccnt(UPDATED_CCNT)
            .ccut(UPDATED_CCUT)
            .respons(UPDATED_RESPONS)
            .wdayed(UPDATED_WDAYED)
            .respons2(UPDATED_RESPONS_2)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .pedatm(UPDATED_PEDATM)
            .medatm(UPDATED_MEDATM);
        return bidPrjSafe;
    }

    @BeforeEach
    public void initTest() {
        bidPrjSafe = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjSafe() throws Exception {
        int databaseSizeBeforeCreate = bidPrjSafeRepository.findAll().size();
        // Create the BidPrjSafe
        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
        restBidPrjSafeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO)))
            .andExpect(status().isCreated());

        // Validate the BidPrjSafe in the database
        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjSafe testBidPrjSafe = bidPrjSafeList.get(bidPrjSafeList.size() - 1);
        assertThat(testBidPrjSafe.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjSafe.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjSafe.getSdate()).isEqualTo(DEFAULT_SDATE);
        assertThat(testBidPrjSafe.getStype()).isEqualTo(DEFAULT_STYPE);
        assertThat(testBidPrjSafe.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testBidPrjSafe.getDiedNum()).isEqualByComparingTo(DEFAULT_DIED_NUM);
        assertThat(testBidPrjSafe.getHurtNum()).isEqualByComparingTo(DEFAULT_HURT_NUM);
        assertThat(testBidPrjSafe.getDisasterScript()).isEqualTo(DEFAULT_DISASTER_SCRIPT);
        assertThat(testBidPrjSafe.getPeDate()).isEqualTo(DEFAULT_PE_DATE);
        assertThat(testBidPrjSafe.getPrmNo()).isEqualTo(DEFAULT_PRM_NO);
        assertThat(testBidPrjSafe.getMeDate()).isEqualTo(DEFAULT_ME_DATE);
        assertThat(testBidPrjSafe.getMrmNo()).isEqualTo(DEFAULT_MRM_NO);
        assertThat(testBidPrjSafe.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBidPrjSafe.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjSafe.getCtUnit()).isEqualTo(DEFAULT_CT_UNIT);
        assertThat(testBidPrjSafe.getCtrNo()).isEqualTo(DEFAULT_CTR_NO);
        assertThat(testBidPrjSafe.getCcnt()).isEqualTo(DEFAULT_CCNT);
        assertThat(testBidPrjSafe.getCcut()).isEqualTo(DEFAULT_CCUT);
        assertThat(testBidPrjSafe.getRespons()).isEqualTo(DEFAULT_RESPONS);
        assertThat(testBidPrjSafe.getWdayed()).isEqualByComparingTo(DEFAULT_WDAYED);
        assertThat(testBidPrjSafe.getRespons2()).isEqualTo(DEFAULT_RESPONS_2);
        assertThat(testBidPrjSafe.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjSafe.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjSafe.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjSafe.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjSafe.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjSafe.getPedatm()).isEqualTo(DEFAULT_PEDATM);
        assertThat(testBidPrjSafe.getMedatm()).isEqualTo(DEFAULT_MEDATM);
    }

    @Test
    @Transactional
    void createBidPrjSafeWithExistingId() throws Exception {
//        // Create the BidPrjSafe with an existing ID
//        bidPrjSafe.setId();
//        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
//
//        int databaseSizeBeforeCreate = bidPrjSafeRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjSafeMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSafe in the database
//        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
//        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjSafes() throws Exception {
        // Initialize the database
        bidPrjSafeRepository.saveAndFlush(bidPrjSafe);

        // Get all the bidPrjSafeList
        restBidPrjSafeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjSafe.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].sdate").value(hasItem(DEFAULT_SDATE.toString())))
            .andExpect(jsonPath("$.[*].stype").value(hasItem(DEFAULT_STYPE)))
            .andExpect(jsonPath("$.[*].media").value(hasItem(DEFAULT_MEDIA)))
            .andExpect(jsonPath("$.[*].diedNum").value(hasItem(sameNumber(DEFAULT_DIED_NUM))))
            .andExpect(jsonPath("$.[*].hurtNum").value(hasItem(sameNumber(DEFAULT_HURT_NUM))))
            .andExpect(jsonPath("$.[*].disasterScript").value(hasItem(DEFAULT_DISASTER_SCRIPT)))
            .andExpect(jsonPath("$.[*].peDate").value(hasItem(DEFAULT_PE_DATE.toString())))
            .andExpect(jsonPath("$.[*].prmNo").value(hasItem(DEFAULT_PRM_NO)))
            .andExpect(jsonPath("$.[*].meDate").value(hasItem(DEFAULT_ME_DATE.toString())))
            .andExpect(jsonPath("$.[*].mrmNo").value(hasItem(DEFAULT_MRM_NO)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
            .andExpect(jsonPath("$.[*].ctUnit").value(hasItem(DEFAULT_CT_UNIT)))
            .andExpect(jsonPath("$.[*].ctrNo").value(hasItem(DEFAULT_CTR_NO)))
            .andExpect(jsonPath("$.[*].ccnt").value(hasItem(DEFAULT_CCNT)))
            .andExpect(jsonPath("$.[*].ccut").value(hasItem(DEFAULT_CCUT)))
            .andExpect(jsonPath("$.[*].respons").value(hasItem(DEFAULT_RESPONS)))
            .andExpect(jsonPath("$.[*].wdayed").value(hasItem(sameNumber(DEFAULT_WDAYED))))
            .andExpect(jsonPath("$.[*].respons2").value(hasItem(DEFAULT_RESPONS_2)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
            .andExpect(jsonPath("$.[*].pedatm").value(hasItem(DEFAULT_PEDATM)))
            .andExpect(jsonPath("$.[*].medatm").value(hasItem(DEFAULT_MEDATM)));
    }

    @Test
    @Transactional
    void getBidPrjSafe() throws Exception {
        // Initialize the database
        bidPrjSafeRepository.saveAndFlush(bidPrjSafe);

        // Get the bidPrjSafe
        restBidPrjSafeMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjSafe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjSafe.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.sdate").value(DEFAULT_SDATE.toString()))
            .andExpect(jsonPath("$.stype").value(DEFAULT_STYPE))
            .andExpect(jsonPath("$.media").value(DEFAULT_MEDIA))
            .andExpect(jsonPath("$.diedNum").value(sameNumber(DEFAULT_DIED_NUM)))
            .andExpect(jsonPath("$.hurtNum").value(sameNumber(DEFAULT_HURT_NUM)))
            .andExpect(jsonPath("$.disasterScript").value(DEFAULT_DISASTER_SCRIPT))
            .andExpect(jsonPath("$.peDate").value(DEFAULT_PE_DATE.toString()))
            .andExpect(jsonPath("$.prmNo").value(DEFAULT_PRM_NO))
            .andExpect(jsonPath("$.meDate").value(DEFAULT_ME_DATE.toString()))
            .andExpect(jsonPath("$.mrmNo").value(DEFAULT_MRM_NO))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
            .andExpect(jsonPath("$.ctUnit").value(DEFAULT_CT_UNIT))
            .andExpect(jsonPath("$.ctrNo").value(DEFAULT_CTR_NO))
            .andExpect(jsonPath("$.ccnt").value(DEFAULT_CCNT))
            .andExpect(jsonPath("$.ccut").value(DEFAULT_CCUT))
            .andExpect(jsonPath("$.respons").value(DEFAULT_RESPONS))
            .andExpect(jsonPath("$.wdayed").value(sameNumber(DEFAULT_WDAYED)))
            .andExpect(jsonPath("$.respons2").value(DEFAULT_RESPONS_2))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
            .andExpect(jsonPath("$.pedatm").value(DEFAULT_PEDATM))
            .andExpect(jsonPath("$.medatm").value(DEFAULT_MEDATM));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjSafe() throws Exception {
        // Get the bidPrjSafe
        restBidPrjSafeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjSafe() throws Exception {
        // Initialize the database
        bidPrjSafeRepository.saveAndFlush(bidPrjSafe);

        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();

        // Update the bidPrjSafe
        BidPrjSafe updatedBidPrjSafe = bidPrjSafeRepository.findById(bidPrjSafe.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjSafe are not directly saved in db
        em.detach(updatedBidPrjSafe);
        updatedBidPrjSafe
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .sdate(UPDATED_SDATE)
            .stype(UPDATED_STYPE)
            .media(UPDATED_MEDIA)
            .diedNum(UPDATED_DIED_NUM)
            .hurtNum(UPDATED_HURT_NUM)
            .disasterScript(UPDATED_DISASTER_SCRIPT)
            .peDate(UPDATED_PE_DATE)
            .prmNo(UPDATED_PRM_NO)
            .meDate(UPDATED_ME_DATE)
            .mrmNo(UPDATED_MRM_NO)
            .remark(UPDATED_REMARK)
            .confOut(UPDATED_CONF_OUT)
            .ctUnit(UPDATED_CT_UNIT)
            .ctrNo(UPDATED_CTR_NO)
            .ccnt(UPDATED_CCNT)
            .ccut(UPDATED_CCUT)
            .respons(UPDATED_RESPONS)
            .wdayed(UPDATED_WDAYED)
            .respons2(UPDATED_RESPONS_2)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .pedatm(UPDATED_PEDATM)
            .medatm(UPDATED_MEDATM);
        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(updatedBidPrjSafe);

        restBidPrjSafeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjSafeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSafe in the database
        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSafe testBidPrjSafe = bidPrjSafeList.get(bidPrjSafeList.size() - 1);
        assertThat(testBidPrjSafe.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSafe.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSafe.getSdate()).isEqualTo(UPDATED_SDATE);
        assertThat(testBidPrjSafe.getStype()).isEqualTo(UPDATED_STYPE);
        assertThat(testBidPrjSafe.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testBidPrjSafe.getDiedNum()).isEqualByComparingTo(UPDATED_DIED_NUM);
        assertThat(testBidPrjSafe.getHurtNum()).isEqualByComparingTo(UPDATED_HURT_NUM);
        assertThat(testBidPrjSafe.getDisasterScript()).isEqualTo(UPDATED_DISASTER_SCRIPT);
        assertThat(testBidPrjSafe.getPeDate()).isEqualTo(UPDATED_PE_DATE);
        assertThat(testBidPrjSafe.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
        assertThat(testBidPrjSafe.getMeDate()).isEqualTo(UPDATED_ME_DATE);
        assertThat(testBidPrjSafe.getMrmNo()).isEqualTo(UPDATED_MRM_NO);
        assertThat(testBidPrjSafe.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjSafe.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjSafe.getCtUnit()).isEqualTo(UPDATED_CT_UNIT);
        assertThat(testBidPrjSafe.getCtrNo()).isEqualTo(UPDATED_CTR_NO);
        assertThat(testBidPrjSafe.getCcnt()).isEqualTo(UPDATED_CCNT);
        assertThat(testBidPrjSafe.getCcut()).isEqualTo(UPDATED_CCUT);
        assertThat(testBidPrjSafe.getRespons()).isEqualTo(UPDATED_RESPONS);
        assertThat(testBidPrjSafe.getWdayed()).isEqualByComparingTo(UPDATED_WDAYED);
        assertThat(testBidPrjSafe.getRespons2()).isEqualTo(UPDATED_RESPONS_2);
        assertThat(testBidPrjSafe.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSafe.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSafe.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSafe.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSafe.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjSafe.getPedatm()).isEqualTo(UPDATED_PEDATM);
        assertThat(testBidPrjSafe.getMedatm()).isEqualTo(UPDATED_MEDATM);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjSafe() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();
//        bidPrjSafe.setId(count.incrementAndGet());
//
//        // Create the BidPrjSafe
//        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjSafeMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjSafeDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSafe in the database
//        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
//        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjSafe() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();
//        bidPrjSafe.setId(count.incrementAndGet());
//
//        // Create the BidPrjSafe
//        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSafeMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSafe in the database
//        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
//        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjSafe() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();
//        bidPrjSafe.setId(count.incrementAndGet());
//
//        // Create the BidPrjSafe
//        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSafeMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjSafe in the database
//        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
//        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjSafeWithPatch() throws Exception {
        // Initialize the database
        bidPrjSafeRepository.saveAndFlush(bidPrjSafe);

        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();

        // Update the bidPrjSafe using partial update
        BidPrjSafe partialUpdatedBidPrjSafe = new BidPrjSafe();
        partialUpdatedBidPrjSafe.setId(bidPrjSafe.getId());

        partialUpdatedBidPrjSafe
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .sdate(UPDATED_SDATE)
            .diedNum(UPDATED_DIED_NUM)
            .disasterScript(UPDATED_DISASTER_SCRIPT)
            .peDate(UPDATED_PE_DATE)
            .meDate(UPDATED_ME_DATE)
            .remark(UPDATED_REMARK)
            .ctUnit(UPDATED_CT_UNIT)
            .ctrNo(UPDATED_CTR_NO)
            .respons(UPDATED_RESPONS)
            .respons2(UPDATED_RESPONS_2)
            .createDate(UPDATED_CREATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .medatm(UPDATED_MEDATM);

        restBidPrjSafeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSafe.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSafe))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSafe in the database
        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSafe testBidPrjSafe = bidPrjSafeList.get(bidPrjSafeList.size() - 1);
        assertThat(testBidPrjSafe.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSafe.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSafe.getSdate()).isEqualTo(UPDATED_SDATE);
        assertThat(testBidPrjSafe.getStype()).isEqualTo(DEFAULT_STYPE);
        assertThat(testBidPrjSafe.getMedia()).isEqualTo(DEFAULT_MEDIA);
        assertThat(testBidPrjSafe.getDiedNum()).isEqualByComparingTo(UPDATED_DIED_NUM);
        assertThat(testBidPrjSafe.getHurtNum()).isEqualByComparingTo(DEFAULT_HURT_NUM);
        assertThat(testBidPrjSafe.getDisasterScript()).isEqualTo(UPDATED_DISASTER_SCRIPT);
        assertThat(testBidPrjSafe.getPeDate()).isEqualTo(UPDATED_PE_DATE);
        assertThat(testBidPrjSafe.getPrmNo()).isEqualTo(DEFAULT_PRM_NO);
        assertThat(testBidPrjSafe.getMeDate()).isEqualTo(UPDATED_ME_DATE);
        assertThat(testBidPrjSafe.getMrmNo()).isEqualTo(DEFAULT_MRM_NO);
        assertThat(testBidPrjSafe.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjSafe.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjSafe.getCtUnit()).isEqualTo(UPDATED_CT_UNIT);
        assertThat(testBidPrjSafe.getCtrNo()).isEqualTo(UPDATED_CTR_NO);
        assertThat(testBidPrjSafe.getCcnt()).isEqualTo(DEFAULT_CCNT);
        assertThat(testBidPrjSafe.getCcut()).isEqualTo(DEFAULT_CCUT);
        assertThat(testBidPrjSafe.getRespons()).isEqualTo(UPDATED_RESPONS);
        assertThat(testBidPrjSafe.getWdayed()).isEqualByComparingTo(DEFAULT_WDAYED);
        assertThat(testBidPrjSafe.getRespons2()).isEqualTo(UPDATED_RESPONS_2);
        assertThat(testBidPrjSafe.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSafe.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjSafe.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjSafe.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSafe.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjSafe.getPedatm()).isEqualTo(DEFAULT_PEDATM);
        assertThat(testBidPrjSafe.getMedatm()).isEqualTo(UPDATED_MEDATM);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjSafeWithPatch() throws Exception {
        // Initialize the database
        bidPrjSafeRepository.saveAndFlush(bidPrjSafe);

        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();

        // Update the bidPrjSafe using partial update
        BidPrjSafe partialUpdatedBidPrjSafe = new BidPrjSafe();
        partialUpdatedBidPrjSafe.setId(bidPrjSafe.getId());

        partialUpdatedBidPrjSafe
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .sdate(UPDATED_SDATE)
            .stype(UPDATED_STYPE)
            .media(UPDATED_MEDIA)
            .diedNum(UPDATED_DIED_NUM)
            .hurtNum(UPDATED_HURT_NUM)
            .disasterScript(UPDATED_DISASTER_SCRIPT)
            .peDate(UPDATED_PE_DATE)
            .prmNo(UPDATED_PRM_NO)
            .meDate(UPDATED_ME_DATE)
            .mrmNo(UPDATED_MRM_NO)
            .remark(UPDATED_REMARK)
            .confOut(UPDATED_CONF_OUT)
            .ctUnit(UPDATED_CT_UNIT)
            .ctrNo(UPDATED_CTR_NO)
            .ccnt(UPDATED_CCNT)
            .ccut(UPDATED_CCUT)
            .respons(UPDATED_RESPONS)
            .wdayed(UPDATED_WDAYED)
            .respons2(UPDATED_RESPONS_2)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .pedatm(UPDATED_PEDATM)
            .medatm(UPDATED_MEDATM);

        restBidPrjSafeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSafe.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSafe))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSafe in the database
        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSafe testBidPrjSafe = bidPrjSafeList.get(bidPrjSafeList.size() - 1);
        assertThat(testBidPrjSafe.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSafe.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSafe.getSdate()).isEqualTo(UPDATED_SDATE);
        assertThat(testBidPrjSafe.getStype()).isEqualTo(UPDATED_STYPE);
        assertThat(testBidPrjSafe.getMedia()).isEqualTo(UPDATED_MEDIA);
        assertThat(testBidPrjSafe.getDiedNum()).isEqualByComparingTo(UPDATED_DIED_NUM);
        assertThat(testBidPrjSafe.getHurtNum()).isEqualByComparingTo(UPDATED_HURT_NUM);
        assertThat(testBidPrjSafe.getDisasterScript()).isEqualTo(UPDATED_DISASTER_SCRIPT);
        assertThat(testBidPrjSafe.getPeDate()).isEqualTo(UPDATED_PE_DATE);
        assertThat(testBidPrjSafe.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
        assertThat(testBidPrjSafe.getMeDate()).isEqualTo(UPDATED_ME_DATE);
        assertThat(testBidPrjSafe.getMrmNo()).isEqualTo(UPDATED_MRM_NO);
        assertThat(testBidPrjSafe.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjSafe.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjSafe.getCtUnit()).isEqualTo(UPDATED_CT_UNIT);
        assertThat(testBidPrjSafe.getCtrNo()).isEqualTo(UPDATED_CTR_NO);
        assertThat(testBidPrjSafe.getCcnt()).isEqualTo(UPDATED_CCNT);
        assertThat(testBidPrjSafe.getCcut()).isEqualTo(UPDATED_CCUT);
        assertThat(testBidPrjSafe.getRespons()).isEqualTo(UPDATED_RESPONS);
        assertThat(testBidPrjSafe.getWdayed()).isEqualByComparingTo(UPDATED_WDAYED);
        assertThat(testBidPrjSafe.getRespons2()).isEqualTo(UPDATED_RESPONS_2);
        assertThat(testBidPrjSafe.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSafe.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSafe.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSafe.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSafe.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjSafe.getPedatm()).isEqualTo(UPDATED_PEDATM);
        assertThat(testBidPrjSafe.getMedatm()).isEqualTo(UPDATED_MEDATM);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjSafe() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();
//        bidPrjSafe.setId(count.incrementAndGet());
//
//        // Create the BidPrjSafe
//        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjSafeMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjSafeDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSafe in the database
//        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
//        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjSafe() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();
//        bidPrjSafe.setId(count.incrementAndGet());
//
//        // Create the BidPrjSafe
//        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSafeMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSafe in the database
//        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
//        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjSafe() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSafeRepository.findAll().size();
//        bidPrjSafe.setId(count.incrementAndGet());
//
//        // Create the BidPrjSafe
//        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeMapper.toDto(bidPrjSafe);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSafeMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjSafeDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjSafe in the database
//        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
//        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjSafe() throws Exception {
        // Initialize the database
        bidPrjSafeRepository.saveAndFlush(bidPrjSafe);

        int databaseSizeBeforeDelete = bidPrjSafeRepository.findAll().size();

        // Delete the bidPrjSafe
        restBidPrjSafeMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjSafe.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjSafe> bidPrjSafeList = bidPrjSafeRepository.findAll();
        assertThat(bidPrjSafeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
