package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntity;
import com.alexandrebfreitas.wue.repository.OnsCmoSemanalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCmoSemanalSearchRepository;
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
 * Integration tests for the {@link OnsCmoSemanalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCmoSemanalResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_CMOMEDIASEMANAL = 1D;
    private static final Double UPDATED_VAL_CMOMEDIASEMANAL = 2D;

    private static final Double DEFAULT_VAL_CMOLEVE = 1D;
    private static final Double UPDATED_VAL_CMOLEVE = 2D;

    private static final Double DEFAULT_VAL_CMOMEDIA = 1D;
    private static final Double UPDATED_VAL_CMOMEDIA = 2D;

    private static final Double DEFAULT_VAL_CMOPESADA = 1D;
    private static final Double UPDATED_VAL_CMOPESADA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-cmo-semanals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-cmo-semanals/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCmoSemanalRepository onsCmoSemanalRepository;

    @Autowired
    private OnsCmoSemanalSearchRepository onsCmoSemanalSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCmoSemanalMockMvc;

    private OnsCmoSemanalEntity onsCmoSemanalEntity;

    private OnsCmoSemanalEntity insertedOnsCmoSemanalEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCmoSemanalEntity createEntity() {
        return new OnsCmoSemanalEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valCmomediasemanal(DEFAULT_VAL_CMOMEDIASEMANAL)
            .valCmoleve(DEFAULT_VAL_CMOLEVE)
            .valCmomedia(DEFAULT_VAL_CMOMEDIA)
            .valCmopesada(DEFAULT_VAL_CMOPESADA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCmoSemanalEntity createUpdatedEntity() {
        return new OnsCmoSemanalEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCmomediasemanal(UPDATED_VAL_CMOMEDIASEMANAL)
            .valCmoleve(UPDATED_VAL_CMOLEVE)
            .valCmomedia(UPDATED_VAL_CMOMEDIA)
            .valCmopesada(UPDATED_VAL_CMOPESADA);
    }

    @BeforeEach
    public void initTest() {
        onsCmoSemanalEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCmoSemanalEntity != null) {
            onsCmoSemanalRepository.delete(insertedOnsCmoSemanalEntity);
            onsCmoSemanalSearchRepository.delete(insertedOnsCmoSemanalEntity);
            insertedOnsCmoSemanalEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCmoSemanal() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        // Create the OnsCmoSemanal
        var returnedOnsCmoSemanalEntity = om.readValue(
            restOnsCmoSemanalMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCmoSemanalEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCmoSemanalEntity.class
        );

        // Validate the OnsCmoSemanal in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCmoSemanalEntityUpdatableFieldsEquals(
            returnedOnsCmoSemanalEntity,
            getPersistedOnsCmoSemanalEntity(returnedOnsCmoSemanalEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCmoSemanalEntity = returnedOnsCmoSemanalEntity;
    }

    @Test
    @Transactional
    void createOnsCmoSemanalWithExistingId() throws Exception {
        // Create the OnsCmoSemanal with an existing ID
        onsCmoSemanalEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCmoSemanalMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(onsCmoSemanalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCmoSemanals() throws Exception {
        // Initialize the database
        insertedOnsCmoSemanalEntity = onsCmoSemanalRepository.saveAndFlush(onsCmoSemanalEntity);

        // Get all the onsCmoSemanalList
        restOnsCmoSemanalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCmoSemanalEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCmomediasemanal").value(hasItem(DEFAULT_VAL_CMOMEDIASEMANAL)))
            .andExpect(jsonPath("$.[*].valCmoleve").value(hasItem(DEFAULT_VAL_CMOLEVE)))
            .andExpect(jsonPath("$.[*].valCmomedia").value(hasItem(DEFAULT_VAL_CMOMEDIA)))
            .andExpect(jsonPath("$.[*].valCmopesada").value(hasItem(DEFAULT_VAL_CMOPESADA)));
    }

    @Test
    @Transactional
    void getOnsCmoSemanal() throws Exception {
        // Initialize the database
        insertedOnsCmoSemanalEntity = onsCmoSemanalRepository.saveAndFlush(onsCmoSemanalEntity);

        // Get the onsCmoSemanal
        restOnsCmoSemanalMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCmoSemanalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCmoSemanalEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valCmomediasemanal").value(DEFAULT_VAL_CMOMEDIASEMANAL))
            .andExpect(jsonPath("$.valCmoleve").value(DEFAULT_VAL_CMOLEVE))
            .andExpect(jsonPath("$.valCmomedia").value(DEFAULT_VAL_CMOMEDIA))
            .andExpect(jsonPath("$.valCmopesada").value(DEFAULT_VAL_CMOPESADA));
    }

    @Test
    @Transactional
    void getNonExistingOnsCmoSemanal() throws Exception {
        // Get the onsCmoSemanal
        restOnsCmoSemanalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCmoSemanal() throws Exception {
        // Initialize the database
        insertedOnsCmoSemanalEntity = onsCmoSemanalRepository.saveAndFlush(onsCmoSemanalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCmoSemanalSearchRepository.save(onsCmoSemanalEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());

        // Update the onsCmoSemanal
        OnsCmoSemanalEntity updatedOnsCmoSemanalEntity = onsCmoSemanalRepository.findById(onsCmoSemanalEntity.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCmoSemanalEntity are not directly saved in db
        em.detach(updatedOnsCmoSemanalEntity);
        updatedOnsCmoSemanalEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCmomediasemanal(UPDATED_VAL_CMOMEDIASEMANAL)
            .valCmoleve(UPDATED_VAL_CMOLEVE)
            .valCmomedia(UPDATED_VAL_CMOMEDIA)
            .valCmopesada(UPDATED_VAL_CMOPESADA);

        restOnsCmoSemanalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCmoSemanalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCmoSemanalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCmoSemanalEntityToMatchAllProperties(updatedOnsCmoSemanalEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCmoSemanalEntity> onsCmoSemanalSearchList = Streamable.of(onsCmoSemanalSearchRepository.findAll()).toList();
                OnsCmoSemanalEntity testOnsCmoSemanalSearch = onsCmoSemanalSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsCmoSemanalEntityAllPropertiesEquals(testOnsCmoSemanalSearch, updatedOnsCmoSemanalEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCmoSemanal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        onsCmoSemanalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCmoSemanalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCmoSemanalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCmoSemanalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCmoSemanal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        onsCmoSemanalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemanalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCmoSemanalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCmoSemanal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        onsCmoSemanalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemanalMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(onsCmoSemanalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCmoSemanalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCmoSemanalEntity = onsCmoSemanalRepository.saveAndFlush(onsCmoSemanalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCmoSemanal using partial update
        OnsCmoSemanalEntity partialUpdatedOnsCmoSemanalEntity = new OnsCmoSemanalEntity();
        partialUpdatedOnsCmoSemanalEntity.setId(onsCmoSemanalEntity.getId());

        partialUpdatedOnsCmoSemanalEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCmomediasemanal(UPDATED_VAL_CMOMEDIASEMANAL)
            .valCmomedia(UPDATED_VAL_CMOMEDIA)
            .valCmopesada(UPDATED_VAL_CMOPESADA);

        restOnsCmoSemanalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCmoSemanalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCmoSemanalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCmoSemanal in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCmoSemanalEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCmoSemanalEntity, onsCmoSemanalEntity),
            getPersistedOnsCmoSemanalEntity(onsCmoSemanalEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCmoSemanalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCmoSemanalEntity = onsCmoSemanalRepository.saveAndFlush(onsCmoSemanalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCmoSemanal using partial update
        OnsCmoSemanalEntity partialUpdatedOnsCmoSemanalEntity = new OnsCmoSemanalEntity();
        partialUpdatedOnsCmoSemanalEntity.setId(onsCmoSemanalEntity.getId());

        partialUpdatedOnsCmoSemanalEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valCmomediasemanal(UPDATED_VAL_CMOMEDIASEMANAL)
            .valCmoleve(UPDATED_VAL_CMOLEVE)
            .valCmomedia(UPDATED_VAL_CMOMEDIA)
            .valCmopesada(UPDATED_VAL_CMOPESADA);

        restOnsCmoSemanalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCmoSemanalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCmoSemanalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCmoSemanal in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCmoSemanalEntityUpdatableFieldsEquals(
            partialUpdatedOnsCmoSemanalEntity,
            getPersistedOnsCmoSemanalEntity(partialUpdatedOnsCmoSemanalEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCmoSemanal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        onsCmoSemanalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCmoSemanalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCmoSemanalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCmoSemanalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCmoSemanal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        onsCmoSemanalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemanalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCmoSemanalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCmoSemanal() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        onsCmoSemanalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCmoSemanalMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCmoSemanalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCmoSemanal in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCmoSemanal() throws Exception {
        // Initialize the database
        insertedOnsCmoSemanalEntity = onsCmoSemanalRepository.saveAndFlush(onsCmoSemanalEntity);
        onsCmoSemanalRepository.save(onsCmoSemanalEntity);
        onsCmoSemanalSearchRepository.save(onsCmoSemanalEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCmoSemanal
        restOnsCmoSemanalMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCmoSemanalEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCmoSemanalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCmoSemanal() throws Exception {
        // Initialize the database
        insertedOnsCmoSemanalEntity = onsCmoSemanalRepository.saveAndFlush(onsCmoSemanalEntity);
        onsCmoSemanalSearchRepository.save(onsCmoSemanalEntity);

        // Search the onsCmoSemanal
        restOnsCmoSemanalMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCmoSemanalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCmoSemanalEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valCmomediasemanal").value(hasItem(DEFAULT_VAL_CMOMEDIASEMANAL)))
            .andExpect(jsonPath("$.[*].valCmoleve").value(hasItem(DEFAULT_VAL_CMOLEVE)))
            .andExpect(jsonPath("$.[*].valCmomedia").value(hasItem(DEFAULT_VAL_CMOMEDIA)))
            .andExpect(jsonPath("$.[*].valCmopesada").value(hasItem(DEFAULT_VAL_CMOPESADA)));
    }

    protected long getRepositoryCount() {
        return onsCmoSemanalRepository.count();
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

    protected OnsCmoSemanalEntity getPersistedOnsCmoSemanalEntity(OnsCmoSemanalEntity onsCmoSemanal) {
        return onsCmoSemanalRepository.findById(onsCmoSemanal.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCmoSemanalEntityToMatchAllProperties(OnsCmoSemanalEntity expectedOnsCmoSemanalEntity) {
        assertOnsCmoSemanalEntityAllPropertiesEquals(
            expectedOnsCmoSemanalEntity,
            getPersistedOnsCmoSemanalEntity(expectedOnsCmoSemanalEntity)
        );
    }

    protected void assertPersistedOnsCmoSemanalEntityToMatchUpdatableProperties(OnsCmoSemanalEntity expectedOnsCmoSemanalEntity) {
        assertOnsCmoSemanalEntityAllUpdatablePropertiesEquals(
            expectedOnsCmoSemanalEntity,
            getPersistedOnsCmoSemanalEntity(expectedOnsCmoSemanalEntity)
        );
    }
}
