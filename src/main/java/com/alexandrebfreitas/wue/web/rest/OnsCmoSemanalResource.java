package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntity;
import com.alexandrebfreitas.wue.repository.OnsCmoSemanalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCmoSemanalSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntity}.
 */
@RestController
@RequestMapping("/api/ons-cmo-semanals")
@Transactional
public class OnsCmoSemanalResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCmoSemanalResource.class);

    private static final String ENTITY_NAME = "onsCmoSemanal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCmoSemanalRepository onsCmoSemanalRepository;

    private final OnsCmoSemanalSearchRepository onsCmoSemanalSearchRepository;

    public OnsCmoSemanalResource(
        OnsCmoSemanalRepository onsCmoSemanalRepository,
        OnsCmoSemanalSearchRepository onsCmoSemanalSearchRepository
    ) {
        this.onsCmoSemanalRepository = onsCmoSemanalRepository;
        this.onsCmoSemanalSearchRepository = onsCmoSemanalSearchRepository;
    }

    /**
     * {@code POST  /ons-cmo-semanals} : Create a new onsCmoSemanal.
     *
     * @param onsCmoSemanalEntity the onsCmoSemanalEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCmoSemanalEntity, or with status {@code 400 (Bad Request)} if the onsCmoSemanal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCmoSemanalEntity> createOnsCmoSemanal(@RequestBody OnsCmoSemanalEntity onsCmoSemanalEntity)
        throws URISyntaxException {
        LOG.debug("REST request to save OnsCmoSemanal : {}", onsCmoSemanalEntity);
        if (onsCmoSemanalEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCmoSemanal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCmoSemanalEntity = onsCmoSemanalRepository.save(onsCmoSemanalEntity);
        onsCmoSemanalSearchRepository.index(onsCmoSemanalEntity);
        return ResponseEntity.created(new URI("/api/ons-cmo-semanals/" + onsCmoSemanalEntity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCmoSemanalEntity.getId().toString()))
            .body(onsCmoSemanalEntity);
    }

    /**
     * {@code PUT  /ons-cmo-semanals/:id} : Updates an existing onsCmoSemanal.
     *
     * @param id the id of the onsCmoSemanalEntity to save.
     * @param onsCmoSemanalEntity the onsCmoSemanalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCmoSemanalEntity,
     * or with status {@code 400 (Bad Request)} if the onsCmoSemanalEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCmoSemanalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCmoSemanalEntity> updateOnsCmoSemanal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCmoSemanalEntity onsCmoSemanalEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCmoSemanal : {}, {}", id, onsCmoSemanalEntity);
        if (onsCmoSemanalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCmoSemanalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCmoSemanalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCmoSemanalEntity = onsCmoSemanalRepository.save(onsCmoSemanalEntity);
        onsCmoSemanalSearchRepository.index(onsCmoSemanalEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCmoSemanalEntity.getId().toString()))
            .body(onsCmoSemanalEntity);
    }

    /**
     * {@code PATCH  /ons-cmo-semanals/:id} : Partial updates given fields of an existing onsCmoSemanal, field will ignore if it is null
     *
     * @param id the id of the onsCmoSemanalEntity to save.
     * @param onsCmoSemanalEntity the onsCmoSemanalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCmoSemanalEntity,
     * or with status {@code 400 (Bad Request)} if the onsCmoSemanalEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCmoSemanalEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCmoSemanalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCmoSemanalEntity> partialUpdateOnsCmoSemanal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCmoSemanalEntity onsCmoSemanalEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCmoSemanal partially : {}, {}", id, onsCmoSemanalEntity);
        if (onsCmoSemanalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCmoSemanalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCmoSemanalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCmoSemanalEntity> result = onsCmoSemanalRepository
            .findById(onsCmoSemanalEntity.getId())
            .map(existingOnsCmoSemanal -> {
                if (onsCmoSemanalEntity.getIdSubsistema() != null) {
                    existingOnsCmoSemanal.setIdSubsistema(onsCmoSemanalEntity.getIdSubsistema());
                }
                if (onsCmoSemanalEntity.getNomSubsistema() != null) {
                    existingOnsCmoSemanal.setNomSubsistema(onsCmoSemanalEntity.getNomSubsistema());
                }
                if (onsCmoSemanalEntity.getDinInstante() != null) {
                    existingOnsCmoSemanal.setDinInstante(onsCmoSemanalEntity.getDinInstante());
                }
                if (onsCmoSemanalEntity.getValCmomediasemanal() != null) {
                    existingOnsCmoSemanal.setValCmomediasemanal(onsCmoSemanalEntity.getValCmomediasemanal());
                }
                if (onsCmoSemanalEntity.getValCmoleve() != null) {
                    existingOnsCmoSemanal.setValCmoleve(onsCmoSemanalEntity.getValCmoleve());
                }
                if (onsCmoSemanalEntity.getValCmomedia() != null) {
                    existingOnsCmoSemanal.setValCmomedia(onsCmoSemanalEntity.getValCmomedia());
                }
                if (onsCmoSemanalEntity.getValCmopesada() != null) {
                    existingOnsCmoSemanal.setValCmopesada(onsCmoSemanalEntity.getValCmopesada());
                }

                return existingOnsCmoSemanal;
            })
            .map(onsCmoSemanalRepository::save)
            .map(savedOnsCmoSemanal -> {
                onsCmoSemanalSearchRepository.index(savedOnsCmoSemanal);
                return savedOnsCmoSemanal;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCmoSemanalEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-cmo-semanals} : get all the onsCmoSemanals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCmoSemanals in body.
     */
    @GetMapping("")
    public List<OnsCmoSemanalEntity> getAllOnsCmoSemanals() {
        LOG.debug("REST request to get all OnsCmoSemanals");
        return onsCmoSemanalRepository.findAll();
    }

    /**
     * {@code GET  /ons-cmo-semanals/:id} : get the "id" onsCmoSemanal.
     *
     * @param id the id of the onsCmoSemanalEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCmoSemanalEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCmoSemanalEntity> getOnsCmoSemanal(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCmoSemanal : {}", id);
        Optional<OnsCmoSemanalEntity> onsCmoSemanalEntity = onsCmoSemanalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCmoSemanalEntity);
    }

    /**
     * {@code DELETE  /ons-cmo-semanals/:id} : delete the "id" onsCmoSemanal.
     *
     * @param id the id of the onsCmoSemanalEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCmoSemanal(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCmoSemanal : {}", id);
        onsCmoSemanalRepository.deleteById(id);
        onsCmoSemanalSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-cmo-semanals/_search?query=:query} : search for the onsCmoSemanal corresponding
     * to the query.
     *
     * @param query the query of the onsCmoSemanal search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCmoSemanalEntity> searchOnsCmoSemanals(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCmoSemanals for query {}", query);
        try {
            return StreamSupport.stream(onsCmoSemanalSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
