package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsCurvaCargaHorariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCurvaCargaHorariaSearchRepository;
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
 * Integration tests for the {@link OnsCurvaCargaHorariaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCurvaCargaHorariaResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_CARGAENERGIAHOMWMED = 1D;
    private static final Double UPDATED_VAL_CARGAENERGIAHOMWMED = 2D;

    private static final String ENTITY_API_URL = "/api/ons-curva-carga-horarias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-curva-carga-horarias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCurvaCargaHorariaRepository onsCurvaCargaHorariaRepository;

    @Autowired
    private OnsCurvaCargaHorariaSearchRepository onsCurvaCargaHorariaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCurvaCargaHorariaMockMvc;

    private OnsCurvaCargaHorariaEntity onsCurvaCargaHorariaEntity;

    private OnsCurvaCargaHorariaEntity insertedOnsCurvaCargaHorariaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCurvaCargaHorariaEntity createEntity() {
        return new OnsCurvaCargaHorariaEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valCargaenergiahomwmed(DEFAULT_VAL_CARGAENERGIAHOMWMED);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCurvaCargaHorariaEntity createUpdatedEntity() {
        return new OnsCurvaCargaHorariaEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiahomwmed(UPDATED_VAL_CARGAENERGIAHOMWMED);
    }

    @BeforeEach
    public void initTest() {
        onsCurvaCargaHorariaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCurvaCargaHorariaEntity != null) {
            onsCurvaCargaHorariaRepository.delete(insertedOnsCurvaCargaHorariaEntity);
            onsCurvaCargaHorariaSearchRepository.delete(insertedOnsCurvaCargaHorariaEntity);
            insertedOnsCurvaCargaHorariaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCurvaCargaHoraria() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        // Create the OnsCurvaCargaHoraria
        var returnedOnsCurvaCargaHorariaEntity = om.readValue(
            restOnsCurvaCargaHorariaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCurvaCargaHorariaEntity.class
        );

        // Validate the OnsCurvaCargaHoraria in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCurvaCargaHorariaEntityUpdatableFieldsEquals(
            returnedOnsCurvaCargaHorariaEntity,
            getPersistedOnsCurvaCargaHorariaEntity(returnedOnsCurvaCargaHorariaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCurvaCargaHorariaEntity = returnedOnsCurvaCargaHorariaEntity;
    }

    @Test
    @Transactional
    void createOnsCurvaCargaHorariaWithExistingId() throws Exception {
        // Create the OnsCurvaCargaHoraria with an existing ID
        onsCurvaCargaHorariaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCurvaCargaHorariaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCurvaCargaHorarias() throws Exception {
        // Initialize the database
        insertedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.saveAndFlush(onsCurvaCargaHorariaEntity);

        // Get all the onsCurvaCargaHorariaList
        restOnsCurvaCargaHorariaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCurvaCargaHorariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCargaenergiahomwmed").value(hasItem(DEFAULT_VAL_CARGAENERGIAHOMWMED)));
    }

    @Test
    @Transactional
    void getOnsCurvaCargaHoraria() throws Exception {
        // Initialize the database
        insertedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.saveAndFlush(onsCurvaCargaHorariaEntity);

        // Get the onsCurvaCargaHoraria
        restOnsCurvaCargaHorariaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCurvaCargaHorariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCurvaCargaHorariaEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valCargaenergiahomwmed").value(DEFAULT_VAL_CARGAENERGIAHOMWMED));
    }

    @Test
    @Transactional
    void getNonExistingOnsCurvaCargaHoraria() throws Exception {
        // Get the onsCurvaCargaHoraria
        restOnsCurvaCargaHorariaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCurvaCargaHoraria() throws Exception {
        // Initialize the database
        insertedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.saveAndFlush(onsCurvaCargaHorariaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCurvaCargaHorariaSearchRepository.save(onsCurvaCargaHorariaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());

        // Update the onsCurvaCargaHoraria
        OnsCurvaCargaHorariaEntity updatedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository
            .findById(onsCurvaCargaHorariaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCurvaCargaHorariaEntity are not directly saved in db
        em.detach(updatedOnsCurvaCargaHorariaEntity);
        updatedOnsCurvaCargaHorariaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiahomwmed(UPDATED_VAL_CARGAENERGIAHOMWMED);

        restOnsCurvaCargaHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCurvaCargaHorariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCurvaCargaHorariaEntityToMatchAllProperties(updatedOnsCurvaCargaHorariaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCurvaCargaHorariaEntity> onsCurvaCargaHorariaSearchList = Streamable.of(
                    onsCurvaCargaHorariaSearchRepository.findAll()
                ).toList();
                OnsCurvaCargaHorariaEntity testOnsCurvaCargaHorariaSearch = onsCurvaCargaHorariaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsCurvaCargaHorariaEntityAllPropertiesEquals(testOnsCurvaCargaHorariaSearch, updatedOnsCurvaCargaHorariaEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCurvaCargaHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        onsCurvaCargaHorariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCurvaCargaHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCurvaCargaHorariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCurvaCargaHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        onsCurvaCargaHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCurvaCargaHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCurvaCargaHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        onsCurvaCargaHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCurvaCargaHorariaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCurvaCargaHorariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.saveAndFlush(onsCurvaCargaHorariaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCurvaCargaHoraria using partial update
        OnsCurvaCargaHorariaEntity partialUpdatedOnsCurvaCargaHorariaEntity = new OnsCurvaCargaHorariaEntity();
        partialUpdatedOnsCurvaCargaHorariaEntity.setId(onsCurvaCargaHorariaEntity.getId());

        partialUpdatedOnsCurvaCargaHorariaEntity.nomSubsistema(UPDATED_NOM_SUBSISTEMA).dinInstante(UPDATED_DIN_INSTANTE);

        restOnsCurvaCargaHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCurvaCargaHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCurvaCargaHoraria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCurvaCargaHorariaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCurvaCargaHorariaEntity, onsCurvaCargaHorariaEntity),
            getPersistedOnsCurvaCargaHorariaEntity(onsCurvaCargaHorariaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCurvaCargaHorariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.saveAndFlush(onsCurvaCargaHorariaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCurvaCargaHoraria using partial update
        OnsCurvaCargaHorariaEntity partialUpdatedOnsCurvaCargaHorariaEntity = new OnsCurvaCargaHorariaEntity();
        partialUpdatedOnsCurvaCargaHorariaEntity.setId(onsCurvaCargaHorariaEntity.getId());

        partialUpdatedOnsCurvaCargaHorariaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCargaenergiahomwmed(UPDATED_VAL_CARGAENERGIAHOMWMED);

        restOnsCurvaCargaHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCurvaCargaHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCurvaCargaHoraria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCurvaCargaHorariaEntityUpdatableFieldsEquals(
            partialUpdatedOnsCurvaCargaHorariaEntity,
            getPersistedOnsCurvaCargaHorariaEntity(partialUpdatedOnsCurvaCargaHorariaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCurvaCargaHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        onsCurvaCargaHorariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCurvaCargaHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCurvaCargaHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCurvaCargaHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        onsCurvaCargaHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCurvaCargaHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCurvaCargaHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        onsCurvaCargaHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCurvaCargaHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCurvaCargaHorariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCurvaCargaHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCurvaCargaHoraria() throws Exception {
        // Initialize the database
        insertedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.saveAndFlush(onsCurvaCargaHorariaEntity);
        onsCurvaCargaHorariaRepository.save(onsCurvaCargaHorariaEntity);
        onsCurvaCargaHorariaSearchRepository.save(onsCurvaCargaHorariaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCurvaCargaHoraria
        restOnsCurvaCargaHorariaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCurvaCargaHorariaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCurvaCargaHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCurvaCargaHoraria() throws Exception {
        // Initialize the database
        insertedOnsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.saveAndFlush(onsCurvaCargaHorariaEntity);
        onsCurvaCargaHorariaSearchRepository.save(onsCurvaCargaHorariaEntity);

        // Search the onsCurvaCargaHoraria
        restOnsCurvaCargaHorariaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCurvaCargaHorariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCurvaCargaHorariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCargaenergiahomwmed").value(hasItem(DEFAULT_VAL_CARGAENERGIAHOMWMED)));
    }

    protected long getRepositoryCount() {
        return onsCurvaCargaHorariaRepository.count();
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

    protected OnsCurvaCargaHorariaEntity getPersistedOnsCurvaCargaHorariaEntity(OnsCurvaCargaHorariaEntity onsCurvaCargaHoraria) {
        return onsCurvaCargaHorariaRepository.findById(onsCurvaCargaHoraria.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCurvaCargaHorariaEntityToMatchAllProperties(
        OnsCurvaCargaHorariaEntity expectedOnsCurvaCargaHorariaEntity
    ) {
        assertOnsCurvaCargaHorariaEntityAllPropertiesEquals(
            expectedOnsCurvaCargaHorariaEntity,
            getPersistedOnsCurvaCargaHorariaEntity(expectedOnsCurvaCargaHorariaEntity)
        );
    }

    protected void assertPersistedOnsCurvaCargaHorariaEntityToMatchUpdatableProperties(
        OnsCurvaCargaHorariaEntity expectedOnsCurvaCargaHorariaEntity
    ) {
        assertOnsCurvaCargaHorariaEntityAllUpdatablePropertiesEquals(
            expectedOnsCurvaCargaHorariaEntity,
            getPersistedOnsCurvaCargaHorariaEntity(expectedOnsCurvaCargaHorariaEntity)
        );
    }
}
