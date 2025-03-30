package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsCvuUsinaTermicasEntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsCvuUsinaTermicasEntity;
import com.alexandrebfreitas.wue.repository.OnsCvuUsinaTermicasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCvuUsinaTermicasSearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link OnsCvuUsinaTermicasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsCvuUsinaTermicasResourceIT {

    private static final LocalDate DEFAULT_DAT_INICIOSEMANA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_INICIOSEMANA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DAT_FIMSEMANA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_FIMSEMANA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ANO_REFERENCIA = 1;
    private static final Integer UPDATED_ANO_REFERENCIA = 2;

    private static final Integer DEFAULT_MES_REFERENCIA = 1;
    private static final Integer UPDATED_MES_REFERENCIA = 2;

    private static final Integer DEFAULT_NUM_REVISAO = 1;
    private static final Integer UPDATED_NUM_REVISAO = 2;

    private static final String DEFAULT_NOM_SEMANAOPERATIVA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SEMANAOPERATIVA = "BBBBBBBBBB";

    private static final Integer DEFAULT_COD_MODELOS = 1;
    private static final Integer UPDATED_COD_MODELOS = 2;

    private static final String DEFAULT_ID_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_ID_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SUBSISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SUBSISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINA = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VAL_CVU = new BigDecimal(1);
    private static final BigDecimal UPDATED_VAL_CVU = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/ons-cvu-usina-termicas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-cvu-usina-termicas/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsCvuUsinaTermicasRepository onsCvuUsinaTermicasRepository;

    @Autowired
    private OnsCvuUsinaTermicasSearchRepository onsCvuUsinaTermicasSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsCvuUsinaTermicasMockMvc;

    private OnsCvuUsinaTermicasEntity onsCvuUsinaTermicasEntity;

    private OnsCvuUsinaTermicasEntity insertedOnsCvuUsinaTermicasEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCvuUsinaTermicasEntity createEntity() {
        return new OnsCvuUsinaTermicasEntity()
            .datIniciosemana(DEFAULT_DAT_INICIOSEMANA)
            .datFimsemana(DEFAULT_DAT_FIMSEMANA)
            .anoReferencia(DEFAULT_ANO_REFERENCIA)
            .mesReferencia(DEFAULT_MES_REFERENCIA)
            .numRevisao(DEFAULT_NUM_REVISAO)
            .nomSemanaoperativa(DEFAULT_NOM_SEMANAOPERATIVA)
            .codModelos(DEFAULT_COD_MODELOS)
            .idSubsistema(DEFAULT_ID_SUBSISTEMA)
            .nomSubsistema(DEFAULT_NOM_SUBSISTEMA)
            .nomUsina(DEFAULT_NOM_USINA)
            .valCvu(DEFAULT_VAL_CVU);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsCvuUsinaTermicasEntity createUpdatedEntity() {
        return new OnsCvuUsinaTermicasEntity()
            .datIniciosemana(UPDATED_DAT_INICIOSEMANA)
            .datFimsemana(UPDATED_DAT_FIMSEMANA)
            .anoReferencia(UPDATED_ANO_REFERENCIA)
            .mesReferencia(UPDATED_MES_REFERENCIA)
            .numRevisao(UPDATED_NUM_REVISAO)
            .nomSemanaoperativa(UPDATED_NOM_SEMANAOPERATIVA)
            .codModelos(UPDATED_COD_MODELOS)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomUsina(UPDATED_NOM_USINA)
            .valCvu(UPDATED_VAL_CVU);
    }

    @BeforeEach
    public void initTest() {
        onsCvuUsinaTermicasEntity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsCvuUsinaTermicasEntity != null) {
            onsCvuUsinaTermicasRepository.delete(insertedOnsCvuUsinaTermicasEntity);
            onsCvuUsinaTermicasSearchRepository.delete(insertedOnsCvuUsinaTermicasEntity);
            insertedOnsCvuUsinaTermicasEntity = null;
        }
    }

    @Test
    @Transactional
    void createOnsCvuUsinaTermicas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        // Create the OnsCvuUsinaTermicas
        var returnedOnsCvuUsinaTermicasEntity = om.readValue(
            restOnsCvuUsinaTermicasMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsCvuUsinaTermicasEntity.class
        );

        // Validate the OnsCvuUsinaTermicas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsCvuUsinaTermicasEntityUpdatableFieldsEquals(
            returnedOnsCvuUsinaTermicasEntity,
            getPersistedOnsCvuUsinaTermicasEntity(returnedOnsCvuUsinaTermicasEntity)
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsCvuUsinaTermicasEntity = returnedOnsCvuUsinaTermicasEntity;
    }

    @Test
    @Transactional
    void createOnsCvuUsinaTermicasWithExistingId() throws Exception {
        // Create the OnsCvuUsinaTermicas with an existing ID
        onsCvuUsinaTermicasEntity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsCvuUsinaTermicasMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsCvuUsinaTermicas() throws Exception {
        // Initialize the database
        insertedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.saveAndFlush(onsCvuUsinaTermicasEntity);

        // Get all the onsCvuUsinaTermicasList
        restOnsCvuUsinaTermicasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCvuUsinaTermicasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].datIniciosemana").value(hasItem(DEFAULT_DAT_INICIOSEMANA.toString())))
            .andExpect(jsonPath("$.[*].datFimsemana").value(hasItem(DEFAULT_DAT_FIMSEMANA.toString())))
            .andExpect(jsonPath("$.[*].anoReferencia").value(hasItem(DEFAULT_ANO_REFERENCIA)))
            .andExpect(jsonPath("$.[*].mesReferencia").value(hasItem(DEFAULT_MES_REFERENCIA)))
            .andExpect(jsonPath("$.[*].numRevisao").value(hasItem(DEFAULT_NUM_REVISAO)))
            .andExpect(jsonPath("$.[*].nomSemanaoperativa").value(hasItem(DEFAULT_NOM_SEMANAOPERATIVA)))
            .andExpect(jsonPath("$.[*].codModelos").value(hasItem(DEFAULT_COD_MODELOS)))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].valCvu").value(hasItem(sameNumber(DEFAULT_VAL_CVU))));
    }

    @Test
    @Transactional
    void getOnsCvuUsinaTermicas() throws Exception {
        // Initialize the database
        insertedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.saveAndFlush(onsCvuUsinaTermicasEntity);

        // Get the onsCvuUsinaTermicas
        restOnsCvuUsinaTermicasMockMvc
            .perform(get(ENTITY_API_URL_ID, onsCvuUsinaTermicasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsCvuUsinaTermicasEntity.getId().intValue()))
            .andExpect(jsonPath("$.datIniciosemana").value(DEFAULT_DAT_INICIOSEMANA.toString()))
            .andExpect(jsonPath("$.datFimsemana").value(DEFAULT_DAT_FIMSEMANA.toString()))
            .andExpect(jsonPath("$.anoReferencia").value(DEFAULT_ANO_REFERENCIA))
            .andExpect(jsonPath("$.mesReferencia").value(DEFAULT_MES_REFERENCIA))
            .andExpect(jsonPath("$.numRevisao").value(DEFAULT_NUM_REVISAO))
            .andExpect(jsonPath("$.nomSemanaoperativa").value(DEFAULT_NOM_SEMANAOPERATIVA))
            .andExpect(jsonPath("$.codModelos").value(DEFAULT_COD_MODELOS))
            .andExpect(jsonPath("$.idSubsistema").value(DEFAULT_ID_SUBSISTEMA))
            .andExpect(jsonPath("$.nomSubsistema").value(DEFAULT_NOM_SUBSISTEMA))
            .andExpect(jsonPath("$.nomUsina").value(DEFAULT_NOM_USINA))
            .andExpect(jsonPath("$.valCvu").value(sameNumber(DEFAULT_VAL_CVU)));
    }

    @Test
    @Transactional
    void getNonExistingOnsCvuUsinaTermicas() throws Exception {
        // Get the onsCvuUsinaTermicas
        restOnsCvuUsinaTermicasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsCvuUsinaTermicas() throws Exception {
        // Initialize the database
        insertedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.saveAndFlush(onsCvuUsinaTermicasEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsCvuUsinaTermicasSearchRepository.save(onsCvuUsinaTermicasEntity);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());

        // Update the onsCvuUsinaTermicas
        OnsCvuUsinaTermicasEntity updatedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository
            .findById(onsCvuUsinaTermicasEntity.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsCvuUsinaTermicasEntity are not directly saved in db
        em.detach(updatedOnsCvuUsinaTermicasEntity);
        updatedOnsCvuUsinaTermicasEntity
            .datIniciosemana(UPDATED_DAT_INICIOSEMANA)
            .datFimsemana(UPDATED_DAT_FIMSEMANA)
            .anoReferencia(UPDATED_ANO_REFERENCIA)
            .mesReferencia(UPDATED_MES_REFERENCIA)
            .numRevisao(UPDATED_NUM_REVISAO)
            .nomSemanaoperativa(UPDATED_NOM_SEMANAOPERATIVA)
            .codModelos(UPDATED_COD_MODELOS)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomUsina(UPDATED_NOM_USINA)
            .valCvu(UPDATED_VAL_CVU);

        restOnsCvuUsinaTermicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsCvuUsinaTermicasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsCvuUsinaTermicasEntityToMatchAllProperties(updatedOnsCvuUsinaTermicasEntity);

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OnsCvuUsinaTermicasEntity> onsCvuUsinaTermicasSearchList = Streamable.of(
                    onsCvuUsinaTermicasSearchRepository.findAll()
                ).toList();
                OnsCvuUsinaTermicasEntity testOnsCvuUsinaTermicasSearch = onsCvuUsinaTermicasSearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsCvuUsinaTermicasEntityAllPropertiesEquals(testOnsCvuUsinaTermicasSearch, updatedOnsCvuUsinaTermicasEntity);
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsCvuUsinaTermicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        onsCvuUsinaTermicasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCvuUsinaTermicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsCvuUsinaTermicasEntity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsCvuUsinaTermicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        onsCvuUsinaTermicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCvuUsinaTermicasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsCvuUsinaTermicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        onsCvuUsinaTermicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCvuUsinaTermicasMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsCvuUsinaTermicasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.saveAndFlush(onsCvuUsinaTermicasEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCvuUsinaTermicas using partial update
        OnsCvuUsinaTermicasEntity partialUpdatedOnsCvuUsinaTermicasEntity = new OnsCvuUsinaTermicasEntity();
        partialUpdatedOnsCvuUsinaTermicasEntity.setId(onsCvuUsinaTermicasEntity.getId());

        partialUpdatedOnsCvuUsinaTermicasEntity
            .numRevisao(UPDATED_NUM_REVISAO)
            .nomSemanaoperativa(UPDATED_NOM_SEMANAOPERATIVA)
            .codModelos(UPDATED_COD_MODELOS);

        restOnsCvuUsinaTermicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCvuUsinaTermicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCvuUsinaTermicas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCvuUsinaTermicasEntityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOnsCvuUsinaTermicasEntity, onsCvuUsinaTermicasEntity),
            getPersistedOnsCvuUsinaTermicasEntity(onsCvuUsinaTermicasEntity)
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsCvuUsinaTermicasWithPatch() throws Exception {
        // Initialize the database
        insertedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.saveAndFlush(onsCvuUsinaTermicasEntity);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsCvuUsinaTermicas using partial update
        OnsCvuUsinaTermicasEntity partialUpdatedOnsCvuUsinaTermicasEntity = new OnsCvuUsinaTermicasEntity();
        partialUpdatedOnsCvuUsinaTermicasEntity.setId(onsCvuUsinaTermicasEntity.getId());

        partialUpdatedOnsCvuUsinaTermicasEntity
            .datIniciosemana(UPDATED_DAT_INICIOSEMANA)
            .datFimsemana(UPDATED_DAT_FIMSEMANA)
            .anoReferencia(UPDATED_ANO_REFERENCIA)
            .mesReferencia(UPDATED_MES_REFERENCIA)
            .numRevisao(UPDATED_NUM_REVISAO)
            .nomSemanaoperativa(UPDATED_NOM_SEMANAOPERATIVA)
            .codModelos(UPDATED_COD_MODELOS)
            .idSubsistema(UPDATED_ID_SUBSISTEMA)
            .nomSubsistema(UPDATED_NOM_SUBSISTEMA)
            .nomUsina(UPDATED_NOM_USINA)
            .valCvu(UPDATED_VAL_CVU);

        restOnsCvuUsinaTermicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsCvuUsinaTermicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isOk());

        // Validate the OnsCvuUsinaTermicas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsCvuUsinaTermicasEntityUpdatableFieldsEquals(
            partialUpdatedOnsCvuUsinaTermicasEntity,
            getPersistedOnsCvuUsinaTermicasEntity(partialUpdatedOnsCvuUsinaTermicasEntity)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsCvuUsinaTermicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        onsCvuUsinaTermicasEntity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsCvuUsinaTermicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsCvuUsinaTermicasEntity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsCvuUsinaTermicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        onsCvuUsinaTermicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCvuUsinaTermicasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsCvuUsinaTermicas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        onsCvuUsinaTermicasEntity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsCvuUsinaTermicasMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsCvuUsinaTermicasEntity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsCvuUsinaTermicas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsCvuUsinaTermicas() throws Exception {
        // Initialize the database
        insertedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.saveAndFlush(onsCvuUsinaTermicasEntity);
        onsCvuUsinaTermicasRepository.save(onsCvuUsinaTermicasEntity);
        onsCvuUsinaTermicasSearchRepository.save(onsCvuUsinaTermicasEntity);

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsCvuUsinaTermicas
        restOnsCvuUsinaTermicasMockMvc
            .perform(delete(ENTITY_API_URL_ID, onsCvuUsinaTermicasEntity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(onsCvuUsinaTermicasSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsCvuUsinaTermicas() throws Exception {
        // Initialize the database
        insertedOnsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.saveAndFlush(onsCvuUsinaTermicasEntity);
        onsCvuUsinaTermicasSearchRepository.save(onsCvuUsinaTermicasEntity);

        // Search the onsCvuUsinaTermicas
        restOnsCvuUsinaTermicasMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsCvuUsinaTermicasEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onsCvuUsinaTermicasEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].datIniciosemana").value(hasItem(DEFAULT_DAT_INICIOSEMANA.toString())))
            .andExpect(jsonPath("$.[*].datFimsemana").value(hasItem(DEFAULT_DAT_FIMSEMANA.toString())))
            .andExpect(jsonPath("$.[*].anoReferencia").value(hasItem(DEFAULT_ANO_REFERENCIA)))
            .andExpect(jsonPath("$.[*].mesReferencia").value(hasItem(DEFAULT_MES_REFERENCIA)))
            .andExpect(jsonPath("$.[*].numRevisao").value(hasItem(DEFAULT_NUM_REVISAO)))
            .andExpect(jsonPath("$.[*].nomSemanaoperativa").value(hasItem(DEFAULT_NOM_SEMANAOPERATIVA)))
            .andExpect(jsonPath("$.[*].codModelos").value(hasItem(DEFAULT_COD_MODELOS)))
            .andExpect(jsonPath("$.[*].idSubsistema").value(hasItem(DEFAULT_ID_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomSubsistema").value(hasItem(DEFAULT_NOM_SUBSISTEMA)))
            .andExpect(jsonPath("$.[*].nomUsina").value(hasItem(DEFAULT_NOM_USINA)))
            .andExpect(jsonPath("$.[*].valCvu").value(hasItem(sameNumber(DEFAULT_VAL_CVU))));
    }

    protected long getRepositoryCount() {
        return onsCvuUsinaTermicasRepository.count();
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

    protected OnsCvuUsinaTermicasEntity getPersistedOnsCvuUsinaTermicasEntity(OnsCvuUsinaTermicasEntity onsCvuUsinaTermicas) {
        return onsCvuUsinaTermicasRepository.findById(onsCvuUsinaTermicas.getId()).orElseThrow();
    }

    protected void assertPersistedOnsCvuUsinaTermicasEntityToMatchAllProperties(
        OnsCvuUsinaTermicasEntity expectedOnsCvuUsinaTermicasEntity
    ) {
        assertOnsCvuUsinaTermicasEntityAllPropertiesEquals(
            expectedOnsCvuUsinaTermicasEntity,
            getPersistedOnsCvuUsinaTermicasEntity(expectedOnsCvuUsinaTermicasEntity)
        );
    }

    protected void assertPersistedOnsCvuUsinaTermicasEntityToMatchUpdatableProperties(
        OnsCvuUsinaTermicasEntity expectedOnsCvuUsinaTermicasEntity
    ) {
        assertOnsCvuUsinaTermicasEntityAllUpdatablePropertiesEquals(
            expectedOnsCvuUsinaTermicasEntity,
            getPersistedOnsCvuUsinaTermicasEntity(expectedOnsCvuUsinaTermicasEntity)
        );
    }
}
