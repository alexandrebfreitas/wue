package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidrologicosVolumeEsperaRecomendadoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository;
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
 * Integration tests for the {@link OnsDadosHidrologicosVolumeEsperaRecomendadoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosHidrologicosVolumeEsperaRecomendadoResourceIT {

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_VOLUMEESPERA = 1D;
    private static final Double UPDATED_VAL_VOLUMEESPERA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-hidrologicos-volume-espera-recomendados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-hidrologicos-volume-espera-recomendados/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosHidrologicosVolumeEsperaRecomendadoRepository onsDadosHidrologicosVolumeEsperaRecomendadoRepository;

    @Autowired
    private OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc;

    private OnsDadosHidrologicosVolumeEsperaRecomendadoEntity onsDadosHidrologicosVolumeEsperaRecomendadoEntity;

    private OnsDadosHidrologicosVolumeEsperaRecomendadoEntity insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHidrologicosVolumeEsperaRecomendadoEntity createEntity() {
        return new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity()
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valVolumeespera(DEFAULT_VAL_VOLUMEESPERA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHidrologicosVolumeEsperaRecomendadoEntity createUpdatedEntity() {
        return new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity()
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valVolumeespera(UPDATED_VAL_VOLUMEESPERA);
    }

    @BeforeEach
    public void initTest() {
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity != null) {
            onsDadosHidrologicosVolumeEsperaRecomendadoRepository.delete(insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity);
            onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.delete(insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity);
            insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        // Create the OnsDadosHidrologicosVolumeEsperaRecomendado
        var returnedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = om.readValue(
            restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosHidrologicosVolumeEsperaRecomendadoEntity.class
        );

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosHidrologicosVolumeEsperaRecomendadoEntityUpdatableFieldsEquals(
            returnedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity,
            getPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity(returnedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = returnedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity;
    }

    @Test
    @Transactional
    void createOnsDadosHidrologicosVolumeEsperaRecomendadoWithExistingId() throws Exception {
        // Create the OnsDadosHidrologicosVolumeEsperaRecomendado with an existing ID
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosHidrologicosVolumeEsperaRecomendados() throws Exception {
        // Initialize the database
        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.saveAndFlush(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );

        // Get all the onsDadosHidrologicosVolumeEsperaRecomendadoList
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valVolumeespera").value(hasItem(DEFAULT_VAL_VOLUMEESPERA)));
    }

    @Test
    @Transactional
    void getOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        // Initialize the database
        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.saveAndFlush(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );

        // Get the onsDadosHidrologicosVolumeEsperaRecomendado
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId().intValue()))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valVolumeespera").value(DEFAULT_VAL_VOLUMEESPERA));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        // Get the onsDadosHidrologicosVolumeEsperaRecomendado
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        // Initialize the database
        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.saveAndFlush(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.save(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());

        // Update the onsDadosHidrologicosVolumeEsperaRecomendado
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity =
            onsDadosHidrologicosVolumeEsperaRecomendadoRepository
                .findById(onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity are not directly saved in db
        em.detach(updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity);
        updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valVolumeespera(UPDATED_VAL_VOLUMEESPERA);

        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntityToMatchAllProperties(
            updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> onsDadosHidrologicosVolumeEsperaRecomendadoSearchList =
                    Streamable.of(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll()).toList();
                OnsDadosHidrologicosVolumeEsperaRecomendadoEntity testOnsDadosHidrologicosVolumeEsperaRecomendadoSearch =
                    onsDadosHidrologicosVolumeEsperaRecomendadoSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosHidrologicosVolumeEsperaRecomendadoEntityAllPropertiesEquals(
                    testOnsDadosHidrologicosVolumeEsperaRecomendadoSearch,
                    updatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosHidrologicosVolumeEsperaRecomendadoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.saveAndFlush(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHidrologicosVolumeEsperaRecomendado using partial update
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity =
            new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity();
        partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId());

        partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valVolumeespera(UPDATED_VAL_VOLUMEESPERA);

        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHidrologicosVolumeEsperaRecomendadoEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity,
                onsDadosHidrologicosVolumeEsperaRecomendadoEntity
            ),
            getPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity(onsDadosHidrologicosVolumeEsperaRecomendadoEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosHidrologicosVolumeEsperaRecomendadoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.saveAndFlush(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHidrologicosVolumeEsperaRecomendado using partial update
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity =
            new OnsDadosHidrologicosVolumeEsperaRecomendadoEntity();
        partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId());

        partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valVolumeespera(UPDATED_VAL_VOLUMEESPERA);

        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHidrologicosVolumeEsperaRecomendadoEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity,
            getPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity(partialUpdatedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidrologicosVolumeEsperaRecomendadoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHidrologicosVolumeEsperaRecomendado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        // Initialize the database
        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.saveAndFlush(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );
        onsDadosHidrologicosVolumeEsperaRecomendadoRepository.save(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);
        onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.save(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosHidrologicosVolumeEsperaRecomendado
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosHidrologicosVolumeEsperaRecomendado() throws Exception {
        // Initialize the database
        insertedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.saveAndFlush(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );
        onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.save(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);

        // Search the onsDadosHidrologicosVolumeEsperaRecomendado
        restOnsDadosHidrologicosVolumeEsperaRecomendadoMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valVolumeespera").value(hasItem(DEFAULT_VAL_VOLUMEESPERA)));
    }

    protected long getRepositoryCount() {
        return onsDadosHidrologicosVolumeEsperaRecomendadoRepository.count();
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

    protected OnsDadosHidrologicosVolumeEsperaRecomendadoEntity getPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity(
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity onsDadosHidrologicosVolumeEsperaRecomendado
    ) {
        return onsDadosHidrologicosVolumeEsperaRecomendadoRepository
            .findById(onsDadosHidrologicosVolumeEsperaRecomendado.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntityToMatchAllProperties(
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity expectedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity
    ) {
        assertOnsDadosHidrologicosVolumeEsperaRecomendadoEntityAllPropertiesEquals(
            expectedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity,
            getPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity(expectedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity)
        );
    }

    protected void assertPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntityToMatchUpdatableProperties(
        OnsDadosHidrologicosVolumeEsperaRecomendadoEntity expectedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity
    ) {
        assertOnsDadosHidrologicosVolumeEsperaRecomendadoEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity,
            getPersistedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity(expectedOnsDadosHidrologicosVolumeEsperaRecomendadoEntity)
        );
    }
}
