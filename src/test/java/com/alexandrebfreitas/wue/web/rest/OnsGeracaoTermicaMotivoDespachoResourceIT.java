package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoTermicaMotivoDespachoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsGeracaoTermicaMotivoDespachoSearchRepository;
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
 * Integration tests for the {@link OnsGeracaoTermicaMotivoDespachoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsGeracaoTermicaMotivoDespachoResourceIT {

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOM_TIPOPATAMAR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOPATAMAR = "BBBBBBBBBB";

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final Integer DEFAULT_COD_USINAPLANEJAMENTO = 1;
    private static final Integer UPDATED_COD_USINAPLANEJAMENTO = 2;

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_PROGGERACAO = 1D;
    private static final Double UPDATED_VAL_PROGGERACAO = 2D;

    private static final Double DEFAULT_VAL_PROGORDEMMERITO = 1D;
    private static final Double UPDATED_VAL_PROGORDEMMERITO = 2D;

    private static final Double DEFAULT_VAL_PROGORDEMDEMERITOREF = 1D;
    private static final Double UPDATED_VAL_PROGORDEMDEMERITOREF = 2D;

    private static final Double DEFAULT_VAL_PROGORDEMDEMERITOACIMADAINFLEX = 1D;
    private static final Double UPDATED_VAL_PROGORDEMDEMERITOACIMADAINFLEX = 2D;

    private static final Double DEFAULT_VAL_PROGINFLEXIBILIDADE = 1D;
    private static final Double UPDATED_VAL_PROGINFLEXIBILIDADE = 2D;

    private static final Double DEFAULT_VAL_PROGINFLEXEMBUTMERITO = 1D;
    private static final Double UPDATED_VAL_PROGINFLEXEMBUTMERITO = 2D;

    private static final Double DEFAULT_VAL_PROGINFLEXPURA = 1D;
    private static final Double UPDATED_VAL_PROGINFLEXPURA = 2D;

    private static final Double DEFAULT_VAL_PROGRAZAOELETRICA = 1D;
    private static final Double UPDATED_VAL_PROGRAZAOELETRICA = 2D;

    private static final Double DEFAULT_VAL_PROGGARANTIAENERGETICA = 1D;
    private static final Double UPDATED_VAL_PROGGARANTIAENERGETICA = 2D;

    private static final Double DEFAULT_VAL_PROGGFOM = 1D;
    private static final Double UPDATED_VAL_PROGGFOM = 2D;

    private static final Double DEFAULT_VAL_PROGREPOSICAOPERDAS = 1D;
    private static final Double UPDATED_VAL_PROGREPOSICAOPERDAS = 2D;

    private static final Double DEFAULT_VAL_PROGEXPORTACAO = 1D;
    private static final Double UPDATED_VAL_PROGEXPORTACAO = 2D;

    private static final Double DEFAULT_VAL_PROGRESERVAPOTENCIA = 1D;
    private static final Double UPDATED_VAL_PROGRESERVAPOTENCIA = 2D;

    private static final Double DEFAULT_VAL_PROGGSUB = 1D;
    private static final Double UPDATED_VAL_PROGGSUB = 2D;

    private static final Double DEFAULT_VAL_PROGUNITCOMMITMENT = 1D;
    private static final Double UPDATED_VAL_PROGUNITCOMMITMENT = 2D;

    private static final Double DEFAULT_VAL_PROGCONSTRAINEDOFF = 1D;
    private static final Double UPDATED_VAL_PROGCONSTRAINEDOFF = 2D;

    private static final Double DEFAULT_VAL_PROGINFLEXIBILIDADEDESSEM = 1D;
    private static final Double UPDATED_VAL_PROGINFLEXIBILIDADEDESSEM = 2D;

    private static final Double DEFAULT_VAL_VERIFGERACAO = 1D;
    private static final Double UPDATED_VAL_VERIFGERACAO = 2D;

    private static final Double DEFAULT_VAL_VERIFORDEMMERITO = 1D;
    private static final Double UPDATED_VAL_VERIFORDEMMERITO = 2D;

    private static final Double DEFAULT_VAL_VERIFORDEMDEMERITOACIMADAINFLEX = 1D;
    private static final Double UPDATED_VAL_VERIFORDEMDEMERITOACIMADAINFLEX = 2D;

    private static final Double DEFAULT_VAL_VERIFINFLEXIBILIDADE = 1D;
    private static final Double UPDATED_VAL_VERIFINFLEXIBILIDADE = 2D;

    private static final Double DEFAULT_VAL_VERIFINFLEXEMBUTMERITO = 1D;
    private static final Double UPDATED_VAL_VERIFINFLEXEMBUTMERITO = 2D;

    private static final Double DEFAULT_VAL_VERIFINFLEXPURA = 1D;
    private static final Double UPDATED_VAL_VERIFINFLEXPURA = 2D;

    private static final Double DEFAULT_VAL_VERIFRAZAOELETRICA = 1D;
    private static final Double UPDATED_VAL_VERIFRAZAOELETRICA = 2D;

    private static final Double DEFAULT_VAL_VERIFGARANTIAENERGETICA = 1D;
    private static final Double UPDATED_VAL_VERIFGARANTIAENERGETICA = 2D;

    private static final Double DEFAULT_VAL_VERIFGFOM = 1D;
    private static final Double UPDATED_VAL_VERIFGFOM = 2D;

    private static final Double DEFAULT_VAL_VERIFREPOSICAOPERDAS = 1D;
    private static final Double UPDATED_VAL_VERIFREPOSICAOPERDAS = 2D;

    private static final Double DEFAULT_VAL_VERIFEXPORTACAO = 1D;
    private static final Double UPDATED_VAL_VERIFEXPORTACAO = 2D;

    private static final Double DEFAULT_VAL_FDEXP = 1D;
    private static final Double UPDATED_VAL_FDEXP = 2D;

    private static final Double DEFAULT_VAL_VERIFRESERVAPOTENCIA = 1D;
    private static final Double UPDATED_VAL_VERIFRESERVAPOTENCIA = 2D;

    private static final Integer DEFAULT_VAL_ATENDSATISFATORIORPO = 1;
    private static final Integer UPDATED_VAL_ATENDSATISFATORIORPO = 2;

    private static final Double DEFAULT_VAL_VERIFGSUB = 1D;
    private static final Double UPDATED_VAL_VERIFGSUB = 2D;

    private static final Double DEFAULT_VAL_VERIFUNITCOMMITMENT = 1D;
    private static final Double UPDATED_VAL_VERIFUNITCOMMITMENT = 2D;

    private static final Double DEFAULT_VAL_VERIFCONSTRAINEDOFF = 1D;
    private static final Double UPDATED_VAL_VERIFCONSTRAINEDOFF = 2D;

    private static final Integer DEFAULT_TIP_RESTRICAOELETRICA = 1;
    private static final Integer UPDATED_TIP_RESTRICAOELETRICA = 2;

    private static final String ENTITY_API_URL = "/api/ons-geracao-termica-motivo-despachos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-geracao-termica-motivo-despachos/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsGeracaoTermicaMotivoDespachoRepository onsGeracaoTermicaMotivoDespachoRepository;

    @Autowired
    private OnsGeracaoTermicaMotivoDespachoSearchRepository onsGeracaoTermicaMotivoDespachoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsGeracaoTermicaMotivoDespachoMockMvc;

    private OnsGeracaoTermicaMotivoDespachoEntity onsGeracaoTermicaMotivoDespachoEntity;

    private OnsGeracaoTermicaMotivoDespachoEntity insertedOnsGeracaoTermicaMotivoDespachoEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsGeracaoTermicaMotivoDespachoEntity createEntity() {
        return new OnsGeracaoTermicaMotivoDespachoEntity()
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .nomTipopatamar(DEFAULT_NOM_TIPOPATAMAR)
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .nomUsina(DEFAULT_NOM_USINA)
            .codUsinaplanejamento(DEFAULT_COD_USINAPLANEJAMENTO)
            .ceg(DEFAULT_CEG)
            .valProggeracao(DEFAULT_VAL_PROGGERACAO)
            .valProgordemmerito(DEFAULT_VAL_PROGORDEMMERITO)
            .valProgordemdemeritoref(DEFAULT_VAL_PROGORDEMDEMERITOREF)
            .valProgordemdemeritoacimadainflex(DEFAULT_VAL_PROGORDEMDEMERITOACIMADAINFLEX)
            .valProginflexibilidade(DEFAULT_VAL_PROGINFLEXIBILIDADE)
            .valProginflexembutmerito(DEFAULT_VAL_PROGINFLEXEMBUTMERITO)
            .valProginflexpura(DEFAULT_VAL_PROGINFLEXPURA)
            .valPrograzaoeletrica(DEFAULT_VAL_PROGRAZAOELETRICA)
            .valProggarantiaenergetica(DEFAULT_VAL_PROGGARANTIAENERGETICA)
            .valProggfom(DEFAULT_VAL_PROGGFOM)
            .valProgreposicaoperdas(DEFAULT_VAL_PROGREPOSICAOPERDAS)
            .valProgexportacao(DEFAULT_VAL_PROGEXPORTACAO)
            .valProgreservapotencia(DEFAULT_VAL_PROGRESERVAPOTENCIA)
            .valProggsub(DEFAULT_VAL_PROGGSUB)
            .valProgunitcommitment(DEFAULT_VAL_PROGUNITCOMMITMENT)
            .valProgconstrainedoff(DEFAULT_VAL_PROGCONSTRAINEDOFF)
            .valProginflexibilidadedessem(DEFAULT_VAL_PROGINFLEXIBILIDADEDESSEM)
            .valVerifgeracao(DEFAULT_VAL_VERIFGERACAO)
            .valVerifordemmerito(DEFAULT_VAL_VERIFORDEMMERITO)
            .valVerifordemdemeritoacimadainflex(DEFAULT_VAL_VERIFORDEMDEMERITOACIMADAINFLEX)
            .valVerifinflexibilidade(DEFAULT_VAL_VERIFINFLEXIBILIDADE)
            .valVerifinflexembutmerito(DEFAULT_VAL_VERIFINFLEXEMBUTMERITO)
            .valVerifinflexpura(DEFAULT_VAL_VERIFINFLEXPURA)
            .valVerifrazaoeletrica(DEFAULT_VAL_VERIFRAZAOELETRICA)
            .valVerifgarantiaenergetica(DEFAULT_VAL_VERIFGARANTIAENERGETICA)
            .valVerifgfom(DEFAULT_VAL_VERIFGFOM)
            .valVerifreposicaoperdas(DEFAULT_VAL_VERIFREPOSICAOPERDAS)
            .valVerifexportacao(DEFAULT_VAL_VERIFEXPORTACAO)
            .valFdexp(DEFAULT_VAL_FDEXP)
            .valVerifreservapotencia(DEFAULT_VAL_VERIFRESERVAPOTENCIA)
            .valAtendsatisfatoriorpo(DEFAULT_VAL_ATENDSATISFATORIORPO)
            .valVerifgsub(DEFAULT_VAL_VERIFGSUB)
            .valVerifunitcommitment(DEFAULT_VAL_VERIFUNITCOMMITMENT)
            .valVerifconstrainedoff(DEFAULT_VAL_VERIFCONSTRAINEDOFF)
            .tipRestricaoeletrica(DEFAULT_TIP_RESTRICAOELETRICA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsGeracaoTermicaMotivoDespachoEntity createUpdatedEntity() {
        return new OnsGeracaoTermicaMotivoDespachoEntity()
            .dinInstante(UPDATED_DIN_INSTANTE)
            .nomTipopatamar(UPDATED_NOM_TIPOPATAMAR)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomUsina(UPDATED_NOM_USINA)
            .codUsinaplanejamento(UPDATED_COD_USINAPLANEJAMENTO)
            .ceg(UPDATED_CEG)
            .valProggeracao(UPDATED_VAL_PROGGERACAO)
            .valProgordemmerito(UPDATED_VAL_PROGORDEMMERITO)
            .valProgordemdemeritoref(UPDATED_VAL_PROGORDEMDEMERITOREF)
            .valProgordemdemeritoacimadainflex(UPDATED_VAL_PROGORDEMDEMERITOACIMADAINFLEX)
            .valProginflexibilidade(UPDATED_VAL_PROGINFLEXIBILIDADE)
            .valProginflexembutmerito(UPDATED_VAL_PROGINFLEXEMBUTMERITO)
            .valProginflexpura(UPDATED_VAL_PROGINFLEXPURA)
            .valPrograzaoeletrica(UPDATED_VAL_PROGRAZAOELETRICA)
            .valProggarantiaenergetica(UPDATED_VAL_PROGGARANTIAENERGETICA)
            .valProggfom(UPDATED_VAL_PROGGFOM)
            .valProgreposicaoperdas(UPDATED_VAL_PROGREPOSICAOPERDAS)
            .valProgexportacao(UPDATED_VAL_PROGEXPORTACAO)
            .valProgreservapotencia(UPDATED_VAL_PROGRESERVAPOTENCIA)
            .valProggsub(UPDATED_VAL_PROGGSUB)
            .valProgunitcommitment(UPDATED_VAL_PROGUNITCOMMITMENT)
            .valProgconstrainedoff(UPDATED_VAL_PROGCONSTRAINEDOFF)
            .valProginflexibilidadedessem(UPDATED_VAL_PROGINFLEXIBILIDADEDESSEM)
            .valVerifgeracao(UPDATED_VAL_VERIFGERACAO)
            .valVerifordemmerito(UPDATED_VAL_VERIFORDEMMERITO)
            .valVerifordemdemeritoacimadainflex(UPDATED_VAL_VERIFORDEMDEMERITOACIMADAINFLEX)
            .valVerifinflexibilidade(UPDATED_VAL_VERIFINFLEXIBILIDADE)
            .valVerifinflexembutmerito(UPDATED_VAL_VERIFINFLEXEMBUTMERITO)
            .valVerifinflexpura(UPDATED_VAL_VERIFINFLEXPURA)
            .valVerifrazaoeletrica(UPDATED_VAL_VERIFRAZAOELETRICA)
            .valVerifgarantiaenergetica(UPDATED_VAL_VERIFGARANTIAENERGETICA)
            .valVerifgfom(UPDATED_VAL_VERIFGFOM)
            .valVerifreposicaoperdas(UPDATED_VAL_VERIFREPOSICAOPERDAS)
            .valVerifexportacao(UPDATED_VAL_VERIFEXPORTACAO)
            .valFdexp(UPDATED_VAL_FDEXP)
            .valVerifreservapotencia(UPDATED_VAL_VERIFRESERVAPOTENCIA)
            .valAtendsatisfatoriorpo(UPDATED_VAL_ATENDSATISFATORIORPO)
            .valVerifgsub(UPDATED_VAL_VERIFGSUB)
            .valVerifunitcommitment(UPDATED_VAL_VERIFUNITCOMMITMENT)
            .valVerifconstrainedoff(UPDATED_VAL_VERIFCONSTRAINEDOFF)
            .tipRestricaoeletrica(UPDATED_TIP_RESTRICAOELETRICA);
    }

    @BeforeEach
    public void initTest() {
        onsGeracaoTermicaMotivoDespachoEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsGeracaoTermicaMotivoDespachoEntity != null) {
            onsGeracaoTermicaMotivoDespachoRepository.delete(insertedOnsGeracaoTermicaMotivoDespachoEntity);
            onsGeracaoTermicaMotivoDespachoSearchRepository.delete(insertedOnsGeracaoTermicaMotivoDespachoEntity);
            insertedOnsGeracaoTermicaMotivoDespachoEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsGeracaoTermicaMotivoDespacho() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        // Create the OnsGeracaoTermicaMotivoDespacho
        var returnedOnsGeracaoTermicaMotivoDespachoEntity = om.readValue(
            restOnsGeracaoTermicaMotivoDespachoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsGeracaoTermicaMotivoDespachoEntity.class
        );

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsGeracaoTermicaMotivoDespachoEntityUpdatableFieldsEquals(
            returnedOnsGeracaoTermicaMotivoDespachoEntity,
            getPersistedOnsGeracaoTermicaMotivoDespachoEntity(returnedOnsGeracaoTermicaMotivoDespachoEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsGeracaoTermicaMotivoDespachoEntity = returnedOnsGeracaoTermicaMotivoDespachoEntity;
    }

    @Test
    @Transactional
    void createOnsGeracaoTermicaMotivoDespachoWithExistingId() throws Exception {
        // Create the OnsGeracaoTermicaMotivoDespacho with an existing ID
        onsGeracaoTermicaMotivoDespachoEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsGeracaoTermicaMotivoDespachos() throws Exception {
        // Initialize the database
        insertedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.saveAndFlush(
            onsGeracaoTermicaMotivoDespachoEntity
        );

        // Get all the onsGeracaoTermicaMotivoDespachoList
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsGeracaoTermicaMotivoDespachoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].nomTipopatamar").value(hasItem(DEFAULT_NOM_TIPOPATAMAR)))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].codUsinaplanejamento").value(hasItem(DEFAULT_COD_USINAPLANEJAMENTO)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valProggeracao").value(hasItem(DEFAULT_VAL_PROGGERACAO)))
            .andExpect(jsonPath("$.[*].valProgordemmerito").value(hasItem(DEFAULT_VAL_PROGORDEMMERITO)))
            .andExpect(jsonPath("$.[*].valProgordemdemeritoref").value(hasItem(DEFAULT_VAL_PROGORDEMDEMERITOREF)))
            .andExpect(jsonPath("$.[*].valProgordemdemeritoacimadainflex").value(hasItem(DEFAULT_VAL_PROGORDEMDEMERITOACIMADAINFLEX)))
            .andExpect(jsonPath("$.[*].valProginflexibilidade").value(hasItem(DEFAULT_VAL_PROGINFLEXIBILIDADE)))
            .andExpect(jsonPath("$.[*].valProginflexembutmerito").value(hasItem(DEFAULT_VAL_PROGINFLEXEMBUTMERITO)))
            .andExpect(jsonPath("$.[*].valProginflexpura").value(hasItem(DEFAULT_VAL_PROGINFLEXPURA)))
            .andExpect(jsonPath("$.[*].valPrograzaoeletrica").value(hasItem(DEFAULT_VAL_PROGRAZAOELETRICA)))
            .andExpect(jsonPath("$.[*].valProggarantiaenergetica").value(hasItem(DEFAULT_VAL_PROGGARANTIAENERGETICA)))
            .andExpect(jsonPath("$.[*].valProggfom").value(hasItem(DEFAULT_VAL_PROGGFOM)))
            .andExpect(jsonPath("$.[*].valProgreposicaoperdas").value(hasItem(DEFAULT_VAL_PROGREPOSICAOPERDAS)))
            .andExpect(jsonPath("$.[*].valProgexportacao").value(hasItem(DEFAULT_VAL_PROGEXPORTACAO)))
            .andExpect(jsonPath("$.[*].valProgreservapotencia").value(hasItem(DEFAULT_VAL_PROGRESERVAPOTENCIA)))
            .andExpect(jsonPath("$.[*].valProggsub").value(hasItem(DEFAULT_VAL_PROGGSUB)))
            .andExpect(jsonPath("$.[*].valProgunitcommitment").value(hasItem(DEFAULT_VAL_PROGUNITCOMMITMENT)))
            .andExpect(jsonPath("$.[*].valProgconstrainedoff").value(hasItem(DEFAULT_VAL_PROGCONSTRAINEDOFF)))
            .andExpect(jsonPath("$.[*].valProginflexibilidadedessem").value(hasItem(DEFAULT_VAL_PROGINFLEXIBILIDADEDESSEM)))
            .andExpect(jsonPath("$.[*].valVerifgeracao").value(hasItem(DEFAULT_VAL_VERIFGERACAO)))
            .andExpect(jsonPath("$.[*].valVerifordemmerito").value(hasItem(DEFAULT_VAL_VERIFORDEMMERITO)))
            .andExpect(jsonPath("$.[*].valVerifordemdemeritoacimadainflex").value(hasItem(DEFAULT_VAL_VERIFORDEMDEMERITOACIMADAINFLEX)))
            .andExpect(jsonPath("$.[*].valVerifinflexibilidade").value(hasItem(DEFAULT_VAL_VERIFINFLEXIBILIDADE)))
            .andExpect(jsonPath("$.[*].valVerifinflexembutmerito").value(hasItem(DEFAULT_VAL_VERIFINFLEXEMBUTMERITO)))
            .andExpect(jsonPath("$.[*].valVerifinflexpura").value(hasItem(DEFAULT_VAL_VERIFINFLEXPURA)))
            .andExpect(jsonPath("$.[*].valVerifrazaoeletrica").value(hasItem(DEFAULT_VAL_VERIFRAZAOELETRICA)))
            .andExpect(jsonPath("$.[*].valVerifgarantiaenergetica").value(hasItem(DEFAULT_VAL_VERIFGARANTIAENERGETICA)))
            .andExpect(jsonPath("$.[*].valVerifgfom").value(hasItem(DEFAULT_VAL_VERIFGFOM)))
            .andExpect(jsonPath("$.[*].valVerifreposicaoperdas").value(hasItem(DEFAULT_VAL_VERIFREPOSICAOPERDAS)))
            .andExpect(jsonPath("$.[*].valVerifexportacao").value(hasItem(DEFAULT_VAL_VERIFEXPORTACAO)))
            .andExpect(jsonPath("$.[*].valFdexp").value(hasItem(DEFAULT_VAL_FDEXP)))
            .andExpect(jsonPath("$.[*].valVerifreservapotencia").value(hasItem(DEFAULT_VAL_VERIFRESERVAPOTENCIA)))
            .andExpect(jsonPath("$.[*].valAtendsatisfatoriorpo").value(hasItem(DEFAULT_VAL_ATENDSATISFATORIORPO)))
            .andExpect(jsonPath("$.[*].valVerifgsub").value(hasItem(DEFAULT_VAL_VERIFGSUB)))
            .andExpect(jsonPath("$.[*].valVerifunitcommitment").value(hasItem(DEFAULT_VAL_VERIFUNITCOMMITMENT)))
            .andExpect(jsonPath("$.[*].valVerifconstrainedoff").value(hasItem(DEFAULT_VAL_VERIFCONSTRAINEDOFF)))
            .andExpect(jsonPath("$.[*].tipRestricaoeletrica").value(hasItem(DEFAULT_TIP_RESTRICAOELETRICA)));
    }

    @Test
    @Transactional
    void getOnsGeracaoTermicaMotivoDespacho() throws Exception {
        // Initialize the database
        insertedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.saveAndFlush(
            onsGeracaoTermicaMotivoDespachoEntity
        );

        // Get the onsGeracaoTermicaMotivoDespacho
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(get(ENTITY_API_URL_ID, onsGeracaoTermicaMotivoDespachoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsGeracaoTermicaMotivoDespachoEntity.getId().intValue()))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.nomTipopatamar").value(DEFAULT_NOM_TIPOPATAMAR))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.codUsinaplanejamento").value(DEFAULT_COD_USINAPLANEJAMENTO))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.valProggeracao").value(DEFAULT_VAL_PROGGERACAO))
            .andExpect(jsonPath("$.valProgordemmerito").value(DEFAULT_VAL_PROGORDEMMERITO))
            .andExpect(jsonPath("$.valProgordemdemeritoref").value(DEFAULT_VAL_PROGORDEMDEMERITOREF))
            .andExpect(jsonPath("$.valProgordemdemeritoacimadainflex").value(DEFAULT_VAL_PROGORDEMDEMERITOACIMADAINFLEX))
            .andExpect(jsonPath("$.valProginflexibilidade").value(DEFAULT_VAL_PROGINFLEXIBILIDADE))
            .andExpect(jsonPath("$.valProginflexembutmerito").value(DEFAULT_VAL_PROGINFLEXEMBUTMERITO))
            .andExpect(jsonPath("$.valProginflexpura").value(DEFAULT_VAL_PROGINFLEXPURA))
            .andExpect(jsonPath("$.valPrograzaoeletrica").value(DEFAULT_VAL_PROGRAZAOELETRICA))
            .andExpect(jsonPath("$.valProggarantiaenergetica").value(DEFAULT_VAL_PROGGARANTIAENERGETICA))
            .andExpect(jsonPath("$.valProggfom").value(DEFAULT_VAL_PROGGFOM))
            .andExpect(jsonPath("$.valProgreposicaoperdas").value(DEFAULT_VAL_PROGREPOSICAOPERDAS))
            .andExpect(jsonPath("$.valProgexportacao").value(DEFAULT_VAL_PROGEXPORTACAO))
            .andExpect(jsonPath("$.valProgreservapotencia").value(DEFAULT_VAL_PROGRESERVAPOTENCIA))
            .andExpect(jsonPath("$.valProggsub").value(DEFAULT_VAL_PROGGSUB))
            .andExpect(jsonPath("$.valProgunitcommitment").value(DEFAULT_VAL_PROGUNITCOMMITMENT))
            .andExpect(jsonPath("$.valProgconstrainedoff").value(DEFAULT_VAL_PROGCONSTRAINEDOFF))
            .andExpect(jsonPath("$.valProginflexibilidadedessem").value(DEFAULT_VAL_PROGINFLEXIBILIDADEDESSEM))
            .andExpect(jsonPath("$.valVerifgeracao").value(DEFAULT_VAL_VERIFGERACAO))
            .andExpect(jsonPath("$.valVerifordemmerito").value(DEFAULT_VAL_VERIFORDEMMERITO))
            .andExpect(jsonPath("$.valVerifordemdemeritoacimadainflex").value(DEFAULT_VAL_VERIFORDEMDEMERITOACIMADAINFLEX))
            .andExpect(jsonPath("$.valVerifinflexibilidade").value(DEFAULT_VAL_VERIFINFLEXIBILIDADE))
            .andExpect(jsonPath("$.valVerifinflexembutmerito").value(DEFAULT_VAL_VERIFINFLEXEMBUTMERITO))
            .andExpect(jsonPath("$.valVerifinflexpura").value(DEFAULT_VAL_VERIFINFLEXPURA))
            .andExpect(jsonPath("$.valVerifrazaoeletrica").value(DEFAULT_VAL_VERIFRAZAOELETRICA))
            .andExpect(jsonPath("$.valVerifgarantiaenergetica").value(DEFAULT_VAL_VERIFGARANTIAENERGETICA))
            .andExpect(jsonPath("$.valVerifgfom").value(DEFAULT_VAL_VERIFGFOM))
            .andExpect(jsonPath("$.valVerifreposicaoperdas").value(DEFAULT_VAL_VERIFREPOSICAOPERDAS))
            .andExpect(jsonPath("$.valVerifexportacao").value(DEFAULT_VAL_VERIFEXPORTACAO))
            .andExpect(jsonPath("$.valFdexp").value(DEFAULT_VAL_FDEXP))
            .andExpect(jsonPath("$.valVerifreservapotencia").value(DEFAULT_VAL_VERIFRESERVAPOTENCIA))
            .andExpect(jsonPath("$.valAtendsatisfatoriorpo").value(DEFAULT_VAL_ATENDSATISFATORIORPO))
            .andExpect(jsonPath("$.valVerifgsub").value(DEFAULT_VAL_VERIFGSUB))
            .andExpect(jsonPath("$.valVerifunitcommitment").value(DEFAULT_VAL_VERIFUNITCOMMITMENT))
            .andExpect(jsonPath("$.valVerifconstrainedoff").value(DEFAULT_VAL_VERIFCONSTRAINEDOFF))
            .andExpect(jsonPath("$.tipRestricaoeletrica").value(DEFAULT_TIP_RESTRICAOELETRICA));
    }

    @Test
    @Transactional
    void getNonExistingOnsGeracaoTermicaMotivoDespacho() throws Exception {
        // Get the onsGeracaoTermicaMotivoDespacho
        restOnsGeracaoTermicaMotivoDespachoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsGeracaoTermicaMotivoDespacho() throws Exception {
        // Initialize the database
        insertedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.saveAndFlush(
            onsGeracaoTermicaMotivoDespachoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsGeracaoTermicaMotivoDespachoSearchRepository.save(onsGeracaoTermicaMotivoDespachoEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());

        // Update the onsGeracaoTermicaMotivoDespacho
        OnsGeracaoTermicaMotivoDespachoEntity updatedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository
            .findById(onsGeracaoTermicaMotivoDespachoEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsGeracaoTermicaMotivoDespachoEntity are not directly saved in db
        em.detach(updatedOnsGeracaoTermicaMotivoDespachoEntity);
        updatedOnsGeracaoTermicaMotivoDespachoEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .nomTipopatamar(UPDATED_NOM_TIPOPATAMAR)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomUsina(UPDATED_NOM_USINA)
            .codUsinaplanejamento(UPDATED_COD_USINAPLANEJAMENTO)
            .ceg(UPDATED_CEG)
            .valProggeracao(UPDATED_VAL_PROGGERACAO)
            .valProgordemmerito(UPDATED_VAL_PROGORDEMMERITO)
            .valProgordemdemeritoref(UPDATED_VAL_PROGORDEMDEMERITOREF)
            .valProgordemdemeritoacimadainflex(UPDATED_VAL_PROGORDEMDEMERITOACIMADAINFLEX)
            .valProginflexibilidade(UPDATED_VAL_PROGINFLEXIBILIDADE)
            .valProginflexembutmerito(UPDATED_VAL_PROGINFLEXEMBUTMERITO)
            .valProginflexpura(UPDATED_VAL_PROGINFLEXPURA)
            .valPrograzaoeletrica(UPDATED_VAL_PROGRAZAOELETRICA)
            .valProggarantiaenergetica(UPDATED_VAL_PROGGARANTIAENERGETICA)
            .valProggfom(UPDATED_VAL_PROGGFOM)
            .valProgreposicaoperdas(UPDATED_VAL_PROGREPOSICAOPERDAS)
            .valProgexportacao(UPDATED_VAL_PROGEXPORTACAO)
            .valProgreservapotencia(UPDATED_VAL_PROGRESERVAPOTENCIA)
            .valProggsub(UPDATED_VAL_PROGGSUB)
            .valProgunitcommitment(UPDATED_VAL_PROGUNITCOMMITMENT)
            .valProgconstrainedoff(UPDATED_VAL_PROGCONSTRAINEDOFF)
            .valProginflexibilidadedessem(UPDATED_VAL_PROGINFLEXIBILIDADEDESSEM)
            .valVerifgeracao(UPDATED_VAL_VERIFGERACAO)
            .valVerifordemmerito(UPDATED_VAL_VERIFORDEMMERITO)
            .valVerifordemdemeritoacimadainflex(UPDATED_VAL_VERIFORDEMDEMERITOACIMADAINFLEX)
            .valVerifinflexibilidade(UPDATED_VAL_VERIFINFLEXIBILIDADE)
            .valVerifinflexembutmerito(UPDATED_VAL_VERIFINFLEXEMBUTMERITO)
            .valVerifinflexpura(UPDATED_VAL_VERIFINFLEXPURA)
            .valVerifrazaoeletrica(UPDATED_VAL_VERIFRAZAOELETRICA)
            .valVerifgarantiaenergetica(UPDATED_VAL_VERIFGARANTIAENERGETICA)
            .valVerifgfom(UPDATED_VAL_VERIFGFOM)
            .valVerifreposicaoperdas(UPDATED_VAL_VERIFREPOSICAOPERDAS)
            .valVerifexportacao(UPDATED_VAL_VERIFEXPORTACAO)
            .valFdexp(UPDATED_VAL_FDEXP)
            .valVerifreservapotencia(UPDATED_VAL_VERIFRESERVAPOTENCIA)
            .valAtendsatisfatoriorpo(UPDATED_VAL_ATENDSATISFATORIORPO)
            .valVerifgsub(UPDATED_VAL_VERIFGSUB)
            .valVerifunitcommitment(UPDATED_VAL_VERIFUNITCOMMITMENT)
            .valVerifconstrainedoff(UPDATED_VAL_VERIFCONSTRAINEDOFF)
            .tipRestricaoeletrica(UPDATED_TIP_RESTRICAOELETRICA);

        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsGeracaoTermicaMotivoDespachoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsGeracaoTermicaMotivoDespachoEntityToMatchAllProperties(updatedOnsGeracaoTermicaMotivoDespachoEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsGeracaoTermicaMotivoDespachoEntity> onsGeracaoTermicaMotivoDespachoSearchList = Streamable.of(
                    onsGeracaoTermicaMotivoDespachoSearchRepository.findAll()
                ).toList();
                OnsGeracaoTermicaMotivoDespachoEntity testOnsGeracaoTermicaMotivoDespachoSearch =
                    onsGeracaoTermicaMotivoDespachoSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsGeracaoTermicaMotivoDespachoEntityAllPropertiesEquals(
                    testOnsGeracaoTermicaMotivoDespachoSearch,
                    updatedOnsGeracaoTermicaMotivoDespachoEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsGeracaoTermicaMotivoDespacho() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        onsGeracaoTermicaMotivoDespachoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsGeracaoTermicaMotivoDespachoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsGeracaoTermicaMotivoDespacho() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        onsGeracaoTermicaMotivoDespachoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsGeracaoTermicaMotivoDespacho() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        onsGeracaoTermicaMotivoDespachoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsGeracaoTermicaMotivoDespachoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.saveAndFlush(
            onsGeracaoTermicaMotivoDespachoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsGeracaoTermicaMotivoDespacho using partial update
        OnsGeracaoTermicaMotivoDespachoEntity partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity =
            new OnsGeracaoTermicaMotivoDespachoEntity();
        partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity.setId(onsGeracaoTermicaMotivoDespachoEntity.getId());

        partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomUsina(UPDATED_NOM_USINA)
            .codUsinaplanejamento(UPDATED_COD_USINAPLANEJAMENTO)
            .valProgordemdemeritoacimadainflex(UPDATED_VAL_PROGORDEMDEMERITOACIMADAINFLEX)
            .valProginflexibilidade(UPDATED_VAL_PROGINFLEXIBILIDADE)
            .valProginflexembutmerito(UPDATED_VAL_PROGINFLEXEMBUTMERITO)
            .valPrograzaoeletrica(UPDATED_VAL_PROGRAZAOELETRICA)
            .valProggarantiaenergetica(UPDATED_VAL_PROGGARANTIAENERGETICA)
            .valProggfom(UPDATED_VAL_PROGGFOM)
            .valProginflexibilidadedessem(UPDATED_VAL_PROGINFLEXIBILIDADEDESSEM)
            .valVerifordemmerito(UPDATED_VAL_VERIFORDEMMERITO)
            .valVerifinflexibilidade(UPDATED_VAL_VERIFINFLEXIBILIDADE)
            .valVerifinflexembutmerito(UPDATED_VAL_VERIFINFLEXEMBUTMERITO)
            .valVerifinflexpura(UPDATED_VAL_VERIFINFLEXPURA)
            .valVerifrazaoeletrica(UPDATED_VAL_VERIFRAZAOELETRICA)
            .valVerifgfom(UPDATED_VAL_VERIFGFOM)
            .valVerifreposicaoperdas(UPDATED_VAL_VERIFREPOSICAOPERDAS)
            .valVerifexportacao(UPDATED_VAL_VERIFEXPORTACAO)
            .valAtendsatisfatoriorpo(UPDATED_VAL_ATENDSATISFATORIORPO)
            .valVerifgsub(UPDATED_VAL_VERIFGSUB)
            .valVerifunitcommitment(UPDATED_VAL_VERIFUNITCOMMITMENT)
            .valVerifconstrainedoff(UPDATED_VAL_VERIFCONSTRAINEDOFF);

        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsGeracaoTermicaMotivoDespachoEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity, onsGeracaoTermicaMotivoDespachoEntity),
            getPersistedOnsGeracaoTermicaMotivoDespachoEntity(onsGeracaoTermicaMotivoDespachoEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsGeracaoTermicaMotivoDespachoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.saveAndFlush(
            onsGeracaoTermicaMotivoDespachoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsGeracaoTermicaMotivoDespacho using partial update
        OnsGeracaoTermicaMotivoDespachoEntity partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity =
            new OnsGeracaoTermicaMotivoDespachoEntity();
        partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity.setId(onsGeracaoTermicaMotivoDespachoEntity.getId());

        partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .nomTipopatamar(UPDATED_NOM_TIPOPATAMAR)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomUsina(UPDATED_NOM_USINA)
            .codUsinaplanejamento(UPDATED_COD_USINAPLANEJAMENTO)
            .ceg(UPDATED_CEG)
            .valProggeracao(UPDATED_VAL_PROGGERACAO)
            .valProgordemmerito(UPDATED_VAL_PROGORDEMMERITO)
            .valProgordemdemeritoref(UPDATED_VAL_PROGORDEMDEMERITOREF)
            .valProgordemdemeritoacimadainflex(UPDATED_VAL_PROGORDEMDEMERITOACIMADAINFLEX)
            .valProginflexibilidade(UPDATED_VAL_PROGINFLEXIBILIDADE)
            .valProginflexembutmerito(UPDATED_VAL_PROGINFLEXEMBUTMERITO)
            .valProginflexpura(UPDATED_VAL_PROGINFLEXPURA)
            .valPrograzaoeletrica(UPDATED_VAL_PROGRAZAOELETRICA)
            .valProggarantiaenergetica(UPDATED_VAL_PROGGARANTIAENERGETICA)
            .valProggfom(UPDATED_VAL_PROGGFOM)
            .valProgreposicaoperdas(UPDATED_VAL_PROGREPOSICAOPERDAS)
            .valProgexportacao(UPDATED_VAL_PROGEXPORTACAO)
            .valProgreservapotencia(UPDATED_VAL_PROGRESERVAPOTENCIA)
            .valProggsub(UPDATED_VAL_PROGGSUB)
            .valProgunitcommitment(UPDATED_VAL_PROGUNITCOMMITMENT)
            .valProgconstrainedoff(UPDATED_VAL_PROGCONSTRAINEDOFF)
            .valProginflexibilidadedessem(UPDATED_VAL_PROGINFLEXIBILIDADEDESSEM)
            .valVerifgeracao(UPDATED_VAL_VERIFGERACAO)
            .valVerifordemmerito(UPDATED_VAL_VERIFORDEMMERITO)
            .valVerifordemdemeritoacimadainflex(UPDATED_VAL_VERIFORDEMDEMERITOACIMADAINFLEX)
            .valVerifinflexibilidade(UPDATED_VAL_VERIFINFLEXIBILIDADE)
            .valVerifinflexembutmerito(UPDATED_VAL_VERIFINFLEXEMBUTMERITO)
            .valVerifinflexpura(UPDATED_VAL_VERIFINFLEXPURA)
            .valVerifrazaoeletrica(UPDATED_VAL_VERIFRAZAOELETRICA)
            .valVerifgarantiaenergetica(UPDATED_VAL_VERIFGARANTIAENERGETICA)
            .valVerifgfom(UPDATED_VAL_VERIFGFOM)
            .valVerifreposicaoperdas(UPDATED_VAL_VERIFREPOSICAOPERDAS)
            .valVerifexportacao(UPDATED_VAL_VERIFEXPORTACAO)
            .valFdexp(UPDATED_VAL_FDEXP)
            .valVerifreservapotencia(UPDATED_VAL_VERIFRESERVAPOTENCIA)
            .valAtendsatisfatoriorpo(UPDATED_VAL_ATENDSATISFATORIORPO)
            .valVerifgsub(UPDATED_VAL_VERIFGSUB)
            .valVerifunitcommitment(UPDATED_VAL_VERIFUNITCOMMITMENT)
            .valVerifconstrainedoff(UPDATED_VAL_VERIFCONSTRAINEDOFF)
            .tipRestricaoeletrica(UPDATED_TIP_RESTRICAOELETRICA);

        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsGeracaoTermicaMotivoDespachoEntityUpdatableFieldsEquals(
            partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity,
            getPersistedOnsGeracaoTermicaMotivoDespachoEntity(partialUpdatedOnsGeracaoTermicaMotivoDespachoEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsGeracaoTermicaMotivoDespacho() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        onsGeracaoTermicaMotivoDespachoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsGeracaoTermicaMotivoDespachoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsGeracaoTermicaMotivoDespacho() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        onsGeracaoTermicaMotivoDespachoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsGeracaoTermicaMotivoDespacho() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        onsGeracaoTermicaMotivoDespachoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoTermicaMotivoDespachoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsGeracaoTermicaMotivoDespacho in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsGeracaoTermicaMotivoDespacho() throws Exception {
        // Initialize the database
        insertedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.saveAndFlush(
            onsGeracaoTermicaMotivoDespachoEntity
        );
        onsGeracaoTermicaMotivoDespachoRepository.save(onsGeracaoTermicaMotivoDespachoEntity);
        onsGeracaoTermicaMotivoDespachoSearchRepository.save(onsGeracaoTermicaMotivoDespachoEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsGeracaoTermicaMotivoDespacho
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsGeracaoTermicaMotivoDespachoEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoTermicaMotivoDespachoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsGeracaoTermicaMotivoDespacho() throws Exception {
        // Initialize the database
        insertedOnsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.saveAndFlush(
            onsGeracaoTermicaMotivoDespachoEntity
        );
        onsGeracaoTermicaMotivoDespachoSearchRepository.save(onsGeracaoTermicaMotivoDespachoEntity);

        // Search the onsGeracaoTermicaMotivoDespacho
        restOnsGeracaoTermicaMotivoDespachoMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsGeracaoTermicaMotivoDespachoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsGeracaoTermicaMotivoDespachoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].nomTipopatamar").value(hasItem(DEFAULT_NOM_TIPOPATAMAR)))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].codUsinaplanejamento").value(hasItem(DEFAULT_COD_USINAPLANEJAMENTO)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valProggeracao").value(hasItem(DEFAULT_VAL_PROGGERACAO)))
            .andExpect(jsonPath("$.[*].valProgordemmerito").value(hasItem(DEFAULT_VAL_PROGORDEMMERITO)))
            .andExpect(jsonPath("$.[*].valProgordemdemeritoref").value(hasItem(DEFAULT_VAL_PROGORDEMDEMERITOREF)))
            .andExpect(jsonPath("$.[*].valProgordemdemeritoacimadainflex").value(hasItem(DEFAULT_VAL_PROGORDEMDEMERITOACIMADAINFLEX)))
            .andExpect(jsonPath("$.[*].valProginflexibilidade").value(hasItem(DEFAULT_VAL_PROGINFLEXIBILIDADE)))
            .andExpect(jsonPath("$.[*].valProginflexembutmerito").value(hasItem(DEFAULT_VAL_PROGINFLEXEMBUTMERITO)))
            .andExpect(jsonPath("$.[*].valProginflexpura").value(hasItem(DEFAULT_VAL_PROGINFLEXPURA)))
            .andExpect(jsonPath("$.[*].valPrograzaoeletrica").value(hasItem(DEFAULT_VAL_PROGRAZAOELETRICA)))
            .andExpect(jsonPath("$.[*].valProggarantiaenergetica").value(hasItem(DEFAULT_VAL_PROGGARANTIAENERGETICA)))
            .andExpect(jsonPath("$.[*].valProggfom").value(hasItem(DEFAULT_VAL_PROGGFOM)))
            .andExpect(jsonPath("$.[*].valProgreposicaoperdas").value(hasItem(DEFAULT_VAL_PROGREPOSICAOPERDAS)))
            .andExpect(jsonPath("$.[*].valProgexportacao").value(hasItem(DEFAULT_VAL_PROGEXPORTACAO)))
            .andExpect(jsonPath("$.[*].valProgreservapotencia").value(hasItem(DEFAULT_VAL_PROGRESERVAPOTENCIA)))
            .andExpect(jsonPath("$.[*].valProggsub").value(hasItem(DEFAULT_VAL_PROGGSUB)))
            .andExpect(jsonPath("$.[*].valProgunitcommitment").value(hasItem(DEFAULT_VAL_PROGUNITCOMMITMENT)))
            .andExpect(jsonPath("$.[*].valProgconstrainedoff").value(hasItem(DEFAULT_VAL_PROGCONSTRAINEDOFF)))
            .andExpect(jsonPath("$.[*].valProginflexibilidadedessem").value(hasItem(DEFAULT_VAL_PROGINFLEXIBILIDADEDESSEM)))
            .andExpect(jsonPath("$.[*].valVerifgeracao").value(hasItem(DEFAULT_VAL_VERIFGERACAO)))
            .andExpect(jsonPath("$.[*].valVerifordemmerito").value(hasItem(DEFAULT_VAL_VERIFORDEMMERITO)))
            .andExpect(jsonPath("$.[*].valVerifordemdemeritoacimadainflex").value(hasItem(DEFAULT_VAL_VERIFORDEMDEMERITOACIMADAINFLEX)))
            .andExpect(jsonPath("$.[*].valVerifinflexibilidade").value(hasItem(DEFAULT_VAL_VERIFINFLEXIBILIDADE)))
            .andExpect(jsonPath("$.[*].valVerifinflexembutmerito").value(hasItem(DEFAULT_VAL_VERIFINFLEXEMBUTMERITO)))
            .andExpect(jsonPath("$.[*].valVerifinflexpura").value(hasItem(DEFAULT_VAL_VERIFINFLEXPURA)))
            .andExpect(jsonPath("$.[*].valVerifrazaoeletrica").value(hasItem(DEFAULT_VAL_VERIFRAZAOELETRICA)))
            .andExpect(jsonPath("$.[*].valVerifgarantiaenergetica").value(hasItem(DEFAULT_VAL_VERIFGARANTIAENERGETICA)))
            .andExpect(jsonPath("$.[*].valVerifgfom").value(hasItem(DEFAULT_VAL_VERIFGFOM)))
            .andExpect(jsonPath("$.[*].valVerifreposicaoperdas").value(hasItem(DEFAULT_VAL_VERIFREPOSICAOPERDAS)))
            .andExpect(jsonPath("$.[*].valVerifexportacao").value(hasItem(DEFAULT_VAL_VERIFEXPORTACAO)))
            .andExpect(jsonPath("$.[*].valFdexp").value(hasItem(DEFAULT_VAL_FDEXP)))
            .andExpect(jsonPath("$.[*].valVerifreservapotencia").value(hasItem(DEFAULT_VAL_VERIFRESERVAPOTENCIA)))
            .andExpect(jsonPath("$.[*].valAtendsatisfatoriorpo").value(hasItem(DEFAULT_VAL_ATENDSATISFATORIORPO)))
            .andExpect(jsonPath("$.[*].valVerifgsub").value(hasItem(DEFAULT_VAL_VERIFGSUB)))
            .andExpect(jsonPath("$.[*].valVerifunitcommitment").value(hasItem(DEFAULT_VAL_VERIFUNITCOMMITMENT)))
            .andExpect(jsonPath("$.[*].valVerifconstrainedoff").value(hasItem(DEFAULT_VAL_VERIFCONSTRAINEDOFF)))
            .andExpect(jsonPath("$.[*].tipRestricaoeletrica").value(hasItem(DEFAULT_TIP_RESTRICAOELETRICA)));
    }

    protected long getRepositoryCount() {
        return onsGeracaoTermicaMotivoDespachoRepository.count();
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

    protected OnsGeracaoTermicaMotivoDespachoEntity getPersistedOnsGeracaoTermicaMotivoDespachoEntity(
        OnsGeracaoTermicaMotivoDespachoEntity onsGeracaoTermicaMotivoDespacho
    ) {
        return onsGeracaoTermicaMotivoDespachoRepository.findById(onsGeracaoTermicaMotivoDespacho.getId()).orElseThrow();
    }

    protected void assertPersistedOnsGeracaoTermicaMotivoDespachoEntityToMatchAllProperties(
        OnsGeracaoTermicaMotivoDespachoEntity expectedOnsGeracaoTermicaMotivoDespachoEntity
    ) {
        assertOnsGeracaoTermicaMotivoDespachoEntityAllPropertiesEquals(
            expectedOnsGeracaoTermicaMotivoDespachoEntity,
            getPersistedOnsGeracaoTermicaMotivoDespachoEntity(expectedOnsGeracaoTermicaMotivoDespachoEntity)
        );
    }

    protected void assertPersistedOnsGeracaoTermicaMotivoDespachoEntityToMatchUpdatableProperties(
        OnsGeracaoTermicaMotivoDespachoEntity expectedOnsGeracaoTermicaMotivoDespachoEntity
    ) {
        assertOnsGeracaoTermicaMotivoDespachoEntityAllUpdatablePropertiesEquals(
            expectedOnsGeracaoTermicaMotivoDespachoEntity,
            getPersistedOnsGeracaoTermicaMotivoDespachoEntity(expectedOnsGeracaoTermicaMotivoDespachoEntity)
        );
    }
}
