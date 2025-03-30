package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaProgramadaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaProgramadaSearchRepository;
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
 * Integration tests for the {@link OnsCargaEnergiaProgramadaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCargaEnergiaProgramadaResourceIT {

    private static final String DEFAULT_COD_AREACARGA = "AAAAAAAAAA";
    private static final String UPDATED_COD_AREACARGA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DIN_REFERENCIAUTC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_REFERENCIAUTC = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_CARGAGLOBALPROGRAMADA = 1D;
    private static final Double UPDATED_VAL_CARGAGLOBALPROGRAMADA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-carga-energia-programadas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-carga-energia-programadas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCargaEnergiaProgramadaRepository onsCargaEnergiaProgramadaRepository;

    @Autowired
    private OnsCargaEnergiaProgramadaSearchRepository onsCargaEnergiaProgramadaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCargaEnergiaProgramadaMockMvc;

    private OnsCargaEnergiaProgramadaEntity onsCargaEnergiaProgramadaEntity;

    private OnsCargaEnergiaProgramadaEntity insertedOnsCargaEnergiaProgramadaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCargaEnergiaProgramadaEntity createEntity() {
        return new OnsCargaEnergiaProgramadaEntity()
            .codAreacarga(DEFAULT_COD_AREACARGA)
            .datReferencia(DEFAULT_DAT_REFERENCIA)
            .dinReferenciautc(DEFAULT_DIN_REFERENCIAUTC)
            .valCargaglobalprogramada(DEFAULT_VAL_CARGAGLOBALPROGRAMADA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCargaEnergiaProgramadaEntity createUpdatedEntity() {
        return new OnsCargaEnergiaProgramadaEntity()
            .codAreacarga(UPDATED_COD_AREACARGA)
            .datReferencia(UPDATED_DAT_REFERENCIA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobalprogramada(UPDATED_VAL_CARGAGLOBALPROGRAMADA);
    }

    @BeforeEach
    public void initTest() {
        onsCargaEnergiaProgramadaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCargaEnergiaProgramadaEntity != null) {
            onsCargaEnergiaProgramadaRepository.delete(insertedOnsCargaEnergiaProgramadaEntity);
            onsCargaEnergiaProgramadaSearchRepository.delete(insertedOnsCargaEnergiaProgramadaEntity);
            insertedOnsCargaEnergiaProgramadaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaProgramada() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        // Create the OnsCargaEnergiaProgramada
        var returnedOnsCargaEnergiaProgramadaEntity = om.readValue(
            restOnsCargaEnergiaProgramadaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCargaEnergiaProgramadaEntity.class
        );

        // Validate the OnsCargaEnergiaProgramada in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCargaEnergiaProgramadaEntityUpdatableFieldsEquals(
            returnedOnsCargaEnergiaProgramadaEntity,
            getPersistedOnsCargaEnergiaProgramadaEntity(returnedOnsCargaEnergiaProgramadaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCargaEnergiaProgramadaEntity = returnedOnsCargaEnergiaProgramadaEntity;
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaProgramadaWithExistingId() throws Exception {
        // Create the OnsCargaEnergiaProgramada with an existing ID
        onsCargaEnergiaProgramadaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCargaEnergiaProgramadas() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.saveAndFlush(onsCargaEnergiaProgramadaEntity);

        // Get all the onsCargaEnergiaProgramadaList
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaProgramadaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].codAreacarga").value(hasItem(DEFAULT_COD_AREACARGA)))
            .andExpect(jsonPath("$.[*].datReferencia").value(hasItem(DEFAULT_DAT_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].dinReferenciautc").value(hasItem(DEFAULT_DIN_REFERENCIAUTC.toString())))
            .andExpect(jsonPath("$.[*].valCargaglobalprogramada").value(hasItem(DEFAULT_VAL_CARGAGLOBALPROGRAMADA)));
    }

    @Test
    @Transactional
    void getOnsCargaEnergiaProgramada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.saveAndFlush(onsCargaEnergiaProgramadaEntity);

        // Get the onsCargaEnergiaProgramada
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCargaEnergiaProgramadaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCargaEnergiaProgramadaEntity.getId().intValue()))
            .andExpect(jsonPath("$.codAreacarga").value(DEFAULT_COD_AREACARGA))
            .andExpect(jsonPath("$.datReferencia").value(DEFAULT_DAT_REFERENCIA.toString()))
            .andExpect(jsonPath("$.dinReferenciautc").value(DEFAULT_DIN_REFERENCIAUTC.toString()))
            .andExpect(jsonPath("$.valCargaglobalprogramada").value(DEFAULT_VAL_CARGAGLOBALPROGRAMADA));
    }

    @Test
    @Transactional
    void getNonExistingOnsCargaEnergiaProgramada() throws Exception {
        // Get the onsCargaEnergiaProgramada
        restOnsCargaEnergiaProgramadaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCargaEnergiaProgramada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.saveAndFlush(onsCargaEnergiaProgramadaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCargaEnergiaProgramadaSearchRepository.save(onsCargaEnergiaProgramadaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());

        // Update the onsCargaEnergiaProgramada
        OnsCargaEnergiaProgramadaEntity updatedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository
            .findById(onsCargaEnergiaProgramadaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCargaEnergiaProgramadaEntity are not directly saved in db
        em.detach(updatedOnsCargaEnergiaProgramadaEntity);
        updatedOnsCargaEnergiaProgramadaEntity
            .codAreacarga(UPDATED_COD_AREACARGA)
            .datReferencia(UPDATED_DAT_REFERENCIA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobalprogramada(UPDATED_VAL_CARGAGLOBALPROGRAMADA);

        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCargaEnergiaProgramadaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCargaEnergiaProgramadaEntityToMatchAllProperties(updatedOnsCargaEnergiaProgramadaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCargaEnergiaProgramadaEntity> onsCargaEnergiaProgramadaSearchList = Streamable.of(
                    onsCargaEnergiaProgramadaSearchRepository.findAll()
                ).toList();
                OnsCargaEnergiaProgramadaEntity testOnsCargaEnergiaProgramadaSearch = onsCargaEnergiaProgramadaSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsCargaEnergiaProgramadaEntityAllPropertiesEquals(
                    testOnsCargaEnergiaProgramadaSearch,
                    updatedOnsCargaEnergiaProgramadaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCargaEnergiaProgramada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        onsCargaEnergiaProgramadaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCargaEnergiaProgramadaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCargaEnergiaProgramada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        onsCargaEnergiaProgramadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCargaEnergiaProgramada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        onsCargaEnergiaProgramadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCargaEnergiaProgramadaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.saveAndFlush(onsCargaEnergiaProgramadaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaProgramada using partial update
        OnsCargaEnergiaProgramadaEntity partialUpdatedOnsCargaEnergiaProgramadaEntity = new OnsCargaEnergiaProgramadaEntity();
        partialUpdatedOnsCargaEnergiaProgramadaEntity.setId(onsCargaEnergiaProgramadaEntity.getId());

        partialUpdatedOnsCargaEnergiaProgramadaEntity
            .codAreacarga(UPDATED_COD_AREACARGA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobalprogramada(UPDATED_VAL_CARGAGLOBALPROGRAMADA);

        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaProgramadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaProgramada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaProgramadaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCargaEnergiaProgramadaEntity, onsCargaEnergiaProgramadaEntity),
            getPersistedOnsCargaEnergiaProgramadaEntity(onsCargaEnergiaProgramadaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCargaEnergiaProgramadaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.saveAndFlush(onsCargaEnergiaProgramadaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaProgramada using partial update
        OnsCargaEnergiaProgramadaEntity partialUpdatedOnsCargaEnergiaProgramadaEntity = new OnsCargaEnergiaProgramadaEntity();
        partialUpdatedOnsCargaEnergiaProgramadaEntity.setId(onsCargaEnergiaProgramadaEntity.getId());

        partialUpdatedOnsCargaEnergiaProgramadaEntity
            .codAreacarga(UPDATED_COD_AREACARGA)
            .datReferencia(UPDATED_DAT_REFERENCIA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobalprogramada(UPDATED_VAL_CARGAGLOBALPROGRAMADA);

        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaProgramadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaProgramada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaProgramadaEntityUpdatableFieldsEquals(
            partialUpdatedOnsCargaEnergiaProgramadaEntity,
            getPersistedOnsCargaEnergiaProgramadaEntity(partialUpdatedOnsCargaEnergiaProgramadaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCargaEnergiaProgramada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        onsCargaEnergiaProgramadaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCargaEnergiaProgramadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCargaEnergiaProgramada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        onsCargaEnergiaProgramadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCargaEnergiaProgramada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        onsCargaEnergiaProgramadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaProgramadaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaProgramada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCargaEnergiaProgramada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.saveAndFlush(onsCargaEnergiaProgramadaEntity);
        onsCargaEnergiaProgramadaRepository.save(onsCargaEnergiaProgramadaEntity);
        onsCargaEnergiaProgramadaSearchRepository.save(onsCargaEnergiaProgramadaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCargaEnergiaProgramada
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCargaEnergiaProgramadaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaProgramadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCargaEnergiaProgramada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.saveAndFlush(onsCargaEnergiaProgramadaEntity);
        onsCargaEnergiaProgramadaSearchRepository.save(onsCargaEnergiaProgramadaEntity);

        // Search the onsCargaEnergiaProgramada
        restOnsCargaEnergiaProgramadaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCargaEnergiaProgramadaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaProgramadaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].codAreacarga").value(hasItem(DEFAULT_COD_AREACARGA)))
            .andExpect(jsonPath("$.[*].datReferencia").value(hasItem(DEFAULT_DAT_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].dinReferenciautc").value(hasItem(DEFAULT_DIN_REFERENCIAUTC.toString())))
            .andExpect(jsonPath("$.[*].valCargaglobalprogramada").value(hasItem(DEFAULT_VAL_CARGAGLOBALPROGRAMADA)));
    }

    protected long getRepositoryCount() {
        return onsCargaEnergiaProgramadaRepository.count();
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

    protected OnsCargaEnergiaProgramadaEntity getPersistedOnsCargaEnergiaProgramadaEntity(
        OnsCargaEnergiaProgramadaEntity onsCargaEnergiaProgramada
    ) {
        return onsCargaEnergiaProgramadaRepository.findById(onsCargaEnergiaProgramada.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCargaEnergiaProgramadaEntityToMatchAllProperties(
        OnsCargaEnergiaProgramadaEntity expectedOnsCargaEnergiaProgramadaEntity
    ) {
        assertOnsCargaEnergiaProgramadaEntityAllPropertiesEquals(
            expectedOnsCargaEnergiaProgramadaEntity,
            getPersistedOnsCargaEnergiaProgramadaEntity(expectedOnsCargaEnergiaProgramadaEntity)
        );
    }

    protected void assertPersistedOnsCargaEnergiaProgramadaEntityToMatchUpdatableProperties(
        OnsCargaEnergiaProgramadaEntity expectedOnsCargaEnergiaProgramadaEntity
    ) {
        assertOnsCargaEnergiaProgramadaEntityAllUpdatablePropertiesEquals(
            expectedOnsCargaEnergiaProgramadaEntity,
            getPersistedOnsCargaEnergiaProgramadaEntity(expectedOnsCargaEnergiaProgramadaEntity)
        );
    }
}
