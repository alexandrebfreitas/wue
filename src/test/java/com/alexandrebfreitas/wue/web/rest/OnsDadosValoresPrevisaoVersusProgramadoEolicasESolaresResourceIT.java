package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository;
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
 * Integration tests for the {@link OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResourceIT {

    private static final LocalDate DEFAULT_DAT_PROGRAMACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_PROGRAMACAO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUM_PATAMAR = 1;
    private static final Integer UPDATED_NUM_PATAMAR = 2;

    private static final String DEFAULT_COD_USINAPDP = "AAAAAAAAAA";
    private static final String UPDATED_COD_USINAPDP = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINAPDP = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINAPDP = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_PREVISAO = 1D;
    private static final Double UPDATED_VAL_PREVISAO = 2D;

    private static final Double DEFAULT_VAL_PROGRAMADO = 1D;
    private static final Double UPDATED_VAL_PROGRAMADO = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-valores-previsao-versus-programado-eolicas-e-solares";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-valores-previsao-versus-programado-eolicas-e-solares/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository;

    @Autowired
    private OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc;

    private OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity;

    private OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity createEntity() {
        return new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity()
            .datProgramacao(DEFAULT_DAT_PROGRAMACAO)
            .numPatamar(DEFAULT_NUM_PATAMAR)
            .codUsinapdp(DEFAULT_COD_USINAPDP)
            .nomUsinapdp(DEFAULT_NOM_USINAPDP)
            .valPrevisao(DEFAULT_VAL_PREVISAO)
            .valProgramado(DEFAULT_VAL_PROGRAMADO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity createUpdatedEntity() {
        return new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity()
            .datProgramacao(UPDATED_DAT_PROGRAMACAO)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .codUsinapdp(UPDATED_COD_USINAPDP)
            .nomUsinapdp(UPDATED_NOM_USINAPDP)
            .valPrevisao(UPDATED_VAL_PREVISAO)
            .valProgramado(UPDATED_VAL_PROGRAMADO);
    }

    @BeforeEach
    public void initTest() {
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity != null) {
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.delete(
                insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.delete(
                insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );
            insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        // Create the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
        var returnedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity = om.readValue(
            restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.class
        );

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityUpdatableFieldsEquals(
            returnedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity,
            getPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity(
                returnedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            returnedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity;
    }

    @Test
    @Transactional
    void createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresWithExistingId() throws Exception {
        // Create the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares with an existing ID
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.saveAndFlush(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );

        // Get all the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresList
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].datProgramacao").value(hasItem(DEFAULT_DAT_PROGRAMACAO.toString())))
            .andExpect(jsonPath("$.[*].numPatamar").value(hasItem(DEFAULT_NUM_PATAMAR)))
            .andExpect(jsonPath("$.[*].codUsinapdp").value(hasItem(DEFAULT_COD_USINAPDP)))
            .andExpect(jsonPath("$.[*].nomUsinapdp").value(hasItem(DEFAULT_NOM_USINAPDP)))
            .andExpect(jsonPath("$.[*].valPrevisao").value(hasItem(DEFAULT_VAL_PREVISAO)))
            .andExpect(jsonPath("$.[*].valProgramado").value(hasItem(DEFAULT_VAL_PROGRAMADO)));
    }

    @Test
    @Transactional
    void getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.saveAndFlush(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );

        // Get the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId().intValue()))
            .andExpect(jsonPath("$.datProgramacao").value(DEFAULT_DAT_PROGRAMACAO.toString()))
            .andExpect(jsonPath("$.numPatamar").value(DEFAULT_NUM_PATAMAR))
            .andExpect(jsonPath("$.codUsinapdp").value(DEFAULT_COD_USINAPDP))
            .andExpect(jsonPath("$.nomUsinapdp").value(DEFAULT_NOM_USINAPDP))
            .andExpect(jsonPath("$.valPrevisao").value(DEFAULT_VAL_PREVISAO))
            .andExpect(jsonPath("$.valProgramado").value(DEFAULT_VAL_PROGRAMADO));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        // Get the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.saveAndFlush(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.save(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );

        // Update the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository
                .findById(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity are not directly saved in db
        em.detach(updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity);
        updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            .datProgramacao(UPDATED_DAT_PROGRAMACAO)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .codUsinapdp(UPDATED_COD_USINAPDP)
            .nomUsinapdp(UPDATED_NOM_USINAPDP)
            .valPrevisao(UPDATED_VAL_PREVISAO)
            .valProgramado(UPDATED_VAL_PROGRAMADO);

        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityToMatchAllProperties(
            updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
                > onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchList = Streamable.of(
                    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
                ).toList();
                OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity testOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearch =
                    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityAllPropertiesEquals(
                    testOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearch,
                    updatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.saveAndFlush(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares using partial update
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity();
        partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId()
        );

        partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.valProgramado(UPDATED_VAL_PROGRAMADO);

        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity,
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            ),
            getPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.saveAndFlush(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares using partial update
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            new OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity();
        partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId()
        );

        partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            .datProgramacao(UPDATED_DAT_PROGRAMACAO)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .codUsinapdp(UPDATED_COD_USINAPDP)
            .nomUsinapdp(UPDATED_NOM_USINAPDP)
            .valPrevisao(UPDATED_VAL_PREVISAO)
            .valProgramado(UPDATED_VAL_PROGRAMADO);

        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity,
            getPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity(
                partialUpdatedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.saveAndFlush(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.save(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity);
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.save(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.saveAndFlush(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.save(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );

        // Search the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares
        restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].datProgramacao").value(hasItem(DEFAULT_DAT_PROGRAMACAO.toString())))
            .andExpect(jsonPath("$.[*].numPatamar").value(hasItem(DEFAULT_NUM_PATAMAR)))
            .andExpect(jsonPath("$.[*].codUsinapdp").value(hasItem(DEFAULT_COD_USINAPDP)))
            .andExpect(jsonPath("$.[*].nomUsinapdp").value(hasItem(DEFAULT_NOM_USINAPDP)))
            .andExpect(jsonPath("$.[*].valPrevisao").value(hasItem(DEFAULT_VAL_PREVISAO)))
            .andExpect(jsonPath("$.[*].valProgramado").value(hasItem(DEFAULT_VAL_PROGRAMADO)));
    }

    protected long getRepositoryCount() {
        return onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.count();
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

    protected OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity getPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity(
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity onsDadosValoresPrevisaoVersusProgramadoEolicasESolares
    ) {
        return onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository
            .findById(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityToMatchAllProperties(
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity expectedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    ) {
        assertOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityAllPropertiesEquals(
            expectedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity,
            getPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity(
                expectedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            )
        );
    }

    protected void assertPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityToMatchUpdatableProperties(
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity expectedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    ) {
        assertOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity,
            getPersistedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity(
                expectedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            )
        );
    }
}
