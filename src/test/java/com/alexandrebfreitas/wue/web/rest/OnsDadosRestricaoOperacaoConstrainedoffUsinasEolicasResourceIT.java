package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository;
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
 * Integration tests for the {@link OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResourceIT {

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

    private static final String ENTITY_API_URL = "/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository;

    @Autowired
    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc;

    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity;

    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity createEntity() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity()
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
    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity createUpdatedEntity() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity()
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
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity != null) {
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.delete(
                insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.delete(
                insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );
            insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        // Create the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
        var returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity = om.readValue(
            restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.class
        );

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityUpdatableFieldsEquals(
            returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity(
                returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity;
    }

    @Test
    @Transactional
    void createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasWithExistingId() throws Exception {
        // Create the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas with an existing ID
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );

        // Get all the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasList
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId().intValue())))
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
    void getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );

        // Get the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId().intValue()))
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
    void getNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        // Get the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository
                .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity are not directly saved in db
        em.detach(updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity);
        updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
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

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityToMatchAllProperties(
            updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
                > onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchList = Streamable.of(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll()
                ).toList();
                OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity testOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearch =
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityAllPropertiesEquals(
                    testOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearch,
                    updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas using partial update
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity();
        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId()
        );

        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomEstado(UPDATED_NOM_ESTADO)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .codRazaorestricao(UPDATED_COD_RAZAORESTRICAO)
            .codOrigemrestricao(UPDATED_COD_ORIGEMRESTRICAO);

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity,
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            ),
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas using partial update
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity();
        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId()
        );

        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
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

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity(
                partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.save(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity);
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );

        // Search the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId().intValue())))
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
        return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.count();
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

    protected OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
    ) {
        return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository
            .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityToMatchAllProperties(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    ) {
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityAllPropertiesEquals(
            expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity(
                expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            )
        );
    }

    protected void assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityToMatchUpdatableProperties(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    ) {
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity(
                expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
            )
        );
    }
}
