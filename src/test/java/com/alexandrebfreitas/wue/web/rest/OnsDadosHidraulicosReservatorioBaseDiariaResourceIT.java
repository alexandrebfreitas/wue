package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseDiariaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidraulicosReservatorioBaseDiariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHidraulicosReservatorioBaseDiariaSearchRepository;
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
 * Integration tests for the {@link OnsDadosHidraulicosReservatorioBaseDiariaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosHidraulicosReservatorioBaseDiariaResourceIT {

    private static final Double DEFAULT_VAL_NIVELMONTANTE = 1D;
    private static final Double UPDATED_VAL_NIVELMONTANTE = 2D;

    private static final Double DEFAULT_VAL_NIVELJUSANTE = 1D;
    private static final Double UPDATED_VAL_NIVELJUSANTE = 2D;

    private static final Double DEFAULT_VAL_VOLUMEUTILCON = 1D;
    private static final Double UPDATED_VAL_VOLUMEUTILCON = 2D;

    private static final Double DEFAULT_VAL_VAZAOAFLUENTE = 1D;
    private static final Double UPDATED_VAL_VAZAOAFLUENTE = 2D;

    private static final Double DEFAULT_VAL_VAZAOTURBINADA = 1D;
    private static final Double UPDATED_VAL_VAZAOTURBINADA = 2D;

    private static final Double DEFAULT_VAL_VAZAOVERTIDA = 1D;
    private static final Double UPDATED_VAL_VAZAOVERTIDA = 2D;

    private static final Double DEFAULT_VAL_VAZAOOUTRASESTRUTURAS = 1D;
    private static final Double UPDATED_VAL_VAZAOOUTRASESTRUTURAS = 2D;

    private static final Double DEFAULT_VAL_VAZAODEFLUENTE = 1D;
    private static final Double UPDATED_VAL_VAZAODEFLUENTE = 2D;

    private static final Double DEFAULT_VAL_VAZAOTRANSFERIDA = 1D;
    private static final Double UPDATED_VAL_VAZAOTRANSFERIDA = 2D;

    private static final Double DEFAULT_VAL_VAZAONATURAL = 1D;
    private static final Double UPDATED_VAL_VAZAONATURAL = 2D;

    private static final Double DEFAULT_VAL_VAZAOARTIFICIAL = 1D;
    private static final Double UPDATED_VAL_VAZAOARTIFICIAL = 2D;

    private static final Double DEFAULT_VAL_VAZAOINCREMENTAL = 1D;
    private static final Double UPDATED_VAL_VAZAOINCREMENTAL = 2D;

    private static final Double DEFAULT_VAL_VAZAOEVAPORACAOLIQUIDA = 1D;
    private static final Double UPDATED_VAL_VAZAOEVAPORACAOLIQUIDA = 2D;

    private static final Double DEFAULT_VAL_VAZAOUSOCONSUNTIVO = 1D;
    private static final Double UPDATED_VAL_VAZAOUSOCONSUNTIVO = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-hidraulicos-reservatorio-base-diarias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-hidraulicos-reservatorio-base-diarias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosHidraulicosReservatorioBaseDiariaRepository onsDadosHidraulicosReservatorioBaseDiariaRepository;

    @Autowired
    private OnsDadosHidraulicosReservatorioBaseDiariaSearchRepository onsDadosHidraulicosReservatorioBaseDiariaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc;

    private OnsDadosHidraulicosReservatorioBaseDiariaEntity onsDadosHidraulicosReservatorioBaseDiariaEntity;

    private OnsDadosHidraulicosReservatorioBaseDiariaEntity insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHidraulicosReservatorioBaseDiariaEntity createEntity() {
        return new OnsDadosHidraulicosReservatorioBaseDiariaEntity()
            .valNivelmontante(DEFAULT_VAL_NIVELMONTANTE)
            .valNiveljusante(DEFAULT_VAL_NIVELJUSANTE)
            .valVolumeutilcon(DEFAULT_VAL_VOLUMEUTILCON)
            .valVazaoafluente(DEFAULT_VAL_VAZAOAFLUENTE)
            .valVazaoturbinada(DEFAULT_VAL_VAZAOTURBINADA)
            .valVazaovertida(DEFAULT_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaodefluente(DEFAULT_VAL_VAZAODEFLUENTE)
            .valVazaotransferida(DEFAULT_VAL_VAZAOTRANSFERIDA)
            .valVazaonatural(DEFAULT_VAL_VAZAONATURAL)
            .valVazaoartificial(DEFAULT_VAL_VAZAOARTIFICIAL)
            .valVazaoincremental(DEFAULT_VAL_VAZAOINCREMENTAL)
            .valVazaoevaporacaoliquida(DEFAULT_VAL_VAZAOEVAPORACAOLIQUIDA)
            .valVazaousoconsuntivo(DEFAULT_VAL_VAZAOUSOCONSUNTIVO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHidraulicosReservatorioBaseDiariaEntity createUpdatedEntity() {
        return new OnsDadosHidraulicosReservatorioBaseDiariaEntity()
            .valNivelmontante(UPDATED_VAL_NIVELMONTANTE)
            .valNiveljusante(UPDATED_VAL_NIVELJUSANTE)
            .valVolumeutilcon(UPDATED_VAL_VOLUMEUTILCON)
            .valVazaoafluente(UPDATED_VAL_VAZAOAFLUENTE)
            .valVazaoturbinada(UPDATED_VAL_VAZAOTURBINADA)
            .valVazaovertida(UPDATED_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(UPDATED_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaodefluente(UPDATED_VAL_VAZAODEFLUENTE)
            .valVazaotransferida(UPDATED_VAL_VAZAOTRANSFERIDA)
            .valVazaonatural(UPDATED_VAL_VAZAONATURAL)
            .valVazaoartificial(UPDATED_VAL_VAZAOARTIFICIAL)
            .valVazaoincremental(UPDATED_VAL_VAZAOINCREMENTAL)
            .valVazaoevaporacaoliquida(UPDATED_VAL_VAZAOEVAPORACAOLIQUIDA)
            .valVazaousoconsuntivo(UPDATED_VAL_VAZAOUSOCONSUNTIVO);
    }

    @BeforeEach
    public void initTest() {
        onsDadosHidraulicosReservatorioBaseDiariaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity != null) {
            onsDadosHidraulicosReservatorioBaseDiariaRepository.delete(insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity);
            onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.delete(insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity);
            insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        // Create the OnsDadosHidraulicosReservatorioBaseDiaria
        var returnedOnsDadosHidraulicosReservatorioBaseDiariaEntity = om.readValue(
            restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosHidraulicosReservatorioBaseDiariaEntity.class
        );

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityUpdatableFieldsEquals(
            returnedOnsDadosHidraulicosReservatorioBaseDiariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntity(returnedOnsDadosHidraulicosReservatorioBaseDiariaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = returnedOnsDadosHidraulicosReservatorioBaseDiariaEntity;
    }

    @Test
    @Transactional
    void createOnsDadosHidraulicosReservatorioBaseDiariaWithExistingId() throws Exception {
        // Create the OnsDadosHidraulicosReservatorioBaseDiaria with an existing ID
        onsDadosHidraulicosReservatorioBaseDiariaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosHidraulicosReservatorioBaseDiarias() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );

        // Get all the onsDadosHidraulicosReservatorioBaseDiariaList
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosHidraulicosReservatorioBaseDiariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].valNivelmontante").value(hasItem(DEFAULT_VAL_NIVELMONTANTE)))
            .andExpect(jsonPath("$.[*].valNiveljusante").value(hasItem(DEFAULT_VAL_NIVELJUSANTE)))
            .andExpect(jsonPath("$.[*].valVolumeutilcon").value(hasItem(DEFAULT_VAL_VOLUMEUTILCON)))
            .andExpect(jsonPath("$.[*].valVazaoafluente").value(hasItem(DEFAULT_VAL_VAZAOAFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaoturbinada").value(hasItem(DEFAULT_VAL_VAZAOTURBINADA)))
            .andExpect(jsonPath("$.[*].valVazaovertida").value(hasItem(DEFAULT_VAL_VAZAOVERTIDA)))
            .andExpect(jsonPath("$.[*].valVazaooutrasestruturas").value(hasItem(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS)))
            .andExpect(jsonPath("$.[*].valVazaodefluente").value(hasItem(DEFAULT_VAL_VAZAODEFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaotransferida").value(hasItem(DEFAULT_VAL_VAZAOTRANSFERIDA)))
            .andExpect(jsonPath("$.[*].valVazaonatural").value(hasItem(DEFAULT_VAL_VAZAONATURAL)))
            .andExpect(jsonPath("$.[*].valVazaoartificial").value(hasItem(DEFAULT_VAL_VAZAOARTIFICIAL)))
            .andExpect(jsonPath("$.[*].valVazaoincremental").value(hasItem(DEFAULT_VAL_VAZAOINCREMENTAL)))
            .andExpect(jsonPath("$.[*].valVazaoevaporacaoliquida").value(hasItem(DEFAULT_VAL_VAZAOEVAPORACAOLIQUIDA)))
            .andExpect(jsonPath("$.[*].valVazaousoconsuntivo").value(hasItem(DEFAULT_VAL_VAZAOUSOCONSUNTIVO)));
    }

    @Test
    @Transactional
    void getOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );

        // Get the onsDadosHidraulicosReservatorioBaseDiaria
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseDiariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosHidraulicosReservatorioBaseDiariaEntity.getId().intValue()))
            .andExpect(jsonPath("$.valNivelmontante").value(DEFAULT_VAL_NIVELMONTANTE))
            .andExpect(jsonPath("$.valNiveljusante").value(DEFAULT_VAL_NIVELJUSANTE))
            .andExpect(jsonPath("$.valVolumeutilcon").value(DEFAULT_VAL_VOLUMEUTILCON))
            .andExpect(jsonPath("$.valVazaoafluente").value(DEFAULT_VAL_VAZAOAFLUENTE))
            .andExpect(jsonPath("$.valVazaoturbinada").value(DEFAULT_VAL_VAZAOTURBINADA))
            .andExpect(jsonPath("$.valVazaovertida").value(DEFAULT_VAL_VAZAOVERTIDA))
            .andExpect(jsonPath("$.valVazaooutrasestruturas").value(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS))
            .andExpect(jsonPath("$.valVazaodefluente").value(DEFAULT_VAL_VAZAODEFLUENTE))
            .andExpect(jsonPath("$.valVazaotransferida").value(DEFAULT_VAL_VAZAOTRANSFERIDA))
            .andExpect(jsonPath("$.valVazaonatural").value(DEFAULT_VAL_VAZAONATURAL))
            .andExpect(jsonPath("$.valVazaoartificial").value(DEFAULT_VAL_VAZAOARTIFICIAL))
            .andExpect(jsonPath("$.valVazaoincremental").value(DEFAULT_VAL_VAZAOINCREMENTAL))
            .andExpect(jsonPath("$.valVazaoevaporacaoliquida").value(DEFAULT_VAL_VAZAOEVAPORACAOLIQUIDA))
            .andExpect(jsonPath("$.valVazaousoconsuntivo").value(DEFAULT_VAL_VAZAOUSOCONSUNTIVO));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        // Get the onsDadosHidraulicosReservatorioBaseDiaria
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.save(onsDadosHidraulicosReservatorioBaseDiariaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());

        // Update the onsDadosHidraulicosReservatorioBaseDiaria
        OnsDadosHidraulicosReservatorioBaseDiariaEntity updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity =
            onsDadosHidraulicosReservatorioBaseDiariaRepository
                .findById(onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity are not directly saved in db
        em.detach(updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity);
        updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity
            .valNivelmontante(UPDATED_VAL_NIVELMONTANTE)
            .valNiveljusante(UPDATED_VAL_NIVELJUSANTE)
            .valVolumeutilcon(UPDATED_VAL_VOLUMEUTILCON)
            .valVazaoafluente(UPDATED_VAL_VAZAOAFLUENTE)
            .valVazaoturbinada(UPDATED_VAL_VAZAOTURBINADA)
            .valVazaovertida(UPDATED_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(UPDATED_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaodefluente(UPDATED_VAL_VAZAODEFLUENTE)
            .valVazaotransferida(UPDATED_VAL_VAZAOTRANSFERIDA)
            .valVazaonatural(UPDATED_VAL_VAZAONATURAL)
            .valVazaoartificial(UPDATED_VAL_VAZAOARTIFICIAL)
            .valVazaoincremental(UPDATED_VAL_VAZAOINCREMENTAL)
            .valVazaoevaporacaoliquida(UPDATED_VAL_VAZAOEVAPORACAOLIQUIDA)
            .valVazaousoconsuntivo(UPDATED_VAL_VAZAOUSOCONSUNTIVO);

        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntityToMatchAllProperties(
            updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosHidraulicosReservatorioBaseDiariaEntity> onsDadosHidraulicosReservatorioBaseDiariaSearchList = Streamable.of(
                    onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll()
                ).toList();
                OnsDadosHidraulicosReservatorioBaseDiariaEntity testOnsDadosHidraulicosReservatorioBaseDiariaSearch =
                    onsDadosHidraulicosReservatorioBaseDiariaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAllPropertiesEquals(
                    testOnsDadosHidraulicosReservatorioBaseDiariaSearch,
                    updatedOnsDadosHidraulicosReservatorioBaseDiariaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseDiariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosHidraulicosReservatorioBaseDiariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHidraulicosReservatorioBaseDiaria using partial update
        OnsDadosHidraulicosReservatorioBaseDiariaEntity partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity =
            new OnsDadosHidraulicosReservatorioBaseDiariaEntity();
        partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity.setId(onsDadosHidraulicosReservatorioBaseDiariaEntity.getId());

        partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity
            .valNivelmontante(UPDATED_VAL_NIVELMONTANTE)
            .valNiveljusante(UPDATED_VAL_NIVELJUSANTE)
            .valVolumeutilcon(UPDATED_VAL_VOLUMEUTILCON)
            .valVazaoafluente(UPDATED_VAL_VAZAOAFLUENTE)
            .valVazaooutrasestruturas(UPDATED_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaoartificial(UPDATED_VAL_VAZAOARTIFICIAL)
            .valVazaoincremental(UPDATED_VAL_VAZAOINCREMENTAL)
            .valVazaousoconsuntivo(UPDATED_VAL_VAZAOUSOCONSUNTIVO);

        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity,
                onsDadosHidraulicosReservatorioBaseDiariaEntity
            ),
            getPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntity(onsDadosHidraulicosReservatorioBaseDiariaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosHidraulicosReservatorioBaseDiariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHidraulicosReservatorioBaseDiaria using partial update
        OnsDadosHidraulicosReservatorioBaseDiariaEntity partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity =
            new OnsDadosHidraulicosReservatorioBaseDiariaEntity();
        partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity.setId(onsDadosHidraulicosReservatorioBaseDiariaEntity.getId());

        partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity
            .valNivelmontante(UPDATED_VAL_NIVELMONTANTE)
            .valNiveljusante(UPDATED_VAL_NIVELJUSANTE)
            .valVolumeutilcon(UPDATED_VAL_VOLUMEUTILCON)
            .valVazaoafluente(UPDATED_VAL_VAZAOAFLUENTE)
            .valVazaoturbinada(UPDATED_VAL_VAZAOTURBINADA)
            .valVazaovertida(UPDATED_VAL_VAZAOVERTIDA)
            .valVazaooutrasestruturas(UPDATED_VAL_VAZAOOUTRASESTRUTURAS)
            .valVazaodefluente(UPDATED_VAL_VAZAODEFLUENTE)
            .valVazaotransferida(UPDATED_VAL_VAZAOTRANSFERIDA)
            .valVazaonatural(UPDATED_VAL_VAZAONATURAL)
            .valVazaoartificial(UPDATED_VAL_VAZAOARTIFICIAL)
            .valVazaoincremental(UPDATED_VAL_VAZAOINCREMENTAL)
            .valVazaoevaporacaoliquida(UPDATED_VAL_VAZAOEVAPORACAOLIQUIDA)
            .valVazaousoconsuntivo(UPDATED_VAL_VAZAOUSOCONSUNTIVO);

        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntity(partialUpdatedOnsDadosHidraulicosReservatorioBaseDiariaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseDiariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        onsDadosHidraulicosReservatorioBaseDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHidraulicosReservatorioBaseDiariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHidraulicosReservatorioBaseDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );
        onsDadosHidraulicosReservatorioBaseDiariaRepository.save(onsDadosHidraulicosReservatorioBaseDiariaEntity);
        onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.save(onsDadosHidraulicosReservatorioBaseDiariaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosHidraulicosReservatorioBaseDiaria
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosHidraulicosReservatorioBaseDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.saveAndFlush(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );
        onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.save(onsDadosHidraulicosReservatorioBaseDiariaEntity);

        // Search the onsDadosHidraulicosReservatorioBaseDiaria
        restOnsDadosHidraulicosReservatorioBaseDiariaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosHidraulicosReservatorioBaseDiariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosHidraulicosReservatorioBaseDiariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].valNivelmontante").value(hasItem(DEFAULT_VAL_NIVELMONTANTE)))
            .andExpect(jsonPath("$.[*].valNiveljusante").value(hasItem(DEFAULT_VAL_NIVELJUSANTE)))
            .andExpect(jsonPath("$.[*].valVolumeutilcon").value(hasItem(DEFAULT_VAL_VOLUMEUTILCON)))
            .andExpect(jsonPath("$.[*].valVazaoafluente").value(hasItem(DEFAULT_VAL_VAZAOAFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaoturbinada").value(hasItem(DEFAULT_VAL_VAZAOTURBINADA)))
            .andExpect(jsonPath("$.[*].valVazaovertida").value(hasItem(DEFAULT_VAL_VAZAOVERTIDA)))
            .andExpect(jsonPath("$.[*].valVazaooutrasestruturas").value(hasItem(DEFAULT_VAL_VAZAOOUTRASESTRUTURAS)))
            .andExpect(jsonPath("$.[*].valVazaodefluente").value(hasItem(DEFAULT_VAL_VAZAODEFLUENTE)))
            .andExpect(jsonPath("$.[*].valVazaotransferida").value(hasItem(DEFAULT_VAL_VAZAOTRANSFERIDA)))
            .andExpect(jsonPath("$.[*].valVazaonatural").value(hasItem(DEFAULT_VAL_VAZAONATURAL)))
            .andExpect(jsonPath("$.[*].valVazaoartificial").value(hasItem(DEFAULT_VAL_VAZAOARTIFICIAL)))
            .andExpect(jsonPath("$.[*].valVazaoincremental").value(hasItem(DEFAULT_VAL_VAZAOINCREMENTAL)))
            .andExpect(jsonPath("$.[*].valVazaoevaporacaoliquida").value(hasItem(DEFAULT_VAL_VAZAOEVAPORACAOLIQUIDA)))
            .andExpect(jsonPath("$.[*].valVazaousoconsuntivo").value(hasItem(DEFAULT_VAL_VAZAOUSOCONSUNTIVO)));
    }

    protected long getRepositoryCount() {
        return onsDadosHidraulicosReservatorioBaseDiariaRepository.count();
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

    protected OnsDadosHidraulicosReservatorioBaseDiariaEntity getPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntity(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity onsDadosHidraulicosReservatorioBaseDiaria
    ) {
        return onsDadosHidraulicosReservatorioBaseDiariaRepository
            .findById(onsDadosHidraulicosReservatorioBaseDiaria.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntityToMatchAllProperties(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity expectedOnsDadosHidraulicosReservatorioBaseDiariaEntity
    ) {
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAllPropertiesEquals(
            expectedOnsDadosHidraulicosReservatorioBaseDiariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntity(expectedOnsDadosHidraulicosReservatorioBaseDiariaEntity)
        );
    }

    protected void assertPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntityToMatchUpdatableProperties(
        OnsDadosHidraulicosReservatorioBaseDiariaEntity expectedOnsDadosHidraulicosReservatorioBaseDiariaEntity
    ) {
        assertOnsDadosHidraulicosReservatorioBaseDiariaEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosHidraulicosReservatorioBaseDiariaEntity,
            getPersistedOnsDadosHidraulicosReservatorioBaseDiariaEntity(expectedOnsDadosHidraulicosReservatorioBaseDiariaEntity)
        );
    }
}
