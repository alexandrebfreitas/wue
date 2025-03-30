package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidraulicosReservatorioBaseHorariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHidraulicosReservatorioBaseHorariaSearchRepository;
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
 * Integration tests for the {@link OnsDadosHidraulicosReservatorioBaseHorariaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosHidraulicosReservatorioBaseHorariaResourceIT {

    private static final Double DEFAULT_VAL_VOLUMEUTIL = 1D;
    private static final Double UPDATED_VAL_VOLUMEUTIL = 2D;

    private static final Double DEFAULT_VAL_VAZAOAFLUENTE = 1D;
    private static final Double UPDATED_VAL_VAZAOAFLUENTE = 2D;

    private static final Double DEFAULT_VAL_VAZAODEFLUENTE = 1D;
    private static final Double UPDATED_VAL_VAZAODEFLUENTE = 2D;

    private static final Double DEFAULT_VAL_VAZAOTURBINADA = 1D;
    private static final Double UPDATED_VAL_VAZAOTURBINADA = 2D;

    private static final Double DEFAULT_VAL_VAZAOVERTIDA = 1D;
    private static final Double UPDATED_VAL_VAZAOVERTIDA = 2D;

    private static final Double DEFAULT_VAL_VAZAOOUTRASESTRUTURAS = 1D;
    private static final Double UPDATED_VAL_VAZAOOUTRASESTRUTURAS = 2D;

    private static final Double DEFAULT_VAL_VAZAOTRANSFERIDA = 1D;
    private static final Double UPDATED_VAL_VAZAOTRANSFERIDA = 2D;

    private static final Double DEFAULT_VAL_VAZAOVERTIDANAOTURBINAVEL = 1D;
    private static final Double UPDATED_VAL_VAZAOVERTIDANAOTURBINAVEL = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-hidraulicos-reservatorio-base-horarias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-hidraulicos-reservatorio-base-horarias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosHidraulicosReservatorioBaseHorariaRepository onsDadosHidraulicosReservatorioBaseHorariaRepository;

    @Autowired
    private OnsDadosHidraulicosReservatorioBaseHorariaSearchRepository onsDadosHidraulicosReservatorioBaseHorariaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc;

    private OnsDadosHidraulicosReservatorioBaseHorariaEntity onsDadosHidraulicosReservatorioBaseHorariaEntity;

    private OnsDadosHidraulicosReservatorioBaseHorariaEntity insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHidraulicosReservatorioBaseHorariaEntity createEntity() {
        return new OnsDadosHidraulicosReservatorioBaseHorariaEntity()
            .valVolumeutil(DEFAULT_VAL_VOLUMEUTIL)
            .valVazaoafluente(DEFAULT_VAL_VAZAOAFLUENTE)
            .valVazaodefluente(DEFAULT_VAL_VAZAODEFLUENTE)
            .valVazaoturbinada(DEFAULT_VAL_VAZAOTURBINADA)
            .valVazaovertida(DEFAULT_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaotransferida(DEFAULT_VAL_VAZAOTRANSFERIDA)
            .valVazaovertidanaoturbinavel(DEFAULT_VAL_VAZAOVERTIDANAOTURBINAVEL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHidraulicosReservatorioBaseHorariaEntity createUpdatedEntity() {
        return new OnsDadosHidraulicosReservatorioBaseHorariaEntity()
            .valVolumeutil(UPDATED_VAL_VOLUMEUTIL)
            .valVazaoafluente(UPDATED_VAL_VAZAOAFLUENTE)
            .valVazaodefluente(UPDATED_VAL_VAZAODEFLUENTE)
            .valVazaoturbinada(UPDATED_VAL_VAZAOTURBINADA)
            .valVazaovertida(UPDATED_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(UPDATED_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaotransferida(UPDATED_VAL_VAZAOTRANSFERIDA)
            .valVazaovertidanaoturbinavel(UPDATED_VAL_VAZAOVERTIDANAOTURBINAVEL);
    }

    @BeforeEach
    public void initTest() {
        onsDadosHidraulicosReservatorioBaseHorariaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity != null) {
            onsDadosHidraulicosReservatorioBaseHorariaRepository.delete(insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity);
            onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.delete(insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity);
            insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        // Create the OnsDadosHidraulicosReservatorioBaseHoraria
        var returnedOnsDadosHidraulicosReservatorioBaseHorariaEntity = om.readValue(
            restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosHidraulicosReservatorioBaseHorariaEntity.class
        );

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosHidraulicosReservatorioBaseHorariaEntityUpdatableFieldsEquals(
            returnedOnsDadosHidraulicosReservatorioBaseHorariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntity(returnedOnsDadosHidraulicosReservatorioBaseHorariaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = returnedOnsDadosHidraulicosReservatorioBaseHorariaEntity;
    }

    @Test
    @Transactional
    void createOnsDadosHidraulicosReservatorioBaseHorariaWithExistingId() throws Exception {
        // Create the OnsDadosHidraulicosReservatorioBaseHoraria with an existing ID
        onsDadosHidraulicosReservatorioBaseHorariaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosHidraulicosReservatorioBaseHorarias() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );

        // Get all the onsDadosHidraulicosReservatorioBaseHorariaList
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosHidraulicosReservatorioBaseHorariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].valVolumeutil").value(hasItem(DEFAULT_VAL_VOLUMEUTIL)))
            .andExpect(jsonPath("$.[*].valVazaoafluente").value(hasItem(DEFAULT_VAL_VAZAOAFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaodefluente").value(hasItem(DEFAULT_VAL_VAZAODEFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaoturbinada").value(hasItem(DEFAULT_VAL_VAZAOTURBINADA)))
            .andExpect(jsonPath("$.[*].valVazaovertida").value(hasItem(DEFAULT_VAL_VAZAOVERTIDA)))
            .andExpect(jsonPath("$.[*].valVazaooutrasestruturas").value(hasItem(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS)))
            .andExpect(jsonPath("$.[*].valVazaotransferida").value(hasItem(DEFAULT_VAL_VAZAOTRANSFERIDA)))
            .andExpect(jsonPath("$.[*].valVazaovertidanaoturbinavel").value(hasItem(DEFAULT_VAL_VAZAOVERTIDANAOTURBINAVEL)));
    }

    @Test
    @Transactional
    void getOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );

        // Get the onsDadosHidraulicosReservatorioBaseHoraria
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseHorariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosHidraulicosReservatorioBaseHorariaEntity.getId().intValue()))
            .andExpect(jsonPath("$.valVolumeutil").value(DEFAULT_VAL_VOLUMEUTIL))
            .andExpect(jsonPath("$.valVazaoafluente").value(DEFAULT_VAL_VAZAOAFLUENTE))
            .andExpect(jsonPath("$.valVazaodefluente").value(DEFAULT_VAL_VAZAODEFLUENTE))
            .andExpect(jsonPath("$.valVazaoturbinada").value(DEFAULT_VAL_VAZAOTURBINADA))
            .andExpect(jsonPath("$.valVazaovertida").value(DEFAULT_VAL_VAZAOVERTIDA))
            .andExpect(jsonPath("$.valVazaooutrasestruturas").value(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS))
            .andExpect(jsonPath("$.valVazaotransferida").value(DEFAULT_VAL_VAZAOTRANSFERIDA))
            .andExpect(jsonPath("$.valVazaovertidanaoturbinavel").value(DEFAULT_VAL_VAZAOVERTIDANAOTURBINAVEL));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        // Get the onsDadosHidraulicosReservatorioBaseHoraria
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.save(onsDadosHidraulicosReservatorioBaseHorariaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());

        // Update the onsDadosHidraulicosReservatorioBaseHoraria
        OnsDadosHidraulicosReservatorioBaseHorariaEntity updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity =
            onsDadosHidraulicosReservatorioBaseHorariaRepository
                .findById(onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity are not directly saved in db
        em.detach(updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity);
        updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity
            .valVolumeutil(UPDATED_VAL_VOLUMEUTIL)
            .valVazaoafluente(UPDATED_VAL_VAZAOAFLUENTE)
            .valVazaodefluente(UPDATED_VAL_VAZAODEFLUENTE)
            .valVazaoturbinada(UPDATED_VAL_VAZAOTURBINADA)
            .valVazaovertida(UPDATED_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(UPDATED_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaotransferida(UPDATED_VAL_VAZAOTRANSFERIDA)
            .valVazaovertidanaoturbinavel(UPDATED_VAL_VAZAOVERTIDANAOTURBINAVEL);

        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntityToMatchAllProperties(
            updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosHidraulicosReservatorioBaseHorariaEntity> onsDadosHidraulicosReservatorioBaseHorariaSearchList = Streamable.of(
                    onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll()
                ).toList();
                OnsDadosHidraulicosReservatorioBaseHorariaEntity testOnsDadosHidraulicosReservatorioBaseHorariaSearch =
                    onsDadosHidraulicosReservatorioBaseHorariaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosHidraulicosReservatorioBaseHorariaEntityAllPropertiesEquals(
                    testOnsDadosHidraulicosReservatorioBaseHorariaSearch,
                    updatedOnsDadosHidraulicosReservatorioBaseHorariaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosHidraulicosReservatorioBaseHorariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHidraulicosReservatorioBaseHoraria using partial update
        OnsDadosHidraulicosReservatorioBaseHorariaEntity partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity =
            new OnsDadosHidraulicosReservatorioBaseHorariaEntity();
        partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity.setId(onsDadosHidraulicosReservatorioBaseHorariaEntity.getId());

        partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity
            .valVolumeutil(UPDATED_VAL_VOLUMEUTIL)
            .valVazaodefluente(UPDATED_VAL_VAZAODEFLUENTE)
            .valVazaotransferida(UPDATED_VAL_VAZAOTRANSFERIDA)
            .valVazaovertidanaoturbinavel(UPDATED_VAL_VAZAOVERTIDANAOTURBINAVEL);

        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHidraulicosReservatorioBaseHorariaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity,
                onsDadosHidraulicosReservatorioBaseHorariaEntity
            ),
            getPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntity(onsDadosHidraulicosReservatorioBaseHorariaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosHidraulicosReservatorioBaseHorariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHidraulicosReservatorioBaseHoraria using partial update
        OnsDadosHidraulicosReservatorioBaseHorariaEntity partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity =
            new OnsDadosHidraulicosReservatorioBaseHorariaEntity();
        partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity.setId(onsDadosHidraulicosReservatorioBaseHorariaEntity.getId());

        partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity
            .valVolumeutil(UPDATED_VAL_VOLUMEUTIL)
            .valVazaoafluente(UPDATED_VAL_VAZAOAFLUENTE)
            .valVazaodefluente(UPDATED_VAL_VAZAODEFLUENTE)
            .valVazaoturbinada(UPDATED_VAL_VAZAOTURBINADA)
            .valVazaovertida(UPDATED_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(UPDATED_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaotransferida(UPDATED_VAL_VAZAOTRANSFERIDA)
            .valVazaovertidanaoturbinavel(UPDATED_VAL_VAZAOVERTIDANAOTURBINAVEL);

        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHidraulicosReservatorioBaseHorariaEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntity(partialUpdatedOnsDadosHidraulicosReservatorioBaseHorariaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseHorariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHidraulicosReservatorioBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );
        onsDadosHidraulicosReservatorioBaseHorariaRepository.save(onsDadosHidraulicosReservatorioBaseHorariaEntity);
        onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.save(onsDadosHidraulicosReservatorioBaseHorariaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosHidraulicosReservatorioBaseHoraria
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosHidraulicosReservatorioBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );
        onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.save(onsDadosHidraulicosReservatorioBaseHorariaEntity);

        // Search the onsDadosHidraulicosReservatorioBaseHoraria
        restOnsDadosHidraulicosReservatorioBaseHorariaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosHidraulicosReservatorioBaseHorariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosHidraulicosReservatorioBaseHorariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].valVolumeutil").value(hasItem(DEFAULT_VAL_VOLUMEUTIL)))
            .andExpect(jsonPath("$.[*].valVazaoafluente").value(hasItem(DEFAULT_VAL_VAZAOAFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaodefluente").value(hasItem(DEFAULT_VAL_VAZAODEFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaoturbinada").value(hasItem(DEFAULT_VAL_VAZAOTURBINADA)))
            .andExpect(jsonPath("$.[*].valVazaovertida").value(hasItem(DEFAULT_VAL_VAZAOVERTIDA)))
            .andExpect(jsonPath("$.[*].valVazaooutrasestruturas").value(hasItem(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS)))
            .andExpect(jsonPath("$.[*].valVazaotransferida").value(hasItem(DEFAULT_VAL_VAZAOTRANSFERIDA)))
            .andExpect(jsonPath("$.[*].valVazaovertidanaoturbinavel").value(hasItem(DEFAULT_VAL_VAZAOVERTIDANAOTURBINAVEL)));
    }

    protected long getRepositoryCount() {
        return onsDadosHidraulicosReservatorioBaseHorariaRepository.count();
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

    protected OnsDadosHidraulicosReservatorioBaseHorariaEntity getPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntity(
        OnsDadosHidraulicosReservatorioBaseHorariaEntity onsDadosHidraulicosReservatorioBaseHoraria
    ) {
        return onsDadosHidraulicosReservatorioBaseHorariaRepository
            .findById(onsDadosHidraulicosReservatorioBaseHoraria.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntityToMatchAllProperties(
        OnsDadosHidraulicosReservatorioBaseHorariaEntity expectedOnsDadosHidraulicosReservatorioBaseHorariaEntity
    ) {
        assertOnsDadosHidraulicosReservatorioBaseHorariaEntityAllPropertiesEquals(
            expectedOnsDadosHidraulicosReservatorioBaseHorariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntity(expectedOnsDadosHidraulicosReservatorioBaseHorariaEntity)
        );
    }

    protected void assertPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntityToMatchUpdatableProperties(
        OnsDadosHidraulicosReservatorioBaseHorariaEntity expectedOnsDadosHidraulicosReservatorioBaseHorariaEntity
    ) {
        assertOnsDadosHidraulicosReservatorioBaseHorariaEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosHidraulicosReservatorioBaseHorariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseHorariaEntity(expectedOnsDadosHidraulicosReservatorioBaseHorariaEntity)
        );
    }
}
