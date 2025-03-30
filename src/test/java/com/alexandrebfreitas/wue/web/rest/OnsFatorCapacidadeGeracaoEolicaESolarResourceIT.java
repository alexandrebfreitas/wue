package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsFatorCapacidadeGeracaoEolicaESolarEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsFatorCapacidadeGeracaoEolicaESolarEntity;
import com.alexandrebfreitas.wue.repository.OnsFatorCapacidadeGeracaoEolicaESolarRepository;
import com.alexandrebfreitas.wue.repository.search.OnsFatorCapacidadeGeracaoEolicaESolarSearchRepository;
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
 * Integration tests for the {@link OnsFatorCapacidadeGeracaoEolicaESolarResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsFatorCapacidadeGeracaoEolicaESolarResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_PONTOCONEXAO = "AAAAAAAAAA";
    private static final String UPDATED_COD_PONTOCONEXAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_PONTOCONEXAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PONTOCONEXAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_LOCALIZACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_LOCALIZACAO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_LATITUDESECOLETORA = 1D;
    private static final Double UPDATED_VAL_LATITUDESECOLETORA = 2D;

    private static final Double DEFAULT_VAL_LONGITUDESECOLETORA = 1D;
    private static final Double UPDATED_VAL_LONGITUDESECOLETORA = 2D;

    private static final Double DEFAULT_VAL_LATITUDEPONTOCONEXAO = 1D;
    private static final Double UPDATED_VAL_LATITUDEPONTOCONEXAO = 2D;

    private static final Double DEFAULT_VAL_LONGITUDEPONTOCONEXAO = 1D;
    private static final Double UPDATED_VAL_LONGITUDEPONTOCONEXAO = 2D;

    private static final String DEFAULT_NOM_MODALIDADEOPERACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MODALIDADEOPERACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA_CONJUNTO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA_CONJUNTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ID_ONS = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_GERACAOPROGRAMADA = 1D;
    private static final Double UPDATED_VAL_GERACAOPROGRAMADA = 2D;

    private static final Double DEFAULT_VAL_GERACAOVERIFICADA = 1D;
    private static final Double UPDATED_VAL_GERACAOVERIFICADA = 2D;

    private static final Double DEFAULT_VAL_CAPACIDADEINSTALADA = 1D;
    private static final Double UPDATED_VAL_CAPACIDADEINSTALADA = 2D;

    private static final Double DEFAULT_VAL_FATORCAPACIDADE = 1D;
    private static final Double UPDATED_VAL_FATORCAPACIDADE = 2D;

    private static final String ENTITY_API_URL = "/api/ons-fator-capacidade-geracao-eolica-e-solars";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-fator-capacidade-geracao-eolica-e-solars/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsFatorCapacidadeGeracaoEolicaESolarRepository onsFatorCapacidadeGeracaoEolicaESolarRepository;

    @Autowired
    private OnsFatorCapacidadeGeracaoEolicaESolarSearchRepository onsFatorCapacidadeGeracaoEolicaESolarSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc;

    private OnsFatorCapacidadeGeracaoEolicaESolarEntity onsFatorCapacidadeGeracaoEolicaESolarEntity;

    private OnsFatorCapacidadeGeracaoEolicaESolarEntity insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsFatorCapacidadeGeracaoEolicaESolarEntity createEntity() {
        return new OnsFatorCapacidadeGeracaoEolicaESolarEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .codPontoconexao(DEFAULT_COD_PONTOCONEXAO)
            .nomPontoconexao(DEFAULT_NOM_PONTOCONEXAO)
            .nomLocalizacao(DEFAULT_NOM_LOCALIZACAO)
            .valLatitudesecoletora(DEFAULT_VAL_LATITUDESECOLETORA)
            .valLongitudesecoletora(DEFAULT_VAL_LONGITUDESECOLETORA)
            .valLatitudepontoconexao(DEFAULT_VAL_LATITUDEPONTOCONEXAO)
            .valLongitudepontoconexao(DEFAULT_VAL_LONGITUDEPONTOCONEXAO)
            .nomModalidadeoperacao(DEFAULT_NOM_MODALIDADEOPERACAO)
            .nomTipousina(DEFAULT_NOM_TIPOUSINA)
            .nomUsinaConjunto(DEFAULT_NOM_USINA_CONJUNTO)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .idOns(DEFAULT_ID_ONS)
            .ceg(DEFAULT_CEG)
            .valGeracaoprogramada(DEFAULT_VAL_GERACAOPROGRAMADA)
            .valGeracaoverificada(DEFAULT_VAL_GERACAOVERIFICADA)
            .valCapacidadeinstalada(DEFAULT_VAL_CAPACIDADEINSTALADA)
            .valFatorcapacidade(DEFAULT_VAL_FATORCAPACIDADE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsFatorCapacidadeGeracaoEolicaESolarEntity createUpdatedEntity() {
        return new OnsFatorCapacidadeGeracaoEolicaESolarEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .codPontoconexao(UPDATED_COD_PONTOCONEXAO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .nomLocalizacao(UPDATED_NOM_LOCALIZACAO)
            .valLatitudesecoletora(UPDATED_VAL_LATITUDESECOLETORA)
            .valLongitudesecoletora(UPDATED_VAL_LONGITUDESECOLETORA)
            .valLatitudepontoconexao(UPDATED_VAL_LATITUDEPONTOCONEXAO)
            .valLongitudepontoconexao(UPDATED_VAL_LONGITUDEPONTOCONEXAO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomUsinaConjunto(UPDATED_NOM_USINA_CONJUNTO)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valGeracaoprogramada(UPDATED_VAL_GERACAOPROGRAMADA)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA)
            .valCapacidadeinstalada(UPDATED_VAL_CAPACIDADEINSTALADA)
            .valFatorcapacidade(UPDATED_VAL_FATORCAPACIDADE);
    }

    @BeforeEach
    public void initTest() {
        onsFatorCapacidadeGeracaoEolicaESolarEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity != null) {
            onsFatorCapacidadeGeracaoEolicaESolarRepository.delete(insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity);
            onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.delete(insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity);
            insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        // Create the OnsFatorCapacidadeGeracaoEolicaESolar
        var returnedOnsFatorCapacidadeGeracaoEolicaESolarEntity = om.readValue(
            restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsFatorCapacidadeGeracaoEolicaESolarEntity.class
        );

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsFatorCapacidadeGeracaoEolicaESolarEntityUpdatableFieldsEquals(
            returnedOnsFatorCapacidadeGeracaoEolicaESolarEntity,
            getPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntity(returnedOnsFatorCapacidadeGeracaoEolicaESolarEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = returnedOnsFatorCapacidadeGeracaoEolicaESolarEntity;
    }

    @Test
    @Transactional
    void createOnsFatorCapacidadeGeracaoEolicaESolarWithExistingId() throws Exception {
        // Create the OnsFatorCapacidadeGeracaoEolicaESolar with an existing ID
        onsFatorCapacidadeGeracaoEolicaESolarEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsFatorCapacidadeGeracaoEolicaESolars() throws Exception {
        // Initialize the database
        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.saveAndFlush(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );

        // Get all the onsFatorCapacidadeGeracaoEolicaESolarList
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsFatorCapacidadeGeracaoEolicaESolarEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].codPontoconexao").value(hasItem(DEFAULT_COD_PONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].nomPontoconexao").value(hasItem(DEFAULT_NOM_PONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].nomLocalizacao").value(hasItem(DEFAULT_NOM_LOCALIZACAO)))
            .andExpect(jsonPath("$.[*].valLatitudesecoletora").value(hasItem(DEFAULT_VAL_LATITUDESECOLETORA)))
            .andExpect(jsonPath("$.[*].valLongitudesecoletora").value(hasItem(DEFAULT_VAL_LONGITUDESECOLETORA)))
            .andExpect(jsonPath("$.[*].valLatitudepontoconexao").value(hasItem(DEFAULT_VAL_LATITUDEPONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].valLongitudepontoconexao").value(hasItem(DEFAULT_VAL_LONGITUDEPONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomTipousina").value(hasItem(DEFAULT_NOM_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsinaConjunto").value(hasItem(DEFAULT_NOM_USINA_CONJUNTO)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valGeracaoprogramada").value(hasItem(DEFAULT_VAL_GERACAOPROGRAMADA)))
            .andExpect(jsonPath("$.[*].valGeracaoverificada").value(hasItem(DEFAULT_VAL_GERACAOVERIFICADA)))
            .andExpect(jsonPath("$.[*].valCapacidadeinstalada").value(hasItem(DEFAULT_VAL_CAPACIDADEINSTALADA)))
            .andExpect(jsonPath("$.[*].valFatorcapacidade").value(hasItem(DEFAULT_VAL_FATORCAPACIDADE)));
    }

    @Test
    @Transactional
    void getOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        // Initialize the database
        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.saveAndFlush(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );

        // Get the onsFatorCapacidadeGeracaoEolicaESolar
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(get(ENTITY_API_URL_ID, onsFatorCapacidadeGeracaoEolicaESolarEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsFatorCapacidadeGeracaoEolicaESolarEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.codPontoconexao").value(DEFAULT_COD_PONTOCONEXAO))
            .andExpect(jsonPath("$.nomPontoconexao").value(DEFAULT_NOM_PONTOCONEXAO))
            .andExpect(jsonPath("$.nomLocalizacao").value(DEFAULT_NOM_LOCALIZACAO))
            .andExpect(jsonPath("$.valLatitudesecoletora").value(DEFAULT_VAL_LATITUDESECOLETORA))
            .andExpect(jsonPath("$.valLongitudesecoletora").value(DEFAULT_VAL_LONGITUDESECOLETORA))
            .andExpect(jsonPath("$.valLatitudepontoconexao").value(DEFAULT_VAL_LATITUDEPONTOCONEXAO))
            .andExpect(jsonPath("$.valLongitudepontoconexao").value(DEFAULT_VAL_LONGITUDEPONTOCONEXAO))
            .andExpect(jsonPath("$.nomModalidadeoperacao").value(DEFAULT_NOM_MODALIDADEOPERACAO))
            .andExpect(jsonPath("$.nomTipousina").value(DEFAULT_NOM_TIPOUSINA))
            .andExpect(jsonPath("$.nomUsinaConjunto").value(DEFAULT_NOM_USINA_CONJUNTO))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.idOns").value(DEFAULT_ID_ONS))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.valGeracaoprogramada").value(DEFAULT_VAL_GERACAOPROGRAMADA))
            .andExpect(jsonPath("$.valGeracaoverificada").value(DEFAULT_VAL_GERACAOVERIFICADA))
            .andExpect(jsonPath("$.valCapacidadeinstalada").value(DEFAULT_VAL_CAPACIDADEINSTALADA))
            .andExpect(jsonPath("$.valFatorcapacidade").value(DEFAULT_VAL_FATORCAPACIDADE));
    }

    @Test
    @Transactional
    void getNonExistingOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        // Get the onsFatorCapacidadeGeracaoEolicaESolar
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        // Initialize the database
        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.saveAndFlush(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.save(onsFatorCapacidadeGeracaoEolicaESolarEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());

        // Update the onsFatorCapacidadeGeracaoEolicaESolar
        OnsFatorCapacidadeGeracaoEolicaESolarEntity updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity =
            onsFatorCapacidadeGeracaoEolicaESolarRepository.findById(onsFatorCapacidadeGeracaoEolicaESolarEntity.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity are not directly saved in db
        em.detach(updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity);
        updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .codPontoconexao(UPDATED_COD_PONTOCONEXAO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .nomLocalizacao(UPDATED_NOM_LOCALIZACAO)
            .valLatitudesecoletora(UPDATED_VAL_LATITUDESECOLETORA)
            .valLongitudesecoletora(UPDATED_VAL_LONGITUDESECOLETORA)
            .valLatitudepontoconexao(UPDATED_VAL_LATITUDEPONTOCONEXAO)
            .valLongitudepontoconexao(UPDATED_VAL_LONGITUDEPONTOCONEXAO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomUsinaConjunto(UPDATED_NOM_USINA_CONJUNTO)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valGeracaoprogramada(UPDATED_VAL_GERACAOPROGRAMADA)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA)
            .valCapacidadeinstalada(UPDATED_VAL_CAPACIDADEINSTALADA)
            .valFatorcapacidade(UPDATED_VAL_FATORCAPACIDADE);

        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntityToMatchAllProperties(updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsFatorCapacidadeGeracaoEolicaESolarEntity> onsFatorCapacidadeGeracaoEolicaESolarSearchList = Streamable.of(
                    onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll()
                ).toList();
                OnsFatorCapacidadeGeracaoEolicaESolarEntity testOnsFatorCapacidadeGeracaoEolicaESolarSearch =
                    onsFatorCapacidadeGeracaoEolicaESolarSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsFatorCapacidadeGeracaoEolicaESolarEntityAllPropertiesEquals(
                    testOnsFatorCapacidadeGeracaoEolicaESolarSearch,
                    updatedOnsFatorCapacidadeGeracaoEolicaESolarEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        onsFatorCapacidadeGeracaoEolicaESolarEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        onsFatorCapacidadeGeracaoEolicaESolarEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        onsFatorCapacidadeGeracaoEolicaESolarEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsFatorCapacidadeGeracaoEolicaESolarWithPatch() throws Exception {
        // Initialize the database
        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.saveAndFlush(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsFatorCapacidadeGeracaoEolicaESolar using partial update
        OnsFatorCapacidadeGeracaoEolicaESolarEntity partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity =
            new OnsFatorCapacidadeGeracaoEolicaESolarEntity();
        partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity.setId(onsFatorCapacidadeGeracaoEolicaESolarEntity.getId());

        partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .valLatitudesecoletora(UPDATED_VAL_LATITUDESECOLETORA)
            .valLongitudesecoletora(UPDATED_VAL_LONGITUDESECOLETORA)
            .valLatitudepontoconexao(UPDATED_VAL_LATITUDEPONTOCONEXAO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomUsinaConjunto(UPDATED_NOM_USINA_CONJUNTO)
            .idOns(UPDATED_ID_ONS)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA)
            .valFatorcapacidade(UPDATED_VAL_FATORCAPACIDADE);

        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsFatorCapacidadeGeracaoEolicaESolarEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity,
                onsFatorCapacidadeGeracaoEolicaESolarEntity
            ),
            getPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntity(onsFatorCapacidadeGeracaoEolicaESolarEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsFatorCapacidadeGeracaoEolicaESolarWithPatch() throws Exception {
        // Initialize the database
        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.saveAndFlush(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsFatorCapacidadeGeracaoEolicaESolar using partial update
        OnsFatorCapacidadeGeracaoEolicaESolarEntity partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity =
            new OnsFatorCapacidadeGeracaoEolicaESolarEntity();
        partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity.setId(onsFatorCapacidadeGeracaoEolicaESolarEntity.getId());

        partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .codPontoconexao(UPDATED_COD_PONTOCONEXAO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .nomLocalizacao(UPDATED_NOM_LOCALIZACAO)
            .valLatitudesecoletora(UPDATED_VAL_LATITUDESECOLETORA)
            .valLongitudesecoletora(UPDATED_VAL_LONGITUDESECOLETORA)
            .valLatitudepontoconexao(UPDATED_VAL_LATITUDEPONTOCONEXAO)
            .valLongitudepontoconexao(UPDATED_VAL_LONGITUDEPONTOCONEXAO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomUsinaConjunto(UPDATED_NOM_USINA_CONJUNTO)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valGeracaoprogramada(UPDATED_VAL_GERACAOPROGRAMADA)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA)
            .valCapacidadeinstalada(UPDATED_VAL_CAPACIDADEINSTALADA)
            .valFatorcapacidade(UPDATED_VAL_FATORCAPACIDADE);

        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsFatorCapacidadeGeracaoEolicaESolarEntityUpdatableFieldsEquals(
            partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity,
            getPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntity(partialUpdatedOnsFatorCapacidadeGeracaoEolicaESolarEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        onsFatorCapacidadeGeracaoEolicaESolarEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        onsFatorCapacidadeGeracaoEolicaESolarEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        onsFatorCapacidadeGeracaoEolicaESolarEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsFatorCapacidadeGeracaoEolicaESolarEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsFatorCapacidadeGeracaoEolicaESolar in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        // Initialize the database
        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.saveAndFlush(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );
        onsFatorCapacidadeGeracaoEolicaESolarRepository.save(onsFatorCapacidadeGeracaoEolicaESolarEntity);
        onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.save(onsFatorCapacidadeGeracaoEolicaESolarEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsFatorCapacidadeGeracaoEolicaESolar
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsFatorCapacidadeGeracaoEolicaESolar() throws Exception {
        // Initialize the database
        insertedOnsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.saveAndFlush(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );
        onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.save(onsFatorCapacidadeGeracaoEolicaESolarEntity);

        // Search the onsFatorCapacidadeGeracaoEolicaESolar
        restOnsFatorCapacidadeGeracaoEolicaESolarMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsFatorCapacidadeGeracaoEolicaESolarEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsFatorCapacidadeGeracaoEolicaESolarEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].codPontoconexao").value(hasItem(DEFAULT_COD_PONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].nomPontoconexao").value(hasItem(DEFAULT_NOM_PONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].nomLocalizacao").value(hasItem(DEFAULT_NOM_LOCALIZACAO)))
            .andExpect(jsonPath("$.[*].valLatitudesecoletora").value(hasItem(DEFAULT_VAL_LATITUDESECOLETORA)))
            .andExpect(jsonPath("$.[*].valLongitudesecoletora").value(hasItem(DEFAULT_VAL_LONGITUDESECOLETORA)))
            .andExpect(jsonPath("$.[*].valLatitudepontoconexao").value(hasItem(DEFAULT_VAL_LATITUDEPONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].valLongitudepontoconexao").value(hasItem(DEFAULT_VAL_LONGITUDEPONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomTipousina").value(hasItem(DEFAULT_NOM_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsinaConjunto").value(hasItem(DEFAULT_NOM_USINA_CONJUNTO)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valGeracaoprogramada").value(hasItem(DEFAULT_VAL_GERACAOPROGRAMADA)))
            .andExpect(jsonPath("$.[*].valGeracaoverificada").value(hasItem(DEFAULT_VAL_GERACAOVERIFICADA)))
            .andExpect(jsonPath("$.[*].valCapacidadeinstalada").value(hasItem(DEFAULT_VAL_CAPACIDADEINSTALADA)))
            .andExpect(jsonPath("$.[*].valFatorcapacidade").value(hasItem(DEFAULT_VAL_FATORCAPACIDADE)));
    }

    protected long getRepositoryCount() {
        return onsFatorCapacidadeGeracaoEolicaESolarRepository.count();
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

    protected OnsFatorCapacidadeGeracaoEolicaESolarEntity getPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntity(
        OnsFatorCapacidadeGeracaoEolicaESolarEntity onsFatorCapacidadeGeracaoEolicaESolar
    ) {
        return onsFatorCapacidadeGeracaoEolicaESolarRepository.findById(onsFatorCapacidadeGeracaoEolicaESolar.getId()).orElseThrow();
    }

    protected void assertPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntityToMatchAllProperties(
        OnsFatorCapacidadeGeracaoEolicaESolarEntity expectedOnsFatorCapacidadeGeracaoEolicaESolarEntity
    ) {
        assertOnsFatorCapacidadeGeracaoEolicaESolarEntityAllPropertiesEquals(
            expectedOnsFatorCapacidadeGeracaoEolicaESolarEntity,
            getPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntity(expectedOnsFatorCapacidadeGeracaoEolicaESolarEntity)
        );
    }

    protected void assertPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntityToMatchUpdatableProperties(
        OnsFatorCapacidadeGeracaoEolicaESolarEntity expectedOnsFatorCapacidadeGeracaoEolicaESolarEntity
    ) {
        assertOnsFatorCapacidadeGeracaoEolicaESolarEntityAllUpdatablePropertiesEquals(
            expectedOnsFatorCapacidadeGeracaoEolicaESolarEntity,
            getPersistedOnsFatorCapacidadeGeracaoEolicaESolarEntity(expectedOnsFatorCapacidadeGeracaoEolicaESolarEntity)
        );
    }
}
