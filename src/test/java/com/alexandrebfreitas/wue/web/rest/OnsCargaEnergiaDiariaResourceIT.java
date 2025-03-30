package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaDiariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaDiariaSearchRepository;
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
 * Integration tests for the {@link OnsCargaEnergiaDiariaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCargaEnergiaDiariaResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_CARGAENERGIAMWMED = 1D;
    private static final Double UPDATED_VAL_CARGAENERGIAMWMED = 2D;

    private static final String ENTITY_API_URL = "/api/ons-carga-energia-diarias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-carga-energia-diarias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCargaEnergiaDiariaRepository onsCargaEnergiaDiariaRepository;

    @Autowired
    private OnsCargaEnergiaDiariaSearchRepository onsCargaEnergiaDiariaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCargaEnergiaDiariaMockMvc;

    private OnsCargaEnergiaDiariaEntity onsCargaEnergiaDiariaEntity;

    private OnsCargaEnergiaDiariaEntity insertedOnsCargaEnergiaDiariaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCargaEnergiaDiariaEntity createEntity() {
        return new OnsCargaEnergiaDiariaEntity()
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
    public static OnsCargaEnergiaDiariaEntity createUpdatedEntity() {
        return new OnsCargaEnergiaDiariaEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiamwmed(UPDATED_VAL_CARGAENERGIAMWMED);
    }

    @BeforeEach
    public void initTest() {
        onsCargaEnergiaDiariaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCargaEnergiaDiariaEntity != null) {
            onsCargaEnergiaDiariaRepository.delete(insertedOnsCargaEnergiaDiariaEntity);
            onsCargaEnergiaDiariaSearchRepository.delete(insertedOnsCargaEnergiaDiariaEntity);
            insertedOnsCargaEnergiaDiariaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaDiaria() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        // Create the OnsCargaEnergiaDiaria
        var returnedOnsCargaEnergiaDiariaEntity = om.readValue(
            restOnsCargaEnergiaDiariaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCargaEnergiaDiariaEntity.class
        );

        // Validate the OnsCargaEnergiaDiaria in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCargaEnergiaDiariaEntityUpdatableFieldsEquals(
            returnedOnsCargaEnergiaDiariaEntity,
            getPersistedOnsCargaEnergiaDiariaEntity(returnedOnsCargaEnergiaDiariaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCargaEnergiaDiariaEntity = returnedOnsCargaEnergiaDiariaEntity;
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaDiariaWithExistingId() throws Exception {
        // Create the OnsCargaEnergiaDiaria with an existing ID
        onsCargaEnergiaDiariaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCargaEnergiaDiarias() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.saveAndFlush(onsCargaEnergiaDiariaEntity);

        // Get all the onsCargaEnergiaDiariaList
        restOnsCargaEnergiaDiariaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaDiariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCargaenergiamwmed").value(hasItem(DEFAULT_VAL_CARGAENERGIAMWMED)));
    }

    @Test
    @Transactional
    void getOnsCargaEnergiaDiaria() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.saveAndFlush(onsCargaEnergiaDiariaEntity);

        // Get the onsCargaEnergiaDiaria
        restOnsCargaEnergiaDiariaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCargaEnergiaDiariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCargaEnergiaDiariaEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valCargaenergiamwmed").value(DEFAULT_VAL_CARGAENERGIAMWMED));
    }

    @Test
    @Transactional
    void getNonExistingOnsCargaEnergiaDiaria() throws Exception {
        // Get the onsCargaEnergiaDiaria
        restOnsCargaEnergiaDiariaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCargaEnergiaDiaria() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.saveAndFlush(onsCargaEnergiaDiariaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCargaEnergiaDiariaSearchRepository.save(onsCargaEnergiaDiariaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());

        // Update the onsCargaEnergiaDiaria
        OnsCargaEnergiaDiariaEntity updatedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository
            .findById(onsCargaEnergiaDiariaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCargaEnergiaDiariaEntity are not directly saved in db
        em.detach(updatedOnsCargaEnergiaDiariaEntity);
        updatedOnsCargaEnergiaDiariaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiamwmed(UPDATED_VAL_CARGAENERGIAMWMED);

        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCargaEnergiaDiariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCargaEnergiaDiariaEntityToMatchAllProperties(updatedOnsCargaEnergiaDiariaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCargaEnergiaDiariaEntity> onsCargaEnergiaDiariaSearchList = Streamable.of(
                    onsCargaEnergiaDiariaSearchRepository.findAll()
                ).toList();
                OnsCargaEnergiaDiariaEntity testOnsCargaEnergiaDiariaSearch = onsCargaEnergiaDiariaSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsCargaEnergiaDiariaEntityAllPropertiesEquals(testOnsCargaEnergiaDiariaSearch, updatedOnsCargaEnergiaDiariaEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCargaEnergiaDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        onsCargaEnergiaDiariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCargaEnergiaDiariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCargaEnergiaDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        onsCargaEnergiaDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCargaEnergiaDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        onsCargaEnergiaDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCargaEnergiaDiariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.saveAndFlush(onsCargaEnergiaDiariaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaDiaria using partial update
        OnsCargaEnergiaDiariaEntity partialUpdatedOnsCargaEnergiaDiariaEntity = new OnsCargaEnergiaDiariaEntity();
        partialUpdatedOnsCargaEnergiaDiariaEntity.setId(onsCargaEnergiaDiariaEntity.getId());

        partialUpdatedOnsCargaEnergiaDiariaEntity.idSubsistema(UPDATED_ID_SUBSISTEMA);

        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaDiaria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaDiariaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCargaEnergiaDiariaEntity, onsCargaEnergiaDiariaEntity),
            getPersistedOnsCargaEnergiaDiariaEntity(onsCargaEnergiaDiariaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCargaEnergiaDiariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.saveAndFlush(onsCargaEnergiaDiariaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaDiaria using partial update
        OnsCargaEnergiaDiariaEntity partialUpdatedOnsCargaEnergiaDiariaEntity = new OnsCargaEnergiaDiariaEntity();
        partialUpdatedOnsCargaEnergiaDiariaEntity.setId(onsCargaEnergiaDiariaEntity.getId());

        partialUpdatedOnsCargaEnergiaDiariaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiamwmed(UPDATED_VAL_CARGAENERGIAMWMED);

        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaDiaria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaDiariaEntityUpdatableFieldsEquals(
            partialUpdatedOnsCargaEnergiaDiariaEntity,
            getPersistedOnsCargaEnergiaDiariaEntity(partialUpdatedOnsCargaEnergiaDiariaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCargaEnergiaDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        onsCargaEnergiaDiariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCargaEnergiaDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCargaEnergiaDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        onsCargaEnergiaDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCargaEnergiaDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        onsCargaEnergiaDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaDiariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCargaEnergiaDiaria() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.saveAndFlush(onsCargaEnergiaDiariaEntity);
        onsCargaEnergiaDiariaRepository.save(onsCargaEnergiaDiariaEntity);
        onsCargaEnergiaDiariaSearchRepository.save(onsCargaEnergiaDiariaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCargaEnergiaDiaria
        restOnsCargaEnergiaDiariaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCargaEnergiaDiariaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCargaEnergiaDiaria() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.saveAndFlush(onsCargaEnergiaDiariaEntity);
        onsCargaEnergiaDiariaSearchRepository.save(onsCargaEnergiaDiariaEntity);

        // Search the onsCargaEnergiaDiaria
        restOnsCargaEnergiaDiariaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCargaEnergiaDiariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaDiariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCargaenergiamwmed").value(hasItem(DEFAULT_VAL_CARGAENERGIAMWMED)));
    }

    protected long getRepositoryCount() {
        return onsCargaEnergiaDiariaRepository.count();
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

    protected OnsCargaEnergiaDiariaEntity getPersistedOnsCargaEnergiaDiariaEntity(OnsCargaEnergiaDiariaEntity onsCargaEnergiaDiaria) {
        return onsCargaEnergiaDiariaRepository.findById(onsCargaEnergiaDiaria.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCargaEnergiaDiariaEntityToMatchAllProperties(
        OnsCargaEnergiaDiariaEntity expectedOnsCargaEnergiaDiariaEntity
    ) {
        assertOnsCargaEnergiaDiariaEntityAllPropertiesEquals(
            expectedOnsCargaEnergiaDiariaEntity,
            getPersistedOnsCargaEnergiaDiariaEntity(expectedOnsCargaEnergiaDiariaEntity)
        );
    }

    protected void assertPersistedOnsCargaEnergiaDiariaEntityToMatchUpdatableProperties(
        OnsCargaEnergiaDiariaEntity expectedOnsCargaEnergiaDiariaEntity
    ) {
        assertOnsCargaEnergiaDiariaEntityAllUpdatablePropertiesEquals(
            expectedOnsCargaEnergiaDiariaEntity,
            getPersistedOnsCargaEnergiaDiariaEntity(expectedOnsCargaEnergiaDiariaEntity)
        );
    }
}
