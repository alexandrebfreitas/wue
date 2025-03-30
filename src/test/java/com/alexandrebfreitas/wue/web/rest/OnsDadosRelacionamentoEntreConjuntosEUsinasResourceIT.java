package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRelacionamentoEntreConjuntosEUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository;
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
 * Integration tests for the {@link OnsDadosRelacionamentoEntreConjuntosEUsinasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosRelacionamentoEntreConjuntosEUsinasResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ESTAD_ID = "AAAAAAAAAA";
    private static final String UPDATED_ESTAD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_ID_TIPOUSINA = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_CONJUNTOUSINA = 1;
    private static final Integer UPDATED_ID_CONJUNTOUSINA = 2;

    private static final String DEFAULT_ID_ONS_CONJUNTO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS_CONJUNTO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ONS_USINA = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_CONJUNTO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_CONJUNTO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_INICIORELACIONAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_INICIORELACIONAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_FIMRELACIONAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_FIMRELACIONAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/ons-dados-relacionamento-entre-conjuntos-e-usinas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-relacionamento-entre-conjuntos-e-usinas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosRelacionamentoEntreConjuntosEUsinasRepository onsDadosRelacionamentoEntreConjuntosEUsinasRepository;

    @Autowired
    private OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc;

    private OnsDadosRelacionamentoEntreConjuntosEUsinasEntity onsDadosRelacionamentoEntreConjuntosEUsinasEntity;

    private OnsDadosRelacionamentoEntreConjuntosEUsinasEntity insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRelacionamentoEntreConjuntosEUsinasEntity createEntity() {
        return new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .estadId(DEFAULT_ESTAD_ID)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .idTipousina(DEFAULT_ID_TIPOUSINA)
            .idConjuntousina(DEFAULT_ID_CONJUNTOUSINA)
            .idOnsConjunto(DEFAULT_ID_ONS_CONJUNTO)
            .idOnsUsina(DEFAULT_ID_ONS_USINA)
            .nomConjunto(DEFAULT_NOM_CONJUNTO)
            .nomUsina(DEFAULT_NOM_USINA)
            .ceg(DEFAULT_CEG)
            .datIniciorelacionamento(DEFAULT_DAT_INICIORELACIONAMENTO)
            .datFimrelacionamento(DEFAULT_DAT_FIMRELACIONAMENTO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRelacionamentoEntreConjuntosEUsinasEntity createUpdatedEntity() {
        return new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .estadId(UPDATED_ESTAD_ID)
            .nomEstado(UPDATED_NOM_ESTADO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idConjuntousina(UPDATED_ID_CONJUNTOUSINA)
            .idOnsConjunto(UPDATED_ID_ONS_CONJUNTO)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomConjunto(UPDATED_NOM_CONJUNTO)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .datIniciorelacionamento(UPDATED_DAT_INICIORELACIONAMENTO)
            .datFimrelacionamento(UPDATED_DAT_FIMRELACIONAMENTO);
    }

    @BeforeEach
    public void initTest() {
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity != null) {
            onsDadosRelacionamentoEntreConjuntosEUsinasRepository.delete(insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity);
            onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.delete(insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity);
            insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        // Create the OnsDadosRelacionamentoEntreConjuntosEUsinas
        var returnedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = om.readValue(
            restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosRelacionamentoEntreConjuntosEUsinasEntity.class
        );

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosRelacionamentoEntreConjuntosEUsinasEntityUpdatableFieldsEquals(
            returnedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity(returnedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = returnedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity;
    }

    @Test
    @Transactional
    void createOnsDadosRelacionamentoEntreConjuntosEUsinasWithExistingId() throws Exception {
        // Create the OnsDadosRelacionamentoEntreConjuntosEUsinas with an existing ID
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.saveAndFlush(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );

        // Get all the onsDadosRelacionamentoEntreConjuntosEUsinasList
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].estadId").value(hasItem(DEFAULT_ESTAD_ID)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].idConjuntousina").value(hasItem(DEFAULT_ID_CONJUNTOUSINA)))
            .andExpect(jsonPath("$.[*].idOnsConjunto").value(hasItem(DEFAULT_ID_ONS_CONJUNTO)))
            .andExpect(jsonPath("$.[*].idOnsUsina").value(hasItem(DEFAULT_ID_ONS_USINA)))
            .andExpect(jsonPath("$.[*].nomConjunto").value(hasItem(DEFAULT_NOM_CONJUNTO)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].datIniciorelacionamento").value(hasItem(DEFAULT_DAT_INICIORELACIONAMENTO.toString())))
            .andExpect(jsonPath("$.[*].datFimrelacionamento").value(hasItem(DEFAULT_DAT_FIMRELACIONAMENTO.toString())));
    }

    @Test
    @Transactional
    void getOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.saveAndFlush(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );

        // Get the onsDadosRelacionamentoEntreConjuntosEUsinas
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.estadId").value(DEFAULT_ESTAD_ID))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.idTipousina").value(DEFAULT_ID_TIPOUSINA))
            .andExpect(jsonPath("$.idConjuntousina").value(DEFAULT_ID_CONJUNTOUSINA))
            .andExpect(jsonPath("$.idOnsConjunto").value(DEFAULT_ID_ONS_CONJUNTO))
            .andExpect(jsonPath("$.idOnsUsina").value(DEFAULT_ID_ONS_USINA))
            .andExpect(jsonPath("$.nomConjunto").value(DEFAULT_NOM_CONJUNTO))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.datIniciorelacionamento").value(DEFAULT_DAT_INICIORELACIONAMENTO.toString()))
            .andExpect(jsonPath("$.datFimrelacionamento").value(DEFAULT_DAT_FIMRELACIONAMENTO.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        // Get the onsDadosRelacionamentoEntreConjuntosEUsinas
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.saveAndFlush(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.save(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());

        // Update the onsDadosRelacionamentoEntreConjuntosEUsinas
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity =
            onsDadosRelacionamentoEntreConjuntosEUsinasRepository
                .findById(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity are not directly saved in db
        em.detach(updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity);
        updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .estadId(UPDATED_ESTAD_ID)
            .nomEstado(UPDATED_NOM_ESTADO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idConjuntousina(UPDATED_ID_CONJUNTOUSINA)
            .idOnsConjunto(UPDATED_ID_ONS_CONJUNTO)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomConjunto(UPDATED_NOM_CONJUNTO)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .datIniciorelacionamento(UPDATED_DAT_INICIORELACIONAMENTO)
            .datFimrelacionamento(UPDATED_DAT_FIMRELACIONAMENTO);

        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntityToMatchAllProperties(
            updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> onsDadosRelacionamentoEntreConjuntosEUsinasSearchList =
                    Streamable.of(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll()).toList();
                OnsDadosRelacionamentoEntreConjuntosEUsinasEntity testOnsDadosRelacionamentoEntreConjuntosEUsinasSearch =
                    onsDadosRelacionamentoEntreConjuntosEUsinasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosRelacionamentoEntreConjuntosEUsinasEntityAllPropertiesEquals(
                    testOnsDadosRelacionamentoEntreConjuntosEUsinasSearch,
                    updatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosRelacionamentoEntreConjuntosEUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.saveAndFlush(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRelacionamentoEntreConjuntosEUsinas using partial update
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity =
            new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity();
        partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId());

        partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .estadId(UPDATED_ESTAD_ID)
            .nomEstado(UPDATED_NOM_ESTADO)
            .idConjuntousina(UPDATED_ID_CONJUNTOUSINA)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomUsina(UPDATED_NOM_USINA)
            .datIniciorelacionamento(UPDATED_DAT_INICIORELACIONAMENTO);

        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRelacionamentoEntreConjuntosEUsinasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity,
                onsDadosRelacionamentoEntreConjuntosEUsinasEntity
            ),
            getPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity(onsDadosRelacionamentoEntreConjuntosEUsinasEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosRelacionamentoEntreConjuntosEUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.saveAndFlush(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRelacionamentoEntreConjuntosEUsinas using partial update
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity =
            new OnsDadosRelacionamentoEntreConjuntosEUsinasEntity();
        partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId());

        partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .estadId(UPDATED_ESTAD_ID)
            .nomEstado(UPDATED_NOM_ESTADO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idConjuntousina(UPDATED_ID_CONJUNTOUSINA)
            .idOnsConjunto(UPDATED_ID_ONS_CONJUNTO)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomConjunto(UPDATED_NOM_CONJUNTO)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .datIniciorelacionamento(UPDATED_DAT_INICIORELACIONAMENTO)
            .datFimrelacionamento(UPDATED_DAT_FIMRELACIONAMENTO);

        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRelacionamentoEntreConjuntosEUsinasEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity(partialUpdatedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        onsDadosRelacionamentoEntreConjuntosEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreConjuntosEUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRelacionamentoEntreConjuntosEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.saveAndFlush(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );
        onsDadosRelacionamentoEntreConjuntosEUsinasRepository.save(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);
        onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.save(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosRelacionamentoEntreConjuntosEUsinas
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosRelacionamentoEntreConjuntosEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity = onsDadosRelacionamentoEntreConjuntosEUsinasRepository.saveAndFlush(
            onsDadosRelacionamentoEntreConjuntosEUsinasEntity
        );
        onsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository.save(onsDadosRelacionamentoEntreConjuntosEUsinasEntity);

        // Search the onsDadosRelacionamentoEntreConjuntosEUsinas
        restOnsDadosRelacionamentoEntreConjuntosEUsinasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosRelacionamentoEntreConjuntosEUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].estadId").value(hasItem(DEFAULT_ESTAD_ID)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].idConjuntousina").value(hasItem(DEFAULT_ID_CONJUNTOUSINA)))
            .andExpect(jsonPath("$.[*].idOnsConjunto").value(hasItem(DEFAULT_ID_ONS_CONJUNTO)))
            .andExpect(jsonPath("$.[*].idOnsUsina").value(hasItem(DEFAULT_ID_ONS_USINA)))
            .andExpect(jsonPath("$.[*].nomConjunto").value(hasItem(DEFAULT_NOM_CONJUNTO)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].datIniciorelacionamento").value(hasItem(DEFAULT_DAT_INICIORELACIONAMENTO.toString())))
            .andExpect(jsonPath("$.[*].datFimrelacionamento").value(hasItem(DEFAULT_DAT_FIMRELACIONAMENTO.toString())));
    }

    protected long getRepositoryCount() {
        return onsDadosRelacionamentoEntreConjuntosEUsinasRepository.count();
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

    protected OnsDadosRelacionamentoEntreConjuntosEUsinasEntity getPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity(
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity onsDadosRelacionamentoEntreConjuntosEUsinas
    ) {
        return onsDadosRelacionamentoEntreConjuntosEUsinasRepository
            .findById(onsDadosRelacionamentoEntreConjuntosEUsinas.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntityToMatchAllProperties(
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity expectedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity
    ) {
        assertOnsDadosRelacionamentoEntreConjuntosEUsinasEntityAllPropertiesEquals(
            expectedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity(expectedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity)
        );
    }

    protected void assertPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntityToMatchUpdatableProperties(
        OnsDadosRelacionamentoEntreConjuntosEUsinasEntity expectedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity
    ) {
        assertOnsDadosRelacionamentoEntreConjuntosEUsinasEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity(expectedOnsDadosRelacionamentoEntreConjuntosEUsinasEntity)
        );
    }
}
