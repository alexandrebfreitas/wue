package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoUsinaEmBaseHorariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsGeracaoUsinaEmBaseHorariaSearchRepository;
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
 * Integration tests for the {@link OnsGeracaoUsinaEmBaseHorariaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsGeracaoUsinaEmBaseHorariaResourceIT {

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_MODALIDADEOPERACAO = "AAAAAAAAAA";
    private static final String UPDATED_COD_MODALIDADEOPERACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_TIPOCOMBUSTIVEL = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TIPOCOMBUSTIVEL = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ONS = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_GERACAO = 1D;
    private static final Double UPDATED_VAL_GERACAO = 2D;

    private static final String ENTITY_API_URL = "/api/ons-geracao-usina-em-base-horarias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-geracao-usina-em-base-horarias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsGeracaoUsinaEmBaseHorariaRepository onsGeracaoUsinaEmBaseHorariaRepository;

    @Autowired
    private OnsGeracaoUsinaEmBaseHorariaSearchRepository onsGeracaoUsinaEmBaseHorariaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsGeracaoUsinaEmBaseHorariaMockMvc;

    private OnsGeracaoUsinaEmBaseHorariaEntity onsGeracaoUsinaEmBaseHorariaEntity;

    private OnsGeracaoUsinaEmBaseHorariaEntity insertedOnsGeracaoUsinaEmBaseHorariaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsGeracaoUsinaEmBaseHorariaEntity createEntity() {
        return new OnsGeracaoUsinaEmBaseHorariaEntity()
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .codModalidadeoperacao(DEFAULT_COD_MODALIDADEOPERACAO)
            .nomTipousina(DEFAULT_NOM_TIPOUSINA)
            .nomTipocombustivel(DEFAULT_NOM_TIPOCOMBUSTIVEL)
            .nomUsina(DEFAULT_NOM_USINA)
            .idOns(DEFAULT_ID_ONS)
            .ceg(DEFAULT_CEG)
            .valGeracao(DEFAULT_VAL_GERACAO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsGeracaoUsinaEmBaseHorariaEntity createUpdatedEntity() {
        return new OnsGeracaoUsinaEmBaseHorariaEntity()
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .codModalidadeoperacao(UPDATED_COD_MODALIDADEOPERACAO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .nomUsina(UPDATED_NOM_USINA)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valGeracao(UPDATED_VAL_GERACAO);
    }

    @BeforeEach
    public void initTest() {
        onsGeracaoUsinaEmBaseHorariaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsGeracaoUsinaEmBaseHorariaEntity != null) {
            onsGeracaoUsinaEmBaseHorariaRepository.delete(insertedOnsGeracaoUsinaEmBaseHorariaEntity);
            onsGeracaoUsinaEmBaseHorariaSearchRepository.delete(insertedOnsGeracaoUsinaEmBaseHorariaEntity);
            insertedOnsGeracaoUsinaEmBaseHorariaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        // Create the OnsGeracaoUsinaEmBaseHoraria
        var returnedOnsGeracaoUsinaEmBaseHorariaEntity = om.readValue(
            restOnsGeracaoUsinaEmBaseHorariaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsGeracaoUsinaEmBaseHorariaEntity.class
        );

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsGeracaoUsinaEmBaseHorariaEntityUpdatableFieldsEquals(
            returnedOnsGeracaoUsinaEmBaseHorariaEntity,
            getPersistedOnsGeracaoUsinaEmBaseHorariaEntity(returnedOnsGeracaoUsinaEmBaseHorariaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsGeracaoUsinaEmBaseHorariaEntity = returnedOnsGeracaoUsinaEmBaseHorariaEntity;
    }

    @Test
    @Transactional
    void createOnsGeracaoUsinaEmBaseHorariaWithExistingId() throws Exception {
        // Create the OnsGeracaoUsinaEmBaseHoraria with an existing ID
        onsGeracaoUsinaEmBaseHorariaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsGeracaoUsinaEmBaseHorarias() throws Exception {
        // Initialize the database
        insertedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.saveAndFlush(
            onsGeracaoUsinaEmBaseHorariaEntity
        );

        // Get all the onsGeracaoUsinaEmBaseHorariaList
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsGeracaoUsinaEmBaseHorariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].codModalidadeoperacao").value(hasItem(DEFAULT_COD_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomTipousina").value(hasItem(DEFAULT_NOM_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomTipocombustivel").value(hasItem(DEFAULT_NOM_TIPOCOMBUSTIVEL)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valGeracao").value(hasItem(DEFAULT_VAL_GERACAO)));
    }

    @Test
    @Transactional
    void getOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.saveAndFlush(
            onsGeracaoUsinaEmBaseHorariaEntity
        );

        // Get the onsGeracaoUsinaEmBaseHoraria
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsGeracaoUsinaEmBaseHorariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsGeracaoUsinaEmBaseHorariaEntity.getId().intValue()))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.codModalidadeoperacao").value(DEFAULT_COD_MODALIDADEOPERACAO))
            .andExpect(jsonPath("$.nomTipousina").value(DEFAULT_NOM_TIPOUSINA))
            .andExpect(jsonPath("$.nomTipocombustivel").value(DEFAULT_NOM_TIPOCOMBUSTIVEL))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.idOns").value(DEFAULT_ID_ONS))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.valGeracao").value(DEFAULT_VAL_GERACAO));
    }

    @Test
    @Transactional
    void getNonExistingOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        // Get the onsGeracaoUsinaEmBaseHoraria
        restOnsGeracaoUsinaEmBaseHorariaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.saveAndFlush(
            onsGeracaoUsinaEmBaseHorariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsGeracaoUsinaEmBaseHorariaSearchRepository.save(onsGeracaoUsinaEmBaseHorariaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());

        // Update the onsGeracaoUsinaEmBaseHoraria
        OnsGeracaoUsinaEmBaseHorariaEntity updatedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository
            .findById(onsGeracaoUsinaEmBaseHorariaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsGeracaoUsinaEmBaseHorariaEntity are not directly saved in db
        em.detach(updatedOnsGeracaoUsinaEmBaseHorariaEntity);
        updatedOnsGeracaoUsinaEmBaseHorariaEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .codModalidadeoperacao(UPDATED_COD_MODALIDADEOPERACAO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .nomUsina(UPDATED_NOM_USINA)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valGeracao(UPDATED_VAL_GERACAO);

        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsGeracaoUsinaEmBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsGeracaoUsinaEmBaseHorariaEntityToMatchAllProperties(updatedOnsGeracaoUsinaEmBaseHorariaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsGeracaoUsinaEmBaseHorariaEntity> onsGeracaoUsinaEmBaseHorariaSearchList = Streamable.of(
                    onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll()
                ).toList();
                OnsGeracaoUsinaEmBaseHorariaEntity testOnsGeracaoUsinaEmBaseHorariaSearch = onsGeracaoUsinaEmBaseHorariaSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsGeracaoUsinaEmBaseHorariaEntityAllPropertiesEquals(
                    testOnsGeracaoUsinaEmBaseHorariaSearch,
                    updatedOnsGeracaoUsinaEmBaseHorariaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        onsGeracaoUsinaEmBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsGeracaoUsinaEmBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        onsGeracaoUsinaEmBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        onsGeracaoUsinaEmBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsGeracaoUsinaEmBaseHorariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.saveAndFlush(
            onsGeracaoUsinaEmBaseHorariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsGeracaoUsinaEmBaseHoraria using partial update
        OnsGeracaoUsinaEmBaseHorariaEntity partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity = new OnsGeracaoUsinaEmBaseHorariaEntity();
        partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity.setId(onsGeracaoUsinaEmBaseHorariaEntity.getId());

        partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity
            .nomEstado(UPDATED_NOM_ESTADO)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .idOns(UPDATED_ID_ONS);

        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsGeracaoUsinaEmBaseHorariaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity, onsGeracaoUsinaEmBaseHorariaEntity),
            getPersistedOnsGeracaoUsinaEmBaseHorariaEntity(onsGeracaoUsinaEmBaseHorariaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsGeracaoUsinaEmBaseHorariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.saveAndFlush(
            onsGeracaoUsinaEmBaseHorariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsGeracaoUsinaEmBaseHoraria using partial update
        OnsGeracaoUsinaEmBaseHorariaEntity partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity = new OnsGeracaoUsinaEmBaseHorariaEntity();
        partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity.setId(onsGeracaoUsinaEmBaseHorariaEntity.getId());

        partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .codModalidadeoperacao(UPDATED_COD_MODALIDADEOPERACAO)
            .nomTipousina(UPDATED_NOM_TIPOUSINA)
            .nomTipocombustivel(UPDATED_NOM_TIPOCOMBUSTIVEL)
            .nomUsina(UPDATED_NOM_USINA)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valGeracao(UPDATED_VAL_GERACAO);

        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsGeracaoUsinaEmBaseHorariaEntityUpdatableFieldsEquals(
            partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity,
            getPersistedOnsGeracaoUsinaEmBaseHorariaEntity(partialUpdatedOnsGeracaoUsinaEmBaseHorariaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        onsGeracaoUsinaEmBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsGeracaoUsinaEmBaseHorariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        onsGeracaoUsinaEmBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        onsGeracaoUsinaEmBaseHorariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsGeracaoUsinaEmBaseHorariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsGeracaoUsinaEmBaseHoraria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.saveAndFlush(
            onsGeracaoUsinaEmBaseHorariaEntity
        );
        onsGeracaoUsinaEmBaseHorariaRepository.save(onsGeracaoUsinaEmBaseHorariaEntity);
        onsGeracaoUsinaEmBaseHorariaSearchRepository.save(onsGeracaoUsinaEmBaseHorariaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsGeracaoUsinaEmBaseHoraria
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsGeracaoUsinaEmBaseHorariaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsGeracaoUsinaEmBaseHorariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsGeracaoUsinaEmBaseHoraria() throws Exception {
        // Initialize the database
        insertedOnsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.saveAndFlush(
            onsGeracaoUsinaEmBaseHorariaEntity
        );
        onsGeracaoUsinaEmBaseHorariaSearchRepository.save(onsGeracaoUsinaEmBaseHorariaEntity);

        // Search the onsGeracaoUsinaEmBaseHoraria
        restOnsGeracaoUsinaEmBaseHorariaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsGeracaoUsinaEmBaseHorariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsGeracaoUsinaEmBaseHorariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].codModalidadeoperacao").value(hasItem(DEFAULT_COD_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].nomTipousina").value(hasItem(DEFAULT_NOM_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].nomTipocombustivel").value(hasItem(DEFAULT_NOM_TIPOCOMBUSTIVEL)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valGeracao").value(hasItem(DEFAULT_VAL_GERACAO)));
    }

    protected long getRepositoryCount() {
        return onsGeracaoUsinaEmBaseHorariaRepository.count();
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

    protected OnsGeracaoUsinaEmBaseHorariaEntity getPersistedOnsGeracaoUsinaEmBaseHorariaEntity(
        OnsGeracaoUsinaEmBaseHorariaEntity onsGeracaoUsinaEmBaseHoraria
    ) {
        return onsGeracaoUsinaEmBaseHorariaRepository.findById(onsGeracaoUsinaEmBaseHoraria.getId()).orElseThrow();
    }

    protected void assertPersistedOnsGeracaoUsinaEmBaseHorariaEntityToMatchAllProperties(
        OnsGeracaoUsinaEmBaseHorariaEntity expectedOnsGeracaoUsinaEmBaseHorariaEntity
    ) {
        assertOnsGeracaoUsinaEmBaseHorariaEntityAllPropertiesEquals(
            expectedOnsGeracaoUsinaEmBaseHorariaEntity,
            getPersistedOnsGeracaoUsinaEmBaseHorariaEntity(expectedOnsGeracaoUsinaEmBaseHorariaEntity)
        );
    }

    protected void assertPersistedOnsGeracaoUsinaEmBaseHorariaEntityToMatchUpdatableProperties(
        OnsGeracaoUsinaEmBaseHorariaEntity expectedOnsGeracaoUsinaEmBaseHorariaEntity
    ) {
        assertOnsGeracaoUsinaEmBaseHorariaEntityAllUpdatablePropertiesEquals(
            expectedOnsGeracaoUsinaEmBaseHorariaEntity,
            getPersistedOnsGeracaoUsinaEmBaseHorariaEntity(expectedOnsGeracaoUsinaEmBaseHorariaEntity)
        );
    }
}
