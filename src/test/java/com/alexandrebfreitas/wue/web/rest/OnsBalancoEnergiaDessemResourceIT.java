package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntity;
import com.alexandrebfreitas.wue.repository.OnsBalancoEnergiaDessemRepository;
import com.alexandrebfreitas.wue.repository.search.OnsBalancoEnergiaDessemSearchRepository;
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
 * Integration tests for the {@link OnsBalancoEnergiaDessemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsBalancoEnergiaDessemResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_DEMANDA = 1D;
    private static final Double UPDATED_VAL_DEMANDA = 2D;

    private static final Double DEFAULT_VAL_GERACAOHIDRAULICAMWMED = 1D;
    private static final Double UPDATED_VAL_GERACAOHIDRAULICAMWMED = 2D;

    private static final Double DEFAULT_VAL_GERACAOPCHMWMED = 1D;
    private static final Double UPDATED_VAL_GERACAOPCHMWMED = 2D;

    private static final Double DEFAULT_VAL_GERACAOTERMICAMWED = 1D;
    private static final Double UPDATED_VAL_GERACAOTERMICAMWED = 2D;

    private static final Double DEFAULT_VAL_GERACAOPCTMWMED = 1D;
    private static final Double UPDATED_VAL_GERACAOPCTMWMED = 2D;

    private static final Double DEFAULT_VAL_GERACAOEOLICAMWMED = 1D;
    private static final Double UPDATED_VAL_GERACAOEOLICAMWMED = 2D;

    private static final Double DEFAULT_VAL_GERACAOFOTOVOLTAICAMWMED = 1D;
    private static final Double UPDATED_VAL_GERACAOFOTOVOLTAICAMWMED = 2D;

    private static final String ENTITY_API_URL = "/api/ons-balanco-energia-dessems";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-balanco-energia-dessems/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsBalancoEnergiaDessemRepository onsBalancoEnergiaDessemRepository;

    @Autowired
    private OnsBalancoEnergiaDessemSearchRepository onsBalancoEnergiaDessemSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsBalancoEnergiaDessemMockMvc;

    private OnsBalancoEnergiaDessemEntity onsBalancoEnergiaDessemEntity;

    private OnsBalancoEnergiaDessemEntity insertedOnsBalancoEnergiaDessemEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsBalancoEnergiaDessemEntity createEntity() {
        return new OnsBalancoEnergiaDessemEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valDemanda(DEFAULT_VAL_DEMANDA)
            .valGeracaohidraulicamwmed(DEFAULT_VAL_GERACAOHIDRAULICAMWMED)
            .valGeracaopchmwmed(DEFAULT_VAL_GERACAOPCHMWMED)
            .valGeracaotermicamwed(DEFAULT_VAL_GERACAOTERMICAMWED)
            .valGeracaopctmwmed(DEFAULT_VAL_GERACAOPCTMWMED)
            .valGeracaoeolicamwmed(DEFAULT_VAL_GERACAOEOLICAMWMED)
            .valGeracaofotovoltaicamwmed(DEFAULT_VAL_GERACAOFOTOVOLTAICAMWMED);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsBalancoEnergiaDessemEntity createUpdatedEntity() {
        return new OnsBalancoEnergiaDessemEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valDemanda(UPDATED_VAL_DEMANDA)
            .valGeracaohidraulicamwmed(UPDATED_VAL_GERACAOHIDRAULICAMWMED)
            .valGeracaopchmwmed(UPDATED_VAL_GERACAOPCHMWMED)
            .valGeracaotermicamwed(UPDATED_VAL_GERACAOTERMICAMWED)
            .valGeracaopctmwmed(UPDATED_VAL_GERACAOPCTMWMED)
            .valGeracaoeolicamwmed(UPDATED_VAL_GERACAOEOLICAMWMED)
            .valGeracaofotovoltaicamwmed(UPDATED_VAL_GERACAOFOTOVOLTAICAMWMED);
    }

    @BeforeEach
    public void initTest() {
        onsBalancoEnergiaDessemEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsBalancoEnergiaDessemEntity != null) {
            onsBalancoEnergiaDessemRepository.delete(insertedOnsBalancoEnergiaDessemEntity);
            onsBalancoEnergiaDessemSearchRepository.delete(insertedOnsBalancoEnergiaDessemEntity);
            insertedOnsBalancoEnergiaDessemEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsBalancoEnergiaDessem() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        // Create the OnsBalancoEnergiaDessem
        var returnedOnsBalancoEnergiaDessemEntity = om.readValue(
            restOnsBalancoEnergiaDessemMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsBalancoEnergiaDessemEntity.class
        );

        // Validate the OnsBalancoEnergiaDessem in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsBalancoEnergiaDessemEntityUpdatableFieldsEquals(
            returnedOnsBalancoEnergiaDessemEntity,
            getPersistedOnsBalancoEnergiaDessemEntity(returnedOnsBalancoEnergiaDessemEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsBalancoEnergiaDessemEntity = returnedOnsBalancoEnergiaDessemEntity;
    }

    @Test
    @Transactional
    void createOnsBalancoEnergiaDessemWithExistingId() throws Exception {
        // Create the OnsBalancoEnergiaDessem with an existing ID
        onsBalancoEnergiaDessemEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsBalancoEnergiaDessems() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.saveAndFlush(onsBalancoEnergiaDessemEntity);

        // Get all the onsBalancoEnergiaDessemList
        restOnsBalancoEnergiaDessemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsBalancoEnergiaDessemEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valDemanda").value(hasItem(DEFAULT_VAL_DEMANDA)))
            .andExpect(jsonPath("$.[*].valGeracaohidraulicamwmed").value(hasItem(DEFAULT_VAL_GERACAOHIDRAULICAMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaopchmwmed").value(hasItem(DEFAULT_VAL_GERACAOPCHMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaotermicamwed").value(hasItem(DEFAULT_VAL_GERACAOTERMICAMWED)))
            .andExpect(jsonPath("$.[*].valGeracaopctmwmed").value(hasItem(DEFAULT_VAL_GERACAOPCTMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaoeolicamwmed").value(hasItem(DEFAULT_VAL_GERACAOEOLICAMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaofotovoltaicamwmed").value(hasItem(DEFAULT_VAL_GERACAOFOTOVOLTAICAMWMED)));
    }

    @Test
    @Transactional
    void getOnsBalancoEnergiaDessem() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.saveAndFlush(onsBalancoEnergiaDessemEntity);

        // Get the onsBalancoEnergiaDessem
        restOnsBalancoEnergiaDessemMockMvc
            .perform(get(ENTITY_API_URL_ID, onsBalancoEnergiaDessemEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsBalancoEnergiaDessemEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valDemanda").value(DEFAULT_VAL_DEMANDA))
            .andExpect(jsonPath("$.valGeracaohidraulicamwmed").value(DEFAULT_VAL_GERACAOHIDRAULICAMWMED))
            .andExpect(jsonPath("$.valGeracaopchmwmed").value(DEFAULT_VAL_GERACAOPCHMWMED))
            .andExpect(jsonPath("$.valGeracaotermicamwed").value(DEFAULT_VAL_GERACAOTERMICAMWED))
            .andExpect(jsonPath("$.valGeracaopctmwmed").value(DEFAULT_VAL_GERACAOPCTMWMED))
            .andExpect(jsonPath("$.valGeracaoeolicamwmed").value(DEFAULT_VAL_GERACAOEOLICAMWMED))
            .andExpect(jsonPath("$.valGeracaofotovoltaicamwmed").value(DEFAULT_VAL_GERACAOFOTOVOLTAICAMWMED));
    }

    @Test
    @Transactional
    void getNonExistingOnsBalancoEnergiaDessem() throws Exception {
        // Get the onsBalancoEnergiaDessem
        restOnsBalancoEnergiaDessemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsBalancoEnergiaDessem() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.saveAndFlush(onsBalancoEnergiaDessemEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsBalancoEnergiaDessemSearchRepository.save(onsBalancoEnergiaDessemEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());

        // Update the onsBalancoEnergiaDessem
        OnsBalancoEnergiaDessemEntity updatedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository
            .findById(onsBalancoEnergiaDessemEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsBalancoEnergiaDessemEntity are not directly saved in db
        em.detach(updatedOnsBalancoEnergiaDessemEntity);
        updatedOnsBalancoEnergiaDessemEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valDemanda(UPDATED_VAL_DEMANDA)
            .valGeracaohidraulicamwmed(UPDATED_VAL_GERACAOHIDRAULICAMWMED)
            .valGeracaopchmwmed(UPDATED_VAL_GERACAOPCHMWMED)
            .valGeracaotermicamwed(UPDATED_VAL_GERACAOTERMICAMWED)
            .valGeracaopctmwmed(UPDATED_VAL_GERACAOPCTMWMED)
            .valGeracaoeolicamwmed(UPDATED_VAL_GERACAOEOLICAMWMED)
            .valGeracaofotovoltaicamwmed(UPDATED_VAL_GERACAOFOTOVOLTAICAMWMED);

        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsBalancoEnergiaDessemEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsBalancoEnergiaDessemEntityToMatchAllProperties(updatedOnsBalancoEnergiaDessemEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsBalancoEnergiaDessemEntity> onsBalancoEnergiaDessemSearchList = Streamable.of(
                    onsBalancoEnergiaDessemSearchRepository.findAll()
                ).toList();
                OnsBalancoEnergiaDessemEntity testOnsBalancoEnergiaDessemSearch = onsBalancoEnergiaDessemSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsBalancoEnergiaDessemEntityAllPropertiesEquals(
                    testOnsBalancoEnergiaDessemSearch,
                    updatedOnsBalancoEnergiaDessemEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsBalancoEnergiaDessem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        onsBalancoEnergiaDessemEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsBalancoEnergiaDessemEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsBalancoEnergiaDessem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        onsBalancoEnergiaDessemEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsBalancoEnergiaDessem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        onsBalancoEnergiaDessemEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsBalancoEnergiaDessemWithPatch() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.saveAndFlush(onsBalancoEnergiaDessemEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsBalancoEnergiaDessem using partial update
        OnsBalancoEnergiaDessemEntity partialUpdatedOnsBalancoEnergiaDessemEntity = new OnsBalancoEnergiaDessemEntity();
        partialUpdatedOnsBalancoEnergiaDessemEntity.setId(onsBalancoEnergiaDessemEntity.getId());

        partialUpdatedOnsBalancoEnergiaDessemEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .valDemanda(UPDATED_VAL_DEMANDA)
            .valGeracaohidraulicamwmed(UPDATED_VAL_GERACAOHIDRAULICAMWMED)
            .valGeracaotermicamwed(UPDATED_VAL_GERACAOTERMICAMWED)
            .valGeracaopctmwmed(UPDATED_VAL_GERACAOPCTMWMED)
            .valGeracaoeolicamwmed(UPDATED_VAL_GERACAOEOLICAMWMED);

        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsBalancoEnergiaDessemEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsBalancoEnergiaDessem in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsBalancoEnergiaDessemEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsBalancoEnergiaDessemEntity, onsBalancoEnergiaDessemEntity),
            getPersistedOnsBalancoEnergiaDessemEntity(onsBalancoEnergiaDessemEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsBalancoEnergiaDessemWithPatch() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.saveAndFlush(onsBalancoEnergiaDessemEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsBalancoEnergiaDessem using partial update
        OnsBalancoEnergiaDessemEntity partialUpdatedOnsBalancoEnergiaDessemEntity = new OnsBalancoEnergiaDessemEntity();
        partialUpdatedOnsBalancoEnergiaDessemEntity.setId(onsBalancoEnergiaDessemEntity.getId());

        partialUpdatedOnsBalancoEnergiaDessemEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valDemanda(UPDATED_VAL_DEMANDA)
            .valGeracaohidraulicamwmed(UPDATED_VAL_GERACAOHIDRAULICAMWMED)
            .valGeracaopchmwmed(UPDATED_VAL_GERACAOPCHMWMED)
            .valGeracaotermicamwed(UPDATED_VAL_GERACAOTERMICAMWED)
            .valGeracaopctmwmed(UPDATED_VAL_GERACAOPCTMWMED)
            .valGeracaoeolicamwmed(UPDATED_VAL_GERACAOEOLICAMWMED)
            .valGeracaofotovoltaicamwmed(UPDATED_VAL_GERACAOFOTOVOLTAICAMWMED);

        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsBalancoEnergiaDessemEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsBalancoEnergiaDessem in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsBalancoEnergiaDessemEntityUpdatableFieldsEquals(
            partialUpdatedOnsBalancoEnergiaDessemEntity,
            getPersistedOnsBalancoEnergiaDessemEntity(partialUpdatedOnsBalancoEnergiaDessemEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsBalancoEnergiaDessem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        onsBalancoEnergiaDessemEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsBalancoEnergiaDessemEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsBalancoEnergiaDessem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        onsBalancoEnergiaDessemEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsBalancoEnergiaDessem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        onsBalancoEnergiaDessemEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsBalancoEnergiaDessemMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsBalancoEnergiaDessemEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsBalancoEnergiaDessem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsBalancoEnergiaDessem() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.saveAndFlush(onsBalancoEnergiaDessemEntity);
        onsBalancoEnergiaDessemRepository.save(onsBalancoEnergiaDessemEntity);
        onsBalancoEnergiaDessemSearchRepository.save(onsBalancoEnergiaDessemEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsBalancoEnergiaDessem
        restOnsBalancoEnergiaDessemMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsBalancoEnergiaDessemEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsBalancoEnergiaDessemSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsBalancoEnergiaDessem() throws Exception {
        // Initialize the database
        insertedOnsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.saveAndFlush(onsBalancoEnergiaDessemEntity);
        onsBalancoEnergiaDessemSearchRepository.save(onsBalancoEnergiaDessemEntity);

        // Search the onsBalancoEnergiaDessem
        restOnsBalancoEnergiaDessemMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsBalancoEnergiaDessemEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsBalancoEnergiaDessemEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valDemanda").value(hasItem(DEFAULT_VAL_DEMANDA)))
            .andExpect(jsonPath("$.[*].valGeracaohidraulicamwmed").value(hasItem(DEFAULT_VAL_GERACAOHIDRAULICAMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaopchmwmed").value(hasItem(DEFAULT_VAL_GERACAOPCHMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaotermicamwed").value(hasItem(DEFAULT_VAL_GERACAOTERMICAMWED)))
            .andExpect(jsonPath("$.[*].valGeracaopctmwmed").value(hasItem(DEFAULT_VAL_GERACAOPCTMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaoeolicamwmed").value(hasItem(DEFAULT_VAL_GERACAOEOLICAMWMED)))
            .andExpect(jsonPath("$.[*].valGeracaofotovoltaicamwmed").value(hasItem(DEFAULT_VAL_GERACAOFOTOVOLTAICAMWMED)));
    }

    protected long getRepositoryCount() {
        return onsBalancoEnergiaDessemRepository.count();
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

    protected OnsBalancoEnergiaDessemEntity getPersistedOnsBalancoEnergiaDessemEntity(
        OnsBalancoEnergiaDessemEntity onsBalancoEnergiaDessem
    ) {
        return onsBalancoEnergiaDessemRepository.findById(onsBalancoEnergiaDessem.getId()).orElseThrow();
    }

    protected void assertPersistedOnsBalancoEnergiaDessemEntityToMatchAllProperties(
        OnsBalancoEnergiaDessemEntity expectedOnsBalancoEnergiaDessemEntity
    ) {
        assertOnsBalancoEnergiaDessemEntityAllPropertiesEquals(
            expectedOnsBalancoEnergiaDessemEntity,
            getPersistedOnsBalancoEnergiaDessemEntity(expectedOnsBalancoEnergiaDessemEntity)
        );
    }

    protected void assertPersistedOnsBalancoEnergiaDessemEntityToMatchUpdatableProperties(
        OnsBalancoEnergiaDessemEntity expectedOnsBalancoEnergiaDessemEntity
    ) {
        assertOnsBalancoEnergiaDessemEntityAllUpdatablePropertiesEquals(
            expectedOnsBalancoEnergiaDessemEntity,
            getPersistedOnsBalancoEnergiaDessemEntity(expectedOnsBalancoEnergiaDessemEntity)
        );
    }
}
