package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntity;
import com.alexandrebfreitas.wue.repository.OnsIntercambiosEntreSubsistemasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIntercambiosEntreSubsistemasSearchRepository;
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
 * Integration tests for the {@link OnsIntercambiosEntreSubsistemasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsIntercambiosEntreSubsistemasResourceIT {

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ID_SUBSISTEMA_ORIGEM = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA_ORIGEM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA_ORIGEM = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA_ORIGEM = "BBBBBBBBBB";

    private static final String DEFAULT_ID_SUBSISTEMA_DESTINO = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA_DESTINO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA_DESTINO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA_DESTINO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_INTERCAMBIOMWMED = 1D;
    private static final Double UPDATED_VAL_INTERCAMBIOMWMED = 2D;

    private static final String ENTITY_API_URL = "/api/ons-intercambios-entre-subsistemas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-intercambios-entre-subsistemas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsIntercambiosEntreSubsistemasRepository onsIntercambiosEntreSubsistemasRepository;

    @Autowired
    private OnsIntercambiosEntreSubsistemasSearchRepository onsIntercambiosEntreSubsistemasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsIntercambiosEntreSubsistemasMockMvc;

    private OnsIntercambiosEntreSubsistemasEntity onsIntercambiosEntreSubsistemasEntity;

    private OnsIntercambiosEntreSubsistemasEntity insertedOnsIntercambiosEntreSubsistemasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIntercambiosEntreSubsistemasEntity createEntity() {
        return new OnsIntercambiosEntreSubsistemasEntity()
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .idSubsistemaOrigem(DEFAULT_ID_SUBSISTEMA_ORIGEM)
            .nomSubsistemaOrigem(DEFAULT_NOM_SUBSISTEMA_ORIGEM)
            .idSubsistemaDestino(DEFAULT_ID_SUBSISTEMA_DESTINO)
            .nomSubsistemaDestino(DEFAULT_NOM_SUBSISTEMA_DESTINO)
            .valIntercambiomwmed(DEFAULT_VAL_INTERCAMBIOMWMED);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsIntercambiosEntreSubsistemasEntity createUpdatedEntity() {
        return new OnsIntercambiosEntreSubsistemasEntity()
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idSubsistemaOrigem(UPDATED_ID_SUBSISTEMA_ORIGEM)
            .nomSubsistemaOrigem(UPDATED_NOM_SUBSISTEMA_ORIGEM)
            .idSubsistemaDestino(UPDATED_ID_SUBSISTEMA_DESTINO)
            .nomSubsistemaDestino(UPDATED_NOM_SUBSISTEMA_DESTINO)
            .valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);
    }

    @BeforeEach
    public void initTest() {
        onsIntercambiosEntreSubsistemasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsIntercambiosEntreSubsistemasEntity != null) {
            onsIntercambiosEntreSubsistemasRepository.delete(insertedOnsIntercambiosEntreSubsistemasEntity);
            onsIntercambiosEntreSubsistemasSearchRepository.delete(insertedOnsIntercambiosEntreSubsistemasEntity);
            insertedOnsIntercambiosEntreSubsistemasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsIntercambiosEntreSubsistemas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        // Create the OnsIntercambiosEntreSubsistemas
        var returnedOnsIntercambiosEntreSubsistemasEntity = om.readValue(
            restOnsIntercambiosEntreSubsistemasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsIntercambiosEntreSubsistemasEntity.class
        );

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsIntercambiosEntreSubsistemasEntityUpdatableFieldsEquals(
            returnedOnsIntercambiosEntreSubsistemasEntity,
            getPersistedOnsIntercambiosEntreSubsistemasEntity(returnedOnsIntercambiosEntreSubsistemasEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsIntercambiosEntreSubsistemasEntity = returnedOnsIntercambiosEntreSubsistemasEntity;
    }

    @Test
    @Transactional
    void createOnsIntercambiosEntreSubsistemasWithExistingId() throws Exception {
        // Create the OnsIntercambiosEntreSubsistemas with an existing ID
        onsIntercambiosEntreSubsistemasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsIntercambiosEntreSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.saveAndFlush(
            onsIntercambiosEntreSubsistemasEntity
        );

        // Get all the onsIntercambiosEntreSubsistemasList
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsIntercambiosEntreSubsistemasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idSubsistemaOrigem").value(hasItem(DEFAULT_ID_SUBSISTEMA_ORIGEM)))
            .andExpect(jsonPath("$.[*].nomSubsistemaOrigem").value(hasItem(DEFAULT_NOM_SUBSISTEMA_ORIGEM)))
            .andExpect(jsonPath("$.[*].idSubsistemaDestino").value(hasItem(DEFAULT_ID_SUBSISTEMA_DESTINO)))
            .andExpect(jsonPath("$.[*].nomSubsistemaDestino").value(hasItem(DEFAULT_NOM_SUBSISTEMA_DESTINO)))
            .andExpect(jsonPath("$.[*].valIntercambiomwmed").value(hasItem(DEFAULT_VAL_INTERCAMBIOMWMED)));
    }

    @Test
    @Transactional
    void getOnsIntercambiosEntreSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.saveAndFlush(
            onsIntercambiosEntreSubsistemasEntity
        );

        // Get the onsIntercambiosEntreSubsistemas
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsIntercambiosEntreSubsistemasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsIntercambiosEntreSubsistemasEntity.getId().intValue()))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.idSubsistemaOrigem").value(DEFAULT_ID_SUBSISTEMA_ORIGEM))
            .andExpect(jsonPath("$.nomSubsistemaOrigem").value(DEFAULT_NOM_SUBSISTEMA_ORIGEM))
            .andExpect(jsonPath("$.idSubsistemaDestino").value(DEFAULT_ID_SUBSISTEMA_DESTINO))
            .andExpect(jsonPath("$.nomSubsistemaDestino").value(DEFAULT_NOM_SUBSISTEMA_DESTINO))
            .andExpect(jsonPath("$.valIntercambiomwmed").value(DEFAULT_VAL_INTERCAMBIOMWMED));
    }

    @Test
    @Transactional
    void getNonExistingOnsIntercambiosEntreSubsistemas() throws Exception {
        // Get the onsIntercambiosEntreSubsistemas
        restOnsIntercambiosEntreSubsistemasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsIntercambiosEntreSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.saveAndFlush(
            onsIntercambiosEntreSubsistemasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsIntercambiosEntreSubsistemasSearchRepository.save(onsIntercambiosEntreSubsistemasEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());

        // Update the onsIntercambiosEntreSubsistemas
        OnsIntercambiosEntreSubsistemasEntity updatedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository
            .findById(onsIntercambiosEntreSubsistemasEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsIntercambiosEntreSubsistemasEntity are not directly saved in db
        em.detach(updatedOnsIntercambiosEntreSubsistemasEntity);
        updatedOnsIntercambiosEntreSubsistemasEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idSubsistemaOrigem(UPDATED_ID_SUBSISTEMA_ORIGEM)
            .nomSubsistemaOrigem(UPDATED_NOM_SUBSISTEMA_ORIGEM)
            .idSubsistemaDestino(UPDATED_ID_SUBSISTEMA_DESTINO)
            .nomSubsistemaDestino(UPDATED_NOM_SUBSISTEMA_DESTINO)
            .valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);

        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsIntercambiosEntreSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsIntercambiosEntreSubsistemasEntityToMatchAllProperties(updatedOnsIntercambiosEntreSubsistemasEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsIntercambiosEntreSubsistemasEntity> onsIntercambiosEntreSubsistemasSearchList = Streamable.of(
                    onsIntercambiosEntreSubsistemasSearchRepository.findAll()
                ).toList();
                OnsIntercambiosEntreSubsistemasEntity testOnsIntercambiosEntreSubsistemasSearch =
                    onsIntercambiosEntreSubsistemasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsIntercambiosEntreSubsistemasEntityAllPropertiesEquals(
                    testOnsIntercambiosEntreSubsistemasSearch,
                    updatedOnsIntercambiosEntreSubsistemasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsIntercambiosEntreSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        onsIntercambiosEntreSubsistemasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsIntercambiosEntreSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsIntercambiosEntreSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        onsIntercambiosEntreSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsIntercambiosEntreSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        onsIntercambiosEntreSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsIntercambiosEntreSubsistemasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.saveAndFlush(
            onsIntercambiosEntreSubsistemasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIntercambiosEntreSubsistemas using partial update
        OnsIntercambiosEntreSubsistemasEntity partialUpdatedOnsIntercambiosEntreSubsistemasEntity =
            new OnsIntercambiosEntreSubsistemasEntity();
        partialUpdatedOnsIntercambiosEntreSubsistemasEntity.setId(onsIntercambiosEntreSubsistemasEntity.getId());

        partialUpdatedOnsIntercambiosEntreSubsistemasEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idSubsistemaOrigem(UPDATED_ID_SUBSISTEMA_ORIGEM)
            .valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);

        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIntercambiosEntreSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIntercambiosEntreSubsistemas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIntercambiosEntreSubsistemasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsIntercambiosEntreSubsistemasEntity, onsIntercambiosEntreSubsistemasEntity),
            getPersistedOnsIntercambiosEntreSubsistemasEntity(onsIntercambiosEntreSubsistemasEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsIntercambiosEntreSubsistemasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.saveAndFlush(
            onsIntercambiosEntreSubsistemasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsIntercambiosEntreSubsistemas using partial update
        OnsIntercambiosEntreSubsistemasEntity partialUpdatedOnsIntercambiosEntreSubsistemasEntity =
            new OnsIntercambiosEntreSubsistemasEntity();
        partialUpdatedOnsIntercambiosEntreSubsistemasEntity.setId(onsIntercambiosEntreSubsistemasEntity.getId());

        partialUpdatedOnsIntercambiosEntreSubsistemasEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idSubsistemaOrigem(UPDATED_ID_SUBSISTEMA_ORIGEM)
            .nomSubsistemaOrigem(UPDATED_NOM_SUBSISTEMA_ORIGEM)
            .idSubsistemaDestino(UPDATED_ID_SUBSISTEMA_DESTINO)
            .nomSubsistemaDestino(UPDATED_NOM_SUBSISTEMA_DESTINO)
            .valIntercambiomwmed(UPDATED_VAL_INTERCAMBIOMWMED);

        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsIntercambiosEntreSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsIntercambiosEntreSubsistemas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsIntercambiosEntreSubsistemasEntityUpdatableFieldsEquals(
            partialUpdatedOnsIntercambiosEntreSubsistemasEntity,
            getPersistedOnsIntercambiosEntreSubsistemasEntity(partialUpdatedOnsIntercambiosEntreSubsistemasEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsIntercambiosEntreSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        onsIntercambiosEntreSubsistemasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsIntercambiosEntreSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsIntercambiosEntreSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        onsIntercambiosEntreSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsIntercambiosEntreSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        onsIntercambiosEntreSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsIntercambiosEntreSubsistemasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsIntercambiosEntreSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsIntercambiosEntreSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.saveAndFlush(
            onsIntercambiosEntreSubsistemasEntity
        );
        onsIntercambiosEntreSubsistemasRepository.save(onsIntercambiosEntreSubsistemasEntity);
        onsIntercambiosEntreSubsistemasSearchRepository.save(onsIntercambiosEntreSubsistemasEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsIntercambiosEntreSubsistemas
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsIntercambiosEntreSubsistemasEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsIntercambiosEntreSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsIntercambiosEntreSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.saveAndFlush(
            onsIntercambiosEntreSubsistemasEntity
        );
        onsIntercambiosEntreSubsistemasSearchRepository.save(onsIntercambiosEntreSubsistemasEntity);

        // Search the onsIntercambiosEntreSubsistemas
        restOnsIntercambiosEntreSubsistemasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsIntercambiosEntreSubsistemasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsIntercambiosEntreSubsistemasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idSubsistemaOrigem").value(hasItem(DEFAULT_ID_SUBSISTEMA_ORIGEM)))
            .andExpect(jsonPath("$.[*].nomSubsistemaOrigem").value(hasItem(DEFAULT_NOM_SUBSISTEMA_ORIGEM)))
            .andExpect(jsonPath("$.[*].idSubsistemaDestino").value(hasItem(DEFAULT_ID_SUBSISTEMA_DESTINO)))
            .andExpect(jsonPath("$.[*].nomSubsistemaDestino").value(hasItem(DEFAULT_NOM_SUBSISTEMA_DESTINO)))
            .andExpect(jsonPath("$.[*].valIntercambiomwmed").value(hasItem(DEFAULT_VAL_INTERCAMBIOMWMED)));
    }

    protected long getRepositoryCount() {
        return onsIntercambiosEntreSubsistemasRepository.count();
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

    protected OnsIntercambiosEntreSubsistemasEntity getPersistedOnsIntercambiosEntreSubsistemasEntity(
        OnsIntercambiosEntreSubsistemasEntity onsIntercambiosEntreSubsistemas
    ) {
        return onsIntercambiosEntreSubsistemasRepository.findById(onsIntercambiosEntreSubsistemas.getId()).orElseThrow();
    }

    protected void assertPersistedOnsIntercambiosEntreSubsistemasEntityToMatchAllProperties(
        OnsIntercambiosEntreSubsistemasEntity expectedOnsIntercambiosEntreSubsistemasEntity
    ) {
        assertOnsIntercambiosEntreSubsistemasEntityAllPropertiesEquals(
            expectedOnsIntercambiosEntreSubsistemasEntity,
            getPersistedOnsIntercambiosEntreSubsistemasEntity(expectedOnsIntercambiosEntreSubsistemasEntity)
        );
    }

    protected void assertPersistedOnsIntercambiosEntreSubsistemasEntityToMatchUpdatableProperties(
        OnsIntercambiosEntreSubsistemasEntity expectedOnsIntercambiosEntreSubsistemasEntity
    ) {
        assertOnsIntercambiosEntreSubsistemasEntityAllUpdatablePropertiesEquals(
            expectedOnsIntercambiosEntreSubsistemasEntity,
            getPersistedOnsIntercambiosEntreSubsistemasEntity(expectedOnsIntercambiosEntreSubsistemasEntity)
        );
    }
}
