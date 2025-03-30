package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsModalidadeOperacaoUsinasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsModalidadeOperacaoUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsModalidadeOperacaoUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsModalidadeOperacaoUsinasSearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link OnsModalidadeOperacaoUsinasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsModalidadeOperacaoUsinasResourceIT {

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MODALIDADEOPERACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MODALIDADEOPERACAO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_POTENCIAAUTORIZADA = 1D;
    private static final Double UPDATED_VAL_POTENCIAAUTORIZADA = 2D;

    private static final String DEFAULT_SGL_CENTROOPERACAO = "AAAAAAAAAA";
    private static final String UPDATED_SGL_CENTROOPERACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_PONTOCONEXAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PONTOCONEXAO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_STS_ANEEL = "AAAAAAAAAA";
    private static final String UPDATED_STS_ANEEL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ons-modalidade-operacao-usinas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-modalidade-operacao-usinas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsModalidadeOperacaoUsinasRepository onsModalidadeOperacaoUsinasRepository;

    @Autowired
    private OnsModalidadeOperacaoUsinasSearchRepository onsModalidadeOperacaoUsinasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsModalidadeOperacaoUsinasMockMvc;

    private OnsModalidadeOperacaoUsinasEntity onsModalidadeOperacaoUsinasEntity;

    private OnsModalidadeOperacaoUsinasEntity insertedOnsModalidadeOperacaoUsinasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsModalidadeOperacaoUsinasEntity createEntity() {
        return new OnsModalidadeOperacaoUsinasEntity()
            .nomUsina(DEFAULT_NOM_USINA)
            .ceg(DEFAULT_CEG)
            .nomModalidadeoperacao(DEFAULT_NOM_MODALIDADEOPERACAO)
            .valPotenciaautorizada(DEFAULT_VAL_POTENCIAAUTORIZADA)
            .sglCentrooperacao(DEFAULT_SGL_CENTROOPERACAO)
            .nomPontoconexao(DEFAULT_NOM_PONTOCONEXAO)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .stsAneel(DEFAULT_STS_ANEEL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsModalidadeOperacaoUsinasEntity createUpdatedEntity() {
        return new OnsModalidadeOperacaoUsinasEntity()
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .valPotenciaautorizada(UPDATED_VAL_POTENCIAAUTORIZADA)
            .sglCentrooperacao(UPDATED_SGL_CENTROOPERACAO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .stsAneel(UPDATED_STS_ANEEL);
    }

    @BeforeEach
    public void initTest() {
        onsModalidadeOperacaoUsinasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsModalidadeOperacaoUsinasEntity != null) {
            onsModalidadeOperacaoUsinasRepository.delete(insertedOnsModalidadeOperacaoUsinasEntity);
            onsModalidadeOperacaoUsinasSearchRepository.delete(insertedOnsModalidadeOperacaoUsinasEntity);
            insertedOnsModalidadeOperacaoUsinasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsModalidadeOperacaoUsinas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        // Create the OnsModalidadeOperacaoUsinas
        var returnedOnsModalidadeOperacaoUsinasEntity = om.readValue(
            restOnsModalidadeOperacaoUsinasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsModalidadeOperacaoUsinasEntity.class
        );

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsModalidadeOperacaoUsinasEntityUpdatableFieldsEquals(
            returnedOnsModalidadeOperacaoUsinasEntity,
            getPersistedOnsModalidadeOperacaoUsinasEntity(returnedOnsModalidadeOperacaoUsinasEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsModalidadeOperacaoUsinasEntity = returnedOnsModalidadeOperacaoUsinasEntity;
    }

    @Test
    @Transactional
    void createOnsModalidadeOperacaoUsinasWithExistingId() throws Exception {
        // Create the OnsModalidadeOperacaoUsinas with an existing ID
        onsModalidadeOperacaoUsinasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsModalidadeOperacaoUsinas() throws Exception {
        // Initialize the database
        insertedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.saveAndFlush(onsModalidadeOperacaoUsinasEntity);

        // Get all the onsModalidadeOperacaoUsinasList
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsModalidadeOperacaoUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].valPotenciaautorizada").value(hasItem(DEFAULT_VAL_POTENCIAAUTORIZADA)))
            .andExpect(jsonPath("$.[*].sglCentrooperacao").value(hasItem(DEFAULT_SGL_CENTROOPERACAO)))
            .andExpect(jsonPath("$.[*].nomPontoconexao").value(hasItem(DEFAULT_NOM_PONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].stsAneel").value(hasItem(DEFAULT_STS_ANEEL)));
    }

    @Test
    @Transactional
    void getOnsModalidadeOperacaoUsinas() throws Exception {
        // Initialize the database
        insertedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.saveAndFlush(onsModalidadeOperacaoUsinasEntity);

        // Get the onsModalidadeOperacaoUsinas
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsModalidadeOperacaoUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsModalidadeOperacaoUsinasEntity.getId().intValue()))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.nomModalidadeoperacao").value(DEFAULT_NOM_MODALIDADEOPERACAO))
            .andExpect(jsonPath("$.valPotenciaautorizada").value(DEFAULT_VAL_POTENCIAAUTORIZADA))
            .andExpect(jsonPath("$.sglCentrooperacao").value(DEFAULT_SGL_CENTROOPERACAO))
            .andExpect(jsonPath("$.nomPontoconexao").value(DEFAULT_NOM_PONTOCONEXAO))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.stsAneel").value(DEFAULT_STS_ANEEL));
    }

    @Test
    @Transactional
    void getNonExistingOnsModalidadeOperacaoUsinas() throws Exception {
        // Get the onsModalidadeOperacaoUsinas
        restOnsModalidadeOperacaoUsinasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsModalidadeOperacaoUsinas() throws Exception {
        // Initialize the database
        insertedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.saveAndFlush(onsModalidadeOperacaoUsinasEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsModalidadeOperacaoUsinasSearchRepository.save(onsModalidadeOperacaoUsinasEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());

        // Update the onsModalidadeOperacaoUsinas
        OnsModalidadeOperacaoUsinasEntity updatedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository
            .findById(onsModalidadeOperacaoUsinasEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsModalidadeOperacaoUsinasEntity are not directly saved in db
        em.detach(updatedOnsModalidadeOperacaoUsinasEntity);
        updatedOnsModalidadeOperacaoUsinasEntity
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .valPotenciaautorizada(UPDATED_VAL_POTENCIAAUTORIZADA)
            .sglCentrooperacao(UPDATED_SGL_CENTROOPERACAO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .stsAneel(UPDATED_STS_ANEEL);

        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsModalidadeOperacaoUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsModalidadeOperacaoUsinasEntityToMatchAllProperties(updatedOnsModalidadeOperacaoUsinasEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsModalidadeOperacaoUsinasEntity> onsModalidadeOperacaoUsinasSearchList = Streamable.of(
                    onsModalidadeOperacaoUsinasSearchRepository.findAll()
                ).toList();
                OnsModalidadeOperacaoUsinasEntity testOnsModalidadeOperacaoUsinasSearch = onsModalidadeOperacaoUsinasSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsModalidadeOperacaoUsinasEntityAllPropertiesEquals(
                    testOnsModalidadeOperacaoUsinasSearch,
                    updatedOnsModalidadeOperacaoUsinasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsModalidadeOperacaoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        onsModalidadeOperacaoUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsModalidadeOperacaoUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsModalidadeOperacaoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        onsModalidadeOperacaoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsModalidadeOperacaoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        onsModalidadeOperacaoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsModalidadeOperacaoUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.saveAndFlush(onsModalidadeOperacaoUsinasEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsModalidadeOperacaoUsinas using partial update
        OnsModalidadeOperacaoUsinasEntity partialUpdatedOnsModalidadeOperacaoUsinasEntity = new OnsModalidadeOperacaoUsinasEntity();
        partialUpdatedOnsModalidadeOperacaoUsinasEntity.setId(onsModalidadeOperacaoUsinasEntity.getId());

        partialUpdatedOnsModalidadeOperacaoUsinasEntity
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .sglCentrooperacao(UPDATED_SGL_CENTROOPERACAO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .stsAneel(UPDATED_STS_ANEEL);

        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsModalidadeOperacaoUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsModalidadeOperacaoUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsModalidadeOperacaoUsinasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsModalidadeOperacaoUsinasEntity, onsModalidadeOperacaoUsinasEntity),
            getPersistedOnsModalidadeOperacaoUsinasEntity(onsModalidadeOperacaoUsinasEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsModalidadeOperacaoUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.saveAndFlush(onsModalidadeOperacaoUsinasEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsModalidadeOperacaoUsinas using partial update
        OnsModalidadeOperacaoUsinasEntity partialUpdatedOnsModalidadeOperacaoUsinasEntity = new OnsModalidadeOperacaoUsinasEntity();
        partialUpdatedOnsModalidadeOperacaoUsinasEntity.setId(onsModalidadeOperacaoUsinasEntity.getId());

        partialUpdatedOnsModalidadeOperacaoUsinasEntity
            .nomUsina(UPDATED_NOM_USINA)
            .ceg(UPDATED_CEG)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .valPotenciaautorizada(UPDATED_VAL_POTENCIAAUTORIZADA)
            .sglCentrooperacao(UPDATED_SGL_CENTROOPERACAO)
            .nomPontoconexao(UPDATED_NOM_PONTOCONEXAO)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .stsAneel(UPDATED_STS_ANEEL);

        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsModalidadeOperacaoUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsModalidadeOperacaoUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsModalidadeOperacaoUsinasEntityUpdatableFieldsEquals(
            partialUpdatedOnsModalidadeOperacaoUsinasEntity,
            getPersistedOnsModalidadeOperacaoUsinasEntity(partialUpdatedOnsModalidadeOperacaoUsinasEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsModalidadeOperacaoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        onsModalidadeOperacaoUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsModalidadeOperacaoUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsModalidadeOperacaoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        onsModalidadeOperacaoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsModalidadeOperacaoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        onsModalidadeOperacaoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsModalidadeOperacaoUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsModalidadeOperacaoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsModalidadeOperacaoUsinas() throws Exception {
        // Initialize the database
        insertedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.saveAndFlush(onsModalidadeOperacaoUsinasEntity);
        onsModalidadeOperacaoUsinasRepository.save(onsModalidadeOperacaoUsinasEntity);
        onsModalidadeOperacaoUsinasSearchRepository.save(onsModalidadeOperacaoUsinasEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsModalidadeOperacaoUsinas
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsModalidadeOperacaoUsinasEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsModalidadeOperacaoUsinasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsModalidadeOperacaoUsinas() throws Exception {
        // Initialize the database
        insertedOnsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.saveAndFlush(onsModalidadeOperacaoUsinasEntity);
        onsModalidadeOperacaoUsinasSearchRepository.save(onsModalidadeOperacaoUsinasEntity);

        // Search the onsModalidadeOperacaoUsinas
        restOnsModalidadeOperacaoUsinasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsModalidadeOperacaoUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsModalidadeOperacaoUsinasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].valPotenciaautorizada").value(hasItem(DEFAULT_VAL_POTENCIAAUTORIZADA)))
            .andExpect(jsonPath("$.[*].sglCentrooperacao").value(hasItem(DEFAULT_SGL_CENTROOPERACAO)))
            .andExpect(jsonPath("$.[*].nomPontoconexao").value(hasItem(DEFAULT_NOM_PONTOCONEXAO)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].stsAneel").value(hasItem(DEFAULT_STS_ANEEL)));
    }

    protected long getRepositoryCount() {
        return onsModalidadeOperacaoUsinasRepository.count();
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

    protected OnsModalidadeOperacaoUsinasEntity getPersistedOnsModalidadeOperacaoUsinasEntity(
        OnsModalidadeOperacaoUsinasEntity onsModalidadeOperacaoUsinas
    ) {
        return onsModalidadeOperacaoUsinasRepository.findById(onsModalidadeOperacaoUsinas.getId()).orElseThrow();
    }

    protected void assertPersistedOnsModalidadeOperacaoUsinasEntityToMatchAllProperties(
        OnsModalidadeOperacaoUsinasEntity expectedOnsModalidadeOperacaoUsinasEntity
    ) {
        assertOnsModalidadeOperacaoUsinasEntityAllPropertiesEquals(
            expectedOnsModalidadeOperacaoUsinasEntity,
            getPersistedOnsModalidadeOperacaoUsinasEntity(expectedOnsModalidadeOperacaoUsinasEntity)
        );
    }

    protected void assertPersistedOnsModalidadeOperacaoUsinasEntityToMatchUpdatableProperties(
        OnsModalidadeOperacaoUsinasEntity expectedOnsModalidadeOperacaoUsinasEntity
    ) {
        assertOnsModalidadeOperacaoUsinasEntityAllUpdatablePropertiesEquals(
            expectedOnsModalidadeOperacaoUsinasEntity,
            getPersistedOnsModalidadeOperacaoUsinasEntity(expectedOnsModalidadeOperacaoUsinasEntity)
        );
    }
}
