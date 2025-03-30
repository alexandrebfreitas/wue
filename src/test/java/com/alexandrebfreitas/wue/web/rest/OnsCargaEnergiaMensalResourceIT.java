package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaMensalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaMensalSearchRepository;
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
 * Integration tests for the {@link OnsCargaEnergiaMensalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCargaEnergiaMensalResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_CARGAENERGIAMWMED = 1D;
    private static final Double UPDATED_VAL_CARGAENERGIAMWMED = 2D;

    private static final String ENTITY_API_URL = "/api/ons-carga-energia-mensals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-carga-energia-mensals/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCargaEnergiaMensalRepository onsCargaEnergiaMensalRepository;

    @Autowired
    private OnsCargaEnergiaMensalSearchRepository onsCargaEnergiaMensalSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCargaEnergiaMensalMockMvc;

    private OnsCargaEnergiaMensalEntity onsCargaEnergiaMensalEntity;

    private OnsCargaEnergiaMensalEntity insertedOnsCargaEnergiaMensalEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCargaEnergiaMensalEntity createEntity() {
        return new OnsCargaEnergiaMensalEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valCargaenergiamwmed(DEFAULT_VAL_CARGAENERGIAMWMED);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCargaEnergiaMensalEntity createUpdatedEntity() {
        return new OnsCargaEnergiaMensalEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiamwmed(UPDATED_VAL_CARGAENERGIAMWMED);
    }

    @BeforeEach
    public void initTest() {
        onsCargaEnergiaMensalEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCargaEnergiaMensalEntity != null) {
            onsCargaEnergiaMensalRepository.delete(insertedOnsCargaEnergiaMensalEntity);
            onsCargaEnergiaMensalSearchRepository.delete(insertedOnsCargaEnergiaMensalEntity);
            insertedOnsCargaEnergiaMensalEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaMensal() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        // Create the OnsCargaEnergiaMensal
        var returnedOnsCargaEnergiaMensalEntity = om.readValue(
            restOnsCargaEnergiaMensalMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCargaEnergiaMensalEntity.class
        );

        // Validate the OnsCargaEnergiaMensal in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCargaEnergiaMensalEntityUpdatableFieldsEquals(
            returnedOnsCargaEnergiaMensalEntity,
            getPersistedOnsCargaEnergiaMensalEntity(returnedOnsCargaEnergiaMensalEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCargaEnergiaMensalEntity = returnedOnsCargaEnergiaMensalEntity;
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaMensalWithExistingId() throws Exception {
        // Create the OnsCargaEnergiaMensal with an existing ID
        onsCargaEnergiaMensalEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCargaEnergiaMensalMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCargaEnergiaMensals() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.saveAndFlush(onsCargaEnergiaMensalEntity);

        // Get all the onsCargaEnergiaMensalList
        restOnsCargaEnergiaMensalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaMensalEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCargaenergiamwmed").value(hasItem(DEFAULT_VAL_CARGAENERGIAMWMED)));
    }

    @Test
    @Transactional
    void getOnsCargaEnergiaMensal() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.saveAndFlush(onsCargaEnergiaMensalEntity);

        // Get the onsCargaEnergiaMensal
        restOnsCargaEnergiaMensalMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCargaEnergiaMensalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCargaEnergiaMensalEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valCargaenergiamwmed").value(DEFAULT_VAL_CARGAENERGIAMWMED));
    }

    @Test
    @Transactional
    void getNonExistingOnsCargaEnergiaMensal() throws Exception {
        // Get the onsCargaEnergiaMensal
        restOnsCargaEnergiaMensalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCargaEnergiaMensal() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.saveAndFlush(onsCargaEnergiaMensalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCargaEnergiaMensalSearchRepository.save(onsCargaEnergiaMensalEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());

        // Update the onsCargaEnergiaMensal
        OnsCargaEnergiaMensalEntity updatedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository
            .findById(onsCargaEnergiaMensalEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCargaEnergiaMensalEntity are not directly saved in db
        em.detach(updatedOnsCargaEnergiaMensalEntity);
        updatedOnsCargaEnergiaMensalEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiamwmed(UPDATED_VAL_CARGAENERGIAMWMED);

        restOnsCargaEnergiaMensalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCargaEnergiaMensalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCargaEnergiaMensalEntityToMatchAllProperties(updatedOnsCargaEnergiaMensalEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCargaEnergiaMensalEntity> onsCargaEnergiaMensalSearchList = Streamable.of(
                    onsCargaEnergiaMensalSearchRepository.findAll()
                ).toList();
                OnsCargaEnergiaMensalEntity testOnsCargaEnergiaMensalSearch = onsCargaEnergiaMensalSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsCargaEnergiaMensalEntityAllPropertiesEquals(testOnsCargaEnergiaMensalSearch, updatedOnsCargaEnergiaMensalEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCargaEnergiaMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        onsCargaEnergiaMensalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaMensalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCargaEnergiaMensalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCargaEnergiaMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        onsCargaEnergiaMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaMensalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCargaEnergiaMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        onsCargaEnergiaMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaMensalMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCargaEnergiaMensalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.saveAndFlush(onsCargaEnergiaMensalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaMensal using partial update
        OnsCargaEnergiaMensalEntity partialUpdatedOnsCargaEnergiaMensalEntity = new OnsCargaEnergiaMensalEntity();
        partialUpdatedOnsCargaEnergiaMensalEntity.setId(onsCargaEnergiaMensalEntity.getId());

        partialUpdatedOnsCargaEnergiaMensalEntity
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiamwmed(UPDATED_VAL_CARGAENERGIAMWMED);

        restOnsCargaEnergiaMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaMensalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaMensal in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaMensalEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCargaEnergiaMensalEntity, onsCargaEnergiaMensalEntity),
            getPersistedOnsCargaEnergiaMensalEntity(onsCargaEnergiaMensalEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCargaEnergiaMensalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.saveAndFlush(onsCargaEnergiaMensalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaMensal using partial update
        OnsCargaEnergiaMensalEntity partialUpdatedOnsCargaEnergiaMensalEntity = new OnsCargaEnergiaMensalEntity();
        partialUpdatedOnsCargaEnergiaMensalEntity.setId(onsCargaEnergiaMensalEntity.getId());

        partialUpdatedOnsCargaEnergiaMensalEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiamwmed(UPDATED_VAL_CARGAENERGIAMWMED);

        restOnsCargaEnergiaMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaMensalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaMensal in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaMensalEntityUpdatableFieldsEquals(
            partialUpdatedOnsCargaEnergiaMensalEntity,
            getPersistedOnsCargaEnergiaMensalEntity(partialUpdatedOnsCargaEnergiaMensalEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCargaEnergiaMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        onsCargaEnergiaMensalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCargaEnergiaMensalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCargaEnergiaMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        onsCargaEnergiaMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCargaEnergiaMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        onsCargaEnergiaMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaMensalMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaMensalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCargaEnergiaMensal() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.saveAndFlush(onsCargaEnergiaMensalEntity);
        onsCargaEnergiaMensalRepository.save(onsCargaEnergiaMensalEntity);
        onsCargaEnergiaMensalSearchRepository.save(onsCargaEnergiaMensalEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCargaEnergiaMensal
        restOnsCargaEnergiaMensalMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCargaEnergiaMensalEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaMensalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCargaEnergiaMensal() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.saveAndFlush(onsCargaEnergiaMensalEntity);
        onsCargaEnergiaMensalSearchRepository.save(onsCargaEnergiaMensalEntity);

        // Search the onsCargaEnergiaMensal
        restOnsCargaEnergiaMensalMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCargaEnergiaMensalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaMensalEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCargaenergiamwmed").value(hasItem(DEFAULT_VAL_CARGAENERGIAMWMED)));
    }

    protected long getRepositoryCount() {
        return onsCargaEnergiaMensalRepository.count();
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

    protected OnsCargaEnergiaMensalEntity getPersistedOnsCargaEnergiaMensalEntity(OnsCargaEnergiaMensalEntity onsCargaEnergiaMensal) {
        return onsCargaEnergiaMensalRepository.findById(onsCargaEnergiaMensal.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCargaEnergiaMensalEntityToMatchAllProperties(
        OnsCargaEnergiaMensalEntity expectedOnsCargaEnergiaMensalEntity
    ) {
        assertOnsCargaEnergiaMensalEntityAllPropertiesEquals(
            expectedOnsCargaEnergiaMensalEntity,
            getPersistedOnsCargaEnergiaMensalEntity(expectedOnsCargaEnergiaMensalEntity)
        );
    }

    protected void assertPersistedOnsCargaEnergiaMensalEntityToMatchUpdatableProperties(
        OnsCargaEnergiaMensalEntity expectedOnsCargaEnergiaMensalEntity
    ) {
        assertOnsCargaEnergiaMensalEntityAllUpdatablePropertiesEquals(
            expectedOnsCargaEnergiaMensalEntity,
            getPersistedOnsCargaEnergiaMensalEntity(expectedOnsCargaEnergiaMensalEntity)
        );
    }
}
