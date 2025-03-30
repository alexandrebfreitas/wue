package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioReservatorioRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioReservatorioSearchRepository;
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
 * Integration tests for the {@link OnsEarDiarioReservatorioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsEarDiarioReservatorioResourceIT {

    private static final String DEFAULT_ID_SUBSISTEMA_JUSANTE = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA_JUSANTE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA_JUSANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA_JUSANTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EAR_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EAR_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES = 1D;
    private static final Double UPDATED_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES = 2D;

    private static final Double DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES = 1D;
    private static final Double UPDATED_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES = 2D;

    private static final Double DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES = 1D;
    private static final Double UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES = 2D;

    private static final Double DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES = 1D;
    private static final Double UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES = 2D;

    private static final Double DEFAULT_EAR_RESERVATORIO_PERCENTUAL = 1D;
    private static final Double UPDATED_EAR_RESERVATORIO_PERCENTUAL = 2D;

    private static final Double DEFAULT_EAR_TOTAL_MWMES = 1D;
    private static final Double UPDATED_EAR_TOTAL_MWMES = 2D;

    private static final Double DEFAULT_EAR_MAXIMA_TOTAL_MWMES = 1D;
    private static final Double UPDATED_EAR_MAXIMA_TOTAL_MWMES = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARBACIA = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARBACIA = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARMAXBACIA = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARMAXBACIA = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARSUBSISTEMA = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARSUBSISTEMA = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMA = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARMAXSUBSISTEMA = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARSUBSISTEMAJUSANTE = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARSUBSISTEMAJUSANTE = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARSIN = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARSIN = 2D;

    private static final Double DEFAULT_VAL_CONTRIBEARMAXSIN = 1D;
    private static final Double UPDATED_VAL_CONTRIBEARMAXSIN = 2D;

    private static final String ENTITY_API_URL = "/api/ons-ear-diario-reservatorios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-ear-diario-reservatorios/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsEarDiarioReservatorioRepository onsEarDiarioReservatorioRepository;

    @Autowired
    private OnsEarDiarioReservatorioSearchRepository onsEarDiarioReservatorioSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsEarDiarioReservatorioMockMvc;

    private OnsEarDiarioReservatorioEntity onsEarDiarioReservatorioEntity;

    private OnsEarDiarioReservatorioEntity insertedOnsEarDiarioReservatorioEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioReservatorioEntity createEntity() {
        return new OnsEarDiarioReservatorioEntity()
            .idSubsistemaJusante(DEFAULT_ID_SUBSISTEMA_JUSANTE)
            .nomSubsistemaJusante(DEFAULT_NOM_SUBSISTEMA_JUSANTE)
            .earData(DEFAULT_EAR_DATA)
            .earReservatorioSubsistemaProprioMwmes(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earReservatorioSubsistemaJusanteMwmes(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earmaxReservatorioSubsistemaProprioMwmes(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earmaxReservatorioSubsistemaJusanteMwmes(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earReservatorioPercentual(DEFAULT_EAR_RESERVATORIO_PERCENTUAL)
            .earTotalMwmes(DEFAULT_EAR_TOTAL_MWMES)
            .earMaximaTotalMwmes(DEFAULT_EAR_MAXIMA_TOTAL_MWMES)
            .valContribearbacia(DEFAULT_VAL_CONTRIBEARBACIA)
            .valContribearmaxbacia(DEFAULT_VAL_CONTRIBEARMAXBACIA)
            .valContribearsubsistema(DEFAULT_VAL_CONTRIBEARSUBSISTEMA)
            .valContribearmaxsubsistema(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMA)
            .valContribearsubsistemajusante(DEFAULT_VAL_CONTRIBEARSUBSISTEMAJUSANTE)
            .valContribearmaxsubsistemajusante(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE)
            .valContribearsin(DEFAULT_VAL_CONTRIBEARSIN)
            .valContribearmaxsin(DEFAULT_VAL_CONTRIBEARMAXSIN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsEarDiarioReservatorioEntity createUpdatedEntity() {
        return new OnsEarDiarioReservatorioEntity()
            .idSubsistemaJusante(UPDATED_ID_SUBSISTEMA_JUSANTE)
            .nomSubsistemaJusante(UPDATED_NOM_SUBSISTEMA_JUSANTE)
            .earData(UPDATED_EAR_DATA)
            .earReservatorioSubsistemaProprioMwmes(UPDATED_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earReservatorioSubsistemaJusanteMwmes(UPDATED_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earmaxReservatorioSubsistemaProprioMwmes(UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earmaxReservatorioSubsistemaJusanteMwmes(UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earReservatorioPercentual(UPDATED_EAR_RESERVATORIO_PERCENTUAL)
            .earTotalMwmes(UPDATED_EAR_TOTAL_MWMES)
            .earMaximaTotalMwmes(UPDATED_EAR_MAXIMA_TOTAL_MWMES)
            .valContribearbacia(UPDATED_VAL_CONTRIBEARBACIA)
            .valContribearmaxbacia(UPDATED_VAL_CONTRIBEARMAXBACIA)
            .valContribearsubsistema(UPDATED_VAL_CONTRIBEARSUBSISTEMA)
            .valContribearmaxsubsistema(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMA)
            .valContribearsubsistemajusante(UPDATED_VAL_CONTRIBEARSUBSISTEMAJUSANTE)
            .valContribearmaxsubsistemajusante(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE)
            .valContribearsin(UPDATED_VAL_CONTRIBEARSIN)
            .valContribearmaxsin(UPDATED_VAL_CONTRIBEARMAXSIN);
    }

    @BeforeEach
    public void initTest() {
        onsEarDiarioReservatorioEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsEarDiarioReservatorioEntity != null) {
            onsEarDiarioReservatorioRepository.delete(insertedOnsEarDiarioReservatorioEntity);
            onsEarDiarioReservatorioSearchRepository.delete(insertedOnsEarDiarioReservatorioEntity);
            insertedOnsEarDiarioReservatorioEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsEarDiarioReservatorio() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        // Create the OnsEarDiarioReservatorio
        var returnedOnsEarDiarioReservatorioEntity = om.readValue(
            restOnsEarDiarioReservatorioMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsEarDiarioReservatorioEntity.class
        );

        // Validate the OnsEarDiarioReservatorio in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsEarDiarioReservatorioEntityUpdatableFieldsEquals(
            returnedOnsEarDiarioReservatorioEntity,
            getPersistedOnsEarDiarioReservatorioEntity(returnedOnsEarDiarioReservatorioEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsEarDiarioReservatorioEntity = returnedOnsEarDiarioReservatorioEntity;
    }

    @Test
    @Transactional
    void createOnsEarDiarioReservatorioWithExistingId() throws Exception {
        // Create the OnsEarDiarioReservatorio with an existing ID
        onsEarDiarioReservatorioEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsEarDiarioReservatorioMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsEarDiarioReservatorios() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.saveAndFlush(onsEarDiarioReservatorioEntity);

        // Get all the onsEarDiarioReservatorioList
        restOnsEarDiarioReservatorioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioReservatorioEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistemaJusante").value(hasItem(DEFAULT_ID_SUBSISTEMA_JUSANTE)))
            .andExpect(jsonPath("$.[*].nomSubsistemaJusante").value(hasItem(DEFAULT_NOM_SUBSISTEMA_JUSANTE)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(
                jsonPath("$.[*].earReservatorioSubsistemaProprioMwmes").value(hasItem(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES))
            )
            .andExpect(
                jsonPath("$.[*].earReservatorioSubsistemaJusanteMwmes").value(hasItem(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES))
            )
            .andExpect(
                jsonPath("$.[*].earmaxReservatorioSubsistemaProprioMwmes").value(
                    hasItem(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
                )
            )
            .andExpect(
                jsonPath("$.[*].earmaxReservatorioSubsistemaJusanteMwmes").value(
                    hasItem(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
                )
            )
            .andExpect(jsonPath("$.[*].earReservatorioPercentual").value(hasItem(DEFAULT_EAR_RESERVATORIO_PERCENTUAL)))
            .andExpect(jsonPath("$.[*].earTotalMwmes").value(hasItem(DEFAULT_EAR_TOTAL_MWMES)))
            .andExpect(jsonPath("$.[*].earMaximaTotalMwmes").value(hasItem(DEFAULT_EAR_MAXIMA_TOTAL_MWMES)))
            .andExpect(jsonPath("$.[*].valContribearbacia").value(hasItem(DEFAULT_VAL_CONTRIBEARBACIA)))
            .andExpect(jsonPath("$.[*].valContribearmaxbacia").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXBACIA)))
            .andExpect(jsonPath("$.[*].valContribearsubsistema").value(hasItem(DEFAULT_VAL_CONTRIBEARSUBSISTEMA)))
            .andExpect(jsonPath("$.[*].valContribearmaxsubsistema").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMA)))
            .andExpect(jsonPath("$.[*].valContribearsubsistemajusante").value(hasItem(DEFAULT_VAL_CONTRIBEARSUBSISTEMAJUSANTE)))
            .andExpect(jsonPath("$.[*].valContribearmaxsubsistemajusante").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE)))
            .andExpect(jsonPath("$.[*].valContribearsin").value(hasItem(DEFAULT_VAL_CONTRIBEARSIN)))
            .andExpect(jsonPath("$.[*].valContribearmaxsin").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXSIN)));
    }

    @Test
    @Transactional
    void getOnsEarDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.saveAndFlush(onsEarDiarioReservatorioEntity);

        // Get the onsEarDiarioReservatorio
        restOnsEarDiarioReservatorioMockMvc
            .perform(get(ENTITY_API_URL_ID, onsEarDiarioReservatorioEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsEarDiarioReservatorioEntity.getId().intValue()))
            .andExpect(jsonPath("$.idSubsistemaJusante").value(DEFAULT_ID_SUBSISTEMA_JUSANTE))
            .andExpect(jsonPath("$.nomSubsistemaJusante").value(DEFAULT_NOM_SUBSISTEMA_JUSANTE))
            .andExpect(jsonPath("$.earData").value(DEFAULT_EAR_DATA.toString()))
            .andExpect(jsonPath("$.earReservatorioSubsistemaProprioMwmes").value(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES))
            .andExpect(jsonPath("$.earReservatorioSubsistemaJusanteMwmes").value(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES))
            .andExpect(jsonPath("$.earmaxReservatorioSubsistemaProprioMwmes").value(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES))
            .andExpect(jsonPath("$.earmaxReservatorioSubsistemaJusanteMwmes").value(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES))
            .andExpect(jsonPath("$.earReservatorioPercentual").value(DEFAULT_EAR_RESERVATORIO_PERCENTUAL))
            .andExpect(jsonPath("$.earTotalMwmes").value(DEFAULT_EAR_TOTAL_MWMES))
            .andExpect(jsonPath("$.earMaximaTotalMwmes").value(DEFAULT_EAR_MAXIMA_TOTAL_MWMES))
            .andExpect(jsonPath("$.valContribearbacia").value(DEFAULT_VAL_CONTRIBEARBACIA))
            .andExpect(jsonPath("$.valContribearmaxbacia").value(DEFAULT_VAL_CONTRIBEARMAXBACIA))
            .andExpect(jsonPath("$.valContribearsubsistema").value(DEFAULT_VAL_CONTRIBEARSUBSISTEMA))
            .andExpect(jsonPath("$.valContribearmaxsubsistema").value(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMA))
            .andExpect(jsonPath("$.valContribearsubsistemajusante").value(DEFAULT_VAL_CONTRIBEARSUBSISTEMAJUSANTE))
            .andExpect(jsonPath("$.valContribearmaxsubsistemajusante").value(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE))
            .andExpect(jsonPath("$.valContribearsin").value(DEFAULT_VAL_CONTRIBEARSIN))
            .andExpect(jsonPath("$.valContribearmaxsin").value(DEFAULT_VAL_CONTRIBEARMAXSIN));
    }

    @Test
    @Transactional
    void getNonExistingOnsEarDiarioReservatorio() throws Exception {
        // Get the onsEarDiarioReservatorio
        restOnsEarDiarioReservatorioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsEarDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.saveAndFlush(onsEarDiarioReservatorioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsEarDiarioReservatorioSearchRepository.save(onsEarDiarioReservatorioEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());

        // Update the onsEarDiarioReservatorio
        OnsEarDiarioReservatorioEntity updatedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository
            .findById(onsEarDiarioReservatorioEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsEarDiarioReservatorioEntity are not directly saved in db
        em.detach(updatedOnsEarDiarioReservatorioEntity);
        updatedOnsEarDiarioReservatorioEntity
            .idSubsistemaJusante(UPDATED_ID_SUBSISTEMA_JUSANTE)
            .nomSubsistemaJusante(UPDATED_NOM_SUBSISTEMA_JUSANTE)
            .earData(UPDATED_EAR_DATA)
            .earReservatorioSubsistemaProprioMwmes(UPDATED_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earReservatorioSubsistemaJusanteMwmes(UPDATED_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earmaxReservatorioSubsistemaProprioMwmes(UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earmaxReservatorioSubsistemaJusanteMwmes(UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earReservatorioPercentual(UPDATED_EAR_RESERVATORIO_PERCENTUAL)
            .earTotalMwmes(UPDATED_EAR_TOTAL_MWMES)
            .earMaximaTotalMwmes(UPDATED_EAR_MAXIMA_TOTAL_MWMES)
            .valContribearbacia(UPDATED_VAL_CONTRIBEARBACIA)
            .valContribearmaxbacia(UPDATED_VAL_CONTRIBEARMAXBACIA)
            .valContribearsubsistema(UPDATED_VAL_CONTRIBEARSUBSISTEMA)
            .valContribearmaxsubsistema(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMA)
            .valContribearsubsistemajusante(UPDATED_VAL_CONTRIBEARSUBSISTEMAJUSANTE)
            .valContribearmaxsubsistemajusante(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE)
            .valContribearsin(UPDATED_VAL_CONTRIBEARSIN)
            .valContribearmaxsin(UPDATED_VAL_CONTRIBEARMAXSIN);

        restOnsEarDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsEarDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsEarDiarioReservatorioEntityToMatchAllProperties(updatedOnsEarDiarioReservatorioEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsEarDiarioReservatorioEntity> onsEarDiarioReservatorioSearchList = Streamable.of(
                    onsEarDiarioReservatorioSearchRepository.findAll()
                ).toList();
                OnsEarDiarioReservatorioEntity testOnsEarDiarioReservatorioSearch = onsEarDiarioReservatorioSearchList.get(
                    searchDatabaseSizeAfter - 1
                );

                assertOnsEarDiarioReservatorioEntityAllPropertiesEquals(
                    testOnsEarDiarioReservatorioSearch,
                    updatedOnsEarDiarioReservatorioEntity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsEarDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        onsEarDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsEarDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsEarDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        onsEarDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsEarDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        onsEarDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReservatorioMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsEarDiarioReservatorioWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.saveAndFlush(onsEarDiarioReservatorioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioReservatorio using partial update
        OnsEarDiarioReservatorioEntity partialUpdatedOnsEarDiarioReservatorioEntity = new OnsEarDiarioReservatorioEntity();
        partialUpdatedOnsEarDiarioReservatorioEntity.setId(onsEarDiarioReservatorioEntity.getId());

        partialUpdatedOnsEarDiarioReservatorioEntity
            .earData(UPDATED_EAR_DATA)
            .earReservatorioSubsistemaProprioMwmes(UPDATED_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earmaxReservatorioSubsistemaProprioMwmes(UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earReservatorioPercentual(UPDATED_EAR_RESERVATORIO_PERCENTUAL)
            .earMaximaTotalMwmes(UPDATED_EAR_MAXIMA_TOTAL_MWMES)
            .valContribearbacia(UPDATED_VAL_CONTRIBEARBACIA)
            .valContribearmaxbacia(UPDATED_VAL_CONTRIBEARMAXBACIA)
            .valContribearsubsistema(UPDATED_VAL_CONTRIBEARSUBSISTEMA)
            .valContribearmaxsubsistema(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMA)
            .valContribearmaxsubsistemajusante(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE)
            .valContribearsin(UPDATED_VAL_CONTRIBEARSIN)
            .valContribearmaxsin(UPDATED_VAL_CONTRIBEARMAXSIN);

        restOnsEarDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioReservatorio in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioReservatorioEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsEarDiarioReservatorioEntity, onsEarDiarioReservatorioEntity),
            getPersistedOnsEarDiarioReservatorioEntity(onsEarDiarioReservatorioEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsEarDiarioReservatorioWithPatch() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.saveAndFlush(onsEarDiarioReservatorioEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsEarDiarioReservatorio using partial update
        OnsEarDiarioReservatorioEntity partialUpdatedOnsEarDiarioReservatorioEntity = new OnsEarDiarioReservatorioEntity();
        partialUpdatedOnsEarDiarioReservatorioEntity.setId(onsEarDiarioReservatorioEntity.getId());

        partialUpdatedOnsEarDiarioReservatorioEntity
            .idSubsistemaJusante(UPDATED_ID_SUBSISTEMA_JUSANTE)
            .nomSubsistemaJusante(UPDATED_NOM_SUBSISTEMA_JUSANTE)
            .earData(UPDATED_EAR_DATA)
            .earReservatorioSubsistemaProprioMwmes(UPDATED_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earReservatorioSubsistemaJusanteMwmes(UPDATED_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earmaxReservatorioSubsistemaProprioMwmes(UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
            .earmaxReservatorioSubsistemaJusanteMwmes(UPDATED_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
            .earReservatorioPercentual(UPDATED_EAR_RESERVATORIO_PERCENTUAL)
            .earTotalMwmes(UPDATED_EAR_TOTAL_MWMES)
            .earMaximaTotalMwmes(UPDATED_EAR_MAXIMA_TOTAL_MWMES)
            .valContribearbacia(UPDATED_VAL_CONTRIBEARBACIA)
            .valContribearmaxbacia(UPDATED_VAL_CONTRIBEARMAXBACIA)
            .valContribearsubsistema(UPDATED_VAL_CONTRIBEARSUBSISTEMA)
            .valContribearmaxsubsistema(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMA)
            .valContribearsubsistemajusante(UPDATED_VAL_CONTRIBEARSUBSISTEMAJUSANTE)
            .valContribearmaxsubsistemajusante(UPDATED_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE)
            .valContribearsin(UPDATED_VAL_CONTRIBEARSIN)
            .valContribearmaxsin(UPDATED_VAL_CONTRIBEARMAXSIN);

        restOnsEarDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsEarDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsEarDiarioReservatorio in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsEarDiarioReservatorioEntityUpdatableFieldsEquals(
            partialUpdatedOnsEarDiarioReservatorioEntity,
            getPersistedOnsEarDiarioReservatorioEntity(partialUpdatedOnsEarDiarioReservatorioEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsEarDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        onsEarDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsEarDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsEarDiarioReservatorioEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsEarDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        onsEarDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsEarDiarioReservatorio() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        onsEarDiarioReservatorioEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsEarDiarioReservatorioMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsEarDiarioReservatorioEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsEarDiarioReservatorio in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsEarDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.saveAndFlush(onsEarDiarioReservatorioEntity);
        onsEarDiarioReservatorioRepository.save(onsEarDiarioReservatorioEntity);
        onsEarDiarioReservatorioSearchRepository.save(onsEarDiarioReservatorioEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsEarDiarioReservatorio
        restOnsEarDiarioReservatorioMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsEarDiarioReservatorioEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsEarDiarioReservatorioSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsEarDiarioReservatorio() throws Exception {
        // Initialize the database
        insertedOnsEarDiarioReservatorioEntity = onsEarDiarioReservatorioRepository.saveAndFlush(onsEarDiarioReservatorioEntity);
        onsEarDiarioReservatorioSearchRepository.save(onsEarDiarioReservatorioEntity);

        // Search the onsEarDiarioReservatorio
        restOnsEarDiarioReservatorioMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsEarDiarioReservatorioEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsEarDiarioReservatorioEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubsistemaJusante").value(hasItem(DEFAULT_ID_SUBSISTEMA_JUSANTE)))
            .andExpect(jsonPath("$.[*].nomSubsistemaJusante").value(hasItem(DEFAULT_NOM_SUBSISTEMA_JUSANTE)))
            .andExpect(jsonPath("$.[*].earData").value(hasItem(DEFAULT_EAR_DATA.toString())))
            .andExpect(
                jsonPath("$.[*].earReservatorioSubsistemaProprioMwmes").value(hasItem(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES))
            )
            .andExpect(
                jsonPath("$.[*].earReservatorioSubsistemaJusanteMwmes").value(hasItem(DEFAULT_EAR_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES))
            )
            .andExpect(
                jsonPath("$.[*].earmaxReservatorioSubsistemaProprioMwmes").value(
                    hasItem(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_PROPRIO_MWMES)
                )
            )
            .andExpect(
                jsonPath("$.[*].earmaxReservatorioSubsistemaJusanteMwmes").value(
                    hasItem(DEFAULT_EARMAX_RESERVATORIO_SUBSISTEMA_JUSANTE_MWMES)
                )
            )
            .andExpect(jsonPath("$.[*].earReservatorioPercentual").value(hasItem(DEFAULT_EAR_RESERVATORIO_PERCENTUAL)))
            .andExpect(jsonPath("$.[*].earTotalMwmes").value(hasItem(DEFAULT_EAR_TOTAL_MWMES)))
            .andExpect(jsonPath("$.[*].earMaximaTotalMwmes").value(hasItem(DEFAULT_EAR_MAXIMA_TOTAL_MWMES)))
            .andExpect(jsonPath("$.[*].valContribearbacia").value(hasItem(DEFAULT_VAL_CONTRIBEARBACIA)))
            .andExpect(jsonPath("$.[*].valContribearmaxbacia").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXBACIA)))
            .andExpect(jsonPath("$.[*].valContribearsubsistema").value(hasItem(DEFAULT_VAL_CONTRIBEARSUBSISTEMA)))
            .andExpect(jsonPath("$.[*].valContribearmaxsubsistema").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMA)))
            .andExpect(jsonPath("$.[*].valContribearsubsistemajusante").value(hasItem(DEFAULT_VAL_CONTRIBEARSUBSISTEMAJUSANTE)))
            .andExpect(jsonPath("$.[*].valContribearmaxsubsistemajusante").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXSUBSISTEMAJUSANTE)))
            .andExpect(jsonPath("$.[*].valContribearsin").value(hasItem(DEFAULT_VAL_CONTRIBEARSIN)))
            .andExpect(jsonPath("$.[*].valContribearmaxsin").value(hasItem(DEFAULT_VAL_CONTRIBEARMAXSIN)));
    }

    protected long getRepositoryCount() {
        return onsEarDiarioReservatorioRepository.count();
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

    protected OnsEarDiarioReservatorioEntity getPersistedOnsEarDiarioReservatorioEntity(
        OnsEarDiarioReservatorioEntity onsEarDiarioReservatorio
    ) {
        return onsEarDiarioReservatorioRepository.findById(onsEarDiarioReservatorio.getId()).orElseThrow();
    }

    protected void assertPersistedOnsEarDiarioReservatorioEntityToMatchAllProperties(
        OnsEarDiarioReservatorioEntity expectedOnsEarDiarioReservatorioEntity
    ) {
        assertOnsEarDiarioReservatorioEntityAllPropertiesEquals(
            expectedOnsEarDiarioReservatorioEntity,
            getPersistedOnsEarDiarioReservatorioEntity(expectedOnsEarDiarioReservatorioEntity)
        );
    }

    protected void assertPersistedOnsEarDiarioReservatorioEntityToMatchUpdatableProperties(
        OnsEarDiarioReservatorioEntity expectedOnsEarDiarioReservatorioEntity
    ) {
        assertOnsEarDiarioReservatorioEntityAllUpdatablePropertiesEquals(
            expectedOnsEarDiarioReservatorioEntity,
            getPersistedOnsEarDiarioReservatorioEntity(expectedOnsEarDiarioReservatorioEntity)
        );
    }
}
