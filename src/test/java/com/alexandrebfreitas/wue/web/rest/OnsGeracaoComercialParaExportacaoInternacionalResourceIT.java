package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoComercialParaExportacaoInternacionalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsGeracaoComercialParaExportacaoInternacionalSearchRepository;
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
 * Integration tests for the {@link OnsGeracaoComercialParaExportacaoInternacionalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsGeracaoComercialParaExportacaoInternacionalResourceIT {

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

    private static final String ENTITY_API_URL = "/api/ons-geracao-comercial-para-exportacao-internacionals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-geracao-comercial-para-exportacao-internacionals/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsGeracaoComercialParaExportacaoInternacionalRepository onsGeracaoComercialParaExportacaoInternacionalRepository;

    @Autowired
    private OnsGeracaoComercialParaExportacaoInternacionalSearchRepository onsGeracaoComercialParaExportacaoInternacionalSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsGeracaoComercialParaExportacaoInternacionalMockMvc;

    private OnsGeracaoComercialParaExportacaoInternacionalEntity onsGeracaoComercialParaExportacaoInternacionalEntity;

    private OnsGeracaoComercialParaExportacaoInternacionalEntity insertedOnsGeracaoComercialParaExportacaoInternacionalEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsGeracaoComercialParaExportacaoInternacionalEntity createEntity() {
        return new OnsGeracaoComercialParaExportacaoInternacionalEntity()
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
    public static OnsGeracaoComercialParaExportacaoInternacionalEntity createUpdatedEntity() {
        return new OnsGeracaoComercialParaExportacaoInternacionalEntity()
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
        onsGeracaoComercialParaExportacaoInternacionalEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsGeracaoComercialParaExportacaoInternacionalEntity != null) {
            onsGeracaoComercialParaExportacaoInternacionalRepository.delete(insertedOnsGeracaoComercialParaExportacaoInternacionalEntity);
            onsGeracaoComercialParaExportacaoInternacionalSearchRepository.delete(
                insertedOnsGeracaoComercialParaExportacaoInternacionalEntity
            );
            insertedOnsGeracaoComercialParaExportacaoInternacionalEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        // Create the OnsGeracaoComercialParaExportacaoInternacional
        var returnedOnsGeracaoComercialParaExportacaoInternacionalEntity = om.readValue(
            restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsGeracaoComercialParaExportacaoInternacionalEntity.class
        );

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsGeracaoComercialParaExportacaoInternacionalEntityUpdatableFieldsEquals(
            returnedOnsGeracaoComercialParaExportacaoInternacionalEntity,
            getPersistedOnsGeracaoComercialParaExportacaoInternacionalEntity(returnedOnsGeracaoComercialParaExportacaoInternacionalEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity = returnedOnsGeracaoComercialParaExportacaoInternacionalEntity;
    }

    @Test
    @Transactional
    void createOnsGeracaoComercialParaExportacaoInternacionalWithExistingId() throws Exception {
        // Create the OnsGeracaoComercialParaExportacaoInternacional with an existing ID
        onsGeracaoComercialParaExportacaoInternacionalEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsGeracaoComercialParaExportacaoInternacionals() throws Exception {
        // Initialize the database
        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.saveAndFlush(onsGeracaoComercialParaExportacaoInternacionalEntity);

        // Get all the onsGeracaoComercialParaExportacaoInternacionalList
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsGeracaoComercialParaExportacaoInternacionalEntity.getId().intValue())))
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
    void getOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        // Initialize the database
        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.saveAndFlush(onsGeracaoComercialParaExportacaoInternacionalEntity);

        // Get the onsGeracaoComercialParaExportacaoInternacional
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(get(ENTITY_API_URL_ID, onsGeracaoComercialParaExportacaoInternacionalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsGeracaoComercialParaExportacaoInternacionalEntity.getId().intValue()))
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
    void getNonExistingOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        // Get the onsGeracaoComercialParaExportacaoInternacional
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        // Initialize the database
        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.saveAndFlush(onsGeracaoComercialParaExportacaoInternacionalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsGeracaoComercialParaExportacaoInternacionalSearchRepository.save(onsGeracaoComercialParaExportacaoInternacionalEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());

        // Update the onsGeracaoComercialParaExportacaoInternacional
        OnsGeracaoComercialParaExportacaoInternacionalEntity updatedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository
                .findById(onsGeracaoComercialParaExportacaoInternacionalEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsGeracaoComercialParaExportacaoInternacionalEntity are not directly saved in db
        em.detach(updatedOnsGeracaoComercialParaExportacaoInternacionalEntity);
        updatedOnsGeracaoComercialParaExportacaoInternacionalEntity
            .nomConversora(UPDATED_NOM_CONVERSORA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valModalidadecontratual(UPDATED_VAL_MODALIDADECONTRATUAL)
            .valModalidadeemergencial(UPDATED_VAL_MODALIDADEEMERGENCIAL)
            .valModalidadeoportunidade(UPDATED_VAL_MODALIDADEOPORTUNIDADE)
            .valModalidadeteste(UPDATED_VAL_MODALIDADETESTE)
            .valModalidadeexcepcional(UPDATED_VAL_MODALIDADEEXCEPCIONAL);

        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsGeracaoComercialParaExportacaoInternacionalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsGeracaoComercialParaExportacaoInternacionalEntityToMatchAllProperties(
            updatedOnsGeracaoComercialParaExportacaoInternacionalEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsGeracaoComercialParaExportacaoInternacionalEntity> onsGeracaoComercialParaExportacaoInternacionalSearchList =
                    Streamable.of(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll()).toList();
                OnsGeracaoComercialParaExportacaoInternacionalEntity testOnsGeracaoComercialParaExportacaoInternacionalSearch =
                    onsGeracaoComercialParaExportacaoInternacionalSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsGeracaoComercialParaExportacaoInternacionalEntityAllPropertiesEquals(
                    testOnsGeracaoComercialParaExportacaoInternacionalSearch,
                    updatedOnsGeracaoComercialParaExportacaoInternacionalEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        onsGeracaoComercialParaExportacaoInternacionalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsGeracaoComercialParaExportacaoInternacionalEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        onsGeracaoComercialParaExportacaoInternacionalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        onsGeracaoComercialParaExportacaoInternacionalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsGeracaoComercialParaExportacaoInternacionalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.saveAndFlush(onsGeracaoComercialParaExportacaoInternacionalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsGeracaoComercialParaExportacaoInternacional using partial update
        OnsGeracaoComercialParaExportacaoInternacionalEntity partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            new OnsGeracaoComercialParaExportacaoInternacionalEntity();
        partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity.setId(
            onsGeracaoComercialParaExportacaoInternacionalEntity.getId()
        );

        partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valModalidadecontratual(UPDATED_VAL_MODALIDADECONTRATUAL)
            .valModalidadeteste(UPDATED_VAL_MODALIDADETESTE)
            .valModalidadeexcepcional(UPDATED_VAL_MODALIDADEEXCEPCIONAL);

        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsGeracaoComercialParaExportacaoInternacionalEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity,
                onsGeracaoComercialParaExportacaoInternacionalEntity
            ),
            getPersistedOnsGeracaoComercialParaExportacaoInternacionalEntity(onsGeracaoComercialParaExportacaoInternacionalEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsGeracaoComercialParaExportacaoInternacionalWithPatch() throws Exception {
        // Initialize the database
        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.saveAndFlush(onsGeracaoComercialParaExportacaoInternacionalEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsGeracaoComercialParaExportacaoInternacional using partial update
        OnsGeracaoComercialParaExportacaoInternacionalEntity partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            new OnsGeracaoComercialParaExportacaoInternacionalEntity();
        partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity.setId(
            onsGeracaoComercialParaExportacaoInternacionalEntity.getId()
        );

        partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity
            .nomConversora(UPDATED_NOM_CONVERSORA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valModalidadecontratual(UPDATED_VAL_MODALIDADECONTRATUAL)
            .valModalidadeemergencial(UPDATED_VAL_MODALIDADEEMERGENCIAL)
            .valModalidadeoportunidade(UPDATED_VAL_MODALIDADEOPORTUNIDADE)
            .valModalidadeteste(UPDATED_VAL_MODALIDADETESTE)
            .valModalidadeexcepcional(UPDATED_VAL_MODALIDADEEXCEPCIONAL);

        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsGeracaoComercialParaExportacaoInternacionalEntityUpdatableFieldsEquals(
            partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity,
            getPersistedOnsGeracaoComercialParaExportacaoInternacionalEntity(
                partialUpdatedOnsGeracaoComercialParaExportacaoInternacionalEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        onsGeracaoComercialParaExportacaoInternacionalEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsGeracaoComercialParaExportacaoInternacionalEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        onsGeracaoComercialParaExportacaoInternacionalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        onsGeracaoComercialParaExportacaoInternacionalEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoComercialParaExportacaoInternacionalEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsGeracaoComercialParaExportacaoInternacional in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        // Initialize the database
        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.saveAndFlush(onsGeracaoComercialParaExportacaoInternacionalEntity);
        onsGeracaoComercialParaExportacaoInternacionalRepository.save(onsGeracaoComercialParaExportacaoInternacionalEntity);
        onsGeracaoComercialParaExportacaoInternacionalSearchRepository.save(onsGeracaoComercialParaExportacaoInternacionalEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsGeracaoComercialParaExportacaoInternacional
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsGeracaoComercialParaExportacaoInternacionalEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoComercialParaExportacaoInternacionalSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsGeracaoComercialParaExportacaoInternacional() throws Exception {
        // Initialize the database
        insertedOnsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.saveAndFlush(onsGeracaoComercialParaExportacaoInternacionalEntity);
        onsGeracaoComercialParaExportacaoInternacionalSearchRepository.save(onsGeracaoComercialParaExportacaoInternacionalEntity);

        // Search the onsGeracaoComercialParaExportacaoInternacional
        restOnsGeracaoComercialParaExportacaoInternacionalMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsGeracaoComercialParaExportacaoInternacionalEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsGeracaoComercialParaExportacaoInternacionalEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomConversora").value(hasItem(DEFAULT_NOM_CONVERSORA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valModalidadecontratual").value(hasItem(DEFAULT_VAL_MODALIDADECONTRATUAL)))
            .andExpect(jsonPath("$.[*].valModalidadeemergencial").value(hasItem(DEFAULT_VAL_MODALIDADEEMERGENCIAL)))
            .andExpect(jsonPath("$.[*].valModalidadeoportunidade").value(hasItem(DEFAULT_VAL_MODALIDADEOPORTUNIDADE)))
            .andExpect(jsonPath("$.[*].valModalidadeteste").value(hasItem(DEFAULT_VAL_MODALIDADETESTE)))
            .andExpect(jsonPath("$.[*].valModalidadeexcepcional").value(hasItem(DEFAULT_VAL_MODALIDADEEXCEPCIONAL)));
    }

    protected long getRepositoryCount() {
        return onsGeracaoComercialParaExportacaoInternacionalRepository.count();
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

    protected OnsGeracaoComercialParaExportacaoInternacionalEntity getPersistedOnsGeracaoComercialParaExportacaoInternacionalEntity(
        OnsGeracaoComercialParaExportacaoInternacionalEntity onsGeracaoComercialParaExportacaoInternacional
    ) {
        return onsGeracaoComercialParaExportacaoInternacionalRepository
            .findById(onsGeracaoComercialParaExportacaoInternacional.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsGeracaoComercialParaExportacaoInternacionalEntityToMatchAllProperties(
        OnsGeracaoComercialParaExportacaoInternacionalEntity expectedOnsGeracaoComercialParaExportacaoInternacionalEntity
    ) {
        assertOnsGeracaoComercialParaExportacaoInternacionalEntityAllPropertiesEquals(
            expectedOnsGeracaoComercialParaExportacaoInternacionalEntity,
            getPersistedOnsGeracaoComercialParaExportacaoInternacionalEntity(expectedOnsGeracaoComercialParaExportacaoInternacionalEntity)
        );
    }

    protected void assertPersistedOnsGeracaoComercialParaExportacaoInternacionalEntityToMatchUpdatableProperties(
        OnsGeracaoComercialParaExportacaoInternacionalEntity expectedOnsGeracaoComercialParaExportacaoInternacionalEntity
    ) {
        assertOnsGeracaoComercialParaExportacaoInternacionalEntityAllUpdatablePropertiesEquals(
            expectedOnsGeracaoComercialParaExportacaoInternacionalEntity,
            getPersistedOnsGeracaoComercialParaExportacaoInternacionalEntity(expectedOnsGeracaoComercialParaExportacaoInternacionalEntity)
        );
    }
}
