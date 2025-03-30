package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository;
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
 * Integration tests for the {@link OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResourceIT {

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

    private static final String DEFAULT_ID_ONS_PEQUENASUSINAS = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS_PEQUENASUSINAS = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ONS_USINA = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_PEQUENASUSINAS = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PEQUENASUSINAS = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository;

    @Autowired
    private OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc;

    private OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity;

    private OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity createEntity() {
        return new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .estadId(DEFAULT_ESTAD_ID)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .idTipousina(DEFAULT_ID_TIPOUSINA)
            .idOnsPequenasusinas(DEFAULT_ID_ONS_PEQUENASUSINAS)
            .idOnsUsina(DEFAULT_ID_ONS_USINA)
            .nomPequenasusinas(DEFAULT_NOM_PEQUENASUSINAS)
            .nomUsina(DEFAULT_NOM_USINA)
            .ceg(DEFAULT_CEG);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity createUpdatedEntity() {
        return new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .estadId(UPDATED_ESTAD_ID)
            .nomEstado(UPDATED_NOM_ESTADO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idOnsPequenasusinas(UPDATED_ID_ONS_PEQUENASUSINAS)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomPequenasusinas(UPDATED_NOM_PEQUENASUSINAS)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG);
    }

    @BeforeEach
    public void initTest() {
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity != null) {
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.delete(
                insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.delete(
                insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );
            insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        // Create the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
        var returnedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity = om.readValue(
            restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.class
        );

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityUpdatableFieldsEquals(
            returnedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity(
                returnedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            returnedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity;
    }

    @Test
    @Transactional
    void createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasWithExistingId() throws Exception {
        // Create the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas with an existing ID
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.saveAndFlush(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );

        // Get all the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasList
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].estadId").value(hasItem(DEFAULT_ESTAD_ID)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].idOnsPequenasusinas").value(hasItem(DEFAULT_ID_ONS_PEQUENASUSINAS)))
            .andExpect(jsonPath("$.[*].idOnsUsina").value(hasItem(DEFAULT_ID_ONS_USINA)))
            .andExpect(jsonPath("$.[*].nomPequenasusinas").value(hasItem(DEFAULT_NOM_PEQUENASUSINAS)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)));
    }

    @Test
    @Transactional
    void getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.saveAndFlush(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );

        // Get the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.estadId").value(DEFAULT_ESTAD_ID))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.idTipousina").value(DEFAULT_ID_TIPOUSINA))
            .andExpect(jsonPath("$.idOnsPequenasusinas").value(DEFAULT_ID_ONS_PEQUENASUSINAS))
            .andExpect(jsonPath("$.idOnsUsina").value(DEFAULT_ID_ONS_USINA))
            .andExpect(jsonPath("$.nomPequenasusinas").value(DEFAULT_NOM_PEQUENASUSINAS))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        // Get the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.saveAndFlush(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.save(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );

        // Update the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository
                .findById(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity are not directly saved in db
        em.detach(updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity);
        updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .estadId(UPDATED_ESTAD_ID)
            .nomEstado(UPDATED_NOM_ESTADO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idOnsPequenasusinas(UPDATED_ID_ONS_PEQUENASUSINAS)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomPequenasusinas(UPDATED_NOM_PEQUENASUSINAS)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG);

        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityToMatchAllProperties(
            updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
                > onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchList = Streamable.of(
                    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
                ).toList();
                OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity testOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearch =
                    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityAllPropertiesEquals(
                    testOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearch,
                    updatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.saveAndFlush(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas using partial update
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity();
        partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId()
        );

        partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG);

        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity,
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            ),
            getPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.saveAndFlush(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas using partial update
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity();
        partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId()
        );

        partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .estadId(UPDATED_ESTAD_ID)
            .nomEstado(UPDATED_NOM_ESTADO)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .idOnsPequenasusinas(UPDATED_ID_ONS_PEQUENASUSINAS)
            .idOnsUsina(UPDATED_ID_ONS_USINA)
            .nomPequenasusinas(UPDATED_NOM_PEQUENASUSINAS)
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG);

        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity(
                partialUpdatedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.saveAndFlush(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.save(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity);
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.save(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.saveAndFlush(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.save(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );

        // Search the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
        restOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].estadId").value(hasItem(DEFAULT_ESTAD_ID)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].idOnsPequenasusinas").value(hasItem(DEFAULT_ID_ONS_PEQUENASUSINAS)))
            .andExpect(jsonPath("$.[*].idOnsUsina").value(hasItem(DEFAULT_ID_ONS_USINA)))
            .andExpect(jsonPath("$.[*].nomPequenasusinas").value(hasItem(DEFAULT_NOM_PEQUENASUSINAS)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)));
    }

    protected long getRepositoryCount() {
        return onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.count();
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

    protected OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity getPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity(
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
    ) {
        return onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository
            .findById(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityToMatchAllProperties(
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity expectedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    ) {
        assertOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityAllPropertiesEquals(
            expectedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity(
                expectedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            )
        );
    }

    protected void assertPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityToMatchUpdatableProperties(
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity expectedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    ) {
        assertOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity,
            getPersistedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity(
                expectedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            )
        );
    }
}
