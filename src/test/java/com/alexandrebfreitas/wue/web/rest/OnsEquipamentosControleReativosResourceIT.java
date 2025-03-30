package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEquipamentosControleReativosEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEquipamentosControleReativosEntity;
import com.alexandrebfreitas.wue.repository.OnsEquipamentosControleReativosRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEquipamentosControleReativosSearchRepository;
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
 * Integration tests for the {@link OnsEquipamentosControleReativosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEquipamentosControleReativosResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBESTACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBESTACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_AGENTE_PROPRIETARIO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AGENTE_PROPRIETARIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPODEREDE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPODEREDE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPOEQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOEQUIPAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_EQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_EQUIPAMENTO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_POTREATIVANOMINAL_MVAR = 1D;
    private static final Double UPDATED_VAL_POTREATIVANOMINAL_MVAR = 2D;

    private static final Double DEFAULT_VAL_LIMITEINFERIOR_MVAR = 1D;
    private static final Double UPDATED_VAL_LIMITEINFERIOR_MVAR = 2D;

    private static final Double DEFAULT_VAL_LIMITESUPERIOR_MVAR = 1D;
    private static final Double UPDATED_VAL_LIMITESUPERIOR_MVAR = 2D;

    private static final LocalDate DEFAULT_DAT_ENTRADAOPERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_ENTRADAOPERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_DESATIVACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_DESATIVACAO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COD_EQUIPAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COD_EQUIPAMENTO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ons-equipamentos-controle-reativos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-equipamentos-controle-reativos/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEquipamentosControleReativosRepository onsEquipamentosControleReativosRepository;

    @Autowired
    private OnsEquipamentosControleReativosSearchRepository onsEquipamentosControleReativosSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEquipamentosControleReativosMockMvc;

    private OnsEquipamentosControleReativosEntity onsEquipamentosControleReativosEntity;

    private OnsEquipamentosControleReativosEntity insertedOnsEquipamentosControleReativosEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEquipamentosControleReativosEntity createEntity() {
        return new OnsEquipamentosControleReativosEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .nomSubestacao(DEFAULT_NOM_SUBESTACAO)
            .nomAgenteProprietario(DEFAULT_NOM_AGENTE_PROPRIETARIO)
            .nomTipoderede(DEFAULT_NOM_TIPODEREDE)
            .nomTipoequipamento(DEFAULT_NOM_TIPOEQUIPAMENTO)
            .nomEquipamento(DEFAULT_NOM_EQUIPAMENTO)
            .valPotreativanominalMvar(DEFAULT_VAL_POTREATIVANOMINAL_MVAR)
            .valLimiteinferiorMvar(DEFAULT_VAL_LIMITEINFERIOR_MVAR)
            .valLimitesuperiorMvar(DEFAULT_VAL_LIMITESUPERIOR_MVAR)
            .datEntradaoperacao(DEFAULT_DAT_ENTRADAOPERACAO)
            .datDesativacao(DEFAULT_DAT_DESATIVACAO)
            .codEquipamento(DEFAULT_COD_EQUIPAMENTO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEquipamentosControleReativosEntity createUpdatedEntity() {
        return new OnsEquipamentosControleReativosEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomSubestacao(UPDATED_NOM_SUBESTACAO)
            .nomAgenteProprietario(UPDATED_NOM_AGENTE_PROPRIETARIO)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .nomTipoequipamento(UPDATED_NOM_TIPOEQUIPAMENTO)
            .nomEquipamento(UPDATED_NOM_EQUIPAMENTO)
            .valPotreativanominalMvar(UPDATED_VAL_POTREATIVANOMINAL_MVAR)
            .valLimiteinferiorMvar(UPDATED_VAL_LIMITEINFERIOR_MVAR)
            .valLimitesuperiorMvar(UPDATED_VAL_LIMITESUPERIOR_MVAR)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO);
    }

    @BeforeEach
    public void initTest() {
        onsEquipamentosControleReativosEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEquipamentosControleReativosEntity != null) {
            onsEquipamentosControleReativosRepository.delete(insertedOnsEquipamentosControleReativosEntity);
            onsEquipamentosControleReativosSearchRepository.delete(insertedOnsEquipamentosControleReativosEntity);
            insertedOnsEquipamentosControleReativosEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEquipamentosControleReativos() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        // Create the OnsEquipamentosControleReativos
        var returnedOnsEquipamentosControleReativosEntity = om.readValue(
            restOnsEquipamentosControleReativosMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEquipamentosControleReativosEntity.class
        );

        // Validate the OnsEquipamentosControleReativos in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEquipamentosControleReativosEntityUpdatableFieldsEquals(
            returnedOnsEquipamentosControleReativosEntity,
            getPersistedOnsEquipamentosControleReativosEntity(returnedOnsEquipamentosControleReativosEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEquipamentosControleReativosEntity = returnedOnsEquipamentosControleReativosEntity;
    }

    @Test
    @Transactional
    void createOnsEquipamentosControleReativosWithExistingId() throws Exception {
        // Create the OnsEquipamentosControleReativos with an existing ID
        onsEquipamentosControleReativosEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEquipamentosControleReativos() throws Exception {
        // Initialize the database
        insertedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.saveAndFlush(
            onsEquipamentosControleReativosEntity
        );

        // Get all the onsEquipamentosControleReativosList
        restOnsEquipamentosControleReativosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEquipamentosControleReativosEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomSubestacao").value(hasItem(DEFAULT_NOM_SUBESTACAO)))
            .andExpect(jsonPath("$.[*].nomAgenteProprietario").value(hasItem(DEFAULT_NOM_AGENTE_PROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomTipoderede").value(hasItem(DEFAULT_NOM_TIPODEREDE)))
            .andExpect(jsonPath("$.[*].nomTipoequipamento").value(hasItem(DEFAULT_NOM_TIPOEQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].nomEquipamento").value(hasItem(DEFAULT_NOM_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].valPotreativanominalMvar").value(hasItem(DEFAULT_VAL_POTREATIVANOMINAL_MVAR)))
            .andExpect(jsonPath("$.[*].valLimiteinferiorMvar").value(hasItem(DEFAULT_VAL_LIMITEINFERIOR_MVAR)))
            .andExpect(jsonPath("$.[*].valLimitesuperiorMvar").value(hasItem(DEFAULT_VAL_LIMITESUPERIOR_MVAR)))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)));
    }

    @Test
    @Transactional
    void getOnsEquipamentosControleReativos() throws Exception {
        // Initialize the database
        insertedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.saveAndFlush(
            onsEquipamentosControleReativosEntity
        );

        // Get the onsEquipamentosControleReativos
        restOnsEquipamentosControleReativosMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEquipamentosControleReativosEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEquipamentosControleReativosEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.nomSubestacao").value(DEFAULT_NOM_SUBESTACAO))
            .andExpect(jsonPath("$.nomAgenteProprietario").value(DEFAULT_NOM_AGENTE_PROPRIETARIO))
            .andExpect(jsonPath("$.nomTipoderede").value(DEFAULT_NOM_TIPODEREDE))
            .andExpect(jsonPath("$.nomTipoequipamento").value(DEFAULT_NOM_TIPOEQUIPAMENTO))
            .andExpect(jsonPath("$.nomEquipamento").value(DEFAULT_NOM_EQUIPAMENTO))
            .andExpect(jsonPath("$.valPotreativanominalMvar").value(DEFAULT_VAL_POTREATIVANOMINAL_MVAR))
            .andExpect(jsonPath("$.valLimiteinferiorMvar").value(DEFAULT_VAL_LIMITEINFERIOR_MVAR))
            .andExpect(jsonPath("$.valLimitesuperiorMvar").value(DEFAULT_VAL_LIMITESUPERIOR_MVAR))
            .andExpect(jsonPath("$.datEntradaoperacao").value(DEFAULT_DAT_ENTRADAOPERACAO.toString()))
            .andExpect(jsonPath("$.datDesativacao").value(DEFAULT_DAT_DESATIVACAO.toString()))
            .andExpect(jsonPath("$.codEquipamento").value(DEFAULT_COD_EQUIPAMENTO));
    }

    @Test
    @Transactional
    void getNonExistingOnsEquipamentosControleReativos() throws Exception {
        // Get the onsEquipamentosControleReativos
        restOnsEquipamentosControleReativosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEquipamentosControleReativos() throws Exception {
        // Initialize the database
        insertedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.saveAndFlush(
            onsEquipamentosControleReativosEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEquipamentosControleReativosSearchRepository.save(onsEquipamentosControleReativosEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());

        // Update the onsEquipamentosControleReativos
        OnsEquipamentosControleReativosEntity updatedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository
            .findById(onsEquipamentosControleReativosEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEquipamentosControleReativosEntity are not directly saved in db
        em.detach(updatedOnsEquipamentosControleReativosEntity);
        updatedOnsEquipamentosControleReativosEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomSubestacao(UPDATED_NOM_SUBESTACAO)
            .nomAgenteProprietario(UPDATED_NOM_AGENTE_PROPRIETARIO)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .nomTipoequipamento(UPDATED_NOM_TIPOEQUIPAMENTO)
            .nomEquipamento(UPDATED_NOM_EQUIPAMENTO)
            .valPotreativanominalMvar(UPDATED_VAL_POTREATIVANOMINAL_MVAR)
            .valLimiteinferiorMvar(UPDATED_VAL_LIMITEINFERIOR_MVAR)
            .valLimitesuperiorMvar(UPDATED_VAL_LIMITESUPERIOR_MVAR)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO);

        restOnsEquipamentosControleReativosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEquipamentosControleReativosEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEquipamentosControleReativosEntityToMatchAllProperties(updatedOnsEquipamentosControleReativosEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEquipamentosControleReativosEntity> onsEquipamentosControleReativosSearchList = Streamable.of(
                    onsEquipamentosControleReativosSearchRepository.findAll()
                ).toList();
                OnsEquipamentosControleReativosEntity testOnsEquipamentosControleReativosSearch =
                    onsEquipamentosControleReativosSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsEquipamentosControleReativosEntityAllPropertiesEquals(
                    testOnsEquipamentosControleReativosSearch,
                    updatedOnsEquipamentosControleReativosEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEquipamentosControleReativos() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        onsEquipamentosControleReativosEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEquipamentosControleReativosEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEquipamentosControleReativos() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        onsEquipamentosControleReativosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEquipamentosControleReativos() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        onsEquipamentosControleReativosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEquipamentosControleReativosWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.saveAndFlush(
            onsEquipamentosControleReativosEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEquipamentosControleReativos using partial update
        OnsEquipamentosControleReativosEntity partialUpdatedOnsEquipamentosControleReativosEntity =
            new OnsEquipamentosControleReativosEntity();
        partialUpdatedOnsEquipamentosControleReativosEntity.setId(onsEquipamentosControleReativosEntity.getId());

        partialUpdatedOnsEquipamentosControleReativosEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomTipoequipamento(UPDATED_NOM_TIPOEQUIPAMENTO)
            .nomEquipamento(UPDATED_NOM_EQUIPAMENTO)
            .valLimiteinferiorMvar(UPDATED_VAL_LIMITEINFERIOR_MVAR)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO);

        restOnsEquipamentosControleReativosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEquipamentosControleReativosEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEquipamentosControleReativos in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEquipamentosControleReativosEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsEquipamentosControleReativosEntity, onsEquipamentosControleReativosEntity),
            getPersistedOnsEquipamentosControleReativosEntity(onsEquipamentosControleReativosEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEquipamentosControleReativosWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.saveAndFlush(
            onsEquipamentosControleReativosEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEquipamentosControleReativos using partial update
        OnsEquipamentosControleReativosEntity partialUpdatedOnsEquipamentosControleReativosEntity =
            new OnsEquipamentosControleReativosEntity();
        partialUpdatedOnsEquipamentosControleReativosEntity.setId(onsEquipamentosControleReativosEntity.getId());

        partialUpdatedOnsEquipamentosControleReativosEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomSubestacao(UPDATED_NOM_SUBESTACAO)
            .nomAgenteProprietario(UPDATED_NOM_AGENTE_PROPRIETARIO)
            .nomTipoderede(UPDATED_NOM_TIPODEREDE)
            .nomTipoequipamento(UPDATED_NOM_TIPOEQUIPAMENTO)
            .nomEquipamento(UPDATED_NOM_EQUIPAMENTO)
            .valPotreativanominalMvar(UPDATED_VAL_POTREATIVANOMINAL_MVAR)
            .valLimiteinferiorMvar(UPDATED_VAL_LIMITEINFERIOR_MVAR)
            .valLimitesuperiorMvar(UPDATED_VAL_LIMITESUPERIOR_MVAR)
            .datEntradaoperacao(UPDATED_DAT_ENTRADAOPERACAO)
            .datDesativacao(UPDATED_DAT_DESATIVACAO)
            .codEquipamento(UPDATED_COD_EQUIPAMENTO);

        restOnsEquipamentosControleReativosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEquipamentosControleReativosEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEquipamentosControleReativos in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEquipamentosControleReativosEntityUpdatableFieldsEquals(
            partialUpdatedOnsEquipamentosControleReativosEntity,
            getPersistedOnsEquipamentosControleReativosEntity(partialUpdatedOnsEquipamentosControleReativosEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEquipamentosControleReativos() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        onsEquipamentosControleReativosEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEquipamentosControleReativosEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEquipamentosControleReativos() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        onsEquipamentosControleReativosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEquipamentosControleReativos() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        onsEquipamentosControleReativosEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEquipamentosControleReativosEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEquipamentosControleReativos in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEquipamentosControleReativos() throws Exception {
        // Initialize the database
        insertedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.saveAndFlush(
            onsEquipamentosControleReativosEntity
        );
        onsEquipamentosControleReativosRepository.save(onsEquipamentosControleReativosEntity);
        onsEquipamentosControleReativosSearchRepository.save(onsEquipamentosControleReativosEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEquipamentosControleReativos
        restOnsEquipamentosControleReativosMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsEquipamentosControleReativosEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEquipamentosControleReativosSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEquipamentosControleReativos() throws Exception {
        // Initialize the database
        insertedOnsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.saveAndFlush(
            onsEquipamentosControleReativosEntity
        );
        onsEquipamentosControleReativosSearchRepository.save(onsEquipamentosControleReativosEntity);

        // Search the onsEquipamentosControleReativos
        restOnsEquipamentosControleReativosMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEquipamentosControleReativosEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEquipamentosControleReativosEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].nomSubestacao").value(hasItem(DEFAULT_NOM_SUBESTACAO)))
            .andExpect(jsonPath("$.[*].nomAgenteProprietario").value(hasItem(DEFAULT_NOM_AGENTE_PROPRIETARIO)))
            .andExpect(jsonPath("$.[*].nomTipoderede").value(hasItem(DEFAULT_NOM_TIPODEREDE)))
            .andExpect(jsonPath("$.[*].nomTipoequipamento").value(hasItem(DEFAULT_NOM_TIPOEQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].nomEquipamento").value(hasItem(DEFAULT_NOM_EQUIPAMENTO)))
            .andExpect(jsonPath("$.[*].valPotreativanominalMvar").value(hasItem(DEFAULT_VAL_POTREATIVANOMINAL_MVAR)))
            .andExpect(jsonPath("$.[*].valLimiteinferiorMvar").value(hasItem(DEFAULT_VAL_LIMITEINFERIOR_MVAR)))
            .andExpect(jsonPath("$.[*].valLimitesuperiorMvar").value(hasItem(DEFAULT_VAL_LIMITESUPERIOR_MVAR)))
            .andExpect(jsonPath("$.[*].datEntradaoperacao").value(hasItem(DEFAULT_DAT_ENTRADAOPERACAO.toString())))
            .andExpect(jsonPath("$.[*].datDesativacao").value(hasItem(DEFAULT_DAT_DESATIVACAO.toString())))
            .andExpect(jsonPath("$.[*].codEquipamento").value(hasItem(DEFAULT_COD_EQUIPAMENTO)));
    }

    protected long getRepositoryCount() {
        return onsEquipamentosControleReativosRepository.count();
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

    protected OnsEquipamentosControleReativosEntity getPersistedOnsEquipamentosControleReativosEntity(
        OnsEquipamentosControleReativosEntity onsEquipamentosControleReativos
    ) {
        return onsEquipamentosControleReativosRepository.findById(onsEquipamentosControleReativos.getId()).orElseThrow();
    }

    protected void assertPersistedOnsEquipamentosControleReativosEntityToMatchAllProperties(
        OnsEquipamentosControleReativosEntity expectedOnsEquipamentosControleReativosEntity
    ) {
        assertOnsEquipamentosControleReativosEntityAllPropertiesEquals(
            expectedOnsEquipamentosControleReativosEntity,
            getPersistedOnsEquipamentosControleReativosEntity(expectedOnsEquipamentosControleReativosEntity)
        );
    }

    protected void assertPersistedOnsEquipamentosControleReativosEntityToMatchUpdatableProperties(
        OnsEquipamentosControleReativosEntity expectedOnsEquipamentosControleReativosEntity
    ) {
        assertOnsEquipamentosControleReativosEntityAllUpdatablePropertiesEquals(
            expectedOnsEquipamentosControleReativosEntity,
            getPersistedOnsEquipamentosControleReativosEntity(expectedOnsEquipamentosControleReativosEntity)
        );
    }
}
