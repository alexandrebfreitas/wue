package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaVerificadaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaVerificadaSearchRepository;
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
 * Integration tests for the {@link OnsCargaEnergiaVerificadaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCargaEnergiaVerificadaResourceIT {

    private static final String DEFAULT_COD_AREACARGA = "AAAAAAAAAA";
    private static final String UPDATED_COD_AREACARGA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DIN_REFERENCIAUTC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_REFERENCIAUTC = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_CARGAGLOBAL = 1D;
    private static final Double UPDATED_VAL_CARGAGLOBAL = 2D;

    private static final Double DEFAULT_VAL_CARGAGLOBALSMMG = 1D;
    private static final Double UPDATED_VAL_CARGAGLOBALSMMG = 2D;

    private static final Double DEFAULT_VAL_CARGAMMGD = 1D;
    private static final Double UPDATED_VAL_CARGAMMGD = 2D;

    private static final Double DEFAULT_VAL_CARGAGLOBALCONS = 1D;
    private static final Double UPDATED_VAL_CARGAGLOBALCONS = 2D;

    private static final Double DEFAULT_VAL_CONSISTENCIA = 1D;
    private static final Double UPDATED_VAL_CONSISTENCIA = 2D;

    private static final Double DEFAULT_VAL_CARGASUPERVISIONADA = 1D;
    private static final Double UPDATED_VAL_CARGASUPERVISIONADA = 2D;

    private static final Double DEFAULT_VAL_CARGANAOSUPERVISIONADA = 1D;
    private static final Double UPDATED_VAL_CARGANAOSUPERVISIONADA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-carga-energia-verificadas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-carga-energia-verificadas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCargaEnergiaVerificadaRepository onsCargaEnergiaVerificadaRepository;

    @Autowired
    private OnsCargaEnergiaVerificadaSearchRepository onsCargaEnergiaVerificadaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCargaEnergiaVerificadaMockMvc;

    private OnsCargaEnergiaVerificadaEntity onsCargaEnergiaVerificadaEntity;

    private OnsCargaEnergiaVerificadaEntity insertedOnsCargaEnergiaVerificadaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCargaEnergiaVerificadaEntity createEntity() {
        return new OnsCargaEnergiaVerificadaEntity()
            .codAreacarga(DEFAULT_COD_AREACARGA)
            .datReferencia(DEFAULT_DAT_REFERENCIA)
            .dinReferenciautc(DEFAULT_DIN_REFERENCIAUTC)
            .valCargaglobal(DEFAULT_VAL_CARGAGLOBAL)
            .valCargaglobalsmmg(DEFAULT_VAL_CARGAGLOBALSMMG)
            .valCargammgd(DEFAULT_VAL_CARGAMMGD)
            .valCargaglobalcons(DEFAULT_VAL_CARGAGLOBALCONS)
            .valConsistencia(DEFAULT_VAL_CONSISTENCIA)
            .valCargasupervisionada(DEFAULT_VAL_CARGASUPERVISIONADA)
            .valCarganaosupervisionada(DEFAULT_VAL_CARGANAOSUPERVISIONADA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCargaEnergiaVerificadaEntity createUpdatedEntity() {
        return new OnsCargaEnergiaVerificadaEntity()
            .codAreacarga(UPDATED_COD_AREACARGA)
            .datReferencia(UPDATED_DAT_REFERENCIA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobal(UPDATED_VAL_CARGAGLOBAL)
            .valCargaglobalsmmg(UPDATED_VAL_CARGAGLOBALSMMG)
            .valCargammgd(UPDATED_VAL_CARGAMMGD)
            .valCargaglobalcons(UPDATED_VAL_CARGAGLOBALCONS)
            .valConsistencia(UPDATED_VAL_CONSISTENCIA)
            .valCargasupervisionada(UPDATED_VAL_CARGASUPERVISIONADA)
            .valCarganaosupervisionada(UPDATED_VAL_CARGANAOSUPERVISIONADA);
    }

    @BeforeEach
    public void initTest() {
        onsCargaEnergiaVerificadaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCargaEnergiaVerificadaEntity != null) {
            onsCargaEnergiaVerificadaRepository.delete(insertedOnsCargaEnergiaVerificadaEntity);
            onsCargaEnergiaVerificadaSearchRepository.delete(insertedOnsCargaEnergiaVerificadaEntity);
            insertedOnsCargaEnergiaVerificadaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaVerificada() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        // Create the OnsCargaEnergiaVerificada
        var returnedOnsCargaEnergiaVerificadaEntity = om.readValue(
            restOnsCargaEnergiaVerificadaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCargaEnergiaVerificadaEntity.class
        );

        // Validate the OnsCargaEnergiaVerificada in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCargaEnergiaVerificadaEntityUpdatableFieldsEquals(
            returnedOnsCargaEnergiaVerificadaEntity,
            getPersistedOnsCargaEnergiaVerificadaEntity(returnedOnsCargaEnergiaVerificadaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCargaEnergiaVerificadaEntity = returnedOnsCargaEnergiaVerificadaEntity;
    }

    @Test
    @Transactional
    void createOnsCargaEnergiaVerificadaWithExistingId() throws Exception {
        // Create the OnsCargaEnergiaVerificada with an existing ID
        onsCargaEnergiaVerificadaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCargaEnergiaVerificadas() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.saveAndFlush(onsCargaEnergiaVerificadaEntity);

        // Get all the onsCargaEnergiaVerificadaList
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaVerificadaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].codAreacarga").value(hasItem(DEFAULT_COD_AREACARGA)))
            .andExpect(jsonPath("$.[*].datReferencia").value(hasItem(DEFAULT_DAT_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].dinReferenciautc").value(hasItem(DEFAULT_DIN_REFERENCIAUTC.toString())))
            .andExpect(jsonPath("$.[*].valCargaglobal").value(hasItem(DEFAULT_VAL_CARGAGLOBAL)))
            .andExpect(jsonPath("$.[*].valCargaglobalsmmg").value(hasItem(DEFAULT_VAL_CARGAGLOBALSMMG)))
            .andExpect(jsonPath("$.[*].valCargammgd").value(hasItem(DEFAULT_VAL_CARGAMMGD)))
            .andExpect(jsonPath("$.[*].valCargaglobalcons").value(hasItem(DEFAULT_VAL_CARGAGLOBALCONS)))
            .andExpect(jsonPath("$.[*].valConsistencia").value(hasItem(DEFAULT_VAL_CONSISTENCIA)))
            .andExpect(jsonPath("$.[*].valCargasupervisionada").value(hasItem(DEFAULT_VAL_CARGASUPERVISIONADA)))
            .andExpect(jsonPath("$.[*].valCarganaosupervisionada").value(hasItem(DEFAULT_VAL_CARGANAOSUPERVISIONADA)));
    }

    @Test
    @Transactional
    void getOnsCargaEnergiaVerificada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.saveAndFlush(onsCargaEnergiaVerificadaEntity);

        // Get the onsCargaEnergiaVerificada
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCargaEnergiaVerificadaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCargaEnergiaVerificadaEntity.getId().intValue()))
            .andExpect(jsonPath("$.codAreacarga").value(DEFAULT_COD_AREACARGA))
            .andExpect(jsonPath("$.datReferencia").value(DEFAULT_DAT_REFERENCIA.toString()))
            .andExpect(jsonPath("$.dinReferenciautc").value(DEFAULT_DIN_REFERENCIAUTC.toString()))
            .andExpect(jsonPath("$.valCargaglobal").value(DEFAULT_VAL_CARGAGLOBAL))
            .andExpect(jsonPath("$.valCargaglobalsmmg").value(DEFAULT_VAL_CARGAGLOBALSMMG))
            .andExpect(jsonPath("$.valCargammgd").value(DEFAULT_VAL_CARGAMMGD))
            .andExpect(jsonPath("$.valCargaglobalcons").value(DEFAULT_VAL_CARGAGLOBALCONS))
            .andExpect(jsonPath("$.valConsistencia").value(DEFAULT_VAL_CONSISTENCIA))
            .andExpect(jsonPath("$.valCargasupervisionada").value(DEFAULT_VAL_CARGASUPERVISIONADA))
            .andExpect(jsonPath("$.valCarganaosupervisionada").value(DEFAULT_VAL_CARGANAOSUPERVISIONADA));
    }

    @Test
    @Transactional
    void getNonExistingOnsCargaEnergiaVerificada() throws Exception {
        // Get the onsCargaEnergiaVerificada
        restOnsCargaEnergiaVerificadaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCargaEnergiaVerificada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.saveAndFlush(onsCargaEnergiaVerificadaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCargaEnergiaVerificadaSearchRepository.save(onsCargaEnergiaVerificadaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());

        // Update the onsCargaEnergiaVerificada
        OnsCargaEnergiaVerificadaEntity updatedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository
            .findById(onsCargaEnergiaVerificadaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCargaEnergiaVerificadaEntity are not directly saved in db
        em.detach(updatedOnsCargaEnergiaVerificadaEntity);
        updatedOnsCargaEnergiaVerificadaEntity
            .codAreacarga(UPDATED_COD_AREACARGA)
            .datReferencia(UPDATED_DAT_REFERENCIA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobal(UPDATED_VAL_CARGAGLOBAL)
            .valCargaglobalsmmg(UPDATED_VAL_CARGAGLOBALSMMG)
            .valCargammgd(UPDATED_VAL_CARGAMMGD)
            .valCargaglobalcons(UPDATED_VAL_CARGAGLOBALCONS)
            .valConsistencia(UPDATED_VAL_CONSISTENCIA)
            .valCargasupervisionada(UPDATED_VAL_CARGASUPERVISIONADA)
            .valCarganaosupervisionada(UPDATED_VAL_CARGANAOSUPERVISIONADA);

        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCargaEnergiaVerificadaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCargaEnergiaVerificadaEntityToMatchAllProperties(updatedOnsCargaEnergiaVerificadaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCargaEnergiaVerificadaEntity> onsCargaEnergiaVerificadaSearchList = Streamable.of(
                    onsCargaEnergiaVerificadaSearchRepository.findAll()
                ).toList();
                OnsCargaEnergiaVerificadaEntity testOnsCargaEnergiaVerificadaSearch = onsCargaEnergiaVerificadaSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsCargaEnergiaVerificadaEntityAllPropertiesEquals(
                    testOnsCargaEnergiaVerificadaSearch,
                    updatedOnsCargaEnergiaVerificadaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCargaEnergiaVerificada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        onsCargaEnergiaVerificadaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCargaEnergiaVerificadaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCargaEnergiaVerificada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        onsCargaEnergiaVerificadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCargaEnergiaVerificada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        onsCargaEnergiaVerificadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCargaEnergiaVerificadaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.saveAndFlush(onsCargaEnergiaVerificadaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaVerificada using partial update
        OnsCargaEnergiaVerificadaEntity partialUpdatedOnsCargaEnergiaVerificadaEntity = new OnsCargaEnergiaVerificadaEntity();
        partialUpdatedOnsCargaEnergiaVerificadaEntity.setId(onsCargaEnergiaVerificadaEntity.getId());

        partialUpdatedOnsCargaEnergiaVerificadaEntity
            .codAreacarga(UPDATED_COD_AREACARGA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobal(UPDATED_VAL_CARGAGLOBAL)
            .valCargaglobalsmmg(UPDATED_VAL_CARGAGLOBALSMMG)
            .valCargaglobalcons(UPDATED_VAL_CARGAGLOBALCONS)
            .valConsistencia(UPDATED_VAL_CONSISTENCIA)
            .valCargasupervisionada(UPDATED_VAL_CARGASUPERVISIONADA);

        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaVerificadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaVerificada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaVerificadaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCargaEnergiaVerificadaEntity, onsCargaEnergiaVerificadaEntity),
            getPersistedOnsCargaEnergiaVerificadaEntity(onsCargaEnergiaVerificadaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCargaEnergiaVerificadaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.saveAndFlush(onsCargaEnergiaVerificadaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCargaEnergiaVerificada using partial update
        OnsCargaEnergiaVerificadaEntity partialUpdatedOnsCargaEnergiaVerificadaEntity = new OnsCargaEnergiaVerificadaEntity();
        partialUpdatedOnsCargaEnergiaVerificadaEntity.setId(onsCargaEnergiaVerificadaEntity.getId());

        partialUpdatedOnsCargaEnergiaVerificadaEntity
            .codAreacarga(UPDATED_COD_AREACARGA)
            .datReferencia(UPDATED_DAT_REFERENCIA)
            .dinReferenciautc(UPDATED_DIN_REFERENCIAUTC)
            .valCargaglobal(UPDATED_VAL_CARGAGLOBAL)
            .valCargaglobalsmmg(UPDATED_VAL_CARGAGLOBALSMMG)
            .valCargammgd(UPDATED_VAL_CARGAMMGD)
            .valCargaglobalcons(UPDATED_VAL_CARGAGLOBALCONS)
            .valConsistencia(UPDATED_VAL_CONSISTENCIA)
            .valCargasupervisionada(UPDATED_VAL_CARGASUPERVISIONADA)
            .valCarganaosupervisionada(UPDATED_VAL_CARGANAOSUPERVISIONADA);

        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCargaEnergiaVerificadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCargaEnergiaVerificada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCargaEnergiaVerificadaEntityUpdatableFieldsEquals(
            partialUpdatedOnsCargaEnergiaVerificadaEntity,
            getPersistedOnsCargaEnergiaVerificadaEntity(partialUpdatedOnsCargaEnergiaVerificadaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCargaEnergiaVerificada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        onsCargaEnergiaVerificadaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCargaEnergiaVerificadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCargaEnergiaVerificada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        onsCargaEnergiaVerificadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCargaEnergiaVerificada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        onsCargaEnergiaVerificadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCargaEnergiaVerificadaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCargaEnergiaVerificada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCargaEnergiaVerificada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.saveAndFlush(onsCargaEnergiaVerificadaEntity);
        onsCargaEnergiaVerificadaRepository.save(onsCargaEnergiaVerificadaEntity);
        onsCargaEnergiaVerificadaSearchRepository.save(onsCargaEnergiaVerificadaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCargaEnergiaVerificada
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCargaEnergiaVerificadaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCargaEnergiaVerificadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCargaEnergiaVerificada() throws Exception {
        // Initialize the database
        insertedOnsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.saveAndFlush(onsCargaEnergiaVerificadaEntity);
        onsCargaEnergiaVerificadaSearchRepository.save(onsCargaEnergiaVerificadaEntity);

        // Search the onsCargaEnergiaVerificada
        restOnsCargaEnergiaVerificadaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCargaEnergiaVerificadaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCargaEnergiaVerificadaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].codAreacarga").value(hasItem(DEFAULT_COD_AREACARGA)))
            .andExpect(jsonPath("$.[*].datReferencia").value(hasItem(DEFAULT_DAT_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].dinReferenciautc").value(hasItem(DEFAULT_DIN_REFERENCIAUTC.toString())))
            .andExpect(jsonPath("$.[*].valCargaglobal").value(hasItem(DEFAULT_VAL_CARGAGLOBAL)))
            .andExpect(jsonPath("$.[*].valCargaglobalsmmg").value(hasItem(DEFAULT_VAL_CARGAGLOBALSMMG)))
            .andExpect(jsonPath("$.[*].valCargammgd").value(hasItem(DEFAULT_VAL_CARGAMMGD)))
            .andExpect(jsonPath("$.[*].valCargaglobalcons").value(hasItem(DEFAULT_VAL_CARGAGLOBALCONS)))
            .andExpect(jsonPath("$.[*].valConsistencia").value(hasItem(DEFAULT_VAL_CONSISTENCIA)))
            .andExpect(jsonPath("$.[*].valCargasupervisionada").value(hasItem(DEFAULT_VAL_CARGASUPERVISIONADA)))
            .andExpect(jsonPath("$.[*].valCarganaosupervisionada").value(hasItem(DEFAULT_VAL_CARGANAOSUPERVISIONADA)));
    }

    protected long getRepositoryCount() {
        return onsCargaEnergiaVerificadaRepository.count();
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

    protected OnsCargaEnergiaVerificadaEntity getPersistedOnsCargaEnergiaVerificadaEntity(
        OnsCargaEnergiaVerificadaEntity onsCargaEnergiaVerificada
    ) {
        return onsCargaEnergiaVerificadaRepository.findById(onsCargaEnergiaVerificada.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCargaEnergiaVerificadaEntityToMatchAllProperties(
        OnsCargaEnergiaVerificadaEntity expectedOnsCargaEnergiaVerificadaEntity
    ) {
        assertOnsCargaEnergiaVerificadaEntityAllPropertiesEquals(
            expectedOnsCargaEnergiaVerificadaEntity,
            getPersistedOnsCargaEnergiaVerificadaEntity(expectedOnsCargaEnergiaVerificadaEntity)
        );
    }

    protected void assertPersistedOnsCargaEnergiaVerificadaEntityToMatchUpdatableProperties(
        OnsCargaEnergiaVerificadaEntity expectedOnsCargaEnergiaVerificadaEntity
    ) {
        assertOnsCargaEnergiaVerificadaEntityAllUpdatablePropertiesEquals(
            expectedOnsCargaEnergiaVerificadaEntity,
            getPersistedOnsCargaEnergiaVerificadaEntity(expectedOnsCargaEnergiaVerificadaEntity)
        );
    }
}
