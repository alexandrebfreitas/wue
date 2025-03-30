package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntity;
import com.alexandrebfreitas.wue.repository.OnsIntercambioSinComOutrosPaisesRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIntercambioSinComOutrosPaisesSearchRepository;
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
 * Integration tests for the {@link OnsIntercambioSinComOutrosPaisesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIntercambioSinComOutrosPaisesResourceIT {

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOM_PAISDESTINO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PAISDESTINO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_INTERCAMBIOMWMED = 1D;
    private static final Double UPDATED_VAL_INTERCAMBIOMWMED = 2D;

    private static final String ENTITY_API_URL = "/api/ons-intercambio-sin-com-outros-paises";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-intercambio-sin-com-outros-paises/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIntercambioSinComOutrosPaisesRepository onsIntercambioSinComOutrosPaisesRepository;

    @Autowired
    private OnsIntercambioSinComOutrosPaisesSearchRepository onsIntercambioSinComOutrosPaisesSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIntercambioSinComOutrosPaisesMockMvc;

    private OnsIntercambioSinComOutrosPaisesEntity onsIntercambioSinComOutrosPaisesEntity;

    private OnsIntercambioSinComOutrosPaisesEntity insertedOnsIntercambioSinComOutrosPaisesEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIntercambioSinComOutrosPaisesEntity createEntity() {
        return new OnsIntercambioSinComOutrosPaisesEntity()
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .nomPaisdestino(DEFAULT_NOM_PAISDESTINO)
            .valIntercambiomwmed(DEFAULT_VAL_INTERCAMBIOMWMED);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIntercambioSinComOutrosPaisesEntity createUpdatedEntity() {
        return new OnsIntercambioSinComOutrosPaisesEntity()
            .dinInstante(UPDATED_DIN_INSTANTE)
            .nomPaisdestino(UPDATED_NOM_PAISDESTINO)
            .valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);
    }

    @BeforeEach
    public void initTest() {
        onsIntercambioSinComOutrosPaisesEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIntercambioSinComOutrosPaisesEntity != null) {
            onsIntercambioSinComOutrosPaisesRepository.delete(insertedOnsIntercambioSinComOutrosPaisesEntity);
            onsIntercambioSinComOutrosPaisesSearchRepository.delete(insertedOnsIntercambioSinComOutrosPaisesEntity);
            insertedOnsIntercambioSinComOutrosPaisesEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIntercambioSinComOutrosPaises() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        // Create the OnsIntercambioSinComOutrosPaises
        var returnedOnsIntercambioSinComOutrosPaisesEntity = om.readValue(
            restOnsIntercambioSinComOutrosPaisesMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIntercambioSinComOutrosPaisesEntity.class
        );

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIntercambioSinComOutrosPaisesEntityUpdatableFieldsEquals(
            returnedOnsIntercambioSinComOutrosPaisesEntity,
            getPersistedOnsIntercambioSinComOutrosPaisesEntity(returnedOnsIntercambioSinComOutrosPaisesEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIntercambioSinComOutrosPaisesEntity = returnedOnsIntercambioSinComOutrosPaisesEntity;
    }

    @Test
    @Transactional
    void createOnsIntercambioSinComOutrosPaisesWithExistingId() throws Exception {
        // Create the OnsIntercambioSinComOutrosPaises with an existing ID
        onsIntercambioSinComOutrosPaisesEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIntercambioSinComOutrosPaises() throws Exception {
        // Initialize the database
        insertedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.saveAndFlush(
            onsIntercambioSinComOutrosPaisesEntity
        );

        // Get all the onsIntercambioSinComOutrosPaisesList
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsIntercambioSinComOutrosPaisesEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].nomPaisdestino").value(hasItem(DEFAULT_NOM_PAISDESTINO)))
            .andExpect(jsonPath("$.[*].valIntercambiomwmed").value(hasItem(DEFAULT_VAL_INTERCAMBIOMWMED)));
    }

    @Test
    @Transactional
    void getOnsIntercambioSinComOutrosPaises() throws Exception {
        // Initialize the database
        insertedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.saveAndFlush(
            onsIntercambioSinComOutrosPaisesEntity
        );

        // Get the onsIntercambioSinComOutrosPaises
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIntercambioSinComOutrosPaisesEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsIntercambioSinComOutrosPaisesEntity.getId().intValue()))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.nomPaisdestino").value(DEFAULT_NOM_PAISDESTINO))
            .andExpect(jsonPath("$.valIntercambiomwmed").value(DEFAULT_VAL_INTERCAMBIOMWMED));
    }

    @Test
    @Transactional
    void getNonExistingOnsIntercambioSinComOutrosPaises() throws Exception {
        // Get the onsIntercambioSinComOutrosPaises
        restOnsIntercambioSinComOutrosPaisesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIntercambioSinComOutrosPaises() throws Exception {
        // Initialize the database
        insertedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.saveAndFlush(
            onsIntercambioSinComOutrosPaisesEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIntercambioSinComOutrosPaisesSearchRepository.save(onsIntercambioSinComOutrosPaisesEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());

        // Update the onsIntercambioSinComOutrosPaises
        OnsIntercambioSinComOutrosPaisesEntity updatedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository
            .findById(onsIntercambioSinComOutrosPaisesEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIntercambioSinComOutrosPaisesEntity are not directly saved in db
        em.detach(updatedOnsIntercambioSinComOutrosPaisesEntity);
        updatedOnsIntercambioSinComOutrosPaisesEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .nomPaisdestino(UPDATED_NOM_PAISDESTINO)
            .valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);

        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIntercambioSinComOutrosPaisesEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIntercambioSinComOutrosPaisesEntityToMatchAllProperties(updatedOnsIntercambioSinComOutrosPaisesEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsIntercambioSinComOutrosPaisesEntity> onsIntercambioSinComOutrosPaisesSearchList = Streamable.of(
                    onsIntercambioSinComOutrosPaisesSearchRepository.findAll()
                ).toList();
                OnsIntercambioSinComOutrosPaisesEntity testOnsIntercambioSinComOutrosPaisesSearch =
                    onsIntercambioSinComOutrosPaisesSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIntercambioSinComOutrosPaisesEntityAllPropertiesEquals(
                    testOnsIntercambioSinComOutrosPaisesSearch,
                    updatedOnsIntercambioSinComOutrosPaisesEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIntercambioSinComOutrosPaises() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        onsIntercambioSinComOutrosPaisesEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIntercambioSinComOutrosPaisesEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIntercambioSinComOutrosPaises() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        onsIntercambioSinComOutrosPaisesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIntercambioSinComOutrosPaises() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        onsIntercambioSinComOutrosPaisesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIntercambioSinComOutrosPaisesWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.saveAndFlush(
            onsIntercambioSinComOutrosPaisesEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIntercambioSinComOutrosPaises using partial update
        OnsIntercambioSinComOutrosPaisesEntity partialUpdatedOnsIntercambioSinComOutrosPaisesEntity =
            new OnsIntercambioSinComOutrosPaisesEntity();
        partialUpdatedOnsIntercambioSinComOutrosPaisesEntity.setId(onsIntercambioSinComOutrosPaisesEntity.getId());

        partialUpdatedOnsIntercambioSinComOutrosPaisesEntity.valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);

        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIntercambioSinComOutrosPaisesEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIntercambioSinComOutrosPaises in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIntercambioSinComOutrosPaisesEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsIntercambioSinComOutrosPaisesEntity, onsIntercambioSinComOutrosPaisesEntity),
            getPersistedOnsIntercambioSinComOutrosPaisesEntity(onsIntercambioSinComOutrosPaisesEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIntercambioSinComOutrosPaisesWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.saveAndFlush(
            onsIntercambioSinComOutrosPaisesEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIntercambioSinComOutrosPaises using partial update
        OnsIntercambioSinComOutrosPaisesEntity partialUpdatedOnsIntercambioSinComOutrosPaisesEntity =
            new OnsIntercambioSinComOutrosPaisesEntity();
        partialUpdatedOnsIntercambioSinComOutrosPaisesEntity.setId(onsIntercambioSinComOutrosPaisesEntity.getId());

        partialUpdatedOnsIntercambioSinComOutrosPaisesEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .nomPaisdestino(UPDATED_NOM_PAISDESTINO)
            .valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);

        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIntercambioSinComOutrosPaisesEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIntercambioSinComOutrosPaises in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIntercambioSinComOutrosPaisesEntityUpdatableFieldsEquals(
            partialUpdatedOnsIntercambioSinComOutrosPaisesEntity,
            getPersistedOnsIntercambioSinComOutrosPaisesEntity(partialUpdatedOnsIntercambioSinComOutrosPaisesEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIntercambioSinComOutrosPaises() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        onsIntercambioSinComOutrosPaisesEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIntercambioSinComOutrosPaisesEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIntercambioSinComOutrosPaises() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        onsIntercambioSinComOutrosPaisesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIntercambioSinComOutrosPaises() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        onsIntercambioSinComOutrosPaisesEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIntercambioSinComOutrosPaisesEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIntercambioSinComOutrosPaises in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIntercambioSinComOutrosPaises() throws Exception {
        // Initialize the database
        insertedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.saveAndFlush(
            onsIntercambioSinComOutrosPaisesEntity
        );
        onsIntercambioSinComOutrosPaisesRepository.save(onsIntercambioSinComOutrosPaisesEntity);
        onsIntercambioSinComOutrosPaisesSearchRepository.save(onsIntercambioSinComOutrosPaisesEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIntercambioSinComOutrosPaises
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIntercambioSinComOutrosPaisesEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambioSinComOutrosPaisesSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIntercambioSinComOutrosPaises() throws Exception {
        // Initialize the database
        insertedOnsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.saveAndFlush(
            onsIntercambioSinComOutrosPaisesEntity
        );
        onsIntercambioSinComOutrosPaisesSearchRepository.save(onsIntercambioSinComOutrosPaisesEntity);

        // Search the onsIntercambioSinComOutrosPaises
        restOnsIntercambioSinComOutrosPaisesMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsIntercambioSinComOutrosPaisesEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsIntercambioSinComOutrosPaisesEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].nomPaisdestino").value(hasItem(DEFAULT_NOM_PAISDESTINO)))
            .andExpect(jsonPath("$.[*].valIntercambiomwmed").value(hasItem(DEFAULT_VAL_INTERCAMBIOMWMED)));
    }

    protected long getRepositoryCount() {
        return onsIntercambioSinComOutrosPaisesRepository.count();
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

    protected OnsIntercambioSinComOutrosPaisesEntity getPersistedOnsIntercambioSinComOutrosPaisesEntity(
        OnsIntercambioSinComOutrosPaisesEntity onsIntercambioSinComOutrosPaises
    ) {
        return onsIntercambioSinComOutrosPaisesRepository.findById(onsIntercambioSinComOutrosPaises.getId()).orElseThrow();
    }

    protected void assertPersistedOnsIntercambioSinComOutrosPaisesEntityToMatchAllProperties(
        OnsIntercambioSinComOutrosPaisesEntity expectedOnsIntercambioSinComOutrosPaisesEntity
    ) {
        assertOnsIntercambioSinComOutrosPaisesEntityAllPropertiesEquals(
            expectedOnsIntercambioSinComOutrosPaisesEntity,
            getPersistedOnsIntercambioSinComOutrosPaisesEntity(expectedOnsIntercambioSinComOutrosPaisesEntity)
        );
    }

    protected void assertPersistedOnsIntercambioSinComOutrosPaisesEntityToMatchUpdatableProperties(
        OnsIntercambioSinComOutrosPaisesEntity expectedOnsIntercambioSinComOutrosPaisesEntity
    ) {
        assertOnsIntercambioSinComOutrosPaisesEntityAllUpdatablePropertiesEquals(
            expectedOnsIntercambioSinComOutrosPaisesEntity,
            getPersistedOnsIntercambioSinComOutrosPaisesEntity(expectedOnsIntercambioSinComOutrosPaisesEntity)
        );
    }
}
