package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosDisponibilidadeUsinasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosDisponibilidadeUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosDisponibilidadeUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosDisponibilidadeUsinasSearchRepository;
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
 * Integration tests for the {@link OnsDadosDisponibilidadeUsinasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosDisponibilidadeUsinasResourceIT {

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

    private static final String DEFAULT_ID_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_ID_TIPOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPOCOMBUSTIVEL = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOCOMBUSTIVEL = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ONS = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_POTENCIAINSTALADA = 1D;
    private static final Double UPDATED_VAL_POTENCIAINSTALADA = 2D;

    private static final Double DEFAULT_VAL_DISPOPERACIONAL = 1D;
    private static final Double UPDATED_VAL_DISPOPERACIONAL = 2D;

    private static final Double DEFAULT_VAL_DISPSINCRONIZADA = 1D;
    private static final Double UPDATED_VAL_DISPSINCRONIZADA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-disponibilidade-usinas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-disponibilidade-usinas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosDisponibilidadeUsinasRepository onsDadosDisponibilidadeUsinasRepository;

    @Autowired
    private OnsDadosDisponibilidadeUsinasSearchRepository onsDadosDisponibilidadeUsinasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosDisponibilidadeUsinasMockMvc;

    private OnsDadosDisponibilidadeUsinasEntity onsDadosDisponibilidadeUsinasEntity;

    private OnsDadosDisponibilidadeUsinasEntity insertedOnsDadosDisponibilidadeUsinasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosDisponibilidadeUsinasEntity createEntity() {
        return new OnsDadosDisponibilidadeUsinasEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .nomUsina(DEFAULT_NOM_USINA)
            .idTipousina(DEFAULT_ID_TIPOUSINA)
            .nomTipocombustivel(DEFAULT_NOM_TIPOCOMBUSTIVEL)
            .idOns(DEFAULT_ID_ONS)
            .ceg(DEFAULT_CEG)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valPotenciainstalada(DEFAULT_VAL_POTENCIAINSTALADA)
            .valDispoperacional(DEFAULT_VAL_DISPOPERACIONAL)
            .valDispsincronizada(DEFAULT_VAL_DISPSINCRONIZADA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosDisponibilidadeUsinasEntity createUpdatedEntity() {
        return new OnsDadosDisponibilidadeUsinasEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valPotenciainstalada(UPDATED_VAL_POTENCIAINSTALADA)
            .valDispoperacional(UPDATED_VAL_DISPOPERACIONAL)
            .valDispsincronizada(UPDATED_VAL_DISPSINCRONIZADA);
    }

    @BeforeEach
    public void initTest() {
        onsDadosDisponibilidadeUsinasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosDisponibilidadeUsinasEntity != null) {
            onsDadosDisponibilidadeUsinasRepository.delete(insertedOnsDadosDisponibilidadeUsinasEntity);
            onsDadosDisponibilidadeUsinasSearchRepository.delete(insertedOnsDadosDisponibilidadeUsinasEntity);
            insertedOnsDadosDisponibilidadeUsinasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosDisponibilidadeUsinas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        // Create the OnsDadosDisponibilidadeUsinas
        var returnedOnsDadosDisponibilidadeUsinasEntity = om.readValue(
            restOnsDadosDisponibilidadeUsinasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosDisponibilidadeUsinasEntity.class
        );

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosDisponibilidadeUsinasEntityUpdatableFieldsEquals(
            returnedOnsDadosDisponibilidadeUsinasEntity,
            getPersistedOnsDadosDisponibilidadeUsinasEntity(returnedOnsDadosDisponibilidadeUsinasEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosDisponibilidadeUsinasEntity = returnedOnsDadosDisponibilidadeUsinasEntity;
    }

    @Test
    @Transactional
    void createOnsDadosDisponibilidadeUsinasWithExistingId() throws Exception {
        // Create the OnsDadosDisponibilidadeUsinas with an existing ID
        onsDadosDisponibilidadeUsinasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosDisponibilidadeUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.saveAndFlush(
            onsDadosDisponibilidadeUsinasEntity
        );

        // Get all the onsDadosDisponibilidadeUsinasList
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosDisponibilidadeUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomTipocombustivel").value(hasItem(DEFAULT_NOM_TIPOCOMBUSTIVEL)))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valPotenciainstalada").value(hasItem(DEFAULT_VAL_POTENCIAINSTALADA)))
            .andExpect(jsonPath("$.[*].valDispoperacional").value(hasItem(DEFAULT_VAL_DISPOPERACIONAL)))
            .andExpect(jsonPath("$.[*].valDispsincronizada").value(hasItem(DEFAULT_VAL_DISPSINCRONIZADA)));
    }

    @Test
    @Transactional
    void getOnsDadosDisponibilidadeUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.saveAndFlush(
            onsDadosDisponibilidadeUsinasEntity
        );

        // Get the onsDadosDisponibilidadeUsinas
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosDisponibilidadeUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosDisponibilidadeUsinasEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.idTipousina").value(DEFAULT_ID_TIPOUSINA))
            .andExpect(jsonPath("$.nomTipocombustivel").value(DEFAULT_NOM_TIPOCOMBUSTIVEL))
            .andExpect(jsonPath("$.idOns").value(DEFAULT_ID_ONS))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valPotenciainstalada").value(DEFAULT_VAL_POTENCIAINSTALADA))
            .andExpect(jsonPath("$.valDispoperacional").value(DEFAULT_VAL_DISPOPERACIONAL))
            .andExpect(jsonPath("$.valDispsincronizada").value(DEFAULT_VAL_DISPSINCRONIZADA));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosDisponibilidadeUsinas() throws Exception {
        // Get the onsDadosDisponibilidadeUsinas
        restOnsDadosDisponibilidadeUsinasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosDisponibilidadeUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.saveAndFlush(
            onsDadosDisponibilidadeUsinasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosDisponibilidadeUsinasSearchRepository.save(onsDadosDisponibilidadeUsinasEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());

        // Update the onsDadosDisponibilidadeUsinas
        OnsDadosDisponibilidadeUsinasEntity updatedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository
            .findById(onsDadosDisponibilidadeUsinasEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosDisponibilidadeUsinasEntity are not directly saved in db
        em.detach(updatedOnsDadosDisponibilidadeUsinasEntity);
        updatedOnsDadosDisponibilidadeUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valPotenciainstalada(UPDATED_VAL_POTENCIAINSTALADA)
            .valDispoperacional(UPDATED_VAL_DISPOPERACIONAL)
            .valDispsincronizada(UPDATED_VAL_DISPSINCRONIZADA);

        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosDisponibilidadeUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosDisponibilidadeUsinasEntityToMatchAllProperties(updatedOnsDadosDisponibilidadeUsinasEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosDisponibilidadeUsinasEntity> onsDadosDisponibilidadeUsinasSearchList = Streamable.of(
                    onsDadosDisponibilidadeUsinasSearchRepository.findAll()
                ).toList();
                OnsDadosDisponibilidadeUsinasEntity testOnsDadosDisponibilidadeUsinasSearch = onsDadosDisponibilidadeUsinasSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsDadosDisponibilidadeUsinasEntityAllPropertiesEquals(
                    testOnsDadosDisponibilidadeUsinasSearch,
                    updatedOnsDadosDisponibilidadeUsinasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosDisponibilidadeUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        onsDadosDisponibilidadeUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosDisponibilidadeUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosDisponibilidadeUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        onsDadosDisponibilidadeUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosDisponibilidadeUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        onsDadosDisponibilidadeUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosDisponibilidadeUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.saveAndFlush(
            onsDadosDisponibilidadeUsinasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosDisponibilidadeUsinas using partial update
        OnsDadosDisponibilidadeUsinasEntity partialUpdatedOnsDadosDisponibilidadeUsinasEntity = new OnsDadosDisponibilidadeUsinasEntity();
        partialUpdatedOnsDadosDisponibilidadeUsinasEntity.setId(onsDadosDisponibilidadeUsinasEntity.getId());

        partialUpdatedOnsDadosDisponibilidadeUsinasEntity
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valPotenciainstalada(UPDATED_VAL_POTENCIAINSTALADA)
            .valDispoperacional(UPDATED_VAL_DISPOPERACIONAL);

        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosDisponibilidadeUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosDisponibilidadeUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosDisponibilidadeUsinasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsDadosDisponibilidadeUsinasEntity, onsDadosDisponibilidadeUsinasEntity),
            getPersistedOnsDadosDisponibilidadeUsinasEntity(onsDadosDisponibilidadeUsinasEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosDisponibilidadeUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.saveAndFlush(
            onsDadosDisponibilidadeUsinasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosDisponibilidadeUsinas using partial update
        OnsDadosDisponibilidadeUsinasEntity partialUpdatedOnsDadosDisponibilidadeUsinasEntity = new OnsDadosDisponibilidadeUsinasEntity();
        partialUpdatedOnsDadosDisponibilidadeUsinasEntity.setId(onsDadosDisponibilidadeUsinasEntity.getId());

        partialUpdatedOnsDadosDisponibilidadeUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomUsina(UPDATED_NOM_USINA)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valPotenciainstalada(UPDATED_VAL_POTENCIAINSTALADA)
            .valDispoperacional(UPDATED_VAL_DISPOPERACIONAL)
            .valDispsincronizada(UPDATED_VAL_DISPSINCRONIZADA);

        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosDisponibilidadeUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosDisponibilidadeUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosDisponibilidadeUsinasEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosDisponibilidadeUsinasEntity,
            getPersistedOnsDadosDisponibilidadeUsinasEntity(partialUpdatedOnsDadosDisponibilidadeUsinasEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosDisponibilidadeUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        onsDadosDisponibilidadeUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosDisponibilidadeUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosDisponibilidadeUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        onsDadosDisponibilidadeUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosDisponibilidadeUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        onsDadosDisponibilidadeUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosDisponibilidadeUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosDisponibilidadeUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosDisponibilidadeUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.saveAndFlush(
            onsDadosDisponibilidadeUsinasEntity
        );
        onsDadosDisponibilidadeUsinasRepository.save(onsDadosDisponibilidadeUsinasEntity);
        onsDadosDisponibilidadeUsinasSearchRepository.save(onsDadosDisponibilidadeUsinasEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosDisponibilidadeUsinas
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsDadosDisponibilidadeUsinasEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosDisponibilidadeUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosDisponibilidadeUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.saveAndFlush(
            onsDadosDisponibilidadeUsinasEntity
        );
        onsDadosDisponibilidadeUsinasSearchRepository.save(onsDadosDisponibilidadeUsinasEntity);

        // Search the onsDadosDisponibilidadeUsinas
        restOnsDadosDisponibilidadeUsinasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosDisponibilidadeUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosDisponibilidadeUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomTipocombustivel").value(hasItem(DEFAULT_NOM_TIPOCOMBUSTIVEL)))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valPotenciainstalada").value(hasItem(DEFAULT_VAL_POTENCIAINSTALADA)))
            .andExpect(jsonPath("$.[*].valDispoperacional").value(hasItem(DEFAULT_VAL_DISPOPERACIONAL)))
            .andExpect(jsonPath("$.[*].valDispsincronizada").value(hasItem(DEFAULT_VAL_DISPSINCRONIZADA)));
    }

    protected long getRepositoryCount() {
        return onsDadosDisponibilidadeUsinasRepository.count();
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

    protected OnsDadosDisponibilidadeUsinasEntity getPersistedOnsDadosDisponibilidadeUsinasEntity(
        OnsDadosDisponibilidadeUsinasEntity onsDadosDisponibilidadeUsinas
    ) {
        return onsDadosDisponibilidadeUsinasRepository.findById(onsDadosDisponibilidadeUsinas.getId()).orElseThrow();
    }

    protected void assertPersistedOnsDadosDisponibilidadeUsinasEntityToMatchAllProperties(
        OnsDadosDisponibilidadeUsinasEntity expectedOnsDadosDisponibilidadeUsinasEntity
    ) {
        assertOnsDadosDisponibilidadeUsinasEntityAllPropertiesEquals(
            expectedOnsDadosDisponibilidadeUsinasEntity,
            getPersistedOnsDadosDisponibilidadeUsinasEntity(expectedOnsDadosDisponibilidadeUsinasEntity)
        );
    }

    protected void assertPersistedOnsDadosDisponibilidadeUsinasEntityToMatchUpdatableProperties(
        OnsDadosDisponibilidadeUsinasEntity expectedOnsDadosDisponibilidadeUsinasEntity
    ) {
        assertOnsDadosDisponibilidadeUsinasEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosDisponibilidadeUsinasEntity,
            getPersistedOnsDadosDisponibilidadeUsinasEntity(expectedOnsDadosDisponibilidadeUsinasEntity)
        );
    }
}
