package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosProgramadosElementosFluxoControladoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosProgramadosElementosFluxoControladoSearchRepository;
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
 * Integration tests for the {@link OnsDadosProgramadosElementosFluxoControladoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosProgramadosElementosFluxoControladoResourceIT {

    private static final LocalDate DEFAULT_DIN_PROGRAMACAODIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIN_PROGRAMACAODIA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUM_PATAMAR = 1;
    private static final Integer UPDATED_NUM_PATAMAR = 2;

    private static final String DEFAULT_NOM_ELEMENTOFLUXOCONTROLADO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ELEMENTOFLUXOCONTROLADO = "BBBBBBBBBB";

    private static final String DEFAULT_DSC_ELEMENTOFLUXOCONTROLADO = "AAAAAAAAAA";
    private static final String UPDATED_DSC_ELEMENTOFLUXOCONTROLADO = "BBBBBBBBBB";

    private static final Integer DEFAULT_TIP_TERMINAL = 1;
    private static final Integer UPDATED_TIP_TERMINAL = 2;

    private static final String DEFAULT_COD_SUBMERCADO = "AAAAAAAAAA";
    private static final String UPDATED_COD_SUBMERCADO = "BBBBBBBBBB";

    private static final Double DEFAULT_VAL_CARGA = 1D;
    private static final Double UPDATED_VAL_CARGA = 2D;

    private static final String ENTITY_API_URL = "/api/ons-dados-programados-elementos-fluxo-controlados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-programados-elementos-fluxo-controlados/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosProgramadosElementosFluxoControladoRepository onsDadosProgramadosElementosFluxoControladoRepository;

    @Autowired
    private OnsDadosProgramadosElementosFluxoControladoSearchRepository onsDadosProgramadosElementosFluxoControladoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosProgramadosElementosFluxoControladoMockMvc;

    private OnsDadosProgramadosElementosFluxoControladoEntity onsDadosProgramadosElementosFluxoControladoEntity;

    private OnsDadosProgramadosElementosFluxoControladoEntity insertedOnsDadosProgramadosElementosFluxoControladoEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosProgramadosElementosFluxoControladoEntity createEntity() {
        return new OnsDadosProgramadosElementosFluxoControladoEntity()
            .dinProgramacaodia(DEFAULT_DIN_PROGRAMACAODIA)
            .numPatamar(DEFAULT_NUM_PATAMAR)
            .nomElementofluxocontrolado(DEFAULT_NOM_ELEMENTOFLUXOCONTROLADO)
            .dscElementofluxocontrolado(DEFAULT_DSC_ELEMENTOFLUXOCONTROLADO)
            .tipTerminal(DEFAULT_TIP_TERMINAL)
            .codSubmercado(DEFAULT_COD_SUBMERCADO)
            .valCarga(DEFAULT_VAL_CARGA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosProgramadosElementosFluxoControladoEntity createUpdatedEntity() {
        return new OnsDadosProgramadosElementosFluxoControladoEntity()
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .nomElementofluxocontrolado(UPDATED_NOM_ELEMENTOFLUXOCONTROLADO)
            .dscElementofluxocontrolado(UPDATED_DSC_ELEMENTOFLUXOCONTROLADO)
            .tipTerminal(UPDATED_TIP_TERMINAL)
            .codSubmercado(UPDATED_COD_SUBMERCADO)
            .valCarga(UPDATED_VAL_CARGA);
    }

    @BeforeEach
    public void initTest() {
        onsDadosProgramadosElementosFluxoControladoEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosProgramadosElementosFluxoControladoEntity != null) {
            onsDadosProgramadosElementosFluxoControladoRepository.delete(insertedOnsDadosProgramadosElementosFluxoControladoEntity);
            onsDadosProgramadosElementosFluxoControladoSearchRepository.delete(insertedOnsDadosProgramadosElementosFluxoControladoEntity);
            insertedOnsDadosProgramadosElementosFluxoControladoEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        // Create the OnsDadosProgramadosElementosFluxoControlado
        var returnedOnsDadosProgramadosElementosFluxoControladoEntity = om.readValue(
            restOnsDadosProgramadosElementosFluxoControladoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosProgramadosElementosFluxoControladoEntity.class
        );

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosProgramadosElementosFluxoControladoEntityUpdatableFieldsEquals(
            returnedOnsDadosProgramadosElementosFluxoControladoEntity,
            getPersistedOnsDadosProgramadosElementosFluxoControladoEntity(returnedOnsDadosProgramadosElementosFluxoControladoEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosProgramadosElementosFluxoControladoEntity = returnedOnsDadosProgramadosElementosFluxoControladoEntity;
    }

    @Test
    @Transactional
    void createOnsDadosProgramadosElementosFluxoControladoWithExistingId() throws Exception {
        // Create the OnsDadosProgramadosElementosFluxoControlado with an existing ID
        onsDadosProgramadosElementosFluxoControladoEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosProgramadosElementosFluxoControlados() throws Exception {
        // Initialize the database
        insertedOnsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.saveAndFlush(
            onsDadosProgramadosElementosFluxoControladoEntity
        );

        // Get all the onsDadosProgramadosElementosFluxoControladoList
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosProgramadosElementosFluxoControladoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinProgramacaodia").value(hasItem(DEFAULT_DIN_PROGRAMACAODIA.toString())))
            .andExpect(jsonPath("$.[*].numPatamar").value(hasItem(DEFAULT_NUM_PATAMAR)))
            .andExpect(jsonPath("$.[*].nomElementofluxocontrolado").value(hasItem(DEFAULT_NOM_ELEMENTOFLUXOCONTROLADO)))
            .andExpect(jsonPath("$.[*].dscElementofluxocontrolado").value(hasItem(DEFAULT_DSC_ELEMENTOFLUXOCONTROLADO)))
            .andExpect(jsonPath("$.[*].tipTerminal").value(hasItem(DEFAULT_TIP_TERMINAL)))
            .andExpect(jsonPath("$.[*].codSubmercado").value(hasItem(DEFAULT_COD_SUBMERCADO)))
            .andExpect(jsonPath("$.[*].valCarga").value(hasItem(DEFAULT_VAL_CARGA)));
    }

    @Test
    @Transactional
    void getOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        // Initialize the database
        insertedOnsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.saveAndFlush(
            onsDadosProgramadosElementosFluxoControladoEntity
        );

        // Get the onsDadosProgramadosElementosFluxoControlado
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosProgramadosElementosFluxoControladoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosProgramadosElementosFluxoControladoEntity.getId().intValue()))
            .andExpect(jsonPath("$.dinProgramacaodia").value(DEFAULT_DIN_PROGRAMACAODIA.toString()))
            .andExpect(jsonPath("$.numPatamar").value(DEFAULT_NUM_PATAMAR))
            .andExpect(jsonPath("$.nomElementofluxocontrolado").value(DEFAULT_NOM_ELEMENTOFLUXOCONTROLADO))
            .andExpect(jsonPath("$.dscElementofluxocontrolado").value(DEFAULT_DSC_ELEMENTOFLUXOCONTROLADO))
            .andExpect(jsonPath("$.tipTerminal").value(DEFAULT_TIP_TERMINAL))
            .andExpect(jsonPath("$.codSubmercado").value(DEFAULT_COD_SUBMERCADO))
            .andExpect(jsonPath("$.valCarga").value(DEFAULT_VAL_CARGA));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        // Get the onsDadosProgramadosElementosFluxoControlado
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        // Initialize the database
        insertedOnsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.saveAndFlush(
            onsDadosProgramadosElementosFluxoControladoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosProgramadosElementosFluxoControladoSearchRepository.save(onsDadosProgramadosElementosFluxoControladoEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());

        // Update the onsDadosProgramadosElementosFluxoControlado
        OnsDadosProgramadosElementosFluxoControladoEntity updatedOnsDadosProgramadosElementosFluxoControladoEntity =
            onsDadosProgramadosElementosFluxoControladoRepository
                .findById(onsDadosProgramadosElementosFluxoControladoEntity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosProgramadosElementosFluxoControladoEntity are not directly saved in db
        em.detach(updatedOnsDadosProgramadosElementosFluxoControladoEntity);
        updatedOnsDadosProgramadosElementosFluxoControladoEntity
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .nomElementofluxocontrolado(UPDATED_NOM_ELEMENTOFLUXOCONTROLADO)
            .dscElementofluxocontrolado(UPDATED_DSC_ELEMENTOFLUXOCONTROLADO)
            .tipTerminal(UPDATED_TIP_TERMINAL)
            .codSubmercado(UPDATED_COD_SUBMERCADO)
            .valCarga(UPDATED_VAL_CARGA);

        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosProgramadosElementosFluxoControladoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosProgramadosElementosFluxoControladoEntityToMatchAllProperties(
            updatedOnsDadosProgramadosElementosFluxoControladoEntity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsDadosProgramadosElementosFluxoControladoEntity> onsDadosProgramadosElementosFluxoControladoSearchList =
                    Streamable.of(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll()).toList();
                OnsDadosProgramadosElementosFluxoControladoEntity testOnsDadosProgramadosElementosFluxoControladoSearch =
                    onsDadosProgramadosElementosFluxoControladoSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosProgramadosElementosFluxoControladoEntityAllPropertiesEquals(
                    testOnsDadosProgramadosElementosFluxoControladoSearch,
                    updatedOnsDadosProgramadosElementosFluxoControladoEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        onsDadosProgramadosElementosFluxoControladoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosProgramadosElementosFluxoControladoEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        onsDadosProgramadosElementosFluxoControladoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        onsDadosProgramadosElementosFluxoControladoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosProgramadosElementosFluxoControladoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.saveAndFlush(
            onsDadosProgramadosElementosFluxoControladoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosProgramadosElementosFluxoControlado using partial update
        OnsDadosProgramadosElementosFluxoControladoEntity partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity =
            new OnsDadosProgramadosElementosFluxoControladoEntity();
        partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity.setId(onsDadosProgramadosElementosFluxoControladoEntity.getId());

        partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .valCarga(UPDATED_VAL_CARGA);

        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosProgramadosElementosFluxoControladoEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity,
                onsDadosProgramadosElementosFluxoControladoEntity
            ),
            getPersistedOnsDadosProgramadosElementosFluxoControladoEntity(onsDadosProgramadosElementosFluxoControladoEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosProgramadosElementosFluxoControladoWithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.saveAndFlush(
            onsDadosProgramadosElementosFluxoControladoEntity
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosProgramadosElementosFluxoControlado using partial update
        OnsDadosProgramadosElementosFluxoControladoEntity partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity =
            new OnsDadosProgramadosElementosFluxoControladoEntity();
        partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity.setId(onsDadosProgramadosElementosFluxoControladoEntity.getId());

        partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity
            .dinProgramacaodia(UPDATED_DIN_PROGRAMACAODIA)
            .numPatamar(UPDATED_NUM_PATAMAR)
            .nomElementofluxocontrolado(UPDATED_NOM_ELEMENTOFLUXOCONTROLADO)
            .dscElementofluxocontrolado(UPDATED_DSC_ELEMENTOFLUXOCONTROLADO)
            .tipTerminal(UPDATED_TIP_TERMINAL)
            .codSubmercado(UPDATED_COD_SUBMERCADO)
            .valCarga(UPDATED_VAL_CARGA);

        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosProgramadosElementosFluxoControladoEntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity,
            getPersistedOnsDadosProgramadosElementosFluxoControladoEntity(partialUpdatedOnsDadosProgramadosElementosFluxoControladoEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        onsDadosProgramadosElementosFluxoControladoEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosProgramadosElementosFluxoControladoEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        onsDadosProgramadosElementosFluxoControladoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        onsDadosProgramadosElementosFluxoControladoEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosProgramadosElementosFluxoControladoEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosProgramadosElementosFluxoControlado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        // Initialize the database
        insertedOnsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.saveAndFlush(
            onsDadosProgramadosElementosFluxoControladoEntity
        );
        onsDadosProgramadosElementosFluxoControladoRepository.save(onsDadosProgramadosElementosFluxoControladoEntity);
        onsDadosProgramadosElementosFluxoControladoSearchRepository.save(onsDadosProgramadosElementosFluxoControladoEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosProgramadosElementosFluxoControlado
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosProgramadosElementosFluxoControladoEntity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsDadosProgramadosElementosFluxoControladoSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosProgramadosElementosFluxoControlado() throws Exception {
        // Initialize the database
        insertedOnsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.saveAndFlush(
            onsDadosProgramadosElementosFluxoControladoEntity
        );
        onsDadosProgramadosElementosFluxoControladoSearchRepository.save(onsDadosProgramadosElementosFluxoControladoEntity);

        // Search the onsDadosProgramadosElementosFluxoControlado
        restOnsDadosProgramadosElementosFluxoControladoMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosProgramadosElementosFluxoControladoEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsDadosProgramadosElementosFluxoControladoEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].dinProgramacaodia").value(hasItem(DEFAULT_DIN_PROGRAMACAODIA.toString())))
            .andExpect(jsonPath("$.[*].numPatamar").value(hasItem(DEFAULT_NUM_PATAMAR)))
            .andExpect(jsonPath("$.[*].nomElementofluxocontrolado").value(hasItem(DEFAULT_NOM_ELEMENTOFLUXOCONTROLADO)))
            .andExpect(jsonPath("$.[*].dscElementofluxocontrolado").value(hasItem(DEFAULT_DSC_ELEMENTOFLUXOCONTROLADO)))
            .andExpect(jsonPath("$.[*].tipTerminal").value(hasItem(DEFAULT_TIP_TERMINAL)))
            .andExpect(jsonPath("$.[*].codSubmercado").value(hasItem(DEFAULT_COD_SUBMERCADO)))
            .andExpect(jsonPath("$.[*].valCarga").value(hasItem(DEFAULT_VAL_CARGA)));
    }

    protected long getRepositoryCount() {
        return onsDadosProgramadosElementosFluxoControladoRepository.count();
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

    protected OnsDadosProgramadosElementosFluxoControladoEntity getPersistedOnsDadosProgramadosElementosFluxoControladoEntity(
        OnsDadosProgramadosElementosFluxoControladoEntity onsDadosProgramadosElementosFluxoControlado
    ) {
        return onsDadosProgramadosElementosFluxoControladoRepository
            .findById(onsDadosProgramadosElementosFluxoControlado.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosProgramadosElementosFluxoControladoEntityToMatchAllProperties(
        OnsDadosProgramadosElementosFluxoControladoEntity expectedOnsDadosProgramadosElementosFluxoControladoEntity
    ) {
        assertOnsDadosProgramadosElementosFluxoControladoEntityAllPropertiesEquals(
            expectedOnsDadosProgramadosElementosFluxoControladoEntity,
            getPersistedOnsDadosProgramadosElementosFluxoControladoEntity(expectedOnsDadosProgramadosElementosFluxoControladoEntity)
        );
    }

    protected void assertPersistedOnsDadosProgramadosElementosFluxoControladoEntityToMatchUpdatableProperties(
        OnsDadosProgramadosElementosFluxoControladoEntity expectedOnsDadosProgramadosElementosFluxoControladoEntity
    ) {
        assertOnsDadosProgramadosElementosFluxoControladoEntityAllUpdatablePropertiesEquals(
            expectedOnsDadosProgramadosElementosFluxoControladoEntity,
            getPersistedOnsDadosProgramadosElementosFluxoControladoEntity(expectedOnsDadosProgramadosElementosFluxoControladoEntity)
        );
    }
}
