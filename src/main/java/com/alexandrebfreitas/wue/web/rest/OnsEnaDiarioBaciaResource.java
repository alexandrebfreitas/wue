package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioBaciaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioBaciaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntity}.
 */
@RestController
@RequestMapping("/api/ons-ena-diario-bacias")
@Transactional
public class OnsEnaDiarioBaciaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEnaDiarioBaciaResource.class);

    private static final String ENTITY_NAME = "onsEnaDiarioBacia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEnaDiarioBaciaRepository onsEnaDiarioBaciaRepository;

    private final OnsEnaDiarioBaciaSearchRepository onsEnaDiarioBaciaSearchRepository;

    public OnsEnaDiarioBaciaResource(
        OnsEnaDiarioBaciaRepository onsEnaDiarioBaciaRepository,
        OnsEnaDiarioBaciaSearchRepository onsEnaDiarioBaciaSearchRepository
    ) {
        this.onsEnaDiarioBaciaRepository = onsEnaDiarioBaciaRepository;
        this.onsEnaDiarioBaciaSearchRepository = onsEnaDiarioBaciaSearchRepository;
    }

    /**
     * {@code POST  /ons-ena-diario-bacias} : Create a new onsEnaDiarioBacia.
     *
     * @param onsEnaDiarioBaciaEntity the onsEnaDiarioBaciaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEnaDiarioBaciaEntity, or with status {@code 400 (Bad Request)} if the onsEnaDiarioBacia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEnaDiarioBaciaEntity> createOnsEnaDiarioBacia(@RequestBody OnsEnaDiarioBaciaEntity onsEnaDiarioBaciaEntity)
        throws URISyntaxException {
        LOG.debug("REST request to save OnsEnaDiarioBacia : {}", onsEnaDiarioBaciaEntity);
        if (onsEnaDiarioBaciaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsEnaDiarioBacia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.save(onsEnaDiarioBaciaEntity);
        onsEnaDiarioBaciaSearchRepository.index(onsEnaDiarioBaciaEntity);
        return ResponseEntity.created(new URI("/api/ons-ena-diario-bacias/" + onsEnaDiarioBaciaEntity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioBaciaEntity.getId().toString()))
            .body(onsEnaDiarioBaciaEntity);
    }

    /**
     * {@code PUT  /ons-ena-diario-bacias/:id} : Updates an existing onsEnaDiarioBacia.
     *
     * @param id the id of the onsEnaDiarioBaciaEntity to save.
     * @param onsEnaDiarioBaciaEntity the onsEnaDiarioBaciaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioBaciaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioBaciaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioBaciaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioBaciaEntity> updateOnsEnaDiarioBacia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioBaciaEntity onsEnaDiarioBaciaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsEnaDiarioBacia : {}, {}", id, onsEnaDiarioBaciaEntity);
        if (onsEnaDiarioBaciaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioBaciaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioBaciaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.save(onsEnaDiarioBaciaEntity);
        onsEnaDiarioBaciaSearchRepository.index(onsEnaDiarioBaciaEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioBaciaEntity.getId().toString()))
            .body(onsEnaDiarioBaciaEntity);
    }

    /**
     * {@code PATCH  /ons-ena-diario-bacias/:id} : Partial updates given fields of an existing onsEnaDiarioBacia, field will ignore if it is null
     *
     * @param id the id of the onsEnaDiarioBaciaEntity to save.
     * @param onsEnaDiarioBaciaEntity the onsEnaDiarioBaciaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioBaciaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioBaciaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEnaDiarioBaciaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioBaciaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEnaDiarioBaciaEntity> partialUpdateOnsEnaDiarioBacia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioBaciaEntity onsEnaDiarioBaciaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsEnaDiarioBacia partially : {}, {}", id, onsEnaDiarioBaciaEntity);
        if (onsEnaDiarioBaciaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioBaciaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioBaciaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEnaDiarioBaciaEntity> result = onsEnaDiarioBaciaRepository
            .findById(onsEnaDiarioBaciaEntity.getId())
            .map(existingOnsEnaDiarioBacia -> {
                if (onsEnaDiarioBaciaEntity.getEnaArmazenavelBaciaPercentualmlt() != null) {
                    existingOnsEnaDiarioBacia.setEnaArmazenavelBaciaPercentualmlt(
                        onsEnaDiarioBaciaEntity.getEnaArmazenavelBaciaPercentualmlt()
                    );
                }

                return existingOnsEnaDiarioBacia;
            })
            .map(onsEnaDiarioBaciaRepository::save)
            .map(savedOnsEnaDiarioBacia -> {
                onsEnaDiarioBaciaSearchRepository.index(savedOnsEnaDiarioBacia);
                return savedOnsEnaDiarioBacia;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioBaciaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-ena-diario-bacias} : get all the onsEnaDiarioBacias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEnaDiarioBacias in body.
     */
    @GetMapping("")
    public List<OnsEnaDiarioBaciaEntity> getAllOnsEnaDiarioBacias() {
        LOG.debug("REST request to get all OnsEnaDiarioBacias");
        return onsEnaDiarioBaciaRepository.findAll();
    }

    /**
     * {@code GET  /ons-ena-diario-bacias/:id} : get the "id" onsEnaDiarioBacia.
     *
     * @param id the id of the onsEnaDiarioBaciaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEnaDiarioBaciaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioBaciaEntity> getOnsEnaDiarioBacia(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsEnaDiarioBacia : {}", id);
        Optional<OnsEnaDiarioBaciaEntity> onsEnaDiarioBaciaEntity = onsEnaDiarioBaciaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEnaDiarioBaciaEntity);
    }

    /**
     * {@code DELETE  /ons-ena-diario-bacias/:id} : delete the "id" onsEnaDiarioBacia.
     *
     * @param id the id of the onsEnaDiarioBaciaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEnaDiarioBacia(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEnaDiarioBacia : {}", id);
        onsEnaDiarioBaciaRepository.deleteById(id);
        onsEnaDiarioBaciaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ena-diario-bacias/_search?query=:query} : search for the onsEnaDiarioBacia corresponding
     * to the query.
     *
     * @param query the query of the onsEnaDiarioBacia search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEnaDiarioBaciaEntity> searchOnsEnaDiarioBacias(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsEnaDiarioBacias for query {}", query);
        try {
            return StreamSupport.stream(onsEnaDiarioBaciaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
