package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEnaDiarioSubsistemaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioSubsistemaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioSubsistemaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEnaDiarioSubsistemaEntity}.
 */
@RestController
@RequestMapping("/api/ons-ena-diario-subsistemas")
@Transactional
public class OnsEnaDiarioSubsistemaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEnaDiarioSubsistemaResource.class);

    private static final String ENTITY_NAME = "onsEnaDiarioSubsistema";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEnaDiarioSubsistemaRepository onsEnaDiarioSubsistemaRepository;

    private final OnsEnaDiarioSubsistemaSearchRepository onsEnaDiarioSubsistemaSearchRepository;

    public OnsEnaDiarioSubsistemaResource(
        OnsEnaDiarioSubsistemaRepository onsEnaDiarioSubsistemaRepository,
        OnsEnaDiarioSubsistemaSearchRepository onsEnaDiarioSubsistemaSearchRepository
    ) {
        this.onsEnaDiarioSubsistemaRepository = onsEnaDiarioSubsistemaRepository;
        this.onsEnaDiarioSubsistemaSearchRepository = onsEnaDiarioSubsistemaSearchRepository;
    }

    /**
     * {@code POST  /ons-ena-diario-subsistemas} : Create a new onsEnaDiarioSubsistema.
     *
     * @param onsEnaDiarioSubsistemaEntity the onsEnaDiarioSubsistemaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEnaDiarioSubsistemaEntity, or with status {@code 400 (Bad Request)} if the onsEnaDiarioSubsistema has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEnaDiarioSubsistemaEntity> createOnsEnaDiarioSubsistema(
        @RequestBody OnsEnaDiarioSubsistemaEntity onsEnaDiarioSubsistemaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsEnaDiarioSubsistema : {}", onsEnaDiarioSubsistemaEntity);
        if (onsEnaDiarioSubsistemaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsEnaDiarioSubsistema cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.save(onsEnaDiarioSubsistemaEntity);
        onsEnaDiarioSubsistemaSearchRepository.index(onsEnaDiarioSubsistemaEntity);
        return ResponseEntity.created(new URI("/api/ons-ena-diario-subsistemas/" + onsEnaDiarioSubsistemaEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioSubsistemaEntity.getId().toString())
            )
            .body(onsEnaDiarioSubsistemaEntity);
    }

    /**
     * {@code PUT  /ons-ena-diario-subsistemas/:id} : Updates an existing onsEnaDiarioSubsistema.
     *
     * @param id the id of the onsEnaDiarioSubsistemaEntity to save.
     * @param onsEnaDiarioSubsistemaEntity the onsEnaDiarioSubsistemaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioSubsistemaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioSubsistemaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioSubsistemaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioSubsistemaEntity> updateOnsEnaDiarioSubsistema(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioSubsistemaEntity onsEnaDiarioSubsistemaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsEnaDiarioSubsistema : {}, {}", id, onsEnaDiarioSubsistemaEntity);
        if (onsEnaDiarioSubsistemaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioSubsistemaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioSubsistemaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.save(onsEnaDiarioSubsistemaEntity);
        onsEnaDiarioSubsistemaSearchRepository.index(onsEnaDiarioSubsistemaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioSubsistemaEntity.getId().toString())
            )
            .body(onsEnaDiarioSubsistemaEntity);
    }

    /**
     * {@code PATCH  /ons-ena-diario-subsistemas/:id} : Partial updates given fields of an existing onsEnaDiarioSubsistema, field will ignore if it is null
     *
     * @param id the id of the onsEnaDiarioSubsistemaEntity to save.
     * @param onsEnaDiarioSubsistemaEntity the onsEnaDiarioSubsistemaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioSubsistemaEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioSubsistemaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEnaDiarioSubsistemaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioSubsistemaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEnaDiarioSubsistemaEntity> partialUpdateOnsEnaDiarioSubsistema(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioSubsistemaEntity onsEnaDiarioSubsistemaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsEnaDiarioSubsistema partially : {}, {}", id, onsEnaDiarioSubsistemaEntity);
        if (onsEnaDiarioSubsistemaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioSubsistemaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioSubsistemaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEnaDiarioSubsistemaEntity> result = onsEnaDiarioSubsistemaRepository
            .findById(onsEnaDiarioSubsistemaEntity.getId())
            .map(existingOnsEnaDiarioSubsistema -> {
                if (onsEnaDiarioSubsistemaEntity.getEnaArmazenavelRegiaoPercentualmlt() != null) {
                    existingOnsEnaDiarioSubsistema.setEnaArmazenavelRegiaoPercentualmlt(
                        onsEnaDiarioSubsistemaEntity.getEnaArmazenavelRegiaoPercentualmlt()
                    );
                }

                return existingOnsEnaDiarioSubsistema;
            })
            .map(onsEnaDiarioSubsistemaRepository::save)
            .map(savedOnsEnaDiarioSubsistema -> {
                onsEnaDiarioSubsistemaSearchRepository.index(savedOnsEnaDiarioSubsistema);
                return savedOnsEnaDiarioSubsistema;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioSubsistemaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-ena-diario-subsistemas} : get all the onsEnaDiarioSubsistemas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEnaDiarioSubsistemas in body.
     */
    @GetMapping("")
    public List<OnsEnaDiarioSubsistemaEntity> getAllOnsEnaDiarioSubsistemas() {
        LOG.debug("REST request to get all OnsEnaDiarioSubsistemas");
        return onsEnaDiarioSubsistemaRepository.findAll();
    }

    /**
     * {@code GET  /ons-ena-diario-subsistemas/:id} : get the "id" onsEnaDiarioSubsistema.
     *
     * @param id the id of the onsEnaDiarioSubsistemaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEnaDiarioSubsistemaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioSubsistemaEntity> getOnsEnaDiarioSubsistema(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsEnaDiarioSubsistema : {}", id);
        Optional<OnsEnaDiarioSubsistemaEntity> onsEnaDiarioSubsistemaEntity = onsEnaDiarioSubsistemaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEnaDiarioSubsistemaEntity);
    }

    /**
     * {@code DELETE  /ons-ena-diario-subsistemas/:id} : delete the "id" onsEnaDiarioSubsistema.
     *
     * @param id the id of the onsEnaDiarioSubsistemaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEnaDiarioSubsistema(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEnaDiarioSubsistema : {}", id);
        onsEnaDiarioSubsistemaRepository.deleteById(id);
        onsEnaDiarioSubsistemaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ena-diario-subsistemas/_search?query=:query} : search for the onsEnaDiarioSubsistema corresponding
     * to the query.
     *
     * @param query the query of the onsEnaDiarioSubsistema search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEnaDiarioSubsistemaEntity> searchOnsEnaDiarioSubsistemas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsEnaDiarioSubsistemas for query {}", query);
        try {
            return StreamSupport.stream(onsEnaDiarioSubsistemaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
