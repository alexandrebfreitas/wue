package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsTaxasTeifaETeipEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsTaxasTeifaETeipEntity;
import com.alexandrebfreitas.wue.repository.OnsTaxasTeifaETeipRepository;
import com.alexandrebfreitas.wue.repository.search.OnsTaxasTeifaETeipSearchRepository;
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
 * Integration tests for the {@link OnsTaxasTeifaETeipResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsTaxasTeifaETeipResourceIT {

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_COD_CEG = "AAAAAAAAAA";
    private static final String UPDATED_COD_CEG = "BBBBBBBBBB";

    private static final String DEFAULT_ID_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_ID_TIPOUSINA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_MES = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_MES = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOM_TAXA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TAXA = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_TAXA = 1D;
    private static final Double UPDATED_VAL_TAXA = 2D;

    private static final Double DEFAULT_NUM_VERSAO = 1D;
    private static final Double UPDATED_NUM_VERSAO = 2D;

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/ons-taxas-teifa-e-teips";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-taxas-teifa-e-teips/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsTaxasTeifaETeipRepository onsTaxasTeifaETeipRepository;

    @Autowired
    private OnsTaxasTeifaETeipSearchRepository onsTaxasTeifaETeipSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsTaxasTeifaETeipMockMvc;

    private OnsTaxasTeifaETeipEntity onsTaxasTeifaETeipEntity;

    private OnsTaxasTeifaETeipEntity insertedOnsTaxasTeifaETeipEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsTaxasTeifaETeipEntity createEntity() {
        return new OnsTaxasTeifaETeipEntity()
            .nomUsina(DEFAULT_NOM_USINA)
            .codCeg(DEFAULT_COD_CEG)
            .idTipousina(DEFAULT_ID_TIPOUSINA)
            .dinMes(DEFAULT_DIN_MES)
            .nomTaxa(DEFAULT_NOM_TAXA)
            .valTaxa(DEFAULT_VAL_TAXA)
            .numVersao(DEFAULT_NUM_VERSAO)
            .dinInstante(DEFAULT_DIN_INSTANTE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsTaxasTeifaETeipEntity createUpdatedEntity() {
        return new OnsTaxasTeifaETeipEntity()
            .nomUsina(UPDATED_NOM_USINA)
            .codCeg(UPDATED_COD_CEG)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .dinMes(UPDATED_DIN_MES)
            .nomTaxa(UPDATED_NOM_TAXA)
            .valTaxa(UPDATED_VAL_TAXA)
            .numVersao(UPDATED_NUM_VERSAO)
            .dinInstante(UPDATED_DIN_INSTANTE);
    }

    @BeforeEach
    public void initTest() {
        onsTaxasTeifaETeipEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsTaxasTeifaETeipEntity != null) {
            onsTaxasTeifaETeipRepository.delete(insertedOnsTaxasTeifaETeipEntity);
            onsTaxasTeifaETeipSearchRepository.delete(insertedOnsTaxasTeifaETeipEntity);
            insertedOnsTaxasTeifaETeipEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsTaxasTeifaETeip() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        // Create the OnsTaxasTeifaETeip
        var returnedOnsTaxasTeifaETeipEntity = om.readValue(
            restOnsTaxasTeifaETeipMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsTaxasTeifaETeipEntity.class
        );

        // Validate the OnsTaxasTeifaETeip in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsTaxasTeifaETeipEntityUpdatableFieldsEquals(
            returnedOnsTaxasTeifaETeipEntity,
            getPersistedOnsTaxasTeifaETeipEntity(returnedOnsTaxasTeifaETeipEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsTaxasTeifaETeipEntity = returnedOnsTaxasTeifaETeipEntity;
    }

    @Test
    @Transactional
    void createOnsTaxasTeifaETeipWithExistingId() throws Exception {
        // Create the OnsTaxasTeifaETeip with an existing ID
        onsTaxasTeifaETeipEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsTaxasTeifaETeipMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsTaxasTeifaETeips() throws Exception {
        // Initialize the database
        insertedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.saveAndFlush(onsTaxasTeifaETeipEntity);

        // Get all the onsTaxasTeifaETeipList
        restOnsTaxasTeifaETeipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsTaxasTeifaETeipEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].codCeg").value(hasItem(DEFAULT_COD_CEG)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].dinMes").value(hasItem(DEFAULT_DIN_MES.toString())))
            .andExpect(jsonPath("$.[*].nomTaxa").value(hasItem(DEFAULT_NOM_TAXA)))
            .andExpect(jsonPath("$.[*].valTaxa").value(hasItem(DEFAULT_VAL_TAXA)))
            .andExpect(jsonPath("$.[*].numVersao").value(hasItem(DEFAULT_NUM_VERSAO)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())));
    }

    @Test
    @Transactional
    void getOnsTaxasTeifaETeip() throws Exception {
        // Initialize the database
        insertedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.saveAndFlush(onsTaxasTeifaETeipEntity);

        // Get the onsTaxasTeifaETeip
        restOnsTaxasTeifaETeipMockMvc
            .perform(get(ENTITY_API_URL_ID, onsTaxasTeifaETeipEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsTaxasTeifaETeipEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.codCeg").value(DEFAULT_COD_CEG))
            .andExpect(jsonPath("$.idTipousina").value(DEFAULT_ID_TIPOUSINA))
            .andExpect(jsonPath("$.dinMes").value(DEFAULT_DIN_MES.toString()))
            .andExpect(jsonPath("$.nomTaxa").value(DEFAULT_NOM_TAXA))
            .andExpect(jsonPath("$.valTaxa").value(DEFAULT_VAL_TAXA))
            .andExpect(jsonPath("$.numVersao").value(DEFAULT_NUM_VERSAO))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOnsTaxasTeifaETeip() throws Exception {
        // Get the onsTaxasTeifaETeip
        restOnsTaxasTeifaETeipMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsTaxasTeifaETeip() throws Exception {
        // Initialize the database
        insertedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.saveAndFlush(onsTaxasTeifaETeipEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsTaxasTeifaETeipSearchRepository.save(onsTaxasTeifaETeipEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());

        // Update the onsTaxasTeifaETeip
        OnsTaxasTeifaETeipEntity updatedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository
            .findById(onsTaxasTeifaETeipEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsTaxasTeifaETeipEntity are not directly saved in db
        em.detach(updatedOnsTaxasTeifaETeipEntity);
        updatedOnsTaxasTeifaETeipEntity
            .nomUsina(UPDATED_NOM_USINA)
            .codCeg(UPDATED_COD_CEG)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .dinMes(UPDATED_DIN_MES)
            .nomTaxa(UPDATED_NOM_TAXA)
            .valTaxa(UPDATED_VAL_TAXA)
            .numVersao(UPDATED_NUM_VERSAO)
            .dinInstante(UPDATED_DIN_INSTANTE);

        restOnsTaxasTeifaETeipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsTaxasTeifaETeipEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsTaxasTeifaETeipEntityToMatchAllProperties(updatedOnsTaxasTeifaETeipEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsTaxasTeifaETeipEntity> onsTaxasTeifaETeipSearchList = Streamable.of(
                    onsTaxasTeifaETeipSearchRepository.findAll()
                ).toList();
                OnsTaxasTeifaETeipEntity testOnsTaxasTeifaETeipSearch = onsTaxasTeifaETeipSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsTaxasTeifaETeipEntityAllPropertiesEquals(testOnsTaxasTeifaETeipSearch, updatedOnsTaxasTeifaETeipEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsTaxasTeifaETeip() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        onsTaxasTeifaETeipEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsTaxasTeifaETeipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsTaxasTeifaETeipEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsTaxasTeifaETeip() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        onsTaxasTeifaETeipEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsTaxasTeifaETeipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsTaxasTeifaETeip() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        onsTaxasTeifaETeipEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsTaxasTeifaETeipMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsTaxasTeifaETeipWithPatch() throws Exception {
        // Initialize the database
        insertedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.saveAndFlush(onsTaxasTeifaETeipEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsTaxasTeifaETeip using partial update
        OnsTaxasTeifaETeipEntity partialUpdatedOnsTaxasTeifaETeipEntity = new OnsTaxasTeifaETeipEntity();
        partialUpdatedOnsTaxasTeifaETeipEntity.setId(onsTaxasTeifaETeipEntity.getId());

        partialUpdatedOnsTaxasTeifaETeipEntity.codCeg(UPDATED_COD_CEG).idTipousina(UPDATED_ID_TIPOUSINA).nomTaxa(UPDATED_NOM_TAXA);

        restOnsTaxasTeifaETeipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsTaxasTeifaETeipEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsTaxasTeifaETeip in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsTaxasTeifaETeipEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsTaxasTeifaETeipEntity, onsTaxasTeifaETeipEntity),
            getPersistedOnsTaxasTeifaETeipEntity(onsTaxasTeifaETeipEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsTaxasTeifaETeipWithPatch() throws Exception {
        // Initialize the database
        insertedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.saveAndFlush(onsTaxasTeifaETeipEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsTaxasTeifaETeip using partial update
        OnsTaxasTeifaETeipEntity partialUpdatedOnsTaxasTeifaETeipEntity = new OnsTaxasTeifaETeipEntity();
        partialUpdatedOnsTaxasTeifaETeipEntity.setId(onsTaxasTeifaETeipEntity.getId());

        partialUpdatedOnsTaxasTeifaETeipEntity
            .nomUsina(UPDATED_NOM_USINA)
            .codCeg(UPDATED_COD_CEG)
            .idTipousina(UPDATED_ID_TIPOUSINA)
            .dinMes(UPDATED_DIN_MES)
            .nomTaxa(UPDATED_NOM_TAXA)
            .valTaxa(UPDATED_VAL_TAXA)
            .numVersao(UPDATED_NUM_VERSAO)
            .dinInstante(UPDATED_DIN_INSTANTE);

        restOnsTaxasTeifaETeipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsTaxasTeifaETeipEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsTaxasTeifaETeip in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsTaxasTeifaETeipEntityUpdatableFieldsEquals(
            partialUpdatedOnsTaxasTeifaETeipEntity,
            getPersistedOnsTaxasTeifaETeipEntity(partialUpdatedOnsTaxasTeifaETeipEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsTaxasTeifaETeip() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        onsTaxasTeifaETeipEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsTaxasTeifaETeipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsTaxasTeifaETeipEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsTaxasTeifaETeip() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        onsTaxasTeifaETeipEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsTaxasTeifaETeipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsTaxasTeifaETeip() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        onsTaxasTeifaETeipEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsTaxasTeifaETeipMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsTaxasTeifaETeipEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsTaxasTeifaETeip in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsTaxasTeifaETeip() throws Exception {
        // Initialize the database
        insertedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.saveAndFlush(onsTaxasTeifaETeipEntity);
        onsTaxasTeifaETeipRepository.save(onsTaxasTeifaETeipEntity);
        onsTaxasTeifaETeipSearchRepository.save(onsTaxasTeifaETeipEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsTaxasTeifaETeip
        restOnsTaxasTeifaETeipMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsTaxasTeifaETeipEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsTaxasTeifaETeipSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsTaxasTeifaETeip() throws Exception {
        // Initialize the database
        insertedOnsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.saveAndFlush(onsTaxasTeifaETeipEntity);
        onsTaxasTeifaETeipSearchRepository.save(onsTaxasTeifaETeipEntity);

        // Search the onsTaxasTeifaETeip
        restOnsTaxasTeifaETeipMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsTaxasTeifaETeipEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsTaxasTeifaETeipEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].codCeg").value(hasItem(DEFAULT_COD_CEG)))
            .andExpect(jsonPath("$.[*].idTipousina").value(hasItem(DEFAULT_ID_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].dinMes").value(hasItem(DEFAULT_DIN_MES.toString())))
            .andExpect(jsonPath("$.[*].nomTaxa").value(hasItem(DEFAULT_NOM_TAXA)))
            .andExpect(jsonPath("$.[*].valTaxa").value(hasItem(DEFAULT_VAL_TAXA)))
            .andExpect(jsonPath("$.[*].numVersao").value(hasItem(DEFAULT_NUM_VERSAO)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())));
    }

    protected long getRepositoryCount() {
        return onsTaxasTeifaETeipRepository.count();
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

    protected OnsTaxasTeifaETeipEntity getPersistedOnsTaxasTeifaETeipEntity(OnsTaxasTeifaETeipEntity onsTaxasTeifaETeip) {
        return onsTaxasTeifaETeipRepository.findById(onsTaxasTeifaETeip.getId()).orElseThrow();
    }

    protected void assertPersistedOnsTaxasTeifaETeipEntityToMatchAllProperties(OnsTaxasTeifaETeipEntity expectedOnsTaxasTeifaETeipEntity) {
        assertOnsTaxasTeifaETeipEntityAllPropertiesEquals(
            expectedOnsTaxasTeifaETeipEntity,
            getPersistedOnsTaxasTeifaETeipEntity(expectedOnsTaxasTeifaETeipEntity)
        );
    }

    protected void assertPersistedOnsTaxasTeifaETeipEntityToMatchUpdatableProperties(
        OnsTaxasTeifaETeipEntity expectedOnsTaxasTeifaETeipEntity
    ) {
        assertOnsTaxasTeifaETeipEntityAllUpdatablePropertiesEquals(
            expectedOnsTaxasTeifaETeipEntity,
            getPersistedOnsTaxasTeifaETeipEntity(expectedOnsTaxasTeifaETeipEntity)
        );
    }
}
