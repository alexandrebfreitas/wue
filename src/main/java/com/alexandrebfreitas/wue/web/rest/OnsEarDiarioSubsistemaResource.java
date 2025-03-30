package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioSubsistemaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEarDiarioSubsistemaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntity}.
 */
@RestController
@RequestMapping("/api/ons-ear-diario-subsistemas")
@Transactional
public class OnsEarDiarioSubsistemaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEarDiarioSubsistemaResource.class);

    private static final String ENTITY_NAME = "onsEarDiarioSubsistema";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEarDiarioSubsistemaRepository onsEarDiarioSubsistemaRepository;

    private final OnsEarDiarioSubsistemaSearchRepository onsEarDiarioSubsistemaSearchRepository;

    public OnsEarDiarioSubsistemaResource(
        OnsEarDiarioSubsistemaRepository onsEarDiarioSubsistemaRepository,
        OnsEarDiarioSubsistemaSearchRepository onsEarDiarioSubsistemaSearchRepository
    ) {
        this.onsEarDiarioSubsistemaRepository = onsEarDiarioSubsistemaRepository;
        this.onsEarDiarioSubsistemaSearchRepository = onsEarDiarioSubsistemaSearchRepository;
    }

    /**
     * {@code POST  /ons-ear-diario-subsistemas} : Create a new onsEarDiarioSubsistema.
     *
     * @param onsEarDiarioSubsistemaEntity the onsEarDiarioSubsistemaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEarDiarioSubsistemaEntity, or with status {@code 400 (Bad Request)} if the onsEarDiarioSubsistema has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEarDiarioSubsistemaEntity> createOnsEarDiarioSubsistema(
        @RequestBody OnsEarDiarioSubsistemaEntity onsEarDiarioSubsistemaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsEarDiarioSubsistema : {}", onsEarDiarioSubsistemaEntity);
        if (onsEarDiarioSubsistemaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsEarDiarioSubsistema cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.save(onsEarDiarioSubsistemaEntity);
        onsEarDiarioSubsistemaSearchRepository.index(onsEarDiarioSubsistemaEntity);
        return ResponseEntity.created(new URI("/api/ons-ear-diario-subsistemas/" + onsEarDiarioSubsistemaEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsEarDiarioSubsistemaEntity.getId().toString())
            )
            .body(onsEarDiarioSubsistemaEntity);
    }

    /**
     * {@code PUT  /ons-ear-diario-subsistemas/:id} : Updates an existing onsEarDiarioSubsistema.
     *
     * @param id the id of the onsEarDiarioSubsistemaEntity to save.
     * @param onsEarDiarioSubsistemaEntity the onsEarDiarioSubsistemaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioSubsistemaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioSubsistemaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioSubsistemaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEarDiarioSubsistemaEntity> updateOnsEarDiarioSubsistema(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioSubsistemaEntity onsEarDiarioSubsistemaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsEarDiarioSubsistema : {}, {}", id, onsEarDiarioSubsistemaEntity);
        if (onsEarDiarioSubsistemaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioSubsistemaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioSubsistemaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.save(onsEarDiarioSubsistemaEntity);
        onsEarDiarioSubsistemaSearchRepository.index(onsEarDiarioSubsistemaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEarDiarioSubsistemaEntity.getId().toString())
            )
            .body(onsEarDiarioSubsistemaEntity);
    }

    /**
     * {@code PATCH  /ons-ear-diario-subsistemas/:id} : Partial updates given fields of an existing onsEarDiarioSubsistema, field will ignore if it is null
     *
     * @param id the id of the onsEarDiarioSubsistemaEntity to save.
     * @param onsEarDiarioSubsistemaEntity the onsEarDiarioSubsistemaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEarDiarioSubsistemaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEarDiarioSubsistemaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEarDiarioSubsistemaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEarDiarioSubsistemaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEarDiarioSubsistemaEntity> partialUpdateOnsEarDiarioSubsistema(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEarDiarioSubsistemaEntity onsEarDiarioSubsistemaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsEarDiarioSubsistema partially : {}, {}", id, onsEarDiarioSubsistemaEntity);
        if (onsEarDiarioSubsistemaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEarDiarioSubsistemaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEarDiarioSubsistemaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEarDiarioSubsistemaEntity> result = onsEarDiarioSubsistemaRepository
            .findById(onsEarDiarioSubsistemaEntity.getId())
            .map(existingOnsEarDiarioSubsistema -> {
                if (onsEarDiarioSubsistemaEntity.getIdSubsistema() != null) {
                    existingOnsEarDiarioSubsistema.setIdSubsistema(onsEarDiarioSubsistemaEntity.getIdSubsistema());
                }
                if (onsEarDiarioSubsistemaEntity.getNomSubsistema() != null) {
                    existingOnsEarDiarioSubsistema.setNomSubsistema(onsEarDiarioSubsistemaEntity.getNomSubsistema());
                }
                if (onsEarDiarioSubsistemaEntity.getEarData() != null) {
                    existingOnsEarDiarioSubsistema.setEarData(onsEarDiarioSubsistemaEntity.getEarData());
                }
                if (onsEarDiarioSubsistemaEntity.getEarMaxSubsistema() != null) {
                    existingOnsEarDiarioSubsistema.setEarMaxSubsistema(onsEarDiarioSubsistemaEntity.getEarMaxSubsistema());
                }
                if (onsEarDiarioSubsistemaEntity.getEarVerifSubsistemaMwmes() != null) {
                    existingOnsEarDiarioSubsistema.setEarVerifSubsistemaMwmes(onsEarDiarioSubsistemaEntity.getEarVerifSubsistemaMwmes());
                }
                if (onsEarDiarioSubsistemaEntity.getEarVerifSubsistemaPercentual() != null) {
                    existingOnsEarDiarioSubsistema.setEarVerifSubsistemaPercentual(
                        onsEarDiarioSubsistemaEntity.getEarVerifSubsistemaPercentual()
                    );
                }

                return existingOnsEarDiarioSubsistema;
            })
            .map(onsEarDiarioSubsistemaRepository::save)
            .map(savedOnsEarDiarioSubsistema -> {
                onsEarDiarioSubsistemaSearchRepository.index(savedOnsEarDiarioSubsistema);
                return savedOnsEarDiarioSubsistema;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEarDiarioSubsistemaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-ear-diario-subsistemas} : get all the onsEarDiarioSubsistemas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEarDiarioSubsistemas in body.
     */
    @GetMapping("")
    public List<OnsEarDiarioSubsistemaEntity> getAllOnsEarDiarioSubsistemas() {
        LOG.debug("REST request to get all OnsEarDiarioSubsistemas");
        return onsEarDiarioSubsistemaRepository.findAll();
    }

    /**
     * {@code GET  /ons-ear-diario-subsistemas/:id} : get the "id" onsEarDiarioSubsistema.
     *
     * @param id the id of the onsEarDiarioSubsistemaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEarDiarioSubsistemaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEarDiarioSubsistemaEntity> getOnsEarDiarioSubsistema(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsEarDiarioSubsistema : {}", id);
        Optional<OnsEarDiarioSubsistemaEntity> onsEarDiarioSubsistemaEntity = onsEarDiarioSubsistemaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEarDiarioSubsistemaEntity);
    }

    /**
     * {@code DELETE  /ons-ear-diario-subsistemas/:id} : delete the "id" onsEarDiarioSubsistema.
     *
     * @param id the id of the onsEarDiarioSubsistemaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEarDiarioSubsistema(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEarDiarioSubsistema : {}", id);
        onsEarDiarioSubsistemaRepository.deleteById(id);
        onsEarDiarioSubsistemaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ear-diario-subsistemas/_search?query=:query} : search for the onsEarDiarioSubsistema corresponding
     * to the query.
     *
     * @param query the query of the onsEarDiarioSubsistema search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEarDiarioSubsistemaEntity> searchOnsEarDiarioSubsistemas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsEarDiarioSubsistemas for query {}", query);
        try {
            return StreamSupport.stream(onsEarDiarioSubsistemaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
