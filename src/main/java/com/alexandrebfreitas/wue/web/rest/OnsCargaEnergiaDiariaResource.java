package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaDiariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaDiariaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntity}.
 */
@RestController
@RequestMapping("/api/ons-carga-energia-diarias")
@Transactional
public class OnsCargaEnergiaDiariaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCargaEnergiaDiariaResource.class);

    private static final String ENTITY_NAME = "onsCargaEnergiaDiaria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCargaEnergiaDiariaRepository onsCargaEnergiaDiariaRepository;

    private final OnsCargaEnergiaDiariaSearchRepository onsCargaEnergiaDiariaSearchRepository;

    public OnsCargaEnergiaDiariaResource(
        OnsCargaEnergiaDiariaRepository onsCargaEnergiaDiariaRepository,
        OnsCargaEnergiaDiariaSearchRepository onsCargaEnergiaDiariaSearchRepository
    ) {
        this.onsCargaEnergiaDiariaRepository = onsCargaEnergiaDiariaRepository;
        this.onsCargaEnergiaDiariaSearchRepository = onsCargaEnergiaDiariaSearchRepository;
    }

    /**
     * {@code POST  /ons-carga-energia-diarias} : Create a new onsCargaEnergiaDiaria.
     *
     * @param onsCargaEnergiaDiariaEntity the onsCargaEnergiaDiariaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCargaEnergiaDiariaEntity, or with status {@code 400 (Bad Request)} if the onsCargaEnergiaDiaria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCargaEnergiaDiariaEntity> createOnsCargaEnergiaDiaria(
        @RequestBody OnsCargaEnergiaDiariaEntity onsCargaEnergiaDiariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCargaEnergiaDiaria : {}", onsCargaEnergiaDiariaEntity);
        if (onsCargaEnergiaDiariaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCargaEnergiaDiaria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.save(onsCargaEnergiaDiariaEntity);
        onsCargaEnergiaDiariaSearchRepository.index(onsCargaEnergiaDiariaEntity);
        return ResponseEntity.created(new URI("/api/ons-carga-energia-diarias/" + onsCargaEnergiaDiariaEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaDiariaEntity.getId().toString())
            )
            .body(onsCargaEnergiaDiariaEntity);
    }

    /**
     * {@code PUT  /ons-carga-energia-diarias/:id} : Updates an existing onsCargaEnergiaDiaria.
     *
     * @param id the id of the onsCargaEnergiaDiariaEntity to save.
     * @param onsCargaEnergiaDiariaEntity the onsCargaEnergiaDiariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaDiariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaDiariaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaDiariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaDiariaEntity> updateOnsCargaEnergiaDiaria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaDiariaEntity onsCargaEnergiaDiariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCargaEnergiaDiaria : {}, {}", id, onsCargaEnergiaDiariaEntity);
        if (onsCargaEnergiaDiariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaDiariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaDiariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.save(onsCargaEnergiaDiariaEntity);
        onsCargaEnergiaDiariaSearchRepository.index(onsCargaEnergiaDiariaEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaDiariaEntity.getId().toString()))
            .body(onsCargaEnergiaDiariaEntity);
    }

    /**
     * {@code PATCH  /ons-carga-energia-diarias/:id} : Partial updates given fields of an existing onsCargaEnergiaDiaria, field will ignore if it is null
     *
     * @param id the id of the onsCargaEnergiaDiariaEntity to save.
     * @param onsCargaEnergiaDiariaEntity the onsCargaEnergiaDiariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaDiariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaDiariaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCargaEnergiaDiariaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaDiariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCargaEnergiaDiariaEntity> partialUpdateOnsCargaEnergiaDiaria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaDiariaEntity onsCargaEnergiaDiariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCargaEnergiaDiaria partially : {}, {}", id, onsCargaEnergiaDiariaEntity);
        if (onsCargaEnergiaDiariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaDiariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaDiariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCargaEnergiaDiariaEntity> result = onsCargaEnergiaDiariaRepository
            .findById(onsCargaEnergiaDiariaEntity.getId())
            .map(existingOnsCargaEnergiaDiaria -> {
                if (onsCargaEnergiaDiariaEntity.getIdSubsistema() != null) {
                    existingOnsCargaEnergiaDiaria.setIdSubsistema(onsCargaEnergiaDiariaEntity.getIdSubsistema());
                }
                if (onsCargaEnergiaDiariaEntity.getNomSubsistema() != null) {
                    existingOnsCargaEnergiaDiaria.setNomSubsistema(onsCargaEnergiaDiariaEntity.getNomSubsistema());
                }
                if (onsCargaEnergiaDiariaEntity.getDinInstante() != null) {
                    existingOnsCargaEnergiaDiaria.setDinInstante(onsCargaEnergiaDiariaEntity.getDinInstante());
                }
                if (onsCargaEnergiaDiariaEntity.getValCargaenergiamwmed() != null) {
                    existingOnsCargaEnergiaDiaria.setValCargaenergiamwmed(onsCargaEnergiaDiariaEntity.getValCargaenergiamwmed());
                }

                return existingOnsCargaEnergiaDiaria;
            })
            .map(onsCargaEnergiaDiariaRepository::save)
            .map(savedOnsCargaEnergiaDiaria -> {
                onsCargaEnergiaDiariaSearchRepository.index(savedOnsCargaEnergiaDiaria);
                return savedOnsCargaEnergiaDiaria;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaDiariaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-carga-energia-diarias} : get all the onsCargaEnergiaDiarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCargaEnergiaDiarias in body.
     */
    @GetMapping("")
    public List<OnsCargaEnergiaDiariaEntity> getAllOnsCargaEnergiaDiarias() {
        LOG.debug("REST request to get all OnsCargaEnergiaDiarias");
        return onsCargaEnergiaDiariaRepository.findAll();
    }

    /**
     * {@code GET  /ons-carga-energia-diarias/:id} : get the "id" onsCargaEnergiaDiaria.
     *
     * @param id the id of the onsCargaEnergiaDiariaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCargaEnergiaDiariaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaDiariaEntity> getOnsCargaEnergiaDiaria(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCargaEnergiaDiaria : {}", id);
        Optional<OnsCargaEnergiaDiariaEntity> onsCargaEnergiaDiariaEntity = onsCargaEnergiaDiariaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCargaEnergiaDiariaEntity);
    }

    /**
     * {@code DELETE  /ons-carga-energia-diarias/:id} : delete the "id" onsCargaEnergiaDiaria.
     *
     * @param id the id of the onsCargaEnergiaDiariaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCargaEnergiaDiaria(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCargaEnergiaDiaria : {}", id);
        onsCargaEnergiaDiariaRepository.deleteById(id);
        onsCargaEnergiaDiariaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-carga-energia-diarias/_search?query=:query} : search for the onsCargaEnergiaDiaria corresponding
     * to the query.
     *
     * @param query the query of the onsCargaEnergiaDiaria search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCargaEnergiaDiariaEntity> searchOnsCargaEnergiaDiarias(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCargaEnergiaDiarias for query {}", query);
        try {
            return StreamSupport.stream(onsCargaEnergiaDiariaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
