package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository;
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
 * Integration tests for the {@link OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResourceIT {

    private static final String DEFAULT_COD_TIPOAGREGACAO = "AAAAAAAAAA";
    private static final String UPDATED_COD_TIPOAGREGACAO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_PERIODICIDADE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PERIODICIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_AGREGACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AGREGACAO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUM_TRANSFORMADORESOPERACAO = 1;
    private static final Integer UPDATED_NUM_TRANSFORMADORESOPERACAO = 2;

    private static final Integer DEFAULT_NUM_TRANSFORMADORESVIOLADOS = 1;
    private static final Integer UPDATED_NUM_TRANSFORMADORESVIOLADOS = 2;

    private static final Double DEFAULT_VAL_CCAT = 1D;
    private static final Double UPDATED_VAL_CCAT = 2D;

    private static final String ENTITY_API_URL =
        "/api/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository;

    @Autowired
    private OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc;

    private OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity;

    private OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity createEntity() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity()
            .codTipoagregacao(DEFAULT_COD_TIPOAGREGACAO)
            .idPeriodicidade(DEFAULT_ID_PERIODICIDADE)
            .nomAgregacao(DEFAULT_NOM_AGREGACAO)
            .dinReferencia(DEFAULT_DIN_REFERENCIA)
            .numTransformadoresoperacao(DEFAULT_NUM_TRANSFORMADORESOPERACAO)
            .numTransformadoresviolados(DEFAULT_NUM_TRANSFORMADORESVIOLADOS)
            .valCcat(DEFAULT_VAL_CCAT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity createUpdatedEntity() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity()
            .codTipoagregacao(UPDATED_COD_TIPOAGREGACAO)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .nomAgregacao(UPDATED_NOM_AGREGACAO)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numTransformadoresoperacao(UPDATED_NUM_TRANSFORMADORESOPERACAO)
            .numTransformadoresviolados(UPDATED_NUM_TRANSFORMADORESVIOLADOS)
            .valCcat(UPDATED_VAL_CCAT);
    }

    @BeforeEach
    public void initTest() {
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity != null) {
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.delete(
                insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.delete(
                insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );
            insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        // Create the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
        var returnedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity = om.readValue(
            restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity)
                        )
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.class
        );

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityUpdatableFieldsEquals(
            returnedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity,
            getPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity(
                returnedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            returnedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity;
    }

    @Test
    @Transactional
    void createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresWithExistingId() throws Exception {
        // Create the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores with an existing ID
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.saveAndFlush(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );

        // Get all the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresList
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].codTipoagregacao").value(hasItem(DEFAULT_COD_TIPOAGREGACAO)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].nomAgregacao").value(hasItem(DEFAULT_NOM_AGREGACAO)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].numTransformadoresoperacao").value(hasItem(DEFAULT_NUM_TRANSFORMADORESOPERACAO)))
            .andExpect(jsonPath("$.[*].numTransformadoresviolados").value(hasItem(DEFAULT_NUM_TRANSFORMADORESVIOLADOS)))
            .andExpect(jsonPath("$.[*].valCcat").value(hasItem(DEFAULT_VAL_CCAT)));
    }

    @Test
    @Transactional
    void getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.saveAndFlush(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );

        // Get the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                get(ENTITY_API_URL_ID, onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.id").value(
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId().intValue()
                )
            )
            .andExpect(jsonPath("$.codTipoagregacao").value(DEFAULT_COD_TIPOAGREGACAO))
            .andExpect(jsonPath("$.idPeriodicidade").value(DEFAULT_ID_PERIODICIDADE))
            .andExpect(jsonPath("$.nomAgregacao").value(DEFAULT_NOM_AGREGACAO))
            .andExpect(jsonPath("$.dinReferencia").value(DEFAULT_DIN_REFERENCIA.toString()))
            .andExpect(jsonPath("$.numTransformadoresoperacao").value(DEFAULT_NUM_TRANSFORMADORESOPERACAO))
            .andExpect(jsonPath("$.numTransformadoresviolados").value(DEFAULT_NUM_TRANSFORMADORESVIOLADOS))
            .andExpect(jsonPath("$.valCcat").value(DEFAULT_VAL_CCAT));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        // Get the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.saveAndFlush(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.save(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );

        // Update the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository
                .findById(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity are not directly saved in db
        em.detach(updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity);
        updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            .codTipoagregacao(UPDATED_COD_TIPOAGREGACAO)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .nomAgregacao(UPDATED_NOM_AGREGACAO)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numTransformadoresoperacao(UPDATED_NUM_TRANSFORMADORESOPERACAO)
            .numTransformadoresviolados(UPDATED_NUM_TRANSFORMADORESVIOLADOS)
            .valCcat(UPDATED_VAL_CCAT);

        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                put(
                    ENTITY_API_URL_ID,
                    updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId()
                )
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        om.writeValueAsBytes(
                            updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
                        )
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityToMatchAllProperties(
            updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
                > onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchList = Streamable.of(
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
                ).toList();
                OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity testOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearch =
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchList.get(
                        searchDatabaseSizeAfter - 1
                    );

                assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAllPropertiesEquals(
                    testOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearch,
                    updatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.saveAndFlush(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores using partial update
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity();
        partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId()
        );

        partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numTransformadoresoperacao(UPDATED_NUM_TRANSFORMADORESOPERACAO)
            .numTransformadoresviolados(UPDATED_NUM_TRANSFORMADORESVIOLADOS);

        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(
                            partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
                        )
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity,
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            ),
            getPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.saveAndFlush(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores using partial update
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity();
        partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId()
        );

        partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            .codTipoagregacao(UPDATED_COD_TIPOAGREGACAO)
            .idPeriodicidade(UPDATED_ID_PERIODICIDADE)
            .nomAgregacao(UPDATED_NOM_AGREGACAO)
            .dinReferencia(UPDATED_DIN_REFERENCIA)
            .numTransformadoresoperacao(UPDATED_NUM_TRANSFORMADORESOPERACAO)
            .numTransformadoresviolados(UPDATED_NUM_TRANSFORMADORESVIOLADOS)
            .valCcat(UPDATED_VAL_CCAT);

        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                patch(
                    ENTITY_API_URL_ID,
                    partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId()
                )
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(
                            partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
                        )
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity,
            getPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity(
                partialUpdatedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.saveAndFlush(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.save(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.save(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() throws Exception {
        // Initialize the database
        insertedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.saveAndFlush(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.save(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );

        // Search the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
        restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresMockMvc
            .perform(
                get(
                    ENTITY_SEARCH_API_URL +
                    "?query=id:" +
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId()
                )
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].codTipoagregacao").value(hasItem(DEFAULT_COD_TIPOAGREGACAO)))
            .andExpect(jsonPath("$.[*].idPeriodicidade").value(hasItem(DEFAULT_ID_PERIODICIDADE)))
            .andExpect(jsonPath("$.[*].nomAgregacao").value(hasItem(DEFAULT_NOM_AGREGACAO)))
            .andExpect(jsonPath("$.[*].dinReferencia").value(hasItem(DEFAULT_DIN_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].numTransformadoresoperacao").value(hasItem(DEFAULT_NUM_TRANSFORMADORESOPERACAO)))
            .andExpect(jsonPath("$.[*].numTransformadoresviolados").value(hasItem(DEFAULT_NUM_TRANSFORMADORESVIOLADOS)))
            .andExpect(jsonPath("$.[*].valCcat").value(hasItem(DEFAULT_VAL_CCAT)));
    }

    protected long getRepositoryCount() {
        return onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.count();
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

    protected OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity getPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
    ) {
        return onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository
            .findById(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityToMatchAllProperties(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity expectedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    ) {
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAllPropertiesEquals(
            expectedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity,
            getPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity(
                expectedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            )
        );
    }

    protected void assertPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityToMatchUpdatableProperties(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity expectedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    ) {
        assertOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity,
            getPersistedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity(
                expectedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            )
        );
    }
}
