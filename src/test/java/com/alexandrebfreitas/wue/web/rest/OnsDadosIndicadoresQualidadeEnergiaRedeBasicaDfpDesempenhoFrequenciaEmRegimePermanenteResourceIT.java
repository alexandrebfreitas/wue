package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository;
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
 * Integration tests for the {@link OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteResourceIT {

    private static final String DEFAULT_ID_PERIODICIDADE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PERIODICIDADE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUM_DESVIO_PERM_SOBRE = 1;
    private static final Integer UPDATED_NUM_DESVIO_PERM_SOBRE = 2;

    private static final Integer DEFAULT_NUM_DESVIO_PERM_SUB = 1;
    private static final Integer UPDATED_NUM_DESVIO_PERM_SUB = 2;

    private static final Integer DEFAULT_NUM_DESVIO_DIST_SOBRE = 1;
    private static final Integer UPDATED_NUM_DESVIO_DIST_SOBRE = 2;

    private static final Integer DEFAULT_NUM_DESVIO_DIST_SUB = 1;
    private static final Integer UPDATED_NUM_DESVIO_DIST_SUB = 2;

    private static final Integer DEFAULT_NUM_MINUTOS = 1;
    private static final Integer UPDATED_NUM_MINUTOS = 2;

    private static final Integer DEFAULT_NUM_VIOLADODIS = 1;
    private static final Integer UPDATED_NUM_VIOLADODIS = 2;

    private static final Integer DEFAULT_NUM_VIOLADOPERM = 1;
    private static final Integer UPDATED_NUM_VIOLADOPERM = 2;

    private static final Double DEFAULT_VAL_DFP = 1D;
    private static final Double UPDATED_VAL_DFP = 2D;

    private static final String ENTITY_API_URL =
        "/api/ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository;

    @Autowired
    private OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc;

    private OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity;

    private OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity createEntity() {
        return new OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity()
            .idPeriodicidade(DEFAULT_ID_PERIODICIDADE)
            .dinReferencia(DEFAULT_DIN_REFERENCIA)
            .numDesvioPermSobre(DEFAULT_NUM_DESVIO_PERM_SOBRE)
            .numDesvioPermSub(DEFAULT_NUM_DESVIO_PERM_SUB)
            .numDesvioDistSobre(DEFAULT_NUM_DESVIO_DIST_SOBRE)
            .numDesvioDistSub(DEFAULT_NUM_DESVIO_DIST_SUB)
            .numMinutos(DEFAULT_NUM_MINUTOS)
            .numVioladodis(DEFAULT_NUM_VIOLADODIS)
            .numVioladoperm(DEFAULT_NUM_VIOLADOPERM)
            .valDfp(DEFAULT_VAL_DFP);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity createUpdatedEntity() {
        return new OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity()
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numDesvioPermSobre(UPDATED_NUM_DESVIO_PERM_SOBRE)
            .numDesvioPermSub(UPDATED_NUM_DESVIO_PERM_SUB)
            .numDesvioDistSobre(UPDATED_NUM_DESVIO_DIST_SOBRE)
            .numDesvioDistSub(UPDATED_NUM_DESVIO_DIST_SUB)
            .numMinutos(UPDATED_NUM_MINUTOS)
            .numVioladodis(UPDATED_NUM_VIOLADODIS)
            .numVioladoperm(UPDATED_NUM_VIOLADOPERM)
            .valDfp(UPDATED_VAL_DFP);
    }

    @BeforeEach
    public void initTest() {
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity != null) {
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.delete(
                insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.delete(
                insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );
            insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        // Create the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
        var returnedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity = om.readValue(
            restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            om.writeValueAsBytes(
                                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
                            )
                        )
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.class
        );

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityUpdatableFieldsEquals(
            returnedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity,
            getPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity(
                returnedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            returnedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity;
    }

    @Test
    @Transactional
    void createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteWithExistingId() throws Exception {
        // Create the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente with an existing ID
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        om.writeValueAsBytes(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)
                    )
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.saveAndFlush(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );

        // Get all the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteList
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].numDesvioPermSobre").value(hasItem(DEFAULT_NUM_DESVIO_PERM_SOBRE)))
            .andExpect(jsonPath("$.[*].numDesvioPermSub").value(hasItem(DEFAULT_NUM_DESVIO_PERM_SUB)))
            .andExpect(jsonPath("$.[*].numDesvioDistSobre").value(hasItem(DEFAULT_NUM_DESVIO_DIST_SOBRE)))
            .andExpect(jsonPath("$.[*].numDesvioDistSub").value(hasItem(DEFAULT_NUM_DESVIO_DIST_SUB)))
            .andExpect(jsonPath("$.[*].numMinutos").value(hasItem(DEFAULT_NUM_MINUTOS)))
            .andExpect(jsonPath("$.[*].numVioladodis").value(hasItem(DEFAULT_NUM_VIOLADODIS)))
            .andExpect(jsonPath("$.[*].numVioladoperm").value(hasItem(DEFAULT_NUM_VIOLADOPERM)))
            .andExpect(jsonPath("$.[*].valDfp").value(hasItem(DEFAULT_VAL_DFP)));
    }

    @Test
    @Transactional
    void getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.saveAndFlush(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );

        // Get the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                get(ENTITY_API_URL_ID, onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId())
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.id").value(
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId().intValue()
                )
            )
            .andExpect(jsonPath("$.idPeriodicidade").value(DEFAULT_ID_PERIODICIDADE))
            .andExpect(jsonPath("$.dinReferencia").value(DEFAULT_DIN_REFERENCIA.toString()))
            .andExpect(jsonPath("$.numDesvioPermSobre").value(DEFAULT_NUM_DESVIO_PERM_SOBRE))
            .andExpect(jsonPath("$.numDesvioPermSub").value(DEFAULT_NUM_DESVIO_PERM_SUB))
            .andExpect(jsonPath("$.numDesvioDistSobre").value(DEFAULT_NUM_DESVIO_DIST_SOBRE))
            .andExpect(jsonPath("$.numDesvioDistSub").value(DEFAULT_NUM_DESVIO_DIST_SUB))
            .andExpect(jsonPath("$.numMinutos").value(DEFAULT_NUM_MINUTOS))
            .andExpect(jsonPath("$.numVioladodis").value(DEFAULT_NUM_VIOLADODIS))
            .andExpect(jsonPath("$.numVioladoperm").value(DEFAULT_NUM_VIOLADOPERM))
            .andExpect(jsonPath("$.valDfp").value(DEFAULT_VAL_DFP));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        // Get the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.saveAndFlush(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.save(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );

        // Update the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository
                .findById(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity are not directly saved in db
        em.detach(updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity);
        updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numDesvioPermSobre(UPDATED_NUM_DESVIO_PERM_SOBRE)
            .numDesvioPermSub(UPDATED_NUM_DESVIO_PERM_SUB)
            .numDesvioDistSobre(UPDATED_NUM_DESVIO_DIST_SOBRE)
            .numDesvioDistSub(UPDATED_NUM_DESVIO_DIST_SUB)
            .numMinutos(UPDATED_NUM_MINUTOS)
            .numVioladodis(UPDATED_NUM_VIOLADODIS)
            .numVioladoperm(UPDATED_NUM_VIOLADOPERM)
            .valDfp(UPDATED_VAL_DFP);

        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                put(
                    ENTITY_API_URL_ID,
                    updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
                )
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        om.writeValueAsBytes(
                            updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
                        )
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityToMatchAllProperties(
            updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
                > onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchList = Streamable.of(
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
                ).toList();
                OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity testOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearch =
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchList.get(
                        searchDatabaseSizeAfter - 1
                    );

                assertOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityAllPropertiesEquals(
                    testOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearch,
                    updatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        om.writeValueAsBytes(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)
                    )
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        om.writeValueAsBytes(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)
                    )
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente()
        throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        om.writeValueAsBytes(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)
                    )
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.saveAndFlush(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente using partial update
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            new OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity();
        partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
        );

        partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numDesvioDistSobre(UPDATED_NUM_DESVIO_DIST_SOBRE)
            .numDesvioDistSub(UPDATED_NUM_DESVIO_DIST_SUB)
            .numVioladodis(UPDATED_NUM_VIOLADODIS)
            .numVioladoperm(UPDATED_NUM_VIOLADOPERM);

        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(
                            partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
                        )
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity,
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            ),
            getPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.saveAndFlush(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente using partial update
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            new OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity();
        partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
        );

        partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numDesvioPermSobre(UPDATED_NUM_DESVIO_PERM_SOBRE)
            .numDesvioPermSub(UPDATED_NUM_DESVIO_PERM_SUB)
            .numDesvioDistSobre(UPDATED_NUM_DESVIO_DIST_SOBRE)
            .numDesvioDistSub(UPDATED_NUM_DESVIO_DIST_SUB)
            .numMinutos(UPDATED_NUM_MINUTOS)
            .numVioladodis(UPDATED_NUM_VIOLADODIS)
            .numVioladoperm(UPDATED_NUM_VIOLADOPERM)
            .valDfp(UPDATED_VAL_DFP);

        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(
                            partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
                        )
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity,
            getPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity(
                partialUpdatedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)
                    )
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)
                    )
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente()
        throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity)
                    )
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.saveAndFlush(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.save(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.save(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                delete(
                    ENTITY_API_URL_ID,
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
                )
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.saveAndFlush(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.save(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );

        // Search the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
        restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteMockMvc
            .perform(
                get(
                    ENTITY_SEARCH_API_URL +
                    "?query=id:" +
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
                )
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].numDesvioPermSobre").value(hasItem(DEFAULT_NUM_DESVIO_PERM_SOBRE)))
            .andExpect(jsonPath("$.[*].numDesvioPermSub").value(hasItem(DEFAULT_NUM_DESVIO_PERM_SUB)))
            .andExpect(jsonPath("$.[*].numDesvioDistSobre").value(hasItem(DEFAULT_NUM_DESVIO_DIST_SOBRE)))
            .andExpect(jsonPath("$.[*].numDesvioDistSub").value(hasItem(DEFAULT_NUM_DESVIO_DIST_SUB)))
            .andExpect(jsonPath("$.[*].numMinutos").value(hasItem(DEFAULT_NUM_MINUTOS)))
            .andExpect(jsonPath("$.[*].numVioladodis").value(hasItem(DEFAULT_NUM_VIOLADODIS)))
            .andExpect(jsonPath("$.[*].numVioladoperm").value(hasItem(DEFAULT_NUM_VIOLADOPERM)))
            .andExpect(jsonPath("$.[*].valDfp").value(hasItem(DEFAULT_VAL_DFP)));
    }

    protected long getRepositoryCount() {
        return onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.count();
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

    protected OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity getPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity(
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
    ) {
        return onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository
            .findById(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityToMatchAllProperties(
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity expectedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    ) {
        assertOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityAllPropertiesEquals(
            expectedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity,
            getPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity(
                expectedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            )
        );
    }

    protected void assertPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityToMatchUpdatableProperties(
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity expectedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    ) {
        assertOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity,
            getPersistedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity(
                expectedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            )
        );
    }
}
