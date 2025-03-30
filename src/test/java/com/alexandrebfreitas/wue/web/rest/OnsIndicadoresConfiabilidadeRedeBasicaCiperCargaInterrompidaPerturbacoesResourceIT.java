package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository;
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
 * Integration tests for the {@link OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResourceIT {

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

    private static final Double DEFAULT_VAL_CIPER_1 = 1D;
    private static final Double UPDATED_VAL_CIPER_1 = 2D;

    private static final Double DEFAULT_VAL_CIPER_2 = 1D;
    private static final Double UPDATED_VAL_CIPER_2 = 2D;

    private static final Double DEFAULT_VAL_CIPER_3 = 1D;
    private static final Double UPDATED_VAL_CIPER_3 = 2D;

    private static final Double DEFAULT_VAL_CIPER_4 = 1D;
    private static final Double UPDATED_VAL_CIPER_4 = 2D;

    private static final Double DEFAULT_VAL_CIPER_5 = 1D;
    private static final Double UPDATED_VAL_CIPER_5 = 2D;

    private static final String ENTITY_API_URL = "/api/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository;

    @Autowired
    private OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc;

    private OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity;

    private OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity createEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity()
            .dscAgregacao(DEFAULT_DSC_AGREGACAO)
            .codCaracteristica(DEFAULT_COD_CARACTERISTICA)
            .dscCaracteristica(DEFAULT_DSC_CARACTERISTICA)
            .idPeriodicidade(DEFAULT_ID_PERIODICIDADE)
            .dinReferencia(DEFAULT_DIN_REFERENCIA)
            .valCiper1(DEFAULT_VAL_CIPER_1)
            .valCiper2(DEFAULT_VAL_CIPER_2)
            .valCiper3(DEFAULT_VAL_CIPER_3)
            .valCiper4(DEFAULT_VAL_CIPER_4)
            .valCiper5(DEFAULT_VAL_CIPER_5);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity createUpdatedEntity() {
        return new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity()
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valCiper1(UPDATED_VAL_CIPER_1)
            .valCiper2(UPDATED_VAL_CIPER_2)
            .valCiper3(UPDATED_VAL_CIPER_3)
            .valCiper4(UPDATED_VAL_CIPER_4)
            .valCiper5(UPDATED_VAL_CIPER_5);
    }

    @BeforeEach
    public void initTest() {
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity != null) {
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.delete(
                insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.delete(
                insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );
            insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
        var returnedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity = om.readValue(
            restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.class
        );

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityUpdatableFieldsEquals(
            returnedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity(
                returnedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            returnedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity;
    }

    @Test
    @Transactional
    void createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesWithExistingId() throws Exception {
        // Create the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes with an existing ID
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );

        // Get all the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesList
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valCiper1").value(hasItem(DEFAULT_VAL_CIPER_1)))
            .andExpect(jsonPath("$.[*].valCiper2").value(hasItem(DEFAULT_VAL_CIPER_2)))
            .andExpect(jsonPath("$.[*].valCiper3").value(hasItem(DEFAULT_VAL_CIPER_3)))
            .andExpect(jsonPath("$.[*].valCiper4").value(hasItem(DEFAULT_VAL_CIPER_4)))
            .andExpect(jsonPath("$.[*].valCiper5").value(hasItem(DEFAULT_VAL_CIPER_5)));
    }

    @Test
    @Transactional
    void getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );

        // Get the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.id").value(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId().intValue())
            )
            .andExpect(jsonPath("$.dscAgregacao").value(DEFAULT_DSC_AGREGACAO))
            .andExpect(jsonPath("$.codCaracteristica").value(DEFAULT_COD_CARACTERISTICA))
            .andExpect(jsonPath("$.dscCaracteristica").value(DEFAULT_DSC_CARACTERISTICA))
            .andExpect(jsonPath("$.idPeriodicidade").value(DEFAULT_ID_PERIODICIDADE))
            .andExpect(jsonPath("$.dinReferencia").value(DEFAULT_DIN_REFERENCIA.toString()))
            .andExpect(jsonPath("$.valCiper1").value(DEFAULT_VAL_CIPER_1))
            .andExpect(jsonPath("$.valCiper2").value(DEFAULT_VAL_CIPER_2))
            .andExpect(jsonPath("$.valCiper3").value(DEFAULT_VAL_CIPER_3))
            .andExpect(jsonPath("$.valCiper4").value(DEFAULT_VAL_CIPER_4))
            .andExpect(jsonPath("$.valCiper5").value(DEFAULT_VAL_CIPER_5));
    }

    @Test
    @Transactional
    void getNonExistingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        // Get the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );

        // Update the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository
                .findById(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity are not directly saved in db
        em.detach(updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity);
        updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valCiper1(UPDATED_VAL_CIPER_1)
            .valCiper2(UPDATED_VAL_CIPER_2)
            .valCiper3(UPDATED_VAL_CIPER_3)
            .valCiper4(UPDATED_VAL_CIPER_4)
            .valCiper5(UPDATED_VAL_CIPER_5);

        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityToMatchAllProperties(
            updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
                > onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchList = Streamable.of(
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
                ).toList();
                OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity testOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearch =
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityAllPropertiesEquals(
                    testOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearch,
                    updatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valCiper2(UPDATED_VAL_CIPER_2)
            .valCiper3(UPDATED_VAL_CIPER_3)
            .valCiper4(UPDATED_VAL_CIPER_4);

        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity)
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity,
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            ),
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes using partial update
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            new OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity();
        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId()
        );

        partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .codCaracteristica(UPDATED_COD_CARACTERISTICA)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .valCiper1(UPDATED_VAL_CIPER_1)
            .valCiper2(UPDATED_VAL_CIPER_2)
            .valCiper3(UPDATED_VAL_CIPER_3)
            .valCiper4(UPDATED_VAL_CIPER_4)
            .valCiper5(UPDATED_VAL_CIPER_5);

        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity)
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityUpdatableFieldsEquals(
            partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity(
                partialUpdatedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.saveAndFlush(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );

        // Search the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
        restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesMockMvc
            .perform(
                get(
                    ENTITY_SEARCH_API_URL +
                    "?query=id:" +
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId()
                )
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].codCaracteristica").value(hasItem(DEFAULT_COD_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valCiper1").value(hasItem(DEFAULT_VAL_CIPER_1)))
            .andExpect(jsonPath("$.[*].valCiper2").value(hasItem(DEFAULT_VAL_CIPER_2)))
            .andExpect(jsonPath("$.[*].valCiper3").value(hasItem(DEFAULT_VAL_CIPER_3)))
            .andExpect(jsonPath("$.[*].valCiper4").value(hasItem(DEFAULT_VAL_CIPER_4)))
            .andExpect(jsonPath("$.[*].valCiper5").value(hasItem(DEFAULT_VAL_CIPER_5)));
    }

    protected long getRepositoryCount() {
        return onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.count();
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

    protected OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity getPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity(
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
    ) {
        return onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository
            .findById(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityToMatchAllProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityAllPropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity(
                expectedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            )
        );
    }

    protected void assertPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityToMatchUpdatableProperties(
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity expectedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    ) {
        assertOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntityAllUpdatablePropertiesEquals(
            expectedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity,
            getPersistedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity(
                expectedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            )
        );
    }
}
