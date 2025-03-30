package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaMensalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaMensalSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntity}.
 */
@RestController
@RequestMapping("/api/ons-carga-energia-mensals")
@Transactional
public class OnsCargaEnergiaMensalResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCargaEnergiaMensalResource.class);

    private static final String ENTITY_NAME = "onsCargaEnergiaMensal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCargaEnergiaMensalRepository onsCargaEnergiaMensalRepository;

    private final OnsCargaEnergiaMensalSearchRepository onsCargaEnergiaMensalSearchRepository;

    public OnsCargaEnergiaMensalResource(
        OnsCargaEnergiaMensalRepository onsCargaEnergiaMensalRepository,
        OnsCargaEnergiaMensalSearchRepository onsCargaEnergiaMensalSearchRepository
    ) {
        this.onsCargaEnergiaMensalRepository = onsCargaEnergiaMensalRepository;
        this.onsCargaEnergiaMensalSearchRepository = onsCargaEnergiaMensalSearchRepository;
    }

    /**
     * {@code POST  /ons-carga-energia-mensals} : Create a new onsCargaEnergiaMensal.
     *
     * @param onsCargaEnergiaMensalEntity the onsCargaEnergiaMensalEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCargaEnergiaMensalEntity, or with status {@code 400 (Bad Request)} if the onsCargaEnergiaMensal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCargaEnergiaMensalEntity> createOnsCargaEnergiaMensal(
        @RequestBody OnsCargaEnergiaMensalEntity onsCargaEnergiaMensalEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCargaEnergiaMensal : {}", onsCargaEnergiaMensalEntity);
        if (onsCargaEnergiaMensalEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCargaEnergiaMensal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.save(onsCargaEnergiaMensalEntity);
        onsCargaEnergiaMensalSearchRepository.index(onsCargaEnergiaMensalEntity);
        return ResponseEntity.created(new URI("/api/ons-carga-energia-mensals/" + onsCargaEnergiaMensalEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaMensalEntity.getId().toString())
            )
            .body(onsCargaEnergiaMensalEntity);
    }

    /**
     * {@code PUT  /ons-carga-energia-mensals/:id} : Updates an existing onsCargaEnergiaMensal.
     *
     * @param id the id of the onsCargaEnergiaMensalEntity to save.
     * @param onsCargaEnergiaMensalEntity the onsCargaEnergiaMensalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaMensalEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaMensalEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaMensalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaMensalEntity> updateOnsCargaEnergiaMensal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaMensalEntity onsCargaEnergiaMensalEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCargaEnergiaMensal : {}, {}", id, onsCargaEnergiaMensalEntity);
        if (onsCargaEnergiaMensalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaMensalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaMensalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.save(onsCargaEnergiaMensalEntity);
        onsCargaEnergiaMensalSearchRepository.index(onsCargaEnergiaMensalEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaMensalEntity.getId().toString()))
            .body(onsCargaEnergiaMensalEntity);
    }

    /**
     * {@code PATCH  /ons-carga-energia-mensals/:id} : Partial updates given fields of an existing onsCargaEnergiaMensal, field will ignore if it is null
     *
     * @param id the id of the onsCargaEnergiaMensalEntity to save.
     * @param onsCargaEnergiaMensalEntity the onsCargaEnergiaMensalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaMensalEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaMensalEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCargaEnergiaMensalEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaMensalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCargaEnergiaMensalEntity> partialUpdateOnsCargaEnergiaMensal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaMensalEntity onsCargaEnergiaMensalEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCargaEnergiaMensal partially : {}, {}", id, onsCargaEnergiaMensalEntity);
        if (onsCargaEnergiaMensalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaMensalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaMensalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCargaEnergiaMensalEntity> result = onsCargaEnergiaMensalRepository
            .findById(onsCargaEnergiaMensalEntity.getId())
            .map(existingOnsCargaEnergiaMensal -> {
                if (onsCargaEnergiaMensalEntity.getIdSubsistema() != null) {
                    existingOnsCargaEnergiaMensal.setIdSubsistema(onsCargaEnergiaMensalEntity.getIdSubsistema());
                }
                if (onsCargaEnergiaMensalEntity.getNomSubsistema() != null) {
                    existingOnsCargaEnergiaMensal.setNomSubsistema(onsCargaEnergiaMensalEntity.getNomSubsistema());
                }
                if (onsCargaEnergiaMensalEntity.getDinInstante() != null) {
                    existingOnsCargaEnergiaMensal.setDinInstante(onsCargaEnergiaMensalEntity.getDinInstante());
                }
                if (onsCargaEnergiaMensalEntity.getValCargaenergiamwmed() != null) {
                    existingOnsCargaEnergiaMensal.setValCargaenergiamwmed(onsCargaEnergiaMensalEntity.getValCargaenergiamwmed());
                }

                return existingOnsCargaEnergiaMensal;
            })
            .map(onsCargaEnergiaMensalRepository::save)
            .map(savedOnsCargaEnergiaMensal -> {
                onsCargaEnergiaMensalSearchRepository.index(savedOnsCargaEnergiaMensal);
                return savedOnsCargaEnergiaMensal;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaMensalEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-carga-energia-mensals} : get all the onsCargaEnergiaMensals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCargaEnergiaMensals in body.
     */
    @GetMapping("")
    public List<OnsCargaEnergiaMensalEntity> getAllOnsCargaEnergiaMensals() {
        LOG.debug("REST request to get all OnsCargaEnergiaMensals");
        return onsCargaEnergiaMensalRepository.findAll();
    }

    /**
     * {@code GET  /ons-carga-energia-mensals/:id} : get the "id" onsCargaEnergiaMensal.
     *
     * @param id the id of the onsCargaEnergiaMensalEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCargaEnergiaMensalEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaMensalEntity> getOnsCargaEnergiaMensal(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCargaEnergiaMensal : {}", id);
        Optional<OnsCargaEnergiaMensalEntity> onsCargaEnergiaMensalEntity = onsCargaEnergiaMensalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCargaEnergiaMensalEntity);
    }

    /**
     * {@code DELETE  /ons-carga-energia-mensals/:id} : delete the "id" onsCargaEnergiaMensal.
     *
     * @param id the id of the onsCargaEnergiaMensalEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCargaEnergiaMensal(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCargaEnergiaMensal : {}", id);
        onsCargaEnergiaMensalRepository.deleteById(id);
        onsCargaEnergiaMensalSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-carga-energia-mensals/_search?query=:query} : search for the onsCargaEnergiaMensal corresponding
     * to the query.
     *
     * @param query the query of the onsCargaEnergiaMensal search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCargaEnergiaMensalEntity> searchOnsCargaEnergiaMensals(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCargaEnergiaMensals for query {}", query);
        try {
            return StreamSupport.stream(onsCargaEnergiaMensalSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
