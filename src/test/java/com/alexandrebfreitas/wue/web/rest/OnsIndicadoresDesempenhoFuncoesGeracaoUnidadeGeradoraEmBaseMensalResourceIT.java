package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MODALIDADEOPERACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MODALIDADEOPERACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_AGENTEPROPRIETARIO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AGENTEPROPRIETARIO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_ID_TIPOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_USINA = "AAAAAAAAAA";
    private static final String UPDATED_ID_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final String DEFAULT_COD_EQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COD_EQUIPAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_UNIDADEGERADORA = "AAAAAAAAAA";
    private static final String UPDATED_NUM_UNIDADEGERADORA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_UNIDADEGERADORA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_UNIDADEGERADORA = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_POTENCIA = 1D;
    private static final Double UPDATED_VAL_POTENCIA = 2D;

    private static final Double DEFAULT_VAL_DISPF = 1D;
    private static final Double UPDATED_VAL_DISPF = 2D;

    private static final Double DEFAULT_VAL_INDISPPF = 1D;
    private static final Double UPDATED_VAL_INDISPPF = 2D;

    private static final Double DEFAULT_VAL_INDISPFF = 1D;
    private static final Double UPDATED_VAL_INDISPFF = 2D;

    private static final Double DEFAULT_VAL_DMDFF = 1D;
    private static final Double UPDATED_VAL_DMDFF = 2D;

    private static final Double DEFAULT_VAL_FDFF = 1D;
    private static final Double UPDATED_VAL_FDFF = 2D;

    private static final Double DEFAULT_VAL_TDFF = 1D;
    private static final Double UPDATED_VAL_TDFF = 2D;

    private static final String ENTITY_API_URL = "/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository;

    @Autowired
    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc;

    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity;

    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity createEntity() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .nomModalidadeoperacao(DEFAULT_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(DEFAULT_NOM_AGENTEPROPRIETARIO)
            .idTipousina(DEFAULT_ID_TIPOUSINA)
            .idUsina(DEFAULT_ID_USINA)
            .nomUsina(DEFAULT_NOM_USINA)
            .ceg(DEFAULT_CEG)
            .codEquipamento(DEFAULT_COD_EQUIPAMENTO)
            .numUnidadegeradora(DEFAULT_NUM_UNIDADEGERADORA)
            .nomUnidadegeradora(DEFAULT_NOM_UNIDADEGERADORA)
            .valPotencia(DEFAULT_VAL_POTENCIA)
            .valDispf(DEFAULT_VAL_DISPF)
            .valIndisppf(DEFAULT_VAL_INDISPPF)
            .valIndispff(DEFAULT_VAL_INDISPFF)
            .valDmdff(DEFAULT_VAL_DMDFF)
            .valFdff(DEFAULT_VAL_FDFF)
            .valTdff(DEFAULT_VAL_TDFF);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity createUpdatedEntity() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idUsina(UPDATED_ID_USINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .numUnidadegeradora(UPDATED_NUM_UNIDADEGERADORA)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .valPotencia(UPDATED_VAL_POTENCIA)
            .valDispf(UPDATED_VAL_DISPF)
            .valIndisppf(UPDATED_VAL_INDISPPF)
            .valIndispff(UPDATED_VAL_INDISPFF)
            .valDmdff(UPDATED_VAL_DMDFF)
            .valFdff(UPDATED_VAL_FDFF)
            .valTdff(UPDATED_VAL_TDFF);
    }

    @BeforeEach
    public void initTest() {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity != null) {
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.delete(
                insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.delete(
                insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );
            insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        // Create the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
        var returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity = om.readValue(
            restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.class
        );

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityUpdatableFieldsEquals(
            returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity(
                returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity;
    }

    @Test
    @Transactional
    void createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalWithExistingId() throws Exception {
        // Create the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal with an existing ID
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );

        // Get all the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalList
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].idUsina").value(hasItem(DEFAULT_ID_USINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].numUnidadegeradora").value(hasItem(DEFAULT_NUM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].nomUnidadegeradora").value(hasItem(DEFAULT_NOM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].valPotencia").value(hasItem(DEFAULT_VAL_POTENCIA)))
            .andExpect(jsonPath("$.[*].valDispf").value(hasItem(DEFAULT_VAL_DISPF)))
            .andExpect(jsonPath("$.[*].valIndisppf").value(hasItem(DEFAULT_VAL_INDISPPF)))
            .andExpect(jsonPath("$.[*].valIndispff").value(hasItem(DEFAULT_VAL_INDISPFF)))
            .andExpect(jsonPath("$.[*].valDmdff").value(hasItem(DEFAULT_VAL_DMDFF)))
            .andExpect(jsonPath("$.[*].valFdff").value(hasItem(DEFAULT_VAL_FDFF)))
            .andExpect(jsonPath("$.[*].valTdff").value(hasItem(DEFAULT_VAL_TDFF)));
    }

    @Test
    @Transactional
    void getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );

        // Get the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.nomModalidadeoperacao").value(DEFAULT_NOM_MODALIDADEOPERACAO))
            .andExpect(jsonPath("$.nomAgenteproprietario").value(DEFAULT_NOM_AGENTEPROPRIETARIO))
            .andExpect(jsonPath("$.idTipousina").value(DEFAULT_ID_TIPOUSINA))
            .andExpect(jsonPath("$.idUsina").value(DEFAULT_ID_USINA))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.codEquipamento").value(DEFAULT_COD_EQUIPAMENTO))
            .andExpect(jsonPath("$.numUnidadegeradora").value(DEFAULT_NUM_UNIDADEGERADORA))
            .andExpect(jsonPath("$.nomUnidadegeradora").value(DEFAULT_NOM_UNIDADEGERADORA))
            .andExpect(jsonPath("$.valPotencia").value(DEFAULT_VAL_POTENCIA))
            .andExpect(jsonPath("$.valDispf").value(DEFAULT_VAL_DISPF))
            .andExpect(jsonPath("$.valIndisppf").value(DEFAULT_VAL_INDISPPF))
            .andExpect(jsonPath("$.valIndispff").value(DEFAULT_VAL_INDISPFF))
            .andExpect(jsonPath("$.valDmdff").value(DEFAULT_VAL_DMDFF))
            .andExpect(jsonPath("$.valFdff").value(DEFAULT_VAL_FDFF))
            .andExpect(jsonPath("$.valTdff").value(DEFAULT_VAL_TDFF));
    }

    @Test
    @Transactional
    void getNonExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        // Get the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );

        // Update the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository
                .findById(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity are not directly saved in db
        em.detach(updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity);
        updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idUsina(UPDATED_ID_USINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .numUnidadegeradora(UPDATED_NUM_UNIDADEGERADORA)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .valPotencia(UPDATED_VAL_POTENCIA)
            .valDispf(UPDATED_VAL_DISPF)
            .valIndisppf(UPDATED_VAL_INDISPPF)
            .valIndispff(UPDATED_VAL_INDISPFF)
            .valDmdff(UPDATED_VAL_DMDFF)
            .valFdff(UPDATED_VAL_FDFF)
            .valTdff(UPDATED_VAL_TDFF);

        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityToMatchAllProperties(
            updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
                > onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchList = Streamable.of(
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
                ).toList();
                OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity testOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearch =
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityAllPropertiesEquals(
                    testOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearch,
                    updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal using partial update
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity();
        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId()
        );

        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .idUsina(UPDATED_ID_USINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .valIndisppf(UPDATED_VAL_INDISPPF)
            .valTdff(UPDATED_VAL_TDFF);

        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity,
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            ),
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal using partial update
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity();
        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId()
        );

        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idUsina(UPDATED_ID_USINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .numUnidadegeradora(UPDATED_NUM_UNIDADEGERADORA)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .valPotencia(UPDATED_VAL_POTENCIA)
            .valDispf(UPDATED_VAL_DISPF)
            .valIndisppf(UPDATED_VAL_INDISPPF)
            .valIndispff(UPDATED_VAL_INDISPFF)
            .valDmdff(UPDATED_VAL_DMDFF)
            .valFdff(UPDATED_VAL_FDFF)
            .valTdff(UPDATED_VAL_TDFF);

        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityUpdatableFieldsEquals(
            partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity(
                partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );

        // Search the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalMockMvc
            .perform(
                get(ENTITY_SEARCH_API_URL + "?query=id:" + onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].idUsina").value(hasItem(DEFAULT_ID_USINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].numUnidadegeradora").value(hasItem(DEFAULT_NUM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].nomUnidadegeradora").value(hasItem(DEFAULT_NOM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].valPotencia").value(hasItem(DEFAULT_VAL_POTENCIA)))
            .andExpect(jsonPath("$.[*].valDispf").value(hasItem(DEFAULT_VAL_DISPF)))
            .andExpect(jsonPath("$.[*].valIndisppf").value(hasItem(DEFAULT_VAL_INDISPPF)))
            .andExpect(jsonPath("$.[*].valIndispff").value(hasItem(DEFAULT_VAL_INDISPFF)))
            .andExpect(jsonPath("$.[*].valDmdff").value(hasItem(DEFAULT_VAL_DMDFF)))
            .andExpect(jsonPath("$.[*].valFdff").value(hasItem(DEFAULT_VAL_FDFF)))
            .andExpect(jsonPath("$.[*].valTdff").value(hasItem(DEFAULT_VAL_TDFF)));
    }

    protected long getRepositoryCount() {
        return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.count();
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

    protected OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
    ) {
        return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository
            .findById(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityToMatchAllProperties(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    ) {
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityAllPropertiesEquals(
            expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity(
                expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            )
        );
    }

    protected void assertPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityToMatchUpdatableProperties(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    ) {
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntityAllUpdatablePropertiesEquals(
            expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity(
                expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            )
        );
    }
}
