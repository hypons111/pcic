package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.TecPage;
import gov.pcc.pwc.repository.TecPageRepository;
import gov.pcc.pwc.service.dto.TecPageDTO;
import gov.pcc.pwc.service.mapper.TecPageMapper;
import java.time.Instant;
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
 * Integration tests for the {@link TecPageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TecPageResourceIT {

    private static final String DEFAULT_MENU = "AAAAAAAAAA";
    private static final String UPDATED_MENU = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_MENU = "AAAAAAAAAA";
    private static final String UPDATED_SUB_MENU = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PAGE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_PAGE_TITLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_PAGE_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAGE_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PAGE_END = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAGE_END = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISDELETE = false;
    private static final Boolean UPDATED_ISDELETE = true;

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/tec-pages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{pageId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TecPageRepository tecPageRepository;

    @Autowired
    private TecPageMapper tecPageMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTecPageMockMvc;

    private TecPage tecPage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecPage createEntity(EntityManager em) {
        TecPage tecPage = new TecPage()
            .menu(DEFAULT_MENU)
            .subMenu(DEFAULT_SUB_MENU)
            .contentType(DEFAULT_CONTENT_TYPE)
            .pageTitle(DEFAULT_PAGE_TITLE)
            .pageStart(DEFAULT_PAGE_START)
            .pageEnd(DEFAULT_PAGE_END)
            .isdelete(DEFAULT_ISDELETE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return tecPage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecPage createUpdatedEntity(EntityManager em) {
        TecPage tecPage = new TecPage()
            .menu(UPDATED_MENU)
            .subMenu(UPDATED_SUB_MENU)
            .contentType(UPDATED_CONTENT_TYPE)
            .pageTitle(UPDATED_PAGE_TITLE)
            .pageStart(UPDATED_PAGE_START)
            .pageEnd(UPDATED_PAGE_END)
            .isdelete(UPDATED_ISDELETE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return tecPage;
    }

    @BeforeEach
    public void initTest() {
        tecPage = createEntity(em);
    }

    @Test
    @Transactional
    void createTecPage() throws Exception {
        int databaseSizeBeforeCreate = tecPageRepository.findAll().size();
        // Create the TecPage
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);
        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isCreated());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeCreate + 1);
        TecPage testTecPage = tecPageList.get(tecPageList.size() - 1);
        assertThat(testTecPage.getMenu()).isEqualTo(DEFAULT_MENU);
        assertThat(testTecPage.getSubMenu()).isEqualTo(DEFAULT_SUB_MENU);
        assertThat(testTecPage.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testTecPage.getPageTitle()).isEqualTo(DEFAULT_PAGE_TITLE);
        assertThat(testTecPage.getPageStart()).isEqualTo(DEFAULT_PAGE_START);
        assertThat(testTecPage.getPageEnd()).isEqualTo(DEFAULT_PAGE_END);
        assertThat(testTecPage.getIsdelete()).isEqualTo(DEFAULT_ISDELETE);
        assertThat(testTecPage.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testTecPage.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testTecPage.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testTecPage.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createTecPageWithExistingId() throws Exception {
        // Create the TecPage with an existing ID
        tecPage.setPageId(1L);
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        int databaseSizeBeforeCreate = tecPageRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMenuIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setMenu(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPageStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setPageStart(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPageEndIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setPageEnd(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsdeleteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setIsdelete(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setUpdateUser(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setUpdateTime(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setCreateUser(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecPageRepository.findAll().size();
        // set the field null
        tecPage.setCreateTime(null);

        // Create the TecPage, which fails.
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        restTecPageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isBadRequest());

        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTecPages() throws Exception {
        // Initialize the database
        tecPageRepository.saveAndFlush(tecPage);

        // Get all the tecPageList
        restTecPageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=pageId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].pageId").value(hasItem(tecPage.getPageId().intValue())))
            .andExpect(jsonPath("$.[*].menu").value(hasItem(DEFAULT_MENU)))
            .andExpect(jsonPath("$.[*].subMenu").value(hasItem(DEFAULT_SUB_MENU)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pageTitle").value(hasItem(DEFAULT_PAGE_TITLE)))
            .andExpect(jsonPath("$.[*].pageStart").value(hasItem(DEFAULT_PAGE_START.toString())))
            .andExpect(jsonPath("$.[*].pageEnd").value(hasItem(DEFAULT_PAGE_END.toString())))
            .andExpect(jsonPath("$.[*].isdelete").value(hasItem(DEFAULT_ISDELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getTecPage() throws Exception {
        // Initialize the database
        tecPageRepository.saveAndFlush(tecPage);

        // Get the tecPage
        restTecPageMockMvc
            .perform(get(ENTITY_API_URL_ID, tecPage.getPageId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.pageId").value(tecPage.getPageId().intValue()))
            .andExpect(jsonPath("$.menu").value(DEFAULT_MENU))
            .andExpect(jsonPath("$.subMenu").value(DEFAULT_SUB_MENU))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.pageTitle").value(DEFAULT_PAGE_TITLE))
            .andExpect(jsonPath("$.pageStart").value(DEFAULT_PAGE_START.toString()))
            .andExpect(jsonPath("$.pageEnd").value(DEFAULT_PAGE_END.toString()))
            .andExpect(jsonPath("$.isdelete").value(DEFAULT_ISDELETE.booleanValue()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTecPage() throws Exception {
        // Get the tecPage
        restTecPageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTecPage() throws Exception {
        // Initialize the database
        tecPageRepository.saveAndFlush(tecPage);

        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();

        // Update the tecPage
        TecPage updatedTecPage = tecPageRepository.findById(tecPage.getPageId()).get();
        // Disconnect from session so that the updates on updatedTecPage are not directly saved in db
        em.detach(updatedTecPage);
        updatedTecPage
            .menu(UPDATED_MENU)
            .subMenu(UPDATED_SUB_MENU)
            .contentType(UPDATED_CONTENT_TYPE)
            .pageTitle(UPDATED_PAGE_TITLE)
            .pageStart(UPDATED_PAGE_START)
            .pageEnd(UPDATED_PAGE_END)
            .isdelete(UPDATED_ISDELETE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        TecPageDTO tecPageDTO = tecPageMapper.toDto(updatedTecPage);

        restTecPageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tecPageDTO.getPageId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tecPageDTO))
            )
            .andExpect(status().isOk());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
        TecPage testTecPage = tecPageList.get(tecPageList.size() - 1);
        assertThat(testTecPage.getMenu()).isEqualTo(UPDATED_MENU);
        assertThat(testTecPage.getSubMenu()).isEqualTo(UPDATED_SUB_MENU);
        assertThat(testTecPage.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testTecPage.getPageTitle()).isEqualTo(UPDATED_PAGE_TITLE);
        assertThat(testTecPage.getPageStart()).isEqualTo(UPDATED_PAGE_START);
        assertThat(testTecPage.getPageEnd()).isEqualTo(UPDATED_PAGE_END);
        assertThat(testTecPage.getIsdelete()).isEqualTo(UPDATED_ISDELETE);
        assertThat(testTecPage.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testTecPage.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testTecPage.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testTecPage.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingTecPage() throws Exception {
        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();
        tecPage.setPageId(count.incrementAndGet());

        // Create the TecPage
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTecPageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tecPageDTO.getPageId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tecPageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTecPage() throws Exception {
        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();
        tecPage.setPageId(count.incrementAndGet());

        // Create the TecPage
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecPageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tecPageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTecPage() throws Exception {
        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();
        tecPage.setPageId(count.incrementAndGet());

        // Create the TecPage
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecPageMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecPageDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTecPageWithPatch() throws Exception {
        // Initialize the database
        tecPageRepository.saveAndFlush(tecPage);

        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();

        // Update the tecPage using partial update
        TecPage partialUpdatedTecPage = new TecPage();
        partialUpdatedTecPage.setPageId(tecPage.getPageId());

        partialUpdatedTecPage
            .menu(UPDATED_MENU)
            .pageStart(UPDATED_PAGE_START)
            .pageEnd(UPDATED_PAGE_END)
            .isdelete(UPDATED_ISDELETE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restTecPageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTecPage.getPageId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTecPage))
            )
            .andExpect(status().isOk());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
        TecPage testTecPage = tecPageList.get(tecPageList.size() - 1);
        assertThat(testTecPage.getMenu()).isEqualTo(UPDATED_MENU);
        assertThat(testTecPage.getSubMenu()).isEqualTo(DEFAULT_SUB_MENU);
        assertThat(testTecPage.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testTecPage.getPageTitle()).isEqualTo(DEFAULT_PAGE_TITLE);
        assertThat(testTecPage.getPageStart()).isEqualTo(UPDATED_PAGE_START);
        assertThat(testTecPage.getPageEnd()).isEqualTo(UPDATED_PAGE_END);
        assertThat(testTecPage.getIsdelete()).isEqualTo(UPDATED_ISDELETE);
        assertThat(testTecPage.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testTecPage.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testTecPage.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testTecPage.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateTecPageWithPatch() throws Exception {
        // Initialize the database
        tecPageRepository.saveAndFlush(tecPage);

        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();

        // Update the tecPage using partial update
        TecPage partialUpdatedTecPage = new TecPage();
        partialUpdatedTecPage.setPageId(tecPage.getPageId());

        partialUpdatedTecPage
            .menu(UPDATED_MENU)
            .subMenu(UPDATED_SUB_MENU)
            .contentType(UPDATED_CONTENT_TYPE)
            .pageTitle(UPDATED_PAGE_TITLE)
            .pageStart(UPDATED_PAGE_START)
            .pageEnd(UPDATED_PAGE_END)
            .isdelete(UPDATED_ISDELETE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restTecPageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTecPage.getPageId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTecPage))
            )
            .andExpect(status().isOk());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
        TecPage testTecPage = tecPageList.get(tecPageList.size() - 1);
        assertThat(testTecPage.getMenu()).isEqualTo(UPDATED_MENU);
        assertThat(testTecPage.getSubMenu()).isEqualTo(UPDATED_SUB_MENU);
        assertThat(testTecPage.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testTecPage.getPageTitle()).isEqualTo(UPDATED_PAGE_TITLE);
        assertThat(testTecPage.getPageStart()).isEqualTo(UPDATED_PAGE_START);
        assertThat(testTecPage.getPageEnd()).isEqualTo(UPDATED_PAGE_END);
        assertThat(testTecPage.getIsdelete()).isEqualTo(UPDATED_ISDELETE);
        assertThat(testTecPage.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testTecPage.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testTecPage.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testTecPage.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingTecPage() throws Exception {
        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();
        tecPage.setPageId(count.incrementAndGet());

        // Create the TecPage
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTecPageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tecPageDTO.getPageId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tecPageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTecPage() throws Exception {
        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();
        tecPage.setPageId(count.incrementAndGet());

        // Create the TecPage
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecPageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tecPageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTecPage() throws Exception {
        int databaseSizeBeforeUpdate = tecPageRepository.findAll().size();
        tecPage.setPageId(count.incrementAndGet());

        // Create the TecPage
        TecPageDTO tecPageDTO = tecPageMapper.toDto(tecPage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecPageMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tecPageDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TecPage in the database
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTecPage() throws Exception {
        // Initialize the database
        tecPageRepository.saveAndFlush(tecPage);

        int databaseSizeBeforeDelete = tecPageRepository.findAll().size();

        // Delete the tecPage
        restTecPageMockMvc
            .perform(delete(ENTITY_API_URL_ID, tecPage.getPageId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TecPage> tecPageList = tecPageRepository.findAll();
        assertThat(tecPageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
