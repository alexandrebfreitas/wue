package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaNosSubsistemasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaNosSubsistemasEntity;
import com.alexandrebfreitas.wue.repository.OnsBalancoEnergiaNosSubsistemasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsBalancoEnergiaNosSubsistemasSearchRepository;
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
 * Integration tests for the {@link OnsBalancoEnergiaNosSubsistemasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsBalancoEnergiaNosSubsistemasResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_GERHIDRAULICA = 1D;
    private static final Double UPDATED_VAL_GERHIDRAULICA = 2D;

    private static final Double DEFAULT_VAL_GERTERMICA = 1D;
    private static final Double UPDATED_VAL_GERTERMICA = 2D;

    private static final Double DEFAULT_VAL_GEREOLICA = 1D;
    private static final Double UPDATED_VAL_GEREOLICA = 2D;

    private static final Double DEFAULT_VAL_GERSOLAR = 1D;
    private static final Double UPDATED_VAL_GERSOLAR = 2D;

    private static final Double DEFAULT_VAL_CARGA = 1D;
    private static final Double UPDATED_VAL_CARGA = 2D;

    private static final Double DEFAULT_VAL_INTERCAMBIO = 1D;
    private static final Double UPDATED_VAL_INTERCAMBIO = 2D;

    private static final String ENTITY_API_URL = "/api/ons-balanco-energia-nos-subsistemas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-balanco-energia-nos-subsistemas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsBalancoEnergiaNosSubsistemasRepository onsBalancoEnergiaNosSubsistemasRepository;

    @Autowired
    private OnsBalancoEnergiaNosSubsistemasSearchRepository onsBalancoEnergiaNosSubsistemasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsBalancoEnergiaNosSubsistemasMockMvc;

    private OnsBalancoEnergiaNosSubsistemasEntity onsBalancoEnergiaNosSubsistemasEntity;

    private OnsBalancoEnergiaNosSubsistemasEntity insertedOnsBalancoEnergiaNosSubsistemasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsBalancoEnergiaNosSubsistemasEntity createEntity() {
        return new OnsBalancoEnergiaNosSubsistemasEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valGerhidraulica(DEFAULT_VAL_GERHIDRAULICA)
            .valGertermica(DEFAULT_VAL_GERTERMICA)
            .valGereolica(DEFAULT_VAL_GEREOLICA)
            .valGersolar(DEFAULT_VAL_GERSOLAR)
            .valCarga(DEFAULT_VAL_CARGA)
            .valIntercambio(DEFAULT_VAL_INTERCAMBIO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsBalancoEnergiaNosSubsistemasEntity createUpdatedEntity() {
        return new OnsBalancoEnergiaNosSubsistemasEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGerhidraulica(UPDATED_VAL_GERHIDRAULICA)
            .valGertermica(UPDATED_VAL_GERTERMICA)
            .valGereolica(UPDATED_VAL_GEREOLICA)
            .valGersolar(UPDATED_VAL_GERSOLAR)
            .valCarga(UPDATED_VAL_CARGA)
            .valIntercambio(UPDATED_VAL_INTERCAMBIO);
    }

    @BeforeEach
    public void initTest() {
        onsBalancoEnergiaNosSubsistemasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsBalancoEnergiaNosSubsistemasEntity != null) {
            onsBalancoEnergiaNosSubsistemasRepository.delete(insertedOnsBalancoEnergiaNosSubsistemasEntity);
            onsBalancoEnergiaNosSubsistemasSearchRepository.delete(insertedOnsBalancoEnergiaNosSubsistemasEntity);
            insertedOnsBalancoEnergiaNosSubsistemasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsBalancoEnergiaNosSubsistemas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        // Create the OnsBalancoEnergiaNosSubsistemas
        var returnedOnsBalancoEnergiaNosSubsistemasEntity = om.readValue(
            restOnsBalancoEnergiaNosSubsistemasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsBalancoEnergiaNosSubsistemasEntity.class
        );

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsBalancoEnergiaNosSubsistemasEntityUpdatableFieldsEquals(
            returnedOnsBalancoEnergiaNosSubsistemasEntity,
            getPersistedOnsBalancoEnergiaNosSubsistemasEntity(returnedOnsBalancoEnergiaNosSubsistemasEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsBalancoEnergiaNosSubsistemasEntity = returnedOnsBalancoEnergiaNosSubsistemasEntity;
    }

    @Test
    @Transactional
    void createOnsBalancoEnergiaNosSubsistemasWithExistingId() throws Exception {
        // Create the OnsBalancoEnergiaNosSubsistemas with an existing ID
        onsBalancoEnergiaNosSubsistemasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsBalancoEnergiaNosSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.saveAndFlush(
            onsBalancoEnergiaNosSubsistemasEntity
        );

        // Get all the onsBalancoEnergiaNosSubsistemasList
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsBalancoEnergiaNosSubsistemasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valGerhidraulica").value(hasItem(DEFAULT_VAL_GERHIDRAULICA)))
            .andExpect(jsonPath("$.[*].valGertermica").value(hasItem(DEFAULT_VAL_GERTERMICA)))
            .andExpect(jsonPath("$.[*].valGereolica").value(hasItem(DEFAULT_VAL_GEREOLICA)))
            .andExpect(jsonPath("$.[*].valGersolar").value(hasItem(DEFAULT_VAL_GERSOLAR)))
            .andExpect(jsonPath("$.[*].valCarga").value(hasItem(DEFAULT_VAL_CARGA)))
            .andExpect(jsonPath("$.[*].valIntercambio").value(hasItem(DEFAULT_VAL_INTERCAMBIO)));
    }

    @Test
    @Transactional
    void getOnsBalancoEnergiaNosSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.saveAndFlush(
            onsBalancoEnergiaNosSubsistemasEntity
        );

        // Get the onsBalancoEnergiaNosSubsistemas
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsBalancoEnergiaNosSubsistemasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsBalancoEnergiaNosSubsistemasEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valGerhidraulica").value(DEFAULT_VAL_GERHIDRAULICA))
            .andExpect(jsonPath("$.valGertermica").value(DEFAULT_VAL_GERTERMICA))
            .andExpect(jsonPath("$.valGereolica").value(DEFAULT_VAL_GEREOLICA))
            .andExpect(jsonPath("$.valGersolar").value(DEFAULT_VAL_GERSOLAR))
            .andExpect(jsonPath("$.valCarga").value(DEFAULT_VAL_CARGA))
            .andExpect(jsonPath("$.valIntercambio").value(DEFAULT_VAL_INTERCAMBIO));
    }

    @Test
    @Transactional
    void getNonExistingOnsBalancoEnergiaNosSubsistemas() throws Exception {
        // Get the onsBalancoEnergiaNosSubsistemas
        restOnsBalancoEnergiaNosSubsistemasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsBalancoEnergiaNosSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.saveAndFlush(
            onsBalancoEnergiaNosSubsistemasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsBalancoEnergiaNosSubsistemasSearchRepository.save(onsBalancoEnergiaNosSubsistemasEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());

        // Update the onsBalancoEnergiaNosSubsistemas
        OnsBalancoEnergiaNosSubsistemasEntity updatedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository
            .findById(onsBalancoEnergiaNosSubsistemasEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsBalancoEnergiaNosSubsistemasEntity are not directly saved in db
        em.detach(updatedOnsBalancoEnergiaNosSubsistemasEntity);
        updatedOnsBalancoEnergiaNosSubsistemasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGerhidraulica(UPDATED_VAL_GERHIDRAULICA)
            .valGertermica(UPDATED_VAL_GERTERMICA)
            .valGereolica(UPDATED_VAL_GEREOLICA)
            .valGersolar(UPDATED_VAL_GERSOLAR)
            .valCarga(UPDATED_VAL_CARGA)
            .valIntercambio(UPDATED_VAL_INTERCAMBIO);

        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsBalancoEnergiaNosSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsBalancoEnergiaNosSubsistemasEntityToMatchAllProperties(updatedOnsBalancoEnergiaNosSubsistemasEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsBalancoEnergiaNosSubsistemasEntity> onsBalancoEnergiaNosSubsistemasSearchList = Streamable.of(
                    onsBalancoEnergiaNosSubsistemasSearchRepository.findAll()
                ).toList();
                OnsBalancoEnergiaNosSubsistemasEntity testOnsBalancoEnergiaNosSubsistemasSearch =
                    onsBalancoEnergiaNosSubsistemasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsBalancoEnergiaNosSubsistemasEntityAllPropertiesEquals(
                    testOnsBalancoEnergiaNosSubsistemasSearch,
                    updatedOnsBalancoEnergiaNosSubsistemasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsBalancoEnergiaNosSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        onsBalancoEnergiaNosSubsistemasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsBalancoEnergiaNosSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsBalancoEnergiaNosSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        onsBalancoEnergiaNosSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsBalancoEnergiaNosSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        onsBalancoEnergiaNosSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsBalancoEnergiaNosSubsistemasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.saveAndFlush(
            onsBalancoEnergiaNosSubsistemasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsBalancoEnergiaNosSubsistemas using partial update
        OnsBalancoEnergiaNosSubsistemasEntity partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity =
            new OnsBalancoEnergiaNosSubsistemasEntity();
        partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity.setId(onsBalancoEnergiaNosSubsistemasEntity.getId());

        partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGertermica(UPDATED_VAL_GERTERMICA)
            .valGereolica(UPDATED_VAL_GEREOLICA)
            .valGersolar(UPDATED_VAL_GERSOLAR);

        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsBalancoEnergiaNosSubsistemasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity, onsBalancoEnergiaNosSubsistemasEntity),
            getPersistedOnsBalancoEnergiaNosSubsistemasEntity(onsBalancoEnergiaNosSubsistemasEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsBalancoEnergiaNosSubsistemasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.saveAndFlush(
            onsBalancoEnergiaNosSubsistemasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsBalancoEnergiaNosSubsistemas using partial update
        OnsBalancoEnergiaNosSubsistemasEntity partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity =
            new OnsBalancoEnergiaNosSubsistemasEntity();
        partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity.setId(onsBalancoEnergiaNosSubsistemasEntity.getId());

        partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valGerhidraulica(UPDATED_VAL_GERHIDRAULICA)
            .valGertermica(UPDATED_VAL_GERTERMICA)
            .valGereolica(UPDATED_VAL_GEREOLICA)
            .valGersolar(UPDATED_VAL_GERSOLAR)
            .valCarga(UPDATED_VAL_CARGA)
            .valIntercambio(UPDATED_VAL_INTERCAMBIO);

        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsBalancoEnergiaNosSubsistemasEntityUpdatableFieldsEquals(
            partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity,
            getPersistedOnsBalancoEnergiaNosSubsistemasEntity(partialUpdatedOnsBalancoEnergiaNosSubsistemasEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsBalancoEnergiaNosSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        onsBalancoEnergiaNosSubsistemasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsBalancoEnergiaNosSubsistemasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsBalancoEnergiaNosSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        onsBalancoEnergiaNosSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsBalancoEnergiaNosSubsistemas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        onsBalancoEnergiaNosSubsistemasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsBalancoEnergiaNosSubsistemasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsBalancoEnergiaNosSubsistemas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsBalancoEnergiaNosSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.saveAndFlush(
            onsBalancoEnergiaNosSubsistemasEntity
        );
        onsBalancoEnergiaNosSubsistemasRepository.save(onsBalancoEnergiaNosSubsistemasEntity);
        onsBalancoEnergiaNosSubsistemasSearchRepository.save(onsBalancoEnergiaNosSubsistemasEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsBalancoEnergiaNosSubsistemas
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsBalancoEnergiaNosSubsistemasEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaNosSubsistemasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsBalancoEnergiaNosSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.saveAndFlush(
            onsBalancoEnergiaNosSubsistemasEntity
        );
        onsBalancoEnergiaNosSubsistemasSearchRepository.save(onsBalancoEnergiaNosSubsistemasEntity);

        // Search the onsBalancoEnergiaNosSubsistemas
        restOnsBalancoEnergiaNosSubsistemasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsBalancoEnergiaNosSubsistemasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsBalancoEnergiaNosSubsistemasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valGerhidraulica").value(hasItem(DEFAULT_VAL_GERHIDRAULICA)))
            .andExpect(jsonPath("$.[*].valGertermica").value(hasItem(DEFAULT_VAL_GERTERMICA)))
            .andExpect(jsonPath("$.[*].valGereolica").value(hasItem(DEFAULT_VAL_GEREOLICA)))
            .andExpect(jsonPath("$.[*].valGersolar").value(hasItem(DEFAULT_VAL_GERSOLAR)))
            .andExpect(jsonPath("$.[*].valCarga").value(hasItem(DEFAULT_VAL_CARGA)))
            .andExpect(jsonPath("$.[*].valIntercambio").value(hasItem(DEFAULT_VAL_INTERCAMBIO)));
    }

    protected long getRepositoryCount() {
        return onsBalancoEnergiaNosSubsistemasRepository.count();
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

    protected OnsBalancoEnergiaNosSubsistemasEntity getPersistedOnsBalancoEnergiaNosSubsistemasEntity(
        OnsBalancoEnergiaNosSubsistemasEntity onsBalancoEnergiaNosSubsistemas
    ) {
        return onsBalancoEnergiaNosSubsistemasRepository.findById(onsBalancoEnergiaNosSubsistemas.getId()).orElseThrow();
    }

    protected void assertPersistedOnsBalancoEnergiaNosSubsistemasEntityToMatchAllProperties(
        OnsBalancoEnergiaNosSubsistemasEntity expectedOnsBalancoEnergiaNosSubsistemasEntity
    ) {
        assertOnsBalancoEnergiaNosSubsistemasEntityAllPropertiesEquals(
            expectedOnsBalancoEnergiaNosSubsistemasEntity,
            getPersistedOnsBalancoEnergiaNosSubsistemasEntity(expectedOnsBalancoEnergiaNosSubsistemasEntity)
        );
    }

    protected void assertPersistedOnsBalancoEnergiaNosSubsistemasEntityToMatchUpdatableProperties(
        OnsBalancoEnergiaNosSubsistemasEntity expectedOnsBalancoEnergiaNosSubsistemasEntity
    ) {
        assertOnsBalancoEnergiaNosSubsistemasEntityAllUpdatablePropertiesEquals(
            expectedOnsBalancoEnergiaNosSubsistemasEntity,
            getPersistedOnsBalancoEnergiaNosSubsistemasEntity(expectedOnsBalancoEnergiaNosSubsistemasEntity)
        );
    }
}
