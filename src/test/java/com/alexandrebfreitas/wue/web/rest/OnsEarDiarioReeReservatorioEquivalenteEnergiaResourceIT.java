package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioReeReservatorioEquivalenteEnergiaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioReeReservatorioEquivalenteEnergiaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository;
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
 * Integration tests for the {@link OnsEarDiarioReeReservatorioEquivalenteEnergiaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEarDiarioReeReservatorioEquivalenteEnergiaResourceIT {

    private static final String DEFAULT_NOM_REE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_REE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EAR_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EAR_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_EAR_MAX_REE = 1D;
    private static final Double UPDATED_EAR_MAX_REE = 2D;

    private static final Double DEFAULT_EAR_VERIF_REE_MWMES = 1D;
    private static final Double UPDATED_EAR_VERIF_REE_MWMES = 2D;

    private static final Double DEFAULT_EAR_VERIF_REE_PERCENTUAL = 1D;
    private static final Double UPDATED_EAR_VERIF_REE_PERCENTUAL = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ear-diario-ree-reservatorio-equivalente-energias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ear-diario-ree-reservatorio-equivalente-energias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEarDiarioReeReservatorioEquivalenteEnergiaRepository onsEarDiarioReeReservatorioEquivalenteEnergiaRepository;

    @Autowired
    private OnsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc;

    private OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity onsEarDiarioReeReservatorioEquivalenteEnergiaEntity;

    private OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity createEntity() {
        return new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity()
            .nomRee(DEFAULT_NOM_REE)
            .earData(DEFAULT_EAR_DATA)
            .earMaxRee(DEFAULT_EAR_MAX_REE)
            .earVerifReeMwmes(DEFAULT_EAR_VERIF_REE_MWMES)
            .earVerifReePercentual(DEFAULT_EAR_VERIF_REE_PERCENTUAL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity createUpdatedEntity() {
        return new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity()
            .nomRee(UPDATED_NOM_REE)
            .earData(UPDATED_EAR_DATA)
            .earMaxRee(UPDATED_EAR_MAX_REE)
            .earVerifReeMwmes(UPDATED_EAR_VERIF_REE_MWMES)
            .earVerifReePercentual(UPDATED_EAR_VERIF_REE_PERCENTUAL);
    }

    @BeforeEach
    public void initTest() {
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity != null) {
            onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.delete(insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
            onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.delete(
                insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
            );
            insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        // Create the OnsEarDiarioReeReservatorioEquivalenteEnergia
        var returnedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = om.readValue(
            restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.class
        );

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(
            returnedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity(returnedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = returnedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity;
    }

    @Test
    @Transactional
    void createOnsEarDiarioReeReservatorioEquivalenteEnergiaWithExistingId() throws Exception {
        // Create the OnsEarDiarioReeReservatorioEquivalenteEnergia with an existing ID
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEarDiarioReeReservatorioEquivalenteEnergias() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        // Get all the onsEarDiarioReeReservatorioEquivalenteEnergiaList
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomRee").value(hasItem(DEFAULT_NOM_REE)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(jsonPath("$.[*].earMaxRee").value(hasItem(DEFAULT_EAR_MAX_REE)))
            .andExpect(jsonPath("$.[*].earVerifReeMwmes").value(hasItem(DEFAULT_EAR_VERIF_REE_MWMES)))
            .andExpect(jsonPath("$.[*].earVerifReePercentual").value(hasItem(DEFAULT_EAR_VERIF_REE_PERCENTUAL)));
    }

    @Test
    @Transactional
    void getOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        // Get the onsEarDiarioReeReservatorioEquivalenteEnergia
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomRee").value(DEFAULT_NOM_REE))
            .andExpect(jsonPath("$.earData").value(DEFAULT_EAR_DATA.toString()))
            .andExpect(jsonPath("$.earMaxRee").value(DEFAULT_EAR_MAX_REE))
            .andExpect(jsonPath("$.earVerifReeMwmes").value(DEFAULT_EAR_VERIF_REE_MWMES))
            .andExpect(jsonPath("$.earVerifReePercentual").value(DEFAULT_EAR_VERIF_REE_PERCENTUAL));
    }

    @Test
    @Transactional
    void getNonExistingOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Get the onsEarDiarioReeReservatorioEquivalenteEnergia
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.save(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());

        // Update the onsEarDiarioReeReservatorioEquivalenteEnergia
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity =
            onsEarDiarioReeReservatorioEquivalenteEnergiaRepository
                .findById(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity are not directly saved in db
        em.detach(updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
        updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
            .nomRee(UPDATED_NOM_REE)
            .earData(UPDATED_EAR_DATA)
            .earMaxRee(UPDATED_EAR_MAX_REE)
            .earVerifReeMwmes(UPDATED_EAR_VERIF_REE_MWMES)
            .earVerifReePercentual(UPDATED_EAR_VERIF_REE_PERCENTUAL);

        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityToMatchAllProperties(
            updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity> onsEarDiarioReeReservatorioEquivalenteEnergiaSearchList =
                    Streamable.of(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll()).toList();
                OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity testOnsEarDiarioReeReservatorioEquivalenteEnergiaSearch =
                    onsEarDiarioReeReservatorioEquivalenteEnergiaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityAllPropertiesEquals(
                    testOnsEarDiarioReeReservatorioEquivalenteEnergiaSearch,
                    updatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEarDiarioReeReservatorioEquivalenteEnergiaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioReeReservatorioEquivalenteEnergia using partial update
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity =
            new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity();
        partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId()
        );

        partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
            .earData(UPDATED_EAR_DATA)
            .earMaxRee(UPDATED_EAR_MAX_REE)
            .earVerifReeMwmes(UPDATED_EAR_VERIF_REE_MWMES);

        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity,
                onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
            ),
            getPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEarDiarioReeReservatorioEquivalenteEnergiaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioReeReservatorioEquivalenteEnergia using partial update
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity =
            new OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity();
        partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId()
        );

        partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
            .nomRee(UPDATED_NOM_REE)
            .earData(UPDATED_EAR_DATA)
            .earMaxRee(UPDATED_EAR_MAX_REE)
            .earVerifReeMwmes(UPDATED_EAR_VERIF_REE_MWMES)
            .earVerifReePercentual(UPDATED_EAR_VERIF_REE_PERCENTUAL);

        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityUpdatableFieldsEquals(
            partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity(
                partialUpdatedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioReeReservatorioEquivalenteEnergia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.save(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);
        onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.save(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEarDiarioReeReservatorioEquivalenteEnergia
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEarDiarioReeReservatorioEquivalenteEnergia() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity = onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.saveAndFlush(
            onsEarDiarioReeReservatorioEquivalenteEnergiaEntity
        );
        onsEarDiarioReeReservatorioEquivalenteEnergiaSearchRepository.save(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity);

        // Search the onsEarDiarioReeReservatorioEquivalenteEnergia
        restOnsEarDiarioReeReservatorioEquivalenteEnergiaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioReeReservatorioEquivalenteEnergiaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomRee").value(hasItem(DEFAULT_NOM_REE)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(jsonPath("$.[*].earMaxRee").value(hasItem(DEFAULT_EAR_MAX_REE)))
            .andExpect(jsonPath("$.[*].earVerifReeMwmes").value(hasItem(DEFAULT_EAR_VERIF_REE_MWMES)))
            .andExpect(jsonPath("$.[*].earVerifReePercentual").value(hasItem(DEFAULT_EAR_VERIF_REE_PERCENTUAL)));
    }

    protected long getRepositoryCount() {
        return onsEarDiarioReeReservatorioEquivalenteEnergiaRepository.count();
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

    protected OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity getPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity(
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity onsEarDiarioReeReservatorioEquivalenteEnergia
    ) {
        return onsEarDiarioReeReservatorioEquivalenteEnergiaRepository
            .findById(onsEarDiarioReeReservatorioEquivalenteEnergia.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityToMatchAllProperties(
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity expectedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
    ) {
        assertOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityAllPropertiesEquals(
            expectedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity(expectedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity)
        );
    }

    protected void assertPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityToMatchUpdatableProperties(
        OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity expectedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity
    ) {
        assertOnsEarDiarioReeReservatorioEquivalenteEnergiaEntityAllUpdatablePropertiesEquals(
            expectedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity,
            getPersistedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity(expectedOnsEarDiarioReeReservatorioEquivalenteEnergiaEntity)
        );
    }
}
