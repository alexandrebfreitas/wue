package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsReservatoriosEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsReservatoriosEntity;
import com.alexandrebfreitas.wue.repository.OnsReservatoriosRepository;
import com.alexandrebfreitas.wue.repository.search.OnsReservatoriosSearchRepository;
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
 * Integration tests for the {@link OnsReservatoriosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsReservatoriosResourceIT {

    private static final String DEFAULT_NOM_REE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_REE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_ENTRADA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_ENTRADA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_COTAMAXIMA = 1D;
    private static final Double UPDATED_VAL_COTAMAXIMA = 2D;

    private static final Double DEFAULT_VAL_COTAMINIMA = 1D;
    private static final Double UPDATED_VAL_COTAMINIMA = 2D;

    private static final Double DEFAULT_VAL_VOLMAX = 1D;
    private static final Double UPDATED_VAL_VOLMAX = 2D;

    private static final Double DEFAULT_VAL_VOLMIN = 1D;
    private static final Double UPDATED_VAL_VOLMIN = 2D;

    private static final Double DEFAULT_VAL_VOLUTILTOT = 1D;
    private static final Double UPDATED_VAL_VOLUTILTOT = 2D;

    private static final Double DEFAULT_VAL_PRODUTIBILIDADEESPECIFICA = 1D;
    private static final Double UPDATED_VAL_PRODUTIBILIDADEESPECIFICA = 2D;

    private static final Double DEFAULT_VAL_PRODUTIVIDADE_65_VOLUTIL = 1D;
    private static final Double UPDATED_VAL_PRODUTIVIDADE_65_VOLUTIL = 2D;

    private static final String DEFAULT_VAL_TIPOPERDA = "AAAAAAAAAA";
    private static final String UPDATED_VAL_TIPOPERDA = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_PERDA = 1D;
    private static final Double UPDATED_VAL_PERDA = 2D;

    private static final Double DEFAULT_VAL_LATITUDE = 1D;
    private static final Double UPDATED_VAL_LATITUDE = 2D;

    private static final Double DEFAULT_VAL_LONGITUDE = 1D;
    private static final Double UPDATED_VAL_LONGITUDE = 2D;

    private static final String DEFAULT_ID_RESERVATORIO = "AAAAAAAAAA";
    private static final String UPDATED_ID_RESERVATORIO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ons-reservatorios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-reservatorios/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsReservatoriosRepository onsReservatoriosRepository;

    @Autowired
    private OnsReservatoriosSearchRepository onsReservatoriosSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsReservatoriosMockMvc;

    private OnsReservatoriosEntity onsReservatoriosEntity;

    private OnsReservatoriosEntity insertedOnsReservatoriosEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsReservatoriosEntity createEntity() {
        return new OnsReservatoriosEntity()
            .nomRee(DEFAULT_NOM_REE)
            .datEntrada(DEFAULT_DAT_ENTRADA)
            .valCotamaxima(DEFAULT_VAL_COTAMAXIMA)
            .valCotaminima(DEFAULT_VAL_COTAMINIMA)
            .valVolmax(DEFAULT_VAL_VOLMAX)
            .valVolmin(DEFAULT_VAL_VOLMIN)
            .valVolutiltot(DEFAULT_VAL_VOLUTILTOT)
            .valProdutibilidadeespecifica(DEFAULT_VAL_PRODUTIBILIDADEESPECIFICA)
            .valProdutividade65volutil(DEFAULT_VAL_PRODUTIVIDADE_65_VOLUTIL)
            .valTipoperda(DEFAULT_VAL_TIPOPERDA)
            .valPerda(DEFAULT_VAL_PERDA)
            .valLatitude(DEFAULT_VAL_LATITUDE)
            .valLongitude(DEFAULT_VAL_LONGITUDE)
            .idReservatorio(DEFAULT_ID_RESERVATORIO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsReservatoriosEntity createUpdatedEntity() {
        return new OnsReservatoriosEntity()
            .nomRee(UPDATED_NOM_REE)
            .datEntrada(UPDATED_DAT_ENTRADA)
            .valCotamaxima(UPDATED_VAL_COTAMAXIMA)
            .valCotaminima(UPDATED_VAL_COTAMINIMA)
            .valVolmax(UPDATED_VAL_VOLMAX)
            .valVolmin(UPDATED_VAL_VOLMIN)
            .valVolutiltot(UPDATED_VAL_VOLUTILTOT)
            .valProdutibilidadeespecifica(UPDATED_VAL_PRODUTIBILIDADEESPECIFICA)
            .valProdutividade65volutil(UPDATED_VAL_PRODUTIVIDADE_65_VOLUTIL)
            .valTipoperda(UPDATED_VAL_TIPOPERDA)
            .valPerda(UPDATED_VAL_PERDA)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .idReservatorio(UPDATED_ID_RESERVATORIO);
    }

    @BeforeEach
    public void initTest() {
        onsReservatoriosEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsReservatoriosEntity != null) {
            onsReservatoriosRepository.delete(insertedOnsReservatoriosEntity);
            onsReservatoriosSearchRepository.delete(insertedOnsReservatoriosEntity);
            insertedOnsReservatoriosEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsReservatorios() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        // Create the OnsReservatorios
        var returnedOnsReservatoriosEntity = om.readValue(
            restOnsReservatoriosMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsReservatoriosEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsReservatoriosEntity.class
        );

        // Validate the OnsReservatorios in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsReservatoriosEntityUpdatableFieldsEquals(
            returnedOnsReservatoriosEntity,
            getPersistedOnsReservatoriosEntity(returnedOnsReservatoriosEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsReservatoriosEntity = returnedOnsReservatoriosEntity;
    }

    @Test
    @Transactional
    void createOnsReservatoriosWithExistingId() throws Exception {
        // Create the OnsReservatorios with an existing ID
        onsReservatoriosEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsReservatoriosMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsReservatoriosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsReservatorios() throws Exception {
        // Initialize the database
        insertedOnsReservatoriosEntity = onsReservatoriosRepository.saveAndFlush(onsReservatoriosEntity);

        // Get all the onsReservatoriosList
        restOnsReservatoriosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsReservatoriosEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomRee").value(hasItem(DEFAULT_NOM_REE)))
            .andExpect(jsonPath("$.[*].datEntrada").value(hasItem(DEFAULT_DAT_ENTRADA.toString())))
            .andExpect(jsonPath("$.[*].valCotamaxima").value(hasItem(DEFAULT_VAL_COTAMAXIMA)))
            .andExpect(jsonPath("$.[*].valCotaminima").value(hasItem(DEFAULT_VAL_COTAMINIMA)))
            .andExpect(jsonPath("$.[*].valVolmax").value(hasItem(DEFAULT_VAL_VOLMAX)))
            .andExpect(jsonPath("$.[*].valVolmin").value(hasItem(DEFAULT_VAL_VOLMIN)))
            .andExpect(jsonPath("$.[*].valVolutiltot").value(hasItem(DEFAULT_VAL_VOLUTILTOT)))
            .andExpect(jsonPath("$.[*].valProdutibilidadeespecifica").value(hasItem(DEFAULT_VAL_PRODUTIBILIDADEESPECIFICA)))
            .andExpect(jsonPath("$.[*].valProdutividade65volutil").value(hasItem(DEFAULT_VAL_PRODUTIVIDADE_65_VOLUTIL)))
            .andExpect(jsonPath("$.[*].valTipoperda").value(hasItem(DEFAULT_VAL_TIPOPERDA)))
            .andExpect(jsonPath("$.[*].valPerda").value(hasItem(DEFAULT_VAL_PERDA)))
            .andExpect(jsonPath("$.[*].valLatitude").value(hasItem(DEFAULT_VAL_LATITUDE)))
            .andExpect(jsonPath("$.[*].valLongitude").value(hasItem(DEFAULT_VAL_LONGITUDE)))
            .andExpect(jsonPath("$.[*].idReservatorio").value(hasItem(DEFAULT_ID_RESERVATORIO)));
    }

    @Test
    @Transactional
    void getOnsReservatorios() throws Exception {
        // Initialize the database
        insertedOnsReservatoriosEntity = onsReservatoriosRepository.saveAndFlush(onsReservatoriosEntity);

        // Get the onsReservatorios
        restOnsReservatoriosMockMvc
            .perform(get(ENTITY_API_URL_ID, onsReservatoriosEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsReservatoriosEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomRee").value(DEFAULT_NOM_REE))
            .andExpect(jsonPath("$.datEntrada").value(DEFAULT_DAT_ENTRADA.toString()))
            .andExpect(jsonPath("$.valCotamaxima").value(DEFAULT_VAL_COTAMAXIMA))
            .andExpect(jsonPath("$.valCotaminima").value(DEFAULT_VAL_COTAMINIMA))
            .andExpect(jsonPath("$.valVolmax").value(DEFAULT_VAL_VOLMAX))
            .andExpect(jsonPath("$.valVolmin").value(DEFAULT_VAL_VOLMIN))
            .andExpect(jsonPath("$.valVolutiltot").value(DEFAULT_VAL_VOLUTILTOT))
            .andExpect(jsonPath("$.valProdutibilidadeespecifica").value(DEFAULT_VAL_PRODUTIBILIDADEESPECIFICA))
            .andExpect(jsonPath("$.valProdutividade65volutil").value(DEFAULT_VAL_PRODUTIVIDADE_65_VOLUTIL))
            .andExpect(jsonPath("$.valTipoperda").value(DEFAULT_VAL_TIPOPERDA))
            .andExpect(jsonPath("$.valPerda").value(DEFAULT_VAL_PERDA))
            .andExpect(jsonPath("$.valLatitude").value(DEFAULT_VAL_LATITUDE))
            .andExpect(jsonPath("$.valLongitude").value(DEFAULT_VAL_LONGITUDE))
            .andExpect(jsonPath("$.idReservatorio").value(DEFAULT_ID_RESERVATORIO));
    }

    @Test
    @Transactional
    void getNonExistingOnsReservatorios() throws Exception {
        // Get the onsReservatorios
        restOnsReservatoriosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsReservatorios() throws Exception {
        // Initialize the database
        insertedOnsReservatoriosEntity = onsReservatoriosRepository.saveAndFlush(onsReservatoriosEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsReservatoriosSearchRepository.save(onsReservatoriosEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());

        // Update the onsReservatorios
        OnsReservatoriosEntity updatedOnsReservatoriosEntity = onsReservatoriosRepository
            .findById(onsReservatoriosEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsReservatoriosEntity are not directly saved in db
        em.detach(updatedOnsReservatoriosEntity);
        updatedOnsReservatoriosEntity
            .nomRee(UPDATED_NOM_REE)
            .datEntrada(UPDATED_DAT_ENTRADA)
            .valCotamaxima(UPDATED_VAL_COTAMAXIMA)
            .valCotaminima(UPDATED_VAL_COTAMINIMA)
            .valVolmax(UPDATED_VAL_VOLMAX)
            .valVolmin(UPDATED_VAL_VOLMIN)
            .valVolutiltot(UPDATED_VAL_VOLUTILTOT)
            .valProdutibilidadeespecifica(UPDATED_VAL_PRODUTIBILIDADEESPECIFICA)
            .valProdutividade65volutil(UPDATED_VAL_PRODUTIVIDADE_65_VOLUTIL)
            .valTipoperda(UPDATED_VAL_TIPOPERDA)
            .valPerda(UPDATED_VAL_PERDA)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .idReservatorio(UPDATED_ID_RESERVATORIO);

        restOnsReservatoriosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsReservatoriosEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsReservatoriosEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsReservatoriosEntityToMatchAllProperties(updatedOnsReservatoriosEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsReservatoriosEntity> onsReservatoriosSearchList = Streamable.of(
                    onsReservatoriosSearchRepository.findAll()
                ).toList();
                OnsReservatoriosEntity testOnsReservatoriosSearch = onsReservatoriosSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsReservatoriosEntityAllPropertiesEquals(testOnsReservatoriosSearch, updatedOnsReservatoriosEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsReservatorios() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        onsReservatoriosEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsReservatoriosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsReservatoriosEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsReservatoriosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsReservatorios() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        onsReservatoriosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsReservatoriosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsReservatoriosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsReservatorios() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        onsReservatoriosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsReservatoriosMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsReservatoriosEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsReservatoriosWithPatch() throws Exception {
        // Initialize the database
        insertedOnsReservatoriosEntity = onsReservatoriosRepository.saveAndFlush(onsReservatoriosEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsReservatorios using partial update
        OnsReservatoriosEntity partialUpdatedOnsReservatoriosEntity = new OnsReservatoriosEntity();
        partialUpdatedOnsReservatoriosEntity.setId(onsReservatoriosEntity.getId());

        partialUpdatedOnsReservatoriosEntity
            .datEntrada(UPDATED_DAT_ENTRADA)
            .valCotamaxima(UPDATED_VAL_COTAMAXIMA)
            .valVolmax(UPDATED_VAL_VOLMAX)
            .valTipoperda(UPDATED_VAL_TIPOPERDA)
            .valPerda(UPDATED_VAL_PERDA)
            .valLatitude(UPDATED_VAL_LATITUDE);

        restOnsReservatoriosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsReservatoriosEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsReservatoriosEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsReservatorios in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsReservatoriosEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsReservatoriosEntity, onsReservatoriosEntity),
            getPersistedOnsReservatoriosEntity(onsReservatoriosEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsReservatoriosWithPatch() throws Exception {
        // Initialize the database
        insertedOnsReservatoriosEntity = onsReservatoriosRepository.saveAndFlush(onsReservatoriosEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsReservatorios using partial update
        OnsReservatoriosEntity partialUpdatedOnsReservatoriosEntity = new OnsReservatoriosEntity();
        partialUpdatedOnsReservatoriosEntity.setId(onsReservatoriosEntity.getId());

        partialUpdatedOnsReservatoriosEntity
            .nomRee(UPDATED_NOM_REE)
            .datEntrada(UPDATED_DAT_ENTRADA)
            .valCotamaxima(UPDATED_VAL_COTAMAXIMA)
            .valCotaminima(UPDATED_VAL_COTAMINIMA)
            .valVolmax(UPDATED_VAL_VOLMAX)
            .valVolmin(UPDATED_VAL_VOLMIN)
            .valVolutiltot(UPDATED_VAL_VOLUTILTOT)
            .valProdutibilidadeespecifica(UPDATED_VAL_PRODUTIBILIDADEESPECIFICA)
            .valProdutividade65volutil(UPDATED_VAL_PRODUTIVIDADE_65_VOLUTIL)
            .valTipoperda(UPDATED_VAL_TIPOPERDA)
            .valPerda(UPDATED_VAL_PERDA)
            .valLatitude(UPDATED_VAL_LATITUDE)
            .valLongitude(UPDATED_VAL_LONGITUDE)
            .idReservatorio(UPDATED_ID_RESERVATORIO);

        restOnsReservatoriosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsReservatoriosEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsReservatoriosEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsReservatorios in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsReservatoriosEntityUpdatableFieldsEquals(
            partialUpdatedOnsReservatoriosEntity,
            getPersistedOnsReservatoriosEntity(partialUpdatedOnsReservatoriosEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsReservatorios() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        onsReservatoriosEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsReservatoriosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsReservatoriosEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsReservatoriosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsReservatorios() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        onsReservatoriosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsReservatoriosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsReservatoriosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsReservatorios() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        onsReservatoriosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsReservatoriosMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsReservatoriosEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsReservatorios in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsReservatorios() throws Exception {
        // Initialize the database
        insertedOnsReservatoriosEntity = onsReservatoriosRepository.saveAndFlush(onsReservatoriosEntity);
        onsReservatoriosRepository.save(onsReservatoriosEntity);
        onsReservatoriosSearchRepository.save(onsReservatoriosEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsReservatorios
        restOnsReservatoriosMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsReservatoriosEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsReservatoriosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsReservatorios() throws Exception {
        // Initialize the database
        insertedOnsReservatoriosEntity = onsReservatoriosRepository.saveAndFlush(onsReservatoriosEntity);
        onsReservatoriosSearchRepository.save(onsReservatoriosEntity);

        // Search the onsReservatorios
        restOnsReservatoriosMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsReservatoriosEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsReservatoriosEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomRee").value(hasItem(DEFAULT_NOM_REE)))
            .andExpect(jsonPath("$.[*].datEntrada").value(hasItem(DEFAULT_DAT_ENTRADA.toString())))
            .andExpect(jsonPath("$.[*].valCotamaxima").value(hasItem(DEFAULT_VAL_COTAMAXIMA)))
            .andExpect(jsonPath("$.[*].valCotaminima").value(hasItem(DEFAULT_VAL_COTAMINIMA)))
            .andExpect(jsonPath("$.[*].valVolmax").value(hasItem(DEFAULT_VAL_VOLMAX)))
            .andExpect(jsonPath("$.[*].valVolmin").value(hasItem(DEFAULT_VAL_VOLMIN)))
            .andExpect(jsonPath("$.[*].valVolutiltot").value(hasItem(DEFAULT_VAL_VOLUTILTOT)))
            .andExpect(jsonPath("$.[*].valProdutibilidadeespecifica").value(hasItem(DEFAULT_VAL_PRODUTIBILIDADEESPECIFICA)))
            .andExpect(jsonPath("$.[*].valProdutividade65volutil").value(hasItem(DEFAULT_VAL_PRODUTIVIDADE_65_VOLUTIL)))
            .andExpect(jsonPath("$.[*].valTipoperda").value(hasItem(DEFAULT_VAL_TIPOPERDA)))
            .andExpect(jsonPath("$.[*].valPerda").value(hasItem(DEFAULT_VAL_PERDA)))
            .andExpect(jsonPath("$.[*].valLatitude").value(hasItem(DEFAULT_VAL_LATITUDE)))
            .andExpect(jsonPath("$.[*].valLongitude").value(hasItem(DEFAULT_VAL_LONGITUDE)))
            .andExpect(jsonPath("$.[*].idReservatorio").value(hasItem(DEFAULT_ID_RESERVATORIO)));
    }

    protected long getRepositoryCount() {
        return onsReservatoriosRepository.count();
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

    protected OnsReservatoriosEntity getPersistedOnsReservatoriosEntity(OnsReservatoriosEntity onsReservatorios) {
        return onsReservatoriosRepository.findById(onsReservatorios.getId()).orElseThrow();
    }

    protected void assertPersistedOnsReservatoriosEntityToMatchAllProperties(OnsReservatoriosEntity expectedOnsReservatoriosEntity) {
        assertOnsReservatoriosEntityAllPropertiesEquals(
            expectedOnsReservatoriosEntity,
            getPersistedOnsReservatoriosEntity(expectedOnsReservatoriosEntity)
        );
    }

    protected void assertPersistedOnsReservatoriosEntityToMatchUpdatableProperties(OnsReservatoriosEntity expectedOnsReservatoriosEntity) {
        assertOnsReservatoriosEntityAllUpdatablePropertiesEquals(
            expectedOnsReservatoriosEntity,
            getPersistedOnsReservatoriosEntity(expectedOnsReservatoriosEntity)
        );
    }
}
