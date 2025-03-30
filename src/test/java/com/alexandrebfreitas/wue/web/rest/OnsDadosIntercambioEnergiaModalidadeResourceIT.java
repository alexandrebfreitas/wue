package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosIntercambioEnergiaModalidadeEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosIntercambioEnergiaModalidadeEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIntercambioEnergiaModalidadeRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIntercambioEnergiaModalidadeSearchRepository;
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
 * Integration tests for the {@link OnsDadosIntercambioEnergiaModalidadeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosIntercambioEnergiaModalidadeResourceIT {

    private static final String DEFAULT_NOM_CONVERSORA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_CONVERSORA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_MODALIDADECONTRATUAL = 1D;
    private static final Double UPDATED_VAL_MODALIDADECONTRATUAL = 2D;

    private static final Double DEFAULT_VAL_MODALIDADEEMERGENCIAL = 1D;
    private static final Double UPDATED_VAL_MODALIDADEEMERGENCIAL = 2D;

    private static final Double DEFAULT_VAL_MODALIDADEOPORTUNIDADE = 1D;
    private static final Double UPDATED_VAL_MODALIDADEOPORTUNIDADE = 2D;

    private static final Double DEFAULT_VAL_MODALIDADETESTE = 1D;
    private static final Double UPDATED_VAL_MODALIDADETESTE = 2D;

    private static final Double DEFAULT_VAL_MODALIDADEEXCEPCIONAL = 1D;
    private static final Double UPDATED_VAL_MODALIDADEEXCEPCIONAL = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-intercambio-energia-modalidades";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-intercambio-energia-modalidades/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosIntercambioEnergiaModalidadeRepository onsDadosIntercambioEnergiaModalidadeRepository;

    @Autowired
    private OnsDadosIntercambioEnergiaModalidadeSearchRepository onsDadosIntercambioEnergiaModalidadeSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosIntercambioEnergiaModalidadeMockMvc;

    private OnsDadosIntercambioEnergiaModalidadeEntity onsDadosIntercambioEnergiaModalidadeEntity;

    private OnsDadosIntercambioEnergiaModalidadeEntity insertedOnsDadosIntercambioEnergiaModalidadeEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIntercambioEnergiaModalidadeEntity createEntity() {
        return new OnsDadosIntercambioEnergiaModalidadeEntity()
            .nomConversora(DEFAULT_NOM_CONVERSORA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valModalidadecontratual(DEFAULT_VAL_MODALIDADECONTRATUAL)
            .valModalidadeemergencial(DEFAULT_VAL_MODALIDADEEMERGENCIAL)
            .valModalidadeoportunidade(DEFAULT_VAL_MODALIDADEOPORTUNIDADE)
            .valModalidadeteste(DEFAULT_VAL_MODALIDADETESTE)
            .valModalidadeexcepcional(DEFAULT_VAL_MODALIDADEEXCEPCIONAL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIntercambioEnergiaModalidadeEntity createUpdatedEntity() {
        return new OnsDadosIntercambioEnergiaModalidadeEntity()
            .nomConversora(UPDATED_NOM_CONVERSORA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valModalidadecontratual(UPDATED_VAL_MODALIDADECONTRATUAL)
            .valModalidadeemergencial(UPDATED_VAL_MODALIDADEEMERGENCIAL)
            .valModalidadeoportunidade(UPDATED_VAL_MODALIDADEOPORTUNIDADE)
            .valModalidadeteste(UPDATED_VAL_MODALIDADETESTE)
            .valModalidadeexcepcional(UPDATED_VAL_MODALIDADEEXCEPCIONAL);
    }

    @BeforeEach
    public void initTest() {
        onsDadosIntercambioEnergiaModalidadeEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosIntercambioEnergiaModalidadeEntity != null) {
            onsDadosIntercambioEnergiaModalidadeRepository.delete(insertedOnsDadosIntercambioEnergiaModalidadeEntity);
            onsDadosIntercambioEnergiaModalidadeSearchRepository.delete(insertedOnsDadosIntercambioEnergiaModalidadeEntity);
            insertedOnsDadosIntercambioEnergiaModalidadeEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosIntercambioEnergiaModalidade() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        // Create the OnsDadosIntercambioEnergiaModalidade
        var returnedOnsDadosIntercambioEnergiaModalidadeEntity = om.readValue(
            restOnsDadosIntercambioEnergiaModalidadeMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosIntercambioEnergiaModalidadeEntity.class
        );

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosIntercambioEnergiaModalidadeEntityUpdatableFieldsEquals(
            returnedOnsDadosIntercambioEnergiaModalidadeEntity,
            getPersistedOnsDadosIntercambioEnergiaModalidadeEntity(returnedOnsDadosIntercambioEnergiaModalidadeEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosIntercambioEnergiaModalidadeEntity = returnedOnsDadosIntercambioEnergiaModalidadeEntity;
    }

    @Test
    @Transactional
    void createOnsDadosIntercambioEnergiaModalidadeWithExistingId() throws Exception {
        // Create the OnsDadosIntercambioEnergiaModalidade with an existing ID
        onsDadosIntercambioEnergiaModalidadeEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosIntercambioEnergiaModalidades() throws Exception {
        // Initialize the database
        insertedOnsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.saveAndFlush(
            onsDadosIntercambioEnergiaModalidadeEntity
        );

        // Get all the onsDadosIntercambioEnergiaModalidadeList
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosIntercambioEnergiaModalidadeEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomConversora").value(hasItem(DEFAULT_NOM_CONVERSORA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valModalidadecontratual").value(hasItem(DEFAULT_VAL_MODALIDADECONTRATUAL)))
            .andExpect(jsonPath("$.[*].valModalidadeemergencial").value(hasItem(DEFAULT_VAL_MODALIDADEEMERGENCIAL)))
            .andExpect(jsonPath("$.[*].valModalidadeoportunidade").value(hasItem(DEFAULT_VAL_MODALIDADEOPORTUNIDADE)))
            .andExpect(jsonPath("$.[*].valModalidadeteste").value(hasItem(DEFAULT_VAL_MODALIDADETESTE)))
            .andExpect(jsonPath("$.[*].valModalidadeexcepcional").value(hasItem(DEFAULT_VAL_MODALIDADEEXCEPCIONAL)));
    }

    @Test
    @Transactional
    void getOnsDadosIntercambioEnergiaModalidade() throws Exception {
        // Initialize the database
        insertedOnsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.saveAndFlush(
            onsDadosIntercambioEnergiaModalidadeEntity
        );

        // Get the onsDadosIntercambioEnergiaModalidade
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosIntercambioEnergiaModalidadeEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosIntercambioEnergiaModalidadeEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomConversora").value(DEFAULT_NOM_CONVERSORA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valModalidadecontratual").value(DEFAULT_VAL_MODALIDADECONTRATUAL))
            .andExpect(jsonPath("$.valModalidadeemergencial").value(DEFAULT_VAL_MODALIDADEEMERGENCIAL))
            .andExpect(jsonPath("$.valModalidadeoportunidade").value(DEFAULT_VAL_MODALIDADEOPORTUNIDADE))
            .andExpect(jsonPath("$.valModalidadeteste").value(DEFAULT_VAL_MODALIDADETESTE))
            .andExpect(jsonPath("$.valModalidadeexcepcional").value(DEFAULT_VAL_MODALIDADEEXCEPCIONAL));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosIntercambioEnergiaModalidade() throws Exception {
        // Get the onsDadosIntercambioEnergiaModalidade
        restOnsDadosIntercambioEnergiaModalidadeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosIntercambioEnergiaModalidade() throws Exception {
        // Initialize the database
        insertedOnsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.saveAndFlush(
            onsDadosIntercambioEnergiaModalidadeEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosIntercambioEnergiaModalidadeSearchRepository.save(onsDadosIntercambioEnergiaModalidadeEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());

        // Update the onsDadosIntercambioEnergiaModalidade
        OnsDadosIntercambioEnergiaModalidadeEntity updatedOnsDadosIntercambioEnergiaModalidadeEntity =
            onsDadosIntercambioEnergiaModalidadeRepository.findById(onsDadosIntercambioEnergiaModalidadeEntity.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosIntercambioEnergiaModalidadeEntity are not directly saved in db
        em.detach(updatedOnsDadosIntercambioEnergiaModalidadeEntity);
        updatedOnsDadosIntercambioEnergiaModalidadeEntity
            .nomConversora(UPDATED_NOM_CONVERSORA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valModalidadecontratual(UPDATED_VAL_MODALIDADECONTRATUAL)
            .valModalidadeemergencial(UPDATED_VAL_MODALIDADEEMERGENCIAL)
            .valModalidadeoportunidade(UPDATED_VAL_MODALIDADEOPORTUNIDADE)
            .valModalidadeteste(UPDATED_VAL_MODALIDADETESTE)
            .valModalidadeexcepcional(UPDATED_VAL_MODALIDADEEXCEPCIONAL);

        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosIntercambioEnergiaModalidadeEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosIntercambioEnergiaModalidadeEntityToMatchAllProperties(updatedOnsDadosIntercambioEnergiaModalidadeEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosIntercambioEnergiaModalidadeEntity> onsDadosIntercambioEnergiaModalidadeSearchList = Streamable.of(
                    onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll()
                ).toList();
                OnsDadosIntercambioEnergiaModalidadeEntity testOnsDadosIntercambioEnergiaModalidadeSearch =
                    onsDadosIntercambioEnergiaModalidadeSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosIntercambioEnergiaModalidadeEntityAllPropertiesEquals(
                    testOnsDadosIntercambioEnergiaModalidadeSearch,
                    updatedOnsDadosIntercambioEnergiaModalidadeEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosIntercambioEnergiaModalidade() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        onsDadosIntercambioEnergiaModalidadeEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosIntercambioEnergiaModalidadeEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosIntercambioEnergiaModalidade() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        onsDadosIntercambioEnergiaModalidadeEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosIntercambioEnergiaModalidade() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        onsDadosIntercambioEnergiaModalidadeEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosIntercambioEnergiaModalidadeWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.saveAndFlush(
            onsDadosIntercambioEnergiaModalidadeEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIntercambioEnergiaModalidade using partial update
        OnsDadosIntercambioEnergiaModalidadeEntity partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity =
            new OnsDadosIntercambioEnergiaModalidadeEntity();
        partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity.setId(onsDadosIntercambioEnergiaModalidadeEntity.getId());

        partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity
            .nomConversora(UPDATED_NOM_CONVERSORA)
            .valModalidadecontratual(UPDATED_VAL_MODALIDADECONTRATUAL)
            .valModalidadeemergencial(UPDATED_VAL_MODALIDADEEMERGENCIAL)
            .valModalidadeoportunidade(UPDATED_VAL_MODALIDADEOPORTUNIDADE)
            .valModalidadeteste(UPDATED_VAL_MODALIDADETESTE);

        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIntercambioEnergiaModalidadeEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity, onsDadosIntercambioEnergiaModalidadeEntity),
            getPersistedOnsDadosIntercambioEnergiaModalidadeEntity(onsDadosIntercambioEnergiaModalidadeEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosIntercambioEnergiaModalidadeWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.saveAndFlush(
            onsDadosIntercambioEnergiaModalidadeEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIntercambioEnergiaModalidade using partial update
        OnsDadosIntercambioEnergiaModalidadeEntity partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity =
            new OnsDadosIntercambioEnergiaModalidadeEntity();
        partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity.setId(onsDadosIntercambioEnergiaModalidadeEntity.getId());

        partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity
            .nomConversora(UPDATED_NOM_CONVERSORA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valModalidadecontratual(UPDATED_VAL_MODALIDADECONTRATUAL)
            .valModalidadeemergencial(UPDATED_VAL_MODALIDADEEMERGENCIAL)
            .valModalidadeoportunidade(UPDATED_VAL_MODALIDADEOPORTUNIDADE)
            .valModalidadeteste(UPDATED_VAL_MODALIDADETESTE)
            .valModalidadeexcepcional(UPDATED_VAL_MODALIDADEEXCEPCIONAL);

        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIntercambioEnergiaModalidadeEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity,
            getPersistedOnsDadosIntercambioEnergiaModalidadeEntity(partialUpdatedOnsDadosIntercambioEnergiaModalidadeEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosIntercambioEnergiaModalidade() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        onsDadosIntercambioEnergiaModalidadeEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosIntercambioEnergiaModalidadeEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosIntercambioEnergiaModalidade() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        onsDadosIntercambioEnergiaModalidadeEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosIntercambioEnergiaModalidade() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        onsDadosIntercambioEnergiaModalidadeEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIntercambioEnergiaModalidadeEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIntercambioEnergiaModalidade in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosIntercambioEnergiaModalidade() throws Exception {
        // Initialize the database
        insertedOnsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.saveAndFlush(
            onsDadosIntercambioEnergiaModalidadeEntity
        );
        onsDadosIntercambioEnergiaModalidadeRepository.save(onsDadosIntercambioEnergiaModalidadeEntity);
        onsDadosIntercambioEnergiaModalidadeSearchRepository.save(onsDadosIntercambioEnergiaModalidadeEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosIntercambioEnergiaModalidade
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosIntercambioEnergiaModalidadeEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIntercambioEnergiaModalidadeSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosIntercambioEnergiaModalidade() throws Exception {
        // Initialize the database
        insertedOnsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.saveAndFlush(
            onsDadosIntercambioEnergiaModalidadeEntity
        );
        onsDadosIntercambioEnergiaModalidadeSearchRepository.save(onsDadosIntercambioEnergiaModalidadeEntity);

        // Search the onsDadosIntercambioEnergiaModalidade
        restOnsDadosIntercambioEnergiaModalidadeMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosIntercambioEnergiaModalidadeEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosIntercambioEnergiaModalidadeEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomConversora").value(hasItem(DEFAULT_NOM_CONVERSORA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valModalidadecontratual").value(hasItem(DEFAULT_VAL_MODALIDADECONTRATUAL)))
            .andExpect(jsonPath("$.[*].valModalidadeemergencial").value(hasItem(DEFAULT_VAL_MODALIDADEEMERGENCIAL)))
            .andExpect(jsonPath("$.[*].valModalidadeoportunidade").value(hasItem(DEFAULT_VAL_MODALIDADEOPORTUNIDADE)))
            .andExpect(jsonPath("$.[*].valModalidadeteste").value(hasItem(DEFAULT_VAL_MODALIDADETESTE)))
            .andExpect(jsonPath("$.[*].valModalidadeexcepcional").value(hasItem(DEFAULT_VAL_MODALIDADEEXCEPCIONAL)));
    }

    protected long getRepositoryCount() {
        return onsDadosIntercambioEnergiaModalidadeRepository.count();
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

    protected OnsDadosIntercambioEnergiaModalidadeEntity getPersistedOnsDadosIntercambioEnergiaModalidadeEntity(
        OnsDadosIntercambioEnergiaModalidadeEntity onsDadosIntercambioEnergiaModalidade
    ) {
        return onsDadosIntercambioEnergiaModalidadeRepository.findById(onsDadosIntercambioEnergiaModalidade.getId()).orElseThrow();
    }

    protected void assertPersistedOnsDadosIntercambioEnergiaModalidadeEntityToMatchAllProperties(
        OnsDadosIntercambioEnergiaModalidadeEntity expectedOnsDadosIntercambioEnergiaModalidadeEntity
    ) {
        assertOnsDadosIntercambioEnergiaModalidadeEntityAllPropertiesEquals(
            expectedOnsDadosIntercambioEnergiaModalidadeEntity,
            getPersistedOnsDadosIntercambioEnergiaModalidadeEntity(expectedOnsDadosIntercambioEnergiaModalidadeEntity)
        );
    }

    protected void assertPersistedOnsDadosIntercambioEnergiaModalidadeEntityToMatchUpdatableProperties(
        OnsDadosIntercambioEnergiaModalidadeEntity expectedOnsDadosIntercambioEnergiaModalidadeEntity
    ) {
        assertOnsDadosIntercambioEnergiaModalidadeEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosIntercambioEnergiaModalidadeEntity,
            getPersistedOnsDadosIntercambioEnergiaModalidadeEntity(expectedOnsDadosIntercambioEnergiaModalidadeEntity)
        );
    }
}
