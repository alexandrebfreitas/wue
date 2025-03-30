package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntity;
import com.alexandrebfreitas.wue.repository.OnsBalancoEnergiaDessemRepository;
import com.alexandrebfreitas.wue.repository.search.OnsBalancoEnergiaDessemSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntity}.
 */
@RestController
@RequestMapping("/api/ons-balanco-energia-dessems")
@Transactional
public class OnsBalancoEnergiaDessemResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsBalancoEnergiaDessemResource.class);

    private static final String ENTITY_NAME = "onsBalancoEnergiaDessem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsBalancoEnergiaDessemRepository onsBalancoEnergiaDessemRepository;

    private final OnsBalancoEnergiaDessemSearchRepository onsBalancoEnergiaDessemSearchRepository;

    public OnsBalancoEnergiaDessemResource(
        OnsBalancoEnergiaDessemRepository onsBalancoEnergiaDessemRepository,
        OnsBalancoEnergiaDessemSearchRepository onsBalancoEnergiaDessemSearchRepository
    ) {
        this.onsBalancoEnergiaDessemRepository = onsBalancoEnergiaDessemRepository;
        this.onsBalancoEnergiaDessemSearchRepository = onsBalancoEnergiaDessemSearchRepository;
    }

    /**
     * {@code POST  /ons-balanco-energia-dessems} : Create a new onsBalancoEnergiaDessem.
     *
     * @param onsBalancoEnergiaDessemEntity the onsBalancoEnergiaDessemEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsBalancoEnergiaDessemEntity, or with status {@code 400 (Bad Request)} if the onsBalancoEnergiaDessem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsBalancoEnergiaDessemEntity> createOnsBalancoEnergiaDessem(
        @RequestBody OnsBalancoEnergiaDessemEntity onsBalancoEnergiaDessemEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsBalancoEnergiaDessem : {}", onsBalancoEnergiaDessemEntity);
        if (onsBalancoEnergiaDessemEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsBalancoEnergiaDessem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.save(onsBalancoEnergiaDessemEntity);
        onsBalancoEnergiaDessemSearchRepository.index(onsBalancoEnergiaDessemEntity);
        return ResponseEntity.created(new URI("/api/ons-balanco-energia-dessems/" + onsBalancoEnergiaDessemEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsBalancoEnergiaDessemEntity.getId().toString())
            )
            .body(onsBalancoEnergiaDessemEntity);
    }

    /**
     * {@code PUT  /ons-balanco-energia-dessems/:id} : Updates an existing onsBalancoEnergiaDessem.
     *
     * @param id the id of the onsBalancoEnergiaDessemEntity to save.
     * @param onsBalancoEnergiaDessemEntity the onsBalancoEnergiaDessemEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsBalancoEnergiaDessemEntity,
     * or with status {@code 400 (Bad Request)} if the onsBalancoEnergiaDessemEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsBalancoEnergiaDessemEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsBalancoEnergiaDessemEntity> updateOnsBalancoEnergiaDessem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsBalancoEnergiaDessemEntity onsBalancoEnergiaDessemEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsBalancoEnergiaDessem : {}, {}", id, onsBalancoEnergiaDessemEntity);
        if (onsBalancoEnergiaDessemEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsBalancoEnergiaDessemEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsBalancoEnergiaDessemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.save(onsBalancoEnergiaDessemEntity);
        onsBalancoEnergiaDessemSearchRepository.index(onsBalancoEnergiaDessemEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsBalancoEnergiaDessemEntity.getId().toString())
            )
            .body(onsBalancoEnergiaDessemEntity);
    }

    /**
     * {@code PATCH  /ons-balanco-energia-dessems/:id} : Partial updates given fields of an existing onsBalancoEnergiaDessem, field will ignore if it is null
     *
     * @param id the id of the onsBalancoEnergiaDessemEntity to save.
     * @param onsBalancoEnergiaDessemEntity the onsBalancoEnergiaDessemEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsBalancoEnergiaDessemEntity,
     * or with status {@code 400 (Bad Request)} if the onsBalancoEnergiaDessemEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsBalancoEnergiaDessemEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsBalancoEnergiaDessemEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsBalancoEnergiaDessemEntity> partialUpdateOnsBalancoEnergiaDessem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsBalancoEnergiaDessemEntity onsBalancoEnergiaDessemEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsBalancoEnergiaDessem partially : {}, {}", id, onsBalancoEnergiaDessemEntity);
        if (onsBalancoEnergiaDessemEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsBalancoEnergiaDessemEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsBalancoEnergiaDessemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsBalancoEnergiaDessemEntity> result = onsBalancoEnergiaDessemRepository
            .findById(onsBalancoEnergiaDessemEntity.getId())
            .map(existingOnsBalancoEnergiaDessem -> {
                if (onsBalancoEnergiaDessemEntity.getIdSubsistema() != null) {
                    existingOnsBalancoEnergiaDessem.setIdSubsistema(onsBalancoEnergiaDessemEntity.getIdSubsistema());
                }
                if (onsBalancoEnergiaDessemEntity.getNomSubsistema() != null) {
                    existingOnsBalancoEnergiaDessem.setNomSubsistema(onsBalancoEnergiaDessemEntity.getNomSubsistema());
                }
                if (onsBalancoEnergiaDessemEntity.getDinInstante() != null) {
                    existingOnsBalancoEnergiaDessem.setDinInstante(onsBalancoEnergiaDessemEntity.getDinInstante());
                }
                if (onsBalancoEnergiaDessemEntity.getValDemanda() != null) {
                    existingOnsBalancoEnergiaDessem.setValDemanda(onsBalancoEnergiaDessemEntity.getValDemanda());
                }
                if (onsBalancoEnergiaDessemEntity.getValGeracaohidraulicamwmed() != null) {
                    existingOnsBalancoEnergiaDessem.setValGeracaohidraulicamwmed(
                        onsBalancoEnergiaDessemEntity.getValGeracaohidraulicamwmed()
                    );
                }
                if (onsBalancoEnergiaDessemEntity.getValGeracaopchmwmed() != null) {
                    existingOnsBalancoEnergiaDessem.setValGeracaopchmwmed(onsBalancoEnergiaDessemEntity.getValGeracaopchmwmed());
                }
                if (onsBalancoEnergiaDessemEntity.getValGeracaotermicamwed() != null) {
                    existingOnsBalancoEnergiaDessem.setValGeracaotermicamwed(onsBalancoEnergiaDessemEntity.getValGeracaotermicamwed());
                }
                if (onsBalancoEnergiaDessemEntity.getValGeracaopctmwmed() != null) {
                    existingOnsBalancoEnergiaDessem.setValGeracaopctmwmed(onsBalancoEnergiaDessemEntity.getValGeracaopctmwmed());
                }
                if (onsBalancoEnergiaDessemEntity.getValGeracaoeolicamwmed() != null) {
                    existingOnsBalancoEnergiaDessem.setValGeracaoeolicamwmed(onsBalancoEnergiaDessemEntity.getValGeracaoeolicamwmed());
                }
                if (onsBalancoEnergiaDessemEntity.getValGeracaofotovoltaicamwmed() != null) {
                    existingOnsBalancoEnergiaDessem.setValGeracaofotovoltaicamwmed(
                        onsBalancoEnergiaDessemEntity.getValGeracaofotovoltaicamwmed()
                    );
                }

                return existingOnsBalancoEnergiaDessem;
            })
            .map(onsBalancoEnergiaDessemRepository::save)
            .map(savedOnsBalancoEnergiaDessem -> {
                onsBalancoEnergiaDessemSearchRepository.index(savedOnsBalancoEnergiaDessem);
                return savedOnsBalancoEnergiaDessem;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsBalancoEnergiaDessemEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-balanco-energia-dessems} : get all the onsBalancoEnergiaDessems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsBalancoEnergiaDessems in body.
     */
    @GetMapping("")
    public List<OnsBalancoEnergiaDessemEntity> getAllOnsBalancoEnergiaDessems() {
        LOG.debug("REST request to get all OnsBalancoEnergiaDessems");
        return onsBalancoEnergiaDessemRepository.findAll();
    }

    /**
     * {@code GET  /ons-balanco-energia-dessems/:id} : get the "id" onsBalancoEnergiaDessem.
     *
     * @param id the id of the onsBalancoEnergiaDessemEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsBalancoEnergiaDessemEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsBalancoEnergiaDessemEntity> getOnsBalancoEnergiaDessem(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsBalancoEnergiaDessem : {}", id);
        Optional<OnsBalancoEnergiaDessemEntity> onsBalancoEnergiaDessemEntity = onsBalancoEnergiaDessemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsBalancoEnergiaDessemEntity);
    }

    /**
     * {@code DELETE  /ons-balanco-energia-dessems/:id} : delete the "id" onsBalancoEnergiaDessem.
     *
     * @param id the id of the onsBalancoEnergiaDessemEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsBalancoEnergiaDessem(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsBalancoEnergiaDessem : {}", id);
        onsBalancoEnergiaDessemRepository.deleteById(id);
        onsBalancoEnergiaDessemSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-balanco-energia-dessems/_search?query=:query} : search for the onsBalancoEnergiaDessem corresponding
     * to the query.
     *
     * @param query the query of the onsBalancoEnergiaDessem search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsBalancoEnergiaDessemEntity> searchOnsBalancoEnergiaDessems(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsBalancoEnergiaDessems for query {}", query);
        try {
            return StreamSupport.stream(onsBalancoEnergiaDessemSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
