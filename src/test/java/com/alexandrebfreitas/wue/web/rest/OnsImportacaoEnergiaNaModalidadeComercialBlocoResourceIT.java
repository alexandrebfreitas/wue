package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity;
import com.alexandrebfreitas.wue.repository.OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository;
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
 * Integration tests for the {@link OnsImportacaoEnergiaNaModalidadeComercialBlocoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsImportacaoEnergiaNaModalidadeComercialBlocoResourceIT {

    private static final String DEFAULT_NOM_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PAIS = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_AGENTE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AGENTE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_BLOCO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_BLOCO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_BLOCO = "AAAAAAAAAA";
    private static final String UPDATED_COD_BLOCO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_IMPORTACAOPROGRAMADA = 1D;
    private static final Double UPDATED_VAL_IMPORTACAOPROGRAMADA = 2D;

    private static final Double DEFAULT_VAL_IMPORTACAODESPACHADA = 1D;
    private static final Double UPDATED_VAL_IMPORTACAODESPACHADA = 2D;

    private static final Double DEFAULT_VAL_IMPORTACAOVERIFICADA = 1D;
    private static final Double UPDATED_VAL_IMPORTACAOVERIFICADA = 2D;

    private static final Double DEFAULT_VAL_PRECO = 1D;
    private static final Double UPDATED_VAL_PRECO = 2D;

    private static final String ENTITY_API_URL = "/api/ons-importacao-energia-na-modalidade-comercial-blocos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-importacao-energia-na-modalidade-comercial-blocos/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository onsImportacaoEnergiaNaModalidadeComercialBlocoRepository;

    @Autowired
    private OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc;

    private OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity onsImportacaoEnergiaNaModalidadeComercialBlocoEntity;

    private OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity createEntity() {
        return new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity()
            .nomPais(DEFAULT_NOM_PAIS)
            .nomAgente(DEFAULT_NOM_AGENTE)
            .nomBloco(DEFAULT_NOM_BLOCO)
            .codBloco(DEFAULT_COD_BLOCO)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .valImportacaoprogramada(DEFAULT_VAL_IMPORTACAOPROGRAMADA)
            .valImportacaodespachada(DEFAULT_VAL_IMPORTACAODESPACHADA)
            .valImportacaoverificada(DEFAULT_VAL_IMPORTACAOVERIFICADA)
            .valPreco(DEFAULT_VAL_PRECO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity createUpdatedEntity() {
        return new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity()
            .nomPais(UPDATED_NOM_PAIS)
            .nomAgente(UPDATED_NOM_AGENTE)
            .nomBloco(UPDATED_NOM_BLOCO)
            .codBloco(UPDATED_COD_BLOCO)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valImportacaoprogramada(UPDATED_VAL_IMPORTACAOPROGRAMADA)
            .valImportacaodespachada(UPDATED_VAL_IMPORTACAODESPACHADA)
            .valImportacaoverificada(UPDATED_VAL_IMPORTACAOVERIFICADA)
            .valPreco(UPDATED_VAL_PRECO);
    }

    @BeforeEach
    public void initTest() {
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity != null) {
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.delete(insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
            onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.delete(
                insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
            );
            insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        // Create the OnsImportacaoEnergiaNaModalidadeComercialBloco
        var returnedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity = om.readValue(
            restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.class
        );

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityUpdatableFieldsEquals(
            returnedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity,
            getPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity(returnedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity = returnedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity;
    }

    @Test
    @Transactional
    void createOnsImportacaoEnergiaNaModalidadeComercialBlocoWithExistingId() throws Exception {
        // Create the OnsImportacaoEnergiaNaModalidadeComercialBloco with an existing ID
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsImportacaoEnergiaNaModalidadeComercialBlocos() throws Exception {
        // Initialize the database
        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.saveAndFlush(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);

        // Get all the onsImportacaoEnergiaNaModalidadeComercialBlocoList
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomPais").value(hasItem(DEFAULT_NOM_PAIS)))
            .andExpect(jsonPath("$.[*].nomAgente").value(hasItem(DEFAULT_NOM_AGENTE)))
            .andExpect(jsonPath("$.[*].nomBloco").value(hasItem(DEFAULT_NOM_BLOCO)))
            .andExpect(jsonPath("$.[*].codBloco").value(hasItem(DEFAULT_COD_BLOCO)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valImportacaoprogramada").value(hasItem(DEFAULT_VAL_IMPORTACAOPROGRAMADA)))
            .andExpect(jsonPath("$.[*].valImportacaodespachada").value(hasItem(DEFAULT_VAL_IMPORTACAODESPACHADA)))
            .andExpect(jsonPath("$.[*].valImportacaoverificada").value(hasItem(DEFAULT_VAL_IMPORTACAOVERIFICADA)))
            .andExpect(jsonPath("$.[*].valPreco").value(hasItem(DEFAULT_VAL_PRECO)));
    }

    @Test
    @Transactional
    void getOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        // Initialize the database
        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.saveAndFlush(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);

        // Get the onsImportacaoEnergiaNaModalidadeComercialBloco
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(get(ENTITY_API_URL_ID, onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomPais").value(DEFAULT_NOM_PAIS))
            .andExpect(jsonPath("$.nomAgente").value(DEFAULT_NOM_AGENTE))
            .andExpect(jsonPath("$.nomBloco").value(DEFAULT_NOM_BLOCO))
            .andExpect(jsonPath("$.codBloco").value(DEFAULT_COD_BLOCO))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.valImportacaoprogramada").value(DEFAULT_VAL_IMPORTACAOPROGRAMADA))
            .andExpect(jsonPath("$.valImportacaodespachada").value(DEFAULT_VAL_IMPORTACAODESPACHADA))
            .andExpect(jsonPath("$.valImportacaoverificada").value(DEFAULT_VAL_IMPORTACAOVERIFICADA))
            .andExpect(jsonPath("$.valPreco").value(DEFAULT_VAL_PRECO));
    }

    @Test
    @Transactional
    void getNonExistingOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        // Get the onsImportacaoEnergiaNaModalidadeComercialBloco
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        // Initialize the database
        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.saveAndFlush(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.save(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());

        // Update the onsImportacaoEnergiaNaModalidadeComercialBloco
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository
                .findById(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity are not directly saved in db
        em.detach(updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
        updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
            .nomPais(UPDATED_NOM_PAIS)
            .nomAgente(UPDATED_NOM_AGENTE)
            .nomBloco(UPDATED_NOM_BLOCO)
            .codBloco(UPDATED_COD_BLOCO)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valImportacaoprogramada(UPDATED_VAL_IMPORTACAOPROGRAMADA)
            .valImportacaodespachada(UPDATED_VAL_IMPORTACAODESPACHADA)
            .valImportacaoverificada(UPDATED_VAL_IMPORTACAOVERIFICADA)
            .valPreco(UPDATED_VAL_PRECO);

        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityToMatchAllProperties(
            updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> onsImportacaoEnergiaNaModalidadeComercialBlocoSearchList =
                    Streamable.of(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll()).toList();
                OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity testOnsImportacaoEnergiaNaModalidadeComercialBlocoSearch =
                    onsImportacaoEnergiaNaModalidadeComercialBlocoSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityAllPropertiesEquals(
                    testOnsImportacaoEnergiaNaModalidadeComercialBlocoSearch,
                    updatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsImportacaoEnergiaNaModalidadeComercialBlocoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.saveAndFlush(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsImportacaoEnergiaNaModalidadeComercialBloco using partial update
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity();
        partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId()
        );

        partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
            .nomPais(UPDATED_NOM_PAIS)
            .nomBloco(UPDATED_NOM_BLOCO)
            .codBloco(UPDATED_COD_BLOCO)
            .valImportacaodespachada(UPDATED_VAL_IMPORTACAODESPACHADA)
            .valImportacaoverificada(UPDATED_VAL_IMPORTACAOVERIFICADA)
            .valPreco(UPDATED_VAL_PRECO);

        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity,
                onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
            ),
            getPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsImportacaoEnergiaNaModalidadeComercialBlocoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.saveAndFlush(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsImportacaoEnergiaNaModalidadeComercialBloco using partial update
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            new OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity();
        partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId()
        );

        partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
            .nomPais(UPDATED_NOM_PAIS)
            .nomAgente(UPDATED_NOM_AGENTE)
            .nomBloco(UPDATED_NOM_BLOCO)
            .codBloco(UPDATED_COD_BLOCO)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .valImportacaoprogramada(UPDATED_VAL_IMPORTACAOPROGRAMADA)
            .valImportacaodespachada(UPDATED_VAL_IMPORTACAODESPACHADA)
            .valImportacaoverificada(UPDATED_VAL_IMPORTACAOVERIFICADA)
            .valPreco(UPDATED_VAL_PRECO);

        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityUpdatableFieldsEquals(
            partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity,
            getPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity(
                partialUpdatedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsImportacaoEnergiaNaModalidadeComercialBloco in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        // Initialize the database
        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.saveAndFlush(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
        onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.save(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
        onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.save(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsImportacaoEnergiaNaModalidadeComercialBloco
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsImportacaoEnergiaNaModalidadeComercialBloco() throws Exception {
        // Initialize the database
        insertedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.saveAndFlush(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
        onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.save(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);

        // Search the onsImportacaoEnergiaNaModalidadeComercialBloco
        restOnsImportacaoEnergiaNaModalidadeComercialBlocoMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomPais").value(hasItem(DEFAULT_NOM_PAIS)))
            .andExpect(jsonPath("$.[*].nomAgente").value(hasItem(DEFAULT_NOM_AGENTE)))
            .andExpect(jsonPath("$.[*].nomBloco").value(hasItem(DEFAULT_NOM_BLOCO)))
            .andExpect(jsonPath("$.[*].codBloco").value(hasItem(DEFAULT_COD_BLOCO)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].valImportacaoprogramada").value(hasItem(DEFAULT_VAL_IMPORTACAOPROGRAMADA)))
            .andExpect(jsonPath("$.[*].valImportacaodespachada").value(hasItem(DEFAULT_VAL_IMPORTACAODESPACHADA)))
            .andExpect(jsonPath("$.[*].valImportacaoverificada").value(hasItem(DEFAULT_VAL_IMPORTACAOVERIFICADA)))
            .andExpect(jsonPath("$.[*].valPreco").value(hasItem(DEFAULT_VAL_PRECO)));
    }

    protected long getRepositoryCount() {
        return onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.count();
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

    protected OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity getPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity(
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity onsImportacaoEnergiaNaModalidadeComercialBloco
    ) {
        return onsImportacaoEnergiaNaModalidadeComercialBlocoRepository
            .findById(onsImportacaoEnergiaNaModalidadeComercialBloco.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityToMatchAllProperties(
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity expectedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
    ) {
        assertOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityAllPropertiesEquals(
            expectedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity,
            getPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity(expectedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity)
        );
    }

    protected void assertPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityToMatchUpdatableProperties(
        OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity expectedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity
    ) {
        assertOnsImportacaoEnergiaNaModalidadeComercialBlocoEntityAllUpdatablePropertiesEquals(
            expectedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity,
            getPersistedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity(expectedOnsImportacaoEnergiaNaModalidadeComercialBlocoEntity)
        );
    }
}
