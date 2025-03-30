package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioBaciaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioBaciaSearchRepository;
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
 * Integration tests for the {@link OnsEnaDiarioBaciaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEnaDiarioBaciaResourceIT {

    private static final Double DEFAULT_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT = 1D;
    private static final Double UPDATED_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ena-diario-bacias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ena-diario-bacias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEnaDiarioBaciaRepository onsEnaDiarioBaciaRepository;

    @Autowired
    private OnsEnaDiarioBaciaSearchRepository onsEnaDiarioBaciaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEnaDiarioBaciaMockMvc;

    private OnsEnaDiarioBaciaEntity onsEnaDiarioBaciaEntity;

    private OnsEnaDiarioBaciaEntity insertedOnsEnaDiarioBaciaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioBaciaEntity createEntity() {
        return new OnsEnaDiarioBaciaEntity().enaArmazenavelBaciaPercentualmlt(DEFAULT_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEnaDiarioBaciaEntity createUpdatedEntity() {
        return new OnsEnaDiarioBaciaEntity().enaArmazenavelBaciaPercentualmlt(UPDATED_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT);
    }

    @BeforeEach
    public void initTest() {
        onsEnaDiarioBaciaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEnaDiarioBaciaEntity != null) {
            onsEnaDiarioBaciaRepository.delete(insertedOnsEnaDiarioBaciaEntity);
            onsEnaDiarioBaciaSearchRepository.delete(insertedOnsEnaDiarioBaciaEntity);
            insertedOnsEnaDiarioBaciaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEnaDiarioBacia() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        // Create the OnsEnaDiarioBacia
        var returnedOnsEnaDiarioBaciaEntity = om.readValue(
            restOnsEnaDiarioBaciaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEnaDiarioBaciaEntity.class
        );

        // Validate the OnsEnaDiarioBacia in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEnaDiarioBaciaEntityUpdatableFieldsEquals(
            returnedOnsEnaDiarioBaciaEntity,
            getPersistedOnsEnaDiarioBaciaEntity(returnedOnsEnaDiarioBaciaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEnaDiarioBaciaEntity = returnedOnsEnaDiarioBaciaEntity;
    }

    @Test
    @Transactional
    void createOnsEnaDiarioBaciaWithExistingId() throws Exception {
        // Create the OnsEnaDiarioBacia with an existing ID
        onsEnaDiarioBaciaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEnaDiarioBaciaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEnaDiarioBacias() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.saveAndFlush(onsEnaDiarioBaciaEntity);

        // Get all the onsEnaDiarioBaciaList
        restOnsEnaDiarioBaciaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioBaciaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaArmazenavelBaciaPercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT)));
    }

    @Test
    @Transactional
    void getOnsEnaDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.saveAndFlush(onsEnaDiarioBaciaEntity);

        // Get the onsEnaDiarioBacia
        restOnsEnaDiarioBaciaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEnaDiarioBaciaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEnaDiarioBaciaEntity.getId().intValue()))
            .andExpect(jsonPath("$.enaArmazenavelBaciaPercentualmlt").value(DEFAULT_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT));
    }

    @Test
    @Transactional
    void getNonExistingOnsEnaDiarioBacia() throws Exception {
        // Get the onsEnaDiarioBacia
        restOnsEnaDiarioBaciaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEnaDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.saveAndFlush(onsEnaDiarioBaciaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEnaDiarioBaciaSearchRepository.save(onsEnaDiarioBaciaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());

        // Update the onsEnaDiarioBacia
        OnsEnaDiarioBaciaEntity updatedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository
            .findById(onsEnaDiarioBaciaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEnaDiarioBaciaEntity are not directly saved in db
        em.detach(updatedOnsEnaDiarioBaciaEntity);
        updatedOnsEnaDiarioBaciaEntity.enaArmazenavelBaciaPercentualmlt(UPDATED_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT);

        restOnsEnaDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEnaDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEnaDiarioBaciaEntityToMatchAllProperties(updatedOnsEnaDiarioBaciaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEnaDiarioBaciaEntity> onsEnaDiarioBaciaSearchList = Streamable.of(
                    onsEnaDiarioBaciaSearchRepository.findAll()
                ).toList();
                OnsEnaDiarioBaciaEntity testOnsEnaDiarioBaciaSearch = onsEnaDiarioBaciaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsEnaDiarioBaciaEntityAllPropertiesEquals(testOnsEnaDiarioBaciaSearch, updatedOnsEnaDiarioBaciaEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEnaDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        onsEnaDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEnaDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEnaDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        onsEnaDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEnaDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        onsEnaDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEnaDiarioBaciaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.saveAndFlush(onsEnaDiarioBaciaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioBacia using partial update
        OnsEnaDiarioBaciaEntity partialUpdatedOnsEnaDiarioBaciaEntity = new OnsEnaDiarioBaciaEntity();
        partialUpdatedOnsEnaDiarioBaciaEntity.setId(onsEnaDiarioBaciaEntity.getId());

        restOnsEnaDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioBacia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioBaciaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsEnaDiarioBaciaEntity, onsEnaDiarioBaciaEntity),
            getPersistedOnsEnaDiarioBaciaEntity(onsEnaDiarioBaciaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEnaDiarioBaciaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.saveAndFlush(onsEnaDiarioBaciaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEnaDiarioBacia using partial update
        OnsEnaDiarioBaciaEntity partialUpdatedOnsEnaDiarioBaciaEntity = new OnsEnaDiarioBaciaEntity();
        partialUpdatedOnsEnaDiarioBaciaEntity.setId(onsEnaDiarioBaciaEntity.getId());

        partialUpdatedOnsEnaDiarioBaciaEntity.enaArmazenavelBaciaPercentualmlt(UPDATED_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT);

        restOnsEnaDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEnaDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEnaDiarioBacia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEnaDiarioBaciaEntityUpdatableFieldsEquals(
            partialUpdatedOnsEnaDiarioBaciaEntity,
            getPersistedOnsEnaDiarioBaciaEntity(partialUpdatedOnsEnaDiarioBaciaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEnaDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        onsEnaDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEnaDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEnaDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEnaDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        onsEnaDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEnaDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        onsEnaDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEnaDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEnaDiarioBaciaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEnaDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEnaDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.saveAndFlush(onsEnaDiarioBaciaEntity);
        onsEnaDiarioBaciaRepository.save(onsEnaDiarioBaciaEntity);
        onsEnaDiarioBaciaSearchRepository.save(onsEnaDiarioBaciaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEnaDiarioBacia
        restOnsEnaDiarioBaciaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsEnaDiarioBaciaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEnaDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEnaDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.saveAndFlush(onsEnaDiarioBaciaEntity);
        onsEnaDiarioBaciaSearchRepository.save(onsEnaDiarioBaciaEntity);

        // Search the onsEnaDiarioBacia
        restOnsEnaDiarioBaciaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEnaDiarioBaciaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEnaDiarioBaciaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].enaArmazenavelBaciaPercentualmlt").value(hasItem(DEFAULT_ENA_ARMAZENAVEL_BACIA_PERCENTUALMLT)));
    }

    protected long getRepositoryCount() {
        return onsEnaDiarioBaciaRepository.count();
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

    protected OnsEnaDiarioBaciaEntity getPersistedOnsEnaDiarioBaciaEntity(OnsEnaDiarioBaciaEntity onsEnaDiarioBacia) {
        return onsEnaDiarioBaciaRepository.findById(onsEnaDiarioBacia.getId()).orElseThrow();
    }

    protected void assertPersistedOnsEnaDiarioBaciaEntityToMatchAllProperties(OnsEnaDiarioBaciaEntity expectedOnsEnaDiarioBaciaEntity) {
        assertOnsEnaDiarioBaciaEntityAllPropertiesEquals(
            expectedOnsEnaDiarioBaciaEntity,
            getPersistedOnsEnaDiarioBaciaEntity(expectedOnsEnaDiarioBaciaEntity)
        );
    }

    protected void assertPersistedOnsEnaDiarioBaciaEntityToMatchUpdatableProperties(
        OnsEnaDiarioBaciaEntity expectedOnsEnaDiarioBaciaEntity
    ) {
        assertOnsEnaDiarioBaciaEntityAllUpdatablePropertiesEquals(
            expectedOnsEnaDiarioBaciaEntity,
            getPersistedOnsEnaDiarioBaciaEntity(expectedOnsEnaDiarioBaciaEntity)
        );
    }
}
