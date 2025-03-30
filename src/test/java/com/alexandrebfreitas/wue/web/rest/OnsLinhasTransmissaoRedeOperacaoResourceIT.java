package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntity;
import com.alexandrebfreitas.wue.repository.OnsLinhasTransmissaoRedeOperacaoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsLinhasTransmissaoRedeOperacaoSearchRepository;
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
 * Integration tests for the {@link OnsLinhasTransmissaoRedeOperacaoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsLinhasTransmissaoRedeOperacaoResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA_TERMINALDE = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA_TERMINALDE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA_TERMINALDE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA_TERMINALDE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_SUBSISTEMA_TERMINALPARA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA_TERMINALPARA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA_TERMINALPARA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA_TERMINALPARA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO_TERMINALDE = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO_TERMINALDE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO_DE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO_DE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO_TERMINALPARA = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO_TERMINALPARA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO_PARA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO_PARA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBESTACAO_DE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBESTACAO_DE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBESTACAO_PARA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBESTACAO_PARA = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_NIVELTENSAO_KV = 1D;
    private static final Double UPDATED_VAL_NIVELTENSAO_KV = 2D;

    private static final String DEFAULT_NOM_TIPODEREDE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPODEREDE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPOLINHA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOLINHA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_AGENTEPROPRIETARIO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AGENTEPROPRIETARIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_LINHADETRANSMISSAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_LINHADETRANSMISSAO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_EQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COD_EQUIPAMENTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_ENTRADAOPERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_ENTRADAOPERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_DESATIVACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_DESATIVACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_PREVISTA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_PREVISTA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_COMPRIMENTO = 1D;
    private static final Double UPDATED_VAL_COMPRIMENTO = 2D;

    private static final Double DEFAULT_VAL_RESISTENCIA = 1D;
    private static final Double UPDATED_VAL_RESISTENCIA = 2D;

    private static final Double DEFAULT_VAL_REATANCIA = 1D;
    private static final Double UPDATED_VAL_REATANCIA = 2D;

    private static final Double DEFAULT_VAL_SHUNT = 1D;
    private static final Double UPDATED_VAL_SHUNT = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERLONGASEMLIMIT = 1D;
    private static final Double UPDATED_VAL_CAPACOPERLONGASEMLIMIT = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERLONGACOMLIMIT = 1D;
    private static final Double UPDATED_VAL_CAPACOPERLONGACOMLIMIT = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERCURTASEMLIMIT = 1D;
    private static final Double UPDATED_VAL_CAPACOPERCURTASEMLIMIT = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERCURTACOMLIMIT = 1D;
    private static final Double UPDATED_VAL_CAPACOPERCURTACOMLIMIT = 2D;

    private static final Double DEFAULT_VAL_CAPACIDADEOPERVERAODIALONGA = 1D;
    private static final Double UPDATED_VAL_CAPACIDADEOPERVERAODIALONGA = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERINVERNODIALONGA = 1D;
    private static final Double UPDATED_VAL_CAPACOPERINVERNODIALONGA = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERINVERNONOITELONGA = 1D;
    private static final Double UPDATED_VAL_CAPACOPERINVERNONOITELONGA = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERVERADIACURTA = 1D;
    private static final Double UPDATED_VAL_CAPACOPERVERADIACURTA = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERVERAONOITECURTA = 1D;
    private static final Double UPDATED_VAL_CAPACOPERVERAONOITECURTA = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERINVERNODIACURTA = 1D;
    private static final Double UPDATED_VAL_CAPACOPERINVERNODIACURTA = 2D;

    private static final Double DEFAULT_VAL_CAPACOPERINVERNONOITECURTA = 1D;
    private static final Double UPDATED_VAL_CAPACOPERINVERNONOITECURTA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-linhas-transmissao-rede-operacaos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-linhas-transmissao-rede-operacaos/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsLinhasTransmissaoRedeOperacaoRepository onsLinhasTransmissaoRedeOperacaoRepository;

    @Autowired
    private OnsLinhasTransmissaoRedeOperacaoSearchRepository onsLinhasTransmissaoRedeOperacaoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsLinhasTransmissaoRedeOperacaoMockMvc;

    private OnsLinhasTransmissaoRedeOperacaoEntity onsLinhasTransmissaoRedeOperacaoEntity;

    private OnsLinhasTransmissaoRedeOperacaoEntity insertedOnsLinhasTransmissaoRedeOperacaoEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsLinhasTransmissaoRedeOperacaoEntity createEntity() {
        return new OnsLinhasTransmissaoRedeOperacaoEntity()
            .idSubsistemaTerminalde(DEFAULT_ID_SUBSISTEMA_TERMINALDE)
            .nomSubsistemaTerminalde(DEFAULT_NOM_SUBSISTEMA_TERMINALDE)
            .idSubsistemaTerminalpara(DEFAULT_ID_SUBSISTEMA_TERMINALPARA)
            .nomSubsistemaTerminalpara(DEFAULT_NOM_SUBSISTEMA_TERMINALPARA)
            .idEstadoTerminalde(DEFAULT_ID_ESTADO_TERMINALDE)
            .nomEstadoDe(DEFAULT_NOM_ESTADO_DE)
            .idEstadoTerminalpara(DEFAULT_ID_ESTADO_TERMINALPARA)
            .nomEstadoPara(DEFAULT_NOM_ESTADO_PARA)
            .nomSubestacaoDe(DEFAULT_NOM_SUBESTACAO_DE)
            .nomSubestacaoPara(DEFAULT_NOM_SUBESTACAO_PARA)
            .valNiveltensaoKv(DEFAULT_VAL_NIVELTENSAO_KV)
            .nomTipoderede(DEFAULT_NOM_TIPODEREDE)
            .nomTipolinha(DEFAULT_NOM_TIPOLINHA)
            .nomAgenteproprietario(DEFAULT_NOM_AGENTEPROPRIETARIO)
            .nomLinhadetransmissao(DEFAULT_NOM_LINHADETRANSMISSAO)
            .codEquipamento(DEFAULT_COD_EQUIPAMENTO)
            .datEntradaoperacao(DEFAULT_DAT_ENTRADAOPERACAO)
            .datDesativacao(DEFAULT_DAT_DESATIVACAO)
            .datPrevista(DEFAULT_DAT_PREVISTA)
            .valComprimento(DEFAULT_VAL_COMPRIMENTO)
            .valResistencia(DEFAULT_VAL_RESISTENCIA)
            .valReatancia(DEFAULT_VAL_REATANCIA)
            .valShunt(DEFAULT_VAL_SHUNT)
            .valCapacoperlongasemlimit(DEFAULT_VAL_CAPACOPERLONGASEMLIMIT)
            .valCapacoperlongacomlimit(DEFAULT_VAL_CAPACOPERLONGACOMLIMIT)
            .valCapacopercurtasemlimit(DEFAULT_VAL_CAPACOPERCURTASEMLIMIT)
            .valCapacopercurtacomlimit(DEFAULT_VAL_CAPACOPERCURTACOMLIMIT)
            .valCapacidadeoperveraodialonga(DEFAULT_VAL_CAPACIDADEOPERVERAODIALONGA)
            .valCapacoperinvernodialonga(DEFAULT_VAL_CAPACOPERINVERNODIALONGA)
            .valCapacoperinvernonoitelonga(DEFAULT_VAL_CAPACOPERINVERNONOITELONGA)
            .valCapacoperveradiacurta(DEFAULT_VAL_CAPACOPERVERADIACURTA)
            .valCapacoperveraonoitecurta(DEFAULT_VAL_CAPACOPERVERAONOITECURTA)
            .valCapacoperinvernodiacurta(DEFAULT_VAL_CAPACOPERINVERNODIACURTA)
            .valCapacoperinvernonoitecurta(DEFAULT_VAL_CAPACOPERINVERNONOITECURTA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsLinhasTransmissaoRedeOperacaoEntity createUpdatedEntity() {
        return new OnsLinhasTransmissaoRedeOperacaoEntity()
            .idSubsistemaTerminalde(UPDATED_ID_SUBSISTEMA_TERMINALDE)
            .nomSubsistemaTerminalde(UPDATED_NOM_SUBSISTEMA_TERMINALDE)
            .idSubsistemaTerminalpara(UPDATED_ID_SUBSISTEMA_TERMINALPARA)
            .nomSubsistemaTerminalpara(UPDATED_NOM_SUBSISTEMA_TERMINALPARA)
            .idEstadoTerminalde(UPDATED_ID_ESTADO_TERMINALDE)
            .nomEstadoDe(UPDATED_NOM_ESTADO_DE)
            .idEstadoTerminalpara(UPDATED_ID_ESTADO_TERMINALPARA)
            .nomEstadoPara(UPDATED_NOM_ESTADO_PARA)
            .nomSubestacaoDe(UPDATED_NOM_SUBESTACAO_DE)
            .nomSubestacaoPara(UPDATED_NOM_SUBESTACAO_PARA)
            .valNiveltensaoKv(UPDATED_VAL_NIVELTENSAO_KV)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .nomTipolinha(UPDATED_NOM_TIPOLINHA)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomLinhadetransmissao(UPDATED_NOM_LINHADETRANSMISSAO)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .datPrevista(UPDATED_DAT_PREVISTA)
            .valComprimento(UPDATED_VAL_COMPRIMENTO)
            .valResistencia(UPDATED_VAL_RESISTENCIA)
            .valReatancia(UPDATED_VAL_REATANCIA)
            .valShunt(UPDATED_VAL_SHUNT)
            .valCapacoperlongasemlimit(UPDATED_VAL_CAPACOPERLONGASEMLIMIT)
            .valCapacoperlongacomlimit(UPDATED_VAL_CAPACOPERLONGACOMLIMIT)
            .valCapacopercurtasemlimit(UPDATED_VAL_CAPACOPERCURTASEMLIMIT)
            .valCapacopercurtacomlimit(UPDATED_VAL_CAPACOPERCURTACOMLIMIT)
            .valCapacidadeoperveraodialonga(UPDATED_VAL_CAPACIDADEOPERVERAODIALONGA)
            .valCapacoperinvernodialonga(UPDATED_VAL_CAPACOPERINVERNODIALONGA)
            .valCapacoperinvernonoitelonga(UPDATED_VAL_CAPACOPERINVERNONOITELONGA)
            .valCapacoperveradiacurta(UPDATED_VAL_CAPACOPERVERADIACURTA)
            .valCapacoperveraonoitecurta(UPDATED_VAL_CAPACOPERVERAONOITECURTA)
            .valCapacoperinvernodiacurta(UPDATED_VAL_CAPACOPERINVERNODIACURTA)
            .valCapacoperinvernonoitecurta(UPDATED_VAL_CAPACOPERINVERNONOITECURTA);
    }

    @BeforeEach
    public void initTest() {
        onsLinhasTransmissaoRedeOperacaoEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsLinhasTransmissaoRedeOperacaoEntity != null) {
            onsLinhasTransmissaoRedeOperacaoRepository.delete(insertedOnsLinhasTransmissaoRedeOperacaoEntity);
            onsLinhasTransmissaoRedeOperacaoSearchRepository.delete(insertedOnsLinhasTransmissaoRedeOperacaoEntity);
            insertedOnsLinhasTransmissaoRedeOperacaoEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsLinhasTransmissaoRedeOperacao() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        // Create the OnsLinhasTransmissaoRedeOperacao
        var returnedOnsLinhasTransmissaoRedeOperacaoEntity = om.readValue(
            restOnsLinhasTransmissaoRedeOperacaoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsLinhasTransmissaoRedeOperacaoEntity.class
        );

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsLinhasTransmissaoRedeOperacaoEntityUpdatableFieldsEquals(
            returnedOnsLinhasTransmissaoRedeOperacaoEntity,
            getPersistedOnsLinhasTransmissaoRedeOperacaoEntity(returnedOnsLinhasTransmissaoRedeOperacaoEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsLinhasTransmissaoRedeOperacaoEntity = returnedOnsLinhasTransmissaoRedeOperacaoEntity;
    }

    @Test
    @Transactional
    void createOnsLinhasTransmissaoRedeOperacaoWithExistingId() throws Exception {
        // Create the OnsLinhasTransmissaoRedeOperacao with an existing ID
        onsLinhasTransmissaoRedeOperacaoEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsLinhasTransmissaoRedeOperacaos() throws Exception {
        // Initialize the database
        insertedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.saveAndFlush(
            onsLinhasTransmissaoRedeOperacaoEntity
        );

        // Get all the onsLinhasTransmissaoRedeOperacaoList
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsLinhasTransmissaoRedeOperacaoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistemaTerminalde").value(hasItem(DEFAULT_ID_SUBSISTEMA_TERMINALDE)))
            .andExpect(jsonPath("$.[*].nomSubsistemaTerminalde").value(hasItem(DEFAULT_NOM_SUBSISTEMA_TERMINALDE)))
            .andExpect(jsonPath("$.[*].idSubsistemaTerminalpara").value(hasItem(DEFAULT_ID_SUBSISTEMA_TERMINALPARA)))
            .andExpect(jsonPath("$.[*].nomSubsistemaTerminalpara").value(hasItem(DEFAULT_NOM_SUBSISTEMA_TERMINALPARA)))
            .andExpect(jsonPath("$.[*].idEstadoTerminalde").value(hasItem(DEFAULT_ID_ESTADO_TERMINALDE)))
            .andExpect(jsonPath("$.[*].nomEstadoDe").value(hasItem(DEFAULT_NOM_ESTADO_DE)))
            .andExpect(jsonPath("$.[*].idEstadoTerminalpara").value(hasItem(DEFAULT_ID_ESTADO_TERMINALPARA)))
            .andExpect(jsonPath("$.[*].nomEstadoPara").value(hasItem(DEFAULT_NOM_ESTADO_PARA)))
            .andExpect(jsonPath("$.[*].nomSubestacaoDe").value(hasItem(DEFAULT_NOM_SUBESTACAO_DE)))
            .andExpect(jsonPath("$.[*].nomSubestacaoPara").value(hasItem(DEFAULT_NOM_SUBESTACAO_PARA)))
            .andExpect(jsonPath("$.[*].valNiveltensaoKv").value(hasItem(DEFAULT_VAL_NIVELTENSAO_KV)))
            .andExpect(jsonPath("$.[*].nomTipoderede").value(hasItem(DEFAULT_NOM_TIPODEREDE)))
            .andExpect(jsonPath("$.[*].nomTipolinha").value(hasItem(DEFAULT_NOM_TIPOLINHA)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomLinhadetransmissao").value(hasItem(DEFAULT_NOM_LINHADETRANSMISSAO)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].datPrevista").value(hasItem(DEFAULT_DAT_PREVISTA.toString())))
            .andExpect(jsonPath("$.[*].valComprimento").value(hasItem(DEFAULT_VAL_COMPRIMENTO)))
            .andExpect(jsonPath("$.[*].valResistencia").value(hasItem(DEFAULT_VAL_RESISTENCIA)))
            .andExpect(jsonPath("$.[*].valReatancia").value(hasItem(DEFAULT_VAL_REATANCIA)))
            .andExpect(jsonPath("$.[*].valShunt").value(hasItem(DEFAULT_VAL_SHUNT)))
            .andExpect(jsonPath("$.[*].valCapacoperlongasemlimit").value(hasItem(DEFAULT_VAL_CAPACOPERLONGASEMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacoperlongacomlimit").value(hasItem(DEFAULT_VAL_CAPACOPERLONGACOMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacopercurtasemlimit").value(hasItem(DEFAULT_VAL_CAPACOPERCURTASEMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacopercurtacomlimit").value(hasItem(DEFAULT_VAL_CAPACOPERCURTACOMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacidadeoperveraodialonga").value(hasItem(DEFAULT_VAL_CAPACIDADEOPERVERAODIALONGA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernodialonga").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNODIALONGA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernonoitelonga").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNONOITELONGA)))
            .andExpect(jsonPath("$.[*].valCapacoperveradiacurta").value(hasItem(DEFAULT_VAL_CAPACOPERVERADIACURTA)))
            .andExpect(jsonPath("$.[*].valCapacoperveraonoitecurta").value(hasItem(DEFAULT_VAL_CAPACOPERVERAONOITECURTA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernodiacurta").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNODIACURTA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernonoitecurta").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNONOITECURTA)));
    }

    @Test
    @Transactional
    void getOnsLinhasTransmissaoRedeOperacao() throws Exception {
        // Initialize the database
        insertedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.saveAndFlush(
            onsLinhasTransmissaoRedeOperacaoEntity
        );

        // Get the onsLinhasTransmissaoRedeOperacao
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(get(ENTITY_API_URL_ID, onsLinhasTransmissaoRedeOperacaoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsLinhasTransmissaoRedeOperacaoEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistemaTerminalde").value(DEFAULT_ID_SUBSISTEMA_TERMINALDE))
            .andExpect(jsonPath("$.nomSubsistemaTerminalde").value(DEFAULT_NOM_SUBSISTEMA_TERMINALDE))
            .andExpect(jsonPath("$.idSubsistemaTerminalpara").value(DEFAULT_ID_SUBSISTEMA_TERMINALPARA))
            .andExpect(jsonPath("$.nomSubsistemaTerminalpara").value(DEFAULT_NOM_SUBSISTEMA_TERMINALPARA))
            .andExpect(jsonPath("$.idEstadoTerminalde").value(DEFAULT_ID_ESTADO_TERMINALDE))
            .andExpect(jsonPath("$.nomEstadoDe").value(DEFAULT_NOM_ESTADO_DE))
            .andExpect(jsonPath("$.idEstadoTerminalpara").value(DEFAULT_ID_ESTADO_TERMINALPARA))
            .andExpect(jsonPath("$.nomEstadoPara").value(DEFAULT_NOM_ESTADO_PARA))
            .andExpect(jsonPath("$.nomSubestacaoDe").value(DEFAULT_NOM_SUBESTACAO_DE))
            .andExpect(jsonPath("$.nomSubestacaoPara").value(DEFAULT_NOM_SUBESTACAO_PARA))
            .andExpect(jsonPath("$.valNiveltensaoKv").value(DEFAULT_VAL_NIVELTENSAO_KV))
            .andExpect(jsonPath("$.nomTipoderede").value(DEFAULT_NOM_TIPODEREDE))
            .andExpect(jsonPath("$.nomTipolinha").value(DEFAULT_NOM_TIPOLINHA))
            .andExpect(jsonPath("$.nomAgenteproprietario").value(DEFAULT_NOM_AGENTEPROPRIETARIO))
            .andExpect(jsonPath("$.nomLinhadetransmissao").value(DEFAULT_NOM_LINHADETRANSMISSAO))
            .andExpect(jsonPath("$.codEquipamento").value(DEFAULT_COD_EQUIPAMENTO))
            .andExpect(jsonPath("$.datEntradaoperacao").value(DEFAULT_DAT_ENTRADAOPERACAO.toString()))
            .andExpect(jsonPath("$.datDesativacao").value(DEFAULT_DAT_DESATIVACAO.toString()))
            .andExpect(jsonPath("$.datPrevista").value(DEFAULT_DAT_PREVISTA.toString()))
            .andExpect(jsonPath("$.valComprimento").value(DEFAULT_VAL_COMPRIMENTO))
            .andExpect(jsonPath("$.valResistencia").value(DEFAULT_VAL_RESISTENCIA))
            .andExpect(jsonPath("$.valReatancia").value(DEFAULT_VAL_REATANCIA))
            .andExpect(jsonPath("$.valShunt").value(DEFAULT_VAL_SHUNT))
            .andExpect(jsonPath("$.valCapacoperlongasemlimit").value(DEFAULT_VAL_CAPACOPERLONGASEMLIMIT))
            .andExpect(jsonPath("$.valCapacoperlongacomlimit").value(DEFAULT_VAL_CAPACOPERLONGACOMLIMIT))
            .andExpect(jsonPath("$.valCapacopercurtasemlimit").value(DEFAULT_VAL_CAPACOPERCURTASEMLIMIT))
            .andExpect(jsonPath("$.valCapacopercurtacomlimit").value(DEFAULT_VAL_CAPACOPERCURTACOMLIMIT))
            .andExpect(jsonPath("$.valCapacidadeoperveraodialonga").value(DEFAULT_VAL_CAPACIDADEOPERVERAODIALONGA))
            .andExpect(jsonPath("$.valCapacoperinvernodialonga").value(DEFAULT_VAL_CAPACOPERINVERNODIALONGA))
            .andExpect(jsonPath("$.valCapacoperinvernonoitelonga").value(DEFAULT_VAL_CAPACOPERINVERNONOITELONGA))
            .andExpect(jsonPath("$.valCapacoperveradiacurta").value(DEFAULT_VAL_CAPACOPERVERADIACURTA))
            .andExpect(jsonPath("$.valCapacoperveraonoitecurta").value(DEFAULT_VAL_CAPACOPERVERAONOITECURTA))
            .andExpect(jsonPath("$.valCapacoperinvernodiacurta").value(DEFAULT_VAL_CAPACOPERINVERNODIACURTA))
            .andExpect(jsonPath("$.valCapacoperinvernonoitecurta").value(DEFAULT_VAL_CAPACOPERINVERNONOITECURTA));
    }

    @Test
    @Transactional
    void getNonExistingOnsLinhasTransmissaoRedeOperacao() throws Exception {
        // Get the onsLinhasTransmissaoRedeOperacao
        restOnsLinhasTransmissaoRedeOperacaoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsLinhasTransmissaoRedeOperacao() throws Exception {
        // Initialize the database
        insertedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.saveAndFlush(
            onsLinhasTransmissaoRedeOperacaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsLinhasTransmissaoRedeOperacaoSearchRepository.save(onsLinhasTransmissaoRedeOperacaoEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());

        // Update the onsLinhasTransmissaoRedeOperacao
        OnsLinhasTransmissaoRedeOperacaoEntity updatedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository
            .findById(onsLinhasTransmissaoRedeOperacaoEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsLinhasTransmissaoRedeOperacaoEntity are not directly saved in db
        em.detach(updatedOnsLinhasTransmissaoRedeOperacaoEntity);
        updatedOnsLinhasTransmissaoRedeOperacaoEntity
            .idSubsistemaTerminalde(UPDATED_ID_SUBSISTEMA_TERMINALDE)
            .nomSubsistemaTerminalde(UPDATED_NOM_SUBSISTEMA_TERMINALDE)
            .idSubsistemaTerminalpara(UPDATED_ID_SUBSISTEMA_TERMINALPARA)
            .nomSubsistemaTerminalpara(UPDATED_NOM_SUBSISTEMA_TERMINALPARA)
            .idEstadoTerminalde(UPDATED_ID_ESTADO_TERMINALDE)
            .nomEstadoDe(UPDATED_NOM_ESTADO_DE)
            .idEstadoTerminalpara(UPDATED_ID_ESTADO_TERMINALPARA)
            .nomEstadoPara(UPDATED_NOM_ESTADO_PARA)
            .nomSubestacaoDe(UPDATED_NOM_SUBESTACAO_DE)
            .nomSubestacaoPara(UPDATED_NOM_SUBESTACAO_PARA)
            .valNiveltensaoKv(UPDATED_VAL_NIVELTENSAO_KV)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .nomTipolinha(UPDATED_NOM_TIPOLINHA)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomLinhadetransmissao(UPDATED_NOM_LINHADETRANSMISSAO)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .datPrevista(UPDATED_DAT_PREVISTA)
            .valComprimento(UPDATED_VAL_COMPRIMENTO)
            .valResistencia(UPDATED_VAL_RESISTENCIA)
            .valReatancia(UPDATED_VAL_REATANCIA)
            .valShunt(UPDATED_VAL_SHUNT)
            .valCapacoperlongasemlimit(UPDATED_VAL_CAPACOPERLONGASEMLIMIT)
            .valCapacoperlongacomlimit(UPDATED_VAL_CAPACOPERLONGACOMLIMIT)
            .valCapacopercurtasemlimit(UPDATED_VAL_CAPACOPERCURTASEMLIMIT)
            .valCapacopercurtacomlimit(UPDATED_VAL_CAPACOPERCURTACOMLIMIT)
            .valCapacidadeoperveraodialonga(UPDATED_VAL_CAPACIDADEOPERVERAODIALONGA)
            .valCapacoperinvernodialonga(UPDATED_VAL_CAPACOPERINVERNODIALONGA)
            .valCapacoperinvernonoitelonga(UPDATED_VAL_CAPACOPERINVERNONOITELONGA)
            .valCapacoperveradiacurta(UPDATED_VAL_CAPACOPERVERADIACURTA)
            .valCapacoperveraonoitecurta(UPDATED_VAL_CAPACOPERVERAONOITECURTA)
            .valCapacoperinvernodiacurta(UPDATED_VAL_CAPACOPERINVERNODIACURTA)
            .valCapacoperinvernonoitecurta(UPDATED_VAL_CAPACOPERINVERNONOITECURTA);

        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsLinhasTransmissaoRedeOperacaoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsLinhasTransmissaoRedeOperacaoEntityToMatchAllProperties(updatedOnsLinhasTransmissaoRedeOperacaoEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsLinhasTransmissaoRedeOperacaoEntity> onsLinhasTransmissaoRedeOperacaoSearchList = Streamable.of(
                    onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll()
                ).toList();
                OnsLinhasTransmissaoRedeOperacaoEntity testOnsLinhasTransmissaoRedeOperacaoSearch =
                    onsLinhasTransmissaoRedeOperacaoSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsLinhasTransmissaoRedeOperacaoEntityAllPropertiesEquals(
                    testOnsLinhasTransmissaoRedeOperacaoSearch,
                    updatedOnsLinhasTransmissaoRedeOperacaoEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsLinhasTransmissaoRedeOperacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        onsLinhasTransmissaoRedeOperacaoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsLinhasTransmissaoRedeOperacaoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsLinhasTransmissaoRedeOperacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        onsLinhasTransmissaoRedeOperacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsLinhasTransmissaoRedeOperacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        onsLinhasTransmissaoRedeOperacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsLinhasTransmissaoRedeOperacaoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.saveAndFlush(
            onsLinhasTransmissaoRedeOperacaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsLinhasTransmissaoRedeOperacao using partial update
        OnsLinhasTransmissaoRedeOperacaoEntity partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity =
            new OnsLinhasTransmissaoRedeOperacaoEntity();
        partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity.setId(onsLinhasTransmissaoRedeOperacaoEntity.getId());

        partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity
            .idSubsistemaTerminalde(UPDATED_ID_SUBSISTEMA_TERMINALDE)
            .idSubsistemaTerminalpara(UPDATED_ID_SUBSISTEMA_TERMINALPARA)
            .nomSubestacaoDe(UPDATED_NOM_SUBESTACAO_DE)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datPrevista(UPDATED_DAT_PREVISTA)
            .valShunt(UPDATED_VAL_SHUNT)
            .valCapacoperlongasemlimit(UPDATED_VAL_CAPACOPERLONGASEMLIMIT)
            .valCapacoperlongacomlimit(UPDATED_VAL_CAPACOPERLONGACOMLIMIT)
            .valCapacidadeoperveraodialonga(UPDATED_VAL_CAPACIDADEOPERVERAODIALONGA)
            .valCapacoperinvernodialonga(UPDATED_VAL_CAPACOPERINVERNODIALONGA)
            .valCapacoperinvernonoitelonga(UPDATED_VAL_CAPACOPERINVERNONOITELONGA)
            .valCapacoperveraonoitecurta(UPDATED_VAL_CAPACOPERVERAONOITECURTA)
            .valCapacoperinvernodiacurta(UPDATED_VAL_CAPACOPERINVERNODIACURTA);

        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsLinhasTransmissaoRedeOperacaoEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity, onsLinhasTransmissaoRedeOperacaoEntity),
            getPersistedOnsLinhasTransmissaoRedeOperacaoEntity(onsLinhasTransmissaoRedeOperacaoEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsLinhasTransmissaoRedeOperacaoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.saveAndFlush(
            onsLinhasTransmissaoRedeOperacaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsLinhasTransmissaoRedeOperacao using partial update
        OnsLinhasTransmissaoRedeOperacaoEntity partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity =
            new OnsLinhasTransmissaoRedeOperacaoEntity();
        partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity.setId(onsLinhasTransmissaoRedeOperacaoEntity.getId());

        partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity
            .idSubsistemaTerminalde(UPDATED_ID_SUBSISTEMA_TERMINALDE)
            .nomSubsistemaTerminalde(UPDATED_NOM_SUBSISTEMA_TERMINALDE)
            .idSubsistemaTerminalpara(UPDATED_ID_SUBSISTEMA_TERMINALPARA)
            .nomSubsistemaTerminalpara(UPDATED_NOM_SUBSISTEMA_TERMINALPARA)
            .idEstadoTerminalde(UPDATED_ID_ESTADO_TERMINALDE)
            .nomEstadoDe(UPDATED_NOM_ESTADO_DE)
            .idEstadoTerminalpara(UPDATED_ID_ESTADO_TERMINALPARA)
            .nomEstadoPara(UPDATED_NOM_ESTADO_PARA)
            .nomSubestacaoDe(UPDATED_NOM_SUBESTACAO_DE)
            .nomSubestacaoPara(UPDATED_NOM_SUBESTACAO_PARA)
            .valNiveltensaoKv(UPDATED_VAL_NIVELTENSAO_KV)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .nomTipolinha(UPDATED_NOM_TIPOLINHA)
            .nomAgenteproprietario(UPDATED_NOM_AGENTEPROPRIETARIO)
            .nomLinhadetransmissao(UPDATED_NOM_LINHADETRANSMISSAO)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .datPrevista(UPDATED_DAT_PREVISTA)
            .valComprimento(UPDATED_VAL_COMPRIMENTO)
            .valResistencia(UPDATED_VAL_RESISTENCIA)
            .valReatancia(UPDATED_VAL_REATANCIA)
            .valShunt(UPDATED_VAL_SHUNT)
            .valCapacoperlongasemlimit(UPDATED_VAL_CAPACOPERLONGASEMLIMIT)
            .valCapacoperlongacomlimit(UPDATED_VAL_CAPACOPERLONGACOMLIMIT)
            .valCapacopercurtasemlimit(UPDATED_VAL_CAPACOPERCURTASEMLIMIT)
            .valCapacopercurtacomlimit(UPDATED_VAL_CAPACOPERCURTACOMLIMIT)
            .valCapacidadeoperveraodialonga(UPDATED_VAL_CAPACIDADEOPERVERAODIALONGA)
            .valCapacoperinvernodialonga(UPDATED_VAL_CAPACOPERINVERNODIALONGA)
            .valCapacoperinvernonoitelonga(UPDATED_VAL_CAPACOPERINVERNONOITELONGA)
            .valCapacoperveradiacurta(UPDATED_VAL_CAPACOPERVERADIACURTA)
            .valCapacoperveraonoitecurta(UPDATED_VAL_CAPACOPERVERAONOITECURTA)
            .valCapacoperinvernodiacurta(UPDATED_VAL_CAPACOPERINVERNODIACURTA)
            .valCapacoperinvernonoitecurta(UPDATED_VAL_CAPACOPERINVERNONOITECURTA);

        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsLinhasTransmissaoRedeOperacaoEntityUpdatableFieldsEquals(
            partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity,
            getPersistedOnsLinhasTransmissaoRedeOperacaoEntity(partialUpdatedOnsLinhasTransmissaoRedeOperacaoEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsLinhasTransmissaoRedeOperacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        onsLinhasTransmissaoRedeOperacaoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsLinhasTransmissaoRedeOperacaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsLinhasTransmissaoRedeOperacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        onsLinhasTransmissaoRedeOperacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsLinhasTransmissaoRedeOperacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        onsLinhasTransmissaoRedeOperacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsLinhasTransmissaoRedeOperacaoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsLinhasTransmissaoRedeOperacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsLinhasTransmissaoRedeOperacao() throws Exception {
        // Initialize the database
        insertedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.saveAndFlush(
            onsLinhasTransmissaoRedeOperacaoEntity
        );
        onsLinhasTransmissaoRedeOperacaoRepository.save(onsLinhasTransmissaoRedeOperacaoEntity);
        onsLinhasTransmissaoRedeOperacaoSearchRepository.save(onsLinhasTransmissaoRedeOperacaoEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsLinhasTransmissaoRedeOperacao
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsLinhasTransmissaoRedeOperacaoEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsLinhasTransmissaoRedeOperacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsLinhasTransmissaoRedeOperacao() throws Exception {
        // Initialize the database
        insertedOnsLinhasTransmissaoRedeOperacaoEntity = onsLinhasTransmissaoRedeOperacaoRepository.saveAndFlush(
            onsLinhasTransmissaoRedeOperacaoEntity
        );
        onsLinhasTransmissaoRedeOperacaoSearchRepository.save(onsLinhasTransmissaoRedeOperacaoEntity);

        // Search the onsLinhasTransmissaoRedeOperacao
        restOnsLinhasTransmissaoRedeOperacaoMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsLinhasTransmissaoRedeOperacaoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsLinhasTransmissaoRedeOperacaoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistemaTerminalde").value(hasItem(DEFAULT_ID_SUBSISTEMA_TERMINALDE)))
            .andExpect(jsonPath("$.[*].nomSubsistemaTerminalde").value(hasItem(DEFAULT_NOM_SUBSISTEMA_TERMINALDE)))
            .andExpect(jsonPath("$.[*].idSubsistemaTerminalpara").value(hasItem(DEFAULT_ID_SUBSISTEMA_TERMINALPARA)))
            .andExpect(jsonPath("$.[*].nomSubsistemaTerminalpara").value(hasItem(DEFAULT_NOM_SUBSISTEMA_TERMINALPARA)))
            .andExpect(jsonPath("$.[*].idEstadoTerminalde").value(hasItem(DEFAULT_ID_ESTADO_TERMINALDE)))
            .andExpect(jsonPath("$.[*].nomEstadoDe").value(hasItem(DEFAULT_NOM_ESTADO_DE)))
            .andExpect(jsonPath("$.[*].idEstadoTerminalpara").value(hasItem(DEFAULT_ID_ESTADO_TERMINALPARA)))
            .andExpect(jsonPath("$.[*].nomEstadoPara").value(hasItem(DEFAULT_NOM_ESTADO_PARA)))
            .andExpect(jsonPath("$.[*].nomSubestacaoDe").value(hasItem(DEFAULT_NOM_SUBESTACAO_DE)))
            .andExpect(jsonPath("$.[*].nomSubestacaoPara").value(hasItem(DEFAULT_NOM_SUBESTACAO_PARA)))
            .andExpect(jsonPath("$.[*].valNiveltensaoKv").value(hasItem(DEFAULT_VAL_NIVELTENSAO_KV)))
            .andExpect(jsonPath("$.[*].nomTipoderede").value(hasItem(DEFAULT_NOM_TIPODEREDE)))
            .andExpect(jsonPath("$.[*].nomTipolinha").value(hasItem(DEFAULT_NOM_TIPOLINHA)))
            .andExpect(jsonPath("$.[*].nomAgenteproprietario").value(hasItem(DEFAULT_NOM_AGENTEPROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomLinhadetransmissao").value(hasItem(DEFAULT_NOM_LINHADETRANSMISSAO)))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].datPrevista").value(hasItem(DEFAULT_DAT_PREVISTA.toString())))
            .andExpect(jsonPath("$.[*].valComprimento").value(hasItem(DEFAULT_VAL_COMPRIMENTO)))
            .andExpect(jsonPath("$.[*].valResistencia").value(hasItem(DEFAULT_VAL_RESISTENCIA)))
            .andExpect(jsonPath("$.[*].valReatancia").value(hasItem(DEFAULT_VAL_REATANCIA)))
            .andExpect(jsonPath("$.[*].valShunt").value(hasItem(DEFAULT_VAL_SHUNT)))
            .andExpect(jsonPath("$.[*].valCapacoperlongasemlimit").value(hasItem(DEFAULT_VAL_CAPACOPERLONGASEMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacoperlongacomlimit").value(hasItem(DEFAULT_VAL_CAPACOPERLONGACOMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacopercurtasemlimit").value(hasItem(DEFAULT_VAL_CAPACOPERCURTASEMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacopercurtacomlimit").value(hasItem(DEFAULT_VAL_CAPACOPERCURTACOMLIMIT)))
            .andExpect(jsonPath("$.[*].valCapacidadeoperveraodialonga").value(hasItem(DEFAULT_VAL_CAPACIDADEOPERVERAODIALONGA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernodialonga").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNODIALONGA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernonoitelonga").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNONOITELONGA)))
            .andExpect(jsonPath("$.[*].valCapacoperveradiacurta").value(hasItem(DEFAULT_VAL_CAPACOPERVERADIACURTA)))
            .andExpect(jsonPath("$.[*].valCapacoperveraonoitecurta").value(hasItem(DEFAULT_VAL_CAPACOPERVERAONOITECURTA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernodiacurta").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNODIACURTA)))
            .andExpect(jsonPath("$.[*].valCapacoperinvernonoitecurta").value(hasItem(DEFAULT_VAL_CAPACOPERINVERNONOITECURTA)));
    }

    protected long getRepositoryCount() {
        return onsLinhasTransmissaoRedeOperacaoRepository.count();
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

    protected OnsLinhasTransmissaoRedeOperacaoEntity getPersistedOnsLinhasTransmissaoRedeOperacaoEntity(
        OnsLinhasTransmissaoRedeOperacaoEntity onsLinhasTransmissaoRedeOperacao
    ) {
        return onsLinhasTransmissaoRedeOperacaoRepository.findById(onsLinhasTransmissaoRedeOperacao.getId()).orElseThrow();
    }

    protected void assertPersistedOnsLinhasTransmissaoRedeOperacaoEntityToMatchAllProperties(
        OnsLinhasTransmissaoRedeOperacaoEntity expectedOnsLinhasTransmissaoRedeOperacaoEntity
    ) {
        assertOnsLinhasTransmissaoRedeOperacaoEntityAllPropertiesEquals(
            expectedOnsLinhasTransmissaoRedeOperacaoEntity,
            getPersistedOnsLinhasTransmissaoRedeOperacaoEntity(expectedOnsLinhasTransmissaoRedeOperacaoEntity)
        );
    }

    protected void assertPersistedOnsLinhasTransmissaoRedeOperacaoEntityToMatchUpdatableProperties(
        OnsLinhasTransmissaoRedeOperacaoEntity expectedOnsLinhasTransmissaoRedeOperacaoEntity
    ) {
        assertOnsLinhasTransmissaoRedeOperacaoEntityAllUpdatablePropertiesEquals(
            expectedOnsLinhasTransmissaoRedeOperacaoEntity,
            getPersistedOnsLinhasTransmissaoRedeOperacaoEntity(expectedOnsLinhasTransmissaoRedeOperacaoEntity)
        );
    }
}
