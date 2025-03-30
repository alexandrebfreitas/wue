package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioSubsistemaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioSubsistemaSearchRepository;
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
 * Integration tests for the {@link OnsEarDiarioSubsistemaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEarDiarioSubsistemaResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EAR_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EAR_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_EAR_MAX_SUBSISTEMA = 1D;
    private static final Double UPDATED_EAR_MAX_SUBSISTEMA = 2D;

    private static final Double DEFAULT_EAR_VERIF_SUBSISTEMA_MWMES = 1D;
    private static final Double UPDATED_EAR_VERIF_SUBSISTEMA_MWMES = 2D;

    private static final Double DEFAULT_EAR_VERIF_SUBSISTEMA_PERCENTUAL = 1D;
    private static final Double UPDATED_EAR_VERIF_SUBSISTEMA_PERCENTUAL = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ear-diario-subsistemas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ear-diario-subsistemas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEarDiarioSubsistemaRepository onsEarDiarioSubsistemaRepository;

    @Autowired
    private OnsEarDiarioSubsistemaSearchRepository onsEarDiarioSubsistemaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEarDiarioSubsistemaMockMvc;

    private OnsEarDiarioSubsistemaEntity onsEarDiarioSubsistemaEntity;

    private OnsEarDiarioSubsistemaEntity insertedOnsEarDiarioSubsistemaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioSubsistemaEntity createEntity() {
        return new OnsEarDiarioSubsistemaEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .earData(DEFAULT_EAR_DATA)
            .earMaxSubsistema(DEFAULT_EAR_MAX_SUBSISTEMA)
            .earVerifSubsistemaMwmes(DEFAULT_EAR_VERIF_SUBSISTEMA_MWMES)
            .earVerifSubsistemaPercentual(DEFAULT_EAR_VERIF_SUBSISTEMA_PERCENTUAL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioSubsistemaEntity createUpdatedEntity() {
        return new OnsEarDiarioSubsistemaEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .earData(UPDATED_EAR_DATA)
            .earMaxSubsistema(UPDATED_EAR_MAX_SUBSISTEMA)
            .earVerifSubsistemaMwmes(UPDATED_EAR_VERIF_SUBSISTEMA_MWMES)
            .earVerifSubsistemaPercentual(UPDATED_EAR_VERIF_SUBSISTEMA_PERCENTUAL);
    }

    @BeforeEach
    public void initTest() {
        onsEarDiarioSubsistemaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEarDiarioSubsistemaEntity != null) {
            onsEarDiarioSubsistemaRepository.delete(insertedOnsEarDiarioSubsistemaEntity);
            onsEarDiarioSubsistemaSearchRepository.delete(insertedOnsEarDiarioSubsistemaEntity);
            insertedOnsEarDiarioSubsistemaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEarDiarioSubsistema() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        // Create the OnsEarDiarioSubsistema
        var returnedOnsEarDiarioSubsistemaEntity = om.readValue(
            restOnsEarDiarioSubsistemaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEarDiarioSubsistemaEntity.class
        );

        // Validate the OnsEarDiarioSubsistema in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEarDiarioSubsistemaEntityUpdatableFieldsEquals(
            returnedOnsEarDiarioSubsistemaEntity,
            getPersistedOnsEarDiarioSubsistemaEntity(returnedOnsEarDiarioSubsistemaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEarDiarioSubsistemaEntity = returnedOnsEarDiarioSubsistemaEntity;
    }

    @Test
    @Transactional
    void createOnsEarDiarioSubsistemaWithExistingId() throws Exception {
        // Create the OnsEarDiarioSubsistema with an existing ID
        onsEarDiarioSubsistemaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEarDiarioSubsistemas() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.saveAndFlush(onsEarDiarioSubsistemaEntity);

        // Get all the onsEarDiarioSubsistemaList
        restOnsEarDiarioSubsistemaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioSubsistemaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(jsonPath("$.[*].earMaxSubsistema").value(hasItem(DEFAULT_EAR_MAX_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].earVerifSubsistemaMwmes").value(hasItem(DEFAULT_EAR_VERIF_SUBSISTEMA_MWMES)))
            .andExpect(jsonPath("$.[*].earVerifSubsistemaPercentual").value(hasItem(DEFAULT_EAR_VERIF_SUBSISTEMA_PERCENTUAL)));
    }

    @Test
    @Transactional
    void getOnsEarDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.saveAndFlush(onsEarDiarioSubsistemaEntity);

        // Get the onsEarDiarioSubsistema
        restOnsEarDiarioSubsistemaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEarDiarioSubsistemaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEarDiarioSubsistemaEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.earData").value(DEFAULT_EAR_DATA.toString()))
            .andExpect(jsonPath("$.earMaxSubsistema").value(DEFAULT_EAR_MAX_SUBSISTEMA))
            .andExpect(jsonPath("$.earVerifSubsistemaMwmes").value(DEFAULT_EAR_VERIF_SUBSISTEMA_MWMES))
            .andExpect(jsonPath("$.earVerifSubsistemaPercentual").value(DEFAULT_EAR_VERIF_SUBSISTEMA_PERCENTUAL));
    }

    @Test
    @Transactional
    void getNonExistingOnsEarDiarioSubsistema() throws Exception {
        // Get the onsEarDiarioSubsistema
        restOnsEarDiarioSubsistemaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEarDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.saveAndFlush(onsEarDiarioSubsistemaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEarDiarioSubsistemaSearchRepository.save(onsEarDiarioSubsistemaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());

        // Update the onsEarDiarioSubsistema
        OnsEarDiarioSubsistemaEntity updatedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository
            .findById(onsEarDiarioSubsistemaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEarDiarioSubsistemaEntity are not directly saved in db
        em.detach(updatedOnsEarDiarioSubsistemaEntity);
        updatedOnsEarDiarioSubsistemaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .earData(UPDATED_EAR_DATA)
            .earMaxSubsistema(UPDATED_EAR_MAX_SUBSISTEMA)
            .earVerifSubsistemaMwmes(UPDATED_EAR_VERIF_SUBSISTEMA_MWMES)
            .earVerifSubsistemaPercentual(UPDATED_EAR_VERIF_SUBSISTEMA_PERCENTUAL);

        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEarDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEarDiarioSubsistemaEntityToMatchAllProperties(updatedOnsEarDiarioSubsistemaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEarDiarioSubsistemaEntity> onsEarDiarioSubsistemaSearchList = Streamable.of(
                    onsEarDiarioSubsistemaSearchRepository.findAll()
                ).toList();
                OnsEarDiarioSubsistemaEntity testOnsEarDiarioSubsistemaSearch = onsEarDiarioSubsistemaSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsEarDiarioSubsistemaEntityAllPropertiesEquals(
                    testOnsEarDiarioSubsistemaSearch,
                    updatedOnsEarDiarioSubsistemaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEarDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        onsEarDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEarDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEarDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        onsEarDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEarDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        onsEarDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEarDiarioSubsistemaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.saveAndFlush(onsEarDiarioSubsistemaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioSubsistema using partial update
        OnsEarDiarioSubsistemaEntity partialUpdatedOnsEarDiarioSubsistemaEntity = new OnsEarDiarioSubsistemaEntity();
        partialUpdatedOnsEarDiarioSubsistemaEntity.setId(onsEarDiarioSubsistemaEntity.getId());

        partialUpdatedOnsEarDiarioSubsistemaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .earData(UPDATED_EAR_DATA)
            .earMaxSubsistema(UPDATED_EAR_MAX_SUBSISTEMA)
            .earVerifSubsistemaMwmes(UPDATED_EAR_VERIF_SUBSISTEMA_MWMES);

        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioSubsistema in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioSubsistemaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsEarDiarioSubsistemaEntity, onsEarDiarioSubsistemaEntity),
            getPersistedOnsEarDiarioSubsistemaEntity(onsEarDiarioSubsistemaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEarDiarioSubsistemaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.saveAndFlush(onsEarDiarioSubsistemaEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioSubsistema using partial update
        OnsEarDiarioSubsistemaEntity partialUpdatedOnsEarDiarioSubsistemaEntity = new OnsEarDiarioSubsistemaEntity();
        partialUpdatedOnsEarDiarioSubsistemaEntity.setId(onsEarDiarioSubsistemaEntity.getId());

        partialUpdatedOnsEarDiarioSubsistemaEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .earData(UPDATED_EAR_DATA)
            .earMaxSubsistema(UPDATED_EAR_MAX_SUBSISTEMA)
            .earVerifSubsistemaMwmes(UPDATED_EAR_VERIF_SUBSISTEMA_MWMES)
            .earVerifSubsistemaPercentual(UPDATED_EAR_VERIF_SUBSISTEMA_PERCENTUAL);

        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioSubsistema in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioSubsistemaEntityUpdatableFieldsEquals(
            partialUpdatedOnsEarDiarioSubsistemaEntity,
            getPersistedOnsEarDiarioSubsistemaEntity(partialUpdatedOnsEarDiarioSubsistemaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEarDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        onsEarDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEarDiarioSubsistemaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEarDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        onsEarDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEarDiarioSubsistema() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        onsEarDiarioSubsistemaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioSubsistemaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioSubsistemaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioSubsistema in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEarDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.saveAndFlush(onsEarDiarioSubsistemaEntity);
        onsEarDiarioSubsistemaRepository.save(onsEarDiarioSubsistemaEntity);
        onsEarDiarioSubsistemaSearchRepository.save(onsEarDiarioSubsistemaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEarDiarioSubsistema
        restOnsEarDiarioSubsistemaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsEarDiarioSubsistemaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioSubsistemaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEarDiarioSubsistema() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.saveAndFlush(onsEarDiarioSubsistemaEntity);
        onsEarDiarioSubsistemaSearchRepository.save(onsEarDiarioSubsistemaEntity);

        // Search the onsEarDiarioSubsistema
        restOnsEarDiarioSubsistemaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEarDiarioSubsistemaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioSubsistemaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(jsonPath("$.[*].earMaxSubsistema").value(hasItem(DEFAULT_EAR_MAX_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].earVerifSubsistemaMwmes").value(hasItem(DEFAULT_EAR_VERIF_SUBSISTEMA_MWMES)))
            .andExpect(jsonPath("$.[*].earVerifSubsistemaPercentual").value(hasItem(DEFAULT_EAR_VERIF_SUBSISTEMA_PERCENTUAL)));
    }

    protected long getRepositoryCount() {
        return onsEarDiarioSubsistemaRepository.count();
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

    protected OnsEarDiarioSubsistemaEntity getPersistedOnsEarDiarioSubsistemaEntity(OnsEarDiarioSubsistemaEntity onsEarDiarioSubsistema) {
        return onsEarDiarioSubsistemaRepository.findById(onsEarDiarioSubsistema.getId()).orElseThrow();
    }

    protected void assertPersistedOnsEarDiarioSubsistemaEntityToMatchAllProperties(
        OnsEarDiarioSubsistemaEntity expectedOnsEarDiarioSubsistemaEntity
    ) {
        assertOnsEarDiarioSubsistemaEntityAllPropertiesEquals(
            expectedOnsEarDiarioSubsistemaEntity,
            getPersistedOnsEarDiarioSubsistemaEntity(expectedOnsEarDiarioSubsistemaEntity)
        );
    }

    protected void assertPersistedOnsEarDiarioSubsistemaEntityToMatchUpdatableProperties(
        OnsEarDiarioSubsistemaEntity expectedOnsEarDiarioSubsistemaEntity
    ) {
        assertOnsEarDiarioSubsistemaEntityAllUpdatablePropertiesEquals(
            expectedOnsEarDiarioSubsistemaEntity,
            getPersistedOnsEarDiarioSubsistemaEntity(expectedOnsEarDiarioSubsistemaEntity)
        );
    }
}
