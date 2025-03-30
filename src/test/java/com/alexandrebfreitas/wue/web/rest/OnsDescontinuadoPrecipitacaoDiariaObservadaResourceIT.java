package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDescontinuadoPrecipitacaoDiariaObservadaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDescontinuadoPrecipitacaoDiariaObservadaEntity;
import com.alexandrebfreitas.wue.repository.OnsDescontinuadoPrecipitacaoDiariaObservadaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository;
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
 * Integration tests for the {@link OnsDescontinuadoPrecipitacaoDiariaObservadaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDescontinuadoPrecipitacaoDiariaObservadaResourceIT {

    private static final String DEFAULT_COD_ESTACAO = "AAAAAAAAAA";
    private static final String UPDATED_COD_ESTACAO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_LATITUDE = 1D;
    private static final Double UPDATED_VAL_LATITUDE = 2D;

    private static final Double DEFAULT_VAL_LONGITUDE = 1D;
    private static final Double UPDATED_VAL_LONGITUDE = 2D;

    private static final Double DEFAULT_VAL_MEDIDA = 1D;
    private static final Double UPDATED_VAL_MEDIDA = 2D;

    private static final LocalDate DEFAULT_DAT_OBSERVADA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_OBSERVADA = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/ons-descontinuado-precipitacao-diaria-observadas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-descontinuado-precipitacao-diaria-observadas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDescontinuadoPrecipitacaoDiariaObservadaRepository onsDescontinuadoPrecipitacaoDiariaObservadaRepository;

    @Autowired
    private OnsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc;

    private OnsDescontinuadoPrecipitacaoDiariaObservadaEntity onsDescontinuadoPrecipitacaoDiariaObservadaEntity;

    private OnsDescontinuadoPrecipitacaoDiariaObservadaEntity insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDescontinuadoPrecipitacaoDiariaObservadaEntity createEntity() {
        return new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity()
            .codEstacao(DEFAULT_COD_ESTACAO)
            .valLatitude(DEFAULT_VAL_LATITUDE)
            .valLongitude(DEFAULT_VAL_LONGITUDE)
            .valMedida(DEFAULT_VAL_MEDIDA)
            .datObservada(DEFAULT_DAT_OBSERVADA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDescontinuadoPrecipitacaoDiariaObservadaEntity createUpdatedEntity() {
        return new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity()
            .codEstacao(UPDATED_COD_ESTACAO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .valMedida(UPDATED_VAL_MEDIDA)
            .datObservada(UPDATED_DAT_OBSERVADA);
    }

    @BeforeEach
    public void initTest() {
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity != null) {
            onsDescontinuadoPrecipitacaoDiariaObservadaRepository.delete(insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity);
            onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.delete(insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity);
            insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        // Create the OnsDescontinuadoPrecipitacaoDiariaObservada
        var returnedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = om.readValue(
            restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDescontinuadoPrecipitacaoDiariaObservadaEntity.class
        );

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDescontinuadoPrecipitacaoDiariaObservadaEntityUpdatableFieldsEquals(
            returnedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity,
            getPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity(returnedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = returnedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity;
    }

    @Test
    @Transactional
    void createOnsDescontinuadoPrecipitacaoDiariaObservadaWithExistingId() throws Exception {
        // Create the OnsDescontinuadoPrecipitacaoDiariaObservada with an existing ID
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDescontinuadoPrecipitacaoDiariaObservadas() throws Exception {
        // Initialize the database
        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.saveAndFlush(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );

        // Get all the onsDescontinuadoPrecipitacaoDiariaObservadaList
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].codEstacao").value(hasItem(DEFAULT_COD_ESTACAO)))
            .andExpect(jsonPath("$.[*].valLatitude").value(hasItem(DEFAULT_VAL_LATITUDE)))
            .andExpect(jsonPath("$.[*].valLongitude").value(hasItem(DEFAULT_VAL_LONGITUDE)))
            .andExpect(jsonPath("$.[*].valMedida").value(hasItem(DEFAULT_VAL_MEDIDA)))
            .andExpect(jsonPath("$.[*].datObservada").value(hasItem(DEFAULT_DAT_OBSERVADA.toString())));
    }

    @Test
    @Transactional
    void getOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        // Initialize the database
        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.saveAndFlush(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );

        // Get the onsDescontinuadoPrecipitacaoDiariaObservada
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId().intValue()))
            .andExpect(jsonPath("$.codEstacao").value(DEFAULT_COD_ESTACAO))
            .andExpect(jsonPath("$.valLatitude").value(DEFAULT_VAL_LATITUDE))
            .andExpect(jsonPath("$.valLongitude").value(DEFAULT_VAL_LONGITUDE))
            .andExpect(jsonPath("$.valMedida").value(DEFAULT_VAL_MEDIDA))
            .andExpect(jsonPath("$.datObservada").value(DEFAULT_DAT_OBSERVADA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        // Get the onsDescontinuadoPrecipitacaoDiariaObservada
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        // Initialize the database
        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.saveAndFlush(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.save(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());

        // Update the onsDescontinuadoPrecipitacaoDiariaObservada
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity =
            onsDescontinuadoPrecipitacaoDiariaObservadaRepository
                .findById(onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity are not directly saved in db
        em.detach(updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity);
        updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity
            .codEstacao(UPDATED_COD_ESTACAO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .valMedida(UPDATED_VAL_MEDIDA)
            .datObservada(UPDATED_DAT_OBSERVADA);

        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntityToMatchAllProperties(
            updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity> onsDescontinuadoPrecipitacaoDiariaObservadaSearchList =
                    Streamable.of(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll()).toList();
                OnsDescontinuadoPrecipitacaoDiariaObservadaEntity testOnsDescontinuadoPrecipitacaoDiariaObservadaSearch =
                    onsDescontinuadoPrecipitacaoDiariaObservadaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDescontinuadoPrecipitacaoDiariaObservadaEntityAllPropertiesEquals(
                    testOnsDescontinuadoPrecipitacaoDiariaObservadaSearch,
                    updatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDescontinuadoPrecipitacaoDiariaObservadaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.saveAndFlush(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDescontinuadoPrecipitacaoDiariaObservada using partial update
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity =
            new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity();
        partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId());

        partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity
            .codEstacao(UPDATED_COD_ESTACAO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valMedida(UPDATED_VAL_MEDIDA)
            .datObservada(UPDATED_DAT_OBSERVADA);

        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDescontinuadoPrecipitacaoDiariaObservadaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity,
                onsDescontinuadoPrecipitacaoDiariaObservadaEntity
            ),
            getPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity(onsDescontinuadoPrecipitacaoDiariaObservadaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDescontinuadoPrecipitacaoDiariaObservadaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.saveAndFlush(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDescontinuadoPrecipitacaoDiariaObservada using partial update
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity =
            new OnsDescontinuadoPrecipitacaoDiariaObservadaEntity();
        partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId());

        partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity
            .codEstacao(UPDATED_COD_ESTACAO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .valMedida(UPDATED_VAL_MEDIDA)
            .datObservada(UPDATED_DAT_OBSERVADA);

        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDescontinuadoPrecipitacaoDiariaObservadaEntityUpdatableFieldsEquals(
            partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity,
            getPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity(partialUpdatedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        onsDescontinuadoPrecipitacaoDiariaObservadaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDescontinuadoPrecipitacaoDiariaObservadaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDescontinuadoPrecipitacaoDiariaObservada in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        // Initialize the database
        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.saveAndFlush(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );
        onsDescontinuadoPrecipitacaoDiariaObservadaRepository.save(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);
        onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.save(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDescontinuadoPrecipitacaoDiariaObservada
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDescontinuadoPrecipitacaoDiariaObservada() throws Exception {
        // Initialize the database
        insertedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity = onsDescontinuadoPrecipitacaoDiariaObservadaRepository.saveAndFlush(
            onsDescontinuadoPrecipitacaoDiariaObservadaEntity
        );
        onsDescontinuadoPrecipitacaoDiariaObservadaSearchRepository.save(onsDescontinuadoPrecipitacaoDiariaObservadaEntity);

        // Search the onsDescontinuadoPrecipitacaoDiariaObservada
        restOnsDescontinuadoPrecipitacaoDiariaObservadaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDescontinuadoPrecipitacaoDiariaObservadaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].codEstacao").value(hasItem(DEFAULT_COD_ESTACAO)))
            .andExpect(jsonPath("$.[*].valLatitude").value(hasItem(DEFAULT_VAL_LATITUDE)))
            .andExpect(jsonPath("$.[*].valLongitude").value(hasItem(DEFAULT_VAL_LONGITUDE)))
            .andExpect(jsonPath("$.[*].valMedida").value(hasItem(DEFAULT_VAL_MEDIDA)))
            .andExpect(jsonPath("$.[*].datObservada").value(hasItem(DEFAULT_DAT_OBSERVADA.toString())));
    }

    protected long getRepositoryCount() {
        return onsDescontinuadoPrecipitacaoDiariaObservadaRepository.count();
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

    protected OnsDescontinuadoPrecipitacaoDiariaObservadaEntity getPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity(
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity onsDescontinuadoPrecipitacaoDiariaObservada
    ) {
        return onsDescontinuadoPrecipitacaoDiariaObservadaRepository
            .findById(onsDescontinuadoPrecipitacaoDiariaObservada.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntityToMatchAllProperties(
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity expectedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity
    ) {
        assertOnsDescontinuadoPrecipitacaoDiariaObservadaEntityAllPropertiesEquals(
            expectedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity,
            getPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity(expectedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity)
        );
    }

    protected void assertPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntityToMatchUpdatableProperties(
        OnsDescontinuadoPrecipitacaoDiariaObservadaEntity expectedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity
    ) {
        assertOnsDescontinuadoPrecipitacaoDiariaObservadaEntityAllUpdatablePropertiesEquals(
            expectedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity,
            getPersistedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity(expectedOnsDescontinuadoPrecipitacaoDiariaObservadaEntity)
        );
    }
}
