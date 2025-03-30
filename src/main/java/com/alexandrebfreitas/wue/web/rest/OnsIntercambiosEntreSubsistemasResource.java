package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntity;
import com.alexandrebfreitas.wue.repository.OnsIntercambiosEntreSubsistemasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIntercambiosEntreSubsistemasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntity}.
 */
@RestController
@RequestMapping("/api/ons-intercambios-entre-subsistemas")
@Transactional
public class OnsIntercambiosEntreSubsistemasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsIntercambiosEntreSubsistemasResource.class);

    private static final String ENTITY_NAME = "onsIntercambiosEntreSubsistemas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIntercambiosEntreSubsistemasRepository onsIntercambiosEntreSubsistemasRepository;

    private final OnsIntercambiosEntreSubsistemasSearchRepository onsIntercambiosEntreSubsistemasSearchRepository;

    public OnsIntercambiosEntreSubsistemasResource(
        OnsIntercambiosEntreSubsistemasRepository onsIntercambiosEntreSubsistemasRepository,
        OnsIntercambiosEntreSubsistemasSearchRepository onsIntercambiosEntreSubsistemasSearchRepository
    ) {
        this.onsIntercambiosEntreSubsistemasRepository = onsIntercambiosEntreSubsistemasRepository;
        this.onsIntercambiosEntreSubsistemasSearchRepository = onsIntercambiosEntreSubsistemasSearchRepository;
    }

    /**
     * {@code POST  /ons-intercambios-entre-subsistemas} : Create a new onsIntercambiosEntreSubsistemas.
     *
     * @param onsIntercambiosEntreSubsistemasEntity the onsIntercambiosEntreSubsistemasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIntercambiosEntreSubsistemasEntity, or with status {@code 400 (Bad Request)} if the onsIntercambiosEntreSubsistemas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsIntercambiosEntreSubsistemasEntity> createOnsIntercambiosEntreSubsistemas(
        @RequestBody OnsIntercambiosEntreSubsistemasEntity onsIntercambiosEntreSubsistemasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsIntercambiosEntreSubsistemas : {}", onsIntercambiosEntreSubsistemasEntity);
        if (onsIntercambiosEntreSubsistemasEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsIntercambiosEntreSubsistemas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.save(onsIntercambiosEntreSubsistemasEntity);
        onsIntercambiosEntreSubsistemasSearchRepository.index(onsIntercambiosEntreSubsistemasEntity);
        return ResponseEntity.created(new URI("/api/ons-intercambios-entre-subsistemas/" + onsIntercambiosEntreSubsistemasEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIntercambiosEntreSubsistemasEntity.getId().toString()
                )
            )
            .body(onsIntercambiosEntreSubsistemasEntity);
    }

    /**
     * {@code PUT  /ons-intercambios-entre-subsistemas/:id} : Updates an existing onsIntercambiosEntreSubsistemas.
     *
     * @param id the id of the onsIntercambiosEntreSubsistemasEntity to save.
     * @param onsIntercambiosEntreSubsistemasEntity the onsIntercambiosEntreSubsistemasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIntercambiosEntreSubsistemasEntity,
     * or with status {@code 400 (Bad Request)} if the onsIntercambiosEntreSubsistemasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIntercambiosEntreSubsistemasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsIntercambiosEntreSubsistemasEntity> updateOnsIntercambiosEntreSubsistemas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIntercambiosEntreSubsistemasEntity onsIntercambiosEntreSubsistemasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsIntercambiosEntreSubsistemas : {}, {}", id, onsIntercambiosEntreSubsistemasEntity);
        if (onsIntercambiosEntreSubsistemasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIntercambiosEntreSubsistemasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIntercambiosEntreSubsistemasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIntercambiosEntreSubsistemasEntity = onsIntercambiosEntreSubsistemasRepository.save(onsIntercambiosEntreSubsistemasEntity);
        onsIntercambiosEntreSubsistemasSearchRepository.index(onsIntercambiosEntreSubsistemasEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIntercambiosEntreSubsistemasEntity.getId().toString()
                )
            )
            .body(onsIntercambiosEntreSubsistemasEntity);
    }

    /**
     * {@code PATCH  /ons-intercambios-entre-subsistemas/:id} : Partial updates given fields of an existing onsIntercambiosEntreSubsistemas, field will ignore if it is null
     *
     * @param id the id of the onsIntercambiosEntreSubsistemasEntity to save.
     * @param onsIntercambiosEntreSubsistemasEntity the onsIntercambiosEntreSubsistemasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIntercambiosEntreSubsistemasEntity,
     * or with status {@code 400 (Bad Request)} if the onsIntercambiosEntreSubsistemasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIntercambiosEntreSubsistemasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIntercambiosEntreSubsistemasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsIntercambiosEntreSubsistemasEntity> partialUpdateOnsIntercambiosEntreSubsistemas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIntercambiosEntreSubsistemasEntity onsIntercambiosEntreSubsistemasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIntercambiosEntreSubsistemas partially : {}, {}",
            id,
            onsIntercambiosEntreSubsistemasEntity
        );
        if (onsIntercambiosEntreSubsistemasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIntercambiosEntreSubsistemasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIntercambiosEntreSubsistemasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIntercambiosEntreSubsistemasEntity> result = onsIntercambiosEntreSubsistemasRepository
            .findById(onsIntercambiosEntreSubsistemasEntity.getId())
            .map(existingOnsIntercambiosEntreSubsistemas -> {
                if (onsIntercambiosEntreSubsistemasEntity.getDinInstante() != null) {
                    existingOnsIntercambiosEntreSubsistemas.setDinInstante(onsIntercambiosEntreSubsistemasEntity.getDinInstante());
                }
                if (onsIntercambiosEntreSubsistemasEntity.getIdSubsistemaOrigem() != null) {
                    existingOnsIntercambiosEntreSubsistemas.setIdSubsistemaOrigem(
                        onsIntercambiosEntreSubsistemasEntity.getIdSubsistemaOrigem()
                    );
                }
                if (onsIntercambiosEntreSubsistemasEntity.getNomSubsistemaOrigem() != null) {
                    existingOnsIntercambiosEntreSubsistemas.setNomSubsistemaOrigem(
                        onsIntercambiosEntreSubsistemasEntity.getNomSubsistemaOrigem()
                    );
                }
                if (onsIntercambiosEntreSubsistemasEntity.getIdSubsistemaDestino() != null) {
                    existingOnsIntercambiosEntreSubsistemas.setIdSubsistemaDestino(
                        onsIntercambiosEntreSubsistemasEntity.getIdSubsistemaDestino()
                    );
                }
                if (onsIntercambiosEntreSubsistemasEntity.getNomSubsistemaDestino() != null) {
                    existingOnsIntercambiosEntreSubsistemas.setNomSubsistemaDestino(
                        onsIntercambiosEntreSubsistemasEntity.getNomSubsistemaDestino()
                    );
                }
                if (onsIntercambiosEntreSubsistemasEntity.getValIntercambiomwmed() != null) {
                    existingOnsIntercambiosEntreSubsistemas.setValIntercambiomwmed(
                        onsIntercambiosEntreSubsistemasEntity.getValIntercambiomwmed()
                    );
                }

                return existingOnsIntercambiosEntreSubsistemas;
            })
            .map(onsIntercambiosEntreSubsistemasRepository::save)
            .map(savedOnsIntercambiosEntreSubsistemas -> {
                onsIntercambiosEntreSubsistemasSearchRepository.index(savedOnsIntercambiosEntreSubsistemas);
                return savedOnsIntercambiosEntreSubsistemas;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsIntercambiosEntreSubsistemasEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-intercambios-entre-subsistemas} : get all the onsIntercambiosEntreSubsistemas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIntercambiosEntreSubsistemas in body.
     */
    @GetMapping("")
    public List<OnsIntercambiosEntreSubsistemasEntity> getAllOnsIntercambiosEntreSubsistemas() {
        LOG.debug("REST request to get all OnsIntercambiosEntreSubsistemas");
        return onsIntercambiosEntreSubsistemasRepository.findAll();
    }

    /**
     * {@code GET  /ons-intercambios-entre-subsistemas/:id} : get the "id" onsIntercambiosEntreSubsistemas.
     *
     * @param id the id of the onsIntercambiosEntreSubsistemasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIntercambiosEntreSubsistemasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsIntercambiosEntreSubsistemasEntity> getOnsIntercambiosEntreSubsistemas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsIntercambiosEntreSubsistemas : {}", id);
        Optional<OnsIntercambiosEntreSubsistemasEntity> onsIntercambiosEntreSubsistemasEntity =
            onsIntercambiosEntreSubsistemasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIntercambiosEntreSubsistemasEntity);
    }

    /**
     * {@code DELETE  /ons-intercambios-entre-subsistemas/:id} : delete the "id" onsIntercambiosEntreSubsistemas.
     *
     * @param id the id of the onsIntercambiosEntreSubsistemasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIntercambiosEntreSubsistemas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsIntercambiosEntreSubsistemas : {}", id);
        onsIntercambiosEntreSubsistemasRepository.deleteById(id);
        onsIntercambiosEntreSubsistemasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-intercambios-entre-subsistemas/_search?query=:query} : search for the onsIntercambiosEntreSubsistemas corresponding
     * to the query.
     *
     * @param query the query of the onsIntercambiosEntreSubsistemas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsIntercambiosEntreSubsistemasEntity> searchOnsIntercambiosEntreSubsistemas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsIntercambiosEntreSubsistemas for query {}", query);
        try {
            return StreamSupport.stream(onsIntercambiosEntreSubsistemasSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
