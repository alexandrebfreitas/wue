package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository;
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
 * Integration tests for the {@link OnsEnaDiarioReeReservatorioEquivalenteEnergiaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEnaDiarioReeReservatorioEquivalenteEnergiaResourceIT {

    private static final Double DEFAULT_ENA_ARMAZENAVEL_REE_PERCENTUALMLT = 1D;
    private static final Double UPDATED_ENA_ARMAZENAVEL_REE_PERCENTUALMLT = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ena-diario-ree-reservatorio-equivalente-energias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ena-diario-ree-reservatorio-equivalente-energias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository;

    @Autowired
    private OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc;

    private OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity;

    private OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity createEntity() {
        return new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity()
            .enaArmazenavelReePercentualmlt(DEFAULT_ENA_ARMAZENAVEL_REE_PERCENTUALMLT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity createUpdatedEntity() {
        return new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity()
            .enaArmazenavelReePercentualmlt(UPDATED_ENA_ARMAZENAVEL_REE_PERCENTUALMLT);
    }

    @BeforeEach
    public void initTest() {
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity != null) {
            onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.delete(insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
            onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.delete(
                insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
            );
            insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        // Create the OnsEnaDiarioReeReservatorioEquivalenteEnergia
        var returnedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = om.readValue(
            restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.class
        );

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(
            returnedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity(returnedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = returnedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity;
    }

    @Test
    @Transactional
    void createOnsEnaDiarioReeReservatorioEquivalenteEnergiaWithExistingId() throws Exception {
        // Create the OnsEnaDiarioReeReservatorioEquivalenteEnergia with an existing ID
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEnaDiarioReeReservatorioEquivalenteEnergias() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        // Get all the onsEnaDiarioReeReservatorioEquivalenteEnergiaList
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaArmazenavelReePercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_REE_PERCENTUALMLT)));
    }

    @Test
    @Transactional
    void getOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        // Get the onsEnaDiarioReeReservatorioEquivalenteEnergia
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId().intValue()))
            .andExpect(jsonPath("$.enaArmazenavelReePercentualmlt").value(DEFAULT_ENA_ARMAZENAVEL_REE_PERCENTUALMLT));
    }

    @Test
    @Transactional
    void getNonExistingOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Get the onsEnaDiarioReeReservatorioEquivalenteEnergia
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.save(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());

        // Update the onsEnaDiarioReeReservatorioEquivalenteEnergia
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity =
            onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository
                .findById(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity are not directly saved in db
        em.detach(updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
        updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.enaArmazenavelReePercentualmlt(
            UPDATED_ENA_ARMAZENAVEL_REE_PERCENTUALMLT
        );

        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityToMatchAllProperties(
            updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchList =
                    Streamable.of(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll()).toList();
                OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity testOnsEnaDiarioReeReservatorioEquivalenteEnergiaSearch =
                    onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAllPropertiesEquals(
                    testOnsEnaDiarioReeReservatorioEquivalenteEnergiaSearch,
                    updatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEnaDiarioReeReservatorioEquivalenteEnergiaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioReeReservatorioEquivalenteEnergia using partial update
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity =
            new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity();
        partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId()
        );

        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity,
                onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
            ),
            getPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEnaDiarioReeReservatorioEquivalenteEnergiaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioReeReservatorioEquivalenteEnergia using partial update
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity =
            new OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity();
        partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId()
        );

        partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.enaArmazenavelReePercentualmlt(
            UPDATED_ENA_ARMAZENAVEL_REE_PERCENTUALMLT
        );

        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(
            partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity(
                partialUpdatedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.save(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);
        onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.save(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEnaDiarioReeReservatorioEquivalenteEnergia
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEnaDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity = onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository.save(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity);

        // Search the onsEnaDiarioReeReservatorioEquivalenteEnergia
        restOnsEnaDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaArmazenavelReePercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_REE_PERCENTUALMLT)));
    }

    protected long getRepositoryCount() {
        return onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository.count();
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

    protected OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity getPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity onsEnaDiarioReeReservatorioEquivalenteEnergia
    ) {
        return onsEnaDiarioReeReservatorioEquivalenteEnergiaRepository
            .findById(onsEnaDiarioReeReservatorioEquivalenteEnergia.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityToMatchAllProperties(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity expectedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
    ) {
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAllPropertiesEquals(
            expectedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity(expectedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity)
        );
    }

    protected void assertPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityToMatchUpdatableProperties(
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity expectedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity
    ) {
        assertOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntityAllUpdatablePropertiesEquals(
            expectedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity(expectedOnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity)
        );
    }
}
