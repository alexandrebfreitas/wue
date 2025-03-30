package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository;
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
 * Integration tests for the {@link OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResourceIT {

    private static final String DEFAULT_COD_INDICADOR = "AAAAAAAAAA";
    private static final String UPDATED_COD_INDICADOR = "BBBBBBBBBB";

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

    private static final Double DEFAULT_VAL_INDICADOR = 1D;
    private static final Double UPDATED_VAL_INDICADOR = 2D;

    private static final Double DEFAULT_NUM_PERTURBACOES = 1D;
    private static final Double UPDATED_NUM_PERTURBACOES = 2D;

    private static final Double DEFAULT_NUM_PERTURBACOESCORTECARGA = 1D;
    private static final Double UPDATED_NUM_PERTURBACOESCORTECARGA = 2D;

    private static final Double DEFAULT_NUM_PERTURBACOESCORTECARGA_0_A_50_MW = 1D;
    private static final Double UPDATED_NUM_PERTURBACOESCORTECARGA_0_A_50_MW = 2D;

    private static final Double DEFAULT_NUM_PERTURBACOESCORTECARGA_50_A_100_MW = 1D;
    private static final Double UPDATED_NUM_PERTURBACOESCORTECARGA_50_A_100_MW = 2D;

    private static final Double DEFAULT_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW = 1D;
    private static final Double UPDATED_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW = 2D;

    private static final String ENTITY_API_URL = "/api/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc;

    private OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity;

    private OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity createEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity()
            .codIndicador(DEFAULT_COD_INDICADOR)
            .dscAgregacao(DEFAULT_DSC_AGREGACAO)
            .codCaracteristica(DEFAULT_COD_CARACTERISTICA)
            .dscCaracteristica(DEFAULT_DSC_CARACTERISTICA)
            .idPeriodicidade(DEFAULT_ID_PERIODICIDADE)
            .dinReferencia(DEFAULT_DIN_REFERENCIA)
            .valIndicador(DEFAULT_VAL_INDICADOR)
            .numPerturbacoes(DEFAULT_NUM_PERTURBACOES)
            .numPerturbacoescortecarga(DEFAULT_NUM_PERTURBACOESCORTECARGA)
            .numPerturbacoescortecarga0a50mw(DEFAULT_NUM_PERTURBACOESCORTECARGA_0_A_50_MW)
            .numPerturbacoescortecarga50a100mw(DEFAULT_NUM_PERTURBACOESCORTECARGA_50_A_100_MW)
            .numPerturbacoescortecargaMaior100mw(DEFAULT_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity createUpdatedEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity()
            .codIndicador(UPDATED_COD_INDICADOR)
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valIndicador(UPDATED_VAL_INDICADOR)
            .numPerturbacoes(UPDATED_NUM_PERTURBACOES)
            .numPerturbacoescortecarga(UPDATED_NUM_PERTURBACOESCORTECARGA)
            .numPerturbacoescortecarga0a50mw(UPDATED_NUM_PERTURBACOESCORTECARGA_0_A_50_MW)
            .numPerturbacoescortecarga50a100mw(UPDATED_NUM_PERTURBACOESCORTECARGA_50_A_100_MW)
            .numPerturbacoescortecargaMaior100mw(UPDATED_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW);
    }

    @BeforeEach
    public void initTest() {
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity != null) {
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.delete(
                insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.delete(
                insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );
            insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
        var returnedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity = om.readValue(
            restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.class
        );

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityUpdatableFieldsEquals(
            returnedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity(
                returnedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            returnedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity;
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsWithExistingId() throws Exception {
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs with an existing ID
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );

        // Get all the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsList
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].codIndicador").value(hasItem(DEFAULT_COD_INDICADOR)))
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valIndicador").value(hasItem(DEFAULT_VAL_INDICADOR)))
            .andExpect(jsonPath("$.[*].numPerturbacoes").value(hasItem(DEFAULT_NUM_PERTURBACOES)))
            .andExpect(jsonPath("$.[*].numPerturbacoescortecarga").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA)))
            .andExpect(jsonPath("$.[*].numPerturbacoescortecarga0a50mw").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA_0_A_50_MW)))
            .andExpect(jsonPath("$.[*].numPerturbacoescortecarga50a100mw").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA_50_A_100_MW)))
            .andExpect(
                jsonPath("$.[*].numPerturbacoescortecargaMaior100mw").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW))
            );
    }

    @Test
    @Transactional
    void getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );

        // Get the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId().intValue()))
            .andExpect(jsonPath("$.codIndicador").value(DEFAULT_COD_INDICADOR))
            .andExpect(jsonPath("$.dscAgregacao").value(DEFAULT_DSC_AGREGACAO))
            .andExpect(jsonPath("$.codCaracteristica").value(DEFAULT_COD_CARACTERISTICA))
            .andExpect(jsonPath("$.dscCaracteristica").value(DEFAULT_DSC_CARACTERISTICA))
            .andExpect(jsonPath("$.idPeriodicidade").value(DEFAULT_ID_PERIODICIDADE))
            .andExpect(jsonPath("$.dinReferencia").value(DEFAULT_DIN_REFERENCIA.toString()))
            .andExpect(jsonPath("$.valIndicador").value(DEFAULT_VAL_INDICADOR))
            .andExpect(jsonPath("$.numPerturbacoes").value(DEFAULT_NUM_PERTURBACOES))
            .andExpect(jsonPath("$.numPerturbacoescortecarga").value(DEFAULT_NUM_PERTURBACOESCORTECARGA))
            .andExpect(jsonPath("$.numPerturbacoescortecarga0a50mw").value(DEFAULT_NUM_PERTURBACOESCORTECARGA_0_A_50_MW))
            .andExpect(jsonPath("$.numPerturbacoescortecarga50a100mw").value(DEFAULT_NUM_PERTURBACOESCORTECARGA_50_A_100_MW))
            .andExpect(jsonPath("$.numPerturbacoescortecargaMaior100mw").value(DEFAULT_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW));
    }

    @Test
    @Transactional
    void getNonExistingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        // Get the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );

        // Update the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository
                .findById(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity are not directly saved in db
        em.detach(updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity);
        updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            .codIndicador(UPDATED_COD_INDICADOR)
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valIndicador(UPDATED_VAL_INDICADOR)
            .numPerturbacoes(UPDATED_NUM_PERTURBACOES)
            .numPerturbacoescortecarga(UPDATED_NUM_PERTURBACOESCORTECARGA)
            .numPerturbacoescortecarga0a50mw(UPDATED_NUM_PERTURBACOESCORTECARGA_0_A_50_MW)
            .numPerturbacoescortecarga50a100mw(UPDATED_NUM_PERTURBACOESCORTECARGA_50_A_100_MW)
            .numPerturbacoescortecargaMaior100mw(UPDATED_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW);

        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityToMatchAllProperties(
            updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
                > onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchList = Streamable.of(
                    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
                ).toList();
                OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity testOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearch =
                    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityAllPropertiesEquals(
                    testOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearch,
                    updatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            .codIndicador(UPDATED_COD_INDICADOR)
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .numPerturbacoes(UPDATED_NUM_PERTURBACOES)
            .numPerturbacoescortecarga(UPDATED_NUM_PERTURBACOESCORTECARGA)
            .numPerturbacoescortecarga50a100mw(UPDATED_NUM_PERTURBACOESCORTECARGA_50_A_100_MW);

        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity,
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            ),
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            .codIndicador(UPDATED_COD_INDICADOR)
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valIndicador(UPDATED_VAL_INDICADOR)
            .numPerturbacoes(UPDATED_NUM_PERTURBACOES)
            .numPerturbacoescortecarga(UPDATED_NUM_PERTURBACOESCORTECARGA)
            .numPerturbacoescortecarga0a50mw(UPDATED_NUM_PERTURBACOESCORTECARGA_0_A_50_MW)
            .numPerturbacoescortecarga50a100mw(UPDATED_NUM_PERTURBACOESCORTECARGA_50_A_100_MW)
            .numPerturbacoescortecargaMaior100mw(UPDATED_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW);

        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityUpdatableFieldsEquals(
            partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );

        // Search the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
        restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsMockMvc
            .perform(
                get(ENTITY_SEARCH_API_URL + "?query=id:" + onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].codIndicador").value(hasItem(DEFAULT_COD_INDICADOR)))
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valIndicador").value(hasItem(DEFAULT_VAL_INDICADOR)))
            .andExpect(jsonPath("$.[*].numPerturbacoes").value(hasItem(DEFAULT_NUM_PERTURBACOES)))
            .andExpect(jsonPath("$.[*].numPerturbacoescortecarga").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA)))
            .andExpect(jsonPath("$.[*].numPerturbacoescortecarga0a50mw").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA_0_A_50_MW)))
            .andExpect(jsonPath("$.[*].numPerturbacoescortecarga50a100mw").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA_50_A_100_MW)))
            .andExpect(
                jsonPath("$.[*].numPerturbacoescortecargaMaior100mw").value(hasItem(DEFAULT_NUM_PERTURBACOESCORTECARGA_MAIOR_100_MW))
            );
    }

    protected long getRepositoryCount() {
        return onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.count();
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

    protected OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity getPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity(
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
    ) {
        return onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository
            .findById(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityToMatchAllProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityAllPropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity(
                expectedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            )
        );
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityToMatchUpdatableProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntityAllUpdatablePropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity(
                expectedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            )
        );
    }
}
