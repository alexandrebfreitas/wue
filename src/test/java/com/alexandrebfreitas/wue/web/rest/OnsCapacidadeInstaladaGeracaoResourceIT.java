package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntity;
import com.alexandrebfreitas.wue.repository.OnsCapacidadeInstaladaGeracaoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCapacidadeInstaladaGeracaoSearchRepository;
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
 * Integration tests for the {@link OnsCapacidadeInstaladaGeracaoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCapacidadeInstaladaGeracaoResourceIT {

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

    private static final String DEFAULT_NOM_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_UNIDADEGERADORA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_UNIDADEGERADORA = "BBBBBBBBBB";

    private static final String DEFAULT_COD_EQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COD_EQUIPAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_UNIDADEGERADORA = "AAAAAAAAAA";
    private static final String UPDATED_NUM_UNIDADEGERADORA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_COMBUSTIVEL = "AAAAAAAAAA";
    private static final String UPDATED_NOM_COMBUSTIVEL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_ENTRADATESTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_ENTRADATESTE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_ENTRADAOPERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_ENTRADAOPERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_DESATIVACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_DESATIVACAO = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_POTENCIAEFETIVA = 1D;
    private static final Double UPDATED_VAL_POTENCIAEFETIVA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-capacidade-instalada-geracaos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-capacidade-instalada-geracaos/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCapacidadeInstaladaGeracaoRepository onsCapacidadeInstaladaGeracaoRepository;

    @Autowired
    private OnsCapacidadeInstaladaGeracaoSearchRepository onsCapacidadeInstaladaGeracaoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCapacidadeInstaladaGeracaoMockMvc;

    private OnsCapacidadeInstaladaGeracaoEntity onsCapacidadeInstaladaGeracaoEntity;

    private OnsCapacidadeInstaladaGeracaoEntity insertedOnsCapacidadeInstaladaGeracaoEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCapacidadeInstaladaGeracaoEntity createEntity() {
        return new OnsCapacidadeInstaladaGeracaoEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .nomModalidadeoperacao(DEFAULT_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(DEFAULT_NOM_AGENTEPROPRIETARIO)
            .nomTipousina(DEFAULT_NOM_TIPOUSINA)
            .nomUsina(DEFAULT_NOM_USINA)
            .ceg(DEFAULT_CEG)
            .nomUnidadegeradora(DEFAULT_NOM_UNIDADEGERADORA)
            .codEquipamento(DEFAULT_COD_EQUIPAMENTO)
            .numUnidadegeradora(DEFAULT_NUM_UNIDADEGERADORA)
            .nomCombustivel(DEFAULT_NOM_COMBUSTIVEL)
            .datEntradateste(DEFAULT_DAT_ENTRADATESTE)
            .datEntradaoperacao(DEFAULT_DAT_ENTRADAOPERACAO)
            .datDesativacao(DEFAULT_DAT_DESATIVACAO)
            .valPotenciaefetiva(DEFAULT_VAL_POTENCIAEFETIVA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCapacidadeInstaladaGeracaoEntity createUpdatedEntity() {
        return new OnsCapacidadeInstaladaGeracaoEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .numUnidadegeradora(UPDATED_NUM_UNIDADEGERADORA)
            .nomCombustivel(UPDATED_NOM_COMBUSTIVEL)
            .datEntradateste(UPDATED_DAT_ENTRADATESTE)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .valPotenciaefetiva(UPDATED_VAL_POTENCIAEFETIVA);
    }

    @BeforeEach
    public void initTest() {
        onsCapacidadeInstaladaGeracaoEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCapacidadeInstaladaGeracaoEntity != null) {
            onsCapacidadeInstaladaGeracaoRepository.delete(insertedOnsCapacidadeInstaladaGeracaoEntity);
            onsCapacidadeInstaladaGeracaoSearchRepository.delete(insertedOnsCapacidadeInstaladaGeracaoEntity);
            insertedOnsCapacidadeInstaladaGeracaoEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCapacidadeInstaladaGeracao() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        // Create the OnsCapacidadeInstaladaGeracao
        var returnedOnsCapacidadeInstaladaGeracaoEntity = om.readValue(
            restOnsCapacidadeInstaladaGeracaoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCapacidadeInstaladaGeracaoEntity.class
        );

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCapacidadeInstaladaGeracaoEntityUpdatableFieldsEquals(
            returnedOnsCapacidadeInstaladaGeracaoEntity,
            getPersistedOnsCapacidadeInstaladaGeracaoEntity(returnedOnsCapacidadeInstaladaGeracaoEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCapacidadeInstaladaGeracaoEntity = returnedOnsCapacidadeInstaladaGeracaoEntity;
    }

    @Test
    @Transactional
    void createOnsCapacidadeInstaladaGeracaoWithExistingId() throws Exception {
        // Create the OnsCapacidadeInstaladaGeracao with an existing ID
        onsCapacidadeInstaladaGeracaoEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCapacidadeInstaladaGeracaos() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.saveAndFlush(
            onsCapacidadeInstaladaGeracaoEntity
        );

        // Get all the onsCapacidadeInstaladaGeracaoList
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCapacidadeInstaladaGeracaoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomTipousina").value(hasItem(DEFAULT_NOM_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].nomUnidadegeradora").value(hasItem(DEFAULT_NOM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].numUnidadegeradora").value(hasItem(DEFAULT_NUM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].nomCombustivel").value(hasItem(DEFAULT_NOM_COMBUSTIVEL)))
            .andExpect(jsonPath("$.[*].datEntradateste").value(hasItem(DEFAULT_DAT_ENTRADATESTE.toString())))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].valPotenciaefetiva").value(hasItem(DEFAULT_VAL_POTENCIAEFETIVA)));
    }

    @Test
    @Transactional
    void getOnsCapacidadeInstaladaGeracao() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.saveAndFlush(
            onsCapacidadeInstaladaGeracaoEntity
        );

        // Get the onsCapacidadeInstaladaGeracao
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCapacidadeInstaladaGeracaoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCapacidadeInstaladaGeracaoEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.nomModalidadeoperacao").value(DEFAULT_NOM_MODALIDADEOPERACAO))
            .andExpect(jsonPath("$.nomAgenteproprietario").value(DEFAULT_NOM_AGENTEPROPRIETARIO))
            .andExpect(jsonPath("$.nomTipousina").value(DEFAULT_NOM_TIPOUSINA))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.nomUnidadegeradora").value(DEFAULT_NOM_UNIDADEGERADORA))
            .andExpect(jsonPath("$.codEquipamento").value(DEFAULT_COD_EQUIPAMENTO))
            .andExpect(jsonPath("$.numUnidadegeradora").value(DEFAULT_NUM_UNIDADEGERADORA))
            .andExpect(jsonPath("$.nomCombustivel").value(DEFAULT_NOM_COMBUSTIVEL))
            .andExpect(jsonPath("$.datEntradateste").value(DEFAULT_DAT_ENTRADATESTE.toString()))
            .andExpect(jsonPath("$.datEntradaoperacao").value(DEFAULT_DAT_ENTRADAOPERACAO.toString()))
            .andExpect(jsonPath("$.datDesativacao").value(DEFAULT_DAT_DESATIVACAO.toString()))
            .andExpect(jsonPath("$.valPotenciaefetiva").value(DEFAULT_VAL_POTENCIAEFETIVA));
    }

    @Test
    @Transactional
    void getNonExistingOnsCapacidadeInstaladaGeracao() throws Exception {
        // Get the onsCapacidadeInstaladaGeracao
        restOnsCapacidadeInstaladaGeracaoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCapacidadeInstaladaGeracao() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.saveAndFlush(
            onsCapacidadeInstaladaGeracaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCapacidadeInstaladaGeracaoSearchRepository.save(onsCapacidadeInstaladaGeracaoEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());

        // Update the onsCapacidadeInstaladaGeracao
        OnsCapacidadeInstaladaGeracaoEntity updatedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository
            .findById(onsCapacidadeInstaladaGeracaoEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCapacidadeInstaladaGeracaoEntity are not directly saved in db
        em.detach(updatedOnsCapacidadeInstaladaGeracaoEntity);
        updatedOnsCapacidadeInstaladaGeracaoEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .numUnidadegeradora(UPDATED_NUM_UNIDADEGERADORA)
            .nomCombustivel(UPDATED_NOM_COMBUSTIVEL)
            .datEntradateste(UPDATED_DAT_ENTRADATESTE)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .valPotenciaefetiva(UPDATED_VAL_POTENCIAEFETIVA);

        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCapacidadeInstaladaGeracaoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCapacidadeInstaladaGeracaoEntityToMatchAllProperties(updatedOnsCapacidadeInstaladaGeracaoEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCapacidadeInstaladaGeracaoEntity> onsCapacidadeInstaladaGeracaoSearchList = Streamable.of(
                    onsCapacidadeInstaladaGeracaoSearchRepository.findAll()
                ).toList();
                OnsCapacidadeInstaladaGeracaoEntity testOnsCapacidadeInstaladaGeracaoSearch = onsCapacidadeInstaladaGeracaoSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsCapacidadeInstaladaGeracaoEntityAllPropertiesEquals(
                    testOnsCapacidadeInstaladaGeracaoSearch,
                    updatedOnsCapacidadeInstaladaGeracaoEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCapacidadeInstaladaGeracao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        onsCapacidadeInstaladaGeracaoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCapacidadeInstaladaGeracaoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCapacidadeInstaladaGeracao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        onsCapacidadeInstaladaGeracaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCapacidadeInstaladaGeracao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        onsCapacidadeInstaladaGeracaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCapacidadeInstaladaGeracaoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.saveAndFlush(
            onsCapacidadeInstaladaGeracaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCapacidadeInstaladaGeracao using partial update
        OnsCapacidadeInstaladaGeracaoEntity partialUpdatedOnsCapacidadeInstaladaGeracaoEntity = new OnsCapacidadeInstaladaGeracaoEntity();
        partialUpdatedOnsCapacidadeInstaladaGeracaoEntity.setId(onsCapacidadeInstaladaGeracaoEntity.getId());

        partialUpdatedOnsCapacidadeInstaladaGeracaoEntity
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .datDesativacao(UPDATED_DAT_DESATIVACAO);

        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCapacidadeInstaladaGeracaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCapacidadeInstaladaGeracao in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCapacidadeInstaladaGeracaoEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCapacidadeInstaladaGeracaoEntity, onsCapacidadeInstaladaGeracaoEntity),
            getPersistedOnsCapacidadeInstaladaGeracaoEntity(onsCapacidadeInstaladaGeracaoEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCapacidadeInstaladaGeracaoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.saveAndFlush(
            onsCapacidadeInstaladaGeracaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCapacidadeInstaladaGeracao using partial update
        OnsCapacidadeInstaladaGeracaoEntity partialUpdatedOnsCapacidadeInstaladaGeracaoEntity = new OnsCapacidadeInstaladaGeracaoEntity();
        partialUpdatedOnsCapacidadeInstaladaGeracaoEntity.setId(onsCapacidadeInstaladaGeracaoEntity.getId());

        partialUpdatedOnsCapacidadeInstaladaGeracaoEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .nomUnidadegeradora(UPDATED_NOM_UNIDADEGERADORA)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .numUnidadegeradora(UPDATED_NUM_UNIDADEGERADORA)
            .nomCombustivel(UPDATED_NOM_COMBUSTIVEL)
            .datEntradateste(UPDATED_DAT_ENTRADATESTE)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .valPotenciaefetiva(UPDATED_VAL_POTENCIAEFETIVA);

        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCapacidadeInstaladaGeracaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCapacidadeInstaladaGeracao in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCapacidadeInstaladaGeracaoEntityUpdatableFieldsEquals(
            partialUpdatedOnsCapacidadeInstaladaGeracaoEntity,
            getPersistedOnsCapacidadeInstaladaGeracaoEntity(partialUpdatedOnsCapacidadeInstaladaGeracaoEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCapacidadeInstaladaGeracao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        onsCapacidadeInstaladaGeracaoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCapacidadeInstaladaGeracaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCapacidadeInstaladaGeracao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        onsCapacidadeInstaladaGeracaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCapacidadeInstaladaGeracao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        onsCapacidadeInstaladaGeracaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCapacidadeInstaladaGeracaoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCapacidadeInstaladaGeracao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCapacidadeInstaladaGeracao() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.saveAndFlush(
            onsCapacidadeInstaladaGeracaoEntity
        );
        onsCapacidadeInstaladaGeracaoRepository.save(onsCapacidadeInstaladaGeracaoEntity);
        onsCapacidadeInstaladaGeracaoSearchRepository.save(onsCapacidadeInstaladaGeracaoEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCapacidadeInstaladaGeracao
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCapacidadeInstaladaGeracaoEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeInstaladaGeracaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCapacidadeInstaladaGeracao() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.saveAndFlush(
            onsCapacidadeInstaladaGeracaoEntity
        );
        onsCapacidadeInstaladaGeracaoSearchRepository.save(onsCapacidadeInstaladaGeracaoEntity);

        // Search the onsCapacidadeInstaladaGeracao
        restOnsCapacidadeInstaladaGeracaoMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCapacidadeInstaladaGeracaoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCapacidadeInstaladaGeracaoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomTipousina").value(hasItem(DEFAULT_NOM_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].nomUnidadegeradora").value(hasItem(DEFAULT_NOM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].numUnidadegeradora").value(hasItem(DEFAULT_NUM_UNIDADEGERADORA)))
            .andExpect(jsonPath("$.[*].nomCombustivel").value(hasItem(DEFAULT_NOM_COMBUSTIVEL)))
            .andExpect(jsonPath("$.[*].datEntradateste").value(hasItem(DEFAULT_DAT_ENTRADATESTE.toString())))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].valPotenciaefetiva").value(hasItem(DEFAULT_VAL_POTENCIAEFETIVA)));
    }

    protected long getRepositoryCount() {
        return onsCapacidadeInstaladaGeracaoRepository.count();
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

    protected OnsCapacidadeInstaladaGeracaoEntity getPersistedOnsCapacidadeInstaladaGeracaoEntity(
        OnsCapacidadeInstaladaGeracaoEntity onsCapacidadeInstaladaGeracao
    ) {
        return onsCapacidadeInstaladaGeracaoRepository.findById(onsCapacidadeInstaladaGeracao.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCapacidadeInstaladaGeracaoEntityToMatchAllProperties(
        OnsCapacidadeInstaladaGeracaoEntity expectedOnsCapacidadeInstaladaGeracaoEntity
    ) {
        assertOnsCapacidadeInstaladaGeracaoEntityAllPropertiesEquals(
            expectedOnsCapacidadeInstaladaGeracaoEntity,
            getPersistedOnsCapacidadeInstaladaGeracaoEntity(expectedOnsCapacidadeInstaladaGeracaoEntity)
        );
    }

    protected void assertPersistedOnsCapacidadeInstaladaGeracaoEntityToMatchUpdatableProperties(
        OnsCapacidadeInstaladaGeracaoEntity expectedOnsCapacidadeInstaladaGeracaoEntity
    ) {
        assertOnsCapacidadeInstaladaGeracaoEntityAllUpdatablePropertiesEquals(
            expectedOnsCapacidadeInstaladaGeracaoEntity,
            getPersistedOnsCapacidadeInstaladaGeracaoEntity(expectedOnsCapacidadeInstaladaGeracaoEntity)
        );
    }
}
