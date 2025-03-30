package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioSubsistemaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEnaDiarioSubsistemaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioSubsistemaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioSubsistemaSearchRepository;
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
 * Integration tests for the {@link OnsEnaDiarioSubsistemaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEnaDiarioSubsistemaResourceIT {

    private static final Double DEFAULT_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT = 1D;
    private static final Double UPDATED_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ena-diario-subsistemas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ena-diario-subsistemas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEnaDiarioSubsistemaRepository onsEnaDiarioSubsistemaRepository;

    @Autowired
    private OnsEnaDiarioSubsistemaSearchRepository onsEnaDiarioSubsistemaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEnaDiarioSubsistemaMockMvc;

    private OnsEnaDiarioSubsistemaEntity onsEnaDiarioSubsistemaEntity;

    private OnsEnaDiarioSubsistemaEntity insertedOnsEnaDiarioSubsistemaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioSubsistemaEntity createEntity() {
        return new OnsEnaDiarioSubsistemaEntity().enaArmazenavelRegiaoPercentualmlt(DEFAULT_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioSubsistemaEntity createUpdatedEntity() {
        return new OnsEnaDiarioSubsistemaEntity().enaArmazenavelRegiaoPercentualmlt(UPDATED_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT);
    }

    @BeforeEach
    public void initTest() {
        onsEnaDiarioSubsistemaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEnaDiarioSubsistemaEntity != null) {
            onsEnaDiarioSubsistemaRepository.delete(insertedOnsEnaDiarioSubsistemaEntity);
            onsEnaDiarioSubsistemaSearchRepository.delete(insertedOnsEnaDiarioSubsistemaEntity);
            insertedOnsEnaDiarioSubsistemaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEnaDiarioSubsistema() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        // Create the OnsEnaDiarioSubsistema
        var returnedOnsEnaDiarioSubsistemaEntity = om.readValue(
            restOnsEnaDiarioSubsistemaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEnaDiarioSubsistemaEntity.class
        );

        // Validate the OnsEnaDiarioSubsistema in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEnaDiarioSubsistemaEntityUpdatableFieldsEquals(
            returnedOnsEnaDiarioSubsistemaEntity,
            getPersistedOnsEnaDiarioSubsistemaEntity(returnedOnsEnaDiarioSubsistemaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEnaDiarioSubsistemaEntity = returnedOnsEnaDiarioSubsistemaEntity;
    }

    @Test
    @Transactional
    void createOnsEnaDiarioSubsistemaWithExistingId() throws Exception {
        // Create the OnsEnaDiarioSubsistema with an existing ID
        onsEnaDiarioSubsistemaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEnaDiarioSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.saveAndFlush(onsEnaDiarioSubsistemaEntity);

        // Get all the onsEnaDiarioSubsistemaList
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioSubsistemaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaArmazenavelRegiaoPercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT)));
    }

    @Test
    @Transactional
    void getOnsEnaDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.saveAndFlush(onsEnaDiarioSubsistemaEntity);

        // Get the onsEnaDiarioSubsistema
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEnaDiarioSubsistemaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEnaDiarioSubsistemaEntity.getId().intValue()))
            .andExpect(jsonPath("$.enaArmazenavelRegiaoPercentualmlt").value(DEFAULT_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT));
    }

    @Test
    @Transactional
    void getNonExistingOnsEnaDiarioSubsistema() throws Exception {
        // Get the onsEnaDiarioSubsistema
        restOnsEnaDiarioSubsistemaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEnaDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.saveAndFlush(onsEnaDiarioSubsistemaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEnaDiarioSubsistemaSearchRepository.save(onsEnaDiarioSubsistemaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());

        // Update the onsEnaDiarioSubsistema
        OnsEnaDiarioSubsistemaEntity updatedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository
            .findById(onsEnaDiarioSubsistemaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEnaDiarioSubsistemaEntity are not directly saved in db
        em.detach(updatedOnsEnaDiarioSubsistemaEntity);
        updatedOnsEnaDiarioSubsistemaEntity.enaArmazenavelRegiaoPercentualmlt(UPDATED_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT);

        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEnaDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEnaDiarioSubsistemaEntityToMatchAllProperties(updatedOnsEnaDiarioSubsistemaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEnaDiarioSubsistemaEntity> onsEnaDiarioSubsistemaSearchList = Streamable.of(
                    onsEnaDiarioSubsistemaSearchRepository.findAll()
                ).toList();
                OnsEnaDiarioSubsistemaEntity testOnsEnaDiarioSubsistemaSearch = onsEnaDiarioSubsistemaSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsEnaDiarioSubsistemaEntityAllPropertiesEquals(
                    testOnsEnaDiarioSubsistemaSearch,
                    updatedOnsEnaDiarioSubsistemaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEnaDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        onsEnaDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEnaDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEnaDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        onsEnaDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEnaDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        onsEnaDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEnaDiarioSubsistemaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.saveAndFlush(onsEnaDiarioSubsistemaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioSubsistema using partial update
        OnsEnaDiarioSubsistemaEntity partialUpdatedOnsEnaDiarioSubsistemaEntity = new OnsEnaDiarioSubsistemaEntity();
        partialUpdatedOnsEnaDiarioSubsistemaEntity.setId(onsEnaDiarioSubsistemaEntity.getId());

        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioSubsistema in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioSubsistemaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsEnaDiarioSubsistemaEntity, onsEnaDiarioSubsistemaEntity),
            getPersistedOnsEnaDiarioSubsistemaEntity(onsEnaDiarioSubsistemaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEnaDiarioSubsistemaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.saveAndFlush(onsEnaDiarioSubsistemaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioSubsistema using partial update
        OnsEnaDiarioSubsistemaEntity partialUpdatedOnsEnaDiarioSubsistemaEntity = new OnsEnaDiarioSubsistemaEntity();
        partialUpdatedOnsEnaDiarioSubsistemaEntity.setId(onsEnaDiarioSubsistemaEntity.getId());

        partialUpdatedOnsEnaDiarioSubsistemaEntity.enaArmazenavelRegiaoPercentualmlt(UPDATED_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT);

        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioSubsistema in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioSubsistemaEntityUpdatableFieldsEquals(
            partialUpdatedOnsEnaDiarioSubsistemaEntity,
            getPersistedOnsEnaDiarioSubsistemaEntity(partialUpdatedOnsEnaDiarioSubsistemaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEnaDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        onsEnaDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEnaDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEnaDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        onsEnaDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEnaDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        onsEnaDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioSubsistemaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEnaDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.saveAndFlush(onsEnaDiarioSubsistemaEntity);
        onsEnaDiarioSubsistemaRepository.save(onsEnaDiarioSubsistemaEntity);
        onsEnaDiarioSubsistemaSearchRepository.save(onsEnaDiarioSubsistemaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEnaDiarioSubsistema
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsEnaDiarioSubsistemaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEnaDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.saveAndFlush(onsEnaDiarioSubsistemaEntity);
        onsEnaDiarioSubsistemaSearchRepository.save(onsEnaDiarioSubsistemaEntity);

        // Search the onsEnaDiarioSubsistema
        restOnsEnaDiarioSubsistemaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEnaDiarioSubsistemaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioSubsistemaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaArmazenavelRegiaoPercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_REGIAO_PERCENTUALMLT)));
    }

    protected long getRepositoryCount() {
        return onsEnaDiarioSubsistemaRepository.count();
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

    protected OnsEnaDiarioSubsistemaEntity getPersistedOnsEnaDiarioSubsistemaEntity(OnsEnaDiarioSubsistemaEntity onsEnaDiarioSubsistema) {
        return onsEnaDiarioSubsistemaRepository.findById(onsEnaDiarioSubsistema.getId()).orElseThrow();
    }

    protected void assertPersistedOnsEnaDiarioSubsistemaEntityToMatchAllProperties(
        OnsEnaDiarioSubsistemaEntity expectedOnsEnaDiarioSubsistemaEntity
    ) {
        assertOnsEnaDiarioSubsistemaEntityAllPropertiesEquals(
            expectedOnsEnaDiarioSubsistemaEntity,
            getPersistedOnsEnaDiarioSubsistemaEntity(expectedOnsEnaDiarioSubsistemaEntity)
        );
    }

    protected void assertPersistedOnsEnaDiarioSubsistemaEntityToMatchUpdatableProperties(
        OnsEnaDiarioSubsistemaEntity expectedOnsEnaDiarioSubsistemaEntity
    ) {
        assertOnsEnaDiarioSubsistemaEntityAllUpdatablePropertiesEquals(
            expectedOnsEnaDiarioSubsistemaEntity,
            getPersistedOnsEnaDiarioSubsistemaEntity(expectedOnsEnaDiarioSubsistemaEntity)
        );
    }
}
