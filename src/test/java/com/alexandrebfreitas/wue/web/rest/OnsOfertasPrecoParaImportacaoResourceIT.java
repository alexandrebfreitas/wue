package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsOfertasPrecoParaImportacaoEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsOfertasPrecoParaImportacaoEntity;
import com.alexandrebfreitas.wue.repository.OnsOfertasPrecoParaImportacaoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsOfertasPrecoParaImportacaoSearchRepository;
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
 * Integration tests for the {@link OnsOfertasPrecoParaImportacaoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsOfertasPrecoParaImportacaoResourceIT {

    private static final String DEFAULT_NOM_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PAIS = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_AGENTE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AGENTE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_BLOCO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_BLOCO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_INICIOVALIDADE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_INICIOVALIDADE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_FIMVALIDADE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_FIMVALIDADE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VAL_PRECO = 1D;
    private static final Double UPDATED_VAL_PRECO = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ofertas-preco-para-importacaos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ofertas-preco-para-importacaos/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsOfertasPrecoParaImportacaoRepository onsOfertasPrecoParaImportacaoRepository;

    @Autowired
    private OnsOfertasPrecoParaImportacaoSearchRepository onsOfertasPrecoParaImportacaoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsOfertasPrecoParaImportacaoMockMvc;

    private OnsOfertasPrecoParaImportacaoEntity onsOfertasPrecoParaImportacaoEntity;

    private OnsOfertasPrecoParaImportacaoEntity insertedOnsOfertasPrecoParaImportacaoEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsOfertasPrecoParaImportacaoEntity createEntity() {
        return new OnsOfertasPrecoParaImportacaoEntity()
            .nomPais(DEFAULT_NOM_PAIS)
            .nomAgente(DEFAULT_NOM_AGENTE)
            .nomBloco(DEFAULT_NOM_BLOCO)
            .datIniciovalidade(DEFAULT_DAT_INICIOVALIDADE)
            .datFimvalidade(DEFAULT_DAT_FIMVALIDADE)
            .valPreco(DEFAULT_VAL_PRECO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsOfertasPrecoParaImportacaoEntity createUpdatedEntity() {
        return new OnsOfertasPrecoParaImportacaoEntity()
            .nomPais(UPDATED_NOM_PAIS)
            .nomAgente(UPDATED_NOM_AGENTE)
            .nomBloco(UPDATED_NOM_BLOCO)
            .datIniciovalidade(UPDATED_DAT_INICIOVALIDADE)
            .datFimvalidade(UPDATED_DAT_FIMVALIDADE)
            .valPreco(UPDATED_VAL_PRECO);
    }

    @BeforeEach
    public void initTest() {
        onsOfertasPrecoParaImportacaoEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsOfertasPrecoParaImportacaoEntity != null) {
            onsOfertasPrecoParaImportacaoRepository.delete(insertedOnsOfertasPrecoParaImportacaoEntity);
            onsOfertasPrecoParaImportacaoSearchRepository.delete(insertedOnsOfertasPrecoParaImportacaoEntity);
            insertedOnsOfertasPrecoParaImportacaoEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsOfertasPrecoParaImportacao() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        // Create the OnsOfertasPrecoParaImportacao
        var returnedOnsOfertasPrecoParaImportacaoEntity = om.readValue(
            restOnsOfertasPrecoParaImportacaoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsOfertasPrecoParaImportacaoEntity.class
        );

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsOfertasPrecoParaImportacaoEntityUpdatableFieldsEquals(
            returnedOnsOfertasPrecoParaImportacaoEntity,
            getPersistedOnsOfertasPrecoParaImportacaoEntity(returnedOnsOfertasPrecoParaImportacaoEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsOfertasPrecoParaImportacaoEntity = returnedOnsOfertasPrecoParaImportacaoEntity;
    }

    @Test
    @Transactional
    void createOnsOfertasPrecoParaImportacaoWithExistingId() throws Exception {
        // Create the OnsOfertasPrecoParaImportacao with an existing ID
        onsOfertasPrecoParaImportacaoEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsOfertasPrecoParaImportacaos() throws Exception {
        // Initialize the database
        insertedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.saveAndFlush(
            onsOfertasPrecoParaImportacaoEntity
        );

        // Get all the onsOfertasPrecoParaImportacaoList
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsOfertasPrecoParaImportacaoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomPais").value(hasItem(DEFAULT_NOM_PAIS)))
            .andExpect(jsonPath("$.[*].nomAgente").value(hasItem(DEFAULT_NOM_AGENTE)))
            .andExpect(jsonPath("$.[*].nomBloco").value(hasItem(DEFAULT_NOM_BLOCO)))
            .andExpect(jsonPath("$.[*].datIniciovalidade").value(hasItem(DEFAULT_DAT_INICIOVALIDADE.toString())))
            .andExpect(jsonPath("$.[*].datFimvalidade").value(hasItem(DEFAULT_DAT_FIMVALIDADE.toString())))
            .andExpect(jsonPath("$.[*].valPreco").value(hasItem(DEFAULT_VAL_PRECO)));
    }

    @Test
    @Transactional
    void getOnsOfertasPrecoParaImportacao() throws Exception {
        // Initialize the database
        insertedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.saveAndFlush(
            onsOfertasPrecoParaImportacaoEntity
        );

        // Get the onsOfertasPrecoParaImportacao
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(get(ENTITY_API_URL_ID, onsOfertasPrecoParaImportacaoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsOfertasPrecoParaImportacaoEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomPais").value(DEFAULT_NOM_PAIS))
            .andExpect(jsonPath("$.nomAgente").value(DEFAULT_NOM_AGENTE))
            .andExpect(jsonPath("$.nomBloco").value(DEFAULT_NOM_BLOCO))
            .andExpect(jsonPath("$.datIniciovalidade").value(DEFAULT_DAT_INICIOVALIDADE.toString()))
            .andExpect(jsonPath("$.datFimvalidade").value(DEFAULT_DAT_FIMVALIDADE.toString()))
            .andExpect(jsonPath("$.valPreco").value(DEFAULT_VAL_PRECO));
    }

    @Test
    @Transactional
    void getNonExistingOnsOfertasPrecoParaImportacao() throws Exception {
        // Get the onsOfertasPrecoParaImportacao
        restOnsOfertasPrecoParaImportacaoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsOfertasPrecoParaImportacao() throws Exception {
        // Initialize the database
        insertedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.saveAndFlush(
            onsOfertasPrecoParaImportacaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsOfertasPrecoParaImportacaoSearchRepository.save(onsOfertasPrecoParaImportacaoEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());

        // Update the onsOfertasPrecoParaImportacao
        OnsOfertasPrecoParaImportacaoEntity updatedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository
            .findById(onsOfertasPrecoParaImportacaoEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsOfertasPrecoParaImportacaoEntity are not directly saved in db
        em.detach(updatedOnsOfertasPrecoParaImportacaoEntity);
        updatedOnsOfertasPrecoParaImportacaoEntity
            .nomPais(UPDATED_NOM_PAIS)
            .nomAgente(UPDATED_NOM_AGENTE)
            .nomBloco(UPDATED_NOM_BLOCO)
            .datIniciovalidade(UPDATED_DAT_INICIOVALIDADE)
            .datFimvalidade(UPDATED_DAT_FIMVALIDADE)
            .valPreco(UPDATED_VAL_PRECO);

        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsOfertasPrecoParaImportacaoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsOfertasPrecoParaImportacaoEntityToMatchAllProperties(updatedOnsOfertasPrecoParaImportacaoEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsOfertasPrecoParaImportacaoEntity> onsOfertasPrecoParaImportacaoSearchList = Streamable.of(
                    onsOfertasPrecoParaImportacaoSearchRepository.findAll()
                ).toList();
                OnsOfertasPrecoParaImportacaoEntity testOnsOfertasPrecoParaImportacaoSearch = onsOfertasPrecoParaImportacaoSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsOfertasPrecoParaImportacaoEntityAllPropertiesEquals(
                    testOnsOfertasPrecoParaImportacaoSearch,
                    updatedOnsOfertasPrecoParaImportacaoEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsOfertasPrecoParaImportacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        onsOfertasPrecoParaImportacaoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsOfertasPrecoParaImportacaoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsOfertasPrecoParaImportacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        onsOfertasPrecoParaImportacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsOfertasPrecoParaImportacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        onsOfertasPrecoParaImportacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsOfertasPrecoParaImportacaoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.saveAndFlush(
            onsOfertasPrecoParaImportacaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsOfertasPrecoParaImportacao using partial update
        OnsOfertasPrecoParaImportacaoEntity partialUpdatedOnsOfertasPrecoParaImportacaoEntity = new OnsOfertasPrecoParaImportacaoEntity();
        partialUpdatedOnsOfertasPrecoParaImportacaoEntity.setId(onsOfertasPrecoParaImportacaoEntity.getId());

        partialUpdatedOnsOfertasPrecoParaImportacaoEntity
            .nomAgente(UPDATED_NOM_AGENTE)
            .nomBloco(UPDATED_NOM_BLOCO)
            .valPreco(UPDATED_VAL_PRECO);

        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsOfertasPrecoParaImportacaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsOfertasPrecoParaImportacao in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsOfertasPrecoParaImportacaoEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsOfertasPrecoParaImportacaoEntity, onsOfertasPrecoParaImportacaoEntity),
            getPersistedOnsOfertasPrecoParaImportacaoEntity(onsOfertasPrecoParaImportacaoEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsOfertasPrecoParaImportacaoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.saveAndFlush(
            onsOfertasPrecoParaImportacaoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsOfertasPrecoParaImportacao using partial update
        OnsOfertasPrecoParaImportacaoEntity partialUpdatedOnsOfertasPrecoParaImportacaoEntity = new OnsOfertasPrecoParaImportacaoEntity();
        partialUpdatedOnsOfertasPrecoParaImportacaoEntity.setId(onsOfertasPrecoParaImportacaoEntity.getId());

        partialUpdatedOnsOfertasPrecoParaImportacaoEntity
            .nomPais(UPDATED_NOM_PAIS)
            .nomAgente(UPDATED_NOM_AGENTE)
            .nomBloco(UPDATED_NOM_BLOCO)
            .datIniciovalidade(UPDATED_DAT_INICIOVALIDADE)
            .datFimvalidade(UPDATED_DAT_FIMVALIDADE)
            .valPreco(UPDATED_VAL_PRECO);

        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsOfertasPrecoParaImportacaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsOfertasPrecoParaImportacao in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsOfertasPrecoParaImportacaoEntityUpdatableFieldsEquals(
            partialUpdatedOnsOfertasPrecoParaImportacaoEntity,
            getPersistedOnsOfertasPrecoParaImportacaoEntity(partialUpdatedOnsOfertasPrecoParaImportacaoEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsOfertasPrecoParaImportacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        onsOfertasPrecoParaImportacaoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsOfertasPrecoParaImportacaoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsOfertasPrecoParaImportacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        onsOfertasPrecoParaImportacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsOfertasPrecoParaImportacao() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        onsOfertasPrecoParaImportacaoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsOfertasPrecoParaImportacaoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsOfertasPrecoParaImportacao in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsOfertasPrecoParaImportacao() throws Exception {
        // Initialize the database
        insertedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.saveAndFlush(
            onsOfertasPrecoParaImportacaoEntity
        );
        onsOfertasPrecoParaImportacaoRepository.save(onsOfertasPrecoParaImportacaoEntity);
        onsOfertasPrecoParaImportacaoSearchRepository.save(onsOfertasPrecoParaImportacaoEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsOfertasPrecoParaImportacao
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsOfertasPrecoParaImportacaoEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsOfertasPrecoParaImportacaoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsOfertasPrecoParaImportacao() throws Exception {
        // Initialize the database
        insertedOnsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.saveAndFlush(
            onsOfertasPrecoParaImportacaoEntity
        );
        onsOfertasPrecoParaImportacaoSearchRepository.save(onsOfertasPrecoParaImportacaoEntity);

        // Search the onsOfertasPrecoParaImportacao
        restOnsOfertasPrecoParaImportacaoMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsOfertasPrecoParaImportacaoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsOfertasPrecoParaImportacaoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomPais").value(hasItem(DEFAULT_NOM_PAIS)))
            .andExpect(jsonPath("$.[*].nomAgente").value(hasItem(DEFAULT_NOM_AGENTE)))
            .andExpect(jsonPath("$.[*].nomBloco").value(hasItem(DEFAULT_NOM_BLOCO)))
            .andExpect(jsonPath("$.[*].datIniciovalidade").value(hasItem(DEFAULT_DAT_INICIOVALIDADE.toString())))
            .andExpect(jsonPath("$.[*].datFimvalidade").value(hasItem(DEFAULT_DAT_FIMVALIDADE.toString())))
            .andExpect(jsonPath("$.[*].valPreco").value(hasItem(DEFAULT_VAL_PRECO)));
    }

    protected long getRepositoryCount() {
        return onsOfertasPrecoParaImportacaoRepository.count();
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

    protected OnsOfertasPrecoParaImportacaoEntity getPersistedOnsOfertasPrecoParaImportacaoEntity(
        OnsOfertasPrecoParaImportacaoEntity onsOfertasPrecoParaImportacao
    ) {
        return onsOfertasPrecoParaImportacaoRepository.findById(onsOfertasPrecoParaImportacao.getId()).orElseThrow();
    }

    protected void assertPersistedOnsOfertasPrecoParaImportacaoEntityToMatchAllProperties(
        OnsOfertasPrecoParaImportacaoEntity expectedOnsOfertasPrecoParaImportacaoEntity
    ) {
        assertOnsOfertasPrecoParaImportacaoEntityAllPropertiesEquals(
            expectedOnsOfertasPrecoParaImportacaoEntity,
            getPersistedOnsOfertasPrecoParaImportacaoEntity(expectedOnsOfertasPrecoParaImportacaoEntity)
        );
    }

    protected void assertPersistedOnsOfertasPrecoParaImportacaoEntityToMatchUpdatableProperties(
        OnsOfertasPrecoParaImportacaoEntity expectedOnsOfertasPrecoParaImportacaoEntity
    ) {
        assertOnsOfertasPrecoParaImportacaoEntityAllUpdatablePropertiesEquals(
            expectedOnsOfertasPrecoParaImportacaoEntity,
            getPersistedOnsOfertasPrecoParaImportacaoEntity(expectedOnsOfertasPrecoParaImportacaoEntity)
        );
    }
}
