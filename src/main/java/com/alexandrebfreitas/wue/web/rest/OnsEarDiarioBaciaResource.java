package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEarDiarioBaciaEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioBaciaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioBaciaSearchRepository;
import com.alexandrebfreitas.wue.web.rest.errors.BadRequestAlertException;
import com.alexandrebfreitas.wue.web.rest.errors.ElasticsearchExceptionMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEarDiarioBaciaEntity}.
 */
@RestController
@RequestMapping("/api/ons-ear-diario-bacias")
@Transactional
public class OnsEarDiarioBaciaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEarDiarioBaciaResource.class);

    private static final String ENTITY_NAME = "onsEarDiarioBacia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEarDiarioBaciaRepository onsEarDiarioBaciaRepository;

    private final OnsEarDiarioBaciaSearchRepository onsEarDiarioBaciaSearchRepository;

    public OnsEarDiarioBaciaResource(
        OnsEarDiarioBaciaRepository onsEarDiarioBaciaRepository,
        OnsEarDiarioBaciaSearchRepository onsEarDiarioBaciaSearchRepository
    ) {
        this.onsEarDiarioBaciaRepository = onsEarDiarioBaciaRepository;
        this.onsEarDiarioBaciaSearchRepository = onsEarDiarioBaciaSearchRepository;
    }

    /**
     * {@code POST  /ons-ear-diario-bacias} : Create a new onsEarDiarioBacia.
     *
     * @param onsEarDiarioBaciaEntity the onsEarDiarioBaciaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEarDiarioBaciaEntity, or with status {@code 400 (Bad Request)} if the onsEarDiarioBacia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEarDiarioBaciaEntity> createOnsEarDiarioBacia(@RequestBody OnsEarDiarioBaciaEntity onsEarDiarioBaciaEntity)
        throws URISyntaxException {
        LOG.debug("REST request to save OnsEarDiarioBacia : {}", onsEarDiarioBaciaEntity);
        if (onsEarDiarioBaciaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsEarDiarioBacia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.save(onsEarDiarioBaciaEntity);
        onsEarDiarioBaciaSearchRepository.index(onsEarDiarioBaciaEntity);
        return ResponseEntity.created(new URI("/api/ons-ear-diario-bacias/" + onsEarDiarioBaciaEntity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsEarDiarioBaciaEntity.getId().toString()))
            .body(onsEarDiarioBaciaEntity);
    }

    /**
     * {@code PUT  /ons-ear-diario-bacias/:id} : Updates an existing onsEarDiarioBacia.
     *
     * @param id the id of the onsEarDiarioBaciaEntity to save.
     * @param onsEarDiarioBaciaEntity the onsEarDiarioBaciaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioBaciaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioBaciaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioBaciaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEarDiarioBaciaEntity> updateOnsEarDiarioBacia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioBaciaEntity onsEarDiarioBaciaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsEarDiarioBacia : {}, {}", id, onsEarDiarioBaciaEntity);
        if (onsEarDiarioBaciaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioBaciaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioBaciaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.save(onsEarDiarioBaciaEntity);
        onsEarDiarioBaciaSearchRepository.index(onsEarDiarioBaciaEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEarDiarioBaciaEntity.getId().toString()))
            .body(onsEarDiarioBaciaEntity);
    }

    /**
     * {@code PATCH  /ons-ear-diario-bacias/:id} : Partial updates given fields of an existing onsEarDiarioBacia, field will ignore if it is null
     *
     * @param id the id of the onsEarDiarioBaciaEntity to save.
     * @param onsEarDiarioBaciaEntity the onsEarDiarioBaciaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioBaciaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioBaciaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEarDiarioBaciaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioBaciaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEarDiarioBaciaEntity> partialUpdateOnsEarDiarioBacia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioBaciaEntity onsEarDiarioBaciaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsEarDiarioBacia partially : {}, {}", id, onsEarDiarioBaciaEntity);
        if (onsEarDiarioBaciaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioBaciaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioBaciaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEarDiarioBaciaEntity> result = onsEarDiarioBaciaRepository
            .findById(onsEarDiarioBaciaEntity.getId())
            .map(existingOnsEarDiarioBacia -> {
                if (onsEarDiarioBaciaEntity.getNomCurto() != null) {
                    existingOnsEarDiarioBacia.setNomCurto(onsEarDiarioBaciaEntity.getNomCurto());
                }
                if (onsEarDiarioBaciaEntity.getEarData() != null) {
                    existingOnsEarDiarioBacia.setEarData(onsEarDiarioBaciaEntity.getEarData());
                }
                if (onsEarDiarioBaciaEntity.getEarMaxBacia() != null) {
                    existingOnsEarDiarioBacia.setEarMaxBacia(onsEarDiarioBaciaEntity.getEarMaxBacia());
                }
                if (onsEarDiarioBaciaEntity.getEarVerifBaciaMwmes() != null) {
                    existingOnsEarDiarioBacia.setEarVerifBaciaMwmes(onsEarDiarioBaciaEntity.getEarVerifBaciaMwmes());
                }
                if (onsEarDiarioBaciaEntity.getEarVerifBaciaPercentual() != null) {
                    existingOnsEarDiarioBacia.setEarVerifBaciaPercentual(onsEarDiarioBaciaEntity.getEarVerifBaciaPercentual());
                }

                return existingOnsEarDiarioBacia;
            })
            .map(onsEarDiarioBaciaRepository::save)
            .map(savedOnsEarDiarioBacia -> {
                onsEarDiarioBaciaSearchRepository.index(savedOnsEarDiarioBacia);
                return savedOnsEarDiarioBacia;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEarDiarioBaciaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-ear-diario-bacias} : get all the onsEarDiarioBacias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEarDiarioBacias in body.
     */
    @GetMapping("")
    public List<OnsEarDiarioBaciaEntity> getAllOnsEarDiarioBacias() {
        LOG.debug("REST request to get all OnsEarDiarioBacias");
        return onsEarDiarioBaciaRepository.findAll();
    }

    /**
     * {@code GET  /ons-ear-diario-bacias/:id} : get the "id" onsEarDiarioBacia.
     *
     * @param id the id of the onsEarDiarioBaciaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEarDiarioBaciaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEarDiarioBaciaEntity> getOnsEarDiarioBacia(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsEarDiarioBacia : {}", id);
        Optional<OnsEarDiarioBaciaEntity> onsEarDiarioBaciaEntity = onsEarDiarioBaciaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEarDiarioBaciaEntity);
    }

    /**
     * {@code DELETE  /ons-ear-diario-bacias/:id} : delete the "id" onsEarDiarioBacia.
     *
     * @param id the id of the onsEarDiarioBaciaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEarDiarioBacia(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEarDiarioBacia : {}", id);
        onsEarDiarioBaciaRepository.deleteById(id);
        onsEarDiarioBaciaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ear-diario-bacias/_search?query=:query} : search for the onsEarDiarioBacia corresponding
     * to the query.
     *
     * @param query the query of the onsEarDiarioBacia search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEarDiarioBaciaEntity> searchOnsEarDiarioBacias(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsEarDiarioBacias for query {}", query);
        try {
            return StreamSupport.stream(onsEarDiarioBaciaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
