package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaNosSubsistemasEntity;
import com.alexandrebfreitas.wue.repository.OnsBalancoEnergiaNosSubsistemasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsBalancoEnergiaNosSubsistemasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaNosSubsistemasEntity}.
 */
@RestController
@RequestMapping("/api/ons-balanco-energia-nos-subsistemas")
@Transactional
public class OnsBalancoEnergiaNosSubsistemasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsBalancoEnergiaNosSubsistemasResource.class);

    private static final String ENTITY_NAME = "onsBalancoEnergiaNosSubsistemas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsBalancoEnergiaNosSubsistemasRepository onsBalancoEnergiaNosSubsistemasRepository;

    private final OnsBalancoEnergiaNosSubsistemasSearchRepository onsBalancoEnergiaNosSubsistemasSearchRepository;

    public OnsBalancoEnergiaNosSubsistemasResource(
        OnsBalancoEnergiaNosSubsistemasRepository onsBalancoEnergiaNosSubsistemasRepository,
        OnsBalancoEnergiaNosSubsistemasSearchRepository onsBalancoEnergiaNosSubsistemasSearchRepository
    ) {
        this.onsBalancoEnergiaNosSubsistemasRepository = onsBalancoEnergiaNosSubsistemasRepository;
        this.onsBalancoEnergiaNosSubsistemasSearchRepository = onsBalancoEnergiaNosSubsistemasSearchRepository;
    }

    /**
     * {@code POST  /ons-balanco-energia-nos-subsistemas} : Create a new onsBalancoEnergiaNosSubsistemas.
     *
     * @param onsBalancoEnergiaNosSubsistemasEntity the onsBalancoEnergiaNosSubsistemasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsBalancoEnergiaNosSubsistemasEntity, or with status {@code 400 (Bad Request)} if the onsBalancoEnergiaNosSubsistemas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsBalancoEnergiaNosSubsistemasEntity> createOnsBalancoEnergiaNosSubsistemas(
        @RequestBody OnsBalancoEnergiaNosSubsistemasEntity onsBalancoEnergiaNosSubsistemasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsBalancoEnergiaNosSubsistemas : {}", onsBalancoEnergiaNosSubsistemasEntity);
        if (onsBalancoEnergiaNosSubsistemasEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsBalancoEnergiaNosSubsistemas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.save(onsBalancoEnergiaNosSubsistemasEntity);
        onsBalancoEnergiaNosSubsistemasSearchRepository.index(onsBalancoEnergiaNosSubsistemasEntity);
        return ResponseEntity.created(new URI("/api/ons-balanco-energia-nos-subsistemas/" + onsBalancoEnergiaNosSubsistemasEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsBalancoEnergiaNosSubsistemasEntity.getId().toString()
                )
            )
            .body(onsBalancoEnergiaNosSubsistemasEntity);
    }

    /**
     * {@code PUT  /ons-balanco-energia-nos-subsistemas/:id} : Updates an existing onsBalancoEnergiaNosSubsistemas.
     *
     * @param id the id of the onsBalancoEnergiaNosSubsistemasEntity to save.
     * @param onsBalancoEnergiaNosSubsistemasEntity the onsBalancoEnergiaNosSubsistemasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsBalancoEnergiaNosSubsistemasEntity,
     * or with status {@code 400 (Bad Request)} if the onsBalancoEnergiaNosSubsistemasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsBalancoEnergiaNosSubsistemasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsBalancoEnergiaNosSubsistemasEntity> updateOnsBalancoEnergiaNosSubsistemas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsBalancoEnergiaNosSubsistemasEntity onsBalancoEnergiaNosSubsistemasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsBalancoEnergiaNosSubsistemas : {}, {}", id, onsBalancoEnergiaNosSubsistemasEntity);
        if (onsBalancoEnergiaNosSubsistemasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsBalancoEnergiaNosSubsistemasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsBalancoEnergiaNosSubsistemasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsBalancoEnergiaNosSubsistemasEntity = onsBalancoEnergiaNosSubsistemasRepository.save(onsBalancoEnergiaNosSubsistemasEntity);
        onsBalancoEnergiaNosSubsistemasSearchRepository.index(onsBalancoEnergiaNosSubsistemasEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsBalancoEnergiaNosSubsistemasEntity.getId().toString()
                )
            )
            .body(onsBalancoEnergiaNosSubsistemasEntity);
    }

    /**
     * {@code PATCH  /ons-balanco-energia-nos-subsistemas/:id} : Partial updates given fields of an existing onsBalancoEnergiaNosSubsistemas, field will ignore if it is null
     *
     * @param id the id of the onsBalancoEnergiaNosSubsistemasEntity to save.
     * @param onsBalancoEnergiaNosSubsistemasEntity the onsBalancoEnergiaNosSubsistemasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsBalancoEnergiaNosSubsistemasEntity,
     * or with status {@code 400 (Bad Request)} if the onsBalancoEnergiaNosSubsistemasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsBalancoEnergiaNosSubsistemasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsBalancoEnergiaNosSubsistemasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsBalancoEnergiaNosSubsistemasEntity> partialUpdateOnsBalancoEnergiaNosSubsistemas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsBalancoEnergiaNosSubsistemasEntity onsBalancoEnergiaNosSubsistemasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsBalancoEnergiaNosSubsistemas partially : {}, {}",
            id,
            onsBalancoEnergiaNosSubsistemasEntity
        );
        if (onsBalancoEnergiaNosSubsistemasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsBalancoEnergiaNosSubsistemasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsBalancoEnergiaNosSubsistemasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsBalancoEnergiaNosSubsistemasEntity> result = onsBalancoEnergiaNosSubsistemasRepository
            .findById(onsBalancoEnergiaNosSubsistemasEntity.getId())
            .map(existingOnsBalancoEnergiaNosSubsistemas -> {
                if (onsBalancoEnergiaNosSubsistemasEntity.getIdSubsistema() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setIdSubsistema(onsBalancoEnergiaNosSubsistemasEntity.getIdSubsistema());
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getNomSubsistema() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setNomSubsistema(onsBalancoEnergiaNosSubsistemasEntity.getNomSubsistema());
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getDinInstante() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setDinInstante(onsBalancoEnergiaNosSubsistemasEntity.getDinInstante());
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getValGerhidraulica() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setValGerhidraulica(
                        onsBalancoEnergiaNosSubsistemasEntity.getValGerhidraulica()
                    );
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getValGertermica() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setValGertermica(onsBalancoEnergiaNosSubsistemasEntity.getValGertermica());
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getValGereolica() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setValGereolica(onsBalancoEnergiaNosSubsistemasEntity.getValGereolica());
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getValGersolar() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setValGersolar(onsBalancoEnergiaNosSubsistemasEntity.getValGersolar());
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getValCarga() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setValCarga(onsBalancoEnergiaNosSubsistemasEntity.getValCarga());
                }
                if (onsBalancoEnergiaNosSubsistemasEntity.getValIntercambio() != null) {
                    existingOnsBalancoEnergiaNosSubsistemas.setValIntercambio(onsBalancoEnergiaNosSubsistemasEntity.getValIntercambio());
                }

                return existingOnsBalancoEnergiaNosSubsistemas;
            })
            .map(onsBalancoEnergiaNosSubsistemasRepository::save)
            .map(savedOnsBalancoEnergiaNosSubsistemas -> {
                onsBalancoEnergiaNosSubsistemasSearchRepository.index(savedOnsBalancoEnergiaNosSubsistemas);
                return savedOnsBalancoEnergiaNosSubsistemas;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsBalancoEnergiaNosSubsistemasEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-balanco-energia-nos-subsistemas} : get all the onsBalancoEnergiaNosSubsistemas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsBalancoEnergiaNosSubsistemas in body.
     */
    @GetMapping("")
    public List<OnsBalancoEnergiaNosSubsistemasEntity> getAllOnsBalancoEnergiaNosSubsistemas() {
        LOG.debug("REST request to get all OnsBalancoEnergiaNosSubsistemas");
        return onsBalancoEnergiaNosSubsistemasRepository.findAll();
    }

    /**
     * {@code GET  /ons-balanco-energia-nos-subsistemas/:id} : get the "id" onsBalancoEnergiaNosSubsistemas.
     *
     * @param id the id of the onsBalancoEnergiaNosSubsistemasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsBalancoEnergiaNosSubsistemasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsBalancoEnergiaNosSubsistemasEntity> getOnsBalancoEnergiaNosSubsistemas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsBalancoEnergiaNosSubsistemas : {}", id);
        Optional<OnsBalancoEnergiaNosSubsistemasEntity> onsBalancoEnergiaNosSubsistemasEntity =
            onsBalancoEnergiaNosSubsistemasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsBalancoEnergiaNosSubsistemasEntity);
    }

    /**
     * {@code DELETE  /ons-balanco-energia-nos-subsistemas/:id} : delete the "id" onsBalancoEnergiaNosSubsistemas.
     *
     * @param id the id of the onsBalancoEnergiaNosSubsistemasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsBalancoEnergiaNosSubsistemas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsBalancoEnergiaNosSubsistemas : {}", id);
        onsBalancoEnergiaNosSubsistemasRepository.deleteById(id);
        onsBalancoEnergiaNosSubsistemasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-balanco-energia-nos-subsistemas/_search?query=:query} : search for the onsBalancoEnergiaNosSubsistemas corresponding
     * to the query.
     *
     * @param query the query of the onsBalancoEnergiaNosSubsistemas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsBalancoEnergiaNosSubsistemasEntity> searchOnsBalancoEnergiaNosSubsistemas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsBalancoEnergiaNosSubsistemas for query {}", query);
        try {
            return StreamSupport.stream(onsBalancoEnergiaNosSubsistemasSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
