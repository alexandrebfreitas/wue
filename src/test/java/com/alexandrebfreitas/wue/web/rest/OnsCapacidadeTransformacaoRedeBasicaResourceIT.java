package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCapacidadeTransformacaoRedeBasicaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCapacidadeTransformacaoRedeBasicaEntity;
import com.alexandrebfreitas.wue.repository.OnsCapacidadeTransformacaoRedeBasicaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCapacidadeTransformacaoRedeBasicaSearchRepository;
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
 * Integration tests for the {@link OnsCapacidadeTransformacaoRedeBasicaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCapacidadeTransformacaoRedeBasicaResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPOTRANSFORMADOR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOTRANSFORMADOR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_AGENTEPROPRIETARIO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AGENTEPROPRIETARIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBESTACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBESTACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TRANSFORMADOR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TRANSFORMADOR = "BBBBBBBBBB";

    private static final String DEFAULT_COD_EQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COD_EQUIPAMENTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_ENTRADAOPERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_ENTRADAOPERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_DESATIVACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_DESATIVACAO = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_TENSAOPRIMARIO_KV = 1D;
    private static final Double UPDATED_VAL_TENSAOPRIMARIO_KV = 2D;

    private static final Double DEFAULT_VAL_TENSAOSECUNDARIO_KV = 1D;
    private static final Double UPDATED_VAL_TENSAOSECUNDARIO_KV = 2D;

    private static final Double DEFAULT_VAL_TENSAOTERCIARIO_KV = 1D;
    private static final Double UPDATED_VAL_TENSAOTERCIARIO_KV = 2D;

    private static final Double DEFAULT_VAL_POTENCIANOMINAL_MVA = 1D;
    private static final Double UPDATED_VAL_POTENCIANOMINAL_MVA = 2D;

    private static final String DEFAULT_NOM_TIPODEREDE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPODEREDE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_BARRA_PRIMARIO = 1;
    private static final Integer UPDATED_NUM_BARRA_PRIMARIO = 2;

    private static final Integer DEFAULT_NUM_BARRA_SECUNDARIO = 1;
    private static final Integer UPDATED_NUM_BARRA_SECUNDARIO = 2;

    private static final String ENTITY_API_URL = "/api/ons-capacidade-transformacao-rede-basicas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-capacidade-transformacao-rede-basicas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCapacidadeTransformacaoRedeBasicaRepository onsCapacidadeTransformacaoRedeBasicaRepository;

    @Autowired
    private OnsCapacidadeTransformacaoRedeBasicaSearchRepository onsCapacidadeTransformacaoRedeBasicaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCapacidadeTransformacaoRedeBasicaMockMvc;

    private OnsCapacidadeTransformacaoRedeBasicaEntity onsCapacidadeTransformacaoRedeBasicaEntity;

    private OnsCapacidadeTransformacaoRedeBasicaEntity insertedOnsCapacidadeTransformacaoRedeBasicaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCapacidadeTransformacaoRedeBasicaEntity createEntity() {
        return new OnsCapacidadeTransformacaoRedeBasicaEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .nomTipotransformador(DEFAULT_NOM_TIPOTRANSFORMADOR)
            .nomAgenteproprietario(DEFAULT_NOM_AGENTEPROPRIETARIO)
            .nomSubestacao(DEFAULT_NOM_SUBESTACAO)
            .nomTransformador(DEFAULT_NOM_TRANSFORMADOR)
            .codEquipamento(DEFAULT_COD_EQUIPAMENTO)
            .datEntradaoperacao(DEFAULT_DAT_ENTRADAOPERACAO)
            .datDesativacao(DEFAULT_DAT_DESATIVACAO)
            .valTensaoprimarioKv(DEFAULT_VAL_TENSAOPRIMARIO_KV)
            .valTensaosecundarioKv(DEFAULT_VAL_TENSAOSECUNDARIO_KV)
            .valTensaoterciarioKv(DEFAULT_VAL_TENSAOTERCIARIO_KV)
            .valPotencianominalMva(DEFAULT_VAL_POTENCIANOMINAL_MVA)
            .nomTipoderede(DEFAULT_NOM_TIPODEREDE)
            .numBarraPrimario(DEFAULT_NUM_BARRA_PRIMARIO)
            .numBarraSecundario(DEFAULT_NUM_BARRA_SECUNDARIO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCapacidadeTransformacaoRedeBasicaEntity createUpdatedEntity() {
        return new OnsCapacidadeTransformacaoRedeBasicaEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomTipotransformador(UPDATED_NOM_TIPOTRANSFORMADOR)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomSubestacao(UPDATED_NOM_SUBESTACAO)
            .nomTransformador(UPDATED_NOM_TRANSFORMADOR)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .valTensaoprimarioKv(UPDATED_VAL_TENSAOPRIMARIO_KV)
            .valTensaosecundarioKv(UPDATED_VAL_TENSAOSECUNDARIO_KV)
            .valTensaoterciarioKv(UPDATED_VAL_TENSAOTERCIARIO_KV)
            .valPotencianominalMva(UPDATED_VAL_POTENCIANOMINAL_MVA)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .numBarraPrimario(UPDATED_NUM_BARRA_PRIMARIO)
            .numBarraSecundario(UPDATED_NUM_BARRA_SECUNDARIO);
    }

    @BeforeEach
    public void initTest() {
        onsCapacidadeTransformacaoRedeBasicaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCapacidadeTransformacaoRedeBasicaEntity != null) {
            onsCapacidadeTransformacaoRedeBasicaRepository.delete(insertedOnsCapacidadeTransformacaoRedeBasicaEntity);
            onsCapacidadeTransformacaoRedeBasicaSearchRepository.delete(insertedOnsCapacidadeTransformacaoRedeBasicaEntity);
            insertedOnsCapacidadeTransformacaoRedeBasicaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        // Create the OnsCapacidadeTransformacaoRedeBasica
        var returnedOnsCapacidadeTransformacaoRedeBasicaEntity = om.readValue(
            restOnsCapacidadeTransformacaoRedeBasicaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCapacidadeTransformacaoRedeBasicaEntity.class
        );

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCapacidadeTransformacaoRedeBasicaEntityUpdatableFieldsEquals(
            returnedOnsCapacidadeTransformacaoRedeBasicaEntity,
            getPersistedOnsCapacidadeTransformacaoRedeBasicaEntity(returnedOnsCapacidadeTransformacaoRedeBasicaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = returnedOnsCapacidadeTransformacaoRedeBasicaEntity;
    }

    @Test
    @Transactional
    void createOnsCapacidadeTransformacaoRedeBasicaWithExistingId() throws Exception {
        // Create the OnsCapacidadeTransformacaoRedeBasica with an existing ID
        onsCapacidadeTransformacaoRedeBasicaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCapacidadeTransformacaoRedeBasicas() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.saveAndFlush(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );

        // Get all the onsCapacidadeTransformacaoRedeBasicaList
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCapacidadeTransformacaoRedeBasicaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomTipotransformador").value(hasItem(DEFAULT_NOM_TIPOTRANSFORMADOR)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomSubestacao").value(hasItem(DEFAULT_NOM_SUBESTACAO)))
            .andExpect(jsonPath("$.[*].nomTransformador").value(hasItem(DEFAULT_NOM_TRANSFORMADOR)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].valTensaoprimarioKv").value(hasItem(DEFAULT_VAL_TENSAOPRIMARIO_KV)))
            .andExpect(jsonPath("$.[*].valTensaosecundarioKv").value(hasItem(DEFAULT_VAL_TENSAOSECUNDARIO_KV)))
            .andExpect(jsonPath("$.[*].valTensaoterciarioKv").value(hasItem(DEFAULT_VAL_TENSAOTERCIARIO_KV)))
            .andExpect(jsonPath("$.[*].valPotencianominalMva").value(hasItem(DEFAULT_VAL_POTENCIANOMINAL_MVA)))
            .andExpect(jsonPath("$.[*].nomTipoderede").value(hasItem(DEFAULT_NOM_TIPODEREDE)))
            .andExpect(jsonPath("$.[*].numBarraPrimario").value(hasItem(DEFAULT_NUM_BARRA_PRIMARIO)))
            .andExpect(jsonPath("$.[*].numBarraSecundario").value(hasItem(DEFAULT_NUM_BARRA_SECUNDARIO)));
    }

    @Test
    @Transactional
    void getOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.saveAndFlush(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );

        // Get the onsCapacidadeTransformacaoRedeBasica
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCapacidadeTransformacaoRedeBasicaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCapacidadeTransformacaoRedeBasicaEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.nomTipotransformador").value(DEFAULT_NOM_TIPOTRANSFORMADOR))
            .andExpect(jsonPath("$.nomAgenteproprietario").value(DEFAULT_NOM_AGENTEPROPRIETARIO))
            .andExpect(jsonPath("$.nomSubestacao").value(DEFAULT_NOM_SUBESTACAO))
            .andExpect(jsonPath("$.nomTransformador").value(DEFAULT_NOM_TRANSFORMADOR))
            .andExpect(jsonPath("$.codEquipamento").value(DEFAULT_COD_EQUIPAMENTO))
            .andExpect(jsonPath("$.datEntradaoperacao").value(DEFAULT_DAT_ENTRADAOPERACAO.toString()))
            .andExpect(jsonPath("$.datDesativacao").value(DEFAULT_DAT_DESATIVACAO.toString()))
            .andExpect(jsonPath("$.valTensaoprimarioKv").value(DEFAULT_VAL_TENSAOPRIMARIO_KV))
            .andExpect(jsonPath("$.valTensaosecundarioKv").value(DEFAULT_VAL_TENSAOSECUNDARIO_KV))
            .andExpect(jsonPath("$.valTensaoterciarioKv").value(DEFAULT_VAL_TENSAOTERCIARIO_KV))
            .andExpect(jsonPath("$.valPotencianominalMva").value(DEFAULT_VAL_POTENCIANOMINAL_MVA))
            .andExpect(jsonPath("$.nomTipoderede").value(DEFAULT_NOM_TIPODEREDE))
            .andExpect(jsonPath("$.numBarraPrimario").value(DEFAULT_NUM_BARRA_PRIMARIO))
            .andExpect(jsonPath("$.numBarraSecundario").value(DEFAULT_NUM_BARRA_SECUNDARIO));
    }

    @Test
    @Transactional
    void getNonExistingOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        // Get the onsCapacidadeTransformacaoRedeBasica
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.saveAndFlush(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCapacidadeTransformacaoRedeBasicaSearchRepository.save(onsCapacidadeTransformacaoRedeBasicaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());

        // Update the onsCapacidadeTransformacaoRedeBasica
        OnsCapacidadeTransformacaoRedeBasicaEntity updatedOnsCapacidadeTransformacaoRedeBasicaEntity =
            onsCapacidadeTransformacaoRedeBasicaRepository.findById(onsCapacidadeTransformacaoRedeBasicaEntity.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCapacidadeTransformacaoRedeBasicaEntity are not directly saved in db
        em.detach(updatedOnsCapacidadeTransformacaoRedeBasicaEntity);
        updatedOnsCapacidadeTransformacaoRedeBasicaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomTipotransformador(UPDATED_NOM_TIPOTRANSFORMADOR)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomSubestacao(UPDATED_NOM_SUBESTACAO)
            .nomTransformador(UPDATED_NOM_TRANSFORMADOR)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .valTensaoprimarioKv(UPDATED_VAL_TENSAOPRIMARIO_KV)
            .valTensaosecundarioKv(UPDATED_VAL_TENSAOSECUNDARIO_KV)
            .valTensaoterciarioKv(UPDATED_VAL_TENSAOTERCIARIO_KV)
            .valPotencianominalMva(UPDATED_VAL_POTENCIANOMINAL_MVA)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .numBarraPrimario(UPDATED_NUM_BARRA_PRIMARIO)
            .numBarraSecundario(UPDATED_NUM_BARRA_SECUNDARIO);

        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCapacidadeTransformacaoRedeBasicaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCapacidadeTransformacaoRedeBasicaEntityToMatchAllProperties(updatedOnsCapacidadeTransformacaoRedeBasicaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCapacidadeTransformacaoRedeBasicaEntity> onsCapacidadeTransformacaoRedeBasicaSearchList = Streamable.of(
                    onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll()
                ).toList();
                OnsCapacidadeTransformacaoRedeBasicaEntity testOnsCapacidadeTransformacaoRedeBasicaSearch =
                    onsCapacidadeTransformacaoRedeBasicaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsCapacidadeTransformacaoRedeBasicaEntityAllPropertiesEquals(
                    testOnsCapacidadeTransformacaoRedeBasicaSearch,
                    updatedOnsCapacidadeTransformacaoRedeBasicaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        onsCapacidadeTransformacaoRedeBasicaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCapacidadeTransformacaoRedeBasicaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        onsCapacidadeTransformacaoRedeBasicaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        onsCapacidadeTransformacaoRedeBasicaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCapacidadeTransformacaoRedeBasicaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.saveAndFlush(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCapacidadeTransformacaoRedeBasica using partial update
        OnsCapacidadeTransformacaoRedeBasicaEntity partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity =
            new OnsCapacidadeTransformacaoRedeBasicaEntity();
        partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity.setId(onsCapacidadeTransformacaoRedeBasicaEntity.getId());

        partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .valTensaoprimarioKv(UPDATED_VAL_TENSAOPRIMARIO_KV)
            .valTensaosecundarioKv(UPDATED_VAL_TENSAOSECUNDARIO_KV)
            .numBarraPrimario(UPDATED_NUM_BARRA_PRIMARIO);

        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCapacidadeTransformacaoRedeBasicaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity, onsCapacidadeTransformacaoRedeBasicaEntity),
            getPersistedOnsCapacidadeTransformacaoRedeBasicaEntity(onsCapacidadeTransformacaoRedeBasicaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCapacidadeTransformacaoRedeBasicaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.saveAndFlush(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCapacidadeTransformacaoRedeBasica using partial update
        OnsCapacidadeTransformacaoRedeBasicaEntity partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity =
            new OnsCapacidadeTransformacaoRedeBasicaEntity();
        partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity.setId(onsCapacidadeTransformacaoRedeBasicaEntity.getId());

        partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomTipotransformador(UPDATED_NOM_TIPOTRANSFORMADOR)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomSubestacao(UPDATED_NOM_SUBESTACAO)
            .nomTransformador(UPDATED_NOM_TRANSFORMADOR)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .valTensaoprimarioKv(UPDATED_VAL_TENSAOPRIMARIO_KV)
            .valTensaosecundarioKv(UPDATED_VAL_TENSAOSECUNDARIO_KV)
            .valTensaoterciarioKv(UPDATED_VAL_TENSAOTERCIARIO_KV)
            .valPotencianominalMva(UPDATED_VAL_POTENCIANOMINAL_MVA)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .numBarraPrimario(UPDATED_NUM_BARRA_PRIMARIO)
            .numBarraSecundario(UPDATED_NUM_BARRA_SECUNDARIO);

        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCapacidadeTransformacaoRedeBasicaEntityUpdatableFieldsEquals(
            partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity,
            getPersistedOnsCapacidadeTransformacaoRedeBasicaEntity(partialUpdatedOnsCapacidadeTransformacaoRedeBasicaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        onsCapacidadeTransformacaoRedeBasicaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCapacidadeTransformacaoRedeBasicaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        onsCapacidadeTransformacaoRedeBasicaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        onsCapacidadeTransformacaoRedeBasicaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCapacidadeTransformacaoRedeBasicaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCapacidadeTransformacaoRedeBasica in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.saveAndFlush(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );
        onsCapacidadeTransformacaoRedeBasicaRepository.save(onsCapacidadeTransformacaoRedeBasicaEntity);
        onsCapacidadeTransformacaoRedeBasicaSearchRepository.save(onsCapacidadeTransformacaoRedeBasicaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCapacidadeTransformacaoRedeBasica
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsCapacidadeTransformacaoRedeBasicaEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCapacidadeTransformacaoRedeBasicaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCapacidadeTransformacaoRedeBasica() throws Exception {
        // Initialize the database
        insertedOnsCapacidadeTransformacaoRedeBasicaEntity = onsCapacidadeTransformacaoRedeBasicaRepository.saveAndFlush(
            onsCapacidadeTransformacaoRedeBasicaEntity
        );
        onsCapacidadeTransformacaoRedeBasicaSearchRepository.save(onsCapacidadeTransformacaoRedeBasicaEntity);

        // Search the onsCapacidadeTransformacaoRedeBasica
        restOnsCapacidadeTransformacaoRedeBasicaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCapacidadeTransformacaoRedeBasicaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCapacidadeTransformacaoRedeBasicaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomTipotransformador").value(hasItem(DEFAULT_NOM_TIPOTRANSFORMADOR)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomSubestacao").value(hasItem(DEFAULT_NOM_SUBESTACAO)))
            .andExpect(jsonPath("$.[*].nomTransformador").value(hasItem(DEFAULT_NOM_TRANSFORMADOR)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].valTensaoprimarioKv").value(hasItem(DEFAULT_VAL_TENSAOPRIMARIO_KV)))
            .andExpect(jsonPath("$.[*].valTensaosecundarioKv").value(hasItem(DEFAULT_VAL_TENSAOSECUNDARIO_KV)))
            .andExpect(jsonPath("$.[*].valTensaoterciarioKv").value(hasItem(DEFAULT_VAL_TENSAOTERCIARIO_KV)))
            .andExpect(jsonPath("$.[*].valPotencianominalMva").value(hasItem(DEFAULT_VAL_POTENCIANOMINAL_MVA)))
            .andExpect(jsonPath("$.[*].nomTipoderede").value(hasItem(DEFAULT_NOM_TIPODEREDE)))
            .andExpect(jsonPath("$.[*].numBarraPrimario").value(hasItem(DEFAULT_NUM_BARRA_PRIMARIO)))
            .andExpect(jsonPath("$.[*].numBarraSecundario").value(hasItem(DEFAULT_NUM_BARRA_SECUNDARIO)));
    }

    protected long getRepositoryCount() {
        return onsCapacidadeTransformacaoRedeBasicaRepository.count();
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

    protected OnsCapacidadeTransformacaoRedeBasicaEntity getPersistedOnsCapacidadeTransformacaoRedeBasicaEntity(
        OnsCapacidadeTransformacaoRedeBasicaEntity onsCapacidadeTransformacaoRedeBasica
    ) {
        return onsCapacidadeTransformacaoRedeBasicaRepository.findById(onsCapacidadeTransformacaoRedeBasica.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCapacidadeTransformacaoRedeBasicaEntityToMatchAllProperties(
        OnsCapacidadeTransformacaoRedeBasicaEntity expectedOnsCapacidadeTransformacaoRedeBasicaEntity
    ) {
        assertOnsCapacidadeTransformacaoRedeBasicaEntityAllPropertiesEquals(
            expectedOnsCapacidadeTransformacaoRedeBasicaEntity,
            getPersistedOnsCapacidadeTransformacaoRedeBasicaEntity(expectedOnsCapacidadeTransformacaoRedeBasicaEntity)
        );
    }

    protected void assertPersistedOnsCapacidadeTransformacaoRedeBasicaEntityToMatchUpdatableProperties(
        OnsCapacidadeTransformacaoRedeBasicaEntity expectedOnsCapacidadeTransformacaoRedeBasicaEntity
    ) {
        assertOnsCapacidadeTransformacaoRedeBasicaEntityAllUpdatablePropertiesEquals(
            expectedOnsCapacidadeTransformacaoRedeBasicaEntity,
            getPersistedOnsCapacidadeTransformacaoRedeBasicaEntity(expectedOnsCapacidadeTransformacaoRedeBasicaEntity)
        );
    }
}
