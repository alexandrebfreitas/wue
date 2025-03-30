package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioReservatorioRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioReservatorioSearchRepository;
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
 * Integration tests for the {@link OnsEnaDiarioReservatorioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEnaDiarioReservatorioResourceIT {

    private static final Double DEFAULT_ENA_BRUTA_RES_MWMED = 1D;
    private static final Double UPDATED_ENA_BRUTA_RES_MWMED = 2D;

    private static final Double DEFAULT_ENA_BRUTA_RES_PERCENTUALMLT = 1D;
    private static final Double UPDATED_ENA_BRUTA_RES_PERCENTUALMLT = 2D;

    private static final Double DEFAULT_ENA_ARMAZENAVEL_RES_MWMED = 1D;
    private static final Double UPDATED_ENA_ARMAZENAVEL_RES_MWMED = 2D;

    private static final Double DEFAULT_ENA_ARMAZENAVEL_RES_PERCENTUALMLT = 1D;
    private static final Double UPDATED_ENA_ARMAZENAVEL_RES_PERCENTUALMLT = 2D;

    private static final Double DEFAULT_ENA_QUEDA_BRUTA = 1D;
    private static final Double UPDATED_ENA_QUEDA_BRUTA = 2D;

    private static final Double DEFAULT_MLT_ENA = 1D;
    private static final Double UPDATED_MLT_ENA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ena-diario-reservatorios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ena-diario-reservatorios/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEnaDiarioReservatorioRepository onsEnaDiarioReservatorioRepository;

    @Autowired
    private OnsEnaDiarioReservatorioSearchRepository onsEnaDiarioReservatorioSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEnaDiarioReservatorioMockMvc;

    private OnsEnaDiarioReservatorioEntity onsEnaDiarioReservatorioEntity;

    private OnsEnaDiarioReservatorioEntity insertedOnsEnaDiarioReservatorioEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioReservatorioEntity createEntity() {
        return new OnsEnaDiarioReservatorioEntity()
            .enaBrutaResMwmed(DEFAULT_ENA_BRUTA_RES_MWMED)
            .enaBrutaResPercentualmlt(DEFAULT_ENA_BRUTA_RES_PERCENTUALMLT)
            .enaArmazenavelResMwmed(DEFAULT_ENA_ARMAZENAVEL_RES_MWMED)
            .enaArmazenavelResPercentualmlt(DEFAULT_ENA_ARMAZENAVEL_RES_PERCENTUALMLT)
            .enaQuedaBruta(DEFAULT_ENA_QUEDA_BRUTA)
            .mltEna(DEFAULT_MLT_ENA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioReservatorioEntity createUpdatedEntity() {
        return new OnsEnaDiarioReservatorioEntity()
            .enaBrutaResMwmed(UPDATED_ENA_BRUTA_RES_MWMED)
            .enaBrutaResPercentualmlt(UPDATED_ENA_BRUTA_RES_PERCENTUALMLT)
            .enaArmazenavelResMwmed(UPDATED_ENA_ARMAZENAVEL_RES_MWMED)
            .enaArmazenavelResPercentualmlt(UPDATED_ENA_ARMAZENAVEL_RES_PERCENTUALMLT)
            .enaQuedaBruta(UPDATED_ENA_QUEDA_BRUTA)
            .mltEna(UPDATED_MLT_ENA);
    }

    @BeforeEach
    public void initTest() {
        onsEnaDiarioReservatorioEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEnaDiarioReservatorioEntity != null) {
            onsEnaDiarioReservatorioRepository.delete(insertedOnsEnaDiarioReservatorioEntity);
            onsEnaDiarioReservatorioSearchRepository.delete(insertedOnsEnaDiarioReservatorioEntity);
            insertedOnsEnaDiarioReservatorioEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEnaDiarioReservatorio() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        // Create the OnsEnaDiarioReservatorio
        var returnedOnsEnaDiarioReservatorioEntity = om.readValue(
            restOnsEnaDiarioReservatorioMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEnaDiarioReservatorioEntity.class
        );

        // Validate the OnsEnaDiarioReservatorio in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEnaDiarioReservatorioEntityUpdatableFieldsEquals(
            returnedOnsEnaDiarioReservatorioEntity,
            getPersistedOnsEnaDiarioReservatorioEntity(returnedOnsEnaDiarioReservatorioEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEnaDiarioReservatorioEntity = returnedOnsEnaDiarioReservatorioEntity;
    }

    @Test
    @Transactional
    void createOnsEnaDiarioReservatorioWithExistingId() throws Exception {
        // Create the OnsEnaDiarioReservatorio with an existing ID
        onsEnaDiarioReservatorioEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEnaDiarioReservatorios() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.saveAndFlush(onsEnaDiarioReservatorioEntity);

        // Get all the onsEnaDiarioReservatorioList
        restOnsEnaDiarioReservatorioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioReservatorioEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaBrutaResMwmed").value(hasItem(DEFAULT_ENA_BRUTA_RES_MWMED)))
            .andExpect(jsonPath("$.[*].enaBrutaResPercentualmlt").value(hasItem(DEFAULT_ENA_BRUTA_RES_PERCENTUALMLT)))
            .andExpect(jsonPath("$.[*].enaArmazenavelResMwmed").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_RES_MWMED)))
            .andExpect(jsonPath("$.[*].enaArmazenavelResPercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_RES_PERCENTUALMLT)))
            .andExpect(jsonPath("$.[*].enaQuedaBruta").value(hasItem(DEFAULT_ENA_QUEDA_BRUTA)))
            .andExpect(jsonPath("$.[*].mltEna").value(hasItem(DEFAULT_MLT_ENA)));
    }

    @Test
    @Transactional
    void getOnsEnaDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.saveAndFlush(onsEnaDiarioReservatorioEntity);

        // Get the onsEnaDiarioReservatorio
        restOnsEnaDiarioReservatorioMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEnaDiarioReservatorioEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEnaDiarioReservatorioEntity.getId().intValue()))
            .andExpect(jsonPath("$.enaBrutaResMwmed").value(DEFAULT_ENA_BRUTA_RES_MWMED))
            .andExpect(jsonPath("$.enaBrutaResPercentualmlt").value(DEFAULT_ENA_BRUTA_RES_PERCENTUALMLT))
            .andExpect(jsonPath("$.enaArmazenavelResMwmed").value(DEFAULT_ENA_ARMAZENAVEL_RES_MWMED))
            .andExpect(jsonPath("$.enaArmazenavelResPercentualmlt").value(DEFAULT_ENA_ARMAZENAVEL_RES_PERCENTUALMLT))
            .andExpect(jsonPath("$.enaQuedaBruta").value(DEFAULT_ENA_QUEDA_BRUTA))
            .andExpect(jsonPath("$.mltEna").value(DEFAULT_MLT_ENA));
    }

    @Test
    @Transactional
    void getNonExistingOnsEnaDiarioReservatorio() throws Exception {
        // Get the onsEnaDiarioReservatorio
        restOnsEnaDiarioReservatorioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEnaDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.saveAndFlush(onsEnaDiarioReservatorioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEnaDiarioReservatorioSearchRepository.save(onsEnaDiarioReservatorioEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());

        // Update the onsEnaDiarioReservatorio
        OnsEnaDiarioReservatorioEntity updatedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository
            .findById(onsEnaDiarioReservatorioEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEnaDiarioReservatorioEntity are not directly saved in db
        em.detach(updatedOnsEnaDiarioReservatorioEntity);
        updatedOnsEnaDiarioReservatorioEntity
            .enaBrutaResMwmed(UPDATED_ENA_BRUTA_RES_MWMED)
            .enaBrutaResPercentualmlt(UPDATED_ENA_BRUTA_RES_PERCENTUALMLT)
            .enaArmazenavelResMwmed(UPDATED_ENA_ARMAZENAVEL_RES_MWMED)
            .enaArmazenavelResPercentualmlt(UPDATED_ENA_ARMAZENAVEL_RES_PERCENTUALMLT)
            .enaQuedaBruta(UPDATED_ENA_QUEDA_BRUTA)
            .mltEna(UPDATED_MLT_ENA);

        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEnaDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEnaDiarioReservatorioEntityToMatchAllProperties(updatedOnsEnaDiarioReservatorioEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEnaDiarioReservatorioEntity> onsEnaDiarioReservatorioSearchList = Streamable.of(
                    onsEnaDiarioReservatorioSearchRepository.findAll()
                ).toList();
                OnsEnaDiarioReservatorioEntity testOnsEnaDiarioReservatorioSearch = onsEnaDiarioReservatorioSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsEnaDiarioReservatorioEntityAllPropertiesEquals(
                    testOnsEnaDiarioReservatorioSearch,
                    updatedOnsEnaDiarioReservatorioEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEnaDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        onsEnaDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEnaDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEnaDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        onsEnaDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEnaDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        onsEnaDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEnaDiarioReservatorioWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.saveAndFlush(onsEnaDiarioReservatorioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioReservatorio using partial update
        OnsEnaDiarioReservatorioEntity partialUpdatedOnsEnaDiarioReservatorioEntity = new OnsEnaDiarioReservatorioEntity();
        partialUpdatedOnsEnaDiarioReservatorioEntity.setId(onsEnaDiarioReservatorioEntity.getId());

        partialUpdatedOnsEnaDiarioReservatorioEntity
            .enaBrutaResMwmed(UPDATED_ENA_BRUTA_RES_MWMED)
            .enaArmazenavelResMwmed(UPDATED_ENA_ARMAZENAVEL_RES_MWMED)
            .enaArmazenavelResPercentualmlt(UPDATED_ENA_ARMAZENAVEL_RES_PERCENTUALMLT)
            .mltEna(UPDATED_MLT_ENA);

        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioReservatorio in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioReservatorioEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsEnaDiarioReservatorioEntity, onsEnaDiarioReservatorioEntity),
            getPersistedOnsEnaDiarioReservatorioEntity(onsEnaDiarioReservatorioEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEnaDiarioReservatorioWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.saveAndFlush(onsEnaDiarioReservatorioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioReservatorio using partial update
        OnsEnaDiarioReservatorioEntity partialUpdatedOnsEnaDiarioReservatorioEntity = new OnsEnaDiarioReservatorioEntity();
        partialUpdatedOnsEnaDiarioReservatorioEntity.setId(onsEnaDiarioReservatorioEntity.getId());

        partialUpdatedOnsEnaDiarioReservatorioEntity
            .enaBrutaResMwmed(UPDATED_ENA_BRUTA_RES_MWMED)
            .enaBrutaResPercentualmlt(UPDATED_ENA_BRUTA_RES_PERCENTUALMLT)
            .enaArmazenavelResMwmed(UPDATED_ENA_ARMAZENAVEL_RES_MWMED)
            .enaArmazenavelResPercentualmlt(UPDATED_ENA_ARMAZENAVEL_RES_PERCENTUALMLT)
            .enaQuedaBruta(UPDATED_ENA_QUEDA_BRUTA)
            .mltEna(UPDATED_MLT_ENA);

        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioReservatorio in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioReservatorioEntityUpdatableFieldsEquals(
            partialUpdatedOnsEnaDiarioReservatorioEntity,
            getPersistedOnsEnaDiarioReservatorioEntity(partialUpdatedOnsEnaDiarioReservatorioEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEnaDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        onsEnaDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEnaDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEnaDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        onsEnaDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEnaDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        onsEnaDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioReservatorioEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEnaDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.saveAndFlush(onsEnaDiarioReservatorioEntity);
        onsEnaDiarioReservatorioRepository.save(onsEnaDiarioReservatorioEntity);
        onsEnaDiarioReservatorioSearchRepository.save(onsEnaDiarioReservatorioEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEnaDiarioReservatorio
        restOnsEnaDiarioReservatorioMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsEnaDiarioReservatorioEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEnaDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.saveAndFlush(onsEnaDiarioReservatorioEntity);
        onsEnaDiarioReservatorioSearchRepository.save(onsEnaDiarioReservatorioEntity);

        // Search the onsEnaDiarioReservatorio
        restOnsEnaDiarioReservatorioMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEnaDiarioReservatorioEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioReservatorioEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaBrutaResMwmed").value(hasItem(DEFAULT_ENA_BRUTA_RES_MWMED)))
            .andExpect(jsonPath("$.[*].enaBrutaResPercentualmlt").value(hasItem(DEFAULT_ENA_BRUTA_RES_PERCENTUALMLT)))
            .andExpect(jsonPath("$.[*].enaArmazenavelResMwmed").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_RES_MWMED)))
            .andExpect(jsonPath("$.[*].enaArmazenavelResPercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_RES_PERCENTUALMLT)))
            .andExpect(jsonPath("$.[*].enaQuedaBruta").value(hasItem(DEFAULT_ENA_QUEDA_BRUTA)))
            .andExpect(jsonPath("$.[*].mltEna").value(hasItem(DEFAULT_MLT_ENA)));
    }

    protected long getRepositoryCount() {
        return onsEnaDiarioReservatorioRepository.count();
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

    protected OnsEnaDiarioReservatorioEntity getPersistedOnsEnaDiarioReservatorioEntity(
        OnsEnaDiarioReservatorioEntity onsEnaDiarioReservatorio
    ) {
        return onsEnaDiarioReservatorioRepository.findById(onsEnaDiarioReservatorio.getId()).orElseThrow();
    }

    protected void assertPersistedOnsEnaDiarioReservatorioEntityToMatchAllProperties(
        OnsEnaDiarioReservatorioEntity expectedOnsEnaDiarioReservatorioEntity
    ) {
        assertOnsEnaDiarioReservatorioEntityAllPropertiesEquals(
            expectedOnsEnaDiarioReservatorioEntity,
            getPersistedOnsEnaDiarioReservatorioEntity(expectedOnsEnaDiarioReservatorioEntity)
        );
    }

    protected void assertPersistedOnsEnaDiarioReservatorioEntityToMatchUpdatableProperties(
        OnsEnaDiarioReservatorioEntity expectedOnsEnaDiarioReservatorioEntity
    ) {
        assertOnsEnaDiarioReservatorioEntityAllUpdatablePropertiesEquals(
            expectedOnsEnaDiarioReservatorioEntity,
            getPersistedOnsEnaDiarioReservatorioEntity(expectedOnsEnaDiarioReservatorioEntity)
        );
    }
}
