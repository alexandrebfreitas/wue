package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository;
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
 * Integration tests for the {@link OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResourceIT {

    private static final String DEFAULT_DSC_AGREGACAO = "AAAAAAAAAA";
    private static final String UPDATED_DSC_AGREGACAO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_CARACTERISTICA = "AAAAAAAAAA";
    private static final String UPDATED_COD_CARACTERISTICA = "BBBBBBBBBB";

    private static final String DEFAULT_DSC_CARACTERISTICA = "AAAAAAAAAA";
    private static final String UPDATED_DSC_CARACTERISTICA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_PERIODICIDADE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PERIODICIDADE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_DREQ = 1D;
    private static final Double UPDATED_VAL_DREQ = 2D;

    private static final Double DEFAULT_VAL_FREQ = 1D;
    private static final Double UPDATED_VAL_FREQ = 2D;

    private static final String ENTITY_API_URL = "/api/ons-indicadores-confiabilidade-rede-basica-dreq-freqs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-indicadores-confiabilidade-rede-basica-dreq-freqs/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc;

    private OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity;

    private OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity createEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity()
            .dscAgregacao(DEFAULT_DSC_AGREGACAO)
            .codCaracteristica(DEFAULT_COD_CARACTERISTICA)
            .dscCaracteristica(DEFAULT_DSC_CARACTERISTICA)
            .idPeriodicidade(DEFAULT_ID_PERIODICIDADE)
            .dinReferencia(DEFAULT_DIN_REFERENCIA)
            .valDreq(DEFAULT_VAL_DREQ)
            .valFreq(DEFAULT_VAL_FREQ);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity createUpdatedEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity()
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valDreq(UPDATED_VAL_DREQ)
            .valFreq(UPDATED_VAL_FREQ);
    }

    @BeforeEach
    public void initTest() {
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity != null) {
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.delete(insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.delete(
                insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
            );
            insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq
        var returnedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity = om.readValue(
            restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.class
        );

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityUpdatableFieldsEquals(
            returnedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity(returnedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity = returnedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity;
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqWithExistingId() throws Exception {
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq with an existing ID
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqs() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.saveAndFlush(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);

        // Get all the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqList
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valDreq").value(hasItem(DEFAULT_VAL_DREQ)))
            .andExpect(jsonPath("$.[*].valFreq").value(hasItem(DEFAULT_VAL_FREQ)));
    }

    @Test
    @Transactional
    void getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.saveAndFlush(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);

        // Get the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId().intValue()))
            .andExpect(jsonPath("$.dscAgregacao").value(DEFAULT_DSC_AGREGACAO))
            .andExpect(jsonPath("$.codCaracteristica").value(DEFAULT_COD_CARACTERISTICA))
            .andExpect(jsonPath("$.dscCaracteristica").value(DEFAULT_DSC_CARACTERISTICA))
            .andExpect(jsonPath("$.idPeriodicidade").value(DEFAULT_ID_PERIODICIDADE))
            .andExpect(jsonPath("$.dinReferencia").value(DEFAULT_DIN_REFERENCIA.toString()))
            .andExpect(jsonPath("$.valDreq").value(DEFAULT_VAL_DREQ))
            .andExpect(jsonPath("$.valFreq").value(DEFAULT_VAL_FREQ));
    }

    @Test
    @Transactional
    void getNonExistingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        // Get the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.saveAndFlush(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.save(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());

        // Update the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository
                .findById(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity are not directly saved in db
        em.detach(updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
        updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valDreq(UPDATED_VAL_DREQ)
            .valFreq(UPDATED_VAL_FREQ);

        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityToMatchAllProperties(
            updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchList =
                    Streamable.of(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll()).toList();
                OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity testOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearch =
                    onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityAllPropertiesEquals(
                    testOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearch,
                    updatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.saveAndFlush(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valDreq(UPDATED_VAL_DREQ);

        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity,
                onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
            ),
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.saveAndFlush(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valDreq(UPDATED_VAL_DREQ)
            .valFreq(UPDATED_VAL_FREQ);

        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityUpdatableFieldsEquals(
            partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.saveAndFlush(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.save(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.save(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.saveAndFlush(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.save(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);

        // Search the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq
        restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valDreq").value(hasItem(DEFAULT_VAL_DREQ)))
            .andExpect(jsonPath("$.[*].valFreq").value(hasItem(DEFAULT_VAL_FREQ)));
    }

    protected long getRepositoryCount() {
        return onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.count();
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

    protected OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity getPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity(
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity onsIndicadoresConfiabilidadeRedeBasicaDreqFreq
    ) {
        return onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository
            .findById(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityToMatchAllProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityAllPropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity(expectedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity)
        );
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityToMatchUpdatableProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntityAllUpdatablePropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity(expectedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity)
        );
    }
}
