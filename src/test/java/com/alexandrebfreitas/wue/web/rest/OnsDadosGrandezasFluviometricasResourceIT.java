package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosGrandezasFluviometricasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosGrandezasFluviometricasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosGrandezasFluviometricasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosGrandezasFluviometricasSearchRepository;
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
 * Integration tests for the {@link OnsDadosGrandezasFluviometricasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosGrandezasFluviometricasResourceIT {

    private static final String DEFAULT_ID_POSTOFLUV = "AAAAAAAAAA";
    private static final String UPDATED_ID_POSTOFLUV = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_POSTOFLUVIOMETRICO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_POSTOFLUVIOMETRICO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_LATITUDE = 1D;
    private static final Double UPDATED_VAL_LATITUDE = 2D;

    private static final Double DEFAULT_VAL_LONGITUDE = 1D;
    private static final Double UPDATED_VAL_LONGITUDE = 2D;

    private static final String DEFAULT_NOM_RIO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_RIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_BACIA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_BACIA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_MEDICAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_MEDICAO = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_VAZAOMEDIA = 1D;
    private static final Double UPDATED_VAL_VAZAOMEDIA = 2D;

    private static final Double DEFAULT_VAL_VAZAOMEDIAINCR = 1D;
    private static final Double UPDATED_VAL_VAZAOMEDIAINCR = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-grandezas-fluviometricas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-grandezas-fluviometricas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosGrandezasFluviometricasRepository onsDadosGrandezasFluviometricasRepository;

    @Autowired
    private OnsDadosGrandezasFluviometricasSearchRepository onsDadosGrandezasFluviometricasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosGrandezasFluviometricasMockMvc;

    private OnsDadosGrandezasFluviometricasEntity onsDadosGrandezasFluviometricasEntity;

    private OnsDadosGrandezasFluviometricasEntity insertedOnsDadosGrandezasFluviometricasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosGrandezasFluviometricasEntity createEntity() {
        return new OnsDadosGrandezasFluviometricasEntity()
            .idPostofluv(DEFAULT_ID_POSTOFLUV)
            .nomPostofluviometrico(DEFAULT_NOM_POSTOFLUVIOMETRICO)
            .valLatitude(DEFAULT_VAL_LATITUDE)
            .valLongitude(DEFAULT_VAL_LONGITUDE)
            .nomRio(DEFAULT_NOM_RIO)
            .nomBacia(DEFAULT_NOM_BACIA)
            .dinMedicao(DEFAULT_DIN_MEDICAO)
            .valVazaomedia(DEFAULT_VAL_VAZAOMEDIA)
            .valVazaomediaincr(DEFAULT_VAL_VAZAOMEDIAINCR);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosGrandezasFluviometricasEntity createUpdatedEntity() {
        return new OnsDadosGrandezasFluviometricasEntity()
            .idPostofluv(UPDATED_ID_POSTOFLUV)
            .nomPostofluviometrico(UPDATED_NOM_POSTOFLUVIOMETRICO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .nomRio(UPDATED_NOM_RIO)
            .nomBacia(UPDATED_NOM_BACIA)
            .dinMedicao(UPDATED_DIN_MEDICAO)
            .valVazaomedia(UPDATED_VAL_VAZAOMEDIA)
            .valVazaomediaincr(UPDATED_VAL_VAZAOMEDIAINCR);
    }

    @BeforeEach
    public void initTest() {
        onsDadosGrandezasFluviometricasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosGrandezasFluviometricasEntity != null) {
            onsDadosGrandezasFluviometricasRepository.delete(insertedOnsDadosGrandezasFluviometricasEntity);
            onsDadosGrandezasFluviometricasSearchRepository.delete(insertedOnsDadosGrandezasFluviometricasEntity);
            insertedOnsDadosGrandezasFluviometricasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosGrandezasFluviometricas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        // Create the OnsDadosGrandezasFluviometricas
        var returnedOnsDadosGrandezasFluviometricasEntity = om.readValue(
            restOnsDadosGrandezasFluviometricasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosGrandezasFluviometricasEntity.class
        );

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosGrandezasFluviometricasEntityUpdatableFieldsEquals(
            returnedOnsDadosGrandezasFluviometricasEntity,
            getPersistedOnsDadosGrandezasFluviometricasEntity(returnedOnsDadosGrandezasFluviometricasEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosGrandezasFluviometricasEntity = returnedOnsDadosGrandezasFluviometricasEntity;
    }

    @Test
    @Transactional
    void createOnsDadosGrandezasFluviometricasWithExistingId() throws Exception {
        // Create the OnsDadosGrandezasFluviometricas with an existing ID
        onsDadosGrandezasFluviometricasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosGrandezasFluviometricas() throws Exception {
        // Initialize the database
        insertedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.saveAndFlush(
            onsDadosGrandezasFluviometricasEntity
        );

        // Get all the onsDadosGrandezasFluviometricasList
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosGrandezasFluviometricasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPostofluv").value(hasItem(DEFAULT_ID_POSTOFLUV)))
            .andExpect(jsonPath("$.[*].nomPostofluviometrico").value(hasItem(DEFAULT_NOM_POSTOFLUVIOMETRICO)))
            .andExpect(jsonPath("$.[*].valLatitude").value(hasItem(DEFAULT_VAL_LATITUDE)))
            .andExpect(jsonPath("$.[*].valLongitude").value(hasItem(DEFAULT_VAL_LONGITUDE)))
            .andExpect(jsonPath("$.[*].nomRio").value(hasItem(DEFAULT_NOM_RIO)))
            .andExpect(jsonPath("$.[*].nomBacia").value(hasItem(DEFAULT_NOM_BACIA)))
            .andExpect(jsonPath("$.[*].dinMedicao").value(hasItem(DEFAULT_DIN_MEDICAO.toString())))
            .andExpect(jsonPath("$.[*].valVazaomedia").value(hasItem(DEFAULT_VAL_VAZAOMEDIA)))
            .andExpect(jsonPath("$.[*].valVazaomediaincr").value(hasItem(DEFAULT_VAL_VAZAOMEDIAINCR)));
    }

    @Test
    @Transactional
    void getOnsDadosGrandezasFluviometricas() throws Exception {
        // Initialize the database
        insertedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.saveAndFlush(
            onsDadosGrandezasFluviometricasEntity
        );

        // Get the onsDadosGrandezasFluviometricas
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosGrandezasFluviometricasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosGrandezasFluviometricasEntity.getId().intValue()))
            .andExpect(jsonPath("$.idPostofluv").value(DEFAULT_ID_POSTOFLUV))
            .andExpect(jsonPath("$.nomPostofluviometrico").value(DEFAULT_NOM_POSTOFLUVIOMETRICO))
            .andExpect(jsonPath("$.valLatitude").value(DEFAULT_VAL_LATITUDE))
            .andExpect(jsonPath("$.valLongitude").value(DEFAULT_VAL_LONGITUDE))
            .andExpect(jsonPath("$.nomRio").value(DEFAULT_NOM_RIO))
            .andExpect(jsonPath("$.nomBacia").value(DEFAULT_NOM_BACIA))
            .andExpect(jsonPath("$.dinMedicao").value(DEFAULT_DIN_MEDICAO.toString()))
            .andExpect(jsonPath("$.valVazaomedia").value(DEFAULT_VAL_VAZAOMEDIA))
            .andExpect(jsonPath("$.valVazaomediaincr").value(DEFAULT_VAL_VAZAOMEDIAINCR));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosGrandezasFluviometricas() throws Exception {
        // Get the onsDadosGrandezasFluviometricas
        restOnsDadosGrandezasFluviometricasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosGrandezasFluviometricas() throws Exception {
        // Initialize the database
        insertedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.saveAndFlush(
            onsDadosGrandezasFluviometricasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosGrandezasFluviometricasSearchRepository.save(onsDadosGrandezasFluviometricasEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());

        // Update the onsDadosGrandezasFluviometricas
        OnsDadosGrandezasFluviometricasEntity updatedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository
            .findById(onsDadosGrandezasFluviometricasEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosGrandezasFluviometricasEntity are not directly saved in db
        em.detach(updatedOnsDadosGrandezasFluviometricasEntity);
        updatedOnsDadosGrandezasFluviometricasEntity
            .idPostofluv(UPDATED_ID_POSTOFLUV)
            .nomPostofluviometrico(UPDATED_NOM_POSTOFLUVIOMETRICO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .nomRio(UPDATED_NOM_RIO)
            .nomBacia(UPDATED_NOM_BACIA)
            .dinMedicao(UPDATED_DIN_MEDICAO)
            .valVazaomedia(UPDATED_VAL_VAZAOMEDIA)
            .valVazaomediaincr(UPDATED_VAL_VAZAOMEDIAINCR);

        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosGrandezasFluviometricasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosGrandezasFluviometricasEntityToMatchAllProperties(updatedOnsDadosGrandezasFluviometricasEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosGrandezasFluviometricasEntity> onsDadosGrandezasFluviometricasSearchList = Streamable.of(
                    onsDadosGrandezasFluviometricasSearchRepository.findAll()
                ).toList();
                OnsDadosGrandezasFluviometricasEntity testOnsDadosGrandezasFluviometricasSearch =
                    onsDadosGrandezasFluviometricasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosGrandezasFluviometricasEntityAllPropertiesEquals(
                    testOnsDadosGrandezasFluviometricasSearch,
                    updatedOnsDadosGrandezasFluviometricasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosGrandezasFluviometricas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        onsDadosGrandezasFluviometricasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosGrandezasFluviometricasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosGrandezasFluviometricas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        onsDadosGrandezasFluviometricasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosGrandezasFluviometricas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        onsDadosGrandezasFluviometricasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosGrandezasFluviometricasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.saveAndFlush(
            onsDadosGrandezasFluviometricasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosGrandezasFluviometricas using partial update
        OnsDadosGrandezasFluviometricasEntity partialUpdatedOnsDadosGrandezasFluviometricasEntity =
            new OnsDadosGrandezasFluviometricasEntity();
        partialUpdatedOnsDadosGrandezasFluviometricasEntity.setId(onsDadosGrandezasFluviometricasEntity.getId());

        partialUpdatedOnsDadosGrandezasFluviometricasEntity
            .nomPostofluviometrico(UPDATED_NOM_POSTOFLUVIOMETRICO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .nomRio(UPDATED_NOM_RIO)
            .valVazaomediaincr(UPDATED_VAL_VAZAOMEDIAINCR);

        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosGrandezasFluviometricasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosGrandezasFluviometricas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosGrandezasFluviometricasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsDadosGrandezasFluviometricasEntity, onsDadosGrandezasFluviometricasEntity),
            getPersistedOnsDadosGrandezasFluviometricasEntity(onsDadosGrandezasFluviometricasEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosGrandezasFluviometricasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.saveAndFlush(
            onsDadosGrandezasFluviometricasEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosGrandezasFluviometricas using partial update
        OnsDadosGrandezasFluviometricasEntity partialUpdatedOnsDadosGrandezasFluviometricasEntity =
            new OnsDadosGrandezasFluviometricasEntity();
        partialUpdatedOnsDadosGrandezasFluviometricasEntity.setId(onsDadosGrandezasFluviometricasEntity.getId());

        partialUpdatedOnsDadosGrandezasFluviometricasEntity
            .idPostofluv(UPDATED_ID_POSTOFLUV)
            .nomPostofluviometrico(UPDATED_NOM_POSTOFLUVIOMETRICO)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .nomRio(UPDATED_NOM_RIO)
            .nomBacia(UPDATED_NOM_BACIA)
            .dinMedicao(UPDATED_DIN_MEDICAO)
            .valVazaomedia(UPDATED_VAL_VAZAOMEDIA)
            .valVazaomediaincr(UPDATED_VAL_VAZAOMEDIAINCR);

        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosGrandezasFluviometricasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosGrandezasFluviometricas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosGrandezasFluviometricasEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosGrandezasFluviometricasEntity,
            getPersistedOnsDadosGrandezasFluviometricasEntity(partialUpdatedOnsDadosGrandezasFluviometricasEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosGrandezasFluviometricas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        onsDadosGrandezasFluviometricasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosGrandezasFluviometricasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosGrandezasFluviometricas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        onsDadosGrandezasFluviometricasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosGrandezasFluviometricas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        onsDadosGrandezasFluviometricasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosGrandezasFluviometricasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosGrandezasFluviometricas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosGrandezasFluviometricas() throws Exception {
        // Initialize the database
        insertedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.saveAndFlush(
            onsDadosGrandezasFluviometricasEntity
        );
        onsDadosGrandezasFluviometricasRepository.save(onsDadosGrandezasFluviometricasEntity);
        onsDadosGrandezasFluviometricasSearchRepository.save(onsDadosGrandezasFluviometricasEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosGrandezasFluviometricas
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosGrandezasFluviometricasEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosGrandezasFluviometricasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosGrandezasFluviometricas() throws Exception {
        // Initialize the database
        insertedOnsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.saveAndFlush(
            onsDadosGrandezasFluviometricasEntity
        );
        onsDadosGrandezasFluviometricasSearchRepository.save(onsDadosGrandezasFluviometricasEntity);

        // Search the onsDadosGrandezasFluviometricas
        restOnsDadosGrandezasFluviometricasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosGrandezasFluviometricasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosGrandezasFluviometricasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPostofluv").value(hasItem(DEFAULT_ID_POSTOFLUV)))
            .andExpect(jsonPath("$.[*].nomPostofluviometrico").value(hasItem(DEFAULT_NOM_POSTOFLUVIOMETRICO)))
            .andExpect(jsonPath("$.[*].valLatitude").value(hasItem(DEFAULT_VAL_LATITUDE)))
            .andExpect(jsonPath("$.[*].valLongitude").value(hasItem(DEFAULT_VAL_LONGITUDE)))
            .andExpect(jsonPath("$.[*].nomRio").value(hasItem(DEFAULT_NOM_RIO)))
            .andExpect(jsonPath("$.[*].nomBacia").value(hasItem(DEFAULT_NOM_BACIA)))
            .andExpect(jsonPath("$.[*].dinMedicao").value(hasItem(DEFAULT_DIN_MEDICAO.toString())))
            .andExpect(jsonPath("$.[*].valVazaomedia").value(hasItem(DEFAULT_VAL_VAZAOMEDIA)))
            .andExpect(jsonPath("$.[*].valVazaomediaincr").value(hasItem(DEFAULT_VAL_VAZAOMEDIAINCR)));
    }

    protected long getRepositoryCount() {
        return onsDadosGrandezasFluviometricasRepository.count();
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

    protected OnsDadosGrandezasFluviometricasEntity getPersistedOnsDadosGrandezasFluviometricasEntity(
        OnsDadosGrandezasFluviometricasEntity onsDadosGrandezasFluviometricas
    ) {
        return onsDadosGrandezasFluviometricasRepository.findById(onsDadosGrandezasFluviometricas.getId()).orElseThrow();
    }

    protected void assertPersistedOnsDadosGrandezasFluviometricasEntityToMatchAllProperties(
        OnsDadosGrandezasFluviometricasEntity expectedOnsDadosGrandezasFluviometricasEntity
    ) {
        assertOnsDadosGrandezasFluviometricasEntityAllPropertiesEquals(
            expectedOnsDadosGrandezasFluviometricasEntity,
            getPersistedOnsDadosGrandezasFluviometricasEntity(expectedOnsDadosGrandezasFluviometricasEntity)
        );
    }

    protected void assertPersistedOnsDadosGrandezasFluviometricasEntityToMatchUpdatableProperties(
        OnsDadosGrandezasFluviometricasEntity expectedOnsDadosGrandezasFluviometricasEntity
    ) {
        assertOnsDadosGrandezasFluviometricasEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosGrandezasFluviometricasEntity,
            getPersistedOnsDadosGrandezasFluviometricasEntity(expectedOnsDadosGrandezasFluviometricasEntity)
        );
    }
}
