package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioBaciaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEarDiarioBaciaEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioBaciaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioBaciaSearchRepository;
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
 * Integration tests for the {@link OnsEarDiarioBaciaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEarDiarioBaciaResourceIT {

    private static final String DEFAULT_NOM_CURTO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_CURTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EAR_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EAR_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_EAR_MAX_BACIA = 1D;
    private static final Double UPDATED_EAR_MAX_BACIA = 2D;

    private static final Double DEFAULT_EAR_VERIF_BACIA_MWMES = 1D;
    private static final Double UPDATED_EAR_VERIF_BACIA_MWMES = 2D;

    private static final Double DEFAULT_EAR_VERIF_BACIA_PERCENTUAL = 1D;
    private static final Double UPDATED_EAR_VERIF_BACIA_PERCENTUAL = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ear-diario-bacias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ear-diario-bacias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEarDiarioBaciaRepository onsEarDiarioBaciaRepository;

    @Autowired
    private OnsEarDiarioBaciaSearchRepository onsEarDiarioBaciaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEarDiarioBaciaMockMvc;

    private OnsEarDiarioBaciaEntity onsEarDiarioBaciaEntity;

    private OnsEarDiarioBaciaEntity insertedOnsEarDiarioBaciaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioBaciaEntity createEntity() {
        return new OnsEarDiarioBaciaEntity()
            .nomCurto(DEFAULT_NOM_CURTO)
            .earData(DEFAULT_EAR_DATA)
            .earMaxBacia(DEFAULT_EAR_MAX_BACIA)
            .earVerifBaciaMwmes(DEFAULT_EAR_VERIF_BACIA_MWMES)
            .earVerifBaciaPercentual(DEFAULT_EAR_VERIF_BACIA_PERCENTUAL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioBaciaEntity createUpdatedEntity() {
        return new OnsEarDiarioBaciaEntity()
            .nomCurto(UPDATED_NOM_CURTO)
            .earData(UPDATED_EAR_DATA)
            .earMaxBacia(UPDATED_EAR_MAX_BACIA)
            .earVerifBaciaMwmes(UPDATED_EAR_VERIF_BACIA_MWMES)
            .earVerifBaciaPercentual(UPDATED_EAR_VERIF_BACIA_PERCENTUAL);
    }

    @BeforeEach
    public void initTest() {
        onsEarDiarioBaciaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEarDiarioBaciaEntity != null) {
            onsEarDiarioBaciaRepository.delete(insertedOnsEarDiarioBaciaEntity);
            onsEarDiarioBaciaSearchRepository.delete(insertedOnsEarDiarioBaciaEntity);
            insertedOnsEarDiarioBaciaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEarDiarioBacia() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        // Create the OnsEarDiarioBacia
        var returnedOnsEarDiarioBaciaEntity = om.readValue(
            restOnsEarDiarioBaciaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEarDiarioBaciaEntity.class
        );

        // Validate the OnsEarDiarioBacia in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEarDiarioBaciaEntityUpdatableFieldsEquals(
            returnedOnsEarDiarioBaciaEntity,
            getPersistedOnsEarDiarioBaciaEntity(returnedOnsEarDiarioBaciaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEarDiarioBaciaEntity = returnedOnsEarDiarioBaciaEntity;
    }

    @Test
    @Transactional
    void createOnsEarDiarioBaciaWithExistingId() throws Exception {
        // Create the OnsEarDiarioBacia with an existing ID
        onsEarDiarioBaciaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEarDiarioBaciaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEarDiarioBacias() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.saveAndFlush(onsEarDiarioBaciaEntity);

        // Get all the onsEarDiarioBaciaList
        restOnsEarDiarioBaciaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioBaciaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomCurto").value(hasItem(DEFAULT_NOM_CURTO)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(jsonPath("$.[*].earMaxBacia").value(hasItem(DEFAULT_EAR_MAX_BACIA)))
            .andExpect(jsonPath("$.[*].earVerifBaciaMwmes").value(hasItem(DEFAULT_EAR_VERIF_BACIA_MWMES)))
            .andExpect(jsonPath("$.[*].earVerifBaciaPercentual").value(hasItem(DEFAULT_EAR_VERIF_BACIA_PERCENTUAL)));
    }

    @Test
    @Transactional
    void getOnsEarDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.saveAndFlush(onsEarDiarioBaciaEntity);

        // Get the onsEarDiarioBacia
        restOnsEarDiarioBaciaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEarDiarioBaciaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEarDiarioBaciaEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomCurto").value(DEFAULT_NOM_CURTO))
            .andExpect(jsonPath("$.earData").value(DEFAULT_EAR_DATA.toString()))
            .andExpect(jsonPath("$.earMaxBacia").value(DEFAULT_EAR_MAX_BACIA))
            .andExpect(jsonPath("$.earVerifBaciaMwmes").value(DEFAULT_EAR_VERIF_BACIA_MWMES))
            .andExpect(jsonPath("$.earVerifBaciaPercentual").value(DEFAULT_EAR_VERIF_BACIA_PERCENTUAL));
    }

    @Test
    @Transactional
    void getNonExistingOnsEarDiarioBacia() throws Exception {
        // Get the onsEarDiarioBacia
        restOnsEarDiarioBaciaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEarDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.saveAndFlush(onsEarDiarioBaciaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEarDiarioBaciaSearchRepository.save(onsEarDiarioBaciaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());

        // Update the onsEarDiarioBacia
        OnsEarDiarioBaciaEntity updatedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository
            .findById(onsEarDiarioBaciaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEarDiarioBaciaEntity are not directly saved in db
        em.detach(updatedOnsEarDiarioBaciaEntity);
        updatedOnsEarDiarioBaciaEntity
            .nomCurto(UPDATED_NOM_CURTO)
            .earData(UPDATED_EAR_DATA)
            .earMaxBacia(UPDATED_EAR_MAX_BACIA)
            .earVerifBaciaMwmes(UPDATED_EAR_VERIF_BACIA_MWMES)
            .earVerifBaciaPercentual(UPDATED_EAR_VERIF_BACIA_PERCENTUAL);

        restOnsEarDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEarDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEarDiarioBaciaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEarDiarioBaciaEntityToMatchAllProperties(updatedOnsEarDiarioBaciaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEarDiarioBaciaEntity> onsEarDiarioBaciaSearchList = Streamable.of(
                    onsEarDiarioBaciaSearchRepository.findAll()
                ).toList();
                OnsEarDiarioBaciaEntity testOnsEarDiarioBaciaSearch = onsEarDiarioBaciaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsEarDiarioBaciaEntityAllPropertiesEquals(testOnsEarDiarioBaciaSearch, updatedOnsEarDiarioBaciaEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEarDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        onsEarDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEarDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEarDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        onsEarDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEarDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        onsEarDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioBaciaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEarDiarioBaciaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.saveAndFlush(onsEarDiarioBaciaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioBacia using partial update
        OnsEarDiarioBaciaEntity partialUpdatedOnsEarDiarioBaciaEntity = new OnsEarDiarioBaciaEntity();
        partialUpdatedOnsEarDiarioBaciaEntity.setId(onsEarDiarioBaciaEntity.getId());

        partialUpdatedOnsEarDiarioBaciaEntity
            .earMaxBacia(UPDATED_EAR_MAX_BACIA)
            .earVerifBaciaMwmes(UPDATED_EAR_VERIF_BACIA_MWMES)
            .earVerifBaciaPercentual(UPDATED_EAR_VERIF_BACIA_PERCENTUAL);

        restOnsEarDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioBaciaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioBacia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioBaciaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsEarDiarioBaciaEntity, onsEarDiarioBaciaEntity),
            getPersistedOnsEarDiarioBaciaEntity(onsEarDiarioBaciaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEarDiarioBaciaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.saveAndFlush(onsEarDiarioBaciaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioBacia using partial update
        OnsEarDiarioBaciaEntity partialUpdatedOnsEarDiarioBaciaEntity = new OnsEarDiarioBaciaEntity();
        partialUpdatedOnsEarDiarioBaciaEntity.setId(onsEarDiarioBaciaEntity.getId());

        partialUpdatedOnsEarDiarioBaciaEntity
            .nomCurto(UPDATED_NOM_CURTO)
            .earData(UPDATED_EAR_DATA)
            .earMaxBacia(UPDATED_EAR_MAX_BACIA)
            .earVerifBaciaMwmes(UPDATED_EAR_VERIF_BACIA_MWMES)
            .earVerifBaciaPercentual(UPDATED_EAR_VERIF_BACIA_PERCENTUAL);

        restOnsEarDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioBaciaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioBacia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioBaciaEntityUpdatableFieldsEquals(
            partialUpdatedOnsEarDiarioBaciaEntity,
            getPersistedOnsEarDiarioBaciaEntity(partialUpdatedOnsEarDiarioBaciaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEarDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        onsEarDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEarDiarioBaciaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEarDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        onsEarDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEarDiarioBacia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        onsEarDiarioBaciaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioBaciaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioBaciaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioBacia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEarDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.saveAndFlush(onsEarDiarioBaciaEntity);
        onsEarDiarioBaciaRepository.save(onsEarDiarioBaciaEntity);
        onsEarDiarioBaciaSearchRepository.save(onsEarDiarioBaciaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEarDiarioBacia
        restOnsEarDiarioBaciaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsEarDiarioBaciaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioBaciaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEarDiarioBacia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.saveAndFlush(onsEarDiarioBaciaEntity);
        onsEarDiarioBaciaSearchRepository.save(onsEarDiarioBaciaEntity);

        // Search the onsEarDiarioBacia
        restOnsEarDiarioBaciaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEarDiarioBaciaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioBaciaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomCurto").value(hasItem(DEFAULT_NOM_CURTO)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(jsonPath("$.[*].earMaxBacia").value(hasItem(DEFAULT_EAR_MAX_BACIA)))
            .andExpect(jsonPath("$.[*].earVerifBaciaMwmes").value(hasItem(DEFAULT_EAR_VERIF_BACIA_MWMES)))
            .andExpect(jsonPath("$.[*].earVerifBaciaPercentual").value(hasItem(DEFAULT_EAR_VERIF_BACIA_PERCENTUAL)));
    }

    protected long getRepositoryCount() {
        return onsEarDiarioBaciaRepository.count();
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

    protected OnsEarDiarioBaciaEntity getPersistedOnsEarDiarioBaciaEntity(OnsEarDiarioBaciaEntity onsEarDiarioBacia) {
        return onsEarDiarioBaciaRepository.findById(onsEarDiarioBacia.getId()).orElseThrow();
    }

    protected void assertPersistedOnsEarDiarioBaciaEntityToMatchAllProperties(OnsEarDiarioBaciaEntity expectedOnsEarDiarioBaciaEntity) {
        assertOnsEarDiarioBaciaEntityAllPropertiesEquals(
            expectedOnsEarDiarioBaciaEntity,
            getPersistedOnsEarDiarioBaciaEntity(expectedOnsEarDiarioBaciaEntity)
        );
    }

    protected void assertPersistedOnsEarDiarioBaciaEntityToMatchUpdatableProperties(
        OnsEarDiarioBaciaEntity expectedOnsEarDiarioBaciaEntity
    ) {
        assertOnsEarDiarioBaciaEntityAllUpdatablePropertiesEquals(
            expectedOnsEarDiarioBaciaEntity,
            getPersistedOnsEarDiarioBaciaEntity(expectedOnsEarDiarioBaciaEntity)
        );
    }
}
