package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository;
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
 * Integration tests for the {@link OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResourceIT {

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

    private static final Double DEFAULT_VAL_SM_1 = 1D;
    private static final Double UPDATED_VAL_SM_1 = 2D;

    private static final Double DEFAULT_VAL_SM_2 = 1D;
    private static final Double UPDATED_VAL_SM_2 = 2D;

    private static final Double DEFAULT_VAL_SM_3 = 1D;
    private static final Double UPDATED_VAL_SM_3 = 2D;

    private static final Double DEFAULT_VAL_SM_4 = 1D;
    private static final Double UPDATED_VAL_SM_4 = 2D;

    private static final Double DEFAULT_VAL_SM_5 = 1D;
    private static final Double UPDATED_VAL_SM_5 = 2D;

    private static final String ENTITY_API_URL = "/api/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc;

    private OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity;

    private OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity createEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity()
            .dscAgregacao(DEFAULT_DSC_AGREGACAO)
            .codCaracteristica(DEFAULT_COD_CARACTERISTICA)
            .dscCaracteristica(DEFAULT_DSC_CARACTERISTICA)
            .idPeriodicidade(DEFAULT_ID_PERIODICIDADE)
            .dinReferencia(DEFAULT_DIN_REFERENCIA)
            .valSm1(DEFAULT_VAL_SM_1)
            .valSm2(DEFAULT_VAL_SM_2)
            .valSm3(DEFAULT_VAL_SM_3)
            .valSm4(DEFAULT_VAL_SM_4)
            .valSm5(DEFAULT_VAL_SM_5);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity createUpdatedEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity()
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valSm1(UPDATED_VAL_SM_1)
            .valSm2(UPDATED_VAL_SM_2)
            .valSm3(UPDATED_VAL_SM_3)
            .valSm4(UPDATED_VAL_SM_4)
            .valSm5(UPDATED_VAL_SM_5);
    }

    @BeforeEach
    public void initTest() {
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity != null) {
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.delete(
                insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.delete(
                insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );
            insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
        var returnedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity = om.readValue(
            restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.class
        );

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityUpdatableFieldsEquals(
            returnedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity(
                returnedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            returnedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity;
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesWithExistingId() throws Exception {
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes with an existing ID
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );

        // Get all the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesList
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(hasItem(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId().intValue()))
            )
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valSm1").value(hasItem(DEFAULT_VAL_SM_1)))
            .andExpect(jsonPath("$.[*].valSm2").value(hasItem(DEFAULT_VAL_SM_2)))
            .andExpect(jsonPath("$.[*].valSm3").value(hasItem(DEFAULT_VAL_SM_3)))
            .andExpect(jsonPath("$.[*].valSm4").value(hasItem(DEFAULT_VAL_SM_4)))
            .andExpect(jsonPath("$.[*].valSm5").value(hasItem(DEFAULT_VAL_SM_5)));
    }

    @Test
    @Transactional
    void getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );

        // Get the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId().intValue()))
            .andExpect(jsonPath("$.dscAgregacao").value(DEFAULT_DSC_AGREGACAO))
            .andExpect(jsonPath("$.codCaracteristica").value(DEFAULT_COD_CARACTERISTICA))
            .andExpect(jsonPath("$.dscCaracteristica").value(DEFAULT_DSC_CARACTERISTICA))
            .andExpect(jsonPath("$.idPeriodicidade").value(DEFAULT_ID_PERIODICIDADE))
            .andExpect(jsonPath("$.dinReferencia").value(DEFAULT_DIN_REFERENCIA.toString()))
            .andExpect(jsonPath("$.valSm1").value(DEFAULT_VAL_SM_1))
            .andExpect(jsonPath("$.valSm2").value(DEFAULT_VAL_SM_2))
            .andExpect(jsonPath("$.valSm3").value(DEFAULT_VAL_SM_3))
            .andExpect(jsonPath("$.valSm4").value(DEFAULT_VAL_SM_4))
            .andExpect(jsonPath("$.valSm5").value(DEFAULT_VAL_SM_5));
    }

    @Test
    @Transactional
    void getNonExistingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        // Get the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );

        // Update the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository
                .findById(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity are not directly saved in db
        em.detach(updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity);
        updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valSm1(UPDATED_VAL_SM_1)
            .valSm2(UPDATED_VAL_SM_2)
            .valSm3(UPDATED_VAL_SM_3)
            .valSm4(UPDATED_VAL_SM_4)
            .valSm5(UPDATED_VAL_SM_5);

        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityToMatchAllProperties(
            updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
                > onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchList = Streamable.of(
                    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
                ).toList();
                OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity testOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearch =
                    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAllPropertiesEquals(
                    testOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearch,
                    updatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valSm1(UPDATED_VAL_SM_1)
            .valSm3(UPDATED_VAL_SM_3)
            .valSm4(UPDATED_VAL_SM_4);

        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity,
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            ),
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valSm1(UPDATED_VAL_SM_1)
            .valSm2(UPDATED_VAL_SM_2)
            .valSm3(UPDATED_VAL_SM_3)
            .valSm4(UPDATED_VAL_SM_4)
            .valSm5(UPDATED_VAL_SM_5);

        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityUpdatableFieldsEquals(
            partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );

        // Search the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesMockMvc
            .perform(
                get(ENTITY_SEARCH_API_URL + "?query=id:" + onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(hasItem(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId().intValue()))
            )
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valSm1").value(hasItem(DEFAULT_VAL_SM_1)))
            .andExpect(jsonPath("$.[*].valSm2").value(hasItem(DEFAULT_VAL_SM_2)))
            .andExpect(jsonPath("$.[*].valSm3").value(hasItem(DEFAULT_VAL_SM_3)))
            .andExpect(jsonPath("$.[*].valSm4").value(hasItem(DEFAULT_VAL_SM_4)))
            .andExpect(jsonPath("$.[*].valSm5").value(hasItem(DEFAULT_VAL_SM_5)));
    }

    protected long getRepositoryCount() {
        return onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.count();
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

    protected OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity getPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
    ) {
        return onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository
            .findById(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityToMatchAllProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAllPropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity(
                expectedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            )
        );
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityToMatchUpdatableProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntityAllUpdatablePropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity(
                expectedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            )
        );
    }
}
