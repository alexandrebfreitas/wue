package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository;
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
 * Integration tests for the {@link OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResourceIT {

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

    private static final Integer DEFAULT_DIN_ANO = 1;
    private static final Integer UPDATED_DIN_ANO = 2;

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

    private static final String ENTITY_API_URL = "/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository;

    @Autowired
    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc;

    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity;

    private OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity createEntity() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity()
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
            .dinAno(DEFAULT_DIN_ANO)
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
    public static OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity createUpdatedEntity() {
        return new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity()
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
            .dinAno(UPDATED_DIN_ANO)
            .valDispf(UPDATED_VAL_DISPF)
            .valIndisppf(UPDATED_VAL_INDISPPF)
            .valIndispff(UPDATED_VAL_INDISPFF)
            .valDmdff(UPDATED_VAL_DMDFF)
            .valFdff(UPDATED_VAL_FDFF)
            .valTdff(UPDATED_VAL_TDFF);
    }

    @BeforeEach
    public void initTest() {
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity != null) {
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.delete(
                insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.delete(
                insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );
            insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        // Create the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
        var returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity = om.readValue(
            restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.class
        );

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityUpdatableFieldsEquals(
            returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity(
                returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            returnedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity;
    }

    @Test
    @Transactional
    void createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualWithExistingId() throws Exception {
        // Create the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual with an existing ID
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );

        // Get all the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualList
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId().intValue())
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
            .andExpect(jsonPath("$.[*].dinAno").value(hasItem(DEFAULT_DIN_ANO)))
            .andExpect(jsonPath("$.[*].valDispf").value(hasItem(DEFAULT_VAL_DISPF)))
            .andExpect(jsonPath("$.[*].valIndisppf").value(hasItem(DEFAULT_VAL_INDISPPF)))
            .andExpect(jsonPath("$.[*].valIndispff").value(hasItem(DEFAULT_VAL_INDISPFF)))
            .andExpect(jsonPath("$.[*].valDmdff").value(hasItem(DEFAULT_VAL_DMDFF)))
            .andExpect(jsonPath("$.[*].valFdff").value(hasItem(DEFAULT_VAL_FDFF)))
            .andExpect(jsonPath("$.[*].valTdff").value(hasItem(DEFAULT_VAL_TDFF)));
    }

    @Test
    @Transactional
    void getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );

        // Get the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId().intValue()))
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
            .andExpect(jsonPath("$.dinAno").value(DEFAULT_DIN_ANO))
            .andExpect(jsonPath("$.valDispf").value(DEFAULT_VAL_DISPF))
            .andExpect(jsonPath("$.valIndisppf").value(DEFAULT_VAL_INDISPPF))
            .andExpect(jsonPath("$.valIndispff").value(DEFAULT_VAL_INDISPFF))
            .andExpect(jsonPath("$.valDmdff").value(DEFAULT_VAL_DMDFF))
            .andExpect(jsonPath("$.valFdff").value(DEFAULT_VAL_FDFF))
            .andExpect(jsonPath("$.valTdff").value(DEFAULT_VAL_TDFF));
    }

    @Test
    @Transactional
    void getNonExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        // Get the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );

        // Update the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository
                .findById(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity are not directly saved in db
        em.detach(updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity);
        updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
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
            .dinAno(UPDATED_DIN_ANO)
            .valDispf(UPDATED_VAL_DISPF)
            .valIndisppf(UPDATED_VAL_INDISPPF)
            .valIndispff(UPDATED_VAL_INDISPFF)
            .valDmdff(UPDATED_VAL_DMDFF)
            .valFdff(UPDATED_VAL_FDFF)
            .valTdff(UPDATED_VAL_TDFF);

        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityToMatchAllProperties(
            updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
                > onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchList = Streamable.of(
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
                ).toList();
                OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity testOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearch =
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityAllPropertiesEquals(
                    testOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearch,
                    updatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual using partial update
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity();
        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId()
        );

        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .ceg(UPDATED_CEG)
            .numUnidadegeradora(UPDATED_NUM_UNIDADEGERADORA)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .dinAno(UPDATED_DIN_ANO)
            .valDispf(UPDATED_VAL_DISPF)
            .valDmdff(UPDATED_VAL_DMDFF);

        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity,
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            ),
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual using partial update
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            new OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity();
        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId()
        );

        partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
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
            .dinAno(UPDATED_DIN_ANO)
            .valDispf(UPDATED_VAL_DISPF)
            .valIndisppf(UPDATED_VAL_INDISPPF)
            .valIndispff(UPDATED_VAL_INDISPFF)
            .valDmdff(UPDATED_VAL_DMDFF)
            .valFdff(UPDATED_VAL_FDFF)
            .valTdff(UPDATED_VAL_TDFF);

        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityUpdatableFieldsEquals(
            partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity(
                partialUpdatedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual() throws Exception {
        // Initialize the database
        insertedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.saveAndFlush(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.save(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );

        // Search the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
        restOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualMockMvc
            .perform(
                get(ENTITY_SEARCH_API_URL + "?query=id:" + onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId().intValue())
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
            .andExpect(jsonPath("$.[*].dinAno").value(hasItem(DEFAULT_DIN_ANO)))
            .andExpect(jsonPath("$.[*].valDispf").value(hasItem(DEFAULT_VAL_DISPF)))
            .andExpect(jsonPath("$.[*].valIndisppf").value(hasItem(DEFAULT_VAL_INDISPPF)))
            .andExpect(jsonPath("$.[*].valIndispff").value(hasItem(DEFAULT_VAL_INDISPFF)))
            .andExpect(jsonPath("$.[*].valDmdff").value(hasItem(DEFAULT_VAL_DMDFF)))
            .andExpect(jsonPath("$.[*].valFdff").value(hasItem(DEFAULT_VAL_FDFF)))
            .andExpect(jsonPath("$.[*].valTdff").value(hasItem(DEFAULT_VAL_TDFF)));
    }

    protected long getRepositoryCount() {
        return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.count();
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

    protected OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
    ) {
        return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository
            .findById(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityToMatchAllProperties(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    ) {
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityAllPropertiesEquals(
            expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity(
                expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            )
        );
    }

    protected void assertPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityToMatchUpdatableProperties(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    ) {
        assertOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntityAllUpdatablePropertiesEquals(
            expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity,
            getPersistedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity(
                expectedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            )
        );
    }
}
