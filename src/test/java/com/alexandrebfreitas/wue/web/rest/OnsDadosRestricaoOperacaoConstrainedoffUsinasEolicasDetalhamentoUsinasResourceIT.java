package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository;
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
 * Integration tests for the {@link OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MODALIDADEOPERACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MODALIDADEOPERACAO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_CONJUNTOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_CONJUNTOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DIN_INSTANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_INSTANTE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ID_ONS = "AAAAAAAAAA";
    private static final String UPDATED_ID_ONS = "BBBBBBBBBB";

    private static final String DEFAULT_CEG = "AAAAAAAAAA";
    private static final String UPDATED_CEG = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_VENTOVERIFICADO = 1D;
    private static final Double UPDATED_VAL_VENTOVERIFICADO = 2D;

    private static final Double DEFAULT_FLG_DADOVENTOINVALIDO = 1D;
    private static final Double UPDATED_FLG_DADOVENTOINVALIDO = 2D;

    private static final Double DEFAULT_VAL_GERACAOESTIMADA = 1D;
    private static final Double UPDATED_VAL_GERACAOESTIMADA = 2D;

    private static final Double DEFAULT_VAL_GERACAOVERIFICADA = 1D;
    private static final Double UPDATED_VAL_GERACAOVERIFICADA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL =
        "/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository;

    @Autowired
    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc;

    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity;

    private OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity createEntity() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity()
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomModalidadeoperacao(DEFAULT_NOM_MODALIDADEOPERACAO)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomConjuntousina(DEFAULT_NOM_CONJUNTOUSINA)
            .nomUsina(DEFAULT_NOM_USINA)
            .dinInstante(DEFAULT_DIN_INSTANTE)
            .idOns(DEFAULT_ID_ONS)
            .ceg(DEFAULT_CEG)
            .valVentoverificado(DEFAULT_VAL_VENTOVERIFICADO)
            .flgDadoventoinvalido(DEFAULT_FLG_DADOVENTOINVALIDO)
            .valGeracaoestimada(DEFAULT_VAL_GERACAOESTIMADA)
            .valGeracaoverificada(DEFAULT_VAL_GERACAOVERIFICADA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity createUpdatedEntity() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity()
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .idEstado(UPDATED_ID_ESTADO)
            .nomConjuntousina(UPDATED_NOM_CONJUNTOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valVentoverificado(UPDATED_VAL_VENTOVERIFICADO)
            .flgDadoventoinvalido(UPDATED_FLG_DADOVENTOINVALIDO)
            .valGeracaoestimada(UPDATED_VAL_GERACAOESTIMADA)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA);
    }

    @BeforeEach
    public void initTest() {
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity != null) {
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.delete(
                insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.delete(
                insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );
            insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        // Create the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
        var returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity = om.readValue(
            restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.class
        );

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityUpdatableFieldsEquals(
            returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity(
                returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            returnedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity;
    }

    @Test
    @Transactional
    void createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasWithExistingId() throws Exception {
        // Create the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas with an existing ID
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );

        // Get all the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasList
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomConjuntousina").value(hasItem(DEFAULT_NOM_CONJUNTOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valVentoverificado").value(hasItem(DEFAULT_VAL_VENTOVERIFICADO)))
            .andExpect(jsonPath("$.[*].flgDadoventoinvalido").value(hasItem(DEFAULT_FLG_DADOVENTOINVALIDO)))
            .andExpect(jsonPath("$.[*].valGeracaoestimada").value(hasItem(DEFAULT_VAL_GERACAOESTIMADA)))
            .andExpect(jsonPath("$.[*].valGeracaoverificada").value(hasItem(DEFAULT_VAL_GERACAOVERIFICADA)));
    }

    @Test
    @Transactional
    void getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );

        // Get the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.id").value(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId().intValue())
            )
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomModalidadeoperacao").value(DEFAULT_NOM_MODALIDADEOPERACAO))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomConjuntousina").value(DEFAULT_NOM_CONJUNTOUSINA))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.dinInstante").value(DEFAULT_DIN_INSTANTE.toString()))
            .andExpect(jsonPath("$.idOns").value(DEFAULT_ID_ONS))
            .andExpect(jsonPath("$.ceg").value(DEFAULT_CEG))
            .andExpect(jsonPath("$.valVentoverificado").value(DEFAULT_VAL_VENTOVERIFICADO))
            .andExpect(jsonPath("$.flgDadoventoinvalido").value(DEFAULT_FLG_DADOVENTOINVALIDO))
            .andExpect(jsonPath("$.valGeracaoestimada").value(DEFAULT_VAL_GERACAOESTIMADA))
            .andExpect(jsonPath("$.valGeracaoverificada").value(DEFAULT_VAL_GERACAOVERIFICADA));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        // Get the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository
                .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity are not directly saved in db
        em.detach(updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity);
        updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .idEstado(UPDATED_ID_ESTADO)
            .nomConjuntousina(UPDATED_NOM_CONJUNTOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valVentoverificado(UPDATED_VAL_VENTOVERIFICADO)
            .flgDadoventoinvalido(UPDATED_FLG_DADOVENTOINVALIDO)
            .valGeracaoestimada(UPDATED_VAL_GERACAOESTIMADA)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA);

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityToMatchAllProperties(
            updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
                > onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchList = Streamable.of(
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
                ).toList();
                OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity testOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearch =
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityAllPropertiesEquals(
                    testOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearch,
                    updatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas using partial update
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity();
        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId()
        );

        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idOns(UPDATED_ID_ONS)
            .valVentoverificado(UPDATED_VAL_VENTOVERIFICADO)
            .valGeracaoestimada(UPDATED_VAL_GERACAOESTIMADA)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA);

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity)
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity,
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            ),
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas using partial update
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity();
        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId()
        );

        partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .idEstado(UPDATED_ID_ESTADO)
            .nomConjuntousina(UPDATED_NOM_CONJUNTOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .dinInstante(UPDATED_DIN_INSTANTE)
            .idOns(UPDATED_ID_ONS)
            .ceg(UPDATED_CEG)
            .valVentoverificado(UPDATED_VAL_VENTOVERIFICADO)
            .flgDadoventoinvalido(UPDATED_FLG_DADOVENTOINVALIDO)
            .valGeracaoestimada(UPDATED_VAL_GERACAOESTIMADA)
            .valGeracaoverificada(UPDATED_VAL_GERACAOVERIFICADA);

        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(
                        om.writeValueAsBytes(partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity)
                    )
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity(
                partialUpdatedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() throws Exception {
        // Initialize the database
        insertedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.saveAndFlush(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );

        // Search the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
        restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasMockMvc
            .perform(
                get(
                    ENTITY_SEARCH_API_URL +
                    "?query=id:" +
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId()
                )
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(
                    hasItem(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId().intValue())
                )
            )
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomConjuntousina").value(hasItem(DEFAULT_NOM_CONJUNTOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].dinInstante").value(hasItem(DEFAULT_DIN_INSTANTE.toString())))
            .andExpect(jsonPath("$.[*].idOns").value(hasItem(DEFAULT_ID_ONS)))
            .andExpect(jsonPath("$.[*].ceg").value(hasItem(DEFAULT_CEG)))
            .andExpect(jsonPath("$.[*].valVentoverificado").value(hasItem(DEFAULT_VAL_VENTOVERIFICADO)))
            .andExpect(jsonPath("$.[*].flgDadoventoinvalido").value(hasItem(DEFAULT_FLG_DADOVENTOINVALIDO)))
            .andExpect(jsonPath("$.[*].valGeracaoestimada").value(hasItem(DEFAULT_VAL_GERACAOESTIMADA)))
            .andExpect(jsonPath("$.[*].valGeracaoverificada").value(hasItem(DEFAULT_VAL_GERACAOVERIFICADA)));
    }

    protected long getRepositoryCount() {
        return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.count();
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

    protected OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
    ) {
        return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository
            .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityToMatchAllProperties(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    ) {
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityAllPropertiesEquals(
            expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity(
                expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            )
        );
    }

    protected void assertPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityToMatchUpdatableProperties(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    ) {
        assertOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity,
            getPersistedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity(
                expectedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            )
        );
    }
}
