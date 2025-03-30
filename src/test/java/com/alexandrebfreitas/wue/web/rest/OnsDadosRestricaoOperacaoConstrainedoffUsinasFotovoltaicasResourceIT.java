package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository;
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
 * Integration tests for the {@link OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ONS = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_GERACAO = 1D;
    private static final Double UPDATED_VAL_GERACAO = 2D;

    private static final Double DEFAULT_VAL_GERACAOLIMITADA = 1D;
    private static final Double UPDATED_VAL_GERACAOLIMITADA = 2D;

    private static final Double DEFAULT_VAL_DISPONIBILIDADE = 1D;
    private static final Double UPDATED_VAL_DISPONIBILIDADE = 2D;

    private static final Double DEFAULT_VAL_GERACAOREFERENCIA = 1D;
    private static final Double UPDATED_VAL_GERACAOREFERENCIA = 2D;

    private static final Double DEFAULT_VAL_GERACAOREFERENCIAFINAL = 1D;
    private static final Double UPDATED_VAL_GERACAOREFERENCIAFINAL = 2D;

    private static final String DEFAULT_COD_RAZAORESTRICAO = "AAAAAAAAAA";
    private static final String UPDATED_COD_RAZAORESTRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_ORIGEMRESTRICAO = "AAAAAAAAAA";
    private static final String UPDATED_COD_ORIGEMRESTRICAO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository;

    @Autowired
    private OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc;

    private OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity;

    private OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity createEntity() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .nomUsina(DEFAULT_NOM_USINA)
            .idOns(DEFAULT_ID_ONS)
            .ceg(DEFAULT_CEG)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valGeracao(DEFAULT_VAL_GERACAO)
            .valGeracaolimitada(DEFAULT_VAL_GERACAOLIMITADA)
            .valDisponibilidade(DEFAULT_VAL_DISPONIBILIDADE)
            .valGeracaoreferencia(DEFAULT_VAL_GERACAOREFERENCIA)
            .valGeracaoreferenciafinal(DEFAULT_VAL_GERACAOREFERENCIAFINAL)
            .codRazaorestricao(DEFAULT_COD_RAZAORESTRICAO)
            .codOrigemrestricao(DEFAULT_COD_ORIGEMRESTRICAO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity createUpdatedEntity() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGeracao(UPDATED_VAL_GERACAO)
            .valGeracaolimitada(UPDATED_VAL_GERACAOLIMITADA)
            .valDisponibilidade(UPDATED_VAL_DISPONIBILIDADE)
            .valGeracaoreferencia(UPDATED_VAL_GERACAOREFERENCIA)
            .valGeracaoreferenciafinal(UPDATED_VAL_GERACAOREFERENCIAFINAL)
            .codRazaorestricao(UPDATED_COD_RAZAORESTRICAO)
            .codOrigemrestricao(UPDATED_COD_ORIGEMRESTRICAO);
    }

    @BeforeEach
    public void initTest() {
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity != null) {
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.delete(
                insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.delete(
                insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );
            insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        // Create the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
        var returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity = om.readValue(
            restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.class
        );

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityUpdatableFieldsEquals(
            returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity(
                returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity;
    }

    @Test
    @Transactional
    void createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasWithExistingId() throws Exception {
        // Create the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas with an existing ID
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );

        // Get all the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasList
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(hasItem(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId().intValue()))
            )
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valGeracao").value(hasItem(DEFAULT_VAL_GERACAO)))
            .andExpect(jsonPath("$.[*].valGeracaolimitada").value(hasItem(DEFAULT_VAL_GERACAOLIMITADA)))
            .andExpect(jsonPath("$.[*].valDisponibilidade").value(hasItem(DEFAULT_VAL_DISPONIBILIDADE)))
            .andExpect(jsonPath("$.[*].valGeracaoreferencia").value(hasItem(DEFAULT_VAL_GERACAOREFERENCIA)))
            .andExpect(jsonPath("$.[*].valGeracaoreferenciafinal").value(hasItem(DEFAULT_VAL_GERACAOREFERENCIAFINAL)))
            .andExpect(jsonPath("$.[*].codRazaorestricao").value(hasItem(DEFAULT_COD_RAZAORESTRICAO)))
            .andExpect(jsonPath("$.[*].codOrigemrestricao").value(hasItem(DEFAULT_COD_ORIGEMRESTRICAO)));
    }

    @Test
    @Transactional
    void getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );

        // Get the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.idOns").value(DEFAULT_ID_ONS))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valGeracao").value(DEFAULT_VAL_GERACAO))
            .andExpect(jsonPath("$.valGeracaolimitada").value(DEFAULT_VAL_GERACAOLIMITADA))
            .andExpect(jsonPath("$.valDisponibilidade").value(DEFAULT_VAL_DISPONIBILIDADE))
            .andExpect(jsonPath("$.valGeracaoreferencia").value(DEFAULT_VAL_GERACAOREFERENCIA))
            .andExpect(jsonPath("$.valGeracaoreferenciafinal").value(DEFAULT_VAL_GERACAOREFERENCIAFINAL))
            .andExpect(jsonPath("$.codRazaorestricao").value(DEFAULT_COD_RAZAORESTRICAO))
            .andExpect(jsonPath("$.codOrigemrestricao").value(DEFAULT_COD_ORIGEMRESTRICAO));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        // Get the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository
                .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity are not directly saved in db
        em.detach(updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity);
        updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGeracao(UPDATED_VAL_GERACAO)
            .valGeracaolimitada(UPDATED_VAL_GERACAOLIMITADA)
            .valDisponibilidade(UPDATED_VAL_DISPONIBILIDADE)
            .valGeracaoreferencia(UPDATED_VAL_GERACAOREFERENCIA)
            .valGeracaoreferenciafinal(UPDATED_VAL_GERACAOREFERENCIAFINAL)
            .codRazaorestricao(UPDATED_COD_RAZAORESTRICAO)
            .codOrigemrestricao(UPDATED_COD_ORIGEMRESTRICAO);

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityToMatchAllProperties(
            updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
                > onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchList = Streamable.of(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
                ).toList();
                OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity testOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearch =
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityAllPropertiesEquals(
                    testOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearch,
                    updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas using partial update
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity();
        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId()
        );

        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGeracao(UPDATED_VAL_GERACAO)
            .valGeracaolimitada(UPDATED_VAL_GERACAOLIMITADA)
            .valDisponibilidade(UPDATED_VAL_DISPONIBILIDADE)
            .valGeracaoreferencia(UPDATED_VAL_GERACAOREFERENCIA)
            .codRazaorestricao(UPDATED_COD_RAZAORESTRICAO)
            .codOrigemrestricao(UPDATED_COD_ORIGEMRESTRICAO);

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity,
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            ),
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas using partial update
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity();
        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId()
        );

        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGeracao(UPDATED_VAL_GERACAO)
            .valGeracaolimitada(UPDATED_VAL_GERACAOLIMITADA)
            .valDisponibilidade(UPDATED_VAL_DISPONIBILIDADE)
            .valGeracaoreferencia(UPDATED_VAL_GERACAOREFERENCIA)
            .valGeracaoreferenciafinal(UPDATED_VAL_GERACAOREFERENCIAFINAL)
            .codRazaorestricao(UPDATED_COD_RAZAORESTRICAO)
            .codOrigemrestricao(UPDATED_COD_ORIGEMRESTRICAO);

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity(
                partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );

        // Search the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(hasItem(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId().intValue()))
            )
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valGeracao").value(hasItem(DEFAULT_VAL_GERACAO)))
            .andExpect(jsonPath("$.[*].valGeracaolimitada").value(hasItem(DEFAULT_VAL_GERACAOLIMITADA)))
            .andExpect(jsonPath("$.[*].valDisponibilidade").value(hasItem(DEFAULT_VAL_DISPONIBILIDADE)))
            .andExpect(jsonPath("$.[*].valGeracaoreferencia").value(hasItem(DEFAULT_VAL_GERACAOREFERENCIA)))
            .andExpect(jsonPath("$.[*].valGeracaoreferenciafinal").value(hasItem(DEFAULT_VAL_GERACAOREFERENCIAFINAL)))
            .andExpect(jsonPath("$.[*].codRazaorestricao").value(hasItem(DEFAULT_COD_RAZAORESTRICAO)))
            .andExpect(jsonPath("$.[*].codOrigemrestricao").value(hasItem(DEFAULT_COD_ORIGEMRESTRICAO)));
    }

    protected long getRepositoryCount() {
        return onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.count();
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

    protected OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
    ) {
        return onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository
            .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityToMatchAllProperties(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    ) {
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityAllPropertiesEquals(
            expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity(
                expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            )
        );
    }

    protected void assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityToMatchUpdatableProperties(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    ) {
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity(
                expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            )
        );
    }
}
