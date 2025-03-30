package com.alexandrebfreitas.wue.web.rest;

import static com.alexandrebfreitas.wue.domain.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityAsserts.*;
import static com.alexandrebfreitas.wue.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alexandrebfreitas.wue.IntegrationTest;
import com.alexandrebfreitas.wue.domain.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity;
import com.alexandrebfreitas.wue.repository.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository;
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
 * Integration tests for the {@link OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ResourceIT {

    private static final String DEFAULT_DAT_PDP = "AAAAAAAAAA";
    private static final String UPDATED_DAT_PDP = "BBBBBBBBBB";

    private static final String DEFAULT_COD_SUBMERCADO = "AAAAAAAAAA";
    private static final String UPDATED_COD_SUBMERCADO = "BBBBBBBBBB";

    private static final String DEFAULT_SGL_TIPOUSINA = "AAAAAAAAAA";
    private static final String UPDATED_SGL_TIPOUSINA = "BBBBBBBBBB";

    private static final String DEFAULT_COD_USINAPDP = "AAAAAAAAAA";
    private static final String UPDATED_COD_USINAPDP = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USINAPDP = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USINAPDP = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_INTDESPA = 1;
    private static final Integer UPDATED_NUM_INTDESPA = 2;

    private static final Integer DEFAULT_VAL_DESPASUP = 1;
    private static final Integer UPDATED_VAL_DESPASUP = 2;

    private static final String ENTITY_API_URL = "/api/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/_search";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository;

    @Autowired
    private OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc;

    private OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity;

    private OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity createEntity() {
        return new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity()
            .datPdp(DEFAULT_DAT_PDP)
            .codSubmercado(DEFAULT_COD_SUBMERCADO)
            .sglTipousina(DEFAULT_SGL_TIPOUSINA)
            .codUsinapdp(DEFAULT_COD_USINAPDP)
            .nomUsinapdp(DEFAULT_NOM_USINAPDP)
            .numIntdespa(DEFAULT_NUM_INTDESPA)
            .valDespasup(DEFAULT_VAL_DESPASUP);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity createUpdatedEntity() {
        return new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity()
            .datPdp(UPDATED_DAT_PDP)
            .codSubmercado(UPDATED_COD_SUBMERCADO)
            .sglTipousina(UPDATED_SGL_TIPOUSINA)
            .codUsinapdp(UPDATED_COD_USINAPDP)
            .nomUsinapdp(UPDATED_NOM_USINAPDP)
            .numIntdespa(UPDATED_NUM_INTDESPA)
            .valDespasup(UPDATED_VAL_DESPASUP);
    }

    @BeforeEach
    public void initTest() {
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity != null) {
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.delete(
                insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.delete(
                insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );
            insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity = null;
        }
    }

    @Test
    @Transactional
    void createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        // Create the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
        var returnedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity = om.readValue(
            restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.class
        );

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityUpdatableFieldsEquals(
            returnedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity,
            getPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity(
                returnedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            )
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });

        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            returnedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity;
    }

    @Test
    @Transactional
    void createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024WithExistingId() throws Exception {
        // Create the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 with an existing ID
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s() throws Exception {
        // Initialize the database
        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.saveAndFlush(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );

        // Get all the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024List
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(hasItem(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId().intValue()))
            )
            .andExpect(jsonPath("$.[*].datPdp").value(hasItem(DEFAULT_DAT_PDP)))
            .andExpect(jsonPath("$.[*].codSubmercado").value(hasItem(DEFAULT_COD_SUBMERCADO)))
            .andExpect(jsonPath("$.[*].sglTipousina").value(hasItem(DEFAULT_SGL_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].codUsinapdp").value(hasItem(DEFAULT_COD_USINAPDP)))
            .andExpect(jsonPath("$.[*].nomUsinapdp").value(hasItem(DEFAULT_NOM_USINAPDP)))
            .andExpect(jsonPath("$.[*].numIntdespa").value(hasItem(DEFAULT_NUM_INTDESPA)))
            .andExpect(jsonPath("$.[*].valDespasup").value(hasItem(DEFAULT_VAL_DESPASUP)));
    }

    @Test
    @Transactional
    void getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        // Initialize the database
        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.saveAndFlush(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );

        // Get the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(get(ENTITY_API_URL_ID, onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId().intValue()))
            .andExpect(jsonPath("$.datPdp").value(DEFAULT_DAT_PDP))
            .andExpect(jsonPath("$.codSubmercado").value(DEFAULT_COD_SUBMERCADO))
            .andExpect(jsonPath("$.sglTipousina").value(DEFAULT_SGL_TIPOUSINA))
            .andExpect(jsonPath("$.codUsinapdp").value(DEFAULT_COD_USINAPDP))
            .andExpect(jsonPath("$.nomUsinapdp").value(DEFAULT_NOM_USINAPDP))
            .andExpect(jsonPath("$.numIntdespa").value(DEFAULT_NUM_INTDESPA))
            .andExpect(jsonPath("$.valDespasup").value(DEFAULT_VAL_DESPASUP));
    }

    @Test
    @Transactional
    void getNonExistingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        // Get the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        // Initialize the database
        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.saveAndFlush(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.save(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );

        // Update the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository
                .findById(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                .orElseThrow();
        // Disconnect from session so that the updates on updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity are not directly saved in db
        em.detach(updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity);
        updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            .datPdp(UPDATED_DAT_PDP)
            .codSubmercado(UPDATED_COD_SUBMERCADO)
            .sglTipousina(UPDATED_SGL_TIPOUSINA)
            .codUsinapdp(UPDATED_COD_USINAPDP)
            .nomUsinapdp(UPDATED_NOM_USINAPDP)
            .numIntdespa(UPDATED_NUM_INTDESPA)
            .valDespasup(UPDATED_VAL_DESPASUP);

        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityToMatchAllProperties(
            updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );

        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(
                    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
                );
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<
                    OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
                > onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchList = Streamable.of(
                    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
                ).toList();
                OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity testOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Search =
                    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchList.get(searchDatabaseSizeAfter - 1);

                assertOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityAllPropertiesEquals(
                    testOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Search,
                    updatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
                );
            });
    }

    @Test
    @Transactional
    void putNonExistingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                put(ENTITY_API_URL_ID, onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024WithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.saveAndFlush(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 using partial update
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity();
        partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId()
        );

        partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            .datPdp(UPDATED_DAT_PDP)
            .nomUsinapdp(UPDATED_NOM_USINAPDP)
            .valDespasup(UPDATED_VAL_DESPASUP);

        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityUpdatableFieldsEquals(
            createUpdateProxyForBean(
                partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity,
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            ),
            getPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            )
        );
    }

    @Test
    @Transactional
    void fullUpdateOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024WithPatch() throws Exception {
        // Initialize the database
        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.saveAndFlush(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 using partial update
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            new OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity();
        partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId()
        );

        partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            .datPdp(UPDATED_DAT_PDP)
            .codSubmercado(UPDATED_COD_SUBMERCADO)
            .sglTipousina(UPDATED_SGL_TIPOUSINA)
            .codUsinapdp(UPDATED_COD_USINAPDP)
            .nomUsinapdp(UPDATED_NOM_USINAPDP)
            .numIntdespa(UPDATED_NUM_INTDESPA)
            .valDespasup(UPDATED_VAL_DESPASUP);

        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isOk());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityUpdatableFieldsEquals(
            partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity,
            getPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity(
                partialUpdatedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            )
        );
    }

    @Test
    @Transactional
    void patchNonExistingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isBadRequest());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        // Initialize the database
        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.saveAndFlush(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.save(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.save(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );

        long databaseSizeBeforeDelete = getRepositoryCount();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(
                delete(ENTITY_API_URL_ID, onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.findAll()
        );
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024() throws Exception {
        // Initialize the database
        insertedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.saveAndFlush(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.save(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );

        // Search the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
        restOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024MockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].id").value(hasItem(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId().intValue()))
            )
            .andExpect(jsonPath("$.[*].datPdp").value(hasItem(DEFAULT_DAT_PDP)))
            .andExpect(jsonPath("$.[*].codSubmercado").value(hasItem(DEFAULT_COD_SUBMERCADO)))
            .andExpect(jsonPath("$.[*].sglTipousina").value(hasItem(DEFAULT_SGL_TIPOUSINA)))
            .andExpect(jsonPath("$.[*].codUsinapdp").value(hasItem(DEFAULT_COD_USINAPDP)))
            .andExpect(jsonPath("$.[*].nomUsinapdp").value(hasItem(DEFAULT_NOM_USINAPDP)))
            .andExpect(jsonPath("$.[*].numIntdespa").value(hasItem(DEFAULT_NUM_INTDESPA)))
            .andExpect(jsonPath("$.[*].valDespasup").value(hasItem(DEFAULT_VAL_DESPASUP)));
    }

    protected long getRepositoryCount() {
        return onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.count();
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

    protected OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity getPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity(
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
    ) {
        return onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository
            .findById(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.getId())
            .orElseThrow();
    }

    protected void assertPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityToMatchAllProperties(
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity expectedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    ) {
        assertOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityAllPropertiesEquals(
            expectedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity,
            getPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity(
                expectedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            )
        );
    }

    protected void assertPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityToMatchUpdatableProperties(
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity expectedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    ) {
        assertOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024EntityAllUpdatablePropertiesEquals(
            expectedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity,
            getPersistedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity(
                expectedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            )
        );
    }
}
