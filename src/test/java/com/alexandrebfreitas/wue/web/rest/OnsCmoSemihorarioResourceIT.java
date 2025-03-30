package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntity;
import com.alexandrebfreitas.wue.repository.OnsCmoSemihorarioRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCmoSemihorarioSearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.util.Streamable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link OnsCmoSemihorarioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCmoSemihorarioResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_CMO = 1D;
    private static final Double UPDATED_VAL_CMO = 2D;

    private static final String ENTITY_API_URL = "/api/ons-cmo-semihorarios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-cmo-semihorarios/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCmoSemihorarioRepository onsCmoSemihorarioRepository;

    @Autowired
    private OnsCmoSemihorarioSearchRepository onsCmoSemihorarioSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCmoSemihorarioMockMvc;

    private OnsCmoSemihorarioEntity onsCmoSemihorarioEntity;

    private OnsCmoSemihorarioEntity insertedOnsCmoSemihorarioEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCmoSemihorarioEntity createEntity() {
        return new OnsCmoSemihorarioEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valCmo(DEFAULT_VAL_CMO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCmoSemihorarioEntity createUpdatedEntity() {
        return new OnsCmoSemihorarioEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCmo(UPDATED_VAL_CMO);
    }

    @BeforeEach
    public void initTest() {
        onsCmoSemihorarioEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCmoSemihorarioEntity != null) {
            onsCmoSemihorarioRepository.delete(insertedOnsCmoSemihorarioEntity);
            onsCmoSemihorarioSearchRepository.delete(insertedOnsCmoSemihorarioEntity);
            insertedOnsCmoSemihorarioEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCmoSemihorario() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        // Create the OnsCmoSemihorario
        var returnedOnsCmoSemihorarioEntity = om.readValue(
            restOnsCmoSemihorarioMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCmoSemihorarioEntity.class
        );

        // Validate the OnsCmoSemihorario in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCmoSemihorarioEntityUpdatableFieldsEquals(
            returnedOnsCmoSemihorarioEntity,
            getPersistedOnsCmoSemihorarioEntity(returnedOnsCmoSemihorarioEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCmoSemihorarioEntity = returnedOnsCmoSemihorarioEntity;
    }

    @Test
    @Transactional
    void createOnsCmoSemihorarioWithExistingId() throws Exception {
        // Create the OnsCmoSemihorario with an existing ID
        onsCmoSemihorarioEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCmoSemihorarioMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCmoSemihorarios() throws Exception {
        // Initialize the database
        insertedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository.saveAndFlush(onsCmoSemihorarioEntity);

        // Get all the onsCmoSemihorarioList
        restOnsCmoSemihorarioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCmoSemihorarioEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCmo").value(hasItem(DEFAULT_VAL_CMO)));
    }

    @Test
    @Transactional
    void getOnsCmoSemihorario() throws Exception {
        // Initialize the database
        insertedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository.saveAndFlush(onsCmoSemihorarioEntity);

        // Get the onsCmoSemihorario
        restOnsCmoSemihorarioMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCmoSemihorarioEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCmoSemihorarioEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valCmo").value(DEFAULT_VAL_CMO));
    }

    @Test
    @Transactional
    void getNonExistingOnsCmoSemihorario() throws Exception {
        // Get the onsCmoSemihorario
        restOnsCmoSemihorarioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCmoSemihorario() throws Exception {
        // Initialize the database
        insertedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository.saveAndFlush(onsCmoSemihorarioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCmoSemihorarioSearchRepository.save(onsCmoSemihorarioEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());

        // Update the onsCmoSemihorario
        OnsCmoSemihorarioEntity updatedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository
            .findById(onsCmoSemihorarioEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCmoSemihorarioEntity are not directly saved in db
        em.detach(updatedOnsCmoSemihorarioEntity);
        updatedOnsCmoSemihorarioEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCmo(UPDATED_VAL_CMO);

        restOnsCmoSemihorarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCmoSemihorarioEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCmoSemihorarioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCmoSemihorarioEntityToMatchAllProperties(updatedOnsCmoSemihorarioEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCmoSemihorarioEntity> onsCmoSemihorarioSearchList = Streamable.of(
                    onsCmoSemihorarioSearchRepository.findAll()
                ).toList();
                OnsCmoSemihorarioEntity testOnsCmoSemihorarioSearch = onsCmoSemihorarioSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsCmoSemihorarioEntityAllPropertiesEquals(testOnsCmoSemihorarioSearch, updatedOnsCmoSemihorarioEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCmoSemihorario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        onsCmoSemihorarioEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCmoSemihorarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCmoSemihorarioEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCmoSemihorario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        onsCmoSemihorarioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemihorarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCmoSemihorario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        onsCmoSemihorarioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemihorarioMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCmoSemihorarioWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository.saveAndFlush(onsCmoSemihorarioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCmoSemihorario using partial update
        OnsCmoSemihorarioEntity partialUpdatedOnsCmoSemihorarioEntity = new OnsCmoSemihorarioEntity();
        partialUpdatedOnsCmoSemihorarioEntity.setId(onsCmoSemihorarioEntity.getId());

        partialUpdatedOnsCmoSemihorarioEntity.nomSubsistema(UPDATED_NOM_SUBSISTEMA);

        restOnsCmoSemihorarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCmoSemihorarioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCmoSemihorarioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCmoSemihorario in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCmoSemihorarioEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCmoSemihorarioEntity, onsCmoSemihorarioEntity),
            getPersistedOnsCmoSemihorarioEntity(onsCmoSemihorarioEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCmoSemihorarioWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository.saveAndFlush(onsCmoSemihorarioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCmoSemihorario using partial update
        OnsCmoSemihorarioEntity partialUpdatedOnsCmoSemihorarioEntity = new OnsCmoSemihorarioEntity();
        partialUpdatedOnsCmoSemihorarioEntity.setId(onsCmoSemihorarioEntity.getId());

        partialUpdatedOnsCmoSemihorarioEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCmo(UPDATED_VAL_CMO);

        restOnsCmoSemihorarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCmoSemihorarioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCmoSemihorarioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCmoSemihorario in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCmoSemihorarioEntityUpdatableFieldsEquals(
            partialUpdatedOnsCmoSemihorarioEntity,
            getPersistedOnsCmoSemihorarioEntity(partialUpdatedOnsCmoSemihorarioEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCmoSemihorario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        onsCmoSemihorarioEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCmoSemihorarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCmoSemihorarioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCmoSemihorario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        onsCmoSemihorarioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemihorarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCmoSemihorario() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        onsCmoSemihorarioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemihorarioMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCmoSemihorarioEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCmoSemihorario in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCmoSemihorario() throws Exception {
        // Initialize the database
        insertedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository.saveAndFlush(onsCmoSemihorarioEntity);
        onsCmoSemihorarioRepository.save(onsCmoSemihorarioEntity);
        onsCmoSemihorarioSearchRepository.save(onsCmoSemihorarioEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCmoSemihorario
        restOnsCmoSemihorarioMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCmoSemihorarioEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemihorarioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCmoSemihorario() throws Exception {
        // Initialize the database
        insertedOnsCmoSemihorarioEntity = onsCmoSemihorarioRepository.saveAndFlush(onsCmoSemihorarioEntity);
        onsCmoSemihorarioSearchRepository.save(onsCmoSemihorarioEntity);

        // Search the onsCmoSemihorario
        restOnsCmoSemihorarioMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCmoSemihorarioEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCmoSemihorarioEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCmo").value(hasItem(DEFAULT_VAL_CMO)));
    }

    protected long getRepositoryCount() {
        return onsCmoSemihorarioRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected OnsCmoSemihorarioEntity getPersistedOnsCmoSemihorarioEntity(OnsCmoSemihorarioEntity onsCmoSemihorario) {
        return onsCmoSemihorarioRepository.findById(onsCmoSemihorario.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCmoSemihorarioEntityToMatchAllProperties(OnsCmoSemihorarioEntity expectedOnsCmoSemihorarioEntity) {
        assertOnsCmoSemihorarioEntityAllPropertiesEquals(
            expectedOnsCmoSemihorarioEntity,
            getPersistedOnsCmoSemihorarioEntity(expectedOnsCmoSemihorarioEntity)
        );
    }

    protected void assertPersistedOnsCmoSemihorarioEntityToMatchUpdatableProperties(
        OnsCmoSemihorarioEntity expectedOnsCmoSemihorarioEntity
    ) {
        assertOnsCmoSemihorarioEntityAllUpdatablePropertiesEquals(
            expectedOnsCmoSemihorarioEntity,
            getPersistedOnsCmoSemihorarioEntity(expectedOnsCmoSemihorarioEntity)
        );
    }
}
