package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.TecContent;
import gov.pcc.pwc.repository.TecContentRepository;
import gov.pcc.pwc.service.dto.TecContentDTO;
import gov.pcc.pwc.service.mapper.TecContentMapper;
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
 * Integration tests for the {@link TecContentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TecContentResourceIT {

    private static final Long DEFAULT_PAGE_ID = 1L;
    private static final Long UPDATED_PAGE_ID = 2L;

    private static final String DEFAULT_CONTENT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/tec-contents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{contentId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TecContentRepository tecContentRepository;

    @Autowired
    private TecContentMapper tecContentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTecContentMockMvc;

    private TecContent tecContent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecContent createEntity(EntityManager em) {
        TecContent tecContent = new TecContent()
            .pageId(DEFAULT_PAGE_ID)
            .contentTitle(DEFAULT_CONTENT_TITLE)
            .content(DEFAULT_CONTENT)
            .isdelete(DEFAULT_ISDELETE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return tecContent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecContent createUpdatedEntity(EntityManager em) {
        TecContent tecContent = new TecContent()
            .pageId(UPDATED_PAGE_ID)
            .contentTitle(UPDATED_CONTENT_TITLE)
            .content(UPDATED_CONTENT)
            .isdelete(UPDATED_ISDELETE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return tecContent;
    }

    @BeforeEach
    public void initTest() {
        tecContent = createEntity(em);
    }

    @Test
    @Transactional
    void createTecContent() throws Exception {
        int databaseSizeBeforeCreate = tecContentRepository.findAll().size();
        // Create the TecContent
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);
        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isCreated());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeCreate + 1);
        TecContent testTecContent = tecContentList.get(tecContentList.size() - 1);
        assertThat(testTecContent.getPageId()).isEqualTo(DEFAULT_PAGE_ID);
        assertThat(testTecContent.getContentTitle()).isEqualTo(DEFAULT_CONTENT_TITLE);
        assertThat(testTecContent.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testTecContent.getIsdelete()).isEqualTo(DEFAULT_ISDELETE);
        assertThat(testTecContent.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testTecContent.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testTecContent.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testTecContent.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createTecContentWithExistingId() throws Exception {
        // Create the TecContent with an existing ID
        tecContent.setContentId(1L);
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        int databaseSizeBeforeCreate = tecContentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecContentRepository.findAll().size();
        // set the field null
        tecContent.setPageId(null);

        // Create the TecContent, which fails.
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecContentRepository.findAll().size();
        // set the field null
        tecContent.setContent(null);

        // Create the TecContent, which fails.
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsdeleteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecContentRepository.findAll().size();
        // set the field null
        tecContent.setIsdelete(null);

        // Create the TecContent, which fails.
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecContentRepository.findAll().size();
        // set the field null
        tecContent.setUpdateUser(null);

        // Create the TecContent, which fails.
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecContentRepository.findAll().size();
        // set the field null
        tecContent.setUpdateTime(null);

        // Create the TecContent, which fails.
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecContentRepository.findAll().size();
        // set the field null
        tecContent.setCreateUser(null);

        // Create the TecContent, which fails.
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecContentRepository.findAll().size();
        // set the field null
        tecContent.setCreateTime(null);

        // Create the TecContent, which fails.
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        restTecContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isBadRequest());

        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTecContents() throws Exception {
        // Initialize the database
        tecContentRepository.saveAndFlush(tecContent);

        // Get all the tecContentList
        restTecContentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=contentId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(tecContent.getContentId().intValue())))
            .andExpect(jsonPath("$.[*].pageId").value(hasItem(DEFAULT_PAGE_ID.intValue())))
            .andExpect(jsonPath("$.[*].contentTitle").value(hasItem(DEFAULT_CONTENT_TITLE)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].isdelete").value(hasItem(DEFAULT_ISDELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getTecContent() throws Exception {
        // Initialize the database
        tecContentRepository.saveAndFlush(tecContent);

        // Get the tecContent
        restTecContentMockMvc
            .perform(get(ENTITY_API_URL_ID, tecContent.getContentId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.contentId").value(tecContent.getContentId().intValue()))
            .andExpect(jsonPath("$.pageId").value(DEFAULT_PAGE_ID.intValue()))
            .andExpect(jsonPath("$.contentTitle").value(DEFAULT_CONTENT_TITLE))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.isdelete").value(DEFAULT_ISDELETE.booleanValue()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTecContent() throws Exception {
        // Get the tecContent
        restTecContentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTecContent() throws Exception {
        // Initialize the database
        tecContentRepository.saveAndFlush(tecContent);

        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();

        // Update the tecContent
        TecContent updatedTecContent = tecContentRepository.findById(tecContent.getContentId()).get();
        // Disconnect from session so that the updates on updatedTecContent are not directly saved in db
        em.detach(updatedTecContent);
        updatedTecContent
            .pageId(UPDATED_PAGE_ID)
            .contentTitle(UPDATED_CONTENT_TITLE)
            .content(UPDATED_CONTENT)
            .isdelete(UPDATED_ISDELETE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        TecContentDTO tecContentDTO = tecContentMapper.toDto(updatedTecContent);

        restTecContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tecContentDTO.getContentId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tecContentDTO))
            )
            .andExpect(status().isOk());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
        TecContent testTecContent = tecContentList.get(tecContentList.size() - 1);
        assertThat(testTecContent.getPageId()).isEqualTo(UPDATED_PAGE_ID);
        assertThat(testTecContent.getContentTitle()).isEqualTo(UPDATED_CONTENT_TITLE);
        assertThat(testTecContent.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testTecContent.getIsdelete()).isEqualTo(UPDATED_ISDELETE);
        assertThat(testTecContent.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testTecContent.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testTecContent.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testTecContent.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingTecContent() throws Exception {
        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();
        tecContent.setContentId(count.incrementAndGet());

        // Create the TecContent
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTecContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tecContentDTO.getContentId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tecContentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTecContent() throws Exception {
        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();
        tecContent.setContentId(count.incrementAndGet());

        // Create the TecContent
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tecContentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTecContent() throws Exception {
        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();
        tecContent.setContentId(count.incrementAndGet());

        // Create the TecContent
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecContentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tecContentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTecContentWithPatch() throws Exception {
        // Initialize the database
        tecContentRepository.saveAndFlush(tecContent);

        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();

        // Update the tecContent using partial update
        TecContent partialUpdatedTecContent = new TecContent();
        partialUpdatedTecContent.setContentId(tecContent.getContentId());

        partialUpdatedTecContent
            .pageId(UPDATED_PAGE_ID)
            .content(UPDATED_CONTENT)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restTecContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTecContent.getContentId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTecContent))
            )
            .andExpect(status().isOk());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
        TecContent testTecContent = tecContentList.get(tecContentList.size() - 1);
        assertThat(testTecContent.getPageId()).isEqualTo(UPDATED_PAGE_ID);
        assertThat(testTecContent.getContentTitle()).isEqualTo(DEFAULT_CONTENT_TITLE);
        assertThat(testTecContent.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testTecContent.getIsdelete()).isEqualTo(DEFAULT_ISDELETE);
        assertThat(testTecContent.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testTecContent.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testTecContent.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testTecContent.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateTecContentWithPatch() throws Exception {
        // Initialize the database
        tecContentRepository.saveAndFlush(tecContent);

        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();

        // Update the tecContent using partial update
        TecContent partialUpdatedTecContent = new TecContent();
        partialUpdatedTecContent.setContentId(tecContent.getContentId());

        partialUpdatedTecContent
            .pageId(UPDATED_PAGE_ID)
            .contentTitle(UPDATED_CONTENT_TITLE)
            .content(UPDATED_CONTENT)
            .isdelete(UPDATED_ISDELETE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restTecContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTecContent.getContentId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTecContent))
            )
            .andExpect(status().isOk());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
        TecContent testTecContent = tecContentList.get(tecContentList.size() - 1);
        assertThat(testTecContent.getPageId()).isEqualTo(UPDATED_PAGE_ID);
        assertThat(testTecContent.getContentTitle()).isEqualTo(UPDATED_CONTENT_TITLE);
        assertThat(testTecContent.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testTecContent.getIsdelete()).isEqualTo(UPDATED_ISDELETE);
        assertThat(testTecContent.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testTecContent.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testTecContent.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testTecContent.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingTecContent() throws Exception {
        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();
        tecContent.setContentId(count.incrementAndGet());

        // Create the TecContent
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTecContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tecContentDTO.getContentId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tecContentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTecContent() throws Exception {
        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();
        tecContent.setContentId(count.incrementAndGet());

        // Create the TecContent
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tecContentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTecContent() throws Exception {
        int databaseSizeBeforeUpdate = tecContentRepository.findAll().size();
        tecContent.setContentId(count.incrementAndGet());

        // Create the TecContent
        TecContentDTO tecContentDTO = tecContentMapper.toDto(tecContent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTecContentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tecContentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TecContent in the database
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTecContent() throws Exception {
        // Initialize the database
        tecContentRepository.saveAndFlush(tecContent);

        int databaseSizeBeforeDelete = tecContentRepository.findAll().size();

        // Delete the tecContent
        restTecContentMockMvc
            .perform(delete(ENTITY_API_URL_ID, tecContent.getContentId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TecContent> tecContentList = tecContentRepository.findAll();
        assertThat(tecContentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
