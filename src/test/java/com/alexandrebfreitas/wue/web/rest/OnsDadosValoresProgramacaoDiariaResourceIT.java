package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosValoresProgramacaoDiariaEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosValoresProgramacaoDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosValoresProgramacaoDiariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosValoresProgramacaoDiariaSearchRepository;
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
 * Integration tests for the {@link OnsDadosValoresProgramacaoDiariaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosValoresProgramacaoDiariaResourceIT {

    private static final LocalDate DEFAULT_DIN_PROGRAMACAODIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_PROGRAMACAODIA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUM_PATAMAR = 1;
    private static final Integer UPDATED_NUM_PATAMAR = 2;

    private static final String DEFAULT_COD_EXIBICAOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_COD_EXIBICAOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_GERACAO = "AAAAAAAAAA";
    private static final String UPDATED_TIP_GERACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MODALIDADEOPERACAO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MODALIDADEOPERACAO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ID_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ESTADO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_GERACAOPROGRAMADA = 1D;
    private static final Double UPDATED_VAL_GERACAOPROGRAMADA = 2D;

    private static final Double DEFAULT_VAL_DISPONIBILIDADE = 1D;
    private static final Double UPDATED_VAL_DISPONIBILIDADE = 2D;

    private static final Double DEFAULT_VAL_ORDEMMERITO = 1D;
    private static final Double UPDATED_VAL_ORDEMMERITO = 2D;

    private static final Double DEFAULT_VAL_INFLEXIBILIDADE = 1D;
    private static final Double UPDATED_VAL_INFLEXIBILIDADE = 2D;

    private static final Double DEFAULT_VAL_UC = 1D;
    private static final Double UPDATED_VAL_UC = 2D;

    private static final Double DEFAULT_VAL_RAZAOELETRICA = 1D;
    private static final Double UPDATED_VAL_RAZAOELETRICA = 2D;

    private static final Double DEFAULT_VAL_GERACAOENERGETICA = 1D;
    private static final Double UPDATED_VAL_GERACAOENERGETICA = 2D;

    private static final Double DEFAULT_VAL_GESUBGSUB = 1D;
    private static final Double UPDATED_VAL_GESUBGSUB = 2D;

    private static final Double DEFAULT_VAL_EXPORTACAO = 1D;
    private static final Double UPDATED_VAL_EXPORTACAO = 2D;

    private static final Double DEFAULT_VAL_REPOSICAOEXPORTACAO = 1D;
    private static final Double UPDATED_VAL_REPOSICAOEXPORTACAO = 2D;

    private static final Double DEFAULT_VAL_FALTACOMBUSTIVEL = 1D;
    private static final Double UPDATED_VAL_FALTACOMBUSTIVEL = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-valores-programacao-diarias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-valores-programacao-diarias/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosValoresProgramacaoDiariaRepository onsDadosValoresProgramacaoDiariaRepository;

    @Autowired
    private OnsDadosValoresProgramacaoDiariaSearchRepository onsDadosValoresProgramacaoDiariaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosValoresProgramacaoDiariaMockMvc;

    private OnsDadosValoresProgramacaoDiariaEntity onsDadosValoresProgramacaoDiariaEntity;

    private OnsDadosValoresProgramacaoDiariaEntity insertedOnsDadosValoresProgramacaoDiariaEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosValoresProgramacaoDiariaEntity createEntity() {
        return new OnsDadosValoresProgramacaoDiariaEntity()
            .dinProgramacaodia(DEFAULT_DIN_PROGRAMACAODIA)
            .numPatamar(DEFAULT_NUM_PATAMAR)
            .codExibicaousina(DEFAULT_COD_EXIBICAOUSINA)
            .nomUsina(DEFAULT_NOM_USINA)
            .tipGeracao(DEFAULT_TIP_GERACAO)
            .nomModalidadeoperacao(DEFAULT_NOM_MODALIDADEOPERACAO)
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .idEstado(DEFAULT_ID_ESTADO)
            .nomEstado(DEFAULT_NOM_ESTADO)
            .valGeracaoprogramada(DEFAULT_VAL_GERACAOPROGRAMADA)
            .valDisponibilidade(DEFAULT_VAL_DISPONIBILIDADE)
            .valOrdemmerito(DEFAULT_VAL_ORDEMMERITO)
            .valInflexibilidade(DEFAULT_VAL_INFLEXIBILIDADE)
            .valUc(DEFAULT_VAL_UC)
            .valRazaoeletrica(DEFAULT_VAL_RAZAOELETRICA)
            .valGeracaoenergetica(DEFAULT_VAL_GERACAOENERGETICA)
            .valGesubgsub(DEFAULT_VAL_GESUBGSUB)
            .valExportacao(DEFAULT_VAL_EXPORTACAO)
            .valReposicaoexportacao(DEFAULT_VAL_REPOSICAOEXPORTACAO)
            .valFaltacombustivel(DEFAULT_VAL_FALTACOMBUSTIVEL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosValoresProgramacaoDiariaEntity createUpdatedEntity() {
        return new OnsDadosValoresProgramacaoDiariaEntity()
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .codExibicaousina(UPDATED_COD_EXIBICAOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .tipGeracao(UPDATED_TIP_GERACAO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .valGeracaoprogramada(UPDATED_VAL_GERACAOPROGRAMADA)
            .valDisponibilidade(UPDATED_VAL_DISPONIBILIDADE)
            .valOrdemmerito(UPDATED_VAL_ORDEMMERITO)
            .valInflexibilidade(UPDATED_VAL_INFLEXIBILIDADE)
            .valUc(UPDATED_VAL_UC)
            .valRazaoeletrica(UPDATED_VAL_RAZAOELETRICA)
            .valGeracaoenergetica(UPDATED_VAL_GERACAOENERGETICA)
            .valGesubgsub(UPDATED_VAL_GESUBGSUB)
            .valExportacao(UPDATED_VAL_EXPORTACAO)
            .valReposicaoexportacao(UPDATED_VAL_REPOSICAOEXPORTACAO)
            .valFaltacombustivel(UPDATED_VAL_FALTACOMBUSTIVEL);
    }

    @BeforeEach
    public void initTest() {
        onsDadosValoresProgramacaoDiariaEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosValoresProgramacaoDiariaEntity != null) {
            onsDadosValoresProgramacaoDiariaRepository.delete(insertedOnsDadosValoresProgramacaoDiariaEntity);
            onsDadosValoresProgramacaoDiariaSearchRepository.delete(insertedOnsDadosValoresProgramacaoDiariaEntity);
            insertedOnsDadosValoresProgramacaoDiariaEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosValoresProgramacaoDiaria() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        // Create the OnsDadosValoresProgramacaoDiaria
        var returnedOnsDadosValoresProgramacaoDiariaEntity = om.readValue(
            restOnsDadosValoresProgramacaoDiariaMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosValoresProgramacaoDiariaEntity.class
        );

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosValoresProgramacaoDiariaEntityUpdatableFieldsEquals(
            returnedOnsDadosValoresProgramacaoDiariaEntity,
            getPersistedOnsDadosValoresProgramacaoDiariaEntity(returnedOnsDadosValoresProgramacaoDiariaEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosValoresProgramacaoDiariaEntity = returnedOnsDadosValoresProgramacaoDiariaEntity;
    }

    @Test
    @Transactional
    void createOnsDadosValoresProgramacaoDiariaWithExistingId() throws Exception {
        // Create the OnsDadosValoresProgramacaoDiaria with an existing ID
        onsDadosValoresProgramacaoDiariaEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosValoresProgramacaoDiarias() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.saveAndFlush(
            onsDadosValoresProgramacaoDiariaEntity
        );

        // Get all the onsDadosValoresProgramacaoDiariaList
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosValoresProgramacaoDiariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinProgramacaodia").value(hasItem(DEFAULT_DIN_PROGRAMACAODIA.toString())))
            .andExpect(jsonPath("$.[*].numPatamar").value(hasItem(DEFAULT_NUM_PATAMAR)))
            .andExpect(jsonPath("$.[*].codExibicaousina").value(hasItem(DEFAULT_COD_EXIBICAOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].tipGeracao").value(hasItem(DEFAULT_TIP_GERACAO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].valGeracaoprogramada").value(hasItem(DEFAULT_VAL_GERACAOPROGRAMADA)))
            .andExpect(jsonPath("$.[*].valDisponibilidade").value(hasItem(DEFAULT_VAL_DISPONIBILIDADE)))
            .andExpect(jsonPath("$.[*].valOrdemmerito").value(hasItem(DEFAULT_VAL_ORDEMMERITO)))
            .andExpect(jsonPath("$.[*].valInflexibilidade").value(hasItem(DEFAULT_VAL_INFLEXIBILIDADE)))
            .andExpect(jsonPath("$.[*].valUc").value(hasItem(DEFAULT_VAL_UC)))
            .andExpect(jsonPath("$.[*].valRazaoeletrica").value(hasItem(DEFAULT_VAL_RAZAOELETRICA)))
            .andExpect(jsonPath("$.[*].valGeracaoenergetica").value(hasItem(DEFAULT_VAL_GERACAOENERGETICA)))
            .andExpect(jsonPath("$.[*].valGesubgsub").value(hasItem(DEFAULT_VAL_GESUBGSUB)))
            .andExpect(jsonPath("$.[*].valExportacao").value(hasItem(DEFAULT_VAL_EXPORTACAO)))
            .andExpect(jsonPath("$.[*].valReposicaoexportacao").value(hasItem(DEFAULT_VAL_REPOSICAOEXPORTACAO)))
            .andExpect(jsonPath("$.[*].valFaltacombustivel").value(hasItem(DEFAULT_VAL_FALTACOMBUSTIVEL)));
    }

    @Test
    @Transactional
    void getOnsDadosValoresProgramacaoDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.saveAndFlush(
            onsDadosValoresProgramacaoDiariaEntity
        );

        // Get the onsDadosValoresProgramacaoDiaria
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosValoresProgramacaoDiariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosValoresProgramacaoDiariaEntity.getId().intValue()))
            .andExpect(jsonPath("$.dinProgramacaodia").value(DEFAULT_DIN_PROGRAMACAODIA.toString()))
            .andExpect(jsonPath("$.numPatamar").value(DEFAULT_NUM_PATAMAR))
            .andExpect(jsonPath("$.codExibicaousina").value(DEFAULT_COD_EXIBICAOUSINA))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.tipGeracao").value(DEFAULT_TIP_GERACAO))
            .andExpect(jsonPath("$.nomModalidadeoperacao").value(DEFAULT_NOM_MODALIDADEOPERACAO))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.nomEstado").value(DEFAULT_NOM_ESTADO))
            .andExpect(jsonPath("$.valGeracaoprogramada").value(DEFAULT_VAL_GERACAOPROGRAMADA))
            .andExpect(jsonPath("$.valDisponibilidade").value(DEFAULT_VAL_DISPONIBILIDADE))
            .andExpect(jsonPath("$.valOrdemmerito").value(DEFAULT_VAL_ORDEMMERITO))
            .andExpect(jsonPath("$.valInflexibilidade").value(DEFAULT_VAL_INFLEXIBILIDADE))
            .andExpect(jsonPath("$.valUc").value(DEFAULT_VAL_UC))
            .andExpect(jsonPath("$.valRazaoeletrica").value(DEFAULT_VAL_RAZAOELETRICA))
            .andExpect(jsonPath("$.valGeracaoenergetica").value(DEFAULT_VAL_GERACAOENERGETICA))
            .andExpect(jsonPath("$.valGesubgsub").value(DEFAULT_VAL_GESUBGSUB))
            .andExpect(jsonPath("$.valExportacao").value(DEFAULT_VAL_EXPORTACAO))
            .andExpect(jsonPath("$.valReposicaoexportacao").value(DEFAULT_VAL_REPOSICAOEXPORTACAO))
            .andExpect(jsonPath("$.valFaltacombustivel").value(DEFAULT_VAL_FALTACOMBUSTIVEL));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosValoresProgramacaoDiaria() throws Exception {
        // Get the onsDadosValoresProgramacaoDiaria
        restOnsDadosValoresProgramacaoDiariaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosValoresProgramacaoDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.saveAndFlush(
            onsDadosValoresProgramacaoDiariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosValoresProgramacaoDiariaSearchRepository.save(onsDadosValoresProgramacaoDiariaEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());

        // Update the onsDadosValoresProgramacaoDiaria
        OnsDadosValoresProgramacaoDiariaEntity updatedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository
            .findById(onsDadosValoresProgramacaoDiariaEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosValoresProgramacaoDiariaEntity are not directly saved in db
        em.detach(updatedOnsDadosValoresProgramacaoDiariaEntity);
        updatedOnsDadosValoresProgramacaoDiariaEntity
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .codExibicaousina(UPDATED_COD_EXIBICAOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .tipGeracao(UPDATED_TIP_GERACAO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .valGeracaoprogramada(UPDATED_VAL_GERACAOPROGRAMADA)
            .valDisponibilidade(UPDATED_VAL_DISPONIBILIDADE)
            .valOrdemmerito(UPDATED_VAL_ORDEMMERITO)
            .valInflexibilidade(UPDATED_VAL_INFLEXIBILIDADE)
            .valUc(UPDATED_VAL_UC)
            .valRazaoeletrica(UPDATED_VAL_RAZAOELETRICA)
            .valGeracaoenergetica(UPDATED_VAL_GERACAOENERGETICA)
            .valGesubgsub(UPDATED_VAL_GESUBGSUB)
            .valExportacao(UPDATED_VAL_EXPORTACAO)
            .valReposicaoexportacao(UPDATED_VAL_REPOSICAOEXPORTACAO)
            .valFaltacombustivel(UPDATED_VAL_FALTACOMBUSTIVEL);

        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosValoresProgramacaoDiariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosValoresProgramacaoDiariaEntityToMatchAllProperties(updatedOnsDadosValoresProgramacaoDiariaEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosValoresProgramacaoDiariaEntity> onsDadosValoresProgramacaoDiariaSearchList = Streamable.of(
                    onsDadosValoresProgramacaoDiariaSearchRepository.findAll()
                ).toList();
                OnsDadosValoresProgramacaoDiariaEntity testOnsDadosValoresProgramacaoDiariaSearch =
                    onsDadosValoresProgramacaoDiariaSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosValoresProgramacaoDiariaEntityAllPropertiesEquals(
                    testOnsDadosValoresProgramacaoDiariaSearch,
                    updatedOnsDadosValoresProgramacaoDiariaEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosValoresProgramacaoDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        onsDadosValoresProgramacaoDiariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosValoresProgramacaoDiariaEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosValoresProgramacaoDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        onsDadosValoresProgramacaoDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosValoresProgramacaoDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        onsDadosValoresProgramacaoDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosValoresProgramacaoDiariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.saveAndFlush(
            onsDadosValoresProgramacaoDiariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosValoresProgramacaoDiaria using partial update
        OnsDadosValoresProgramacaoDiariaEntity partialUpdatedOnsDadosValoresProgramacaoDiariaEntity =
            new OnsDadosValoresProgramacaoDiariaEntity();
        partialUpdatedOnsDadosValoresProgramacaoDiariaEntity.setId(onsDadosValoresProgramacaoDiariaEntity.getId());

        partialUpdatedOnsDadosValoresProgramacaoDiariaEntity
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .tipGeracao(UPDATED_TIP_GERACAO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomEstado(UPDATED_NOM_ESTADO)
            .valOrdemmerito(UPDATED_VAL_ORDEMMERITO)
            .valInflexibilidade(UPDATED_VAL_INFLEXIBILIDADE)
            .valGesubgsub(UPDATED_VAL_GESUBGSUB)
            .valExportacao(UPDATED_VAL_EXPORTACAO);

        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosValoresProgramacaoDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosValoresProgramacaoDiariaEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsDadosValoresProgramacaoDiariaEntity, onsDadosValoresProgramacaoDiariaEntity),
            getPersistedOnsDadosValoresProgramacaoDiariaEntity(onsDadosValoresProgramacaoDiariaEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosValoresProgramacaoDiariaWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.saveAndFlush(
            onsDadosValoresProgramacaoDiariaEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosValoresProgramacaoDiaria using partial update
        OnsDadosValoresProgramacaoDiariaEntity partialUpdatedOnsDadosValoresProgramacaoDiariaEntity =
            new OnsDadosValoresProgramacaoDiariaEntity();
        partialUpdatedOnsDadosValoresProgramacaoDiariaEntity.setId(onsDadosValoresProgramacaoDiariaEntity.getId());

        partialUpdatedOnsDadosValoresProgramacaoDiariaEntity
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .codExibicaousina(UPDATED_COD_EXIBICAOUSINA)
            .nomUsina(UPDATED_NOM_USINA)
            .tipGeracao(UPDATED_TIP_GERACAO)
            .nomModalidadeoperacao(UPDATED_NOM_MODALIDADEOPERACAO)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .idEstado(UPDATED_ID_ESTADO)
            .nomEstado(UPDATED_NOM_ESTADO)
            .valGeracaoprogramada(UPDATED_VAL_GERACAOPROGRAMADA)
            .valDisponibilidade(UPDATED_VAL_DISPONIBILIDADE)
            .valOrdemmerito(UPDATED_VAL_ORDEMMERITO)
            .valInflexibilidade(UPDATED_VAL_INFLEXIBILIDADE)
            .valUc(UPDATED_VAL_UC)
            .valRazaoeletrica(UPDATED_VAL_RAZAOELETRICA)
            .valGeracaoenergetica(UPDATED_VAL_GERACAOENERGETICA)
            .valGesubgsub(UPDATED_VAL_GESUBGSUB)
            .valExportacao(UPDATED_VAL_EXPORTACAO)
            .valReposicaoexportacao(UPDATED_VAL_REPOSICAOEXPORTACAO)
            .valFaltacombustivel(UPDATED_VAL_FALTACOMBUSTIVEL);

        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosValoresProgramacaoDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosValoresProgramacaoDiariaEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosValoresProgramacaoDiariaEntity,
            getPersistedOnsDadosValoresProgramacaoDiariaEntity(partialUpdatedOnsDadosValoresProgramacaoDiariaEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosValoresProgramacaoDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        onsDadosValoresProgramacaoDiariaEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosValoresProgramacaoDiariaEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosValoresProgramacaoDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        onsDadosValoresProgramacaoDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosValoresProgramacaoDiaria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        onsDadosValoresProgramacaoDiariaEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosValoresProgramacaoDiariaEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosValoresProgramacaoDiaria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosValoresProgramacaoDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.saveAndFlush(
            onsDadosValoresProgramacaoDiariaEntity
        );
        onsDadosValoresProgramacaoDiariaRepository.save(onsDadosValoresProgramacaoDiariaEntity);
        onsDadosValoresProgramacaoDiariaSearchRepository.save(onsDadosValoresProgramacaoDiariaEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosValoresProgramacaoDiaria
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosValoresProgramacaoDiariaEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosValoresProgramacaoDiariaSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosValoresProgramacaoDiaria() throws Exception {
        // Initialize the database
        insertedOnsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.saveAndFlush(
            onsDadosValoresProgramacaoDiariaEntity
        );
        onsDadosValoresProgramacaoDiariaSearchRepository.save(onsDadosValoresProgramacaoDiariaEntity);

        // Search the onsDadosValoresProgramacaoDiaria
        restOnsDadosValoresProgramacaoDiariaMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosValoresProgramacaoDiariaEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosValoresProgramacaoDiariaEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinProgramacaodia").value(hasItem(DEFAULT_DIN_PROGRAMACAODIA.toString())))
            .andExpect(jsonPath("$.[*].numPatamar").value(hasItem(DEFAULT_NUM_PATAMAR)))
            .andExpect(jsonPath("$.[*].codExibicaousina").value(hasItem(DEFAULT_COD_EXIBICAOUSINA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].tipGeracao").value(hasItem(DEFAULT_TIP_GERACAO)))
            .andExpect(jsonPath("$.[*].nomModalidadeoperacao").value(hasItem(DEFAULT_NOM_MODALIDADEOPERACAO)))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].nomEstado").value(hasItem(DEFAULT_NOM_ESTADO)))
            .andExpect(jsonPath("$.[*].valGeracaoprogramada").value(hasItem(DEFAULT_VAL_GERACAOPROGRAMADA)))
            .andExpect(jsonPath("$.[*].valDisponibilidade").value(hasItem(DEFAULT_VAL_DISPONIBILIDADE)))
            .andExpect(jsonPath("$.[*].valOrdemmerito").value(hasItem(DEFAULT_VAL_ORDEMMERITO)))
            .andExpect(jsonPath("$.[*].valInflexibilidade").value(hasItem(DEFAULT_VAL_INFLEXIBILIDADE)))
            .andExpect(jsonPath("$.[*].valUc").value(hasItem(DEFAULT_VAL_UC)))
            .andExpect(jsonPath("$.[*].valRazaoeletrica").value(hasItem(DEFAULT_VAL_RAZAOELETRICA)))
            .andExpect(jsonPath("$.[*].valGeracaoenergetica").value(hasItem(DEFAULT_VAL_GERACAOENERGETICA)))
            .andExpect(jsonPath("$.[*].valGesubgsub").value(hasItem(DEFAULT_VAL_GESUBGSUB)))
            .andExpect(jsonPath("$.[*].valExportacao").value(hasItem(DEFAULT_VAL_EXPORTACAO)))
            .andExpect(jsonPath("$.[*].valReposicaoexportacao").value(hasItem(DEFAULT_VAL_REPOSICAOEXPORTACAO)))
            .andExpect(jsonPath("$.[*].valFaltacombustivel").value(hasItem(DEFAULT_VAL_FALTACOMBUSTIVEL)));
    }

    protected long getRepositoryCount() {
        return onsDadosValoresProgramacaoDiariaRepository.count();
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

    protected OnsDadosValoresProgramacaoDiariaEntity getPersistedOnsDadosValoresProgramacaoDiariaEntity(
        OnsDadosValoresProgramacaoDiariaEntity onsDadosValoresProgramacaoDiaria
    ) {
        return onsDadosValoresProgramacaoDiariaRepository.findById(onsDadosValoresProgramacaoDiaria.getId()).orElseThrow();
    }

    protected void assertPersistedOnsDadosValoresProgramacaoDiariaEntityToMatchAllProperties(
        OnsDadosValoresProgramacaoDiariaEntity expectedOnsDadosValoresProgramacaoDiariaEntity
    ) {
        assertOnsDadosValoresProgramacaoDiariaEntityAllPropertiesEquals(
            expectedOnsDadosValoresProgramacaoDiariaEntity,
            getPersistedOnsDadosValoresProgramacaoDiariaEntity(expectedOnsDadosValoresProgramacaoDiariaEntity)
        );
    }

    protected void assertPersistedOnsDadosValoresProgramacaoDiariaEntityToMatchUpdatableProperties(
        OnsDadosValoresProgramacaoDiariaEntity expectedOnsDadosValoresProgramacaoDiariaEntity
    ) {
        assertOnsDadosValoresProgramacaoDiariaEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosValoresProgramacaoDiariaEntity,
            getPersistedOnsDadosValoresProgramacaoDiariaEntity(expectedOnsDadosValoresProgramacaoDiariaEntity)
        );
    }
}
