package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository;
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
 * Integration tests for the {@link OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResourceIT {

    private static final String DEFAULT_DSC_AGREGACAO = "AAAAAAAAAA";
    private static final String UPDATED_DSC_AGREGACAO = "BBBBBBBBBB";

    private static final String DEFAULT_DSC_CARACTERISTICA = "AAAAAAAAAA";
    private static final String UPDATED_DSC_CARACTERISTICA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUM_NPRC_CONCLUIDAS = 1;
    private static final Integer UPDATED_NUM_NPRC_CONCLUIDAS = 2;

    private static final Integer DEFAULT_NUM_NPRP_PROGRAMADAS = 1;
    private static final Integer UPDATED_NUM_NPRP_PROGRAMADAS = 2;

    private static final Integer DEFAULT_NUM_NPRAT_ATRASADAS = 1;
    private static final Integer UPDATED_NUM_NPRAT_ATRASADAS = 2;

    private static final Integer DEFAULT_NUM_NPRA_ANTECIPADAS = 1;
    private static final Integer UPDATED_NUM_NPRA_ANTECIPADAS = 2;

    private static final Integer DEFAULT_NUM_NPRCP_CONCLUIDAS_PRAZO = 1;
    private static final Integer UPDATED_NUM_NPRCP_CONCLUIDAS_PRAZO = 2;

    private static final Double DEFAULT_VAL_ECPA = 1D;
    private static final Double UPDATED_VAL_ECPA = 2D;

    private static final Double DEFAULT_VAL_PCPA = 1D;
    private static final Double UPDATED_VAL_PCPA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository;

    @Autowired
    private OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc;

    private OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity;

    private OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity createEntity() {
        return new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity()
            .dscAgregacao(DEFAULT_DSC_AGREGACAO)
            .dscCaracteristica(DEFAULT_DSC_CARACTERISTICA)
            .dinReferencia(DEFAULT_DIN_REFERENCIA)
            .numNprcConcluidas(DEFAULT_NUM_NPRC_CONCLUIDAS)
            .numNprpProgramadas(DEFAULT_NUM_NPRP_PROGRAMADAS)
            .numNpratAtrasadas(DEFAULT_NUM_NPRAT_ATRASADAS)
            .numNpraAntecipadas(DEFAULT_NUM_NPRA_ANTECIPADAS)
            .numNprcpConcluidasPrazo(DEFAULT_NUM_NPRCP_CONCLUIDAS_PRAZO)
            .valEcpa(DEFAULT_VAL_ECPA)
            .valPcpa(DEFAULT_VAL_PCPA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity createUpdatedEntity() {
        return new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity()
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numNprcConcluidas(UPDATED_NUM_NPRC_CONCLUIDAS)
            .numNprpProgramadas(UPDATED_NUM_NPRP_PROGRAMADAS)
            .numNpratAtrasadas(UPDATED_NUM_NPRAT_ATRASADAS)
            .numNpraAntecipadas(UPDATED_NUM_NPRA_ANTECIPADAS)
            .numNprcpConcluidasPrazo(UPDATED_NUM_NPRCP_CONCLUIDAS_PRAZO)
            .valEcpa(UPDATED_VAL_ECPA)
            .valPcpa(UPDATED_VAL_PCPA);
    }

    @BeforeEach
    public void initTest() {
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity != null) {
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.delete(
                insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.delete(
                insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );
            insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        // Create the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
        var returnedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity = om.readValue(
            restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.class
        );

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityUpdatableFieldsEquals(
            returnedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity,
            getPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity(
                returnedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            returnedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity;
    }

    @Test
    @Transactional
    void createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaWithExistingId() throws Exception {
        // Create the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa with an existing ID
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.saveAndFlush(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );

        // Get all the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaList
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].numNprcConcluidas").value(hasItem(DEFAULT_NUM_NPRC_CONCLUIDAS)))
            .andExpect(jsonPath("$.[*].numNprpProgramadas").value(hasItem(DEFAULT_NUM_NPRP_PROGRAMADAS)))
            .andExpect(jsonPath("$.[*].numNpratAtrasadas").value(hasItem(DEFAULT_NUM_NPRAT_ATRASADAS)))
            .andExpect(jsonPath("$.[*].numNpraAntecipadas").value(hasItem(DEFAULT_NUM_NPRA_ANTECIPADAS)))
            .andExpect(jsonPath("$.[*].numNprcpConcluidasPrazo").value(hasItem(DEFAULT_NUM_NPRCP_CONCLUIDAS_PRAZO)))
            .andExpect(jsonPath("$.[*].valEcpa").value(hasItem(DEFAULT_VAL_ECPA)))
            .andExpect(jsonPath("$.[*].valPcpa").value(hasItem(DEFAULT_VAL_PCPA)));
    }

    @Test
    @Transactional
    void getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.saveAndFlush(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );

        // Get the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId().intValue()))
            .andExpect(jsonPath("$.dscAgregacao").value(DEFAULT_DSC_AGREGACAO))
            .andExpect(jsonPath("$.dscCaracteristica").value(DEFAULT_DSC_CARACTERISTICA))
            .andExpect(jsonPath("$.dinReferencia").value(DEFAULT_DIN_REFERENCIA.toString()))
            .andExpect(jsonPath("$.numNprcConcluidas").value(DEFAULT_NUM_NPRC_CONCLUIDAS))
            .andExpect(jsonPath("$.numNprpProgramadas").value(DEFAULT_NUM_NPRP_PROGRAMADAS))
            .andExpect(jsonPath("$.numNpratAtrasadas").value(DEFAULT_NUM_NPRAT_ATRASADAS))
            .andExpect(jsonPath("$.numNpraAntecipadas").value(DEFAULT_NUM_NPRA_ANTECIPADAS))
            .andExpect(jsonPath("$.numNprcpConcluidasPrazo").value(DEFAULT_NUM_NPRCP_CONCLUIDAS_PRAZO))
            .andExpect(jsonPath("$.valEcpa").value(DEFAULT_VAL_ECPA))
            .andExpect(jsonPath("$.valPcpa").value(DEFAULT_VAL_PCPA));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        // Get the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.saveAndFlush(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.save(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());

        // Update the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository
                .findById(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity are not directly saved in db
        em.detach(updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);
        updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numNprcConcluidas(UPDATED_NUM_NPRC_CONCLUIDAS)
            .numNprpProgramadas(UPDATED_NUM_NPRP_PROGRAMADAS)
            .numNpratAtrasadas(UPDATED_NUM_NPRAT_ATRASADAS)
            .numNpraAntecipadas(UPDATED_NUM_NPRA_ANTECIPADAS)
            .numNprcpConcluidasPrazo(UPDATED_NUM_NPRCP_CONCLUIDAS_PRAZO)
            .valEcpa(UPDATED_VAL_ECPA)
            .valPcpa(UPDATED_VAL_PCPA);

        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityToMatchAllProperties(
            updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
                > onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchList = Streamable.of(
                    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll()
                ).toList();
                OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity testOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearch =
                    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityAllPropertiesEquals(
                    testOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearch,
                    updatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.saveAndFlush(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa using partial update
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity();
        partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId()
        );

        partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .numNprcConcluidas(UPDATED_NUM_NPRC_CONCLUIDAS)
            .valEcpa(UPDATED_VAL_ECPA);

        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity,
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            ),
            getPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.saveAndFlush(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa using partial update
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            new OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity();
        partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId()
        );

        partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            .dscAgregacao(UPDATED_DSC_AGREGACAO)
            .dscCaracteristica(UPDATED_DSC_CARACTERISTICA)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numNprcConcluidas(UPDATED_NUM_NPRC_CONCLUIDAS)
            .numNprpProgramadas(UPDATED_NUM_NPRP_PROGRAMADAS)
            .numNpratAtrasadas(UPDATED_NUM_NPRAT_ATRASADAS)
            .numNpraAntecipadas(UPDATED_NUM_NPRA_ANTECIPADAS)
            .numNprcpConcluidasPrazo(UPDATED_NUM_NPRCP_CONCLUIDAS_PRAZO)
            .valEcpa(UPDATED_VAL_ECPA)
            .valPcpa(UPDATED_VAL_PCPA);

        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity,
            getPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity(
                partialUpdatedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.saveAndFlush(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.save(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.save(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.saveAndFlush(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            );
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.save(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);

        // Search the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
        restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dscAgregacao").value(hasItem(DEFAULT_DSC_AGREGACAO)))
            .andExpect(jsonPath("$.[*].dscCaracteristica").value(hasItem(DEFAULT_DSC_CARACTERISTICA)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].numNprcConcluidas").value(hasItem(DEFAULT_NUM_NPRC_CONCLUIDAS)))
            .andExpect(jsonPath("$.[*].numNprpProgramadas").value(hasItem(DEFAULT_NUM_NPRP_PROGRAMADAS)))
            .andExpect(jsonPath("$.[*].numNpratAtrasadas").value(hasItem(DEFAULT_NUM_NPRAT_ATRASADAS)))
            .andExpect(jsonPath("$.[*].numNpraAntecipadas").value(hasItem(DEFAULT_NUM_NPRA_ANTECIPADAS)))
            .andExpect(jsonPath("$.[*].numNprcpConcluidasPrazo").value(hasItem(DEFAULT_NUM_NPRCP_CONCLUIDAS_PRAZO)))
            .andExpect(jsonPath("$.[*].valEcpa").value(hasItem(DEFAULT_VAL_ECPA)))
            .andExpect(jsonPath("$.[*].valPcpa").value(hasItem(DEFAULT_VAL_PCPA)));
    }

    protected long getRepositoryCount() {
        return onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.count();
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

    protected OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity getPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity(
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
    ) {
        return onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository
            .findById(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityToMatchAllProperties(
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity expectedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    ) {
        assertOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityAllPropertiesEquals(
            expectedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity,
            getPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity(
                expectedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            )
        );
    }

    protected void assertPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityToMatchUpdatableProperties(
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity expectedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    ) {
        assertOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity,
            getPersistedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity(
                expectedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
            )
        );
    }
}
